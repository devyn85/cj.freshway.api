package cjfw.wms.batch.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.batch.dto.BatchHistoryReqDto;
import cjfw.wms.batch.dto.BatchHistoryResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : yewon.kim (yewon.kim9@cj.net)
 * @date : 2025.07.08
 * @description : 배치 > 배치관리 > 배치 등록/수정 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.08 yewon.kim (yewon.kim9@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BatchHistoryService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "batchHistoryService.";
	
	private final CommonDao commonDao;

	private final UserContext userContext;
	
	/**
	 * @description : 배치 JOB 이력 조회(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.08 yewon.kim (yewon.kim9@cj.net)  생성 </pre>
	 */
	public List<BatchHistoryResDto> getBatchJobHistList(BatchHistoryReqDto dto) {
		List<BatchHistoryResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getBatchJobHistList", dto);
		return result;
	}

    /**
     * @description : 배치 JOB 상세내역 조회(목록)
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.08 yewon.kim (yewon.kim9@cj.net)  생성 </pre>
     */
    public List<BatchHistoryResDto> getBatchJobDetailHistList(BatchHistoryReqDto dto) {
        List<BatchHistoryResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getBatchJobDetailHistList", dto);
        return result;
    }

	/**
	 * @description : 배치 PARAM 이력 조회(목록)
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.08 yewon.kim (yewon.kim9@cj.net)  생성 </pre>
	 */
	public List<BatchHistoryResDto> getBatchParamHistList(BatchHistoryReqDto dto) {
		List<BatchHistoryResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getBatchParamHistList", dto);
		return result;
	}

	/**
	 * @description : 배치 Step 이력 조회(목록)
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.08 yewon.kim (yewon.kim9@cj.net)  생성 </pre>
	 */
	public List<BatchHistoryResDto> getBatchStepHistList(BatchHistoryReqDto dto) {
		List<BatchHistoryResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getBatchStepHistList", dto);
		return result;
	}

}
