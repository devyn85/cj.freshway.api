package cjfw.wms.om.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "문서정보상세 DTO")
//@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OmOrderCreationSTOBatchResultDetailDto extends CommonDto {

    /** 데이터번호 */
    @Schema(description = "데이터번호")
    private String serialkey;

    /** 문서일자 */
    @Schema(description = "문서일자")
    private String docdt;

    /** 문서유형 */
    @Schema(description = "문서유형")
    private String doctype;

    /** 문서번호 */
    @Schema(description = "문서번호")
    private String docno;

    /** 문서라인 */
    @Schema(description = "문서라인")
    private String docline;

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;
    
    /** 센터코드 명 */
    @Schema(description = "센터코드 명")
    private String dcname;

    /** 고객사코드 */
    @Schema(description = "고객사코드")
    private String storerkey;

    /** 주문통합문서유형 */
    @Schema(description = "주문통합문서유형")
    private String mergedoctype;

    /** 주문통합문서일자 */
    @Schema(description = "주문통합문서일자")
    private String mergedocdt;

    /** 주문통합번호 */
    @Schema(description = "주문통합번호")
    private String mergedocno;

    /** 계획문서유형 */
    @Schema(description = "계획문서유형")
    private String plandoctype;

    /** 계획문서일자 */
    @Schema(description = "계획문서일자")
    private String plandocdt;

    /** 계획문서번호 */
    @Schema(description = "계획문서번호")
    private String plandocno;

    /** 조직코드 */
    @Schema(description = "조직코드")
    private String organize;

    /** 창고코드 SAP 창고 혹은 별도의 창고 코드 */
    @Schema(description = "창고코드(SAP 창고 혹은 별도 창고 코드)")
    private String area;

    /** 쇼핑몰전표번호 */
    @Schema(description = "쇼핑몰전표번호")
    private String mallinvoice;

    /** 쇼핑몰전표라인 */
    @Schema(description = "쇼핑몰전표라인")
    private String mallinvoiceline;

    /** 배송사 */
    @Schema(description = "배송사")
    private String courier;

    /** 경유지코드 */
    @Schema(description = "경유지코드")
    private String route;

    /** 수송사 */
    @Schema(description = "수송사")
    private String carrier;

    /** 운송장유형 */
    @Schema(description = "운송장유형")
    private String invoicetype;

    /** 운송장번호 */
    @Schema(description = "운송장번호")
    private String invoiceno;

    /** 운송장라인 */
    @Schema(description = "운송장라인")
    private String invoiceline;

    /** 구매유형 */
    @Schema(description = "구매유형")
    private String potype;

    /** 구매번호 */
    @Schema(description = "구매번호")
    private String pokey;

    /** 구매라인번호 */
    @Schema(description = "구매라인번호")
    private String poline;

    /** 판매유형 */
    @Schema(description = "판매유형")
    private String sotype;

    /** 판매번호 */
    @Schema(description = "판매번호")
    private String sokey;

    /** 판매라인번호 */
    @Schema(description = "판매라인번호")
    private String soline;

    /** 고객사주문유형 */
    @Schema(description = "고객사주문유형")
    private String ordertype;

    /** 발생원인문서번호 */
    @Schema(description = "발생원인문서번호")
    private String sourcekey;

    /** 발생원인문서라인 */
    @Schema(description = "발생원인문서라인")
    private String sourceline;

    /** 합포장번호 혹은 장바구니 번호 */
    @Schema(description = "합포장번호 혹은 장바구니 번호")
    private String mixboxkey;

    /** 사이트(ORACLE ERP ,SAP R/3 에서 사용시) */
    @Schema(description = "사이트(ORACLE ERP, SAP R/3 사용 시)")
    private String site;

    /** 플랜트 SAP타입 */
    @Schema(description = "플랜트(SAP 타입)")
    private String plant;

    /** 스토리지로케이션 SAP로케이션 */
    @Schema(description = "스토리지로케이션(SAP 로케이션)")
    private String storageloc;

    /** MovementType,StockTransferType ... SAPTYPE */
    @Schema(description = "재고이동유형(MovementType/StockTransferType 등 SAP 타입)")
    private String stocktranstype;

    /** 재고 IN,OUT,INOUT */
    @Schema(description = "재고 입출고 유형(IN, OUT, INOUT)")
    private String iotype;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 대체상품코드 */
    @Schema(description = "대체상품코드")
    private String altersku;

    /** 판매상품코드 */
    @Schema(description = "판매상품코드")
    private String retailsku;

    /** 고객사원주문량 */
    @Schema(description = "고객사원주문량")
    private String storerorderqty;

    /** 고객사원주문조정량 */
    @Schema(description = "고객사원주문조정량")
    private String storeradjustqty;

    /** 고객실적수량 */
    @Schema(description = "고객실적수량")
    private String storerconfirmqty;

    /** 고객원주문단위 */
    @Schema(description = "고객원주문단위")
    private String storeruom;

    /** BUNMO UOM에 내입된 수량 */
    @Schema(description = "BUNMO UOM에 내입된 수량")
    private String bunja;

    /** BASE UOM에 내입된 수량 대부분 BASEUOM 이므로 1이다 */
    @Schema(description = "BASE UOM에 내입된 수량(대부분 1)")
    private String bunmo;

    /** 오더단위 */
    @Schema(description = "오더단위")
    private String uom;

    /** 고객최초주문수량 */
    @Schema(description = "고객최초주문수량")
    private String orderqty;

    /** 고객요청주문수정량 */
    @Schema(description = "고객요청주문수정량")
    private String orderadjustqty;

    /** 진행예정수량_전표생성단계 */
    @Schema(description = "진행예정수량_전표생성단계")
    private String openqty;

    /** 진행조정수량 */
    @Schema(description = "진행조정수량")
    private String openadjustqty;

    /** 정보진행수량(할당,전표체크 등) */
    @Schema(description = "정보진행수량(할당, 전표체크 등)")
    private String processqty;

    /** 현장작업진행수량(피킹 등)_WMS진행단계 */
    @Schema(description = "현장작업진행수량(피킹 등)_WMS진행단계")
    private String workqty;

    /** 검수수량 */
    @Schema(description = "검수수량")
    private String inspectqty;

    /** 확정수량 */
    @Schema(description = "확정수량")
    private String confirmqty;

    /** 확정중량 */
    @Schema(description = "확정중량")
    private String confirmweight;

    /** 운송장발행수량 */
    @Schema(description = "운송장발행수량")
    private String invoiceqty;

    /** QC수량 */
    @Schema(description = "QC수량")
    private String qcqty;

    /** QC처리여부 */
    @Schema(description = "QC처리여부")
    private String qcflag;

    /** 확정후 취소 유형 */
    @Schema(description = "확정후 취소 유형")
    private String returntype;

    /** 확정이후 취소량(반품, 인수거절, 확정취소 등) */
    @Schema(description = "확정이후 취소량(반품, 인수거절, 확정취소 등)")
    private String returnqty;

    /** 　*/
    @Schema(description = "")
    private String returnflag;

    /** 　*/
    @Schema(description = "")
    private String returnreason;

    /** 확정후 취소시 취소사유 */
    @Schema(description = "확정후 취소시 취소사유")
    private String returnmsg;

    /** 확정후 취소일자 시간 */
    @Schema(description = "확정후 취소일자 시간")
    private String returndate;

    /** 확정후 취소자 */
    @Schema(description = "확정후 취소자")
    private String returnwho;

    /** 취소 유형 */
    @Schema(description = "취소 유형")
    private String canceltype;

    /** 취소량(주문취소) */
    @Schema(description = "취소량(주문취소)")
    private String cancelqty;

    /** 　*/
    @Schema(description = "")
    private String cancelreason;

    /** 취소시 취소사유 */
    @Schema(description = "취소시 취소사유")
    private String cancelmsg;

    /** 취소처리구분(N,R,C,Y) */
    @Schema(description = "취소처리구분(N,R,C,Y)")
    private String cancelflag;

    /** 취소일자 시간 */
    @Schema(description = "취소일자 시간")
    private String canceldate;

    /** 취소자 */
    @Schema(description = "취소자")
    private String cancelwho;

    /** 재고 구분 LOT */
    @Schema(description = "재고 구분 LOT")
    private String lot;

    /** 로트구분값 1 */
    @Schema(description = "로트구분값 1")
    private String lottable01;

    /** 로트구분값 2 */
    @Schema(description = "로트구분값 2")
    private String lottable02;

    /** 로트구분값 3 */
    @Schema(description = "로트구분값 3")
    private String lottable03;

    /** 로트구분값 4 */
    @Schema(description = "로트구분값 4")
    private String lottable04;

    /** 로트구분값 5 */
    @Schema(description = "로트구분값 5")
    private String lottable05;

    /** 재고 구분 ID */
    @Schema(description = "재고 구분 ID")
    private String stockid;

    /** 재고 등급(ABC) */
    @Schema(description = "재고 등급(ABC)")
    private String stockgrade;

    /** 통화코드 */
    @Schema(description = "통화코드")
    private String currency;

    /** 환율 */
    @Schema(description = "환율")
    private String exchangerate;

    /** 공장도가 세액 제외 */
    @Schema(description = "공장도가(세액 제외)")
    private String factoryprice;

    /** 구매가 세액제외 */
    @Schema(description = "구매가(세액 제외)")
    private String purchaseprice;

    /** 판매가 세액제외 */
    @Schema(description = "판매가(세액 제외)")
    private String saleprice;

    /** 부가가치세 */
    @Schema(description = "부가가치세")
    private String vat;

    /** 할인율 */
    @Schema(description = "할인율")
    private String dcrate;

    /** 마진율 */
    @Schema(description = "마진율")
    private String marginrate;

    /** 우선순위 */
    @Schema(description = "우선순위")
    private String priority;

    /** 배송채널 */
    @Schema(description = "배송채널")
    private String channel;

    /** 전표가 종결처리되는 확정일자 */
    @Schema(description = "전표가 종결처리되는 확정일자")
    private String confirmdate;

    /** 배송완료일자 */
    @Schema(description = "배송완료일자")
    private String deliverydate;

    /** 전기일자 */
    @Schema(description = "전기일자")
    private String postingdate;

    /** 적용일자 */
    @Schema(description = "적용일자")
    private String effectivedate;

    /** 요청일자 */
    @Schema(description = "요청일자")
    private String requestdate;

    /** 메모1 */
    @Schema(description = "메모1")
    private String memo1;

    /** 메모2 */
    @Schema(description = "메모2")
    private String memo2;

    /** 배송 메시지 */
    @Schema(description = "배송 메시지")
    private String deliverymemo;

    /** 고객 요청 메시지 */
    @Schema(description = "고객 요청 메시지")
    private String requestmemo;

    /** 패킹방법 박스코드/컨테이너 등 */
    @Schema(description = "패킹방법(BOXxxx, CONTxxx 등)")
    private String packingmethod;

    /** 박스번호 혹은 컨테이너번호 등 */
    @Schema(description = "박스번호/컨테이너번호 등")
    private String packingno;

    /** 처리코드 */
    @Schema(description = "처리코드")
    private String processcode;

    /** 처리 전략 코드 */
    @Schema(description = "처리 전략 코드")
    private String stragetycode;

    /** 작업 전략 코드 */
    @Schema(description = "작업 전략 코드")
    private String workprocesscode;

    /** 인터페이스 전략 코드 */
    @Schema(description = "인터페이스 전략 코드")
    private String interfaceprocesscode;

    /** 물류 설비 전략 코드 */
    @Schema(description = "물류 설비 전략 코드")
    private String facilityprocesscode;

    /** 사유코드 */
    @Schema(description = "사유코드")
    private String reasoncode;

    /** 사유코드 */
    @Schema(description = "사유코드")
    private String reasonmsg;

    /** 사유코드 */
    @Schema(description = "사유코드")
    private String reasoncode2;

    /** 사유코드 */
    @Schema(description = "사유코드")
    private String reasonmsg2;

    /** 정보팀 수정 메세지 */
    @Schema(description = "정보팀 수정 메세지")
    private String sysadjustmsg;

    /** 수정가능여부 */
    @Schema(description = "수정가능여부")
    private String modifypossYn;

    /** 작업진행가능여부 */
    @Schema(description = "작업진행가능여부")
    private String procpossYn;

    /** 작업진행관련메세지 */
    @Schema(description = "작업진행관련메세지")
    private String procpossMsg;

    /** 기타정보 1 */
    @Schema(description = "기타정보 1")
    private String other01;

    /** 기타정보 2 */
    @Schema(description = "기타정보 2")
    private String other02;

    /** 기타정보 3 */
    @Schema(description = "기타정보 3")
    private String other03;

    /** 기타정보 4 */
    @Schema(description = "기타정보 4")
    private String other04;

    /** 기타정보 5 */
    @Schema(description = "기타정보 5")
    private String other05;

    /** 참조데이터1 */
    @Schema(description = "참조데이터1")
    private String reference01;

    /** 참조데이터2 */
    @Schema(description = "참조데이터2")
    private String reference02;

    /** 참조데이터3 */
    @Schema(description = "참조데이터3")
    private String reference03;

    /** 참조데이터4 */
    @Schema(description = "참조데이터4")
    private String reference04;

    /** 참조데이터5 */
    @Schema(description = "참조데이터5")
    private String reference05;

    /** 참조데이터6 */
    @Schema(description = "참조데이터6")
    private String reference06;

    /** 참조데이터7 */
    @Schema(description = "참조데이터7")
    private String reference07;

    /** 참조데이터8 */
    @Schema(description = "참조데이터8")
    private String reference08;

    /** 참조데이터9 */
    @Schema(description = "참조데이터9")
    private String reference09;

    /** 참조데이터10 */
    @Schema(description = "참조데이터10")
    private String reference10;

    /** 상태 */
    @Schema(description = "상태")
    private String status;

    /** 삭제여부 */
    @Schema(description = "삭제여부")
    private String delYn;

    /** OMS 구분 */
    @Schema(description = "OMS 구분")
    private String omsFlag;

    /** TMS  구분 */
    @Schema(description = "TMS 구분")
    private String tmsFlag;

    /** 상차 구분 */
    @Schema(description = "상차 구분")
    private String loadFlag;

    /** 하차 구분 */
    @Schema(description = "하차 구분")
    private String unloadFlag;

    /** 데이터흐름제어 */
    @Schema(description = "데이터흐름제어")
    private String trafficcop;

    /** 아카이브제어 */
    @Schema(description = "아카이브제어")
    private String archivecop;

    /** 인터페이스참조문자 */
    @Schema(description = "인터페이스참조문자")
    private String ifDestination;

    /** I/F구분 */
    @Schema(description = "I/F구분")
    private String ifFlag;

    /** 인터페이스카운트 */
    @Schema(description = "인터페이스카운트")
    private String ifCnt;

    /** 전송시간 */
    @Schema(description = "전송시간")
    private String ifDate;

    /** 인터페이스메모 */
    @Schema(description = "인터페이스메모")
    private String ifMemo;

    /** 수신파일형태 */
    @Schema(description = "수신파일형태")
    private String ifReceiveType;

    /** 수신파일명 */
    @Schema(description = "수신파일명")
    private String ifReceiveFile;

    /** 수신검증파일명 */
    @Schema(description = "수신검증파일명")
    private String ifAuditFile;

    /** 송신파일형태 */
    @Schema(description = "송신파일형태")
    private String ifSendType;

    /** 실적전송파일명 */
    @Schema(description = "실적전송파일명")
    private String ifSendFile;

    /** 처리에러코드 */
    @Schema(description = "처리에러코드")
    private String errcode;

    /** 인터페이스 에러나 전표에러 메세지 */
    @Schema(description = "인터페이스/전표 에러 메세지")
    private String errmsg;

    /** 고객실적수량2 */
    @Schema(description = "고객실적수량2")
    private String storerresultqty;

    /** 광역상차수량 */
    @Schema(description = "광역상차수량")
    private String routeinspectqty;

    /** 광역확정수량 */
    @Schema(description = "광역확정수량")
    private String routeconfirmqty;

    /** 상품벤더 */
    @Schema(description = "상품벤더")
    private String vendor;

    /** 상품공급자 */
    @Schema(description = "상품공급자")
    private String supplier;

    /** 주문자 */
    @Schema(description = "주문자")
    private String ordercreator;

    /** 가공박스예정수량 */
    @Schema(description = "가공박스예정수량")
    private String etcqty1;

    /** 가공박스확정수량 */
    @Schema(description = "가공박스확정수량")
    private String etcqty2;

    /** 가진오더여부(R or T) */
    @Schema(description = "가진오더여부(R or T)")
    private String realYn;

    /** 추가오더여부(Y/N) */
    @Schema(description = "추가오더여부(Y/N)")
    private String addYn;

    /** 가중량여부(Y/N) */
    @Schema(description = "가중량여부(Y/N)")
    private String tempYn;

    /** 화주이체여부(Y/N) */
    @Schema(description = "화주이체여부(Y/N)")
    private String moveYn;

    /** 매핑key */
    @Schema(description = "매핑key")
    private String mapkeyNo;

    /** 매핑key항번 */
    @Schema(description = "매핑key항번")
    private String mapkeyLine;

    /** 이력번호SEQ */
    @Schema(description = "이력번호SEQ")
    private String issueNo;

    /** 원오더번호 */
    @Schema(description = "원오더번호")
    private String refDocNo;

    /** 원오더항번 */
    @Schema(description = "원오더항번")
    private String refDocLine;

    /** 취소종결요청 */
    @Schema(description = "취소종결요청")
    private String cancelEndYn;

    /** 　*/
    @Schema(description = "")
    private String mapDiv;

    /** 　*/
    @Schema(description = "")
    private String quickYn;

    /** 　*/
    @Schema(description = "")
    private String refDocid;

    /** 　*/
    @Schema(description = "")
    private String refCustDocid;

    /** 배송시간 */
    @Schema(description = "배송시간")
    private String openTime;

    /** 최초등록시간 */
    @Schema(description = "최초등록시간")
    private String adddate;

    /** 최종변경시간 */
    @Schema(description = "최종변경시간")
    private String editdate;

    /** 최초등록자 */
    @Schema(description = "최초등록자")
    private String addwho;

    /** 최종변경자 */
    @Schema(description = "최종변경자")
    private String editwho;
}
