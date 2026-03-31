package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.08 
 * @description : 자동분배-상품별 상세 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.08 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "자동분배-상품별 상세 결과")
public class WdAllocationBatchGroupResTab1SkuDto extends CommonDto{
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	@Schema(description = "상품코드", example = "SKU001")
    private String sku;
    @Schema(description = "SKU명", example = "베이직 티셔츠")
    private String skuname;
    @Schema(description = "OMS미마감건수", example = "10")
    private String omsFlag;
    @Schema(description = "전표수", example = "5")
    private String slipcnt;
    @Schema(description = "상품수", example = "3")
    private String skucnt;
    @Schema(description = "주문량", example = "100")
    private BigDecimal orderqty;
    @Schema(description = "분배량", example = "90")
    private BigDecimal processqty;
    @Schema(description = "피킹량", example = "80")
    private BigDecimal workqty;
    @Schema(description = "상차스캔량", example = "70")
    private BigDecimal inspectqty;
    @Schema(description = "출고확정량", example = "60")
    private BigDecimal confirmqty;
    @Schema(description = "WORKPROCESSCODE", example = "WP001")
    private String workprocesscode;
    @Schema(description = "DC코드", example = "DC001")
    private String dccode;
    @Schema(description = "화주키", example = "STR001")
    private String storerkey;
    @Schema(description = "UOM", example = "EA")
    private String uom;
    @Schema(description = "OPEN수량", example = "20")
    private BigDecimal openqty;
    @Schema(description = "플랜트", example = "PLANT01")
    private String plant;
    @Schema(description = "저장유형", example = "TYPEA")
    private String storagetype;
    @Schema(description = "거리유형", example = "DIST01")
    private String distancetype;
    @Schema(description = "배치그룹", example = "BATCH01")
    private String batchgroup;
    @Schema(description = "전표일자", example = "2025-07-08")
    private String slipdt;
    @Schema(description = "SERIALYN", example = "Y")
    private String serialyn;
}
