package cjfw.core.edms;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import cjfw.core.utility.ContextUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ZnApiUtils {	
	public static final String CTX_ROOT = ContextUtil.getProperty("cf.edms.url"); //Config.getString("edms.url");// "http://52.2.92.180:9700/edms/";
	
	@SuppressWarnings("deprecation")
	public static DefaultHttpClient getThreadSafeClient() {

		DefaultHttpClient client = new DefaultHttpClient();
		ClientConnectionManager mgr = client.getConnectionManager();
		HttpParams params = client.getParams();

		client = new DefaultHttpClient(new ThreadSafeClientConnManager(params,
				mgr.getSchemeRegistry()), params);

		return client;
	}
	
	public static String getDoctypeidByCode(String token, String wpid,
			String doctypecd) {
		String doctypeid = "";

		//if (httpclient == null)
		DefaultHttpClient httpclient = getThreadSafeClient();
		HttpPost httppost = new HttpPost(CTX_ROOT
				+ "znDocType/selectZnDocTypeSelectivePage.json");
		List<NameValuePair> nameValuePairs = null;
		HttpParams params = null;
		HttpResponse response = null;
		HttpEntity httpEntity = null;
		JSONObject resObj = null;
		JSONObject row = null;

		try {
			// 해당 객체에 담긴 key 값으로 원하는 데이터를 가져온다.
			nameValuePairs = new ArrayList<NameValuePair>();

			nameValuePairs
					.add(new BasicNameValuePair("doctypecode", doctypecd));
			nameValuePairs.add(new BasicNameValuePair("workplaceId", wpid));

			params = new BasicHttpParams();

			httppost.setParams(params);
			httppost.setHeader("nadi_auth_token", token);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			response = httpclient.execute(httppost);
			httpEntity = response.getEntity();

			resObj = (JSONObject) JSONValue.parseWithException(EntityUtils
					.toString(httpEntity));
			JSONObject doctypeattributelist = (JSONObject) resObj
					.get("znDocTypeList");
			JSONArray lists = (JSONArray) doctypeattributelist.get("list");

			// for(int t=0;t<lists.size();t++){
			if (lists.size() > 0) {
				row = (JSONObject) lists.get(0);
				doctypeid = String.valueOf(row.get("doctypeid"));
			}

		} catch (UnsupportedEncodingException e) {
			log.error("Exception", e);
		} catch (Exception e) {
			log.error("Exception", e);
		}

		return doctypeid;
	}
	
	public static String getDocTypeAttributeid(Map param) {
		String doctypeattributeid = "";
		String token = String.valueOf(param.get("token"));

		//if (httpclient == null)
		DefaultHttpClient httpclient = getThreadSafeClient();
		HttpPost httppost = new HttpPost(CTX_ROOT
				+ "znDocTypeAttribute/selectZnDocTypeAttributeInWorkplace.json");
		List<NameValuePair> nameValuePairs = null;
		HttpParams params = null;
		HttpResponse response = null;
		HttpEntity httpEntity = null;
		JSONObject resObj = null;
		JSONObject row = null;

		try {
			// 해당 객체에 담긴 key 값으로 원하는 데이터를 가져온다.
			nameValuePairs = new ArrayList<NameValuePair>();

			nameValuePairs.add(new BasicNameValuePair("strdoctypeid", String
					.valueOf(param.get("doctypeid"))));
			nameValuePairs.add(new BasicNameValuePair("strattributeid", String
					.valueOf(param.get("attrid"))));
			nameValuePairs.add(new BasicNameValuePair("workplaceId", String
					.valueOf(param.get("wpid"))));

			params = new BasicHttpParams();

			httppost.setParams(params);
			httppost.setHeader("nadi_auth_token", token);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			response = httpclient.execute(httppost);
			httpEntity = response.getEntity();

			resObj = (JSONObject) JSONValue.parseWithException(EntityUtils
					.toString(httpEntity));
			//System.out.println(resObj);
			JSONObject doctypeattributelist = (JSONObject) resObj
					.get("znDocTypeAttributeList");
			JSONArray lists = (JSONArray) doctypeattributelist.get("lists");

			for (int t = 0; t < lists.size(); t++) {
				row = (JSONObject) lists.get(t);
				doctypeattributeid = (String) row.get("doctypeattributeid");
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			log.error("Exception", e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception", e);
		}

		return doctypeattributeid;
	}
	
	public static String getDocid(String token, String requestno,
			String doctypeid, String workplaceid, String attid) {
		String docid = "";
		long startTime = System.nanoTime();
		//if (httpclient == null)
		DefaultHttpClient httpclient = getThreadSafeClient();
		HttpPost httppost = new HttpPost(CTX_ROOT + "imgsys/checkDocId.json");
		List<NameValuePair> nameValuePairs = null;
		HttpParams params = null;
		HttpResponse response = null;
		HttpEntity httpEntity = null;
		JSONObject resObj = null;

		try {
			// 해당 객체에 담긴 key 값으로 원하는 데이터를 가져온다.
			nameValuePairs = new ArrayList<NameValuePair>();

			nameValuePairs.add(new BasicNameValuePair("requestno", requestno));
			nameValuePairs.add(new BasicNameValuePair("doctypeid", doctypeid));
			nameValuePairs.add(new BasicNameValuePair("workplaceid",
					workplaceid));
			nameValuePairs.add(new BasicNameValuePair("attid", attid));

			params = new BasicHttpParams();

			httppost.setParams(params);
			httppost.setHeader("nadi_auth_token", token);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			response = httpclient.execute(httppost);
			httpEntity = response.getEntity();

			resObj = (JSONObject) JSONValue.parseWithException(EntityUtils
					.toString(httpEntity));

			docid = (String) resObj.get("docid");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			log.error("Exception", e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception", e);
		}

		long endTime = System.nanoTime();
		long lTime = endTime - startTime;
		//System.out.println("getDocid :: TIME : " + lTime / 1000000.0 + "(ms)");
		return docid;
	}
	
	public static Map uploadFile(String token, String filename) {
		//if (httpclient == null)
		DefaultHttpClient httpclient = getThreadSafeClient();
		HttpPost httppost = new HttpPost(CTX_ROOT + "imgsys/fileUpload.json");
		HttpEntity resEntry = null;
		Map resMap = new HashMap();
		File file = null;
		try {
			MultipartEntity reqEntity = new MultipartEntity();
			file = new File(filename);
			
			int index = 0;
			reqEntity.addPart("file0", new FileBody(file));

			httppost.setEntity(reqEntity);
			httppost.setHeader("nadi_auth_token", token);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(httppost, responseHandler);

			JSONObject uploadResult = (JSONObject) JSONValue
					.parseWithException(responseBody);
			JSONObject resultTxt = (JSONObject) uploadResult.get("result");
			String resKey = (String) resultTxt.get("key");
			String hashid = (String) uploadResult.get("hashid");

			resMap.put("orgname", URLEncoder.encode(file.getName(), "UTF-8"));
			resMap.put("filesize", file.length());
			resMap.put("hashid", hashid);
		} catch (UnsupportedEncodingException e) {
			log.error("Exception", e);
		} catch (ClientProtocolException e) {
			log.error("Exception", e);
		} catch (Exception e) {
			log.error("Exception", e);
		} finally {
			httpclient.getConnectionManager().shutdown();
			if(file != null){
				file.delete();
			}
		}

		return resMap;
	}
	
	//20240724 / 파일 일괄 업로드 후 필요 데이터리턴
	public static Map BatchFileUplad(String token, List filename, String Doctypeid) throws Exception {
		System.out.println("batchfileUpload 시작");
		DefaultHttpClient httpclient = getThreadSafeClient();
		HttpPost httppost = new HttpPost(CTX_ROOT+"imgsys/batchfileUpload.json");
		HttpEntity resEntry = null;
		Map resMap = new HashMap();
		
		try {
			MultipartEntity reqEntity = new  MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName("UTF-8"));
			reqEntity.addPart("doctype", new StringBody(Doctypeid));
			for(int i = 0 ; i < filename.size() ; i++) {
				Map<String, String> fileMap = new HashMap();
				fileMap = (Map<String,String>) filename.get(i);
				File file = new File(fileMap.get("filename"));
				reqEntity.addPart("file"+i, new FileBody(file));
			}
			
			httppost.setEntity(reqEntity);
			httppost.setHeader("nadi_auth_token", token);
			
			HttpResponse responseBody = httpclient.execute(httppost);
			resEntry = responseBody.getEntity();
			
			JSONObject uploadResult = (JSONObject)JSONValue.parseWithException(EntityUtils.toString(resEntry, "UTF-8"));
			JSONArray znDocExd = (JSONArray) uploadResult.get("znDocExd");
			JSONArray znDocImgs = (JSONArray)uploadResult.get("znDocImgs");
			JSONArray znFiles = (JSONArray)uploadResult.get("znFiles");
			JSONArray znDocFiles = (JSONArray)uploadResult.get("znDocFiles");
			JSONArray znNodeDocFiles = (JSONArray)uploadResult.get("znNodeDocFiles");
			JSONObject resObj = null;
			System.out.println("znDocExd : " + znDocExd.toJSONString());
			System.out.println("znDocImgs : " + znDocImgs.toJSONString());
			System.out.println("znFiles : " + znFiles.toJSONString());
			System.out.println("znDocFiles : " + znDocFiles.toJSONString());
			System.out.println("znNodeDocFiles : " + znNodeDocFiles.toJSONString());
			if(znDocExd.size()>0){
			    	resObj = (JSONObject)znDocExd.get(0);
			    	System.out.println("docid : " + resObj.get("docid"));
			}

			resMap.put("znDocExd", znDocExd);
			resMap.put("znDocImgs", znDocImgs);
			resMap.put("znFiles", znFiles);
			resMap.put("znDocFiles", znDocFiles);
			resMap.put("znNodeDocFiles", znNodeDocFiles);
			
		} catch(UnsupportedEncodingException e){
			e.printStackTrace();
			throw e;
		} catch(ClientProtocolException e){
			e.printStackTrace();
			throw e;
		} catch(Exception e){
			e.printStackTrace();
			throw e;
		} finally {
		}
		return resMap;
	}
	
	public static String loginProcess(String id, String pw) {
		String endResult = null;
		String sessId = "";
		DefaultHttpClient httpclient = null;
		try {
			httpclient = getThreadSafeClient();
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("username", id));
			nameValuePairs.add(new BasicNameValuePair("password", pw));

			HttpParams params = new BasicHttpParams();
			
			System.out.println("===========================CTX_ROOT 호출 테스트==================================");
			System.out.println("CTX_ROOT =" + CTX_ROOT);
			
			HttpPost post = new HttpPost(CTX_ROOT + "authUser");
			post.setParams(params);
			HttpResponse response = null;
			BasicResponseHandler myHandler = new BasicResponseHandler();

			try {
				post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			} catch (UnsupportedEncodingException e) {
				log.error("Exception", e); 
				e.printStackTrace();
			}

			try {
				response = httpclient.execute(post);

				int statusCode = response.getStatusLine().getStatusCode();
				String location = response.getLastHeader("Location").getValue();
				List cookielist = httpclient.getCookieStore().getCookies();
				Cookie cookie = null;
				try {
					if (cookielist.size() > 0) {
						cookie = (Cookie) cookielist.get(0);
						sessId = cookie.getValue();
					}
				} catch (Exception ee) {
					log.error("Exception", ee);
				}

				if (statusCode == 302) {
					HttpPost post2 = new HttpPost(location);
					HttpResponse response2 = httpclient.execute(post2);
					HttpEntity httpEntity = response2.getEntity();

					endResult = EntityUtils.toString(httpEntity);
				} else {
					HttpEntity httpEntity2 = response.getEntity();
					endResult = EntityUtils.toString(httpEntity2);
				}

			} catch (ClientProtocolException e) {
				log.error("Exception", e);
			} catch (IOException e) {
				log.error("Exception", e);
			}
			Thread.sleep(500);
		} catch (Exception ex) {
			log.error("Exception", ex);
		} finally {
			if(httpclient != null){
				if(httpclient.getConnectionManager() != null){
					httpclient.getConnectionManager().shutdown();
				}				
			}
		}

		return endResult;
	}
	
	public static String getLoginToken(String userid, String userpw) {
		String loginRt = "";
		String token = "";
		JSONObject object = null;

		try {
			loginRt = ZnApiUtils.loginProcess(userid, userpw);
			object = (JSONObject) JSONValue.parseWithException(loginRt);
			token = (String) object.get("nadi_auth_token");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception", e);
		}

		return token;
	}
}
