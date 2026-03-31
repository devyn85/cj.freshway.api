package cjfw.batch.scm0320;

import cjfw.batch.common.EaiLocaterService;
import cjfw.batch.common.dto.BatchParamsUtil;
import cjfw.batch.common.dto.EaiProperties;
import cjfw.batch.scm0320.saop.DTSCM0320SCM;
import cjfw.batch.scm0320.saop.DTSCM0320SCMResponse;
import cjfw.batch.scm0320.saop.SISCM0320SCMSO;
import cjfw.batch.scm0320.saop.SISCM0320SCMSOService;
import cjfw.core.dataaccess.CommonDao;
import jakarta.xml.ws.BindingProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Slf4j
public class ProcessScm0320Batch extends Thread {

    private final CommonDao commonDao;
	private List<DTSCM0320SCM.ITEM> dataList = null;
	private Map<String, String> locater = null;

	private EaiProperties props;

	public ProcessScm0320Batch(CommonDao commonDao, List<DTSCM0320SCM.ITEM> mmDataList, Map<String, String> locater) {
		this.commonDao = commonDao;
        this.dataList = mmDataList;
		this.locater = locater;
	}

	@Override
	public void run() {
		BatchParamsUtil batchParam = new BatchParamsUtil();
		Scm0320ParamsReqDto defaultParams = new Scm0320ParamsReqDto();
		String errorMessage = null;

		SISCM0320SCMSOService service = new SISCM0320SCMSOService();
		SISCM0320SCMSO port = service.getPort(SISCM0320SCMSO.class);

		DateTimeFormatter dayFormatter  = DateTimeFormatter.ofPattern("yyyyMMdd");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");

		LocalDateTime now = LocalDateTime.now();
		String dayString  = now.format(dayFormatter);
		String timeString = now.format(timeFormatter);

		DTSCM0320SCM result = new DTSCM0320SCM();
		result.setXSYS("SCM");
		result.setXDATS(dayString);
		result.setXTIMS(timeString);
		result.setXROWS(String.valueOf(dataList.size()));
		result.setItem(dataList);

		// 실적 전송
		try {
			DTSCM0320SCMResponse response = null;

			// 1) batch-config.xml eai-cm0160 설정값 가져오기.
			Map<String, Object> context = ((BindingProvider) port).getRequestContext();
			context.put(BindingProvider.USERNAME_PROPERTY, locater.get("username"));
			context.put(BindingProvider.PASSWORD_PROPERTY, locater.get("password"));
			context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, locater.get("endpoint"));

			response = port.siSCM0320SCMSO(result);
			if(response != null) {
				defaultParams.setSEARCH_IF_FLAG("P");

				for(DTSCM0320SCMResponse.ITEMRET item : response.getITEMRET()) {
					defaultParams.setUPDATE_IF_FLAG(item.getIFFLAG());
					defaultParams.setUPDATE_IF_MEMO(item.getIFMEMO());
					defaultParams.setIFTIMESTAMP(item.getIFTIMESTAMP());
					defaultParams.setDOCNO(item.getDOCNO());
					defaultParams.setDOCLINE(item.getDOCLINE());

					commonDao.update("scm0320Service.updateScm0320", defaultParams);
				}
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			errorMessage = e.getMessage();
			int len =  errorMessage.length();
			if(len > 120) {
				len = 120;
			}

			//실제값 FLAG처리
			for(DTSCM0320SCM.ITEM data : dataList) {
				defaultParams.setSEARCH_IF_FLAG("P");
				defaultParams.setUPDATE_IF_FLAG("E");
				defaultParams.setUPDATE_IF_MEMO(errorMessage.substring(1,len));
				defaultParams.setIFTIMESTAMP(data.getIFTIMESTAMP());
				defaultParams.setDOCNO(data.getDOCNO());
				defaultParams.setDOCLINE(data.getDOCLINE());

				commonDao.update("scm0320Service.updateScm0320", defaultParams);
			}
		}
	}
}