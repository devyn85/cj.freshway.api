package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : han@wemeetmobility.com
 * @date : 2025.01.20
 * @description : 좌표 변환 실패 정보 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.20 han@wemeetmobility.com 생성
 * </pre>
 */
@Getter
@AllArgsConstructor
@Schema(description = "좌표 변환 실패 정보 DTO")
public class CoordinateFailInfo {

    /** 실착지 코드 */
    @Schema(description = "실착지 코드", example = "CUST001")
    private String truthCustKey;

    /** 거래처명 */
    @Schema(description = "거래처명", example = "ABC마트 강남점")
    private String custName;

    /** 주소 */
    @Schema(description = "주소", example = "서울특별시 강남구 테헤란로 123")
    private String address;

    /** 오류 메시지 */
    @Schema(description = "오류 메시지", example = "좌표 변환 실패")
    private String errorMessage;
}
