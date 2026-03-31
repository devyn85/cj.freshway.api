package cjfw.wms.ms.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.15
 * @description : 기본LOC 정보 조회 결과 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.15        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@Schema(description = "기본LOC 정보 조회 결과 DTO")
public class MsSkuDcLocChangeResDto extends CommonProcedureDto {
	@Schema(description = "체크 여부", example = "0")
    private String checkYn;

    @Schema(description = "고객사 코드", example = "STORER001")
    private String storerKey;

    @Schema(description = "물류센터 코드", example = "DC001")
    private String dcCode;

    @Schema(description = "존", example = "ZONE01")
    private String zone;

    @Schema(description = "로케이션", example = "LOC001")
    private String loc;

    @Schema(description = "상품 코드", example = "SKU001")
    private String sku;

    @Schema(description = "상품명")
    private String skuName;
    
    @Schema(description = "최초 등록자")
    private String addWho;
    
    @Schema(description = "최초 등록일시")
    private String addDate;

    @Schema(description = "최종 변경자")
    private String editWho;
    
    @Schema(description = "최종 변경일시")
    private String editDate;
    
    @Schema(description = "최종 변경자")
    private String editWhoNm;
    
    @Schema(description = "최초 등록자")
    private String addWhoNm;
    
    @Schema(description = "삭제여부", example = "N")
    private String delYn;
    
    @Schema(description = "소진후중단여부", example = "N")
    private String exhaustionStopYn;
    
    @Schema(description = "현재고수량", example = "1000")
    private BigDecimal qty;
}
