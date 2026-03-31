package cjfw.wms.comfunc.func.file.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 샘플 파일 저장 Request DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SampleFilePopupGetReqDto {
	@NotNull(message = "파일그룹 번호는 필수 값입니다.")
	@Min(value=1, message="0보다 큰 파일그룹 번호를 입력하세요.")
    private Integer attchFileGrpNo;  //첨부파일 그룹 번호
}

