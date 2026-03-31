package cjfw.wms.gwms.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cjfw.core.utility.ContextUtil;
import lombok.extern.log4j.Log4j2;


/**
 * Canal Framework version (no FrameOne Parameters).
 * - parseJsonToMap: JSON -> LinkedHashMap
 * - adapter: reflectively call {class}.{method}(Map<String,Object> inParams) and return Map
 * - toJsonString: Object -> JSON string
 */
@Log4j2
public class GwmsUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @SuppressWarnings("unchecked")
    public static LinkedHashMap<String, Object> parseJsonToMap(String json) throws Exception {
        try {
            return MAPPER.readValue(json, LinkedHashMap.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> adapter(String className, String methodName, Map<String, Object> inParams) {
        Map<String, Object> empty = new LinkedHashMap<>();

        try {
            // 1) 문자열로 넘어온 클래스 로드
            Class<?> clazz = Class.forName(className);

            // 2) 스프링이 관리하는 빈 인스턴스 획득 (절대 new 하지 않음)
            Object bean = ContextUtil.getBean(clazz);

            // 3) 호출할 메서드 탐색: (Map) -> (Object) -> () 순으로 시도
            Method m = findMethod(clazz, methodName);
            if (m == null) {
                log.error("No suitable method found: {}.{}(Map|Object|)", className, methodName);
                return empty;
            }

            // 4) 인자 결정
            Object result;
            if (m.getParameterCount() == 1) {
                Class<?> p0 = m.getParameterTypes()[0];
                if (Map.class.isAssignableFrom(p0)) {
                    result = m.invoke(bean, inParams);
                } else {
                    result = m.invoke(bean, inParams); // Object 등 단일 파라미터
                }
            } else {
                result = m.invoke(bean);
            }

            if (result instanceof Map) {
                return (Map<String, Object>) result;
            }
            log.warn("Method returned non-Map. class={}, method={}, actualType={}",
                    className, methodName, (result == null ? "null" : result.getClass().getName()));
            return empty;

        } catch (Throwable t) {
            log.error("adapter invoke error: class={}, method={}, msg={}", className, methodName, t.toString(), t);
            return empty;
        }
    }

    /** (Map) -> (Object) -> () 순으로 메서드 탐색 */
    private static Method findMethod(Class<?> clazz, String methodName) {
        try {
            return clazz.getMethod(methodName, Map.class);
        } catch (NoSuchMethodException ignored) {}

        try {
            return clazz.getMethod(methodName, Object.class);
        } catch (NoSuchMethodException ignored) {}

        try {
            return clazz.getMethod(methodName);
        } catch (NoSuchMethodException ignored) {}

        return null;
    }

    public static String toJsonString(Object value) {
        try {
            return MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> castList(Object o) {
        if (o instanceof List) return (List<Map<String, Object>>) o;
        return new ArrayList<>();
    }

    public static Map<String, Object> first(List<Map<String, Object>> list) {
        if (list == null || list.isEmpty()) return Collections.emptyMap();
        return list.get(0);
    }

    public static Object val(Map<String, Object> m, String k) {
        return (m == null) ? null : m.get(k);
    }


    /**
     * List<Map>의 모든 키를 소문자로 변환해서 반환합니다.
     * - 입력이 null이면 빈 리스트 반환
     * - 각 요소가 null이면 건너뜀
     * - 키가 null이면 그대로 null 키로 둠
     * - 동일 키가 대소문자만 다른 경우, 나중 항목이 덮어씁니다.
     * - 키 순서를 유지하기 위해 LinkedHashMap 사용
     */
    public static List<Map<String, Object>> listKeyChangeLower(List<? extends Map<String, ?>> rslt) {
        if (rslt == null || rslt.isEmpty()) return Collections.emptyList();
        List<Map<String,Object>> newList = new ArrayList<>();
        for (Map<String, ?> stringMap : rslt) {
            HashMap<String, Object> tm = new HashMap<>(stringMap);
            Iterator<String> iteratorKey = tm.keySet().iterator();
            Map<String, Object> newMap = new HashMap<>();

            while (iteratorKey.hasNext()) {
                String key = iteratorKey.next();
                newMap.put(key.toLowerCase(), tm.get(key));
            }
            newList.add(newMap);
        }

        return newList;
    }

    public static String makeParam(Map<String, Object> map){
        String retrunMsg="";
        for(Map.Entry<String, Object> entry : map.entrySet()){
            retrunMsg += "<" + entry.getKey() + ">" + entry.getValue() + "</" + entry.getKey() + ">";
        }
        return retrunMsg;

    }

    static int toInt(Object v) {
        if (v == null) return 0;
        try { return Integer.parseInt(v.toString().trim()); }
        catch (Exception ignore) { return 0; }   // 숫자 아님 → 0
    }
}
