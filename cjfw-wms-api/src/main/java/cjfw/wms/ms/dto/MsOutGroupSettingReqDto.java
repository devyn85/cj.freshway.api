package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "실비차 세팅 저장 요청 DTO")
public class MsOutGroupSettingReqDto {
	
	@Schema(description = "센터코드", example = "2600")
	private String dccode;
	
	@Schema(description = "조차코드", example = "10")
	private String outGroupCd;
	
	@Schema(description = "최대 중량", example = "10")
	private String maxWeight;
	
	@Schema(description = "최대 CBM", example = "10")
	private String maxCbm;
	
	@Schema(description = "최대 착지수", example = "10")
	private String maxLoadQty;
	
	@Schema(description = "차량 수", example = "3")
	private String carCount;

}
