package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2026.02.19 
 * @description : 일배분류서출력 헤더 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.02.19 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "일배분류서출력 헤더 결과")
public class WdDeliveryLabelDailyResPrintHeaderDto extends CommonDto{
	/** 일배 분류서 */
	@Schema(description = "일배 분류서", example = "일배 분류서")
	private String title;
	
	/** DELIVERYGROUP */
	@Schema(description = "DELIVERYGROUP", example = "")
	private String deliverygroup;
	
	/** FROM_CUSTKEY */
	@Schema(description = "FROM_CUSTKEY", example = "VEND0001")
	private String fromCustkey;

	/** FROM_CUSTNAME */
	@Schema(description = "FROM_CUSTNAME", example = "A공급사")
	private String fromCustname;
	
	/** CARNO */
	@Schema(description = "CARNO", example = "")
	private String carno;
	
	/** DRIVERNAME */
	@Schema(description = "DRIVERNAME", example = "")
	private String drivername;

	/** DELIVERYDATE */
	@Schema(description = "DELIVERYDATE", example = "2026-02-19")
	private String deliverydate;

	/** DCCODE */
	@Schema(description = "DCCODE", example = "DC10")
	private String dccode;

	/** DCNAME */
	@Schema(description = "DCNAME", example = "신선물류센터")
	private String dcname;

	/** TOTALCNT */
	@Schema(description = "TOTALCNT", example = "150")
	private BigDecimal totalcnt;
}
