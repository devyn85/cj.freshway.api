package cjfw.wms.tm.dto.result;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "TM 계획 경로 DTO")
public class TmPlanRouteDto {
    private Long serialkey;
    private String dccode;
    private String deliverydt;
    private String tmDeliverytype;
    private String carno;
    private String returnTripNo;
    private String fromCusttype;
    private String fromCustkey;
    private String toCusttype;
    private String toCustkey;
    private LocalDateTime toExpctDate;
    private BigDecimal drivedistance;
    private BigDecimal drivetim;
    private String routePolyline;
    private String delYn;
    private LocalDateTime adddate;
    private String addwho;
}


