package cjfw.wms.tm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.tm.dto.TmVehicleCustomerListReqDto;
import cjfw.wms.tm.dto.TmVehicleCustomerListResDto;
import cjfw.wms.tm.dto.TmVehicleInfoReqDto;
import cjfw.wms.tm.dto.TmVehicleInfoResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.09.10
 * @description : 거래처 목록 팝업 조회 서비스
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.10 OhEunbeom      생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmLocationMonitorCustomerListByCarPopupService {

	private transient static final String SERVICEID_PREFIX = "tmLocationMonitorCustomerListByCarPopupService.";

	private final CommonDao commonDao;

	private final UserContext userContext;


	/**
	 * @description : 차량 상세 정보 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.15 OhEunbeom                생성 </pre>
	 */
	public TmVehicleInfoResDto getVehicleInfo(TmVehicleInfoReqDto reqDto) {
		TmVehicleInfoResDto result = commonDao.selectOne(SERVICEID_PREFIX + "getVehicleInfo", reqDto);
		return result;
	}

	/**
	 * @description : 차량별 거래처 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.15 OhEunbeom                생성 </pre>
	 */
	public List<TmVehicleCustomerListResDto> getVehicleCustomerList(TmVehicleCustomerListReqDto reqDto) {
		List<TmVehicleCustomerListResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getVehicleCustomerList", reqDto);
		return result;
	}

	/**
	 * @description : 차량 상세 정보 조회 (Carrier 버전)
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12 OhEunbeom                생성 </pre>
	 */
	public TmVehicleInfoResDto getVehicleInfoByCarrier(TmVehicleInfoReqDto reqDto) {
		TmVehicleInfoResDto result = commonDao.selectOne(SERVICEID_PREFIX + "getVehicleInfoByCarrier", reqDto);
		return result;
	}

	/**
	 * @description : 차량별 거래처 목록 조회 (Carrier 버전)
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12 OhEunbeom                생성 </pre>
	 */
	public List<TmVehicleCustomerListResDto> getVehicleCustomerListByCarrier(TmVehicleCustomerListReqDto reqDto) {
		List<TmVehicleCustomerListResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getVehicleCustomerListByCarrier", reqDto);
		return result;
	}

}
