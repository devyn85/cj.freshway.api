package cjfw.wms.tm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : han@wemeetmobility.com
 * @date : 2025.11.27
 * @description : TM 배차 Excel 셀 DTO (업로드/다운로드 통합)
 */
@Getter
@ToString
@AllArgsConstructor
public class TmDispatchCellDto {

    /**
     * 컬럼 정의
     */
    private final TmDispatchExcelColumn column;

    /**
     * 셀 값
     */
    private final Object value;

    /**
     * 오류 메시지
     */
    private final String errorMessage;

    /**
     * 정상 셀 생성
     */
    public static TmDispatchCellDto of(TmDispatchExcelColumn column, Object value) {
        return new TmDispatchCellDto(column, value, null);
    }

    /**
     * 에러 셀 생성
     */
    public static TmDispatchCellDto error(
        TmDispatchExcelColumn column,
        Object value,
        String errorMessage
    ) {
        return new TmDispatchCellDto(column, value, errorMessage);
    }

    /**
     * 유효성 확인
     */
    public boolean isValid() {
        return errorMessage == null;
    }

    /**
     * 에러 이유 (errorMessage의 별칭)
     */
    public String getErrorReason() {
        return errorMessage;
    }

    /**
     * Excel 파싱 시 셀 생성 (required 검증 및 타입 검증 포함)
     */
    public static TmDispatchCellDto from(TmDispatchExcelColumn column, Object rawValue) {
        // required 검증
        if (column.isRequired()) {
            if (rawValue == null || (rawValue instanceof String && ((String) rawValue).trim().isEmpty())) {
                return TmDispatchCellDto.error(column, rawValue, "필수 입력 항목입니다");
            }
        }

        return TmDispatchCellDto.of(column, rawValue);
    }
}
