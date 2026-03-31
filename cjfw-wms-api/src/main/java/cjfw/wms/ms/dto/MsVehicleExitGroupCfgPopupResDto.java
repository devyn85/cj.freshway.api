package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "출차그룹 조회 응답 DTO")
public class MsVehicleExitGroupCfgPopupResDto {
    @Schema(description = "DC 코드")
    private String dcCode;

    @Schema(description = "출차 그룹 코드")
    private String outGroupCd;
    
    @Schema(description = "출차 그룹 명")
    private String outGroupNm;

    @Schema(description = "입차 시간 (HH:MM)")
    private String inTime;

    @Schema(description = "출차 시간 (HH:MM)")
    private String outTime;

    @Schema(description = "사용 여부 (Y/N)")
    private String useYn;

    @Schema(description = "등록 일시 (yyyy-MM-dd HH:mm:ss)")
    private String addDate;

    @Schema(description = "등록자")
    private String addWho;

    @Schema(description = "수정 일시 (yyyy-MM-dd HH:mm:ss)")
    private String editDate;

    @Schema(description = "수정자")
    private String editWho;
    
    @Schema(description = "비고")
    private String rmk;
}
