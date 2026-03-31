package cjfw.wms.kp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net)
 * @date : 2025.09.24
 * @description : KP KX 미처리현황 상세조회 결과 DTO
 */
@Data
@Schema(description = "KX 미처리현황 상세조회 결과")
public class KpKxCloseDtlResT1Dto {
    @Schema(description = "문서유형", example = "DP")
    private String doctype;

    @Schema(description = "센터코드", example = "1000")
    private String dccode;

    @Schema(description = "문서번호", example = "DOC123456")
    private String docno;

    @Schema(description = "문서라인", example = "1")
    private String docline;

    @Schema(description = "SKU", example = "SKU123456")
    private String sku;

    @Schema(description = "미처리수량", example = "10")
    private Integer openqty;

    @Schema(description = "확정수량", example = "5")
    private Integer confirmqty;

    @Schema(description = "납기일자", example = "20250922")
    private String deliverydate;

    @Schema(description = "IF ID", example = "COM0430")
    private String ifId;

    @Schema(description = "슬립번호", example = "SLIP123456")
    private String slipno;

    @Schema(description = "슬립라인", example = "1")
    private String slipline;

    @Schema(description = "단위", example = "EA")
    private String uom;

    @Schema(description = "주문수량+조정수량", example = "100")
    private Integer orderqty;

    @Schema(description = "IF 플래그", example = "Y")
    private String ifFlag;

    @Schema(description = "IF 일시", example = "2025-09-22 12:00:00")
    private String ifDate;

    @Schema(description = "IF 메모", example = "비고 메모")
    private String ifMemo;
}