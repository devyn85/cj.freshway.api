/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.sysmgt.func.batch.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.sysmgt.func.batch.dto.BatchLogJobGetReqDto;
import cjfw.wms.sysmgt.func.batch.dto.BatchLogJobGetResDto;
import cjfw.wms.sysmgt.func.batch.dto.BatchLogStepGetReqDto;
import cjfw.wms.sysmgt.func.batch.dto.BatchLogStepGetResDto;
import cjfw.wms.sysmgt.func.batch.dto.BatchLogStepMsgGetReqDto;
import cjfw.wms.sysmgt.func.batch.dto.BatchLogStepMsgGetResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 기록된 배치 실행 이력을 보여준다. 
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BatchLogService{
	
	private final CommonDao commonDao;

	/**
	 * Job List 조회
	 */
	public Page<List<BatchLogJobGetResDto>> getJobList(BatchLogJobGetReqDto batchLogJobGetReqDto, Page page) {
		Page<List<BatchLogJobGetResDto>> list = commonDao.selectPageList("batch.getJobList", batchLogJobGetReqDto, page);
		return list;
	}
	
	/**
	 * Step List 조회
	 */
	public List<BatchLogStepGetResDto> getStepList(BatchLogStepGetReqDto batchLogStepGetReqDto) {
		List<BatchLogStepGetResDto> list = commonDao.selectList("batch.getStepList", batchLogStepGetReqDto);
		return list;
	}

	/** 
	 * Step MSG를 조회한다.<br>
	 * TODO 삭제
	 */
	public BatchLogStepMsgGetResDto getStepMsg(BatchLogStepMsgGetReqDto batchLogStepMsgGetReqDto) {
		BatchLogStepMsgGetResDto result = commonDao.selectOne("batch.getStepResultMessage", batchLogStepMsgGetReqDto);
		return result;
	}

}
