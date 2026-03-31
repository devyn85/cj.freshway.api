package cjfw.wms.tm.dto.tempMonitor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "센터서류 업로드 조건 DTO")
public class TmTempDocReqDto {
    @Schema(description = "테이블 시리얼 번호")
    private String serialKey;
    @Schema(description = "요청 번호")
    private String reqNo;
    @Schema(description = "요청 문서")
    private String reqDoc;
}
