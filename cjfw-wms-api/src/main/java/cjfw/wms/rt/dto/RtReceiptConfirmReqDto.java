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
 * @date : 2025.09.16
 * @description : 반품확정처리 Request DTO Class
 * @issues :
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.16 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "반품확정처리 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RtReceiptConfirmReqDto extends CommonProcedureDto {
    /** /** status */
    @Schema(description = "status")
    private String status;

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

    /** serialyn */
    @Schema(description = "serialyn")
    private String serialyn;

    /** storagetype */
    @Schema(description = "storagetype")
    private String storagetype;

    /** slipdtFrom */
    @Schema(description = "slipdtFrom")
    private String slipdtFrom;

    /** slipdtTo */
    @Schema(description = "slipdtTo")
    private String slipdtTo;

    /** blno */
    @Schema(description = "blno")
    private String blno;

    /** serialno */
    @Schema(description = "serialno")
    private String serialno;

    /** wdCustkey */
    @Schema(description = "wdCustkey")
    private String wdCustkey;

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

    /** docnoWd */
    @Schema(description = "docnoWd")
    private String docnoWd;

    /** 주문번호(다중검색) */
	@MultiSearch
    @Schema(description = "주문번호-다중OR검색")
    private List<String> docnoWdMulti;

    /** returntype */
    @Schema(description = "returntype")
    private String returntype;

    /** channel */
    @Schema(description = "channel")
    private String channel;

    /** potype */
    @Schema(description = "potype")
    private String potype;

    /** packingmethod */
    @Schema(description = "packingmethod")
    private String packingmethod;

    /** gMultiCourier */
    @Schema(description = "gMultiCourier")
    private String gMultiCourier;

    /** dccode */
    @Schema(description = "dccode")
    private String dccode;

    /** storerkey */
    @Schema(description = "storerkey")
    private String storerkey;

    /** gMultiArea */
    @Schema(description = "gMultiArea")
    private String gMultiArea;

    /** docdt */
    @Schema(description = "docdt")
    private String docdt;

    /** docno */
    @Schema(description = "docno")
    private String docno;

    /** 주문번호(다중검색) */
	@MultiSearch
    @Schema(description = "주문번호-다중OR검색")
    private List<String> docnoMulti;

    /** sku */
    @Schema(description = "sku")
    private String sku;

    /** gMultiOrganize */
    @Schema(description = "gMultiOrganize")
    private String gMultiOrganize;

    /** serialCheck */
    @Schema(description = "serialCheck")
    private String serialCheck;

    /** vendoreturn */
    @Schema(description = "vendoreturn")
    private String vendoreturn;

    /** Y */
    @Schema(description = "Y")
    private String Y;

    /** N */
    @Schema(description = "N")
    private String N;

    /** fromCustkey */
    @Schema(description = "fromCustkey")
    private String fromCustkey;

    /** docline */
    @Schema(description = "docline")
    private String docline;

    /** gubun */
    @Schema(description = "gubun")
    private String gubun;

    /** slipno */
    @Schema(description = "slipno")
    private String slipno;

    /** slipline */
    @Schema(description = "slipline")
    private String slipline;

    /** 제목*/
    @Schema(description = "제목", nullable = false, example = "")
    private String title;

    /** 내용 */
    @Schema(description = "내용", nullable = false, example = "")
    private String cnts;

    /** 보내는 메일 주소 */
    @Schema(description = "보내는 메일 주소", nullable = false, example = "")
    private String sendrEmailAddr;

    /** 받는 사람 이름 */
    @Schema(description = "받는 사람 이름", nullable = false, example = "")
    private String rcvrNm;

    /** 받는 메일 주소 */
    @Schema(description = "받는 메일 주소", nullable = false, example = "")
    private String rcvrEmailAddr;

    /** 참조 메일 주소 */
    @Schema(description = "참조 메일 주소", nullable = false, example = "")
    private String refEmailAddr;

    @Schema(description = "출력리스트")
    private List<RtReceiptConfirmResDetailDto> saveList;

    @Schema(description = "출력리스트")
    private List<RtReceiptConfirmResDto> saveMasterList;
}
