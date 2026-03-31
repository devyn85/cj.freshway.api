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
 * @author : OhEunbeom
 * @date : 2025.01.15
 * @description : 배차목록(차량별) 조회 요청 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.15 OhEunbeom      생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "배차목록(차량별) 조회 요청 DTO")
public class TmDispatchListByCarReqDto extends Page<Object> {

    /**배송시작일자*/
    @Schema(description = "배송시작일자", required = true)
    private String deliverydtFrom;

    /**배송종료일자*/
    @Schema(description = "배송종료일자", required = true)
    private String deliverydtTo;

    /**센터코드*/
    @Schema(description = "센터코드", required = true)
    private String dccode;

    /**계약유형*/
    @Schema(description = "계약유형")
    private String contracttype;

    /**차량번호 리스트*/
    @Schema(description = "차량번호 리스트")
    private List<String> carnoList;

    /**배송유형*/
    @Schema(description = "배송유형")
    private String tmDeliverytype;

    /**차량톤수*/
    @Schema(description = "차량톤수")
    private String carcapacity;

}
