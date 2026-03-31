package cjfw.wms.etcApi.service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.gwms.service.GwmsUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nets.sso.agent.web.common.xml.Config;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : yewon.kim (yewon.kim9@cj.net)
 * @date : 2026.01.25
 * @description : CRM API > 배송정보조회. OFN, CRM 배송정보 입력. 일별메모 조회. OFN, CRM 일별메모 입력
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.20 yewon.kim (yewon.kim9@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CrmApiService {

	private final CommonDao commonDao;

    /**
     * 배송정보 조회
     **/
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectCustDlvInfo(Map<String, Object> inParams) {
		Map<String, Object> outParams = new HashMap<>();
		Map<String, Object> dataSet   = new HashMap<String, Object>();
        Map<String, Object> dataMap;
        List<HashMap<String, Object>> rslt;

        try {
            // 대소문자 변환.
            dataMap = convertKeysToUpperCase((HashMap<String, Object>) inParams.get("data"));
            dataMap.put("STORERKEY", "FW00");

            // 필수값 체크
            if ("".equals(dataMap.get("CUSTKEY")) || dataMap.get("CUSTKEY") == null) {
                dataSet.put("err_cd", "E");
                dataSet.put("err_msg_ctt", "CUSTKEY가 NULL입니다.");
                outParams.put("ds_list", dataSet);
                return outParams;
            }
            rslt = commonDao.selectList("crmApiService.selectCustDlvInfo", (Object) dataMap);

            dataSet.put("err_cd", "S");
            dataSet.put("err_msg_ctt", "");
            dataSet.put("data", rslt);
		} catch (Exception e) {
            dataSet.put("err_cd", "E");
            dataSet.put("err_msg_ctt", e.getMessage()); // 실패 메시지 리스트
        }
		outParams.put("ds_list", dataSet);
		return outParams;
	}

    /**
     * OFN, CRM 배송정보 입력
     **/
    @SuppressWarnings("unchecked")
    public Map<String, Object> insertCustDlvInfo(Map<String, Object> inParams) {
        Map<String, Object>          outParams    = new HashMap<>();
        Map<String, Object>          dataSet      = new HashMap<String, Object>();
        Map<String, Object>          dataMap;
        List<HashMap<String,Object>> fileList;

        try {
            if(null != inParams.get("data")) {
                dataMap = convertKeysToUpperCase((HashMap<String, Object>) inParams.get("data"));
                String sourceSystem = dataMap.get("SOURCESYSTEM") == null ? "CRM" : dataMap.get("SOURCESYSTEM").toString();

                dataMap.put("STORERKEY", "FW00");
                dataMap.put("STATUS", "01");
                dataMap.put("SOURCE_SYSTEM", sourceSystem);
                dataMap.put("ADDWHO", sourceSystem);
                dataMap.put("EDITWHO", sourceSystem);

                commonDao.insert("crmApiService.insertCustDlvInfo", (Object) dataMap);

                fileList = (ArrayList<HashMap<String, Object>>) dataMap.get("ATTACHFILES");
                for(Map<String, Object> map : fileList){
                    Map<String, Object> paramMap = new HashMap<String, Object>();

                    String strFileName = (String) map.get("FileName");
                    String strFileExtension = "";

                    int dot_idx = strFileName.lastIndexOf('.');
                    if(dot_idx > -1){
                        //strFileExtension = strFileName.substring(dot_idx+1, strFileName.length());
                        strFileExtension = strFileName.substring(dot_idx+1);
                    }

                    paramMap.put("ISSUENO", dataMap.get("CUSTKEY"));
                    paramMap.put("DOC_TYPE", "100");
                    paramMap.put("TYPE", "");
                    paramMap.put("DOC_NAME", "CRMDLVINFO_FILE");
                    paramMap.put("FILE_NAME", map.get("FileName"));
                    paramMap.put("FILE_EXTENSION", strFileExtension);
                    paramMap.put("UPLOAD_HASH_ID", map.get("Type"));
                    paramMap.put("UPLOAD_RES_DOC_ID", map.get("EdmsFileId"));
                    paramMap.put("UPLOAD_WORKPLACE_ID", Config.getString("edms.workPlaceId"));
                    paramMap.put("STATUS", map.get("FileStatus"));
                    paramMap.put("SOURCE_SYSTEM", sourceSystem);

                    commonDao.insert("crmApiService.insertCustDlvInfoFile", (Object) paramMap);
                }
                dataSet.put("err_cd", "S");
                dataSet.put("err_msg_ctt", "");
            }else{
                dataSet.put("err_cd", "E");
                dataSet.put("err_msg_ctt", "null");
            }
        } catch (DataAccessException e) {
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                String oraMsg = ((SQLException) root).getMessage();
                dataSet.put("err_cd", "E");
                dataSet.put("err_msg_ctt", oraMsg);
            } else {
                dataSet.put("err_cd", "E");
                dataSet.put("err_msg_ctt", e.getMessage());
            }
        } catch(Exception e) {
            dataSet.put("err_cd", "E");
            dataSet.put("err_msg_ctt", e.getMessage());
        }
        outParams.put("ds_list", dataSet);
        return outParams;
    }

    /**
     * 일별메모 조회
     **/
    public Map<String, Object> selectDailyMemo(Map<String, Object> inParams) {
        Map<String, Object> outParams       = new HashMap<>();
        Map<String, Object> dataSet         = new HashMap<String, Object>();
        List<HashMap<String, Object>> rslt  = new ArrayList<HashMap<String, Object>>();
        Map<String, Object> dataMap;

        try {
            dataMap = convertKeysToUpperCase((HashMap<String, Object>) inParams.get("data"));
            dataMap.put("STORERKEY", "FW00");

            // 필수값 체크
            if ("".equals(dataMap.get("MEMOID")) || dataMap.get("MEMOID") == null) {
                dataSet.put("err_cd", "E");
                dataSet.put("err_msg_ctt", "MEMOID가 NULL입니다.");
                outParams.put("ds_list", dataSet);
                return outParams;
            }

            rslt = commonDao.selectList("crmApiService.selectDailyMemo", (Object) dataMap);

            dataSet.put("err_cd", "S");
            dataSet.put("err_msg_ctt", "");
            dataSet.put("data", rslt);
        } catch (Exception e) {
            dataSet.put("err_cd", "E");
            dataSet.put("err_msg_ctt", e.getMessage());
        }
        outParams.put("ds_list", dataSet);

        return outParams;
    }

    /**
     * OFN, CRM 일별메모 입력
     **/
    public Map<String, Object> insertDailyMemo(Map<String, Object> inParams) {
        Map<String, Object>          outParams    = new HashMap<>();
        Map<String, Object>          dataSet      = new HashMap<String, Object>();
        Map<String, Object>          dataMap;
        List<HashMap<String,Object>> fileList;
        List<HashMap<String,Object>> keyList;
        String issueNo, memoLevel    = "";

        try {
            if(null != inParams.get("data")) {
                dataMap = convertKeysToUpperCase((HashMap<String, Object>) inParams.get("data"));
                String sourceSystem = dataMap.get("SOURCESYSTEM") == null ? "CRM" : dataMap.get("SOURCESYSTEM").toString();

                dataMap.put("STORERKEY", "FW00");
                dataMap.put("SOURCE_SYSTEM", sourceSystem);
                dataMap.put("ADDWHO", sourceSystem);
                dataMap.put("EDITWHO", sourceSystem);

                //상태값
                memoLevel = dataMap.get("MEMOLEVEL").toString();
                if (memoLevel.equals("M")){
                    dataMap.put("STATUS", "01");
                } else {
                    dataMap.put("STATUS", "03");
                }

                fileList = (ArrayList<HashMap<String, Object>>) dataMap.get("ATTACHFILES");
                if(dataMap.get("MEMODATE") == null){
                    dataMap.put("MEMODATE", new Date());
                }

                commonDao.insert("crmApiService.insertDailyMemo", (Object) dataMap);
                if (memoLevel.equals("C")){
                    commonDao.update("crmApiService.updateCrmMemoStatus", (Object) dataMap);
                }

                // 일별메모 Serialkey 추출
                keyList = commonDao.selectList("crmApiService.selectDailyMemoKey", (Object) dataMap);
                issueNo = keyList.get(0).get("SERIALKEY").toString();

                // File Data insert
                for(Map<String, Object> map : fileList){
                    Map<String, Object> paramMap = new HashMap<String, Object>();

                    String strFileName = (String) map.get("FileName");
                    String strFileExtension = "";

                    int dot_idx = strFileName.lastIndexOf('.');
                    if(dot_idx > -1){
                        //strFileExtension = strFileName.substring(dot_idx+1, strFileName.length());
                        strFileExtension = strFileName.substring(dot_idx+1);
                    }
                    paramMap.put("ISSUENO", issueNo);
                    paramMap.put("DOCTYPE", "100");
                    paramMap.put("TYPE", "");
                    paramMap.put("DOC_NAME", "CRMMEMO_FILE");
                    paramMap.put("FILE_NAME", map.get("FileName"));
                    paramMap.put("FILE_EXTENSION", strFileExtension);
                    paramMap.put("UPLOAD_HASH_ID", map.get("Type"));
                    paramMap.put("UPLOAD_RES_DOC_ID", map.get("EdmsFileId"));
                    paramMap.put("UPLOAD_WORKPLACE_ID", Config.getString("edms.workPlaceId"));

                    commonDao.insert("crmApiService.insertDailyMemoFile", (Object) paramMap);
                }

                dataSet.put("err_cd", "S");
                dataSet.put("err_msg_ctt", "");
            }else{
                dataSet.put("err_cd", "E");
                dataSet.put("err_msg_ctt", "null");
            }
        } catch (DataAccessException e) {
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                String oraMsg = ((SQLException) root).getMessage();
                dataSet.put("err_cd", "E");
                dataSet.put("err_msg_ctt", oraMsg);
            } else {
                dataSet.put("err_cd", "E");
                dataSet.put("err_msg_ctt", e.getMessage());
            }
        } catch(Exception e) {
            dataSet.put("err_cd", "E");
            dataSet.put("err_msg_ctt", e.getMessage());
        }
        outParams.put("ds_list", dataSet);
        return outParams;
    }


    private Map<String, Object> convertKeysToUpperCase(Map<String, Object> originalMap){
        Map<String, Object> result = new HashMap<String, Object>();

        for (Map.Entry<String, Object> entry : originalMap.entrySet()) {
            result.put(entry.getKey().toUpperCase(), entry.getValue());
        }

        return result;
    }
}