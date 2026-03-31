package cjfw.wms.kp.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.kp.dto.KpLocationCapaScanReqDto;
import cjfw.wms.kp.dto.KpLocationCapaScanResT1Dto;
import cjfw.wms.kp.dto.KpLocationCapaScanResT2Dto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.09
 * @description : 지표 > 재고 운영 > 물류센터 Capa 스캔 현황 Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.09 KimDongHan (liop0123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KpLocationCapaScanService {
	
    private final CommonDao commonDao;
    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(KpLocationCapaScanService.class.getSimpleName()) + ".";
    
    /**
     * @description : 지표 > 재고 운영 > 물류센터 Capa 스캔 현황 피킹존 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.09 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public List<KpLocationCapaScanResT1Dto> getPickingZoneList(KpLocationCapaScanReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getPickingZoneList", dto);
	}

    /**
     * @description : 지표 > 재고 운영 > 물류센터 Capa 스캔 현황 요약_탭 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.09 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public List<KpLocationCapaScanResT1Dto> getMasterT1List(KpLocationCapaScanReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterT1List", dto);
	}

    /**
     * @description : 지표 > 재고 운영 > 물류센터 Capa 스캔 현황 상세_탭 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.09 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public List<KpLocationCapaScanResT2Dto> getMasterT2List(KpLocationCapaScanReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterT2List", dto);
	}
}
