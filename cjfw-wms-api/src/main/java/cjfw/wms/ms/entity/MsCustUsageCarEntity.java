package cjfw.wms.ms.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.08.29 
 * @description : 거래처별전용차량정보 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.29 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MsCustUsageCarEntity extends CommonDto {
			
	/** 조회 기간 (종료) */
    @Schema(description = "조회 기간 (종료)", example = "")
    private String periodTo;

    /** 조회 기간 (시작) */
    @Schema(description = "조회 기간 (시작)", example = "")
    private String periodFrom;

    /** 운전자 ID/이름 */
    @Schema(description = "운전자 ID/이름", example = "")
    private String driverIdNm;
    
    /** 거래처 코드 */
    @Schema(description = "거래처 코드", example = "")
    private String custkey;
    
    /** 차량번호 */
    @Schema(description = "차량번호", example = "")
    private String carno;

    /** 거래처차량유형 */
    @Schema(description = "거래처차량유형", example = "")
    private String custcartype;

    /** 시작일 */
    @Schema(description = "시작일", example = "")
    private String fromdate;

    /** 종료일 */
    @Schema(description = "종료일", example = "")
    private String todate;

    /** 우선순위 */
    @Schema(description = "우선순위", example = "")
    private String priority;

    /** 운전자1 */
    @Schema(description = "운전자1", example = "")
    private String driver1;

    /** 운전자2 */
    @Schema(description = "운전자2", example = "")
    private String driver2;

    /** 메모 */
    @Schema(description = "메모", example = "")
    private String memo;
    
    /** 적용여부 */
    @Schema(description = "적용여부", example = "")
    private String applyYn;
    
    /** 센터코드 */
    @Schema(description = "센터코드", example = "")
    private String dccode;
	
}