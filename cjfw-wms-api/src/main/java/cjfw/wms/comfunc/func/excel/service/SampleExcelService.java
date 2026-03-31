/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.comfunc.func.excel.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.excel.ExcelDownload;
import cjfw.core.excel.ExcelDownloader;
import cjfw.core.excel.ExcelUpload;
import cjfw.core.excel.ExcelUploader;
import cjfw.core.model.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * Grid Excel 처리 서비스 클래스<br>
 */
@Service
@RequiredArgsConstructor
public class SampleExcelService {

	private final UserContext userContext;

	/**
	 * 엑셀 파일을 Grid 데이터로 업로드<br>
	 */
	public ExcelUpload uploadExcel(MultipartFile file) {
		return ExcelUploader.uploadExcel(file);

		// other 메소드 참조 샘플 코드
		// return ExcelUploader.uploadExcel(file, Arrays.asList(new String[] {"aa", "bb", "cc", "dd"}), true)
	}

	/**
	 * Grid를 엑셀 파일로 다운로드<br>
	 */
	public void downloadExcel(HttpServletRequest request, HttpServletResponse response, ExcelDownload excelDownload) {
		excelDownload.setUserId(userContext.getUserId());
		excelDownload.setUserNm(userContext.getUserNm());
		ExcelDownloader.downloadExcel(request, response, excelDownload);
	}
}
