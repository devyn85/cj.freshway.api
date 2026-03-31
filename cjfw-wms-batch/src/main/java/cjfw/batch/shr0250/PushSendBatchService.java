package cjfw.batch.shr0250;

import cjfw.batch.common.EaiLocaterService;
import cjfw.batch.common.dto.BatchParamsUtil;
import cjfw.batch.shr0250.soap.DTSHR0250EAI;
import cjfw.batch.shr0250.soap.DTSHR0250EAIResponse;
import cjfw.batch.shr0250.soap.SISHR0250EAISO;
import cjfw.batch.shr0250.soap.SISHR0250EAISOService;
import cjfw.core.dataaccess.CommonDao;
import jakarta.xml.ws.BindingProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PushSendBatchService {

	private final CommonDao commonDao;

	private final EaiLocaterService eaiLocaterService;

	// 서비스/다른 곳에서 직접 호출할 수 있도록 로직 분리
	@Transactional
	public void callpushSendJobService(BatchParamsUtil batchParam) throws JobExecutionException {
		List<PushSendParamsDto> pushList = commonDao.selectList("pushSendBatchService.selectPush");

		for(PushSendParamsDto pushData : pushList){
			String[] result = sendPUSH(pushData.getRECEIVEPHONE()
					, pushData.getSENDMESSAGE()
					, pushData.getRECEIVEGROUP()
					, pushData.getRECEIVENAME() == null? "" : pushData.getRECEIVENAME()
			);
			PushSendParamsDto updateParam = new PushSendParamsDto();
			updateParam.setSERIALKEY(pushData.getSERIALKEY());
			updateParam.setSTATUS(result[0]);
			updateParam.setIF_MEMO(result[1]);
			updateParam.setIF_FLAG(Objects.toString(result[2], "")); //TODO-LIST(1) 조치되면 SETSTRING 으로 변경.
			commonDao.update("pushSendBatchService.updatePush", updateParam);
		}
	}

	public String[] sendPUSH(String toCustkey,String message,String menuID,String seq) {
		String[] result = new String[3];
		Map<String, String> locator = null;
		try {
			DateTimeFormatter dayFormatter  = DateTimeFormatter.ofPattern("yyyyMMdd");
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");

			LocalDateTime now = LocalDateTime.now();
			String dayString  = now.format(dayFormatter);
			String timeString = now.format(timeFormatter);

			DTSHR0250EAI pushData = new DTSHR0250EAI();
			pushData.setXSYS("SCM");
			pushData.setXDATS(dayString);
			pushData.setXTIMS(timeString);

			DTSHR0250EAI.TPARAM tparam = new DTSHR0250EAI.TPARAM();
			tparam.setCUID(toCustkey);
			tparam.setMSG(message);
			tparam.setMID(menuID);
			tparam.setSEQ(seq);
			tparam.setSENDDATE("");
			pushData.setTPARAM(tparam);
			DTSHR0250EAIResponse response = null;

			locator = eaiLocaterService.getLocater("SHR0250");

			// JAX-WS Service & Port 생성
			SISHR0250EAISOService service = new SISHR0250EAISOService();
			SISHR0250EAISO port = service.getPort(SISHR0250EAISO.class);

			Map<String, Object> context = ((BindingProvider) port).getRequestContext();
			context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, locator.get("endpoint"));
			context.put(BindingProvider.USERNAME_PROPERTY, locator.get("username"));
			context.put(BindingProvider.PASSWORD_PROPERTY, locator.get("password"));
			response = port.siSHR0250EAISO(pushData);
			result[2] = response.getXSTAT();  // TODO-LIST(1) response = NULL 일 경우 예외처리 혹은 NULL 안되게
			if("S".equals(response.getXSTAT())) {
				result[0] = "90";
			} else {
				result[0] = "E";
				result[1] = response.getXMSGS();
			}
		} catch (Exception e) {
			log.info(e.toString());
			result[0] = "E";
			result[1] = "PUSH전송중 에러발생 : "+e ;
		}
		return result;
	}
}