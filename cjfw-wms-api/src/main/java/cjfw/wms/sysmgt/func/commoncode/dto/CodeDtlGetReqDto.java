package cjfw.wms.sysmgt.func.commoncode.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeDtlGetReqDto {
	@Schema(description = "공통그룹코드", example = "TPL_TIMEZONE", nullable = false)
    @NotEmpty(message = "공통그룹코드는 필수 값입니다.")
    private String comGrpCd;
	
	@Schema(description = "사용여부", example = " ", nullable = true)
    @JsonIgnore
    private String useYn;
}
