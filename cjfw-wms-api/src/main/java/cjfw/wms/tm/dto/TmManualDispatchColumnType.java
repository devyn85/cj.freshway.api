package cjfw.wms.tm.dto;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : han@wemeetmobility.com
 * @date : 2025.01.20
 * @description : TM 수동배차 컬럼 데이터 타입
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.20 han@wemeetmobility.com 생성
 * </pre>
 */
public enum TmManualDispatchColumnType {
    STRING,   // 문자열
    INTEGER,  // 정수
    DECIMAL,  // 소수
    DATE      // 날짜 (YYYYMMDD)
}
