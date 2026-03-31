package cjfw.batch.job.autoconfirmcanceldpwd;

import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.batch.common.dto.BatchParamsUtil;
import cjfw.batch.config.ApiBatchClient;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.utility.ContextUtil;
import cjfw.wms.common.util.ApiAuthUtil;
import cjfw.wms.common.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.ParseException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.sf.json.JSONObject;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExdcDpWdAutoConfirmCancelService {

    private static final String BATCH_IF_ID = "ID225"; //신성장가입출고취소처리
    private final CommonDao commonDao;

    @Autowired
    private ApiBatchClient apiClient;

    /**
     * ----------------------------
     * 1. 당일매입매출 가출고 취소
     * -----------------------------
     *  1-1. 가출고 취소 대상 조회
     *  1-2. 가출고 취소 대상 디테일 조회
     *  1-3. 외부창고 보관료 전표 유무 체크
     *  1-4. SAP 관련 사전 체크
     *  1-5. SAP 호출
     *  1-6. SAP 입고생성여부 체크
     *  1-7. 가출고 취소 처리 (DB Pkg 호출)
     *  1-8. 가출고 결품 처리 (DB Pkg 호출)
     *  1-9. 매핑 테이블 Update
     *-----------------------------
     * 2. 당일매입매출 가입고 취소
     * ----------------------------
     *  2-1. 가입고 취소 대상 조회
     *  2-2. 가입고 취소 대상 디테일 조회
     * 	2-3. 외부창고요율 체크
     *  2-4. 가입고 취소 처리 (DB Pkg 호출)
     * 	2-5. 가입고 결품 처리 (DB Pkg 호출)
     * 	2-6. 매핑 테이블 Update
     */
    public void callAutoConfirmCancelService(BatchParamsUtil batchParam, BatchLogParamsDto batchLogDto) {
        //BATCH JOB LOG설정
        String[] logOption = batchParam.getAVC_LOG_PARAMS().split("\\^"); //AVC_LOG_PARAMS 문자열 분리
        BatchLogParamsDto copyDto = new BatchLogParamsDto();
        if (batchLogDto != null) {
            BeanUtils.copyProperties(batchLogDto, copyDto);
            copyDto.setJobDiv("JAVA SERVICE");
            copyDto.setNodeLevel(batchLogDto.getNodeLevel()+1 );
            copyDto.setCommand(BATCH_IF_ID);
            logSystemOut(copyDto, "START", logOption[4], "70", "JAVA SERVICE START");
        }

        String errorCheck = ""; //S:정상, E:에러
        String errorStatus = ""; //errorCheck가 'E'일때만 해당 (10:요율체크, 20:보관료전표유무, 30:sap체크)
        String reqMsg = "";

        int errorCode = 0;
        String errorMessage = null;
		String ifStaus = null; //IF_MASTER 호출결과

		batchParam.setAVC_COMMAND("START");	// CHECK(*1)후 START()방식 또는 직접 START(*END)방식 모두 가능함.
		batchParam.setAVC_IFID(BATCH_IF_ID);
		commonDao.selectOne("batchCommonService.procIFStatus", batchParam);
		ifStaus = batchParam.getOUT_RETURN_MSG();
		logSystemOut(copyDto, "INFO", logOption[4], "85", "IF_MASTER SET START RESULT:"+ (ifStaus.equals("END")?"OK":ifStaus));
		if( ifStaus.equals("END") ) {
            try {
                //1-1. 가출고 취소 대상 조회
                ExdcDpWdAutoConfirmCancelReqDto inputParam = new ExdcDpWdAutoConfirmCancelReqDto();
                List<ExdcDpWdAutoConfirmCancelResDto> wdList = commonDao.selectList("exdcDpWdAutoConfirmCancelService.selectWdList", inputParam);
                logSystemOut(copyDto, "INFO", logOption[4], "91", "[sqlId]selectWdList Rows:"+String.valueOf(wdList.size()));

                if(wdList != null && !wdList.isEmpty()) {

                    for(ExdcDpWdAutoConfirmCancelResDto wdData : wdList) {
                        inputParam.setDccode(wdData.getDccode());
                        inputParam.setStorerkey("FW00");
                        inputParam.setSlipno(wdData.getSlipno());
                        inputParam.setSlipdt(wdData.getSlipdt());
                        inputParam.setDocno(wdData.getDocNo());
                        List<ExdcDpWdAutoConfirmCancelResDto> wdDetailList = commonDao.selectList("exdcDpWdAutoConfirmCancelService.selectWdDetailList", inputParam);
                        logSystemOut(copyDto, "INFO", logOption[4], "102", "[sqlId]selectWdDetailList Rows:"+String.valueOf(wdDetailList.size()));

                        if(wdDetailList != null && !wdDetailList.isEmpty()) {
                            for(ExdcDpWdAutoConfirmCancelResDto wdDetailData : wdDetailList) {
                                errorCheck = "S";

                                //취소 처리할 수량이 0이면 pass
                                int confirmQty = (int)Float.parseFloat(String.valueOf(wdDetailData.getConfirmqty()));
                                if (confirmQty == 0) {
                                    reqMsg = "confirmQty=0";
                                    break;
                                }

                                // 1-3. 외부창고 보관료 전표 유무 체크
                                inputParam = new ExdcDpWdAutoConfirmCancelReqDto();
                                inputParam.setDccode(wdDetailData.getDccode());
                                inputParam.setStorerkey("FW00");
                                inputParam.setSlipno(wdDetailData.getSlipno());
                                inputParam.setSlipline(wdDetailData.getSlipline());
                                inputParam.setStockid(wdDetailData.getStockid());
                                int checkExistDocCnt = commonDao.selectOne("exdcDpWdAutoConfirmCancelService.checkExistDoc", inputParam);
                                if(checkExistDocCnt > 0) {
                                    errorCheck = "E";
                                    errorStatus = "20"; // 20:보관료전표유무
                                    reqMsg = "<DCCODE>"+ inputParam.getDccode() + "</DCCODE><SLIPNO>" + inputParam.getSlipno()  + "</SLIPNO><SLIPLINE>" +
                                             inputParam.getSlipline() +"</SLIPLINE><STOCKID>"+ inputParam.getStockid() +"</STOCKID>";
                                }

                                if("S".equals(errorCheck)) {
                                    // 1-7. 가출고 취소 처리 (DB Pkg 호출)
                                    StringBuffer requestMsg = new StringBuffer();
                                    requestMsg.append("<DCCODE>").append(wdDetailData.getDccode()).append("</DCCODE>");
                                    requestMsg.append("<STORERKEY>").append(wdDetailData.getStorerkey()).append("</STORERKEY>");
                                    requestMsg.append("<ORGANIZE>").append(wdDetailData.getOrganize()).append("</ORGANIZE>");
                                    requestMsg.append("<DOCDT>").append(wdDetailData.getDocdt()).append("</DOCDT>");
                                    requestMsg.append("<DOCNO>").append(wdDetailData.getDocNo()).append("</DOCNO>");
                                    requestMsg.append("<DOCLINE>").append(wdDetailData.getDocline()).append("</DOCLINE>");
                                    requestMsg.append("<SLIPDT>").append(wdDetailData.getSlipdt()).append("</SLIPDT>");
                                    requestMsg.append("<SLIPNO>").append(wdDetailData.getSlipno()).append("</SLIPNO>");
                                    requestMsg.append("<SLIPLINE>").append(wdDetailData.getSlipline()).append("</SLIPLINE>");
                                    requestMsg.append("<SKU>").append(wdDetailData.getSku()).append("</SKU>");
                                    requestMsg.append("<UOM>").append(wdDetailData.getUom()).append("</UOM>");
                                    requestMsg.append("<BUNJA>").append(wdDetailData.getBunja()).append("</BUNJA>");
                                    requestMsg.append("<BUNMO>").append(wdDetailData.getBunmo()).append("</BUNMO>");
                                    requestMsg.append("<TRANQTY>").append(wdDetailData.getConfirmqty().multiply(BigDecimal.valueOf(-1))).append("</TRANQTY>");// 마이너스 처리
                                    requestMsg.append("<SHORTAGETRANQTY>").append(wdDetailData.getShortagetranqty()).append("</SHORTAGETRANQTY>");
                                    requestMsg.append("<LOTTABLE01>").append(wdDetailData.getLottable01()).append("</LOTTABLE01>");
                                    requestMsg.append("<STOCKID>").append(wdDetailData.getStockid()).append("</STOCKID>");
                                    requestMsg.append("<STOCKGRADE>").append(wdDetailData.getStockgrade()).append("</STOCKGRADE>");
                                    requestMsg.append("<REASONCODE>Z4</REASONCODE>");
                                    requestMsg.append("<REASONMSG>").append(wdDetailData.getReasonmsg()).append("</REASONMSG>");
                                    requestMsg.append("<REFERENCE02>").append(wdDetailData.getReference02()).append("</REFERENCE02>");
                                    requestMsg.append("<TRANBOX>").append(wdDetailData.getTransbox()).append("</TRANBOX>");
                                    requestMsg.append("<REFERENCE10>").append(wdDetailData.getReference10()).append("</REFERENCE10>");

                                    BatchParamsUtil procParam = new BatchParamsUtil();
                                    procParam.setAVC_SYSTEM("WMSAPP");
                                    procParam.setAVC_EXECUTEMODE("");
                                    procParam.setAVC_COMMAND("CONFIRM");
                                    procParam.setAVC_DCCODE(wdDetailData.getDccode());
                                    procParam.setAVC_STORERKEY("FW00");
                                    procParam.setAVC_ORGANIZE("%");
                                    procParam.setAVC_AREA("%");
                                    procParam.setAVC_REQUESTCODE("");
                                    procParam.setAVC_REQUESTMSG(requestMsg.toString());
                                    procParam.setAVC_WORKER("LOGISONEBATCH");
                                    procParam.setAVC_SECURITYKEY("");
                                    procParam.setAI_SPID(1000000001);
                                    procParam.setVC_RESULTMSG("");
                                    procParam.setVC_RETURNMSG("");
                                    procParam.setAVC_LOG_PARAMS(batchParam.getAVC_LOG_PARAMS());

                                    String strErrorCode = "";
                                    try {
                                        commonDao.selectOne("exdcDpWdAutoConfirmCancelService.callProcedureWd", procParam);
                                        strErrorCode = String.valueOf(procParam.getI_ERR());
                                    } catch (Exception e) {
                                        errorCheck = "E";
                                        errorStatus = "50"; // 50:패키지 내부 오류
                                        strErrorCode = String.valueOf(procParam.getI_ERR());
                                        reqMsg = "REQUESTMSG =>" + requestMsg.toString();
                                    }

                                    if(Integer.parseInt(strErrorCode) < 0) {
                                        errorCheck = "E";
                                        errorStatus = "50"; // 50:패키지 내부 오류
                                        reqMsg = "REQUESTMSG =>" + requestMsg.toString();
                                    }

                                    // 1-8. 가출고 결품 처리 (DB Pkg 호출)
                                    requestMsg.setLength(0);
                                    requestMsg.append("<DCCODE>").append(wdDetailData.getDccode()).append("</DCCODE>");
                                    requestMsg.append("<STORERKEY>").append(wdDetailData.getStorerkey()).append("</STORERKEY>");
                                    requestMsg.append("<ORGANIZE>").append(wdDetailData.getOrganize()).append("</ORGANIZE>");
                                    requestMsg.append("<DOCDT>").append(wdDetailData.getDocdt()).append("</DOCDT>");
                                    requestMsg.append("<DOCNO>").append(wdDetailData.getDocNo()).append("</DOCNO>");
                                    requestMsg.append("<DOCLINE>").append(wdDetailData.getDocline()).append("</DOCLINE>");
                                    requestMsg.append("<SLIPDT>").append(wdDetailData.getSlipdt()).append("</SLIPDT>");
                                    requestMsg.append("<SLIPNO>").append(wdDetailData.getSlipno()).append("</SLIPNO>");
                                    requestMsg.append("<SLIPLINE>").append(wdDetailData.getSlipline()).append("</SLIPLINE>");
                                    requestMsg.append("<SKU>").append(wdDetailData.getSku()).append("</SKU>");
                                    requestMsg.append("<UOM>").append(wdDetailData.getUom()).append("</UOM>");
                                    requestMsg.append("<BUNJA>").append(wdDetailData.getBunja()).append("</BUNJA>");
                                    requestMsg.append("<BUNMO>").append(wdDetailData.getBunmo()).append("</BUNMO>");
                                    requestMsg.append("<TRANQTY>0</TRANQTY>");// 마이너스 처리
                                    requestMsg.append("<SHORTAGETRANQTY>").append(wdDetailData.getConfirmqty()).append("</SHORTAGETRANQTY>");
                                    requestMsg.append("<STOCKID>").append(wdDetailData.getStockid()).append("</STOCKID>");
                                    requestMsg.append("<REASONCODE>Z4</REASONCODE>");
                                    requestMsg.append("<REASONMSG>").append(wdDetailData.getReasonmsg()).append("</REASONMSG>");
                                    requestMsg.append("<REFERENCE02>").append(wdDetailData.getReference02()).append("</REFERENCE02>");

                                    procParam = new  BatchParamsUtil();
                                    procParam.setAVC_SYSTEM("WMSAPP");
                                    procParam.setAVC_EXECUTEMODE("");
                                    procParam.setAVC_COMMAND("CONFIRM");
                                    procParam.setAVC_DCCODE(wdDetailData.getDccode());
                                    procParam.setAVC_STORERKEY("FW00");
                                    procParam.setAVC_ORGANIZE("%");
                                    procParam.setAVC_AREA("%");
                                    procParam.setAVC_REQUESTCODE("");
                                    procParam.setAVC_REQUESTMSG(requestMsg.toString());
                                    procParam.setAVC_WORKER("LOGISONEBATCH");
                                    procParam.setAVC_SECURITYKEY("");
                                    procParam.setAI_SPID(1000000001);
                                    procParam.setVC_RESULTMSG("");
                                    procParam.setVC_RETURNMSG("");
                                    procParam.setAVC_LOG_PARAMS(batchParam.getAVC_LOG_PARAMS());

                                    try {
                                        commonDao.selectOne("exdcDpWdAutoConfirmCancelService.callProcedureWd", procParam);
                                        strErrorCode = String.valueOf(procParam.getI_ERR());
                                    } catch (Exception e) {
                                        errorCheck = "E";
                                        errorStatus = "50"; // 50:패키지 내부 오류
                                        strErrorCode = String.valueOf(procParam.getI_ERR());
                                        reqMsg = "REQUESTMSG =>" + requestMsg.toString();
                                    }

                                    if(Integer.parseInt(strErrorCode) < 0) {
                                        errorCheck = "E";
                                        errorStatus = "50"; // 50:패키지 내부 오류
                                        reqMsg = "REQUESTMSG =>" + requestMsg.toString();
                                    }
                                }


                                //1-8. 매핑테이블 Update
                                inputParam = new ExdcDpWdAutoConfirmCancelReqDto();
                                inputParam.setMapkeyNo(wdDetailData.getMapkeyNo());
                                inputParam.setMapkeyLine(wdDetailData.getMapkeyLine());
                                inputParam.setTmpSokey(wdDetailData.getTmpSokey());
                                inputParam.setTmpSoline(wdDetailData.getTmpSoline());
                                inputParam.setErrorCheck(errorCheck);
                                inputParam.setWorker("LOGISONEBATCH");
                                if("E".equals(errorCheck)) {
                                    inputParam.setErrorStatus(errorStatus);
                                }
                                commonDao.update("exdcDpWdAutoConfirmCancelService.updateMappingTableSO", inputParam);
                            }
                        }
                    }
                }

                errorCheck = ""; //S:정상, E:에러
                errorStatus = "";

                // 2-1. 가입고 취소 대상 조회
                inputParam = new ExdcDpWdAutoConfirmCancelReqDto();
                List<ExdcDpWdAutoConfirmCancelResDto> dpList = commonDao.selectList("exdcDpWdAutoConfirmCancelService.selectDpList", inputParam);
                logSystemOut(copyDto, "INFO", logOption[4], "271", "[sqlId]selectDpList Rows:"+String.valueOf(dpList.size()));

                for(ExdcDpWdAutoConfirmCancelResDto dpData : dpList) {
                    inputParam.setDccode(dpData.getDccode());
                    inputParam.setStorerkey("FW00");
                    inputParam.setSlipno(dpData.getSlipno());
                    inputParam.setSlipdt(dpData.getSlipdt());
                    List<ExdcDpWdAutoConfirmCancelResDto> dpDetailList = commonDao.selectList("exdcDpWdAutoConfirmCancelService.selectDpDetailList", inputParam);
                    logSystemOut(copyDto, "INFO", logOption[4], "279", "[sqlId]selectWdDetailList Rows:"+String.valueOf(dpDetailList.size()));

                    if(!dpDetailList.isEmpty()) {
                        for (ExdcDpWdAutoConfirmCancelResDto dpDetailData : dpDetailList) {
                            errorCheck = "S";

                            //취소 처리할 수량이 0이면 pass
                            int confirmQty = (int) Float.parseFloat(String.valueOf(dpDetailData.getConfirmqty()));
                            if (confirmQty == 0) {
                                reqMsg = "confirmQty=0";
                                break;
                            }

                            // 1-6. SAP 입고생성여부 체크.
                            if("S".equals(errorCheck)) {
                                String id = "findGrCancelAllowed.do";
                                String url = ContextUtil.getProperty("cf.api.srm.url") + id;
                                String username = ContextUtil.getProperty("cf.api.srm.username");
                                String password = ContextUtil.getProperty("cf.api.srm.password");
                                String interfaceAuthKey = ApiAuthUtil.createBasicAuthHeader(username, password);
                                String returnCode = null;

                                Map<String, Object> paramsMap = new HashMap<String, Object>();
                                paramsMap.put("invNo", dpDetailData.getDocNo());
                                paramsMap.put("invLno", dpDetailData.getDocline());
                                Timestamp requestTimestamp = new Timestamp(System.currentTimeMillis());

                                JSONObject response = null;
                                try {
                                    response = apiClient.callApi(url, null, interfaceAuthKey, paramsMap, null);

                                    returnCode = (String) response.get("returnCode");
                                    JSONObject data = (JSONObject) response.get("data");


                                    if(!"S".equals(returnCode) || data == null){
                                        errorCheck = "E";
                                        errorStatus = "40"; // 40:sap 입고생성여부
                                    }else{
                                        String cancelYn = (String) data.get("tmpGrCancelYn");
                                        if(!"Y".equals(cancelYn)){
                                            errorCheck = "E";
                                            errorStatus = "40"; // 40:sap 입고생성여부
                                        }
                                    }

                                } catch (UnsupportedEncodingException e) {
                                    errorCheck = "E";
                                    errorStatus = "40"; // 40:sap 입고생성여부
                                } catch (IOException e) {
                                    errorCheck = "E";
                                    errorStatus = "40"; // 40:sap 입고생성여부
                                } catch (ParseException e) {
                                    errorCheck = "E";
                                    errorStatus = "40"; // 40:sap 입고생성여부
                                } catch (Exception e) {
                                    errorCheck = "E";
                                    errorStatus = "40"; // 40:sap 입고생성여부
                                } finally{
                                    try{
                                        this.logOutBoundApi(returnCode, url, paramsMap, response, requestTimestamp);
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            }


                            if("S".equals(errorCheck)) {
                                // 2-4. 가입고 취소 처리 (DB Pkg 호출)
                                StringBuffer requestMsg = new StringBuffer();
                                requestMsg.append("<DCCODE>").append(dpDetailData.getDccode()).append("</DCCODE>");
                                requestMsg.append("<STORERKEY>").append(dpDetailData.getStorerkey()).append("</STORERKEY>");
                                requestMsg.append("<ORGANIZE>").append(dpDetailData.getOrganize()).append("</ORGANIZE>");
                                requestMsg.append("<DOCDT>").append(dpDetailData.getDocdt()).append("</DOCDT>");
                                requestMsg.append("<DOCNO>").append(dpDetailData.getDocNo()).append("</DOCNO>");
                                requestMsg.append("<DOCLINE>").append(dpDetailData.getDocline()).append("</DOCLINE>");
                                requestMsg.append("<SLIPDT>").append(dpDetailData.getSlipdt()).append("</SLIPDT>");
                                requestMsg.append("<SLIPNO>").append(dpDetailData.getSlipno()).append("</SLIPNO>");
                                requestMsg.append("<SLIPLINE>").append(dpDetailData.getSlipline()).append("</SLIPLINE>");
                                requestMsg.append("<SKU>").append(dpDetailData.getSku()).append("</SKU>");
                                requestMsg.append("<UOM>").append(dpDetailData.getUom()).append("</UOM>");
                                requestMsg.append("<BUNJA>").append(dpDetailData.getBunja()).append("</BUNJA>");
                                requestMsg.append("<BUNMO>").append(dpDetailData.getBunmo()).append("</BUNMO>");
                                requestMsg.append("<TRANQTY>").append(dpDetailData.getConfirmqty().multiply(BigDecimal.valueOf(-1))).append("</TRANQTY>");// 마이너스 처리
                                requestMsg.append("<SHORTAGETRANQTY>").append(dpDetailData.getShortagetranqty()).append("</SHORTAGETRANQTY>");
                                requestMsg.append("<LOTTABLE01>").append(dpDetailData.getLottable01()).append("</LOTTABLE01>");
                                requestMsg.append("<STOCKID>").append(dpDetailData.getStockid()).append("</STOCKID>");
                                requestMsg.append("<STOCKGRADE>").append(dpDetailData.getStockgrade()).append("</STOCKGRADE>");
                                requestMsg.append("<REASONCODE>V").append(dpDetailData.getReasoncode()).append("</REASONCODE>");
                                //requestMsg.append("<REASONMSG>").append(dpDetailData.getReasonmsg()).append("</REASONMSG>");
                                requestMsg.append("<REASONMSG>").append(StringUtils.defaultString(dpDetailData.getReasonmsg())).append("</REASONMSG>");
                                //requestMsg.append("<REFERENCE02>").append(dpDetailData.getReference02()).append("</REFERENCE02>");
                                requestMsg.append("<REFERENCE02>").append(StringUtils.defaultString(dpDetailData.getReference02())).append("</REFERENCE02>");
                                requestMsg.append("<TRANBOX>").append(dpDetailData.getTransbox()).append("</TRANBOX>");

                                BatchParamsUtil procParam = new BatchParamsUtil();
                                procParam.setAVC_SYSTEM("WMSAPP");
                                procParam.setAVC_EXECUTEMODE("");
                                procParam.setAVC_COMMAND("CONFIRM");
                                procParam.setAVC_DCCODE(dpDetailData.getDccode());
                                procParam.setAVC_STORERKEY("FW00");
                                procParam.setAVC_ORGANIZE("%");
                                procParam.setAVC_AREA("%");
                                procParam.setAVC_REQUESTCODE("");
                                procParam.setAVC_REQUESTMSG(requestMsg.toString());
                                procParam.setAVC_WORKER("LOGISONEBATCH");
                                procParam.setAVC_SECURITYKEY("");
                                procParam.setAI_SPID(1000000001);
                                procParam.setVC_RESULTMSG("");
                                procParam.setVC_RETURNMSG("");
                                procParam.setAVC_LOG_PARAMS(batchParam.getAVC_LOG_PARAMS());

                                String strErrorCode = "";
                                try {
                                    commonDao.selectOne("exdcDpWdAutoConfirmCancelService.callProcedureDp", procParam);
                                    strErrorCode = String.valueOf(batchParam.getI_ERR());
                                } catch (Exception e) {
                                    errorCheck = "E";
                                    errorStatus = "50"; // 50:패키지 내부 오류
                                    strErrorCode = String.valueOf(batchParam.getI_ERR());
                                    reqMsg = "REQUESTMSG =>" + requestMsg.toString();
                                }

                                if(Integer.parseInt(strErrorCode) < 0) {
                                    errorCheck = "E";
                                    errorStatus = "50"; // 50:패키지 내부 오류
                                    reqMsg = "REQUESTMSG =>" + requestMsg.toString();
                                }

                                // 1-8. 가출고 결품 처리 (DB Pkg 호출)
                                requestMsg.setLength(0);
                                requestMsg.append("<DCCODE>").append(dpDetailData.getDccode()).append("</DCCODE>");
                                requestMsg.append("<STORERKEY>").append(dpDetailData.getStorerkey()).append("</STORERKEY>");
                                requestMsg.append("<ORGANIZE>").append(dpDetailData.getOrganize()).append("</ORGANIZE>");
                                requestMsg.append("<DOCDT>").append(dpDetailData.getDocdt()).append("</DOCDT>");
                                requestMsg.append("<DOCNO>").append(dpDetailData.getDocNo()).append("</DOCNO>");
                                requestMsg.append("<DOCLINE>").append(dpDetailData.getDocline()).append("</DOCLINE>");
                                requestMsg.append("<SLIPDT>").append(dpDetailData.getSlipdt()).append("</SLIPDT>");
                                requestMsg.append("<SLIPNO>").append(dpDetailData.getSlipno()).append("</SLIPNO>");
                                requestMsg.append("<SLIPLINE>").append(dpDetailData.getSlipline()).append("</SLIPLINE>");
                                requestMsg.append("<SKU>").append(dpDetailData.getSku()).append("</SKU>");
                                requestMsg.append("<UOM>").append(dpDetailData.getUom()).append("</UOM>");
                                requestMsg.append("<BUNJA>").append(dpDetailData.getBunja()).append("</BUNJA>");
                                requestMsg.append("<BUNMO>").append(dpDetailData.getBunmo()).append("</BUNMO>");
                                requestMsg.append("<TRANQTY>0</TRANQTY>");// 마이너스 처리
                                requestMsg.append("<SHORTAGETRANQTY>").append(dpDetailData.getConfirmqty()).append("</SHORTAGETRANQTY>");
                                requestMsg.append("<STOCKID>").append(dpDetailData.getStockid()).append("</STOCKID>");
                                requestMsg.append("<REASONCODE>V</REASONCODE>");
                                //requestMsg.append("<REASONMSG>").append(dpDetailData.getReasonmsg()).append("</REASONMSG>");
                                requestMsg.append("<REASONMSG>").append(StringUtils.defaultString(dpDetailData.getReasonmsg())).append("</REASONMSG>");
                                //requestMsg.append("<REFERENCE02>").append(dpDetailData.getReference02()).append("</REFERENCE02>");
                                requestMsg.append("<REFERENCE02>").append(StringUtils.defaultString(dpDetailData.getReference02())).append("</REFERENCE02>");

                                procParam = new  BatchParamsUtil();
                                procParam.setAVC_SYSTEM("WMSAPP");
                                procParam.setAVC_EXECUTEMODE("");
                                procParam.setAVC_COMMAND("CONFIRM");
                                procParam.setAVC_DCCODE(dpDetailData.getDccode());
                                procParam.setAVC_STORERKEY("FW00");
                                procParam.setAVC_ORGANIZE("%");
                                procParam.setAVC_AREA("%");
                                procParam.setAVC_REQUESTCODE("");
                                procParam.setAVC_REQUESTMSG(requestMsg.toString());
                                procParam.setAVC_WORKER("LOGISONEBATCH");
                                procParam.setAVC_SECURITYKEY("");
                                procParam.setAI_SPID(1000000001);
                                procParam.setVC_RESULTMSG("");
                                procParam.setVC_RETURNMSG("");
                                procParam.setAVC_LOG_PARAMS(batchParam.getAVC_LOG_PARAMS());

                                try {
                                    commonDao.selectOne("exdcDpWdAutoConfirmCancelService.callProcedureDp", procParam);
                                    strErrorCode = String.valueOf(batchParam.getI_ERR());
                                } catch (Exception e) {
                                    errorCheck = "E";
                                    errorStatus = "50"; // 50:패키지 내부 오류
                                    strErrorCode = String.valueOf(batchParam.getI_ERR());
                                    reqMsg = "REQUESTMSG =>" + requestMsg.toString();
                                }

                                if(Integer.parseInt(strErrorCode) < 0) {
                                    errorCheck = "E";
                                    errorStatus = "50"; // 50:패키지 내부 오류
                                    reqMsg = "REQUESTMSG =>" + requestMsg.toString();
                                }
                            }

                            //2-6. 매핑테이블 Update
                            inputParam = new ExdcDpWdAutoConfirmCancelReqDto();
                            inputParam.setMapkeyNo(dpDetailData.getMapkeyNo());
                            inputParam.setMapkeyLine(dpDetailData.getMapkeyLine());
                            inputParam.setTmpDpkey(dpDetailData.getTmpDpkey());
                            inputParam.setTmpDpline(dpDetailData.getTmpDpline());
                            inputParam.setErrorCheck(errorCheck);
                            inputParam.setWorker("LOGISONEBATCH");
                            if("E".equals(errorCheck)) {
                                inputParam.setErrorStatus(errorStatus);
                            }
                            commonDao.update("exdcDpWdAutoConfirmCancelService.updateMappingTableIV", inputParam);
                        }
                    }
                }
                //commonDao.update("exdcDpWdAutoConfirmCancelService.updateStatusEnd", batchParam);
            } catch (Exception e) {
                reqMsg = e.getMessage();
                //BATCH JOB LOG설정
                copyDto.setResultCode("-1");
                copyDto.setResultMsg(reqMsg);
                logSystemOut(copyDto, "INFO", logOption[4], "484", e.getMessage());
            }
        }

		//IF_MASTER설정
		if ( ifStaus.equals("END") ) {
			batchParam.setAVC_COMMAND("END");
			batchParam.setAVC_IFID(BATCH_IF_ID);
			batchParam.setOUT_SUCCESS(Integer.valueOf(copyDto.getResultCode()));
			batchParam.setOUT_ERR_CODE(Integer.valueOf(copyDto.getResultCode()));
			batchParam.setOUT_RETURN_MSG(reqMsg);
			commonDao.selectOne("batchCommonService.procIFStatus", batchParam);
		} else {
			copyDto.setResultCode("-20001");
		}

		//BATCH JOB LOG설정
		logSystemOut(copyDto, "END", logOption[4], "501", "JAVA SERVICE END");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logOutBoundApi(String success, String url, Map<String, Object> requestMap, JSONObject response, Timestamp requestTimestamp){
        //■ 1. API 호출 이전 데이터 수집
        String worker = "LOGISONEBATCH";
        String errorMessage = null;
        String errorStackTrace = null;
        String sourceIp = "0.0.0.0";
        String springProfiles = System.getProperty("spring.profiles.active", "local");
        try {
            InetAddress local = InetAddress.getLocalHost();
            sourceIp = local.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        Timestamp responseTimestamp = new Timestamp(System.currentTimeMillis());
        long latency = responseTimestamp.getTime() - requestTimestamp.getTime();

        ApiReqDto apiInputParam = new  ApiReqDto();
        apiInputParam.setSTORERKEY("FW00");
        apiInputParam.setHTTPMETHOD("POST");
        apiInputParam.setURL(url);
        apiInputParam.setSOURCEIP(sourceIp);
        apiInputParam.setREQUESTTIME(requestTimestamp.toString());
        apiInputParam.setRESPONSETIME(responseTimestamp.toString());
        apiInputParam.setLATENCY(latency);
        apiInputParam.setREQUESTBODY(Objects.toString(requestMap, ""));
        apiInputParam.setRESPONSEBODY(Objects.toString(response, ""));
        apiInputParam.setSTATUS(success);
        apiInputParam.setERRORSTACKTRACE(errorStackTrace);
        apiInputParam.setWORKER(worker);

        commonDao.selectOne("exdcDpWdAutoConfirmCancelService.saveApiLog", apiInputParam);
    }

    /*
     * @description : System.out.println ==> BATCH LOG기록으로 전환
     */
    public void logSystemOut(BatchLogParamsDto reqDto, String jobStatus, String isPrint, String lineNo, String logMsg) {
        if (!StringUtil.isEmpty(reqDto.getJobExecutionId())) {
            reqDto.setJobStatus(jobStatus);
            reqDto.setLineNo(lineNo);
            reqDto.setResultMsg(logMsg);
            try {
                commonDao.selectOne("batchCommonService.insertBatchLog", reqDto);
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        }
    }
}