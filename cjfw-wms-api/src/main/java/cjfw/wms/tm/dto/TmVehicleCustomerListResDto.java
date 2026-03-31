/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.tm.dto;

import lombok.Data;

/**
 * 차량별 거래처 목록 조회 응답 DTO
 * 
 * @author System
 * @since 2025.09.11
 */
@Data
public class TmVehicleCustomerListResDto {
    
    /** 순번 */
    private String seqNo;

    /** 실거래처 코드 */
    private String custKey;
    
    /** 거래처명 */
    private String custName;
    
    /** 실제 도착시간 (HH:MM) */
    private String actualArrivalTime;
    
    /** 예상 도착시간 (HH:MM) */
    private String expectedArrivalTime;
    
    /** 거래처 주소 */
    private String custAddress;
    
    /** 요청 도착시간 FROM (HH:MM) */
    private String reqdlvtime1From;

    /** 요청 도착시간 TO (HH:MM) */
    private String reqdlvtime1To;
    
    /** 비밀번호 타입 */
    private String keyCustType;

    /** 출입KEY 세부정보 */
    private String keydetail;
    
    /** 대면검수 여부 */
    private String faceInspect;

    /** 특수조건 여부 */
    private String specialConditionYn;

    /** 반품여부 */
    private String returnYn;

    /** 클레임여부 */
    private String claimYn;
    
    /** 고정 POP 정보 */
    private String defDistrictCode;
    
    /** 고정 차량번호 */
    private String defCarno;

}
