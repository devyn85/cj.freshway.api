package cjfw.wms.ms.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "로케이션 일괄업로드 응답 DTO")
public class MsPopUploadLocationResDto {
	@Schema(description = "성공 건수", example = "28")
    private int successCount;

    @Schema(description = "오류 건수", example = "2")
    private int errorCount;

    @Schema(description = "검증/저장 결과 행 리스트")
    private List<?> rows;
    
    @Schema(description = "라인 번호", example = "1")
    private Integer fileLineNo;

    @Schema(description = "로케이션", example = "I09-0302")
    private String loc;

    @Schema(description = "검증/저장 결과 상태 (S:성공, E:오류)", example = "S")
    private String processYn;

    @Schema(description = "검증/저장 결과 메시지", example = "OK")
    private String processMsg;
	
	/** 체크여부 */
    @Schema(description = "체크여부", example = "0")
    private String checkyn;
}
