package cjfw.wms.ms.dto;

import java.util.List;

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
 * @date        : 2025.07.15
 * @description : 기본LOC 정보 조회 조건 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.15        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "기본LOC 정보 정보 조회 조건 DTO")
public class MsSkuDcLocChangeReqDto extends CommonProcedureDto {
    @Schema(description = "물류센터 코드", example = "DC001")
    private String dcCode;

    @Schema(description = "존", example = "ZONE01")
    private String zone;

    @Schema(description = "FROM 로케이션", example = "LOC001")
    private String fromLoc;
    
    @Schema(description = "TO 로케이션", example = "LOC001")
    private String toLoc;
    
    @Schema(description = "로케이션", example = "LOC001")
    private String loc;

    @Schema(description = "상품 코드", example = "SKU001")
    private String sku;

    @Schema(description = "삭제여부", example = "Y")
    private String delYn;
    
    @Schema(description = "체크여부", example = "Y")
    private String checkYn;
    
    /** processType */
	@Schema(description = "processType", example = "SPMS_CUSTDLVINFO_EXLCHK")
	private String processtype;
    
    /** 저장 목록 */
	@Schema(description = "저장 목록", example = "")
	private List<MsSkuDcLocChangeReqDto> locList;
}
