package cjfw.wms.cm.service;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.cm.dto.CmLabelPriorityReqDto;
import cjfw.wms.wd.dto.WdDeliveryLabelResTab1PrintDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved. 
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.05.09 
 * @description : 프로그램별 그리드 정열 및 출력물 정열 공통 기능을 구현한 Service Class 
 * @issues : <pre> 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 sss (kduimux@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmLabelPriorityService {
	private transient static final String SERVICEID_PREFIX = "cmLabelPriorityService.";
	
	private final CommonDao commonDao;
	
	/**
	 * @description : 프로그램별 그리드 정열 순서 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss  생성 </pre>
	 */
	public <R, T> List<R> getDataSortList(CmLabelPriorityReqDto reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDataSortList", reqDto);
	}
	
	/**
	 * @description : 프로그램별 정열 컬럼 세팅
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss  생성 </pre>
	 */
	public <T> void setSortPrdOrd(CmLabelPriorityReqDto condDto, T targetDto) {
		List<HashMap<String, Object>> sortList = commonDao.selectList(SERVICEID_PREFIX + "getDataSortList", condDto);
		if (sortList == null || sortList.isEmpty()) {
			return;
		}

		Map<String, Object> sortMap = sortList.get(0);
		for (int i = 1; i <= 8; i++) {
			String key = "PRD_ORD" + i;
			String setter = "setPrdOrd" + i;
			Object value = sortMap.get(key);
			invokeSetter(targetDto, setter, value);
		}
	}
	
	private void invokeSetter(Object target, String methodName, Object value) {
	    try {
	        Method m = target.getClass().getMethod(methodName, String.class);
	        m.invoke(target, value == null ? "" : value.toString());
	    } catch (NoSuchMethodException e) {
	        // 해당 DTO에 없으면 그냥 무시
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	/**
	 * @description : 정열순에 의한 List 수동 정열
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss  생성 </pre>
	 */
	public <R, T> List<R> setListSort(CmLabelPriorityReqDto reqDto,List<T> list) {
		if(list != null || list.size() > 0) {
			// 정렬순서 조회
			List<HashMap> sortList = commonDao.selectList(SERVICEID_PREFIX + "getDataSortList", reqDto);
			Map<String, Object> sortMap = sortList.get(0);
			
		    reqDto.setPrdOrd1(Optional.ofNullable(sortMap.get("PRD_ORD1")).map(Object::toString).orElse(""));
		    reqDto.setPrdOrd2(Optional.ofNullable(sortMap.get("PRD_ORD2")).map(Object::toString).orElse(""));
		    reqDto.setPrdOrd3(Optional.ofNullable(sortMap.get("PRD_ORD3")).map(Object::toString).orElse(""));
		    reqDto.setPrdOrd4(Optional.ofNullable(sortMap.get("PRD_ORD4")).map(Object::toString).orElse(""));
		    reqDto.setPrdOrd5(Optional.ofNullable(sortMap.get("PRD_ORD5")).map(Object::toString).orElse(""));
		    reqDto.setPrdOrd6(Optional.ofNullable(sortMap.get("PRD_ORD6")).map(Object::toString).orElse(""));
		    reqDto.setPrdOrd7(Optional.ofNullable(sortMap.get("PRD_ORD7")).map(Object::toString).orElse(""));
		    reqDto.setPrdOrd8(Optional.ofNullable(sortMap.get("PRD_ORD8")).map(Object::toString).orElse(""));
		    
		    // 1. 정렬 기준 컬럼 리스트 추출 (Snake -> Camel 변환)
		    List<String> sortColumns = Stream.of("PRD_ORD1", "PRD_ORD2", "PRD_ORD3", "PRD_ORD4", 
		                                         "PRD_ORD5", "PRD_ORD6", "PRD_ORD7", "PRD_ORD8")
		        .map(key -> (String) sortMap.get(key))
		        .filter(val -> val != null && !val.trim().isEmpty()) // 값이 있는 것만 필터링
		        .map(this::snakeToCamel) // Camel Case로 변환
		        .collect(Collectors.toList());
	
		    // 2. Comparator를 이용한 동적 정렬
		    if (!sortColumns.isEmpty()) {
		    	list.sort((o1, o2) -> {
		            for (String property : sortColumns) {
		                try {
		                    // Reflection을 사용하여 getter 호출 (Field 직접 접근도 가능하나 getter 권장)
		                    Object val1 = new PropertyDescriptor(property, WdDeliveryLabelResTab1PrintDto.class).getReadMethod().invoke(o1);
		                    Object val2 = new PropertyDescriptor(property, WdDeliveryLabelResTab1PrintDto.class).getReadMethod().invoke(o2);
	
		                    if (val1 == null && val2 == null) continue;
		                    if (val1 == null) return -1;
		                    if (val2 == null) return 1;
	
		                    int compare = ((Comparable<Object>) val1).compareTo(val2);
		                    if (compare != 0) return compare; // 값이 다르면 정렬 결과 반환, 같으면 다음 우선순위 컬럼으로
		                    
		                } catch (Exception e) {
		                    log.error("Sorting error on field: " + property, e);
		                }
		            }
		            return 0;
		        });
		    }			
		}
		return (List<R>) list;
	}	
	
	/**
	 * @description : 스네이크 케이스(Snake Case)를 카멜 케이스(Camel Case)로 변환
	 * @param str 변환할 스네이크 케이스 문자열
	 * @return 카멜 케이스로 변환된 문자열
	 */
	private String snakeToCamel(String str) {
	    if (str == null || str.isEmpty()) return "";
	    StringBuilder result = new StringBuilder();
	    boolean nextUpper = false;
	    for (char c : str.toLowerCase().toCharArray()) {
	        if (c == '_') {
	            nextUpper = true;
	        } else {
	            if (nextUpper) {
	                result.append(Character.toUpperCase(c));
	                nextUpper = false;
	            } else {
	                result.append(c);
	            }
	        }
	    }
	    return result.toString();
	}
}
