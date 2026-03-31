package cjfw.wms.kp.service;



import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.kp.dto.KpEXstorageMonitoringDetailListReqDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringDetailListResDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringDetailSubListReqDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringDetailSubListResDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringDetailTcsListReqDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringDetailTcsListResDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringDetailTcsSubListReqDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringDetailTcsSubListResDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringLocalDetailListReqDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringLocalDetailListResDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringLocalDetailSubListReqDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringLocalDetailSubListResDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringLocalMasterListReqDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringLocalMasterListResDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringMasterListReqDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringMasterListResDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringTCSMasterListReqDto;
import cjfw.wms.kp.dto.KpEXstorageMonitoringTCSMasterListResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.11 
 * @description : 외부창고재고모니터링 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.11 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KpEXstorageMonitoringService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "kpEXstorageMonitoringService.";
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 * @description : 외부창고재고모니터링 헤더 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.11 ParkJinWoo 생성
	 */
	public List<KpEXstorageMonitoringMasterListResDto> getMasterList(KpEXstorageMonitoringMasterListReqDto kpEXstorageMonitoringMasterListReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderList", kpEXstorageMonitoringMasterListReqDto);
	}
	
	/**
	 * @description : 외부창고재고모니터링Local 헤더 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.11 ParkJinWoo 생성
	 */
	public List<KpEXstorageMonitoringLocalMasterListResDto> getDataLocalHeaderList(KpEXstorageMonitoringLocalMasterListReqDto kpEXstorageMonitoringLocalMasterListReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDataLocalHeaderList", kpEXstorageMonitoringLocalMasterListReqDto);
	}
	
	/**
	 * @description : 외부창고재고모니터링Tcs 헤더 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.11 ParkJinWoo 생성
	 */
	public List<KpEXstorageMonitoringTCSMasterListResDto> getDataTcsHeaderList(KpEXstorageMonitoringTCSMasterListReqDto kpEXstorageMonitoringTCSMasterListReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDataTcsHeaderList", kpEXstorageMonitoringTCSMasterListReqDto);
	}
	
	/**
	 * @description : 외부창고재고모니터링 상세 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.11 ParkJinWoo 생성
	 */
	public List<KpEXstorageMonitoringDetailListResDto> getDataDetailList(KpEXstorageMonitoringDetailListReqDto kpEXstorageMonitoringDetailListReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDataDetailList", kpEXstorageMonitoringDetailListReqDto);
	}
	
	/**
	 * @description : 외부창고재고모니터링Local 상세 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.11 ParkJinWoo 생성
	 */
	public List<KpEXstorageMonitoringLocalDetailListResDto> getDataLocalDetailList(KpEXstorageMonitoringLocalDetailListReqDto kpEXstorageMonitoringLocalDetailListReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDataLocalDetailList", kpEXstorageMonitoringLocalDetailListReqDto);
	}
	
	
	/**
	 * @description : 외부창고재고모니터링Sub 상세 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.11 ParkJinWoo 생성
	 */
	public List<KpEXstorageMonitoringDetailSubListResDto> getDataDetailSubList(KpEXstorageMonitoringDetailSubListReqDto kpEXstorageMonitoringDetailSubListReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDataDetailSubList", kpEXstorageMonitoringDetailSubListReqDto);
	}
	
	/**
	 * @description : 외부창고재고모니터링Sub 상세 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.11 ParkJinWoo 생성
	 */
	public List<KpEXstorageMonitoringDetailTcsListResDto> getDataDetailTcsList(KpEXstorageMonitoringDetailTcsListReqDto kpEXstorageMonitoringDetailTcsListReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDataDetailTcsList", kpEXstorageMonitoringDetailTcsListReqDto);
	}
	
	/**
	 * @description : 외부창고재고모니터링Sub 상세 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.11 ParkJinWoo 생성
	 */
	public List<KpEXstorageMonitoringLocalDetailSubListResDto> getDataLocalDetailSubList(KpEXstorageMonitoringLocalDetailSubListReqDto kpEXstorageMonitoringLocalDetailSubListReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDataLocalDetailSubList", kpEXstorageMonitoringLocalDetailSubListReqDto);
	}
	
	/**
	 * @description : 외부창고재고모니터링Sub 상세 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.11 ParkJinWoo 생성
	 */
	public List<KpEXstorageMonitoringDetailTcsSubListResDto> getDataDetailTcsSubList(KpEXstorageMonitoringDetailTcsSubListReqDto kpEXstorageMonitoringDetailTcsSubListReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDataDetailTcsSubList", kpEXstorageMonitoringDetailTcsSubListReqDto);
	}
}
