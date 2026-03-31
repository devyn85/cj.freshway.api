package cjfw.wms.ms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsDriverReqDto;
import cjfw.wms.ms.dto.MsDriverResDto;
import cjfw.wms.ms.entity.MsDriverEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.cj.com) 
 * @date : 2025.05.10 
 * @description :  ADMIN > 시스템운영 > 프로그램 정보를 조회 및 저장 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.10 SangSuSung(kduimux@cj.cj.com) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsDriverService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "MsDriverService.";
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 * @description : 프로그램 목록 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	public List<MsDriverResDto> getDatalist(MsDriverReqDto dto) {
		List<MsDriverResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getDatalist", dto);
		return list;
	}
	
	/**
	 * @description : 프로그램 저장(CUD)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.30.30 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	public String saveConfirm(List<MsDriverReqDto> list) {
		if (null != list) {
			for (var dto : list) {
				var entity = ModelMapperUtil.map(dto, userContext, MsDriverEntity.class);
				if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
					commonDao.insert(SERVICEID_PREFIX +"insertPilot30", entity);
				} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
					commonDao.update(SERVICEID_PREFIX +"updatePilot30", entity);
				} else if ((CanalFrameConstants.DELETE).equals(dto.getRowStatus())) {
					commonDao.delete(SERVICEID_PREFIX +"deletePilot30", entity);
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
