package cjfw.wms.om.dto;

import java.math.BigDecimal;

import cjfw.wms.common.annotation.MaskingTelno;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.09.12
 * @description : 저장품자동발주 기능을 구현한 Res DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "저장품자동발주 조회")
public class OmPurchaseStorageAutoTotalResDto extends CommonProcedureDto {
	@Schema(description = "체크여부")
    private String checkYn;
	
	@Schema(description = "고객사 키")
    private String custKey;

    @Schema(description = "고객사명")
    private String custName;

    @Schema(description = "경로 코드")
    private String route;

    @Schema(description = "경로 조직 코드")
    private String routeOrganize;

    @Schema(description = "경로 조직명")
    private String routeOrganizeNm;

    @Schema(description = "물류센터 코드")
    private String dcCode;

    @Schema(description = "물류센터명")
    private String dcName;

    @Schema(description = "조직 코드")
    private String organize;

    @Schema(description = "조직명")
    private String organizeName;

    @Schema(description = "상품 코드")
    private String sku;

    @Schema(description = "상품명")
    private String skuName;

    @Schema(description = "인수도 형태")
    private String deliveryType;

    @Schema(description = "바이어 키")
    private String buyerKey;
    
    @Schema(description = "매입처 키")
    private String storerKey;

    @Schema(description = "고객 유형")
    private String custType;

    @Schema(description = "고객 유형명")
    private String custTypeNm;

    @Schema(description = "Area")
    private String area;

    @Schema(description = "채널")
    private String channel;

    @Schema(description = "채널명")
    private String channelNm;

    @Schema(description = "시리얼번호 관리 여부")
    private String serialYn;

    @Schema(description = "PO 일자")
    private String poDt;

    @Schema(description = "출발 DC 코드")
    private String fromDcCode;

    @Schema(description = "출발 조직 코드")
    private String fromOrganize;

    @Schema(description = "빈 필드")
    private String emptyField;

    @Schema(description = "코드 값 (STD)")
    private String std;

    @Schema(description = "Zero 값 (0)")
    private BigDecimal zero;

    // --- 재고 및 오더 관련 수량 ---
    @Schema(description = "재고 수량")
    private BigDecimal stockQty;

    @Schema(description = "박스 수량")
    private BigDecimal boxQty;

    @Schema(description = "재고 수량 (Display)")
    private BigDecimal stockQtyDisp;

    @Schema(description = "계약 재고 수량")
    private BigDecimal stockQtyContract;

    @Schema(description = "외부창고 재고 수량")
    private BigDecimal stockQtyOutDisp;

    @Schema(description = "재주문점")
    private BigDecimal reorderPoint;

    @Schema(description = "목표 재고량")
    private BigDecimal stockGoal;

    @Schema(description = "총 출고 요청 수량 (WD QTY)")
    private BigDecimal qty;

    @Schema(description = "금일 출고 수량 (SHIP QTY)")
    private BigDecimal shipQty;

    @Schema(description = "업체 확정 수량")
    private BigDecimal vendorConfirmQty;

    @Schema(description = "금일 입고 수량 (RECEIVE QTY)")
    private BigDecimal receiveQty;

    @Schema(description = "차후 입고 예정 수량 (AFRECEIVE QTY)")
    private BigDecimal afReceiveQty;

    @Schema(description = "예상 안전재고 수량 (PSBQTY)")
    private BigDecimal psbQty;

    @Schema(description = "예상 안전재고 수량 2 (PSBQTY2)")
    private BigDecimal psbQty2;

    @Schema(description = "기타 수량 1")
    private BigDecimal etcQty1;

    @Schema(description = "기타 수량 2")
    private BigDecimal etcQty2;

    @Schema(description = "기타 수량 3")
    private BigDecimal etcQty3;

    @Schema(description = "할당 피킹 수량")
    private BigDecimal qtyPicked;

    @Schema(description = "이동 확정 수량")
    private BigDecimal moveConfirmQty;

    @Schema(description = "가용 수량 1 (PSBQTY2 + MOVE_CONFIRMQTY)")
    private BigDecimal fusibilityQty;

    @Schema(description = "가용 수량 2 (STOCKQTY_DISP - QTY)")
    private BigDecimal fusibilityQty2;

    @Schema(description = "CONFIRM 수량 (자동산정된 확정 발주 수량)")
    private BigDecimal confirmQty;

    @Schema(description = "CONFIRM 수량 (재주문점/목표재고 기준, 발주 단위 적용 전)")
    private BigDecimal confirmQtyOut;

    @Schema(description = "CONFIRM 수량 (DUMMY 포함, 발주 단위 적용 전)")
    private BigDecimal confirmQtyAdd;

    // --- 수량 계산 보조 필드 ---
    @Schema(description = "단위 (UOM)")
    private String uom;

    @Schema(description = "구매 단위")
    private String purchaseUom;

    @Schema(description = "박스당 수량 (QTYPERBOX)")
    private BigDecimal boxPerQty;

    @Schema(description = "분자")
    private BigDecimal bunja;

    @Schema(description = "분모")
    private BigDecimal bunmo;

    @Schema(description = "파레트 단위 (LAYERPERPLT * BOXPERLAYER)")
    private BigDecimal palletUnit;

    @Schema(description = "BOX 계산값 (EA -> BOX)")
    private BigDecimal box;

    @Schema(description = "구매 단위당 EA 변환값")
    private BigDecimal eaCal;

    @Schema(description = "구매 단위당 KG 변환값")
    private BigDecimal kgCal;

    @Schema(description = "구매 단위당 BOX 변환값")
    private BigDecimal boxCal;

    @Schema(description = "발주 수량 단위")
    private BigDecimal orderQtyUnit;

    @Schema(description = "CAL BOX")
    private BigDecimal calBox;

    @Schema(description = "REAL ORDER BOX")
    private BigDecimal realOrderBox;

    @Schema(description = "REAL CFM BOX")
    private BigDecimal realCfmBox;

    @Schema(description = "TRAN BOX")
    private BigDecimal tranBox;

    @Schema(description = "EA (계산 필드)")
    private BigDecimal ea;

    @Schema(description = "KG (계산 필드)")
    private BigDecimal kg;

    // --- 기간 및 일수 관련 필드 ---
    @Schema(description = "평균 재고 보유일")
    private BigDecimal stockAvgDay;

    @Schema(description = "발주 리드타임 영업일 계산값 1")
    private BigDecimal valFnGetDayCount;

    @Schema(description = "리드 타임")
    private BigDecimal leadTime;

    @Schema(description = "리드 타임 2")
    private BigDecimal leadTime2;

    @Schema(description = "재고 보유일")
    private BigDecimal stockDay;

    @Schema(description = "안전 재고 보유일")
    private BigDecimal stockDay2;

    @Schema(description = "발주일/입고일 차이 (DATEDIFF)")
    private BigDecimal dateDiff;

    @Schema(description = "영업일 수 (BIZDATE)")
    private BigDecimal bizDate;

    @Schema(description = "도착 예정일 (RECEIVEDT)")
    private String receiveDt;

    @Schema(description = "도착 예정일 2 (RECEIVEDT2)")
    private String receiveDt2;

    @Schema(description = "도착 예정일 (CAL)")
    private String receiveDtCal;

    @Schema(description = "실행일수")
    private BigDecimal execDay;

    @Schema(description = "자동 실행일수")
    private BigDecimal autoExecDay;

    @Schema(description = "기준일자 (STD_DT)")
    private String stdDt;

    @Schema(description = "출고 제외 수량 (DATEDIFF_QTY)")
    private BigDecimal dateDiffQty;

    @Schema(description = "확정 오더량 보유일 (CONFIRMQTY_STOCKDAY)")
    private BigDecimal confirmQtyStockDay;

    @Schema(description = "기존 재고 보유일 (STOCKQTY_DISP_STOCKDAY)")
    private BigDecimal stockQtyDispStockDay;

    @Schema(description = "유통기한 기준일")
    private String duration;
    
    @Schema(description = "최소 잔여 유효기간/총 유효기간")
    private String durationTerm;

    // --- 주간 출고 및 주문 정보 ---
    @Schema(description = "출고 일수 1주차")
    private BigDecimal shipDay1W;

    @Schema(description = "출고 일수 2주차")
    private BigDecimal shipDay2W;

    @Schema(description = "출고 일수 3주차")
    private BigDecimal shipDay3W;

    @Schema(description = "출고 수량 변동 1주차")
    private String shipQtyChg1W;

    @Schema(description = "출고 수량 변동 2주차")
    private String shipQtyChg2W;

    @Schema(description = "출고 수량 변동 3주차")
    private String shipQtyChg3W;

    @Schema(description = "출고 수량 1주차")
    private BigDecimal shipQty1W;

    @Schema(description = "출고 수량 2주차")
    private BigDecimal shipQty2W;

    @Schema(description = "출고 수량 3주차")
    private BigDecimal shipQty3W;

    @Schema(description = "주문 건수 1주차")
    private BigDecimal ordCnt1W;

    @Schema(description = "주문 건수 2주차")
    private BigDecimal ordCnt2W;

    @Schema(description = "주문 건수 3주차")
    private BigDecimal ordCnt3W;
    
    @Schema(description = "주문 수량 1일")
    private BigDecimal ord1Day;    
    
    @Schema(description = "주문 수량 2일")
    private BigDecimal ord2Day;
    
    @Schema(description = "주문 수량 3일")
    private BigDecimal ord3Day;
    
    @Schema(description = "주문 수량 4일")
    private BigDecimal ord4Day;
    
    @Schema(description = "주문 수량 5일")
    private BigDecimal ord5Day;
    
    @Schema(description = "주문 수량 6일")
    private BigDecimal ord6Day;
    
    @Schema(description = "주문 수량 7일")
    private BigDecimal ord7Day;    
    
    @Schema(description = "주문 수량 8일")
    private BigDecimal ord8Day;
    
    @Schema(description = "주문 수량 9일")
    private BigDecimal ord9Day;
    
    @Schema(description = "주문 수량 10일")
    private BigDecimal ord10Day;
    
    @Schema(description = "주문 수량 11일")
    private BigDecimal ord11Day;
    
    @Schema(description = "주문 수량 12일")
    private BigDecimal ord12Day;
    
    @Schema(description = "주문 수량 13일")
    private BigDecimal ord13Day;
    
    @Schema(description = "주문 수량 14일")
    private BigDecimal ord14Day;

    @Schema(description = "확정 오더 수량 1주차")
    private BigDecimal confirmQty1Week;

    @Schema(description = "확정 오더 수량 2주차")
    private BigDecimal confirmQty2Week;

    @Schema(description = "확정 오더 수량 3주차")
    private BigDecimal confirmQty3Week;

    @Schema(description = "확정 오더 수량 4주차")
    private BigDecimal confirmQty4Week;

    @Schema(description = "확정 오더 수량 1개월")
    private BigDecimal confirmQty1Month;

    @Schema(description = "확정 오더 수량 1개월 평균")
    private BigDecimal confirmQty1MonthAvg;

    @Schema(description = "일별 출고 수량 1일차")
    private BigDecimal afterDayWdQty01;

    @Schema(description = "일별 출고 수량 2일차")
    private BigDecimal afterDayWdQty02;

    @Schema(description = "일별 출고 수량 3일차")
    private BigDecimal afterDayWdQty03;

    @Schema(description = "일별 출고 수량 4일차")
    private BigDecimal afterDayWdQty04;

    @Schema(description = "일별 출고 수량 5일차")
    private BigDecimal afterDayWdQty05;

    @Schema(description = "일별 출고 수량 6일차")
    private BigDecimal afterDayWdQty06;

    @Schema(description = "일별 출고 수량 7일차")
    private BigDecimal afterDayWdQty07;

    @Schema(description = "일별 출고 수량 8일차")
    private BigDecimal afterDayWdQty08;

    @Schema(description = "일별 출고 수량 9일차")
    private BigDecimal afterDayWdQty09;

    @Schema(description = "일별 출고 수량 10일차")
    private BigDecimal afterDayWdQty10;

    @Schema(description = "일별 출고 수량 11일차")
    private BigDecimal afterDayWdQty11;

    @Schema(description = "일별 출고 수량 12일차")
    private BigDecimal afterDayWdQty12;

    @Schema(description = "일별 출고 수량 13일차")
    private BigDecimal afterDayWdQty13;

    @Schema(description = "일별 출고 수량 14일차")
    private BigDecimal afterDayWdQty14;

    @Schema(description = "일별 출고 수량 15일차")
    private BigDecimal afterDayWdQty15;

    @Schema(description = "일별 출고 수량 16일차")
    private BigDecimal afterDayWdQty16;

    @Schema(description = "일별 출고 수량 17일차")
    private BigDecimal afterDayWdQty17;

    @Schema(description = "일별 출고 수량 18일차")
    private BigDecimal afterDayWdQty18;

    @Schema(description = "일별 출고 수량 19일차")
    private BigDecimal afterDayWdQty19;

    @Schema(description = "일별 출고 수량 20일차")
    private BigDecimal afterDayWdQty20;

    @Schema(description = "재고 소진 정지 여부")
    private String exhaustionStopYn;

    @Schema(description = "안전 재고량")
    private BigDecimal stockSafety2;
    
    @Schema(description = "안전 재고량")
    private BigDecimal stockSafety3;

    @Schema(description = "예상 오픈 수량")
    private BigDecimal preOpenQty;

    @Schema(description = "기주문 수량")
    private BigDecimal orderedQty;

    // --- 재고/발주 정책 및 속성 ---
    @Schema(description = "발주 유형")
    private String purchaseType;

    @Schema(description = "공장")
    private String plant;

    @Schema(description = "보관 유형")
    private String storageType;

    @Schema(description = "보관 유형명")
    private String storageTypeNm;

    @Schema(description = "구매 그룹")
    private String purchaseGroup;

    @Schema(description = "최소 주문 수량")
    private BigDecimal minOrderQty;

    @Schema(description = "레이어당 팔레트 수")
    private BigDecimal layerPerPlt;

    @Schema(description = "레이어당 박스 수")
    private BigDecimal boxPerLayer;

    @Schema(description = "안전 계수")
    private BigDecimal coefficientSafety;

    @Schema(description = "구매 주기")
    private BigDecimal purInterval;

    @Schema(description = "배송 요일")
    private String deliveryWeek;

    @Schema(description = "반품 유형")
    private String returnType;

    @Schema(description = "시리얼 키")
    private BigDecimal serialKey;

    @Schema(description = "MOQ 상품 코드")
    private String moqSku;
    
    @Schema(description = "MOQ PLT")
    private String moqSkuPlt;
    
    @Schema(description = "MOQ 업체")
    private String moqVender;
    
    @Schema(description = "MOQ 업체 PLT")
    private String moqVenderPlt;

    @Schema(description = "팔레트당 박스 수")
    private BigDecimal boxPerPlt;

    @Schema(description = "팔레트당 박스 수량")
    private BigDecimal boxPerPltQty;

    @Schema(description = "TPL SKU 그룹")
    private String tplSkuGroup;

    @Schema(description = "고객 상품 코드")
    private String custSku;

    @Schema(description = "관리 유형")
    private String controlType;

    @Schema(description = "관리 시작일")
    private String controlFromDate;

    @Schema(description = "관리 종료일")
    private String controlToDate;

    @Schema(description = "CONFIRM 수량 수정 여부")
    private String confirmQtyEditYn;

    // --- 상태 및 기타 참조 필드 ---
    @Schema(description = "담당자명 1")
    private String empName1;

    @Schema(description = "담당자명 2")
    private String empName2;

    @Schema(description = "담당자명 3")
    private String empName3;

    @Schema(description = "담당자명 4")
    private String empName4;

    @Schema(description = "담당자명 5")
    private String empName5;

    @Schema(description = "담당자 전화번호 1")
    private String empPhone1;

    @Schema(description = "담당자 전화번호 2")
    private String empPhone2;

    @Schema(description = "담당자 전화번호 3")
    private String empPhone3;

    @Schema(description = "담당자 전화번호 4")
    private String empPhone4;

    @Schema(description = "담당자 전화번호 5")
    private String empPhone5;

    @Schema(description = "부 매입처 키")
    private String slaveCustKey;

    @Schema(description = "부 매입처명")
    private String slaveCustName;

    @Schema(description = "Vendor 확정 상태")
    private String vendorConfirmStatus;

    @Schema(description = "CONFIRM 수량 체크 상태")
    private String confirmQtyCheck;

    @Schema(description = "발주 정지 상태")
    private String stopStatus;

    @Schema(description = "발주 정지 상세 정보")
    private String stopInfo;

    @Schema(description = "박스 관리 플래그")
    private String boxFlag;

    @Schema(description = "Dummy Company 플래그")
    private BigDecimal dumCompany;

    @Schema(description = "Dummy 여부")
    private String dumYn;

    @Schema(description = "Dummy 수량")
    private BigDecimal dumQty;

    @Schema(description = "평균 중량")
    private BigDecimal avgWeight;

    @Schema(description = "라벨")
    private String label;

    @Schema(description = "참조 정보 01")
    private String reference01;

    @Schema(description = "참조 정보 02")
    private String reference02;

    @Schema(description = "참조 정보 03")
    private String reference03;

    @Schema(description = "참조 정보 04")
    private String reference04;

    @Schema(description = "참조 정보 06")
    private String reference06;

    @MaskingTelno
    @Schema(description = "참조 정보 07")
    private String reference07;

    @Schema(description = "추가자")
    private String addWho;

    @Schema(description = "추가 일자")
    private String addDate;

    @Schema(description = "수정자")
    private String editWho;

    @Schema(description = "수정 일자")
    private String editDate;

    @Schema(description = "라인 01")
    private String line01;

    @Schema(description = "라인 02")
    private BigDecimal line02;

    @Schema(description = "월요일 발주 여부")
    private String monYn;

    @Schema(description = "화요일 발주 여부")
    private String tueYn;

    @Schema(description = "수요일 발주 여부")
    private String wedYn;

    @Schema(description = "목요일 발주 여부")
    private String thuYn;

    @Schema(description = "금요일 발주 여부")
    private String friYn;

    @Schema(description = "토요일 발주 여부")
    private String satYn;

    @Schema(description = "일요일 발주 여부")
    private String sunYn;

    @Schema(description = "UOM 분자 (계산)")
    private BigDecimal bunjaCal;

    @Schema(description = "UOM 분모 (계산)")
    private BigDecimal bunmoCal;
    
    @Schema(description = "최종 생성시간")
    private String lastCreation;
    
    @Schema(description = "직송그룹 센터")
    private String directdlvFromDcCode;
    
    @Schema(description = "직송그룹 고객번호")
    private String directdlvCustKey;
    
    @Schema(description = "직송그룹 주문수량")
    private String directdlvOrderQty;
    
    @Schema(description = "직송그룹 수량단위")
    private String directdlvUom;
    
    @Schema(description = "예외입고품의여부")
    private String excptDpApprYn;
    
    @Schema(description = "PLT환산")
    private BigDecimal pltTrans;
    
    @Schema(description = "B/L번호")
    private String convSerialNo;
    
    @Schema(description = "계약유형")
    private String contractType;
    
    @Schema(description = "덤발주수량")
    private BigDecimal finalPlt;
    
    @Schema(description = "발주보류여부")
    private String poHoldYn;
    
    @Schema(description = "MAX")
    private String maxStock;
    
    @Schema(description = "외부창고코드")
    private String outOrganize;
    
    @Schema(description = "외부창고명")
    private String outOrganizeName;
    
    @Schema(description = "창고주소")
    private String address;
    
    @Schema(description = "이력변호")
    private String serialNo;
    
    @Schema(description = "계약업체")
    private String contractCustkey;
    
    @Schema(description = "계약업체명")
    private String contractCustNm;
    
    @Schema(description = "재고 구분 ID")
    private String stockid;
    
    @Schema(description = "재고 등급(ABC)")
    private String stockgrade;
    
    @Schema(description = "재고유형")
    private String stocktype;
    
    @Schema(description = "출고예정량")
    private BigDecimal wdInplanQty;
    
    @Schema(description = "재고유형")
    private String taxCode;
    
    /** 수신자이메일 */
    @Schema(description = "수신자이메일")
    private String rcvrEmail;

    /** 수신자명 */
    @Schema(description = "수신자명")
    private String rcvrNm;
}
