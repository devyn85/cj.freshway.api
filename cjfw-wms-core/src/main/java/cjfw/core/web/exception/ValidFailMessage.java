package cjfw.core.web.exception;

import lombok.Builder;
import lombok.Getter;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.22
 * @description : ValidFailMessage 기능을 구현한 Class
 * 				  @Valid 실패 정보
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.22        sungyeon.lee       생성
 */
@Getter
@Builder
public class ValidFailMessage {
    private String rejectField;
    private Object rejectValue;
    private String message;
}
