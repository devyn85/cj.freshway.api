package cjfw.wms.cm.dto;

import cjfw.wms.common.annotation.MaskingTelno;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.05.09 
 * @description : 기사 검색 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "차량 검색 결과")
public class CmCarPopupResDto {
	/** 차량번호 */
	@Schema(description = "차량번호", nullable = false, example = "Y00050")
	private String code;
	
	/** 운전자 */
	@Schema(description = "운전자", nullable = false, example = "홍길동")
	private String name;
	
	/** contracttype */
	@Schema(description = "contracttype", nullable = false, example = "홍길동")
	private String contracttype;

	/** contracttypeNm */
	@Schema(description = "contracttypeNm", nullable = false, example = "홍길동")
	private String contracttypeNm;
	
    /** 운전자 1 전화번호 */
    @MaskingTelno
    @Schema(description = "운전자 1 전화번호")
    private String phone1;

    /** 운전자 2 전화번호 */
    @MaskingTelno
    @Schema(description = "운전자 2 전화번호")
    private String phone2;    	
}
