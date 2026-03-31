package cjfw.wms.tm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.06.30 
 * @description : 배차마스터체크결과 조회 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.04 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor            
@AllArgsConstructor
@Schema(description = "배차작업로그 요청 DTO")
public class TmAllocationCheckMasterReqDto extends CommonDto  {
	   /** 시스템 구분 */
    @Schema(description = "시스템 코드(예: WMS, TMS 등)")
    private String avcSystem;

    /** 실행 모드 */
    @Schema(description = "실행 모드")
    private String avcExecuteMode;

    /** 명령 */
    @Schema(description = "실행 명령")
    private String avcCommand;

    /** DC 코드 */
    @Schema(description = "센터 코드")
    private String avcDcCode;

    /** 화주사 */
    @Schema(description = "화주사(STORERKEY)")
    private String avcStorerKey;

    /** 조직 */
    @Schema(description = "조직 코드")
    private String avcOrganize;

    /** 권역 */
    @Schema(description = "권역 코드")
    private String avcArea;

    /** 요청 코드 */
    @Schema(description = "요청 코드")
    private String avcRequestCode;

    /** 요청 메시지 */
    @Schema(description = "요청 메시지")
    private String avcRequestMsg;

    /** 작업자 */
    @Schema(description = "작업자 ID")
    private String avcWorker;

    /** 보안 키 */
    @Schema(description = "보안 키")
    private String avcSecurityKey;

    /** 세션‧프로세스 ID */
    @Schema(description = "세션/프로세스 ID")
    private Integer aiSpid;

    /** 실행 단계 */
    @Schema(description = "실행 단계")
    private Integer iExecuteStep;
    
    /** 요청 메시지 */
    @Schema(description = "배송일자")
    private String pvcDeliveryDt;
    
    private String dcCode;

}
