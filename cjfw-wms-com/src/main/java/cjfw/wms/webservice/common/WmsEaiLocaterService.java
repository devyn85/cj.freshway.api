package cjfw.wms.webservice.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import cjfw.core.utility.ContextUtil;

@Slf4j
@Service
@RequiredArgsConstructor
public class WmsEaiLocaterService {

	public Map<String, String> getLocater(String serviceName) {
        String eaiUrl = ContextUtil.getProperty("cf.eai.url");
        String eaiMode = ContextUtil.getProperty("cf.eai.mode"); 
        String eaiUser = ContextUtil.getProperty("cf.eai.userId");
        String eaiPass = ContextUtil.getProperty("cf.eai.password");
        String eaiScmIfaceNamespace = ContextUtil.getProperty("cf.eai.scmIfaceNamespace");
        String eaiHttps = ContextUtil.getProperty("cf.eai.httpPort");  
        Map<String, String> resultMap = new HashMap<>();
        
        String iface = null;
        if ("SCM0540".equals(serviceName)) {
            iface = "SI_SCM0540_SCM_SO";
        } else if ("SCM0550".equals(serviceName)) {
            iface = "SI_SCM0550_SCM_SO";    
        } else if ("SCM0560".equals(serviceName)) {
            iface = "SI_SCM0560_SCM_SO";
        } else if ("SCM0570".equals(serviceName)) {
            iface = "SI_SCM0570_SCM_SO";    
        } else {
        	iface = "";
        }
        
        String ifaceNamespace = null;
        if ("SCM0540".equals(serviceName)) {
            ifaceNamespace = eaiScmIfaceNamespace + "/TCS";
        } else if ("SCM0550".equals(serviceName)) {
            ifaceNamespace = eaiScmIfaceNamespace + "/TCS";
        } else if ("SCM0560".equals(serviceName)) {
            ifaceNamespace = eaiScmIfaceNamespace + "/TCS";
        } else if ("SCM0570".equals(serviceName)) {
            ifaceNamespace = eaiScmIfaceNamespace + "/TCS";    
        } else {
        	ifaceNamespace = "";
        }
        
        String httpPort = eaiHttps;
        int httpPortNum = "https".equals(httpPort) ? 50001 : 50000;
        
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