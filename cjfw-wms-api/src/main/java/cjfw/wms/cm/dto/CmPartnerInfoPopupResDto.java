package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author :  
 * @date : 2026.01.23
 * @description : 협력사 정보(단건) 팝업조회 Response DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.01.23   생성 </pre>
 */
@Data
@Schema(description = "협력사 정보(단건) 팝업조회 Response DTO")
public class CmPartnerInfoPopupResDto {
    /** 협력사코드 */
    @Schema(description = "협력사코드")
    private String custkey;

    /** 협력사명 */
    @Schema(description = "협력사명")
    private String custname;

    /** 주소 */
    @Schema(description = "주소")
    private String addr;

    /** 사업자 번호 */
    @Schema(description = "사업자 등록 번호")
    private String vatno;

    /** 사업자등록업태 */
    @Schema(description = "사업자등록업태")
    private String vattype;
    
    
    /** PL3 사용여부 */
    @Schema(description = "PL3 사용여부")
    private String pl3UseYn;

    /** PL3 담당자명 */
    @Schema(description = "PL3 담당자명")
    private String pl3Mngempnm;

    /** 조달여부 */
    @Schema(description = "조달여부")
    private String deliveryYn; 
    
    /** 조달입차시간 */
    @Schema(description = "조달시간")
    private String deliveryInTime; 
    
    /** 대표전화번호 RPST_PHONE */
    @Schema(description = "대표전화번호")
    private String rpstPhone;

    /** 사업자 등록 종목 */
    @Schema(description = "사업자 등록 종목")
    private String vatcategory;

    /** PL3 사명 */
    @Schema(description = "PL3 사명")
    private String pl3nm;

    /** PL3 담당자전화번호 */
    @Schema(description = "PL3 담당자전화번호")
    private String pl3Phone;

    /** 조달상품저장유무 */
    @Schema(description = "조달상품저장유무")
    private String deliverySaveYn;

    /** 조달상품계약기간시작 */
    @Schema(description = "조달상품계약기간시작")
    private String deliveryCtrtTerm;

    /** 등록자명 */
    @Schema(description = "등록자명")
    private String addwhonm;

    /** 수정자명 */
    @Schema(description = "수정자명")
    private String editwhonm;

    /** 등록일시 */
    @Schema(description = "등록일시")
    private String adddate;

    /** 수정일시 */
    @Schema(description = "수정일시")
    private String editdate;

}