package cjfw.wms.kp.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.11.29 
 * @description : 광역출고검수현황 상세 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.29 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "광역출고검수현황 상세 결과")
public class KpDpInspectMonitoringSTOResDetailDto extends CommonProcedureDto{
	/**
     * 차량번호
     */
    @Schema(description = "차량번호", example = "12가3456")
    private String carno;

    /**
     * POP번호
     */
    @Schema(description = "POP번호", example = "POP001")
    private String deliverygroup;

    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "SKU00123")
    private String sku;

    /**
     * 상품명칭
     */
    @Schema(description = "상품명칭", example = "샘플상품명")
    private String skuname;

    /**
     * 분류피킹업체
     */
    @Schema(description = "분류피킹업체", example = "CJ")
    private String assortpickcust;

    /**
     * 저장유무
     */
    @Schema(description = "저장유무", example = "Y")
    private String channel;

    /**
     * 주문수량
     */
    @Schema(description = "주문수량", example = "100")
    private BigDecimal orderqty;

    /**
     * 스캔완료수량
     */
    @Schema(description = "스캔완료수량", example = "90")
    private BigDecimal scanqty;

    /**
     * 현결품수량
     */
    @Schema(description = "현결품수량", example = "10")
    private BigDecimal shortageqty;

    /**
     * 주문단위
     */
    @Schema(description = "주문단위", example = "EA")
    private String uom;

    /**
     * 협력사명
     */
    @Schema(description = "협력사명", example = "협력사A")
    private String fromCustname;

    /**
     * 판매처명
     */
    @Schema(description = "판매처명", example = "판매처B")
    private String toCustname;

    /**
     * 검수진행상태
     */
    @Schema(description = "검수진행상태", example = "C")
    private String status;

    /**
     * fromDc
     */
    @Schema(description = "fromDc", example = "DC01")
    private String fromDc;

    /**
     * fromDcname
     */
    @Schema(description = "fromDcname", example = "수도권센터")
    private String fromDcname;

    /**
     * slipdt
     */
    @Schema(description = "slipdt", example = "2025-11-29")
    private String slipdt;

    /**
     * toDc
     */
    @Schema(description = "toDc", example = "DC02")
    private String toDc;

    /**
     * toDcname
     */
    @Schema(description = "toDcname", example = "영남센터")
    private String toDcname;

    /**
     * toCustkey
     */
    @Schema(description = "toCustkey", example = "CUST001")
    private String toCustkey;
}
