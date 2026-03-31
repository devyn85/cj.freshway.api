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
import cjfw.wms.cm.entity.CmSyProcessTempAjEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.common.util.EtcUtil;
import cjfw.wms.wd.dto.WdShipmentETCReqDto;
import cjfw.wms.wd.dto.WdShipmentETCResTab1Dto;
import cjfw.wms.wd.dto.WdShipmentETCResTab2Dto;
import cjfw.wms.wd.dto.WdShipmentETCResTab3Dto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 고혜미 
 * @date : 2025.10.15 
 * @description : 매각출고처리 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.15 고혜미 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdShipmentETCService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "wdShipmentETCService.";
	private transient static final String SERVICEID_TEMP_PREFIX = "cmTempTableService.";
	
	private transient static final String PAKAGE_NAME = "SPAJ_ADJUSTMENT";
	private transient static final String PROCESSTYPE = "WDSHIPMENTETC";	
	private transient static final String TEMPTABLETYPE = "WD";		
	private transient static final String TEMPTABLETYPE2 = "AJ";		
	
	private final CommonDao commonDao;
	private final UserContext userContext;
	private final CmCommonService cmCommonService;

	/**
	 * @description : 매각출고처리 > 기타출고 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.15 고혜미 생성 </pre>
	 */
	public List<WdShipmentETCResTab1Dto> getTab1MasterList(WdShipmentETCReqDto dto) {
		
		List<WdShipmentETCResTab1Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1MasterList", dto);
		return list;
	}
	
	/**
	 * @description : 재고 > 재고조사 > 재고조사등록 상세 저장(5건 보다 큰 경우) Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.02 KimDongHan (liop0123@cj.net) 생성 </pre>
	 */
    public String saveMasterList(WdShipmentETCReqDto paramDto) throws Exception {
      
    	// BATCHPROCESSCONFIRM
    	// 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        WdShipmentETCReqDto reqDto = ModelMapperUtil.map(paramDto, WdShipmentETCReqDto.class);
        
        // 저장리스트
        List<WdShipmentETCResTab1Dto> saveList = reqDto.getSaveList(); 

		reqDto.setPackagename(PAKAGE_NAME);
		reqDto.setProcesstype(PROCESSTYPE);
		
		/*1. 임시테이블 삭제*/
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE2, reqDto); 
		
		int chunkSize = 200;
		List<CmSyProcessTempAjEntity> insertList = new ArrayList<>();	
        
        for (int i = 0; i < saveList.size(); i++) {
        	
        	WdShipmentETCResTab1Dto dto = saveList.get(i);
        	
        	dto.setWdDate(paramDto.getWdDate()); // 처리일자
        	dto.setIotype("WD");  
        	
			CmSyProcessTempAjEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempAjEntity.class);
			
			entity.setPackagename(PAKAGE_NAME); // 패키지명
			entity.setProcesstype(PROCESSTYPE); // 프로세스타입
			
			String columnsDto    = "DCCODE|STORERKEY|WD_DATE|ORGANIZE|AREA|ORDERTYPE|SKU|ZONE|LOC|LOT|UOM|IOTYPE|STOCKTYPE|STOCKGRADE|STOCKID|ETCQTY|POTYPE|REASONCODE|REASONMSG|COSTCD|OTHER05|CUSTKEY";                                                      // 그리드 변수
			String columnsEntity = "DCCODE|STORERKEY|DOCDT|ORGANIZE|AREA|ORDERTYPE|SKU|ZONE|LOC|LOT|UOM|IOTYPE|STOCKTYPE|STOCKGRADE|STOCKID|ORDERQTY|OTHER01|OTHER02|OTHER03|OTHER04|OTHER05|CUSTKEY";     // 컬럼명
			entity = (CmSyProcessTempAjEntity) EtcUtil.conversionEntityToAsis(dto, entity, columnsDto, columnsEntity);
			
			// START.필수입력 check - 그리드 변수 생략함.
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
		String[] keyList   = {"PROCESSTYPE", "WD_DATE"};
		Object[] valueList = {PROCESSTYPE, paramDto.getWdDate()};
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));		
		int rv = cmCommonService.saveProcedure(reqDto); 
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_057", new String[]{"ST_INQUIRY"}) + resultMessage); // {0} 저장 시 문제가 발생했습니다.  재고조사등록
		}
		/*END.PAKAGE 호출*/ 
		
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
	
	/**
	 * @description : 매각출고처리 - 기타출고 처리 저장[매각]
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.17 고혜미 생성 </pre>
	 */
	public String saveMasterList01(WdShipmentETCReqDto paramDto) throws Exception{	
        List<WdShipmentETCResTab1Dto> saveList = paramDto.getSaveList(); // 저장리스트
        String disposeDate = paramDto.getDisposeDate();
        log.info("saveList ==> {}", saveList);
        log.info("disposeDate ==> {}", disposeDate);

    	for( var dto : saveList) {
    		dto.setDisposeDate(disposeDate);
    		var entity = ModelMapperUtil.map(dto, userContext, WdShipmentETCResTab1Dto.class);
			commonDao.selectOne(SERVICEID_PREFIX +"insertTab1MasterList", entity);
    	}
    	
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	
	/**
	 * @description : 매각출고처리 - 기타출고 처리 저장[기부]
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.16 고혜미 생성 </pre>
	 */	
	public String saveMasterList02(WdShipmentETCReqDto paramDto) throws Exception{		
    	String userId = paramDto.getGUserId();
    	String userSpId = paramDto.getGSpid();
    	String processtype = PROCESSTYPE;
    	String avcCommand = paramDto.getAvc_COMMAND(); // 패키지가 수행할 커맨드
    	String tempTableType = TEMPTABLETYPE; // 임시테이블 유형(ex. OM, WD, DP...)
    	
		String docdt = paramDto.getDocdt(); // 처리일자
		String potype = paramDto.getPotype(); // 처리유형
		String reasoncode = paramDto.getReasoncode(); // 처리사유
		String reasonmsg = paramDto.getReasonmsg(); // 세부사유
		String other05 = paramDto.getOther05(); // 물류귀책배부
		String costcd = paramDto.getCostcd(); // 귀속부서코드
		String custkey = paramDto.getCustkey(); // 거래처코드
		
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        // 프로시저 파라미터 세팅
        // PKG 파라마터 세팅 - 공통(1/4)
        // 화면에서 넘겨줄 배치 파라미터와 변수들을 서버에서 넘겨준다.
        WdShipmentETCReqDto reqDto = new WdShipmentETCReqDto();
        reqDto.setAvc_COMMAND(avcCommand);
        reqDto.setProcesstype(processtype);
        reqDto.setTemptabletype(tempTableType);
        reqDto.setDocdt(docdt);
        reqDto.setPackagename(PAKAGE_NAME);
        reqDto.setAvc_SYSTEM(paramDto.getGSystem());
        reqDto.setAvc_DCCODE(paramDto.getGDccode());
        reqDto.setAvc_STORERKEY(paramDto.getGStorerkey());
        reqDto.setAvc_WORKER(paramDto.getGUserId());
        reqDto.setAi_SPID(paramDto.getGSpid());
        
        String columnsDto    = "DCCODE|STORERKEY|ORGANIZE|AREA|SKU|ZONE|LOC|LOT|STOCKID|STOCKGRADE|STOCKTYPE|ETCQTY|UOM|ORDERTYPE|POTYPE|REASONCODE|REASONMSG|COSTCD|OTHER05|CUSTKEY";
        String columnsEntity = "DCCODE|STORERKEY|ORGANIZE|AREA|SKU|ZONE|LOC|LOT|STOCKID|STOCKGRADE|STOCKTYPE|ORDERQTY|UOM|ORDERTYPE|OTHER01|OTHER02|OTHER03|OTHER04|OTHER05|CUSTKEY";        
        
        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {
        	    "PROCESSTYPE",
        	    "DOCDT",
        	    "i_ERR"
        	    ,"vc_RESULTMSG"
        	};

    	Object[] valueList = {
			reqDto.getProcesstype(),            // PROCESSTYPE = AJ_ADJUSTMENTREQ_DU
    	    reqDto.getDocdt(),                  // DOCDT = tab1.div_required.cal_APPRREQDT
    	    null,
    	    null
    	};        
    	reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
    	
        List<WdShipmentETCResTab1Dto> saveList = paramDto.getSaveList(); // 저장리스트
        log.info("saveList ==> {}", saveList);
		
        // temp table 데이터 삭제 진행
    	CmSyProcessTempAjEntity tempDeleteReqDto = ModelMapperUtil.map(reqDto, userContext, CmSyProcessTempAjEntity.class);
    	tempDeleteReqDto.setSpid(reqDto.getGSpid());
    	commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTempAJ", tempDeleteReqDto);
    	int chunkSize = 200;
    	
    	List<CmSyProcessTempAjEntity> insertList = new ArrayList<CmSyProcessTempAjEntity>();
    	
    	for (int i = 0; i < saveList.size(); i++) {
    		WdShipmentETCResTab1Dto saveDto = saveList.get(i);
    		CmSyProcessTempAjEntity entity = ModelMapperUtil.map(saveDto, userContext, CmSyProcessTempAjEntity.class);
            
            entity = (CmSyProcessTempAjEntity) EtcUtil.conversionEntityToAsis(saveDto, entity, columnsDto, columnsEntity);
            
            entity.setProcesstype(processtype);
            entity.setTemptabletype(tempTableType);
            entity.setDocdt(docdt);
            entity.setSpid(userSpId);
            entity.setProcesscreator(userId);            
            entity.setListno("DS_HEADER");
            entity.setIotype("WD");
			
			insertList.add(entity);
			log.info("▶entity------------->{}",entity);
			
    	    // 200개마다 혹은 마지막 루프일 때 insert
			 if (insertList.size() == chunkSize || i == saveList.size() -1) {
    	        commonDao.insert(SERVICEID_TEMP_PREFIX + "insertSyProcessTempAj", insertList);
    	        insertList.clear(); // 다음 배치 준비
    	    }
    	}
    	
        commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, reqDto);

        // 프로시저 OUT Parameter(3/4)
        resultCode    = (String)reqDto.getResultCode();
        resultMessage = (String)reqDto.getResultMessage();

        log.info("resultCode->{}",resultCode);
        log.info("resultMessage->{}",resultMessage);
        
       
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 매각출고처리 > 매각내역 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.17 고혜미 생성 </pre>
	 */
	public List<WdShipmentETCResTab3Dto> getTab3MasterList(WdShipmentETCReqDto dto) {
		
		List<WdShipmentETCResTab3Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab3MasterList", dto);
		return list;
	}	
	
	/**
	 * @description : 매각출고처리 - 매각내역 처리 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.20 고혜미 생성 </pre>
	 */
	public String saveMasterList03(WdShipmentETCReqDto paramDto) throws Exception{	
        List<WdShipmentETCResTab3Dto> saveList = paramDto.getSaveList3(); // 저장리스트

        log.info("saveList ==> {}", saveList);

    	for( var dto : saveList) {
    		var entity = ModelMapperUtil.map(dto, userContext, WdShipmentETCResTab3Dto.class);
			commonDao.selectOne(SERVICEID_PREFIX +"saveTab3MasterList", entity);
    	}
    	
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	/**
	 * @description : 매각출고처리 > 처리결과 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.16 고혜미 생성 </pre>
	 */
	public List<WdShipmentETCResTab2Dto> getTab2MasterList(WdShipmentETCReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<WdShipmentETCResTab2Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab2MasterList", dto);
		return list;
	}
	
}
