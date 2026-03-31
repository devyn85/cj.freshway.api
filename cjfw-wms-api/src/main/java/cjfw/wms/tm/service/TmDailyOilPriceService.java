package cjfw.wms.tm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.tm.dto.TmDailyOilPriceListReqDto;
import cjfw.wms.tm.dto.TmDailyOilPriceListResDto;
import cjfw.wms.tm.dto.TmEntityRuleMasterListResDto;
import cjfw.wms.tm.entity.TmDailyOilPriceEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.09.05
 * @description : 유가정보 기능을 구현한 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.05 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmDailyOilPriceService {

	private transient static final String SERVICEID_PREFIX = "tmDailyOilPriceService.";
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @description : 유가정보 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.05 ParkJinWoo 생성
	 */
	public List<TmDailyOilPriceListResDto> getMasterList(TmDailyOilPriceListReqDto reqDto) {
//		tmInvoicelogMgrReqDto.setDocType("WD");
		return commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderlist",reqDto);
	}
	
	
	/**
	 * @description : 유가 저장 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.05 ParkJinWoo 생성
	 */
	public String saveConfirm(TmDailyOilPriceListReqDto req) {
		if(req != null) {
		List<TmDailyOilPriceListResDto> list = req.getSaveList();
		for (var dto : list) {
			var entity = ModelMapperUtil.map(dto, userContext, TmDailyOilPriceEntity.class);
			if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
				int seq = commonDao.insert(SERVICEID_PREFIX +"insertGasStationPrice", entity);
					if(seq == 0) {
					 throw new UserHandleException("MSG.COM.VAL.067", new String[]{"데이터"});
					}
			} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
				int seq = commonDao.update(SERVICEID_PREFIX +"updateGasStationPrice", entity);
					if(seq == 0) {
						 throw new UserHandleException("MSG.COM.VAL.067", new String[]{"데이터"});
					}
			} else if ((CanalFrameConstants.DELETE).equals(dto.getRowStatus())) {
				commonDao.update(SERVICEID_PREFIX +"deleteGasStationPrice", entity);
			} 
		}
		}
	
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}


}
