package cjfw.wms.wd.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.10.15 
 * @description : 이력배송라벨출력-분류표출력 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.15 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "이력배송라벨출력-분류표출력 목록 결과")
public class WdDeliveryLabelSNResTab2Dto extends CommonDto{
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	/**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "01")
    private String dccode;
    /**
     * 물류센터명
     */
    @Schema(description = "물류센터명", example = "강남DC")
    private String dcname;
    /**
     * 관리처코드
     */
    @Schema(description = "관리처코드", example = "CUSTKEY001")
    private String toCustkey;
    /**
     * 납품처명1
     */
    @Schema(description = "납품처명1", example = "(R)CJ제일제당")
    private String lblCustname1;
    /**
     * 납품처명2
     */
    @Schema(description = "납품처명2", example = "( 10EA/BOX )")
    private String lblCustname2;
    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "SKU12345")
    private String lblSku;
    /**
     * 상품명1
     */
    @Schema(description = "상품명1", example = "백설 하얀 설탕")
    private String lblSkuname1;
    /**
     * 상품명2
     */
    @Schema(description = "상품명2", example = "1KG")
    private String lblSkuname2;
    /**
     * 수량
     */
    @Schema(description = "수량", example = "10.00000")
    private String lblQty;
    /**
     * 수량
     */
    @Schema(description = "수량", example = "10.00000")
    private String lblQty2;
    /**
     * 차량번호
     */
    @Schema(description = "차량번호", example = "50가1234")
    private String lblPageno1;
    /**
     * 출차조
     */
    @Schema(description = "출차조", example = "1")
    private String lblCargroup;
    /**
     * POP
     */
    @Schema(description = "POP", example = "D01-1")
    private String lblDeliverygroup;
    /**
     * 특이사항
     */
    @Schema(description = "특이사항", example = "물량변경")
    private String lblMemo;
    /**
     * 로케이션
     */
    @Schema(description = "로케이션", example = "A-01-01")
    private String lblLoc;
    /**
     * storagetype
     */
    @Schema(description = "storagetype", example = "상온")
    private String storagetype;
    /**
     * 저장조건
     */
    @Schema(description = "저장조건", example = "상온")
    private String lblStoragetype;
    /**
     * lblStoragetype1
     */
    @Schema(description = "lblStoragetype1", example = "상온")
    private String lblStoragetype1;
    /**
     * lblStoragetype2
     */
    @Schema(description = "lblStoragetype2", example = "상온")
    private String lblStoragetype2;
    /**
     * 배송일자
     */
    @Schema(description = "배송일자", example = "2025-10-15")
    private String lblDeliverydt;
    /**
     * 업체명
     */
    @Schema(description = "업체명", example = "CJ대한통운")
    private String lblFromCustname;
    /**
     * 페이지번호
     */
    @Schema(description = "페이지번호", example = "1/1")
    private String lblPageno2;
    /**
     * 바코드텍스트
     */
    @Schema(description = "바코드텍스트", example = "010101000000000000101010")
    private String lblBarcodetxt;
    /**
     * 바코드1
     */
    @Schema(description = "바코드1", example = "010101000000000000101010")
    private String lblBarcode1;
    /**
     * 바코드2
     */
    @Schema(description = "바코드2", example = "010101000000000000101010")
    private String lblBarcode2;
    /**
     * 차량변경여부
     */
    @Schema(description = "차량변경여부", example = "차량변경")
    private String carchange;
    /**
     * Lbl Sms Yn
     */
    @Schema(description = "Lbl Sms Yn", example = "Y")
    private String lblSmsYn;
    /**
     * Sorter대상
     */
    @Schema(description = "Sorter대상", example = "VIP")
    private String lblMarkword;
    /**
     * 특별관리고객표기
     */
    @Schema(description = "특별관리고객표기", example = "2025-10-15")
    private String lblManudate;
    /**
     * Lbl Placeoforigin
     */
    @Schema(description = "Lbl Placeoforigin", example = "FWKOR")
    private String lblPlaceoforigin;
    /**
     * Lbl From Carname
     */
    @Schema(description = "Lbl From Carname", example = "CJ로지스")
    private String lblFromCarname;
    /**
     * Lbl Routename
     */
    @Schema(description = "Lbl Routename", example = "강남코스")
    private String lblRoutename;
    /**
     * Loc Yn
     */
    @Schema(description = "Loc Yn", example = "1")
    private String locYn;
    /**
     * Invoicesort
     */
    @Schema(description = "Invoicesort", example = "0101010000000000001010")
    private String invoicesort;
    /**
     * Skupriority
     */
    @Schema(description = "Skupriority", example = "5")
    private String skupriority;
    /**
     * Predeliverygroup
     */
    @Schema(description = "Predeliverygroup", example = "D02")
    private String predeliverygroup;
    /**
     * Print Yn
     */
    @Schema(description = "Print Yn", example = "Y")
    private String printYn;
    /**
     * Lbl Etc Msg
     */
    @Schema(description = "Lbl Etc Msg", example = "본 배송분류표는 관련법상의 고지사항과는 무관합니다.")
    private String lblEtcMsg;
    /**
     * Deliverygroup Seq
     */
    @Schema(description = "Deliverygroup Seq", example = "01")
    private String deliverygroupSeq;
    /**
     * Lbl Deliverygroup Chg
     */
    @Schema(description = "Lbl Deliverygroup Chg", example = "D01-1")
    private String lblDeliverygroupChg;
    /**
     * ZONE
     */
    @Schema(description = "zone", example = "A01")
    private String zone;
    /**
     * lblFolabelYn
     */
    @Schema(description = "lblFolabelYn", example = "")
    private String lblFolabelYn;
    /**
     * lblMemoOfn
     */
    @Schema(description = "lblMemoOfn", example = "")
    private String lblMemoOfn;
    /**
     * title
     */
    @Schema(description = "title", example = "")
    private String title;
    /**
     * totalqty
     */
    @Schema(description = "totalqty", example = "")
    private String totalqty;
}
