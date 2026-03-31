package cjfw.wms.ms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsDCxCustReqDto;
import cjfw.wms.ms.dto.MsDCxCustResDto;
import cjfw.wms.ms.entity.MsDCxCustEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.08
 * @description : 기준정보 > 센터기준정보 > 센터별거래처관리 목록 조회 및 저장 Service
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.08        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsDCxCustService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msDCxCustService.";
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 * 
	 * @description : 센터별거래처관리 목록조회 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.08        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<MsDCxCustResDto> getMasterList(MsDCxCustReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}
	
	/**
	 * 
	 * @description : 센터별거래처관리 저장 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.08        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String saveMasterList(List<MsDCxCustReqDto> list) {
		if (null != list) {
			for (var dto : list) {
				var entity = ModelMapperUtil.map(dto, userContext, MsDCxCustEntity.class);
				
				if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
					commonDao.insert(SERVICEID_PREFIX +"insertMaster", entity);
				} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
					commonDao.update(SERVICEID_PREFIX +"updateMaster", entity);
//					commonDao.selectOne(SERVICEID_PREFIX +"update", entity);
					
//					if(entity.getErr() > 0) {
//						throw new UserHandleException(""+"에러코드 : "+ entity.getErr() + "에러메세지 : " + entity.getErrmsg());
//					}
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

}
