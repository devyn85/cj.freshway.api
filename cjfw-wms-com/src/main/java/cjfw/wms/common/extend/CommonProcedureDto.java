package cjfw.wms.common.extend;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.cj.com) 
 * @date : 2025.04.30 
 * @description : 오라클 패키지/프로시져를 호출 시 사용하는 DTO
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.04.30 SangSuSung(kduimux@cj.com) 생성
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class CommonProcedureDto extends CommonDto {
    // START.오라클 pakage 변수
	// 요청
	/** 패키지명 */
	@Schema(description = "패키지명", example = "SP_DUMMY")
	private String packagename;
	/** 패키지가 수행할 커맨드 명령어 */
	@Schema(description = "패키지가 수행할 커맨드", example = "EXECUTE")
	private String avc_COMMAND;  
	/** 시스템 */
	@Schema(description = "시스템", example = "WMS")
	private String avc_SYSTEM;    
	/** 수행모드 */
	@Schema(description = "수행모드", example = "")
	private String avc_EXECUTEMODE;  
	/** 센터코드 */
	@Schema(description = "센터코드", example = "2600")
	private String avc_DCCODE; 
	/** 고객사코드 */
	@Schema(description = "고객사코드", example = "CJFW")
	private String avc_STORERKEY; 		
	/** 조직코드 */
	@Schema(description = "조직코드", example = "2600")
	private String avc_ORGANIZE; 	
	/** 창고동정보(default:'STD') */
	@Schema(description = "창고동정보(default:'STD')", example = "STD")
	private String avc_AREA;	
	/** 요청코드 */
	@Schema(description = "요청코드", example = "")
	private String avc_REQUESTCODE; 
	/** 요청문자열->(<SLIPDT>20250520</SLIPDT>) */
	@Schema(description = "요청문자열", example = "")
	private String avc_REQUESTMSG; 	
	/** 작업자 */
	@Schema(description = "작업자", example = "superexp")
	private String avc_WORKER; 
	/** 보안KEY */
	@Schema(description = "보안KEY", example = "")
	private String avc_SECURITYKEY; 	
	/** SPID */
	@Schema(description = "SPID", example = "")
	private String ai_SPID;
	/** 병렬수행번호 */
	@Schema(description = "HASHID", example = "")
	private String ai_HASHID;
    @Schema(description = "프로세스 타입", example = "WD_XXX")
    private String processtype;	
    @Schema(description = "임시테이블 타입", example = "WD")
    private String temptabletype;	
    @Schema(description = "사유코드", example = "")
    private String reasoncode;	    
    @Schema(description = "사유메세지", example = "")
    private String reasonmsg;	  
    
	// 응답
	/** 에러코드(0:성공) */
	@Schema(description = "결과에러코드(0:성공)", example = "0")
	private String resultCode; 
	/** 결과메세지-UI용 */
	@Schema(description = "결과메세지-UI용", example = "정상적으로 처리되었습니다./XXX은(는) 처리 불가한 명령입니다.")
	private String resultMessage; 
	/** 결과메세지-로그수집용 */
	@Schema(description = "결과메세지-로그수집용", example = "ORA-1400 발생..")
	private String returnMessage; 	
	
	// END.오라클 pakage 변수
}
