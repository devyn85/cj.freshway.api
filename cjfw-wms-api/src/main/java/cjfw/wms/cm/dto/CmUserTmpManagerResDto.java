package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.09.29 
 * @description : 임시 사용자 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.29 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "임시 사용자 응답 DTO")
public class CmUserTmpManagerResDto {
	/** 사용자ID */
	@Schema(description = "사용자ID", example = "")
	private String userId;
	
	/** 패스워드 */
	@Schema(description = "패스워드", example = "")
	private String pwdNo;
	
	/** 사원번호 */
	@Schema(description = "사원번호", example = "")
	private String empNo;
	
	/** 소속구분 */
	@Schema(description = "소속구분", example = "")
	private String empType;
	
	/** 권한코드 */
	@Schema(description = "권한코드", example = "")
	private String authority;
	
	/** 사용자명 */
	@Schema(description = "사용자명", example = "")
	private String userNm;
	
	/** 기본센터코드 */
	@Schema(description = "기본센터코드", example = "")
	private String defDccode;
	
	/** 기본고객코드 */
	@Schema(description = "기본고객코드", example = "")
	private String defStorerkey;
	
	/** 기본조직코드 */
	@Schema(description = "기본조직코드", example = "")
	private String defOrganize;
	
	/** 기본창고코드 */
	@Schema(description = "기본창고코드", example = "")
	private String defArea;
	
	/** 진행상태 */
	@Schema(description = "진행상태", example = "")
	private String status;
	
	/** 센터APP사용여부 */
	@Schema(description = "센터APP사용여부", example = "")
	private String dcAppUserYn;
	
	/** 배송APP사용여부 */
	@Schema(description = "배송APP사용여부", example = "")
	private String dlvAppUserYn;
	
	/** 업체코드 */
	@Schema(description = "업체코드", example = "")
	private String custkey;
	
	/** 메일ID */
	@Schema(description = "메일ID", example = "")
	private String mailId;
	
	/** 핸드폰번호 */
	@Schema(description = "핸드폰번호", example = "")
	private String handphoneNo;
	
	/** 임시테이블여부 */
	@Schema(description = "임시테이블여부", example = "")
	private String tempYn;
}