package cjfw.wms.ib.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ib.dto.IbKxStoragefeeExpenseReqDto;
import cjfw.wms.ib.dto.IbKxStoragefeeExpenseResDto;
import cjfw.wms.ib.entity.IbKxStoragefeeExpenseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.11.05
 * @description : 보관료 마감 기능을 구현한 Service Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.05        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class IbKxStoragefeeExpenseService {
	
    private final CommonDao commonDao;
    private final UserContext userContext;
    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = "ibKxStoragefeeExpenseService.";
    
	/**
	 * @description :  보관료 마감 현황 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE              AUTHOR             MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.05        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<IbKxStoragefeeExpenseResDto> getMasterList(IbKxStoragefeeExpenseReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}
	
	/**
	 * 
	 * @description : 보관료 계산 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.05        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String saveKxStorageExpense(List<IbKxStoragefeeExpenseReqDto> list) {
		if (null != list) {
			for (var dto : list) {
				int checkExistCnt = 0;
				Map<String, Object> map = commonDao.selectOne(SERVICEID_PREFIX + "checkExist", dto);
				if(map != null && map.get("CHECK_CNT") != null) {
					checkExistCnt = Integer.parseInt(String.valueOf(map.get("CHECK_CNT")));	
				}				
				if(checkExistCnt > 0){
					throw new UserHandleException("마감 후에는 송장 금액 수정이 불가합니다.");
				} else {
					var entity = ModelMapperUtil.map(dto, userContext, IbKxStoragefeeExpenseEntity.class);
					commonDao.insert(SERVICEID_PREFIX +"saveKxStorageExpense", entity);
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * 
	 * @description : 보관료 계산 취소 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.05        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String cancelKxStorageExpense(List<IbKxStoragefeeExpenseReqDto> list) {
		if (null != list) {
			for (var dto : list) {
				int checkExistCnt = 0;
				Map<String, Object> map = commonDao.selectOne(SERVICEID_PREFIX + "checkExist", dto);
				if(map != null && map.get("CHECK_CNT") != null) {
					checkExistCnt = Integer.parseInt(String.valueOf(map.get("CHECK_CNT")));	
				}
				
				if(checkExistCnt > 0){
					throw new UserHandleException("마감 후에는 송장 금액 수정이 불가합니다.");
				} else {
					var entity = ModelMapperUtil.map(dto, userContext, IbKxStoragefeeExpenseEntity.class);
					log.info("cancelKxStorageExpense entity ::: {}", entity);
					commonDao.insert(SERVICEID_PREFIX +"cancelKxStorageExpense", entity);
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
