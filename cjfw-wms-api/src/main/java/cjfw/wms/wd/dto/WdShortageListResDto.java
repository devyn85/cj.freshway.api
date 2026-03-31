package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2026.03.05 
 * @description : 출고결품현황 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.03.05 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "출고결품현황 목록 결과")
public class WdShortageListResDto {
	/** 출고일자 */
	@Schema(description = "출고일자", example = "20231024")
	private String slipdt;

	/** 주문번호 */
	@Schema(description = "주문번호", example = "DOC12345678")
	private String docno;

	/** 관리처코드 */
	@Schema(description = "관리처코드", example = "CUST001")
	private String custkey;

	/** 관리처명 */
	@Schema(description = "관리처명", example = "현대백화점")
	private String custname;

	/** 영업조직 */
	@Schema(description = "영업조직", example = "영업본부")
	private String saleorganize;

	/** 사업장 */
	@Schema(description = "사업장", example = "서울사업소")
	private String saledepartment;

	/** 영업그룹 */
	@Schema(description = "영업그룹", example = "영업1팀")
	private String salegroup;

	/** MA */
	@Schema(description = "MA", example = "홍길동")
	private String ma;

	/** MD */
	@Schema(description = "MD", example = "김MD")
	private String somdname;

	/** 영업경로(대) */
	@Schema(description = "영업경로(대)", example = "오프라인")
	private String salecushrc1;

	/** 영업경로(중) */
	@Schema(description = "영업경로(중)", example = "백화점")
	private String salecushrc2;

	/** 영업경로(소) */
	@Schema(description = "영업경로(소)", example = "수도권")
	private String salecushrc3;

	/** 상품코드 */
	@Schema(description = "상품코드", example = "SKU10001")
	private String sku;

	/** 상품명칭 */
	@Schema(description = "상품명칭", example = "유기농 우유")
	private String skuname;

	/** 플랜트 */
	@Schema(description = "플랜트", example = "[P01]서울공장")
	private String plantDescr;

	/** 단위 */
	@Schema(description = "단위", example = "EA")
	private String uom;

	/** 취소량 */
	@Schema(description = "취소량", example = "10.5")
	private BigDecimal cancelqty;

	/** 저장유무 */
	@Schema(description = "저장유무", example = "Y")
	private String channel;

	/** 사유코드 */
	@Schema(description = "사유코드", example = "R01")
	private String reasoncode;

	/** 사유메시지 */
	@Schema(description = "사유메시지", example = "재고부족")
	private String reasonmsg;

	/** 협력사코드 */
	@Schema(description = "협력사코드", example = "SUPP001")
	private String fromCustkey;

	/** 협력사명 */
	@Schema(description = "협력사명", example = "A협력사")
	private String fromCustname;

	/** 결품처리시간 */
	@Schema(description = "결품처리시간", example = "2023-10-24 14:00:00")
	private String canceldate;

	/** 결품처리자 */
	@Schema(description = "결품처리자", example = "[ADMIN]관리자")
	private String cancelwho;

	/** POP번호 */
	@Schema(description = "POP번호", example = "POP001")
	private String deliverygroup;

	/** 저장조건 */
	@Schema(description = "저장조건", example = "냉장")
	private String storagetype;

	/** 슈트번호 */
	@Schema(description = "슈트번호", example = "D01")
	private String dockno;

	/** 차량번호 */
	@Schema(description = "차량번호", example = "12가3456")
	private String carno;

	/** PLANT */
	@Schema(description = "PLANT", example = "P01")
	private String plant;
}
