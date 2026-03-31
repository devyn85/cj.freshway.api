package cjfw.wms.ms.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.extend.CommonProcedureDto;
import cjfw.wms.ms.entity.MsPopMngEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.08.12
 * @description : 차량정보 조회 결과 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.12        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Schema(description = "차량정보 조회 결과 DTO")
public class MsCarDriverExcelResDto extends CommonProcedureDto{
	
	
	
	@Schema(description = "데이터 번호")
    private String serialKey;

    @Schema(description = "물류센터 코드")
    private String dccode;

    @Schema(description = "차량 번호")
    private String carno;

    @Schema(description = "계약 유형")
    private String contracttype;
    
    @Schema(description = "톤수")
    private String carcapacity;
    
    @Schema(description = "배송유형_배송")
    private String deliveryyn;
    
    @Schema(description = "배송유형_수송")
    private String carryyn;
    
    @Schema(description = "배송유형_일배")
    private String procsamedayyn;

    @Schema(description = "배송유형_저장")
    private String procstorageyn;
    
    @Schema(description = "배송유형_자차")
    private String owncaryn;

    @Schema(description = "배송유형_반품")
    private String returnyn;
    
    @Schema(description = "마감유형")
    private String carorderclosecd;
    
    @Schema(description = "2회전 가능 여부")
    private String reuseyn;
    
    @Schema(description = "출차그룹")
    private String outgroup;
    
    @Schema(description = "운송사")
    private String courier;

    @Schema(description = "2차운송사")
    private String caragentkey;
    
    @Schema(description = "차량소독증유효기간TO")
    private String fromdate;

    @Schema(description = "보건증유효기간TO")
    private String todate;

    @Schema(description = "차량등록증유효기간TO")
    private String todate2;
    
    @Schema(description = "연식")
    private String vehicleyear;
    
    @Schema(description = "연료구분")
    private String vehicletypecd;
    
    @Schema(description = "연비")
    private BigDecimal fuelefficiency;
    
    @Schema(description = "기본적재량")
    private BigDecimal optloadweight;
    
    @Schema(description = "최대적재량")
    private BigDecimal maxweight;
    
    /** 2회전 기본착지수 */
    @Schema(description = "기본착지수")
    private String other03;
    
    @Schema(description = "최대착지수")
    private String maxlanding;
    
    /** 2회전 기본적재중량  */
    @Schema(description = "2회전 기본적재중량")
    private BigDecimal baseweight2nd;
    
    /** 2회전 최대적재중량 */
    @Schema(description = "2회전 최대적재중량")
    private BigDecimal maxweight2nd;
    
    /** 2회전 기본착지수 */
    @Schema(description = "2회전 기본착지수")
    private String baselanding2nd;
    
    /** 2회전 최대착지수 */
    @Schema(description = "2회전 최대착지수 ")
    private String maxlanding2nd;
    
    @Schema(description = "보조원여부")
    private String subdriveryn;
    
    @Schema(description = "차고지 주소_기본")
    private String garageaddress1;

    @Schema(description = "차고지 주소_상세")
    private String garageaddress2;
    
    @Schema(description = "운전자명")
    private String drivername;
    
    @Schema(description = "운전자1 전화번호")
    private String phone1;
    
    @Schema(description = "근무시작시간")
    private String workfromhour;
    
    @Schema(description = "근무종료시간")
    private String worktohour;
    
    @Schema(description = "위도")
    private String latitude;
    
    @Schema(description = "경도")
    private String longitude;
    
    
    @Schema(description = "온도기록장치ID")
    private String thermometerno;
    
	/** 프로세스 진행 여부 */
	@Schema(description = "프로세스 진행 여부", example = "")
	private String processYn;
	
	/** 프로세스 메시지 */
	@Schema(description = "프로세스 메시지", example = "")
	private String processMsg;
    
}
