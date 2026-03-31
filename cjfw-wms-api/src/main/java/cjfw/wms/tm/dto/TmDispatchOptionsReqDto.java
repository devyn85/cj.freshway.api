package cjfw.wms.tm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : System Generated
 * @date : 2025.09.10
 * @description : 차량 배차 옵션 저장 요청 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.10 System Generated      생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "차량 배차 옵션 저장 요청 DTO")
public class TmDispatchOptionsReqDto extends CommonDto {

    @Schema(description = "배차 유형별 옵션 설정 1:배송 / 3:조달", example = "1", nullable = false)
	private String planOptionType; 
	
    @Schema(description = "센터코드", example = "2600", nullable = false)
    private String dccode;
	
	/** 최대 중량 사용 여부 */
    @Schema(description = "최대 중량 사용 여부", example = "true", nullable = false)
    private Boolean useMaxWeight;
    
    /** 스킬 사용 여부 */
    @Schema(description = "스킬 사용 여부", example = "true", nullable = false)
    private Boolean useSkills;

    /** 최대 위치 사용 여부 */
    @Schema(description = "최대 위치 사용 여부", example = "false", nullable = false)
    private Boolean useMaxLocation;

    /** 라운드 사용 여부 */
    @Schema(description = "다회전 여부", example = "true", nullable = false)
    private Boolean useRounds;

    /** 최대 CBM 사용 여부 */
    @Schema(description = "최대 CBM 사용 여부", example = "true", nullable = false)
    private Boolean useMaxCbm;

    /** 최대 POP 사용 여부 */
    @Schema(description = "최대 POP 사용 여부", example = "true", nullable = false)
    private Boolean useMaxPop;

    /** 최대 CBM (deprecated - 톤급별 비율로 대체) */
    @Schema(description = "최대 CBM 증/차감 옵션 (deprecated)", example = "100.0", nullable = true)
    @Deprecated
    private double offsetCbm;

    /** POP 개수 */
    @Schema(description = "최대 POP 개수", example = "50", nullable = true)
    private int popCount;

    /** 1톤 CBM 허용비율 (%) */
    @Schema(description = "1톤 CBM 허용비율 (%)", example = "100", nullable = true)
    private Integer cbmRatio1;

    /** 1.4톤 CBM 허용비율 (%) */
    @Schema(description = "1.4톤 CBM 허용비율 (%)", example = "100", nullable = true)
    private Integer cbmRatio1_4;

    /** 2.5톤 CBM 허용비율 (%) */
    @Schema(description = "2.5톤 CBM 허용비율 (%)", example = "100", nullable = true)
    private Integer cbmRatio2_5;

    /** 3.5톤 CBM 허용비율 (%) */
    @Schema(description = "3.5톤 CBM 허용비율 (%)", example = "100", nullable = true)
    private Integer cbmRatio3_5;

    /** 5톤 CBM 허용비율 (%) */
    @Schema(description = "5톤 CBM 허용비율 (%)", example = "100", nullable = true)
    private Integer cbmRatio5;

    /** 11톤 CBM 허용비율 (%) */
    @Schema(description = "11톤 CBM 허용비율 (%)", example = "100", nullable = true)
    private Integer cbmRatio11;

}
