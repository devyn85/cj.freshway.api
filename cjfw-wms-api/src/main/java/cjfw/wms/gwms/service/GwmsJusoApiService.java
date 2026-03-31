package cjfw.wms.gwms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.gwms.dto.MsPostxpopReqDto;
import cjfw.wms.gwms.dto.MsPostxpopResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 김환수 (hwansoo.kim@cj.net)
 * @date : 2025.11.05
 * @description : 중계 API > 국가주소연계 API 마스터 물류 송신(WM001), 지번 물류 송신(WM002), 변경 물류 송신(WM003)
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.05 김환수 (hwansoo.kim@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GwmsJusoApiService {

	private final CommonDao commonDao;

	@SuppressWarnings("unchecked")
	public Map<String, Object> setJusoApiData(Map<String, Object> inParams) {
		Map<String, Object> outParams       = new HashMap<>();
		Map<String, Object> dataMap         = new HashMap<>();
		List<Map<String, Object>> dataList  = new ArrayList<>(); //juso_data_list:[]
		List<Map<String, Object>> dataList2 = new ArrayList<>(); //IF_GWWM_00x": []
		Map<String, Object> dataSet         = new HashMap<>();
        Map<String, Object> paramMap        = new HashMap<>();
		String ifId = null; //WM001,WM002,WM003분기용도
    	String userid  = this.getClass().getSimpleName();
    	Integer loopCnt = 0;
		List<Map<String, Object>> rslt = new ArrayList<>();

		MsPostxpopReqDto popReqDto = new MsPostxpopReqDto();

		//SonarQube조치용도
		String rtnData  = "data";
		String rtnXstat = "XSTAT";
		String rtnXmsgs = "XMSGS";

		try {
			// 1) 입력 파싱 (null/형 안전)
			if (inParams != null && inParams.get("data") != null) {
				Object dm   = inParams.get("data");
				
				if (dm instanceof Map) {
					dataMap = (Map<String, Object>) dm;
					ifId = String.valueOf(dataMap.get("if_id"));
					Object dl = dataMap.get("juso_data_list");
					if (dl instanceof List) {
						dataList = (List<Map<String, Object>>) dl;
					}
				}

				// 2) 리스트 루프
				if (!dataList.isEmpty()) {
                    for (Map<String, Object> rowLvl01 : dataList) {
                        if (rowLvl01 == null) continue;

                        Object rowLvl02 = rowLvl01.get(ifId);
                        if (!(rowLvl02 instanceof List)) continue;
                        
                        dataList2 = (List<Map<String, Object>>) rowLvl02;

						for (Map<String, Object> ifIdMap : dataList2) {
	                        if (ifIdMap == null) continue;

	                        //공통컬럼
                        	paramMap.put("T_REG_DT"   , GwmsUtils.val(ifIdMap, "t_reg_dt"   ));
							paramMap.put("SEND_YN"    , GwmsUtils.val(ifIdMap, "send_yn"    ));
                        	paramMap.put("CTP_KOR_NM" , GwmsUtils.val(ifIdMap, "ctp_kor_nm" ));
                        	paramMap.put("T_TRS_ID"   , GwmsUtils.val(ifIdMap, "t_trs_id"   ));
                        	paramMap.put("EMD_KOR_NM" , GwmsUtils.val(ifIdMap, "emd_kor_nm" ));
                        	paramMap.put("SIG_KOR_NM" , GwmsUtils.val(ifIdMap, "sig_kor_nm" ));

							// 3) 파라미터 맵 구성
	                        if (ifId.equals("IF_GWWM_001")) {	//국가주문연계 API MST 데이터 물류 테이블에 적재
	                        	String zipcode = (String) GwmsUtils.val(ifIdMap, "zipcode");

	                        	paramMap.put("ZIPCODE"    , zipcode);
	                        	paramMap.put("LI_KOR_NM"  , GwmsUtils.val(ifIdMap, "li_kor_nm"  ));
	                        	paramMap.put("BUNJI"      , GwmsUtils.val(ifIdMap, "bunji"      ));
	                        	paramMap.put("APT_BUL_GB" , GwmsUtils.val(ifIdMap, "apt_bul_gb" ));
	                        	paramMap.put("MOUNT_YN"   , GwmsUtils.val(ifIdMap, "mount_yn"   ));
	                        	paramMap.put("HO"         , GwmsUtils.val(ifIdMap, "ho"         ));
	                        	paramMap.put("BULD_SE_CD" , GwmsUtils.val(ifIdMap, "buld_se_cd" ));
	                        	paramMap.put("ADR_MNG_NO" , GwmsUtils.val(ifIdMap, "adr_mng_no" ));
	                        	paramMap.put("MVMN_RES_CD", GwmsUtils.val(ifIdMap, "mvmn_res_cd"));
	                        	paramMap.put("BJDONG_CD"  , GwmsUtils.val(ifIdMap, "bjdong_cd"  ));
	                        	paramMap.put("EFFECT_DE"  , GwmsUtils.val(ifIdMap, "effect_de"  ));
	                        	paramMap.put("BULD_MNNM"  , GwmsUtils.val(ifIdMap, "buld_mnnm"  ));
	                        	paramMap.put("RN"         , GwmsUtils.val(ifIdMap, "rn"         ));
	                        	paramMap.put("BULD_SLNO"  , GwmsUtils.val(ifIdMap, "buld_slno"  ));
	                        	paramMap.put("RN_CD"      , GwmsUtils.val(ifIdMap, "rn_cd"      ));
								commonDao.insert("gwmsJusoApiService.insertJusoApiMstMng", paramMap);
								commonDao.insert("gwmsJusoApiService.insertJusoApiMstMngPopRequest", paramMap);

								// 신규로 넘어온 우편번호가 디폴트 세팅할 정보가 존재하면 해당정보로 POP를 생성하고 I/F 테이블에 적재한다.
								popReqDto.setZipcode(zipcode);
								popReqDto.setUserid(userid);
								List<MsPostxpopResDto> popList = commonDao.selectList("gwmsJusoApiService.getMappingListApply", popReqDto);
								for (MsPostxpopResDto popResDto : popList) {
									commonDao.insert("gwmsJusoApiService.insertMappingListApply", popResDto);
									commonDao.insert("gwmsJusoApiService.insertIFPostxPop"      , popResDto);
								}

	                        } else if (ifId.equals("IF_GWWM_002")) {	//국가주문연계 API 지번 데이터 물류 테이블에 적재
								paramMap.put("BUNJI"      , GwmsUtils.val(ifIdMap, "bunji"      ));
								paramMap.put("MOUNT_YN"   , GwmsUtils.val(ifIdMap, "mount_yn"   ));
								paramMap.put("HO"         , GwmsUtils.val(ifIdMap, "ho"         ));
								paramMap.put("BULD_SE_CD" , GwmsUtils.val(ifIdMap, "buld_se_cd" ));
								paramMap.put("ADR_MNG_NO" , GwmsUtils.val(ifIdMap, "adr_mng_no" ));
								paramMap.put("MVMN_RES_CD", GwmsUtils.val(ifIdMap, "mvmn_res_cd"));
								paramMap.put("BJDONG_CD"  , GwmsUtils.val(ifIdMap, "bjdong_cd"  ));
								paramMap.put("BULD_MNNM"  , GwmsUtils.val(ifIdMap, "buld_mnnm"  ));
								paramMap.put("BULD_SLNO"  , GwmsUtils.val(ifIdMap, "buld_slno"  ));
								paramMap.put("RN_CD"      , GwmsUtils.val(ifIdMap, "rn_cd"      ));
								commonDao.insert("gwmsJusoApiService.insertJusoApiLnbrMng", paramMap);

	                        } else if (ifId.equals("IF_GWWM_003")) {	//국가주문연계 API 변경 데이터 물류 테이블에 적재
	                        	paramMap.put("CHG_HIST_INFO"    , GwmsUtils.val(ifIdMap, "chg_hist_info"    ));
	                        	paramMap.put("ROAD_END_BSI_NO"  , GwmsUtils.val(ifIdMap, "road_end_bsi_no"  ));
	                        	paramMap.put("USE_YN"           , GwmsUtils.val(ifIdMap, "use_yn"           ));
	                        	paramMap.put("ALWNC_RESN"       , GwmsUtils.val(ifIdMap, "alwnc_resn"       ));
	                        	paramMap.put("ENG_RN"           , GwmsUtils.val(ifIdMap, "eng_rn"           ));
	                        	paramMap.put("EMD_NO"           , GwmsUtils.val(ifIdMap, "emd_no"           ));
	                        	paramMap.put("EMD_CD"           , GwmsUtils.val(ifIdMap, "emd_cd"           ));
	                        	paramMap.put("CTP_ENG_NM"       , GwmsUtils.val(ifIdMap, "ctp_eng_nm"       ));
	                        	paramMap.put("EMD_GB"           , GwmsUtils.val(ifIdMap, "emd_gb"           ));
	                        	paramMap.put("EMD_ENG_NM"       , GwmsUtils.val(ifIdMap, "emd_eng_nm"       ));
	                        	paramMap.put("EFFECT_DE"        , GwmsUtils.val(ifIdMap, "effect_de"        ));
	                        	paramMap.put("SIG_CD"           , GwmsUtils.val(ifIdMap, "sig_cd"           ));
	                        	paramMap.put("ROAD_START_BSI_NO", GwmsUtils.val(ifIdMap, "road_start_bsi_no"));
	                        	paramMap.put("RN"               , GwmsUtils.val(ifIdMap, "rn"               ));
	                        	paramMap.put("RN_NO"            , GwmsUtils.val(ifIdMap, "rn_no"            ));
	                        	paramMap.put("SIG_ENG_NM"       , GwmsUtils.val(ifIdMap, "sig_eng_nm"       ));
								commonDao.insert("gwmsJusoApiService.insertJusoApiChgMng", paramMap);
	                        }	                        
	                        loopCnt++;
						}	
                    }
				}
				dataSet.put(rtnXstat, "S");
			} else {
				dataSet.put(rtnData, rslt);
				dataSet.put(rtnXstat, "E");
				dataSet.put(rtnXmsgs, "null");
			}
		} catch (Exception e) {
			dataSet.put(rtnData, rslt);
			dataSet.put(rtnXstat, "E");
			dataSet.put(rtnXmsgs, e.getMessage());
		}
		outParams.put("ds_list", dataSet);

		// API LOG 기록(MG_APILOG)
    	paramMap.put("AVC_REQUESTBODY"    , ifId);
    	paramMap.put("AVC_RESPONSEBODY"   , "Loop CNT:"+loopCnt+", RESPONSE:"+convChar(GwmsUtils.toJsonString(outParams))); //outParams.toString()
        paramMap.put("AVC_STATUS"         , convChar((String)dataSet.get(rtnXstat)));
        paramMap.put("AVC_ERRORMESSAGE"   , convChar((String)dataSet.get(rtnXmsgs)));
        paramMap.put("AVC_ERRORSTACKTRACE", convChar(inParams.toString()));
        paramMap.put("AVC_WORKER"         , userid);
		commonDao.selectOne("GwmsCommonService.insertApiLog", paramMap);
		
		return outParams;
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