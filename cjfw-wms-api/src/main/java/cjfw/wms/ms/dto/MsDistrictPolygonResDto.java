package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.08.19 
 * @description : 권역 폴리곤 조회 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.19 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@Schema(description = "권역 폴리곤 조회 결과 DTO")
public class MsDistrictPolygonResDto {
	
	@Schema(description = "행정코드")
	private String hjdongCd;
	
	@Schema(description = "시도명")
	private String ctpKorNm;
	
	@Schema(description = "시군구명")
	private String sigKorNm;
	
	@Schema(description = "행정동명")
	private String hjdongNm;
	
	@Schema(description = "폴리곤 geojson")
	private String geojson;
	
    @Schema(description = "유효 시작일")
    private String fromDate;

    @Schema(description = "유효 종료일")
    private String toDate;

    @Schema(description = "이동사유")
    private String mvmnResCd;

}
