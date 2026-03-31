package cjfw.wms.ms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsPopMngSTOReqDto;
import cjfw.wms.ms.dto.MsPopMngSTOResDto;
import cjfw.wms.ms.entity.MsPopMngSTOEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net)
 * @date : 2025.07.18 
 * @description : 기준정보 > 권역기준정보 > 거래처별POP관리(광역일배) Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.18 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsPopMngSTOService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msPopMngSTOService.";
	
	private final CommonDao commonDao;

	private final UserContext userContext;
	
	/**
	 * @description : 거래처별POP관리(광역일배) 정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsPopMngSTOResDto> getMasterList(MsPopMngSTOReqDto dto) {
		List<MsPopMngSTOResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
		return result;
	}
		
	/**
	 * @description :거래처별POP관리(광역일배) 정보 MERGE
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public String saveMasterList(List<MsPopMngSTOReqDto> dtoList) {
		if (null != dtoList) {
			for (MsPopMngSTOReqDto dto : dtoList) {
				MsPopMngSTOEntity entity = ModelMapperUtil.map(dto, userContext, MsPopMngSTOEntity.class);
				commonDao.update(SERVICEID_PREFIX +"saveMasterList", entity);
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
}
