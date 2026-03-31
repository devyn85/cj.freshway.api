package cjfw.wms.wd.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.10 
 * @description : 이력상품출고현황 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.10 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "이력상품출고현황 목록 결과")
public class WdInplanSNResDto {
	/**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "DC001")
    private String dccode;
    /**
     * 물류센터명
     */
    @Schema(description = "물류센터명", example = "")
    private String dccodeName;

    /**
     * 창고
     */
    @Schema(description = "창고", example = "ORG001")
    private String organize;
    
    /**
     * 창고명
     */
    @Schema(description = "창고명", example = "")
    private String organizeName;

    /**
     * 출고일자
     */
    @Schema(description = "출고일자", example = "2025-06-10")
    private String slipdt;

    /**
     * 주문유형
     */
    @Schema(description = "주문유형", example = "정상출고")
    private String ordertype;

    /**
     * 주문사유
     */
    @Schema(description = "주문사유", example = "일반판매")
    private String potype;

    /**
     * 주문번호
     */
    @Schema(description = "주문번호", example = "ORD20250610001")
    private String docno;

    /**
     * 영업조직
     */
    @Schema(description = "영업조직", example = "영업1팀")
    private String saleorganize;

    /**
     * 사업장
     */
    @Schema(description = "사업장", example = "본사")
    private String saledepartment;

    /**
     * 영업그룹
     */
    @Schema(description = "영업그룹", example = "온라인")
    private String salegroup;

    /**
     * 판매처코드
     */
    @Schema(description = "판매처코드", example = "VAT001")
    private String toVatno;

    /**
     * 판매처명
     */
    @Schema(description = "판매처명", example = "롯데마트")
    private String toVatowner;

    /**
     * 관리처코드
     */
    @Schema(description = "관리처코드", example = "CUST001")
    private String toCustKey;

    /**
     * 관리처명
     */
    @Schema(description = "관리처명", example = "홍길동")
    private String toCustName;

    /**
     * 진행상태
     */
    @Schema(description = "진행상태", example = "처리중")
    private String status;

    /**
     * POP번호
     */
    @Schema(description = "POP번호", example = "POP001")
    private String deliverygroup;

    /**
     * 차량번호
     */
    @Schema(description = "차량번호", example = "123가4567")
    private String carno;

    /**
     * STORERKEY
     */
    @Schema(description = "STORERKEY", example = "STORER001")
    private String storerkey;

    /**
     * DOCDT
     */
    @Schema(description = "DOCDT", example = "2025-06-10")
    private String docdt;

    /**
     * DOCTYPE
     */
    @Schema(description = "DOCTYPE", example = "DOCCL1")
    private String doctype;

    /**
     * SLIPNO
     */
    @Schema(description = "SLIPNO", example = "SLIP001")
    private String slipno;

    /**
     * SLIPTYPE
     */
    @Schema(description = "SLIPTYPE", example = "TYPEA")
    private String sliptype;

    /**
     * CUSTGROUP
     */
    @Schema(description = "CUSTGROUP", example = "CUSTGRP01")
    private String custgroup;

    /**
     * DEL_YN
     */
    @Schema(description = "DEL_YN", example = "N")
    private String delYn;
}
