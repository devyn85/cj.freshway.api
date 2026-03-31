package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.10.10
 * @description : 배차목록(권역별) 조회 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.10 OhEunbeom      생성 </pre>
 */
@Data
@Schema(description = "배차목록(권역별) 조회 응답 DTO")
public class TmDispatchListByDistrictResDto{

    @Schema(description = "센터코드")
    private String dccode;

    @Schema(description = "센터명")
    private String dcname;

    @Schema(description = "배송일자")
    private String deliverydt;

    @Schema(description = "행정동코드")
    private String hjdongCd;

    @Schema(description = "시/도")
    private String ctpKorNm;

    @Schema(description = "시/군/구")
    private String sigKorNm;

    @Schema(description = "읍/면/동")
    private String hjdongNm;

    @Schema(description = "급식 개수")
    private Integer mealCount;

    @Schema(description = "급식 물량")
    private Double mealWeight;

    @Schema(description = "외식 개수")
    private Integer restaurantCount;

    @Schema(description = "외식 물량")
    private Double restaurantWeight;

    @Schema(description = "O2O 개수")
    private Integer o2oCount;

    @Schema(description = "O2O 물량")
    private Double o2oWeight;

    @Schema(description = "총 개수")
    private Integer totalCount;

    @Schema(description = "총 물량")
    private Double totalWeight;

}
