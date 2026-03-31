package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.09.10 
 * @description : 연비관리 기능을 구현한 RES DTO Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.10 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "연비관리 조회 resDto")
public class TmHjdDataListResDto {

	  /** 행정동코드 */
    @Schema(description = "행정동코드")
    private String hjdongCd;
    private String cdNm;
    /** 시도명 */
    @Schema(description = "시도명")
    private String ctpKorNm;
    /** 시도명 */
    @Schema(description = "부모코드")
    private String parentCd;

    /** 시군구명 */
    @Schema(description = "시군구명")
    private String sigKorNm;

    /** 행정동명 */
    @Schema(description = "행정동명")
    private String hjdongNm;

    /** 이동사유코드 */
    @Schema(description = "이동사유코드")
    private String mvmnResCd;

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
