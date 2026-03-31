package cjfw.wms.cm.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.07.09
 * @description : 시스템작업 임시 WD Entity
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.09 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
 */
@Data
@NoArgsConstructor
public class CmSyProcessTempWdEntity extends CommonProcedureDto {

	 /** 처리유형 */
    private String processtype;

    /** 처리자 */
    private String processcreator;

    /** SPID */
    private String spid;

    /** 처리플래그 */
    private String processflag;

    /** 작업시스템 */
    private String tasksystem;

    /** 피킹배치번호 */
    private String pickBatchNo;

    /** 피킹리스트번호 */
    private String pickListNo;

    /** 피킹번호 */
    private String pickNo;

    /** 작업자 */
    private String worker;

    /** 배송일자 */
    private String deliverydate;
    
    /** 문서일자 */
    private String docdt;

    /** 문서번호 */
    private String docno;

    /** 문서라인 */
    private String docline;

    /** 주문유형 */
    private String ordertype;

    /** 전표일자 */
    private String slipdt;

    /** 전표번호 */
    private String slipno;

    /** 전표라인 */
    private String slipline;

    /** 전표유형 */
    private String sliptype;

    /** 입출고유형 */
    private String iotype;

    /** 센터코드 */
    private String dccode;

    /** 화주코드 */
    private String storerkey;

    /** 조직 */
    private String organize;

    /** 지역 */
    private String area;

    /** 상품코드 */
    private String sku;

    /** 존 */
    private String zone;

    /** 로케이션 */
    private String loc;

    /** 로트번호 */
    private String lot;

    /** 재고ID */
    private String stockid;

    /** 재고속성 */
    private String stockgrade;

    /** 재고위치 */
    private String stocktype;

    /** 주문수량 */
    private BigDecimal orderqty;

    /** 개봉수량 */
    private BigDecimal openqty;

    /** 처리수량 */
    private BigDecimal processqty;

    /** 작업수량 */
    private BigDecimal workqty;

    /** 검수수량 */
    private BigDecimal inspectqty;

    /** 확정수량 */
    private BigDecimal confirmqty;

    /** 송장수량 */
    private BigDecimal invoiceqty;

    /** 단위 */
    private String uom;

    /** 분자 */
    private BigDecimal bunja;

    /** 분모 */
    private BigDecimal bunmo;

    /** 기타수량1 */
    private BigDecimal etcqty1;

    /** 기타수량2 */
    private BigDecimal etcqty2;

    /** 혼합박스키 */
    private String mixboxkey;

    /** 작업키 */
    private String taskkey;

    /** 웨이브키 */
    private String wavekey;

    /** 원시시리얼키1 */
    private String srcserialkey1;

    /** 원시시리얼키2 */
    private String srcserialkey2;

    /** 송장번호 */
    private String invoiceno;

    /** 납품그룹 */
    private String deliverygroup;

    /** 거래처코드 */
    private String custkey;

    /** 상태 */
    private String status;

    /** 삭제여부 */
    private String delYn;

    /** 교통담당자 */
    private String trafficcop;

    /** 보관담당자 */
    private String archivecop;

    /** 정렬키 */
    private String sortkey;

    /** 작업일자 */
    private String workdt;

    /** 문서유형 */
    private String doctype;

    /** 처리메시지 */
    private String processmsg;

    /** 작업일시 */
    private String taskdt;

    /** 생성유형 */
    private String createtype;

    /** 취소유형 */
    private String canceltype;

    /** 납품일자 */
    private String deliverydt;

    /** 차량번호 */
    private String carno;

    /** 참조값1 */
    private String other01;

    /** 참조값2 */
    private String other02;

    /** 참조값3 */
    private String other03;

    /** 참조값4 */
    private String other04;

    /** 참조값5 */
    private String other05;

    /** 인쇄수량 */
    private BigDecimal printedqty;

    /** 우선순위 */
    private String priority;

    /** 등록일 */
    private String adddate;

    /** 수정일 */
    private String editdate;

    /** 등록자 */
    private String addwho;

    /** 수정자 */
    private String editwho;
    
    /** 인보이스출력키 */
    private String invoiceprintkey;    
    
    /** 인보이스유형 */
    private String invoicetype;    
    
    /** 분할관리처코드 */
    private String mngplcid;    
    
    /** 배송관리처코드 */
    private String shptoid;       
    
    /** 인보이스프린트타입 */
    private String invoiceprinttype;
    
    //20251209 printType 추가 박진우
    /** 프린트타입 */
    private String printType;

    //20251227 docName 추가 박진우
    /** 문서이름 */
    private String docName;
}
