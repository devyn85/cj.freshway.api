package cjfw.wms.cm.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmSearchPartnerPopupDetailReqDto;
import cjfw.wms.cm.dto.CmSearchPartnerPopupDetailResDto;
import cjfw.wms.cm.dto.CmSearchPartnerPopupReqDto;
import cjfw.wms.cm.dto.CmSearchPartnerPopupResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CmSearchPartnerPopupService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private static final String SERVICEID_PREFIX = "cmSearchPartnerPopup.";
	private final CommonDao commonDao;
	/**
	 * @description : 협력사 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.12 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	public Page<CmSearchPartnerPopupResDto> getPartnerList(CmSearchPartnerPopupReqDto cmSearchPartnerPopupReqDto, Page<?> page) {
		prepareMulti(cmSearchPartnerPopupReqDto);
		return commonDao.selectPageList(SERVICEID_PREFIX + "getPartnerList", cmSearchPartnerPopupReqDto, page);
	}
	
	/**
	 * @description : 협력사 단건 조회
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	public CmSearchPartnerPopupDetailResDto getPartner(CmSearchPartnerPopupDetailReqDto cmSearchPartnerPopupDetailReqDto) {
        return commonDao.selectOne(SERVICEID_PREFIX + "getPartner", cmSearchPartnerPopupDetailReqDto);
    }

	/* multiSelect → 999개 청크(codeGroups) */
	private void prepareMulti(CmSearchPartnerPopupReqDto req) {
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
