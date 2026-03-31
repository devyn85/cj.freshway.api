package cjfw.wms.st.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonTriggerDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.10.28
 * @description : 재고 > 재고조사 > 재고조사지시 Entity DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.28 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "KIT 상품 생산 IF Entity") 
public class StMKitIFEntity extends CommonTriggerDto {

	/* 데이터번호 */
	@Schema(description = "데이터번호")
	private String serialkey;
	
	/* 일자별 순번 (SAP 채번용) */
	@Schema(description = "일자별 순번 (SAP 채번용)")
	private String ifSeq;
	
	/* 센터코드 */
	@Schema(description = "센터코드")
	private String dccode;

	/* 창고코드 */
	@Schema(description = "창고코드")
	private String area;
	
	/* 생산일자 */
	@Schema(description = "생산일자")
	private String planDt;
	
	/* 이동유형(947 분해.. 모품목감모, 948 모품생산 둘중하나 ) */
	@Schema(description = "이동유형(947 분해.. 모품목감모, 948 모품생산 둘중하나 )")
	private String movementtype;
	
	/* 문서번호 */
	@Schema(description = "문서번호")
	private String slipno;
	
	/* 상품코드 */
	@Schema(description = "상품코드")
	private String kitSku;
	
	/* 생산(양수) , 해체(음수) */
	@Schema(description = "생산(양수) , 해체(음수)")
	private BigDecimal confirmqty;
	
	/* 모품목 단위기준 */
	@Schema(description = "모품목 단위기준")
	private String kitUom;
	
	/* 코스트센타(부서코드) */
	@Schema(description = "코스트센타(부서코드)")
	private String costcd;
	
	/* 고객코드 */
	@Schema(description = "고객코드")
	private String custkey;
	
	/* N' 으로 드림 */
	@Schema(description = "N' 으로 드림")
	private String ifFlag;
	
  
}
