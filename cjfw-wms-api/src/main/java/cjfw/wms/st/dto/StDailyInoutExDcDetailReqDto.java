package cjfw.wms.st.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : ParkJinWoo 
 * @date : 2025.06.16 
 * @description : 외부비축상품별수불현황 조회 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.16    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부비축상품별수불현황 조회 ") 
public class StDailyInoutExDcDetailReqDto extends CommonDto {	
	/**수불일자시작일자*/
	@Schema(description = "수불일자시작일자")
	private String fromDeliveryDate;
	
	/**수불일자종료일자*/
	@Schema(description = "수불일자종료일자")
	private String toDeliveryDate;
	
	/**창고*/
	@Schema(description = "창고")
	private String organize;
	
	/**상품코드*/
	@Schema(description = "상품코드")
	private String sku;
	
	/**B/L번호*/
	@Schema(description = "B/L번호")
	private String blNo;

	private String fixDcCode;
    
}
