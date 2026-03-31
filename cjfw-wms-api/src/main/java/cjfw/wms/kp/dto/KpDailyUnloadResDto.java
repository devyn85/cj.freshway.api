package cjfw.wms.kp.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2026.01.19
 * @description : 데일리 생산성 하역 지표 관리 response dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.01.19 JiHoPark  생성 </pre>
 */
@Data
@Schema(description = "데일리 생산성 하역 지표 관리 response dto")
public class KpDailyUnloadResDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "0")
	private String chk = "0";

	/** serialkey */
	@Schema(description = "serialkey")
	private BigDecimal serialkey;

	/** 원시리얼번호 */
	@Schema(description = "원시리얼번호")
	private BigDecimal orgSerialkey;

	/** 회사코드 */
	@Schema(description = "회사코드")
	private String storerkey;

	/** 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;

	/** 물류센터명 */
	@Schema(description = "물류센터명")
	private String dccodenm;

	/** 일자 */
	@Schema(description = "일자")
	private String deliverydt;

	/** 대구분 */
	@Schema(description = "대구분")
	private String gubun1;

	/** 소구분 */
	@Schema(description = "소구분")
	private String gubun2;

	/** 인원(T/O) */
	@Schema(description = "인원(T/O)")
	private String gubun3;

	/** FROM */
	@Schema(description = "FROM")
	private String fromHour;

	/** TO */
	@Schema(description = "TO")
	private String toHour;

	/** 작업시간 */
	@Schema(description = "작업시간")
	private BigDecimal worktime;

	/** 투입인원 */
	@Schema(description = "투입인원")
	private String workperson;

	/** 인시 */
	@Schema(description = "인시")
	private String workmm;

	/** 상태 */
	@Schema(description = "상태")
	private String status;

	/** 삭제여부 */
	@Schema(description = "삭제여부")
	private String delYn;

	/** 물류센터 */
	@Schema(description = "등록자")
	private String addwho;

	/** 물류센터 */
	@Schema(description = "수정자")
	private String editwho;

	/** 물류센터 */
	@Schema(description = "등록일자")
	private String adddate;

	/** 물류센터 */
	@Schema(description = "수정일자")
	private String editdate;

	/** 물류센터 */
	@Schema(description = "예외여부")
	private String excptYn;

	/** 물류센터 */
	@Schema(description = "계약시작일자")
	private String contractFromdate;

	/** 물류센터 */
	@Schema(description = "계약종료일자")
	private String contractTodate;

	/** 관리처코드 */
	@Schema(description = "관리처코드")
	private String custkey;

	/** 비고 */
	@Schema(description = "비고")
	private String bigo;

	/** 예외시작일자 */
	@Schema(description = "예외시작일자")
	private String excptFromdate;

	/** 예외종료일자 */
	@Schema(description = "예외종료일자")
	private String excptTodate;

	/** 1일 투입인원 */
	@Schema(description = "1일 투입인원")
	private BigDecimal d01Workperson;

	/** 1일 인시 */
	@Schema(description = "1일 인시")
	private BigDecimal d01Workmm;

	/** 2일 투입인원 */
	@Schema(description = "2일 투입인원")
	private BigDecimal d02Workperson;

	/** 2일 인시 */
	@Schema(description = "2일 인시")
	private BigDecimal d02Workmm;

	/** 3일 투입인원 */
	@Schema(description = "3일 투입인원")
	private BigDecimal d03Workperson;

	/** 3일 인시 */
	@Schema(description = "3일 인시")
	private BigDecimal d03Workmm;

	/** 4일 투입인원 */
	@Schema(description = "4일 투입인원")
	private BigDecimal d04Workperson;

	/** 4일 인시 */
	@Schema(description = "4일 인시")
	private BigDecimal d04Workmm;

	/** 5일 투입인원 */
	@Schema(description = "5일 투입인원")
	private BigDecimal d05Workperson;

	/** 5일 인시 */
	@Schema(description = "5일 인시")
	private BigDecimal d05Workmm;

	/** 6일 투입인원 */
	@Schema(description = "6일 투입인원")
	private BigDecimal d06Workperson;

	/** 6일 인시 */
	@Schema(description = "6일 인시")
	private BigDecimal d06Workmm;

	/** 7일 투입인원 */
	@Schema(description = "7일 투입인원")
	private BigDecimal d07Workperson;

	/** 7일 인시 */
	@Schema(description = "7일 인시")
	private BigDecimal d07Workmm;

	/** 8일 투입인원 */
	@Schema(description = "8일 투입인원")
	private BigDecimal d08Workperson;

	/** 8일 인시 */
	@Schema(description = "8일 인시")
	private BigDecimal d08Workmm;

	/** 9일 투입인원 */
	@Schema(description = "9일 투입인원")
	private BigDecimal d09Workperson;

	/** 9일 인시 */
	@Schema(description = "9일 인시")
	private BigDecimal d09Workmm;

	/** 10일 투입인원 */
	@Schema(description = "10일 투입인원")
	private BigDecimal d10Workperson;

	/** 10일 인시 */
	@Schema(description = "10일 인시")
	private BigDecimal d10Workmm;

	/** 11일 투입인원 */
	@Schema(description = "11일 투입인원")
	private BigDecimal d11Workperson;

	/** 11일 인시 */
	@Schema(description = "11일 인시")
	private BigDecimal d11Workmm;

	/** 12일 투입인원 */
	@Schema(description = "12일 투입인원")
	private BigDecimal d12Workperson;

	/** 12일 인시 */
	@Schema(description = "12일 인시")
	private BigDecimal d12Workmm;

	/** 13일 투입인원 */
	@Schema(description = "13일 투입인원")
	private BigDecimal d13Workperson;

	/** 13일 인시 */
	@Schema(description = "13일 인시")
	private BigDecimal d13Workmm;

	/** 14일 투입인원 */
	@Schema(description = "14일 투입인원")
	private BigDecimal d14Workperson;

	/** 14일 인시 */
	@Schema(description = "14일 인시")
	private BigDecimal d14Workmm;

	/** 15일 투입인원 */
	@Schema(description = "15일 투입인원")
	private BigDecimal d15Workperson;

	/** 15일 인시 */
	@Schema(description = "15일 인시")
	private BigDecimal d15Workmm;

	/** 16일 투입인원 */
	@Schema(description = "16일 투입인원")
	private BigDecimal d16Workperson;

	/** 16일 인시 */
	@Schema(description = "16일 인시")
	private BigDecimal d16Workmm;

	/** 17일 투입인원 */
	@Schema(description = "17일 투입인원")
	private BigDecimal d17Workperson;

	/** 17일 인시 */
	@Schema(description = "17일 인시")
	private BigDecimal d17Workmm;

	/** 18일 투입인원 */
	@Schema(description = "18일 투입인원")
	private BigDecimal d18Workperson;

	/** 18일 인시 */
	@Schema(description = "18일 인시")
	private BigDecimal d18Workmm;

	/** 19일 투입인원 */
	@Schema(description = "19일 투입인원")
	private BigDecimal d19Workperson;

	/** 19일 인시 */
	@Schema(description = "19일 인시")
	private BigDecimal d19Workmm;

	/** 20일 투입인원 */
	@Schema(description = "20일 투입인원")
	private BigDecimal d20Workperson;

	/** 20일 인시 */
	@Schema(description = "20일 인시")
	private BigDecimal d20Workmm;

	/** 21일 투입인원 */
	@Schema(description = "21일 투입인원")
	private BigDecimal d21Workperson;

	/** 21일 인시 */
	@Schema(description = "21일 인시")
	private BigDecimal d21Workmm;

	/** 22일 투입인원 */
	@Schema(description = "22일 투입인원")
	private BigDecimal d22Workperson;

	/** 22일 인시 */
	@Schema(description = "22일 인시")
	private BigDecimal d22Workmm;

	/** 23일 투입인원 */
	@Schema(description = "23일 투입인원")
	private BigDecimal d23Workperson;

	/** 23일 인시 */
	@Schema(description = "23일 인시")
	private BigDecimal d23Workmm;

	/** 24일 투입인원 */
	@Schema(description = "24일 투입인원")
	private BigDecimal d24Workperson;

	/** 24일 인시 */
	@Schema(description = "24일 인시")
	private BigDecimal d24Workmm;

	/** 25일 투입인원 */
	@Schema(description = "25일 투입인원")
	private BigDecimal d25Workperson;

	/** 25일 인시 */
	@Schema(description = "25일 인시")
	private BigDecimal d25Workmm;

	/** 26일 투입인원 */
	@Schema(description = "26일 투입인원")
	private BigDecimal d26Workperson;

	/** 26일 인시 */
	@Schema(description = "26일 인시")
	private BigDecimal d26Workmm;

	/** 27일 투입인원 */
	@Schema(description = "27일 투입인원")
	private BigDecimal d27Workperson;

	/** 27일 인시 */
	@Schema(description = "27일 인시")
	private BigDecimal d27Workmm;

	/** 28일 투입인원 */
	@Schema(description = "28일 투입인원")
	private BigDecimal d28Workperson;

	/** 28일 인시 */
	@Schema(description = "28일 인시")
	private BigDecimal d28Workmm;

	/** 29일 투입인원 */
	@Schema(description = "29일 투입인원")
	private BigDecimal d29Workperson;

	/** 29일 인시 */
	@Schema(description = "29일 인시")
	private BigDecimal d29Workmm;

	/** 30일 투입인원 */
	@Schema(description = "30일 투입인원")
	private BigDecimal d30Workperson;

	/** 30일 인시 */
	@Schema(description = "30일 인시")
	private BigDecimal d30Workmm;

	/** 31일 투입인원 */
	@Schema(description = "31일 투입인원")
	private BigDecimal d31Workperson;

	/** 31일 인시 */
	@Schema(description = "31일 인시")
	private BigDecimal d31Workmm;

	/** 일자년월 */
    @Schema(description = "일자년월")
    private String yyyymm;
}
