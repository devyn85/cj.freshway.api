package cjfw.wms.kp.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.12.02
 * @description : 지표/모니터링 > 센터운영지표 > 출고결품실적 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.02 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "지표/모니터링 > 센터운영지표 > 출고결품실적 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpWdShortageResultReqDto extends CommonProcedureDto {
	
	@Schema(description = "물류센터")
	private String dccode;
	
	@Schema(description = "조회일자")
	private String docdt;
	
	@Schema(description = "조회일자")
	private String slipdt;
	
	@Schema(description = "exReasoncodeYn")
	private String exReasoncodeYn;

	@Schema(description = "reasoncode")
	private String reasoncode;
	
	@Schema(description = "reasontype")
	private String reasontype;
	
	@Schema(description = "channel")
	private String channel;
	
	@Schema(description = "skupart")
	private String skupart;

	@Schema(description = "day")
	private String day;
	
	@Schema(description = "days")
	private List<String> days;
	
	@Schema(description = "colList")
	private List<String> colList;

	/** vendor */
	@Schema(description = "vendor", nullable = false, example = "")
	private String vendor;
	
	/** vendor(다중검색) */
	@MultiSearch
    @Schema(description = "vendor-다중OR검색")
    private List<List<String>> vendorMulti; 
	
}
