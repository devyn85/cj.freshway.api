package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.05.30
 * @description : 저장위치정보 목록 조회 요청 dto
 * @issues : <pre>
 * -----------------------------------------------------------
 * DATE AUTHOR MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.30 KwonJungYun (jungyun8667@cj.net) 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "저장위치정보 요청")
public class MsPlantXSLListReqDto extends CommonDto {

	/** 물류코드 */
	@Schema(description = "물류코드", nullable = false, example = "")
	private String dccode;

	/** 센터 */
	@Schema(description = "플랜트", nullable = false, example = "")
	private String plant;

	/** 저장위치 */
	@Schema(description = "저장위치", nullable = false, example = "")
	private String organize;

	/** 계약구분 */
	@Schema(description = "계약구분", nullable = false, example = "")
	private String contractyn;

	/** 현재고 */
	@Schema(description = "현재고", nullable = false, example = "")
	private String qtyyn;
}
