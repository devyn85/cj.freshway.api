package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.17
 * @description : 기준정보 > 사용자및센터정보 > 물류센터별창고관리 요청 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.17        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "물류센터별창고관리 조회 응답")
public class CmDcXOrganizeManagerResDto {
	@Schema(description = "데이터 번호")
    private String serialKey;

    @Schema(description = "센터 코드", example = "DC001")
    private String dcCode;

    @Schema(description = "조직 코드", example = "ORG001")
    private String organize;
    
    @Schema(description = "창고명", example = "창고1")
    private String description;

    @Schema(description = "상태")
    private String status;

    @Schema(description = "삭제 여부")
    private String delYn;

    @Schema(description = "최초 등록 시간", example = "YYYYMMDDHH24MISS")
    private String addDate;

    @Schema(description = "최종 변경 시간", example = "YYYYMMDDHH24MISS")
    private String editDate;

    @Schema(description = "최초 등록자")
    private String addWho;

    @Schema(description = "최종 변경자")
    private String editWho;
    
    @Schema(description = "최종 변경자")
    private String editWhoNm;
    
    @Schema(description = "최초 등록자")
    private String addWhoNm;
    
    @Schema(description = "코드", example = "BASE001")
    private String comCd;
    
    @Schema(description = "코드명", example = "[ORG001]창고1")
    private String comNm;


}