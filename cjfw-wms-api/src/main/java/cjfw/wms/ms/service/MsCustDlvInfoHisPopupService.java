package cjfw.wms.ms.service;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.ms.dto.MsCustDlvInfoHisPopupReqDto;
import cjfw.wms.ms.dto.MsCustDlvInfoHisPopupResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.07.28 
 * @description : 고객배송조건 수신이력 팝업 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.28 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsCustDlvInfoHisPopupService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private static final String SERVICEID_PREFIX = "msCustDlvInfoHisPopup.";
	private final CommonDao commonDao;
	
	/**
	 * @description : 고객배송조건 수신이력 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.28 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public Page<MsCustDlvInfoHisPopupResDto> getCustDlvInfoHis(MsCustDlvInfoHisPopupReqDto msCustDlvInfoHisPopupReqDto, Page<?> page) {
        return commonDao.selectPageList(SERVICEID_PREFIX + "getCustDlvInfoHis", msCustDlvInfoHisPopupReqDto, page);
    }
}
