package cjfw.wms.portal.common.sample.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CntrPickingGroupDcCodeResDto {
	@Schema(description = "센터코드",nullable = false)
	private String cdNm;
	@Schema(description = "센터코드|센터이릅",nullable = false)
	private String comCd;
	@Schema(description = "센터이름",nullable = false)
	private String description;
}
