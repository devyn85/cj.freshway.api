package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved. 
 *
 * @author : ParkJinWoo 
 * @date : 2025.06.04 
 * @description : 외부창고요율관리 저장 요청 DTO
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.04 ParkJinWoo 생성
 */
@Data
@Schema(description = "외부창고요율관리 저장 요청 DTO")
public class MsExDcRateSaveResDto extends CommonDto{


 
	@Schema(description = "상품명")
    private String skuName;

    @Schema(description = "센터명")
    private String dcName;

    @Schema(description = "창고명")
    private String organizeNm;

    @Schema(description = "고객명")
    private String custName;

    @Schema(description = "등록일시")
    private String addDate;

    @Schema(description = "수정일시")
    private String editDate;

    @Schema(description = "등록자 ID")
    private String addWho;

    @Schema(description = "수정자 ID")
    private String editWho;

    @Schema(description = "실중량")
    private Integer netWeight;

    @Schema(description = "박스입수")
    private Integer qtyPerBox;

    @Schema(description = "저장조건(SKU)")
    private String storageTypeSku;

    @Schema(description = "작업비")
    private String workPrice;

    // --- 환산 필드 ---
    private Integer grPriceUpperTrans;
    private Integer giPriceUpperTrans;
    private Integer storagePriceUpperTrans;
    private Integer stoGrPriceUpperTrans;
    private Integer stoGiPriceUpperTrans;

    private Integer grPriceLowerTrans;
    private Integer giPriceLowerTrans;
    private Integer storagePriceLowerTrans;
    private Integer stoGrPriceLowerTrans;
    private Integer stoGiPriceLowerTrans;

    private String status;
    private String delYn;
}
