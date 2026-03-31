package cjfw.wms.ms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.ms.dto.MsSkuDcLocChangeReqDto;
import cjfw.wms.ms.dto.MsSkuDcLocChangeResDto;
import cjfw.wms.ms.dto.MsSkuDcLocChangeResultResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.15
 * @description : 기준정보 > 센터기준정보 > 기본LOC 일괄등록 목록 조회 및 저장 Service
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.15        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsSkuDcLocChangeService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msSkuDcLocChangeService.";
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 * 
	 * @description : 기본LOC 일괄등록 조회 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.15        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<MsSkuDcLocChangeResDto> getMasterList(MsSkuDcLocChangeReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}
	
	/**
	 * @description : 로케이션 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.15        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<MsSkuDcLocChangeResultResDto> saveMasterList(MsSkuDcLocChangeReqDto dto) {
		// 임시 테이블에 이전 처리했던 기록들을 삭제
		commonDao.delete(SERVICEID_PREFIX + "deleteTempTable", dto);
		
		for (MsSkuDcLocChangeReqDto item : dto.getLocList()) {
			item.setProcesstype("MS_SKUDCLOCCHANGE");
            commonDao.insert(SERVICEID_PREFIX + "insertTempTable", item); 
        }
		
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(dto, dto, "SPMS_SKUDCSET");
		
		// PKG 파라마터 세팅 및 실행 - 업무(2/4)
		String[] keyList = {"PROCESSTYPE","PROCESSCREATOR","SPID","DCCODE","STORERKEY"};
		Object[] valueList = {"MS_SKUDCLOCCHANGE", userContext.getUserId(), userContext.getSpid(), userContext.getDccode(), userContext.getStorerkey()};
		dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		dto.setAvc_COMMAND("CHANGE_SKUDCLOC");
		dto.setAvc_EXECUTEMODE("NOCOMMIT");
		dto.setProcesstype("MS_SKUDCLOCCHANGE");
		
		commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, dto);
		
		// 프로시저 OUT Parameter(3/4)
		String resultCode    = (String)dto.getResultCode();
		String resultMessage = (String)dto.getResultMessage();
		
		// 프로시저 Exception 처리(4/4)
		if (!resultCode.equals("0")) {
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"기본LOC 일괄등록"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		
		return commonDao.selectList(SERVICEID_PREFIX + "getDataResultlist", dto);
	}
	
	/**
	 * @description : 엑셀 업로드 유효성 검사(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.02        JangJaeHyun (jhjang43@cj.net)       생성
	 */
    public List<MsSkuDcLocChangeResultResDto> getValidateExcelList(List<MsSkuDcLocChangeReqDto> dtoList) {
        List<MsSkuDcLocChangeResultResDto> checkedResult = new ArrayList<MsSkuDcLocChangeResultResDto>();
        if(dtoList != null) {
            for (MsSkuDcLocChangeReqDto dto : dtoList) {
            	MsSkuDcLocChangeResultResDto result = commonDao.selectOne(SERVICEID_PREFIX + "getValidateExcelList", dto);
                checkedResult.add(result);
            }
        }
        return checkedResult;
    }

    /**
	 * 
	 * @description : 업로드 결과 조회 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.24        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<MsSkuDcLocChangeResultResDto> getExcelUploadList(MsSkuDcLocChangeReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getExcelUploadList", dto);
	}
	/**
	 * 
	 * @description : Zone 조회 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 *  2026.03.26 
	 */
	public MsSkuDcLocChangeResDto getZoneList(MsSkuDcLocChangeReqDto dto) {
		// 1. 결과는 문자열로 받는다
	    String zone = commonDao.selectOne(SERVICEID_PREFIX + "getZoneList", dto);
	    
	    return MsSkuDcLocChangeResDto.builder()
                .zone(zone)
                .build();
	}
}
