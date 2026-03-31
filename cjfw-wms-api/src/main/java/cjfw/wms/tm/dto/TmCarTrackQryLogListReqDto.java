package cjfw.wms.tm.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.01.XX
 * @description : 차량 이력 조회 요청 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.XX OhEunbeom      생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "차량 이력 조회 요청 DTO")
public class TmCarTrackQryLogListReqDto {

    /** 센터코드 */
    @Schema(description = "센터코드", required = true)
    @NotBlank(message = "센터코드는 필수입니다")
    private String dccode;

    /** 조회일자 시작 */
    @Schema(description = "조회일자 시작 (YYYYMMDD)", required = true)
    @NotBlank(message = "조회일자 시작은 필수입니다")
    private String fromDate;

    /** 조회일자 종료 */
    @Schema(description = "조회일자 종료 (YYYYMMDD)", required = true)
    @NotBlank(message = "조회일자 종료는 필수입니다")
    private String toDate;

    /** 차량번호 리스트 */
    @Schema(description = "차량번호 리스트")
    private List<String> carnoList;

    /** 사용자명 */
    @Schema(description = "사용자명 (LIKE 검색)")
    private String username;

}

