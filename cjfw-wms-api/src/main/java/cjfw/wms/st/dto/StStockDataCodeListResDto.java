package cjfw.wms.st.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.16 
 * @description : 존 리스트 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.16 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "존 리스트 결과")
public class StStockDataCodeListResDto {
	@Schema(description = "dccode", example = "")
    private String dccode;
    @Schema(description = "basecode", example = "")
    private String basecode;
    @Schema(description = "basedescr", example = "")
    private String basedescr;
		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
