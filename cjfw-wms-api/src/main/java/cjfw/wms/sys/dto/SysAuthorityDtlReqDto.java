package cjfw.wms.sys.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.05.15 
 * @description : 권한별 프로그램 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.15 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "권한별 프로그램 요청 DTO")
public class SysAuthorityDtlReqDto {
	/** 권한그룹코드 */
	@Schema(description = "권한그룹코드", example = "LOGISONE_00")
	private String authCd;
	
	/** 프로그램코드 */
	@Schema(description = "프로그램코드", example = "WM10100510")
	private String progCd;	
	
	/** 조회권한 */
	@Schema(description = "조회권한", example = "0")
	private String searchYn;	
	
	/** 신규권한 */
	@Schema(description = "신규권한", example = "0")
	private String newYn;
	
	/** 삭제권한 */
	@Schema(description = "삭제권한", example = "0")
	private String deleteYn;
	
	/** 저장권한 */
	@Schema(description = "저장권한", example = "0")
	private String saveYn;
	
	/** 인쇄권한 */
	@Schema(description = "인쇄권한", example = "0")
	private String printYn;
	
	/** 사용자버튼1권한 */
	@Schema(description = "사용자버튼1권한", example = "0")
	private String btn1Yn;
	
	/** 사용자버튼2권한 */
	@Schema(description = "사용자버튼2권한", example = "0")
	private String btn2Yn;
	
	/** 사용자버튼3권한 */
	@Schema(description = "사용자버튼3권한", example = "0")
	private String btn3Yn;
	
	/** 사용자버튼4권한 */
	@Schema(description = "사용자버튼4권한", example = "0")
	private String btn4Yn;
	
	/** 사용자버튼5권한 */
	@Schema(description = "사용자버튼5권한", example = "0")
	private String btn5Yn;
	
	/** 사용자버튼6권한 */
	@Schema(description = "사용자버튼6권한", example = "0")
	private String btn6Yn;
	
	/** 사용자버튼7권한 */
	@Schema(description = "사용자버튼7권한", example = "0")
	private String btn7Yn;
	
	/** 사용자버튼8권한 */
	@Schema(description = "사용자버튼8권한", example = "0")
	private String btn8Yn;
	
	/** 사용자버튼9권한 */
	@Schema(description = "사용자버튼9권한", example = "0")
	private String btn9Yn;
	
	/** 사용자버튼10권한 */
	@Schema(description = "사용자버튼10권한", example = "0")
	private String btn10Yn;
	
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
	
	/** GridRow 저장 구분 */
	@Schema(description = "GridRow 저장 구분", example = "U", allowableValues = {"I", "U", "D"})
    private String rowStatus;
}
