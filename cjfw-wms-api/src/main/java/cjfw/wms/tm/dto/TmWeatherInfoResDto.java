package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System Generated
 * @date : 2025.01.XX
 * @description : 날씨정보 조회 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.XX System Generated      생성 </pre>
 */
@Data
@Schema(description = "날씨정보 조회 응답 DTO")
public class TmWeatherInfoResDto {

    /**기준일자*/
    @Schema(description = "기준일자", example = "20250101")
    private String baseDate;

    /**기준시간*/
    @Schema(description = "기준시간", example = "0600")
    private String baseTime;

    /**예보일자*/
    @Schema(description = "예보일자", example = "20250101")
    private String fcstDate;

    /**예보시간대*/
    @Schema(description = "예보시간대 (06/12/18/24)", example = "06")
    private String timezone;

    /**X좌표*/
    @Schema(description = "X좌표", example = "60")
    private String nx;

    /**Y좌표*/
    @Schema(description = "Y좌표", example = "127")
    private String ny;

    /**행정경계LVL*/
    @Schema(description = "행정경계 LVL", example = "1168010100")
    private String lvl;

    /**행정동코드*/
    @Schema(description = "행정동코드(기상청 링크)", example = "1168010100")
    private String hjdongCd;

    /**행정동코드*/
    @Schema(description = "행정동코드(행정경계)", example = "1168010100")
    private String orgHjdongCd;

    /**하늘상태*/
    @Schema(description = "하늘상태", example = "1")
    private String sky;

    /**1시간 적설량*/
    @Schema(description = "1시간 적설량", example = "0")
    private String sno;

    /**1시간 강수량*/
    @Schema(description = "1시간 강수량", example = "0")
    private String pcp;

    /**강수형태*/
    @Schema(description = "강수형태", example = "0")
    private String pty;

    /**경도*/
    @Schema(description = "경도", example = "126.9780")
    private String lonS100;

    /**위도*/
    @Schema(description = "위도", example = "37.5665")
    private String latS100;

    /**시도명*/
    @Schema(description = "시도명 (행정경계 1단계)", example = "서울특별시")
    private String ctpKorNm;

    /**시군구명*/
    @Schema(description = "시군구명 (행정경계 2단계)", example = "종로구")
    private String sigKorNm;

    /**시군구명*/
    @Schema(description = "행정동명 (행정경계 3단계)", example = "")
    private String hjdongNm;
}

