package cjfw.wms.ms.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.09.04
 * @description : 차량출입정보 Entity
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.04        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "차량출입정보 Entity") 
public class MsCarDriverEntryInfoEntity extends CommonDto {
	@Schema(description = "시리얼키")
    private String entrySerialKey;
	
	@Schema(description = "센터코드", maxLength = 10)
    private String dcCode;
	
	@Schema(description = "차량번호")
    private String carNo;
	
	@Schema(description = "작업인원수", maxLength = 30)
    private String popno;
	
	@Schema(description = "출차그룹", maxLength = 30)
    private String outGroupCd;

    @Schema(description = "TC 센터코드", maxLength = 10)
    private String tcDcCode;

    @Schema(description = "도크번호", maxLength = 20)
    private String dockno;

    @Schema(description = "최초등록시간 (자동 생성)", accessMode = Schema.AccessMode.READ_ONLY)
    private String addDate;

    @Schema(description = "최종변경시간 (자동 갱신)", accessMode = Schema.AccessMode.READ_ONLY)
    private String editDate;

    @Schema(description = "최초등록자", maxLength = 24)
    private String addWho;

    @Schema(description = "최종변경자", maxLength = 24)
    private String editWho;
}


