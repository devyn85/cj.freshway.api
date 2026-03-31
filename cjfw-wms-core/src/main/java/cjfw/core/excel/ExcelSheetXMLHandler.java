package cjfw.core.excel;

import com.github.pagehelper.util.StringUtil;

import cjfw.core.exception.UserHandleException;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFReader.SheetIterator;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : ExcelSheetXMLHandler 기능을 구현한 Class
 * 				  String type의 value를 위한 ExcelSheet SaxParser 적용 핸들러
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
@Slf4j
public class ExcelSheetXMLHandler implements XSSFSheetXMLHandler.SheetContentsHandler {

    private int currentCol = -1;
//    private int currRowNum = 0;
    private int columnRow = 0;
    private int startRow = 1;
    private String sheetStr = "rId";
    private List<List<String>> allCellData = new ArrayList<List<String>>();   // row데이터의 리스트
    private List<String> cellData = new ArrayList<String>();
    private List<String> columnData = new ArrayList<String>();

    /**
     * 
     * @description : ExcelSheetXMLHandler의 생성자
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public ExcelSheetXMLHandler(File file, int columnRow, int startRow, List listColNames, int sheetNum) {
        this.columnRow = columnRow;
        this.startRow = startRow;
        this.columnData = listColNames;

        try {
            OPCPackage opc = OPCPackage.open(file);
            XSSFReader xssfReader = new XSSFReader(opc);
            StylesTable styles = xssfReader.getStylesTable();
            ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(opc);

            //선택한 시트번호가 있는지 없는지 체크하는 로직 추가
            SheetIterator iter = (SheetIterator) xssfReader.getSheetsData();
            int totalSheetNum = 0;
            while (iter.hasNext()) {
                iter.next();
                totalSheetNum++;
            }

            if(sheetNum+1 > totalSheetNum) {
                throw new UserHandleException("NOTEXIST_SHEET"); // 선택한 시트번호가 없을 경우 throw
            }

            /* rId는 SAX 메소드에서 id값으로 세팅되어 있음(고정값)
             * 선택한 시트 데이터 세팅 -> rId1 : 1번시트, rId2 : 2번시트 ..... rId10 : 10번 시트
             */
            InputStream inputStream = xssfReader.getSheet(sheetStr +(sheetNum+1));

            InputSource inputSource = new InputSource(inputStream);
            ContentHandler handle = new XSSFSheetXMLHandler(styles, strings, this, false);

            XMLReader xmlReader = XMLHelper.newXMLReader();
            xmlReader.setContentHandler(handle);

            xmlReader.parse(inputSource);
            inputStream.close();
            opc.close();
        } catch (Exception e) {
            log.error("ExcelSheetXMLHandler.readExcel.Exception : " , e);
        }
    }

    /**
     * 
     * @description : getRows 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public List<List<String>> getRows() {
        return allCellData;
    }

    /**
     * 
     * @description : getHeader 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public List<String> getHeader() {
        return columnData;
    }

    /**
     * 
     * @description : getParsingData 기능을 구현한 Method
     * 				 Parsing 결과 조회 기능
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public ExcelUpload getParsingData(){
        HashMap<String, Object> allData = new HashMap<String, Object>();
        allData.put("columnName", columnData);

        ArrayList rowsData = new ArrayList();
        for(int r = 0; r < allCellData.size(); r++) {
            Map rowData = new HashMap<String, String>();
            List<String> cdata = allCellData.get(r);
            if(cdata == null) {
                continue;
            }
            boolean[] isRowData = new boolean[cdata.size()]; // 해당 로우의 사이즈 정보
            int isRowTrueCnt = 0; // cell의 empty 카운트 수

            for(int i = 0; columnData != null && i < columnData.size(); i++) {

                // row 중 Cell 전체가 isEmpty이면 true
                if(StringUtil.isEmpty(cdata.get(i))) {
                    isRowData[i] = true;
                } else {
                    isRowData[i] = false;
                }

            	/*
            	 * 해당 Cell의 정보가 날짜 및 숫자 타입일 때 해당 프로젝트 별로 dataField에 맞게 로직 추가 필요
            	 ex) 2022-04-01 -> 4/1/22
            	 if(columnData.get(i).get("dataField").indexOf("/") > -1){

            	 }
            	 *
            	 */

                // ("컬렴명","컬럼Value") 형식
                rowData.put((String)columnData.get(i), (cdata.size() <= i ? "" : cdata.get(i)));

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
        excelUpload.setColumnNames(columnData);
        excelUpload.setRowsData(rowsData);
        return excelUpload;
    }

    /**
     * 
     * @overridden  : @see org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler#startRow(int)
     * @description : startRow 기능을 Override하여 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    @Override
    public void startRow(int arg0) {
        this.currentCol = -1;
//        this.currRowNum = arg0;
    }

    /**
     * 
     * @overridden  : @see org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler#cell(java.lang.String, java.lang.String, org.apache.poi.xssf.usermodel.XSSFComment)
     * @description : cell 기능을 Override하여 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    @Override
    public void cell(String columnName, String value, XSSFComment var3) {
        int iCol = (new CellReference(columnName)).getCol();
        int emptyCol = iCol - currentCol - 1;

        for (int i = 0; i < emptyCol; i++) {
            cellData.add("");
        }
        currentCol = iCol;
        cellData.add(value);
    }

    /**
     * 
     * @overridden  : @see org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler#headerFooter(java.lang.String, boolean, java.lang.String)
     * @description : headerFooter 기능을 Override하여 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    @Override
    public void headerFooter(String arg0, boolean arg1, String arg2) {
        //사용 X
    }

    /**
     * 
     * @overridden  : @see org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler#endRow(int)
     * @description : endRow 기능을 Override하여 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    @Override
    public void endRow(int rowNum) {
        if (rowNum == columnRow) {
            columnData = new ArrayList(cellData);
        } else if(rowNum >= startRow) {
            if (cellData.size() < columnData.size()) {
                for (int i = cellData.size(); i < columnData.size(); i++) {
                    cellData.add("");
                }
            }
            allCellData.add(new ArrayList(cellData));
        }
        cellData.clear();
    }

    /**
     * 
     * @description : hyperlinkCell 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.17        sungyeon.lee       생성
     */
    public void hyperlinkCell(String arg0, String arg1, String arg2, String arg3, XSSFComment arg4) {
        // TODO Auto-generated method stub
    }
}
