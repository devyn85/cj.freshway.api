package cjfw.wms.tm.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.11.20
 * @description : 분할 미적용 관리처 request dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.20 JiHoPark  생성 </pre>
 */
@Data
@Schema(description = "분할 미적용 관리처 response dto")
public class TmMngplcIDUndoneResDto extends CommonProcedureDto {
	/** 출고일자 */
	@Schema(description = "출고일자")
	private String deliverydate;

	/** 주문번호 */
	@Schema(description = "주문번호")
	private String docno;

	/** 고객코드 */
	@Schema(description = "고객코드")
	private String toCustkey;

	/** 고객명 */
	@Schema(description = "고객명")
	private String toCustname;

	/** 기본POP */
	@Schema(description = "기본POP")
	private String toCustpop;

	/** 물류센터 */
	@Schema(description = "고객코드")
	private String mngplcId;

	/** 물류센터 */
	@Schema(description = "고객명")
	private String mngplcname;

	/** 물류센터 */
	@Schema(description = "기본POP")
	private String mngplcpop;

	/** 물류센터 */
	@Schema(description = "상품코드")
	private String sku;

	/** 물류센터 */
	@Schema(description = "상품명")
	private String skuname;

	/** 물류센터 */
	@Schema(description = "단위")
	private String storeruom;

	/** 물류센터 */
	@Schema(description = "주문량")
	private BigDecimal storeropenqty;

}
