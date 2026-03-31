package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : LeeHyunsung (zoot0134@cj.net)
 * @date : 2025.11.17
 * @description : 센터 권역 신규 행정동 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.17 LeeHyunsung (zoot0134@cj.net) 생성 </pre>
 */
@Data
@NoArgsConstructor
@Schema(description = "센터 권역 신규 행정동 응답 DTO")
public class MsCenterDistrictNewHjdongResDto {
	@Schema(description = "행정동코드")
    private String hjdongCd;
	
	@Schema(description = "행정동명")
    private String hjdongNm;
	
	@Schema(description = "시군구명")
    private String sigKorNm;
	
	@Schema(description = "시도명")
    private String ctpKorNm;

	@Schema(description = "보기유무")
    private String showYn;
}
