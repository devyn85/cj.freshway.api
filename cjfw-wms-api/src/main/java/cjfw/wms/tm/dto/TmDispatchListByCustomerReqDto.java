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
 * @author : OhEunbeom
 * @date : 2025.09.10
 * @description : 배차목록(거래처별) 조회 요청 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.10 OhEunbeom      생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "배차목록(거래처별) 조회 요청 DTO")
public class TmDispatchListByCustomerReqDto extends Page<Object> {

    /**배송시작일자*/
    @Schema(description = "배송시작일자", required = true)
    private String deliverydtFrom;

    /**배송종료일자*/
    @Schema(description = "배송종료일자", required = true)
    private String deliverydtTo;

    /**센터코드*/
    @Schema(description = "센터코드", required = true)
    private String dccode;

    /**배송유형*/
    @Schema(description = "배송유형")
    private String tmDeliverytype;

    /**권역그룹*/
    @Schema(description = "권역그룹")
    private String districtgroup;

    /**권역코드*/
    @Schema(description = "권역코드")
    private String districtcode;

    /**POP번호*/
    @Schema(description = "POP번호")
    private String popno;

    /** POP 번호 */
    @MultiSearch
    @Schema(description = "POP 번호-다중OR검색")
    private List<String> popnoMulti;

    /**차량번호 리스트*/
    @Schema(description = "차량번호 리스트")
    private List<String> carnoList;

    /**거래처키 리스트*/
    @Schema(description = "거래처(실착지)키 리스트")
    private List<String> truthcustkeyList;

    /**주문마감유형*/
    @Schema(description = "주문마감유형")
    private String ordercloseroute;

}
