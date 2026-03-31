package cjfw.batch.common;

import cjfw.batch.common.dto.EaiProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class EaiLocaterService {

	private final EaiProperties props;

	public Map<String, String> getLocater(String batchName) {
		Map<String, String> resultMap = new HashMap<>();
		String eaiMode = null;
		if("SCM0320".equals(batchName) || "SCM0060".equals(batchName) || "SCM0160".equals(batchName) || "SCM0330".equals(batchName) || "SCM0110".equals(batchName)) {
			eaiMode = props.getMode();
		} else if("SHR0130".equals(batchName) || "SHR0160".equals(batchName) || "SHR0250".equals(batchName)	) {
			eaiMode = props.getSmsMode();
		} else {
			eaiMode = "";
		}

		String iface = null;
		if("SCM0320".equals(batchName)) {
			iface = "SI_SCM0320_SCM_SO";
		} else if("SCM0060".equals(batchName)) {
			iface = "SI_SCM0060_SCM_SO";
		} else if("SCM0160".equals(batchName)) {
			iface = "SI_SCM0160_SCM_SO";
		} else if("SCM0110".equals(batchName)) {
			iface = "SI_SCM0110_SCM_SO";
		} else if("SCM0330".equals(batchName)) {
			iface = "SI_SCM0330_SCM_SO";
		} else if("SHR0130".equals(batchName)) {
			iface = "SI_SHR0130_EAI_SO";
		} else if("SHR0160".equals(batchName)) {
			iface = "SI_SHR0160_EAI_AO";
		} else if("SHR0250".equals(batchName)) {
			iface = "SI_SHR0250_EAI_SO";
		} else {
			iface = "";
		}

		String ifaceNamespace = null;
		if("SCM0320".equals(batchName) || "SCM0330".equals(batchName) || "SCM0160".equals(batchName) || "SCM0110".equals(batchName)) {
			ifaceNamespace = props.getScmIfaceNamespace() + "/MM";
		} else if("SCM0060".equals(batchName)) {
			ifaceNamespace = props.getScmIfaceNamespace() + "/SD";
		} else if("SHR0130".equals(batchName) || "SHR0160".equals(batchName) || "SHR0250".equals(batchName)) {
			ifaceNamespace = props.getShrIfaceNamespace() + "/COMM";
		} else {
			ifaceNamespace = "";
		}

		String eaiUrl      = props.getUrl();       // ex) fwpiqas.cjfreshway.co.kr
		String eaiUser     = props.getUserId();    // ex) SCM_IFQUSER
		String eaiPass     = props.getPassword();  // ex) IFQUSER2@15#@
		boolean useHttps   = props.getUseHttps();  // 필요시 http/https 토글
        useHttps = false;
		String httpPort = useHttps ? "https" : "http";
		int httpPortNum = useHttps ? 50001 : 50000;

		String endpoint = httpPort + "://" + eaiUrl + ":" + httpPortNum
				+ "/XISOAPAdapter/MessageServlet"
				+ "?senderParty="
				+ "&senderService=" + eaiMode
				+ "&receiverParty="
				+ "&receiverService="
				+ "&interface=" + iface
				+ "&interfaceNamespace=" + ifaceNamespace;

		resultMap.put("endpoint", endpoint);
		resultMap.put("username", eaiUser);
		resultMap.put("password", eaiPass);

		return resultMap;
	}
}