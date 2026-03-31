package cjfw.wms.cm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmCarInfoPopupReqDto;
import cjfw.wms.cm.dto.CmCarInfoPopupResDto;
import cjfw.wms.cm.dto.CmCustInfoPopupReqDto;
import cjfw.wms.cm.dto.CmCustInfoPopupResDto;
import cjfw.wms.cm.dto.CmPartnerInfoPopupReqDto;
import cjfw.wms.cm.dto.CmPartnerInfoPopupResDto;
//import cjfw.wms.cm.dto.CmPartnerInfoPopupReqDto;
//import cjfw.wms.cm.dto.CmPartnerInfoPopupResDto;
import cjfw.wms.cm.dto.CmPopupReqDto;
import cjfw.wms.cm.dto.CmPopupResDto;
import cjfw.wms.cm.dto.CmSearchCustReqDto;
import cjfw.wms.cm.dto.CmSearchCustResDto;
import cjfw.wms.cm.dto.CmSkuInfoPopupReqDto;
import cjfw.wms.cm.dto.CmSkuInfoPopupResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net)
 * @date : 2025.05.08
 * @description : 공통 검색 Service
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.08 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmSearchService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmSearchService.";

	private final CommonDao commonDao;

	/**
	 * @description : 거래처 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.08 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public List<CmSearchCustResDto> getCustList(CmSearchCustReqDto cmSearchCustReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getCustList", cmSearchCustReqDto);
	}


	/** @description : 상품정보 Popup 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.27 YangChangHwan (iamai@cj.net) 생성 </pre>
	*/
	public CmSkuInfoPopupResDto getSkuInfoPopup(CmSkuInfoPopupReqDto reqDto) {
		return commonDao.selectOne(SERVICEID_PREFIX + "getSkuInfoPopup", reqDto);
	}

	/** @description : 거래처정보 Popup 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.27 YangChangHwan (iamai@cj.net) 생성 </pre>
	*/
	public CmCustInfoPopupResDto getCustInfoPopup(CmCustInfoPopupReqDto reqDto) {
		return commonDao.selectOne(SERVICEID_PREFIX + "getCustInfoPopup", reqDto);
	}
	
	/** @description : 협력사정보 Popup 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.23  생성 </pre>
	*/
	public CmPartnerInfoPopupResDto getPartnerInfoPopup(CmPartnerInfoPopupReqDto reqDto) {
		return commonDao.selectOne(SERVICEID_PREFIX + "getPartnerInfoPopup", reqDto);
	}

	/** @description : 차량정보 Popup 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.27 YangChangHwan (iamai@cj.net) 생성 </pre>
	*/
	public CmCarInfoPopupResDto getCarInfoPopup(CmCarInfoPopupReqDto reqDto) {
		return commonDao.selectOne(SERVICEID_PREFIX + "getCarInfoPopup", reqDto);
	}
	
	/**
	 * @description : BOX 부서 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 sss (kduimux@cj.net) 생성 </pre>
	 */
	public Page<CmPopupResDto> getBoxList(CmPopupReqDto reqDto, Page<CmPopupResDto> page) {
		Page<CmPopupResDto> result = commonDao.selectPageList(SERVICEID_PREFIX + "getBoxList", reqDto, page);
		return result;
	}

	 /**
     * @description : 원거리유형 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.05.09 sss (kduimux@cj.net) 생성 </pre>
     */
    public Page<CmPopupResDto> getDistanceTypeList(CmPopupReqDto reqDto, Page<CmPopupResDto> page) {
        //return commonDao.selectList(SERVICEID_PREFIX + "getDistanceTypeList", reqDto);
        
        Page<CmPopupResDto> result = commonDao.selectPageList(SERVICEID_PREFIX + "getDistanceTypeList", reqDto, page);
        return result;
    }

}
