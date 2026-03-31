package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "수발주 정보 팝업 응답 DTO")
public class MsPurchaseCustPopupResDto {
	@Schema(description = "데이터번호", example = "100070")
    private Long serialKey;

    @Schema(description = "센터코드", example = "2650")
    private String dcCode;

    @Schema(description = "고객사", example = "FW00")
    private String storerKey;

    @Schema(description = "거래처유형", example = "D")
    private String custType;

    @Schema(description = "상품코드", example = "100070")
    private String sku;

    @Schema(description = "상품명", example = "백설 밀가루")
    private String description;

    @Schema(description = "플랜트코드", example = "2651")
    private String plant;

    @Schema(description = "플랜트", example = "[2651]동탄물류센터")
    private String plantDescr;
    
    @Schema(description = "저장구분", example = "1")
    private String channel;

    @Schema(description = "시작일자", example = "2024-06-01")
    private String fromDate;
    
    @Schema(description = "종료일자", example = "2024-06-30")
    private String toDate;

    @Schema(description = "구매담당코드", example = "BUYER01")
    private String custKey;
    
    @Schema(description = "구매담당자코드", example = "BUYER01")
    private String buyerKey;
    
    @Schema(description = "실공급센터", example = "")
    private String route;

    @Schema(description = "경유지조직", example = "1000-1111")
    private String routeOrganize;
    
    @Schema(description = "기본단위", example = "EA")
    private String uom;
}
