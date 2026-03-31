package cjfw.wms.tm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.tm.dto.TmPlanSummaryDiffCarResDto;
import cjfw.wms.tm.dto.TmPlanSummaryDiffCustResDto;
import cjfw.wms.tm.dto.TmPlanSummaryDiffReqDto;
import cjfw.wms.tm.dto.TmPlanSummaryDiffResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.11.20 
 * @description : 배차 결과 비교 서비스 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.20 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmPlanSummaryDiffService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "tmPlanSummaryDiffService.";
	
	private final CommonDao commonDao;
	
	/**
	 * @description : 배차 요약정보 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.21 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
    public List<TmPlanSummaryDiffResDto> getMasterList(TmPlanSummaryDiffReqDto dto) {
    	return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
    }
    
    /**
     * @description : 배차 차량 요약정보 조회 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.21 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public TmPlanSummaryDiffCarResDto getMasterCarList(TmPlanSummaryDiffReqDto dto) {
    	List<TmPlanSummaryDiffCarResDto> carDtoList = commonDao.selectList(SERVICEID_PREFIX + "getMasterCarSummaryList", dto);
    	List<TmPlanSummaryDiffCustResDto> custDtoList = commonDao.selectList(SERVICEID_PREFIX + "getMasterCustList", dto);
    	return TmPlanSummaryDiffCarResDto
    			.builder()
    			.carno(dto.getCarno())
    			.carSummaryDiff(carDtoList)
    			.custSummaryDiff(custDtoList)
    			.build();
    }
    
}
