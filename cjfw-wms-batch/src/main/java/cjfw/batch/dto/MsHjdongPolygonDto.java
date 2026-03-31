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
 * @description : 행정동 폴리곤 정보 DTO (배치용)
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
@Schema(description = "행정동 폴리곤 정보 DTO")
public class MsHjdongPolygonDto {

    @Schema(description = "행정동코드", example = "1168010100")
    private String hjdongCd;

    @Schema(description = "행정동명", example = "역삼동")
    private String hjdongNm;

    @Schema(description = "권역 폴리곤 (WKT 형식)")
    private StringReader spatialGeom;

    @Schema(description = "권역 폴리곤 (String 형식)")
    private String spatialGeomStr;

    @Schema(description = "권역 폴리곤 (String 형식)")
    private String geojson;

    @Schema(description = "시도 한글명", example = "서울특별시")
    private String ctpKorNm;

    @Schema(description = "시군구 한글명", example = "강남구")
    private String sigKorNm;

    @Schema(description = "삭제 여부", defaultValue = "N", maxLength = 1, requiredMode = Schema.RequiredMode.REQUIRED)
    @Builder.Default
    private String delYn = "N";

    @Schema(description = "유효 시작일", example = "20250701", maxLength = 8)
    private String fromDate;

    @Schema(description = "유효 종료일", example = "99991231", maxLength = 8)
    private String toDate;

    @Schema(description = "최초등록시간 (자동 생성)", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime addDate;

    @Schema(description = "최종변경시간 (자동 갱신)", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime editDate;

    @Schema(description = "최초등록자", defaultValue = "SYSTEM", maxLength = 24, requiredMode = Schema.RequiredMode.REQUIRED)
    @Builder.Default
    private String addWho = "SYSTEM";

    @Schema(description = "최종변경자", defaultValue = "SYSTEM", maxLength = 24, requiredMode = Schema.RequiredMode.REQUIRED)
    @Builder.Default
    private String editWho = "SYSTEM";

    @Schema(description = "이동사유")
    private String mvmnResCd;

}
