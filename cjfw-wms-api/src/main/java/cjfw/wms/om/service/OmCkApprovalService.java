package cjfw.wms.om.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.om.dto.OmCkApprovalReqDto;
import cjfw.wms.om.dto.OmCkApprovalResDto;
import cjfw.wms.om.entity.OmCkApprovalEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net)
 * @date : 2025.09.26 
 * @description : 주문 > 주문요청 > CK주문결재내역 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.26 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OmCkApprovalService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "omCkApprovalService.";
	
	private final CommonDao commonDao;

	private final UserContext userContext;
	
	/**
	 * @description : CK주문결재내역 마스터 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.26 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<OmCkApprovalResDto> getMasterList(OmCkApprovalReqDto dto) {
		List<OmCkApprovalResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
		return result;
	}
	

	/**
	 * @description :CK주문결재내역 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.26 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public String saveMasterList(List<OmCkApprovalReqDto> dtoList)  {
		if(dtoList != null) {
			for(OmCkApprovalReqDto dto : dtoList) {
				OmCkApprovalEntity entity = ModelMapperUtil.map(dto, userContext, OmCkApprovalEntity.class);
				commonDao.update(SERVICEID_PREFIX + "saveMasterList", entity);
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
		
		
	
	
}
