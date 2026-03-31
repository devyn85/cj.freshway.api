package cjfw.wms.ms.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "실비차 세팅 응답 요청 DTO")
public class MsOutGroupSettingResDto {
	
	@Schema(description = "센터 조차 목록")
	private List<OutGroup> outGroupList;
	
	@Schema(description = "센터 조차 세팅 리스트")
	private List<OutGroupSetting> outGroupSettingList;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class OutGroup {
		
		@Schema(description = "조차코드", example = "10")
		private String key;
		
		@Schema(description = "조차명", example = "1조")
		private String value;
	}
	
	@Data
	public static class OutGroupSetting {
	
		@Schema(description = "로케이션", example = "2040")
		private String dccode;
		
		@Schema(description = "조차코드", example = "10")
		private String outGroupCd;
		
		@Schema(description = "조차명", example = "1조")
		private String outGroupNm;
		
		@Schema(description = "최대중량", example = "10")
		private String maxWeight;
		
		@Schema(description = "최대 CBM", example = "10")
		private String maxCbm;
		
		@Schema(description = "최대 착지수", example = "10")
		private String maxLoadQty;
		
	}

}
