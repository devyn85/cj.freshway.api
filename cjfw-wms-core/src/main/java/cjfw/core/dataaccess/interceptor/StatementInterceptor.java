package cjfw.core.dataaccess.interceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cjfw.core.exception.SystemException;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : intercpetor를 활용하여 SQL 로깅하는 기능을 구현한 Controller Class
 * 				  myBatis 에 interceptor 를 통해 sql 관련 정보를 얻어옴
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "batch", args = {Statement.class}),
		@Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
		@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class})})
public class StatementInterceptor implements Interceptor {

	private static final Logger log = LoggerFactory.getLogger(StatementInterceptor.class);

	/**
	 * 
	 * @overridden  : @see org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.Invocation)
	 * @description : intercept 기능을 Override하여 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		String sql = null;
		// 로그 레벨이 Debug 로 켜져있을 경우에만 로그를 남기도록 한다.  
		// 운영시에는 평상시에는 로그를 꺼두고 있다가 필요할 경우에는 서비스 리스트에 등록하여 로그 남기도록 한다.
		try {
			sql = getBoundSql(invocation);
			log.info(sql);
		} catch(Exception e) {
			//오류가 발생하여도 본 거래에는 영향을 주지 않도록 한다.
			log.error("StatementInterceptor.intercept.Exception : ", e);
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
	private String getBoundSql(Invocation invocation) {

		String result = null;
		try {
			StatementHandler handler = (StatementHandler)invocation.getTarget();
			BoundSql boundSql = handler.getBoundSql();

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
	 * 
	 * @overridden  : @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object)
	 * @description : plugin 기능을 Override하여 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
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
	@Override
	public void setProperties(Properties properties) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * 
	 * @description : (미사용-Map으로 Wrapping하는 Concept 지양) getClassType 기능을 구현한 Method
	 * 				  Map에서 key로 꺼낸 value 오브젝트의 Class Type 에 따라 쿼테이션이 포함된 데이터 문자열 리턴 
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
//	private String getClassType(@SuppressWarnings("rawtypes") Map param, String key) {
//		return getClassType(getValue(param, key));
//	}

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
	 * @description : (미사용-Map으로 Wrapping하는 Concept 지양) getValue 기능을 구현한 Method
	 * 				  key에 해당하는 value를 반환
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
//	private Object getValue(@SuppressWarnings("rawtypes") Map param, String key) {
//		if(key != null && !"".equals(key)) {
//			Object obj = param;
//			String[] keylist = key.split("\\.");
//
//			if(keylist.length == 0) {
//				obj = param.get(key);
//			} else {
//				for(int i = 0; i < keylist.length; i++) {
//					obj = getMappingObject(obj, keylist[i]);
//					if(obj == null) {
//						// 앞 index에서 수행한 내용 때문에 obj가 null이 되었다면?
//						break;
//					}
//				}
//			}
//			return obj;
//		} else {
//			return null;
//		}
//	}

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
}
