package cjfw.wms.wd.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "출차지시처리 request dto")
public class WdLoadPopReqDto extends CommonDto {
	@Schema(description = "센터코드", example = "2600")
	private String dccode;

	@Schema(description = "상차지시코드 - N:상차불가능, Y:상차가능", example = "Y")
	private String loadStatus;

	@Schema(description = "배송일자", example = "20251025")
	private String shipDt;

}
