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
 * 차량 상세 정보 조회 응답 DTO
 * 
 * @author System
 * @since 2025.09.11
 */
@Data
public class TmVehicleInfoResDto {
    
    /** 계약유형 */
    private String contracttype;
    
    /** 계약유형명 */
    private String contracttypeName;
    
    /** 출차그룹 */
    private String cargroup;
    
    /** 출차그룹명 */
    private String cargroupName;
    
    /** 차량번호 */
    private String carno;
    
    /** 차량 톤수 */
    private String carcapacity;
    
    /** 차량톤급명 */
    private String carcapacityName;
    
    /** 회차정보 */
    private String prepriority;
    
    /** 최대 회차 */
    private String maxPrepriority;
    
    /** 기사명 */
    private String drivername;

    /** 기사 전화번호 */
    private String phone1;

}
