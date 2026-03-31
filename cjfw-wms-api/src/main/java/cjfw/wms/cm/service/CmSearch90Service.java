package cjfw.wms.cm.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmCarPopPopupReqDto;
import cjfw.wms.cm.dto.CmCarPopPopupResDto;
import cjfw.wms.cm.dto.CmDriverPopupReqDto;
import cjfw.wms.cm.dto.CmDriverPopupResDto;
import cjfw.wms.cm.dto.CmSkuGroupListPopupReqDto;
import cjfw.wms.cm.dto.CmSkuGroupListPopupResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved. 
 *
 * @author : ParkJinWoo (jw.park@cj.net) 
 * @date : 2025.05.09 
 * @description : 파일럿90 기능을 구현한 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 ParkJinWoo (jw.park@cj.net) 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmSearch90Service {
	private transient static final String SERVICEID_PREFIX = "cmSearch90Service.";
	
	private final CommonDao commonDao;
//	
	
	/**
	 * 
	 * @description : 기사조회팝업 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.12 ParkJinWoo 생성
	 */
	public Page<CmDriverPopupResDto> getDriverList (CmDriverPopupReqDto CmDriverPopupReqDto,Page<?> page ){
		prepareMulti(CmDriverPopupReqDto);
		return commonDao.selectPageList(SERVICEID_PREFIX + "getDriverList", CmDriverPopupReqDto,page);
	}
	
	/* multiSelect → 999개 청크(codeGroups) */
	private void prepareMulti(CmDriverPopupReqDto req) {
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
	 * 
	 * @description : carPop리스트조회 팝업 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.12 ParkJinWoo 생성
	 */
	public Page<CmCarPopPopupResDto> getCarPopList (CmCarPopPopupReqDto CmCarPopPopupReqDto,Page page ){
		return commonDao.selectPageList(SERVICEID_PREFIX + "getCarPopList", CmCarPopPopupReqDto,page);
	}
	
	/**
	 * 
	 * @description : 상품그룹2 목록조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.19 ParkJinWoo 생성
	 */
	public Page<CmSkuGroupListPopupResDto> getNewSkuGroup2List (CmSkuGroupListPopupReqDto CmSkuGroupListPopupReqDto,Page page ){
		prepareMulti(CmSkuGroupListPopupReqDto);
		return commonDao.selectPageList(SERVICEID_PREFIX + "getNewSkuGroup2List", CmSkuGroupListPopupReqDto,page);
	}
	
	/* multiSelect → 999개 청크(codeGroups) */
	private void prepareMulti(CmSkuGroupListPopupReqDto req) {
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
}
