package cjfw.wms.kp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author      : (자동생성)
 * @date        : 2025.12.16
 * @description : 입고검수현황 상세 하단 탭(목록) 응답 DTO
 */
@Data
@Schema(description = "입고검수현황 상세 하단 탭(목록) 응답")
public class KpDpInspectMonitoringDtlResDto {
    @Schema(description = "센터코드")
    private String storerkey;

    @Schema(description = "센터코드")
    private String dccode;

    @Schema(description = "전표일자")
    private String docdt;

    @Schema(description = "전표번호")
    private String docno;

    @Schema(description = "SKU")
    private String sku;

    @Schema(description = "SKU명")
    private String skuname;

    @Schema(description = "입고처코드")
    private String toCustkey;

    @Schema(description = "입고처명")
    private String toCustname;

    @Schema(description = "주문수량")
    private Integer orderqty;

    @Schema(description = "검수수량")
    private Integer inspectqty;

    @Schema(description = "부족수량")
    private Integer shortageqty;

    @Schema(description = "단위")
    private String uom;

    @Schema(description = "결과(Y:스캔완료, I:스캔중, N:미스캔)")
    private String result;

    @Schema(description = "사유코드")
    private String reasoncode;

    @Schema(description = "사유메시지")
    private String reasonmsg;

    @Schema(description = "배송그룹")
    private String deliverygroup;

    @Schema(description = "수정자")
    private String editwho;
    
    @Schema(description = "검수시작시간")
    private String inspectStartTime;

    @Schema(description = "보관유형")
    private String storagetype;
}
