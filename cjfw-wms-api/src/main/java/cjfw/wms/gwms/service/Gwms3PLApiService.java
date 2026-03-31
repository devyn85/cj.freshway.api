package cjfw.wms.gwms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.gwms.dto.Gwms3PlApiReqDto;
import cjfw.wms.gwms.dto.GwmsDp3PlApiResDto;
import cjfw.wms.gwms.dto.GwmsDpInplan3PlApiResDto;
import cjfw.wms.gwms.dto.GwmsSt3PlApiResDto;
import cjfw.wms.gwms.dto.GwmsWdInplan3PlApiResDto;
import cjfw.wms.st.service.StDailyOnhandQtyAPIIFService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.11.28
 * @description : 중계 API
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class Gwms3PLApiService {

    private final CommonDao commonDao;
    private final StDailyOnhandQtyAPIIFService stDailyOnhandQtyAPIIFService;
    private transient static final String SERVICEID_PREFIX = "gwms3PLApiService.";


    /**
     * @description : 입고 확정내역 조회 API
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.12.05 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<GwmsDp3PlApiResDto> get3PlDpData(HttpServletRequest request, Gwms3PlApiReqDto paramDto) {

        List<GwmsDp3PlApiResDto> list = new ArrayList<>();

        Optional<Map<String, String>> headers = stDailyOnhandQtyAPIIFService.checkRequestHeaders(request);
        
        if (headers.isPresent()) {
            list = commonDao.selectList(SERVICEID_PREFIX + "selectDpApiData", paramDto);
        } 

        return list;
    }
    
    /**
     * @description : 재고 현황 조회 API
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.12.05 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<GwmsSt3PlApiResDto> get3PlStData(HttpServletRequest request, Gwms3PlApiReqDto paramDto) {
    	
    	List<GwmsSt3PlApiResDto> list = new ArrayList<>();
    	
    	Optional<Map<String, String>> headers = stDailyOnhandQtyAPIIFService.checkRequestHeaders(request);
    	
    	if (headers.isPresent()) {
    		list = commonDao.selectList(SERVICEID_PREFIX + "selectStApiData", paramDto);
    	} 
    	
    	return list;
    }
    
    /**
     * @description : 입고진행 현황 조회 API
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.12.05 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<GwmsDpInplan3PlApiResDto> get3PlDpInplanData(HttpServletRequest request, Gwms3PlApiReqDto paramDto) {
    	
    	List<GwmsDpInplan3PlApiResDto> list = new ArrayList<>();
    	
    	Optional<Map<String, String>> headers = stDailyOnhandQtyAPIIFService.checkRequestHeaders(request);
    	
    	if (headers.isPresent()) {
    		list = commonDao.selectList(SERVICEID_PREFIX + "selectDpInplanApiData", paramDto);
    	} 
    	
    	return list;
    }
    
    /**
     * @description : 출고진행 현황 조회 API
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.12.05 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<GwmsWdInplan3PlApiResDto> get3PlWdInplanData(HttpServletRequest request, Gwms3PlApiReqDto paramDto) {
    	
    	List<GwmsWdInplan3PlApiResDto> list = new ArrayList<>();
    	
    	Optional<Map<String, String>> headers = stDailyOnhandQtyAPIIFService.checkRequestHeaders(request);
    	
    	if (headers.isPresent()) {
    		list = commonDao.selectList(SERVICEID_PREFIX + "selectWdInplanApiData", paramDto);
    	} 
    	
    	return list;
    }
}
