/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.cm.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmCarAreaPopupReqDto;
import cjfw.wms.cm.dto.CmCarAreaPopupResDto;
import cjfw.wms.cm.dto.CmDcPopupReqDto;
import cjfw.wms.cm.dto.CmDcPopupResDto;
import lombok.RequiredArgsConstructor;

/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net)
 * @date : 2025.05.09
 * @description : OO 기능을 구현한 Controller Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.09 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@Service
@RequiredArgsConstructor
public class CmSearch70Service {

	private final CommonDao commonDao;


	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmSearch70Service.";

	/** @description : 차량, 권역 목록 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.09 YangChangHwan (iamai@cj.net) 생성 </pre>
	*/
	public Page<CmCarAreaPopupResDto> getCarAreaList(CmCarAreaPopupReqDto reqDto, Page page) {
		return commonDao.selectPageList(SERVICEID_PREFIX + "getCarAreaList", reqDto, page);
	}


	/** @description : 센터 목록 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.13 YangChangHwan (iamai@cj.net) 생성 </pre>
	*/
	public Page<CmDcPopupResDto> getDcList(CmDcPopupReqDto reqDto, Page<?> page) {
		// 센터조회 팝업
		prepareMulti(reqDto);
		return commonDao.selectPageList(SERVICEID_PREFIX + "getDcList", reqDto, page);
	}

	/* multiSelect → 999개 청크(codeGroups) */
	private void prepareMulti(CmDcPopupReqDto req) {
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
