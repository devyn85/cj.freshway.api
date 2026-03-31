package cjfw.core.edms;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.exception.UserHandleException;
import cjfw.core.file.FileUpload;
import cjfw.core.file.dto.FileUploadResDto;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.ExecuteUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : FileUploader 기능을 구현한 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class EdmsFileUploader {
	private final UserContext context;
	private static final String FILE_UPLOAD_PATH = "cf.edms.tempDir";
	
	public List<Map<String, Object>> batchFileRegister(List<FileUpload> fileUploadList) {
		return batchFileRegisterExecute(fileUploadList, "1313");
	}
	
	public List<Map<String, Object>> batchFileRegister(List<FileUpload> fileUploadList, String doctypecd) {
		return batchFileRegisterExecute(fileUploadList, doctypecd);
	}
	
	/**
	 * @description : File 목록에 대한 EDMS 저장 (실제 실행 함수)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.24 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public List<Map<String, Object>> batchFileRegisterExecute(List<FileUpload> fileUploadList, String doctypecd) {
		List<Map<String, Object>> rfileList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();

		try {
			// EDMS 연동을 위한 파일 설정
			for(FileUpload fileUpload : fileUploadList) {
				Map<String, Object> fileMap = new HashMap<String, Object>();
				
				// 연동 위한 파일명 변경
				if (fileUpload.getTransFileNm() != null && !"".equals(fileUpload.getTransFileNm())) {
					fileMap.put("orgfilename", fileUpload.getTransFileNm());
					fileMap.put("filename", fileUpload.getSavePathNm1() + "/" + fileUpload.getTransFileNm());
				} else {
					fileMap.put("orgfilename", fileUpload.getAttchFileNm());
					fileMap.put("filename", fileUpload.getSavePathNm1() + "/" + fileUpload.getAttchFileNm());
				}
				
				fileList.add(fileMap);
			}
						
			Map<String, Object> result = new HashMap<String, Object>();
			
			String tokenEdms = ZnApiUtils.getLoginToken(context.getUserNo(), context.getUserNo());
			String doctypeid = ZnApiUtils.getDoctypeidByCode(tokenEdms, "101", doctypecd);	// EDMS에서 사용하는 doctypeid 조회	//256
			result = ZnApiUtils.BatchFileUplad(tokenEdms, fileList, doctypeid);
			JSONArray jresult = (JSONArray)result.get("znDocExd");
			JSONObject resObj = (JSONObject)jresult.get(0);
			System.out.println("doctypeattributeid :: " + resObj.get("doctypeattributeid")); 
			System.out.println("docid:::" +resObj.get("docid")); 
			
			ZnDocumentApi znApi = new ZnDocumentApi();
			Map<String, Object> rMap = znApi.BatchFileRegister(tokenEdms, result);
			
			rfileList = (List)rMap.get("filelist");
			
			if (rfileList.size() <= 0 ){
				throw new UserHandleException("MSG.COM.ERR.015"); // 저장 처리 중 오류가 발생하였습니다.
			}
			
		} catch (Exception e) {
			throw new UserHandleException("MSG.COM.ERR.015"); // 저장 처리 중 오류가 발생하였습니다.
		}
		
		return rfileList;
	}
	
	/**
	 * 
	 * @description : saveFilesWithParam 기능을 구현한 Method
	 * 				  파일 업로드 공통모듈로서 업로드와 관련된 세부 작업 수행
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private void saveFilesWithParam(List<FileUpload> fileUploadList) {
		// 업로드 그룹
		if (fileUploadList != null) {
			for (FileUpload fileUpload : fileUploadList) {
				if (fileUpload == null) {
					throw new UserHandleException("MSG.COM.ERR.004");
				}
				
				fileUpload.setUserId(context.getUserId());
				
				if ("I".equals(fileUpload.getRowStatus())) { // 신규 파일 저장
					// 확장자 세팅
					String fileNm = fileUpload.getAttchFileNm();
					if (StringUtils.isBlank(fileNm)) {
						// 파일을 찾을 수 없습니다.
						throw new UserHandleException("MSG.COM.ERR.004");
					}
					// 파일 이름 내 "." 뒤 확장자로 저장
					String extNm = fileNm.substring(fileNm.lastIndexOf('.') + 1).toLowerCase();
					fileUpload.setAttchFileExtNm(extNm);
	
					// 물리적 파일 저장
					saveFilesAtomic(fileUpload);
				} else if ("D".equals(fileUpload.getRowStatus())) {
					removeFile(fileUpload);
				}
			}
		}
	}

	/**
	 * 
	 * @description : saveFilesAtomic 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private void saveFilesAtomic(FileUpload fileUpload) {

		String[] configs = getUploadConfig(fileUpload.getFileTp());
		String[] extentions = org.springframework.util.StringUtils.tokenizeToStringArray(configs[1], "|");

		String uploadedDirFullPath = ContextUtil.getProperty(FILE_UPLOAD_PATH) + "/" + context.getUserNo();
		String fileNameExt = fileUpload.getAttchFileNm().substring(fileUpload.getAttchFileNm().lastIndexOf('.') + 1);
		fileUpload.setSavePathNm1(uploadedDirFullPath);

		int limit = Integer.parseInt(configs[0]);

		if(StringUtils.isEmpty(uploadedDirFullPath)) {
			// 업로드 경로가 지정되지 않았습니다.
			throw new UserHandleException("MSG.COM.ERR.003");
		}

		File uploadedDir = new File(uploadedDirFullPath);
		if(!(uploadedDir.exists())) {
			uploadedDir.mkdirs();

			if(("/".equals(File.separator)) && ("true".equals(ContextUtil.getProperty("upload.setDirectoryPermission")))) {
				chmod(uploadedDir, ContextUtil.getProperty("cf.file.dirPermission"));
				String parentPath = uploadedDir.getParent();
				while(parentPath != null) {
					if(parentPath.equals(ContextUtil.getProperty(FILE_UPLOAD_PATH))) {
						break;
					}
					File parentDir = new File(parentPath);
					chmod(parentDir, ContextUtil.getProperty("cf.file.dirPermission"));
					parentPath = parentDir.getParent();
				}
			}
		}

		String filePath = uploadedDirFullPath + "/" + fileUpload.getAttchFileNm();
		boolean isValid = false;
		String ext = fileNameExt;

		for(String extention : extentions) {
			if(ext.equalsIgnoreCase(extention)) {
				isValid = true;
				break;
			}
		}
		
		if (!(isValid)) {
			throw new UserHandleException("MSG.COM.ERR.005");
		}
		
		try {
			byte[] fileByte = fileUpload.getFile().getBytes();
			if(fileSizeValidate(fileByte, limit)) {
				uploadFile(fileByte, filePath);
			} else {
				throw new UserHandleException("MSG.COM.ERR.006");
			}
		} catch (IOException e) {
            log.error("에러발생", e);
		}
	}
	
	/**
	 * 
	 * @description : uploadFile 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private void uploadFile(byte[] file, String filePath) {
		byte[] buffer = new byte[8192];
		File f = new File(filePath);
		if(f.exists()) {
			try {
				Path path = Paths.get(f.getPath());
				Files.delete(path);
			} catch (IOException e) {
				log.error("FileUploader.upload().IOException : ", e);
			}
		}
		
		try (
			InputStream inStream = new ByteArrayInputStream(file);
			OutputStream outStream = new FileOutputStream(filePath);
		){
			while(true) {
				int n = inStream.read(buffer);
				if(n <= 0) {
					break;
				}
				outStream.write(buffer, 0, n);
			}
		} catch(IOException e) {
			log.error("FileUploader.uploadFile().IOException : ", e);
		} 
		
		
		if(!f.setReadable(true, false)) {
			//파일읽기전용으로 만들기에 실패했을 때 처리
			log.error("FileUploader.uploadFile() file setReadable fail!");
		}
	}
	
	/**
	 * 
	 * @description : removeFile 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private void removeFile(FileUpload fileUpload) {
		String uploadedDirPath = fileUpload.getSavePathNm1();
		String uploadedFileName = fileUpload.getSavePathNm2();
		String uploadedFileFullPath = new StringBuilder(ContextUtil.getProperty(FILE_UPLOAD_PATH)).append("/").append(uploadedDirPath).append("/").append(uploadedFileName).toString();
		File uploadfile = new File(uploadedFileFullPath);
		if (uploadfile.exists()) {
			Path path = Paths.get(uploadfile.getPath());
			try {
				Files.delete(path);
			} catch (IOException e) {
				log.error("FileUploader.removeFile().IOException : ", e);
			}
		}
	}
	
	/**
	 * 
	 * @description : chmod 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private void chmod(File f, String perm) {
		ExecuteUtil.execute("chmod " + perm + " " + f.getAbsolutePath(), 2000L);
	}
	
	/**
	 * 
	 * @description : fileSizeValidate 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private boolean fileSizeValidate(byte[] file, int limit) {
		if(limit <= 0) {
			return true;
		}
		return file.length > limit ? false : true;
	}
	
	/**
	 * 
	 * @description : getUploadConfig 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private String[] getUploadConfig(String type) {
		String limit = ContextUtil.getProperty("cf.file." + type + ".sizeLimit");
		String extention = ContextUtil.getProperty("cf.file." + type + ".extentionLimit");
		return new String[]{limit, extention};
	}
	
	/**
	 * 
	 * @description : getUploadDirDateFormat 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private String getUploadDirDateFormat(String type) {
		DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String dayString =LocalDate.now().format(newFormatter);
		String ret = new StringBuilder(type).append("/").append(dayString.substring(0, 4)).append("/").append(dayString.substring(4, 8)).toString();
		return ret;
	}
	
	/**
	 * 
	 * @description : getFileNameWithPk 기능을 구현한 Method
	 * 				  업로드된 파일명의 보안 적용 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private String getFileNameWithPk(String refKey, String fileSeq) {
		String localRefKey = refKey;
		String localFileSeq = fileSeq;

		if(localRefKey != null && localRefKey.length() > 0) {
			while(localRefKey.length() < 16) {
				localRefKey = "0" + localRefKey;
			}
		}
		if(localFileSeq != null && localFileSeq.length() > 0) {
			while(localFileSeq.length() < 4) {
				localFileSeq = "0" + localFileSeq;
			}
		} else {
			localFileSeq = "";
		}
		String uploadedFileName = localRefKey + localFileSeq;
		while(uploadedFileName.length() < 20) {
			uploadedFileName = "0" + uploadedFileName;
		}
		return uploadedFileName;
	}
}
