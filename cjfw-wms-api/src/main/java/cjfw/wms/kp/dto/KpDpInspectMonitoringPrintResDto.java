package cjfw.wms.kp.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author      : JiSooKim (jskim14@cj.net)
 * @date        : 2025.12.22
 * @description : 입고검수현황 미입고 프린트용 응답 DTO
 * @issues      :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.22 JiSooKim (jskim14@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "입고검수현황 미입고 프린트용 응답")
public class KpDpInspectMonitoringPrintResDto extends CommonDto {
    @Schema(description = "센터명")
    private String dcname;

    @Schema(description = "전표일자")
    private String slipdt;

    @Schema(description = "출고처명")
    private String fromCustname;

    @Schema(description = "표준배송그룹")
    private String deliverygroupStd;

    @Schema(description = "확정배송그룹")
    private String deliverygroupCnf;

    @Schema(description = "관리처명")
    private String toCustname;

    @Schema(description = "상품내역")
    private String skuname;

    @Schema(description = "미입고수량")
    private Integer qty;

    @Schema(description = "상품단위")
    private String uom;

    @Schema(description = "상품보관유형")
    private String storagetypename;

    @Schema(description = "결과")
    private String result;
}
