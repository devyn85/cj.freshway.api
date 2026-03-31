package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.08.29 
 * @description : 피킹작업지시-조회생성 상세 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.29 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "피킹작업지시-조회생성 상세 결과")
public class WdTaskResTab2DetailDto extends CommonDto{
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/**
     * 배송일자
     */
    @Schema(description = "배송일자", example = "2025-08-29")
    private String deliverydate;
    
    /**
     * 출고일자
     */
    @Schema(description = "출고일자", example = "2025-08-29")
    private String docdt;

    /**
     * 주문번호
     */
    @Schema(description = "주문번호", example = "ORDER12345")
    private String docno;

    /**
     * 관리처코드
     */
    @Schema(description = "관리처코드", example = "CUST001")
    private String toCustkey;

    /**
     * 배송인도처명
     */
    @Schema(description = "배송인도처명", example = "거래처 A")
    private String toCustname;

    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "SKU001")
    private String sku;

    /**
     * 상품명칭
     */
    @Schema(description = "상품명칭", example = "샘플 상품")
    private String skuname;

    /**
     * 지시건수
     */
    @Schema(description = "지시건수", example = "5")
    private BigDecimal taskcount;

    /**
     * 진행예정량
     */
    @Schema(description = "진행예정량", example = "100")
    private BigDecimal processqty;

    /**
     * 처리량
     */
    @Schema(description = "처리량", example = "95")
    private BigDecimal workqty;

    /**
     * 검수량
     */
    @Schema(description = "검수량", example = "95")
    private BigDecimal inspectqty;

    /**
     * 확정수량
     */
    @Schema(description = "확정수량", example = "95")
    private BigDecimal confirmqty;

    /**
     * 주문단위
     */
    @Schema(description = "주문단위", example = "BOX")
    private String uom;

    /**
     * 처리물량(KG)
     */
    @Schema(description = "처리물량(KG)", example = "45.5")
    private BigDecimal workkg;

    /**
     * 고객코드
     */
    @Schema(description = "고객코드", example = "CUSTREF07")
    private String reference07;

    /**
     * 고객명
     */
    @Schema(description = "고객명", example = "홍길동")
    private String reference08;

    /**
     * 센터코드
     */
    @Schema(description = "센터코드", example = "DC01")
    private String dccode;

    /**
     * 고객사
     */
    @Schema(description = "고객사", example = "STORERKEY01")
    private String storerkey;

    /**
     * 작업지시일자
     */
    @Schema(description = "작업지시일자", example = "2025-08-28")
    private String taskdt;

    /**
     * 작업대상시스템
     */
    @Schema(description = "작업대상시스템", example = "WMS")
    private String tasksystem;

    /**
     * 전표일자
     */
    @Schema(description = "전표일자", example = "2025-08-29")
    private String slipdt;

    /**
     * 전표번호
     */
    @Schema(description = "전표번호", example = "SLIP001")
    private String slipno;

    /**
     * 분류키
     */
    @Schema(description = "분류키", example = "WAVEKEY01")
    private String wavekey;

    /**
     * 창고
     */
    @Schema(description = "창고", example = "WH01")
    private String organize;

    /**
     * POP번호
     */
    @Schema(description = "POP번호", example = "POP001")
    private String deliverygroup;

    /**
     * 고객코드
     */
    @Schema(description = "고객코드", example = "CUSTKEY01")
    private String custkey;

    /**
     * 주문유형
     */
    @Schema(description = "주문유형", example = "NORMAL")
    private String ordertype;

    /**
     * 배치번호
     */
    @Schema(description = "배치번호", example = "BATCH001")
    private String pickBatchNo;

    /**
     * 배치번호
     */
    @Schema(description = "배치번호", example = "PLNO001")
    private String pickListNo;

    /**
     * 슈트
     */
    @Schema(description = "슈트", example = "PICK001")
    private String pickNo;

    /**
     * 수량|주문량
     */
    @Schema(description = "수량|주문량", example = "100")
    private BigDecimal storerorderqty;

    /**
     * 수량|예정량
     */
    @Schema(description = "수량|예정량", example = "100")
    private BigDecimal openqty;

    /**
     * 모바일지시여부
     */
    @Schema(description = "모바일지시여부", example = "DC01")
    private String mobileInstructYn;
}
