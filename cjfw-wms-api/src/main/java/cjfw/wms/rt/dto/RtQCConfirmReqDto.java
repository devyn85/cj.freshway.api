package cjfw.wms.rt.dto;

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
 * @date : 2025.09.23
 * @description : 반품판정처리 Request DTO Class
 * @issues :
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.23 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "반품판정처리 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RtQCConfirmReqDto extends CommonProcedureDto {
    /** item */
    @Schema(description = "item")
    private String item;

    /** other03 */
    @Schema(description = "other03")
    private String other03;

    /** fixdccode */
    @Schema(description = "fixdccode")
    private String fixdccode;

    /** channel */
    @Schema(description = "channel")
    private String channel;

    /** storagetype */
    @Schema(description = "storagetype")
    private String storagetype;

    /** saledepartment */
    @Schema(description = "saledepartment")
    private String saledepartment;

    /** salegroup */
    @Schema(description = "salegroup")
    private String salegroup;

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

    /** statusRtqc */
    @Schema(description = "statusRtqc")
    private String statusRtqc;

    /** qctype */
    @Schema(description = "qctype")
    private String qctype;

    /** qcdtFrom */
    @Schema(description = "qcdtFrom")
    private String qcdtFrom;

    /** qcdtTo */
    @Schema(description = "qcdtTo")
    private String qcdtTo;

    /** memo */
    @Schema(description = "memo")
    private String memo;

    /** docno */
    @Schema(description = "docno")
    private String docno;

    /** docline */
    @Schema(description = "docline")
    private String docline;

    /** sku */
    @Schema(description = "sku")
    private String sku;

    /** 상품(다중OR검색) */
    @MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;

    /** vendoreturnyn */
    @Schema(description = "vendoreturnyn")
    private String vendoreturnyn;

    /** qcdt */
    @Schema(description = "qcdt")
    private String qcdt;

    /** wdAutoalloc */
    @Schema(description = "wdAutoalloc")
    private String wdAutoalloc;

    /** fromCustkey */
    @Schema(description = "fromCustkey")
    private String fromCustkey;

    /** 관리처코드(다중OR검색) */
    @MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> fromCustkeyMulti;

    /** vendor */
    @Schema(description = "vendor")
    private String vendor;

    /** popSearchYn */
    @Schema(description = "popSearchYn")
    private String popSearchYn;

    @Schema(description = "출력리스트")
    private List<RtQCConfirmResDto> saveList;

    /** 주문번호(다중검색) */
    @MultiSearch
    @Schema(description = "주문번호-다중OR검색")
    private List<String> docnoMulti;

    /** vendor */
    @MultiSearch
    @Schema(description = "vendor-다중검색")
    private List<String> vendorMulti;
}
