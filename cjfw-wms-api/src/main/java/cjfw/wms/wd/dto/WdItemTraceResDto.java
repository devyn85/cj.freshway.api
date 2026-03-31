package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.11.17
 * @description : 모니터링 > 검수 > 검수 공정별 Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.17 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "모니터링 > 검수 > 검수 공정별 Response DTO")
public class WdItemTraceResDto {
	
	/* 출고센터 */
	@Schema(description = "출고센터")
	private String dccode;

	/* 출고센터명 */
	@Schema(description = "출고센터명")
	private String dcname;

	/* 출고일자 */
	@Schema(description = "출고일자")
	private String deliverydate;

	/* 전표번호 */
	@Schema(description = "전표번호")
	private String docno;

	/* 전표순번 */
	@Schema(description = "전표순번")
	private String docline;

	/* 저장유무 */
	@Schema(description = "저장유무")
	private String channel;

	/* 저장유무명 */
	@Schema(description = "저장유무명")
	private String channelName;
	
	/* 저장조건 */
	@Schema(description = "저장조건")
	private String storagetype;

	/* 저장조건명 */
	@Schema(description = "저장조건명")
	private String storagetypeName;

	/* 공급업체 협력사코드 */
	@Schema(description = "공급업체 협력사코드")
	private String fromCustkey;

	/* 공급업체 협력사명 */
	@Schema(description = "공급업체 협력사명")
	private String fromCustname;

	/* 공급업체 협력사유형 */
	@Schema(description = "공급업체 협력사유형")
	private String fromCusttype;

	/* 고객 관리처코드 */
	@Schema(description = "고객 관리처코드")
	private String toCustkey;

	/* 고객 관리처코드명 */
	@Schema(description = "고객 관리처코드명")
	private String toCustname;

	/* 고객 관리처유형 */
	@Schema(description = "고객 관리처유형")
	private String toCusttype;

	/* 상품코드 */
	@Schema(description = "상품코드")
	private String sku;

	/* 상품명칭 */
	@Schema(description = "상품명칭")
	private String skuname;

	/* 상품단위 */
	@Schema(description = "상품단위")
	private String uom;

	/* 주문수량 */
	@Schema(description = "원주문량")
	private String orderqty;

	/* 오픈수량 */
	@Schema(description = "오픈수량")
	private String openqty;

	/* PO 주문번호 (일배/광역일배만 나옴) */
	@Schema(description = "PO 주문번호 (일배/광역일배만 나옴)")
	private String pono;

	/* PO 주문 라인 (일배/광역일배만 나옴) */
	@Schema(description = "PO 주문 라인 (일배/광역일배만 나옴)")
	private String poline;

	/* 입고 검수수량 일배 검수(받는센타) */
	@Schema(description = "입고 검수수량 일배 검수(받는센타)")
	private String ilbaeInspectQty;

	/* 입고 입고 확정 일배입고 (받는센타) */
	@Schema(description = "입고 입고 확정 일배입고 (받는센타)")
	private String ilbaeConfirmQty;

	/* 광역 STO 요청 수량 */
	@Schema(description = "광역 STO 요청 수량")
	private String stoRequetQty;

	/* 광역 검수 입고 STO 검수 처리 여부 필요함 */
	@Schema(description = "광역 검수 입고 STO 검수 처리 여부 필요함")
	private String stoDpinspectQty;

	/* 광역 미정의수량 이거 어떻게 할지 숫자가 나올랑가  이름 미정의 */
	@Schema(description = "광역 미정의수량 이거 어떻게 할지 숫자가 나올랑가  이름 미정의")
	private String stoDpQty;

	/* 분배수량 */
	@Schema(description = "분배수량")
	private String qtyallocatedQty;

	/* 피킹 수량 */
	@Schema(description = "피킹 수량")
	private String qtypickedQty;

	/* 출고확정수량 */
	@Schema(description = "출고확정수량")
	private String confirmqty;

	/* 결품 수량 */
	@Schema(description = "결품 수량")
	private String cancelqty;

	/* 상차 수량 */
	@Schema(description = "상차 수량")
	private String upQty;

	/* 하차수량 */
	@Schema(description = "하차수량")
	private String downQty;
	
	/* 결품처리자 */
	@Schema(description = "결품처리자")
	private String cancelwhoName;
	
	/**************************** POP ******************************/
	/* ROWNCNT */
	@Schema(description = "ROWNCNT")
	private BigDecimal rowncnt;

	/* 단위 */
	@Schema(description = "단위")
	private String storeruom;

	/* 입고,전체 : 예정량,예정 */
	@Schema(description = "입고,전체 : 예정량,예정")
	private BigDecimal storeropenqty;

	/* 입고,출고 : 입고량 */
	@Schema(description = "입고,출고 : 입고량")
	private BigDecimal dpInspectqty;

	/* 출고 : 출고량 */
	@Schema(description = "출고 : 출고량")
	private BigDecimal wdInspectqty;

	/* TOTAL_CNT */
	@Schema(description = "TOTAL_CNT")
	private BigDecimal totalCnt;

	/* DELIVERYGROUP */
	@Schema(description = "DELIVERYGROUP")
	private String deliverygroup;


}
