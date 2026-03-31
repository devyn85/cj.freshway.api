package cjfw.core.edms;

import java.util.Map;

import org.springframework.stereotype.Component;

import cjfw.core.file.FileDownloader;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import jakarta.servlet.http.HttpServletResponse;
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
public class EdmsFileDownloader {
	private final UserContext context;
	private final FileDownloader fileDownloader;
	private static final String FILE_UPLOAD_PATH = "cf.edms.tempDir";
	
	/**
	 * @description : EDMS 파일 다운로드
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public void downloadFile(HttpServletResponse response, String attrvalue, String docTypeId) {
		// EDMS 파일 WAS 서버로 다운로드
		Map rMap = ZnDocumentApi.viewTCS(attrvalue, ContextUtil.getProperty(FILE_UPLOAD_PATH) + "/" + context.getUserNo(), context.getUserNo(), docTypeId);
		
		// WAS 서버 파일 클라이언트로 다운로드
		fileDownloader.downloadFileNew(response, ContextUtil.getProperty(FILE_UPLOAD_PATH) + "/" + context.getUserNo() + "/" + attrvalue, attrvalue, null);
	}
	
	/**
	 * @description : EDMS 파일 다운로드 (저장 파일명 추가)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public void downloadFile(HttpServletResponse response, String attrvalue, String docTypeId, String saveFileNm) {
		// EDMS 파일 WAS 서버로 다운로드
		Map rMap = ZnDocumentApi.viewTCS(attrvalue, ContextUtil.getProperty(FILE_UPLOAD_PATH) + "/" + context.getUserNo(), context.getUserNo(), docTypeId);
		
		log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ EdmsFileDownloader.downloadFile() {}", rMap.toString());
		
		// WAS 서버 파일 클라이언트로 다운로드
		fileDownloader.downloadFileNew(response, ContextUtil.getProperty(FILE_UPLOAD_PATH) + "/" + context.getUserNo() + "/" + attrvalue, saveFileNm, null);
	}
}
