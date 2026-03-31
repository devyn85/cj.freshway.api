package cjfw.wms.ms.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.dataaccess.largedata.LargeExcel;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.dto.CmCodeDtlReqDto;
import cjfw.wms.cm.dto.CmCodeReqDto;
import cjfw.wms.cm.entity.CmCodeEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.ms.dto.MsPurchaseCustDetailResDto;
import cjfw.wms.ms.dto.MsPurchaseCustReqDto;
import cjfw.wms.ms.dto.MsPurchaseCustResDto;
import cjfw.wms.ms.dto.MsSkuDcSetSTOReqDto;
import cjfw.wms.ms.dto.MsSkuDcSetSTOResDto;
import cjfw.wms.ms.entity.MsPurchaseCustEntity;
import cjfw.wms.ms.entity.MsSkuDcSetSTOEntity;
import cjfw.wms.om.dto.OmPurchaseModifyResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.24
 * @description : 기준정보 > 상품기준정보 > 수발주정보 목록 조회 및 저장 Service
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.24        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsPurchaseCustService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msPurchaseCustService.";
	private transient static final String PAKAGE_NAME = "SPDP_PURCHASE";
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	private final CmCommonService cmCommonService;
	
	/**
	 * 
	 * @description : 수발주정보 정보 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.24        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<MsPurchaseCustResDto> getMasterList(MsPurchaseCustReqDto dto) {
		List<String> skuList = Arrays.stream(dto.getSkuCode().split(","))
		        .map(String::trim)
		        .map(String::new)
		        .collect(Collectors.toList());
		dto.setSkuCodeList(skuList);
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}
	
	/**
	 * 
	 * @description : 수발주정보 대용량 엑셀 다운로드 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.29        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public void getMasterListExcel(MsPurchaseCustReqDto dto, LargeExcel largeExcel) {
	    commonDao.selectLargeExcelDataset(SERVICEID_PREFIX + "getMasterListExcel", dto, largeExcel);
	}
	
	/**
	 * 
	 * @description : 수발주정보 정보 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.24        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<MsPurchaseCustDetailResDto> getDetailList(MsPurchaseCustReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDetailList", dto);
	}
	
	/**
	 * 
	 * @description : 수발주정보 정보 저장 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.24        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String saveMasterList(List<MsPurchaseCustReqDto> list) {
		if (null != list) {
			for (var dto : list) {
				var entity = ModelMapperUtil.map(dto, userContext, MsPurchaseCustEntity.class);
				
				if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
					commonDao.insert(SERVICEID_PREFIX +"insertMaster", entity);
				} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {					
					commonDao.update(SERVICEID_PREFIX +"insertPurchaseMstIfNotExists", entity);
					commonDao.update(SERVICEID_PREFIX +"updateMaster", entity);
//					commonDao.selectOne(SERVICEID_PREFIX +"updateMaster", entity);
					commonDao.selectOne(SERVICEID_PREFIX +"updateCust", entity);
					commonDao.selectOne(SERVICEID_PREFIX +"updatePurchaseBuy", entity);
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * 
	 * @description : 수발주정보 정보 삭제 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.19        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String deleteMasterList(List<MsPurchaseCustReqDto> list) {
		if (null != list) {
			for (var dto : list) {
				var entity = ModelMapperUtil.map(dto, userContext, MsPurchaseCustEntity.class);				
				commonDao.delete(SERVICEID_PREFIX +"deleteCust", entity);
				commonDao.delete(SERVICEID_PREFIX +"deleteMst", entity);
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 엑셀 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * -----------------------------------------------------------
	 * 2025.12.22        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String saveExcelList(List<MsPurchaseCustReqDto> list) {
		if (null != list) {
			//기존데이터 삭제
			commonDao.delete(SERVICEID_PREFIX +"deleteExcel", list.get(0));
			
			for (int i = 0; i < list.size(); i++) {
				var entity = ModelMapperUtil.map(list.get(i), userContext, MsPurchaseCustEntity.class);
				entity.setFileLineNo(i+1);				
				commonDao.update(SERVICEID_PREFIX +"insertExcel", entity);
			}
			
			/*START.PAKAGE 호출*/
			// 프로시저 에러코드 및 메세지
	        String resultCode = "";
	        String resultMessage  = "";
	        
			// PKG 파라마터 세팅 - 공통(1/4)
			MsPurchaseCustResDto resDto = new MsPurchaseCustResDto();
			ProcedureParametersFactory.initParamDto(list.get(0), resDto, PAKAGE_NAME);
	        
			// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
			String[] keyList   = {"PROCEDURE" ,"PROCESSTYPE"         ,"PROCESSCREATOR"     , "SPID"};
			Object[] valueList = {PAKAGE_NAME ,"PURCHASECUST_UPLOAD" ,resDto.getGUserId()  ,resDto.getGSpid()};
			resDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
			resDto.setAvc_COMMAND("UPLOAD");

			int rv = cmCommonService.saveProcedure(resDto); 
			log.info("rv->{}",rv);
			
			// 프로시저 OUT Parameter(3/4)
			resultCode    = StringUtil.nvl(resDto.getResultCode());
			resultMessage = StringUtil.nvl((String)resDto.getResultMessage());
			log.info("resultCode->{}",resultCode);
			log.info("resultMessage->{}",resultMessage);
			
			// 프로시저 Exception 처리(4/4)
			if(!"0".equals(resultCode)){				
				throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"수발주정보-업로드"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
			}
			/*END.PAKAGE 호출*/
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 엑셀 업로드 유효성 검사(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.22        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<MsPurchaseCustResDto> getValidateExcelList(List<MsPurchaseCustReqDto> dtoList) {
		List<MsPurchaseCustResDto> checkedResult = new ArrayList<MsPurchaseCustResDto>();
		if(dtoList != null) {
			for (MsPurchaseCustReqDto dto : dtoList) {
				MsPurchaseCustResDto result = commonDao.selectOne(SERVICEID_PREFIX + "getValidateExcelList", dto);
				checkedResult.add(result);
			}
		}
		return checkedResult;
	}

	/**
	 * 
	 * @description : 업로드 결과 조회 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.24        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<MsPurchaseCustResDto> getExcelUploadList(MsPurchaseCustReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getExcelUploadList", dto);
	}
	/**
	 * @description : 상세 코드 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.02  생성 </pre>
	 */
	@CacheEvict(cacheNames = "userCode", allEntries = true)
	public String saveCmDtlCode(CmCodeReqDto cmCodeReqDto) {
		if (null != cmCodeReqDto.getCodeDtlList()) {
			for (CmCodeDtlReqDto codeMst : cmCodeReqDto.getCodeDtlList()) {
				CmCodeEntity cmCodeEntity = ModelMapperUtil.map(codeMst, userContext, CmCodeEntity.class);
				if ((CanalFrameConstants.INSERT).equals(codeMst.getRowStatus())) {
					commonDao.insert(SERVICEID_PREFIX +"insertCmDtlCode", cmCodeEntity);
				} else if ((CanalFrameConstants.UPDATE).equals(codeMst.getRowStatus())) {
					// 데이터번호 빈값 체크
					if (!"".equals(cmCodeEntity.getSerialkey())) {
						commonDao.insert(SERVICEID_PREFIX +"updateCmDtlCode", cmCodeEntity);
					}
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

}
