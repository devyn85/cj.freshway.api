package cjfw.core.edms;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
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
public class ZnDocumentApi {
	
	public static final String WORKPLACE_ID = ContextUtil.getProperty("cf.edms.workPlaceId");
	public static final String ATTR_ID = ContextUtil.getProperty("cf.edms.attrId");
	public static final String CTX_ROOT = ContextUtil.getProperty("cf.edms.url"); //Config.getString("edms.url");//"http://52.2.92.180:9700/edms/";
	
	public static Map registerTCS(String strTempFilePath, String docId, String userNo, String docTypeId){
		Map rMap = new HashMap();
		String resDocId = "";
		Map param = new HashMap();
		List filelist = new ArrayList(); 
		Map fileMap = new HashMap();
		String loginToken = ZnApiUtils.getLoginToken(userNo, userNo);
//		String docTypeId  = ZnApiUtils.getDoctypeidByCode(loginToken, WORKPLACE_ID, doctypecd);
		//String docTypeId = doctypeid;
		
		param.put("token", loginToken);
		param.put("doctypeid", docTypeId);
		param.put("docid", docId);
		param.put("attrvalue", docId);
		param.put("attrid", ATTR_ID);     
		param.put("wpid", WORKPLACE_ID);
		param.put("state", "2"); 
		List nodes = ZnDocumentApi.getNodeList(loginToken, docTypeId);
		
		
		fileMap = new HashMap();
		fileMap.put("filename", strTempFilePath);
		fileMap.put("nodeid", ((Map)nodes.get(0)).get("nodeid")); 
		filelist.add(fileMap);
		
		param.put("uploadList", filelist);
		rMap = registerAction(param);
		resDocId = (String)rMap.get("docid");
		
		//view(loginToken);
		return rMap;
	}
	
	public static Map viewTCS(String attrvalue, String filePath, String userNo, String docTypeId){
		String loginToken = ZnApiUtils.getLoginToken(userNo, userNo); 		
		
		List nodes = getNodeList(loginToken, docTypeId);
		String nodeid = (String)((Map)nodes.get(0)).get("nodeid");   
		
		List filelist = getFileList( loginToken, attrvalue, ATTR_ID, docTypeId, WORKPLACE_ID, nodeid );
		Map rMap = new HashMap();
		String fileid = "";
		String orgname = "";
		String docfileid = "";
		String docid = "";
		
		//System.out.println( "cnt="+filelist.size());
		HttpResponse response = null;
		if(filelist.size() > 0){
			docfileid = (String)((Map)filelist.get(0)).get("docfileid");
			rMap = (Map)filelist.get(0);
			orgname = (String)rMap.get("orgname");
			response = getFile(loginToken, docfileid, orgname, WORKPLACE_ID, filePath);
			rMap.put("response", response);
		}
		return rMap;
	}
	
	//20240724 / 파일 일괄 업로드 등록
	public static Map BatchFileRegister(String token, Map fileInfo) throws Exception{ //throws Exception 추가 2015.10.08 11:45 장홍진
		DefaultHttpClient httpclient = ZnApiUtils.getThreadSafeClient();
		HttpPost httppost = new HttpPost(CTX_ROOT+"znCmnRegister/registerDoc.json");
		Map info = new HashMap();
		Map retMap = new HashMap();
		
		JSONObject resObj = null;
		JSONObject resRowObj = null;
		JSONArray docimgObj = null;
		JSONArray docfileObj = null;
		JSONArray znfileObj = null;
		Map docfileMap = null;
		List filelist = new ArrayList();
		String resDocid = "";
		try{
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            
            nameValuePairs.add(new BasicNameValuePair("workplaceId", "101"));
    		
            List zdeList = (ArrayList)fileInfo.get("znDocExd");
            
            //문서정보 저장
            for(int i = 0 ; i < zdeList.size() ;i++) {
            	Map obj = (Map)zdeList.get(i);
            	nameValuePairs.add(new BasicNameValuePair("znDocExd["+i+"].docid",  String.valueOf(obj.get("docid"))));
                nameValuePairs.add(new BasicNameValuePair("znDocExd["+i+"].doctypeattributeid",  String.valueOf(obj.get("doctypeattributeid").toString())));
                nameValuePairs.add(new BasicNameValuePair("znDocExd["+i+"].exdvalue", String.valueOf(obj.get("exdvalue"))));
            }
            
            Map filenameTempMap = new HashMap();
          
            List zdiList = (ArrayList)fileInfo.get("znDocImgs");
            
            //이미지와 문서 정보 저장
            for(int i=0;i<zdiList.size();i++){
            	Map znDocImgs = (Map)zdiList.get(i);
            	//원본이미지
            	nameValuePairs.add(new BasicNameValuePair("znDocImgs["+i+"].docid", String.valueOf(znDocImgs.get("docid"))));
                nameValuePairs.add(new BasicNameValuePair("znDocImgs["+i+"].doctypeid", String.valueOf(znDocImgs.get("doctypeid"))));
                nameValuePairs.add(new BasicNameValuePair("znDocImgs["+i+"].state", String.valueOf(znDocImgs.get("state"))));
            }
            
            //파일저장
            List zfList = (ArrayList)fileInfo.get("znFiles");
            for(int i = 0 ; i < zfList.size() ; i++) {
            	Map znFiles = (Map)zfList.get(i);
                nameValuePairs.add(new BasicNameValuePair("znFiles["+i+"].fileid", String.valueOf(znFiles.get("fileid"))));
                nameValuePairs.add(new BasicNameValuePair("znFiles["+i+"].filesize", String.valueOf(znFiles.get("filesize"))));
            }

            //문서와 파일 매핑저장
            List zdfList = (ArrayList)fileInfo.get("znDocFiles");
            for(int i = 0 ; i < zdfList.size() ; i++) {
            	Map znDocFiles = (Map)zdfList.get(i); 
            	nameValuePairs.add(new BasicNameValuePair("znDocFiles["+i+"].docid", String.valueOf(znDocFiles.get("docid"))));
        		nameValuePairs.add(new BasicNameValuePair("znDocFiles["+i+"].fileid",String.valueOf(znDocFiles.get("fileid"))));
        		nameValuePairs.add(new BasicNameValuePair("znDocFiles["+i+"].orgname", String.valueOf(znDocFiles.get("orgname"))));
            }
    	
            //문서파일과 카테코리 매핑 저장
            List zndfList = (ArrayList)fileInfo.get("znNodeDocFiles");
            for(int i = 0 ; i < zndfList.size() ; i++) {
            	Map znNodeDocFiles = (Map)zndfList.get(i); 
            	nameValuePairs.add(new BasicNameValuePair("znNodeDocFiles["+i+"].docfileid", String.valueOf(znNodeDocFiles.get("docfileid"))));
        		nameValuePairs.add(new BasicNameValuePair("znNodeDocFiles["+i+"].nodeid", String.valueOf(znNodeDocFiles.get("nodeid"))));
            }
    		
            
            //1:등록요청 2:승인완료 3:반려 4:종료
            //특수한 경우가 아니면 승인완료 상태로 사용
    		nameValuePairs.add(new BasicNameValuePair("state", "2")); 
    		
                
            HttpParams params = new BasicHttpParams();
            
            httppost.setParams(params);
			httppost.setHeader("nadi_auth_token", token);
            HttpResponse response = null;
            BasicResponseHandler myHandler = new BasicResponseHandler();
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            httppost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            response = httpclient.execute(httppost);
            HttpEntity httpEntity = response.getEntity();
			resObj = (JSONObject)JSONValue.parseWithException(EntityUtils.toString(httpEntity));
			System.out.println( resObj );
            resObj = (JSONObject)resObj.get("znUnitedVO");
            if(resObj!=null){
	            docfileObj = (JSONArray)resObj.get("znDocFiles");
	            znfileObj = (JSONArray)resObj.get("znFiles");
	            
	            System.out.println("docfileObj.size() : " + docfileObj.size());
	            
	            for(int idx=0;idx<docfileObj.size();idx++){
	            	resRowObj = (JSONObject)docfileObj.get(idx);
	            	System.out.println("idx : " + idx + " / " + resRowObj.get("docfileid") + " / orgname : " + resRowObj.get("orgname")+ " / filesize : " + ((JSONObject)znfileObj.get(idx)).get("filesize"));
	            	docfileMap = new HashMap();
	            	docfileMap.put("docfileid", resRowObj.get("docfileid"));
	            	docfileMap.put("orgname", resRowObj.get("orgname"));
	            	docfileMap.put("filesize", ((JSONObject)znfileObj.get(idx)).get("filesize"));
	            	filelist.add(docfileMap);
	            }
            }
            
		} catch(Exception e){
			//log.info(e.toString(), e);
			e.printStackTrace();
			throw e;
		}finally {
			//httpclient.getConnectionManager().shutdown();
			
		}
		
		retMap.put("docid", resDocid);
		retMap.put("filelist", filelist);
		return retMap;
	}
	
	public static Map registerAction(Map paramMap){
		Map retMap = new HashMap();
		//if(httpclient==null) 
		DefaultHttpClient httpclient = ZnApiUtils.getThreadSafeClient();
		HttpPost httppost = new HttpPost(CTX_ROOT+"imgsys/fileUpload.json");
		HttpEntity resEntry = null;
		
		String userid = (String)paramMap.get("userid");
		String userpw = (String)paramMap.get("userpw");
		String loginRt = "";
		String token = (String)paramMap.get("token");
		JSONObject object = null;
		Map upfileinfo = null;
		List fileList = new ArrayList();
		List uploadList = (List)paramMap.get("uploadList");
		Map fileMap = new HashMap();
		
		try {
			
			//�뚯씪 �낅줈��
			for(int idx=0;idx<uploadList.size();idx++){
				fileMap = (Map)uploadList.get(idx);
				upfileinfo = ZnApiUtils.uploadFile(token, (String)fileMap.get("filename") );
				upfileinfo.put("attrid", "");  
				upfileinfo.put("nodeid", fileMap.get("nodeid"));
				fileList.add(upfileinfo);
			}
			
			paramMap.put("token", token);
//			paramMap.put("doctypeid", "100");
//			paramMap.put("attrid", "101");
//			paramMap.put("wpid", "101");
			retMap = insertDocInfo(token, fileList, paramMap);
			
		} catch(Exception e){
			e.printStackTrace();
			log.error("Exception", e);
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		
		return retMap;
	}
	
	public static Map insertDocInfo(String token, List fileList, Map pMap){
		//if(httpclient==null) 
		DefaultHttpClient httpclient = ZnApiUtils.getThreadSafeClient();
		HttpPost httppost = new HttpPost(CTX_ROOT+"znCmnRegister/registerDoc.json");
		Map info = new HashMap();
		Map retMap = new HashMap();
		
		JSONObject resObj = null;
		JSONObject resRowObj = null;
		JSONObject znFileObj = null;
		JSONArray docimgObj = null;
		JSONArray docfileObj = null;
		JSONArray znFiles = null;
		Map docfileMap = null;
		List filelist = new ArrayList();
		String resDocid = "";
		
		String docid = (String)pMap.get("docid");
		//String doctypecd = String.valueOf(pMap.get("doctypeid")); //�꾩떆濡��묐젰�ъ뿉~~
		//String doctypeid = ZnApiUtils.getDoctypeidByCode(token, "101", doctypecd);
		String doctypeid = String.valueOf(pMap.get("doctypeid"));
		String state = String.valueOf(pMap.get("state"));		
		String doctypeattributeid = "";
		
		Map param  = new HashMap();
		param.put("token", token);
		param.put("doctypeid", doctypeid);
		param.put("attrid", pMap.get("attrid"));
		param.put("wpid", pMap.get("wpid"));
		//System.out.println( param.toString() );
		//System.out.println( pMap.toString() );
		
		doctypeattributeid = ZnApiUtils.getDocTypeAttributeid(param);
		
		try{
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            
            nameValuePairs.add(new BasicNameValuePair("workplaceId", "101"));
    		
            nameValuePairs.add(new BasicNameValuePair("znDocExd[0].docid", docid));
            nameValuePairs.add(new BasicNameValuePair("znDocExd[0].doctypeattributeid", doctypeattributeid));
            nameValuePairs.add(new BasicNameValuePair("znDocExd[0].exdvalue", String.valueOf(pMap.get("attrvalue"))));
            
            List nodes = getNodeList(token, doctypeid);
            String selectednodeid = "";
            Map nodeinfo = new HashMap();
            
            //�대떦 移댄뀒怨좊━ �좏깮
            selectednodeid = (String)pMap.get("nodeid");
            
            //移댄뀒怨좊━�좏깮 ��
          
            for(int i=0;i<fileList.size();i++){
            	info = (Map)fileList.get(i);
            	nameValuePairs.add(new BasicNameValuePair("znDocImgs["+i+"].docid", docid));
                nameValuePairs.add(new BasicNameValuePair("znDocImgs["+i+"].doctypeid", doctypeid));
                nameValuePairs.add(new BasicNameValuePair("znFiles["+i+"].fileid", (String)info.get("hashid")));
                nameValuePairs.add(new BasicNameValuePair("znFiles["+i+"].filesize", String.valueOf(info.get("filesize"))));
        		nameValuePairs.add(new BasicNameValuePair("znDocFiles["+i+"].docid", docid));
        		nameValuePairs.add(new BasicNameValuePair("znDocFiles["+i+"].fileid", (String)info.get("hashid")));
        		nameValuePairs.add(new BasicNameValuePair("znDocFiles["+i+"].orgname", (String)info.get("orgname")));
        		nameValuePairs.add(new BasicNameValuePair("znNodeDocFiles["+i+"].docfileid", docid+(String)info.get("hashid")+(String)info.get("orgname")));
        		nameValuePairs.add(new BasicNameValuePair("znNodeDocFiles["+i+"].nodeid", (String)info.get("nodeid") ));
            }
            
            //1:�깅줉�붿껌 2:�뱀씤�꾨즺 3:諛섎젮 4:醫낅즺
            //�뱀닔��寃쎌슦媛��꾨땲硫��뱀씤�꾨즺 �곹깭濡��ъ슜
    		nameValuePairs.add(new BasicNameValuePair("state", state)); 
                
            HttpParams params = new BasicHttpParams();
            
            httppost.setParams(params);
			httppost.setHeader("nadi_auth_token", token);
            HttpResponse response = null;
            BasicResponseHandler myHandler = new BasicResponseHandler();
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            
            response = httpclient.execute(httppost);
            
            //System.out.println(response);
			
			/*int statusCode = response.getStatusLine().getStatusCode();

			if(statusCode==302){
				HttpPost post2 = new HttpPost(location);
				HttpResponse response2 = httpclient.execute(post2);
				HttpEntity httpEntity = response2.getEntity();
				
				endResult = EntityUtils.toString(httpEntity);
			} else {
				HttpEntity httpEntity2 = response.getEntity();					
				endResult = EntityUtils.toString(httpEntity2);
			}*/
            
            HttpEntity httpEntity = response.getEntity();
			resObj = (JSONObject)JSONValue.parseWithException(EntityUtils.toString(httpEntity));
			//System.out.println( resObj );
            resObj = (JSONObject)resObj.get("znUnitedVO");
            if(resObj!=null){
	            docimgObj = (JSONArray)resObj.get("znDocImgs");
	            docfileObj = (JSONArray)resObj.get("znDocFiles");
	            znFiles = (JSONArray)resObj.get("znFiles");
	            //resList = (JSONArray)resObj.get("list");
	            resRowObj = (JSONObject)docimgObj.get(0);
	            resDocid = (String)resRowObj.get("docid");   //System.out.println( "resdocid="+resDocid);
	            
	            for(int idx=0;idx<docfileObj.size();idx++){
	            	resRowObj = (JSONObject)docfileObj.get(idx);
	            	if (znFiles.size() > 0) {
	            		znFileObj = (JSONObject)znFiles.get(idx);
	            	}
	            	//System.out.println( resRowObj.toString() );
	            	docfileMap = new HashMap();
	            	docfileMap.put("docfileid", resRowObj.get("docfileid"));
	            	docfileMap.put("orgname", resRowObj.get("orgname"));
	            		docfileMap.put("filesize", znFileObj.get("filesize"));
	            		if (znFileObj != null) {
	            	} else {
	            		docfileMap.put("filesize", resRowObj.get("filesize"));
	            	}
	            	
	            	//System.out.println( idx+"//docfileid="+ docfileMap.get("docfileid"));
	            	//System.out.println( idx+"//orgname="+ docfileMap.get("orgname"));
	            	//System.out.println( idx+"//filesize="+ docfileMap.get("filesize"));
	            	filelist.add(docfileMap);
	            }
            }
            
		} catch(Exception e){
			e.printStackTrace();
			log.error("Exception", e);
		}
		
		retMap.put("docid", resDocid);
		retMap.put("filelist", filelist);
		return retMap;
	}
	
	public static HttpResponse getFile(String token, String docfileid, String orgname, String workplaceid, String filePath){
		//if(httpclient==null) 
		DefaultHttpClient httpclient = ZnApiUtils.getThreadSafeClient();
		
		InputStream is = null;
		
		HttpPost httppost = new HttpPost(CTX_ROOT+"znImgsysEncrypt/downloadFileEnc.file");
		HttpEntity resEntry = null;
		
		JSONObject object = null;
		Map upfileinfo = null;
		List fileList = new ArrayList();
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("workplaceId", workplaceid)); 
        nameValuePairs.add(new BasicNameValuePair("docfileid", docfileid));
        nameValuePairs.add(new BasicNameValuePair("orgname", orgname));
                    
        HttpParams params = new BasicHttpParams();
		httppost.setParams(params);
		
		HttpResponse response = null;
		try (FileOutputStream fos = new FileOutputStream(filePath+"/"+orgname);) {
			
			httppost.setHeader("nadi_auth_token", token);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			
			
			response = httpclient.execute(httppost);
			
			is = response.getEntity().getContent();
			File tempDir = new File(filePath);
			if(tempDir.exists()&&tempDir.isDirectory()){
				File[] fa = tempDir.listFiles();
				for(File f : fa){
//					f.delete();
				}
			}else{
				tempDir.mkdirs();
			}
			int c;
	        while ((c = is.read()) != -1) {
	            fos.write(c);
	        }
//	        IOUtils.closeQuietly(is);
//	        IOUtils.closeQuietly(fos);
		} catch(Exception e){
			e.printStackTrace();
			log.error("Exception", e);
		} finally {
			httpclient.getConnectionManager().shutdown();
			IOUtils.closeQuietly(is);
//	        IOUtils.closeQuietly(fos);
		}
		
		return response;
	}
	
	public static List getFileList(String token, String requestno, String attid, String doctypeid, String workplaceid, String nodeid){

		//if(httpclient==null) 
		DefaultHttpClient httpclient = ZnApiUtils.getThreadSafeClient();
		HttpPost httppost = new HttpPost(CTX_ROOT+"znCmnView/viewDocFile.json");
		List<NameValuePair> nameValuePairs = null;
		List rList = new ArrayList();
		HttpParams params = null;
		HttpResponse response = null;
		HttpEntity httpEntity = null;
		JSONObject resObj = null;
		JSONArray znDocFile = null;
        JSONObject subObj = null;
        JSONObject nodeObj = null;
        Map rMap = null;
        String parentnodeid = "";
        
        String docid = "";
        String loginRt = "";

        JSONObject object = new JSONObject();
		
		try{
			long startTime = System.nanoTime();
			docid = ZnApiUtils.getDocid(token, requestno, doctypeid, workplaceid, attid);
			//System.out.println("docid="+docid+"//\n"+token+"//\n"+requestno+"//\n"+doctypeid+"//\n"+workplaceid+"//\n"+attid);
			
			if(docid!=null && !"".equals(docid)){

				nameValuePairs = new ArrayList<NameValuePair>();
	            nameValuePairs.add(new BasicNameValuePair("workplaceId", workplaceid));
	            nameValuePairs.add(new BasicNameValuePair("znDocImg.docid", docid));
	            nameValuePairs.add(new BasicNameValuePair("objHandleType", "Y"));
	            if(!"".equals(nodeid)){
	            	nameValuePairs.add(new BasicNameValuePair("znNodeDocFile.nodeid", nodeid));
	            }
	                
	            params = new BasicHttpParams();
	            
	            httppost.setParams(params);
				httppost.setHeader("nadi_auth_token", token);
	            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	            
	            response = httpclient.execute(httppost);
	            httpEntity = response.getEntity();					
	            
	            resObj = (JSONObject)JSONValue.parseWithException(EntityUtils.toString(httpEntity));
				
				znDocFile = (JSONArray)resObj.get("znDocFileList");
	            
	            for(int idx=0; idx<znDocFile.size(); idx++){
	            	subObj = (JSONObject)znDocFile.get(idx);
	            	nodeObj = (JSONObject)subObj.get("znNode");
	            	//System.out.println( subObj );
	            	
//	            	System.out.println( "fileid="+subObj.get("fileid") );
//	            	System.out.println( "name="+subObj.get("orgname") );
//	            	System.out.println( "docfileid="+subObj.get("docfileid") );
//	            	System.out.println( "docid="+subObj.get("docid") );
//	            	System.out.println( "isdeleted="+subObj.get("isdeleted") );
//	            	
//	            	System.out.println( "nodeid="+nodeObj.get("nodeid") );
//	            	System.out.println( "nodenm="+nodeObj.get("name") );	            	
        		            	
	            	rMap = new HashMap();
	            	rMap.put("fileid", subObj.get("fileid"));
	            	rMap.put("orgname", subObj.get("orgname"));
	            	rMap.put("docfileid", subObj.get("docfileid"));
	            	rMap.put("docid", subObj.get("docid"));
	            	rMap.put("filestate", subObj.get("isdeleted"));
	            	rMap.put("nodeid", nodeObj.get("nodeid"));
	            	rMap.put("nodenm", nodeObj.get("name"));
	            	
	            	if(!"X".equals(subObj.get("isdeleted"))) {
	            		rList.add(rMap);
	            	}
	            }
			}
			long endTime = System.nanoTime();
			long lTime = endTime - startTime;
		} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.error("Exception", e);
        } catch(Exception e){
			e.printStackTrace();
			log.error("Exception", e);
		}
		
		return rList;
	}
	
	public static List getNodeList(String token, String doctypeid){
		
		//if(httpclient==null) 
		DefaultHttpClient httpclient = ZnApiUtils.getThreadSafeClient();
		HttpPost httppost = new HttpPost(CTX_ROOT+"znNode/selectZnNodeSelective.json");
		List rList = new ArrayList();
		List<NameValuePair> nameValuePairs = null;
		HttpParams params = null;
		HttpResponse response = null;
		HttpEntity httpEntity = null;
		JSONObject resObj = null;
		JSONArray znNode = null;
        JSONObject subObj = null;
        Map rMap = null;
        String parentnodeid = "";
		
		try{
			nameValuePairs = new ArrayList<NameValuePair>();
            
            nameValuePairs.add(new BasicNameValuePair("workplaceId", "101"));
            nameValuePairs.add(new BasicNameValuePair("descpt", doctypeid));
            nameValuePairs.add(new BasicNameValuePair("isused", "Y"));
                
            params = new BasicHttpParams();
            
            httppost.setParams(params);
			httppost.setHeader("nadi_auth_token", token);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            
            response = httpclient.execute(httppost);
            httpEntity = response.getEntity();					
            
            resObj = (JSONObject)JSONValue.parseWithException(EntityUtils.toString(httpEntity));
            znNode = (JSONArray)resObj.get("znNodeList");
            
            if(znNode!=null && znNode.size()>0){
            	subObj = (JSONObject)znNode.get(0);
            	parentnodeid = (String)subObj.get("nodeid");
            } else {
            	parentnodeid = "";
            }
            
            nameValuePairs = new ArrayList<NameValuePair>();
            
            nameValuePairs.add(new BasicNameValuePair("workplaceId", "101"));
            nameValuePairs.add(new BasicNameValuePair("parentnodeid", parentnodeid));
            nameValuePairs.add(new BasicNameValuePair("isused", "Y"));
                
            params = new BasicHttpParams();
            
            httppost.setParams(params);
			httppost.setHeader("nadi_auth_token", token);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            
            response = httpclient.execute(httppost);
            httpEntity = response.getEntity();					
            
            resObj = (JSONObject)JSONValue.parseWithException(EntityUtils.toString(httpEntity));
            znNode = (JSONArray)resObj.get("znNodeList");
            
            if(znNode!=null){
	            for(int idx=0; idx<znNode.size(); idx++){
	            	subObj = (JSONObject)znNode.get(idx);
	            	rMap = new HashMap();
	            	rMap.put("nodeid", subObj.get("nodeid") );
	            	rMap.put("name", subObj.get("name"));
	            	rList.add(rMap);
	            }
            }
            
		} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.error("Exception", e);
        } catch(Exception e){
			e.printStackTrace();
			log.error("Exception", e);
		}
		
		return rList;
	}
}
