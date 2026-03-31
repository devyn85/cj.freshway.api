package cjfw.wms.tm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.tm.dto.TmTrxCalculationReportReqDto;
import cjfw.wms.tm.dto.TmTrxCalculationReportResDto;
import cjfw.wms.tm.dto.TmTrxCalculationResultReqDto;
import cjfw.wms.tm.dto.TmTrxCalculationResultResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.10.20
 * @description : 운송비정산현황 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.20    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmTrxCalculationResultService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "tmTrxCalculationResultService.";
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @description : 운송비정산 내역 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.10    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public List<TmTrxCalculationResultResDto> getMasterList(TmTrxCalculationResultReqDto reqDto) {
	    return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
	}
	
    /**
     * @description : 월 기준 근무일수 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.10    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<TmTrxCalculationResultResDto> getWorkDay(TmTrxCalculationResultReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getWorkDay", reqDto);
    }
	
}
