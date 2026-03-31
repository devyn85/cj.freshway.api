package cjfw.wms.wd.service;

import java.beans.PropertyDescriptor;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.entity.CmSyProcessTempWdEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.wd.dto.WdDeliveryLabelDailyReqDto;
import cjfw.wms.wd.dto.WdDeliveryLabelDailyResTab1Dto;
import cjfw.wms.wd.dto.WdDeliveryLabelDailyResTab2Dto;
import cjfw.wms.wd.dto.WdDeliveryLabelDailyResPrintDto;
import cjfw.wms.wd.dto.WdDeliveryLabelDailyResPrintHeaderDto;
import cjfw.wms.wd.dto.WdDeliveryLabelDailyResPrintDetailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2026.02.19 
 * @description : 일배분류서출력 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.02.19 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdDeliveryLabelDailyService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "wdDeliveryLabelDailyService.";
	
	private final CommonDao commonDao;
	private final UserContext userContext;
	private final CmCommonService cmCommonService;

	/**
	 * @description : 일배분류서출력-일배 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.19 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdDeliveryLabelDailyResTab1Dto> getTab1MasterList(WdDeliveryLabelDailyReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<WdDeliveryLabelDailyResTab1Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1MasterList", dto);
		return list;
	}
	
	/**
	 * @description : 일배분류서출력-광역일배 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.19 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdDeliveryLabelDailyResTab2Dto> getTab2MasterList(WdDeliveryLabelDailyReqDto dto) {
		
		log.info("#### parameter = "+dto.toString());		
		
		List<WdDeliveryLabelDailyResTab2Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab2MasterList", dto);
		return list;
	}
	

	/**
	 * @description : 일배 출력 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.19 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public WdDeliveryLabelDailyResPrintDto getTab1PrintList(WdDeliveryLabelDailyReqDto dto) { 
		WdDeliveryLabelDailyResPrintDto reportDto = new WdDeliveryLabelDailyResPrintDto();
		log.info("##### parameter = "+dto.toString());		
		
		if("0".equals(dto.getSearchtype())){			
			reportDto.setReportHeaderList(commonDao.selectList(SERVICEID_PREFIX + "getDataPopHeaderlist", dto));
		}else if("1".equals(dto.getSearchtype())){
			reportDto.setReportHeaderList(commonDao.selectList(SERVICEID_PREFIX + "getDataCustHeaderlist", dto));
		}else if("2".equals(dto.getSearchtype())){
			reportDto.setReportHeaderList(commonDao.selectList(SERVICEID_PREFIX + "getDataCarnoHeaderlist", dto));
		}		
		reportDto.setReportDetailList(commonDao.selectList(SERVICEID_PREFIX + "getDetailList", dto));
		
		return reportDto;
	}
	
	/**
	 * @description : 광역일배 출력 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.19 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public WdDeliveryLabelDailyResPrintDto getTab2PrintList(WdDeliveryLabelDailyReqDto dto) { 
		WdDeliveryLabelDailyResPrintDto reportDto = new WdDeliveryLabelDailyResPrintDto();
		log.info("##### parameter = "+dto.toString());		
				
		if("0".equals(dto.getSearchtype())){			
			reportDto.setReportHeaderList(commonDao.selectList(SERVICEID_PREFIX + "getDataSTOPopHeaderlist", dto));
		}else if("1".equals(dto.getSearchtype())){
			reportDto.setReportHeaderList(commonDao.selectList(SERVICEID_PREFIX + "getDataSTOCustHeaderlist", dto));
		}else if("2".equals(dto.getSearchtype())){
			reportDto.setReportHeaderList(commonDao.selectList(SERVICEID_PREFIX + "getDataSTOCarnoHeaderlist", dto));
		}		
		reportDto.setReportDetailList(commonDao.selectList(SERVICEID_PREFIX + "getDetailSTOList", dto));
		
		return reportDto;
	}
		
}
