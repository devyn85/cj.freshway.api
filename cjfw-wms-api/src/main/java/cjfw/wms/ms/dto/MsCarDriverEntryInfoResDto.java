package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.09.04
 * @description : 차량정보 입출차정보 조회 결과 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.04        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@Schema(description = "차량정보 입출차정보 결과 DTO")
public class MsCarDriverEntryInfoResDto {
	@Schema(description = "시리얼키")
    private String entrySerialKey;
	
	@Schema(description = "차량번호")
    private String carNo;
	
	@Schema(description = "센터 코드")
    private String dcCode;

    @Schema(description = "출고 그룹 코드")
    private String outGroupCd;

    @Schema(description = "입차 시간", example = "0900")
    private String inTime;

    @Schema(description = "출차 시간", example = "1800")
    private String outTime;

    @Schema(description = "POP 번호")
    private String popno;

    @Schema(description = "TC 센터 코드")
    private String tcDcCode;

    @Schema(description = "도크 번호")
    private String dockno;
    
    @Schema(description = "출고 그룹 코드 목록")
    private String outGroupCdList;
    
    @Schema(description = "출고 그룹 명 목록")
    private String outGroupNmList;
    
    @Schema(description = "TC 센터 명")
    private String tcName;
}
