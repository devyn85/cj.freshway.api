package cjfw.wms.cm.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KimSunHo (sunhokim6229@cj.net) 
 * @date : 2026.02.20 
 * @description : 배송이슈 사진 파일 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.02.20 KimSunHo (sunhokim6229@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "배송이슈 사진 파일 조회 요청")
public class CmIssuePicturePopupReqDto extends CommonDto {
	/** 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;
	
	/** 배송일 */
	@Schema(description = "배송일")
	private String deliverydt;
	
	/** 고객유형 */
	@Schema(description = "고객유형")
	private String custtype;
	
	/** 실착지코드 */
	@Schema(description = "실착지코드")
	private String truthcustkey;

	/** 차량번호 */
	@Schema(description = "차량번호")
	private String carno;
	
	/** 회차 */
    @Schema(description = "회차")
    private String priority;
    
}
