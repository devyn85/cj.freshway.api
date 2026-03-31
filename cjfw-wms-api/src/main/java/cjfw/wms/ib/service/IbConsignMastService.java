package cjfw.wms.ib.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ib.dto.IbConsignMastReqDto;
import cjfw.wms.ib.dto.IbConsignMastResT1Dto;
import cjfw.wms.ib.dto.IbConsignMastResT2Dto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : 고혜미 (laksjd0606@cj.net)
 * @date : 2025.09.25
 * @description : 정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.25 고혜미 (laksjd0606@cj.net) 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class IbConsignMastService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "ibConsignMastService.";
	private final UserContext userContext;
    private final CommonDao commonDao;
    
	/**
	 * @description : 정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 관리[품목별정산료TAB] 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.12 고혜미 (laksjd0606@cj.net) 생성
	 *         </pre>
	 */
	public List<IbConsignMastResT1Dto> getTab1MasterList(IbConsignMastReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getTab1MasterList", dto);
	}  
    
	/**
	 * @description : 정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 관리[품목별정산료TAB] 생성자료조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.14 고혜미 (laksjd0606@cj.net) 생성
	 *         </pre>
	 */
	public List<IbConsignMastResT1Dto> getTab1CreatDataList(IbConsignMastReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getTab1CreatDataList", dto);
	}    

	/**
	 * @description : 정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산[품목별정산료TAB] 저장 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.12 고혜미 (laksjd0606@cj.net) 생성
     */
	public String saveTab1MasterList(List<IbConsignMastResT1Dto> paramDto) {
		
		if (null != paramDto) {
			for (var dto : paramDto) {
				var entity = ModelMapperUtil.map(dto, userContext, IbConsignMastResT1Dto.class);
				commonDao.selectOne(SERVICEID_PREFIX +"deleteTab1MasterList", entity);
				commonDao.selectOne(SERVICEID_PREFIX +"saveTab1MasterList", entity);
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 관리[기준정보TAB] 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.25 고혜미 (laksjd0606@cj.net) 생성
	 *         </pre>
	 */
	public List<IbConsignMastResT2Dto> getTab2MasterList(IbConsignMastReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getTab2MasterList", dto);
	}

	/**
	 * @description : 정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산[기준정보TAB] 저장 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.25 고혜미 (laksjd0606@cj.net) 생성
     */
	public String saveTab2MasterList(List<IbConsignMastReqDto> paramDto) {
		
		if (null != paramDto) {
			for (var dto : paramDto) {
				var entity = ModelMapperUtil.map(dto, userContext, IbConsignMastResT2Dto.class);
				commonDao.selectOne(SERVICEID_PREFIX +"saveTab2MasterList", entity);
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
}
