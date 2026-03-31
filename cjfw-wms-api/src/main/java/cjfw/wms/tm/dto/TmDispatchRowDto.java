package cjfw.wms.tm.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : han@wemeetmobility.com
 * @date : 2025.11.27
 * @description : TM 배차 Excel 행 DTO (업로드/다운로드 통합)
 */
@Data
@NoArgsConstructor
public class TmDispatchRowDto {

    /**
     * 행 번호 (엑셀 기준, 2부터 시작)
     */
    private Integer rowNumber;

    /**
     * 셀 정보 목록 (Row = List<Cell>)
     */
    private List<TmDispatchCellDto> cells;

    /**
     * 유효하지 않은 셀 목록
     */
    private List<TmDispatchCellDto> invalidCells;

    /**
     * 생성자
     *
     * @param rowNumber 행 번호
     * @param cells 셀 정보 목록
     */
    public TmDispatchRowDto(Integer rowNumber, List<TmDispatchCellDto> cells) {
        this.rowNumber = rowNumber;
        this.cells = cells;
        // 유효하지 않은 셀 목록 생성 (mutable ArrayList로 생성하여 나중에 추가 가능)
        this.invalidCells = cells.stream()
            .filter(cell -> !cell.isValid())
            .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Map 데이터로부터 DTO 생성 (업로드용)
     *
     * @param rowData 행 데이터 Map
     * @param rowNumber 행 번호 (엑셀 기준, 2부터 시작)
     * @return TmDispatchRowDto
     */
    public static TmDispatchRowDto from(Map<String, Object> rowData, int rowNumber) {
        // 모든 컬럼을 순회하면서 Cell 생성 (Cell 내부에서 유효성 검증)
        List<TmDispatchCellDto> cells = Arrays.stream(TmDispatchExcelColumn.values())
            .map(col -> TmDispatchCellDto.from(col, rowData.get(col.getColumnName())))
            .collect(Collectors.toList());

        return new TmDispatchRowDto(rowNumber, cells);
    }

    /**
     * 오류 셀 추가 (검증 실패 시)
     *
     * @param columnName 컬럼명 (한글)
     * @param errorMessage 오류 메시지
     */
    public void addErrorCell(String columnName, String errorMessage) {
        // 컬럼명으로 해당 컬럼 찾기
        TmDispatchExcelColumn column = Arrays.stream(TmDispatchExcelColumn.values())
            .filter(col -> col.getKoreanName().equals(columnName))
            .findFirst()
            .orElse(null);

        if (column == null) {
            return;
        }

        // 기존 셀에서 값 찾기
        Object cellValue = cells.stream()
            .filter(cell -> cell.getColumn().equals(column))
            .findFirst()
            .map(TmDispatchCellDto::getValue)
            .orElse(null);

        // 오류 셀 생성
        TmDispatchCellDto errorCell = TmDispatchCellDto.error(column, cellValue, errorMessage);

        // invalidCells에 추가 (중복 방지 - 같은 컬럼에 이미 오류가 있으면 추가하지 않음)
        boolean alreadyExists = invalidCells.stream()
            .anyMatch(cell -> cell.getColumn().equals(column));

        if (!alreadyExists) {
            invalidCells.add(errorCell);
        }
    }
}
