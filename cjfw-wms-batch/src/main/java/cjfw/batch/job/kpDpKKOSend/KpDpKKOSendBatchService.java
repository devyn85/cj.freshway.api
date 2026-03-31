package cjfw.batch.job.kpDpKKOSend;

import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.batch.common.dto.BatchParamsUtil;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.utility.ContextUtil;
import cjfw.wms.common.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class KpDpKKOSendBatchService {

    private final CommonDao commonDao;

    @Transactional
    public void inspectAutoSms1th(BatchParamsUtil batchParam, BatchLogParamsDto batchLogDto) throws JobExecutionException {
        //BATCH JOB LOG설정
        BatchLogParamsDto copyDto = new BatchLogParamsDto();
        if (batchLogDto != null) {
            BeanUtils.copyProperties(batchLogDto, copyDto);
            copyDto.setJobDiv("JAVA SERVICE");
            copyDto.setNodeLevel(batchLogDto.getNodeLevel()+1 );
            copyDto.setResultCode("0");
            logSystemOut(copyDto, "START", "0", new Throwable().getStackTrace()[0].getLineNumber() + "", "JAVA SERVICE START");
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("CODELIST", "KKO_1st_DC");
        List<KpDpKKOSendParamsDto> sendList = commonDao.selectList("kpDpKKOSendService.select1stMsgHeader", (Object) paramMap);
        logSystemOut(copyDto, "INFO", "0", new Throwable().getStackTrace()[0].getLineNumber() + "", "SMS1차 자동전송 대상조회:"+sendList.size());
        int succCountOrd = 0;
        int succCountKko = 0;
        int errCountOrd = 0;
        int errCountKko = 0;
        if(!sendList.isEmpty()){
            for(KpDpKKOSendParamsDto sendData : sendList){
                try {
                    String custkey = sendData.getCUSTKEY();
                    String custname = sendData.getCUSTNAME();
                    String dcname = sendData.getDCNAME();
                    String dcphone = sendData.getDCPHONE();
                    String slipdt = sendData.getSLIPDT();

                    //알림톡 내용 작성
                    String kko_msg = "[협력사명] " + custkey + " " + custname + "\n" +
                            "[센터명] " + dcname + " " + dcphone + "\n" +
                            "[센터입고일자] " + slipdt + "\n\n" +
                            dcname + " 입고 전입니다." + "\n\n" +
                            "21시 이후 입고로 인해 고객 출차 지연되거나 미납 상품 발생하는 경우, 그 책임은 협력사에 있으므로 반드시 입고 시간 준수해 주시기 바랍니다." + "\n\n" + "감사합니다.";

                    sendData.setSENDTITLE("미입고 내역 안내");
                    sendData.setSENDPHONE("1588-8161");
                    sendData.setTEMPLATECODE("250225_SCM1");
                    sendData.setTEMPLATETITLE("CJ프레시웨이 센터 미입고 알림톡입니다.");
                    sendData.setPROFILEKEY(ContextUtil.getProperty("cf.kakao.appkey"));

                    //협력사 담당자 정보 & 협력사 수신확대 정보 조회
                    List<KpDpKKOSendParamsDto> custRcvSMSlist = commonDao.selectList("kpDpKKOSendService.getInspectCustRcvSMS", sendData);
                    sendData.setSENDMESSAGE(kko_msg);

                    try {
                        //수신자1) 협력사담당자(A01,A03,A06) 연락처가 있는 경우 전송
                        if (!custRcvSMSlist.isEmpty()) {
                            for (KpDpKKOSendParamsDto insertData : custRcvSMSlist) {
                                sendData.setRECEIVEPHONE(insertData.getEMPPHONE1());
                                sendData.setRECEIVENAME(insertData.getEMPNAME1());
                                commonDao.insert("kpDpKKOSendService.insertInspectKKO", sendData);
                                succCountKko++;
                            }
                        }
                    } catch (Exception e) {
                        //BATCH JOB LOG설정
                        errCountKko++;
                        copyDto.setResultCode("-1");
                        logSystemOut(copyDto, "ERR", "0", new Throwable().getStackTrace()[0].getLineNumber() + "", e.toString());
                    }
                    succCountOrd++;
                } catch (Exception e){
                    //BATCH JOB LOG설정
                    errCountOrd++;
                    copyDto.setResultCode("-1");
                    logSystemOut(copyDto, "ERR", "0", new Throwable().getStackTrace()[0].getLineNumber() + "", e.toString());
                }
            }
            logSystemOut(copyDto, "INFO", "0", new Throwable().getStackTrace()[0].getLineNumber() + "",
                    "성공건수: 주문-"+succCountOrd + "건 발송-" + succCountKko + "건 실패건수: 주문-" + errCountOrd + "건 발송-" + errCountKko + "건");
        }
        //BATCH JOB LOG설정
        logSystemOut(copyDto, "END", "0", new Throwable().getStackTrace()[0].getLineNumber() + "", "JAVA SERVICE END");
    }

    @Transactional
    public void inspectAutoSms2th(BatchParamsUtil batchParam, BatchLogParamsDto batchLogDto) throws JobExecutionException {
        //BATCH JOB LOG설정
        BatchLogParamsDto copyDto = new BatchLogParamsDto();
        if (batchLogDto != null) {
            BeanUtils.copyProperties(batchLogDto, copyDto);
            copyDto.setJobDiv("JAVA SERVICE");
            copyDto.setNodeLevel(batchLogDto.getNodeLevel()+1 );
            copyDto.setResultCode("0");
            logSystemOut(copyDto, "START", "0", new Throwable().getStackTrace()[0].getLineNumber() + "", "JAVA SERVICE START");
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("GUBUN", "2");
        paramMap.put("CODELIST", "KKO_2nd_DC");
        List<KpDpKKOSendParamsDto> sendList = commonDao.selectList("kpDpKKOSendService.selectMsgHeader", (Object) paramMap);
        logSystemOut(copyDto, "INFO", "0", new Throwable().getStackTrace()[0].getLineNumber() + "", "SMS2차 자동전송 대상조회(주문): "+sendList.size());
        int succCountOrd = 0;
        int succCountKko = 0;
        int errCountOrd = 0;
        int errCountKko = 0;
        if(!sendList.isEmpty()){
            for(KpDpKKOSendParamsDto sendData : sendList){
                try {
                    List<KpDpKKOSendParamsDto> chkComp = commonDao.selectList("kpDpKKOSendService.selectMsgDetail", sendData);
                    String[] aMultiMdcode = new String[chkComp.size()];
                    KpDpKKOSendParamsDto paramData = new KpDpKKOSendParamsDto();

                    String custkey = sendData.getCUSTKEY();
                    String custname = sendData.getCUSTNAME();
                    String dcname = sendData.getDCNAME();
                    String dcphone = sendData.getDCPHONE();
                    String slipdt = sendData.getSLIPDT();
                    String dpunload_time = sendData.getDPUNLOADTIME();

                    //알림톡 내용 작성
                    String kko_msg     = "";
                    String kko_msg2    = "";

                    if(!chkComp.isEmpty()) {
                        //알림톡 내용 작성
                        kko_msg = "[협력사명] " + custkey + " " + custname + "\n" +
                                "[센터명] " + dcname + " " + dcphone + "\n" +
                                "[센터입고일자] " + slipdt + "\n" +
                                "[센터입고시간] " + dpunload_time + "\n\n" +
                                "[미검수현황] " + "\n";

                        for(int s = 0 ; s < chkComp.size() ; s++) {
                            KpDpKKOSendParamsDto iParam = chkComp.get(s);
                            kko_msg2 = "●" + iParam.getSKU() + " " + iParam.getSKUNAME() +
                                    "-" + iParam.getNONINSPECTQTY() + " " + iParam.getSOMDNAME() + "\n";

                            if ( iParam.getSOMDCODE() != null ) {
                                aMultiMdcode[s] = iParam.getSOMDCODE();
                            } else {
                                aMultiMdcode[s] = "NONE";
                            }
                        }

                        paramData.setSTORERKEY("FW00");
                        paramData.setMULTIMDCODE(List.of(aMultiMdcode));

                        /*inParams.put("SENDMESSAGE", kko_msg.toString());*/
                        sendData.setSENDTITLE("미입고 내역 안내");
                        sendData.setSENDPHONE("1588-8161");
                        sendData.setTEMPLATECODE("250411_SCM미검수(22시30분)");
                        sendData.setTEMPLATETITLE("CJ프레시웨이 미검수현황 알림톡입니다.");
                        sendData.setPROFILEKEY(ContextUtil.getProperty("cf.kakao.appkey"));
                    }

                    //협력사 담당자 정보 & 협력사 수신확대 정보 조회
                    List<KpDpKKOSendParamsDto> custRcvSMSlist = commonDao.selectList("kpDpKKOSendService.getInspectCustRcvSMS", sendData);
                    List<KpDpKKOSendParamsDto> custMdSMSlist = commonDao.selectList("kpDpKKOSendService.getInspectMdRcvSMS", paramData);
                    //실제 발송 메세지
                    int headerLen = kko_msg.length();

                    //알림톡 글자수 1000자로 자르기
                    String[] strArr = getMaxByteStringArray(kko_msg2, 1000-headerLen, -1);
                    for (int a = 0; a < strArr.length; a++) {
                        String kko_msg3 = kko_msg + strArr[a] ;
                        sendData.setSENDMESSAGE(kko_msg3);

                        //수신자1) 협력사담당자(A01,A03,A06) 연락처가 있는 경우 전송
                        try {
                            if(!custRcvSMSlist.isEmpty()){
                                for (KpDpKKOSendParamsDto custRcvSMS : custRcvSMSlist) {
                                    sendData.setRECEIVEPHONE(custRcvSMS.getEMPPHONE1());
                                    sendData.setRECEIVENAME(custRcvSMS.getEMPNAME1());
                                    commonDao.insert("kpDpKKOSendService.insertInspectKKO", sendData);
                                    succCountKko++;
                                }
                            }
                        } catch (Exception e){
                            //BATCH JOB LOG설정
                            errCountKko++;
                            copyDto.setResultCode("-1");
                            logSystemOut(copyDto, "ERR", "0", new Throwable().getStackTrace()[0].getLineNumber() + "", e.toString());
                        }

                        try {
                            //수신자2) 상품MD담당자 연락처가 있는 경우 전송
                            if(!custMdSMSlist.isEmpty()){
                                for (KpDpKKOSendParamsDto custMdSMSdata : custMdSMSlist) {
                                    sendData.setRECEIVEPHONE(custMdSMSdata.getEMPPHONE1());
                                    sendData.setRECEIVENAME(custMdSMSdata.getEMPNAME1());
                                    commonDao.insert("kpDpKKOSendService.insertInspectKKO", sendData);
                                    succCountKko++;
                                }
                            }
                        } catch (Exception e){
                            //BATCH JOB LOG설정
                            errCountKko++;
                            copyDto.setResultCode("-1");
                            logSystemOut(copyDto, "ERR", "0", new Throwable().getStackTrace()[0].getLineNumber() + "", e.toString());
                        }
                    }
                    succCountOrd++;
                } catch (Exception e){
                    //BATCH JOB LOG설정
                    errCountOrd++;
                    copyDto.setResultCode("-1");
                    logSystemOut(copyDto, "ERR", "0", new Throwable().getStackTrace()[0].getLineNumber() + "", e.toString());
                }
            }
            logSystemOut(copyDto, "INFO", "0", new Throwable().getStackTrace()[0].getLineNumber() + "",
                    "성공건수: 주문-"+succCountOrd + "건 발송-" + succCountKko + "건 실패건수: 주문-" + errCountOrd + "건 발송-" + errCountKko + "건");
        }
        //BATCH JOB LOG설정
        logSystemOut(copyDto, "END", "0", new Throwable().getStackTrace()[0].getLineNumber() + "", "JAVA SERVICE END");
    }

    @Transactional
    public void inspectAutoSms3th(BatchParamsUtil batchParam, BatchLogParamsDto batchLogDto) throws JobExecutionException {
        //BATCH JOB LOG설정
        BatchLogParamsDto copyDto = new BatchLogParamsDto();
        if (batchLogDto != null) {
            BeanUtils.copyProperties(batchLogDto, copyDto);
            copyDto.setJobDiv("JAVA SERVICE");
            copyDto.setNodeLevel(batchLogDto.getNodeLevel()+1 );
            copyDto.setResultCode("0");
            logSystemOut(copyDto, "START", "0", new Throwable().getStackTrace()[0].getLineNumber() + "", "JAVA SERVICE START");
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("GUBUN", "3");
        paramMap.put("CODELIST", "KKO_3rd_DC");
        List<KpDpKKOSendParamsDto> sendList = commonDao.selectList("kpDpKKOSendService.selectMsgHeader", (Object) paramMap);
        logSystemOut(copyDto, "INFO", "0", new Throwable().getStackTrace()[0].getLineNumber() + "", "SMS3차 자동전송 대상조회: "+sendList.size());
        int succCountOrd = 0;
        int succCountKko = 0;
        int errCountOrd = 0;
        int errCountKko = 0;
        if(!sendList.isEmpty()){
            for(KpDpKKOSendParamsDto sendData : sendList){
                try {
                    List<KpDpKKOSendParamsDto> chkComp = commonDao.selectList("kpDpKKOSendService.selectMsgDetail", sendData);
                    String[] aMultiMdcode = new String[chkComp.size()];
                    KpDpKKOSendParamsDto inParams = new KpDpKKOSendParamsDto();

                    String custkey = sendData.getCUSTKEY();
                    String custname = sendData.getCUSTNAME();
                    String dcname = sendData.getDCNAME();
                    String dcphone = sendData.getDCPHONE();
                    String slipdt = sendData.getSLIPDT();
                    String dpunload_time = sendData.getDPUNLOADTIME();

                    //알림톡 내용 작성
                    String kko_msg     = "";
                    String kko_msg2    = "";

                    if(!chkComp.isEmpty()) {
                        kko_msg = "[협력사명] " + custkey + " " + custname + "\n" +
                                "[센터명] " + dcname + " " + dcphone + "\n" +
                                "[센터입고일자] " + slipdt + "\n" +
                                "[센터입고시간] " + dpunload_time + "\n\n" +
                                "[미검수현황]" + "\n";
                        for(int s = 0 ; s < chkComp.size() ; s++) {
                            KpDpKKOSendParamsDto iParam = chkComp.get(s);
                            kko_msg2 = "●" + iParam.getSKU() + iParam.getSKUNAME() +
                                    "-" + iParam.getNONINSPECTQTY() + " " + iParam.getSOMDNAME() + iParam.getSOMDNAME() + "\n";

                            if ( iParam.getSOMDCODE() != null ) {
                                aMultiMdcode[s] = iParam.getSOMDCODE();
                            } else {
                                aMultiMdcode[s] = "NONE";
                            }
                        }

                        inParams.setSTORERKEY("FW00");
                        inParams.setMULTIMDCODE(List.of(aMultiMdcode));

                        /*inParams.put("SENDMESSAGE", kko_msg.toString());*/
                        sendData.setSENDTITLE("미입고 내역 안내");
                        sendData.setSENDPHONE("1588-8161");
                        sendData.setTEMPLATECODE("SCM_2차_미검수");
                        sendData.setTEMPLATETITLE("CJ프레시웨이 미검수현황 알림톡입니다.");
                        sendData.setPROFILEKEY(ContextUtil.getProperty("cf.kakao.appkey"));
                    }

                    //협력사 담당자 정보 & 협력사 수신확대 정보 조회
                    List<KpDpKKOSendParamsDto> custRcvSMSlist = commonDao.selectList("kpDpKKOSendService.getInspectCustRcvSMS", sendData);
                    List<KpDpKKOSendParamsDto> custMdSMSlist = commonDao.selectList("kpDpKKOSendService.getInspectMdRcvSMS", inParams);
                    //실제 발송 메세지
                    int headerLen = kko_msg.length();

                    //알림톡 글자수 1000자로 자르기
                    String[] strArr = getMaxByteStringArray(kko_msg2, 1000-headerLen, -1);
                    for (String s : strArr) {
                        sendData.setSENDMESSAGE(kko_msg + s);

                        try {
                            //수신자1) 협력사담당자(A01,A03,A06) 연락처가 있는 경우 전송
                            if (!custRcvSMSlist.isEmpty()) {
                                for (KpDpKKOSendParamsDto custRcvSMS : custRcvSMSlist) {
                                    sendData.setRECEIVEPHONE(custRcvSMS.getEMPPHONE1());
                                    sendData.setRECEIVENAME(custRcvSMS.getEMPNAME1());
                                    commonDao.insert("kpDpKKOSendService.insertInspectKKO", sendData);
                                    succCountKko++;
                                }
                            }
                        } catch (Exception e) {
                            errCountKko++;
                            copyDto.setResultCode("-1");
                            logSystemOut(copyDto, "ERR", "0", new Throwable().getStackTrace()[0].getLineNumber() + "", e.toString());
                        }

                        try {
                            //수신자2) 상품MD담당자 연락처가 있는 경우 전송
                            if (!custMdSMSlist.isEmpty()) {
                                for (KpDpKKOSendParamsDto custMdSMSdata : custMdSMSlist) {
                                    sendData.setRECEIVEPHONE(custMdSMSdata.getEMPPHONE1());
                                    sendData.setRECEIVENAME(custMdSMSdata.getEMPNAME1());
                                    commonDao.insert("kpDpKKOSendService.insertInspectKKO", sendData);
                                    succCountKko++;
                                }
                            }
                        } catch (Exception e) {
                            errCountKko++;
                            copyDto.setResultCode("-1");
                            logSystemOut(copyDto, "ERR", "0", new Throwable().getStackTrace()[0].getLineNumber() + "", e.toString());
                        }
                    }
                    succCountOrd++;
                } catch (Exception e){
                    //BATCH JOB LOG설정
                    errCountOrd++;
                    copyDto.setResultCode("-1");
                    logSystemOut(copyDto, "ERR", "0", new Throwable().getStackTrace()[0].getLineNumber() + "", e.toString());
                }
            }
            logSystemOut(copyDto, "INFO", "0", new Throwable().getStackTrace()[0].getLineNumber() + "",
                    "성공건수: 주문-"+succCountOrd + "건 발송-" + succCountKko + "건 실패건수: 주문-" + errCountOrd + "건 발송-" + errCountKko + "건");
        }
        //BATCH JOB LOG설정
        logSystemOut(copyDto, "END", "0", new Throwable().getStackTrace()[0].getLineNumber() + "", "JAVA SERVICE END");
    }


    @Transactional
    public void inspectAutoSms4th(BatchLogParamsDto batchLogDto) throws JobExecutionException {
        //BATCH JOB LOG설정
        BatchLogParamsDto copyDto = new BatchLogParamsDto();
        if (batchLogDto != null) {
            BeanUtils.copyProperties(batchLogDto, copyDto);
            copyDto.setJobDiv("JAVA SERVICE");
            copyDto.setNodeLevel(batchLogDto.getNodeLevel()+1 );
            copyDto.setResultCode("0");
            logSystemOut(copyDto, "START", "0", "44", "JAVA SERVICE START");
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("CODELIST", "KKO_4th_DC");

        List<KpDpKKOSendParamsDto> sendList = commonDao.selectList("kpDpKKOSendService.selectMsg4thHeader", (Object) paramMap);
        logSystemOut(copyDto, "INFO", "0", "48", "SMS4차 자동전송 대상조회: "+sendList.size());
        int succCountOrd = 0;
        int succCountKko = 0;
        int errCountOrd = 0;
        int errCountKko = 0;
        if(!sendList.isEmpty()){
            for(KpDpKKOSendParamsDto sendData : sendList){
                try {
                    List<KpDpKKOSendParamsDto> chkComp = commonDao.selectList("kpDpKKOSendService.selectMsg4thDetail", sendData);
                    String[] aMultiMdcode = new String[chkComp.size()];
                    KpDpKKOSendParamsDto inParams = new KpDpKKOSendParamsDto();

                    String custkey = sendData.getCUSTKEY();
                    String custname = sendData.getCUSTNAME();
                    String dcname = sendData.getDCNAME();
                    String dcphone = sendData.getDCPHONE();
                    String slipdt = sendData.getSLIPDT();
                    String dpunload_time = sendData.getDPUNLOADTIME();

                    //알림톡 내용 작성
                    String kko_msg     = "";
                    String kko_msg2    = "";

                    if(!chkComp.isEmpty()) {
                        kko_msg = "[협력사명] " + custkey + " " + custname + "\n" +
                                "[센터명] " + dcname + " " + dcphone + "\n" +
                                "[센터입고일자] " + slipdt + "\n" +
                                "[센터입고시간] " + dpunload_time + "\n\n" +
                                "[결품현황]" + "\n";
                        for(int s = 0 ; s < chkComp.size() ; s++) {
                            KpDpKKOSendParamsDto iParam = chkComp.get(s);
                            kko_msg2 = "●" + iParam.getSKU() + " " + iParam.getSKUNAME() +
                                    "-" + iParam.getSHORTAGEQTY() + iParam.getUOM()+ " " + iParam.getSOMDNAME()+ "\n";

                            if ( iParam.getSOMDCODE() != null ) {
                                aMultiMdcode[s] = iParam.getSOMDCODE();
                            } else {
                                aMultiMdcode[s] = "NONE";
                            }
                        }

                        inParams.setSTORERKEY("FW00");
                        inParams.setMULTIMDCODE(List.of(aMultiMdcode));

                        sendData.setSENDTITLE("결품 내역 안내");
                        sendData.setSENDPHONE("1588-8161");
                        sendData.setTEMPLATECODE("250312_SCM4차결품");
                        sendData.setTEMPLATETITLE("CJ프레시웨이 결품 알림톡입니다.");
                        sendData.setPROFILEKEY(ContextUtil.getProperty("cf.kakao.appkey"));
                    }

                    //협력사 담당자 정보 & 협력사 수신확대 정보 조회
                    List<KpDpKKOSendParamsDto> custRcvSMSlist = commonDao.selectList("kpDpKKOSendService.getInspectCustRcvSMS", sendData);
                    List<KpDpKKOSendParamsDto> custMdSMSlist = commonDao.selectList("kpDpKKOSendService.getInspectMdRcvSMS", inParams);
                    //실제 발송 메세지
                    int headerLen = kko_msg.length();

                    //알림톡 글자수 1000자로 자르기
                    String[] strArr = getMaxByteStringArray(kko_msg2, 1000-headerLen, -1);
                    for (String s : strArr) {
                        sendData.setSENDMESSAGE(kko_msg + s);

                        try {
                            //수신자1) 협력사담당자(A01,A03,A06) 연락처가 있는 경우 전송
                            if (!custRcvSMSlist.isEmpty()) {
                                for (KpDpKKOSendParamsDto custRcvSMS : custRcvSMSlist) {
                                    sendData.setRECEIVEPHONE(custRcvSMS.getEMPPHONE1());
                                    sendData.setRECEIVENAME(custRcvSMS.getEMPNAME1());
                                    commonDao.insert("kpDpKKOSendService.insertInspectKKO", sendData);
                                    succCountKko++;
                                }
                            }
                        } catch (Exception e) {
                            errCountKko++;
                            copyDto.setResultCode("-1");
                            logSystemOut(copyDto, "ERR", "0", new Throwable().getStackTrace()[0].getLineNumber() + "", e.toString());
                        }

                        try {
                            //수신자2) 상품MD담당자 연락처가 있는 경우 전송
                            if (!custMdSMSlist.isEmpty()) {
                                for (KpDpKKOSendParamsDto custMdSMSdata : custMdSMSlist) {
                                    sendData.setRECEIVEPHONE(custMdSMSdata.getEMPPHONE1());
                                    sendData.setRECEIVENAME(custMdSMSdata.getEMPNAME1());
                                    commonDao.insert("kpDpKKOSendService.insertInspectKKO", sendData);
                                    succCountKko++;
                                }
                            }
                        } catch (Exception e) {
                            errCountKko++;
                            copyDto.setResultCode("-1");
                            logSystemOut(copyDto, "ERR", "0", new Throwable().getStackTrace()[0].getLineNumber() + "", e.toString());
                        }
                    }
                } catch (Exception e){
                    //BATCH JOB LOG설정
                    errCountOrd++;
                    copyDto.setResultCode("-1");
                    logSystemOut(copyDto, "ERR", "0", new Throwable().getStackTrace()[0].getLineNumber() + "", e.toString());
                }
                succCountOrd++;
            }
            logSystemOut(copyDto, "INFO", "0", new Throwable().getStackTrace()[0].getLineNumber() + "",
                    "성공건수: 주문-"+succCountOrd + "건 발송-" + succCountKko + "건 실패건수: 주문-" + errCountOrd + "건 발송-" + errCountKko + "건");
        }
        //BATCH JOB LOG설정
        logSystemOut(copyDto, "END", "0", new Throwable().getStackTrace()[0].getLineNumber() + "", "JAVA SERVICE END");
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


    public static String[]getMaxByteStringArray(String str, int maxLen, int maxArrays) {
        StringBuilder sb = new StringBuilder();
        ArrayList strList = new ArrayList();

        int curLen = 0;

        String curChar;
        for (int i = 0; i < str.length(); i++) {
            curChar = str.substring(i, i+1);
            curLen += curChar.length();

            if (curLen > maxLen) {
                if (maxArrays == -1 || strList.size() <= maxArrays-2) {
                    strList.add(sb.toString());
                    sb = new StringBuilder();
                    curLen = 0;
                    i--;
                }
                else
                    break;
            }
            else
                sb.append(curChar);
        }

        strList.add(sb.toString());

        String[] strArr = new String[strList.size()];
        for (int i = 0; i < strList.size(); i++) {
            strArr[i] = (String) strList.get(i);
        }

        return strArr;
    }
}