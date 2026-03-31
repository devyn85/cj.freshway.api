package cjfw.wms.ms.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.08.08
 * @description : 권역별차량관리 조회 조건 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.08        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "권역별차량관리 조회 조건 DTO")
public class MsDistrictReqDto extends CommonProcedureDto {
	
	@Schema(description = "테이블 시리얼 번호")
    private String serialKey;

    @Schema(description = "멀티 물류센터 코드", example = "2600")
    private String multiDcCode;
    
    @Schema(description = "물류센터 코드", example = "2600")
    private String dcCode;

    @Schema(description = "설명")
    private String description;

    @Schema(description = "상태")
    private String status;

    @Schema(description = "삭제 여부")
    private String delYn;
    
    @Schema(description = "차량번호", maxLength = 40)
    private String carNo;

    @Schema(description = "보조 차량1")
    private String subCarNo1;

    @Schema(description = "보조 차량2")
    private String subCarNo2;

    @Schema(description = "보조 차량3")
    private String subCarNo3;
    
    @Schema(description = "분배단위")
    private String alloUom;
    
    @Schema(description = "롤테이너(최대)")
    private String rolltainerMax;
    
    @Schema(description = "롤테이너번호FROM")
    private String rolltainerStartNo;
    
    @Schema(description = "롤테이너번호TO")
    private String rolltainerEndNo;
    
    @Schema(description = "팝번호")
    private String districtCode;
    
    @Schema(description = "배송권역")
    private String workPopNo;

    @MultiSearch
    @Schema(description = "배송권역")
    private List<List<String>>  workPopNoMulti;
    
    @Schema(description = "슈트번호")
    private String chuteNo;
    
}
