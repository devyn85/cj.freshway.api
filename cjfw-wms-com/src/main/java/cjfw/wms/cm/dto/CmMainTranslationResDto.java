package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : breaker3317 
 * @date : 2025.10.26 
 * @description : 다국어 응답
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.26 breaker3317 생성 </pre>
 */
@Data
@Schema(description = "다국어 응답")
public class CmMainTranslationResDto {
	/** 라벨코드 */
	@Schema(description = "라벨코드", example = "BOXCNT")
	private String labelCd;
	
	/** 라벨유형 */
	@Schema(description = "라벨유형", example = "LBL")
	private String labelType;
	
	/** 정렬유형 */
	@Schema(description = "정렬유형", example = "L")
	private String alignType;
	
	/** 라벨명 */
	@Schema(description = "라벨명", example = "BOX수량")
	private String labelNm;
}
