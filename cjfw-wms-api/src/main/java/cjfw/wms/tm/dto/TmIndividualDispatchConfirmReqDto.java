package cjfw.wms.tm.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2026. CJ Freshway Co. all rights reserved.
 * @author : devyn
 * @date : 2026.03.04
 * @description : 개별배차 배차확정 요청 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.03.04 devyn                 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "개별배차 배차확정 요청 DTO")
public class TmIndividualDispatchConfirmReqDto extends CommonProcedureDto {

    @Schema(description = "센터코드")
    private String dccode;

    @Schema(description = "배송일자")
    private String slipDt;

    @Schema(description = "차량번호")
    private String carno;

    @Schema(description = "배송유형")
    private String tmDeliveryType;

    @Schema(description = "회차")
    private String priority;

    @Schema(description = "POP")
    private String pop;

    @Schema(description = "배송번호")
    private String deliveryno;

    @Schema(description = "입고전표번호")
    private String docno;

    @Schema(description = "배송그룹")
    private String deliverygroup;

    @Schema(description = "실착지 거래처코드")
    private String truthcustkey;

}
