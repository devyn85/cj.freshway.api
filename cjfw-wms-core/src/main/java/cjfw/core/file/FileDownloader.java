package cjfw.core.file;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.drm.DrmUtil;
import cjfw.core.exception.UserHandleException;
import cjfw.core.file.dto.FileDownloadResDto;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.MessageUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : FileDownloader 관련 기능을 구현한 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
@Component
@RequiredArgsConstructor
public class FileDownloader {
	
	private static final Logger log = LoggerFactory.getLogger(FileDownloader.class);
	private static final String FILE_UPLOAD_PATH = "cf.file.uploadPath";
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 * 
	 * @description : downloadFile 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void downloadFile(HttpServletResponse response, FileDownload fileDownload){
		List<FileDownloadResDto> fileDownloadInfoList = commonDao.selectList("sampleFileUploadService.selectDownloadFileInfo", fileDownload);
		
		if(!(fileDownloadInfoList.size() < 1)) {
			for(FileDownloadResDto fileDownloadInfo : fileDownloadInfoList) {
					String filePath = ContextUtil.getProperty(FILE_UPLOAD_PATH) + "/" 
										+ fileDownloadInfo.getSavePathNm1()+ "/" 
										+ fileDownloadInfo.getSavePathNm2();
					String fileName = fileDownloadInfo.getAttchFileNm();
					download(response, filePath, fileName, null);
			}
		}else {
			throw new UserHandleException("MSG.COM.ERR.004");
		}
		
	}
	
	/**
	 * @description : downloadFile 기능을 구현한 Method
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.02 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public void downloadFileNew(HttpServletResponse response, String sourcePath, String filename, String drmUseYn){
		download(response, sourcePath, filename, drmUseYn);		
	}
	
	/**
	 * 
	 * @description : download 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private void download(HttpServletResponse response, String sourcePath, String filename, String drmUseYn) {
		String localSourcePath = sourcePath;
		String encodedFileName = filename;
		try {
			encodedFileName = URLEncoder.encode(filename, CanalFrameConstants.DEFAULT_CHARACTERSET);
		} catch (UnsupportedEncodingException e) {
			log.error("FileDownloader.download().UnsupportedEncodingException : ", e);
			throw new UserHandleException("MSG.COM.ERR.004");
		}

		localSourcePath = StringUtils.replace(localSourcePath, "..", "");
		File file = new File(localSourcePath);
		
		String srcFileName = null;
		String destFileName = null;
		
		/****************************
		 * DRM packaging 적용 start
		 ****************************/
		String profile = System.getProperty("spring.profiles.active", "local");
		if(!"local".equals(profile) && !"default".equals(profile)) {
			String uploadedDirFullPath = localSourcePath.substring(0, localSourcePath.lastIndexOf("/"));

			if (drmUseYn != null && "Y".equals(drmUseYn)) {
				destFileName = uploadedDirFullPath + "/" + filename;
				
				// DRM 적용
				boolean isSuccess = DrmUtil.packagingDRM(localSourcePath, destFileName, userContext.getArea(), userContext.getUserNm(), "", "");
				if(!isSuccess) {
					log.error("FileDownloader.download().isSuccess : {}", MessageUtil.getMessage("MSG.COM.ERR.069"));
					throw new UserHandleException("MSG.COM.ERR.069");
				}
				
				file = new File(destFileName);
			} else if (drmUseYn != null && "N".equals(drmUseYn)) {
				String attchFileNm = localSourcePath.substring(localSourcePath.lastIndexOf("/") + 1);
				srcFileName = uploadedDirFullPath + "/SRC_" + attchFileNm;
				destFileName = uploadedDirFullPath + "/DEST_" + attchFileNm;
				
				try {
					// DRM 해제 대상 파일 복사
					Files.copy(Paths.get(localSourcePath), Paths.get(srcFileName), StandardCopyOption.REPLACE_EXISTING);
					
					// DRM 해제
					boolean isSuccess = DrmUtil.extractDRM(srcFileName, destFileName);					
					if(!isSuccess) {
						log.error("FileDownloader.download().isSuccess : {}", MessageUtil.getMessage("MSG.COM.ERR.069"));
						throw new UserHandleException("MSG.COM.ERR.069");
					}
					
					file = new File(destFileName);
				} catch(Exception e) {
					log.error("FileDownloader.download.Exception : " , e);
					String errorMessage = "MSG_COM_ERR_069";
					if(e instanceof UserHandleException) {
						errorMessage = ((UserHandleException) e).getErrorCode();
					}
					throw new UserHandleException(errorMessage);
				}
				
			}
		}
		/****************************
		 * DRM packaging 적용 end
		 ****************************/

		// 업로드 경로에 파일이 위치한 경우
		if(!(file.exists())) {
			log.error("FileDownloader.download() > sourcePath : ", sourcePath);
			log.error("FileDownloader.download() > localSourcePath : ", localSourcePath);
			log.error("FileDownloader.download() : ", "File Not Found");
			throw new UserHandleException("MSG.COM.ERR.004");
		}

		String contentType = "application/octet-stream;charset=" + CanalFrameConstants.DEFAULT_CHARACTERSET;
		response.setContentType(contentType);
		response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Length", String.valueOf(file.length()));

		int length = 0;
		byte[] byteBuffer = new byte[8192];
		
		try	(
			FileInputStream fileInputStream = new FileInputStream(file);
			DataInputStream inputStream = new DataInputStream(fileInputStream);
			BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream(), 8192);
		) {
			while((length = inputStream.read(byteBuffer)) != -1) {
				outStream.write(byteBuffer, 0, length);
			}
			outStream.flush();
		} catch(IOException e) {
			log.error("FileDownloader.download().IOException : ", e);
			throw new UserHandleException("MSG.COM.ERR.004");
		} finally {
			try {
				if(srcFileName != null) {
					File srcFile = new File(srcFileName);
					if(srcFile != null && srcFile.exists()) {
						FileUtils.forceDelete(srcFile);
					}
				}
				if(destFileName != null) {
					File destFile = new File(destFileName);
					if(destFile != null && destFile.exists()) {
						FileUtils.forceDelete(destFile);
					}
				}
			} catch(Exception e) {
				log.error("FileDownloader.download.Exception.finally : " , e);
			}
		}
	}
}
