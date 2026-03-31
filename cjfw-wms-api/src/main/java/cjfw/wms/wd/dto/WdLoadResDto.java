package cjfw.wms.wd.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.11.12
 * @description : 출차지시처리 request dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.12 JiHoPark  생성 </pre>
 */
@Data
@Schema(description = "출차지시처리 response dto")
public class WdLoadResDto extends CommonProcedureDto {
	/** 고객사코드 */
	@Schema(description = "고객사코드")
	private String storerkey;

	/** 센터코드 */
	@Schema(description = "센터코드")
	private String dccode;

	/** 문서유형 */
	@Schema(description = "문서유형")
	private String doctype;

	/** 배송일자 */
	@Schema(description = "배송일자")
	private String deliverydt;

	/** 출고일자 */
	@Schema(description = "출고일자")
	private String slipdt;

	/** 차량번호 */
	@Schema(description = "차량번호")
	private String carno;

	/** 기사명 */
	@Schema(description = "기사명")
	private String drivername;

	/** 출차시간 */
	@Schema(description = "출차시간")
	private String dcdeparturedt;

	/** 회차 */
	@Schema(description = "회차")
	private String priority;

	/** POP번호 */
	@Schema(description = "POP번호")
	private String deliverygroup;

	/** 상차상태 */
	@Schema(description = "상차상태")
	private String loadstatus;

	/** 배송그룹 */
	@Schema(description = "배송그룹")
	private String courier;

	/** loadplanqty */
	@Schema(description = "loadplanqty")
	private BigDecimal loadplanqty;

	/** 상차완료량 */
	@Schema(description = "상차완료량")
	private BigDecimal loadcmpqty;

	/** 상차진행률 */
	@Schema(description = "상차진행률")
	private BigDecimal loadrate;

	/** 결품수량 */
	@Schema(description = "결품수량")
	private BigDecimal shortageqty;

	/** 결품율(%) */
	@Schema(description = "결품율(%)")
	private BigDecimal shortagerate;

	/** 강제완료량 */
	@Schema(description = "강제완료량")
	private BigDecimal forcecmpqty;

	/** 강제완료률 */
	@Schema(description = "강제완료률")
	private BigDecimal forcecmprate;

	/** 주문수량 */
	@Schema(description = "주문수량")
	private BigDecimal orderqty;

	/** 확정수량 */
	@Schema(description = "확정수량")
	private BigDecimal unconfirmqty;

	/** 확정량 */
	@Schema(description = "확정량")
	private BigDecimal confirmqty;

	/** 확정율 */
	@Schema(description = "확정율")
	private BigDecimal confirmrate;

	/** 검수완료여부 */
	@Schema(description = "검수완료여부")
	private String forcecmpyn;

	/** 전표번호 */
	@Schema(description = "전표번호")
	private String slipno;

	/** 전표유형 */
	@Schema(description = "전표유형")
	private String sliptype;

	/** 거래처코드 */
	@Schema(description = "거래처코드")
	private String custkey;

	/** 거래처유형 */
	@Schema(description = "거래처유형")
	private String custtype;

	/** 실착지코드 */
	@Schema(description = "실착지코드")
	private String truthcustkey;

	/** 관리처코드 */
	@Schema(description = "관리처코드")
	private String toCustkey;

	/** 관리처명 */
	@Schema(description = "관리처명")
	private String toCustname;

	/** 실착지명 */
	@Schema(description = "실착지명")
	private String truthcustname;

	/** 영업그룹 */
	@Schema(description = "영업그룹")
	private String salegroup;

	/** 사업장 */
	@Schema(description = "사업장")
	private String saledepartment;

	/** 영업조직 */
	@Schema(description = "영업조직")
	private String saleorganize;

	/** 상품코드 */
	@Schema(description = "상품코드")
	private String sku;

	/** 상품명칭 */
	@Schema(description = "상품명칭")
	private String skuname;

	/** 저장유무 */
	@Schema(description = "저장유무")
	private String channel;

	/** 저장조건 */
	@Schema(description = "저장조건")
	private String storagetype;

	/** 단위 */
	@Schema(description = "단위")
	private String uom;

	/** 출고검수량 */
	@Schema(description = "출고검수량")
	private BigDecimal inspectqty;

	/** 피킹량 */
	@Schema(description = "피킹량")
	private BigDecimal workqty;

	/** 검수진행상태 */
	@Schema(description = "검수진행상태")
	private String status;

	/** 플랜트 SAP타입 */
	@Schema(description = "플랜트 SAP타입")
	private String plant;

	/** 플랜트 */
	@Schema(description = "플랜트")
	private String plantDescr;

	/** 상차지시서 출력용 헤더 목록*/
	@Schema(description = "상차지시서 출력용 헤더 목록")
	private List<WdLoadReportDto> dsReportHeader;

	/** 출상차지시서 출력용 메모사항 목록*/
	@Schema(description = "출상차지시서 출력용 메모사항 목록")
	private List<WdLoadReportDto> dsReportMemo;

	/** 상차지시서 출력 목록*/
	@Schema(description = "상차지시서 출력 목록")
	private List<WdLoadReportDto> dsReportDetail;
}
