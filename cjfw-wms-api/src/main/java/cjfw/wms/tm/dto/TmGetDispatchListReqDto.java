package cjfw.wms.tm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : AI Assistant
 * @date : 2025.09.17
 * @description : TM 배차 목록 조회 요청 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.17 AI Assistant          생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(value = {
    "startRow", "listCount", "skipCount", "totalCount", 
    "list", "pageNum", "totalPages"
}, allowGetters = false, allowSetters = false)
@Schema(description = "TM 배차 목록 조회 요청 DTO")
public class TmGetDispatchListReqDto extends CommonDto {

    /** 배송일자 (YYYYMMDD 형식, 필수) */
    @Schema(
        description = "조회 시작일자 (YYYYMMDD 형식)",
        example = "20250320",
        nullable = false,
        pattern = "^[0-9]{8}$"
    )
    @jakarta.validation.constraints.NotBlank(message = "배송일자는 필수입니다")
    @jakarta.validation.constraints.Pattern(regexp = "^[0-9]{8}$", message = "조회 시작일자는 YYYYMMDD 형식이어야 합니다")
    private String deliveryDate;

    /** 물류센터코드 (필수) */
    @Schema(
        description = "물류센터코드",
        example = "105",
        nullable = false
    )
    @jakarta.validation.constraints.NotBlank(message = "물류센터코드는 필수입니다")
    private String dccode;

    /** 배송 유형 */
    @Schema(
        description = "배송 유형",
        example = "WD",
        nullable = true
    )
    private String deliveryType;

    /** 배송 권역 */
    @Schema(
        description = "배송 권역",
        example = "SEOUL",
        nullable = true
    )
    private String regionCode;

    /** 차량번호 검색 */
    @Schema(
        description = "차량번호 검색",
        example = "경기",
        nullable = true
    )
    private String carnoSearch;

    /** 배차 상태 필터 */
    @Schema(
        description = "배차 상태 필터",
        example = "ALL",
        allowableValues = {"ALL", "ACTIVE", "COMPLETED", "CANCELLED"}
    )
    private String dispatchStatus;
    
    @Schema(description = "차량코드목록 (조회용)", hidden = true)
    private String[] carnoList;
    
    public void setCarnoListToArray() {
        if (this.carnoSearch != null && !this.carnoSearch.isEmpty()) {
            this.carnoList = carnoSearch.split(",");
        }
    }
}
