package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.05.08 
 * @description : 거래처 검색 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.08 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "거래처 검색 요청")
public class CmSearchCustReqDto extends CommonDto {
	/** 거래처코드/명 */
	@Schema(description = "거래처코드/명", nullable = false, example = "시립")
	private String name;
	
	@Schema(description = "다중 선택", example = "")
	private String multiSelect;
	
	@Schema(description = "999개 청크 목록(OR IN 생성용)")
	private java.util.List<java.util.List<String>> codeGroups;
	
	@Schema(description = "거래처팝업 확장 여부 파라미터(FWNEXTWMS-2660)", example = "Y")
	private String expandedColumns;
	
	@Schema(description = "물류센터 코드", example = "2600")
	private String dcCode;
	
	@Schema(description = "거래처유형", example = "C")
	private String custType;
}
