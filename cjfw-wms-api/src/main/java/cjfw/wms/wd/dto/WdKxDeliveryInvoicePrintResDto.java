package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.annotation.MaskingAddress;
import cjfw.wms.common.annotation.MaskingName;
import cjfw.wms.common.annotation.MaskingTelno;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.12.26
 * @description : 택배송장발행(온라인) response dto  - 레포트 데이터소스가 대문자임 
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.26 sss  생성 </pre>
 */
@Data
@Schema(description = "택배송장발행(온라인) response dto")
public class WdKxDeliveryInvoicePrintResDto extends CommonDto {


    /** 운송장번호(포맷) */
    @Schema(description = "운송장번호(포맷)")
    private String invoiceno;

    /** 접수일자 포맷(YYYY.MM.DD)-인쇄용 */
    @Schema(description = "접수일자 포맷(YYYY.MM.DD)-인쇄용")
    private String docdt2;

    /** 도착지코드 */
    @Schema(description = "도착지코드")
    private String barcode1;

    /** 운송장번호(포맷)-바코드 */
    @Schema(description = "운송장번호(포맷)-바코드")
    private String barcode2;

    /** 운송장번호(포맷)-바코드 */
    @Schema(description = "운송장번호(포맷)-바코드")
    private String barcode3;

    /** 도착지코드 */
    @Schema(description = "도착지코드")
    private String clsfcd;

    /** 도착지코드1 */
    @Schema(description = "도착지코드1")
    private String clsfcd1;

    /** 도착지코드2 */
    @Schema(description = "도착지코드2")
    private String clsfcd2;

    /** 수화인명 */
    @MaskingName
    @Schema(description = "수화인명")
    private String receiver;

    /** 수화인 전화번호 */
    @MaskingTelno
    @Schema(description = "수화인 전화번호")
    private String call1;

    /** 수화인 휴대폰번호 */
    @MaskingTelno
    @Schema(description = "수화인 휴대폰번호")
    private String call2;

    /** 수화인 주소 */
    @MaskingAddress
    @Schema(description = "수화인 주소")
    private String address1;

    /** 수화인 주소-상세 */
    @MaskingAddress
    @Schema(description = "수화인 주소-상세")
    private String order1;

    /** 발송자명 */
    @MaskingName
    @Schema(description = "발송자명")
    private String sender;

    /** 발송자명 */
    @MaskingName
    @Schema(description = "발송자명")
    private String fromCustname;

    /** 송화인 전화번호 */
    @MaskingTelno
    @Schema(description = "송화인 전화번호")
    private String call3;

    /** 박스수량-송장분리 후 수량 */
    @Schema(description = "박스수량-송장분리 후 수량")
    private String boxqty;

    /** 비용 */
    @Schema(description = "비용")
    private String fare1;

    /** 요금유형(01 선불, 02 착불, 03 신용) */
    @Schema(description = "요금유형(01 선불, 02 착불, 03 신용)")
    private String settlement1;

    /** 송화인 주소 */
    @MaskingAddress
    @Schema(description = "송화인 주소")
    private String sendrAddr;

    /** 상품명1 */
    @Schema(description = "상품명1")
    private String skuname1;

    /** 상품명2 */
    @Schema(description = "상품명2")
    private String skuname2;

    /** 상품명3 */
    @Schema(description = "상품명3")
    private String skuname3;

    /** 상품명4 */
    @Schema(description = "상품명4")
    private String skuname4;

    /** 주소약칭 */
    @Schema(description = "주소약칭")
    private String address3;

    /** 주문수량 */
    @Schema(description = "주문수량")
    private String totalqty;

    /** 배송 메시지 */
    @Schema(description = "배송 메시지")
    private String etcMsg;

    /** 배송집배점명 */
    @Schema(description = "배송집배점명")
    private String clldlvbrannm;

    /** SM분류코드 */
    @Schema(description = "SM분류코드")
    private String clldlvempnicknm;

    /** P2P코드 */
    @Schema(description = "P2P코드")
    private String tmp1;

    /** P2P코드 */
    @Schema(description = "P2P코드")
    private String tmp2;

    /** 현재미사용 */
    @Schema(description = "현재미사용")
    private String tmp3;

    /** 배송SM명 */
    @Schema(description = "배송SM명")
    private String clldlvempnm;

    /** 권역구분 */
    @Schema(description = "권역구분")
    private String rspsdiv;

    /** 접수일자 - 택배접수시각 */
    @Schema(description = "접수일자 - 택배접수시각")
    private String docdt;

    /** 문서번호 */
    @Schema(description = "문서번호")
    private String docno;

    /** 요청일자 - 접수일자(원: 납품요청일자) */
    @Schema(description = "요청일자 - 접수일자(원: 납품요청일자)")
    private String reqDate;
    
	/**충부피*/
	@Schema(description = "충부피")
	private BigDecimal totvolume;    
    
}
