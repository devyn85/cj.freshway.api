package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net)
 * @date : 2025.08.20 
 * @description : 거래처 정보 조회 (단건) 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.20 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Schema(description = "거래처 정보 조회 (단건) 응답 DTO")
public class MsCustDeliveryInfoPMasterResDto {
	
	/** 데이터번호 */
    @Schema(description = "데이터번호", example = "")
    private String serialkey;

    /** 고객사코드 */
    @Schema(description = "고객사코드", example = "")
    private String storerkey;

    /** 거래처코드 */
    @Schema(description = "거래처코드", example = "")
    private String custkey;

    /** custname */
    @Schema(description = "custname", example = "")
    private String custname;

    /** 거래처 유형 */
    @Schema(description = "거래처 유형", example = "")
    private String custtype;

    /** 배송처코드 */
    @Schema(description = "배송처코드", example = "")
    private String dlvcustkey;

    /** 인도처코드 */
    @Schema(description = "인도처코드", example = "")
    private String dropcustkey;

    /** 우선순위 */
    @Schema(description = "우선순위", example = "")
    private String priority;

    /** 지정권역구분 */
    @Schema(description = "지정권역구분", example = "")
    private String districttype;

    /** 지정권역코드 */
    @Schema(description = "지정권역코드", example = "")
    private String districtcode;

    /** 경유지 */
    @Schema(description = "경유지", example = "")
    private String route;

    /** 기본배송택배사 */
    @Schema(description = "기본배송택배사", example = "")
    private String courier;

    /** 기본운송장유형 */
    @Schema(description = "기본운송장유형", example = "")
    private String invoicetype;

    /** 주출고처 */
    @Schema(description = "주출고처", example = "")
    private String dlvDccode;

    /** 타임존 */
    @Schema(description = "타임존", example = "")
    private String timezone;

    /** 국가코드 */
    @Schema(description = "국가코드", example = "")
    private String country;

    /** 주,도 */
    @Schema(description = "주,도", example = "")
    private String state;

    /** 시,읍,면 */
    @Schema(description = "시,읍,면", example = "")
    private String city;

    /** 우편번호 */
    @Schema(description = "우편번호", example = "")
    private String zipcode;

    /** 기본주소 */
    @Schema(description = "기본주소", example = "")
    private String address1;

    /** 상세주소 */
    @Schema(description = "상세주소", example = "")
    private String address2;

    /** 확장주소3 */
    @Schema(description = "확장주소3", example = "")
    private String address3;

    /** 확장주소4 */
    @Schema(description = "확장주소4", example = "")
    private String address4;

    /** 전화번호1 */
    @Schema(description = "전화번호1", example = "")
    private String phone1;

    /** 전화번호2 */
    @Schema(description = "전화번호2", example = "")
    private String phone2;

    /** 팩스번호 */
    @Schema(description = "팩스번호", example = "")
    private String fax;

    /** 이메일 */
    @Schema(description = "이메일", example = "")
    private String email;

    /** 관리 사원명1 */
    @Schema(description = "관리 사원명1", example = "")
    private String empname1;

    /** 관리 사원명2 */
    @Schema(description = "관리 사원명2", example = "")
    private String empname2;

    /** 관리 사원명3 */
    @Schema(description = "관리 사원명3", example = "")
    private String empname3;

    /** 관리 사원명4 */
    @Schema(description = "관리 사원명4", example = "")
    private String empname4;

    /** 관리 사원명5 */
    @Schema(description = "관리 사원명5", example = "")
    private String empname5;

    /** 관리 사원 전화번호1 */
    @Schema(description = "관리 사원 전화번호1", example = "")
    private String empphone1;

    /** 관리 사원 전화번호2 */
    @Schema(description = "관리 사원 전화번호2", example = "")
    private String empphone2;

    /** 관리 사원 전화번호3 */
    @Schema(description = "관리 사원 전화번호3", example = "")
    private String empphone3;

    /** 관리 사원 전화번호4 */
    @Schema(description = "관리 사원 전화번호4", example = "")
    private String empphone4;

    /** 관리 사원 전화번호5 */
    @Schema(description = "관리 사원 전화번호5", example = "")
    private String empphone5;

    /** 거래 시작 일자 */
    @Schema(description = "거래 시작 일자", example = "")
    private String startdate;

    /** 거래 종료 일자 */
    @Schema(description = "거래 종료 일자", example = "")
    private String enddate;

    /** 업무 시작 시간(24hr 2300) */
    @Schema(description = "업무 시작 시간(24hr 2300)", example = "")
    private String opentime;

    /** 업무 종료 시간(24hr 2300) */
    @Schema(description = "업무 종료 시간(24hr 2300)", example = "")
    private String closingtime;

    /** 배송요청요일 */
    @Schema(description = "배송요청요일", example = "")
    private String reqdlvweek;

    /** 배송요청일 */
    @Schema(description = "배송요청일", example = "")
    private String reqdlvdate;

    /** 배송요청시간 */
    @Schema(description = "배송요청시간", example = "")
    private String reqdlvtime1From;

    /** 열쇠보관및수령장소 */
    @Schema(description = "열쇠보관및수령장소", example = "")
    private String keyplace;

    /** 열쇠타입 */
    @Schema(description = "열쇠타입", example = "")
    private String keytype;

    /** 열쇠번호 */
    @Schema(description = "열쇠번호", example = "")
    private String keyno;

    /** 잠금장치타입 */
    @Schema(description = "잠금장치타입", example = "")
    private String locktype;

    /** 잠금장치번호 */
    @Schema(description = "잠금장치번호", example = "")
    private String lockno;

    /** 잠금해제타입 */
    @Schema(description = "잠금해제타입", example = "")
    private String unlocktype;

    /** 잠금해제번호 */
    @Schema(description = "잠금해제번호", example = "")
    private String unlockno;

    /** 진입위치 */
    @Schema(description = "진입위치", example = "")
    private String entryplace;

    /** 진입층 */
    @Schema(description = "진입층", example = "")
    private String entryfloor;

    /** 진입조건1 */
    @Schema(description = "진입조건1", example = "")
    private String entrycondition1;

    /** 진입조건2 */
    @Schema(description = "진입조건2", example = "")
    private String entrycondition2;

    /** 진입조건3 */
    @Schema(description = "진입조건3", example = "")
    private String entrycondition3;

    /** 주차유형 */
    @Schema(description = "주차유형", example = "")
    private String parkingtype;

    /** 주차위치 */
    @Schema(description = "주차위치", example = "")
    private String parkingplace;

    /** 주차층 */
    @Schema(description = "주차층", example = "")
    private String parkingfloor;

    /** 상차위치 */
    @Schema(description = "상차위치", example = "")
    private String loadplace;

    /** 상차층 */
    @Schema(description = "상차층", example = "")
    private String loadfloor;

    /** 상차조건1 */
    @Schema(description = "상차조건1 -> 수정 : 입차시간", example = "")
    private String loadcondition1;

    /** 상차조건2 */
    @Schema(description = "상차조건2", example = "")
    private String loadcondition2;

    /** 상차조건3 */
    @Schema(description = "상차조건3", example = "")
    private String loadcondition3;

    /** 납품위치 */
    @Schema(description = "납품위치", example = "")
    private String unloadplace;

    /** 하차층 */
    @Schema(description = "하차층", example = "")
    private String unloadfloor;

    /** 하차조건1 */
    @Schema(description = "하차조건1", example = "")
    private String unloadcondition1;

    /** 하차조건2 */
    @Schema(description = "하차조건2", example = "")
    private String unloadcondition2;

    /** 하차조건3 */
    @Schema(description = "하차조건3", example = "")
    private String unloadcondition3;

    /** 진입제한차량 */
    @Schema(description = "진입제한차량", example = "")
    private String limitcar;

    /** 상차가능한분당박스수 */
    @Schema(description = "상차가능한분당박스수", example = "")
    private String loadboxpermin;

    /** 상차도구유형 */
    @Schema(description = "상차도구유형", example = "")
    private String loadtooltype;

    /** 하차가능한분당박스수 */
    @Schema(description = "하차가능한분당박스수", example = "")
    private String unloadboxpermin;

    /** 하차도구유형 */
    @Schema(description = "하차도구유형", example = "")
    private String unloadtooltype;

    /** 위도 */
    @Schema(description = "위도", example = "")
    private String latitude;

    /** 경도 */
    @Schema(description = "경도", example = "")
    private String longitude;

    /** 입고전략 */
    @Schema(description = "입고전략", example = "")
    private String instrategy;

    /** 출고전략 */
    @Schema(description = "출고전략", example = "")
    private String outstrategy;

    /** 검품유형 */
    @Schema(description = "검품유형", example = "")
    private String qctype;

    /** 검수유형 */
    @Schema(description = "검수유형", example = "")
    private String inspecttype;

    /** 배송전략1 */
    @Schema(description = "배송전략1", example = "")
    private String dlvstrategy1;

    /** 배송전략2 */
    @Schema(description = "배송전략2", example = "")
    private String dlvstrategy2;

    /** 배송전략3 */
    @Schema(description = "배송전략3", example = "")
    private String dlvstrategy3;

    /** 배송전략4 */
    @Schema(description = "배송전략4", example = "")
    private String dlvstrategy4;

    /** 배송전략5 */
    @Schema(description = "배송전략5", example = "")
    private String dlvstrategy5;

    /** 약도1 */
    @Schema(description = "약도1", example = "")
    private String roughmap1;

    /** 약도2 */
    @Schema(description = "약도2", example = "")
    private String roughmap2;

    /** 약도3 */
    @Schema(description = "약도3", example = "")
    private String roughmap3;

    /** 내부레이아웃 */
    @Schema(description = "내부레이아웃", example = "")
    private String inlayout;

    /** 외부레이아웃 */
    @Schema(description = "외부레이아웃", example = "")
    private String outlayout;

    /** 이미지1 */
    @Schema(description = "이미지1", example = "")
    private String image01;

    /** 이미지2 */
    @Schema(description = "이미지2", example = "")
    private String image02;

    /** 이미지3 */
    @Schema(description = "이미지3", example = "")
    private String image03;

    /** 이미지4 */
    @Schema(description = "이미지4", example = "")
    private String image04;

    /** 이미지5 */
    @Schema(description = "이미지5", example = "")
    private String image05;

    /** 기타1 */
    @Schema(description = "기타1", example = "")
    private String other01;

    /** 기타2 */
    @Schema(description = "기타2", example = "")
    private String other02;

    /** 기타3 */
    @Schema(description = "기타3", example = "")
    private String other03;

    /** 기타4 */
    @Schema(description = "기타4", example = "")
    private String other04;

    /** 기타5 */
    @Schema(description = "기타5", example = "")
    private String other05;

    /** 기타사항 */
    @Schema(description = "기타사항", example = "")
    private String memo;

    /** 인터페이스파일명 */
    @Schema(description = "인터페이스파일명", example = "")
    private String iffilename;

    /** 상태 */
    @Schema(description = "상태", example = "")
    private String status;

    /** 삭제여부 */
    @Schema(description = "삭제여부", example = "")
    private String delYn;

    /** 최초등록시간 */
    @Schema(description = "최초등록시간", example = "")
    private String adddate;

    /** 최종변경시간 */
    @Schema(description = "최종변경시간", example = "")
    private String editdate;

    /** 최초등록자 */
    @Schema(description = "최초등록자", example = "")
    private String addwho;

    /** 최종변경자 */
    @Schema(description = "최종변경자", example = "")
    private String editwho;

    /** 배송요구시간1차 종료 */
    @Schema(description = "배송요구시간1차 종료", example = "")
    private String reqdlvtime1To;

    /** 배송요구시간2차 시작 */
    @Schema(description = "배송요구시간2차 시작", example = "")
    private String reqdlvtime2From;

    /** 배송요구시간2차 종료 */
    @Schema(description = "배송요구시간2차 종료", example = "")
    private String reqdlvtime2To;

    /** 원거리유형 */
    @Schema(description = "원거리유형", example = "")
    private String distancetype;

    /** 지정배송차량 */
    @Schema(description = "지정배송차량", example = "")
    private String carno;

    /** 지정배송그룹 */
    @Schema(description = "지정배송그룹", example = "")
    private String districtgroup;
    
    /** 조달물류여부 */
    @Schema(description = "조달물류여부", example = "")
    private String procLogiYn;

    /** 조달물류시작일자 */
    @Schema(description = "조달물류시작일자", example = "")
    private String procLogiStartDt;

    /** 조달물류종료일자 */
    @Schema(description = "조달물류종료일자", example = "")
    private String procLogiEndDt;

    /** 조달일배차량번호 */
    @Schema(description = "조달일배차량번호", example = "")
    private String procSamedayCarno;

    /** 조달저장차량번호 */
    @Schema(description = "조달저장차량번호", example = "")
    private String procStorageCarno;
    
    /** 반경 */
    @Schema(description = "반경", example = "")
    private String radius;

    /** 체류시간(초) */
    @Schema(description = "체류시간(초)", example = "")
    private String stytime;
    
    /** 대표자명*/
    @Schema(description = "대표자명" )
    private String vatowner;

    /** 사업자번호*/
    @Schema(description = "사업자번호")
    private String vatno;
    
    /** 사업자번호*/
    @Schema(description = "납품담당자 SMS 수신여부")
    private String dlvRespSmsNotiyn;
    
    
    /** 사업자번호*/
    @Schema(description = "납품담당자 메일 수신여부")
    private String dlvRespEmailNotiyn;
    
    /** 사업자번호*/
    @Schema(description = "3PL사용유무")
    private String tplUseYn;
    
    /** 사업자번호*/
    @Schema(description = "3PL운영시작일")
    private String tplOperDt;
	
	
	
}
