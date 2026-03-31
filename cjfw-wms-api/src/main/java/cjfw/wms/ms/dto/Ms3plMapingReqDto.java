package cjfw.wms.ms.dto;

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
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.11.18
 * @description : 3PL전산기준목록 Request DTO Class
 * @issues :
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.18 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "3PL전산기준목록 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ms3plMapingReqDto extends CommonProcedureDto {
    /** courier */
	@Schema(description = "courier")
	private String courier;

    /** courierMulti(다중OR검색) */
    @MultiSearch
    @Schema(description = "courierMulti-다중OR검색")
    private List<List<String>> courierMulti;

	/** custkey */
	@Schema(description = "custkey")
	private String custkey;

    /** custkeyMulti(다중OR검색) */
    @MultiSearch
    @Schema(description = "custkeyMulti-다중OR검색")
    private List<List<String>> custkeyMulti;
}
