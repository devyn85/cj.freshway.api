package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.09.03 
 * @description : 피킹작업지시-피킹리스트 출력 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.03 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "피킹작업지시-피킹리스트 출력 결과")
public class WdTaskResPrintDetailDto extends CommonDto{
	/**
	   * 피킹리스트제목
	   */
	  @Schema(description = "피킹리스트제목", example = "2024년01월01일  CJ대한통운 출고(택배) 피킹리스트")
	  private String pickListTitle;

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
	   * 저장조건
	   */
	  @Schema(description = "저장조건", example = "COLD")
	  private String storagetype;

	  /**
	   * 피킹번호
	   */
	  @Schema(description = "피킹번호", example = "P_001")
	  private String pickNo;

	  /**
	   * 존
	   */
	  @Schema(description = "존", example = "ZONE_A")
	  private String zone;

	  /**
	   * 위치
	   */
	  @Schema(description = "위치", example = "A-01-01")
	  private String loc;

	  /**
	   * 상품코드
	   */
	  @Schema(description = "상품코드", example = "ITEM_001")
	  private String sku;

	  /**
	   * 상품명
	   */
	  @Schema(description = "상품명", example = "상품A/KOR 이력번호:12345 바코드:67890")
	  private String skuname;

	  /**
	   * 단위
	   */
	  @Schema(description = "단위", example = "EA")
	  private String baseuom;

	  /**
	   * 롯트속성1
	   */
	  @Schema(description = "롯트속성1", example = "LOT001")
	  private String lottable01;

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

	  /**
	   * BOX당수량
	   */
	  @Schema(description = "BOX당수량", example = "10")
	  private BigDecimal qtyperbox;

	  /**
	   * KG수량
	   */
	  @Schema(description = "KG수량", example = "5.50")
	  private BigDecimal kgqty;

	  /**
	   * BOX수량
	   */
	  @Schema(description = "BOX수량", example = "10")
	  private BigDecimal boxqty;

	  /**
	   * EA수량
	   */
	  @Schema(description = "EA수량", example = "5")
	  private BigDecimal eaqty;
}
