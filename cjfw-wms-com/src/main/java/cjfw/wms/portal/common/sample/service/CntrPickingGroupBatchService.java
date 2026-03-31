package cjfw.wms.portal.common.sample.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.portal.common.sample.dto.CntrPickingGroupBatchGetReqDto;
import cjfw.wms.portal.common.sample.dto.CntrPickingGroupBatchGetResDto;
import cjfw.wms.portal.common.sample.dto.CntrPickingGroupBatchsaveReqDto;
import cjfw.wms.portal.common.sample.dto.CntrPickingGroupDcCodeResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : 박진우 (jwpark1104@cj.net)
 * @date : 2025.04.21
 * @description : 피킹그룹 기능을 구현한 Service Class
 * @issues : ----------------------------------------------------------- DATE
 *         AUTHOR MAJOR_ISSUE
 *         -----------------------------------------------------------
 *         2025.04.17 박진우 (jwpark1104@cj.net) 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CntrPickingGroupBatchService {

	private final CommonDao commonDao;

	/**
	 * 피킹그룹 조회
	 * 
	 * @param CntrPickingGroupBatchGetReqDto
	 * @return
	 */
	public List<CntrPickingGroupBatchGetResDto> getPickingGroup(
			CntrPickingGroupBatchGetReqDto CntrPickingGroupBatchGetReqDto) {
		List<CntrPickingGroupBatchGetResDto> list = commonDao.selectList("CntrPickingGroupBatchService.getPickingGroupList", CntrPickingGroupBatchGetReqDto);
		return list;
	}

	/**
	 * 피킹그룹 상세조회
	 * 
	 * @param CntrPickingGroupBatchGetReqDto
	 * @return
	 */
	public List<CntrPickingGroupBatchGetResDto> searchDetaile(
			CntrPickingGroupBatchGetReqDto CntrPickingGroupBatchGetReqDto) {
		List<CntrPickingGroupBatchGetResDto> list = commonDao.selectList("CntrPickingGroupBatchService.searchDetaile",
				CntrPickingGroupBatchGetReqDto);
		return list;
	}

	/**
	 * 피킹그룹 상세조회
	 * 
	 * @param CntrPickingGroupBatchsaveReqDto
	 * @return
	 */
	public String savePickingGroup(CntrPickingGroupBatchsaveReqDto CntrPickingGroupBatchsaveReqDto) {

		List<CntrPickingGroupBatchsaveReqDto.insertParams> list = CntrPickingGroupBatchsaveReqDto.getInsertParams();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				// 임시 데이터
				list.get(i).setUserId("SYSTEM");
				if (list.get(i).getFlag().toString().equals("U")) {
					commonDao.update("CntrPickingGroupBatchService.update", list.get(i));
				} else if (list.get(i).getFlag().toString().equals("I")) {
					commonDao.update("CntrPickingGroupBatchService.insert", list.get(i));
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;

	}
	
	public List<CntrPickingGroupDcCodeResDto> getDccode (){
		List<CntrPickingGroupDcCodeResDto> List = commonDao	.selectList("CntrPickingGroupBatchService.getPickingDcCodeList");
		return List;
	}

}
