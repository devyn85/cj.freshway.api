package cjfw.wms.wd.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.15 
 * @description : 배송라벨출력(쿠팡) 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.15 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "배송라벨출력(쿠팡) 결과")
public class WdDeliveryLabelForceCoupangResDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	@Schema(description = "관리처코드", example = "CUST001")
    private String toCustkey;
    @Schema(description = "납품처명1", example = "하나로마트")
    private String lblCustname1;
    @Schema(description = "입수량", example = "100")
    private String lblCustname2;
    @Schema(description = "상품코드", example = "ITEM001")
    private String lblSku;
    @Schema(description = "상품명1", example = "사과")
    private String lblSkuname1;
    @Schema(description = "상품명2", example = "맛있는 사과")
    private String lblSkuname2;
    @Schema(description = "수량", example = "50")
    private String lblQty;
    @Schema(description = "차량번호", example = "123가4567")
    private String lblPageno1;
    @Schema(description = "출차조", example = "A조")
    private String lblCargroup;
    @Schema(description = "POP", example = "POP_A")
    private String lblDeliverygroup;
    @Schema(description = "POP2", example = "POP_B")
    private String lblDeliverygroupChg;
    @Schema(description = "로케이션", example = "A-01-01")
    private String lblLoc;
    @Schema(description = "저장조건", example = "냉장")
    private String lblStoragetype;
    @Schema(description = "배송일자", example = "2025-07-15")
    private String lblDeliverydt;
    @Schema(description = "페이지번호", example = "1")
    private String lblPageno2;
    @Schema(description = "바코드1", example = "BC12345")
    private String lblBarcode1;
    @Schema(description = "박스바코드", example = "BOXBC123")
    private String lblBoxbarcode;
    @Schema(description = "EA바코드", example = "EABC123")
    private String lblEabarcode;
    @Schema(description = "유통기한", example = "2026-07-15")
    private String lblStockLottable01;
    @Schema(description = "피킹번호", example = "PK001")
    private String pickNo;
    @Schema(description = "대배치키", example = "BATCH001")
    private String pickBatchNo;
    @Schema(description = "", example = "MANU123")
    private String lblManudate;
    @Schema(description = "", example = "20")
    private String lblQty2;
    @Schema(description = "", example = "한국")
    private String lblPlaceoforigin;
    @Schema(description = "", example = "본사")
    private String lblFromCustname;
    @Schema(description = "", example = "서울경로")
    private String lblRoutename;
    @Schema(description = "", example = "Y")
    private String locYn;
    @Schema(description = "", example = "1")
    private String invoicesort;
    @Schema(description = "", example = "HIGH")
    private String skupriority;
    @Schema(description = "", example = "PREDELIVERY001")
    private String predeliverygroup;
    @Schema(description = "", example = "Y")
    private String printYn;
    @Schema(description = "", example = "특이사항 없음")
    private String lblEtcMsg;
    @Schema(description = "", example = "1")
    private String deliverygroupSeq;
    @Schema(description = "", example = "Y")
    private String lblSmsYn;
    @Schema(description = "쿠팡관리처 공통코드 등록 여부", example = "Y")
    private String lblCoupangYn;
}
