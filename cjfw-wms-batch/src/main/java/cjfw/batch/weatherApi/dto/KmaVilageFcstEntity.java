package cjfw.batch.weatherApi.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : yewon.kim (yewon.kim9@cj.net)
 * @date : 2025.09.05
 * @description : 날씨 정보 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.05 yewon.kim (yewon.kim9@cj.net) 생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class KmaVilageFcstEntity{
	/** J기준일시 */
	@Schema(description = "기준일시", example = "202509051120")
	private String stdDt;
	
	/** 발표일자 */
	@Schema(description = "발표일자", example = "20250905")
	private String baseDate;

	/** 발표시각 */
	@Schema(description = "발표시각", example = "1100")
	private String baseTime;

	/** 예보일자 */
	@Schema(description = "예보일자", example = "20250905")
	private String fcstDate;

	/** 예보시각 */
	@Schema(description = "예보시각", example = "1100")
	private String fcstTime;

	/** 예보항목 코드 */
	@Schema(description = "예보항목 코드", example = "TMP")
	private String category;
	
	/** 예보 값 */
	@Schema(description = "예보 값", example = "23")
	private String fcstValue;

	/** 격자 X 좌표 */
	@Schema(description = "격자 X 좌표", example = "60")
	private int nx;

	/** 격자 Y 좌표 */
	@Schema(description = "격자 Y 좌표", example = "127")
	private int ny;

}