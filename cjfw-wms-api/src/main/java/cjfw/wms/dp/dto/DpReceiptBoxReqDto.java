package cjfw.wms.dp.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
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
 * @date : 2025.09.08
 * @description : 입고확정처리(수원3층) Request DTO Class
 * @issues :
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.08 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "입고확정처리(수원3층) Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpReceiptBoxReqDto extends CommonProcedureDto {
    /** fromCustkey */
    @Schema(description = "fromCustkey")
    private String fromCustkey;

    /** sku */
    @Schema(description = "sku")
    private String sku;

    /** storagetype */
    @Schema(description = "storagetype")
    private String storagetype;

    /** status */
    @Schema(description = "status")
    private String status;

    /** channel */
    @Schema(description = "channel")
    private String channel;

    /** docdtFrom */
    @Schema(description = "docdtFrom")
    private String docdtFrom;

    /** docdtTo */
    @Schema(description = "docdtTo")
    private String docdtTo;

    /** slipdtFrom */
    @Schema(description = "slipdtFrom")
    private String slipdtFrom;

    /** slipdtTo */
    @Schema(description = "slipdtTo")
    private String slipdtTo;

    /** docno */
    @Schema(description = "docno")
    private String docno;

    /** ordertype */
    @Schema(description = "ordertype")
    private String ordertype;

    /** dccode */
    @Schema(description = "dccode")
    private String dccode;

    /** storerkey */
    @Schema(description = "storerkey")
    private String storerkey;

    /** slipdt */
    @Schema(description = "slipdt")
    private String slipdt;

    /** slipno */
    @Schema(description = "slipno")
    private String slipno;

    /** blno */
    @Schema(description = "blno")
    private String blno;

    /** serialno */
    @Schema(description = "serialno")
    private String serialno;

    /** wdCustkey */
    @Schema(description = "wdCustkey")
    private String wdCustkey;

    /** daycheck */
    @Schema(description = "daycheck")
    private String daycheck;

    /** workprocesscode */
    @Schema(description = "workprocesscode")
    private String workprocesscode;

    /** crossyn */
    @Schema(description = "crossyn")
    private String crossyn;

    /** organize */
    @Schema(description = "organize")
    private String organize;

    /** boxperplt */
    @Schema(description = "boxperplt")
    private String boxperplt;

    /** serialCheck */
    @Schema(description = "serialCheck")
    private String serialCheck;

    /** * 최종변경자 */
    @Schema(description = "* 최종변경자")
    private String editwho;

    @Schema(description = "출력리스트")
    private List<DpReceiptBoxResDetailDto> saveList;

    @Schema(description = "출력리스트")
    private List<DpReceiptBoxResDto> saveMasterList;

        /** 상품(다중선택) */
    @MultiSearch
    @Schema(description = "상품(다중OR검색)")
    private List<List<String>> skuMulti;

        /**
     * 문서번호
     */
    @MultiSearch
    @Schema(description = "문서번호", nullable = false, example = "")
    private List<String> docnoMulti;

    /** 협력사코드 */
    @MultiSearch
    @Schema(description = "협력사코드", nullable = false, example = "")
    private List<String> fromCustkeyMulti;
}
