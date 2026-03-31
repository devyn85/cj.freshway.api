package cjfw.wms.st.service;

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
import cjfw.wms.cm.entity.CmSyProcessTempStEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.common.util.EtcUtil;
import cjfw.wms.st.dto.StLocMoveRPReqDto;
import cjfw.wms.st.dto.StLocMoveRPResPrintDto;
import cjfw.wms.st.dto.StLocMoveRPResSaveDto;
import cjfw.wms.st.dto.StLocMoveRPResTab1Dto;
import cjfw.wms.st.dto.StLocMoveRPResTab2Dto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.16 
 * @description : 출고재고보충(수원3층) Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.16 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StLocMoveRPService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "stLocMoveRPService.";
	private transient static final String SERVICEID_TEMP_PREFIX = "cmTempTableService.";	
	private transient static final String PAKAGE_NAME = "SPST_REPLENISHMENT";
	/** 프로세스타임 */
	private transient static final String PROCESSTYPE = "ST_REPLENISHMENT";	
	/** 임시테이블 타입 */
	private transient static final String TEMPTABLETYPE = "ST";		
	
	/** 공통.CommonDao */
	private final CommonDao commonDao;
	/** 공통.UserContext */
	private final UserContext userContext;
	/** 공통.service */
	private final CmCommonService cmCommonService;

	/**
	 * @description : 보충생성 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<StLocMoveRPResTab1Dto> getTab1MasterList(StLocMoveRPReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<StLocMoveRPResTab1Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1MasterList", dto);
		return list;
	}
	
	/**
	 * @description : ASRS결과 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<StLocMoveRPResTab2Dto> getTab2MasterList(StLocMoveRPReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<StLocMoveRPResTab2Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab2MasterList", dto);
		return list;
	}
	

	/**
	 * @description : 보충생성 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveCreate(StLocMoveRPReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
		
        // 파라미터 위변조 적용(paramDto->reqDto)
        StLocMoveRPReqDto reqDto = ModelMapperUtil.map(paramDto, StLocMoveRPReqDto.class);
        List<StLocMoveRPResSaveDto> saveList = reqDto.getSaveCreationList(); // 저장리스트        
        
        log.info("▶saveList.size->{}",saveList);
        
        StLocMoveRPResSaveDto dto = saveList.get(0);	
        
    	// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
		
    	log.info("▶dto->{}",dto);
    	
    	// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {  "PROCEDURE", 
								"STORERKEY", 		
								"DCCODE",  		  
								"SLIPDT", 	   
								"SKU", 		 
								"FROMZONE", 		
								"TOZONE",  
								"CUSTKEY"};
		Object[] valueList = {  PAKAGE_NAME, 
							    dto.getGStorerkey(), 
							    reqDto.getFixdccode(), 
							    dto.getSlipdt(), 
							    dto.getSku(), 
							    dto.getFromzone(), 
							    dto.getTozone() ,
							    dto.getCustkey()};
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));		
		reqDto.setAvc_DCCODE(reqDto.getFixdccode());
		int rv = cmCommonService.saveProcedure(reqDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶보충생성시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"보충생성"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		 /*END.PAKAGE 호출*/  
		      
       
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	/**
	 * @description : ASRS지시
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveDiraction(StLocMoveRPReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		StLocMoveRPReqDto reqDto = ModelMapperUtil.map(paramDto, StLocMoveRPReqDto.class);
		List<StLocMoveRPResTab1Dto> saveList = reqDto.getSaveList(); // 저장리스트        
		
		log.info("▶saveList.size->{}",saveList);
		
		StLocMoveRPResTab1Dto dto = saveList.get(0);	
		
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
		
		log.info("▶dto->{}",dto);
		
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE", "STORERKEY", 		"DCCODE",  		  "SLIPDT", 	   "SLIPNO", 		"SLIPLINE"};
		Object[] valueList = {PAKAGE_NAME, dto.getGStorerkey(), dto.getDccode(), dto.getSlipdt(), dto.getSlipno(), dto.getSlipline() };
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));		
		reqDto.setAvc_DCCODE(dto.getDccode());
		int rv = cmCommonService.saveProcedure(reqDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶지정취소시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"지정취소"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		/*END.PAKAGE 호출*/  
			
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	/**
	 * @description : ASRS지시
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveMove(StLocMoveRPReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		StLocMoveRPReqDto reqDto = ModelMapperUtil.map(paramDto, StLocMoveRPReqDto.class);
		List<StLocMoveRPResTab1Dto> saveList = reqDto.getSaveList(); // 저장리스트        
		
		log.info("▶saveList.size->{}",saveList);
		
		StLocMoveRPResTab1Dto dto = saveList.get(0);	
	
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
		
		log.info("▶dto->{}",dto);
		
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE", "STORERKEY", 		"DCCODE",  		  "SLIPDT", 	   "SLIPNO", 		"SLIPLINE"};
		Object[] valueList = {PAKAGE_NAME, dto.getGStorerkey(), dto.getDccode(), dto.getSlipdt(), dto.getSlipno(), dto.getSlipline() };
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));		
		reqDto.setAvc_DCCODE(dto.getDccode());
		int rv = cmCommonService.saveProcedure(reqDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶지정취소시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"지정취소"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		/*END.PAKAGE 호출*/  
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	/**
	 * @description : 리스트 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<StLocMoveRPResPrintDto> getPrint(StLocMoveRPReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		StLocMoveRPReqDto reqDto = ModelMapperUtil.map(paramDto, StLocMoveRPReqDto.class);
		List<StLocMoveRPResTab1Dto> saveList = reqDto.getSaveDataList(); // 저장리스트
		reqDto.setPackagename(PAKAGE_NAME);
		reqDto.setProcesstype(PROCESSTYPE);
		log.info("▶reqDto.size->{}",reqDto);
		log.info("▶saveList.size->{}",saveList);
		log.info("▶command->{}",reqDto.getAvc_COMMAND());
		
		/*1. 임시테이블 삭제*/
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
		int chunkSize = 200;
		List<CmSyProcessTempStEntity> insertList = new ArrayList<>();
		
		List<StLocMoveRPResTab1Dto> list = new ArrayList<StLocMoveRPResTab1Dto>();		
		for (int i = 0; i < saveList.size(); i++) {
			StLocMoveRPResTab1Dto dto = saveList.get(i);
			
			CmSyProcessTempStEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempStEntity.class);
        	entity.setPackagename(PAKAGE_NAME); // 패키지명
        	entity.setProcesstype(PROCESSTYPE); // 프로세스타입
			
			log.info("▶dto->{}",dto);
			String columnsDto    = "DCCODE|STORERKEY|SLIPDT|SUPPLNO|SUPPLLINE|ORDERTYPE|FROMLOC|TOLOC|SKU|STOCKID|UOM|LOTTABLE01|OPENQTY_BOX|OPENQTY_EA";
			String columnsEntity = "FROM_DCCODE|FROM_STORERKEY|WORK_NO|BATCH_NO|LIST_NO|FROM_ZONE|FROM_LOC|TO_LOC|FROM_SKU|FROM_STOCKID|FROM_UOM|FROM_LOT|ETCQTY1|ETCQTY2";
						
			entity = (CmSyProcessTempStEntity) EtcUtil.conversionEntityToAsis(dto, entity, columnsDto, columnsEntity);
			
			log.info("▶변환후 entity->{}",entity);
			// START.필수입력 check - 그리드 변수 등
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getFromDccode()))   ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"fromDccode"} )  );   // 해당 정보가 없어 처리할 수 없습니다.-{0}
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getFromStorerkey())) ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"fromStorerkey"} )  ); 
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getWorkNo()))    ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"workNo"} )  );    
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getBatchNo()))       ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"batchNo"} )  );       
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getListNo()))     ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"listNo"} )  );     
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
			log.error("▶리스트 조회시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"리스트 조회"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		/*END.PAKAGE 호출*/  
		
		List<StLocMoveRPResPrintDto> printList = commonDao.selectList(SERVICEID_PREFIX + "getPrintList", reqDto);
		
		return printList;
	}	

}
