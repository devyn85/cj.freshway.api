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
 * @author : sss (kduimux@cj.net)
 * @date : 2025.08.25
 * @description : 재고조사결과처리 Request Entity Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "재고조사결과처리 Entity") 
public class StInquiryMoveEntity extends CommonTriggerDto {
	/** 시리얼키 */
	@Schema(description = "시리얼키")
	private Long serialkey;

	/** 센터코드 */
	@Schema(description = "센터코드")
	private String dccode;

	/** 조사일자 */
	@Schema(description = "조사일자")
	private String inquirydt;

	/** 조사번호 */
	@Schema(description = "조사번호")
	private String inquiryno;

	/** 조사명 */
	@Schema(description = "조사명")
	private String inquiryName;

	/** 우선순위 */
	@Schema(description = "우선순위")
	private Integer priority;

	/** 조사유형 */
	@Schema(description = "조사유형")
	private String inquirytype;

	/** 화주코드 */
	@Schema(description = "화주코드")
	private String storerkey;

	/** 조직 */
	@Schema(description = "조직")
	private String organize;

	/** 구역 */
	@Schema(description = "구역")
	private String area;

	/** 품목코드 */
	@Schema(description = "품목코드")
	private String sku;

	/** 로케이션 */
	@Schema(description = "로케이션")
	private String loc;

	/** 로트 */
	@Schema(description = "로트")
	private String lot;

	/** 재고ID */
	@Schema(description = "재고ID")
	private String stockid;

	/** 재고등급 */
	@Schema(description = "재고등급")
	private String stockgrade;

	/** 지시수량 */
	@Schema(description = "지시수량")
	private BigDecimal orderqty;

	/** 스캔수량A */
	@Schema(description = "스캔수량A")
	private BigDecimal scanqtyA;

	/** 스캔수량B */
	@Schema(description = "스캔수량B")
	private BigDecimal scanqtyB;

	/** 재고수량 */
	@Schema(description = "재고수량")
	private BigDecimal stockqty;

	/** 체크여부 */
	@Schema(description = "체크여부")
	private String checkYn;

	/** 사유코드 */
	@Schema(description = "사유코드")
	private String reasoncode;

	/** 사유메시지 */
	@Schema(description = "사유메시지")
	private String reasonmsg;

	/** 작업자A */
	@Schema(description = "작업자A")
	private String workerA;

	/** 작업자B */
	@Schema(description = "작업자B")
	private String workerB;

	/** 소스타입 */
	@Schema(description = "소스타입")
	private String sourcetype;

	/** 소스키 */
	@Schema(description = "소스키")
	private String sourcekey;

	/** 소스라인 */
	@Schema(description = "소스라인")
	private String sourceline;

	/** 메모 */
	@Schema(description = "메모")
	private String memo;

	/** 상태 */
	@Schema(description = "상태")
	private String status;

	/** 삭제여부 */
	@Schema(description = "삭제여부")
	private String delYn;

	/** 트래픽캅 */
	@Schema(description = "트래픽캅")
	private String trafficcop;

	/** 아카이브캅 */
	@Schema(description = "아카이브캅")
	private String archivecop;

	/** 커밋여부 */
	@Schema(description = "커밋여부")
	private String commitYn;

	/** 커밋일자 */
	@Schema(description = "커밋일자")
	private java.util.Date commitdate;

	/** 커밋자 */
	@Schema(description = "커밋자")
	private String commitwho;

	/** 로트타입 */
	@Schema(description = "로트타입")
	private String lottype;

	/** 수량타입 */
	@Schema(description = "수량타입")
	private String qtytype;

	/** 예상체크 */
	@Schema(description = "예상체크")
	private String chkexpected;

	/** 예약체크 */
	@Schema(description = "예약체크")
	private String chkreserve;

	/** 할당체크 */
	@Schema(description = "할당체크")
	private String chkallocated;

	/** 피킹체크 */
	@Schema(description = "피킹체크")
	private String chkpicked;

	/** 등록일자 */
	@Schema(description = "등록일자")
	private java.util.Date adddate;

	/** 수정일자 */
	@Schema(description = "수정일자")
	private java.util.Date editdate;

	/** 등록자 */
	@Schema(description = "등록자")
	private String addwho;

	/** 수정자 */
	@Schema(description = "수정자")
	private String editwho;

	/** 시리얼번호 */
	@Schema(description = "시리얼번호")
	private String serialno;

	/** 변환시리얼번호 */
	@Schema(description = "변환시리얼번호")
	private String convserialno;

	/** FROM 로트 */
	@Schema(description = "FROM 로트")
	private String fromLot;

	/** 모바일등록여부 */
	@Schema(description = "모바일등록여부")
	private String mobileAddYn;

	/** 사용자명 */
	@Schema(description = "사용자명")
	private String username;
  
}
