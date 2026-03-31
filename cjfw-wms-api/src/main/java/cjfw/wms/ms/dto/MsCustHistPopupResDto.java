package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.07.29 
 * @description : 이력정보 상세정보 팝업 조회 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.19 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "이력정보 상세정보 팝업 조회 응답 DTO")
public class MsCustHistPopupResDto {
    @Schema(description = "일자", example = "2025-07-29")
    private String histDate;

    @Schema(description = "상품코드", example = "100021")
    private String sku;

    @Schema(description = "상품명", example = "냉동만두")
    private String description;
    
    @Schema(description = "저장조건", example = "10")
    private String storagetype;

    @Schema(description = "기본단위 (BOX로 변환)")
    private String baseUom;

    @Schema(description = "길이 (mm 단위)", example = "120")
    private Integer length;

    @Schema(description = "너비 (mm 단위)", example = "80")
    private Integer width;

    @Schema(description = "높이 (mm 단위)", example = "60")
    private Integer height;
}
