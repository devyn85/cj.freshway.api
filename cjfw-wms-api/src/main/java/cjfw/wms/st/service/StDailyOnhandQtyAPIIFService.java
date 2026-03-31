package cjfw.wms.st.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.DateUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.st.dto.StDailyOnhandQtyAPIIFDetailReqDto;
import cjfw.wms.st.dto.StDailyOnhandQtyAPIIFReqDto;
import cjfw.wms.st.entity.StDailyOnhandQtyAPIIFEntity;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.09.04 
 * @description : 외부창고 API 재고현황 인터페이스 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.04    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StDailyOnhandQtyAPIIFService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "stDailyOnhandQtyAPIIFService.";	
	
	private transient static final String MSG_SUC_CODE = "0000";
	private transient static final String MSG_SUC_MESSAGE = "Success";
	private transient static final String MSG_ERR_INTERFACE_ID = "0001";
	private transient static final String MSG_ERR_INTERFACE_AUTH_KEY = "0002";
	private transient static final String MSG_ERR_FAIL_COUNT = "0003";
	private transient static final String MSG_ERR_FAIL_SAVE = "0004";
	private transient static final String MSG_ERR_FAIL_OTHER = "9999";
	
	private transient static final String EX_DCCODE = "2170";
	private transient static final String IF_ID = "ID228";
	private transient static final String USER_ID = "LOGISONEIF";
	private transient static final String HEADER_INTERFACE_ID = "interface_id";
	private transient static final String HEADER_INTERFACE_AUTH_KEY = "interface_auth_key";
	private transient static final String HEADER_INTERFACE_REQ_DT = "interface_req_dt";
	
	private transient static final String HEADER_INTERFACE_ID_VALUE = "WMSIF";
	private transient static final String HEADER_INTERFACE_AUTH_KEY_VALUE = "DuRcQFM3wrkgYCBJVOGrKvbKJhGN0jkU";
	private transient static final String OPCODE_VALUE = "ST0001";
		
	private final CommonDao commonDao;
	
	private final UserContext userContext;
	
	public Optional<Map<String, String>> checkRequestHeaders(HttpServletRequest request) {
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("ERROR_CODE", "");
        resultMap.put("ERROR_MESSAGE", "");
        
        String value = request.getHeader(HEADER_INTERFACE_ID);
        if (value != null) {
            if (!HEADER_INTERFACE_ID_VALUE.equals(value)) {
                resultMap.put("ERROR_CODE", MSG_ERR_INTERFACE_ID);
                resultMap.put("ERROR_MESSAGE", "Invalid Interface ID");
            }
        } else {
            resultMap.put("ERROR_CODE", MSG_ERR_INTERFACE_ID);
            resultMap.put("ERROR_MESSAGE", "Invalid Interface ID");
        }
        
        value = request.getHeader(HEADER_INTERFACE_AUTH_KEY);
        if (value != null) {
            if (!HEADER_INTERFACE_AUTH_KEY_VALUE.equals(value)) {
                resultMap.put("ERROR_CODE", MSG_ERR_INTERFACE_AUTH_KEY);
                resultMap.put("ERROR_MESSAGE", "Invalid Interface Auth Key");
            }
        } else {
            resultMap.put("ERROR_CODE", MSG_ERR_INTERFACE_AUTH_KEY);
            resultMap.put("ERROR_MESSAGE", "Invalid Interface Auth Key");
        }
        
//        Enumeration <String> headerNames = request.getHeaderNames();
//        
//        while (headerNames.hasMoreElements()) {
//            String name = headerNames.nextElement();      // 키
//            String value = request.getHeader(name);       // 값
//            log.info(name + " = " + value);
//            
//            if (HEADER_INTERFACE_ID.equals(name)) {
//                if (!HEADER_INTERFACE_ID_VALUE.equals(value)) {
//                    resultMap.put("ERROR_CODE", "0001");
//                    resultMap.put("ERROR_MESSAGE", "Invalid Interface ID");
//                }
//            }
//            else if (HEADER_INTERFACE_AUTH_KEY.equals(name)) {
//                if (!HEADER_INTERFACE_AUTH_KEY_VALUE.equals(value)) {
//                    resultMap.put("ERROR_CODE", "0002");
//                    resultMap.put("ERROR_MESSAGE", "Invalid Interface Auth Key");
//                }
//            }
//        }
        
        return Optional.of(resultMap);
    }

	
	/**
	 * @description : 외부창고 API 재고현황 인터페이스
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.03    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public ResponseEntity<Map<String, String>> saveIFMasterList(HttpServletRequest request, StDailyOnhandQtyAPIIFReqDto paramDto) {
	    Map<String, String> result = new HashMap<>();
	    result.put("system_type_cd", HEADER_INTERFACE_ID_VALUE);
	    result.put("opcode", OPCODE_VALUE);
	    result.put("prc_count", "0");
	    result.put("result_code", MSG_SUC_CODE);
        result.put("result_message", MSG_SUC_MESSAGE);
        
	    Optional<Map<String, String>> headers = checkRequestHeaders(request);
	    
	    Integer prcCount = 0;
	    
	    if (headers.isPresent()) {
            Map<String, String> headerMap = headers.get();
            String errorCode = headerMap.get("ERROR_CODE");
            if (!"".equals(errorCode)) {
                log.info("StDailyOnhandQtyAPIIFService.saveIFMasterList.headerCheckError >>> : " + headerMap);
                result.put("result_code", headerMap.get("ERROR_CODE"));
                result.put("result_message", headerMap.get("ERROR_MESSAGE"));
            }
        
    	    StDailyOnhandQtyAPIIFReqDto reqDto = ModelMapperUtil.map(paramDto, userContext,  StDailyOnhandQtyAPIIFReqDto.class);
    	    log.info("StDailyOnhandQtyAPIIFService.saveIFMasterList.reqDto >>> : " + reqDto);
    	    
    	    Integer reqCount = reqDto.getReq_count();
    	    Integer dataCount = reqDto.getData().size();
    	    
    	    if (reqCount == null || reqCount.intValue() != dataCount.intValue()) {
    	        log.info("StDailyOnhandQtyAPIIFService.saveIFMasterList.countMismatchError >>> : reqCount=" + reqCount + ", dataCount=" + dataCount);
    	        result.put("result_code", MSG_ERR_FAIL_COUNT);
                result.put("result_message", "Invalid Request Count");
    	    }
    	    
    	    if (MSG_SUC_CODE.equals(result.get("result_code"))) {
        	    for (StDailyOnhandQtyAPIIFDetailReqDto dto : reqDto.getData()) {
        	        try {
            	        StDailyOnhandQtyAPIIFEntity entity = new StDailyOnhandQtyAPIIFEntity(); // ModelMapperUtil.map(dto, userContext, StDailyOnhandQtyAPIIFEntity.class);
            	        entity.setDccode(EX_DCCODE);
            	        entity.setOrganize(dto.getOrganize());
            	        entity.setSku(dto.getSku());
            	        entity.setSkuname(dto.getSkuname());
            	        entity.setUom(dto.getUom());
            	        entity.setConvserialno(dto.getConvserialno());
            	        entity.setWeight(dto.getWeight());
            	        entity.setStqty(dto.getStqty());
            	        entity.setQtyperpallet(dto.getQtyperpallet());
            	        entity.setPalletqty(dto.getPalletqty());
            	        entity.setUsebydate(dto.getUsebydate());
            	        entity.setFactorydate(dto.getFactorydate());
            	        entity.setPlaceoforigin(dto.getPlaceoforigin());
            	        entity.setUserId(USER_ID);
            	        entity.setIfId(IF_ID);
            	        
            	        commonDao.insert(SERVICEID_PREFIX + "saveIFMasterList", entity);
            	        
            	        prcCount++;
        	        } catch (Exception e) {
                        log.error("StDailyOnhandQtyAPIIFService.saveIFMasterList.saveError >>> : ", e);
                        result.put("result_code", MSG_ERR_FAIL_SAVE);
                        result.put("result_message", "Failed to Save Data");
                    }
        	    }
        	    
        	    result.put("prc_count", prcCount.toString());
    	    }
	    }
	    
	    
	    String resDatetime = DateUtil.getDateToStringPatern("yyyyMMddHHmmss");
	    
	    return ResponseEntity.ok()
                .header(HEADER_INTERFACE_ID, HEADER_INTERFACE_ID_VALUE)
                .header(HEADER_INTERFACE_AUTH_KEY, HEADER_INTERFACE_AUTH_KEY_VALUE)
                .header(HEADER_INTERFACE_REQ_DT, resDatetime)
                .body(result);
	}
	
	/**
     * @description : 인터페이스 데이터를 본 테이블에 저장
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.03    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String saveMasterList() {
        Map<String, String> paramSetterMap = new HashMap<>();
        paramSetterMap.put("AVC_IFID", IF_ID);
        paramSetterMap.put("AVC_IFRESULT", "");
        commonDao.selectOne(SERVICEID_PREFIX + "saveStExdcApiStock", paramSetterMap);

        return MSG_SUC_CODE;
    }

}
