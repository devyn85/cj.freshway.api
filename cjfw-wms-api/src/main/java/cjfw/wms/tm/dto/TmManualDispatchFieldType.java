package cjfw.wms.tm.dto;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : han@wemeetmobility.com
 * @date : 2025.01.20
 * @description : TM 수동배차 컬럼 수정 가능 여부 구분
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.20 han@wemeetmobility.com 생성
 * </pre>
 */
public enum TmManualDispatchFieldType {
    FIXED,     // 수정 불가 (읽기 전용, 시스템 고정 값)
    EDITABLE   // 수정 가능 (사용자 입력 허용)
}
