package cjfw.wms.om.service;

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
import cjfw.wms.om.dto.OmCloseMonitoringReqDto;
import cjfw.wms.om.dto.OmCloseMonitoringResCloseTimeDto;
import cjfw.wms.om.dto.OmCloseMonitoringResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.23 
 * @description : 마감주문반영 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.23 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OmCloseMonitoringService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "omCloseMonitoringService.";
	
	private transient static final String PAKAGE_NAME = "SPOM_ORDERCLOSE";
	private transient static final String PROCESSTYPE = "ORDERCLOSE";
	private transient static final String TEMPTABLETYPE = "WD";

	private final CommonDao commonDao;
	private final UserContext userContext;
	private final CmCommonService cmCommonService;

	/**
	 * @description : 전표모니터링 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.23 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<OmCloseMonitoringResDto> getMasterList(OmCloseMonitoringReqDto omCloseMonitoringServiceReqDto) {
		
		log.info("### parameter = "+omCloseMonitoringServiceReqDto.toString());		
		
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", omCloseMonitoringServiceReqDto);
	}
	

	/**
	 * @description : 마감주문반영 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.23 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveConfirm(OmCloseMonitoringReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        String sDeliverydt  = "";
        OmCloseMonitoringReqDto reqDto = ModelMapperUtil.map(paramDto, OmCloseMonitoringReqDto.class);
        
        List<OmCloseMonitoringResDto> saveList = paramDto.getSaveList(); // 저장리스트     
                
		reqDto.setProcesstype(PROCESSTYPE);
		log.info("▶reqDto.size->{}",reqDto);
		log.info("▶saveList.size->{}",saveList);
		log.info("▶command->{}",reqDto.getAvc_COMMAND());
		

		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
        
        /*1. 임시테이블 삭제*/
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
		int chunkSize = 200;
		List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
		
		sDeliverydt = reqDto.getDeliverydt();
		List<OmCloseMonitoringResDto> list = new ArrayList<OmCloseMonitoringResDto>();		
		for (int i = 0; i < saveList.size(); i++) {
			OmCloseMonitoringResDto dto = saveList.get(i);
			
			CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
        	entity.setPackagename(PAKAGE_NAME); // 패키지명
        	entity.setProcesstype(PROCESSTYPE); // 프로세스타입
        	
        	entity.setSlipdt(sDeliverydt);
        	entity.setDeliverydt(sDeliverydt);
			
			log.info("▶변환후 entity->{}",entity);
			// START.필수입력 check - 그리드 변수 등
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDccode())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getStorerkey())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"storerkey"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocno())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docno"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocline())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docline"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSlipdt())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"slipdt"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDeliverydt())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"deliverydt"}));

			// END.필수입력 check
        	insertList.add(entity);        	
        	
        	
        	// 200개마다 혹은 마지막 루프일 때 insert(3/3)
        	if (insertList.size() == chunkSize || i == saveList.size() -1) {
        		commonDao.insert(SERVICEID_PREFIX + "insertTempExcel", insertList); 
            	insertList.clear();
            }
        	
        	reqDto.setAvc_DCCODE(entity.getDccode());
		}
		
		
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE" ,"PROCESSTYPE"  ,"DCCODE"         	,"STORERKEY"			,"DOCTYPE"	,"SLIPDT"				,"DELIVERYDATE" 		};
		Object[] valueList = {PAKAGE_NAME ,PROCESSTYPE   ,reqDto.getAvc_DCCODE()  ,reqDto.getGStorerkey() ,"%"		,reqDto.getDeliverydt()	,reqDto.getDeliverydt() };
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));		
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
	 * @description : 마감기준시간 콤보데이터 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.08 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<OmCloseMonitoringResCloseTimeDto> getCloseTime(OmCloseMonitoringReqDto dto) {
		
		log.info("### parameter = "+dto.toString());		
		
		return commonDao.selectList(SERVICEID_PREFIX + "getCloseTime", dto);
	}

}
