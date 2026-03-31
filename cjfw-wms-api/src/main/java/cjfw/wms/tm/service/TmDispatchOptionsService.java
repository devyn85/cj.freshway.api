package cjfw.wms.tm.service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.wms.tm.domain.TmPlanOption;
import cjfw.wms.tm.dto.TmDispatchOptionsReqDto;
import cjfw.wms.tm.entity.TmPlanOptionEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : System Generated
 * @date : 2025.09.10
 * @description : 차량 배차 옵션 관리 Service (가상 동작 버전)
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.10 System Generated      생성 (매퍼 없이 가상 동작)
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmDispatchOptionsService {

    private transient static final String SERVICEID_PREFIX = "tmDispatchOptionsService.";
    private final CommonDao commonDao;

    /**
     * @description : 차량 배차 옵션 저장
     * @param dto 저장할 옵션 데이터
     * @return 저장 결과
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.10 System Generated      생성 (매퍼 없이 가상 동작)
     * 2025.09.26 JuSungbin (wntjdqls9818@cj.net) 실 작동 처리
     */
    public String saveDispatchOptions(TmDispatchOptionsReqDto dto) {
        
    	if(!(dto.getPlanOptionType().equals("1") || dto.getPlanOptionType().equals("2"))) {
    		throw new UserHandleException("MSG.COM.VAL.121");
    	}
        List<TmPlanOptionEntity> defaultOptions = commonDao.selectList(SERVICEID_PREFIX + "getDefaultMasterList", dto);

        List<TmPlanOptionEntity> optionList = TmPlanOption.toEntity(dto);

        TmPlanOption.validateInputValues(defaultOptions, optionList);

        commonDao.insert(SERVICEID_PREFIX + "saveMasterListInBulk", optionList);
        
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }


    /**
     * @description : 배차 옵션 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.09.26 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public TmDispatchOptionsReqDto getDispatchOptions(TmDispatchOptionsReqDto dto) {
    	
    	List<TmPlanOptionEntity> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
    	if(result.isEmpty()) {
    		result = commonDao.selectList(SERVICEID_PREFIX + "getDefaultMasterList", dto);
    	} 
    	return TmPlanOption.toDto(result);
    }
}
