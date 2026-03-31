package cjfw.wms.om.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.23 
 * @description : 마감주문반영 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.23 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "마감주문반영 요청") 
public class OmCloseMonitoringReqDto extends CommonProcedureDto {
	/** 메인그리드 저장 리스트 */
	List<OmCloseMonitoringResDto> saveList;
	
	/** 배송일자 */
	@Schema(description = "배송일자", nullable = false, example = "")
	private String deliverydt;
	
	
	/** 식별번호유무 */
	@Schema(description = "식별번호유무", nullable = false, example = "")
	private String serialyn;
	
	/** 마감기준시간 */
	@Schema(description = "마감기준시간", nullable = false, example = "")
	private String closetimeStandard;
	

	/** 마감기준시간(다중검색) */
	@MultiSearch
    @Schema(description = "마감기준시간-다중OR검색")
    private List<String> closetimeStandardMulti; 
	
	/** fixdccode */
	@Schema(description = "fixdccode", nullable = false, example = "")
	private String fixdccode;
	
	
	
	
}
