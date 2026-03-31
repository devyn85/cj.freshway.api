package cjfw.wms.tm.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : han@wemeetmobility.com
 * @date : 2025.11.27
 * @description : TM 수동배차 다운로드 Request DTO (거래처 필터링 지원)
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.27 han@wemeetmobility.com 생성
 * </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(value = {
    "startRow", "listCount", "skipCount", "totalCount",
    "list", "pageNum", "totalPages"
})
@Schema(description = "TM 수동배차 다운로드 요청 DTO (거래처 필터링)")
public class TmManualDispatchDownloadReqDto extends CommonDto {

    @Schema(
        description = "배송일자 (YYYYMMDD 형식)",
        example = "20251019",
        required = true
    )
    @NotBlank(message = "배송일자는 필수입니다")
    @Pattern(regexp = "^[0-9]{8}$", message = "배송일자는 YYYYMMDD 형식이어야 합니다")
    private String deliveryDate;

    @Schema(
        description = "배송유형 코드 (1:배송, 2:반품, 3:조달저장, 4:조달일배)",
        example = "1",
        required = true,
        allowableValues = {"1", "2", "3", "4"}
    )
    @NotBlank(message = "배송유형은 필수입니다")
    @Pattern(regexp = "^(1|2|3|4)$", message = "배송유형은 1,2,3,4 중 하나여야 합니다")
    private String deliveryTypeCode;

    @Schema(
        description = "거래처(실착지) 코드 리스트 (선택, 미입력 시 전체 조회)",
        example = "[\"CUST001\", \"CUST002\"]"
    )
    private List<String> truthcustkeyList;

    /**
     * 배송유형 코드를 배송유형으로 변환
     */
    public String getDeliveryType() {
        if (this.deliveryTypeCode == null) {
            return null;
        }

        return switch (this.deliveryTypeCode) {
            case "1" -> "WD";   // 배송
            case "2" -> "WSD";  // 반품
            case "3" -> "WWD";  // 조달(저장)
            case "4" -> "WOD";  // 조달(일배)
            default -> throw new IllegalArgumentException("지원하지 않는 배송유형입니다: " + this.deliveryTypeCode);
        };
    }
}
