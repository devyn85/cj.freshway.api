package cjfw.wms.st.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.entity.CmSyProcessTempAjEntity;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.common.util.EtcUtil;
import cjfw.wms.st.constants.StDisuseRequestContants;
import cjfw.wms.st.dto.StDisuseRequestApprovalResDto;
import cjfw.wms.st.dto.StDisuseRequestProcessResDto;
import cjfw.wms.st.dto.StDisuseRequestReqDto;
import cjfw.wms.st.dto.StDisuseRequestResDto;
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
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.07.01
 * @description : 외부비축폐기요청 서비스
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.01 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StDisuseRequestService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "stDisuseRequestService.";
	private transient static final String SERVICEID_TEMP_PREFIX = "cmTempTableService.";

	private transient static final String PAKAGE_NAME = "SPAJ_REQUEST";

	private final CommonDao commonDao;
	private final UserContext userContext;

	/**
	 * @description : 외부비축폐기요청 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	public List<StDisuseRequestResDto> getDisuseRequestList(StDisuseRequestReqDto reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDisuseRequestList", reqDto);
	}
	/**
	 * @description : 외부비축폐기처리 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	public List<StDisuseRequestProcessResDto> getDisuseProcessList(StDisuseRequestReqDto reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDisuseProcessList", reqDto);
	}

	/**
	 * @description : 외부비축폐기요청결재 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	public List<StDisuseRequestApprovalResDto> getDisuseApprovalList(StDisuseRequestReqDto reqDto) {
		String  approvalType = reqDto.getApprovaltype();
		List<StDisuseRequestApprovalResDto> results = commonDao.selectList(SERVICEID_PREFIX + "getDisuseApprovalList", reqDto);
		if (results == null || results.isEmpty()) return results;
		Integer paramListCnt = results.size() < 100 ? results.size() : 100;
		SI_MM0090_SCM_SOProxy proxy = new SI_MM0090_SCM_SOProxy();

		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");
			int count = 0;
			List<DT_MM0090_SCMIF_ST_STOCKAMT> paramList = new ArrayList<>();
			for(int row = 0; row < results.size(); row++) {
				StDisuseRequestApprovalResDto result = results.get(row);
				DT_MM0090_SCMIF_ST_STOCKAMT param = new DT_MM0090_SCMIF_ST_STOCKAMT();
				// proxy req data set
			    String ioType = result.getIotype();
			    String dccode = result.getDccode();

	            param.setPLANT(result.getDccode());
 	            param.setSTORAGELOC(result.getStorageloc());
	            param.setSKU(result.getSku());
	            param.setUOM(result.getUom());
	            param.setQTY(result.getOrderqty().toString());
	            param.setPOLINE(result.getPoline());
	            param.setSERIALNO("3".equals(result.getSerialtype()) ? "" : result.getSerialno());
	            param.setCONVSERIALNO(result.getConvserialno());
	            param.setSLIPDT(result.getSlipdt());
	            param.setSLIPNO(result.getSlipno());
	            param.setSLIPLINE(result.getSlipline());
			    paramList.add(param);
			    if(paramList.size()!=paramListCnt) {
			    	continue;
			    }
		    	DT_MM0090_SCM reqData = new DT_MM0090_SCM();
				reqData.setXSYS("WMS");
				reqData.setXDATS(dateFormat.format(calendar.getTime()));
				reqData.setXTIMS(timeFormat.format(calendar.getTime()));
				reqData.setXROWS(String.valueOf(paramList.size()));

				reqData.setIF_ST_STOCKAMT(paramList.toArray(new DT_MM0090_SCMIF_ST_STOCKAMT[0]));
				DT_MM0090_SCM_responseIF_ST_STOCKAMT_RET[] response = proxy.si_mm0090_scm_so(reqData);
				if (response == null || response.length == 0) {
				    continue;
				}
				for (DT_MM0090_SCM_responseIF_ST_STOCKAMT_RET scm : response) {
				    try {
				    	StDisuseRequestApprovalResDto updateResult =
				    		    results.stream()
				    		        .filter(r -> {
				    		            return Objects.equals(scm.getPLANT() , r.getDccode()) && Objects.equals(scm.getSLIPDT() , r.getSlipdt()) && Objects.equals(scm.getSLIPNO() , r.getSlipno()) && Objects.equals(scm.getSLIPLINE() , r.getSlipline());
				    		        })
				    		        .findFirst() // Optional<StDisuseRequestApprovalResDto>
				    		        .orElse(null);
				        if (updateResult == null) continue;
						String stat = scm.getXSTAT();
				        updateResult.setChkamt(stat);

				        if ("E".equals(stat)) {
				            updateResult.setStockamtmsg(scm.getXMSGS());
				            continue;
				        }
			            String stockAmt = scm.getSTOCKAMT();

			            if (StDisuseRequestContants.APPROVELTYPE_DDRAJ.equals(approvalType) && StDisuseRequestContants.IOTYPE_WD.equals(updateResult.getIotype()) && !StDisuseRequestContants.DCCODE_2170.equals(updateResult.getDccode())) {
			                stockAmt = String.valueOf(Double.parseDouble(stockAmt) * -1);
			            }

			            updateResult.setStockamt(new BigDecimal(stockAmt));
			            //띄어쓰기가 제거되어야함.
			            String priceStr = scm.getPURCHASEPRICE();

				         // null 안전 처리 + 불필요 문자 제거
				         if (priceStr != null) {
				             priceStr = priceStr
				                 .trim()                         // 앞뒤 공백 제거
				                 .replace("\u00A0", "")           // non-breaking space 제거
				                 .replace("\u2007", "")           // figure space 제거
				                 .replace("\u202F", "")           // narrow no-break space 제거
				                 .replace(",", "");               // 천단위 콤마 제거 (있을 경우)
				         }

				         BigDecimal price = (priceStr == null || priceStr.isEmpty())
				             ? BigDecimal.ZERO
				             : new BigDecimal(priceStr);

				         updateResult.setPrice(price);

				    } catch (Exception e) {
				        System.out.println("SCM 응답 데이터 매핑 실패: " + e.getMessage());
				        e.printStackTrace();
				        continue;
				    }
				}
				paramList.clear();
			}

		} catch(Exception e) {
	        System.err.println("StDisuseReqeustService, SCM 인터페이스 처리 중 예외 발생: " + e.getMessage());
	        e.printStackTrace();
		}

		return results;
	}

	/**
	 * @description : 외부비축폐기요청 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
    public List<StDisuseRequestResDto> saveDisuseRequestList(StDisuseRequestReqDto paramDto) {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        String avc_DCCODE = paramDto.getGDccode(); // 센터코드
        // 프로시저 파라미터 세팅
        // PKG 파라마터 세팅 - 공통(1/4)
        // 화면에서 넘겨줄 배치 파라미터와 변수들을 서버에서 넘겨준다.
    	StDisuseRequestReqDto reqDto = new StDisuseRequestReqDto();
    	reqDto.setAvc_COMMAND(StDisuseRequestContants.REQUEST_DU_AVC_COMMAND);
        reqDto.setProcesstype(StDisuseRequestContants.REQUEST_PROCESSTYPE);
        reqDto.setTemptabletype(StDisuseRequestContants.REQUEST_TEMPTABLETYPE);
        reqDto.setDocdt(paramDto.getApprreqdt());
        reqDto.setIfsendtype(StDisuseRequestContants.REQUEST_IFSENDTYPE);
        reqDto.setWorkprocesscode(StDisuseRequestContants.REQUEST_WORKPROCESSCODE);
        reqDto.setOmsflag(StDisuseRequestContants.REQUEST_OMSFLAG);
        reqDto.setStocktranstype(StDisuseRequestContants.REQUEST_STOCKTRANSTYPE);
	    reqDto.setWdcust(paramDto.getWdcust());              // WD_CUST = 출고자
	    reqDto.setWdmethod(paramDto.getWdmethod());            // WD_METHOD = 출고방법
	    reqDto.setWdmemo(paramDto.getWdmemo());               // WD_MEMO = 비고
	    reqDto.setPackagename(PAKAGE_NAME); 		  		   // 패키지명
	    reqDto.setAvc_SYSTEM(paramDto.getGSystem());       // 시스템
	    reqDto.setAvc_DCCODE(paramDto.getGDccode());       // 센터코드
	    reqDto.setAvc_STORERKEY(paramDto.getGStorerkey()); // 고객사코드
	    reqDto.setAvc_WORKER(paramDto.getGUserId());       // 작업자
	    reqDto.setAi_SPID(paramDto.getGSpid());            // SPID

        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {
        	    "PROCESSTYPE",
        	    "PROCESSCREATOR",
        	    "SPID",
        	    "DOCDT",
        	    "IF_SEND_TYPE",
        	    "WORKPROCESSCODE",
        	    "OMS_FLAG",
        	    "STOCKTRANSTYPE",
        	    "WD_CUST",
        	    "WD_METHOD",
        	    "WD_MEMO",
        	    "REASONMSG"
        	};

    	Object[] valueList = {
			reqDto.getProcesstype(),            // PROCESSTYPE = AJ_ADJUSTMENTREQ_DU
			reqDto.getGUserId(),                // PROCESSCREATOR = g_userId
			reqDto.getGSpid(),                  // SPID = g_spid
    	    reqDto.getDocdt(),                  // DOCDT = tab1.div_required.cal_APPRREQDT
    	    reqDto.getIfsendtype(),          // IF_SEND_TYPE = "WMSAJ"
    	    reqDto.getWorkprocesscode(),        // WORKPROCESSCODE = "WMSAJ"
    	    reqDto.getOmsflag(),             // OMS_FLAG = "Y"
    	    reqDto.getStocktranstype(),         // STOCKTRANSTYPE = 이동유형
    	    reqDto.getWdcust(),              // WD_CUST = 출고자
    	    reqDto.getWdmethod(),            // WD_METHOD = 출고방법
    	    reqDto.getWdmemo(),               // WD_MEMO = 비고
    	    reqDto.getReasonmsg()
    	};
    	reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));

        List<StDisuseRequestResDto> saveList = paramDto.getSaveRequestList(); // 저장리스트

        if (!ObjectUtils.isEmpty(saveList)) {
        	// 임시테이블에 저장
        	CmSyProcessTempAjEntity tempDeleteReqDto = ModelMapperUtil.map(reqDto, userContext, CmSyProcessTempAjEntity.class);
        	tempDeleteReqDto.setSpid(reqDto.getGSpid());
        	commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTempAJ", tempDeleteReqDto);
        	int chunkSize = 200;
        	List<CmSyProcessTempAjEntity> insertList = new ArrayList<CmSyProcessTempAjEntity>();

        	for (int i = 0; i < saveList.size(); i++) {
        	    StDisuseRequestResDto dto = saveList.get(i);
        	    //TEMP 테이블 데이터 세팅
            	CmSyProcessTempAjEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempAjEntity.class);
    			String columnsDto    = "DCCODE|STORERKEY|ORGANIZE|AREA|ZONE|LOC|SKU|UOM|LOT|STOCKID|STOCKGRADE|STOCKTYPE|QTY|OPENQTY|QTYALLOCATED|QTYPICKED|TRANQTY|LOTTABLE01|REASONCODE|DISUSETYPE|IMPUTETYPE|PROCESSMAIN|COSTCD|CUSTKEY|TRANBOX|SERIALNO|CONVSERIALNO";
    			String columnsEntity = "DCCODE|STORERKEY|ORGANIZE|AREA|ZONE|LOC|SKU|UOM|LOT|STOCKID|STOCKGRADE|STOCKTYPE|ORDERQTY|OPENQTY|PROCESSQTY|WORKQTY|CONFIRMQTY|MIXBOXKEY|TASKKEY|DELIVERYGROUP|WAVEKEY|INVOICENO|OTHER01|CUSTKEY|ETCQTY1|OTHER04|OTHER05";
    			entity = (CmSyProcessTempAjEntity) EtcUtil.conversionEntityToAsis(dto, entity, columnsDto, columnsEntity);
                entity.setProcesstype(StDisuseRequestContants.REQUEST_PROCESSTYPE);
                entity.setTemptabletype(StDisuseRequestContants.REQUEST_TEMPTABLETYPE);
                entity.setDocdt(reqDto.getApprreqdt());
                entity.setSpid(reqDto.getGSpid());
                entity.setProcesscreator(entity.getGUserId());
        	    insertList.add(entity);

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
            }


		return commonDao.selectList(SERVICEID_PREFIX + "getDataResultlist", reqDto);
	}

    /**
	 * @description : 외부비축폐기결재 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
    public String saveDisuseApproval(StDisuseRequestReqDto paramdto) {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";

        StDisuseRequestReqDto reqDto = ModelMapperUtil.map(paramdto, StDisuseRequestReqDto.class);
        reqDto.setPackagename(PAKAGE_NAME);
        if(StDisuseRequestContants.APPROVELTYPE_DDRAJ.equals(reqDto.getApprovaltype())) {
            reqDto.setAvc_COMMAND(StDisuseRequestContants.AVC_COMMAND_APPROVAL_DC);
        }else {
            reqDto.setAvc_COMMAND(StDisuseRequestContants.AVC_COMMAND_APPROVAL_DU);
        }
        reqDto.setProcesstype(StDisuseRequestContants.PROCESSTYPE_AJ_APPROVAL);
    	CmSyProcessTempAjEntity tempDeleteReqDto = ModelMapperUtil.map(reqDto, userContext, CmSyProcessTempAjEntity.class);
    	tempDeleteReqDto.setSpid(reqDto.getGSpid());

    	commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTempAJ", tempDeleteReqDto);

    	int chunkSize = 200;

    	List<StDisuseRequestApprovalResDto> saveApprovalList = paramdto.getSaveApprovalList();

    	List<CmSyProcessTempAjEntity> insertList = new ArrayList<CmSyProcessTempAjEntity>();
    	for (int i = 0; i < saveApprovalList.size(); i++) {
    		StDisuseRequestApprovalResDto dto = saveApprovalList.get(i);
        	CmSyProcessTempAjEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempAjEntity.class);
			String columnsDto    = "DCCODE|STORERKEY|ORGANIZE|AREA|SKU|UOM|ORDERQTY|DOCDT|DOCNO|DOCLINE|ORDERTYPE|SLIPDT|SLIPNO|SLIPLINE|SLIPTYPE|IOTYPE|STOCKAMT|PRICE|OTHER04|OTHER02|OTHER03|WEIGHT|REASONCODE|LOC";
			String columnsEntity = "DCCODE|STORERKEY|ORGANIZE|AREA|SKU|UOM|ORDERQTY|DOCDT|DOCNO|DOCLINE|ORDERTYPE|SLIPDT|SLIPNO|SLIPLINE|SLIPTYPE|IOTYPE|ETCQTY1|ETCQTY2|OTHER04|OTHER02|OTHER03|INVOICEQTY|WAVEKEY|LOC";
			entity = (CmSyProcessTempAjEntity) EtcUtil.conversionEntityToAsis(dto, entity, columnsDto, columnsEntity);
            ProcedureParametersFactory.initParamDto(reqDto,entity, PAKAGE_NAME);
            entity.setProcesstype(StDisuseRequestContants.PROCESSTYPE_AJ_APPROVAL);
            entity.setTemptabletype(StDisuseRequestContants.REQUEST_TEMPTABLETYPE);
    	    insertList.add(entity);

    	    // 200개마다 혹은 마지막 루프일 때 insert
    	    if (insertList.size() == chunkSize || i == saveApprovalList.size() -1) {
    	        commonDao.insert(SERVICEID_TEMP_PREFIX + "insertSyProcessTempAj", insertList);
    	        insertList.clear(); // 다음 배치 준비
    	    }
    	}

    	// 프로시저 call
        // PKG 파라마터 세팅 - 공통(1/4)
    	StDisuseRequestReqDto procedureReqDto = new StDisuseRequestReqDto();
    	procedureReqDto.setPackagename(PAKAGE_NAME);
    	procedureReqDto.setAvc_COMMAND(reqDto.getAvc_COMMAND());
        procedureReqDto.setProcesstype(StDisuseRequestContants.PROCESSTYPE_AJ_APPROVAL);
	    procedureReqDto.setAi_SPID(reqDto.getGSpid());            // SPID
	    procedureReqDto.setAvc_SYSTEM(reqDto.getGSystem());       // 시스템
	    procedureReqDto.setAvc_WORKER(reqDto.getGUserId());       // 작업자
	    procedureReqDto.setResultMessage("");
	    procedureReqDto.setReturnMessage("");

        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {
        	    "PROCESSTYPE",
        	    "PROCESSCREATOR",
        	    "SPID"
        	};

    	Object[] valueList = {
			reqDto.getProcesstype(),            // PROCESSTYPE = AJ_ADJUSTMENTREQ_DU
			reqDto.getGUserId(),                // PROCESSCREATOR = g_userId
			reqDto.getGSpid()                   // SPID = g_spid
    	};

    	procedureReqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, procedureReqDto);

        // 프로시저 OUT Parameter(3/4)
        resultCode    = (String)procedureReqDto.getResultCode();
        resultMessage = (String)procedureReqDto.getReturnMessage();

        log.info("resultCode->{}",resultCode);
        log.info("resultMessage->{}",resultMessage);

        // 프로시저 Exception 처리(4/4)
        if(!"0".equals(resultCode)){
            log.error("▶외부비축폐기요청 결재 -> 저장 오류 발생 ");
            throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        }

        // 전자결재용 SSO_ID 요청
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");

            SSO_NON_SAP_REQUEST reqData = new SSO_NON_SAP_REQUEST();
            reqData.setXSYS("WMS");
            reqData.setXDATS(dateFormat.format(calendar.getTime()));
            reqData.setXTIMS(timeFormat.format(calendar.getTime()));
            reqData.setXROWS("1");
            reqData.setINT_SVC_NO(reqDto.getGUserNo());

            SSO_NON_SAP_EAIPortTypeProxy proxy = new SSO_NON_SAP_EAIPortTypeProxy();
            SSO_NON_SAP_RESPONSE response = proxy.GET_SSO_TICKET(reqData);
            String ssoId = "<SSOID>" + response.getSSO_TICKET() + "</SSOID>";
            resultMessage = resultMessage + ssoId;

        } catch (Exception e) {
        	log.error("▶sso proxy 오류 발생 ");
        	throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        }

        return resultMessage;
    }

	/**
	 * @description : 외부비축폐기처리 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
    public String saveDisuseProcess(StDisuseRequestReqDto paramdto) {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
    	// 프로시저 call
        StDisuseRequestReqDto reqDto = ModelMapperUtil.map(paramdto,userContext, StDisuseRequestReqDto.class);
        // PKG 파라마터 세팅 - 공통(1/4)
        ProcedureParametersFactory.initParamDto(paramdto,reqDto, PAKAGE_NAME);
        reqDto.setAvc_COMMAND(StDisuseRequestContants.AVC_COMMAND_CONFIRM_DU);
        reqDto.setAi_SPID(null);
        List<StDisuseRequestProcessResDto> processList = reqDto.getSaveProcessList();

        // PKG 파라마터 세팅 - 업무(2/4)
        // 20251219 운송료(REFERENCE10)는 해당 화면에서 입력하지 않기 때문에 처리하지 않도록 변경
        String[] keyList = {
        	    "DCCODE",
        	    "STORERKEY",
        	    "ORGANIZE",
        	    "AREA",
        	    "SLIPDT",
        	    "SLIPNO",
        	    "SLIPLINE",
        	    "TRANQTY",
        	    "TRANBOX",
//        	    "REFERENCE10"
        	};

        for(StDisuseRequestProcessResDto processDto :processList) {
            Object[] valueList = {
            		processDto.getDccode(),
            		processDto.getStorerkey(),
            		processDto.getOrganize(),
            		processDto.getArea(),
            		processDto.getSlipdt(),
            		processDto.getSlipno(),
            	    processDto.getSlipline(),
            	    processDto.getTranqty(),
            	    processDto.getTranbox(),
//            	    processDto.getReference10()
            	};
            reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
            commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, reqDto);

            // 프로시저 OUT Parameter(3/4)
            resultCode    = (String)reqDto.getResultCode();
            resultMessage = (String)reqDto.getResultMessage();

            log.info("resultCode->{}",resultCode);
            log.info("resultMessage->{}",resultMessage);

            // 프로시저 Exception 처리(4/4)
            if(!"0".equals(resultCode)){
                log.error("▶외부비축폐기요청 처리저장 -> 저장 오류 발생 ");
                throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
            }
        }

		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}


	/**
	 * @description : 외부비축폐기결재 삭제
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
    public String cancelDisuseApproval(StDisuseRequestReqDto paramdto) {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        // 프로시저 call
        // PKG 파라마터 세팅 - 공통(1/4)
        StDisuseRequestReqDto reqDto = ModelMapperUtil.map(paramdto,userContext ,StDisuseRequestReqDto.class);
        List<StDisuseRequestProcessResDto> processList = paramdto.getSaveProcessList();
        reqDto.setPackagename(PAKAGE_NAME);
        reqDto.setAvc_WORKER(paramdto.getGUserId());
	    reqDto.setAvc_SYSTEM(paramdto.getGSystem());       // 시스템
	    reqDto.setAvc_DCCODE(paramdto.getGDccode());       // 센터코드
	    reqDto.setAvc_STORERKEY(paramdto.getGStorerkey()); // 고객사코드
	    reqDto.setAi_SPID(paramdto.getGSpid());            // SPID
        if(StDisuseRequestContants.APPROVELTYPE_DDRAJ.equals(reqDto.getApprovaltype())) {
            reqDto.setAvc_COMMAND(StDisuseRequestContants.AVC_COMMAND_CANCEL_DC);
        }else {
            reqDto.setAvc_COMMAND(StDisuseRequestContants.AVC_COMMAND_CANCEL_DU);
        }
        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {
        	    "DCCODE", "STORERKEY", "ORGANIZE", "AREA", "DOCDT",
        	    "DOCNO", "DOCLINE", "SLIPDT", "SLIPNO", "SLIPLINE",
        	    "ORDERTYPE", "SLIPTYPE", "SKU", "UOM", "TRANQTY",
        	    "STOCKID", "STOCKGRADE", "LOC", "LOT", "IOTYPE"
        	};
        for(StDisuseRequestProcessResDto processDto :processList) {
            Object[] valueList = {
            		processDto.getDccode(),
            		processDto.getStorerkey(),
            		processDto.getOrganize(),
            		processDto.getArea(),
            		processDto.getDocdt(),
            		processDto.getDocno(),
            		processDto.getDocline(),
            		processDto.getSlipdt(),
            		processDto.getSlipno(),
            		processDto.getSlipline(),
            		processDto.getOrdertype(),
            		processDto.getSliptype(),
            		processDto.getSku(),
            		processDto.getUom(),
            		processDto.getTranqty(),
            		processDto.getStockid(),
            		processDto.getStockgrade(),
            		processDto.getLoc(),
            		processDto.getLot(),
            		processDto.getIotype()
            	};
            reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
            commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, reqDto);
            // 프로시저 OUT Parameter(3/4)
            resultCode    = (String)reqDto.getResultCode();
            resultMessage = (String)reqDto.getResultMessage();

            log.info("resultCode->{}",resultCode);
            log.info("resultMessage->{}",resultMessage);

            // 프로시저 Exception 처리(4/4)
            if(!"0".equals(resultCode)){
                log.error("▶외부비축폐기요청 결재 삭제 -> 오류 발생 ");
                throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
            }
        }

        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

	/**
	 * @description : 외부비축폐기처리 삭제
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
    public String cancelDisuseProcessList(StDisuseRequestReqDto paramdto) {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        // 프로시저 call
        // PKG 파라마터 세팅 - 공통(1/4)
        StDisuseRequestReqDto reqDto = ModelMapperUtil.map(paramdto, StDisuseRequestReqDto.class);
        ProcedureParametersFactory.initParamDto(paramdto,reqDto, PAKAGE_NAME);
        if(StDisuseRequestContants.APPROVELTYPE_DDRAJ.equals(reqDto.getApprovaltype())) {
            reqDto.setAvc_COMMAND(StDisuseRequestContants.AVC_COMMAND_CANCEL_DC);
        }else {
            reqDto.setAvc_COMMAND(StDisuseRequestContants.AVC_COMMAND_CANCEL_DU);
        }

        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {
        	    "DCCODE",
        	    "STORERKEY",
        	    "ORGANIZE",
        	    "AREA"
        	};

        Object[] valueList = {
        		reqDto.getFixDccode(),
        		reqDto.getGStorerkey(),
        		reqDto.getOrganize(),
        		reqDto.getGArea()
        	};
        reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, reqDto);

        // 프로시저 OUT Parameter(3/4)
        resultCode    = (String)reqDto.getResultCode();
        resultMessage = (String)reqDto.getResultMessage();

        log.info("resultCode->{}",resultCode);
        log.info("resultMessage->{}",resultMessage);

        // 프로시저 Exception 처리(4/4)
        if(!"0".equals(resultCode)){
            log.error("▶외부비축폐기요청 결재 삭제 -> 오류 발생 ");
            throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        }

        return resultMessage;
    }
}
