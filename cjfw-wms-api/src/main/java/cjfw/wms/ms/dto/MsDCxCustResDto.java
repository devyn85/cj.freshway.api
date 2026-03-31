package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.08
 * @description : 센터별거래처관리 조회 결과 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.08        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@Schema(description = "센터별거래처관리 조회 결과 DTO")
public class MsDCxCustResDto {
	@Schema(description = "데이터 번호")
    private String serialKey;

    @Schema(description = "센터 코드", example = "DC001")
    private String dcCode;

    @Schema(description = "고객사 코드", example = "STORER001")
    private String storerKey;

    @Schema(description = "거래처 코드", example = "CUST001")
    private String custKey;

    @Schema(description = "거래처명")
    private String custName;

    @Schema(description = "거래처 유형")
    private String custType;

    @Schema(description = "파트너 키")
    private String partnerKey;

    @Schema(description = "거리 유형")
    private String distanceType;

    @Schema(description = "상태")
    private String status;

    @Schema(description = "삭제 여부")
    private String delYn;

    @Schema(description = "거래처 유형 코드")
    private String custTypeCd;
    
    @Schema(description = "최초 등록자")
    private String addWho;

    @Schema(description = "최초 등록 시간", example = "YYYY-MM-DD HH:mm:ss")
    private String addDate;

    @Schema(description = "최종 변경자")
    private String editWho;

    @Schema(description = "최종 변경 시간", example = "YYYY-MM-DD HH:mm:ss")
    private String editDate;
    
    @Schema(description = "최종 변경자")
    private String editWhoNm;
    
    @Schema(description = "최초 등록자")
    private String addWhoNm;
}
