package cjfw.wms.tm.dto;

import java.util.List;

import cjfw.core.model.Page;
import cjfw.wms.common.annotation.MultiSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System
 * @date : 2025.01.15
 * @description : 배차목록(차량 변경내역) 조회 요청 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.15 System      생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "배차목록(차량 변경내역) 조회 요청 DTO")
public class TmDispatchListByCarHistoryReqDto extends Page<Object> {

    /**배송시작일자*/
    @Schema(description = "배송시작일자", required = true, example = "20250101")
    private String deliverydtFrom;

    /**배송종료일자*/
    @Schema(description = "배송종료일자", required = true, example = "20250131")
    private String deliverydtTo;

    /**센터코드*/
    @Schema(description = "센터코드", required = true)
    private String dccode;

    /**POP번호*/
    @Schema(description = "POP번호")
    private String popno;

    /** POP 번호 */
    @MultiSearch
    @Schema(description = "POP 번호-다중OR검색")
    private List<String> popnoMulti;

    /**배송유형*/
    @Schema(description = "배송유형")
    private String tmDeliverytype;

    /**차량번호 리스트*/
    @Schema(description = "차량번호 리스트")
    private List<String> carnoList;

    /**관리처 리스트*/
    @Schema(description = "관리처 리스트")
    private List<String> custkeyList;

}
