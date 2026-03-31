package cjfw.wms.common.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import cjfw.wms.common.util.MaskingUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : sss (kduimux@cj.net)
 * @date : 2025.08.25
 * @description :다중선택된 파라미터를 Array 변경하는 클래스 ->ASIS ParamArrayManager를 spring
 *              AOP로 변경
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Aspect
@Component
@Slf4j
public class ParamArrayManagerAop {
	/**
	 * @description : 멀티 OR POST FIX - Multi
	 */
	public static final String MULTI_POST_FIX = "Multi";
	/**
	 * @description : 다중OR검색시 최대 사이즈
	 */
	public static final int IN_OR_SEARCH_SIZE = 999;

	@Before("execution(* *..service.*Service.*(..))")
	public void paramCallService(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();

		/**
		 * @MultiSearch 있는 skuMutil 변수를 다중OR검색을 위한 List<List<String>> 형태 값 세팅
		 */
		for (Object arg : args) {
			// 인자 중에서 특정 DTO 타입인지 확인하고 처리
			if (arg instanceof CommonDto) { // MyDto는 실제 사용할 DTO 클래스명으로 변경
				CommonDto dto = (CommonDto) arg; // DTO로 형변환

				Field[] dtoFields = dto.getClass().getDeclaredFields();
				for (Field fld : dtoFields) {
					// List only
					if (List.class.isAssignableFrom(fld.getType()) && fld.getDeclaredAnnotations().length > 0) {
						Annotation anno = fld.getDeclaredAnnotations()[0];
						fld.setAccessible(true);
						try {
							if (anno instanceof MultiSearch) {
								// 상품.다중OR검색 변수 세팅(sku -> skuMulti를 lI)
								prepareMulti(dto, fld.getName());
							}
						} catch (IllegalArgumentException e) {
							log.error("Reflection error on field1: " + fld.getName(), e);
						}

					}
				}
				
				if(log.isDebugEnabled()){ // Overhead issue를 위해 조건 추가
					log.info("▶Dto data->{}", MaskingUtil.maskLog(dto.toString()));
				}
			}
		}
	}

	/**
	 * 다중OR검색 하기 위한 List<List<String>> 형태로 변환하는 메서드 IN은 1000개 까지 가능하지만 OR방식 조합으로 으로
	 * 하면 5000개도 가능함.
	 * 
	 * <pre>
	 *   1. 배치사이즈가 2일 경우
	 *	   -> [a,b,c,d,e] -> [[a,b],[c,d],[e]]
	 *	 2. 수순
	 *     ->@MultiSearch
	 *	     private List<List<String>> skuMulti; 업무 dto에 변수 추가
	 *     ->쿼리수정  
	 *	     <!-- START.상품.다중OR검색 --> ...
	 * </pre>
	 * 
	 * @param dto
	 * @param val(단건      변수)
	 * @param mutilVal(멀티 변수)
	 */
	public static <T> void prepareMulti(T dto, String mutilVal) {
		java.lang.reflect.Field dtoField = null;
		// skuMulti -> sku
		String val = mutilVal.endsWith(MULTI_POST_FIX)
				? mutilVal.substring(0, mutilVal.length() - MULTI_POST_FIX.length())
				: mutilVal;

		try {
			Class<?> tDto = dto.getClass();
			String csv = "";
			try {
				dtoField = tDto.getDeclaredField(val);
				if (dtoField != null) dtoField.setAccessible(true);
			} catch (NoSuchFieldException e) {
				log.error("Reflection error NoSuchFieldException: {}", val, e);
//				e.printStackTrace();
			} catch (SecurityException e) {
				log.error("Reflection error SecurityException: {}", val, e);
			}

			try {
				if (dtoField != null) csv = (String) dtoField.get(dto);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				log.error("Reflection error on field0: {}", val, e);
			}

			if (csv == null || csv.isBlank())
				return;

			// "a,b,c,d,e" -> [a,b,c,d,e]
			List<String> items = Arrays.stream(csv.split(",")).map(String::trim).filter(s -> !s.isEmpty()).distinct()
					.limit(5000).collect(Collectors.toList());
			if (items.isEmpty())
				return;

			// log.info("csv->"+csv);

			int batchSize = IN_OR_SEARCH_SIZE;
			// int batchSize = 5; // for test
			List<List<String>> groups = new ArrayList<>();
			// 배치사이즈가 2일 경우
			// [a,b,c,d,e] -> [[a,b],[c,d],[e]]

			// 배치사이즈가 999일 경우
			// [a,b,c,d,e,...,x] -> [[a,b,c,d,e,...,x]]
			for (int i = 0; i < items.size(); i += batchSize) {
				groups.add(items.subList(i, Math.min(i + batchSize, items.size())));
			}

			try {
				java.lang.reflect.Field multiField = null;
				try {
					multiField = tDto.getDeclaredField(mutilVal);
					multiField.setAccessible(true);

					if (isListOfList(multiField)) {
						multiField.set(dto, groups);
					} else {
						multiField.set(dto, items);
					}

				} catch (Exception e) {
					while (tDto.getSuperclass() != null) {
						tDto = tDto.getSuperclass();
						try {
							multiField = tDto.getDeclaredField(mutilVal);
							multiField.setAccessible(true);

							if (isListOfList(multiField)) {
								multiField.set(dto, groups);
							} else {
								multiField.set(dto, items);
							}

							break;
						} catch (NoSuchFieldException ignored) {
						}
					}
				}

			} catch (IllegalAccessException e) {
				log.error("Reflection error on field1: {}", val, e);
			}

		} catch (Exception e) {
			log.error("Reflection error on field2: {}", val, e);
		}

	}
	
	/**
	 * List 객체여부 조회
	 * 
	 * @param field
	 * @return
	 */
	public static boolean isListOfList(Field field) {
		if (List.class.isAssignableFrom(field.getType())) {
			Type genericType = field.getGenericType();
			if (genericType instanceof ParameterizedType) {
				Type innerType = ((ParameterizedType) genericType).getActualTypeArguments()[0];
				if (innerType instanceof ParameterizedType
						&& ((ParameterizedType) innerType).getRawType() == List.class) {
					return true;
				}
			}
		}
		return false;
	}

}
