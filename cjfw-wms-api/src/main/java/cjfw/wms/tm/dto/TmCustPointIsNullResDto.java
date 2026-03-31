package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.09.22 
 * @description : 실착지 좌표 Null 데이터 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.22 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "실착지 좌표 Null 데이터 응답 DTO")
public class TmCustPointIsNullResDto {
	
	@Schema(description = "고객사코드")
	private String storerkey;
	
	@Schema(description = "거래처 유형")
	private String custType;
	
	@Schema(description = "거래처 키")
	private String dlvcustkey;
	
	@Schema(description = "주소")
	private String address;

    @Schema(description = "위도", example = "65.123456")
    private String latitude;

    @Schema(description = "경도", example = "123.123456")
    private String longitude;
	
}
