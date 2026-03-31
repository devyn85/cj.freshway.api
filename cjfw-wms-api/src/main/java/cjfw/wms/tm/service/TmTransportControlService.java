package cjfw.wms.tm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.tm.dto.TmTransportControlReqDto;
import cjfw.wms.tm.dto.TmTransportControlResDto;
import cjfw.wms.tm.entity.TmTransportControlEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.11.05
 * @description : 수송배차조정 Service
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.05 JiHoPark 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmTransportControlService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "tmTransportControlService.";

	private final CommonDao commonDao;
	private final UserContext userContext;

	/**
	 * @description : 수송배차조정 - 노선 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.06 JiHoPark  생성 </pre>
	 */
	public List<TmTransportControlResDto> getTransportRoutingList(TmTransportControlReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getTransportRoutingList", dto);
	}

	/**
	 * @description : 수송배차조정 - 수송배차조정 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.05 JiHoPark  생성 </pre>
	 */
	public List<TmTransportControlResDto> getMasterList(TmTransportControlReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}

	/**
	 * @description : 수송배차조정 - 수송배차조정 상세 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.05 JiHoPark  생성 </pre>
	 */
	public List<TmTransportControlResDto> getMasterList2(TmTransportControlReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList2", dto);
	}

	/**
	 * @description : 수송배차조정 - 차량정보 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.11 JiHoPark  생성 </pre>
	 */
	public TmTransportControlResDto getCarInfo(TmTransportControlReqDto dto) {
		TmTransportControlResDto carDto = dto.getMasterInfo();
		return commonDao.selectOne(SERVICEID_PREFIX + "getCarInfo", carDto);
	}

	/**
	 * @description : 수송배차조정 - 비용 정보 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.11 JiHoPark  생성 </pre>
	 */
	public TmTransportControlResDto getTotPrice(TmTransportControlReqDto dto) {
		TmTransportControlResDto priceDto = dto.getMasterInfo();
		return commonDao.selectOne(SERVICEID_PREFIX + "getTotPrice", priceDto);
	}

	/**
	 * @description : 수송배차조정 - 목록 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.11 JiHoPark  생성 </pre>
	 */
    public String saveMasterList(TmTransportControlReqDto dto) {
        TmTransportControlEntity entity = new TmTransportControlEntity();

        List<TmTransportControlResDto> inserList = dto.getInsertMasterList(); // insert
        if (inserList.size() > 0) {
        	for (TmTransportControlResDto insertDto : inserList) {
        		entity = ModelMapperUtil.map(insertDto, userContext, TmTransportControlEntity.class);
        		commonDao.update(SERVICEID_PREFIX + "insertMasterList", entity);
        	}
        }

        List<TmTransportControlResDto> updateList = dto.getUpdateMasterList(); // update
        if (updateList.size() > 0) {
        	for (TmTransportControlResDto updateDto : updateList) {
        		entity = ModelMapperUtil.map(updateDto, userContext, TmTransportControlEntity.class);
        		commonDao.update(SERVICEID_PREFIX + "updateMasterList", entity);
        	}
        }

		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
