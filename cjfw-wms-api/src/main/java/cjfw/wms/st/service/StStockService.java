package cjfw.wms.st.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import cjfw.core.model.UserContext;
import cjfw.wms.ib.service.IbAllWeightService;
import cjfw.wms.st.dto.StAdjustmentRequestExDCElectApprovalDto;
import cjfw.wms.st.dto.StStockReqDto;
import cjfw.wms.st.dto.StStockResDto;
import cjfw.wms.webservice.elecAmt.DT_MM0090_SCM;
import cjfw.wms.webservice.elecAmt.DT_MM0090_SCMIF_ST_STOCKAMT;
import cjfw.wms.webservice.elecAmt.DT_MM0090_SCM_responseIF_ST_STOCKAMT_RET;
import cjfw.wms.webservice.elecAmt.SI_MM0090_SCM_SOProxy;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : sss 
 * @date : 2025.07.01 
 * @description : 재고조회 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.16 성상수 (kduimux@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StStockService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "stStockService.";
	/** 공통.CommonDao */
	private final CommonDao commonDao;
	private final UserContext userContext;
    /**정산.service 금액가져오기*/
    private final IbAllWeightService ibAllWeightService;

	/**
	 * @description : 재고조회 목록 조회
	 * @issues : <pre>
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.01 sss 생성 </pre>
	 */
	public List<StStockResDto> getMasterList(StStockReqDto reqDto) {
		List<StStockResDto> searchResultList = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
		if("Y".equals(reqDto.getViewPriceYn())) {
			ibAllWeightService.fetchSapPrice(searchResultList);
		}
		return searchResultList;
	}
	
	/**
	 * @description : SAP 단가 조회
	 * @issues : <pre>
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.04 공두경 생성 </pre>
	 */
	public Map<String, BigDecimal> getSapAmtInfo(DT_MM0090_SCM reqData, Map<String, BigDecimal> responseStockAmtMap) {		
		SI_MM0090_SCM_SOProxy proxy = new SI_MM0090_SCM_SOProxy();
		String resultKey = "";
		try {
			DT_MM0090_SCM_responseIF_ST_STOCKAMT_RET[] response = proxy.si_mm0090_scm_so(reqData);
	
			//리스폰스 담기
			for (int i = 0; i < Integer.parseInt(reqData.getXROWS()); i++ ) {
				resultKey =  response[i].getPLANT()
						   + response[i].getSKU()
						   + response[i].getUOM();
	//					responseStockAmtMap.putIfAbsent(resultKey, BigDecimal.valueOf(Double.parseDouble(response[i].getSTOCKAMT()))); //뭐가 단가인지 모름
				responseStockAmtMap.putIfAbsent(resultKey, BigDecimal.valueOf(Double.parseDouble(response[i].getPURCHASEPRICE()))); //뭐가 단가인지 모름
			}
		} catch(Exception e) {
			log.error("재고금액 처리중 에러발생", e);
		}

		
		return responseStockAmtMap;
	}
	
	/**
	 * @description : 존 리스트 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public <R, T> List<R> getDataCodeList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDataCodeList", reqDto);
	}
 	
}
