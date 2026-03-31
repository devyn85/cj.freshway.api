package cjfw.wms.comfunc.func.file.dto;

import lombok.Data;

/**
 * 샘플 파일 조회 Request DTO
 */
@Data
public class SampleFilePageGetReqDto {
    private String fromDt;  // 검색조건 시작일
    private String thruDt; // 검색조건 종료일
}

/**
 * > 요청
 * FROM_DT: "2021-06-20"
 * THRU_DT: "2022-06-20"
 */
