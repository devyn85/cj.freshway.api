package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.07 
 * @description : 배송클레임정보 조회Dto 기능을 구현한 Java Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.07 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "배송클레임정보 조회 Dto")
public class TmClaimCarListResDto {

	   /** 선택 */
    @Schema(description = "선택")
    private String checkYn;

    /** VOC번호 */
    @Schema(description = "VOC번호")
    private String vocNo;

    /** 물류센터 */
    @Schema(description = "물류센터")
    private String dcName;

    /** 차량번호 */
    @Schema(description = "차량번호")
    private String carNo;

    /** 센터명 */
    @Schema(description = "센터명")
    private String centerName;

    /** 배송일자 */
    @Schema(description = "배송일자")
    private String deliveryDt;

    /** 관리처코드 */
    @Schema(description = "관리처코드")
    private String custKey;

    /** 관리처명 */
    @Schema(description = "관리처명")
    private String custName;

    /** VOC분류 */
    @Schema(description = "VOC분류")
    private String claimDiv;

    /** 담당MA */
    @Schema(description = "담당MA")
    private String maName;

    /** 등록자 */
    @Schema(description = "등록자")
    private String vocName;

    /** 메모 */
    @Schema(description = "메모")
    private String memo;

    /** 등록일시 */
    @Schema(description = "등록일시")
    private String vocDt;
    
    @Schema(description = "상태값")
    private String rowStatus;
    
    @Schema(description = "물류센터")
    private String fixDcCode;
    
    /** 클래임 대 */
    @Schema(description = "클레임 대")
    private String claimDtlIdL;
    
    /** 클래임 중 */
    @Schema(description = "클레임 중")
    private String claimDtlIdM;
    
    /** 클래임 소 */
    @Schema(description = "클레임 소")
    private String claimDtlIdS;
    
    /** 클래임 세 */
    @Schema(description = "클레임 세")
    private String claimdtlId;
    
    /** 클래임 대 */
    @Schema(description = "클레임 대")
    private String claimDtlIdLNm;
    
    /** 클래임 중 */
    @Schema(description = "클레임 중")
    private String claimDtlIdMNm;
    
    /** 클래임 소 */
    @Schema(description = "클레임 소")
    private String claimDtlIdSNm;
    
    /** 클래임 세 */
    @Schema(description = "클레임 세")
    private String claimdtlIdNm;
}
