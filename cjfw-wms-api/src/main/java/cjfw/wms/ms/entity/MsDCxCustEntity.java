package cjfw.wms.ms.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.08
 * @description : 센터별거래처관리 Entity
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.08        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "센터별거래처관리 Entity") 
public class MsDCxCustEntity extends CommonDto {	
	@Schema(description = "시리얼키 (PK)", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal serialKey;

    @Schema(description = "센터코드", maxLength = 14, requiredMode = Schema.RequiredMode.REQUIRED)
    private String dcCode;

    @Schema(description = "고객사코드", maxLength = 20, requiredMode = Schema.RequiredMode.REQUIRED)
    private String storerKey;

    @Schema(description = "거래처코드", maxLength = 20, requiredMode = Schema.RequiredMode.REQUIRED)
    private String custKey;

    @Schema(description = "거래처 유형", maxLength = 10, requiredMode = Schema.RequiredMode.REQUIRED)
    private String custType;

    @Schema(description = "파트너사 코드", maxLength = 20)
    private String partnerKey;

    @Schema(description = "거리 유형", defaultValue = "N", maxLength = 10)
    private String distanceType;

    @Schema(description = "입고 전략", maxLength = 20)
    private String inStrategy;

    @Schema(description = "출고 전략", maxLength = 20)
    private String outStrategy;

    @Schema(description = "검수 유형", maxLength = 10)
    private String inspectType;

    @Schema(description = "거래처 전략 1", maxLength = 20)
    private String custStrategy1;

    @Schema(description = "거래처 전략 2", maxLength = 20)
    private String custStrategy2;

    @Schema(description = "거래처 전략 3", maxLength = 20)
    private String custStrategy3;

    @Schema(description = "거래처 전략 4", maxLength = 20)
    private String custStrategy4;

    @Schema(description = "거래처 전략 5", maxLength = 20)
    private String custStrategy5;

    @Schema(description = "기타 필드 1", maxLength = 100)
    private String other01;

    @Schema(description = "기타 필드 2", maxLength = 100)
    private String other02;

    @Schema(description = "기타 필드 3", maxLength = 100)
    private String other03;

    @Schema(description = "기타 필드 4", maxLength = 100)
    private String other04;

    @Schema(description = "기타 필드 5", maxLength = 100)
    private String other05;

    @Schema(description = "메모", maxLength = 500)
    private String memo;

    @Schema(description = "I/F 파일명", maxLength = 100)
    private String ifFileName;

    @Schema(description = "상태", defaultValue = "00", maxLength = 10)
    private String status;

    @Schema(description = "삭제 여부", defaultValue = "N", maxLength = 1)
    private String delYn;

    @Schema(description = "Traffic Cop 필드", maxLength = 10)
    private String trafficCop;

    @Schema(description = "Archive Cop 필드", maxLength = 1)
    private String archiveCop;

    @Schema(description = "최초등록시간 (자동 생성)")
    private String addDate;

    @Schema(description = "최종변경시간 (자동 갱신)")
    private String editDate;

    @Schema(description = "최초등록자", defaultValue = "SYSTEM", maxLength = 24)
    private String addWho;

    @Schema(description = "최종변경자", defaultValue = "SYSTEM", maxLength = 24)
    private String editWho;
}
