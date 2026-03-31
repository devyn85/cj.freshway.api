package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2026.02.19 
 * @description : 일배분류서출력 상세 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.02.19 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "일배분류서출력 상세 결과")
public class WdDeliveryLabelDailyResPrintDetailDto extends CommonDto{
	/** dccode */
	@Schema(description = "dccode", example = "DC01")
	private String dccode;

	/** dcname */
	@Schema(description = "dcname", example = "물류센터A")
	private String dcname;

	/** deliverydate */
	@Schema(description = "deliverydate", example = "2026-02-19")
	private String deliverydate;

	/** ordertype */
	@Schema(description = "ordertype", example = "일반배송")
	private String ordertype;

	/** toEmpname */
	@Schema(description = "toEmpname", example = "홍길동")
	private String toEmpname;

	/** carno */
	@Schema(description = "carno", example = "12가3456")
	private String carno;

	/** deliverygroup */
	@Schema(description = "deliverygroup", example = "G01")
	private String deliverygroup;

	/** drivername */
	@Schema(description = "drivername", example = "김기사")
	private String drivername;

	/** salegroup */
	@Schema(description = "salegroup", example = "영업1팀")
	private String salegroup;

	/** fromCustkey */
	@Schema(description = "fromCustkey", example = "CUST001")
	private String fromCustkey;

	/** fromCustname */
	@Schema(description = "fromCustname", example = "공급처A")
	private String fromCustname;

	/** toCustkey */
	@Schema(description = "toCustkey", example = "CUST002")
	private String toCustkey;

	/** toCustname */
	@Schema(description = "toCustname", example = "고객사B")
	private String toCustname;

	/** docno */
	@Schema(description = "docno", example = "DOC20260219")
	private String docno;

	/** docline */
	@Schema(description = "docline", example = "00001")
	private String docline;

	/** pokey */
	@Schema(description = "pokey", example = "PO1001")
	private String pokey;

	/** poline */
	@Schema(description = "poline", example = "00001")
	private String poline;

	/** sku */
	@Schema(description = "sku", example = "SKU001")
	private String sku;

	/** skuname */
	@Schema(description = "skuname", example = "상품명")
	private String skuname;

	/** storagetypenm */
	@Schema(description = "storagetypenm", example = "상온")
	private String storagetypenm;

	/** uom */
	@Schema(description = "uom", example = "EA")
	private String uom;

	/** orderqty */
	@Schema(description = "orderqty", example = "100")
	private BigDecimal orderqty;

	/** adddate */
	@Schema(description = "adddate", example = "2026-02-19 18:00:00")
	private String adddate;

	/** memo1 */
	@Schema(description = "memo1", example = "비고내용")
	private String memo1;

	/** 출발물류센터명 */
	@Schema(description = "출발물류센터명", example = "서울센터")
	private String fromDcname;

}
