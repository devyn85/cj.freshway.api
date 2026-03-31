package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2026. CJ Freshway Co. all rights reserved.
 * @author : devyn
 * @date : 2026.03.04
 * @description : 개별배차 목록 조회 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.03.04 devyn                 생성 </pre>
 */
@Data
@Schema(description = "개별배차 목록 조회 응답 DTO")
public class TmIndividualDispatchResDto {

    /**센터코드*/
    @Schema(description = "센터코드")
    private String dccode;

    /**센터명*/
    @Schema(description = "센터명")
    private String dcname;

    /**배송일자*/
    @Schema(description = "배송일자")
    private String deliverydate;

    /**배송유형*/
    @Schema(description = "배송유형")
    private String tmDeliverytype;

    /**배송유형명*/
    @Schema(description = "배송유형명")
    private String tmDeliverytypeNm;

    /**주문유형*/
    @Schema(description = "주문유형")
    private String ordertype;

    /**주문유형명*/
    @Schema(description = "주문유형명")
    private String ordertypeNm;

    /**권역그룹*/
    @Schema(description = "권역그룹")
    private String districtgroup;

    /**권역그룹명*/
    @Schema(description = "권역그룹명")
    private String districtgroupNm;

    /**권역코드*/
    @Schema(description = "권역코드")
    private String districtcode;

    /**권역명*/
    @Schema(description = "권역명")
    private String districtcodeNm;

    /**화주코드*/
    @Schema(description = "화주코드")
    private String storerkey;

    /**전표번호*/
    @Schema(description = "전표번호")
    private String docno;

    /**POP번호*/
    @Schema(description = "POP번호")
    private String pop;

    /**차량번호*/
    @Schema(description = "차량번호")
    private String carno;

    /**배차상태*/
    @Schema(description = "배차상태코드")
    private String dispatchStatus;

    /**배차상태명*/
    @Schema(description = "배차상태명")
    private String dispatchStatusNm;

    /**행정동코드*/
    @Schema(description = "행정동코드")
    private String hjdongCd;

    /**관리처코드*/
    @Schema(description = "관리처코드")
    private String custkey;

    /**관리처명*/
    @Schema(description = "관리처명")
    private String custname;

    /**관리처주소*/
    @Schema(description = "관리처주소")
    private String custaddress;

    /**실착지코드*/
    @Schema(description = "실착지코드")
    private String truthcustkey;

    /**실착지명*/
    @Schema(description = "실착지명")
    private String truthcustname;

    /**실착지주소*/
    @Schema(description = "실착지주소")
    private String truthcustaddress;

    /**주문수량*/
    @Schema(description = "주문수량")
    private String orderQty;

    /**중량*/
    @Schema(description = "중량")
    private String weight;

    /**CBM*/
    @Schema(description = "CBM")
    private String cube;

}
