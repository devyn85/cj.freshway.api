package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.08.01 
 * @description : 출차그룹 조회 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.01 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "출차그룹 조회 요청")
public class MsVehicleExitGroupCfgPopupReqDto extends CommonDto {
    @Schema(description = "DC 코드", example = "2600")
    private String dcCode;
    
    @Schema(description="커스텀 물류센터 코드", example="2600")
    private String customDccode;
    

    @Schema(description = "출차 그룹 코드", example = "10")
    private String outGroupCd;

    @Schema(description = "입차 시간 (HH:MM)", example = "08:00")
    private String inTime;

    @Schema(description = "출차 시간 (HH:MM)", example = "18:00")
    private String outTime;

    @Schema(description = "사용 여부 (Y/N)", example = "Y")
    private String useYn;

    @Schema(description = "등록자", example = "ADMIN")
    private String addWho;

    @Schema(description = "수정자", example = "ADMIN")
    private String editWho;
    
    @Schema(description = "비고")
    private String rmk;
}
