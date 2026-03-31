package cjfw.wms.dp.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.05.22
 * @description : 입고검수처리 조회 기능을 구현한 DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.22        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "입고검수처리 MASTER 조회 DTO")
public class DpInspectHeaderResDto {
	@Schema(description = "발송 고객사 코드")
    private String fromCustKey;

    @Schema(description = "발송 고객사 명")
    private String fromCustName;

    @Schema(description = "검수 예정 건수")
    private BigDecimal inspectPlanCnt;

    @Schema(description = "검수 완료 건수")
    private BigDecimal inspectedCnt;

    @Schema(description = "검수율")
    private BigDecimal inspectRate;

    @Schema(description = "상태 코드")
    private String status;

    @Schema(description = "상태 명")
    private String statusName;

    @Schema(description = "물류센터 코드")
    private String dcCode;

    @Schema(description = "고객사 코드")
    private String storerKey;

    @Schema(description = "발행 유형")
    private String issueType;

    @Schema(description = "발행 유형 명")
    private String issueTypeName;

    @Schema(description = "전표 일자", example = "20230101")
    private String slipDt;

    @Schema(description = "전표 번호")
    private String slipNo;

    @Schema(description = "문서 번호 (DP)")
    private String docNoDp;

    	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
