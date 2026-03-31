package cjfw.wms.etcApi.service;

import cjfw.core.dataaccess.CommonDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONArray;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : yewon.kim (yewon.kim9@cj.net)
 * @date : 2026.01.25
 * @description : OFN API > 배송정보조회.
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.01.25 yewon.kim (yewon.kim9@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OfnApiService {

	private final CommonDao commonDao;

    /**
     * 배송정보 조회
     **/
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectLocList(Map<String, Object> inParams) {
		Map<String, Object> outParams = new HashMap<>();
        Map<String, Object> dataSet   = new HashMap<String, Object>();
        List<HashMap<String, Object>> rslt;
        Map<String, Object> dataMap;

        try {
            dataMap = convertKeysToUpperCase((HashMap<String, Object>) inParams.get("data"));
            dataMap.put("STORERKEY", "FW00");

            // 필수값 체크
            if ( dataMap.get("SKUS") != null ) {
                JSONArray jsonArray = new JSONArray(dataMap.get("SKUS").toString());
                String[] result = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {
                    result[i] = jsonArray.getString(i);
                }

                dataMap.put("MULTI_SKU", result);
            }else{
                dataSet.put("err_cd", "E");
                dataSet.put("err_msg_ctt", "SKUS가 NULL입니다.");
                outParams.put("ds_list", dataSet);
                return outParams;
            }

            System.out.println(dataMap.entrySet());
            rslt = commonDao.selectList("ofnApiService.selectLocList", (Object) dataMap);

            dataSet.put("err_cd", "S");
            dataSet.put("err_msg_ctt", "");
            dataSet.put("data", listKeyChangeLower(rslt));

        } catch (Exception e) {
            dataSet.put("err_cd", "E");
            dataSet.put("err_msg_ctt", e.getMessage());
        }
        outParams.put("ds_list", dataSet);

        return outParams;
    }

    public List<Map<String,Object>> listKeyChangeLower(List<HashMap<String, Object>> rslt) {
        if(null == rslt) {
            return null;
        }
        List<Map<String,Object>> newList = new ArrayList<Map<String,Object>>();

        for (int i = 0; i < rslt.size(); i++) {

            HashMap<String, Object> tm = new HashMap<String, Object>(rslt.get(i));
            Iterator<String> iteratorKey = tm.keySet().iterator();
            Map<String,Object>  newMap = new HashMap<String,Object>();

            while (iteratorKey.hasNext()) {
                String key = iteratorKey.next();
                newMap.put(key.toLowerCase(), tm.get(key));
            }
            newList.add(newMap);
        }

        return newList;
    }

    private Map<String, Object> convertKeysToUpperCase(Map<String, Object> originalMap){
        Map<String, Object> result = new HashMap<String, Object>();

        for (Map.Entry<String, Object> entry : originalMap.entrySet()) {
            result.put(entry.getKey().toUpperCase(), entry.getValue());
        }

        return result;
    }
}