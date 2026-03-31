package cjfw.wms.wd.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.11.03 
 * @description : 출고확정처리 세금계산서 발행 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.03 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "출고확정처리 세금계산서 발행 결과")
public class WdShipmentResBillYnDto {
	/** 응답 결과 코드 */
	@Schema(description = "응답 결과 코드", example = "")
	private String result;
	
	/** 응답 에러 메시지 */
	@Schema(description = "응답 에러 메시지", example = "")
	private String errorMsg;
    /**
     * 세금계산서발행여부
     */
    @Schema(description = "세금계산서발행여부", example = "Y")
    private String zbillyn;
}
