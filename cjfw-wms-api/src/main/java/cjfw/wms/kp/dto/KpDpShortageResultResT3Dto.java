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
 * @description : 지표 > 센터 운영 > 입고 결품 현황 일배요약_탭 Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.08 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "지표 > 센터 운영 > 입고 결품 현황 일배요약_탭 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpDpShortageResultResT3Dto extends CommonProcedureDto {
	
	/* 01. 입고일자 */
	@Schema(description = "01. 입고일자")
	private String slipdt;
	
	/* 02. 물류센터 */
	@Schema(description = "02. 물류센터")
	private String dccode;

	/* 02. 물류센터 */
	@Schema(description = "02. 물류센터")
	private String dcname;

	/* 03. 원주문번호 */
	@Schema(description = "03. 원주문번호")
	private String sourcekey;

	/* 04. 고객코드 */
	@Schema(description = "04. 고객코드")
	private String custkey;

	/* 05. 고객명 */
	@Schema(description = "05. 고객명")
	private String custname;

	/* 06. 배송인도처코드 */
	@Schema(description = "06. 배송인도처코드")
	private String toCustkey;

	/* 07. 배송인도처명 */
	@Schema(description = "07. 배송인도처명")
	private String toCustname;

	/* 08. 상품코드 */
	@Schema(description = "08. 상품코드")
	private String sku;

	/* 09. 상품명칭 */
	@Schema(description = "09. 상품명칭")
	private String skuname;

	/* 10. 구매단위 */
	@Schema(description = "10. 구매단위")
	private String storeruom;

	/* 11. 주문수량 */
	@Schema(description = "11. 주문수량")
	private BigDecimal orderqty;

	/* 12. 출고수량 */
	@Schema(description = "12. 출고수량")
	private BigDecimal confirmqty;

	/* 13. 결품수량 */
	@Schema(description = "13. 결품수량")
	private BigDecimal shortageqty;

	/* 14. 결품사유 */
	@Schema(description = "14. 결품사유")
	private String reasoncode;

	/* 14. 결품사유 */
	@Schema(description = "14. 결품사유")
	private String reasoncodename;

	/* 15. 조치내역 */
	@Schema(description = "15. 조치내역")
	private String actionhist;

	/* 15. 조치내역 */
	@Schema(description = "15. 조치내역")
	private String actionhistname;

	/* 16. 입고검수유/무 */
	@Schema(description = "16. 입고검수유/무")
	private String inspectynDp;

	/* 17. 상차검수유/무 */
	@Schema(description = "17. 상차검수유/무")
	private String inspectyn;

	/* 18. 협력사코드 */
	@Schema(description = "18. 협력사코드")
	private String fromCustkeyDp;

	/* 19. 협력사명 */
	@Schema(description = "19. 협력사명")
	private String fromCustnameDp;

	/* 20. 담당MA */
	// 2025.09.23 박의병 수석님이 해당 컬럼 마스킹 제외 요청
	//@MaskingName
	@Schema(description = "20. 담당MA")
	private String macode;

	/* 21. 담당MD */
	//@MaskingName
	@Schema(description = "21. 담당MD")
	private String mdcode;

	/* 22. 저장조건 */
	@Schema(description = "22. 저장조건")
	private String storagetype;

	/* 22. 저장조건 */
	@Schema(description = "22. 저장조건")
	private String storagetypename;

	/* INSPECTYN_DP_QTY */
	@Schema(description = "INSPECTYN_DP_QTY")
	private BigDecimal inspectynDpQty;

	/* INSPECTYN_QTY */
	@Schema(description = "INSPECTYN_QTY")
	private BigDecimal inspectynQty;
	
	/* EMP_NM */
	@Schema(description = "EMP_NM")
	private String empNm;

	/* EMP_NM1 */
	@Schema(description = "EMP_NM1")
	private String empNm1;
	
	/* EMP_NM2 */
	@Schema(description = "EMP_NM2")
	private String empNm2;

}
