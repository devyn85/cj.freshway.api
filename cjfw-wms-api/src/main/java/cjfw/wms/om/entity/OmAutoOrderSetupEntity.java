package cjfw.wms.om.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.07.24 
 * @description : 저장품자동발주관리 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class OmAutoOrderSetupEntity extends CommonDto {

	/** 상세코드 */
	@Schema(description = "상세코드", example = "")
	private String confCd;
	
	/** 기타설정구분코드(1-대상고객,2-대상마감유형,3-대상원거리유형) */
	@Schema(description = "기타설정구분코드(1-대상고객,2-대상마감유형,3-대상원거리유형)", example = "")
	private String purchaseConfCd;
	
	/** 호출 쿼리 */
	@Schema(description = "호출 쿼리", example = "")
	private String queryId;
	
	/** 발주코드 */
    @Schema(description = "발주코드", example = "")
    private String purchaseCd;

    /** 삭제여부 */
    @Schema(description = "삭제여부", example = "")
    private String delYn;

    /** 발주코드 */
    @Schema(description = "발주코드", example = "")
    private String purchaseTxt;

    /** 공급센터코드 */
    @Schema(description = "공급센터코드", example = "")
    private String frDccode;

    /** 공급받는센터 */
    @Schema(description = "공급받는센터", example = "")
    private String toDccode;

    /** 상품코드 */
    @Schema(description = "상품코드", example = "")
    private String sku;

    /** 고객사코드 */
    @Schema(description = "고객사코드", example = "")
    private String storerkey;

    /** 재고수량(미사용_통계데이터에서 취득으로변경) */
    @Schema(description = "재고수량(미사용_통계데이터에서 취득으로변경)", example = "")
    private Integer qty;

    /** 구매단위구분코드 */
    @Schema(description = "구매단위구분코드", example = "")
    private String uomDivCd;

    /** 조직코드 */
    @Schema(description = "조직코드", example = "")
    private String organize;

    /** 발주명 */
    @Schema(description = "발주명", example = "")
    private String purchaseName;

    /** 설명 */
    @Schema(description = "설명", example = "")
    private String purchaseConts;

    /** 공급센터코드 */
    @Schema(description = "공급센터코드", example = "")
    private String fromDccode;

    /** 주문일 */
    @Schema(description = "주문일", example = "")
    private String ordDay;

    /** 휴일관리(CM_CALENDAR.CALENDAR_ID) */
    @Schema(description = "휴일관리(CM_CALENDAR.CALENDAR_ID)", example = "")
    private String calendarId;

    /** 발주일 */
    @Schema(description = "발주일", example = "")
    private String purchaseDay;

    /** 발주일휴일처리(-1- 하루전, 0-당일, 1-하루뒤) */
    @Schema(description = "발주일휴일처리(-1- 하루전, 0-당일, 1-하루뒤)", example = "")
    private String purchaseHoliydayCd;

    /** 발주량산정기준(1-주문량, 2-안전재고량) */
    @Schema(description = "발주량산정기준(1-주문량, 2-안전재고량)", example = "")
    private String ordQtyDivCd;

    /** 자센터포함여부 */
    @Schema(description = "자센터포함여부", example = "")
    private String chdDcYn;

    /** 재고감안여부 */
    @Schema(description = "재고감안여부", example = "")
    private String stYn;

    /** 재고감안기준 ( 1-현재고, 2-가용재고 ) */
    @Schema(description = "재고감안기준 ( 1-현재고, 2-가용재고 )", example = "")
    private String stDivCd;

    /** 입고예정감안여부 */
    @Schema(description = "입고예정감안여부", example = "")
    private String dpYn;

    /** 입고예정PO감안여부 */
    @Schema(description = "입고예정PO감안여부", example = "")
    private String dpPoYn;

    /** 입고예정FWSTO감안여부 */
    @Schema(description = "입고예정FWSTO감안여부", example = "")
    private String dpFwstoYn;

    /** 입고예정KXSTO감안여부 */
    @Schema(description = "입고예정KXSTO감안여부", example = "")
    private String dpKxstoYn;

    /** 대상고객지정여부 */
    @Schema(description = "대상고객지정여부", example = "")
    private String incCustkeyYn;

    /** 대상마감유형지정여부 */
    @Schema(description = "대상마감유형지정여부", example = "")
    private String incClosetypeYn;

    /** 대상원거리유형지정여부 */
    @Schema(description = "대상원거리유형지정여부", example = "")
    private String incDistancetypeYn;

    /** 이력상품제외여부 */
    @Schema(description = "이력상품제외여부", example = "")
    private String exSerialYn;

    /** 수행구분 ( 1-일자주기, 2-요일주기 ) */
    @Schema(description = "수행구분 ( 1-일자주기, 2-요일주기 )", example = "")
    private String runDivCd;

    /** 수행일(년) */
    @Schema(description = "수행일(년)", example = "")
    private String runYyyy;

    /** 수행일(월) */
    @Schema(description = "수행일(월)", example = "")
    private String runMm;

    /** 수행일(일) */
    @Schema(description = "수행일(일)", example = "")
    private String runDd;

    /** 수행일(시) */
    @Schema(description = "수행일(시)", example = "")
    private String runHh;

    /** 수행일(수행분)( 00-00분, 10-10분, 20-20분, 30-30분, 40-40분, 50-50분 ) */
    @Schema(description = "수행일(수행분)( 00-00분, 10-10분, 20-20분, 30-30분, 40-40분, 50-50분 )", example = "")
    private String runMi;

    /** 수행요일(월) */
    @Schema(description = "수행요일(월)", example = "")
    private String runMonYn;

    /** 수행요일(화) */
    @Schema(description = "수행요일(화)", example = "")
    private String runTueYn;

    /** 수행요일(수) */
    @Schema(description = "수행요일(수)", example = "")
    private String runWedYn;

    /** 수행요일(목) */
    @Schema(description = "수행요일(목)", example = "")
    private String runThuYn;

    /** 수행요일(금) */
    @Schema(description = "수행요일(금)", example = "")
    private String runFriYn;

    /** 수행요일(토) */
    @Schema(description = "수행요일(토)", example = "")
    private String runSatYn;

    /** 수행요일(일) */
    @Schema(description = "수행요일(일)", example = "")
    private String runSunYn;

    /** 유효시작일 */
    @Schema(description = "유효시작일", example = "")
    private String fromdate;

    /** 유효종료일 */
    @Schema(description = "유효종료일", example = "")
    private String todate;

    /** 수행일휴일체크여부 */
    @Schema(description = "수행일휴일체크여부", example = "")
    private String runHolidaychkYn;

    /** 수행결과송신여부 */
    @Schema(description = "수행결과송신여부", example = "")
    private String procChkYn;

    /** 출고결과송신여부 */
    @Schema(description = "출고결과송신여부", example = "")
    private String resultChkYn;

    /** 출고결과체크시간 (1,2,3,4,5,6,7,8,9,10,11,12 ) */
    @Schema(description = "출고결과체크시간 (1,2,3,4,5,6,7,8,9,10,11,12 )", example = "")
    private String resultChkTerm;

    /** 수신대상명1 */
    @Schema(description = "수신대상명1", example = "")
    private String recvName1;

    /** 수신연락처1 */
    @Schema(description = "수신연락처1", example = "")
    private String recvTel1;

    /** 수신대상명2 */
    @Schema(description = "수신대상명2", example = "")
    private String recvName2;

    /** 수신연락처2 */
    @Schema(description = "수신연락처2", example = "")
    private String recvTel2;

    /** 수신대상명3 */
    @Schema(description = "수신대상명3", example = "")
    private String recvName3;

    /** 수신연락처3 */
    @Schema(description = "수신연락처3", example = "")
    private String recvTel3;

    /** 기타정보 1 */
    @Schema(description = "기타정보 1", example = "")
    private String other01;

    /** 기타정보 2 */
    @Schema(description = "기타정보 2", example = "")
    private String other02;

    /** 기타정보 3 */
    @Schema(description = "기타정보 3", example = "")
    private String other03;

    /** 기타정보 4 */
    @Schema(description = "기타정보 4", example = "")
    private String other04;

    /** 기타정보 5 */
    @Schema(description = "기타정보 5", example = "")
    private String other05;

    /** 참조데이터1 */
    @Schema(description = "참조데이터1", example = "")
    private String reference01;

    /** 참조데이터2 */
    @Schema(description = "참조데이터2", example = "")
    private String reference02;

    /** 참조데이터3 */
    @Schema(description = "참조데이터3", example = "")
    private String reference03;

    /** 참조데이터4 */
    @Schema(description = "참조데이터4", example = "")
    private String reference04;

    /** 참조데이터5 */
    @Schema(description = "참조데이터5", example = "")
    private String reference05;

	/** 성공 여부 */
    @Schema(description = "성공 여부", example = "")
    private Integer success;

    /** 에러 코드 */
    @Schema(description = "에러 코드", example = "")
    private Integer err;

    /** 에러 메시지 */
    @Schema(description = "에러 메시지", example = "")
    private String errmsg;
}