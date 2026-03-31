package cjfw.wms.ms.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.24
 * @description : 수발주기초정보 기능을 구현한 DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.24        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "수발주기초정보 조회 결과 DTO")
public class MsPurchaseCustResDto extends CommonProcedureDto {
	@Schema(description = "체크 여부", example = "0")
    private String checkYn;

    @Schema(description = "발주 구분")
    private String purchaseType;

    @Schema(description = "자동 발주 생성 유형")
    private String reference05;

    @Schema(description = "직송 그룹")
    private String deliveryType;

    @Schema(description = "물류센터 코드", example = "DC001")
    private String dcCode;

    @Schema(description = "상품 코드", example = "SKU001")
    private String sku;

    @Schema(description = "상품명")
    private String skuName;

    @Schema(description = "플랜트 코드", example = "PLANT01")
    private String plant;

    @Schema(description = "플랜트 설명", example = "[PLANT01]플랜트명")
    private String plantDescr;

    @Schema(description = "중단 여부")
    private String controlType;

    @Schema(description = "시작일", example = "YYYYMMDD")
    private String fromDate;

    @Schema(description = "종료일", example = "YYYYMMDD")
    private String toDate;

    @Schema(description = "저장 타입")
    private String storageType;

    @Schema(description = "거래처 코드", example = "CUST001")
    private String custKey;

    @Schema(description = "거래처명")
    private String custName;

    @Schema(description = "경유지")
    private String route;

    @Schema(description = "경유지 조직")
    private String routeOrganize;

    @Schema(description = "경유지 조직명")
    private String routeOrganizeNm;

    @Schema(description = "구매자 키")
    private String buyerKey;

    @Schema(description = "수급 담당명")
    private String buyerKeyNm;

    @Schema(description = "구매 그룹")
    private String purchaseGroup;

    @Schema(description = "최소 발주량")
    private BigDecimal minOrderQty;

    @Schema(description = "발주 수량 단위")
    private BigDecimal orderQtyUnit;

    @Schema(description = "단위 (UOM)")
    private String uom;

    @Schema(description = "팔레트당 적재 단수")
    private BigDecimal layerPerPlt;

    @Schema(description = "적재 단당 박스 수")
    private BigDecimal boxPerLayer;

    @Schema(description = "박스당 낱개 수")
    private BigDecimal qtyPerBox;

    @Schema(description = "안전 계수")
    private BigDecimal coefficientSafety;

    @Schema(description = "리드타임")
    private BigDecimal leadTime;

    @Schema(description = "주문 간격")
    private BigDecimal purInterval;

    @Schema(description = "수기 재발주점")
    private BigDecimal editReorderPoint;

    @Schema(description = "수기 목표 재고 수량")
    private BigDecimal editStockGoal;

    @Schema(description = "배송 주차")
    private String deliveryWeek;

    @Schema(description = "반품 유형")
    private String returnType;

    @Schema(description = "참조 정보1")
    private String reference01;

    @Schema(description = "참조 정보2")
    private String reference02;

    @Schema(description = "참조 정보3")
    private String reference03;

    @Schema(description = "참조 정보6")
    private String reference06;

    @Schema(description = "시리얼 키")
    private String serialKey; // 중복 컬럼이지만 다시 SELECT 됨

    @Schema(description = "고객사 코드 (중복)")
    private String storerKey; // 중복 컬럼이지만 다시 SELECT 됨

    @Schema(description = "거래처 유형")
    private String custType; // 중복 컬럼이지만 다시 SELECT 됨

    @Schema(description = "거래처 유형명")
    private String custTypeNm;

    @Schema(description = "저장 유무 (채널)")
    private String channel;

    @Schema(description = "식별 번호 유무 (시리얼 관리 여부)")
    private String serialYn;

    @Schema(description = "비정량 여부 (Line01)")
    private String line01;

    @Schema(description = "에러 코드")
    private String errCode;

    @Schema(description = "에러 메시지")
    private String errMsg;

    @Schema(description = "토요일 입고 가능 여부")
    private String reference04;

    @Schema(description = "등록자명")
    private String addWho;

    @Schema(description = "등록일", example = "YYYY-MM-DD HH:mm:ss")
    private String addDate;

    @Schema(description = "수정자명")
    private String editWho;

    @Schema(description = "수정일", example = "YYYY-MM-DD HH:mm:ss")
    private String editDate;

    @Schema(description = "발주 가능 여부 (전체)", example = "0/1")
    private String allYn;

    @Schema(description = "발주 가능 여부 (월)", example = "0/1")
    private String monYn;

    @Schema(description = "발주 가능 여부 (화)", example = "0/1")
    private String tueYn;

    @Schema(description = "발주 가능 여부 (수)", example = "0/1")
    private String wedYn;

    @Schema(description = "발주 가능 여부 (목)", example = "0/1")
    private String thuYn;

    @Schema(description = "발주 가능 여부 (금)", example = "0/1")
    private String friYn;

    @Schema(description = "발주 가능 여부 (토)", example = "0/1")
    private String satYn;

    @Schema(description = "발주 가능 여부 (일)", example = "0/1")
    private String sunYn;

    @Schema(description = "자동 발주 산정 기간")
    private BigDecimal autoExecDay;

    @Schema(description = "자동 발주 여부 (월)", example = "0/1")
    private String autoMonYn;

    @Schema(description = "자동 발주 여부 (화)", example = "0/1")
    private String autoTueYn;

    @Schema(description = "자동 발주 여부 (수)", example = "0/1")
    private String autoWedYn;

    @Schema(description = "자동 발주 여부 (목)", example = "0/1")
    private String autoThuYn;

    @Schema(description = "자동 발주 여부 (금)", example = "0/1")
    private String autoFriYn;

    @Schema(description = "자동 발주 여부 (토)", example = "0/1")
    private String autoSatYn;

    @Schema(description = "자동 발주 여부 (일)", example = "0/1")
    private String autoSunYn;

    @Schema(description = "자동 발주 처리 시간대 (00-07)", example = "0/1")
    private String batchTimePeriod1;

    @Schema(description = "자동 발주 처리 시간대 (08-15)", example = "0/1")
    private String batchTimePeriod2;

    @Schema(description = "자동 발주 처리 시간대 (16-23)", example = "0/1")
    private String batchTimePeriod3;

    @Schema(description = "중단 상태 (아이콘 표시용)")
    private String stopStatus;

    @Schema(description = "중단 정보 (상세 메시지)")
    private String stopInfo;

    @Schema(description = "상품 체인 박스당 팔레트 수량")
    private String boxPerPlt;

    @Schema(description = "MS_SKUCHAIN_MOQ.MOQ_SKU")
    private String moqSku;

    @Schema(description = "리드타임2 (MS_SKUCHAIN_MOQ.LEADTIME)")
    private BigDecimal leadTime2;

    @Schema(description = "벤더 최소 주문 수량 (MOQ_VENDER)")
    private BigDecimal moqVender;

    @Schema(description = "보조 고객사 키")
    private String slaveCustKey;

    @Schema(description = "보조 고객사명")
    private String slaveCustName;

    @Schema(description = "소진 시 중단 여부")
    private String exhaustionStopYn;

    @Schema(description = "고객사 상품 코드")
    private String custSku;

    @Schema(description = "참조 정보7")
    private String reference07;
    
    @Schema(description = "최종 변경자")
    private String editWhoNm;
    
    @Schema(description = "최초 등록자")
    private String addWhoNm;
    
    @Schema(description = "영업 그룹", example = "SALES01")
    private String saleGroupCd;

    @Schema(description = "대체 상품 코드", example = "ALTSKU001")
    private String alterSku;

    @Schema(description = "특이사항 여부", example = "Y")
    private String spclNoteYn;

    @Schema(description = "추천 적용 여부", example = "N")
    private String excptDpApprYn;

    @Schema(description = "PLT 변환값 (레이어당 PLT 또는 MOQ 상품 PLT)", example = "10.5")
    private BigDecimal pltChange;

    @Schema(description = "MOQ 상품 (PLT)", example = "5")
    private BigDecimal moqSkuPlt;

    @Schema(description = "MOQ 협력사 (PLT)", example = "25")
    private BigDecimal moqVenderPlt;
    
    @Schema(description = "수발주삭제 여부", example = "Y")
    private String qtyYn;
    
    @Schema(description = "추천안전계수 D-1월", example = "25")
    private BigDecimal coefficientSafety1W;
    
    @Schema(description = "추천안전계수 D-2월", example = "25")
    private BigDecimal coefficientSafety2W;
    
    @Schema(description = "추천안전계수 D-3월", example = "25")
    private BigDecimal coefficientSafety3W;
    
    @Schema(description = "마감기일", example = "25")
    private BigDecimal closeDay;
    
    @Schema(description = "마감시간", example = "1200")
    private BigDecimal closeHour;
    
    @Schema(description = "엑셀업로드 결과", example = "Y")
    private String processYn;
    
    @Schema(description = "업로드 결과 메세지", example = "성공")
    private String processMsg;
    
    private String minOrderQtyEA; // 최소 발주량 (EA)
    @Schema(description = "발주수량EA", example = "")
    private String orderQtyUnitEA; // 발주량 (EA)
    // 컬럼명이 미확정이라 grid 컬럼 정의 보고 대용량 엑셀 다운로드에서 임시 구현하기 위해서 사용.
    @Schema(description = "양산마감일", example = "성공")
    private String tempCloseDay;
    // 컬럼명이 미확정이라 grid 컬럼 정의 보고 대용량 엑셀 다운로드에서 임시 구현하기 위해서 사용.
    @Schema(description = "양산마감시간", example = "성공")
    private String tempCloseTime;
}
