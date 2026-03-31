package cjfw.wms.tm.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TmCrmWmsMemoEntity extends CommonDto {

    /** 조회 시 체크 여부 (고정값 '0') */
    @Schema(description = "체크 여부", example = "")
    private String checkYn;
    
}
