package cjfw.wms.cm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.cm.dto.CmMainCodeListResDto;
import cjfw.wms.cm.dto.CmMainCodeResDto;
import cjfw.wms.cm.dto.CmMainMenuRoleResDto;
import cjfw.wms.cm.dto.CmMainNoticeReqDto;
import cjfw.wms.cm.dto.CmMainNoticeResDto;
import cjfw.wms.cm.dto.CmMainTranslationResDto;
import cjfw.wms.common.extend.CommonDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net)
 * @date : 2025.04.28
 * @description : 메인 공통 API Service
 * @issues :
 * -----------------------------------------------------------
 * DATE AUTHOR MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.04.28 JangGwangSeok (breaker3317@cj.net) 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmMainService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmMainService.";

	private final CommonDao commonDao;

	/**
	 * @description : 로그인 사용자 권한에 따른 메뉴 리스트 조회
	 * @issues :
	 * -----------------------------------------------------------
	 * DATE AUTHOR MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.04.28 JangGwangSeok (breaker3317@cj.net) 생성
	 */
	public List<CmMainMenuRoleResDto> getMenuRoleList(CommonDto commonDto) {
		List<CmMainMenuRoleResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getMenuRoleList", commonDto);
		return list;
	}

	/**
	 * @description : 사용자별 공통 코드 정보 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.08 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Cacheable(value = "userCode", key = "#commonDto?.gSystem")
	public List<CmMainCodeListResDto> getUserCodeList(CommonDto commonDto){
		List<CmMainCodeResDto> commCodeList = commonDao.selectList(SERVICEID_PREFIX + "getUserCodeList", commonDto);
		List<CmMainCodeListResDto> result = new ArrayList<>();
		CmMainCodeListResDto resDto = null;
		for(CmMainCodeResDto commCode: commCodeList){
			if(resDto == null || !resDto.getComGrpCd().equals(commCode.getComGrpCd())){
				resDto = new CmMainCodeListResDto();
				resDto.setComGrpCd(commCode.getComGrpCd());
				resDto.setCommCodes(new ArrayList<>());
				result.add(resDto);
			}
			resDto.getCommCodes().add(commCode);
		}

		return result;
	}
	
	/**
	 * @description : 다국어 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.26 breaker3317 생성 </pre>
	 */
	@Cacheable(value = "transCode", key = "#commonDto?.gLang")
	public List<CmMainTranslationResDto> getTranslationList(CommonDto commonDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getTranslationList", commonDto);
	}
	
	/**
	 * @description : 알림 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public List<CmMainNoticeResDto> getNoticeList(CommonDto commonDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getNoticeList", commonDto);
	}
	
	/**
	 * @description : 알림 읽음 처리
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveNoticeRead(CmMainNoticeReqDto cmMainNoticeReqDto) {
		commonDao.update(SERVICEID_PREFIX + "saveNoticeRead", cmMainNoticeReqDto);
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 공지사항 팝업 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.22 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public List<CmMainNoticeResDto> getNoticePopList(CommonDto commonDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getNoticePopList", commonDto);
	}

}
