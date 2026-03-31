package cjfw.wms.tm.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import cjfw.core.excel.ExcelUpload;
import cjfw.core.excel.ExcelUploader;
import lombok.Getter;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : han@wemeetmobility.com
 * @date : 2025.11.27
 * @description : TM 배차 Excel 전체 DTO (업로드/다운로드 통합)
 */
@Getter
public class TmDispatchExcelDto {

    /**
     * 데이터 시작 행 번호 (2행부터 데이터)
     */
    public static final int DATA_START_ROW_INDEX = 1;

    /**
     * 행 데이터 (DTO 형태)
     */
    private final List<TmDispatchRowDto> rows;

    /**
     * 총 건수
     */
    private final int totalCount;

    /**
     * 유효하지 않은 행 목록
     */
    private final List<TmDispatchRowDto> invalidRows;

    
    private TmDispatchExcelDto(List<TmDispatchRowDto> rows) {
        this.rows = rows;
        this.totalCount = rows.size();
        invalidRows = new ArrayList<>();
         
    }
    /**
     * private 생성자
     */
    private TmDispatchExcelDto(List<TmDispatchRowDto> rows, int totalCount, List<TmDispatchRowDto> invalidRows) {
        this.rows = rows;
        this.totalCount = totalCount;
        // invalidRows를 mutable ArrayList로 변환 (검증 오류 추가를 위해)
        this.invalidRows = new ArrayList<>(invalidRows);
    }

    /**
     * MultipartFile로부터 생성
     *
     * @param file 업로드된 엑셀 파일
     * @return TmDispatchExcelDto
     */
    public static TmDispatchExcelDto from(MultipartFile file) {
        // 엑셀 컬럼명 정의 (columnIndex 순서대로 정렬)
        List<String> excelColumnNames = Arrays.stream(TmDispatchExcelColumn.values())
            .sorted(Comparator.comparingInt(TmDispatchExcelColumn::getColumnIndex))
            .map(TmDispatchExcelColumn::getColumnName)
            .collect(Collectors.toList());

        ExcelUpload excelUpload = ExcelUploader.uploadExcel(file, excelColumnNames, DATA_START_ROW_INDEX);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rawData = (List<Map<String, Object>>) (List<?>) excelUpload.getRowsData();

        // rawData를 순회하면서 RowDto로 변환
        List<TmDispatchRowDto> rows = new ArrayList<>();
        for (int i = 0; i < rawData.size(); i++) {
            int rowNumber = i + DATA_START_ROW_INDEX; // 순서 번호 (1부터 시작)
            rows.add(TmDispatchRowDto.from(rawData.get(i), rowNumber));
        }

        // 유효하지 않은 행 목록 추출 (invalidCells가 비어있지 않은 행)
        List<TmDispatchRowDto> invalidRows = rows.stream()
            .filter(row -> !row.getInvalidCells().isEmpty())
            .collect(Collectors.toList());

        return new TmDispatchExcelDto(rows, rawData.size(), invalidRows);
    }

    /**
     * 데이터가 비어있는지 확인
     *
     * @return 비어있으면 true
     */
    public boolean isNoContent() {
        return totalCount == 0;
    }

    /**
     * 유효하지 않은 행이 존재하는지 확인
     *
     * @return 유효하지 않은 행이 있으면 true
     */
    public boolean hasInvalidRows() {
        return !invalidRows.isEmpty();
    }

    /**
     * 검증 오류 추가
     *
     * @param rowNumber 행 번호
     * @param columnName 컬럼명 (한글)
     * @param errorMessage 오류 메시지
     */
    public void addValidationError(int rowNumber, String columnName, String errorMessage) {
        // 1. rowNumber로 해당 row 찾기
        TmDispatchRowDto targetRow = rows.stream()
            .filter(row -> row.getRowNumber().equals(rowNumber))
            .findFirst()
            .orElse(null);

        if (targetRow == null) {
            return;
        }

        // 2. row에 오류 셀 추가
        targetRow.addErrorCell(columnName, errorMessage);

        // 3. invalidRows 리스트에 추가 (중복 방지)
        if (!invalidRows.contains(targetRow)) {
            invalidRows.add(targetRow);
        }
    }
}
