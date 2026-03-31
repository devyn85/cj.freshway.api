package cjfw.core.dataaccess.largedata;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.drm.DrmUtil;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LargeExcelUtil {

	private static final Logger log = LoggerFactory.getLogger(LargeExcelUtil.class);
	private static final String MSG_COM_ERR_999 = "MSG.COM.ERR.999";

	private String fileName;
	private String sheetName;
	private int ROW_ACCESS_WINDOW_SIZE = 100;
	private SXSSFWorkbook sxssfWorkbook;

	/**
	 * 
	 * @description : LargeExcelUtil의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public LargeExcelUtil(LargeExcel largeExcel){
		// 공통 변수 체크
		if(null == largeExcel.getHeaderColumns()){
			throw new UserHandleException(MSG_COM_ERR_999, new String[]{"HEADER_COLUMNS 미설정 오류"});
		}

		if(null == largeExcel.getExcelFileName()){
			throw new UserHandleException(MSG_COM_ERR_999, new String[]{"EXCEL_FILE_NAME 미설정 오류"});
		}

		this.fileName = largeExcel.getExcelFileName();
		this.sheetName = largeExcel.getExcelSheetName()!=null? largeExcel.getExcelSheetName():"WorkSheet";
		this.sxssfWorkbook = new SXSSFWorkbook(ROW_ACCESS_WINDOW_SIZE);

		SXSSFSheet objSheet = sxssfWorkbook.createSheet(this.sheetName); // sheet 생성
		SXSSFRow objRow = setHeader(objSheet, largeExcel.getHeaderColumns());

		largeExcel.setSxssSheet(objSheet);
		largeExcel.setSxssRow(objRow);
	}

	/**
	 * 
	 * @description : setHeader 기능을 구현한 Method
	 * 				  header 세팅 - xlsx
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private SXSSFRow setHeader(SXSSFSheet objSheet, String[] headerColumns){
		SXSSFRow objRow = objSheet.createRow(0);
		for(int i=0 ; i<headerColumns.length ; i++) {
			Cell cell = objRow.createCell(i);
			cell.setCellValue(headerColumns[i] == null ? "" : headerColumns[i] );
		}
		return objRow;
	}

	/**
	 * 
	 * @description : makeExcelDownload 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void makeExcelDownload(HttpServletRequest request, HttpServletResponse response, LargeExcel largeExcel) {
		log.info("========== ExcelDownload START ==========");

		String uploadFullPath = "";
		String downloadFilePath = "";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		this.fileName += "_"+ formatter.format(LocalDateTime.now()) + ".xlsx";


		log.info("### excelFileName:" + this.fileName);

		log.info("========== ExcelDownload 1 SETP START==========");
		uploadFullPath = makeFilePath(ContextUtil.getProperty("cf.excel.uploadPath"), this.fileName);
		log.info("### uploadFullPath:" + uploadFullPath);

		try (
				// 엑셀파일 저장
				FileOutputStream fielOutputStream = new FileOutputStream(uploadFullPath);
		) {
			this.sxssfWorkbook.write(fielOutputStream);
			log.info("========== ExcelDownload 1 STEP END ==========");
		} catch(FileNotFoundException ex1) {
			log.info("========== ExcelDownload 1 STEP EXCEPTION ==========");
			log.error("LargeExcelUtil.makeExcelDownload.FileNotFoundException : ", ex1);
		} catch(IOException ex2) {
			log.info("========== ExcelDownload 1 STEP EXCEPTION ==========");
			log.error("LargeExcelUtil.makeExcelDownload.IOException : ", ex2);
		}

		log.info("========== ExcelDownload 2 SETP START==========");
		if( !"local".equals(System.getProperty("spring.profiles.active", "local")) && !"default".equals(System.getProperty("spring.profiles.active", "local"))){
			log.info("########## DRM DRMEncoding ##########");
			// DRM 적용
			UserContext userContext = largeExcel.getUserContext();
			String userID = StringUtils.isEmpty(userContext.getUserId()) ? "" : userContext.getUserId();
			String userName = StringUtils.isEmpty(userContext.getUserNm()) ? "" : userContext.getUserNm();
			String deptCd = "";
			String deptNm = "";
			String tempPath = ContextUtil.getProperty("cf.excel.uploadPath");
			// DRM
			String destFilePath = tempPath + "/" + this.fileName;

			boolean isSuccess = DrmUtil.packagingDRM(uploadFullPath, destFilePath, userID, userName, deptCd, deptNm);
			if (!isSuccess) {
				throw new UserHandleException("엑셀 Export 중 오류가 발생하였습니다.");
			}
			File srcFile = new File(uploadFullPath);
			if(srcFile.exists()) {
				Path path = Paths.get(srcFile.getPath());
				try {
					Files.delete(path);
				} catch (IOException e) {
					log.error("LargeExcelUtil.Files.delete().IOException : ", e);
				}
			}
			downloadFilePath = destFilePath;
		} else{
			downloadFilePath = uploadFullPath;
		}

		log.info("========== ExcelDownload 2 STEP END ==========");

		log.info("========== ExcelDownload 3 SETP START==========");
		// 다운로드 진행.
		download(request, response, downloadFilePath, this.fileName);
		log.info("========== ExcelDownload 3 STEP END ==========");

		this.sxssfWorkbook.dispose(); // sxssfWorkbook tmp파일 삭제
	}

	/**
	 * 
	 * @description : makeFilePath 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private String makeFilePath(String uploadPath, String excelFileName) {
		// 디렉토리 없을 경우 생성
		File file = new File(uploadPath);
		if(!file.exists()) file.mkdirs();

		return uploadPath + "/noDRM_" + excelFileName;
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
	private static void download(HttpServletRequest request, HttpServletResponse response, String sourcePath, String filename) {
		String localSourcePath = sourcePath;
		String encodedFileName = filename;

		File file = null;
		try {
			request.setCharacterEncoding(CanalFrameConstants.DEFAULT_CHARACTERSET);

			localSourcePath = StringUtils.replace(localSourcePath, "..", "");
			//업로드 경로에 파일이 위치한 경우
			file = new File(localSourcePath);
			if(!(file.exists())) {
				log.info("File not found.");
			}

			log.info(" ### filename : " + encodedFileName);
			String header = request.getHeader("User-Agent");
			if (header.contains("MSIE") || header.contains("Trident") || header.contains("Chrome")) {
				encodedFileName = URLEncoder.encode(filename, CanalFrameConstants.DEFAULT_CHARACTERSET).replace("\\+", "%20");
			}
			response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");
			response.setHeader("Content-Type", "application/x-download");
			response.setHeader("Content-Transfer-Encoding", "binary;");
			response.setHeader("Pragma", "no-cache;");
			response.setHeader("Expires", "-1;");
//			response.setHeader("Content-Length", String.valueOf(file.length()));
		} catch (Exception e2) {
			log.error("LargeExcelUtil.download.Exception : ", e2);
		}

		try (
				BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
				BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());
		) {
			byte[] readByte = new byte[8192];

			int bytes = 0;
			while ((bytes = fin.read(readByte)) != -1) {
				outs.write(readByte, 0, bytes);
			}
			outs.flush();
		} catch (Exception ex) {
			log.error("LargeExcelUtil.download.FileOutPutException : ", ex);
		} finally {
			try {
				if(null != file && file.exists()){
					Path path = Paths.get(file.getPath());
					Files.delete(path);
				}
			} catch(Exception e) {
				log.error("LargeExcelUtil.download.FilesdeleteException : ", e);
			}
		}
	}
}
