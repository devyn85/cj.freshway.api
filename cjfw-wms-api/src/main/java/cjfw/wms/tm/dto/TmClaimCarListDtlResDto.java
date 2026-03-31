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
public class TmClaimCarListDtlResDto extends CommonDto{


    /** 일련번호 */
    @Schema(description = "일련번호")
    private Long serialKey;

    /** 고객사코드 */
    @Schema(description = "고객사코드")
    private String storerKey;

    /** SAP클레임번호 */
    @Schema(description = "SAP클레임번호")
    private String sapClaimNo;

    /** SAP클레임품목번호 */
    @Schema(description = "SAP클레임품목번호")
    private String sapClaimLine;

    /** 데이터번호 */
    @Schema(description = "데이터번호")
    private Long claimSeq;

    /** 조치일시 */
    @Schema(description = "조치일시")
    private String actionDate;

    /** 조치자명 */
    @Schema(description = "조치자명")
    private String actionWho;

    /** 방법 */
    @Schema(description = "방법")
    private String actionMethod;

    /** 조치내용 */
    @Schema(description = "조치내용")
    private String actionDesc;

    /** 상세내용 */
    @Schema(description = "상세내용")
    private String actionDetailDesc;

    /** 비고 */
    @Schema(description = "비고")
    private String rmk;

    /** 비고 */
    @Schema(description = "비고")
    private String mobileDispYn;

    /** 최초등록시간 */
    @Schema(description = "최초등록시간")
    private String addDate;

    /** 최종변경시간 */
    @Schema(description = "최종변경시간")
    private String editDate;

    /** 최초등록자 */
    @Schema(description = "최초등록자")
    private String addWho;

    /** 최종변경자 */
    @Schema(description = "최종변경자")
    private String editWho;

    /** 등록자명 */
    @Schema(description = "등록자명")
    private String addWhoName;

    /** 수정자명 */
    @Schema(description = "수정자명")
    private String editWhoName;
}
