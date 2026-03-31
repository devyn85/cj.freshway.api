package cjfw.batch.shr0160;

import cjfw.batch.common.EaiLocaterService;
import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.batch.common.dto.BatchParamsUtil;
import cjfw.batch.shr0160.soap.DTSHR0160EAI;
import cjfw.batch.shr0160.soap.SISHR0160EAIAO;
import cjfw.batch.shr0160.soap.SISHR0160EAIAOService;
import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.common.util.StringUtil;
import jakarta.xml.ws.BindingProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailSendBatchService {

	private final EaiLocaterService eaiLocaterService;

    private final CommonDao commonDao;

	public void sendEmail(BatchParamsUtil batchParam, BatchLogParamsDto batchLogDto) {
        //BATCH JOB LOG설정
        String[] logOption = batchParam.getAVC_LOG_PARAMS().split("\\^"); //AVC_LOG_PARAMS 문자열 분리
        BatchLogParamsDto copyDto = new BatchLogParamsDto();
        if (batchLogDto != null) {
            BeanUtils.copyProperties(batchLogDto, copyDto);
            copyDto.setJobDiv("JAVA SERVICE");
            copyDto.setNodeLevel(batchLogDto.getNodeLevel()+1 );
            copyDto.setResultCode("0");
            logSystemOut(copyDto, "START", logOption[4], "44", "JAVA SERVICE START");
        }

        // 이메일 리스트 조회
        List<EmailSendParamsDto> emailList = commonDao.selectList("eailSendBatchService.selectEmail");
        logSystemOut(copyDto, "INFO", logOption[4], "48", "EMAIL발송 대상 건수:"+emailList.size());
        int succCount = 0;
        int errCount = 0;
        String result = null;
        Map<String, String> locator = null;
        if(!emailList.isEmpty()){
            for(EmailSendParamsDto dataList : emailList){
                try {
                    DateTimeFormatter dayFormatter  = DateTimeFormatter.ofPattern("yyyyMMdd");
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");

                    LocalDateTime now = LocalDateTime.now();
                    String dayString  = now.format(dayFormatter);
                    String timeString = now.format(timeFormatter);

                    DTSHR0160EAI sendData = new DTSHR0160EAI();
                    sendData.setXSYS("SCM");
                    sendData.setXDATS(dayString);
                    sendData.setXTIMS(timeString);
                    sendData.setSUBJECT(dataList.getTitle());
                    sendData.setFROM(dataList.getSendrEmailAddr());
                    sendData.setTO(dataList.getRcvrEmailAddr());
                    sendData.setCONTENTTYPE("text/html");
                    sendData.setCONTENT(dataList.getCnts());

                    locator = eaiLocaterService.getLocater("SHR0160");

                    // JAX-WS Service & Port 생성
                    SISHR0160EAIAOService service = new SISHR0160EAIAOService();
                    SISHR0160EAIAO port = service.getPort(SISHR0160EAIAO.class);

                    Map<String, Object> context = ((BindingProvider) port).getRequestContext();
                    context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, locator.get("endpoint"));
                    context.put(BindingProvider.USERNAME_PROPERTY, locator.get("username"));
                    context.put(BindingProvider.PASSWORD_PROPERTY, locator.get("password"));

                    port.siSHR0160EAIAO(sendData);
                    result = "S";
                    succCount++;
                } catch (Exception e) {
                    result = "E";
                    errCount++;
                } finally {
                    // 전송 결과 저장
                    EmailSendParamsDto emailSendParam = new EmailSendParamsDto();// 전송 결과 저장용 DTO
                    emailSendParam.setResult(result);
                    emailSendParam.setEmailNo(dataList.getEmailNo());
                    commonDao.update("eailSendBatchService.updateEmail", emailSendParam);
                }
            }
            logSystemOut(copyDto, "INFO", logOption[4], "70", "성공건수:"+succCount + "건 실패건수:" + errCount + "건");
        }
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