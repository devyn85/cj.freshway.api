package cjfw.wms.sys.entity;

import cjfw.wms.common.extend.CommonTriggerDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.04.30 
 * @description : 프로그램 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.04.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
public class SysProgramEntity extends CommonTriggerDto {
	/** 프로그램코드 */
	@Schema(description = "프로그램코드", nullable = false, example = "WM10251010")
	private String progCd;

	/** 프로그램명 */
	@Schema(description = "프로그램명", example = "입고진행현황")
	private String progNm;

	/** 시스템구분 */
	@Schema(description = "시스템구분", example = "LOGISONE")
	private String systemCl;

	/** 프로그램레벨 */
	@Schema(description = "프로그램레벨", example = "4")
	private String progLvl;

	/** 프로그램내부순번 */
	@Schema(description = "프로그램내부순번", nullable = false, example = "0101040101")
	private String progNo;

	/** 프로그램URL */
	@Schema(description = "프로그램URL", example = "dp::DP_Inplan.xml")
	private String progUrl;

	/** 프로그램 파라미터 */
	@Schema(description = "프로그램 파라미터", example = "STD.COST.CONSIGNCOST")
	private String progArgs;

	/** 프로그램 도움말 URL */
	@Schema(description = "프로그램 도움말 URL", example = "")
	private String proghelpUrl;

	/** 타겟유형 */
	@Schema(description = "타겟유형", example = "")
	private String targetCl;

	/** 메뉴여부 */
	@Schema(description = "메뉴여부", example = "")
	private String menuYn;

	/** 디버그여부 */
	@Schema(description = "디버그여부", example = "")
	private String debugYn;

	/** IP_USEYN */
	@Schema(description = "IP_USEYN", example = "")
	private String ipUseYn;

	/** 사용자버튼1 */
	@Schema(description = "사용자버튼1", example = "배송순위변경")
	private String btn1Nm;

	/** 사용자버튼2 */
	@Schema(description = "사용자버튼2", example = "도착시간변경")
	private String btn2Nm;

	/** 사용자버튼3 */
	@Schema(description = "사용자버튼3", example = "배차확정")
	private String btn3Nm;

	/** 사용자버튼4 */
	@Schema(description = "사용자버튼4", example = "")
	private String btn4Nm;

	/** 사용자버튼5 */
	@Schema(description = "사용자버튼5", example = "")
	private String btn5Nm;

	/** 사용자버튼6 */
	@Schema(description = "사용자버튼6", example = "")
	private String btn6Nm;

	/** 사용자버튼7 */
	@Schema(description = "사용자버튼7", example = "")
	private String btn7Nm;

	/** 사용자버튼8 */
	@Schema(description = "사용자버튼8", example = "")
	private String btn8Nm;

	/** 사용자버튼9 */
	@Schema(description = "사용자버튼9", example = "")
	private String btn9Nm;

	/** 사용자버튼10 */
	@Schema(description = "사용자버튼10", example = "")
	private String btn10Nm;

	/** 비고 */
	@Schema(description = "비고", example = "")
	private String rmk;

	/** 사용여부 */
	@Schema(description = "사용여부", example = "1")
	private String useYn;

	/** 등록자ID */
	@Schema(description = "등록자ID", example = "dev01")
	private String regId;

	/** 등록일시 */
	@Schema(description = "등록일시", example = "2015-10-23 오후 6:08:43")
	private String regDtm;

	/** 수정자ID */
	@Schema(description = "수정자ID", example = "dev01")
	private String updId;

	/** 수정일시 */
	@Schema(description = "수정일시", example = "2021-10-27 오후 5:39:05")
	private String updDtm;

	/** 상위메뉴여부 */
	@Schema(description = "상위메뉴여부", example = "0")
	private String topmenuYn;

	/** 프로그램 옵션 */
	@Schema(description = "프로그램 옵션", example = "IF_COMPSAFSSCM_H1:IF_COMPSAFSSCM_H2")
	private String progOpts;

	/** 권한코드 */
	@Schema(description = "권한코드", example = "00,05,10,20,32,50,70")
	private String authority;
}