package cjfw.wms.gwms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.gwms.dto.HjdongDccodeReqDto;
import cjfw.wms.gwms.dto.HjdongDccodeResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 김환수 (hwansoo.kim@cj.net)
 * @date : 2025.11.20
 * @description : 중계 API > 행정동코드에 대한 주 출고센터 제공 API (TO MDM)
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.20 (hwansoo.kim@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GwmsHjdongDccodeApiService {

	private final CommonDao commonDao;

	@SuppressWarnings("unchecked")
	public Map<String, Object> getDccodeApiData(Map<String, Object> inParams) {
		//SonarQube조치용도
		String itmIfId   = "Interface_id";
		String itmRsltCd = "result_code";
		String itmErrMsg = "error_message";
		
		// Init. Return Map (인터페이스 규격서 내용)
		Map<String, Object> rtnMap = new LinkedHashMap<>(); //(inParams)
		rtnMap.put(itmIfId             , inParams.get(itmIfId));		
		rtnMap.put("Interface_auth_key", inParams.get("Interface_auth_key"));	
		rtnMap.put("Interface_req_dt"  , inParams.get("Interface_req_dt"));
		rtnMap.put("Interface_tracekey", inParams.get("Interface_tracekey"));
		rtnMap.put("system_type_cd"    , inParams.get("system_type_cd"));
		rtnMap.put("opcode"            , inParams.get("opcode"));
		//
		rtnMap.put(itmRsltCd, "-29999");		
		rtnMap.put("result_message", new ArrayList<>());		
		rtnMap.put(itmErrMsg, "내부 오류");		
		//log.info("*******inParams**rtnMap*******: ▦{}\n▦{}", inParams, rtnMap)
		
		long rowCnt = 0;
		
		try {
			//SELECT...
			HjdongDccodeReqDto dccodeReqDto = new HjdongDccodeReqDto();
			dccodeReqDto.setHjdongCd((String)inParams.get("pv_hjdcd"));
			dccodeReqDto.setOrdgrp((String)inParams.get("pv_otcogrpid"));

			List<HjdongDccodeResDto> dccodeList = commonDao.selectList("gwmsHjdongDccodeApiService.selectMainDccodeHjdong", dccodeReqDto);
			rowCnt = dccodeList.size();
			if (rowCnt == 1) { //1건만 정상임.
				rtnMap.put(itmRsltCd, "0");
				rtnMap.put(itmErrMsg, "성공");
			} else if (rowCnt == 0) {
				rtnMap.put(itmRsltCd, "-20001");
				rtnMap.put(itmErrMsg, "주소지 정보(행정동코드)에 맞는 출고센터 정보가 없습니다.\n물류팀 담당자에 문의하세요.");
			} else {
				rtnMap.put(itmRsltCd, "-20002");
				rtnMap.put(itmErrMsg, "주소지 정보(행정동코드)에 복수의 출고센터 정보가 존재합니다.\n물류팀 담당자에 문의하세요."); 
			}
			//result_message 리스트생성
			List<HjdongDccodeResDto> list = new ArrayList<>();
			for (HjdongDccodeResDto dto : dccodeList) {
				if (StringUtils.isEmpty(dto.getRmk())) {
					dto.setRmk("");
				}
				list.add(dto);
			}
			rtnMap.put("result_message", list);
			
        } catch (Exception e) {
			rtnMap.put(itmRsltCd, "-29999");
			rtnMap.put(itmErrMsg, "내부 오류");
		}

		// API LOG 기록(MG_APILOG)
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("AVC_URL"            , inParams.get("url"));
		paramMap.put("AVC_SOURCEIP"       , inParams.get("ip"));
		paramMap.put("AVC_REQUESTBODY"    , inParams.get(itmIfId)       +" | REQUEST:" +convChar(GwmsUtils.toJsonString(inParams))); //inParams.toString()
    	paramMap.put("AVC_RESPONSEBODY"   , "SELECT CNT:"+rowCnt        +" | RESPONSE:"+convChar(GwmsUtils.toJsonString(rtnMap)));   //rtnMap.toString()
        paramMap.put("AVC_STATUS"         , rtnMap.get(itmRsltCd));
        paramMap.put("AVC_ERRORMESSAGE"   , rtnMap.get(itmErrMsg));
        paramMap.put("AVC_ERRORSTACKTRACE", "");
        paramMap.put("AVC_WORKER"         , inParams.get("pv_req_emp_id"));
        //log.info("*******paramMap*******: ▦{}", paramMap)

        commonDao.selectOne("GwmsCommonService.insertApiLog", paramMap);

		return rtnMap;
	}

    /*
    * @description : 문자열에서 특수문자 치환 { => ^( , } => ^)
	*/
    public static String convChar(String pString) {
    	if (pString != null) {
    		return pString.replace("{", "^(").replace("}", "^)");
    	} else {
    	   	return pString;
    	}    			
    }	
}