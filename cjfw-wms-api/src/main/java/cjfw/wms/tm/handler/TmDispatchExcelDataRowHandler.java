package cjfw.wms.tm.handler;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ResultContext;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import cjfw.core.dataaccess.largedata.LargeDataHandler;
import cjfw.core.dataaccess.largedata.LargeExcel;
import cjfw.wms.tm.dto.TmDispatchExcelColumn;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : han@wemeetmobility.com
 * @date : 2025.11.27
 * @description : TM 배차 다운로드 Excel 커스텀 Row Handler (TmDispatchListDownloadDto 직접 사용)
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.27 han@wemeetmobility.com 생성
 * </pre>
 */
@Slf4j
public class TmDispatchExcelDataRowHandler<T> implements LargeDataHandler {

    private final TmDispatchExcelColumn[] columns;
    private final Map<Integer, CellStyle> columnStyleMap;
    private final SXSSFSheet sheet;
    private int rowNum = 1; // 헤더는 0번 행
    private int resultCnt = -1;

    /**
     * 생성자
     */
    public TmDispatchExcelDataRowHandler(LargeExcel largeExcel) {
        this.columns = TmDispatchExcelColumn.values();
        this.sheet = largeExcel.getSxssSheet();

        // Workbook 가져오기 (스타일 생성용)
        SXSSFWorkbook workbook = sheet.getWorkbook();

        // 컬럼별 스타일 생성
        this.columnStyleMap = createColumnStyles(workbook);

        log.info("TmDispatchExcelDataRowHandler 초기화 완료 - 컬럼 수: {}", columns.length);
    }

    /**
     * 컬럼별 스타일 생성 (enum의 align, type 정보 활용)
     */
    private Map<Integer, CellStyle> createColumnStyles(SXSSFWorkbook workbook) {
        Map<Integer, CellStyle> styleMap = new HashMap<>();

        for (int i = 0; i < columns.length; i++) {
            TmDispatchExcelColumn col = columns[i];

            CellStyle style = workbook.createCellStyle();

            // 테두리 설정
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);

            // 수직 정렬
            style.setVerticalAlignment(VerticalAlignment.CENTER);

            // 수평 정렬 (enum의 align 정보 활용)
            switch (col.getAlign()) {
                case LEFT -> style.setAlignment(HorizontalAlignment.LEFT);
                case CENTER -> style.setAlignment(HorizontalAlignment.CENTER);
                case RIGHT -> style.setAlignment(HorizontalAlignment.RIGHT);
            }

            // 천단위 구분기호 (숫자 컬럼)
            if (col.isUseThousandsSeparator()) {
                DataFormat format = workbook.createDataFormat();
                style.setDataFormat(format.getFormat("#,##0.00"));
            }

            styleMap.put(i, style);
        }

        log.info("컬럼 스타일 생성 완료 - {} 개", styleMap.size());
        return styleMap;
    }

    /**
     * MyBatis ResultHandler - 각 row마다 호출됨
     */
    @Override
    public void handleResult(ResultContext resultContext) {
        @SuppressWarnings("unchecked")
        T dataMap = (T) resultContext.getResultObject();

        // Row 생성
        SXSSFRow row = sheet.createRow(rowNum++);

        // 각 컬럼별 Cell 생성 및 값 설정
        for (int i = 0; i < columns.length; i++) {
            TmDispatchExcelColumn col = columns[i];

            // Cell 생성
            Cell cell = row.createCell(i);

            // 스타일 적용 (정렬 포함)
            cell.setCellStyle(columnStyleMap.get(i));

            // NO 컬럼은 행 번호 자동 설정
            if (col == TmDispatchExcelColumn.NO) {
                cell.setCellValue(rowNum - 1); // rowNum은 1부터 시작하므로 -1
            } else {
                // DTO에서 필드 값 가져오기
                Object value = getFieldValue(dataMap, col.getColumnName());
                // 값 설정
                setCellValue(cell, value, col);
            }
        }

        this.resultCnt = resultContext.getResultCount();
    }

    /**
     * Cell에 값 설정 (타입별 처리)
     */
    private void setCellValue(Cell cell, Object value, TmDispatchExcelColumn col) {
        if (value == null) {
            cell.setCellValue("");
            return;
        }

        // 숫자 타입 처리
        if (value instanceof Number) {
            cell.setCellValue(((Number) value).doubleValue());
        }
        // 문자열 타입 처리
        else if (value instanceof String) {
            cell.setCellValue(StringUtils.defaultString((String) value, ""));
        }
        // 기타 타입
        else {
            cell.setCellValue(String.valueOf(value));
        }
    }

    /**
     * 리플렉션으로 DTO 필드 값 가져오기
     */
    private Object getFieldValue(Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (NoSuchFieldException e) {
            log.warn("필드를 찾을 수 없음 - fieldName: {}", fieldName);
            return null;
        } catch (IllegalAccessException e) {
            log.warn("필드 접근 실패 - fieldName: {}, error: {}", fieldName, e.getMessage());
            return null;
        }
    }

    @Override
    public int getResultCnt() {
        return resultCnt;
    }

    @Override
    public void close() {
        // Do nothing
    }
}
