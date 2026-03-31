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
public class GwmsDmApiService {

	private final CommonDao commonDao;

	/**
	 * 중계 입고 확정실적 전송(일배, 저장품, 협력사 반품)
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

                        Object fs140 = row.get("FS140");
                        if (!(fs140 instanceof Map)) continue;

                        ifIdMap = (Map<String, Object>) fs140;
                        if ("".equals(GwmsUtils.val(ifIdMap, "SLIPDT")) || GwmsUtils.val(ifIdMap, "SLIPDT") == null) {
                            dataSet.put("XSTAT", "E");
                            dataSet.put("XMSGS", "SLIPDT가 NULL입니다.");
                            outParams.put("ds_list", dataSet);
                            return outParams;
                        } else if ("".equals(GwmsUtils.val(ifIdMap, "CUSTKEY")) || GwmsUtils.val(ifIdMap, "CUSTKEY") == null) {
                            dataSet.put("XSTAT", "E");
                            dataSet.put("XMSGS", "CUSTKEY가 NULL입니다.");
                            outParams.put("ds_list", dataSet);
                            return outParams;
                        }

                        rslt = commonDao.selectList("gwmsDmApiService.selectDpApiData", (Object) ifIdMap);
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

	/**
	 * 중계 재고조회
	 * @param inParams 입력 파라미터(Map)
	 * @return 처리 결과(Map)
	 */
	public Map<String, Object> selectStApiData(Map<String, Object> inParams) {
		Map<String, Object> outParams = new HashMap<>();
		Map<String, Object> dataMap = new HashMap<>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		Map<String, Object> dataSet = new HashMap<>();
		Map<String, Object> ifIdMap;

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
					for (int i = 0; i < dataList.size(); i++) {
						Map<String, Object> row = dataList.get(i);
						if (row == null) continue;

						Object fs141 = row.get("FS141");
						if (!(fs141 instanceof Map)) continue;

						ifIdMap = (Map<String, Object>) fs141;
						if ("".equals(GwmsUtils.val(ifIdMap, "SKU")) || GwmsUtils.val(ifIdMap, "SKU") == null) {
							dataSet.put("XSTAT", "E");
							dataSet.put("XMSGS", "SKU가 NULL입니다.");
							outParams.put("ds_list", dataSet);
							return outParams;
						} else {
							String sMultiSku = GwmsUtils.val(ifIdMap, "SKU").toString();
							if (!sMultiSku.isEmpty()) {
								String[] aMultiSku = sMultiSku.split(",");
								ifIdMap.put("MULTI_SKU", aMultiSku);
							}
						}

						rslt = commonDao.selectList("gwmsDmApiService.selectStApiData", (Object) ifIdMap);
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

	/**
	 * 입고진행현황
	 * @param inParams 입력 파라미터(Map)
	 * @return 처리 결과(Map)
	 */
	public Map<String, Object> selectDpInplanApiData(Map<String, Object> inParams) {
		Map<String, Object> outParams = new HashMap<>();
		Map<String, Object> dataMap = new HashMap<>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		Map<String, Object> dataSet = new HashMap<>();
		Map<String, Object> ifIdMap;

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

                        Object fs142 = row.get("FS142");
                        if (!(fs142 instanceof Map)) continue;

                        ifIdMap = (Map<String, Object>) fs142;
                        if ("".equals(GwmsUtils.val(ifIdMap, "CUSTKEY")) || GwmsUtils.val(ifIdMap, "CUSTKEY") == null) {
                            dataSet.put("XSTAT", "E");
                            dataSet.put("XMSGS", "CUSTKEY가 NULL입니다.");
                            outParams.put("ds_list", dataSet);
                            return outParams;
                        } else if ("".equals(GwmsUtils.val(ifIdMap, "SLIPDT")) || GwmsUtils.val(ifIdMap, "SLIPDT") == null) {

                            dataSet.put("XSTAT", "E");
                            dataSet.put("XMSGS", "SLIPDT가 NULL입니다.");
                            outParams.put("ds_list", dataSet);
                            return outParams;
                        }
                        rslt = commonDao.selectList("gwmsDmApiService.selectDpInplanApiData", (Object) ifIdMap);
                    }
					dataSet.put("XSTAT", "S");
					dataSet.put("data", GwmsUtils.listKeyChangeLower(rslt));
				} else {
					dataSet.put("data", rslt);
					dataSet.put("XSTAT", "E");
					dataSet.put("XMSGS", "null");
				}
			}
		} catch(Exception e){
			dataSet.put("data", rslt);
			dataSet.put("XSTAT", "E");
			dataSet.put("XMSGS", e.getMessage());
		}
		outParams.put("ds_list", dataSet);
		return outParams;
	}

	/**
	 * 출고진행현황
	 * @param inParams 입력 파라미터(Map)
	 * @return 처리 결과(Map)
	 */
	public Map<String, Object> selectWdInplanApiData(Map<String, Object> inParams) {
		Map<String, Object> outParams = new HashMap<>();
		Map<String, Object> dataMap = new HashMap<>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		Map<String, Object> dataSet = new HashMap<>();
		Map<String, Object> ifIdMap;

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

                        Object fs143 = row.get("FS143");
                        if (!(fs143 instanceof Map)) continue;

                        ifIdMap = (Map<String, Object>) fs143;
                        if ("".equals(GwmsUtils.val(ifIdMap, "CUSTKEY")) || GwmsUtils.val(ifIdMap, "CUSTKEY") == null) {
                            dataSet.put("XSTAT", "E");
                            dataSet.put("XMSGS", "CUSTKEY가 NULL입니다.");
                            outParams.put("ds_list", dataSet);
                            return outParams;
                        } else if ("".equals(GwmsUtils.val(ifIdMap, "SLIPDT")) || GwmsUtils.val(ifIdMap, "SLIPDT") == null) {

                            dataSet.put("XSTAT", "E");
                            dataSet.put("XMSGS", "SLIPDT가 NULL입니다.");
                            outParams.put("ds_list", dataSet);
                            return outParams;
                        }
                        rslt = commonDao.selectList("gwmsDmApiService.selectWdInplanApiData", (Object) ifIdMap);
                    }
					dataSet.put("XSTAT", "S");
					dataSet.put("data", GwmsUtils.listKeyChangeLower(rslt));
				} else {
					dataSet.put("data", rslt);
					dataSet.put("XSTAT", "E");
					dataSet.put("XMSGS", "null");
				}
			}
		} catch(Exception e){
			dataSet.put("data", rslt);
			dataSet.put("XSTAT", "E");
			dataSet.put("XMSGS", e.getMessage());
		}
		outParams.put("ds_list", dataSet);
		return outParams;
	}


	/**
	 * 저장품 발주 현황
	 * @param inParams 입력 파라미터(Map)
	 * @return 처리 결과(Map)
	 */
	public Map<String, Object> selectPoMonitoring(Map<String, Object> inParams) {
		Map<String, Object> outParams = new HashMap<>();
		Map<String, Object> dataMap = new HashMap<>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		Map<String, Object> dataSet = new HashMap<>();
		Map<String, Object> ifIdMap;

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

                        Object FS147 = row.get("FS147");
                        if (!(FS147 instanceof Map)) continue;

                        ifIdMap = (Map<String, Object>) FS147;
                        if ("".equals(GwmsUtils.val(ifIdMap, "CUSTKEY")) || GwmsUtils.val(ifIdMap, "CUSTKEY") == null) {
                            dataSet.put("XSTAT", "E");
                            dataSet.put("XMSGS", "CUSTKEY가 NULL입니다.");
                            outParams.put("ds_list", dataSet);
                            return outParams;
                        } else if ("".equals(GwmsUtils.val(ifIdMap, "SLIPDT")) || GwmsUtils.val(ifIdMap, "SLIPDT") == null) {

                            dataSet.put("XSTAT", "E");
                            dataSet.put("XMSGS", "SLIPDT가 NULL입니다.");
                            outParams.put("ds_list", dataSet);
                            return outParams;
                        }
                        rslt = commonDao.selectList("gwmsDmApiService.selectPoMonitoring", (Object) ifIdMap);
                    }
					dataSet.put("XSTAT", "S");
					dataSet.put("data", GwmsUtils.listKeyChangeLower(rslt));
				} else {
					dataSet.put("data", rslt);
					dataSet.put("XSTAT", "E");
					dataSet.put("XMSGS", "null");
				}
			}
		} catch(Exception e){
			dataSet.put("data", rslt);
			dataSet.put("XSTAT", "E");
			dataSet.put("XMSGS", e.getMessage());
		}
		outParams.put("ds_list", dataSet);
		return outParams;
	}

	/**
	 * 협력사 반품 지시
	 * @param inParams 입력 파라미터(Map)
	 * @return 처리 결과(Map)
	 */
	public Map<String, Object> selectRPApiData(Map<String, Object> inParams) {
		Map<String, Object> outParams = new HashMap<>();
		Map<String, Object> dataMap = new HashMap<>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		Map<String, Object> dataSet = new HashMap<>();
		Map<String, Object> ifIdMap;

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

                        Object FS148 = row.get("FS148");
                        if (!(FS148 instanceof Map)) continue;

                        ifIdMap = (Map<String, Object>) FS148;
                        if ("".equals(GwmsUtils.val(ifIdMap, "CUSTKEY")) || GwmsUtils.val(ifIdMap, "CUSTKEY") == null) {
                            dataSet.put("XSTAT", "E");
                            dataSet.put("XMSGS", "CUSTKEY가 NULL입니다.");
                            outParams.put("ds_list", dataSet);
                            return outParams;
                        } else if ("".equals(GwmsUtils.val(ifIdMap, "SLIPDT")) || GwmsUtils.val(ifIdMap, "SLIPDT") == null) {

                            dataSet.put("XSTAT", "E");
                            dataSet.put("XMSGS", "SLIPDT가 NULL입니다.");
                            outParams.put("ds_list", dataSet);
                            return outParams;
                        }
                        rslt = commonDao.selectList("gwmsDmApiService.selectRPApiData", (Object) ifIdMap);
                    }
					dataSet.put("XSTAT", "S");
					dataSet.put("data", GwmsUtils.listKeyChangeLower(rslt));
				} else {
					dataSet.put("data", rslt);
					dataSet.put("XSTAT", "E");
					dataSet.put("XMSGS", "null");
				}
			}
		} catch(Exception e){
			dataSet.put("data", rslt);
			dataSet.put("XSTAT", "E");
			dataSet.put("XMSGS", e.getMessage());
		}
		outParams.put("ds_list", dataSet);
		return outParams;
	}

}