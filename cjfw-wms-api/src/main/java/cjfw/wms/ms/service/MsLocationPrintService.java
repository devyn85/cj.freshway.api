package cjfw.wms.ms.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.ms.dto.MsLocationPrintReqDto;
import cjfw.wms.ms.dto.MsLocationPrintResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.24
 * @description : 기준정보 > 물류센터 정보 > 로케이션 라벨 출력 Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.24 KimDongHan (liop0123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsLocationPrintService {
	
    private final CommonDao commonDao;
    
    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(MsLocationPrintService.class.getSimpleName()) + ".";

    /**
     * @description : 기준정보 > 물류센터 정보 > 로케이션 라벨 출력 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.24 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<MsLocationPrintResDto> getMasterList(MsLocationPrintReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
    }

}
