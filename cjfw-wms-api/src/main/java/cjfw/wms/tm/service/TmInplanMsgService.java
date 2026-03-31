package cjfw.wms.tm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.tm.dto.TmInplanMsgListReqDto;
import cjfw.wms.tm.dto.TmInplanMsgListResDto;
import cjfw.wms.tm.entity.TmInplanMsgEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.07 
 * @description : 배송전달사항 기능을 구현한 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.07 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmInplanMsgService {

	/**`
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "tmInplanMsgService.";
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @description : 배송전달사항 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.07 ParkJinWoo 생성
	 */
	public List<TmInplanMsgListResDto> getMasterList(TmInplanMsgListReqDto tmInplanMsgListReqDto) {
//		tmInvoicelogMgrReqDto.setDocType("WD");
		return commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderlist",tmInplanMsgListReqDto);
	}
	
	/**
	 * @description : 배송전달사항 저장 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.07 ParkJinWoo 생성
	 */
	public String saveConfirm(TmInplanMsgListReqDto req) {
		if(req != null) {
		List<TmInplanMsgListResDto> list = req.getSaveList();
		for (var dto : list) {
			var entity = ModelMapperUtil.map(dto, userContext, TmInplanMsgEntity.class);
			if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
				commonDao.insert(SERVICEID_PREFIX +"insertDeliveryInfo", entity);
			} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
				commonDao.update(SERVICEID_PREFIX +"updateDeliveryInfo", entity);
			} 
		}
		}
	
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}


}
