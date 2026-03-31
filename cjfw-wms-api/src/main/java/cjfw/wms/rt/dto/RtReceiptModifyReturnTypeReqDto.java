package cjfw.wms.rt.dto;

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
 * @date : 2025.09.10
 * @description : 반품회수/미회수변경 Request DTO Class
 * @issues :
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.10 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "반품회수/미회수변경 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RtReceiptModifyReturnTypeReqDto extends CommonProcedureDto {
    /** returntype */
    @Schema(description = "returntype")
    private String returntype;

    /** channel */
    @Schema(description = "channel")
    private String channel;

    /** item */
    @Schema(description = "item")
    private String item;

    /** gUserid */
    @Schema(description = "gUserid")
    private String gUserid;

    /** gDccode */
    @Schema(description = "gDccode")
    private String gDccode;

    /** gStorerkey */
    @Schema(description = "gStorerkey")
    private String gStorerkey;

    /** gOrganize */
    @Schema(description = "gOrganize")
    private String gOrganize;

    /** gArea */
    @Schema(description = "gArea")
    private String gArea;

    /** gMultiArea */
    @Schema(description = "gMultiArea")
    private String gMultiArea;

    /** slipdtFrom */
    @Schema(description = "slipdtFrom")
    private String slipdtFrom;

    /** slipdtTo */
    @Schema(description = "slipdtTo")
    private String slipdtTo;

    /** storagetype */
    @Schema(description = "storagetype")
    private String storagetype;

    /** docdtFrom */
    @Schema(description = "docdtFrom")
    private String docdtFrom;

    /** docdtTo */
    @Schema(description = "docdtTo")
    private String docdtTo;

    /** confirmdateFrom */
    @Schema(description = "confirmdateFrom")
    private String confirmdateFrom;

    /** confirmdateTo */
    @Schema(description = "confirmdateTo")
    private String confirmdateTo;

    /** potype */
    @Schema(description = "potype")
    private String potype;

    /** ordertype */
    @Schema(description = "ordertype")
    private String ordertype;

    /** vendoreturn */
    @Schema(description = "vendoreturn")
    private String vendoreturn;

    /** gMultiCourier */
    @Schema(description = "gMultiCourier")
    private String gMultiCourier;

    /** reasoncode */
    @Schema(description = "reasoncode")
    private String reasoncode;

    /** reasonmsg */
    @Schema(description = "reasonmsg")
    private String reasonmsg;

    /** storerkey */
    @Schema(description = "storerkey")
    private String storerkey;

    /** dccode */
    @Schema(description = "dccode")
    private String dccode;

    /** slipdt */
    @Schema(description = "slipdt")
    private String slipdt;

    /** slipno */
    @Schema(description = "slipno")
    private String slipno;

    /** slipline */
    @Schema(description = "slipline")
    private String slipline;

    /** organize */
    @Schema(description = "organize")
    private String organize;

    /** docdt */
    @Schema(description = "docdt")
    private String docdt;

    /** docno */
    @Schema(description = "docno")
    private String docno;

    /** docline */
    @Schema(description = "docline")
    private String docline;

    /** sku */
    @Schema(description = "sku")
    private String sku;

    /** gMultiOrganize */
    @Schema(description = "gMultiOrganize")
    private String gMultiOrganize;

    @Schema(description = "출력리스트")
    private List<RtReceiptModifyReturnTypeResDto> saveMasterList;
}
