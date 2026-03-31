package cjfw.wms.cm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.file.FileDownload;
import cjfw.wms.cm.service.CmFileService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.07.14 
 * @description : 타 시스템 연동 테스트
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.14 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/file")
public class CmFileController {
	
	private final CmFileService cmFileService;
	
	/**
	 * @description : NAS 파일 다운로드
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.21 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@PostMapping(value = "/v1.0/fileDownload")
	public void fileDownload(HttpServletResponse response, FileDownload fileDownload) {		
		cmFileService.fileDownload(response, fileDownload);
	}

}