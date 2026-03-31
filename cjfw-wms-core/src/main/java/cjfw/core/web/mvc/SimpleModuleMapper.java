package cjfw.core.web.mvc;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.22
 * @description : SimpleModuleMapper 기능을 구현한 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.22        sungyeon.lee       생성
 */
public class SimpleModuleMapper extends ObjectMapper {
    private static final long serialVersionUID = -3444588157207824639L;

    /**
     * 
     * @description : SimpleModuleMapper의 생성자
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    public SimpleModuleMapper() {
        SimpleModule simpleModule = new SimpleModule();
        //dateTypeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        simpleModule.addDeserializer(String.class, new StringXssDeserializer());
        simpleModule.addSerializer(String.class, new XssDecodeSerializer()); // XSS 문자열에 대한 디코드 추가
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        registerModule(simpleModule);
        //setPropertyNamingStrategy(new JsonNamingStrategy());
        enable(MapperFeature.USE_STD_BEAN_NAMING);
    }
}
