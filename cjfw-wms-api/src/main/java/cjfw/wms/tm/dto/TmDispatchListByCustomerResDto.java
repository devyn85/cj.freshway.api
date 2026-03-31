package cjfw.wms.tm.dto;

import cjfw.wms.tm.util.TmPlanCommon;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.09.10
 * @description : 배차목록(거래처별) 조회 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.10 OhEunbeom      생성 </pre>
 */
@Data
@Schema(description = "배차목록(거래처별) 조회 응답 DTO")
public class TmDispatchListByCustomerResDto{

    /**배송일자*/
    @Schema(description = "배송일자")
    private String deliverydt;

    /**센터코드*/
    @Schema(description = "센터코드")
    private String dccode;

    /**센터명*/
    @Schema(description = "센터명")
    private String dcname;

    /**POP번호*/
    @Schema(description = "POP번호")
    private String popno;

    /**권역그룹*/
    @Schema(description = "권역그룹")
    private String districtgroup;

    /**권역*/
    @Schema(description = "권역")
    private String districtcode;

    /**행정동코드*/
    @Schema(description = "행정동코드")
    private String hjdongCd;

    /**우편번호*/
    @Schema(description = "우편번호")
    private String zipcode;

    /**실착지코드*/
    @Schema(description = "실착지코드")
    private String truthcustkey;

    /**실착지명*/
    @Schema(description = "실착지명")
    private String truthcustname;

    /**관리처코드*/
    @Schema(description = "관리처코드")
    private String custkey;

    /**관리처명*/
    @Schema(description = "관리처명")
    private String custname;

    /**주소*/
    @Schema(description = "주소")
    private String custaddress;

    /**주소*/
    @Schema(description = "주소")
    private String truthaddress;

    /**경로*/
    @Schema(description = "경로")
    private String route;

    /**주문마감경로*/
    @Schema(description = "주문마감경로")
    private String ordercloseroute;

    /**클레임여부*/
    @Schema(description = "클레임여부")
    private String claimyn;

    /**차량번호*/
    @Schema(description = "차량번호")
    private String carno;

    /**기사명*/
    @Schema(description = "기사명")
    private String drivername;

    /**휴대전화*/
    @Schema(description = "휴대전화")
    private String phone1;

    /**계약유형*/
    @Schema(description = "계약유형")
    private String contracttype;

    /**계약유형명*/
    @Schema(description = "계약유형명")
    private String contractname;

    /**배송유형*/
    @Schema(description = "배송유형")
    private String tmDeliverytype;

    /**배송유형명*/
    @Schema(description = "배송유형명")
    private String deliverytypename;

    /**반품여부*/
    @Schema(description = "반품여부 (Y: 반품, N: 배송)")
    private String returnYn;

    /**회차*/
    @Schema(description = "회차")
    private String priority;

    /**운송사코드*/
    @Schema(description = "운송사코드")
    private String courier;

    /**운송사명*/
    @Schema(description = "운송사명")
    private String couriername;

    /**2차 운송사코드*/
    @Schema(description = "2차 운송사코드")
    private String caragentkey;

    /**2차 운송사명*/
    @Schema(description = "2차 운송사명")
    private String carriername;

    /**CBM*/
    @Schema(description = "CBM")
    private String cube;

    /**물량*/
    @Schema(description = "물량")
    private String weight;

    /**대면검수여부*/
    @Schema(description = "대면검수여부")
    private String faceinspect;

    /**OTD*/
    @Schema(description = "OTD")
    private String otd;

    /**특수조건여부*/
    @Schema(description = "특수조건여부")
    private String specialcondition;

    /**키유무*/
    @Schema(description = "키유무")
    private String keytype;

    /**키상세정보*/
    @Schema(description = "키상세정보")
    private String keydetail;

    @Schema(description = "비밀번호유형 코드")
    private String keytypeCd;

    /**
     * 기사명 마스킹 처리
     * 첫글자와 마지막글자를 제외한 중간 글자를 '*'로 마스킹
     */
    public void setDrivername(String drivername) {
        this.drivername = TmPlanCommon.maskDriverName(drivername);
    }

    /**
     * 휴대전화 마스킹 처리
     * 앞자리 3자리와 뒤자리 4자리 제외하고 나머지를 '*'로 마스킹
     */
    public void setPhone1(String phone1) {
        if (phone1 != null && phone1.length() >= 7) {
            // 앞자리 3자리 + 중간 마스킹 + 뒤자리 4자리
            String front = phone1.substring(0, 3);
            String back = phone1.substring(phone1.length() - 4);
            String middle = "*".repeat(phone1.length() - 7);
            this.phone1 = front + middle + back;
        } else {
            this.phone1 = phone1;
        }
    }
}
