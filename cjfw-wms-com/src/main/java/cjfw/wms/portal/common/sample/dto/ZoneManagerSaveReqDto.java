package cjfw.wms.portal.common.sample.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : KimSunHo (sunhokim6229@cj.net)
 * @date        : 2025.04.21
 * @description : 존 정보 저장 Request DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.04.21        KimSunHo (sunhokim6229@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "존 정보 저장 Request DTO")
public class ZoneManagerSaveReqDto {
	@Schema(description = "존 정보 리스트", example = "", nullable = false)
    //@Valid
    @NotEmpty	
    private List<ZoneManagerSaveReqDto.Zone> zones;
	
    @Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
    public static class Zone{
    	@Schema(description = "데이터번호", example = "", nullable = true)
    	private Integer serialkey;
    	
    	@Schema(description = "센터코드", example = "", nullable = false)
    	@NotEmpty(message = "센터코드는 필수 값입니다.")
        private String dccode;
    	
    	@Schema(description = "존코드", example = "", nullable = false)
    	@NotEmpty(message = "존코드는 필수 값입니다.")
    	private String zone; 
    	
    	@Schema(description = "존 설명", example = "", nullable = true)
    	private String description; 
    	
    	@Schema(description = "입고 전략", example = "", nullable = true)
    	private String instrategy; 
    	
    	@Schema(description = "출고 전략", example = "", nullable = true)
    	private String outstrategy; 
    	
    	@Schema(description = "저장유형", example = "", nullable = true)
    	private String storagetype; 
    	
    	@Schema(description = "삭제여부", example = "", nullable = true)
    	private String delYn;
    	
    	@Schema(description = "상태", example = "", nullable = true)
    	private String status;
    	
    	@Schema(description = "구간(방)", example = "", nullable = true)
    	private String zoneGugan;
    	
    	@Schema(description = "GridRow 저장 구분", example = "", allowableValues = {"I", "U", "D"})
    	@NotEmpty(message = "GridRow 저장 구분은 필수 값입니다.")
        private String rowStatus; // "I"(등록), "U"(수정), "D"(삭제)
    }
}
