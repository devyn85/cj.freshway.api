package cjfw.core.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONException;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

@Component
public class ApiClient {
	private static final Log LOG = LogFactory.getLog(ApiClient.class);

	/**
	 * API 호출 메서드
	 * 
	 * @param url
	 * @param interfaceId
	 * @param interfaceAuthKey
	 * @param params
	 * @param files
	 * @return
	 * @throws JSONException 
	 */
	public JSONObject callApi(String url, String interfaceId, String interfaceAuthKey, Map params, List<HashMap<String,Object>> files) throws IOException, ParseException, JSONException {
		// 1) Request Body 생성
		JSONObject requestJson = buildRequestBody(params, files);
		
		System.out.println("----------------------------API Request Start ----------------------------");
		System.out.println("URL : " + url);
		System.out.println("Body : " + requestJson);
		
		// 2) HttpURLConnection 설정
		URL apiUrl = new URL((String) url);
		HttpURLConnection conn = null;

		conn = (HttpURLConnection) apiUrl.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		if(interfaceId != null && interfaceId.length() > 0 && interfaceAuthKey != null && interfaceAuthKey.length() > 0){
			conn.setRequestProperty("Interface_id", interfaceId);
			conn.setRequestProperty("Interface_auth_key", interfaceAuthKey);
		}else if(interfaceAuthKey != null && interfaceAuthKey.length() > 0){
			conn.setRequestProperty("Authorization", interfaceAuthKey);
		}
		conn.setDoOutput(true);
		
		// 3) Body 전송
		String jsonStr = (String) requestJson.toString();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		bw.write(jsonStr);
		bw.flush();
		bw.close();

		// 4) 결과값 받기
		int responseCode = conn.getResponseCode();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line = "";
		while((line = br.readLine()) != null){
			sb.append(line);
		}

		JSONObject responseJson = JSONObject.fromObject(sb.toString());
		
//		if("F".equals(responseJson.optString("err_cd"))){
//			throw new RuntimeException("CRM API 연동 오류: " + responseJson.optString("err_msg_ctt"));
//		}
		
		System.out.println("----------------------------API Request End ----------------------------");
		
		return responseJson;
	}
	
	private static JSONObject buildRequestBody(Map params, List<HashMap<String,Object>> files){
		JSONObject requestJson = JSONObject.fromObject(params);
		
		if(files != null && !files.isEmpty()){
			JSONArray fileArray = JSONArray.fromObject(files);
			requestJson.put("AttachFiles", fileArray);
		}
		
		JSONObject wrappedJson = new JSONObject(); 
		wrappedJson.put("data", requestJson);
		
		return wrappedJson;
	}
}
