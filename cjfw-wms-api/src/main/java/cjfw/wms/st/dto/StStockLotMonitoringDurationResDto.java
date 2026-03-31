package cjfw.wms.st.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2026.02.06 
 * @description : 유통기한 요약장표 소비기한 조회 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.02.06 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class StStockLotMonitoringDurationResDto {
			
	/** 구분 */
    @Schema(description = "구분", example = "")
    private String gubun;    

    /** 잔여 유통기한 */
    @Schema(description = "잔여 유통기한", example = "")
    private String percent;

    /** 전용/범용 */
    @Schema(description = "전용/범용", example = "")
    private String type;

    /** 1 */
    @Schema(description = "1", example = "")
    private String colM0;

    /** 1 */
    @Schema(description = "1", example = "")
    private String colM1;
    
    /** 1 */
    @Schema(description = "1", example = "")
    private String colM2;
    
    /** 1 */
    @Schema(description = "1", example = "")
    private String colM3;
    
    /** 1 */
    @Schema(description = "1", example = "")
    private String colM4;
    
    /** 1 */
    @Schema(description = "1", example = "")
    private String colTotal;
	
    /** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}