package cjfw.wms.st.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.dataaccess.largedata.LargeExcel;
import cjfw.wms.st.dto.StStockLotMonitoringDurationResDto;
import cjfw.wms.st.dto.StStockLotMonitoringReqDto;
import cjfw.wms.st.dto.StStockLotMonitoringResDto;
import cjfw.wms.st.dto.StStockLotMonitoringStoragetypeResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net)
 * @date : 2025.10.14 
 * @description : 재고 > 재고현황 > 유통기한점검 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.14 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StStockLotMonitoringService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "stStockLotMonitoringService.";
	
	private final CommonDao commonDao;

	
	/**
	 * @description : 유통기한 점검 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<StStockLotMonitoringResDto> getMasterList(StStockLotMonitoringReqDto dto) {
		List<StStockLotMonitoringResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
		return result;
	}	
	
	/**
	 * @description : 유통기한 점검 요약장표 소비기한 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.06 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<StStockLotMonitoringDurationResDto> getDurationList(StStockLotMonitoringReqDto dto) {
		List<StStockLotMonitoringDurationResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getDurationList", dto);
		return result;
	}
	
	/**
	 * @description : 유통기한 점검 요약장표 저장조건 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.06 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<StStockLotMonitoringStoragetypeResDto> getStoragetypeList(StStockLotMonitoringReqDto dto) {
		List<StStockLotMonitoringStoragetypeResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getStoragetypeList", dto);
		return result;
	}	
	
	/**
	 * @description : 유통기한 점검 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public void getMasterListExcel(StStockLotMonitoringReqDto dto, LargeExcel largeExcel) {
		commonDao.selectLargeExcelDataset(SERVICEID_PREFIX + "getMasterList", dto, largeExcel);
	}	
}
