package cjfw.wms.tm.dto;

import java.util.List;

import cjfw.core.model.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System
 * @date : 2025.01.XX
 * @description : 배송현황(거래처별) 조회 요청 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.XX System      생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "배송현황(거래처별) 조회 요청 DTO")
public class TmDeliveryStatusByCustomerReqDto extends Page<Object> {

    /**센터코드*/
    @Schema(description = "센터코드", required = true)
    private String dccode;

    /**배송일자*/
    @Schema(description = "배송일자", required = true, example = "20250101")
    private String deliverydt;

    /**계약유형*/
    @Schema(description = "계약유형")
    private String contracttype;

    /**POP 리스트*/
    @Schema(description = "POP 리스트")
    private List<String> popnoList;

    /**차량번호 리스트*/
    @Schema(description = "차량번호 리스트")
    private List<String> carnoList;

    /**페이징 종료 행 (쿼리 내 페이징 처리용)*/
    @Schema(description = "페이징 종료 행", hidden = true)
    private Integer endRow;

}

