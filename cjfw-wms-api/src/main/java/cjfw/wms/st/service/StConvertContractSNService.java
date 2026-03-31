package cjfw.wms.st.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.st.dto.StConvertContractSNReqDto;
import cjfw.wms.st.dto.StConvertContractSNResDto;
import cjfw.wms.st.entity.StConvertContractSNEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.11.14 
 * @description : 상품이력계약정보변경 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.14 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StConvertContractSNService{
	private transient static final String SEERVICED_PREFIX = "StConvertContractSNService.";
	private transient static final String PAKAGE_NAME = "SPST_CONVERT";
	private transient static final String SERVICEID_TEMP_PREFIX = "cmTempTableService.";
	private final CommonDao commonDao;
	private final UserContext userContext;

	/**
	 * @description : 상품이력계약정보변경 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.14 ParkJinWoo 생성
	 */
	public List<StConvertContractSNResDto> getMasterList(StConvertContractSNReqDto reqDto){
		return commonDao.selectList(SEERVICED_PREFIX + "getDataHeaderList",reqDto);
		
	}

	/**
	 * @description : 상품이력계약정보변경 저장 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.14 ParkJinWoo 생성
	 */
	public String saveDataList(StConvertContractSNReqDto req){
		if(req != null) {
			List<StConvertContractSNResDto> list = req.getSaveList();
			for (var dto : list) {
				var entity = ModelMapperUtil.map(dto, userContext, StConvertContractSNEntity.class);
				 if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
					 commonDao.insert(SEERVICED_PREFIX +"insert", entity);
					 commonDao.update(SEERVICED_PREFIX +"update", entity);
					}
			}
		}
			
			return CanalFrameConstants.MSG_COM_SUC_CODE;
		
	}
	
	
} 