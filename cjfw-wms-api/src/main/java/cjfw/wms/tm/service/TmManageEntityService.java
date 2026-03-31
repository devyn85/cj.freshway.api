package cjfw.wms.tm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.tm.dto.TmManageEntityMasterListReqDto;
import cjfw.wms.tm.dto.TmManageEntityMasterListResDto;
import cjfw.wms.tm.entity.TmManageEntityEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.06.30 
 * @description :납품서 츨력로그 (관리자) Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.30 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmManageEntityService {

	/**`
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "tmManageEntityService.";
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @description : 정산항목관리 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 ParkJinWoo 생성
	 */
	public List<TmManageEntityMasterListResDto> getMasterList(TmManageEntityMasterListReqDto tmManageEntityMasterListReqDto) {
//		tmInvoicelogMgrReqDto.setDocType("WD");
		return commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderlist",tmManageEntityMasterListReqDto);
	}
	
	
	/**
	 * @description : 정산항목관리 저장 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 ParkJinWoo 생성
	 */
	public String saveConfirm(TmManageEntityMasterListReqDto req) {
		if(req != null) {
		List<TmManageEntityMasterListResDto> list = req.getSaveList();
		for (var dto : list) {
			var entity = ModelMapperUtil.map(dto, userContext, TmManageEntityEntity.class);
			if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
				if(commonDao.selectList(SERVICEID_PREFIX + "insertChk",entity).size() >0) {
					 throw new UserHandleException("MSG.COM.VAL.067", new String[]{"데이터"});
				}
				commonDao.insert(SERVICEID_PREFIX +"insertSttlItem", entity);
			} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
				int chk = commonDao.update(SERVICEID_PREFIX +"updateSttlItem", entity);
				if(chk == 0) {
					throw new UserHandleException("MSG.COM.VAL.067", new String[]{"데이터"});
				}
			}  else if ((CanalFrameConstants.DELETE).equals(dto.getRowStatus())) {
				commonDao.update(SERVICEID_PREFIX +"deleteSttlItem", entity);
			}else if(dto.getRowStatus().equals(null)||dto.getRowStatus() == "") {
				continue;
			}
		}
		}
	
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}


}
