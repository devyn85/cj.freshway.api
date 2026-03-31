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
 * @date : 2025.09.08
 * @description : 지표 > 센터 운영 > 입고 결품 현황 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.08 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "지표 > 센터 운영 > 입고 결품 현황 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpDpShortageResultReqDto extends CommonProcedureDto {
	
	/* 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;
	
	/* 입고일자 */
	@Schema(description = "입고일자")
	private String docdt;

	/* 결품사유 */
	@Schema(description = "결품사유")
	private String reasoncode;

	/* 귀책구분 */
	@Schema(description = "귀책구분")
	private String reasontype;
	
	/* 협락사코드 */
	@Schema(description = "협락사코드")
	private String fromCustkey;
	
	/* 협락사코드-멀티 */
	@MultiSearch
    @Schema(description = "협락사코드-멀티")
    private List<List<String>> fromCustkeyMulti; 
	
	/* 수급담당 */
	@Schema(description = "수급담당")
	private String pomdcode;	

}
