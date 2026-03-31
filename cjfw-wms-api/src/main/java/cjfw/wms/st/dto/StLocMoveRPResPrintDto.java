package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.09.10 
 * @description : 출고재고보충(수원3층) 출력 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.10 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "출고재고보충(수원3층) 출력 결과")
public class StLocMoveRPResPrintDto extends CommonProcedureDto {
	/**
     * DC코드
     */
    @Schema(description = "DC코드", example = "DC01")
    private String dccode;
    /**
     * 화주
     */
    @Schema(description = "화주", example = "CJGL")
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
    @Schema(description = "전표번호", example = "SLIP1234")
    private String slipno;
    /**
     * 전표라인
     */
    @Schema(description = "전표라인", example = "1")
    private String slipline;
    /**
     * SKU
     */
    @Schema(description = "SKU", example = "SKU12345")
    private String sku;
    /**
     * SKU명
     */
    @Schema(description = "SKU명", example = "SKU명 예시")
    private String skuname;
    /**
     * 보관유형
     */
    @Schema(description = "보관유형", example = "TYPE_A")
    private String storagetype;
    /**
     * 주문유형
     */
    @Schema(description = "주문유형", example = "ORDER_B")
    private String ordertype;
    /**
     * UOM
     */
    @Schema(description = "UOM", example = "EA")
    private String uom;
    /**
     * 재고ID
     */
    @Schema(description = "재고ID", example = "STOCK1234")
    private String stockid;
    /**
     * LOT
     */
    @Schema(description = "LOT", example = "LOT001")
    private String lot;
    /**
     * FROM 로케이션
     */
    @Schema(description = "FROM 로케이션", example = "A-01-01")
    private String fromloc;
    /**
     * TO 로케이션
     */
    @Schema(description = "TO 로케이션", example = "B-01-01")
    private String toloc;
    /**
     * 박스수량
     */
    @Schema(description = "박스수량", example = "10")
    private BigDecimal boxqty;
    /**
     * EA수량
     */
    @Schema(description = "EA수량", example = "100")
    private BigDecimal eaqty;
}
