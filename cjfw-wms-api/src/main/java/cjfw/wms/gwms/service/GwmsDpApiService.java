package cjfw.wms.gwms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : yewon.kim (yewon.kim9@cj.net)
 * @date : 2025.08.20
 * @description : 중계 API
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.20 yewon.kim (yewon.kim9@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GwmsDpApiService {

	private final CommonDao commonDao;

	/**
	 * 협력사 센터 입고 상태 수정
	 * @param inParams 입력 파라미터(Map)
	 * @return 처리 결과(Map)
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectDpApiData(Map<String, Object> inParams) {
		Map<String, Object> outParams = new HashMap<>();
		Map<String, Object> dataMap = new HashMap<>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		Map<String, Object> dataSet = new HashMap<>();
		Map<String, Object> ifIdMap;
		List<Map<String, Object>> rslt = new ArrayList<>();
		String requestMsg = "";

		try {
			// 1) 입력 파싱 (null/형 안전)
			if (inParams != null && inParams.get("data") != null) {
				Object dm = inParams.get("data");
				if (dm instanceof Map) {
					dataMap = (Map<String, Object>) dm;
					Object dl = dataMap.get("data_list");
					if (dl instanceof List) {
						dataList = (List<Map<String, Object>>) dl;
					}
				}

				// 2) 리스트 루프
				if (!dataList.isEmpty()) {
                    for (Map<String, Object> row : dataList) {
                        if (row == null) continue;

                        Object fs144 = row.get("FS144");
                        if (!(fs144 instanceof Map)) continue;

                        ifIdMap = (Map<String, Object>) fs144;
                        requestMsg = GwmsUtils.makeParam(ifIdMap);

                        Map<String, Object> paramMap = new HashMap<>();
                        paramMap.put("avc_SYSTEM", "WMSAPP"); //하드웨어에 종속적인 시스템구분 (ex: WMSAPP)
                        paramMap.put("avc_EXECUTEMODE", "");
                        paramMap.put("avc_COMMAND", "CONFIRM");
                        paramMap.put("avc_DCCODE", GwmsUtils.val(ifIdMap, "DCCODE"));
                        paramMap.put("avc_STORERKEY", "FW00");
                        paramMap.put("avc_ORGANIZE", "%");
                        paramMap.put("avc_AREA", "%");
                        paramMap.put("ai_SPID", "1000000001");
                        paramMap.put("avc_REQUESTCODE", "");
                        paramMap.put("avc_REQUESTMSG", requestMsg);
                        paramMap.put("avc_WORKER", "batch");
                        paramMap.put("PACKAGENAME", "SPDP_RECEIPT");

                        commonDao.selectOne("gwmsDpApiService.callProcExecute", paramMap);
                    }
				}
				dataSet.put("XSTAT", "S");
				dataSet.put("data", GwmsUtils.listKeyChangeLower(rslt));
			} else {
				dataSet.put("data", rslt);
				dataSet.put("XSTAT", "E");
				dataSet.put("XMSGS", "null");
			}
		} catch (Exception e) {
			dataSet.put("data", rslt);
			dataSet.put("XSTAT", "E");
			dataSet.put("XMSGS", e.getMessage());
		}
		outParams.put("ds_list", dataSet);
		return outParams;
	}

}