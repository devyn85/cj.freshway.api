package cjfw.wms.ms.entity;

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
 * @date : 2025.08.04
 * @description : 출차그룹설정 팝업 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.04 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "출차그룹설정 팝업 Entity")
@EqualsAndHashCode(callSuper = false)
public class MsVehicleExitGroupCfgPopupEntity extends CommonDto {
	@Schema(description = "물류센터코드")
    private String dcCode;

	@Schema(description = "출차그룹")
    private String outGroupCd;

	@Schema(description = "입차시간")
    private String inTime;

	@Schema(description = "출차시간")
    private String outTime;

	@Schema(description = "사용여부")
    private String useYn;

	@Schema(description = "작성일")
    private String addDate;

	@Schema(description = "수정일")
    private String editDate;

	@Schema(description = "작성자")
    private String addWho;

	@Schema(description = "수정자")
    private String editWho;
	
    @Schema(description = "비고")
    private String rmk;
}
