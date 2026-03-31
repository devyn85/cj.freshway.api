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
@Schema(description = "지표/모니터링 > 검수지표 > 상차검수지표 현황 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpWdLoadMonitoringResDto extends CommonProcedureDto {

	@Schema(description = "고객코드")
    private String storerkey;
	
	@Schema(description = "물류센터코드")
    private String dccode;

    @Schema(description = "물류센터명")
    private String dcname;

    @Schema(description = "문서유형")
    private String doctype;

    @Schema(description = "배송일자")
    private String deliverydt;

    @Schema(description = "전표일자")
    private String slipdt;

    @Schema(description = "차량번호")
    private String carno;

    @Schema(description = "계약유형")
    private String contracttype;

    @Schema(description = "기사명")
    private String drivername;

    @Schema(description = "출차시간")
    private String dcdeparturedt;

    @Schema(description = "회차")
    private String priority;

    @Schema(description = "배송그룹")
    private String deliverygroup;

    @Schema(description = "상차상태") 
    private String loadstatus;

    @Schema(description = "배송그룹")
    private String courier;

    @Schema(description = "상차계획수량")
    private String loadplanqty;

    @Schema(description = "상차완료량")
    private String loadcmpqty;

    @Schema(description = "상차진행률")
    private String loadrate;

    @Schema(description = "결품수량")
    private String shortageqty;

    @Schema(description = "결품율")
    private String shortagerate;

    @Schema(description = "강제완료량")
    private String forcecmpqty;

    @Schema(description = "강제완료율")
    private String forcecmprate;

    @Schema(description = "주문수량")
    private String orderqty;

    @Schema(description = "미확정수량")
    private String unconfirmqty;

    @Schema(description = "확정수량")
    private String confirmqty;

    @Schema(description = "확정율")
    private String confirmrate;

    @Schema(description = "검수완료여부")
    private String forcecmpyn;

}
