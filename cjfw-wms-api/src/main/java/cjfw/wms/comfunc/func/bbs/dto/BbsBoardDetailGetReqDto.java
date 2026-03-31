package cjfw.wms.comfunc.func.bbs.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BbsBoardDetailGetReqDto {
	@NotNull(message = "게시번호는 필수 값입니다.")
	@Min(value=1, message="0보다 큰 게시번호를 입력하세요.")
	private Integer bbsSeq;     /* 게시번호 */
}
