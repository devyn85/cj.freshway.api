/**
 * Copyright (c) 2020 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.comfunc.func.file.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.edms.EdmsFileDownloader;
import cjfw.core.edms.EdmsFileUploader;
import cjfw.core.edms.ZnDocumentApi;
import cjfw.core.file.FileDownload;
import cjfw.core.file.FileDownloader;
import cjfw.core.file.FileUpload;
import cjfw.core.file.FileUploader;
import cjfw.core.file.FileUploaderEdms;
import cjfw.core.file.dto.FileUploadResDto;
import cjfw.core.model.UserContext;
import cjfw.wms.comfunc.func.file.dto.SampleFilePopupGetReqDto;
import cjfw.wms.comfunc.func.file.dto.SampleFilePopupGetResDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * 파일 업로드 / 다운로드
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SampleFilePopupService{

	private final CommonDao commonDao;

	private final FileUploader fileUploader;

	private final FileDownloader fileDownloader;
	
	private final EdmsFileUploader edmsFileUploader;
	
	private final EdmsFileDownloader edmsFileDownloader;
	
	private final FileUploaderEdms fileUploaderEdms;
	
	private final UserContext context;
	
	/**
	 * 파일 목록을 조회한다.
	 */
	public List<SampleFilePopupGetResDto> getFileList(SampleFilePopupGetReqDto sampleFilePopupGetReqDto) {
		List<SampleFilePopupGetResDto> list = commonDao.selectList("sampleFileUploadService.getFileList", sampleFilePopupGetReqDto);
		if(!list.isEmpty()) {
			return list;// + "MSG.COM.ERR.007"
		}
		return list;// + "MSG.COM.SUC.011"
	}
	
	/**
	 * 파일 목록을 저장한다.
	 */
	public FileUploadResDto saveFileList(List<MultipartFile> files, List<FileUpload> fileInfoList) {
		return fileUploader.saveFiles(files, fileInfoList);
	}
	
	/**
	 * 파일 다운로드
	 */
	public void downloadFile(HttpServletResponse response, FileDownload fileDownload){
		fileDownloader.downloadFile(response, fileDownload);
	}
	
	/**
	 * @description : 차량정보 파일 업로드
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String carDriverSaveFileUpload(List<MultipartFile> files, List<FileUpload> fileInfoList) {
		// NAS 파일 업로드
		List<FileUpload> fileUploadList = fileUploaderEdms.saveFiles(files, fileInfoList);		
		
		// EDMS 파일 업로드
		List<Map<String, Object>> rFileList = edmsFileUploader.batchFileRegister(fileUploadList);
		
		// TO-DO : 파일정보 업무 TABLE에 MERGE
		for(Map<String, Object> rFile : rFileList) {
			log.info("==================== EDMS 업로드 결과 {}", rFile.toString());
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 저장위치정보 파일 업로드
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public FileUploadResDto plantXslSaveFileUpload(List<MultipartFile> files, List<FileUpload> fileInfoList) {
		// 파일명 업무에 맞게 변경
		int idx = 0;
		for(FileUpload fileInfo : fileInfoList) {
			String strFileName = fileInfo.getAttchFileNm();
			String strFileExtension = "";
			String strTransName = "";
			
			int dot_idx = strFileName.lastIndexOf('.');
			if (dot_idx > -1) {
				strFileExtension = strFileName.substring(dot_idx+1, strFileName.length());
				strTransName = "WMS_PLANTXSL_TEST_"+System.currentTimeMillis() + "_" + idx + "." + strFileExtension;
			} else {
	        	strTransName = "WMS_PLANTXSL_TEST_"+System.currentTimeMillis() + "_" + idx;
			}
			fileInfo.setTransFileNm(strTransName);
			idx++;
		}
		
		// NAS 파일 업로드
		List<FileUpload> fileUploadList = fileUploaderEdms.saveFiles(files, fileInfoList);
		
		// EDMS 파일 업로드
		for(FileUpload fileInfo : fileUploadList) {
			String strTempFilePath = fileInfo.getSavePathNm1() + "/" + fileInfo.getTransFileNm();
			Map rMap = ZnDocumentApi.registerTCS(strTempFilePath, fileInfo.getTransFileNm(), context.getUserNo(), "100");
			
			// TO-DO : 파일정보 업무 TABLE에 MERGE
			List<Map<String, Object>> fileList = (List)rMap.get("filelist");
			for(Map<String, Object> file : fileList) {
				log.info("==================== EDMS 업로드 결과 {}", file.toString());
			}
		}
				
		
		return null;
	}
	
	/**
	 * @description : 저장위치정보 파일 다운로드
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.02 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public void plantXslFileDownload(HttpServletResponse response, FileDownload fileDownload) {
		edmsFileDownloader.downloadFile(response, fileDownload.getAttchFileNm(), "100");
		
		// TO-DO : 업무 로직
	}
}
