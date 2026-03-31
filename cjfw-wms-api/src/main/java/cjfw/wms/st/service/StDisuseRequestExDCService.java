package cjfw.wms.st.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.wms.cm.entity.CmSyProcessTempAjEntity;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.common.util.EtcUtil;
import cjfw.wms.st.dto.StAdjustmentRequestExDCElectApprovalDto;
import cjfw.wms.st.dto.StAdjustmentRequestExDCReqDto;
import cjfw.wms.st.dto.StAdjustmentRequestExDCResDto;
import cjfw.wms.st.dto.StDisuseRequestExDCElectApprovalDto;
import cjfw.wms.st.dto.StDisuseRequestExDCReqDto;
import cjfw.wms.st.dto.StDisuseRequestExDCResDto;
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
 * @description : 외부비축재고폐기처리 Service
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.26 JiHoPark 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StDisuseRequestExDCService {
    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     * @return .sqlx returns the location
     */
    private transient static final String PAKAGE_NAME = "SPAJ_REQUEST";
    private transient static final String SERVICEID_PREFIX = "stDisuseRequestExDCService.";
    private transient static final String SERVICEID_TEMP_PREFIX = "cmTempTableService.";

    private final CommonDao commonDao;

    /**
     * @description : 외부비축재고폐기처리 - 폐기 요청 목록 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.27 JiHoPark  생성 </pre>
     */
    public List<StDisuseRequestExDCResDto> getMasterList(StDisuseRequestExDCReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
    }

    /**
     * @description : 외부비축재고폐기처리 - 폐기 결재 목록 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.28 JiHoPark  생성 </pre>
     */
    public List<StDisuseRequestExDCElectApprovalDto> getMasterList3(StDisuseRequestExDCReqDto dto) {
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

        List<StDisuseRequestExDCElectApprovalDto> searchResultList = commonDao.selectList(SERVICEID_PREFIX + "getMasterList3", dto);
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

                        if( findRow >= 0 ) {
                            String stat = response[findRow].getXSTAT();
                            searchResultList.get(i).setChkamt(response[findRow].getXSTAT());

                            if("E".equals(stat)) {
                                searchResultList.get(updateDsRow).setStockamtmsg(response[findRow].getXMSGS());
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
     * @description : 외부비축재고폐기처리 - 폐기 처리 목록 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.28 JiHoPark  생성 </pre>
     */
    public List<StDisuseRequestExDCResDto> getMasterList4(StDisuseRequestExDCReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList4", dto);
    }

    /**
     * @description : 외부비축재고폐기처리 - 폐기 요청 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.29 JiHoPark  생성 </pre>
     */
    public List<StDisuseRequestExDCResDto> saveMasterList1(StDisuseRequestExDCReqDto dto) {
        String userId = dto.getGUserId();
        String userSpId = dto.getGSpid();

        String docdt = dto.getDocdt(); // 결재요청일자
        String wdCust = dto.getWdCust(); // 출고자
        String wdMethod = dto.getWdMethod(); // 출고방법
        String wdMemo = dto.getWdMemo(); // 출고증 비고
        String reasonmsg = dto.getReasonmsg(); // 처리사유

        String processtype = dto.getProcesstype();
        String ifSendType = dto.getIfSendType();
        String workprocesscode = dto.getWorkprocesscode();
        String omsFlag = dto.getOmsFlg();

        String columnsDto    = "";
        String columnsEntity = "";

        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        String returnMessage = "";

        int chunkSize = 200;

        // 외부비축재고폐기 요청
        List<StDisuseRequestExDCResDto> saveList = dto.getSaveMasterList1();
        if (saveList.size() > 0) {
            // convert column 정의
            columnsDto    = "DCCODE|STORERKEY|ORGANIZE|AREA|ZONE|LOC|SKU|UOM|LOT|STOCKID|STOCKGRADE|STOCKTYPE|QTY|OPENQTY|QTYALLOCATED|QTYPICKED|TRANQTY|LOTTABLE01|REASONCODE|DISUSETYPE|IMPUTETYPE|PROCESSMAIN|COSTCD|CUSTKEY|TRANBOX|SERIALNO|CONVSERIALNO";
            columnsEntity = "DCCODE|STORERKEY|ORGANIZE|AREA|ZONE|LOC|SKU|UOM|LOT|STOCKID|STOCKGRADE|STOCKTYPE|ORDERQTY|OPENQTY|PROCESSQTY|WORKQTY|CONFIRMQTY|MIXBOXKEY|TASKKEY|DELIVERYGROUP|WAVEKEY|INVOICENO|OTHER01|CUSTKEY|ETCQTY1|OTHER04|OTHER05";

            // temp table clear
            CmSyProcessTempAjEntity tempAjDto = new CmSyProcessTempAjEntity();
            tempAjDto.setGUserId(userId);
            tempAjDto.setProcesstype(processtype);
            tempAjDto.setGSpid(userSpId);
            commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTempAJ", tempAjDto);

            // temp table 데이터 생성
            List<CmSyProcessTempAjEntity> insertList = new ArrayList<CmSyProcessTempAjEntity>();
            for (int i = 0; i < saveList.size(); i++) {
                StDisuseRequestExDCResDto saveDto = saveList.get(i);

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
            ProcedureParametersFactory.initParamDto(dto, saveDto, PAKAGE_NAME);

            // PKG 파라마터 세팅 - 업무(2/4)
            String[] keyList = { "PROCEDURE"
                    , "PROCESSTYPE"
                    , "PROCESSCREATOR"
                    , "SPID"
                    , "DOCDT"
                    , "IF_SEND_TYPE"
                    , "WORKPROCESSCODE"
                    , "OMS_FLAG"
                    , "STOCKTRANSTYPE"
                    , "WD_CUST"
                    , "WD_METHOD"
                    , "WD_MEMO"
                    , "REASONMSG"
                    };

            Object[] valueList = { PAKAGE_NAME
                                , processtype
                                , userId
                                , userSpId
                                , docdt
                                , ifSendType
                                , workprocesscode
                                , omsFlag
                                , "973"
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
            returnMessage = (String)saveDto.getReturnMessage();

            // 프로시저 Exception 처리(4/4)
            if(!"0".equals(resultCode)){ 
                log.error("▶외부비축재고폐기처리 - 폐기 처리 요청 -> 요청 오류 발생 ");
                throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
            }
        }

        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList2", dto);
    }

    /**
     * @description : 외부비축재고폐기처리 - 폐기 결재 저장
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
                    log.error("▶외부비축재고폐기처리 - 폐기 처리 삭제 -> 삭제 오류 발생 ");
                    throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                }
            }

        }

        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * @description : 외부비축재고폐기처리 - 폐기 결재 전자결재
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

        if (electApprList.size() > 0) {
            StAdjustmentRequestExDCElectApprovalDto electApprovalDto = new StAdjustmentRequestExDCElectApprovalDto();
            // PKG 파라마터 세팅 - 공통(1/4)
            ProcedureParametersFactory.initParamDto(dto, electApprovalDto, PAKAGE_NAME);

            String[] keyList = {
                      "PROCEDURE"
                    , "PROCESSTYPE"
                    , "PROCESSCREATOR"
                    , "SPID"
                    };

            // PKG 파라마터 세팅 - 업무(2/4)
            Object[] valueList = {
                     PAKAGE_NAME
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
                log.error("▶외부비축재고폐기처리 - 폐기 결재 -> 결재진행 오류 발생 ");
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
     * @description : 외부비축재고폐기처리 - 폐기 처리 확정
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.29 JiHoPark  생성 </pre>
     */
    public String saveMasterList4(StDisuseRequestExDCReqDto dto) {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";

        String avc_COMMAND = ""; // 패키지가 수행할 커맨드
        List<StDisuseRequestExDCResDto> saveList = dto.getSaveMasterList4(); // 저장리스트

        if (saveList.size() > 0) {
            for (StDisuseRequestExDCResDto saveDto : saveList) {
                if ("3".equals(saveDto.getApprovalstatus())) {
                    //승인
                    avc_COMMAND = "CONFIRM_DU";
                    
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
                    Object[] valueList = {
                                      saveDto.getDccode()
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
                        log.error("▶외부비축재고조정 - 재고폐기 처리 저장 -> 저장 오류 발생 ");
                        throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                    }
                }// END..if ("3".equals(saveDto.getApprovalstatus())) {
                else if ("0".equals(saveDto.getApprovalstatus())) {
                    //반려
                    avc_COMMAND = "APPROVAL_CANCELDU";
                    
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
                        log.error("▶외부비축재고조정 - 재고폐기 처리 삭제 -> 삭제 오류 발생 ");
                        throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                    }
                }
            }//END..for (StDisuseRequestExDCResDto saveDto : saveList) {
        }

        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
}
