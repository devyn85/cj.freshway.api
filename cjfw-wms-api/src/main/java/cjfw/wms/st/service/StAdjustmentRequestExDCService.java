package cjfw.wms.st.service;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.wms.cm.entity.CmSyProcessTempAjEntity;
import cjfw.wms.cm.entity.CmSyProcessTempStEntity;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.common.util.EtcUtil;
import cjfw.wms.st.dto.StAdjustmentRequestExDCElectApprovalDto;
import cjfw.wms.st.dto.StAdjustmentRequestExDCReqDto;
import cjfw.wms.st.dto.StAdjustmentRequestExDCResDto;
import cjfw.wms.st.dto.StAdjustmentRequestResDto;
import cjfw.wms.st.entity.StAdjustmentRequestExDCEntity;
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
 * @date : 2025.08.26
 * @description : 외부비축재고조정 Service
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.26 JiHoPark 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StAdjustmentRequestExDCService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
    private transient static final String PAKAGE_NAME = "SPAJ_REQUEST";
    private transient static final String SERVICEID_PREFIX = "stAdjustmentRequestExDCService.";
	private transient static final String SERVICEID_TEMP_PREFIX = "cmTempTableService.";

	private final CommonDao commonDao;
	private final UserContext userContext;

	/**
	 * @description : 외부비축재고조정 - 재고조정 요청 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.27 JiHoPark  생성 </pre>
	 */
	public List<StAdjustmentRequestExDCResDto> getMasterList(StAdjustmentRequestExDCReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}

	/**
	 * @description : 외부비축재고조정 - 요청처리 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.28 JiHoPark  생성 </pre>
	 */
	public List<StAdjustmentRequestExDCResDto> getMasterList2(StAdjustmentRequestExDCReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList2", dto);
	}

	/**
	 * @description : 외부비축재고조정 - 재고조정 결재 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.28 JiHoPark  생성 </pre>
	 */
	public List<StAdjustmentRequestExDCElectApprovalDto> getMasterList3(StAdjustmentRequestExDCReqDto dto) {
		String searchKey = "";
		String resultKey = "";
		String ioType = "";
		String dccode = "";
		String approvalType = dto.getApprovaltype();

		Integer paramListCnt = 500;
		Integer paramRow = 0;
		Integer updateDsRow = 0;
		Integer findRow = 0;

		int rsltCnt = 0;

		List<StAdjustmentRequestExDCElectApprovalDto> searchResultList = commonDao.selectList(SERVICEID_PREFIX + "getMasterList3", dto);
		rsltCnt = searchResultList.size();

		SI_MM0090_SCM_SOProxy proxy = new SI_MM0090_SCM_SOProxy();

		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");

			DT_MM0090_SCM  reqData = new DT_MM0090_SCM();
			reqData.setXSYS("WMS");
			reqData.setXDATS(dateFormat.format(calendar.getTime()));
			reqData.setXTIMS(timeFormat.format(calendar.getTime()));
			reqData.setXROWS(String.valueOf('1'));

			DT_MM0090_SCMIF_ST_STOCKAMT param = new DT_MM0090_SCMIF_ST_STOCKAMT();
			DT_MM0090_SCMIF_ST_STOCKAMT[] paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ paramListCnt ];
			if ( rsltCnt <= paramListCnt ) {
				paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ rsltCnt ];
			}

			for (int DsRow = 0; DsRow < rsltCnt; DsRow++ ) {
				param = new DT_MM0090_SCMIF_ST_STOCKAMT();
				ioType = searchResultList.get(DsRow).getIotype();
				dccode = searchResultList.get(DsRow).getDccode();

				param.setPLANT(searchResultList.get(DsRow).getDccode());
				param.setSTORAGELOC(searchResultList.get(DsRow).getStorageloc());
				param.setSKU(searchResultList.get(DsRow).getSku());
				param.setUOM(searchResultList.get(DsRow).getUom());
				param.setQTY(searchResultList.get(DsRow).getOrderqty().toString());
				param.setPOLINE(searchResultList.get(DsRow).getPoline());

				if ("3".equals(searchResultList.get(DsRow).getSerialtype())) {
					param.setSERIALNO("");
				} else {
					param.setSERIALNO(searchResultList.get(DsRow).getSerialno());
				}

				param.setCONVSERIALNO(searchResultList.get(DsRow).getConvserialno());
				param.setSLIPDT(searchResultList.get(DsRow).getSlipdt());
				param.setSLIPNO(searchResultList.get(DsRow).getSlipno());
				param.setSLIPLINE(searchResultList.get(DsRow).getSlipline());

				paramList[paramRow] = param;
				paramRow++;

				if ((paramRow.equals( paramListCnt )) || String.valueOf(DsRow + 1).equals( String.valueOf(rsltCnt)))  {
					reqData = new DT_MM0090_SCM();
					reqData.setXSYS("WMS");
					reqData.setXDATS(dateFormat.format(calendar.getTime()));
					reqData.setXTIMS(timeFormat.format(calendar.getTime()));
					reqData.setXROWS(String.valueOf(paramRow));

					reqData.setIF_ST_STOCKAMT(paramList);

					DT_MM0090_SCM_responseIF_ST_STOCKAMT_RET[] response = proxy.si_mm0090_scm_so(reqData);

					for (int i = 0; i < paramRow; i++ ) {

						if ( updateDsRow >= rsltCnt) {
							break;
						}

						searchKey =  searchResultList.get(updateDsRow).getDccode()
		                           + searchResultList.get(updateDsRow).getSlipdt()
		                           + searchResultList.get(updateDsRow).getSlipno()
		                           + searchResultList.get(updateDsRow).getSlipline();

						findRow = -1;

						for (int ii = 0; ii < paramRow; ii++) {

							resultKey =  response[ii].getPLANT()
									   + response[ii].getSLIPDT()
									   + response[ii].getSLIPNO()
									   + response[ii].getSLIPLINE();

							if( searchKey.equals(resultKey) ) {
								findRow = ii;
								//LOG.info(" response["+i+"].msg ==========================> [ Find row no :" + FindRow.toString() + "]");
								break;
							}

						}

						if ( findRow >= 0 ) {
							String stat = response[findRow].getXSTAT();
							searchResultList.get(i).setChkamt(response[findRow].getXSTAT());

							if ("E".equals(stat)) {
							    if ("Y".equals(searchResultList.get(updateDsRow).getTplSkugroupYn())) {
							        searchResultList.get(updateDsRow).setStockamtmsg("");
							    } else {
							        searchResultList.get(updateDsRow).setStockamtmsg(response[findRow].getXMSGS());
							    }
							} else {

								if("DDRAJ".equals(approvalType)) {

									if( "WD".equals(ioType) && !"2170".equals(dccode)) {
										searchResultList.get(i).setStockamt(BigDecimal.valueOf(Double.parseDouble(response[findRow].getSTOCKAMT()) * -1));
									} else {
										searchResultList.get(i).setStockamt(BigDecimal.valueOf(Double.parseDouble(response[findRow].getSTOCKAMT())));
									}

								} else {
									searchResultList.get(i).setStockamt(BigDecimal.valueOf(Double.parseDouble(response[findRow].getSTOCKAMT())));
								}

								searchResultList.get(i).setPrice(BigDecimal.valueOf(Double.parseDouble(response[findRow].getPURCHASEPRICE())));

							}
						}

						updateDsRow++;
					}

					if ( rsltCnt - DsRow > paramListCnt ) {
						paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ paramListCnt ];
					} else {
						paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ rsltCnt - DsRow];
					}

					paramRow = 0;
				}
			}


		} catch(Exception e) {
			log.error("재고금액 처리중 에러발생", e);
		}

		return searchResultList;
	}

	/**
	 * @description : 외부비축재고조정 - 재고조정 처리 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.28 JiHoPark  생성 </pre>
	 */
	public List<StAdjustmentRequestExDCResDto> getMasterList4(StAdjustmentRequestExDCReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList4", dto);
	}

	/**
	 * @description : 외부비축재고조정 - 재고조정 처리 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.29 JiHoPark  생성 </pre>
	 */
	public List<StAdjustmentRequestExDCResDto> saveMasterList1(StAdjustmentRequestExDCReqDto dto) {
    	String userId = dto.getGUserId();
		String userSpId = dto.getGSpid();

		String docdt = dto.getDocdt();
		String wdCust = dto.getWdCust();
		String wdMethod = dto.getWdMethod();
		String wdMemo = dto.getWdMemo();
		String reasonmsg = dto.getReasonmsg();

		String procedure = dto.getProcedure();
		String processtype = dto.getProcesstype();
		String ifSendType = dto.getIfSendType();
		String workprocesscode = dto.getWorkprocesscode();
		String omsFlag = dto.getOmsFlg();

		String boxCommand = dto.getBoxCommand();
		String boxProcedure = dto.getBoxProcedure();
		String boxProcesstype = dto.getBoxProcesstype();

		String columnsDto    = "";
		String columnsEntity = "";

        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        String returnMessage = "";

        int chunkSize = 200;

        List<StAdjustmentRequestExDCResDto> masterList2 = new ArrayList<>();
        List<StAdjustmentRequestExDCResDto> resultList = new ArrayList<>();
        List<StAdjustmentRequestExDCResDto> boxResultList = new ArrayList<>();

    	// 작업박스수량 조정
    	List<StAdjustmentRequestExDCResDto> boxSaveList = dto.getSaveBoxAdjustList();
    	if (boxSaveList.size() > 0) {
    		// convert column 정의
    		columnsDto    = "DCCODE|STORERKEY|ORGANIZE|AREA|LOC|SKU|UOM|LOT|STOCKID|STOCKGRADE|STOCKTYPE|QTY|TRANQTY|LOTTABLE01|TRANBOX|REASONCODE|COSTCD|CUSTKEY";
    		columnsEntity = "FROM_DCCODE|FROM_STORERKEY|FROM_ORGANIZE|FROM_AREA|FROM_LOC|FROM_SKU|FROM_UOM|FROM_LOT|FROM_STOCKID|FROM_STOCKGRADE|FROM_STOCKTYPE|FROM_ORDERQTY|FROM_CONFIRMQTY|FROM_IOTYPE|ETCQTY1|REASONCODE|COSTCD|CUSTKEY";

    		// temp table clear
    		CmSyProcessTempStEntity tempStDto = new CmSyProcessTempStEntity();
    		tempStDto.setGUserId(userId);
    		tempStDto.setProcesstype(boxProcesstype);
    		tempStDto.setGSpid(userSpId);
        	commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTempST", tempStDto);

        	List<CmSyProcessTempStEntity> insertList = new ArrayList<CmSyProcessTempStEntity>();
        	for (int i = 0; i < boxSaveList.size(); i++) {
        		StAdjustmentRequestExDCResDto boxSaveDto = boxSaveList.get(i);

        		tempStDto = new CmSyProcessTempStEntity();
        		tempStDto = (CmSyProcessTempStEntity) EtcUtil.conversionEntityToAsis(boxSaveDto, tempStDto, columnsDto, columnsEntity);
        		tempStDto.setAvc_COMMAND(boxCommand);
        		tempStDto.setProcesstype(boxProcesstype);
        		tempStDto.setProcesscreator(userId);
        		tempStDto.setSpid(userSpId);
        		tempStDto.setAddwho(userId);
        		tempStDto.setEditwho(userId);

    			insertList.add(tempStDto);

        	    // 200개마다 혹은 마지막 루프일 때 insert
        	    if (insertList.size() == chunkSize) {
        	        commonDao.insert(SERVICEID_TEMP_PREFIX + "insertSyProcessTempSt", insertList);
        	        insertList.clear(); // 다음 배치 준비
        	    }

        	}

        	// 남은 데이터 insert
        	if (insertList.size() > 0) {
        		commonDao.insert(SERVICEID_TEMP_PREFIX + "insertSyProcessTempSt", insertList);
        	}

        	// package proceduce 실행
        	StAdjustmentRequestResDto saveDto = new StAdjustmentRequestResDto();

	        // PKG 파라마터 세팅 - 공통(1/4)
	        ProcedureParametersFactory.initParamDto(dto, saveDto, boxProcedure);

	        // PKG 파라마터 세팅
        	String[] keyList = { "PROCEDURE"
        			, "PROCESSTYPE"
        			, "PROCESSCREATOR"
        			, "SPID"
        			, "DOCDT"
                    };


	        Object[] valueList = { boxProcedure
				        		, boxProcesstype
				        		, userId
				        		, userSpId
				        		, docdt
	        		};

	        saveDto.setAvc_COMMAND(boxCommand);
	        saveDto.setAvc_DCCODE(dto.getAvc_DCCODE());
	        saveDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
	        commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, saveDto);

	        // 프로시저 OUT Parameter(3/4)
	        resultCode    = (String)saveDto.getResultCode();
	        resultMessage = (String)saveDto.getResultMessage();
	        returnMessage = (String)saveDto.getReturnMessage();

	        // 프로시저 Exception 처리(4/4)
	        if(!"0".equals(resultCode)){
	            log.error("▶외부비축 재고조정 요청/처리 - box 조정처리 오류발생 ");
	            throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
	        }

            boxResultList = commonDao.selectList(SERVICEID_PREFIX + "getMasterList2Box", dto);
    	}

    	// 외부비축 재고조정 요청
    	List<StAdjustmentRequestExDCResDto> saveList = dto.getSaveMasterList1();
    	if (saveList.size() > 0) {
    		// convert column 정의
    		columnsDto    = "DCCODE|STORERKEY|ORGANIZE|AREA|ZONE|LOC|SKU|UOM|LOT|STOCKID|STOCKGRADE|STOCKTYPE|QTY|OPENQTY|QTYALLOCATED|QTYPICKED|TRANQTY|LOTTABLE01|REASONCODE|IMPUTETYPE|PROCESSMAIN|COSTCD|CUSTKEY|OTHER03|TRANBOX|SERIALNO|CONVSERIALNO";
    		columnsEntity = "DCCODE|STORERKEY|ORGANIZE|AREA|ZONE|LOC|SKU|UOM|LOT|STOCKID|STOCKGRADE|STOCKTYPE|ORDERQTY|OPENQTY|PROCESSQTY|WORKQTY|CONFIRMQTY|MIXBOXKEY|TASKKEY|WAVEKEY|INVOICENO|OTHER01|CUSTKEY|OTHER03|ETCQTY1|OTHER04|OTHER05";

    		// temp table clear
    		CmSyProcessTempAjEntity tempAjDto = new CmSyProcessTempAjEntity();
    		tempAjDto.setGUserId(userId);
        	tempAjDto.setProcesstype(processtype);
        	tempAjDto.setGSpid(userSpId);
        	commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTempAJ", tempAjDto);

        	// temp table 데이터 생성
        	List<CmSyProcessTempAjEntity> insertList = new ArrayList<CmSyProcessTempAjEntity>();
        	insertList = new ArrayList<CmSyProcessTempAjEntity>();
        	for (int i = 0; i < saveList.size(); i++) {
        		StAdjustmentRequestExDCResDto saveDto = saveList.get(i);

        		tempAjDto = new CmSyProcessTempAjEntity();
        		tempAjDto = (CmSyProcessTempAjEntity) EtcUtil.conversionEntityToAsis(saveDto, tempAjDto, columnsDto, columnsEntity);
        		tempAjDto.setProcesstype(processtype);
        		tempAjDto.setProcesscreator(userId);
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

        	StAdjustmentRequestExDCResDto saveDto = new StAdjustmentRequestExDCResDto();

        	// PKG 파라마터 세팅 - 공통(1/4)
	        ProcedureParametersFactory.initParamDto(dto, saveDto, procedure);

	        // PKG 파라마터 세팅 - 업무(2/4)
        	String[] keyList = { "PROCEDURE"
        			, "PROCESSTYPE"
        			, "PROCESSCREATOR"
        			, "SPID"
        			, "DOCDT"
        			, "IF_SEND_TYPE"
        			, "WORKPROCESSCODE"
        			, "OMS_FLAG"
        			, "WD_CUST"
        			, "WD_METHOD"
        			, "WD_MEMO"
        			, "REASONMSG"
                    };

	        Object[] valueList = { procedure
				        		, processtype
				        		, userId
				        		, userSpId
				        		, docdt
				        		, ifSendType
				        		, workprocesscode
				        		, omsFlag
				        		, wdCust
				        		, wdMethod
				        		, wdMemo
				        		, reasonmsg
	        		};

	        saveDto.setAvc_DCCODE(dto.getAvc_DCCODE());
	        saveDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
	        commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, saveDto);

	        // 프로시저 OUT Parameter(3/4)
	        resultCode    = (String)saveDto.getResultCode();
	        resultMessage = (String)saveDto.getResultMessage();
	        returnMessage =  saveDto.getReturnMessage();

	        // 프로시저 Exception 처리(4/4)
	        if(!"0".equals(resultCode)){
	            log.error("▶외부비축재고조정 - 재고조정 처리 요청 -> 요청 오류 발생 ");
	            throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
	        }

            resultList = commonDao.selectList(SERVICEID_PREFIX + "getMasterList2", dto);
    	}

    	masterList2 = Stream.of(resultList, boxResultList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

		return masterList2;
	}

	/**
	 * @description : 외부비축재고조정 - 재고조정 결재 삭제
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.29 JiHoPark  생성 </pre>
	 */
    public String saveMasterList3(StAdjustmentRequestExDCReqDto dto) {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";

        List<StAdjustmentRequestExDCResDto> deleteList = dto.getDeleteMasterList3(); // 저장리스트
        String avc_COMMAND = dto.getAvc_COMMAND(); // 패키지가 수행할 커맨드

        if (deleteList.size() > 0) {
        	avc_COMMAND = "APPROVAL_CANCELDC";
            log.info("▶avc_COMMAND->{}",avc_COMMAND);

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

            for (StAdjustmentRequestExDCResDto delDto : deleteList) {
                // PKG 파라마터 세팅 - 공통(1/4)
                ProcedureParametersFactory.initParamDto(dto, delDto, PAKAGE_NAME);

                // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>

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
                delDto.setAvc_DCCODE(dto.getAvc_DCCODE());
                delDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, delDto);

                // 프로시저 OUT Parameter(3/4)
                resultCode    = (String)delDto.getResultCode();
                resultMessage = (String)delDto.getResultMessage();

                log.info("resultCode->{}",resultCode);
                log.info("resultMessage->{}",resultMessage);

                // 프로시저 Exception 처리(4/4)
                if(!"0".equals(resultCode)){
                    log.error("▶외부비축재고조정 - 재고조정 처리 삭제 -> 삭제 오류 발생 ");
                    throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                }
            }

        }

		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

    /**
	 * @description : 외부비축재고조정 - 재고조정 결재 전자결재
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.29 JiHoPark  생성 </pre>
	 */
    public String saveElectApproval(StAdjustmentRequestExDCReqDto dto) {
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
        List<StAdjustmentRequestExDCElectApprovalDto> electApprList = dto.getSaveElectApproval(); // 저장리스트

        if (electApprList.size() > 0) {
        	for (int i = 0; i < electApprList.size(); i++) {
            	StAdjustmentRequestExDCElectApprovalDto electDto = electApprList.get(i);

            	tempAjDto = new CmSyProcessTempAjEntity();
            	tempAjDto = (CmSyProcessTempAjEntity) EtcUtil.conversionEntityToAsis(electDto, tempAjDto, columnsDto, columnsEntity);
    			tempAjDto.setProcesstype(processtype);
    			tempAjDto.setProcesscreator(userId);
    			tempAjDto.setSpid(userSpId);
    			tempAjDto.setAddwho(userId);
    			tempAjDto.setEditwho(userId);

    			if (StringUtils.isNotEmpty(tempAjDto.getOther03())) {
    				String strOther03 = tempAjDto.getOther03();
    				strOther03 = strOther03.replace(" &lt; ", "<");
    				strOther03 = strOther03.replace(" &gt; ", ">");
    				tempAjDto.setOther03(strOther03);
    			}

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

        	StAdjustmentRequestExDCElectApprovalDto electApprovalDto = new StAdjustmentRequestExDCElectApprovalDto();
        	// PKG 파라마터 세팅 - 공통(1/4)
            ProcedureParametersFactory.initParamDto(dto, electApprovalDto, procedure);

        	String[] keyList = {
        			  "PROCEDURE"
        			, "PROCESSTYPE"
        			, "PROCESSCREATOR"
        			, "SPID"
                    };

            // PKG 파라마터 세팅 - 업무(2/4)
            Object[] valueList = {
            			  procedure
    			        , processtype
    			        , userId
    			        , userSpId
            		};

            electApprovalDto.setAvc_DCCODE(dto.getAvc_DCCODE());
            electApprovalDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
            commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, electApprovalDto);

            // 프로시저 OUT Parameter(3/4)
            resultCode    = (String)electApprovalDto.getResultCode();
            resultMessage = (String)electApprovalDto.getResultMessage();
            returnMessage =  (String)electApprovalDto.getReturnMessage();
            log.info("resultCode->{}",resultCode);
            log.info("resultMessage->{}",resultMessage);

            // 프로시저 Exception 처리(4/4)
            if(!"0".equals(resultCode)){
                log.error("▶외부비축재고조정 - 재고조정 결재 -> 결재진행 오류 발생 ");
                throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
            }

            SSO_NON_SAP_EAIPortTypeProxy proxy = new SSO_NON_SAP_EAIPortTypeProxy();
            String ssoId  = "";

            try {
    			Calendar calendar = Calendar.getInstance();
    			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    			SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");

    			SSO_NON_SAP_REQUEST  reqData = new SSO_NON_SAP_REQUEST();
    			reqData.setXSYS("WMS");
    			reqData.setXDATS(dateFormat.format(calendar.getTime()));
    			reqData.setXTIMS(timeFormat.format(calendar.getTime()));
    			reqData.setXROWS(String.valueOf(1));
    			//reqData.setINT_SVC_NO("1000005989");    // 글로벌 변수로 대체
    			reqData.setINT_SVC_NO(userNo);    // 글로벌 변수로 대체

    			SSO_NON_SAP_RESPONSE response = proxy.GET_SSO_TICKET(reqData);

    			ssoId = "<SSOID>" + response.getSSO_TICKET() + "</SSOID>";

            } catch(Exception e) {
    			log.error("SSO ERROR", e);
    		}

            resultMessage = "<result>" + returnMessage + ssoId + "</result>";
        }

		return resultMessage;
	}

    /**
	 * @description : 외부비축재고조정 - 재고조정 처리 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.29 JiHoPark  생성 </pre>
	 */
    public String saveMasterList4(StAdjustmentRequestExDCReqDto dto) {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";

        String avc_COMMAND = ""; // 패키지가 수행할 커맨드
        List<StAdjustmentRequestExDCResDto> saveList = dto.getSaveMasterList4(); // 저장리스트

        if (saveList.size() > 0) {
            for (StAdjustmentRequestExDCResDto saveDto : saveList) {
                if ("3".equals(saveDto.getApprovalstatus())) {
                    //승인
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
                                , "TRANBOX"
                     };  
                    
                    // PKG 파라마터 세팅 - 공통(1/4)
                    ProcedureParametersFactory.initParamDto(dto, saveDto, PAKAGE_NAME);
    
                    // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>    
                    Object[] valueList = {saveDto.getDccode()
                                        , saveDto.getStorerkey()
                                        , saveDto.getOrganize()
                                        , saveDto.getArea()
                                        , saveDto.getSlipdt()
                                        , saveDto.getSlipno()
                                        , saveDto.getSlipline()
                                        , saveDto.getTranqty()
                                        , saveDto.getTranbox()
                                        };
    
                    saveDto.setAvc_DCCODE(saveDto.getDccode());
                    saveDto.setAvc_COMMAND(avc_COMMAND);
                    saveDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                    commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, saveDto);
    
                    // 프로시저 OUT Parameter(3/4)
                    resultCode    = (String)saveDto.getResultCode();
                    resultMessage = (String)saveDto.getResultMessage();
    
                    log.info("resultCode->{}",resultCode);
                    log.info("resultMessage->{}",resultMessage);
    
                    // 프로시저 Exception 처리(4/4)
                    if(!"0".equals(resultCode)){
                        log.error("▶외부비축재고조정 - 재고조정 처리 저장 -> 저장 오류 발생 ");
                        throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                    }
                }// END..if ("3".equals(saveDto.getApprovalstatus())) {
                else if ("0".equals(saveDto.getApprovalstatus())) {
                    //반려
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
                    
                    // PKG 파라마터 세팅 - 공통(1/4)
                    ProcedureParametersFactory.initParamDto(dto, saveDto, PAKAGE_NAME);
    
                    // PKG 파라마터 세팅 - 업무(2/4)
                    Object[] valueList = {saveDto.getDccode()
                                        , saveDto.getStorerkey()
                                        , saveDto.getOrganize()
                                        , saveDto.getArea()
                                        , saveDto.getDocdt()
                                        , saveDto.getDocno()
                                        , saveDto.getDocline()
                                        , saveDto.getSlipdt()
                                        , saveDto.getSlipno()
                                        , saveDto.getSlipline()
                                        , saveDto.getOrdertype()
                                        , saveDto.getSliptype()
                                        , saveDto.getSku()
                                        , saveDto.getUom()
                                        , saveDto.getTranqty()
                                        , saveDto.getStockid()
                                        , saveDto.getStockgrade()
                                        , saveDto.getLoc()
                                        , saveDto.getLot()
                                        , saveDto.getIotype()
                                        };
    
                    saveDto.setAvc_DCCODE(saveDto.getDccode());
                    saveDto.setAvc_COMMAND(avc_COMMAND);
                    saveDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                    commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, saveDto);
    
                    // 프로시저 OUT Parameter(3/4)
                    resultCode    = (String)saveDto.getResultCode();
                    resultMessage = (String)saveDto.getResultMessage();
    
                    log.info("resultCode->{}",resultCode);
                    log.info("resultMessage->{}",resultMessage);
    
                    // 프로시저 Exception 처리(4/4)
                    if(!"0".equals(resultCode)){
                        log.error("▶외부비축재고조정 - 재고조정 처리 삭제 -> 삭제 오류 발생 ");
                        throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                    }
                }
            }//END..for (StAdjustmentRequestExDCResDto saveDto : saveList) {
        }
        
        /* 20251220 삭제기능 제거 오쳥에 따른 기능 제거
        List<StAdjustmentRequestExDCResDto> deleteList = dto.getDeleteMasterList4(); // 삭제리스트
        if (deleteList.size() > 0) {
        	avc_COMMAND = "APPROVAL_CANCELDC";
        	String[] keyList = { "DCCODE"
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

            for (StAdjustmentRequestExDCResDto delDto : deleteList) {
                // PKG 파라마터 세팅 - 공통(1/4)
                ProcedureParametersFactory.initParamDto(dto, delDto, PAKAGE_NAME);

                // PKG 파라마터 세팅 - 업무(2/4)
                

                delDto.setAvc_DCCODE(dto.getAvc_DCCODE());
                delDto.setAvc_COMMAND(avc_COMMAND);
                delDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, delDto);

                // 프로시저 OUT Parameter(3/4)
                resultCode    = (String)delDto.getResultCode();
                resultMessage = (String)delDto.getResultMessage();

                log.info("resultCode->{}",resultCode);
                log.info("resultMessage->{}",resultMessage);

                // 프로시저 Exception 처리(4/4)
                if(!"0".equals(resultCode)){
                    log.error("▶외부비축재고조정 - 재고조정 처리 삭제 -> 삭제 오류 발생 ");
                    throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                }
            }
        }
        */
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

    /**
     * @throws RemoteException
	 * @description : 전자결재 최종결재완료된 재고 자동처리
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.05 JiHoPark  생성 </pre>
     */
	public String saveStockAutoHandle(String approvalreqdt, String approvalreqno, String apprstatus, String docId, String approveId, String approveNm)  {
        // 프로시저 에러코드 및 메세지
        String resultCode = "1";
        String resultMessage  = "";
        String errMsg = "";

        StAdjustmentRequestExDCEntity procEntity = new StAdjustmentRequestExDCEntity();
        procEntity.setDccode("2170");
        procEntity.setApprovalreqdt(approvalreqdt);
        procEntity.setApprovalreqno(approvalreqno);
        procEntity.setApprstatus(apprstatus);
        procEntity.setDocId(docId);
        procEntity.setApproveId(approveId);
        procEntity.setApproveNm(approveNm);

        // 전자결제 상태값 update처리
    	commonDao.update(SERVICEID_PREFIX + "updateElecStatus", procEntity);

        // 재고처리할 목록 조회
        List<StAdjustmentRequestExDCResDto> handleList = commonDao.selectList(SERVICEID_PREFIX + "getStockAutoHandleList", procEntity);
        if (handleList.size() > 0) {
        	String[] keyList = {
        			  "DCCODE"
        			, "STORERKEY"
        			, "ORGANIZE"
        			, "AREA"
        			, "SLIPDT"
        			, "SLIPNO"
        			, "SLIPLINE"
        			, "TRANQTY"
        			, "TRANBOX"
                    };

            for (StAdjustmentRequestExDCResDto handleDto : handleList) {
                // PKG 파라마터 세팅 - 공통(1/4)
            	handleDto.setPackagename(PAKAGE_NAME);                 // 패키지명
            	handleDto.setAvc_COMMAND(handleDto.getExecCommand());  // 액션커멘드
            	handleDto.setAvc_SYSTEM("WMSAPP");                     // 시스템
            	handleDto.setAvc_DCCODE(handleDto.getDccode());        // 센터코드
            	handleDto.setAvc_STORERKEY(handleDto.getStorerkey());  // 고객사코드
            	handleDto.setAvc_WORKER(handleDto.getAddwho());        // 작업자
            	handleDto.setAvc_ORGANIZE(handleDto.getOrganize());    // 조직
            	handleDto.setAvc_AREA(handleDto.getArea());            // 창고
            	handleDto.setAi_SPID("1000000001");                    // SPID

                // PKG 파라마터 세팅 - 업무(2/4)
                Object[] valueList = {handleDto.getDccode()
                                    , handleDto.getStorerkey()
                                    , handleDto.getOrganize()
                                    , handleDto.getArea()
                                    , handleDto.getSlipdt()
                                    , handleDto.getSlipno()
                                    , handleDto.getSlipline()
                                    , handleDto.getTranqty()
                                    , handleDto.getTranbox()
                                    };

                handleDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, handleDto);
                // 프로시저 OUT Parameter(3/4)
                resultCode    = (String)handleDto.getResultCode();
                resultMessage = (String)handleDto.getResultMessage();

                log.info("resultCode->{}",resultCode);
                log.info("resultMessage->{}",resultMessage);

                // 프로시저 Exception 처리(4/4)
                if(!"0".equals(resultCode)){
                    log.error("▶외부비축 자동 재고처리 오류 발생 ");
                    throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                }
            }
        }

        if ("0".equals(resultCode)) {
        	resultCode = "1";
        }


	    return resultCode + "_&_" + resultMessage;
    }

}
