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
 * @description : 반품명세서출력 Print Master Response DTO Class (getPrintList)
 */
@Schema(description = "반품명세서출력 Print Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoInvoiceResPrintDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

    /** /** * 배송일자 */
    @Schema(description = "* 배송일자")
    private String deliverydt;

    /** * 공급자사업자번호 */
    @Schema(description = "* 공급자사업자번호")
    private String fromVatno;

    /** * 공급자상호 */
    @Schema(description = "* 공급자상호")
    private String fromVatname;

    /** * 공급자대표자 */
    @Schema(description = "* 공급자대표자")
    private String fromVatowner;

    /** * 공급자업태 */
    @Schema(description = "* 공급자업태")
    private String fromVattype;

    /** * 공급자업종 */
    @Schema(description = "* 공급자업종")
    private String fromVatcategory;

    /** * 공급자주소 */
    @Schema(description = "* 공급자주소")
    private String fromVataddress;

    /** * 거래처코드 */
    @Schema(description = "* 거래처코드")
    private String toCustkey;

    /** * 거래처사업자번호 */
    @Schema(description = "* 거래처사업자번호")
    private String toVatno;

    /** * 거래처상호 */
    @Schema(description = "* 거래처상호")
    private String toVatname;

    /** * 거래처대표자 */
    @Schema(description = "* 거래처대표자")
    private String toVatowner;

    /** * 거래처주소 */
    @Schema(description = "* 거래처주소")
    private String toVataddress;

    /** * 거래처주소1 */
    @Schema(description = "* 거래처주소1")
    private String toVataddress1;

    /** * 거래처주소2 */
    @Schema(description = "* 거래처주소2")
    private String toVataddress2;

    /** * 거래처업태 */
    @Schema(description = "* 거래처업태")
    private String toVattype;

    /** * 거래처업종 */
    @Schema(description = "* 거래처업종")
    private String toVatcategory;

    /** * CS담당자 */
    @Schema(description = "* CS담당자")
    private String csPerson;

    /** * CS전화번호 */
    @Schema(description = "* CS전화번호")
    private String csTel;

    /** * 담당자명 */
    @Schema(description = "* 담당자명")
    private String empname;

    /** * 담당자전화번호 */
    @Schema(description = "* 담당자전화번호")
    private String empphone;

    /** * 센터코드 */
    @Schema(description = "* 센터코드")
    private String fromCustkey;

    /** * 센터명 */
    @Schema(description = "* 센터명")
    private String fromCustname;

    /** * 센터주소 */
    @Schema(description = "* 센터주소")
    private String fromAddress;

    /** * 차량번호 */
    @Schema(description = "* 차량번호")
    private String carno;

    /** * 배차그룹 */
    @Schema(description = "* 배차그룹")
    private String deliverygroup;

    /** * 기사명 */
    @Schema(description = "* 기사명")
    private String drivername;

    /** * 기사전화번호 */
    @Schema(description = "* 기사전화번호")
    private String driverPhone;

    /** * 납품처명 */
    @Schema(description = "* 납품처명")
    private String toCustname;

    /** * 납품처전화번호 */
    @Schema(description = "* 납품처전화번호")
    private String toPhone;

    /** * 납품처주소 */
    @Schema(description = "* 납품처주소")
    private String toAddress;

    /** * 납품처주소1 */
    @Schema(description = "* 납품처주소1")
    private String toAddress1;

    /** * 납품처주소2 */
    @Schema(description = "* 납품처주소2")
    private String toAddress2;

    /** * 세금계산서유형 */
    @Schema(description = "* 세금계산서유형")
    private String invoicetype;

    /** * 문서유형 */
    @Schema(description = "* 문서유형")
    private String doctype;

    /** * 헤더키 */
    @Schema(description = "* 헤더키")
    private String headerkey;

    /** 비고 */
    @Schema(description = "비고")
    private String memo1;
}
