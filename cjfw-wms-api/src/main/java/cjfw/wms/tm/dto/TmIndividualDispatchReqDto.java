package cjfw.wms.tm.dto;

import cjfw.core.model.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2026. CJ Freshway Co. all rights reserved.
 * @author : devyn
 * @date : 2026.03.04
 * @description : 개별배차 목록 조회 요청 DTO
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
@EqualsAndHashCode(callSuper = true)
@Schema(description = "개별배차 목록 조회 요청 DTO")
public class TmIndividualDispatchReqDto extends Page<Object> {

    /**센터코드*/
    @Schema(description = "센터코드", required = true)
    private String dccode;

    /**배송일자From*/
    @Schema(description = "배송일자From (YYYYMMDD)", required = true)
    private String deliverydtFrom;

    /**배송일자To*/
    @Schema(description = "배송일자To (YYYYMMDD)", required = true)
    private String deliverydtTo;

    /**배송유형 (1:배송, 3:센터간이동(SO), 4:센터간이동(PO), 5:미확정PO)*/
    @Schema(description = "배송유형 (1, 3, 4, 5)", required = true)
    private String deliveryType;

    /**거래처코드 (콤마구분)*/
    @Schema(description = "거래처코드 (콤마구분)")
    private String custCode;

    /**차량번호 (콤마구분)*/
    @Schema(description = "차량번호 (콤마구분)")
    private String carNo;

    /**전표번호*/
    @Schema(description = "전표번호")
    private String docNo;

    /**배차상태*/
    @Schema(description = "배차상태")
    private String dispatchStatus;

}
