package cjfw.wms.ms.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.08.19 
 * @description : 행정동 폴리곤 테이블 엔티티
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.19 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */

@Data
public class MsHjdongPolygonEntity {

    @Schema(description = "행정동코드")
    private String hjdongCd;

    @Schema(description = "행정동명")
    private String hjdongNm;

    @Schema(description = "권역 폴리곤")
    private String spatialGeom;

    @Schema(description = "시도 한글명")
    private String ctpKorNm;

    @Schema(description = "시군구 한글명")
    private String sigKorNm;

    @Schema(description = "삭제 여부", defaultValue = "N", maxLength = 1, requiredMode = Schema.RequiredMode.REQUIRED)
    private String delYn = "N";

    @Schema(description = "유효 시작일", example = "20250701", maxLength = 8)
    private String fromDate;

    @Schema(description = "유효 종료일", example = "99991231", maxLength = 8)
    private String toDate;

    @Schema(description = "최초등록시간 (자동 생성)", accessMode = Schema.AccessMode.READ_ONLY)
    private String addDate;

    @Schema(description = "최종변경시간 (자동 갱신)", accessMode = Schema.AccessMode.READ_ONLY)
    private String editDate;

    @Schema(description = "최초등록자", defaultValue = "SYSTEM", maxLength = 24, requiredMode = Schema.RequiredMode.REQUIRED)
    private String addWho;

    @Schema(description = "최종변경자", defaultValue = "SYSTEM", maxLength = 24, requiredMode = Schema.RequiredMode.REQUIRED)
    private String editWho;
}
