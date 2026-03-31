package cjfw.wms.dp.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.08.07
 * @description : 입고라벨출력 Request DTO Class
 * @issues :
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.07 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "입고라벨출력 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpSkuLabelReqDto extends CommonProcedureDto {
    /**
     * storagetype
     */
    @Schema(description = "storagetype")
    private String storagetype;

    /**
     * fromCustkey
     */
    @Schema(description = "fromCustkey")
    private String fromCustkey;

    /**
     * docno
     */
    @Schema(description = "docno")
    private String docno;

    /**
     * sku
     */
    @Schema(description = "sku")
    private String sku;

    /**
     * slipdtFrom
     */
    @Schema(description = "slipdtFrom")
    private String slipdtFrom;

    /**
     * slipdtTo
     */
    @Schema(description = "slipdtTo")
    private String slipdtTo;

    /**
     * docdtFrom
     */
    @Schema(description = "docdtFrom")
    private String docdtFrom;

    /**
     * docdtTo
     */
    @Schema(description = "docdtTo")
    private String docdtTo;

    /**
     * ordertype
     */
    @Schema(description = "ordertype")
    private String ordertype;

    /**
     * dccode
     */
    @Schema(description = "dccode")
    private String dccode;

    /**
     * storerkey
     */
    @Schema(description = "storerkey")
    private String storerkey;

    /**
     * slipno
     */
    @Schema(description = "slipno")
    private String slipno;

    /**
     * slipdt
     */
    @Schema(description = "slipdt")
    private String slipdt;

    /**
     * blno
     */
    @Schema(description = "blno")
    private String blno;

    /**
     * serialno
     */
    @Schema(description = "serialno")
    private String serialno;

    /**
     * contractcompany
     */
    @Schema(description = "contractcompany")
    private String contractcompany;

    /**
     * custkey
     */
    @Schema(description = "custkey")
    private String custkey;

    /**
     * fromCustname
     */
    @Schema(description = "fromCustname")
    private String fromCustname;

    /**
     * lottable01
     */
    @Schema(description = "lottable01")
    private String lottable01;

    /**
     * loc
     */
    @Schema(description = "loc")
    private String loc;

    /**
     * serialCheck
     */
    @Schema(description = "serialCheck")
    private String serialCheck;

    /**
     * saveList
     */
    @Schema(description = "saveList")
    private List<DpSkuLabelResDetailDto> saveList;
}
