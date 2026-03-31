package cjfw.wms.st.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cjfw.wms.ib.service.IbAllWeightService;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.wms.cm.entity.CmSyProcessTempAjEntity;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.common.util.EtcUtil;
import cjfw.wms.st.dto.StAdjustmentCommCdResDto;
import cjfw.wms.st.dto.StAdjustmentRequestElectApprovalDto;
import cjfw.wms.st.dto.StAdjustmentRequestReqDto;
import cjfw.wms.st.dto.StAdjustmentRequestResDto;
import cjfw.wms.webservice.elecAmt.DT_MM0090_SCM;
import cjfw.wms.webservice.elecAmt.DT_MM0090_SCMIF_ST_STOCKAMT;
import cjfw.wms.webservice.elecAmt.DT_MM0090_SCM_responseIF_ST_STOCKAMT_RET;
import cjfw.wms.webservice.elecAmt.SI_MM0090_SCM_SOProxy;
import cjfw.wms.webservice.sso.webservice.SSO_NON_SAP_EAIPortTypeProxy;
import cjfw.wms.webservice.sso.webservice.xsd.SSO_NON_SAP_REQUEST;
import cjfw.wms.webservice.sso.webservice.xsd.SSO_NON_SAP_RESPONSE;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.10.13
 * @description : 재고조정 요청/처리 Service
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.13 JiHoPark 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StAdjustmentRequestService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "stAdjustmentRequestService.";
	private transient static final String SERVICEID_TEMP_PREFIX = "cmTempTableService.";

	private final CommonDao commonDao;
    /**정산.service 금액가져오기*/
    private final IbAllWeightService ibAllWeightService;

	/**
	 * @description : 재고조정 요청/처리 - zone 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.13 JiHoPark  생성 </pre>
	 */
	public List<StAdjustmentCommCdResDto> getZoneList() {
		return commonDao.selectList(SERVICEID_PREFIX + "getZoneList");
	}

	/**
	 * @description : 재고조정 요청/처리 - 재고조정 요청 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.14 JiHoPark  생성 </pre>
	 */
	public List<StAdjustmentRequestResDto> getMasterList(StAdjustmentRequestReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}

	/**
	 * @description : 재고조정 요청/처리 - 재고조정 결재 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.16 JiHoPark  생성 </pre>
	 */
	public List<StAdjustmentRequestElectApprovalDto> getMasterList3(StAdjustmentRequestReqDto dto) {
		List<StAdjustmentRequestElectApprovalDto> resultList = commonDao.selectList(SERVICEID_PREFIX + "getMasterList3", dto);
		if("Y".equals(dto.getViewPriceYn())) {
			ibAllWeightService.fetchSapPrice(resultList);
		}
		return resultList;
	}

	/**
	 * @description : 재고조정 요청/처리 - 재고조정 처리 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.15 JiHoPark  생성 </pre>
	 */
	public List<StAdjustmentRequestResDto> getMasterList4(StAdjustmentRequestReqDto dto) {
		List<StAdjustmentRequestResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getMasterList4", dto);
		if("Y".equals(dto.getViewPriceYn())) {
			ibAllWeightService.fetchSapPrice(list);
		}
		return list;
	}

	/**
	 * @description : 재고조정 요청/처리 - 재고조정 요청 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.14 JiHoPark  생성 </pre>
	 */
	public List<StAdjustmentRequestResDto> saveMasterList1(StAdjustmentRequestReqDto dto) {
    	String userId = dto.getGUserId();
    	String userSpId = dto.getGSpid();
    	String docdt = dto.getDocdt(); // 조정일자

    	String procedure = dto.getProcedure();
    	String processtype = dto.getProcesstype();
		String ifSendType = dto.getIfSendType();
		String workprocesscode = dto.getWorkprocesscode();
		String omsFlag = dto.getOmsFlag();
		String stocktranstype = dto.getStocktranstype();

		String columnsDto    = "DCCODE|STORERKEY|ORGANIZE|AREA|ZONE|LOC|SKU|UOM|LOT|STOCKID|STOCKGRADE|STOCKTYPE|QTY|OPENQTY|QTYALLOCATED|QTYPICKED|TRANQTY|LOTTABLE01|REASONCODE|IMPUTETYPE|PROCESSMAIN|COSTCD|CUSTKEY|OTHER03|OTHER05";
		String columnsEntity = "DCCODE|STORERKEY|ORGANIZE|AREA|ZONE|LOC|SKU|UOM|LOT|STOCKID|STOCKGRADE|STOCKTYPE|ORDERQTY|OPENQTY|PROCESSQTY|WORKQTY|CONFIRMQTY|MIXBOXKEY|TASKKEY|WAVEKEY|INVOICENO|OTHER01|CUSTKEY|OTHER03|OTHER05";

        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        String returnMessage = "";

        int chunkSize = 200;

        // temp table 데이터 삭제 진행
        CmSyProcessTempAjEntity tempAjDto = new CmSyProcessTempAjEntity();
        tempAjDto.setGUserId(userId);
        tempAjDto.setProcesstype(processtype);
        tempAjDto.setGSpid(userSpId);
    	commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTempAJ", tempAjDto);

    	// temp table 데이터 생성
    	List<CmSyProcessTempAjEntity> insertList = new ArrayList<CmSyProcessTempAjEntity>();
        List<StAdjustmentRequestResDto> saveList = dto.getSaveMasterList1(); // 저장리스트
    	for (int i = 0; i < saveList.size(); i++) {
    		StAdjustmentRequestResDto saveDto = saveList.get(i);

    		tempAjDto = new CmSyProcessTempAjEntity();
    		tempAjDto = (CmSyProcessTempAjEntity) EtcUtil.conversionEntityToAsis(saveDto, tempAjDto, columnsDto, columnsEntity);
    		tempAjDto.setProcesstype(processtype);
    		tempAjDto.setProcesscreator(userId);
    		tempAjDto.setSpid(userSpId);
    		tempAjDto.setAddwho(userId);
    		tempAjDto.setEditwho(userId);

			insertList.add(tempAjDto);

    	    // 200개마다 혹은 마지막 루프일 때 insert
    	    if (insertList.size() == chunkSize) {
    	        commonDao.insert(SERVICEID_TEMP_PREFIX + "insertSyProcessTempAj", insertList);
    	        insertList.clear(); // 다음 배치 준비
    	    }

    	}

    	// 남은 데이터 insert
    	if (insertList.size() > 0) {
    		commonDao.insert(SERVICEID_TEMP_PREFIX + "insertSyProcessTempAj", insertList);
    	}

    	// package proceduce 실행
        if (insertList.size() > 0) {
        	StAdjustmentRequestResDto saveDto = new StAdjustmentRequestResDto();

	        // PKG 파라마터 세팅 - 공통(1/4)
	        ProcedureParametersFactory.initParamDto(dto, saveDto, procedure);

	        // PKG 파라마터 세팅
        	String[] keyList = { "PROCEDURE"
        			, "PROCESSTYPE"
        			, "PROCESSCREATOR"
        			, "SPID"
        			, "DOCDT"
        			, "IF_SEND_TYPE"
        			, "WORKPROCESSCODE"
        			, "OMS_FLAG"
        			, "STOCKTRANSTYPE"
                    };


	        Object[] valueList = { procedure
				        		, processtype
				        		, userId
				        		, userSpId
				        		, docdt
				        		, ifSendType
				        		, workprocesscode
				        		, omsFlag
				        		, stocktranstype
	        		};

	        saveDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
	        commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, saveDto);

	        // 프로시저 OUT Parameter(3/4)
	        resultCode    = (String)saveDto.getResultCode();
	        resultMessage = (String)saveDto.getResultMessage();
	        returnMessage = (String)saveDto.getReturnMessage();

	        // 프로시저 Exception 처리(4/4)
	        if(!"0".equals(resultCode)){
	            log.error("▶재고조정 요청/처리 - 재고조정 요청 저장 오류발생 ");
	            throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
	        }
	    }

        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList2", dto);

	}

	/**
	 * @description : 재고조정 요청/처리 - 재고조정 결재 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.16 JiHoPark  생성 </pre>
	 */
    public String saveMasterList3(StAdjustmentRequestReqDto dto) {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        String returnMessage = "";

        List<StAdjustmentRequestElectApprovalDto> deleteList = dto.getDeleteMasterList3(); // 삭제리스트

        if (deleteList.size() > 0) {
        	String[] keyList = {
        			  "DCCODE"
        			, "STORERKEY"
        			, "ORGANIZE"
        			, "AREA"
        			, "DOCDT"
        			, "DOCNO"
        			, "DOCLINE"
        			, "SLIPDT"
        			, "SLIPNO"
        			, "SLIPLINE"
        			, "ORDERTYPE"
        			, "SLIPTYPE"
        			, "SKU"
        			, "UOM"
        			, "TRANQTY"
        			, "STOCKID"
        			, "STOCKGRADE"
        			, "LOC"
        			, "LOT"
        			, "IOTYPE"
                    };

            for (StAdjustmentRequestElectApprovalDto delDto : deleteList) {
                // PKG 파라마터 세팅 - 공통(1/4)
                ProcedureParametersFactory.initParamDto(dto, delDto, "SPAJ_REQUEST");

                // PKG 파라마터 세팅 - 업무(2/4)
                Object[] valueList = {delDto.getDccode()
                                    , delDto.getStorerkey()
                                    , delDto.getOrganize()
                                    , delDto.getArea()
                                    , delDto.getDocdt()
                                    , delDto.getDocno()
                                    , delDto.getDocline()
                                    , delDto.getSlipdt()
                                    , delDto.getSlipno()
                                    , delDto.getSlipline()
                                    , delDto.getOrdertype()
                                    , delDto.getSliptype()
                                    , delDto.getSku()
                                    , delDto.getUom()
                                    , delDto.getTranqty()
                                    , delDto.getStockid()
                                    , delDto.getStockgrade()
                                    , delDto.getLoc()
                                    , delDto.getLot()
                                    , delDto.getIotype()
                                    };

                delDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, delDto);

                // 프로시저 OUT Parameter(3/4)
                resultCode    = (String)delDto.getResultCode();
                resultMessage = (String)delDto.getResultMessage();
                returnMessage = (String)delDto.getReturnMessage();

                log.info("resultCode->{}",resultCode);
                log.info("resultMessage->{}",resultMessage);
                log.info("returnMessage->{}",returnMessage);

                // 프로시저 Exception 처리(4/4)
                if(!"0".equals(resultCode)){
                    log.error("▶재고조정 요청/처리 - 재고조정 결재 삭제 -> 삭제 오류 발생 ");
                    throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                }
            }
        }

		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

    /**
	 * @description : 재고조정 요청/처리 - 재고조정 결재 전자결재
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.16. JiHoPark  생성 </pre>
	 */
    public String saveElectApproval(StAdjustmentRequestReqDto dto) {
    	String userId = dto.getGUserId();
    	String userSpId = dto.getGSpid();
    	String userNo = dto.getGUserNo();

    	String procedure = dto.getProcedure();
    	String processtype = dto.getProcesstype();

    	String columnsDto    = "DCCODE|STORERKEY|ORGANIZE|AREA|SKU|UOM|ORDERQTY|DOCDT|DOCNO|DOCLINE|ORDERTYPE|SLIPDT|SLIPNO|SLIPLINE|SLIPTYPE|IOTYPE|STOCKAMT|PRICE|OTHER04|OTHER02|OTHER03|WEIGHT|REASONCODE|LOC";
		String columnsEntity = "DCCODE|STORERKEY|ORGANIZE|AREA|SKU|UOM|ORDERQTY|DOCDT|DOCNO|DOCLINE|ORDERTYPE|SLIPDT|SLIPNO|SLIPLINE|SLIPTYPE|IOTYPE|ETCQTY1|ETCQTY2|OTHER04|OTHER02|OTHER03|INVOICEQTY|WAVEKEY|LOC";

        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        String returnMessage = "";

        int chunkSize = 200;

        // temp table 데이터 삭제
    	CmSyProcessTempAjEntity tempAjDto = new CmSyProcessTempAjEntity();
    	tempAjDto.setGUserId(userId);
    	tempAjDto.setProcesstype(processtype);
    	tempAjDto.setGSpid(userSpId);
    	commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTempAJ", tempAjDto);

    	// temp table 데이터 생성
    	List<CmSyProcessTempAjEntity> insertList = new ArrayList<CmSyProcessTempAjEntity>();
    	List<StAdjustmentRequestElectApprovalDto> electApprList = dto.getSaveElectApproval(); // 결재 목록

    	for (int i = 0; i < electApprList.size(); i++) {
    		StAdjustmentRequestElectApprovalDto electDto = electApprList.get(i);

    		tempAjDto = new CmSyProcessTempAjEntity();
    		tempAjDto = (CmSyProcessTempAjEntity) EtcUtil.conversionEntityToAsis(electDto, tempAjDto, columnsDto, columnsEntity);
			tempAjDto.setProcesstype(processtype);
			tempAjDto.setProcesscreator(userId);
			tempAjDto.setSpid(userSpId);
			tempAjDto.setAddwho(userId);
			tempAjDto.setEditwho(userId);

    	    insertList.add(tempAjDto);

    	    // 200개마다 혹은 마지막 루프일 때 insert
    	    if (insertList.size() == chunkSize) {
    	        commonDao.insert(SERVICEID_TEMP_PREFIX + "insertSyProcessTempAj", insertList);
    	        insertList.clear(); // 다음 배치 준비
    	    }

    	}

    	// 남은 데이터 insert
    	if (insertList.size() > 0) {
    		commonDao.insert(SERVICEID_TEMP_PREFIX + "insertSyProcessTempAj", insertList);
    	}

        if (electApprList.size() > 0) {
        	StAdjustmentRequestElectApprovalDto electApprovalDto = new StAdjustmentRequestElectApprovalDto();
        	ProcedureParametersFactory.initParamDto(dto, electApprovalDto, procedure);

        	String[] keyList = { "PROCEDURE"
        			, "PROCESSTYPE"
        			, "PROCESSCREATOR"
        			, "SPID"
                    };

	        Object[] valueList = { procedure
				        		, processtype
				        		, userId
				        		, userSpId
	        		};

	        electApprovalDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
	        commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, electApprovalDto);

	        // 프로시저 OUT Parameter(3/4)
	        resultCode    = (String)electApprovalDto.getResultCode();
	        resultMessage = (String)electApprovalDto.getResultMessage();
	        returnMessage = (String)electApprovalDto.getReturnMessage();

	        log.info("resultCode->{}",resultCode);
	        log.info("resultMessage->{}",resultMessage);
	        log.info("returnMessage->{}",returnMessage);

	        // 프로시저 Exception 처리(4/4)
	        if(!"0".equals(resultCode)){
	            log.error("▶재고조정 요청/처리 - 재고조정 결재 -> 결재진행 오류 발생 ");
	            throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
	        }

	        SSO_NON_SAP_EAIPortTypeProxy proxy = new SSO_NON_SAP_EAIPortTypeProxy();
	        String ssoId  = "";

	        try {
	        	Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");

				SSO_NON_SAP_REQUEST  reqData = new SSO_NON_SAP_REQUEST();
				reqData.setXSYS("SCM");
				reqData.setXDATS(dateFormat.format(calendar.getTime()));
				reqData.setXTIMS(timeFormat.format(calendar.getTime()));
				reqData.setXROWS(String.valueOf(1));
				reqData.setINT_SVC_NO(userNo);    // 글로벌 변수로 대체

				SSO_NON_SAP_RESPONSE response = proxy.GET_SSO_TICKET(reqData);

				ssoId = "<SSOID>" + response.getSSO_TICKET() + "</SSOID>";

	        } catch(Exception e) {
				log.error("SSO ERROR", e);
			}

	        resultMessage = "<result>" + returnMessage + ssoId + "</result>";
	        log.info("resultMessage ==> {}", resultMessage);
	    }

		return resultMessage;
	}

	/**
	 * @description : 재고조정 요청/처리 - 재고조정 처리 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.15 JiHoPark  생성 </pre>
	 */
    public String saveMasterList4(StAdjustmentRequestReqDto dto) {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        String returnMessage = "";

        String avc_COMMAND = ""; // 패키지가 수행할 커맨드
        List<StAdjustmentRequestResDto> saveList = dto.getSaveMasterList4(); // 저장리스트
        List<StAdjustmentRequestResDto> deleteList = dto.getDeleteMasterList4(); // 삭제리스트

        if (saveList.size() > 0) {
        	avc_COMMAND = "CONFIRM_DC";

        	String[] keyList = {
        			  "DCCODE"
        			, "STORERKEY"
        			, "ORGANIZE"
        			, "AREA"
        			, "SLIPDT"
        			, "SLIPNO"
        			, "SLIPLINE"
        			, "TRANQTY"
                    };

            for (StAdjustmentRequestResDto saveDto : saveList) {
                // PKG 파라마터 세팅 - 공통(1/4)
                ProcedureParametersFactory.initParamDto(dto, saveDto, "SPAJ_REQUEST");

                // PKG 파라마터 세팅 - 업무(2/4)
                Object[] valueList = {saveDto.getDccode()
                                    , saveDto.getStorerkey()
                                    , saveDto.getOrganize()
                                    , saveDto.getArea()
                                    , saveDto.getSlipdt()
                                    , saveDto.getSlipno()
                                    , saveDto.getSlipline()
                                    , saveDto.getTranqty()
                                    };

                saveDto.setAvc_COMMAND(avc_COMMAND);
                saveDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, saveDto);

                // 프로시저 OUT Parameter(3/4)
                resultCode    = (String)saveDto.getResultCode();
                resultMessage = (String)saveDto.getResultMessage();
                returnMessage = (String)saveDto.getReturnMessage();

                log.info("resultCode->{}",resultCode);
                log.info("resultMessage->{}",resultMessage);
                log.info("returnMessage->{}",returnMessage);

                // 프로시저 Exception 처리(4/4)
                if(!"0".equals(resultCode)){
                    log.error("▶재고조정 요청/처리 - 재고조정 처리 저장 -> 저장 오류 발생 ");
                    throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                }
            }
        }

        if (deleteList.size() > 0) {
        	avc_COMMAND = "APPROVAL_CANCELDC";

        	String[] keyList = {
        			  "DCCODE"
        			, "STORERKEY"
        			, "ORGANIZE"
        			, "AREA"
        			, "DOCDT"
        			, "DOCNO"
        			, "DOCLINE"
        			, "SLIPDT"
        			, "SLIPNO"
        			, "SLIPLINE"
        			, "ORDERTYPE"
        			, "SLIPTYPE"
        			, "SKU"
        			, "UOM"
        			, "TRANQTY"
        			, "STOCKID"
        			, "STOCKGRADE"
        			, "LOC"
        			, "LOT"
        			, "IOTYPE"
                    };

            for (StAdjustmentRequestResDto delDto : deleteList) {
                // PKG 파라마터 세팅 - 공통(1/4)
                ProcedureParametersFactory.initParamDto(dto, delDto, "SPAJ_REQUEST");

                // PKG 파라마터 세팅 - 업무(2/4)
                Object[] valueList = {delDto.getDccode()
                                    , delDto.getStorerkey()
                                    , delDto.getOrganize()
                                    , delDto.getArea()
                                    , delDto.getDocdt()
                                    , delDto.getDocno()
                                    , delDto.getDocline()
                                    , delDto.getSlipdt()
                                    , delDto.getSlipno()
                                    , delDto.getSlipline()
                                    , delDto.getOrdertype()
                                    , delDto.getSliptype()
                                    , delDto.getSku()
                                    , delDto.getUom()
                                    , delDto.getTranqty()
                                    , delDto.getStockid()
                                    , delDto.getStockgrade()
                                    , delDto.getLoc()
                                    , delDto.getLot()
                                    , delDto.getIotype()
                                    };

                delDto.setAvc_COMMAND(avc_COMMAND);
                delDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, delDto);

                // 프로시저 OUT Parameter(3/4)
                resultCode    = (String)delDto.getResultCode();
                resultMessage = (String)delDto.getResultMessage();
                returnMessage = (String)delDto.getReturnMessage();

                log.info("resultCode->{}",resultCode);
                log.info("resultMessage->{}",resultMessage);
                log.info("returnMessage->{}",returnMessage);

                // 프로시저 Exception 처리(4/4)
                if(!"0".equals(resultCode)){
                    log.error("▶재고조정 요청/처리 - 재고조정 처리 삭제 -> 삭제 오류 발생 ");
                    throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                }
            }
        }

		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

}
