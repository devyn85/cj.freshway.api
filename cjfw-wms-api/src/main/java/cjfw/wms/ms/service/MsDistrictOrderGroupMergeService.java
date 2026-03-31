package cjfw.wms.ms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.ms.dto.MsDistrictOrderGroupMergeReqDto;
import cjfw.wms.ms.dto.MsDistrictValidationReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.22 
 * @description : 센터 주문그룹 SRM 공유 Service 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.22 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsDistrictOrderGroupMergeService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msDistrictOrderGroupMergeService.";
	
	private final CommonDao commonDao;

	private final UserContext userContext;
	
	/**
	 * @description : 주문 그룹 / POP 데이터 변동시 SRM 공유 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.22 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public void saveMaster(MsDistrictOrderGroupMergeReqDto dto) {
		commonDao.insert(SERVICEID_PREFIX + "saveMasterList", dto);
	}
	
	/**
	 * @description : 행정동 저장시 변동사항 기준 SRM 공유 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public void saveMasterListNew(MsDistrictOrderGroupMergeReqDto dto) {
        List.of("SCM0880", "SCM0900").forEach(ifId -> {
            dto.setIfId(ifId);
            commonDao.insert(SERVICEID_PREFIX + "saveMasterListNew", dto);
        });
	}
	
	/**
	 * @description : POP 저장시 SRM 공유 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.23 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public void saveMasterFromPop(MsDistrictOrderGroupMergeReqDto dto)  {
		commonDao.insert(SERVICEID_PREFIX + "saveMasterFromPop", dto);
	}

	/**
	 * @description : MS_HJDONG_POP 스냅샷 테이블 MERGE (전체 재계산)
	 */
	public void mergeMsHjdongPop(MsDistrictValidationReqDto dto) {
		commonDao.update("msHjdongPopService.mergeMsHjdongPop", dto);
	}

	/**
	 * @description : MS_HJDONG_POP 스냅샷 대비 변경분을 IF_MS_HJDONG_POP에 적재
	 */
	public void insertIfFromMsHjdongPop(MsDistrictValidationReqDto dto) {
		commonDao.insert("msHjdongPopService.insertIfFromMsHjdongPop", dto);
	}

}
