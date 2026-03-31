package cjfw.wms.kp.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.08
 * @description : 지표 > 센터 운영 > 입고 결품 현황 저장요약_탭 Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.08 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "지표 > 센터 운영 > 입고 결품 현황 저장요약_탭 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpDpShortageResultResT4Dto extends CommonProcedureDto {
	
	/* 01. 입고일자 */
	@Schema(description = "01. 입고일자")
	private String slipdt;

	/* 02. 물류센터 */
	@Schema(description = "02. 물류센터")
	private String dccode;

	/* 02. 물류센터 */
	@Schema(description = "02. 물류센터")
	private String dcname;

	/* 03. 입고전표번호 */
	@Schema(description = "03. 입고전표번호")
	private String slipno;

	/* 04. 저장조건 */
	@Schema(description = "04. 저장조건")
	private String storagetype;

	/* 04. 저장조건 */
	@Schema(description = "04. 저장조건")
	private String storagetypename;

	/* 05. 상품코드 */
	@Schema(description = "05. 상품코드")
	private String sku;

	/* 06. 상품명칭 */
	@Schema(description = "06. 상품명칭")
	private String skuname;

	/* 07. 구매단위 */
	@Schema(description = "07. 구매단위")
	private String storeruom;

	/* 08. 주문수량 */
	@Schema(description = "08. 주문수량")
	private BigDecimal orderqty;

	/* 09. 출고수량 */
	@Schema(description = "09. 출고수량")
	private BigDecimal confirmqty;

	/* 10. 결품수량 */
	@Schema(description = "10. 결품수량")
	private BigDecimal shortageqty;

	/* 11. 결품사유 */
	@Schema(description = "11. 결품사유")
	private String reasoncode;

	/* 11. 결품사유 */
	@Schema(description = "11. 결품사유")
	private String reasoncodename;

	/* 12. 협력사코드 */
	@Schema(description = "12. 협력사코드")
	private String fromCustkey;

	/* 13. 협력사명 */
	@Schema(description = "13. 협력사명")
	private String fromCustname;

	/* 14. 담당MD */
	//@MaskingName
	// 2025.09.23 박의병 수석님이 해당 컬럼 마스킹 제외 요청
	@Schema(description = "14. 담당MD")
	private String mdcode;

	/* 15. 수급담당 */
	@Schema(description = "15. 수급담당")
	private String pomdcode;

	/* 15. 수급담당 */
	@Schema(description = "15. 수급담당")
	private String pomdcodename;

	/* STORERKEY */
	@Schema(description = "STORERKEY")
	private String storerkey;

	/* ORGANIZE */
	@Schema(description = "ORGANIZE")
	private String organize;

	/* AREA */
	@Schema(description = "AREA")
	private String area;

}
