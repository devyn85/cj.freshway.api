package cjfw.wms.tm.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cjfw.core.exception.UserHandleException;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net)
 * @date : 2025.10.24
 * @description : 엔진 배차계획 재계산 요청 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.24 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "엔진 배차계획 재계산 요청 DTO")
public class TmPlanEtaOptimizeAutoReqDto extends CommonDto {

    /** 물류센터 코드 */
    @Schema(description = "물류센터 코드", example = "2600", nullable = false)
    private String dccode;

    /** 배송일자 */
    @Schema(description = "배송일자 (YYYYMMDD 형식)", example = "20250120")
    private String deliveryDate;

    /** 배송 유형 */
    @Schema(description = "배송 유형", example = "1", nullable = true, allowableValues = {"1", "7", "3", "2"})
    private String tmDeliveryType;

    /** 거래처 코드  */
    @Schema(description = "거래처 코드 ',' 단위로 구분 다중검색", example = "CUST001", nullable = true)
    private String custCode;

    @Schema(description = "거래처 코드 리스트", example = "[CUST001]", hidden = true,   nullable = true)
    private String[] custCodeList;

    private List<TmPlanEtaVehicleReqDto> vehicles;

    /** TRUTHCUSTKEY IN 절 1000개 단위 청크 (Oracle IN 1000개 제한 대응) */
    @Schema(description = "TRUTHCUSTKEY IN 절 1000개 단위 청크", hidden = true)
    private List<List<String>> truthCustkeyChunks;

    @Schema(description = "버튼호출정보")
    private String planEntryType;

    public String getDescription() {
        return dccode + "_" + deliveryDate + "_" + tmDeliveryType + "_" + getGUserId();
    }

    /**
     * 주문 정보 없는 차량은 제거
     * TRUTHCUSTKEY IN 절용 1000개 단위 청크 생성 (Oracle IN 1000개 제한 대응)
     */
    public void filteredVehicles() {
        vehicles = vehicles.stream().filter(v -> !v.getSteps().isEmpty()).toList();
        if (vehicles.isEmpty()) {
            throw new UserHandleException("배차할 주문을 포함하여 재계산 다시 시도해주세요.");
        }
        prepareTruthCustkeyChunks();
    }

    private static final int IN_CLAUSE_CHUNK_SIZE = 1000;

    /**
     * vehicles의 steps에서 id를 수집하여 1000개 단위 청크로 설정 (Oracle IN 1000개 제한 대응)
     */
    public void prepareTruthCustkeyChunks() {
        if (vehicles == null) {
            truthCustkeyChunks = null;
            return;
        }
        List<String> ids = vehicles.stream()
                .flatMap(v -> v.getSteps().stream())
                .map(s -> s.getId())
                .filter(id -> id != null && !id.isBlank())
                .distinct()
                .collect(Collectors.toList());
        List<List<String>> chunks = new ArrayList<>();
        for (int i = 0; i < ids.size(); i += IN_CLAUSE_CHUNK_SIZE) {
            chunks.add(ids.subList(i, Math.min(i + IN_CLAUSE_CHUNK_SIZE, ids.size())));
        }
        truthCustkeyChunks = chunks;
    }
}
