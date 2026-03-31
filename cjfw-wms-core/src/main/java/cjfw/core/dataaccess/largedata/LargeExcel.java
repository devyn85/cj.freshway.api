package cjfw.core.dataaccess.largedata;

import lombok.Data;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;

import cjfw.core.model.UserContext;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : LargeExcel 기능을 구현한 Class
 * 				  대량엑셀처리를 위한 모델 Object
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
@Data
public class LargeExcel {
    private String[] headerColumns; // 엑셀 헤더 컬럼
    private String[] dataColumns; // Data Mapping Colum
    private String excelFileName; // 엑셀파일명
    private String excelSheetName; // 엑셀 시트 명
    private SXSSFSheet sxssSheet; // 엑셀 Sheet 객체
    private SXSSFRow sxssRow; // 엑셀 Row 객체
    private UserContext userContext; // DRM처리용 사용자ID/사용자명
}
