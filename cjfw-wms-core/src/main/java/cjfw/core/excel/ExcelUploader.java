package cjfw.core.excel;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.drm.DrmUtil;
import cjfw.core.exception.SystemException;
import cjfw.core.exception.UserHandleException;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.StringUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : ExcelUploader 기능을 구현한 Controller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
@Slf4j
public class ExcelUploader {

	private static final int NOT_USE_COLROW_VAL = -99;
	private static final String MSG_COM_ERR_069 = "MSG.COM.ERR.069";

	/**
	 * 
	 * @description : (Utility classes should not have public constructors) ExcelUploader의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private ExcelUploader() {
		throw new IllegalStateException("Utility class");
	}
		
	/**
	 * 
	 * @description : uploadExcel 기능을 구현한 Method
	 * 				  file객체만 받아서 처리, db컬럼명이 엑셀에 0번째 존재하고, DomParser 사용 시 호출
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public static ExcelUpload uploadExcel(MultipartFile file){
		return uploadExcelProc(file, null, false, 1);
	}

	/**
	 * 
	 * @description : uploadExcel 기능을 구현한 Method
	 * 				  file객체 및 DomParser 사용 처리, db컬럼명을 파라메타로 받음
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public static ExcelUpload uploadExcel(MultipartFile file, List<String> listColNames){
		return uploadExcelProc(file, listColNames, false, 1);
	}
	
	/**
	 * 
	 * @description : uploadExcel 기능을 구현한 Method
	 * 				  file객체 및 DomParser 사용 처리, db컬럼명을 파라메타로 받음
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public static ExcelUpload uploadExcel(MultipartFile file, List<String> listColNames, int startRow){
		return uploadExcelProc(file, listColNames, false, startRow);
	}

	/**
	 * 
	 * @description : uploadExcel 기능을 구현한 Method
	 * 				  file객체, db컬럼명, DomParser/SaxParser 선택처리(isLarge true이면 SaxPaser사용)
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public static ExcelUpload uploadExcel(MultipartFile file, List<String> listColNames, boolean isLarge){
		return uploadExcelProc(file, listColNames, isLarge, 1);
	}
	
	/**
	 *
	 * @description : ################ OOM 대응을 위한 테스트용 메소드 ###################
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public static ExcelUpload uploadExcel(MultipartFile file, List<String> listColNames, boolean isLarge, int startRow){
		return uploadExcelProc(file, listColNames, isLarge, startRow);
	}

	/**
	 * 
	 * @description : uploadExcelProc 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private static ExcelUpload uploadExcelProc(MultipartFile file, List<String> listColNames, boolean isLarge, int startRow) {
		File srcFile = null; // DRM 해제 전 파일
		File destFile = null; // DRM 해제 후 파일
		String srcFileName = ""; // DRM해제 전 업로드 원본 파일경로(DRM해제 전, noDRM_xxxx.xlsx)
		String destFileName = ""; // DRM해제 후 파일경로
//		int startRow = 1;
		int columnRow = 0;
		int sheetNum = 0; // 시트번호 추가(0일 경우 1번 시트를 의미)
	
		ExcelUpload excelUpload = null;

		try {
			String excelTempPath = ContextUtil.getProperty("cf.excel.uploadPath");
			File xlsTmpUploaddDir = new File(excelTempPath);
			
			if(!(xlsTmpUploaddDir.exists())) {
				xlsTmpUploaddDir.mkdirs();
			}
			log.info("== file : {}" , file);
			

			@SuppressWarnings("rawtypes")
			String fileName = file.getOriginalFilename();
			String fileKind = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
			int pos = fileName.lastIndexOf("\\");
			if(pos > -1) {
				fileName = fileName.substring(pos + 1);
			}
			pos = fileName.lastIndexOf("/");
			if(pos > -1) {
				fileName = fileName.substring(pos + 1);
			}

			srcFileName = xlsTmpUploaddDir + "/noDRM_" + fileName;
			destFileName = xlsTmpUploaddDir + "/" + fileName;

			Files.copy(file.getInputStream(), Paths.get(srcFileName), StandardCopyOption.REPLACE_EXISTING);

			String profile = System.getProperty("spring.profiles.active", "local");
			
			log.info("== srcFileName : {}" , srcFileName);
			
			/****************** DRM extract 적용 start *******************/
			if(!"local".equals(profile) && !"default".equals(profile) && !"test".equals(profile)) {

				// decrypt 처리 : destFileName이 전체 경로 파일명
				boolean isSuccess = DrmUtil.extractDRM(srcFileName, destFileName);
				if(!isSuccess) {
					log.error("ExcelUploadReader.excelUploadReader().isSuccess : {}", MessageUtil.getMessage(MSG_COM_ERR_069));
					throw new UserHandleException(MSG_COM_ERR_069);
				}
				// DRM 사용 시 destFileName 으로 변경
				destFile = new File(destFileName);
			}
			/****************** DRM extract 적용 end   *******************/

			else{
				destFile = new File(srcFileName);
			}
			
			XSSFWorkbook xlsxWorkbook = null;
			HSSFWorkbook xlsWorkbook = null;
			XSSFSheet xlsxSheet = null;
			HSSFSheet xlsSheet = null;

			if(destFile != null && !destFile.exists()) {
				throw new UserHandleException("file not existed!!!");
			}

			if(listColNames != null && listColNames.size() > 0) {
				columnRow = NOT_USE_COLROW_VAL;
			}
			if(fileKind.equals("xls")) {
				xlsWorkbook = (HSSFWorkbook)WorkbookFactory.create(destFile);
				// 시트번호 선택 후 예외처리
				if(xlsWorkbook.getNumberOfSheets() < sheetNum+1) {
					throw new UserHandleException("isNotExist Sheet(IS_LARGE=false)");
				}
				xlsSheet = xlsWorkbook.getSheetAt(sheetNum);
				excelUpload = xlsParsing(xlsWorkbook, xlsSheet, columnRow, startRow, listColNames);
			} else {
				if(isLarge){ // SaxParser 처리
					ExcelSheetXMLHandler excelSheetXmlHandler = new ExcelSheetXMLHandler(destFile, columnRow, startRow, listColNames, sheetNum);
					excelUpload = excelSheetXmlHandler.getParsingData();
				} else{ // DomParser 처리
					xlsxWorkbook = (XSSFWorkbook)WorkbookFactory.create(destFile); // XSS(2007이상)
					// 시트번호 선택 후 예외처리
					if(xlsxWorkbook.getNumberOfSheets() < sheetNum+1) {
						throw new UserHandleException("isNotExist Sheet(IS_LARGE=false)");
					}
					xlsxSheet = xlsxWorkbook.getSheetAt(sheetNum);
					excelUpload = xlsxParsing(xlsxWorkbook, xlsxSheet, columnRow, startRow, listColNames);
				}

			}

			if(fileKind.equals("xls") && xlsWorkbook != null) {
				xlsWorkbook.close();
			} else if(xlsxWorkbook != null) {
				xlsxWorkbook.close();
			}

		} catch(Exception e) {
			log.error("ExcelUploadReader.excelUploadReader.Exception : " , e);
			String errorMessage = MSG_COM_ERR_069;
			if(e instanceof UserHandleException) {
				errorMessage = ((UserHandleException) e).getErrorCode();
			}
			throw new UserHandleException(errorMessage);

		} finally {
			try {
				if(srcFile == null)
					srcFile = new File(srcFileName);
				if(srcFile != null && srcFile.exists()) {
					FileUtils.forceDelete(srcFile);
				}
				if(destFile == null)
					destFile = new File(destFileName);
				if(destFile != null && destFile.exists()) {
					FileUtils.forceDelete(destFile);
				}
			}  catch(Exception e) {
				log.error("ExcelUploadReader.ExcelUploadReader.Exception.finally : " , e);
			}
		}
		return excelUpload;
	}
	

	/**
	 * 
	 * @description : xlsxParsing 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	private static ExcelUpload xlsxParsing(XSSFWorkbook xlsxWorkbook, XSSFSheet xlsxSheet, int columnRow, int startDataRow, List listColNames) throws SystemException {
		FormulaEvaluator evaluator = xlsxWorkbook.getCreationHelper().createFormulaEvaluator();
		String data = null;
		List<List> allCellData = new ArrayList<>();
		List headerNames_data = null;
		List dbColumnId_data = null;
		List cellDatas_data = null;
		int rows = xlsxSheet.getPhysicalNumberOfRows();
		
		for(int rownum = 0; rownum < rows; rownum++) {
			XSSFRow xlsxRow = xlsxSheet.getRow(rownum);
			if(rownum == -1) {
				headerNames_data = new ArrayList();
			} else if(columnRow == NOT_USE_COLROW_VAL && listColNames != null && rownum != -1 && rownum < startDataRow) {
				if(dbColumnId_data == null) {
					dbColumnId_data = (ArrayList<String>)listColNames;
				}
			} else if(columnRow != NOT_USE_COLROW_VAL && rownum == columnRow) {
				if(dbColumnId_data == null)
					dbColumnId_data = new ArrayList();
			} else if(rownum >= startDataRow) {
				cellDatas_data = new ArrayList();
			}
			if(xlsxRow != null) {
				int cells = xlsxRow.getLastCellNum();

				for(int cellnum = 0; cellnum < cells; cellnum++) {
					XSSFCell cell = xlsxRow.getCell(cellnum);

					if(cell != null) {
						
						switch(cell.getCellType()) {
							case NUMERIC:
				                short dataFormat = cell.getCellStyle().getDataFormat();
				                if (14 == dataFormat) {
				                	Date dateCellValue = cell.getDateCellValue();
				                	data = new SimpleDateFormat("yyyy-MM-dd").format(dateCellValue);
				                } else {
				                	cell.setCellType(CellType.STRING);
				                	data = new DataFormatter().formatCellValue(cell);
				                }				            	
								
								if(rownum == -1) {
									headerNames_data.add(data);
								} else if(rownum == columnRow) {
									if(listColNames == null && dbColumnId_data != null) {
										dbColumnId_data.add(data);
									}
								} else if(rownum >= startDataRow && cellDatas_data != null) {
									cellDatas_data.add(data);
								}								
								

								break;

							case FORMULA:
								cell.setCellType(CellType.FORMULA);
//								data = cell.getCellFormula();
								data = getCellValue(cell, evaluator); // 엑셀 수식의 결과값 가져오기
								
								if(rownum == -1) {
									headerNames_data.add(data);
								} else if(rownum == columnRow) {
									if(listColNames == null && dbColumnId_data != null) {
										dbColumnId_data.add(data);
									}
								} else if(rownum >= startDataRow && cellDatas_data != null) {
									cellDatas_data.add(data);
								}								
								
								break;
								
							case BOOLEAN:	
							case STRING:
								data = cell.getStringCellValue();
								
								if(rownum == -1) {
									headerNames_data.add(cell.getStringCellValue());
								} else if(rownum == columnRow) {
									if(listColNames == null && dbColumnId_data != null) {
										dbColumnId_data.add(cell.getStringCellValue());
									}
								} else if(rownum >= startDataRow && cellDatas_data != null) {
									cellDatas_data.add(cell.getStringCellValue());
								}
								break;
							default:
								cell.setCellType(CellType.STRING);
								data = cell.getStringCellValue();
								
								if(rownum == -1) {
									headerNames_data.add(cell.getStringCellValue());
								} else if(rownum == columnRow) {
									if(listColNames == null && dbColumnId_data != null) {
										dbColumnId_data.add(cell.getStringCellValue());
									}
								} else if(rownum >= startDataRow && cellDatas_data != null) {
									cellDatas_data.add(cell.getStringCellValue());
								}								
								break;
						}
					} else {
						if(rownum == -1) {
							headerNames_data.add("");
						} else if(rownum == columnRow) {
							if(listColNames == null && dbColumnId_data != null) {
								dbColumnId_data.add("");
							}
						} else if(rownum >= startDataRow && cellDatas_data != null) {
							cellDatas_data.add("");
						}
					}
				}
				if(rownum >= startDataRow) {
					allCellData.add(cellDatas_data);
				}
			}
		}

		return xmlParsingResult(allCellData, dbColumnId_data);
	}

	/**
	 * 
	 * @description : xlsParsing 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	private static ExcelUpload xlsParsing(HSSFWorkbook xlsWorkbook, HSSFSheet xlsSheet, int columnRow, int startDataRow, List listColNames) {

		FormulaEvaluator evaluator = xlsWorkbook.getCreationHelper().createFormulaEvaluator();
		String data = null;
		DecimalFormat df = new DecimalFormat();
		List<List> allCellData = new ArrayList<>();
		List headerNames_data = null;
		List dbColumnId_data = null;
		List cellDatas_data = null;
		int rows = xlsSheet.getPhysicalNumberOfRows();

		for(int rownum = 0; rownum < rows; rownum++) {
			HSSFRow xlsRow = xlsSheet.getRow(rownum);
			if(rownum == -1) {
				headerNames_data = new ArrayList();
			} else if(columnRow == NOT_USE_COLROW_VAL && listColNames != null && rownum != -1 && rownum < startDataRow) {
				if(dbColumnId_data == null)
					dbColumnId_data = (ArrayList<String>)listColNames;
			} else if(columnRow != NOT_USE_COLROW_VAL && rownum == columnRow) {
				if(dbColumnId_data == null)
					dbColumnId_data = new ArrayList();
			} else if(rownum >= startDataRow) {
				cellDatas_data = new ArrayList();
			}
			if(xlsRow != null) {
				int cells = xlsRow.getLastCellNum();
				for(int cellnum = 0; cellnum < cells; cellnum++) {
					HSSFCell cell = xlsRow.getCell(cellnum);

					if(cell != null) {

						switch(cell.getCellType()) {

							case NUMERIC:
								if(DateUtil.isCellDateFormatted(cell)) {
									SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
									data = formatter.format(cell.getDateCellValue());

									if(rownum == -1) {
										headerNames_data.add(data);
									} else if(rownum == columnRow) {
										if(listColNames == null && dbColumnId_data != null) {
											dbColumnId_data.add(data);
										}
									} else if(rownum >= startDataRow && cellDatas_data != null) {
										cellDatas_data.add(data);
									}
								} else {
									double ddata = cell.getNumericCellValue();
									data = df.format(ddata); 
									
									if(rownum == -1) {
										headerNames_data.add(cell.getNumericCellValue());
									} else if(rownum == columnRow) {
										if(listColNames == null && dbColumnId_data != null) {
											dbColumnId_data.add(cell.getNumericCellValue());
										}
									} else if(rownum >= startDataRow && cellDatas_data != null) {
										cellDatas_data.add(cell.getNumericCellValue());
									}
								}

								break;

							case FORMULA:
								if(!"".equals(cell.toString())) {
									if(evaluator.evaluateFormulaCell(cell) == CellType.NUMERIC) {
										double fddata = cell.getNumericCellValue();
										data = df.format(fddata);
									} else if(evaluator.evaluateFormulaCell(cell) == CellType.STRING) {
										data = cell.getStringCellValue();
									} else if(evaluator.evaluateFormulaCell(cell) == CellType.BOOLEAN) {
										boolean fbdata = cell.getBooleanCellValue();
										data = String.valueOf(fbdata);
									}

									if(rownum == -1) {
										headerNames_data.add(data);
									} else if(rownum == columnRow) {
										if(listColNames == null && dbColumnId_data != null) {
											dbColumnId_data.add(data);
										}
									} else if(rownum >= startDataRow && cellDatas_data != null) {
										cellDatas_data.add(data);
									}
								}
								break;

							case STRING:
								if(rownum == -1) {
									headerNames_data.add(cell.getStringCellValue());
								} else if(rownum == columnRow) {
									if(listColNames == null && dbColumnId_data != null) {
										dbColumnId_data.add(cell.getStringCellValue());
									}
								} else if(rownum >= startDataRow && cellDatas_data != null) {
									cellDatas_data.add(cell.getStringCellValue());
								}
								cell.getStringCellValue();
								break;

							case BOOLEAN:
								if(rownum == -1) {
									headerNames_data.add(cell.getBooleanCellValue());
								} else if(rownum == columnRow) {
									if(listColNames == null && dbColumnId_data != null) {
										dbColumnId_data.add(cell.getBooleanCellValue());
									}
								} else if(rownum >= startDataRow && cellDatas_data != null) {
									cellDatas_data.add(cell.getBooleanCellValue());
								}
								break;

							case BLANK:
								if(rownum == -1) {
									headerNames_data.add(cell.getStringCellValue());
								} else if(rownum == columnRow) {
									if(listColNames == null && dbColumnId_data != null) {
										dbColumnId_data.add(cell.getStringCellValue());
									}
								} else if(rownum >= startDataRow && cellDatas_data != null) {
									cellDatas_data.add(cell.getStringCellValue());
								}
								break;

							case ERROR:
								if(rownum == -1) {
									headerNames_data.add(cell.getErrorCellValue());
								} else if(rownum == columnRow) {
									if(listColNames == null && dbColumnId_data != null) {
										dbColumnId_data.add(cell.getErrorCellValue());
									}
								} else if(rownum >= startDataRow && cellDatas_data != null) {
									cellDatas_data.add(cell.getErrorCellValue());
								}

								break;
							default:
								break;
						}
					} else {
						if(rownum == -1) {
							headerNames_data.add("");
						} else if(rownum == columnRow) {
							if(listColNames == null && dbColumnId_data != null) {
								dbColumnId_data.add("");
							}
						} else if(rownum >= startDataRow && cellDatas_data != null) {
							cellDatas_data.add("");
						}
					}
				}
				if(rownum >= startDataRow) {
					allCellData.add(cellDatas_data);
				}
			}
		}

		return xmlParsingResult(allCellData, dbColumnId_data);
	}

	/**
	 * 
	 * @description : xmlParsingResult 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 * 2025.07.11        sunho.kim          컬럼명(헤더) 없을 때 파일 내용 읽도록 추가 
	 */
	private static ExcelUpload xmlParsingResult(List<List> allCellData, List dbColumnIdData){
		ArrayList rowsData = new ArrayList();
		HashMap<String, Object> rowData = null;
		for(int r = 0; r < allCellData.size(); r++) {
			rowData = new HashMap<String, Object>();
			List cdata = allCellData.get(r);
			boolean[] isRowData = new boolean[cdata.size()]; // 해당 로우의 사이즈 정보
			int isRowTrueCnt = 0; // cell의 empty 카운트 수

			if(cdata == null) {
				continue;
			}

			if (dbColumnIdData == null || dbColumnIdData.size() == 0) {
			    //컬럼명(헤더) 없을 때
			    for(int i = 0; i < cdata.size(); i++) {
                    // row 중 Cell 전체가 isEmpty이면 true
                    if(cdata.size() > i) {
                        if (StringUtil.isEmpty(cdata.get(i))) {
                            isRowData[i] = true;
                        } else {
                            isRowData[i] = false;
                        }
                    }
    
                    // ("컬렴명","컬럼Value") 형식
                    rowData.put(String.valueOf(i), (cdata.size() <= i ? "" : cdata.get(i)));
                }
			} else {
    			//컬럼명(헤더) 지정
			    for(int i = 0; dbColumnIdData != null && i < dbColumnIdData.size(); i++) {
    				// row 중 Cell 전체가 isEmpty이면 true
    				if(cdata.size() > i) {
    					if (StringUtil.isEmpty(cdata.get(i))) {
    						isRowData[i] = true;
    					} else {
    						isRowData[i] = false;
    					}
    				}
    
    				// ("컬렴명","컬럼Value") 형식
    				rowData.put((String)dbColumnIdData.get(i), (cdata.size() <= i ? "" : cdata.get(i)));
    			}
			}

			// isRowData 중 Cell empty : true, 아니면 false
			for(int empty = 0; empty < isRowData.length; empty++) {
				if(isRowData[empty] == true) {
					isRowTrueCnt++;
				}
			}

			// 모든 row의 Cell이 빈값일 경우 분기처리
			if(isRowData.length == isRowTrueCnt) {
				break;
			}else {
				rowsData.add(rowData);
			}
		}

		ExcelUpload excelUpload = new ExcelUpload();
		// allData.put("header", headerNames_data);
		excelUpload.setColumnNames(dbColumnIdData);
		excelUpload.setRowsData(rowsData);
		return excelUpload;
	}
	
	// 수식의 결과값 가져오기
	public static String getCellValue(Cell cell, FormulaEvaluator evaluator) {
	    if (cell == null) return "";

	    CellType type = cell.getCellType() == CellType.FORMULA
	            ? evaluator.evaluateFormulaCell(cell)
	            : cell.getCellType();

	    switch (type) {
	        case STRING:
	            return cell.getStringCellValue();
	        case NUMERIC:
	            return DateUtil.isCellDateFormatted(cell)
	                    ? cell.getLocalDateTimeCellValue().toString()
	                    : String.valueOf(cell.getNumericCellValue());
	        case BOOLEAN:
	            return String.valueOf(cell.getBooleanCellValue());
	        default:
	            return "";
	    }
	}
}
