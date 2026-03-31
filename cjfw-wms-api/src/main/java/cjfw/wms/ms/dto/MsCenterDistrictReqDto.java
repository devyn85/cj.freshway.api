package cjfw.wms.ms.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.08.20 
 * @description : 센터 권역 데이터 요청 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.20 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "센터 권역 조회 조건 DTO")
public class MsCenterDistrictReqDto extends CommonDto {
	@Schema(description = "물류센터코드", example = "1000")
	private String dccode;

	@Schema(description = "적용 일자")
	private String effectiveDate;
	
	@Schema(description = "행정동 코드")
	private List<String> hjdongCd;
	
	@Schema(description = "센터코드 조회 리스트", hidden = true)
	private String[] gMultiDccodeList;

    @Schema(description = "FW/FO")
    private String dcgroup;
	
}
