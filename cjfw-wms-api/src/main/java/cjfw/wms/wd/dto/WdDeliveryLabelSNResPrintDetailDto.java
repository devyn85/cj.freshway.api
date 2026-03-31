package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.10.15 
 * @description : 이력배송라벨출력 피킹지출력 상세 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.15 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "이력배송라벨출력 피킹지출력 상세 결과")
public class WdDeliveryLabelSNResPrintDetailDto extends CommonDto{
	/**
     * 화주키
     */
    @Schema(description = "화주키", example = "SKEY001")
    private String storerkey;

    /**
     * 인보이스 일자
     */
    @Schema(description = "인보이스 일자", example = "2024-01-01")
    private String invoicedt;

    /**
     * 보관유형
     */
    @Schema(description = "보관유형", example = "NORMAL")
    private String storagetype;

    /**
     * SKU
     */
    @Schema(description = "SKU", example = "ITEM001")
    private String sku;

    /**
     * SKU명
     */
    @Schema(description = "SKU명", example = "상품A")
    private String skuname;

    /**
     * 확정수량
     */
    @Schema(description = "확정수량", example = "100")
    private BigDecimal confirmqty;

    /**
     * 로케이션
     */
    @Schema(description = "로케이션", example = "L01-01")
    private String loc;

    /**
     * 존
     */
    @Schema(description = "존", example = "ZONEA")
    private String zone;

    /**
     * 단위
     */
    @Schema(description = "단위", example = "EA")
    private String uom;

    /**
     * 박스당 수량
     */
    @Schema(description = "박스당 수량", example = "10")
    private BigDecimal qtyperbox;

    /**
     * LOT속성01
     */
    @Schema(description = "LOT속성01", example = "01")
    private String lottable01;

    /**
     * 피킹리스트 제목
     */
    @Schema(description = "피킹리스트 제목", example = "2024년01월01일   출고(NORMAL) 피킹리스트")
    private String pickListTitle;

    /**
     * KG 수량
     */
    @Schema(description = "KG 수량", example = "10.00")
    private BigDecimal kgqty;

    /**
     * BOX 수량
     */
    @Schema(description = "BOX 수량", example = "10")
    private BigDecimal boxqty;

    /**
     * EA 수량
     */
    @Schema(description = "EA 수량", example = "5")
    private BigDecimal eaqty;
    
    
	  /**
	   * 대배치번호
	   */
	  @Schema(description = "대배치번호", example = "BATCH_001 P_001")
	  private String pickBatchNo;

	  /**
	   * 출고일자
	   */
	  @Schema(description = "출고일자", example = "2024-01-01")
	  private String slipdt;

	  /**
	   * 배송그룹
	   */
	  @Schema(description = "배송그룹", example = "CJ대한통운")
	  private String courier;


	  /**
	   * 피킹번호
	   */
	  @Schema(description = "피킹번호", example = "P_001")
	  private String pickNo;


	  /**
	   * 단위
	   */
	  @Schema(description = "단위", example = "EA")
	  private String baseuom;
	  /**
	   * 처리수량
	   */
	  @Schema(description = "처리수량", example = "10.00")
	  private BigDecimal processqty;

	  /**
	   * 총EA수량
	   */
	  @Schema(description = "총EA수량", example = "100.00")
	  private BigDecimal toteaqty;

}
