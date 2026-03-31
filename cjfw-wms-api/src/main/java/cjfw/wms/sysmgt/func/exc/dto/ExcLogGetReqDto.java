package cjfw.wms.sysmgt.func.exc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 시스템 예외 이력 조회 Request DTO
 */
@Data
public class ExcLogGetReqDto {
	@Schema(description = "시작일자", example = "2022-07-01", nullable = false)
	@NotEmpty(message = "시작일자는 필수 값입니다.")
	private String fromDt;
	@Schema(description = "종료일자", example = "2022-07-28", nullable = false)
	@NotEmpty(message = "종료일자는 필수 값입니다.")
	private String thruDt;

}
