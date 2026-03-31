package cjfw.wms.cm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmCarrierPopupReqDto;
import cjfw.wms.cm.dto.CmCarrierPopupResDto;
import cjfw.wms.cm.dto.CmSkuSpecPopupReqDto;
import cjfw.wms.cm.dto.CmSkuSpecPopupResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : KimSunHo (sunhokim6229@cj.net) 
 * @date : 2025.05.09 
 * @description : 공통 검색 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.08 KimSunHo (sunhokim6229@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmSearch20Service {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmSearch20Service.";

	private final CommonDao commonDao;

	/**
	 * @description : 운송사 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 KimSunHo (sunhokim6229@cj.net) 생성 </pre>
	 */
	public Page<CmCarrierPopupResDto> getCarrierList(CmCarrierPopupReqDto cmSearchCarrierPopupReqDto, Page<?> page) {
		return commonDao.selectPageList(SERVICEID_PREFIX + "getCarrierList", cmSearchCarrierPopupReqDto, page);
	}
	/**
	 * @description : 운송사 목록 조회(드롭다운용)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.12 ParkYosep (dytpq362@cj.net)
	 */
	public Page<CmCarrierPopupResDto> getCarrierDropList(CmCarrierPopupReqDto cmSearchCarrierPopupReqDto, Page<?> page) {
		return commonDao.selectPageList(SERVICEID_PREFIX + "getCarrierDropList", cmSearchCarrierPopupReqDto, page);
	}
	
	/**
	 * @description : 상품 스펙 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.12 KimSunHo (sunhokim6229@cj.net) 생성 </pre>
	 */
	public List<CmSkuSpecPopupResDto> getSkuSpecList(CmSkuSpecPopupReqDto cmSkuSpecPopupReqDto) {
		List<CmSkuSpecPopupResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getSkuSpecList",	cmSkuSpecPopupReqDto);
		
		// Tree 참조용 컬럼 추가(검색결과 처리용)
		for(CmSkuSpecPopupResDto cmSkuSpecPopupResDto: list){
			String upperSpecCode = cmSkuSpecPopupResDto.getSpeccode().substring(0, cmSkuSpecPopupResDto.getSpeccode().length() - 2);
			cmSkuSpecPopupResDto.setRefUpperSpecCode(upperSpecCode);
		}
		
		return list;
	}

}
