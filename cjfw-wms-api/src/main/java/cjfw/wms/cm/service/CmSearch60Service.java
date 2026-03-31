package cjfw.wms.cm.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmCarPopupReqDto;
import cjfw.wms.cm.dto.CmCarPopupResDto;
import cjfw.wms.cm.dto.CmCustBrandPopupReqDto;
import cjfw.wms.cm.dto.CmCustBrandPopupResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.05.12 
 * @description : 공통 검색 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.12 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmSearch60Service {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmSearch60Service.";

	private final CommonDao commonDao;

	/**
	 * @description : 차량 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.12 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public Page<CmCarPopupResDto> getCarList(CmCarPopupReqDto cmGetCarReqDto, Page<?> page) {
		return commonDao.selectPageList(SERVICEID_PREFIX + "getCarList", cmGetCarReqDto, page);
	}
	
	/**
	 * @description : 자동차 번호로 기사의 전화 번호를 조회. 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.11 jun (kthis77@cj.net) 생성 </pre>
	 */
	public CmCarPopupResDto getCarDriverPhoneInfo(CmCarPopupReqDto cmGetCarReqDto) {
        return commonDao.selectOne(SERVICEID_PREFIX + "getCarDriverPhoneInfo", cmGetCarReqDto);
    }
	
	/**
	 * @description : 본점 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.12 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public Page<CmCustBrandPopupResDto> getCustBrandList(CmCustBrandPopupReqDto cmGetCustBrandReqDto, Page<?> page) {
		prepareMulti(cmGetCustBrandReqDto);		
		return commonDao.selectPageList(SERVICEID_PREFIX + "getCustBrandList", cmGetCustBrandReqDto, page);
	}
	
	/* multiSelect → 999개 청크(codeGroups) */
	private void prepareMulti(CmCustBrandPopupReqDto req) {
	    String csv = req.getMultiSelect();
	    if (csv == null || csv.isBlank()) return;

	    List<String> items = Arrays.stream(csv.split(","))
	        .map(String::trim)
	        .filter(s -> !s.isEmpty())
	        .distinct()
	        .limit(5000)
	        .collect(Collectors.toList());
	    if (items.isEmpty()) return;

	    int batchSize = 999;
	    List<List<String>> groups = new ArrayList<>();
	    for (int i = 0; i < items.size(); i += batchSize) {
	        groups.add(items.subList(i, Math.min(i + batchSize, items.size())));
	    }
	    req.setCodeGroups(groups);
	}	
	
	/**
	 * @description : 저장품 자동발주 팝업 조회 (목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public Page<CmCustBrandPopupResDto> getPopDataHeaderList(CmCustBrandPopupReqDto cmGetCustBrandReqDto, Page<?> page) {
		
		Page<CmCustBrandPopupResDto> result = commonDao.selectPageList(SERVICEID_PREFIX + "getPopDataHeaderList", cmGetCustBrandReqDto, page);
		return result;
	}

}
