package cjfw.wms.common.extend;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.wms.common.annotation.MultiSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.cj.com) 
 * @date : 2025.04.30 
 * @description : 요청 DTO 공통 기능을 구현한 Dto Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.04.30 SangSuSung(kduimux@cj.cj.com) 생성
 */
@Data
public class CommonDto extends Page {
    /** System code constant */
    private static final String SYSTEM_CODE = "WMSAPP";	
	
	public CommonDto() {
		setGSystem(SYSTEM_CODE); 								// 시스템구분 초기화
		
		UserContext userContext = ContextUtil.getBean(UserContext.class);
		if(userContext.isValid()){
			setGUserId(userContext.getUserId());	  			// 사용자ID
			setGUserNm(userContext.getUserNm());	  			// 사용자명
			setGUserNo(userContext.getUserNo());	  			// 통합회원번호
			setGStorerkey(userContext.getStorerkey()); 			// 고객코드
			setGDccode(userContext.getDccode()); 				// 센터코드
			setGOrganize(userContext.getOrganize()); 			// 조직코드
			setGArea(userContext.getArea()); 					// 창고코드
			setGSpid(userContext.getSpid()); 					// 접속DB SPID
			setGLang(userContext.getLang());           			// 다국어코드
			setGAuthority(userContext.getAuthority()); 			// 권한코드
			setGRoles(userContext.getRoleList());      			// 권한그룹
			setGEmailAddr(userContext.getEmailAddr());  		// 메일ID
			setGEmptype(userContext.getEmptype());      		// 소속구분
			setGCustkey(userContext.getCustkey());      		// 업체코드
			setGRepUserIdYn(userContext.getRepUserIdYn());      // 대표사용자ID여부
			setRegId(userContext.getUserId());        			// 등록자ID
		}
	}
	
	// START.Global Variables
	/** 시스템구분(WMSAPP) */
	@JsonProperty(value = "gSystem", access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String gSystem;
	/** 사용자ID */
	@JsonProperty(value = "gUserId", access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String gUserId;
	/** 사용자명 */
	@JsonProperty(value = "gUserNm", access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String gUserNm;
	/** 통합회원번호 */
	@JsonProperty(value = "gUserNo", access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String gUserNo;
	/** 고객코드 */
	@JsonProperty(value = "gStorerkey", access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String gStorerkey;
	/** 센터코드 */
	@JsonProperty("gDccode")
	@Schema(description = "물류센터 코드", example = "2600")
	private String gDccode;
	/** 센터코드 */
	@JsonProperty("fixdccode")
	@Schema(description = "물류센터 코드", example = "2600")
	private String fixdccode;
	
	/** 창고 */
	@JsonProperty(value = "gOrganize", access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String gOrganize;
    /** 창고(멀티검색) */
	@MultiSearch
    @Schema(description = "창고-멀티검색")
    private List<String> gOrganizeMulti;	
	
    /** 창고코드 */
    @Schema(description = "창고코드")
    private String organize;	
    
    /** 창고코드-다중검색 */
    @MultiSearch 
    @Schema(description = "창고코드")
    private List<String> organizeMulti;
    
	/** 창고코드 */
	@JsonProperty(value = "gArea", access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String gArea;
	/** 배송그룹 */
	@JsonProperty(value = "gCourier", access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String gCourier;
	/** 멀티지역 */
	@JsonProperty(value = "gMultiArea", access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String gMultiArea;
	/** 멀티배송처 */
	@JsonProperty(value = "gMultiCourier", access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String gMultiCourier;
	/** 멀티센터코드 */
	@JsonProperty(value = "gMultiDccode", access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String gMultiDccode;
	/** 멀티조직코드 */
	@JsonProperty(value = "gMultiOrganize", access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String gMultiOrganize;	
	/** 멀티고객사코드 */
	@JsonProperty(value = "gMultiStorerkey", access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String gMultiStorerkey;	
	/** 접속DB SPID */
	@JsonProperty(value = "gSpid", access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String gSpid;
	/** 다국어코드 */
	@JsonProperty(value = "gLang", access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String gLang;
	/** 권한코드 */
	@JsonProperty(value = "gAuthority", access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
    private String gAuthority;
	/** 권한그룹 */
	@JsonProperty(value = "gRoles", access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private List<String> gRoles;
	/** 메일ID */
	@JsonProperty(value = "gEmailAddr", access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String gEmailAddr;
	/** 소속구분 */
	@JsonProperty(value = "gEmptype", access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String gEmptype;
	/** 사원번호 */
	@JsonProperty(value = "gEmpNo", access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String gEmpNo;
	/** 업체코드 */
	@JsonProperty(value = "gCustkey", access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String gCustkey;
	/** 대표사용자ID여부 */
	@JsonProperty(value = "gRepUserIdYn", access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String gRepUserIdYn;
	// END.Global Variables
	
	/** 등록자ID */
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String regId;
	
	/** row상태(I:신규,U:수정,D:삭제) */
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String rowStatus;
	
	/** 마스킹처리 여부(noMasking을 true 전송 시 마스킹이 해제됨) */
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private boolean noMasking = false;
	/** 마스킹 해제 라벨명 */
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String noMaskingLabel;
	
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/** 고객코드 */
	@Schema(hidden = true)
	private String storerkey;
	
	/** 다국어코드(삭제 예정) */
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String lang;
	
	/** 권한그룹(삭제 예정) */
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private List<String> roles;

}
