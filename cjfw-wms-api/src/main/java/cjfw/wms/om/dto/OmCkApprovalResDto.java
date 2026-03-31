package cjfw.wms.om.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.09.26 
 * @description : CK주문결재내역 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.26 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class OmCkApprovalResDto {
			
	/** 체크여부 */
    @Schema(description = "체크여부", example = "")
    private String checkyn;

    /** 시리얼키 */
    @Schema(description = "시리얼키", example = "")
    private String serialkey;

    /** 요청번호 */
    @Schema(description = "요청번호", example = "")
    private String requestno;

    /** 요청라인 */
    @Schema(description = "요청라인", example = "")
    private String requestline;

    /** 납기일 */
    @Schema(description = "납기일", example = "")
    private String deliverydate;

    /** 전표일자 */
    @Schema(description = "전표일자", example = "")
    private String slipdt;

    /** 상품코드 */
    @Schema(description = "상품코드", example = "")
    private String sku;

    /** SKU 명 */
    @Schema(description = "SKU 명", example = "")
    private String skuname;

    /** 주문 수량 */
    @Schema(description = "주문 수량", example = "")
    private String orderqty;

    /** UOM  */
    @Schema(description = "UOM", example = "")
    private String uom;

    /** 승인 상태 */
    @Schema(description = "승인 상태", example = "")
    private String apprStatus;

    /** 등록자 ID */
    @Schema(description = "등록자 ID", example = "")
    private String addwho;

    /** 등록 일자 */
    @Schema(description = "등록 일자", example = "")
    private String adddate; 

    /** 수정자 ID */
    @Schema(description = "수정자 ID", example = "")
    private String editwho;

    /** 수정 일자 */
    @Schema(description = "수정 일자", example = "")
    private String editdate;

    /** 등록자명 */
    @Schema(description = "등록자명", example = "")
    private String regNm;

    /** 수정자명 */
    @Schema(description = "수정자명", example = "")
    private String updNm;

    /** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
		
}