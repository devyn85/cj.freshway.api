package cjfw.wms.dp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.28
 * @description : 입고하차관리 Request DTO Class
 * @issues :
 *
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.28 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "입고하차관리 Detail Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpUnloadResExcelDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

    /** 인보이스번호 */
    @Schema(description = "인보이스번호")
    private String docno;

    /**
     * 입고예정일
     */
    @Schema(description = "입고예정일")
    private String slipdt;

    /**
     * 센터코드
     */
    @Schema(description = "센터코드")
    private String dccode;

    /**
     * 출발거래처코드
     */
    @Schema(description = "출발거래처코드")
    private String fromCustkey;

    /**
     * 출발거래처명
     */
    @Schema(description = "출발거래처명")
    private String fromCustname;

    /**
     * 배송그룹
     */
    @Schema(description = "배송그룹")
    private String deliverygroup;

    /**
     * 온도1
     */
    @Schema(description = "온도1")
    private String temperature1;

    /**
     * 온도2
     */
    @Schema(description = "온도2")
    private String temperature2;

    /**
     * 온도적정성
     */
    @Schema(description = "온도적정성")
    private String tempoptitype;

    /**
     * 배송시간
     */
    @Schema(description = "배송시간")
    private String deliverytime;

    /**
     * 배송메모
     */
    @Schema(description = "배송메모")
    private String deliverymemo;

    /**
     * 채널
     */
    @Schema(description = "채널")
    private String channel;

    /**
     * 검수시간
     */
    @Schema(description = "검수시간")
    private String insptime;

    /**
     * 주문유형명
     */
    @Schema(description = "주문유형명")
    private String ordertypeName;

    /**
     * 공장명
     */
    @Schema(description = "공장명")
    private String plantDescr;

    /**
     * 채널명
     */
    @Schema(description = "채널명")
    private String channelName;

    /**
     * 보관유형명
     */
    @Schema(description = "보관유형명")
    private String storagetypeName;

    /** 문서라인 */
    @Schema(description = "문서라인")
    private String docline;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명")
    private String skuname;

    /** 단위 */
    @Schema(description = "단위")
    private String uom;

    /** 주문수량 */
    @Schema(description = "주문수량")
    private String orderqty;

    /** 확정수량 */
    @Schema(description = "확정수량")
    private String confirmqty;

}
