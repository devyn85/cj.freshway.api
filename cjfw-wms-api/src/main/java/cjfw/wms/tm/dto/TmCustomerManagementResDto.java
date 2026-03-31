package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.09.18
 * @description : 관리처 목록 조회 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.18 OhEunbeom      생성 </pre>
 */
@Data
@Schema(description = "관리처 목록 조회 응답 DTO")
public class TmCustomerManagementResDto {

    /**실착지키*/
    @Schema(description = "실착지키", example = "TEST_CUST_001")
    private String truthcustkey;

    /**관리처키*/
    @Schema(description = "관리처키", example = "BRANCH_A_001")
    private String custkey;

    /**관리처명*/
    @Schema(description = "관리처명", example = "강남지점")
    private String custName;

    /**실착지주소*/
    @Schema(description = "실착지주소", example = "서울시 강남구 역삼동 123-45")
    private String address;

    /**OTD여부*/
    @Schema(description = "OTD여부 (Y/N)", example = "Y")
    private String otdYn;

    /**대면검수여부*/
    @Schema(description = "대면검수여부 (Y/N)", example = "N")
    private String faceinspect;

    /**키유무*/
    @Schema(description = "키유무", example = "Y")
    private String keytype2;

    /**특수조건유무*/
    @Schema(description = "특수조건유무 (Y/N)", example = "Y")
    private String specialConditionYn;

}
