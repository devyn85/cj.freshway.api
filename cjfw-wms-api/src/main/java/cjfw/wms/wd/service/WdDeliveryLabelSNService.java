package cjfw.wms.wd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.entity.CmSyProcessTempWdEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.wd.dto.WdDeliveryLabelSNReqDto;
import cjfw.wms.wd.dto.WdDeliveryLabelSNResPrintDto;
import cjfw.wms.wd.dto.WdDeliveryLabelSNResTab1DetailDto;
import cjfw.wms.wd.dto.WdDeliveryLabelSNResTab1Dto;
import cjfw.wms.wd.dto.WdDeliveryLabelSNResTab2Dto;
import cjfw.wms.wd.dto.WdDeliveryLabelSNResTab3Dto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.10.15 
 * @description : 이력배송라벨출력 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.15 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdDeliveryLabelSNService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "wdDeliveryLabelSNService.";
	private transient static final String PAKAGE_NAME = "SPWD_INVOICE";
	private transient static final String PAKAGE_NAME_CLOSE = "SPOM_ORDERCLOSE";
	private transient static final String PROCESSTYPE = "WD_INVOICE_SN";
	private transient static final String PROCESSTYPE_CLOSE = "INVOICECANCEL_SN";
	private transient static final String TEMPTABLETYPE = "WD";
	
	private final CommonDao commonDao;
	private final UserContext userContext;
	private final CmCommonService cmCommonService;

	/**
	 * @description : 이력배송라벨출력-분류표생성 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdDeliveryLabelSNResTab1Dto> getTab1MasterList(WdDeliveryLabelSNReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<WdDeliveryLabelSNResTab1Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1MasterList", dto);
		return list;
	}
	/**
	 * @description : 이력배송라벨출력-분류표생성 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdDeliveryLabelSNResTab1DetailDto> getTab1DetailList(WdDeliveryLabelSNReqDto dto) {
		
		log.info("#### parameter = "+dto.toString());		
		
		List<WdDeliveryLabelSNResTab1DetailDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1DetailList", dto);
		return list;
	}
	/**
	 * @description : 이력배송라벨출력-분류표출력 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdDeliveryLabelSNResTab2Dto> getTab2MasterList(WdDeliveryLabelSNReqDto dto) {
		
		log.info("##### parameter = "+dto.toString());		
		
		List<WdDeliveryLabelSNResTab2Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab2MasterList", dto);
		return list;
	}
	
	
	/**
	 * @description : 이력배송라벨출력-회수리스트 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.11 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdDeliveryLabelSNResTab3Dto> getTab3MasterList(WdDeliveryLabelSNReqDto dto) { 
		
		log.info("##### parameter = "+dto.toString());		
		
		List<WdDeliveryLabelSNResTab3Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab3MasterList", dto);
		return list;
	}
	
	/**
	 * @description : 피킹지출력 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public WdDeliveryLabelSNResPrintDto getPrintPickingList(WdDeliveryLabelSNReqDto dto) { 
		WdDeliveryLabelSNResPrintDto reportDto = new WdDeliveryLabelSNResPrintDto();
		log.info("##### parameter = "+dto.toString());		
		
		
		reportDto.setReportHeaderList(commonDao.selectList(SERVICEID_PREFIX + "getPrintHeaderList", dto));
		reportDto.setReportDetailList(commonDao.selectList(SERVICEID_PREFIX + "getPrintDetailList", dto));
		
		return reportDto;
	}
	
	
	/**
	 * @description : 회수리스트 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public WdDeliveryLabelSNResPrintDto getPrintReturnList(WdDeliveryLabelSNReqDto dto) { 
		WdDeliveryLabelSNResPrintDto reportDto = new WdDeliveryLabelSNResPrintDto();
		log.info("##### parameter = "+dto.toString());		
		
		
		reportDto.setReportReturnList(commonDao.selectList(SERVICEID_PREFIX + "getPrintReturnList", dto));
		
		return reportDto;
	}
	
	/**
	 * @description : 이력배송라벨출력 분류표 생성
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveCreationSN(WdDeliveryLabelSNReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdDeliveryLabelSNReqDto reqDto = ModelMapperUtil.map(paramDto, WdDeliveryLabelSNReqDto.class);
		List<WdDeliveryLabelSNResTab1Dto> saveList = reqDto.getSaveCreationSNList(); // 저장리스트
		reqDto.setPackagename(PAKAGE_NAME);
		reqDto.setProcesstype(PROCESSTYPE);
		log.info("▶reqDto.size->{}",reqDto);
		log.info("▶saveList.size->{}",saveList);
		log.info("▶command->{}",reqDto.getAvc_COMMAND());
		
		/*1. 임시테이블 삭제*/
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
		int chunkSize = 200;
		List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
		
		List<WdDeliveryLabelSNResTab1Dto> list = new ArrayList<WdDeliveryLabelSNResTab1Dto>();		
		for (int i = 0; i < saveList.size(); i++) {
			WdDeliveryLabelSNResTab1Dto dto = saveList.get(i);
			
			CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
        	entity.setPackagename(PAKAGE_NAME); // 패키지명
        	entity.setProcesstype(PROCESSTYPE); // 프로세스타입
			
			log.info("▶변환후 entity->{}",entity);
			// START.필수입력 check - 그리드 변수 등
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDccode())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getStorerkey())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"storerkey"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getOrganize())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"organize"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getArea())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"area"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDoctype())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"doctype"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocdt())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docdt"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocno())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docno"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSlipdt())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"slipdt"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSlipno())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"slipno"}));

			// END.필수입력 check
        	insertList.add(entity);
        	
        	// 200개마다 혹은 마지막 루프일 때 insert(3/3)
        	if (insertList.size() == chunkSize || i == saveList.size() -1) {
        		commonDao.insert(SERVICEID_PREFIX + "insertTempExcel", insertList); 
            	insertList.clear();
            }
		}
		
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
		
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE", "PROCESSTYPE"};
		Object[] valueList = {PAKAGE_NAME, PROCESSTYPE};
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));		
		reqDto.setAvc_DCCODE(saveList.get(0).getDccode());
		int rv = cmCommonService.saveProcedure(reqDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶분류표 생성시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"분류표 생성"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	/**
	 * @description : 마감주문반영
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveOrderclose(WdDeliveryLabelSNReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdDeliveryLabelSNReqDto reqDto = ModelMapperUtil.map(paramDto, WdDeliveryLabelSNReqDto.class);
		
		reqDto.setPackagename(PAKAGE_NAME_CLOSE);
		reqDto.setProcesstype(PROCESSTYPE_CLOSE);
		log.info("▶reqDto.size->{}",reqDto);
		
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME_CLOSE);
		
		String[] keyList   = {"PROCEDURE", "PROCESSTYPE"	  
				              ,"STORERKEY"
				              ,"DCCODE" 
				              ,"SLIPDT"			
				             };
		Object[] valueList = {PAKAGE_NAME_CLOSE, PROCESSTYPE_CLOSE  
				             // 업무 파라미터
				             ,reqDto.getGStorerkey()
				             ,reqDto.getDccode() 
				             ,reqDto.getSlipdt()	
				              };
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));		
		reqDto.setAvc_DCCODE(reqDto.getDccode());
		int rv = cmCommonService.saveProcedure(reqDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶마감주문반영시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"마감주문반영"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	

	/**
	 * @description : 피킹작업지시-피킹리스트 출력 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.03 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveInvoiceNoPrtYn(WdDeliveryLabelSNReqDto dto) { 
		
		commonDao.insert(SERVICEID_PREFIX + "saveInvoiceNoPrtYn", dto);
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
}
