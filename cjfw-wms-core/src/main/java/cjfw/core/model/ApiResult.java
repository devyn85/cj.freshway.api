package cjfw.core.model;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.StringUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.20
 * @description : ApiResult 기능을 구현한 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.20        sungyeon.lee       생성
 */
@Data
@Schema(description = "API Result")
public class ApiResult<T> {
    @Schema(description = "상태 코드")
    private int statusCode;
    @Schema(description = "상태 메시지")
    private String statusMessage;
    @Schema(description = "응답 객체")
    private T data;

    /**
     * 
     * @description : createResult 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.20        sungyeon.lee       생성
     */
    public static <T> ApiResult<T> createResult() {
        return createResult(null, "", CanalFrameConstants.ERROR_CODE_SUCCESS);
    }

    /**
     * 
     * @description : createResult 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.20        sungyeon.lee       생성
     */
    public static <T> ApiResult<T> createResult(int status) {
        return createResult(null, "", status);
    }

    /**
     * 
     * @description : createResult 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.20        sungyeon.lee       생성
     */
    public static <T> ApiResult<T> createResult(T data) {
        return createResult(data, "", CanalFrameConstants.ERROR_CODE_SUCCESS);
    }
    
    /**
     * 
     * @description : createResult 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.20        sungyeon.lee       생성
     */
    public static <T> ApiResult<T> createResult(String messageCode) {
        return createResult(null, messageCode, CanalFrameConstants.ERROR_CODE_SUCCESS);
    }
    
    /**
     * 
     * @description : createResult 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.20        sungyeon.lee       생성
     */
    public static <T> ApiResult<T> createResult(T data, String messageCode) {
        return createResult(data, messageCode, CanalFrameConstants.ERROR_CODE_SUCCESS);
    }
    
    /**
     * 
     * @description : createResult 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.20        sungyeon.lee       생성
     */
    public static <T> ApiResult<T> createResult(T data, int status) {
        return createResult(data, "", status);
    } 

    /**
     * 
     * @description : createResult 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.20        sungyeon.lee       생성
     */
    public static <T> ApiResult<T> createResult(String messageCode, int status) {
        return createResult(null, MessageUtil.getMessage(messageCode), status);
    }

    /**
     * 
     * @description : createResult 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.20        sungyeon.lee       생성
     */
    public static <T> ApiResult<T> createResult(T data, String messageCode, int status) {
        if(StringUtil.isEmpty(messageCode) && data != null){
            messageCode = "MSG.COM.SUC.011"; // Default 조회 메시지 코드
        }
        ApiResult<T> apiResult = new ApiResult<T>();
        apiResult.setStatusCode(status);
        
        if(status == -1) { // -1 : [고정]개발자 exception 일 경우 status이고 messageCode에 메세지명이 들어옴
        	apiResult.setStatusMessage(messageCode);
        } else {
        	apiResult.setStatusMessage(MessageUtil.getMessage(messageCode));
        }
        
        apiResult.setData(data);

        return apiResult;
    }

/////////    참조
//    public static class ResultBuilder {
//        Map<String, Object> jsonResultMap;
//        public ResultBuilder() {
//            jsonResultMap = new HashMap<>();
//        }
//        public Json<Map<String, Object>> build() {
//            Json<Map<String, Object>> jsonResult = new Json<>();
//            jsonResult.setResult(jsonResultMap);
//            return jsonResult;
//        }
//        public JsonBuilder map(String key, Object value) {
//            jsonResultMap.put(key, value);
//            return this;
//        }
//        public static JsonBuilder builder() {
//            return new JsonBuilder();
//        }
//    }
//

//    public static <T> Result<T> createErrorJson(String messageCode) {
//        return createErrorJson(MessageUtil.getMessage(messageCode), HttpStatus.NO_CONTENT.value());
//    }
//
//    public static <T> Result<T> createErrorJson(String messageCode, HttpStatus status) {
//        return createErrorJson(MessageUtil.getMessage(messageCode, new Object[] {}), status.value());
//    }
//
//    public static <T> Result<T> createErrorJson(String messageCode, Object[] args) {
//        return createErrorJson(MessageUtil.getMessage(messageCode, args), HttpStatus.NO_CONTENT.value());
//    }
//
//    public static <T> Result<T> createErrorJson(String messageCode, Object[] args, HttpStatus status) {
//        return createErrorJson(MessageUtil.getMessage(messageCode, args), status.value());
//    }
//
//    public static <T> Result<T> createErrorJson(T result, String messageCode) {
//        return createErrorJson(result, MessageUtil.getMessage(messageCode), HttpStatus.NO_CONTENT.value());
//    }
//
//    public static <T> Result<T> createErrorJson(T result, String messageCode, Object[] args) {
//        return createErrorJson(result, MessageUtil.getMessage(messageCode, args), HttpStatus.NO_CONTENT.value());
//    }
//
//    public static <T> Result<T> createErrorJson(T result, String messageCode, Object[] args, HttpStatus status) {
//        return createErrorJson(result, MessageUtil.getMessage(messageCode, args), status.value());
//    }
//
//    public static <T> Result<T> createErrorJson(String message, int status) {
//        Json<T> json = new Json<T>();
//        json.setMessage(message);
//        json.setStatus(status);
//
//        return json;
//    }
//
//    public static <T> Result<T> createErrorJson(T result, String message, int status) {
//        Json<T> json = new Json<T>();
//        json.setResult(result);
//        json.setMessage(message);
//        json.setStatus(status);
//
//        return json;
//    }
//
//    public Boolean isError() {
//        error = (status != HttpStatus.OK.value());
//        return error;
//    }

}
