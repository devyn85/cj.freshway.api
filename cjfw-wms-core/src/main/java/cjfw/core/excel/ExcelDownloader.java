package cjfw.core.excel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.drm.DrmUtil;
import cjfw.core.exception.UserHandleException;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.MessageUtil;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : ExcelDownloader 기능을 구현한 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
@Slf4j
public class ExcelDownloader {
	
	/**
	 * 
	 * @description : ExcelDownloader의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private ExcelDownloader() {}
	
	/**
	 * 
	 * @description : downloadExcel 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public static void downloadExcel(HttpServletRequest request, HttpServletResponse response, ExcelDownload excelDownload) {
		ByteArrayOutputStream baoStream = new ByteArrayOutputStream();

		String data = excelDownload.getData(); // grid data
		String extension = excelDownload.getExtension(); // fiel extension
		String fileName = excelDownload.getFileName(); // file name

		byte[] dataByte = Base64.decodeBase64(data.getBytes()); // 데이터 base64 디코딩
		try {
			// csv 를 엑셀에서 열기 위해서는 euc-kr 로 작성해야 함.
			if (extension.equals("csv")) {
				String sting = new String(dataByte, CanalFrameConstants.DEFAULT_CHARACTERSET);
				baoStream.write(sting.getBytes("euc-kr"));
			} else {
				baoStream.write(dataByte);	// stream 형태로 data 변경
			}
		} catch (Exception e) {
			log.error("ExcelDownloader.downloadExcel().Exception : ", e);
		}
		
		String downFileName = (StringUtils.isEmpty(fileName) ? "grid_export" : fileName); // 다운로드 될 파일명
		if(!downFileName.endsWith(extension)) {
			downFileName = downFileName + "." + extension;
		}
		String excelUploadPath = ContextUtil.getProperty("cf.excel.uploadPath");
		// DRM
		String srcFilePath = excelUploadPath + "/noDRM_" + downFileName;
		String destFilePath = excelUploadPath + "/" + downFileName;
		byte[] byteBuffer = new byte[8192];
		File srcFile = null;
		File destFile = null;
		
		//생성된 임시 파일의 삭제를 위한 try
		try {
			// 파일 업로드
			File directory = new File(excelUploadPath);
			if(!directory.exists()){
				directory.mkdirs();
			}

			//DRM 처리를 위한 임시파일 생성
			createTempFile(dataByte, excelUploadPath, srcFilePath); 
			
			srcFile = new File(srcFilePath);
			
			if (!srcFile.exists()) {
				log.error("ExcelDownloader.[{}] file not existed {}", srcFilePath, MessageUtil.getMessage("MSG_CM_0043"));
				throw new UserHandleException(MessageUtil.getMessage("MSG_CM_0043"));
			}
			
			/****************************
			 * DRM packaging 적용 start
			 * excelDownload.getDrmUseYn() 특정 업무화면 DRM 해제 옵션 추가
			 ****************************/
			String drmUseYn = StringUtils.isEmpty(excelDownload.getDrmUseYn()) ? "" : excelDownload.getDrmUseYn();
			String empType = StringUtils.isEmpty(excelDownload.getEmptype()) ? "" : excelDownload.getEmptype();
			if(
					!"local".equals(System.getProperty("spring.profiles.active", "local")) 
					&& !"default".equals(System.getProperty("spring.profiles.active", "local")) 
					&& (!"N".equals(drmUseYn) || ("N".equals(drmUseYn) && "01/02/03".contains(empType))) // DRM사용 !== "N" OR (DRM사용 === "N" && 사원유형 === 정직원)
			){
				String userID = StringUtils.isEmpty(excelDownload.getUserId()) ? "" : excelDownload.getUserId();
				String userName = StringUtils.isEmpty(excelDownload.getUserNm()) ? "" : excelDownload.getUserNm();
				String deptCd = "";
				String deptNm = "";
	
				boolean isSuccess = DrmUtil.packagingDRM(srcFilePath, destFilePath, userID, userName, deptCd, deptNm);
				if (!isSuccess) {
					throw new UserHandleException("엑셀 Export 중 오류가 발생하였습니다.");
				}
				destFile = new File(destFilePath);
				log.info("DRM PACKAGING...END :: {}", destFilePath);
			}
			/****************************
			 * DRM packaging 적용 end
			 ****************************/
			else {
				destFile = new File(srcFilePath);
			}
			
			// 파일명 한글 처리
			String agent = request.getHeader("User-Agent");
			if (agent.contains("MSIE") || agent.contains("Trident") || agent.contains("Chrome")) {
				downFileName = encodeFileName(downFileName);
			} 
				
			if (destFile != null) {
				//파일 다운로드 
				excelFileDownload(response, downFileName, byteBuffer, destFile); 
			}
		} catch (Exception e) {
			log.error("ExcelDownloader.downloadExcel().Exception : ", e);
		} finally {
			try {
				if (srcFile != null && srcFile.exists()) {
					Path path = Paths.get(srcFilePath);
					Files.delete(path);
				}
				if (destFile != null && destFile.exists()) {
					Path path = Paths.get(destFile.getPath());
					Files.delete(path);
				}
			} catch (IOException e) {
				log.error("ExcelDownloader.downloadExcel().Files.delete.IOException : ", e);
			}
		}
	}

	/**
	 * 
	 * @description : encodeFileName 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private static String encodeFileName(String downFileName) {
		try {
			downFileName = URLEncoder.encode(downFileName, CanalFrameConstants.DEFAULT_CHARACTERSET).replace("\\+", "%20");
		} catch (UnsupportedEncodingException e) {
			log.error("ExcelDownloader.downloadExcel().UnsupportedEncodingException : ", e);
		}
		return downFileName;
	}

	/**
	 * 
	 * @description : excelFileDownload 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private static void excelFileDownload(HttpServletResponse response, String downFileName, byte[] byteBuffer,
			File destFile) {
		int length;
		try (
			DataInputStream inputStream = new DataInputStream(new FileInputStream(destFile));
			BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream(), 8192);
		){
			//response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
		 	response.setHeader("Content-Disposition", "attachment; filename=\"" + downFileName + "\";");
			response.setHeader("Content-Length", String.valueOf(destFile != null ? destFile.length() : 0));

			while ((length = inputStream.read(byteBuffer)) != -1) {
				outStream.write(byteBuffer, 0, length);
			}
			outStream.flush();
		} catch (IOException e) {
			log.error("ExcelDownloader.downloadExcel().IOException.finally : ", e);
		}
	}

	/**
	 * 
	 * @description : createTempFile 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private static void createTempFile(byte[] dataByte, String excelUploadPath, String srcFilePath) {
		try(
			FileOutputStream output = new FileOutputStream(new File(srcFilePath));
		) {
			output.write(dataByte);
		} catch (Exception e) {
			log.error("ExcelDownloader.downloadExcel().FileOutputStream.Exception : ", e);
		}
	}
}
