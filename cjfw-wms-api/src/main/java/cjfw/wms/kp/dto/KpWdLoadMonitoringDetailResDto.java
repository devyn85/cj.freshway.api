package cjfw.wms.kp.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : ParkYosep (dytpq362@cj.net)
 * @date : 2025.12.12
 * @description : 지표/모니터링 > 검수지표 > 상차검수지표 현황 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.12 ParkYosep (dytpq362@cj.net) 생성
 *         </pre>
 */
@Schema(description = "지표/모니터링 > 마감지표 > 마감진행 현황 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpWdLoadMonitoringDetailResDto extends CommonProcedureDto {
	@Schema(description = "물류센터코드")
    private String dccode;

    @Schema(description = "물류센터명")
    private String dcname;

    @Schema(description = "전표일자")
    private String slipdt;

    @Schema(description = "운송사코드")
    private String courier;

    @Schema(description = "운송사명")
    private String couriername;

    @Schema(description = "분류피킹업체")
    private String assortpickcust;

    @Schema(description = "차량번호")
    private String carno;

    @Schema(description = "POP번호")
    private String deliverygroup;

    @Schema(description = "상품코드")
    private String sku;

    @Schema(description = "상품명")
    private String skuname;

    @Schema(description = "주문수량")
    private String orderqty;

    @Schema(description = "스캔완료수량")
    private String scanqty;

    @Schema(description = "현결품수량")
    private String shortageqty;

    @Schema(description = "단위")
    private String uom;

    @Schema(description = "판매처코드")
    private String toCustkey;

    @Schema(description = "판매처명")
    private String toCustname;

    @Schema(description = "협력사명")
    private String fromCustname;

    @Schema(description = "저장유형")
    private String channel;

    @Schema(description = "상태")
    private String status;

    @Schema(description = "검수상태")
    private String inspectstatus;
}
