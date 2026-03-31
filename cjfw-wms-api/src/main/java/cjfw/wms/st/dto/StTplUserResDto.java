package cjfw.wms.st.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : parkyosep(dytpq362@cj.com)
 * @date : 2025.11.06
 * @description : 화주 정보 조회 기능을 구현한 Controller Class 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.06 parkyosep(dytpq362@cj.com) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "차량+권역 팝업 조회 Response DTO")
public class StTplUserResDto {
	/** 창고코드 */
	@Schema(description = "화주id", nullable = false, example = "")
	private String code;
	
	/** 창고명 */
	@Schema(description = "화주명", nullable = false, example = "")
	private String name;
	
	/** 거래처 */
	@Schema(description = "거래처", nullable = false, example = "")
	private String custkey;
	
	/** 거래처명 */
	@Schema(description = "거래처명", nullable = false, example = "")
	private String custNm;
	
	/** 협력사 */
	@Schema(description = "협력사", nullable = false, example = "")
	private String vendor;
	
	/** 협력사명 */
	@Schema(description = "협력사명", nullable = false, example = "")
	private String vendorNm;
		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
