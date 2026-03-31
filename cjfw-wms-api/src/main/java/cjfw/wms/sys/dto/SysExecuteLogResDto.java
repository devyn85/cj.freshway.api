package cjfw.wms.sys.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.07.17 
 * @description : 프로시저실행로그 조회 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.17 JiSooKim (jskim14@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "공통코드 조회 응답")
public class SysExecuteLogResDto {
	/** 테이블시리얼번호 */
	@Schema(description = "테이블시리얼번호", nullable = false, example = "")
	private String serialkey;

	/** 실행일자 */
	@Schema(description = "실행일자", example = "")
	private String executedt;
	
	/** 오브젝트명 */
	@Schema(description = "오브젝트명", example = "")
	private String objectname;

	/** 시스템명 */
	@Schema(description = "시스템명", example = "")
	private String system;

	/** 처리명령 */
	@Schema(description = "처리명령", example = "")
	private String command;
	
	/** 작업자 */
	@Schema(description = "작업자", example = "")
	private String worker;
	
	/** DB SPID */
	@Schema(description = "DB SPID", example = "")
	private String spid;
	
	/** 실행시간 */
	@Schema(description = "실행시간", example = "")
	private String runtime;
	
	/** 실행소요시간 */
	@Schema(description = "실행소요시간", example = "")
	private String executetime;
	
	/** 에러코드 */
	@Schema(description = "에러코드", example = "")
	private String err;
	
	/** 에러사항(인터페이스 에러나 전표에러 메세지) */
	@Schema(description = "에러사항(인터페이스 에러나 전표에러 메세지)", example = "")
	private String errmsg;

	/** 사용자전달메세지 */
	@Schema(description = "사용자전달메세지", example = "")
	private String userdispmsg;

	/** 리턴메세지 */
	@Schema(description = "리턴메세지", example = "")
	private String returnmsg;
	
	/** 물류센터(창고코드) */
	@Schema(description = "물류센터(창고코드)", example = "")
	private String dccode;

	/** 회사(고객사코드 MS_COMPANY코드) */
	@Schema(description = "회사(고객사코드 MS_COMPANY코드)", example = "")
	private String storerkey;
	
	/** 창고(조직코드) */
	@Schema(description = "창고(조직코드)", example = "")
	private String organize;

	/** 작업구역(창고코드 SAP 창고 혹은 별도의 창고 코드) */
	@Schema(description = "작업구역(창고코드 SAP 창고 혹은 별도의 창고 코드)", example = "")
	private String area;
	
	/** 요청코드 */
	@Schema(description = "요청코드", example = "")
	private String requestcode;

	/** 요청메세지 */
	@Schema(description = "요청메세지", example = "")
	private String requestmsg;

	    
}