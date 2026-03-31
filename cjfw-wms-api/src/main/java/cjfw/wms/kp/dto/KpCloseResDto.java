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
 * @date : 2025.08.22
 * @description : 모니터링 > 물동 > 물동마감 진행 현황 Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.22 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "모니터링 > 물동 > 물동마감 진행 현황 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpCloseResDto extends CommonProcedureDto {

	/* 조회월 */
	@Schema(description = "조회월")
	private String dmMonth;
	
	/* 조회월 */
	@Schema(description = "조회월")
	private String docdt;

	/* 조회시작일 */
	@Schema(description = "조회시작일")
	private String docdtFrom;

	/* 조회종료일 */
	@Schema(description = "조회종료일")
	private String docdtTo;

	/* 조회구분 */
	@Schema(description = "조회구분")
	private String searchtype;

	/* 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;
	
	/* 물류센터명 */
	@Schema(description = "물류센터명")
	private String dcname;

	/* 플랜트 */
	@Schema(description = "플랜트")
	private String plant;

	/* 구매입고 : 전체건 */
	@Schema(description = "구매입고 : 전체건")
	private BigDecimal dpNstoTot;

	/* 구매입고 : 처리건 */
	@Schema(description = "구매입고 : 처리건")
	private BigDecimal dpNstoComp;

	/* 구매입고 : 미처리건 */
	@Schema(description = "구매입고 : 미처리건")
	private BigDecimal dpNstoNcomp;

	/* 구매입고 : 진오더 미전환 건 */
	@Schema(description = "구매입고 : 진오더 미전환 건")
	private BigDecimal dpNstoNreal;

	/* 광역입고 : 전체건 */
	@Schema(description = "광역입고 : 전체건")
	private BigDecimal dpStoTot;

	/* 광역입고 : 처리건 */
	@Schema(description = "광역입고 : 처리건")
	private BigDecimal dpStoComp;

	/* 광역입고 : 미처리건 */
	@Schema(description = "광역입고 : 미처리건")
	private BigDecimal dpStoNcomp;

	/* 반품입고 : 전체건 */
	@Schema(description = "반품입고 : 전체건")
	private BigDecimal rtTot;

	/* 반품입고 : 처리건 */
	@Schema(description = "반품입고 : 처리건")
	private BigDecimal rtComp;

	/* 반품입고 : 미처리건 */
	@Schema(description = "반품입고 : 미처리건")
	private BigDecimal rtNcomp;

	/* 고객출고 : 전체건 */
	@Schema(description = "고객출고 : 전체건")
	private BigDecimal wdNstoTot;

	/* 고객출고 : 처리건 */
	@Schema(description = "고객출고 : 처리건")
	private BigDecimal wdNstoComp;

	/* 고객출고 : 미처리건 */
	@Schema(description = "고객출고 : 미처리건")
	private String wdNstoNcomp;

	/* 고객출고 : 진오더 미전환 건 */
	@Schema(description = "고객출고 : 진오더 미전환 건")
	private BigDecimal wdNstoNreal;

	/* 광역출고 : 전체건 */
	@Schema(description = "광역출고 : 전체건")
	private BigDecimal wdStoTot;

	/* 광역출고 : 처리건 */
	@Schema(description = "광역출고 : 처리건")
	private BigDecimal wdStoComp;

	/* 광역출고 : 미처리건 */
	@Schema(description = "광역출고 : 미처리건")
	private BigDecimal wdStoNcomp;

	/* 협력사반품 : 전체건 */
	@Schema(description = "협력사반품 : 전체건")
	private BigDecimal rtStoTot;

	/* 협력사반품 : 처리건 */
	@Schema(description = "협력사반품 : 처리건")
	private BigDecimal rtCustComp;

	/* 협력사반품 : 미처리건 */
	@Schema(description = "협력사반품 : 미처리건")
	private BigDecimal rtCustNcomp;

	/* 운송중재고 : 오더건수 */
	@Schema(description = "운송중재고 : 오더건수")
	private BigDecimal stoSt;

	/* 일배재고 : SKU 수 */
	@Schema(description = "일배재고 : SKU 수")
	private String dailySt;

	/* 폐기예약 : 오더건수 */
	@Schema(description = "폐기예약 : 오더건수")
	private BigDecimal ajDuNcomp;

	/* 감모예약 : 오더건수 */
	@Schema(description = "감모예약 : 오더건수")
	private BigDecimal ajDdNcomp;

	/* 반품예약 : 오더건수 */
	@Schema(description = "반품예약 : 오더건수")
	private BigDecimal rtVendorNcomp;

	/* AJ_DU_COMP */
	@Schema(description = "AJ_DU_COMP")
	private BigDecimal ajDuComp;

	/* AJ_DD_COMP */
	@Schema(description = "AJ_DD_COMP")
	private BigDecimal ajDdComp;

	/* RT_VENDOR_COMP */
	@Schema(description = "RT_VENDOR_COMP")
	private BigDecimal rtVendorComp;

	/* 마감여부 */
	@Schema(description = "마감여부")
	private String closeyn;

	/* 출고정정 : 처리건 */
	@Schema(description = "출고정정 : 처리건")
	private BigDecimal wdQuickComp;

	/* 출고정정 : 미처리건 */
	@Schema(description = "출고정정 : 미처리건")
	private BigDecimal wdQuickNcomp;
}
