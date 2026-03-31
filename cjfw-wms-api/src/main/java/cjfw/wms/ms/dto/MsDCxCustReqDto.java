package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
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
 * @date        : 2025.07.08
 * @description : 센터별거래처관리 조회 조건 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.08        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "센터별거래처관리 조회 조건 DTO")
public class MsDCxCustReqDto extends CommonDto {
	@Schema(description = "데이터 번호")
    private String serialKey;

    @Schema(description = "멀티 센터 코드", example = "DC001")
    private String multiDcCode[];
    
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
    
    @Schema(description = "추가일자 시작")
    private String addDateFrom;
    
    @Schema(description = "추가일자 종료")
    private String addDateTo;
}
