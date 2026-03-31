package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Schema(description = "수발주 정보 팝업 요청 DTO")
public class MsPurchaseCustPopupReqDto extends CommonDto {
	@Schema(description = "센터코드", example = "2650")
    private String dcCode;

    @Schema(description = "데이터번호", example = "2398430,2398433,2398446,2398447,2398795,2398808,2399165,2399166,2399179,2399181")
    private String serialKey;

    @Schema(description = "거래처유형", example = "D")
    private String custType;

    @Schema(description = "상품코드", example = "100070")
    private String sku;

    @Schema(description = "시작일자 (YYYY-MM-DD)", example = "2024-06-01")
    private String fromDate;
    
    @Schema(description = "종료일자 (YYYY-MM-DD)", example = "9999-12-31")
    private String toDate;

    @Schema(description = "플랜트코드", example = "2651")
    private String plant;

    @Schema(description = "실공급센터", example = "1000179")
    private String custKey;

    @Schema(description = "경유지", example = "ROUTE01")
    private String route;

    @Schema(description = "경유지조직", example = "1000-1111")
    private String routeOrganize;
    
    private String uom;

    @Schema(description = "구매담당코드", example = "BUYER01")
    private String buyerKey;
    
    @Schema(description = "최초등록자", example = "admin")
    private String addWho;

    @Schema(description = "최종변경자", example = "admin")
    private String editWho;
}
