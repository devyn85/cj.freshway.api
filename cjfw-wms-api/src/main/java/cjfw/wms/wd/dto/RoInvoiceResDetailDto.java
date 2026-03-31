package cjfw.wms.wd.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.16
 * @description : 반품명세서출력 Detail Response DTO Class (getDetailList)
 */
@Schema(description = "반품명세서출력 Detail Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoInvoiceResDetailDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

    /** 체크여부 */
    @Schema(description = "체크여부")
    private String checkyn;

    /** 화주코드 */
    @Schema(description = "화주코드")
    private String storerkey;

    /** 배송일자 */
    @Schema(description = "배송일자")
    private String deliverydt;

    /** 차량번호 */
    @Schema(description = "차량번호")
    private String carno;

    /** 우선순위 */
    @Schema(description = "우선순위")
    private String priority;

    /** 배차그룹 */
    @Schema(description = "배차그룹")
    private String deliverygroup;

    /** 거래처코드 */
    @Schema(description = "거래처코드")
    private String custkey;

    /** 거래처명 */
    @Schema(description = "거래처명")
    private String custname;

    /** 문서번호 */
    @Schema(description = "문서번호")
    private String docno;

    /** 세금계산서유형��� */
    @Schema(description = "세금계산서유형명")
    private String invoicetypedesc;

    /** 주문유형 */
    @Schema(description = "주문유형")
    private String ordertype;

    /** 출력여부 */
    @Schema(description = "출력여부")
    private String printyn;

    /** 세금계산서유형 */
    @Schema(description = "세금계산서유형")
    private String invoicetype;

    /** 전표일자 */
    @Schema(description = "전표일자")
    private String slipdt;

    /** 전표번호 */
    @Schema(description = "전표번호")
    private String slipno;
}
