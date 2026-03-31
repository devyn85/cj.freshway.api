package cjfw.wms.cm.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.auth.jwt.JwtAuthenticationFilter;
import cjfw.core.model.ApiResult;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.dto.CmUbaReqDto;
import cjfw.wms.cm.dto.CmUserManagerAuthorityResDto;
import cjfw.wms.cm.dto.CmUserManagerConnectResDto;
import cjfw.wms.cm.dto.CmUserManagerReqDto;
import cjfw.wms.cm.dto.CmUserManagerResDto;
import cjfw.wms.cm.service.CmUbaService;
import cjfw.wms.cm.service.CmUserManagerService;
import cjfw.wms.sys.dto.SysAuthorityResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.06.20 
 * @description : 기준정보 > 사용자및센터정보 > 사용자정보관리
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.20 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 사용자및센터정보 > 사용자정보관리", description = "사용자 정보 관리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/user")
public class CmUserManagerController {

	private final CmUserManagerService cmUserManagerService;
	
	/**
	 * @description : 권한그룹 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.12 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "권한그룹 목록 조회", description = "권한그룹 목록 조회")
	@GetMapping(value = "/v1.0/getAuthorityGroupList")
	public ApiResult<List<SysAuthorityResDto>> getAuthorityGroupList(@Valid CmUserManagerReqDto cmUserManagerReqDto) {
		return ApiResult.createResult(cmUserManagerService.getAuthorityGroupList(cmUserManagerReqDto));
	}
	
	/**
	 * @description : 사용자 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.20 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "사용자 목록 조회", description = "사용자 목록 조회")
	@GetMapping(value = "/v1.0/getUserList")
	public ApiResult<List<CmUserManagerResDto>> getUserList(@Valid CmUserManagerReqDto cmUserManagerReqDto) {
		return ApiResult.createResult(cmUserManagerService.getUserList(cmUserManagerReqDto));
	}
	
	/**
	 * @description : 사용자 상세 정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.20 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "사용자 상세 정보 조회", description = "사용자 상세 정보 조회")
	@GetMapping(value = "/v1.0/getUserDetail")
	public ApiResult<CmUserManagerResDto> getUserDetail(@Valid CmUserManagerReqDto cmUserManagerReqDto) {
		
		// 그룹정보유출보안관제(UBA) 로그 기록
		CmUbaReqDto cmUbaReqDto = new CmUbaReqDto();
		cmUbaReqDto.setUserId(cmUbaReqDto.getGUserId()); // 행위자의 시스템 ID
		if (cmUbaReqDto.getGEmailAddr() != null && !"".equals(cmUbaReqDto.getGEmailAddr())) {
			cmUbaReqDto.setEmailAlias(cmUbaReqDto.getGEmailAddr()); // 행위자의 CJWorld ID
		} else {
			cmUbaReqDto.setEmailAlias("N"); // 이메일 정보가 없는 사용자일 경우 "N" 값 설정
		}
		cmUbaReqDto.setUserName(cmUbaReqDto.getGUserNm()); // 행위자 이름
		cmUbaReqDto.setEmployeeYn("01".equals(cmUbaReqDto.getGEmptype()) ? "Y" : "N"); // 임직원 여부 구분 (임직원:Y, 그외:N)
		cmUbaReqDto.setClientIp(JwtAuthenticationFilter.ipHolder.get()); // 접속 Client IP
		cmUbaReqDto.setMenuName("getUserDetail"); // 접근 메뉴명
		cmUbaReqDto.setActionCode("1"); // 조회:1, 수정:2, 삭제:3, 출력:4, 다운로드:5
		cmUbaReqDto.setSearchItem("2"); // 고유식별정보:1 / 그외:2(고유식별정보 : 주민등록번호/여권번호/운전면허번호/외국인등록번호 등)
		cmUbaReqDto.setQCount("1"); // 행위 1건에 포함되는 고객정보 개수
		cmUbaReqDto.setSearchId("userId,userNm,handphoneNo,mailId"); // 행위에 대하여 처리되는 대상(고객) 식별자(고객 ID, 이름, 회원번호, 송장번호 등)
		
		CmUbaService cmUbaService = (CmUbaService) ContextUtil.getBean(CmUbaService.class);
		cmUbaService.saveUbaLog(cmUbaReqDto);
		
		// 20260326#[개인정보 1건도 패킷 취약점에 보고되어 처리]사용자 이름, 핸드폰 번호, 이메일을 Base64로 인코딩하여 반환 by sss	
		CmUserManagerResDto userDto = cmUserManagerService.getUserDetail(cmUserManagerReqDto);
		userDto.setUserNm(Base64.getEncoder().encodeToString(StringUtil.nvl(userDto.getUserNm()).getBytes())); // 사용자 이름을 Base64로 인코딩하여 반환
		userDto.setHandphoneNo(Base64.getEncoder().encodeToString(StringUtil.nvl(userDto.getHandphoneNo()).getBytes())); // 핸드폰 이름을 Base64로 인코딩하여 반환
		userDto.setMailId(Base64.getEncoder().encodeToString(StringUtil.nvl(userDto.getMailId()).getBytes())); // 핸드폰 이름을 Base64로 인코딩하여 반환
		
		// 20260327#[개인정보 1건도 패킷 취약점에 보고되어 처리]UserNmDisp 도 추가 by sss	
		userDto.setUserNmDisp(Base64.getEncoder().encodeToString(StringUtil.nvl(userDto.getUserNmDisp()).getBytes())); // 사용자 이름을 Base64로 인코딩하여 반환
		
		return ApiResult.createResult(userDto);
	}
	
	/**
	 * @description : 사용자 센터 권한 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.20 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "사용자 센터 권한 목록 조회", description = "사용자 센터 권한 목록 조회")
	@GetMapping(value = "/v1.0/getUserConnectList")
	public ApiResult<List<CmUserManagerConnectResDto>> getUserConnectList(@Valid CmUserManagerReqDto cmUserManagerReqDto) {
		return ApiResult.createResult(cmUserManagerService.getUserConnectList(cmUserManagerReqDto));
	}
	
	/**
	 * @description : 사용자 그룹권한 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.20 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "사용자 그룹권한 목록 조회", description = "사용자 그룹권한 목록 조회")
	@GetMapping(value = "/v1.0/getUserAuthorityList")
	public ApiResult<List<CmUserManagerAuthorityResDto>> getUserAuthorityList(@Valid CmUserManagerReqDto cmUserManagerReqDto) {
		return ApiResult.createResult(cmUserManagerService.getUserAuthorityList(cmUserManagerReqDto));
	}
	
	/**
	 * @description : 사용자 관리 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.10 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "사용자 관리 저장", description = "사용자 관리 저장")
	@PostMapping(value = "/v1.0/saveUser")
	public ApiResult<String> saveUser(@RequestBody @Valid CmUserManagerReqDto cmUserManagerReqDto) {
		return ApiResult.createResult(cmUserManagerService.saveUserRoot(cmUserManagerReqDto));
	}
	
	/**
	 * @description : 사용자별 센터 권한 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.05 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "사용자별 센터 권한 저장", description = "사용자별 센터 권한 저장")
	@PostMapping(value = "/v1.0/saveUserConnect")
	public ApiResult<String> saveUserConnect(@RequestBody @Valid CmUserManagerReqDto cmUserManagerReqDto) {
		return ApiResult.createResult(cmUserManagerService.saveUserConnect(cmUserManagerReqDto));
	}
	
	/**
	 * @description : 사용자별 권한 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.08 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "사용자별 권한 저장", description = "사용자별 권한 저장")
	@PostMapping(value = "/v1.0/saveUserAuthority")
	public ApiResult<String> saveUserAuthority(@RequestBody @Valid CmUserManagerReqDto cmUserManagerReqDto) {
		return ApiResult.createResult(cmUserManagerService.saveUserAuthority(cmUserManagerReqDto));
	}
	
	/**
	 * @description : 사용자 인증 SMS 전송
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.28 breaker3317 생성 </pre>
	 */
	@Operation(summary = "사용자 인증 SMS 전송", description = "사용자 인증 SMS 전송")
	@PostMapping(value = "/v1.0/sendSmsVerification")
	public ApiResult<String> insertSmsVerification(@RequestBody @Valid CmUserManagerReqDto cmUserManagerReqDto) {
		return ApiResult.createResult(cmUserManagerService.insertSmsVerification(cmUserManagerReqDto));
	}
	
	/**
	 * @description : 임시 사용자 승인
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.29 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "임시 사용자 승인", description = "임시 사용자 승인")
	@PostMapping(value = "/v1.0/saveUserApprv")
	public ApiResult<String> saveUserApprv(@RequestBody @Valid CmUserManagerReqDto cmUserManagerReqDto) {
		return ApiResult.createResult(cmUserManagerService.saveUserApprv(cmUserManagerReqDto));
	}
	
	/**
	 * @description : 임시비밀번호 생성 및 발송
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.23 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "임시비밀번호 생성 및 발송", description = "임시비밀번호 생성 및 발송")
	@PostMapping(value = "/v1.0/saveUserTmpPwd")
	public ApiResult<String> saveUserTmpPwd(@RequestBody @Valid CmUserManagerReqDto cmUserManagerReqDto) {
		return ApiResult.createResult(cmUserManagerService.saveUserTmpPwd(cmUserManagerReqDto));
	}
	
	/**
	 * @description : 사용자 잠김 계정 해제
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.12 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "사용자 잠김 계정 해제", description = "사용자 잠김 계정 해제")
	@PostMapping(value = "/v1.0/saveUnlockUser")
	public ApiResult<String> saveUnlockUser(@RequestBody @Valid CmUserManagerReqDto cmUserManagerReqDto) {
		return ApiResult.createResult(cmUserManagerService.saveUnlockUser(cmUserManagerReqDto));
	}
}