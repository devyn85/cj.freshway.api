package cjfw.wms.tm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : han@wemeetmobility.com
 * @date : 2025.01.20
 * @description : 차량 정보 DTO (Phase 1-1 조회 결과)
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.20 han@wemeetmobility.com 생성
 * </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleInfoDto {

    /**
     * 차량번호
     */
    private String carno;

    /**
     * 계약유형
     * 지입차, 월대용차, 고정용차, 임시용차, 실비용차
     */
    private String contractType;

    /**
     * 운송사 코드
     */
    private String carrierCode;

    /**
     * 기사명
     */
    private String driverName;

    /**
     * 운송사명
     */
    private String carrierName;

    /**
     * 조차 그룹 코드
     */
    private String outGroupCd;

    /**
     * 출발지 위도 (물류센터 좌표)
     * VARCHAR2 타입으로 DB에 저장되므로 String으로 받음
     */
    private String startLatitude;

    /**
     * 출발지 경도 (물류센터 좌표)
     * VARCHAR2 타입으로 DB에 저장되므로 String으로 받음
     */
    private String startLongitude;
}