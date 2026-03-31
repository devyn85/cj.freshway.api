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
@Schema(description = "재고 > 재고조사 > 재고조사지시 Entity") 
public class StInquiryInplanEntity extends CommonTriggerDto {

	/* 데이터번호 */
	@Schema(description = "데이터번호")
	private String serialkey;
	
	/* 센터코드 */
	@Schema(description = "센터코드")
	private String dccode;

	/* 조사번호 */
	@Schema(description = "조사번호")
	private String inquiryno;

	/* 조사명칭 */
	@Schema(description = "조사명칭")
	private String inquiryName;

	/* 조직코드 */
	@Schema(description = "조직코드")
	private String organize;
	
	/* 창고코드 SAP 창고 혹은 별도의 창고 코드 */
	@Schema(description = "창고코드 SAP 창고 혹은 별도의 창고 코드")
	private String area;
	
	/* 상품코드 */
	@Schema(description = "상품코드")
	private String sku;
	
	/* 로케이션 */
	@Schema(description = "로케이션")
	private String loc;
	
	/* 재고 구분 LOT */
	@Schema(description = "재고 구분 LOT")
	private String lot;
	
	/* 재고 구분 ID */
	@Schema(description = "재고 구분 ID")
	private String stockid;

	/* 재고 등급( ABC ) */
	@Schema(description = "재고 등급( ABC )")
	private String stockgrade;
	
	/* 조사지시량 */
	@Schema(description = "조사지시량")
	private BigDecimal orderqty;
	
	/* 조사당시재고량 */
	@Schema(description = "조사당시재고량")
	private BigDecimal stockqty;

	/* 유통기한설정(0:참조,1:무시) */
	@Schema(description = "유통기한설정(0:참조,1:무시)")
	private String lottype;

	/* 이력번호 */
	@Schema(description = "이력번호")
	private String serialno;

	/* BL번호 */
	@Schema(description = "BL번호")
	private String convserialno;

  
}
