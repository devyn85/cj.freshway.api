package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.09.29 
 * @description : 피킹작업지시(상품별)-조회생성 상세 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.29 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "피킹작업지시(상품별)-조회생성 상세 결과")
public class WdTaskSkuResDetailDto extends CommonDto{
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/**
	 * 고객사
	 */
	@Schema(description = "고객사", example = "고객사코드")
	private String storerkey;
	/**
	 * 센터코드
	 */
	@Schema(description = "센터코드", example = "센터코드")
	private String dccode;
	/**
	 * 피킹일자
	 */
	@Schema(description = "피킹일자", example = "2024-01-01")
	private String taskdt;
	/**
	 * Wave키
	 */
	@Schema(description = "Wave키", example = "WAVE001")
	private String wavekey;
	/**
	 * SY_PROCESSTEMP_WD용
	 */
	@Schema(description = "SY_PROCESSTEMP_WD용", example = "예상값")
	private String other01;
	/**
	 * SY_PROCESSTEMP_WD용
	 */
	@Schema(description = "SY_PROCESSTEMP_WD용", example = "예상값")
	private String other02;
	/**
	 * SY_PROCESSTEMP_WD용
	 */
	@Schema(description = "SY_PROCESSTEMP_WD용", example = "예상값")
	private String other03;
	/**
	 * 상품코드
	 */
	@Schema(description = "상품코드", example = "ITEM001")
	private String sku;
	/**
	 * 상품명칭
	 */
	@Schema(description = "상품명칭", example = "사과")
	private String skuname;
	/**
	 * 주문단위
	 */
	@Schema(description = "주문단위", example = "BOX")
	private String uom;
	/**
	 * 수량|예정량
	 */
	@Schema(description = "수량|예정량", example = "예상값")
	private BigDecimal openqty;
	/**
	 * 수량|할당량
	 */
	@Schema(description = "수량|할당량", example = "예상값")
	private BigDecimal processqty;
	/**
	 * 수량|작업량
	 */
	@Schema(description = "수량|작업량", example = "예상값")
	private BigDecimal workqty;
	/**
	 * 수량|검수량
	 */
	@Schema(description = "수량|검수량", example = "예상값")
	private BigDecimal inspectqty;
	/**
	 * 수량|확정량
	 */
	@Schema(description = "수량|확정량", example = "예상값")
	private BigDecimal confirmqty;
	/**
	 * OMS미마감건수
	 */
	@Schema(description = "OMS미마감건수", example = "예상값")
	private BigDecimal omsFlag;
}
