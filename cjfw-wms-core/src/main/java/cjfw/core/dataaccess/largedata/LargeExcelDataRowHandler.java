package cjfw.core.dataaccess.largedata;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ResultContext;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;

import cjfw.core.exception.UserHandleException;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : LargeExcelDataRowHandler 기능을 구현한 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
@Slf4j
public class LargeExcelDataRowHandler<T> implements LargeDataHandler {

	private static final String MSG_COM_ERR_999 = "MSG.COM.ERR.999";
	private String[] mappingKey;
	private SXSSFSheet objSheet = null;
	private SXSSFRow objRow = null;
	private int rowNum = 1; //row 카운트
	private int resultCnt = -1;

	/**
	 * 
	 * @description : LargeExcelDataRowHandler의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public LargeExcelDataRowHandler(LargeExcel largeExcel) {

		if(null == largeExcel.getDataColumns()){
			throw new UserHandleException(MSG_COM_ERR_999, new String[]{"DATA_COLUMN 미설정 오류"});
		}

		if(null == largeExcel.getSxssSheet()){
			throw new UserHandleException(MSG_COM_ERR_999, new String[]{"SXSSF_SHEET 미설정 오류"});
		}

		if(null == largeExcel.getSxssRow()){
			throw new UserHandleException(MSG_COM_ERR_999, new String[]{"SXSSF_ROW 미설정 오류"});
		}

		this.mappingKey = largeExcel.getDataColumns();
		this.objSheet = largeExcel.getSxssSheet();
		this.objRow = largeExcel.getSxssRow();
	}

	/**
	 * 
	 * @overridden  : @see cjfw.core.dataaccess.largedata.LargeDataHandler#close()
	 * @description : close 기능을 Override하여 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	@Override
	public void close() {
		// Do nothing
	}

	/**
	 * 
	 * @overridden  : @see org.apache.ibatis.session.ResultHandler#handleResult(org.apache.ibatis.session.ResultContext)
	 * @description : handleResult 기능을 Override하여 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	@Override
	public void handleResult(ResultContext resultContext) {

		T dataMap = (T)resultContext.getResultObject();

		this.objRow = objSheet.createRow(rowNum++);

		int k = 0;
		for(String key : this.mappingKey){
			Cell cell = this.objRow.createCell(k);
			// Object dataColumn = dataMap.get(key);
			Object dataColumn = getObejctValue(dataMap, key);
			if(dataColumn instanceof String){
				cell.setCellValue(org.apache.commons.lang3.StringUtils.defaultString((String) dataColumn, ""));
			} else {
				if(null == dataColumn){
					cell.setCellValue("");
				} else {
					cell.setCellValue(org.apache.commons.lang3.StringUtils.defaultIfBlank(String.valueOf(dataColumn), ""));
				}
			}
			k++;
		}

		this.resultCnt = resultContext.getResultCount();
	}

	/**
	 * 
	 * @overridden  : @see cjfw.core.dataaccess.largedata.LargeDataHandler#getResultCnt()
	 * @description : getResultCnt 기능을 Override하여 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	@Override
	public int getResultCnt() {
		return resultCnt;
	}

	/**
	 * 
	 * @description : getObejctValue 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private Object getObejctValue(T dataMap, String key) {
		Object result = null;
		try {
			Method method = dataMap.getClass().getDeclaredMethod("get" + StringUtils.capitalize(key));
			result = method.invoke(dataMap);
		} catch (NoSuchMethodException e) {
			log.error("LargeExcelDataRowHandler.getObejctValue.NoSuchMethodException : ", e);
		} catch (InvocationTargetException e) {
			log.error("LargeExcelDataRowHandler.getObejctValue.InvocationTargetException : ", e);
		} catch (IllegalAccessException e) {
			log.error("LargeExcelDataRowHandler.getObejctValue.IllegalAccessException : ", e);
		}
		return result;
	}
}
