package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.08.04 
 * @description : 센터이체마스터 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class MsCenterStoClosetypeExcelResDto {
	
	/** 센터코드 */
	@Schema(description = "센터코드", example = "")
	private String dccode;
	
	/** 센터마감유형 */
	@Schema(description = "센터마감유형", example = "")
	private String dcClosetype;
		
	/** 상품코드 */
	@Schema(description = "상품코드", example = "")
	private String sku;
	
	/** 적용여부 */
	@Schema(description = "적용여부", example = "")
	private String applyYn;
	
	/** 센터 유효성 */
	@Schema(description = "센터 유효성", example = "")
	private String dccodeChk;
	
	/** 마감유형 유효성 */
	@Schema(description = "마감유형 유효성", example = "")
	private String dcClosetypeChk;
	
	/** 상품 유효성 */
	@Schema(description = "상품 유효성", example = "")
	private String skuChk;
	
	/** 업데이트여부 */
	@Schema(description = "업데이트여부", example = "")
	private String updateYn;
	
	/** 중복여부 */
	@Schema(description = "중복여부", example = "")
	private String duplicateChk;
	
}