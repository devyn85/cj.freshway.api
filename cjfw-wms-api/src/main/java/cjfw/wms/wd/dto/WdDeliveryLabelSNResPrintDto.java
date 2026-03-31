package cjfw.wms.wd.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.10.15 
 * @description : 이력배송라벨출력 출력 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.15 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "이력배송라벨출력 출력 결과")
public class WdDeliveryLabelSNResPrintDto extends CommonDto{
	/** 레포트 헤더 리스트	 */
	List<WdDeliveryLabelSNResPrintHeaderDto> reportHeaderList;
	/** 레포트 상세 리스트	 */
	List<WdDeliveryLabelSNResPrintDetailDto> reportDetailList;
	/** 회수리스트	 */
	List<WdDeliveryLabelSNResPrintReturnDto> reportReturnList;
}
