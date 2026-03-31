package cjfw.wms.kp.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.kp.dto.KpDailyUnloadReqDto;
import cjfw.wms.kp.dto.KpDailyUnloadResDto;
import cjfw.wms.kp.entity.KpDailyUnloadEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2026.01.19
 * @description : 데일리 생산성 하역 지표 관리 Service
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.01.19 JiHoPark 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KpDailyUnloadService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "kpDailyUnloadService.";

	private final CommonDao commonDao;
	private final UserContext userContext;

	/**
	 * @description : 데일리 생산성 하역 지표 관리 - 중복 데이터 여부 확인
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.28 JiHoPark  생성 </pre>
	 */
	private boolean getDuplicatedYn(List<KpDailyUnloadResDto> dtoList) {
		boolean bResult = false;

		for (KpDailyUnloadResDto dto : dtoList) {

			bResult = dtoList.stream()
                    .filter(other -> !other.equals(dto)) // 자기 자신 제외
                    .anyMatch(val -> val.getDccode().equals(dto.getDccode())
                    		&& val.getGubun1().equals(dto.getGubun1())
                    		&& val.getGubun2().equals(dto.getGubun2())
                    		&& val.getFromHour().equals(dto.getFromHour())
                    		&& val.getToHour().equals(dto.getToHour())
                    		&& val.getContractFromdate().equals(dto.getContractFromdate())
                    		);

    		if (bResult) {
    			break;
    		}
    	}

		return bResult;
	}

	/**
	 * @description : 데일리 생산성 하역 지표 관리 - 투입인원 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.19 JiHoPark  생성 </pre>
	 */
	public List<KpDailyUnloadResDto> getMasterList(KpDailyUnloadReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}

	/**
	 * @description : 데일리 생산성 하역 지표 관리 - 센터업무관리 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.20 JiHoPark  생성 </pre>
	 */
	public List<KpDailyUnloadResDto> getPopupMasterList(KpDailyUnloadReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getPopupMasterList", dto);
	}

	/**
	 * @description : 데일리 생산성 하역 지표 관리 - 센터업무관리(예외) 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.20 JiHoPark  생성 </pre>
	 */
	public List<KpDailyUnloadResDto> getPopupMasterList2(KpDailyUnloadReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getPopupMasterList2", dto);
	}

	/**
	 * @description : 데일리 생산성 하역 지표 관리 - 분류피킹 제외대상 고객 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.21 JiHoPark  생성 </pre>
	 */
	public List<KpDailyUnloadResDto> getPopupMasterList3(KpDailyUnloadReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getPopupMasterList3", dto);
	}

	/**
	 * @description : 데일리 생산성 하역 지표 관리 - 투입인원 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.27 JiHoPark  생성 </pre>
	 */
    public String saveMasterList(KpDailyUnloadReqDto dto) {
    	KpDailyUnloadEntity entity = new KpDailyUnloadEntity();

    	List<KpDailyUnloadResDto> saveList = dto.getSaveMasterList();
        if (saveList.size() > 0) {
        	for (KpDailyUnloadResDto saveDto : saveList) {
        		entity = ModelMapperUtil.map(saveDto, userContext, KpDailyUnloadEntity.class);
        		commonDao.update(SERVICEID_PREFIX + "updateMasterList", entity);
        	}
        }

		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

	/**
	 * @description : 데일리 생산성 하역 지표 관리 - 센터업무관리 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.27 JiHoPark  생성 </pre>
	 */
    public String savePopupMasterList(KpDailyUnloadReqDto dto) {
    	KpDailyUnloadEntity entity = new KpDailyUnloadEntity();

		List<KpDailyUnloadResDto> deleteList = dto.getDeletePopupMasterList();
        if (deleteList.size() > 0) {
        	for (KpDailyUnloadResDto deleteDto : deleteList) {
        		entity = ModelMapperUtil.map(deleteDto, userContext, KpDailyUnloadEntity.class);
        		commonDao.update(SERVICEID_PREFIX + "deletePopupMasterList", entity);
        	}
        }

        List<KpDailyUnloadResDto> updateList = dto.getUpdatePopupMasterList();
        if (updateList.size() > 0) {
        	for (KpDailyUnloadResDto updateDto : updateList) {
        		entity = ModelMapperUtil.map(updateDto, userContext, KpDailyUnloadEntity.class);
        		commonDao.update(SERVICEID_PREFIX + "updatePopupMasterList", entity);
        	}
        }

    	List<KpDailyUnloadResDto> insertList = dto.getInsertPopupMasterList();
    	if (insertList.size() > 0) {
        	for (KpDailyUnloadResDto updateDto : updateList) {
        		entity = ModelMapperUtil.map(updateDto, userContext, KpDailyUnloadEntity.class);
        		commonDao.insert(SERVICEID_PREFIX + "insertPopupMasterList", entity);
        	}
        }

		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

    /**
	 * @description : 데일리 생산성 하역 지표 관리 - 센터업무관리(예외) 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.29 JiHoPark  생성 </pre>
	 */
    public String savePopupMasterList2(KpDailyUnloadReqDto dto) {
    	KpDailyUnloadEntity entity = new KpDailyUnloadEntity();

		List<KpDailyUnloadResDto> deleteList = dto.getDeletePopupMasterList();
        if (deleteList.size() > 0) {
        	for (KpDailyUnloadResDto deleteDto : deleteList) {
        		entity = ModelMapperUtil.map(deleteDto, userContext, KpDailyUnloadEntity.class);
        		commonDao.update(SERVICEID_PREFIX + "deletePopupMasterList", entity);
        	}
        }

        List<KpDailyUnloadResDto> updateList = dto.getUpdatePopupMasterList();
        if (updateList.size() > 0) {
        	for (KpDailyUnloadResDto updateDto : updateList) {
        		entity = ModelMapperUtil.map(updateDto, userContext, KpDailyUnloadEntity.class);
        		commonDao.update(SERVICEID_PREFIX + "updatePopupMasterList", entity);
        	}
        }

    	List<KpDailyUnloadResDto> insertList = dto.getInsertPopupMasterList();
        if (insertList.size() > 0) {
        	for (KpDailyUnloadResDto insertDto : insertList) {
        		entity = ModelMapperUtil.map(insertDto, userContext, KpDailyUnloadEntity.class);
        		commonDao.insert(SERVICEID_PREFIX + "insertPopupMasterList", entity);
        	}
        }

		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

    /**
	 * @description : 데일리 생산성 하역 지표 관리 - 분류피킹 제외 대상 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.29 JiHoPark  생성 </pre>
	 */
    public String savePopupMasterList3(KpDailyUnloadReqDto dto) {
    	KpDailyUnloadEntity entity = new KpDailyUnloadEntity();

		List<KpDailyUnloadResDto> deleteList = dto.getDeletePopupMasterList3();
        if (deleteList.size() > 0) {
        	for (KpDailyUnloadResDto deleteDto : deleteList) {
        		entity = ModelMapperUtil.map(deleteDto, userContext, KpDailyUnloadEntity.class);
        		commonDao.update(SERVICEID_PREFIX + "deletePopupMasterList3", entity);
        	}
        }

        List<KpDailyUnloadResDto> updateList = dto.getUpdatePopupMasterList3();
        if (updateList.size() > 0) {
        	for (KpDailyUnloadResDto updateDto : updateList) {
        		entity = ModelMapperUtil.map(updateDto, userContext, KpDailyUnloadEntity.class);
        		commonDao.update(SERVICEID_PREFIX + "updatePopupMasterList3", entity);
        	}
        }

    	List<KpDailyUnloadResDto> insertList = dto.getInsertPopupMasterList3();
        if (insertList.size() > 0) {
        	for (KpDailyUnloadResDto insertDto : insertList) {
        		entity = ModelMapperUtil.map(insertDto, userContext, KpDailyUnloadEntity.class);
        		commonDao.insert(SERVICEID_PREFIX + "insertPopupMasterList3", entity);
        	}
        }

		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
