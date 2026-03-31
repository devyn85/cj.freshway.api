package cjfw.core.excel;

import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : ExcelUpload 기능을 구현한 Class
 * 				  엑셀업로드 처리를 위한 모델 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
@Data
public class ExcelUpload {
	private int startRow; // 읽어올 시작 Row
    private List<Map> rowsData; // 엑셀 row 데이터
    private List<String> columnNames; // 컬럼명(DB)
}
