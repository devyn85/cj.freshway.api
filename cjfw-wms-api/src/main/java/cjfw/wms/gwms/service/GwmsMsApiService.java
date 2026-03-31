package cjfw.wms.gwms.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import cjfw.core.dataaccess.CommonDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : yewon.kim (yewon.kim9@cj.net)
 * @date : 2025.08.20
 * @description : 중계 API > 협력사 등록/수정, 상품 등록/수정
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.20 yewon.kim (yewon.kim9@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GwmsMsApiService {

	private final CommonDao commonDao;

	/**
	 * 협력사 등록
	 * <pre>
	 * inParams 예시:
	 * {
	 *   "data": {
	 *     "data_list": [
	 *       { "FS110": {
	 *           "vd_sn": "...",
	 *           "pv_vd_nm_loc": "...",
	 *           "rep_nm_loc": "...",
	 *           "pv_nat_cd": "...",
	 *           "pv_bank_info":   [ { "bank_cd":"...", "bank_acno":"..." } ],
	 *           "pv_manager_info":[ { "mgr_nm":"...", "mgr_tel":"..." } ],
	 *           "pv_csr_addr":    [ { "pv_adres_zipcode":"...", "pv_adres_addr1":"...", ... } ]
	 *       }} , ...
	 *     ]
	 *   }
	 * }
	 * </pre>
	 *
	 * @param inParams 입력 파라미터(Map)
	 * @return 처리 결과(Map)
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> insertMsCustApiData(Map<String, Object> inParams) {
		Map<String, Object> outParams      = new HashMap<>();
		Map<String, Object> dataMap        = new HashMap<>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		Map<String, Object> dataSet        = new HashMap<>();
		Map<String, Object> ifIdMap;
		List<Map<String, Object>> ifIdList1; // pv_bank_info
		List<Map<String, Object>> ifIdList2; // pv_manager_info
		List<Map<String, Object>> ifIdList3; // pv_csr_addr

		List<Map<String, Object>> rslt = new ArrayList<>();

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

                        Object fs110 = row.get("FS110");
                        if (!(fs110 instanceof Map)) continue;

                        ifIdMap = (Map<String, Object>) fs110;
                        ifIdList1 = GwmsUtils.castList(ifIdMap.get("pv_bank_info"));
                        ifIdList2 = GwmsUtils.castList(ifIdMap.get("pv_manager_info"));
                        ifIdList3 = GwmsUtils.castList(ifIdMap.get("pv_csr_addr"));

                        // 3) 파라미터 맵 구성
                        Map<String, Object> paramMap = new HashMap<>();
                        paramMap.put("STOREKEY", "FW00");
                        paramMap.put("CUSTKEY", GwmsUtils.val(ifIdMap, "vd_sn"));
                        paramMap.put("CUSTTYPE", "P");
                        paramMap.put("DESCRIPTION", GwmsUtils.val(ifIdMap, "pv_vd_nm_loc"));
                        paramMap.put("OWNER", GwmsUtils.val(ifIdMap, "rep_nm_loc"));
                        paramMap.put("COUNTRY", GwmsUtils.val(ifIdMap, "pv_nat_cd"));

                        // 주소(첫 원소 기준)
                        Map<String, Object> addr0 = GwmsUtils.first(ifIdList3);
                        paramMap.put("ZIPCODE", GwmsUtils.val(addr0, "pv_adres_zipcode"));
                        paramMap.put("ADDRESS1", GwmsUtils.val(addr0, "pv_adres_addr1"));
                        paramMap.put("ADDRESS2", GwmsUtils.val(addr0, "pv_adres_addr2"));
                        paramMap.put("ADDRESS3", GwmsUtils.val(addr0, "pv_adres_roadaddr1"));
                        paramMap.put("ADDRESS4", GwmsUtils.val(addr0, "pv_adres_roadaddr2"));
                        paramMap.put("VATADDRESS1", GwmsUtils.val(addr0, "pv_adres_addr1"));
                        paramMap.put("VATADDRESS2", GwmsUtils.val(addr0, "pv_adres_addr2"));
                        paramMap.put("VATADDRESS3", GwmsUtils.val(addr0, "pv_adres_roadaddr1"));
                        paramMap.put("VATADDRESS4", GwmsUtils.val(addr0, "pv_adres_roadaddr2"));


                        paramMap.put("PHONE1", GwmsUtils.val(ifIdMap, "pv_phone_no"));
                        paramMap.put("FAX", GwmsUtils.val(ifIdMap, "pv_fax_no"));
                        paramMap.put("VATNO", GwmsUtils.val(ifIdMap, "biz_reg_no"));
                        paramMap.put("VATOWNER", GwmsUtils.val(ifIdMap, "rep_nm_loc"));
                        paramMap.put("VATTYPE", GwmsUtils.val(ifIdMap, "bos"));
                        paramMap.put("VATNAME", GwmsUtils.val(ifIdMap, "pv_vd_nm_loc"));
                        paramMap.put("VATCATEGORY", GwmsUtils.val(ifIdMap, "tob"));
                        paramMap.put("VATPHONE", GwmsUtils.val(ifIdMap, "pv_phone_no"));
                        paramMap.put("VATFAX", GwmsUtils.val(ifIdMap, "pv_fax_no"));
                        paramMap.put("OTHER01", GwmsUtils.val(ifIdMap, "pay_cond"));
                        paramMap.put("INSTANCEID", GwmsUtils.val(ifIdMap, "pv_instanceid"));
                        paramMap.put("DLVCUSTKEY", GwmsUtils.val(ifIdMap, "vd_sn"));


                        // 은행/담당자(필요 시 확장)
                        Map<String, Object> bank0 = GwmsUtils.first(ifIdList1);
                        paramMap.put("OTHER02", GwmsUtils.val(bank0, "pay_terms_list"));

                        // 하드코딩되는값
                        paramMap.put("DEL_YN", "N");
                        paramMap.put("IF_ID", "MDM0130");
                        paramMap.put("IF_DESTINATION", "MDM");
                        paramMap.put("IF_FLAG", "N");

                        Map<String, Object> mgr0 = GwmsUtils.first(ifIdList2);
                        paramMap.put("EMPNAME", GwmsUtils.val(mgr0, "chr_nm"));
                        paramMap.put("EMPPHONE", GwmsUtils.val(mgr0, "chr_mobile_no"));
                        paramMap.put("PERSONTYPE", GwmsUtils.val(mgr0, "chr_cls"));

                        // 처리 결과 샘플 적재(필요 시 DB 결과로 대체)
                        commonDao.insert("gwmsMsApiService.insertIfMsCust", paramMap);
                        commonDao.insert("gwmsMsApiService.insertIfMsCustdlvinfo", paramMap);
                        commonDao.insert("gwmsMsApiService.insertIfMsCustperson", paramMap);
                    }

					//후속 프로시저 실행.
					Map<String, String> paramSetterMap = new HashMap<>();
					paramSetterMap.put("AVC_IFID", "MDM0130");
					paramSetterMap.put("AVC_IFRESULT", "");
					commonDao.selectOne("gwmsMsApiService.CallIfMsCust", paramSetterMap);
				}
				dataSet.put("XSTAT", "S");
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


	/**
	 * 협력사 수정
	 * @param inParams 입력 파라미터(Map)
	 * @return 처리 결과(Map)
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> updateMsCustApiData(Map<String, Object> inParams) {
		Map<String, Object> outParams      = new HashMap<>();
		Map<String, Object> dataMap        = new HashMap<>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		Map<String, Object> dataSet        = new HashMap<>();
		Map<String, Object> ifIdMap;
		List<Map<String, Object>> ifIdList1;
		List<Map<String, Object>> ifIdList2;
		List<Map<String, Object>> ifIdList3;

		List<Map<String, Object>> rslt = new ArrayList<>();

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

                        Object fs111 = row.get("FS111");
                        if (!(fs111 instanceof Map)) continue;

                        ifIdMap = (Map<String, Object>) fs111;
                        ifIdList1 = GwmsUtils.castList(ifIdMap.get("pv_bank_info"));
                        ifIdList2 = GwmsUtils.castList(ifIdMap.get("pv_manager_info"));
                        ifIdList3 = GwmsUtils.castList(ifIdMap.get("pv_csr_addr"));

                        // 3) 파라미터 맵 구성
                        Map<String, Object> paramMap = new HashMap<>();
                        paramMap.put("STOREKEY", "FW00");
                        paramMap.put("CUSTKEY", GwmsUtils.val(ifIdMap, "vd_sn"));
                        paramMap.put("CUSTTYPE", "P");
                        paramMap.put("DESCRIPTION", GwmsUtils.val(ifIdMap, "pv_vd_nm_loc"));
                        paramMap.put("OWNER", GwmsUtils.val(ifIdMap, "rep_nm_loc"));
                        paramMap.put("COUNTRY", GwmsUtils.val(ifIdMap, "pv_nat_cd"));

                        // 주소(첫 원소 기준)
                        Map<String, Object> addr0 = GwmsUtils.first(ifIdList3);
                        paramMap.put("ZIPCODE", GwmsUtils.val(addr0, "pv_adres_zipcode"));
                        paramMap.put("ADDRESS1", GwmsUtils.val(addr0, "pv_adres_addr1"));
                        paramMap.put("ADDRESS2", GwmsUtils.val(addr0, "pv_adres_addr2"));
                        paramMap.put("ADDRESS3", GwmsUtils.val(addr0, "pv_adres_roadaddr1"));
                        paramMap.put("ADDRESS4", GwmsUtils.val(addr0, "pv_adres_roadaddr2"));
                        paramMap.put("VATADDRESS1", GwmsUtils.val(addr0, "pv_adres_addr1"));
                        paramMap.put("VATADDRESS2", GwmsUtils.val(addr0, "pv_adres_addr2"));
                        paramMap.put("VATADDRESS3", GwmsUtils.val(addr0, "pv_adres_roadaddr1"));
                        paramMap.put("VATADDRESS4", GwmsUtils.val(addr0, "pv_adres_roadaddr2"));


                        paramMap.put("PHONE1", GwmsUtils.val(ifIdMap, "pv_phone_no"));
                        paramMap.put("FAX", GwmsUtils.val(ifIdMap, "pv_fax_no"));
                        paramMap.put("VATNO", GwmsUtils.val(ifIdMap, "biz_reg_no"));
                        paramMap.put("VATOWNER", GwmsUtils.val(ifIdMap, "rep_nm_loc"));
                        paramMap.put("VATTYPE", GwmsUtils.val(ifIdMap, "bos"));
                        paramMap.put("VATNAME", GwmsUtils.val(ifIdMap, "pv_vd_nm_loc"));
                        paramMap.put("VATCATEGORY", GwmsUtils.val(ifIdMap, "tob"));
                        paramMap.put("VATPHONE", GwmsUtils.val(ifIdMap, "pv_phone_no"));
                        paramMap.put("VATFAX", GwmsUtils.val(ifIdMap, "pv_fax_no"));
                        paramMap.put("OTHER01", GwmsUtils.val(ifIdMap, "pay_cond"));
                        paramMap.put("INSTANCEID", GwmsUtils.val(ifIdMap, "pv_instanceid"));
                        paramMap.put("DLVCUSTKEY", GwmsUtils.val(ifIdMap, "vd_sn"));


                        // 은행/담당자(필요 시 확장)
                        Map<String, Object> bank0 = GwmsUtils.first(ifIdList1);
                        paramMap.put("OTHER02", GwmsUtils.val(bank0, "pay_terms_list"));

                        // 하드코딩되는값
                        paramMap.put("DEL_YN", "N");
                        paramMap.put("IF_ID", "MDM0130");
                        paramMap.put("IF_DESTINATION", "MDM");
                        paramMap.put("IF_FLAG", "N");

                        Map<String, Object> mgr0 = GwmsUtils.first(ifIdList2);
                        paramMap.put("EMPNAME", GwmsUtils.val(mgr0, "chr_nm"));
                        paramMap.put("EMPPHONE", GwmsUtils.val(mgr0, "chr_mobile_no"));
                        paramMap.put("PERSONTYPE", GwmsUtils.val(mgr0, "chr_cls"));

                        // 처리 결과 샘플 적재(필요 시 DB 결과로 대체)
                        commonDao.insert("gwmsMsApiService.updateIfMsCust", paramMap);
                        commonDao.insert("gwmsMsApiService.updateIfMsCustdlvinfo", paramMap);
                        commonDao.insert("gwmsMsApiService.updateIfMsCustperson", paramMap);
                    }

					//후속 프로시저 실행.
					Map<String, String> paramSetterMap = new HashMap<>();
					paramSetterMap.put("AVC_IFID", "MDM0130");
					paramSetterMap.put("AVC_IFRESULT", "");
					commonDao.selectOne("gwmsMsApiService.CallIfMsCust", paramSetterMap);
				}
				dataSet.put("XSTAT", "S");
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


	/**
	 * 협력사 상품등록
	 * @param inParams 입력 파라미터(Map)
	 * @return 처리 결과(Map)
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> insertMsSkuApiData(Map<String, Object> inParams) throws JsonProcessingException {

		Map<String, Object> outParams      = new HashMap<>();
		Map<String, Object> dataMap        = new HashMap<>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		Map<String, Object> dataSet        = new HashMap<>();
		Map<String, Object> ifIdMap;

		List<Map<String, Object>> ifIdListM;
		List<Map<String, Object>> ifIdList1;
		List<Map<String, Object>> ifIdList2;
		List<Map<String, Object>> ifIdList3;
		List<Map<String, Object>> ifIdList4;
		List<Map<String, Object>> ifIdList5;
		List<Map<String, Object>> ifIdList6;
		List<Map<String, Object>> ifIdList7;
		List<Map<String, Object>> ifIdList8;
		List<Map<String, Object>> ifIdList9;
		List<Map<String, Object>> ifIdList10;
		List<Map<String, Object>> ifIdList11;

		List<Map<String, Object>> rslt = new ArrayList<>();

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

                        Object fs120 = row.get("FS120");
                        if (!(fs120 instanceof Map)) continue;

                        ifIdMap = (Map<String, Object>) fs120;
                        ifIdListM = GwmsUtils.castList(ifIdMap.get("pv_product_info"));

                        // 3) 파라미터 맵 구성
                        Map<String, Object> paramMap = new HashMap<>();
                        paramMap.put("STORERKEY", "FW00");
                        paramMap.put("INSTANCEID", GwmsUtils.val(ifIdMap, "pv_instanceid"));

                        Map<String, Object> listM = GwmsUtils.first(ifIdListM);
                        paramMap.put("SKU", GwmsUtils.val(listM, "gd_no"));
                        paramMap.put("DESCRIPTION", GwmsUtils.val(listM, "pv_maktx"));
                        paramMap.put("ETC_DESCR1", GwmsUtils.val(listM, "pv_maktn_g"));
                        paramMap.put("ETC_DESCR2", GwmsUtils.val(listM, "pv_msktx"));
                        paramMap.put("SKUGROUP", GwmsUtils.val(listM, "pv_matkl"));
                        paramMap.put("SKUTYPE", GwmsUtils.val(listM, "pv_nbpnb"));
                        paramMap.put("DEL_YN", GwmsUtils.val(listM, "isfw"));
                        ifIdList1 = Collections.singletonList(GwmsUtils.first((List<Map<String, Object>>) listM.get("pv_cjif_info")));
                        ifIdList2 = Collections.singletonList(GwmsUtils.first((List<Map<String, Object>>) listM.get("pv_recycle_info")));
                        ifIdList3 = Collections.singletonList(GwmsUtils.first((List<Map<String, Object>>) listM.get("pv_legal_info")));
                        ifIdList4 = Collections.singletonList(GwmsUtils.first((List<Map<String, Object>>) listM.get("pv_wms_info")));
                        ifIdList5 = Collections.singletonList(GwmsUtils.first((List<Map<String, Object>>) listM.get("pv_product_brand")));
                        ifIdList6 = Collections.singletonList(GwmsUtils.first((List<Map<String, Object>>) listM.get("pv_product_origin")));
                        ifIdList7 = Collections.singletonList(GwmsUtils.first((List<Map<String, Object>>) listM.get("pv_product_detail")));
                        ifIdList8 = Collections.singletonList(GwmsUtils.first((List<Map<String, Object>>) listM.get("pv_product_pallete")));
                        ifIdList9 = Collections.singletonList(GwmsUtils.first((List<Map<String, Object>>) listM.get("pv_product_sale")));
                        ifIdList10 = Collections.singletonList(GwmsUtils.first((List<Map<String, Object>>) listM.get("pv_product_tax")));
                        ifIdList11 = Collections.singletonList(GwmsUtils.first((List<Map<String, Object>>) listM.get("pv_product_nonq")));

                        Map<String, Object> list1 = GwmsUtils.first(ifIdList1);
                        paramMap.put("CUSTSKU", GwmsUtils.val(list1, "pv_cjpcd"));

                        Map<String, Object> list2 = GwmsUtils.first(ifIdList2);
                        paramMap.put("PACKBOXTYPE", GwmsUtils.val(list2, "pv_packmatter"));

                        Map<String, Object> list3 = GwmsUtils.first(ifIdList3);
                        paramMap.put("STYLECODE", GwmsUtils.val(list3, "pv_partsd"));
                        paramMap.put("DURATION", GwmsUtils.val(list3, "pv_expdt"));
                        paramMap.put("SERIALYN", GwmsUtils.val(list3, "pv_rpttyp"));
                        paramMap.put("DURATIONTYPE", GwmsUtils.val(list3, "pv_exptype"));
                        paramMap.put("SERIALTYPE", GwmsUtils.val(list3, "pv_rptorg"));
                        paramMap.put("ABC", GwmsUtils.val(list3, "pv_parts"));

                        Map<String, Object> list4 = GwmsUtils.first(ifIdList4);
                        paramMap.put("STORAGETYPE", GwmsUtils.val(list4, "pv_raube"));
                        paramMap.put("PRODUCT", GwmsUtils.val(list4, "pv_behvo"));
                        paramMap.put("OTHER01", GwmsUtils.val(list4, "pv_breit"));
                        paramMap.put("OTHER02", GwmsUtils.val(list4, "pv_hoehe"));
                        paramMap.put("OTHER03", GwmsUtils.val(list4, "pv_laeng"));

                        Map<String, Object> list5 = GwmsUtils.first(ifIdList5);
                        paramMap.put("BRANDDESCR", GwmsUtils.val(list5, "pv_bname"));
                        paramMap.put("SOMDCODE", GwmsUtils.val(list5, "pv_ekgrp"));

                        Map<String, Object> list6 = GwmsUtils.first(ifIdList6);
                        paramMap.put("INVOICEDESCR", GwmsUtils.val(list6, "pv_lgprodcountry"));
                        paramMap.put("PLACEOFORIGIN", GwmsUtils.val(list6, "pv_mfcnt"));
                        paramMap.put("COUNTRYOFORIGIN", GwmsUtils.val(list6, "pv_mncnt"));

                        Map<String, Object> list7 = GwmsUtils.first(ifIdList7);
                        paramMap.put("REFERENCE04", GwmsUtils.val(list7, "pv_processyn"));
                        paramMap.put("REFERENCE05", GwmsUtils.val(list7, "pv_promotionyn"));

                        Map<String, Object> list8 = GwmsUtils.first(ifIdList8);
                        paramMap.put("BASEUOM", GwmsUtils.val(list8, "pv_meins"));
                        paramMap.put("PURCHASEUOM", GwmsUtils.val(list8, "pv_bstme"));
                        paramMap.put("SALESUOM", GwmsUtils.val(list8, "pv_wvrkm"));
                        paramMap.put("BOXPERLAYER", GwmsUtils.val(list8, "pv_pltqty"));
                        paramMap.put("LAYERPERPLT", GwmsUtils.val(list8, "pv_pltstair"));
                        paramMap.put("REFERENCE03", GwmsUtils.val(list8, "pv_pltunit"));
                        paramMap.put("BOXPERPLT", GwmsUtils.val(list8, "pv_ptqty"));
                        paramMap.put("QTYPERBOX", GwmsUtils.val(list8, "pv_bxqty"));
                        paramMap.put("QTYPERPACK", GwmsUtils.val(list8, "pv_pcqty"));
                        paramMap.put("UOM", GwmsUtils.val(list8, "pv_meinh"));
                        paramMap.put("BUNJA", GwmsUtils.val(list8, "pv_umrez"));
                        paramMap.put("BUNMO", GwmsUtils.val(list8, "pv_umren"));
                        paramMap.put("CONVERTQTY", GwmsUtils.val(list8, "pv_umrez"));
                        paramMap.put("GROSSWEIGHT", GwmsUtils.val(list8, "pv_brgew_1"));
                        paramMap.put("NETWEIGHT", GwmsUtils.val(list8, "pv_ntgew_1"));
                        paramMap.put("BARCODE1", GwmsUtils.val(list8, "pv_ean11_mean"));

                        Map<String, Object> list9 = GwmsUtils.first(ifIdList9);
                        paramMap.put("MIXBOXYN", GwmsUtils.val(list9, "pv_assetmngyn"));

                        Map<String, Object> list10 = GwmsUtils.first(ifIdList10);
                        paramMap.put("OTHER05", GwmsUtils.val(list10, "pv_taxkm"));

                        Map<String, Object> list11 = GwmsUtils.first(ifIdList11);
                        paramMap.put("LINE01", GwmsUtils.val(list11, "pv_nquanyn"));
                        paramMap.put("OTHER04", GwmsUtils.val(list11, "pv_nquanyn"));
                        paramMap.put("LINE02", GwmsUtils.val(list11, "pv_gewav"));

                        //하드코딩 값
                        paramMap.put("IF_FLAG", "N");
                        paramMap.put("IF_ID", "MDM0200");
                        paramMap.put("IF_DESTINATION", "MDM");
                        paramMap.put("CONVERTVAL", (Integer.parseInt(paramMap.get("BUNJA").toString()) / Integer.parseInt(paramMap.get("BUNMO").toString())));

                        // 처리 결과 샘플 적재(필요 시 DB 결과로 대체)
                        commonDao.insert("gwmsMsApiService.insertIfMsSku", paramMap);
                        commonDao.insert("gwmsMsApiService.insertIfMsUom", paramMap);
                    }

					//후속 프로시저 실행.
					Map<String, String> paramSetterMap = new HashMap<>();
					paramSetterMap.put("AVC_IFID", "MDM0200");
					paramSetterMap.put("AVC_IFRESULT", "");
					commonDao.selectOne("gwmsMsApiService.CallIfMsSku", paramSetterMap);
				}
				dataSet.put("XSTAT", "S");
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



	/**
	 * 협력사 상품수정
	 * @param inParams 입력 파라미터(Map)
	 * @return 처리 결과(Map)
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> updateMsSkuApiData(Map<String, Object> inParams) {
		Map<String, Object> outParams      = new HashMap<>();
		Map<String, Object> dataMap        = new HashMap<>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		Map<String, Object> dataSet        = new HashMap<>();
		Map<String, Object> ifIdMap;

		List<Map<String, Object>> ifIdListM;
		List<Map<String, Object>> ifIdList1;
		List<Map<String, Object>> ifIdList2;
		List<Map<String, Object>> ifIdList3;
		List<Map<String, Object>> ifIdList4;
		List<Map<String, Object>> ifIdList5;
		List<Map<String, Object>> ifIdList6;
		List<Map<String, Object>> ifIdList7;
		List<Map<String, Object>> ifIdList8;
		List<Map<String, Object>> ifIdList9;
		List<Map<String, Object>> ifIdList10;
		List<Map<String, Object>> ifIdList11;

		List<Map<String, Object>> rslt = new ArrayList<>();

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

                        Object fs121 = row.get("FS121");
                        if (!(fs121 instanceof Map)) continue;

                        ifIdMap = (Map<String, Object>) fs121;
                        ifIdListM = GwmsUtils.castList(ifIdMap.get("pv_product_info"));

                        // 3) 파라미터 맵 구성
                        Map<String, Object> paramMap = new HashMap<>();
                        paramMap.put("STORERKEY", "FW00");
                        paramMap.put("INSTANCEID", GwmsUtils.val(ifIdMap, "pv_instanceid"));

                        Map<String, Object> listM = GwmsUtils.first(ifIdListM);
                        paramMap.put("SKU", GwmsUtils.val(listM, "gd_no"));
                        paramMap.put("DESCRIPTION", GwmsUtils.val(listM, "pv_maktx"));
                        paramMap.put("ETC_DESCR1", GwmsUtils.val(listM, "pv_maktn_g"));
                        paramMap.put("ETC_DESCR2", GwmsUtils.val(listM, "pv_msktx"));
                        paramMap.put("SKUGROUP", GwmsUtils.val(listM, "pv_matkl"));
                        paramMap.put("SKUTYPE", GwmsUtils.val(listM, "pv_nbpnb"));
                        paramMap.put("DEL_YN", GwmsUtils.val(listM, "isfw"));
                        ifIdList1 = Collections.singletonList(GwmsUtils.first((List<Map<String, Object>>) listM.get("pv_cjif_info")));
                        ifIdList2 = Collections.singletonList(GwmsUtils.first((List<Map<String, Object>>) listM.get("pv_recycle_info")));
                        ifIdList3 = Collections.singletonList(GwmsUtils.first((List<Map<String, Object>>) listM.get("pv_legal_info")));
                        ifIdList4 = Collections.singletonList(GwmsUtils.first((List<Map<String, Object>>) listM.get("pv_wms_info")));
                        ifIdList5 = Collections.singletonList(GwmsUtils.first((List<Map<String, Object>>) listM.get("pv_product_brand")));
                        ifIdList6 = Collections.singletonList(GwmsUtils.first((List<Map<String, Object>>) listM.get("pv_product_origin")));
                        ifIdList7 = Collections.singletonList(GwmsUtils.first((List<Map<String, Object>>) listM.get("pv_product_detail")));
                        ifIdList8 = Collections.singletonList(GwmsUtils.first((List<Map<String, Object>>) listM.get("pv_product_pallete")));
                        ifIdList9 = Collections.singletonList(GwmsUtils.first((List<Map<String, Object>>) listM.get("pv_product_sale")));
                        ifIdList10 = Collections.singletonList(GwmsUtils.first((List<Map<String, Object>>) listM.get("pv_product_tax")));
                        ifIdList11 = Collections.singletonList(GwmsUtils.first((List<Map<String, Object>>) listM.get("pv_product_nonq")));


                        Map<String, Object> list1 = GwmsUtils.first(ifIdList1);
                        paramMap.put("CUSTSKU", GwmsUtils.val(list1, "pv_cjpcd"));

                        Map<String, Object> list2 = GwmsUtils.first(ifIdList2);
                        paramMap.put("PACKBOXTYPE", GwmsUtils.val(list2, "pv_packmatter"));

                        Map<String, Object> list3 = GwmsUtils.first(ifIdList3);
                        paramMap.put("STYLECODE", GwmsUtils.val(list3, "pv_partsd"));
                        paramMap.put("DURATION", GwmsUtils.val(list3, "pv_expdt"));
                        paramMap.put("SERIALYN", GwmsUtils.val(list3, "pv_rpttyp"));
                        paramMap.put("DURATIONTYPE", GwmsUtils.val(list3, "pv_exptype"));
                        paramMap.put("SERIALTYPE", GwmsUtils.val(list3, "pv_rptorg"));
                        paramMap.put("ABC", GwmsUtils.val(list3, "pv_parts"));

                        Map<String, Object> list4 = GwmsUtils.first(ifIdList4);
                        paramMap.put("STORAGETYPE", GwmsUtils.val(list4, "pv_raube"));
                        paramMap.put("PRODUCT", GwmsUtils.val(list4, "pv_behvo"));
                        paramMap.put("OTHER01", GwmsUtils.val(list4, "pv_breit"));
                        paramMap.put("OTHER02", GwmsUtils.val(list4, "pv_hoehe"));
                        paramMap.put("OTHER03", GwmsUtils.val(list4, "pv_laeng"));

                        Map<String, Object> list5 = GwmsUtils.first(ifIdList5);
                        paramMap.put("BRANDDESCR", GwmsUtils.val(list5, "pv_bname"));
                        paramMap.put("SOMDCODE", GwmsUtils.val(list5, "pv_ekgrp"));

                        Map<String, Object> list6 = GwmsUtils.first(ifIdList6);
                        paramMap.put("INVOICEDESCR", GwmsUtils.val(list6, "pv_lgprodcountry"));
                        paramMap.put("PLACEOFORIGIN", GwmsUtils.val(list6, "pv_mfcnt"));
                        paramMap.put("COUNTRYOFORIGIN", GwmsUtils.val(list6, "pv_mncnt"));

                        Map<String, Object> list7 = GwmsUtils.first(ifIdList7);
                        paramMap.put("REFERENCE04", GwmsUtils.val(list7, "pv_processyn"));
                        paramMap.put("REFERENCE05", GwmsUtils.val(list7, "pv_promotionyn"));

                        Map<String, Object> list8 = GwmsUtils.first(ifIdList8);
                        paramMap.put("BASEUOM", GwmsUtils.val(list8, "pv_meins"));
                        paramMap.put("PURCHASEUOM", GwmsUtils.val(list8, "pv_bstme"));
                        paramMap.put("SALESUOM", GwmsUtils.val(list8, "pv_wvrkm"));
                        paramMap.put("BOXPERLAYER", GwmsUtils.val(list8, "pv_pltqty"));
                        paramMap.put("LAYERPERPLT", GwmsUtils.val(list8, "pv_pltstair"));
                        paramMap.put("REFERENCE03", GwmsUtils.val(list8, "pv_pltunit"));
                        paramMap.put("BOXPERPLT", GwmsUtils.val(list8, "pv_ptqty"));
                        paramMap.put("QTYPERBOX", GwmsUtils.val(list8, "pv_bxqty"));
                        paramMap.put("QTYPERPACK", GwmsUtils.val(list8, "pv_pcqty"));
                        paramMap.put("UOM", GwmsUtils.val(list8, "pv_meinh"));
                        paramMap.put("BUNJA", GwmsUtils.val(list8, "pv_umrez"));
                        paramMap.put("BUNMO", GwmsUtils.val(list8, "pv_umren"));
                        paramMap.put("CONVERTQTY", GwmsUtils.val(list8, "pv_umrez"));
                        paramMap.put("GROSSWEIGHT", GwmsUtils.val(list8, "pv_brgew_1"));
                        paramMap.put("NETWEIGHT", GwmsUtils.val(list8, "pv_ntgew_1"));
                        paramMap.put("BARCODE1", GwmsUtils.val(list8, "pv_ean11_mean"));

                        Map<String, Object> list9 = GwmsUtils.first(ifIdList9);
                        paramMap.put("MIXBOXYN", GwmsUtils.val(list9, "pv_assetmngyn"));

                        Map<String, Object> list10 = GwmsUtils.first(ifIdList10);
                        paramMap.put("OTHER05", GwmsUtils.val(list10, "pv_taxkm"));

                        Map<String, Object> list11 = GwmsUtils.first(ifIdList11);
                        paramMap.put("LINE01", GwmsUtils.val(list11, "pv_nquanyn"));
                        paramMap.put("OTHER04", GwmsUtils.val(list11, "pv_nquanyn"));
                        paramMap.put("LINE02", GwmsUtils.val(list11, "pv_gewav"));

                        //하드코딩 값
                        paramMap.put("IF_FLAG", "N");
                        paramMap.put("IF_ID", "MDM0200");
                        paramMap.put("IF_DESTINATION", "MDM");

						// 사용
						int intBunja = GwmsUtils.toInt(paramMap.get("BUNJA"));  // null/빈값/숫자아님 → 0
						int intBunMo = GwmsUtils.toInt(paramMap.get("BUNMO"));  // null/빈값/숫자아님 → 0
						int convertVal = (intBunMo == 0) ? 0 : (intBunja / intBunMo);  // 0으로 나누기 방지
						paramMap.put("CONVERTVAL", Integer.toString(convertVal));

                        // 처리 결과 샘플 적재(필요 시 DB 결과로 대체)
                        commonDao.insert("gwmsMsApiService.updateIfMsSku", paramMap);
                        commonDao.insert("gwmsMsApiService.updateIfMsUom", paramMap);
                    }

					//후속 프로시저 실행.
					Map<String, String> paramSetterMap = new HashMap<>();
					paramSetterMap.put("AVC_IFID", "MDM0200");
					paramSetterMap.put("AVC_IFRESULT", "");
					commonDao.selectOne("gwmsMsApiService.CallIfMsSku", paramSetterMap);
				}
				dataSet.put("XSTAT", "S");
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