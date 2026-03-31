package cjfw.wms.kp.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
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
 * @date : 2025.09.09
 * @description : 지표 > 재고 운영 > 물류센터 Capa 스캔 현황 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.09 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "지표 > 재고 운영 > 물류센터 Capa 스캔 현황 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpLocationCapaScanReqDto extends CommonDto {
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String fixdccode;	
    
    /** 물류센터(다중검색) */
    @MultiSearch
    @Schema(description = "물류센터-다중검색")
    private List<String> fixdccodeMulti;      
	
	/** smydt */
	@Schema(description = "smydt")
	private String smydt;

	/** dccode */
	@Schema(description = "dccode")
	private String dccode;
	
	/** storagetype */
	@Schema(description = "storagetype")
	private String storagetype;

	/** zone */
	@Schema(description = "zone")
	private String zone;

	/** loc */
	@Schema(description = "loc")
	private String loc;

	/** fromSlipdt */
	@Schema(description = "fromSlipdt")
	private String fromSlipdt;

	/** toSlipdt */
	@Schema(description = "toSlipdt")
	private String toSlipdt;
}
