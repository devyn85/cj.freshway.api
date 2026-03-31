package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.08.29 
 * @description : 피킹작업지시-조회생성 상세 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.29 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "피킹작업지시-조회생성 상세 결과")
public class WdTaskResTab3Dto extends CommonDto{
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/**
     * 작업지시일자
     */
    @Schema(description = "작업지시일자", example = "2025-08-29")
    private String taskdt;

    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "SKU12345")
    private String sku;

    /**
     * 상품명칭
     */
    @Schema(description = "상품명칭", example = "샘플상품")
    private String skuname;

    /**
     * 단위
     */
    @Schema(description = "단위", example = "EA")
    private String uom;

    /**
     * 분배량
     */
    @Schema(description = "분배량", example = "100")
    private BigDecimal processqty;

    /**
     * 피킹LOC
     */
    @Schema(description = "피킹LOC", example = "A-01-01")
    private String loc;

    /**
     * 로트
     */
    @Schema(description = "로트", example = "LOT001")
    private String lot;

    /**
     * 객체식별/유통이력
     */
    @Schema(description = "객체식별/유통이력", example = "STOCKID001")
    private String stockid;

    /**
     * 피킹작업자
     */
    @Schema(description = "피킹작업자", example = "picker01")
    private String picker;

    /**
     * 사용자이름
     */
    @Schema(description = "사용자이름", example = "홍길동")
    private String username;

    /**
     * DC코드
     */
    @Schema(description = "DC코드", example = "DC01")
    private String dccode;

    /**
     * 재고등급
     */
    @Schema(description = "재고등급", example = "A")
    private String stockgrade;

    /**
     * 재고유형
     */
    @Schema(description = "재고유형", example = "NORMAL")
    private String stocktype;

    /**
     * 사용자ID
     */
    @Schema(description = "사용자ID", example = "user01")
    private String userid;

    /**
     * 작업시스템
     */
    @Schema(description = "작업시스템", example = "WMS")
    private String tasksystem;
    
    /**
     * 작업유형
     */
    @Schema(description = "작업유형", example = "부분피킹")
    private String tasktypenm;
    
    /**
     * 작업유형
     */
    @Schema(description = "작업유형", example = "AL")
    private String tasktype;
}
