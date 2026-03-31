package cjfw.wms.st.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.09.10 
 * @description : 출고재고보충(수원3층) 보충생성용 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.10 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "출고재고보충(수원3층) 보충생성용") 
public class StLocMoveRPResSaveDto extends CommonProcedureDto {
	
	/** 출고일자 */
	@Schema(description = "출고일자", nullable = false, example = "")
	private String docdt;
	/** 출고일자 */
	@Schema(description = "출고일자", nullable = false, example = "")
	private String slipdt;
	/** 상품코드 */
	@Schema(description = "상품코드", nullable = false, example = "")
	private String sku;
	
	/** fromzone */
	@Schema(description = "fromzone", nullable = false, example = "")
	private String fromzone;
	
	/** tozone */
	@Schema(description = "tozone", nullable = false, example = "")
	private String tozone;
	
	/** custkey */
	@Schema(description = "custkey", nullable = false, example = "")
	private String custkey;
	

	/** fixdccode : 조회조건 */
	@Schema(description = "fixdccode", nullable = false, example = "")
	private String fixdccode;
	
}
