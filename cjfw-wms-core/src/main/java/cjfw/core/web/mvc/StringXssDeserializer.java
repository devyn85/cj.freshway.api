package cjfw.core.web.mvc;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import cjfw.core.utility.XssCheckUtil;

import java.io.IOException;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.22
 * @description : StringXssDeserializer 기능을 구현한 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.22        sungyeon.lee       생성
 */
public class StringXssDeserializer extends StdDeserializer<String> {
    private static final long serialVersionUID = 2987222334137968631L;

    /**
     * 
     * @description : StringXssDeserializer의 생성자
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    public StringXssDeserializer() {
        this(null);
    }
    
    /**
     * 
     * @description : StringXssDeserializer의 생성자
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    protected StringXssDeserializer(Class<?> vc) {
        super(vc);
    }

    /**
     * 
     * @overridden  : @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext)
     * @description : deserialize 기능을 Override하여 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String origin = p.getValueAsString();
        return XssCheckUtil.stripXss(origin);  // XSS filter
    }
}
