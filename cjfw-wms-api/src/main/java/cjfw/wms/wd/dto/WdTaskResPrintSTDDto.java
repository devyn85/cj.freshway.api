package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.09.03 
 * @description : 피킹작업지시-피킹리스트STD출력 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.03 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "피킹작업지시-피킹리스트STD출력 결과")
public class WdTaskResPrintSTDDto extends CommonDto{
	/**
	   * 물류센터코드
	   */
	  @Schema(description = "물류센터코드", example = "DC01")
	  private String dccode;

	  /**
	   * 납품일자
	   */
	  @Schema(description = "납품일자", example = "2024-01-01-0001-0001")
	  private String taskdt;

	  /**
	   * 작업방법
	   */
	  @Schema(description = "작업방법", example = "SYSTEM")
	  private String tasksystem;

	  /**
	   * 플랜트
	   */
	  @Schema(description = "플랜트", example = "P101 PLANT_A")
	  private String plant;

	  /**
	   * 저장조건
	   */
	  @Schema(description = "저장조건", example = "COLD")
	  private String storagetypedesc;

	  /**
	   * 원거리유형
	   */
	  @Schema(description = "원거리유형", example = "SHORT")
	  private String distancetype;

	  /**
	   * 대배치번호
	   */
	  @Schema(description = "대배치번호", example = "P001 BATCH_A")
	  private String pickBatchNo;

	  /**
	   * 피킹리스트번호
	   */
	  @Schema(description = "피킹리스트번호", example = "PL001")
	  private String pickListNo;

	  /**
	   * 피킹번호
	   */
	  @Schema(description = "피킹번호", example = "PK001")
	  private String pickNo;

	  /**
	   * 상품코드
	   */
	  @Schema(description = "상품코드", example = "SKU001")
	  private String sku;

	  /**
	   * 상품명
	   */
	  @Schema(description = "상품명", example = "상품A/KOR")
	  private String skuname;

	  /**
	   * 차량번호
	   */
	  @Schema(description = "차량번호", example = "12가1234")
	  private String carno;

	  /**
	   * POP번호
	   */
	  @Schema(description = "POP번호", example = "12345")
	  private String deliverygroup;

	  /**
	   * 납품처
	   */
	  @Schema(description = "납품처", example = "CUST001")
	  private String toCuatkey;

	  /**
	   * 납품처명
	   */
	  @Schema(description = "납품처명", example = "납품처A")
	  private String toCustname;

	  /**
	   * 출차조
	   */
	  @Schema(description = "출차조", example = "TEAM_A")
	  private String shipteam;

	  /**
	   * 물량
	   */
	  @Schema(description = "물량", example = "100.00")
	  private BigDecimal processqty;

	  /**
	   * 단위
	   */
	  @Schema(description = "단위", example = "EA")
	  private String uom;

	  /**
	   * BOX수량
	   */
	  @Schema(description = "BOX수량", example = "10")
	  private BigDecimal boxqty;

	  /**
	   * EA수량
	   */
	  @Schema(description = "EA수량", example = "2")
	  private BigDecimal eaqty;

	  /**
	   * 출력일시
	   */
	  @Schema(description = "출력일시", example = "2024-01-01 10:00:00")
	  private String printdt;

	  /**
	   * 프린트횟수
	   */
	  @Schema(description = "프린트횟수", example = "1")
	  private BigDecimal printCnt;
}
