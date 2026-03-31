package cjfw.wms.cm.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmPopPopupReqDto;
import cjfw.wms.cm.dto.CmPopPopupResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.06.13 
 * @description : POP조회 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.13 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmPopPopupService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmPopPopupService.";
	
	private final CommonDao commonDao;
	/**
	 * @description : POP 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.13 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	public List<CmPopPopupResDto> getPopList(CmPopPopupReqDto cmPopPopupReqDto) {
		prepareMulti(cmPopPopupReqDto);
		return commonDao.selectList(SERVICEID_PREFIX + "getPopList", cmPopPopupReqDto); 
	}
	
//	public Page<CmPopPopupResDto> getPopList(CmPopPopupReqDto cmPopPopupReqDto, Page<?> page) {
//		prepareMulti(cmPopPopupReqDto);
//		return commonDao.selectPageList(SERVICEID_PREFIX + "getPopList", cmPopPopupReqDto, page); 
//	}
	
	/* multiSelect → 999개 청크(codeGroups) */
	private void prepareMulti(CmPopPopupReqDto req) {
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


