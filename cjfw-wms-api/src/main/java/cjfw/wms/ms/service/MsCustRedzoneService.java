package cjfw.wms.ms.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmCustPopupReqDto;
import cjfw.wms.cm.dto.CmCustPopupResDto;
import cjfw.wms.ms.dto.MsCustRedzoneReqDto;
import cjfw.wms.ms.dto.MsCustRedzoneResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MsCustRedzoneService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private static final String SERVICEID_PREFIX = "msCustRedzone.";
	
	private final CommonDao commonDao;
	/**
	 * @description : 특별관리고객현황 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.27 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	public List<MsCustRedzoneResDto> getMasterList (MsCustRedzoneReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}
	/**
	 * 거래처를 조회한다.
	 */
	public Page<CmCustPopupResDto> getCustPopupPagingList(CmCustPopupReqDto cmCustPopupReqDto, Page page) {
		prepareMulti(cmCustPopupReqDto);
		return commonDao.selectPageList(SERVICEID_PREFIX +"getCustPopupPagingList", cmCustPopupReqDto, page);
	}
	private void prepareMulti(CmCustPopupReqDto req) {
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
