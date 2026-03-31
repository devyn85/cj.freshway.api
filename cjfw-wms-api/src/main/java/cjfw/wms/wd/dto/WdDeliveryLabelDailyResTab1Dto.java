package cjfw.wms.wd.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2026.02.19 
 * @description : 일배분류서출력 일배탭 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.02.19 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "일배분류서출력 일배탭 결과") 
public class WdDeliveryLabelDailyResTab1Dto extends CommonDto {
	/** 센터코드 */
	@Schema(description = "센터코드", example = "DC01")
	private String dccode;

	/** 센터명 */
	@Schema(description = "센터명", example = "서울물류센터")
	private String dcname;

	/** 납품일자 */
	@Schema(description = "납품일자", example = "2026-02-19")
	private String deliverydate;

	/** 주문유형 */
	@Schema(description = "주문유형", example = "일반")
	private String ordertype;

	/** 상품코드 */
	@Schema(description = "상품코드", example = "SKU001")
	private String sku;

	/** 상품명 */
	@Schema(description = "상품명", example = "우유 1L")
	private String skuname;

	/** 보관유형 */
	@Schema(description = "보관유형", example = "냉장")
	private String storagetypenm;

	/** 담당부서 */
	@Schema(description = "담당부서", example = "영업1팀")
	private String salegroup;

	/** 영업사원 */
	@Schema(description = "영업사원", example = "홍길동")
	private String toEmpname;

	/** 차량번호 */
	@Schema(description = "차량번호", example = "12가3456")
	private String carno;

	/** POP */
	@Schema(description = "POP", example = "A-01")
	private String deliverygroup;

	/** 기사명 */
	@Schema(description = "기사명", example = "김기사")
	private String drivername;

	/** 고객코드 */
	@Schema(description = "고객코드", example = "CUST001")
	private String toCustkey;

	/** 고객명 */
	@Schema(description = "고객명", example = "에이비씨마트")
	private String toCustname;

	/** 단위 */
	@Schema(description = "단위", example = "EA")
	private String uom;

	/** 주문수량 */
	@Schema(description = "주문수량", example = "100")
	private BigDecimal orderqty;

	/** 주문시간 */
	@Schema(description = "주문시간", example = "2026-02-19 14:00:00")
	private String adddate;

	/** 주문번호 */
	@Schema(description = "주문번호", example = "ORD20260219")
	private String docno;

	/** 항번 */
	@Schema(description = "항번", example = "1")
	private String docline;

	/** 발주번호 */
	@Schema(description = "발주번호", example = "PO20260219")
	private String pokey;

	/** 구매처 */
	@Schema(description = "구매처", example = "VEND001")
	private String fromCustkey;

	/** 구매처명 */
	@Schema(description = "구매처명", example = "공급상사")
	private String fromCustname;

	/** 비고(물류요청사항) */
	@Schema(description = "비고(물류요청사항)", example = "파손주의")
	private String memo1;

	/** poline */
	@Schema(description = "poline", example = "1")
	private String poline;

		
}
