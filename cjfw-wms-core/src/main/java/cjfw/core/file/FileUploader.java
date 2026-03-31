package cjfw.core.file;

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
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.file.dto.FileUploadResDto;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.ExecuteUtil;
import lombok.RequiredArgsConstructor;

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
@RequiredArgsConstructor
@Component
public class FileUploader {
	private final CommonDao commonDao;
	private final UserContext context;
	private static final Logger log = LoggerFactory.getLogger(FileUploader.class);
	private static final String FILE_UPLOAD_PATH = "cf.file.uploadPath";
	
	/**
	 * 
	 * @description : saveFiles 기능을 구현한 Method
	 * 				  File 목록에 대한 저장 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public FileUploadResDto saveFiles(List<MultipartFile> files, List<FileUpload> fileUploadList) {
		FileUploadResDto fileUploadPostResDto = new FileUploadResDto();

		// 파일 저장 디렉토리 생성 
		File uploadedDir = new File(ContextUtil.getProperty(FILE_UPLOAD_PATH));
		if(!uploadedDir.exists()) {
			uploadedDir.mkdirs();
		}
		
		if(files != null && files.size() != 0) {
			//파일 명으로 비교해서 같으면 파일 데이터를 DTO로 통합하여 로직 처리
			for(MultipartFile file:files) {
				log.info(file.toString());

				for(FileUpload fileUpload : fileUploadList) {
					//파일명과 파일사이즈가 같다면 같은 파일로 인식한다.
					if(fileUpload.getAttchFileNm() != null && fileUpload.getAttchFileSz() != null ) {
						if(fileUpload.getAttchFileNm().equalsIgnoreCase(file.getOriginalFilename()) 
								&& Long.parseLong(fileUpload.getAttchFileSz()) == file.getSize() ) {
							fileUpload.setFile(file);
						}
					}
				}
			}
			fileUploadPostResDto.setAttchFileGrpNo(
					//파일 논리적_물리적 저장
					saveFilesWithParam(fileUploadList)
					);
		}else {//(files.size() != 0)
			if(fileUploadList != null && fileUploadList.size() != 0) {
				//파일이 없고 파일정보가 있다면(삭제만 진행할 시)
				fileUploadPostResDto.setAttchFileGrpNo(
						saveFilesWithParam(fileUploadList)
				);
			} else {
				//파일정보도 없고 파일도 없다면
				throw new UserHandleException("MSG.COM.VAL.031"); // 업로드할 파일을 추가/갱신해 주시기 바랍니다.
			}
		}
		
		return fileUploadPostResDto;
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
	private String saveFilesWithParam(List<FileUpload> fileUploadList) {
		String attchFileGrpNo = "";
		//업로드 그룹
		if(fileUploadList != null) {
			for(FileUpload fileUpload : fileUploadList) {
				if(fileUpload == null) {
					throw new UserHandleException("MSG.COM.ERR.004");
				}

				if("".equals(attchFileGrpNo)) {
					// 기존 attchFileGrpNo가 있을 경우 기존 번호 이용
					attchFileGrpNo = fileUpload.getAttchFileGrpNo();
				}
				String userId = context.getUserId();
				fileUpload.setUserId(userId);
				if("I".equals(fileUpload.getRowStatus())) {//신규 파일 저장
					//attchFileGrpNo가 없는 신규 파일일경우 GrpNo 신규채번
					if("".equals(attchFileGrpNo) || attchFileGrpNo == null) {
						attchFileGrpNo = (String)commonDao.selectOne("sampleFileUploadService.selectFileGrpNo");
						if(attchFileGrpNo == null){
							attchFileGrpNo = "1";
						}
					}
					fileUpload.setAttchFileGrpNo(attchFileGrpNo);
					
					//attchFileNo가 없는 신규 파일일 경우 No 신규 채번 및 세팅 
					if(StringUtils.isBlank(fileUpload.getAttchFileNo())){
						fileUpload.setAttchFileNo(
							(String)commonDao.selectOne("sampleFileUploadService.selectFileSeq", fileUpload)
						);
					}
					//확장자 세팅
					String fileNm = fileUpload.getAttchFileNm();
					if(StringUtils.isBlank(fileNm)) {
						//파일을 찾을 수 없습니다.
						throw new UserHandleException("MSG.COM.ERR.004");
					}
					//파일 이름 내 "." 뒤 확장자로 저장
					String extNm = fileNm.substring(fileNm.lastIndexOf('.') + 1).toLowerCase();
					fileUpload.setAttchFileExtNm(extNm);
	
					//물리적 파일 저장
					saveFilesAtomic(fileUpload);

					//논리적 파일 저장
					commonDao.insert("sampleFileUploadService.insertFileInfo", fileUpload);
				}else if("D".equals(fileUpload.getRowStatus())) {
					commonDao.delete("sampleFileUploadService.deleteFileInfo", fileUpload);
					removeFile(fileUpload);
				}
				
			}//for(fileUploadDto fileUpload : fileUploadList)
		} //if(fileUploadList != null)
		return attchFileGrpNo;
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

		//savePathNm1
		String uploadedDirPath = getUploadDirDateFormat(fileUpload.getFileTp());
		fileUpload.setSavePathNm1(uploadedDirPath);
		
		//savePathNm2
		String uploadedFileName = getFileNameWithPk(fileUpload.getAttchFileGrpNo(), fileUpload.getAttchFileNo());
		fileUpload.setSavePathNm2(uploadedFileName);
		
		String uploadedDirFullPath = ContextUtil.getProperty(FILE_UPLOAD_PATH) + "/" + uploadedDirPath;
		String fileNameExt = fileUpload.getAttchFileNm().substring(fileUpload.getAttchFileNm().lastIndexOf('.') + 1);

		int limit = Integer.parseInt(configs[0]);

		if(StringUtils.isEmpty(uploadedDirFullPath)) {
			//업로드 경로가 지정되지 않았습니다.
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

		String filePath = uploadedDirFullPath + "/" + uploadedFileName;
		log.info(filePath);
		
		boolean isValid = false;

		String ext = fileNameExt;

		for(String extention : extentions) {
			if(ext.equalsIgnoreCase(extention)) {
				isValid = true;
				break;
			}
		}
		
		if(!(isValid)) {
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
