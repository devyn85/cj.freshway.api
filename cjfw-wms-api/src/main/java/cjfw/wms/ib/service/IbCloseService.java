package cjfw.wms.ib.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.ib.dto.IbCloseReqDto;
import cjfw.wms.ib.dto.IbCloseResDto;
import cjfw.wms.ib.entity.IbCloseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.08.21
 * @description : Admin > 모니터링 > 마감상태 관리 Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.21 KimDongHan (liop0123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class IbCloseService {
	
    private final CommonDao commonDao;

    private final UserContext userContext;
    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(IbCloseService.class.getSimpleName()) + ".";

    /**
     * @description : Admin > 모니터링 > 마감상태 관리 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.21 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public List<IbCloseResDto> getMasterList(IbCloseReqDto dto) {
		
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}

	/**
	 * @description : Admin > 모니터링 > 마감상태 관리 저장 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
     * 2025.08.21 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public String saveMasterList(IbCloseReqDto reqDto) {
		
		List<IbCloseResDto> saveList = reqDto.getSaveList();
		
		for (IbCloseResDto dto : saveList) {
			
			IbCloseEntity entity = ModelMapperUtil.map(dto, userContext, IbCloseEntity.class);
			
			if (StringUtil.isEmpty(dto.getRowStatus())) {
				throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_ROWSTATUS"));
			}
		
			if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
				commonDao.insert(SERVICEID_PREFIX + "updateMaster", entity);
			} 
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
}
