package cjfw.wms.st.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.09.28 
 * @description : 출고재고보충(전센터) 출력 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.28 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "출고재고보충(전센터) 출력 결과")
public class StLocMoveRPAllResPrintDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	/**
     * 창고코드
     */
    @Schema(description = "창고코드", example = "DC01")
    private String dccode;

    /**
     * 화주
     */
    @Schema(description = "화주", example = "KEY001")
    private String storerkey;

    /**
     * 조직
     */
    @Schema(description = "조직", example = "ORG01")
    private String organize;

    /**
     * 전표일자
     */
    @Schema(description = "전표일자", example = "2024-01-01")
    private String slipdt;

    /**
     * 전표번호
     */
    @Schema(description = "전표번호", example = "12345678")
    private String slipno;

    /**
     * 전표라인
     */
    @Schema(description = "전표라인", example = "1")
    private String slipline;

    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "SKU001")
    private String sku;

    /**
     * 상품명
     */
    @Schema(description = "상품명", example = "테스트상품명")
    private String skuname;

    /**
     * 저장유형
     */
    @Schema(description = "저장유형", example = "C_STORAGETYPE")
    private String storagetype;

    /**
     * 주문유형
     */
    @Schema(description = "주문유형", example = "C_ORDERTYPE")
    private String ordertype;

    /**
     * UOM
     */
    @Schema(description = "UOM", example = "EA")
    private String uom;

    /**
     * 재고ID
     */
    @Schema(description = "재고ID", example = "STOCK123")
    private String stockid;

    /**
     * LOT
     */
    @Schema(description = "LOT", example = "LOT123")
    private String lot;

    /**
     * FROM 로케이션
     */
    @Schema(description = "FROM 로케이션", example = "FROM_LOC")
    private String fromloc;

    /**
     * TO 로케이션
     */
    @Schema(description = "TO 로케이션", example = "TO_LOC")
    private String toloc;

    /**
     * 박스수량
     */
    @Schema(description = "박스수량", example = "10")
    private BigDecimal boxqty;

    /**
     * 낱개수량
     */
    @Schema(description = "낱개수량", example = "100")
    private BigDecimal eaqty;

    /**
     * ZONE
     */
    @Schema(description = "ZONE", example = "ZONE_A")
    private String zone;

    /**
     * 메모
     */
    @Schema(description = "메모", example = "메모사항")
    private String memo;
}
