package cjfw.wms.comfunc.func.file.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 샘플 페이지 저장 Request DTO
 */
@Data
public class SampleFilePagePostReqDto {
	@NotNull(message = "게시번호는 필수 값입니다.")
	@Min(value=1, message="0보다 큰 게시번호를 입력하세요.")
	private Integer bbsSeq;	/* 게시번호 */
	@NotNull(message = "첨부파일 번호는 필수 값입니다.")
	@Min(value=1, message="0보다 큰 첨부파일 번호를 입력하세요.")
	private Integer attchFileGrpNo;	/* 첨부파일 번호 */
}

