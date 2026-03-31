package cjfw.wms.wd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.wd.dto.WdHomeDeliveryInvoicePopupDeliveryStatusReqDto;
import cjfw.wms.wd.dto.WdHomeDeliveryInvoicePopupDeliveryStatusResDto;
import cjfw.wms.wd.dto.WdHomeDeliveryInvoicePopupDocumentModifyResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.05.16 
 * @description : 택배송장 발행서비스 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.16 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdHomeDeliveryInvoiceService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "wdHomeDeliveryInvoiceService.";

	private final CommonDao commonDao;

	/**
	 * @description : 출고진행현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdHomeDeliveryInvoicePopupDeliveryStatusResDto> getDeliveryStatus(WdHomeDeliveryInvoicePopupDeliveryStatusReqDto wdHomeDeliveryInvoicePopupDeliveryStatusReqDto, Page page) {
		
		log.info("###### parameter = "+wdHomeDeliveryInvoicePopupDeliveryStatusReqDto.toString());		
		
		List<WdHomeDeliveryInvoicePopupDeliveryStatusResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getDeliveryStatus", wdHomeDeliveryInvoicePopupDeliveryStatusReqDto);
		return list;
	}
	
	/**
	 * @description : 주문변경내역 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.13 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdHomeDeliveryInvoicePopupDocumentModifyResDto> getDocumentModifyDetailForDocno(WdHomeDeliveryInvoicePopupDeliveryStatusReqDto wdHomeDeliveryInvoicePopupDeliveryStatusReqDto, Page page) {
		
		log.info("###### parameter = "+wdHomeDeliveryInvoicePopupDeliveryStatusReqDto.toString());		
		
		List<WdHomeDeliveryInvoicePopupDocumentModifyResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getDocumentModifyDetailForDocno", wdHomeDeliveryInvoicePopupDeliveryStatusReqDto);
		return list;
	}
	
}
