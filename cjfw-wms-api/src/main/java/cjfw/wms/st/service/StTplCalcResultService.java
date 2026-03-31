package cjfw.wms.st.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.st.dto.StTplCalcResultReqDto;
import cjfw.wms.st.dto.StTplCalcResultResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkYoSep 
 * @date : 2025.11.12  
 * @description :위탁정산내역현황 기능을 구현한 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.12 ParkYoSep 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StTplCalcResultService {

    private transient static final String SERVICEID_PREFIX = "stTplCalcResultService."; 
    
    private final CommonDao commonDao;
    
    private final UserContext userContext;

    /**
     * @description : 위탁정산내역현황 조회 기능을 구현한 Method 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.12 ParkYoSep 생성
     */
    public List<StTplCalcResultResDto> getMasterList(StTplCalcResultReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList",reqDto);
    }
    
    
}
