package cjfw.batch.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.StringReader;
import java.time.LocalDateTime;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : SYSTEM
 * @date : 2025.01.17
 * @description : IF_MS_HJDONG_POLYGON 테이블 DTO (배치용)
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.01.17 SYSTEM                생성 </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "IF_MS_HJDONG_POLYGON 테이블 DTO")
public class IfMsHjdongPolygonDto {

    @Schema(description = "시리얼키")
    private Long serialkey;

    @Schema(description = "행정동코드", example = "1168010100")
    private String hjdongCd;

    @Schema(description = "행정동명", example = "역삼동")
    private String hjdongNm;

    @Schema(description = "시군구 한글명", example = "강남구")
    private String sigKorNm;

    @Schema(description = "시도 한글명", example = "서울특별시")
    private String ctpKorNm;

    @Schema(description = "권역 폴리곤 (WKT 형식)")
    private StringReader spatialGeom;

    @Schema(description = "권역 폴리곤 (String 형식)")
    private String spatialGeomStr;

    @Schema(description = "리전ID")
    private String regionId;

    @Schema(description = "부모ID")
    private String parentId;

    @Schema(description = "인터페이스 ID")
    private String ifId;

    @Schema(description = "인터페이스 플래그", defaultValue = "N")
    @Builder.Default
    private String ifFlag = "N";

    @Schema(description = "인터페이스 일자")
    private LocalDateTime ifDate;

    @Schema(description = "인터페이스 타임스탬프")
    private LocalDateTime ifTimestamp;
}

