package cjfw.wms.wd.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.11.15 
 * @description : 배송라벨출력-인쇄 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.15 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "배송라벨출력-인쇄 목록 결과")
public class WdDeliveryLabelResTab1PrintDto extends CommonDto{
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/**
     * 제조일자
     */
    @Schema(description = "제조일자", example = "2025-01-01")
    private String lblManudate;
    /**
     * 납품처명1
     */
    @Schema(description = "납품처명1", example = "납품처명1 예시")
    private String lblCustname1;
    /**
     * 납품처
     */
    @Schema(description = "납품처", example = "납품처 예시")
    private String lblCustname2;
    /**
     * 상품명1
     */
    @Schema(description = "상품명1", example = "상품명1 예시")
    private String lblSkuname1;
    /**
     * 상품명2
     */
    @Schema(description = "상품명2", example = "상품명2 예시")
    private String lblSkuname2;
    /**
     * LBL_QTY
     */
    @Schema(description = "LBL_QTY", example = "100")
    private String lblQty;
    /**
     * LBL_QTY_2
     */
    @Schema(description = "LBL_QTY_2", example = "50")
    private String lblQty2;
    /**
     * 페이지번호1
     */
    @Schema(description = "페이지번호1", example = "1")
    private String lblPageno1;
    /**
     * 출차조
     */
    @Schema(description = "출차조", example = "A조")
    private String lblCargroup;
    /**
     * 페이지번호2
     */
    @Schema(description = "페이지번호2", example = "2")
    private String lblPageno2;
    /**
     * 원산국
     */
    @Schema(description = "원산국", example = "대한민국")
    private String lblPlaceoforigin;
    /**
     * 배송일자
     */
    @Schema(description = "배송일자", example = "2025-01-02")
    private String lblDeliverydt;
    /**
     * 차량번호
     */
    @Schema(description = "차량번호", example = "12가1234")
    private String lblDeliverygroup;
    /**
     * 업체명
     */
    @Schema(description = "업체명", example = "CJ푸드빌")
    private String lblFromCustname;
    /**
     * 배송차량명
     */
    @Schema(description = "배송차량명", example = "CJ대한통운 1호차")
    private String lblFromCarname;
    /**
     * 저장빈
     */
    @Schema(description = "저장빈", example = "A-01-01")
    private String lblLoc;
    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "SKU001")
    private String lblSku;
    /**
     * 경로명
     */
    @Schema(description = "경로명", example = "경로A")
    private String lblRoutename;
    /**
     * 바코드1
     */
    @Schema(description = "바코드1", example = "1234567890123")
    private String lblBarcodetxt;
    /**
     * 바코드2
     */
    @Schema(description = "바코드2", example = "0987654321098")
    private String lblBarcode1;
    /**
     * 바코드2
     */
    @Schema(description = "바코드2", example = "0987654321099")
    private String lblBarcode2;
    /**
     * 저장조건 코드
     */
    @Schema(description = "저장조건 코드", example = "C")
    private String storagetype;
    /**
     * 저장조건
     */
    @Schema(description = "저장조건", example = "냉장")
    private String lblStoragetype;
    /**
     * 저장조건1
     */
    @Schema(description = "저장조건1", example = "냉장")
    private String lblStoragetype1;
    /**
     * 저장조건2
     */
    @Schema(description = "저장조건2", example = "냉장보관")
    private String lblStoragetype2;
    /**
     * LBL_MEMO
     */
    @Schema(description = "LBL_MEMO", example = "메모 사항입니다")
    private String lblMemo;
    /**
     * LBL_TO_CUSTKEY
     */
    @Schema(description = "LBL_TO_CUSTKEY", example = "CUST001")
    private String lblToCustkey;
    /**
     * LOC
     */
    @Schema(description = "LOC", example = "A-01-02")
    private String loc;
    /**
     * SKUPRIORITY
     */
    @Schema(description = "SKUPRIORITY", example = "1")
    private String skupriority;
    /**
     * LOC_YN
     */
    @Schema(description = "LOC_YN", example = "Y")
    private String locYn;
    /**
     * INVOICESORT
     */
    @Schema(description = "INVOICESORT", example = "1")
    private String invoicesort;
    /**
     * PREDELIVERYGROUP
     */
    @Schema(description = "PREDELIVERYGROUP", example = "GROUP_A")
    private String predeliverygroup;
    /**
     * LBL_ETC_MSG
     */
    @Schema(description = "LBL_ETC_MSG", example = "기타 메시지")
    private String lblEtcMsg;
    /**
     * DELIVERYGROUP_SEQ
     */
    @Schema(description = "DELIVERYGROUP_SEQ", example = "1")
    private String deliverygroupSeq;
    /**
     * LBL_SMS_YN
     */
    @Schema(description = "LBL_SMS_YN", example = "N")
    private String lblSmsYn;
    /**
     * LBL_DELIVERYGROUP_CHG
     */
    @Schema(description = "LBL_DELIVERYGROUP_CHG", example = "Y")
    private String lblDeliverygroupChg;
    /**
     * LBL_MEMO_OFN
     */
    @Schema(description = "LBL_MEMO_OFN", example = "OFN 메모")
    private String lblMemoOfn;
    /**
     * FO 라벨 관리처 공통코드 등록 여부
     */
    @Schema(description = "FO 라벨 관리처 공통코드 등록 여부", example = "Y")
    private String lblFolabelYn;
    /**
     * SRM 특별관리고객 표기사항
     */
    @Schema(description = "SRM 특별관리고객 표기사항", example = "VIP")
    private String lblMarkword;
    /**
     * TITLE
     */
    @Schema(description = "TITLE", example = "STO")
    private String title;
    /**
     * qty1
     */
    @Schema(description = "qty1", example = "1BOX/0EA")
    private String qty1;
    /**
     * 차량이름
     */
    @Schema(description = "차량이름", example = "")
    private String PAGENO3;
    /**
     * 센터명
     */
    @Schema(description = "센터명", example = "이천물류센터/선마감")
    private String dcname;
}
