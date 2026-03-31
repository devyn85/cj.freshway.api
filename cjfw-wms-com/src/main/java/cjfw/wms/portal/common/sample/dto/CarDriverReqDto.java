package cjfw.wms.portal.common.sample.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.04.17
 * @description : 샘플 기능을 구현한 DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.04.17        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDriverReqDto {

	/**
	 * 조회 조건
	 */
	
	@Schema(description = "차량ID", nullable = true, example = "SC0037")
    private String carId;
	
	@Schema(description = "차량번호", nullable = true, example = "23서울3333")
    private String carNo;
	
	@Schema(description = "물류센터코드", nullable = true, example = "2600")
    private String defDccode;
		
	@Schema(description = "삭제여부", nullable = true, example = "Y")
    private String delYn;
	
	@Schema(description = "계약유형", nullable = true, example = "DELIVERY")
    private String contractType;
	
	/**
	 * 신규/수정
	 */
	
	@Schema(description = "단축번호", nullable = true, example = "0000")
    private String shortNo;	
	
	@Schema(description = "상태", nullable = true, example = "0")
    private String status;
	
}