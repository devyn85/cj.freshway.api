package cjfw.wms.tm.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.11.12
 * @description : 수송배차조정 Entity
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.12 JiHoPark  생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "수송배차조정 Entity")
public class TmTransportControlEntity extends CommonProcedureDto {
	/** 데이터번호/노선번호 */
	@Schema(description = "데이터번호/노선번호")
	private BigDecimal serialkey;

	/** 노선명 */
	@Schema(description = "노선명")
	private String routeNm;

	/** 출발센터 */
	@Schema(description = "출발센터")
	private String fromDccode;

	/** 출발센터명 */
	@Schema(description = "출발센터명")
	private String fromDccodename;

	/** 도착센터 */
	@Schema(description = "도착센터")
	private String toDccode;

	/** 도착센터명 */
	@Schema(description = "도착센터명")
	private String toDccodename;

	/** 시작일자 */
	@Schema(description = "시작일자")
	private String fromdate;

	/** 종료일자 */
	@Schema(description = "종료일자")
	private String todate;

	/** 일자 */
	@Schema(description = "일자")
	private String deliverydt;

	/** 차량번호 */
	@Schema(description = "차량번호")
	private String carno;

	/** 운송사 */
	@Schema(description = "운송사")
	private String courier;

	/** 운송사명 */
	@Schema(description = "운송사명")
	private String couriername;

	/** 2차운송사 */
	@Schema(description = "2차운송사")
	private String slaveCourier;

	/** 2차운송사명 */
	@Schema(description = "2차운송사명")
	private String slaveCouriername;

	/** 톤급 */
	@Schema(description = "톤급")
	private String carcapacity;

	/** 노선 */
	@Schema(description = "노선")
	private String carrierRouteId;

	/** 출발센터 */
	@Schema(description = "출발센터")
	private String dccode;

	/** 상태 */
	@Schema(description = "상태")
	private String status;

	/** 상태명 */
	@Schema(description = "상태명")
	private String statusname;

	/** 경유여부 */
	@Schema(description = "경유여부")
	private String routeYn;

	/** 운행횟수 */
	@Schema(description = "운행횟수")
	private BigDecimal carrierCnt;

	/** 비용 */
	@Schema(description = "비용")
	private BigDecimal totPrice;

	/** 비고 */
	@Schema(description = "비고")
	private String rmk;

	/** 파레트번호 */
	@Schema(description = "파레트번호")
	private String pltId;

	/** 상품코드 */
	@Schema(description = "상품코드")
	private String sku;

	/** 상품명 */
	@Schema(description = "상품명")
	private String skuname;

	/** 단위 */
	@Schema(description = "단위")
	private String uom;

	/** 수량 */
	@Schema(description = "수량")
	private BigDecimal qty;

	/** 수송 SERIALKEY */
	@Schema(description = "수송 SERIALKEY")
	private BigDecimal carrierSerialkey;

	/** 삭제여부 */
	@Schema(description = "삭제여부")
	private String delYn;

	/** 수정자 */
	@Schema(description = "등록일시")
	private String adddate;

	/** 최종변경시간 */
	@Schema(description = "수정일시")
	private String editdate;

	/** 등록자 */
	@Schema(description = "등록자")
	private String addwho;

	/** 수정자 */
	@Schema(description = "수정자")
	private String editwho;

	/** 계약유형 */
	@Schema(description = "계약유형")
	private String contracttype;

	/** 저장조건 */
	@Schema(description = "저장조건")
	private String storagetype;
}
