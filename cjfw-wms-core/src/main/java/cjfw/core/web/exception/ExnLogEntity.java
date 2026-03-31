package cjfw.core.web.exception;

import lombok.Data;

/**
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : wisujang
 * @date : 2023.05.25
 * @description : Exception Log 저장을 위한 Class
 * @issues :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.05.25        wisujang       생성
 */
@Data
public class ExnLogEntity {
    private String excptCnts; // 예외내용
    private String clntAddr; // 클라이언트 주소
    private String svrAddr; // 서버 주소
    private String callUri; // 호출URI
}
