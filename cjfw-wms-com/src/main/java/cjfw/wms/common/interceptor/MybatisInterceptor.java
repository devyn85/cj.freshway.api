package cjfw.wms.common.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cjfw.auth.jwt.JwtAuthenticationFilter;
import cjfw.core.exception.SystemException;
import cjfw.core.utility.ContextUtil;
import cjfw.wms.cm.dto.CmUbaReqDto;
import cjfw.wms.cm.service.CmUbaService;
import cjfw.wms.common.annotation.MaskingAcntno;
import cjfw.wms.common.annotation.MaskingAddress;
import cjfw.wms.common.annotation.MaskingAll;
import cjfw.wms.common.annotation.MaskingBirthday;
import cjfw.wms.common.annotation.MaskingBizno;
import cjfw.wms.common.annotation.MaskingCardno;
import cjfw.wms.common.annotation.MaskingDrvno;
import cjfw.wms.common.annotation.MaskingEmail;
import cjfw.wms.common.annotation.MaskingEngName;
import cjfw.wms.common.annotation.MaskingId;
import cjfw.wms.common.annotation.MaskingIdntNo;
import cjfw.wms.common.annotation.MaskingInner;
import cjfw.wms.common.annotation.MaskingIp;
import cjfw.wms.common.annotation.MaskingName;
import cjfw.wms.common.annotation.MaskingPassPort;
import cjfw.wms.common.annotation.MaskingQrcd;
import cjfw.wms.common.annotation.MaskingSsn;
import cjfw.wms.common.annotation.MaskingTelno;
import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.annotation.SQLInjection;
import cjfw.wms.common.extend.CommonDto;
import cjfw.wms.common.util.EtcUtil;
import cjfw.wms.common.util.MaskingUtil;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.com) 
 * @date : 2025.06.16 
 * @description : myBatis 관련 인터셉트 기능을 구현한 Controller Class 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.16 SangSuSung(kduimux@cj.com) 생성 </pre>
 */
@Intercepts
(
    {
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
       ,@Signature(type = Executor.class, method = "query",  args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})
       ,@Signature(type = Executor.class, method = "query",  args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
    }
)
public class MybatisInterceptor implements Interceptor {
	private static final Logger log = LoggerFactory.getLogger(MybatisInterceptor.class);
	
	/** SQL_TYPES */
    public static final String SQL_TYPES =
            "TABLE, TABLESPACE, PROCEDURE, FUNCTION, TRIGGER, KEY, VIEW, MATERIALIZED VIEW, LIBRARY" +
            "DATABASE LINK, DBLINK, INDEX, CONSTRAINT, TRIGGER, USER, SCHEMA, DATABASE, PLUGGABLE DATABASE, BUCKET, " +
            "CLUSTER, COMMENT, SYNONYM, TYPE, JAVA, SESSION, ROLE, PACKAGE, PACKAGE BODY, OPERATOR" +
            "SEQUENCE, RESTORE POINT, PFILE, CLASS, CURSOR, OBJECT, RULE, USER, DATASET, DATASTORE, " +
            "COLUMN, FIELD, OPERATOR";
    
    // SQL 타입 패턴 (SQL_TYPES는 콤마로 구분된 문자열이라고 가정)
    private static final String SQL_TYPES_PATTERN = SQL_TYPES.replace(",", "|")+ ")\\b\\s.*";
    
    
    /** sqlRegexps */
    private static final String[] SQLREGEXPS = {
            "(?i)(.*)(\\b)+SELECT(\\b)+\\s.*(\\b)+FROM(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+INSERT(\\b)+\\s.*(\\b)+INTO(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+UPDATE(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+DELETE(\\b)+\\s.*(\\b)+FROM(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+UPSERT(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+SAVEPOINT(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+CALL(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+ROLLBACK(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+KILL(\\b)+\\s.*(.*)",
            "(?i)(.*)(\\b)+DROP(\\b)+\\s.*(.*)",
            
            //"(?i)(.*)(\\b)+CREATE(\\b)+(\\s)*(" + SQL_TYPES.replaceAll(",", "|") + ")(\\b)+\\s.*(.*)",
            //"(?i)(.*)(\\b)+ALTER(\\b)+(\\s)*(" + SQL_TYPES.replaceAll(",", "|") + ")(\\b)+\\s.*(.*)",
            //"(?i)(.*)(\\b)+TRUNCATE(\\b)+(\\s)*(" + SQL_TYPES.replaceAll(",", "|") + ")(\\b)+\\s.*(.*)",
            //"(?i)(.*)(\\b)+LOCK(\\b)+(\\s)*(" + SQL_TYPES.replaceAll(",", "|") + ")(\\b)+\\s.*(.*)",
            //"(?i)(.*)(\\b)+UNLOCK(\\b)+(\\s)*(" + SQL_TYPES.replaceAll(",", "|") + ")(\\b)+\\s.*(.*)",
            //"(?i)(.*)(\\b)+RELEASE(\\b)+(\\s)*(" + SQL_TYPES.replaceAll(",", "|") + ")(\\b)+\\s.*(.*)",
            
            // SQL Types에 대한 정규식은 아래와 같이 변경
            "(?i)\\bCREATE\\b\\s+(" + SQL_TYPES_PATTERN ,
            "(?i)\\bALTER\\b\\s+(" + SQL_TYPES_PATTERN ,
            "(?i)\\bTRUNCATE\\b\\s+(" + SQL_TYPES_PATTERN ,
            "(?i)\\bLOCK\\b\\s+(" + SQL_TYPES_PATTERN ,
            "(?i)\\bUNLOCK\\b\\s+(" + SQL_TYPES_PATTERN ,
            "(?i)\\bRELEASE\\b\\s+(" + SQL_TYPES_PATTERN ,
            
            "(?i)(.*)(\\b)+DESC(\\b)+(\\w)*\\s.*(.*)",
            "(?i)(.*)(\\b)+DESCRIBE(\\b)+(\\w)*\\s.*(.*)",
            "(.*)(/\\*|\\*/|;){1,}(.*)",
            "(.*)(-){2,}(.*)",
            "/[\\t\\r\\n]|(--[^\\r\\n]*)|(\\/\\*[\\w\\W]*?(?=\\*)\\*\\/)/gi\r\n",
    };	
    // pre-build the Pattern objects for faster validation
    private final List<Pattern> validationPatterns = getValidationPatterns();
    
    /**
     * 조회 결과가 1건인 경우 마스킹을 적용할지 여부 (default : true)	
     */
    private static final boolean ONE_RESULT_MASKING_YN = true; 
    

	/**
	 * @overridden : @see org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.Invocation) 
	 * @description : intercept 기능을 Override하여 구현한 Method 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.14 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		String sql = null; 
		String method = invocation.getMethod().getName();
		Object[] args = invocation.getArgs();
		MappedStatement mappedStat = (MappedStatement)args[0];
		
		String mapperId = mappedStat.getId();
		Boolean nomasking = false;		
		
		if(args.length > 1) {
			if(args[1] instanceof CommonDto) {
				nomasking = ((CommonDto)args[1]).isNoMasking();
				Object dto = args[1];
				if(!isSqlInjectSafe(dto)) { // check SQL injeciton
					throw new org.springframework.validation.BindException(dto, "BindException");
				}
			}
		}			
		
		// 로그 레벨이 Debug 로 켜져있을 경우에만 로그를 남기도록 한다.  
		// 운영시에는 평상시에는 로그를 꺼두고 있다가 필요할 경우에는 서비스 리스트에 등록하여 로그 남기도록 한다.
		try {
	        String queryID = ((MappedStatement) invocation.getArgs()[0]).getId();
	        Object param = invocation.getArgs()[1];
	        BoundSql boundSql = ((MappedStatement) invocation.getArgs()[0]).getBoundSql(param); 	
	        
			try {
				sql = getBoundSql(boundSql);
			} catch(Exception e) {
				//오류가 발생하여도 본 거래에는 영향을 주지 않도록 한다.
				log.error("StatementInterceptor.intercept.Exception : ", e);
			}	        
	        
	        log.info("\n/* {} */\n{}", queryID, sql);

	        //for (int i = 0; i < boundSql.getParameterMappings().size(); i++) {
	        //    ParameterMapping parameterMapping = boundSql.getParameterMappings().get(i);
	        //    Object bindObject = getObject(boundSql.getParameterObject(), parameterMapping);
            //
	        //    log.info("binding [{}] ->  [{}]", i, bindObject);
            //
	        //}
		} catch(Exception e) {
			//오류가 발생하여도 본 거래에는 영향을 주지 않도록 한다.
			log.error("StatementInterceptor.intercept.Exception : ", e);
		}
		//////////////////////////////////////////////////
		
		if("query".equals(method)) {
			if(!nomasking && !mapperId.contains("!selectKey")) {
				try {
					Object resultSet = invocation.proceed();
					if(resultSet instanceof List) {
						@SuppressWarnings("unchecked")
						List<Object> resultList = (List<Object>)resultSet;
						
						int loop = resultList.size();
						for(int i=0; i<loop; i++) {
							Object dto = resultList.get(i);
							
							if(dto instanceof CommonDto) {
								if(loop == 1 && ONE_RESULT_MASKING_YN) break; // 20251001@1건일 경우에는 개인정보 미처리 BY SSS (김*문님)
								masking(dto);
							}
						}
					} else if(resultSet instanceof CommonDto && ONE_RESULT_MASKING_YN) { 
						masking(resultSet);  // 20251001@1건일 경우에는 개인정보 미처리 BY SSS (김*문님)
					}
					return resultSet;
				} catch (RuntimeException e) {
					log.error("check invocation.proceed() process1!!"); 
				}				
			} else if (nomasking) {
				Object dto = args[1];
				CmUbaReqDto cmUbaReqDto = new CmUbaReqDto();
				cmUbaReqDto.setUserId(cmUbaReqDto.getGUserId()); // 행위자의 시스템 ID
				if (cmUbaReqDto.getGEmailAddr() != null && !"".equals(cmUbaReqDto.getGEmailAddr())) {
					cmUbaReqDto.setEmailAlias(cmUbaReqDto.getGEmailAddr()); // 행위자의 CJWorld ID
				} else {
					cmUbaReqDto.setEmailAlias("N"); // 이메일 정보가 없는 사용자일 경우 "N" 값 설정
				}
				cmUbaReqDto.setUserName(cmUbaReqDto.getGUserNm()); // 행위자 이름
				cmUbaReqDto.setEmployeeYn("01".equals(cmUbaReqDto.getGEmptype()) ? "Y" : "N"); // 임직원 여부 구분 (임직원:Y, 그외:N)
				cmUbaReqDto.setClientIp(JwtAuthenticationFilter.ipHolder.get()); // 접속 Client IP
				cmUbaReqDto.setMenuName(mapperId); // 접근 메뉴명
				cmUbaReqDto.setActionCode("1"); // 조회:1, 수정:2, 삭제:3, 출력:4, 다운로드:5
				cmUbaReqDto.setSearchItem("2"); // 고유식별정보:1 / 그외:2(고유식별정보 : 주민등록번호/여권번호/운전면허번호/외국인등록번호 등)
				cmUbaReqDto.setQCount("1"); // 행위 1건에 포함되는 고객정보 개수
				if(dto instanceof CommonDto) {
					cmUbaReqDto.setSearchId(((CommonDto) dto).getNoMaskingLabel()); // 행위에 대하여 처리되는 대상(고객) 식별자(고객 ID, 이름, 회원번호, 송장번호 등)
				}
				
				// 그룹정보유출보안관제(UBA) 로그 기록
				CmUbaService cmUbaService = (CmUbaService) ContextUtil.getBean(CmUbaService.class);
				cmUbaService.saveUbaLog(cmUbaReqDto);
			}
		}		
		
		return invocation.proceed();
	}
	
	/**
	 * 
	 * @description : 파라미터가 매핑된 SQL문 반환 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	protected String getBoundSql(BoundSql boundSql) {

		String result = null;
		try {

			String sql = boundSql.getSql();

			// Object obj = handler.getParameterHandler().getParameterObject();
			List<ParameterMapping> parameterList = boundSql.getParameterMappings();

			Object paramObejct = boundSql.getParameterObject();
			StringBuilder sb = new StringBuilder();

			//sql 문자열에서 ? 를 split 한 후에 클래스 타입에 맞게 싱글쿼테이션을 붙여서(숫자의 경우는 붙이지 않음) 문자열을 조합한다.
			String[] splitedSql = sql.split("\\?");
			if(parameterList.size() > 0) {
				for(int i = 0; i < parameterList.size(); i++) {
					ParameterMapping parameterMapping = parameterList.get(i);

					// foreach 구문으로 바인딩하는 파라미터는
					// __frch_xxx_0, __frch_xxx_1 과 같은 프로퍼티 명을 가진다
					if(parameterMapping.getProperty().indexOf("__frch_") > -1) {
						sb.append(splitedSql[i]).append(
								getClassType(boundSql.getAdditionalParameter(parameterMapping.getProperty())));
					} else {
						sb.append(splitedSql[i]).append(getClassType(paramObejct, parameterMapping.getProperty()));
					}
				}

				if(splitedSql.length - parameterList.size() > 0) {
					sb.append(splitedSql[splitedSql.length - 1]);
				}
			} else {
				sb.append(sql);
			}
			result = sb.toString();
		} catch(Exception e) {
			log.error("StatementInterceptor.getBoundSql.Exception : ", e);
		}
		return result;
	}	
	
	/**
	 * @description : 마스킹 기능을 구현한 Method 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.16 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	private void masking(Object dto) {
		try {
			Field[] dtoFields = dto.getClass().getDeclaredFields();
			for(Field fld : dtoFields) {
				//String only
				if(String.class.isAssignableFrom(fld.getType()) && fld.getDeclaredAnnotations().length > 0) {
					Annotation anno =  fld.getDeclaredAnnotations()[0];
					fld.setAccessible(true);
					Object val = fld.get(dto);
					if(val == null) continue;
					
					//log.info("FIELD '{}' - {} / {}", fld.getName(), fld.getType(), anno);
				
					if(anno instanceof MaskingId) {
						fld.set(dto, MaskingUtil.maskingId((String)val));
					} else if(anno instanceof MaskingName) {
						fld.set(dto, MaskingUtil.maskingName((String)val));
					} else if(anno instanceof MaskingEngName) {
						fld.set(dto, MaskingUtil.maskingEngName((String)val));
					}
					if(anno instanceof MaskingTelno) {
						fld.set(dto, MaskingUtil.maskingTelno((String)val));
					}
					else if(anno instanceof MaskingEmail) {
						fld.set(dto, MaskingUtil.maskingEmail((String)val));
					}
					else if(anno instanceof MaskingSsn) {
						fld.set(dto, MaskingUtil.maskingSsn((String)val));
					}
					else if(anno instanceof MaskingDrvno) {
						fld.set(dto, MaskingUtil.maskingDrvno((String)val));
					}
					else if(anno instanceof MaskingBirthday) {
						fld.set(dto, MaskingUtil.maskingBirthday((String)val));
					}
					else if(anno instanceof MaskingCardno) {
						fld.set(dto, MaskingUtil.maskingCardno((String)val));
					}
					else if(anno instanceof MaskingAcntno) {
						fld.set(dto, MaskingUtil.maskingAcntno((String)val));
					}
					else if(anno instanceof MaskingBizno) {
						fld.set(dto, MaskingUtil.maskingBizno((String)val));
					}
					else if(anno instanceof MaskingIp) {
						fld.set(dto, MaskingUtil.maskingIp((String)val));
					}
					if(anno instanceof MaskingAll) {
						fld.set(dto, MaskingUtil.maskingAll((String)val));
					}
					if(anno instanceof MaskingInner) {
						fld.set(dto, MaskingUtil.maskingInner((String)val));
					}
					else if(anno instanceof MaskingQrcd) {
						fld.set(dto, MaskingUtil.maskingQrcd((String)val));
					}
					else if(anno instanceof MaskingPassPort) {
						fld.set(dto, MaskingUtil.maskingPassPort((String)val));
					}
					else if(anno instanceof MaskingAddress) {
						fld.set(dto, MaskingUtil.maskingAddress((String)val));
					}
					else if(anno instanceof MaskingIdntNo) {
						String myVal = (String)val;
						
						if(myVal.length() >= 16 && myVal.length() <= 20) {
							fld.set(dto, MaskingUtil.maskingCardno(myVal));
						} else if(myVal.length() == 11) {
							fld.set(dto, MaskingUtil.maskingTelno(myVal));
						} else if(myVal.length() == 13) {
							fld.set(dto, MaskingUtil.maskingSsn(myVal));
						} else if(myVal.length() == 10) {
							fld.set(dto, MaskingUtil.maskingBizno(myVal));
						}
						
					}
					
				}
			}
		} catch (RuntimeException e) {
			log.error("check masking process1!!"); 
		}
		catch (Exception e) {
			log.error("check masking process2!!"); 
		}
	}	

	/**
	 * @overridden : @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object) 
	 * @description : plugin 기능을 wrap하여 Override하여 구현한 Method
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.16 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/**
	 * 
	 * @overridden  : @see org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties)
	 * @description : setProperties 기능을 Override하여 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	
	/**
	 * @overridden : @see org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties) 
	 * @description : setProperties 기능을 Override하여 구현한 Method
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.16 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	@Override
	public void setProperties(Properties properties) {
		int i = 0;
		if(i > 1) log.info("");
	}


	/**
	 * 
	 * @description : getClassType 기능을 구현한 Method
	 * 				  Object에서 꺼낸 value 오브젝트의 Class Type 에 따라 쿼테이션이 포함된 데이터 문자열 리턴
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private String getClassType(Object obj, String key) {
		return getClassType(getMappingObject(obj, key));
	}

	/**
	 * 
	 * @description : getClassType 기능을 구현한 Method
	 * 				  매개변수로 받은 오브젝트의 Class Type 에 따라 쿼테이션이 포함된 데이터 문자열 리턴 
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private String getClassType(Object obj) {
		StringBuilder sb = new StringBuilder();
		if(obj == null) {
			return "null";
		}

		@SuppressWarnings("rawtypes")
		Class classType = obj.getClass();

		//숫자형 (실수형)
		if(classType.equals(BigDecimal.class) || classType.equals(Float.class) || classType.equals(Double.class)
				|| classType.equals(Integer.class) || classType.equals(Long.class)) {
			sb.append(obj);

		//날짜형, 문자형
		} else {
			sb.append("'").append(obj).append("'");
		}
		return sb.toString();
	}


	/**
	 * 
	 * @description : getMappingObject 기능을 구현한 Method
	 * 				  Object 타입이 Map일 때와 VO일 때를 구분하여 key 에 해당하는 값을 반환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private Object getMappingObject(Object obj, String key) {
		if(obj instanceof Map) {
			//Map 인 경우
			return ((Map)obj).get(key);
		} else {
			/**
			 * get 메소드를 통하여 변수값을 획득함
			 */
			Object result = null;
			String methodName = "get" + StringUtils.capitalize(key);
			Method method = getMethod(obj.getClass(), methodName, new Object[]{});
			try {
				result = method.invoke(obj, new Object[]{});
			} catch(IllegalAccessException e) {
				log.error("StatementInterceptor.getMappingObject.IllegalAccessException : ", e);
			} catch(IllegalArgumentException e) {
				log.error("StatementInterceptor.getMappingObject.IllegalArgumentException : ", e);
			} catch(InvocationTargetException e) {
				log.error("StatementInterceptor.getMappingObject.InvocationTargetException : ", e);
			}
			return result;
		}
	}
	
	/**
	 * 
	 * @description : getMethod 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private Method getMethod(Class<?> clazz, String methodName, Object... params) {
		Method result = null;
		Class<?>[] clazzes = new Class[params.length];
		for(int i = 0; i < params.length; i++) {
			clazzes[i] = params[i].getClass();
		}
		try {
			result = clazz.getMethod(methodName, clazzes);
		} catch(SecurityException e) {
			log.error("StatementInterceptor.getMethod.SecurityException : ", e);
			throw new SecurityException(e);
		} catch(NoSuchMethodException e) {
			log.error("StatementInterceptor.getMethod.NoSuchMethodException : ", e);
			throw new SystemException(e);
		}
		return result;
	}
	
	/**
	 * @description : SQL 인젝션 방지 기능을 구현한 Method 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.16 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	private boolean isSqlInjectSafe(Object dto) {
		try {
			Field[] dtoFields = dto.getClass().getDeclaredFields();
			for(Field fld : dtoFields) {
				//String only
				if((String.class.isAssignableFrom(fld.getType()) || List.class.isAssignableFrom(fld.getType())) &&		 
				   fld.getDeclaredAnnotations().length > 0) {
					Annotation anno =  fld.getDeclaredAnnotations()[0];
					fld.setAccessible(true);
					Object val = fld.get(dto);
					//log.info("FIELD '{}' - {} / {}", fld.getName(), fld.getType(), anno);
					if(val == null) continue;
					
				
					if (anno instanceof SQLInjection) { 
						if(val instanceof String) {
							if (!isSqlInjectionSafe((String) val)) {
								log.error("SQLInjection1.val->{}", val);
								return false;
							} 
						} else if(val instanceof List) {
							List<?> arrayList = (List<?>)val;
							for (int i = 0; i < arrayList.size(); i++) {
								Object v = arrayList.get(i);
								log.info("V->"+v);
								if(v instanceof String) {
									if (!isSqlInjectionSafe((String) v)) {
										log.error("SQLInjection2.v->{}", v);
										return false;
									} 
								}
							}
						}
					}
				
				}
			}
		} catch (RuntimeException e) {
			log.error("check checkSqlInject process1!!"); 
		}
		catch (Exception e) {
			log.error("check masking process2!!"); 
		}
		
		return true;
	}	
	
	
	
	/**
	 * @description : SQLInjection을 사용한 기능을 구현한 Method 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.16 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
    private boolean isSqlInjectionSafe(String dataString){
        if(isEmpty(dataString)){
            return true;
        }

        for(Pattern pattern : validationPatterns){
            if(matches(pattern, dataString)){
                return false;
            }
        }
        return true;
	}

	private static List<Pattern> getValidationPatterns() {
		List<Pattern> patterns = new ArrayList<Pattern>();
		for (String sqlExpression : SQLREGEXPS) {
			patterns.add(getPattern(sqlExpression));
		}
		return patterns;
	}

	private static Pattern getPattern(String regEx) {
		return Pattern.compile(regEx, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
	}

	private boolean isEmpty(CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	private boolean matches(Pattern pattern, String dataString) {
		Matcher matcher = pattern.matcher(dataString);
		return matcher.matches();
	}
	
	private Object getObject(Object parameterObject, ParameterMapping parameterMapping) throws IllegalAccessException, NoSuchFieldException {
        if (parameterObject instanceof Number || parameterObject instanceof String)
            return parameterObject;
        else if (parameterObject instanceof Map) {
            String property = parameterMapping.getProperty();
            return ((Map<?, ?>) parameterObject).get(property);
        } else {
            Class<?> aClass = parameterObject.getClass();
            String property = parameterMapping.getProperty();
            Field declaredField = null;
            try {
                declaredField = aClass.getDeclaredField(property);
            } catch (NoSuchFieldException e) {
                while (aClass.getSuperclass() != null) {
                    aClass = aClass.getSuperclass();
                    try {
                        declaredField = aClass.getDeclaredField(property);
                        break;
                    } catch (NoSuchFieldException ignored) {
                    }
                }
            }

            if (declaredField == null)
                throw new NoSuchFieldException(property);

            declaredField.setAccessible(true);
            return declaredField.get(parameterObject);
        }
    }
	
	
		
}
