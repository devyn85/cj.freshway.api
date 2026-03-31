package cjfw.wms.tm.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.tm.dto.TmEntityRuleMasterListReqDto;
import cjfw.wms.tm.dto.TmEntityRuleMasterListResDto;
import cjfw.wms.tm.entity.TmEntityRuleEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.11 
 * @description : 통합수당관리 기능을 구현한 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.11 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmEntityRuleService {

	/**`
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "tmEntityRuleService.";
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @description : 통합수당관리 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.11 ParkJinWoo 생성
	 */
	public List<TmEntityRuleMasterListResDto> getMasterList(TmEntityRuleMasterListReqDto tmEntityRuleMasterListReqDto) {
//		tmInvoicelogMgrReqDto.setDocType("WD");
		return commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderlist",tmEntityRuleMasterListReqDto);
	}
	
	/**
	 * @description : 마감확인 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.01.12 ParkJinWoo 생성
	 */
	public List<TmEntityRuleMasterListResDto> trspCloseChk(TmEntityRuleMasterListReqDto reqDto) {
		if(reqDto == null) {
			return null;
		}
		List<TmEntityRuleMasterListResDto> result = new ArrayList<>();
		List<TmEntityRuleMasterListResDto> list = reqDto.getSaveList();
		for(TmEntityRuleMasterListResDto dto : list) {
			TmEntityRuleMasterListResDto chkval = (commonDao.selectOne(SERVICEID_PREFIX + "trspCloseChk", dto));
			result.add(chkval);
			
		}
		
//		List<TmAmountDailyResDto> result = commonDao.selectList(SERVICEID_PREFIX + "trspCloseChk", reqDto);
		return result;
	}
	/**
	 * @description : 통합수당관리 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.11 ParkJinWoo 생성
	 */
	public List<TmEntityRuleMasterListResDto> geSttlItemCdList(TmEntityRuleMasterListReqDto tmEntityRuleMasterListReqDto) {
//		tmInvoicelogMgrReqDto.setDocType("WD");
		return commonDao.selectList(SERVICEID_PREFIX + "geSttlItemCdList",tmEntityRuleMasterListReqDto);
	}
	/**
	 * @description : 통합수당관리 저장 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.11 ParkJinWoo 생성
	 */
	public String saveConfirm(TmEntityRuleMasterListReqDto req) {
		if(req != null) {
		List<TmEntityRuleMasterListResDto> list = req.getSaveList();
		for (var dto : list) {
			var entity = ModelMapperUtil.map(dto, userContext, TmEntityRuleEntity.class);
			 if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
				 if(commonDao.update(SERVICEID_PREFIX +"updateTmSettlementItemRate", entity) == 0) {
					 throw new UserHandleException("MSG.COM.VAL.067", new String[]{"데이터"});
				}
			 }
			 else if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
					int size = commonDao.insert(SERVICEID_PREFIX +"insertTmSettlementItemRate", entity);
					if(size == 0) {
						throw new UserHandleException("MSG.COM.VAL.067", new String[]{"데이터"});
					}
				}else if ((CanalFrameConstants.DELETE).equals(dto.getRowStatus())) {
				commonDao.update(SERVICEID_PREFIX +"deletetmSettlementItemRate", entity);
			} 
		}
		}
	
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	/**
	 * @description : 통합수당관리 저장 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.11 ParkJinWoo 생성
	 */
	public String saveExcel(TmEntityRuleMasterListReqDto req) {
		if(req != null) {
		List<TmEntityRuleMasterListResDto> list = req.getSaveList();
		for (var dto : list) {
			   /*
	          CASE1 ) UPDATE A
		           |---------------------------| ->A
		           |                             ->B
                 => 정보만 UPDATE A
	          CASE2 ) UPDATE A, INSERT B
		           |---------------------------| ->A
		               |                         ->B
		           =>
		           |---|
		                |----------------------|

			  CASE3 ) UPDATE A, INSERT B
		           |-------|           |-------| ->A
		               |                         ->B
		           =>
		           |---|               |-------| ->A
		                |-------------|          ->B

           */
			var entity = ModelMapperUtil.map(dto, userContext, TmEntityRuleEntity.class);
					List<TmEntityRuleMasterListResDto> result = commonDao.selectList(SERVICEID_PREFIX +"chkVal", entity);
					String chk1  = result.get(0).getChk1();
					String staDt = result.get(0).getStaDt();
					String endDt = result.get(0).getEndDt();
					String serialKey = result.get(0).getSerialKey();
					
					if(chk1.equals("Y")) {
						commonDao.update(SERVICEID_PREFIX +"updateTmSettlementItemRate01", entity);
					}else {
						if(staDt != null && !"".equals(staDt)) {
							entity.setSerialKey(serialKey);
							commonDao.update(SERVICEID_PREFIX +"updateTmSettlementItemRate02", entity);
						}
						if (endDt != null && !"".equals(endDt)){
							entity.setToDate(endDt);
						}
						commonDao.insert(SERVICEID_PREFIX +"insertTmSettlementItemRate", entity);
						
					}

							
						}
					}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	/**
	 * @description : 통합수당관리 저장 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.11 ParkJinWoo 생성
	 */
	public List<TmEntityRuleMasterListResDto> apiPostExcelUpload(TmEntityRuleMasterListReqDto req) {
		List<TmEntityRuleMasterListResDto> result = new ArrayList<TmEntityRuleMasterListResDto>();
		if(req != null) {
//		List<TmEntityRuleMasterListResDto> list ;
//		System.out.print(list);
		result = commonDao.selectList(SERVICEID_PREFIX + "excelValChk", req.getSaveList());
		}
		
	
		
		return  result;
	}

}
