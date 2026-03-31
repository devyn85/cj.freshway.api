package cjfw.wms.om.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "문서정보 DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OmOrderCreationSTOBatchResultHeadDto extends CommonDto {


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

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;

    /** 센터코드 명 */
    @Schema(description = "센터코드 명")
    private String dcname;
    
    /** 고객사코드 */
    @Schema(description = "고객사코드")
    private String storerkey;

    /** 조직코드 */
    @Schema(description = "조직코드")
    private String organize;

    /** 주문통합유형 */
    @Schema(description = "주문통합유형")
    private String mergedoctype;

    /** 주문통합일자 */
    @Schema(description = "주문통합일자")
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

    /** 쇼핑몰 */
    @Schema(description = "쇼핑몰")
    private String shoppingmall;

    /** 쇼핑몰전표번호 */
    @Schema(description = "쇼핑몰전표번호")
    private String mallinvoice;

    /** 배송사코드 */
    @Schema(description = "배송사코드")
    private String courier;

    /** 경유지코드 */
    @Schema(description = "경유지코드")
    private String route;

    /** 수송사코드 */
    @Schema(description = "수송사코드")
    private String carrier;

    /** 인보이스유형 */
    @Schema(description = "인보이스유형")
    private String invoicetype;

    /** 운송장번호 */
    @Schema(description = "운송장번호")
    private String invoiceno;

    /** 구매유형 */
    @Schema(description = "구매유형")
    private String potype;

    /** 구매번호 */
    @Schema(description = "구매번호")
    private String pokey;

    /** 형판매유 */
    @Schema(description = "형판매유")
    private String sotype;

    /** 판매번호 */
    @Schema(description = "판매번호")
    private String sokey;

    /** 고객사주문유형 */
    @Schema(description = "고객사주문유형")
    private String ordertype;

    /** 발생원인문서번호 */
    @Schema(description = "발생원인문서번호")
    private String sourcekey;

    /** FROM 거래처코드 */
    @Schema(description = "FROM 거래처코드")
    private String fromCustkey;

    /** FROM 거래처명 */
    @Schema(description = "FROM 거래처명")
    private String fromCustname;

    /** FROM 거래처 유형 */
    @Schema(description = "FROM 거래처 유형")
    private String fromCusttype;

    /** FROM조직 */
    @Schema(description = "FROM조직")
    private String fromOrganize;

    /** FROM_정산처코드 */
    @Schema(description = "FROM_정산처코드")
    private String fromBilltokey;

    /** FROM 국가코드 */
    @Schema(description = "FROM 국가코드")
    private String fromCountry;

    /** FROM 주,도 */
    @Schema(description = "FROM 주,도")
    private String fromState;

    /** FROM 시,읍,면 */
    @Schema(description = "FROM 시,읍,면")
    private String fromCity;

    /** FROM 우편번호 */
    @Schema(description = "FROM 우편번호")
    private String fromZipcode;

    /** FROM_화면표시용주소 */
    @Schema(description = "FROM_화면표시용주소")
    private String fromAddressDisp;

    /** FROM 기본주소 */
    @Schema(description = "FROM 기본주소")
    private String fromAddress1;

    /** FROM 상세주소 */
    @Schema(description = "FROM 상세주소")
    private String fromAddress2;

    /** FROM 확장주소3 */
    @Schema(description = "FROM 확장주소3")
    private String fromAddress3;

    /** FROM 확장주소4 */
    @Schema(description = "FROM 확장주소4")
    private String fromAddress4;

    /** 화면표시용전화번호 */
    @Schema(description = "화면표시용전화번호")
    private String fromPhone1Disp;

    /** FROM 전화번호1 */
    @Schema(description = "FROM 전화번호1")
    private String fromPhone1;

    /** 화면표시용전화번호 */
    @Schema(description = "화면표시용전화번호")
    private String fromPhone2Disp;

    /** FROM 전화번호2 */
    @Schema(description = "FROM 전화번호2")
    private String fromPhone2;

    /** FROM 팩스번호 */
    @Schema(description = "FROM 팩스번호")
    private String fromFax;

    /** FROM 사업자 등록 등록번호 */
    @Schema(description = "FROM 사업자 등록 등록번호")
    private String fromVatno;

    /** FROM 사업자 등록 사업주명 */
    @Schema(description = "FROM 사업자 등록 사업주명")
    private String fromVatowner;

    /** FROM 사업자 등록 업태 */
    @Schema(description = "FROM 사업자 등록 업태")
    private String fromVattype;

    /** FROM 사업자 등록 종목 */
    @Schema(description = "FROM 사업자 등록 종목")
    private String fromVatcategory;

    /** FROM 사업자 등록 우편번호 */
    @Schema(description = "FROM 사업자 등록 우편번호")
    private String fromVatzipcode;

    /** FROM 사업자 등록 기본주소 */
    @Schema(description = "FROM 사업자 등록 기본주소")
    private String fromVataddress1;

    /** FROM 사업자 등록 상세주소 */
    @Schema(description = "FROM 사업자 등록 상세주소")
    private String fromVataddress2;

    /** FROM 사업자 등록 확장주소3 */
    @Schema(description = "FROM 사업자 등록 확장주소3")
    private String fromVataddress3;

    /** FROM 사업자 등록 확장주소4 */
    @Schema(description = "FROM 사업자 등록 확장주소4")
    private String fromVataddress4;

    /** FROM 사업자 등록 전화번호 */
    @Schema(description = "FROM 사업자 등록 전화번호")
    private String fromVatphone;

    /** FROM 사업자 등록 팩스번호 */
    @Schema(description = "FROM 사업자 등록 팩스번호")
    private String fromVatfax;

    /** FROM 관리 사원명1 */
    @Schema(description = "FROM 관리 사원명1")
    private String fromEmpname1;

    /** FROM 관리 사원명2 */
    @Schema(description = "FROM 관리 사원명2")
    private String fromEmpname2;

    /** FROM 관리 사원 전화번호1 */
    @Schema(description = "FROM 관리 사원 전화번호1")
    private String fromEmpphone1;

    /** FROM 관리 사원 전화번호2 */
    @Schema(description = "FROM 관리 사원 전화번호2")
    private String fromEmpphone2;

    /** TO 거래처코드 */
    @Schema(description = "TO 거래처코드")
    private String toCustkey;

    /** TO 거래처명 */
    @Schema(description = "TO 거래처명")
    private String toCustname;

    /** TO 거래처 유형 */
    @Schema(description = "TO 거래처 유형")
    private String toCusttype;

    /** TO조직 */
    @Schema(description = "TO조직")
    private String toOrganize;

    /** TO_정산처코드 */
    @Schema(description = "TO_정산처코드")
    private String toBilltokey;

    /** TO 국가코드 */
    @Schema(description = "TO 국가코드")
    private String toCountry;

    /** TO 주,도 */
    @Schema(description = "TO 주,도")
    private String toState;

    /** TO 시,읍,면 */
    @Schema(description = "TO 시,읍,면")
    private String toCity;

    /** TO 우편번호 */
    @Schema(description = "TO 우편번호")
    private String toZipcode;

    /** TO_화면표시용주소 */
    @Schema(description = "TO_화면표시용주소")
    private String toAddressDisp;

    /** TO 기본주소 */
    @Schema(description = "TO 기본주소")
    private String toAddress1;

    /** TO 상세주소 */
    @Schema(description = "TO 상세주소")
    private String toAddress2;

    /** TO 확장주소3 */
    @Schema(description = "TO 확장주소3")
    private String toAddress3;

    /** TO 확장주소4 */
    @Schema(description = "TO 확장주소4")
    private String toAddress4;

    /** TO_화면표시용전화번호 */
    @Schema(description = "TO_화면표시용전화번호")
    private String toPhone1Disp;

    /** TO 전화번호1 */
    @Schema(description = "TO 전화번호1")
    private String toPhone1;

    /** TO_화면표시용전화번호 */
    @Schema(description = "TO_화면표시용전화번호")
    private String toPhone2Disp;

    /** TO 전화번호2 */
    @Schema(description = "TO 전화번호2")
    private String toPhone2;

    /** TO 팩스번호 */
    @Schema(description = "TO 팩스번호")
    private String toFax;

    /** TO 사업자 등록 등록번호 */
    @Schema(description = "TO 사업자 등록 등록번호")
    private String toVatno;

    /** TO 사업자 등록 사업주명 */
    @Schema(description = "TO 사업자 등록 사업주명")
    private String toVatowner;

    /** TO 사업자 등록 업태 */
    @Schema(description = "TO 사업자 등록 업태")
    private String toVattype;

    /** TO 사업자 등록 종목 */
    @Schema(description = "TO 사업자 등록 종목")
    private String toVatcategory;

    /** TO 사업자 등록 우편번호 */
    @Schema(description = "TO 사업자 등록 우편번호")
    private String toVatzipcode;

    /** TO 사업자 등록 기본주소 */
    @Schema(description = "TO 사업자 등록 기본주소")
    private String toVataddress1;

    /** TO 사업자 등록 상세주소 */
    @Schema(description = "TO 사업자 등록 상세주소")
    private String toVataddress2;

    /** TO 사업자 등록 확장주소3 */
    @Schema(description = "TO 사업자 등록 확장주소3")
    private String toVataddress3;

    /** TO 사업자 등록 확장주소4 */
    @Schema(description = "TO 사업자 등록 확장주소4")
    private String toVataddress4;

    /** TO 사업자 등록 전화번호 */
    @Schema(description = "TO 사업자 등록 전화번호")
    private String toVatphone;

    /** TO 사업자 등록 팩스번호 */
    @Schema(description = "TO 사업자 등록 팩스번호")
    private String toVatfax;

    /** TO 관리 사원명1 */
    @Schema(description = "TO 관리 사원명1")
    private String toEmpname1;

    /** TO 관리 사원명2 */
    @Schema(description = "TO 관리 사원명2")
    private String toEmpname2;

    /** TO 관리 사원 전화번호1 */
    @Schema(description = "TO 관리 사원 전화번호1")
    private String toEmpphone1;

    /** TO 관리 사원 전화번호2 */
    @Schema(description = "TO 관리 사원 전화번호2")
    private String toEmpphone2;

    /** ordercreator */
    @Schema(description = "")
    private String ordercreator;

    /** 우선순위 */
    @Schema(description = "우선순위")
    private String priority;

    /** 배송채널 */
    @Schema(description = "배송채널")
    private String channel;

    /** 디테일라인수(I/F 체크용일경우 DM_D의 트리거에서 자동 산정을 빼야함) */
    @Schema(description = "디테일라인수(I/F 체크용일경우 DM_D의 트리거에서 자동 산정을 빼야함)")
    private String totline;

    /** 고객최초주문수량 */
    @Schema(description = "고객최초주문수량")
    private String orderqty;

    /** 진행예정수량_전표생성단계 */
    @Schema(description = "진행예정수량_전표생성단계")
    private String openqty;

    /** 정보진행수량(할당,전표체크 등) */
    @Schema(description = "정보진행수량(할당,전표체크 등)")
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

    /** 운송장발행수량 */
    @Schema(description = "운송장발행수량")
    private String invoiceqty;

    /** 주문취소량 */
    @Schema(description = "주문취소량")
    private String cancelqty;

    /** 확정이후 취소량(반품, 인수거절, 확정취소 등) */
    @Schema(description = "확정이후 취소량(반품, 인수거절, 확정취소 등)")
    private String returnqty;

    /** 할인율 */
    @Schema(description = "할인율")
    private String dcrate;

    /** 마진율 */
    @Schema(description = "마진율")
    private String marginrate;

    /** 배송 메시지 */
    @Schema(description = "배송 메시지")
    private String deliverymemo;

    /** 고객 요청 메시지 */
    @Schema(description = "고객 요청 메시지")
    private String requestmemo;

    /** 전표가 확정되는 시간 */
    @Schema(description = "전표가 확정되는 시간")
    private String confirmdate;

    /** 구매일자 */
    @Schema(description = "구매일자")
    private String purchasedate;

    /** 전기일자 */
    @Schema(description = "전기일자")
    private String postingdate;

    /** 적용일자 */
    @Schema(description = "적용일자")
    private String effectivedate;

    /** 배송일자 */
    @Schema(description = "배송일자")
    private String deliverydate;

    /** 물건이 중간에 거쳐가는곳이 있다면 경유지코드를 넣는다 광역배송같은 경우 */
    @Schema(description = "물건이 중간에 거쳐가는곳이 있다면 경유지코드를 넣는다(광역배송 등)")
    private String deliveryroute;

    /** 배송방법 */
    @Schema(description = "배송방법")
    private String deliverytype;

    /** 배송그룹 및 배차번호 */
    @Schema(description = "배송그룹 및 배차번호")
    private String deliverygroup;

    /** C:신용(Credit) ,P:선불 (Prepayment) ,D:착불 (De */
    @Schema(description = "배송운임 결제유형(C:신용, P:선불, D:착불)")
    private String deliveryfeetype;

    /** 배송운임 */
    @Schema(description = "배송운임")
    private String deliveryfee;

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

    /** 메모1 */
    @Schema(description = "메모1")
    private String memo1;

    /** 메모2 */
    @Schema(description = "메모2")
    private String memo2;

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

    /** 주문취소상태 */
    @Schema(description = "주문취소상태")
    private String cancelstatus;

    /** 확정후 상태 */
    @Schema(description = "확정후 상태")
    private String returnstatus;

    /** 삭제여부 */
    @Schema(description = "삭제여부")
    private String delYn;

    /** OMS 구분 */
    @Schema(description = "OMS 구분")
    private String omsFlag;

    /** TMS 구분 */
    @Schema(description = "TMS 구분")
    private String tmsFlag;

    /** 상차 플래그 */
    @Schema(description = "상차 플래그")
    private String loadFlag;

    /** 하차 플래그 */
    @Schema(description = "하차 플래그")
    private String unloadFlag;

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

    /** 작업진행가능여부 */
    @Schema(description = "작업진행가능여부")
    private String procpossYn;

    /** 작업진행관련사항 */
    @Schema(description = "작업진행관련사항")
    private String procpossMsg;

    /** 데이터흐름제어 */
    @Schema(description = "데이터흐름제어")
    private String trafficcop;

    /** 아카이브제어 */
    @Schema(description = "아카이브제어")
    private String archivecop;

    /** 본점코드 */
    @Schema(description = "본점코드")
    private String hdoId;

    /** 판매처코드 */
    @Schema(description = "판매처코드")
    private String slstId;

    /** 배송관리처코드 */
    @Schema(description = "배송관리처코드")
    private String shtpoId;

    /** 분할관리처코드 */
    @Schema(description = "분할관리처코드")
    private String mngplcId;

    /** 가공박스예정수량 */
    @Schema(description = "가공박스예정수량")
    private String etcqty1;

    /** 가공박스확정수량 */
    @Schema(description = "가공박스확정수량")
    private String etcqty2;

    /** 가진오더여부(R or T) */
    @Schema(description = "가진오더여부(R or T)")
    private String realYn;

    /** 매핑구분(R or T) */
    @Schema(description = "매핑구분(R or T)")
    private String mapDiv;

    /** 추가오더여부(Y/N) */
    @Schema(description = "추가오더여부(Y/N)")
    private String addYn;

    /** 매핑KEY */
    @Schema(description = "매핑KEY")
    private String mapkeyNo;

    /** 원오더번호 */
    @Schema(description = "원오더번호")
    private String refDocNo;

    /** 타사주문그룹 */
    @Schema(description = "타사주문그룹")
    private String otcoGrpcd;

    /** B2C여부 */
    @Schema(description = "B2C여부")
    private String b2cYn;

    /** 택배주문여부 */
    @Schema(description = "택배주문여부")
    private String expressYn;

    /** 3자물류 주문유형 */
    @Schema(description = "3자물류 주문유형")
    private String tplType;

    /** 3자물류 주문상세유형 */
    @Schema(description = "3자물류 주문상세유형")
    private String tplDtlType;

    /** 3자물류 협력사ID */
    @Schema(description = "3자물류 협력사ID")
    private String tplBcnrId;

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
