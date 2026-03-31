package cjfw.wms.batch.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.batch.dto.BatchMngReqDto;
import cjfw.wms.batch.dto.BatchMngResDto;
import cjfw.wms.batch.entity.BatchParamSetEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : yewon.kim (yewon.kim9@cj.net)
 * @date : 2025.07.09
 * @description : 배치 > 배치관리 > 배치 등록/수정 > 팝업 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.09 yewon.kim (yewon.kim9@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BatchPopService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "batchPopService.";
	
	private final CommonDao commonDao;

	private final UserContext userContext;
	
	/**
	 * @description : 배치 인수 설정 팝업.
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.09 yewon.kim (yewon.kim9@cj.net)  생성 </pre>
	 */
	public List<BatchMngResDto> getBatchParamSetList(BatchMngReqDto dto) {
		List<BatchMngResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getBatchParamSetList", dto);
		return result;
	}

	public String saveBatchParamSetList(List<BatchMngReqDto> dtoList) {
		String jobName = dtoList.get(0).getJobName();

        for (BatchMngReqDto dto : dtoList) {
            dto.setJobName(jobName);
            BatchParamSetEntity entity = ModelMapperUtil.map(dto, userContext, BatchParamSetEntity.class);

			if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
				commonDao.insert(SERVICEID_PREFIX + "insertBatchD", entity);
			} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
				commonDao.update(SERVICEID_PREFIX + "updateBatchD", entity);
			} else if ((CanalFrameConstants.DELETE).equals(dto.getRowStatus())) {
				commonDao.delete(SERVICEID_PREFIX + "deleteBatchD", entity);
			}

        }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
