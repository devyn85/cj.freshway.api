package cjfw.wms.ms.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonTriggerDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : YeoSeungCheol (pw6375@cj.net)
 * @date        : 2025.07.30
 * @description : 수발주팝업 Entity
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.30        YeoSeungCheol       (pw6375@cj.net) 생성
 */
@Data
@Schema(description = "수발주정보 수정 팝업 Entity")
public class MsPurchaseCustPopupEntity extends CommonTriggerDto {
    @Schema(description = "데이터번호(PK)", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal serialKey;

    @Schema(description = "센터코드")
    private String dcCode;

    @Schema(description = "고객사")
    private String storerKey;

    @Schema(description = "거래처유형")
    private String custType;

    @Schema(description = "상품코드")
    private String sku;

    @Schema(description = "시작일자 (YYYY-MM-DD)")
    private String fromDate;

    @Schema(description = "종료일자 (YYYY-MM-DD)")
    private String toDate;
    
    @Schema(description = "저장유무")
    private String channel;

    @Schema(description = "플랜트코드")
    private String plant;

    @Schema(description = "실공급센터")
    private String custKey;

    @Schema(description = "경유지")
    private String route;

    @Schema(description = "경유지조직")
    private String routeOrganize;

    @Schema(description = "구매처")
    private String buyerKey;
    
    @Schema(description = "기본단위")
    private String uom;

    @Schema(description = "최초등록시간 (자동생성)")
    private String addDate;

    @Schema(description = "최종변경시간 (자동갱신)")
    private String editDate;

    @Schema(description = "최초등록자")
    private String addWho;

    @Schema(description = "최종변경자")
    private String editWho;
    
    private String gUserId;
}
