package cjfw.wms.wd.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.wd.dto.WdItemTraceReqDto;
import cjfw.wms.wd.dto.WdItemTraceResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.11.17
 * @description :모니터링 > 검수 > 검수 공정별  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.17 KimDongHan (liop0123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdItemTraceService {
	
    private final CommonDao commonDao;

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(WdItemTraceService.class.getSimpleName()) + ".";

    /**
     * @description : 모니터링 > 검수 > 검수 공정별 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.17 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<WdItemTraceResDto> getMasterList(WdItemTraceReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
    }
    
    /**
     * @description : 모니터링 > 검수 > 검수 공정별 팝업 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.17 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<WdItemTraceResDto> getPopList(WdItemTraceReqDto dto) {
    	
    	List<WdItemTraceResDto> result = null; 
    	
    	if("DP".equals(dto.getGubun())) {
    		result =  commonDao.selectList(SERVICEID_PREFIX + "getPopListDp", dto);
    	}else if("WD".equals(dto.getGubun())) {
			result =  commonDao.selectList(SERVICEID_PREFIX + "getPopListWd", dto);
		}
    	
    	return result;
    }

}
