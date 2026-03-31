package cjfw.wms.wd.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.16
 * @description : 반품명세서출력 Request DTO (sqlx 모든 파라미터 포함)
 */
@Schema(description = "반품명세서출력 Request DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoInvoiceReqDto extends CommonProcedureDto {
    @Schema(description = "센터코드")
    private String gDccode;

    @Schema(description = "화주코드")
    private String gStorerkey;

    @Schema(description = "조직코드")
    private String gOrganize;

    @Schema(description = "영역코드")
    private String gArea;

    @Schema(description = "다중조직코드")
    private String gMultiOrganize;

    @Schema(description = "다중영역코드")
    private String gMultiArea;

    @Schema(description = "사용자ID")
    private String gUserId;

    @Schema(description = "납품예정일자")
    private String invoicedt;

    @Schema(description = "배송일자")
    private String deliverydt;

    @Schema(description = "배차그룹")
    private String deliverygroup;

    @Schema(description = "다중차량번호")
    private List<String> multiCarNo;

    @Schema(description = "거래처코드(다중)")
    private String toCustkey;

    /** 관리처코드(다중OR검색) */
    @MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> toCustkeyMulti;

    @Schema(description = "다중택배사코드")
    private String gMultiCourier;

    @Schema(description = "프로세스타입")
    private String processtype;

    @Schema(description = "SPID")
    private String gSpid;

    @Schema(description = "차량번호")
    private String carno;

    @Schema(description = "우선순위")
    private String priority;

    @Schema(description = "마스터프린트여부")
    private Boolean isMaster;

    @Schema(description = "출력키")
    private String invoicePrintKey;

    /**
     * 출력리스트
     */
    @Schema(description = "출력리스트")
    private List<RoInvoiceResDto> saveList;

    /**
     * 출력리스트
     */
    @Schema(description = "출력리스트")
    private List<RoInvoiceResDetailDto> saveDetailList;
}

