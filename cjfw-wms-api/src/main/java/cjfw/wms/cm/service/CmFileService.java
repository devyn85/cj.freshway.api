package cjfw.wms.cm.service;

import org.springframework.stereotype.Service;

import cjfw.core.exception.UserHandleException;
import cjfw.core.file.FileDownload;
import cjfw.core.file.FileDownloader;
import cjfw.core.utility.ContextUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.04.30 
 * @description : 기준정보 > 기타기준정보 > 코드마스터 목록 조회 및 저장 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.04.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmFileService {
	
	private final FileDownloader fileDownloader;

	/**
	 * @description : 저장위치정보 파일 다운로드
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.02 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public void fileDownload(HttpServletResponse response, FileDownload fileDownload) {
		if (fileDownload.getDirType() == null || "".equals(fileDownload.getDirType())) {
			throw new UserHandleException("MSG.COM.ERR.004");
		}
		
		String dirPath = "";
		if ("saveFullPath".equals(fileDownload.getDirType())) {
			dirPath = fileDownload.getSavePathNm();
		} else if ("savePath".equals(fileDownload.getDirType())) {
			dirPath = ContextUtil.getProperty("cf.file.uploadPath") + "/" + fileDownload.getSavePathNm();
		} else {
			dirPath = ContextUtil.getProperty("cf.upload.dir."+fileDownload.getDirType());
		}
		
		if (!"".equals(dirPath)) {
			// 다운로드 파일명 변경
			String saveFileNm = fileDownload.getAttchFileNm();
			if (fileDownload.getSaveFileNm() != null && !"".equals(fileDownload.getSaveFileNm())) {
				saveFileNm = fileDownload.getSaveFileNm();
			}
			
			// NAS 서버 파일 클라이언트로 다운로드
			if ("saveFullPath".equals(fileDownload.getDirType())) {
				fileDownloader.downloadFileNew(response, dirPath , saveFileNm, fileDownload.getDrmUseYn());
			} else {
				fileDownloader.downloadFileNew(response, dirPath + "/" + fileDownload.getAttchFileNm() , saveFileNm, fileDownload.getDrmUseYn());
			}
		} else {
			throw new UserHandleException("MSG.COM.ERR.004");
		}
	}

}
