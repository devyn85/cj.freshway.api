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
import cjfw.wms.common.util.EtcUtil;
import cjfw.wms.wd.dto.WdSerialOrderSTOReqDto;
import cjfw.wms.wd.dto.WdSerialOrderSTOResPickingDto;
import cjfw.wms.wd.dto.WdSerialOrderSTOResTab1Dto;
import cjfw.wms.wd.dto.WdSerialOrderSTOResTab2Dto;
import cjfw.wms.wd.dto.WdSerialOrderSTOResTab2ShortageDto;
import cjfw.wms.wd.dto.WdSerialOrderSTOResTab2WdDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.09.29 
 * @description : 이력STO출고처리 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdSerialOrderSTOService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "wdSerialOrderSTOService.";
	private transient static final String PAKAGE_NAME = "SPOM_CREATION";
	private transient static final String PAKAGE_NAME_SHIPMENT = "SPWD_SHIPMENT";
	private transient static final String PROCESSTYPE_STO_SN = "WD_BATCHSTO_SN";
	private transient static final String PROCESSTYPE_CONFIRM_STO = "WD_BATCHCONFIRM_STO";
	private transient static final String PROCESSTYPE_SOSTO = "WD_DISTRIBUTE_SOSTO";
	private transient static final String TEMPTABLETYPE = "WD";
	
	private final CommonDao commonDao;
	private final UserContext userContext;
	private final CmCommonService cmCommonService;

	/**
	 * @description : 이력STO출고처리-STO생성 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdSerialOrderSTOResTab1Dto> getTab1MasterList(WdSerialOrderSTOReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<WdSerialOrderSTOResTab1Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1MasterList", dto);
		return list;
	}
	/**
	 * @description : 이력STO출고처리-STO출고확정 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdSerialOrderSTOResTab2Dto> getTab2MasterList(WdSerialOrderSTOReqDto dto) {
		
		log.info("##### parameter = "+dto.toString());		
		
		List<WdSerialOrderSTOResTab2Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab2MasterList", dto);
		return list;
	}
	
	/**
	 * @description : 이력STO출고처리-출고대상 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdSerialOrderSTOResTab2WdDto> getTab2DetailWDList(WdSerialOrderSTOReqDto dto) { 
		
		log.info("##### parameter = "+dto.toString());		
		
		List<WdSerialOrderSTOResTab2WdDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab2DetailWDList", dto);
		return list;
	}
	
	/**
	 * @description : 이력STO출고처리-결품대상 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdSerialOrderSTOResTab2ShortageDto> getTab2DetailShortageList(WdSerialOrderSTOReqDto dto) { 
		
		log.info("##### parameter = "+dto.toString());		
		
		List<WdSerialOrderSTOResTab2ShortageDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab2DetailShortageList", dto);
		return list;
	}
	
	/**
	 * @description : 이력STO출고처리- 피킹 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdSerialOrderSTOResPickingDto> getPickingList(WdSerialOrderSTOReqDto dto) { 
		
		log.info("##### parameter = "+dto.toString());		
		
		List<WdSerialOrderSTOResPickingDto> list = commonDao.selectList(SERVICEID_PREFIX + "getPickingList", dto);
		return list;
	}
	
	
	/**
	 * @description : 이력STO출고처리-STO생성 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveCreationSTOList(WdSerialOrderSTOReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdSerialOrderSTOReqDto reqDto = ModelMapperUtil.map(paramDto, WdSerialOrderSTOReqDto.class);
		List<WdSerialOrderSTOResTab1Dto> saveList = reqDto.getSaveCreationSTOList(); // 저장리스트
		reqDto.setPackagename(PAKAGE_NAME);
		reqDto.setProcesstype(PROCESSTYPE_STO_SN);
		log.info("▶reqDto.size->{}",reqDto);
		log.info("▶saveList.size->{}",saveList);
		log.info("▶command->{}",reqDto.getAvc_COMMAND());
		
		/*1. 임시테이블 삭제*/
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
		int chunkSize = 200;
		List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
		
		List<WdSerialOrderSTOResTab1Dto> list = new ArrayList<WdSerialOrderSTOResTab1Dto>();		
		for (int i = 0; i < saveList.size(); i++) {
			WdSerialOrderSTOResTab1Dto dto = saveList.get(i);
			
			CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
        	entity.setPackagename(PAKAGE_NAME); // 패키지명
        	entity.setProcesstype(PROCESSTYPE_STO_SN); // 프로세스타입
        	
        	log.info("▶dto->{}",dto);
			String columnsDto    = "SERIALKEY";
			String columnsEntity = "WAVEKEY";
			
			entity.setWavekey(dto.getSerialkey());
			
			//entity = (CmSyProcessTempWdEntity) EtcUtil.conversionEntityToAsis(dto, entity, columnsDto, columnsEntity);
						
			log.info("▶변환후 entity->{}",entity);
			// START.필수입력 check - 그리드 변수 등
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getWavekey())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"wavekey"}));

			// END.필수입력 check
        	insertList.add(entity);
        	
        	// 200개마다 혹은 마지막 루프일 때 insert(3/3)
        	if (insertList.size() == chunkSize || i == saveList.size() -1) {
        		commonDao.insert(SERVICEID_PREFIX + "insertTempExcel2", insertList); 
            	insertList.clear();
            }
		}
		
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
		
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE", "PROCESSTYPE"};
		Object[] valueList = {PAKAGE_NAME, PROCESSTYPE_STO_SN};
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
			log.error("▶STO생성 저장시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"STO생성 저장"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	/**
	 * @description : 이력STO출고처리-STO생성 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveBatchConfirm(WdSerialOrderSTOReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdSerialOrderSTOReqDto reqDto = ModelMapperUtil.map(paramDto, WdSerialOrderSTOReqDto.class);
		List<WdSerialOrderSTOResTab2Dto> saveList = reqDto.getSaveBatchConfirmList(); // 저장리스트
		reqDto.setPackagename(PAKAGE_NAME_SHIPMENT);
		reqDto.setProcesstype(PROCESSTYPE_STO_SN);
		log.info("▶reqDto.size->{}",reqDto);
		log.info("▶saveList.size->{}",saveList);
		log.info("▶command->{}",reqDto.getAvc_COMMAND());

        log.info("▶saveList.size->{}",saveList);       
        WdSerialOrderSTOResTab2Dto dto = saveList.get(0);
        
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME_SHIPMENT);
		        
        log.info("▶dto->{}",dto);
        
        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
    	String[] keyList   = {	 "PROCEDURE" 
    							,"DCCODE"    	 
    							,"STORERKEY"	    
    							,"DOCDT"		 
    							,"DOCNO"	
    						 };
		Object[] valueList = {	 PAKAGE_NAME_SHIPMENT 
								,dto.getDccode() 
								,dto.getStorerkey()
								,dto.getDocdt() 
								,dto.getDocno() 
							  };
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
			log.error("▶STO출고확정 저장시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"STO출고확정 저장"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	/**
	 * @description : 이력STO출고처리-출고대상확정 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveBatchConfirmLine(WdSerialOrderSTOReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdSerialOrderSTOReqDto reqDto = ModelMapperUtil.map(paramDto, WdSerialOrderSTOReqDto.class);
		List<WdSerialOrderSTOResTab2WdDto> saveList = reqDto.getSaveBatchConfirmLine(); // 저장리스트
		reqDto.setPackagename(PAKAGE_NAME_SHIPMENT);
		reqDto.setProcesstype(PROCESSTYPE_CONFIRM_STO);
		log.info("▶reqDto.size->{}",reqDto);
		log.info("▶saveList.size->{}",saveList);
		log.info("▶command->{}",reqDto.getAvc_COMMAND());


		/*1. 임시테이블 삭제*/
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
		int chunkSize = 200;
		List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
		
		List<WdSerialOrderSTOResTab2WdDto> list = new ArrayList<WdSerialOrderSTOResTab2WdDto>();		
		for (int i = 0; i < saveList.size(); i++) {
			WdSerialOrderSTOResTab2WdDto dto = saveList.get(i);
			
			CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
        	entity.setPackagename(PAKAGE_NAME_SHIPMENT); // 패키지명
        	entity.setProcesstype(PROCESSTYPE_CONFIRM_STO); // 프로세스타입
        	
        	log.info("▶dto->{}",dto);
			String columnsDto    = "TASKSYSTEM|SERIALKEY|TRANQTY";
			String columnsEntity = "TASKSYSTEM|WAVEKEY|CONFIRMQTY";
			
			entity = (CmSyProcessTempWdEntity) EtcUtil.conversionEntityToAsis(dto, entity, columnsDto, columnsEntity);
						
			log.info("▶변환후 entity->{}",entity);
			// START.필수입력 check - 그리드 변수 등
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getTasksystem())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"tasksystem"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getWavekey())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"wavekey"}));
			if(entity.getConfirmqty() == null   ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"confirmqty"}));

			// END.필수입력 check
        	insertList.add(entity);
        	
        	// 200개마다 혹은 마지막 루프일 때 insert(3/3)
        	if (insertList.size() == chunkSize || i == saveList.size() -1) {
        		commonDao.insert(SERVICEID_PREFIX + "insertTempExcel3", insertList); 
            	insertList.clear();
            }
		}
		
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME_SHIPMENT);
		
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE", "PROCESSTYPE"};
		Object[] valueList = {PAKAGE_NAME_SHIPMENT, PROCESSTYPE_CONFIRM_STO};
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
			log.error("▶출고대상확정 저장시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"출고대상확정 저장"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	/**
	 * @description : 이력STO출고처리-결품대상확정 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveBatchCancelLine(WdSerialOrderSTOReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdSerialOrderSTOReqDto reqDto = ModelMapperUtil.map(paramDto, WdSerialOrderSTOReqDto.class);
		List<WdSerialOrderSTOResTab2ShortageDto> saveList = reqDto.getSaveBatchCancelLine(); // 저장리스트
		reqDto.setPackagename(PAKAGE_NAME_SHIPMENT);
		reqDto.setProcesstype(PROCESSTYPE_CONFIRM_STO);
		log.info("▶reqDto.size->{}",reqDto);
		log.info("▶saveList.size->{}",saveList);
		log.info("▶command->{}",reqDto.getAvc_COMMAND());
		
		
		/*1. 임시테이블 삭제*/
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
		int chunkSize = 200;
		List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
		
		List<WdSerialOrderSTOResTab2ShortageDto> list = new ArrayList<WdSerialOrderSTOResTab2ShortageDto>();		
		for (int i = 0; i < saveList.size(); i++) {
			WdSerialOrderSTOResTab2ShortageDto dto = saveList.get(i);
			
			CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
			entity.setPackagename(PAKAGE_NAME_SHIPMENT); // 패키지명
			entity.setProcesstype(PROCESSTYPE_CONFIRM_STO); // 프로세스타입
			
			log.info("▶dto->{}",dto);
			String columnsDto    = "TASKSYSTEM|SERIALKEY|TRANQTY|REASONCODE|REASONMSG";
			String columnsEntity = "TASKSYSTEM|WAVEKEY|ETCQTY1|OTHER01|OTHER02";
			
			entity = (CmSyProcessTempWdEntity) EtcUtil.conversionEntityToAsis(dto, entity, columnsDto, columnsEntity);
			
			log.info("▶변환후 entity->{}",entity);
			// START.필수입력 check - 그리드 변수 등
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getTasksystem())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"tasksystem"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getWavekey())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"wavekey"}));
			if(entity.getEtcqty1() == null   ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"etcqty1"}));
			
			// END.필수입력 check
			insertList.add(entity);
			
			// 200개마다 혹은 마지막 루프일 때 insert(3/3)
			if (insertList.size() == chunkSize || i == saveList.size() -1) {
				commonDao.insert(SERVICEID_PREFIX + "insertTempExcel", insertList); 
				insertList.clear();
			}
		}
		
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME_SHIPMENT);
		
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE", "PROCESSTYPE"};
		Object[] valueList = {PAKAGE_NAME_SHIPMENT, PROCESSTYPE_CONFIRM_STO};
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
			log.error("▶결품대상확정 저장시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"결품대상확정 저장"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	

	/**
	 * @description : 이력STO출고처리-SO&STO분리
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveDistribute(WdSerialOrderSTOReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdSerialOrderSTOReqDto reqDto = ModelMapperUtil.map(paramDto, WdSerialOrderSTOReqDto.class);
		List<WdSerialOrderSTOResTab2WdDto> saveList = reqDto.getSaveDistributeList(); // 저장리스트
		reqDto.setPackagename(PAKAGE_NAME_SHIPMENT);
		reqDto.setProcesstype(PROCESSTYPE_SOSTO);
		log.info("▶reqDto.size->{}",reqDto);
		log.info("▶saveList.size->{}",saveList);
		log.info("▶command->{}",reqDto.getAvc_COMMAND());


		/*1. 임시테이블 삭제*/
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
		int chunkSize = 200;
		List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
		
		List<WdSerialOrderSTOResTab2WdDto> list = new ArrayList<WdSerialOrderSTOResTab2WdDto>();		
		for (int i = 0; i < saveList.size(); i++) {
			WdSerialOrderSTOResTab2WdDto dto = saveList.get(i);
			
			CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
        	entity.setPackagename(PAKAGE_NAME_SHIPMENT); // 패키지명
        	entity.setProcesstype(PROCESSTYPE_SOSTO); // 프로세스타입
        	
        	log.info("▶dto->{}",dto);
			String columnsDto    = "TASKSYSTEM|SERIALKEY";
			String columnsEntity = "TASKSYSTEM|WAVEKEY";
			
			entity = (CmSyProcessTempWdEntity) EtcUtil.conversionEntityToAsis(dto, entity, columnsDto, columnsEntity);
						
			log.info("▶변환후 entity->{}",entity);
			// START.필수입력 check - 그리드 변수 등
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getTasksystem())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"tasksystem"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getWavekey())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"wavekey"}));

			// END.필수입력 check
        	insertList.add(entity);
        	
        	// 200개마다 혹은 마지막 루프일 때 insert(3/3)
        	if (insertList.size() == chunkSize || i == saveList.size() -1) {
        		commonDao.insert(SERVICEID_PREFIX + "insertTempExcel2", insertList); 
            	insertList.clear();
            }
		}
		
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME_SHIPMENT);
		
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE", "PROCESSTYPE"};
		Object[] valueList = {PAKAGE_NAME_SHIPMENT, PROCESSTYPE_SOSTO};
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
			log.error("▶SSO&STO분리시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"SO&STO분리"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
}
