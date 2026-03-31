package cjfw.batch.shr0130;

import cjfw.batch.common.EaiLocaterService;
import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.batch.common.dto.BatchParamsUtil;
import cjfw.batch.shr0130.soap.DTSHR0130EAI;
import cjfw.batch.shr0130.soap.DTSHR0130EAIResponse;
import cjfw.batch.shr0130.soap.SISHR0130EAISO;
import cjfw.batch.shr0130.soap.SISHR0130EAISOService;
import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.common.util.StringUtil;
import jakarta.xml.ws.BindingProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsSendBatchService {

	private static final String BATCH_IF_ID = "SHR0180"; //SMS 발송
	private final CommonDao commonDao;

	private final EaiLocaterService eaiLocaterService;

	// 서비스/다른 곳에서 직접 호출할 수 있도록 로직 분리
	public void callSmsSendJobService(BatchParamsUtil batchParam) throws JobExecutionException {
		callSmsSendJobService(batchParam, null);
	}

	@Transactional
	public void callSmsSendJobService(BatchParamsUtil batchParam, BatchLogParamsDto batchLogDto) throws JobExecutionException {
		//BATCH JOB LOG설정
		String[] logOption = batchParam.getAVC_LOG_PARAMS().split("\\^"); //AVC_LOG_PARAMS 문자열 분리 
		BatchLogParamsDto copyDto = new BatchLogParamsDto();
		if (batchLogDto != null) {
			BeanUtils.copyProperties(batchLogDto, copyDto);
			copyDto.setJobDiv("JAVA SERVICE");
			copyDto.setNodeLevel(batchLogDto.getNodeLevel()+1 );
			copyDto.setCommand(BATCH_IF_ID);
			copyDto.setResultCode("0");
			logSystemOut(copyDto, "START", logOption[4], "52", "JAVA SERVICE START");
		}

		SmsSendParamsDto defaultParams = new SmsSendParamsDto();
		String errorMessage = null;
		String ifStaus = null; //IF_MASTER 호출결과

		batchParam.setAVC_COMMAND("START");	// CHECK(*1)후 START()방식 또는 직접 START(*END)방식 모두 가능함.
		batchParam.setAVC_IFID(BATCH_IF_ID);
		commonDao.selectOne("batchCommonService.procIFStatus", batchParam);
		ifStaus = batchParam.getOUT_RETURN_MSG();
		logSystemOut(copyDto, "INFO", logOption[4], "63", "IF_MASTER SET START RESULT:"+ (ifStaus.equals("END")?"OK":ifStaus));
		if( ifStaus.equals("END") ) {
			try {
				List<SmsSendParamsDto> sendList = commonDao.selectList("smsSendBatchService.selectSms"); 
				logSystemOut(copyDto, "INFO", logOption[4], "67", "SMS발송 대상 건수:"+sendList.size());
				if(sendList!=null && !sendList.isEmpty()){
					for(SmsSendParamsDto sendData : sendList){
						String result = sendSms(sendData.getSENDPHONE()
									   			, sendData.getRECEIVEPHONE()
												, sendData.getSENDDT()
												, sendData.getSENDMESSAGE()
						);

						if("S".equals(result)){
							defaultParams.setSERIALKEY(sendData.getSERIALKEY());
							commonDao.update("smsSendBatchService.updateSms", defaultParams);
						}
					}
				}
			} catch (Exception e){
				errorMessage = e.getMessage();
				//BATCH JOB LOG설정
				copyDto.setResultCode("-1");
				logSystemOut(copyDto, "INFO", logOption[4], "86", e.toString());
			} finally {
				commonDao.update("smsSendBatchService.updateSms", defaultParams);
			}
		}

		//IF_MASTER설정
		if ( ifStaus.equals("END") ) {
			batchParam.setAVC_COMMAND("END");
			batchParam.setAVC_IFID(BATCH_IF_ID);
			batchParam.setOUT_SUCCESS(Integer.valueOf(copyDto.getResultCode()));
			batchParam.setOUT_ERR_CODE(Integer.valueOf(copyDto.getResultCode()));
			batchParam.setOUT_RETURN_MSG(errorMessage);
			commonDao.selectOne("batchCommonService.procIFStatus", batchParam);
		} else {
			copyDto.setResultCode("-20001");
		}

		//BATCH JOB LOG설정
		logSystemOut(copyDto, "END", logOption[4], "105", "JAVA SERVICE END");
	}

	/**
	 * WebService를 통해서 단문 SMS메시지를 전송
	 *
	 * @param fromPhoneNo 발신 전화번호 (01*-****-**** OR 01********)
	 * @param toPhoneNo 수신 전화번호 (01*-****-**** OR 01********)
	 * @param reserveDate 전송날짜(YYYYMMDDHHMMSS)
	 * @param message 메시지
	 * @return 결과값(성공 : S, 실패 : F)
	 */
	public String sendSms(String fromPhoneNo, String toPhoneNo, String reserveDate, String message) {
		String result = null;
		Map<String, String> locator = null;

		try {
			DateTimeFormatter dayFormatter  = DateTimeFormatter.ofPattern("yyyyMMdd");
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");

			LocalDateTime now = LocalDateTime.now();
			String dayString  = now.format(dayFormatter);
			String timeString = now.format(timeFormatter);

			DTSHR0130EAI smsData = new DTSHR0130EAI();
			smsData.setXSYS("SCM");
			smsData.setXDATS(dayString);
			smsData.setXTIMS(timeString);
			smsData.setXROWS("1");

			List<DTSHR0130EAI.TPARAM> paramList = new ArrayList<>();
			DTSHR0130EAI.TPARAM param = new DTSHR0130EAI.TPARAM();

			param.setID("SCM");
			param.setCALLBACK(fromPhoneNo);
			param.setPHONE(toPhoneNo);
			if(reserveDate != null && !reserveDate.trim().equals("")){
				param.setDATE(reserveDate);
			}
			param.setMSG(message);

			paramList.add(param);
			smsData.setTPARAM(paramList);

			locator = eaiLocaterService.getLocater("SHR0130");

			// JAX-WS Service & Port 생성
			SISHR0130EAISOService service = new SISHR0130EAISOService();
			SISHR0130EAISO port = service.getPort(SISHR0130EAISO.class);

			Map<String, Object> context = ((BindingProvider) port).getRequestContext();
			context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, locator.get("endpoint"));
			context.put(BindingProvider.USERNAME_PROPERTY, locator.get("username"));
			context.put(BindingProvider.PASSWORD_PROPERTY, locator.get("password"));

			DTSHR0130EAIResponse response = port.siSHR0130EAISO(smsData);
			result = response.getXSTAT();
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return result;

	}

    /*
    * @description : System.out.println ==> BATCH LOG기록으로 전환
	*/
    public void logSystemOut(BatchLogParamsDto reqDto, String jobStatus, String isPrint, String lineNo, String logMsg) {
    	if (!StringUtil.isEmpty(reqDto.getJobExecutionId()) && isPrint.equals("Y")) {
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