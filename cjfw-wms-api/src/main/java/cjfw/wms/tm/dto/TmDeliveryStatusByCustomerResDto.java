package cjfw.wms.tm.dto;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System
 * @date : 2025.01.XX
 * @description : 배송현황(거래처별) 조회 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.XX System      생성 </pre>
 */
@Data
@Schema(description = "배송현황(거래처별) 조회 응답 DTO")
public class TmDeliveryStatusByCustomerResDto {

    /** 고객사코드 */
    @Schema(description = "고객사코드")
    private String storerkey;

    /**센터코드*/
    @Schema(description = "센터코드")
    private String dccode;

    /** 센터명 */
    @Schema(description = "센터명")
    private String dcName;

    /**배송일자*/
    @Schema(description = "배송일자 (YYYYMMDD)")
    private String deliverydt;

    /**권역그룹 ID*/
    @Schema(description = "권역그룹 ID")
    private String dlvgroupId;

    /**권역그룹명*/
    @Schema(description = "권역그룹명")
    private String dlvgroupNm;

    /**배송권역 ID*/
    @Schema(description = "배송권역 ID")
    private String dlvdistrictId;

    /**배송권역명*/
    @Schema(description = "배송권역명")
    private String dlvdistrictNm;

    /**POP*/
    @Schema(description = "POP")
    private String popno;

    /**차량번호*/
    @Schema(description = "차량번호")
    private String carno;

    /**계약유형*/
    @Schema(description = "계약유형")
    private String contracttype;

    /**계약유형명*/
    @Schema(description = "계약유형명")
    private String contractname;

    /**회차*/
    @Schema(description = "회차")
    private String priority;

    /**배송순서*/
    @Schema(description = "배송순서")
    private Integer deliverypriority;

    /** 거래처 타입 */
    @Schema(description = "거래처 타입")
    private String custtype;

    /** 실착지 거래처코드 */
    @Schema(description = "실착지 거래처코드")
    private String truthcustkey;

    /** 실착지 거래처명 */
    @Schema(description = "실착지 거래처명")
    private String truthcustname;

    /**주소*/
    @Schema(description = "주소")
    private String truthaddress;

    /**OTD 시작시간*/
    @Schema(description = "OTD 시작시간 (hh24:mi)")
    private String otdFrom;

    /**OTD 종료시간*/
    @Schema(description = "OTD 종료시간 (hh24:mi)")
    private String otdTo;

    /**알림수신여부*/
    @Schema(description = "알림수신여부")
    private String deliverynotiyn;

    /** 출도착처리명 */
    @Schema(description = "출도착처리명")
    private String regwhoNm;

    /** 출도착처리 */
    @Schema(description = "출도착처리")
    private String regwho;

    /**  */
    @Schema(description = "")
    private String deliverystartRegType;

    /**  */
    @Schema(description = "")
    private String deliveryendRegType;

    /**  */
    @Schema(description = "")
    private Date deliverystartEditdate;

    /**  */
    @Schema(description = "")
    private Date deliveryendEditdate;

    /**도착예정시간*/
    @Schema(description = "도착예정시간 (hh24:mi)")
    private String deliveryplandt;

    /**거래처도착시간*/
    @Schema(description = "거래처도착시간 (hh24:mi)")
    private String custarrivaldt;

    /**거래처출발시간*/
    @Schema(description = "거래처출발시간 (hh24:mi)")
    private String custdeparturedt;

    /**평균도착시간*/
    @Schema(description = "평균도착시간 (hh24:mi)")
    private String avgArrivaldt;

    /**평균작업시간*/
    @Schema(description = "평균작업시간 (hh24:mi)")
    private String avgWorkTime;

    /**중량*/
    @Schema(description = "중량")
    private BigDecimal weight;

    /**체적*/
    @Schema(description = "체적")
    private BigDecimal cube;

    /**사진촬영 유/무*/
    @Schema(description = "사진촬영 유/무")
    private String photoYn;
}
