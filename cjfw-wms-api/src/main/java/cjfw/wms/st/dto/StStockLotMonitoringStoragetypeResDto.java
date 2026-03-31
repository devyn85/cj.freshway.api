package cjfw.wms.st.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2026.02.06 
 * @description : 유통기한 요약장표 저장조건 조회 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.02.06 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class StStockLotMonitoringStoragetypeResDto {
			
	/** 구분 */
    @Schema(description = "구분", example = "")
    private String gubun;    

    /** 전용/범용 */
    @Schema(description = "전용/범용", example = "")
    private String type;
    
    /** 저장조건 */
    @Schema(description = "저장조건", example = "")
    private String storagetype;

    /** 1 */
    @Schema(description = "1", example = "")
    private String colM0;

    /** 2 */
    @Schema(description = "1", example = "")
    private String colM1;
    
    /** 3 */
    @Schema(description = "1", example = "")
    private String colM2;
    
    /** 4 */
    @Schema(description = "1", example = "")
    private String colM3;
    
    /** 5 */
    @Schema(description = "1", example = "")
    private String colM4;
    
    /** 6 */
    @Schema(description = "1", example = "")
    private String colTotal;
	
    /** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}