package cjfw.wms.sys.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.08.01 
 * @description : 인터페이스 상태관리 조회 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.01 JiSooKim (jskim14@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "공통코드 조회 응답")
public class SysIFManagerResDto {
	@Schema(description = "체크여부")
    private String checkYn;

    @Schema(description = "대상 시스템")
    private String targetSystem;

    @Schema(description = "인터페이스 ID")
    private String ifId;

    @Schema(description = "인터페이스 명")
    private String ifName;

    @Schema(description = "삭제 여부")
    private String delYn;

    @Schema(description = "원본 삭제 여부")
    private String orgDelYn;

    @Schema(description = "상태")
    private String status;

    @Schema(description = "원본 상태")
    private String orgStatus;

    @Schema(description = "시작일시")
    private String startDate;

    @Schema(description = "종료일시")
    private String endDate;

    @Schema(description = "마지막 실행일시")
    private String lastRun;

    @Schema(description = "실제 타입")
    private String realType;

    @Schema(description = "동기화 타입")
    private String syncType;

    @Schema(description = "메소드")
    private String method;

    @Schema(description = "EAI 관리 여부")
    private String eaiMngYn;
}