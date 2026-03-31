package cjfw.core.utility;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import cjfw.core.model.UserContext;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.22
 * @description : ModelMapperUtil 기능을 구현한 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.22        sungyeon.lee       생성
 */
@Slf4j
public class ModelMapperUtil {

	/**
	 * 
	 * @description : ModelMapperUtil의 생성자
	 * 				  * Utility classes should not have public constructor
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	private ModelMapperUtil() {
		throw new IllegalStateException("Utility class");
	}
		
    // 공통 매핑 키
    private static final Map<String, String> operMap = Map.of (
            "regId", "userId",
            "updId", "userId",
            "addwho", "userId",
            "addWho", "userId",
            "editwho", "userId",
            "editWho", "userId"
    );

    /**
     * 
     * @description : getModelMapper 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    private static ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    /**
     * 
     * @description : map 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    public static <T> T map(Object source, Class<T> targetClass) {
        return getModelMapper().map(source, targetClass);
    }

    /**
     * 
     * @description : map 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    public static <T> T map(Object source, UserContext userContext, Class<T> targetClass) {
        ModelMapper modelMapper = getModelMapper();
        UserContext clone = userContext.toBuilder().build();
        T t = modelMapper.map(source, targetClass);
        setUserContextInfo(clone, t);
        return t;
    }

    /**
     * 
     * @description : setUserContextInfo 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    private static void setUserContextInfo(UserContext source, Object destination) {
        if (destination == null) {
            return;
        }
        final Class<UserContext> srcClazz = UserContext.class;
        final Class<?> destClazz = destination.getClass();
        Iterator<String> keyEntry = operMap.keySet().iterator();
        while(keyEntry.hasNext()) {
            String k = keyEntry.next();
            String v = operMap.get(k);
            try {
                Field destField = destClazz.getDeclaredField(k);
                Field srcField = srcClazz.getDeclaredField(v);
                srcField.setAccessible(true);
                destField.setAccessible(true);
                Object value = srcField.get(source);
                Object destValue = destField.get(destination);
//                if (!isEmpty(value) && isEmpty(destValue)) {
                // 기존 값 있더라도 overwrite 되게 설정
            	if (!isEmpty(value)) {
                    destField.set(destination, value);
                }
            } catch (NoSuchFieldException e) {
                log.info("ModelMapperUtil::setLoginInfo: ", k);
            } catch (IllegalAccessException e) {
                log.info("ModelMapperUtil::setLoginInfo: ", k);
            }
        }
    }

    /**
     * 
     * @description : isEmpty 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    private static boolean isEmpty(Object value) {
        return (value == null || "".equals(value));
    }
}
