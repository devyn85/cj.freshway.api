package cjfw.wms.kp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.11.20
 * @description : 이체및출고현황 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.20 JeongHyeongCheol (wjdgudcjf@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "이체및출고현황 목록 결과")
public class KpOrderQtyStoInfoResDto {
	
	@Schema(description = "센터 그룹 (이체입고센터)", example = "")
    private String centerGroup;
	
	@Schema(description = "센터 그룹 (이체입고센터)", example = "")
    private String centerDccode;

    @Schema(description = "저장 조건", example = "")
    private String storageType;

    @Schema(description = "총 이체입고 수량", example = "")
    private String inTotalQty;

    @Schema(description = "일평균 이체입고 수량", example = "")
    private String inQtyAvg;

    @Schema(description = "입고 수량 - 2600 (이천)", example = "")
    private String inQty2600;

    @Schema(description = "입고 수량 - 2620 (수원1)", example = "")
    private String inQty2620;

    @Schema(description = "입고 수량 - 2630 (수원2)", example = "")
    private String inQty2630;

    @Schema(description = "입고 수량 - 2650 (동탄1)", example = "")
    private String inQty2650;

    @Schema(description = "입고 수량 - 2660 (동탄2)", example = "")
    private String inQty2660;

    @Schema(description = "입고 수량 - 2260 (양산)", example = "")
    private String inQty2260;

    @Schema(description = "입고 수량 - 2250 (양산직수입)", example = "")
    private String inQty2250;

    // --- SKU 수 집계 정보 ---
    @Schema(description = "총 이체입고 SKU 수", example = "")
    private String inTotalSkuCount;

    @Schema(description = "SKU 수 - 1톤 미만", example = "")
    private String inSkuCountLess1;

    @Schema(description = "SKU 수 - 2.5톤 미만", example = "")
    private String inSkuCountLess25;

    @Schema(description = "SKU 수 - 5톤 미만", example = "")
    private String inSkuCountLess5;

    @Schema(description = "SKU 수 - 10톤 미만", example = "")
    private String inSkuCountLess10;

    @Schema(description = "SKU 수 - 10톤 이상", example = "")
    private String inSkuCountOver10;

    // --- 2600 센터 (이천) 출고 중량 PIVOT ---
    @Schema(description = "출고 중량 총합 - 2600", example = "")
    private String outTon2600Total;

    @Schema(description = "출고 중량 - 2600, 1톤 미만", example = "")
    private String outTon2600Less1;

    @Schema(description = "출고 중량 - 2600, 2.5톤 미만", example = "")
    private String outTon2600Less25;

    @Schema(description = "출고 중량 - 2600, 5톤 미만", example = "")
    private String outTon2600Less5;

    @Schema(description = "출고 중량 - 2600, 10톤 미만", example = "")
    private String outTon2600Less10;

    @Schema(description = "출고 중량 - 2600, 10톤 이상", example = "")
    private String outTon2600Over10;

    // --- 2620 센터 (수원1) 출고 중량 PIVOT ---
    @Schema(description = "출고 중량 총합 - 2620", example = "")
    private String outTon2620Total;

    @Schema(description = "출고 중량 - 2620, 1톤 미만", example = "")
    private String outTon2620Less1;

    @Schema(description = "출고 중량 - 2620, 2.5톤 미만", example = "")
    private String outTon2620Less25;

    @Schema(description = "출고 중량 - 2620, 5톤 미만", example = "")
    private String outTon2620Less5;

    @Schema(description = "출고 중량 - 2620, 10톤 미만", example = "")
    private String outTon2620Less10;

    @Schema(description = "출고 중량 - 2620, 10톤 이상", example = "")
    private String outTon2620Over10;

    // --- 2630 센터 (수원2) 출고 중량 PIVOT ---
    @Schema(description = "출고 중량 총합 - 2630", example = "")
    private String outTon2630Total;

    @Schema(description = "출고 중량 - 2630, 1톤 미만", example = "")
    private String outTon2630Less1;

    @Schema(description = "출고 중량 - 2630, 2.5톤 미만", example = "")
    private String outTon2630Less25;

    @Schema(description = "출고 중량 - 2630, 5톤 미만", example = "")
    private String outTon2630Less5;

    @Schema(description = "출고 중량 - 2630, 10톤 미만", example = "")
    private String outTon2630Less10;

    @Schema(description = "출고 중량 - 2630, 10톤 이상", example = "")
    private String outTon2630Over10;

    // --- 2650 센터 (동탄1) 출고 중량 PIVOT ---
    @Schema(description = "출고 중량 총합 - 2650", example = "")
    private String outTon2650Total;

    @Schema(description = "출고 중량 - 2650, 1톤 미만", example = "")
    private String outTon2650Less1;

    @Schema(description = "출고 중량 - 2650, 2.5톤 미만", example = "")
    private String outTon2650Less25;

    @Schema(description = "출고 중량 - 2650, 5톤 미만", example = "")
    private String outTon2650Less5;

    @Schema(description = "출고 중량 - 2650, 10톤 미만", example = "")
    private String outTon2650Less10;

    @Schema(description = "출고 중량 - 2650, 10톤 이상", example = "")
    private String outTon2650Over10;

    // --- 2660 센터 (동탄2) 출고 중량 PIVOT ---
    @Schema(description = "출고 중량 총합 - 2660", example = "")
    private String outTon2660Total;

    @Schema(description = "출고 중량 - 2660, 1톤 미만", example = "")
    private String outTon2660Less1;

    @Schema(description = "출고 중량 - 2660, 2.5톤 미만", example = "")
    private String outTon2660Less25;

    @Schema(description = "출고 중량 - 2660, 5톤 미만", example = "")
    private String outTon2660Less5;

    @Schema(description = "출고 중량 - 2660, 10톤 미만", example = "")
    private String outTon2660Less10;

    @Schema(description = "출고 중량 - 2660, 10톤 이상", example = "")
    private String outTon2660Over10;

    // --- 2260 센터 (양산) 출고 중량 PIVOT ---
    @Schema(description = "출고 중량 총합 - 2260", example = "")
    private String outTon2260Total;

    @Schema(description = "출고 중량 - 2260, 1톤 미만", example = "")
    private String outTon2260Less1;

    @Schema(description = "출고 중량 - 2260, 2.5톤 미만", example = "")
    private String outTon2260Less25;

    @Schema(description = "출고 중량 - 2260, 5톤 미만", example = "")
    private String outTon2260Less5;

    @Schema(description = "출고 중량 - 2260, 10톤 미만", example = "")
    private String outTon2260Less10;

    @Schema(description = "출고 중량 - 2260, 10톤 이상", example = "")
    private String outTon2260Over10;

    // --- 2250 센터 (양산직수입) 출고 중량 PIVOT ---
    @Schema(description = "출고 중량 총합 - 2250", example = "")
    private String outTon2250Total;

    @Schema(description = "출고 중량 - 2250, 1톤 미만", example = "")
    private String outTon2250Less1;

    @Schema(description = "출고 중량 - 2250, 2.5톤 미만", example = "")
    private String outTon2250Less25;

    @Schema(description = "출고 중량 - 2250, 5톤 미만", example = "")
    private String outTon2250Less5;

    @Schema(description = "출고 중량 - 2250, 10톤 미만", example = "")
    private String outTon2250Less10;

    @Schema(description = "출고 중량 - 2250, 10톤 이상", example = "")
    private String outTon2250Over10;
}
