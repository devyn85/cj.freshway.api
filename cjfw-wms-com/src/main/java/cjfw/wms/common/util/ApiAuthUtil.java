package cjfw.wms.common.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ApiAuthUtil {
	private static final Log LOG = LogFactory.getLog(ApiAuthUtil.class);
	
	public static String createBasicAuthHeader(String username, String password) throws UnsupportedEncodingException{
		String cred = username + ":" + password;
        String base64 = Base64.encodeBase64String(cred.getBytes("UTF-8"));
		return "Basic " + base64;
	}

}
