package cjfw.wms.ms.entity;

import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.08.20 
 * @description : 협력사집하조건 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.20 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MsCustDeliveryInfoPEntity extends CommonDto {
			
	/** 시리얼번호 */
    @Schema(description = "시리얼번호")
    private String serialkey;
    
	/** 구분코드 */
    @Schema(description = "구분코드", example = "")
    private String gubun;

    /** 거래처코드 */
    @Schema(description = "거래처코드", example = "")
    private String custkey;

    /** 거래처명 */
    @Schema(description = "거래처명", example = "")
    private String custname;

    /** 수신범위코드 */
    @Schema(description = "수신범위코드", example = "")
    private String rcvCd;

    /** SMS 수신 여부 */
    @Schema(description = "SMS수신유무", example = "")
    private String smsYn;

    /** Mail 수신 여부 */
    @Schema(description = "메일수신유무", example = "")
    private String mailYn;

    /** 성명 */
    @Schema(description = "성명", example = "")
    private String name;

    /** 전화번호 */
    @Schema(description = "전화번호", example = "")
    private String phone;

    /** 기타 */
    @Schema(description = "기타", example = "")
    private String etc;

    /** 삭제 여부 */
    @Schema(description = "삭제여부", example = "")
    private String delYn;

    /** 고객사코드 */
    @Schema(description = "고객사코드", example = "")
    private String storerkey;

    /** 거래처 유형 */
    @Schema(description = "거래처 유형", example = "")
    private String custtype;

    /** 배송처코드 */
    @Schema(description = "배송처코드", example = "")
    private String dlvcustkey;

    /** 우선순위 */
    @Schema(description = "우선순위", example = "")
    private String priority;

    /** 지정권역구분 */
    @Schema(description = "지정권역구분", example = "")
    private String districttype;

    /** 지정권역코드 */
    @Schema(description = "지정권역코드", example = "")
    private String districtcode;

    /** 지정배송그룹 */
    @Schema(description = "지정배송그룹", example = "")
    private String districtgroup;

    /** 경유지 */
    @Schema(description = "경유지", example = "")
    private String route;

    /** 택배사 */
    @Schema(description = "기본배송택배사", example = "")
    private String courier;

    /** 송장 유형 */
    @Schema(description = "기본운송장유형", example = "")
    private String invoicetype;

    /** 차량 번호 */
    @Schema(description = "지정배송차량", example = "")
    private String carno;

    /** 타임존 */
    @Schema(description = "타임존", example = "")
    private String timezone;

    /** 국가 */
    @Schema(description = "국가코드", example = "")
    private String country;

    /** 주 */
    @Schema(description = "주,도", example = "")
    private String state;

    /** 도시 */
    @Schema(description = "시,읍,면", example = "")
    private String city;

    /** 우편번호 */
    @Schema(description = "우편번호", example = "")
    private String zipcode;

    /** 주소1 */
    @Schema(description = "기본주소", example = "")
    private String address1;

    /** 주소2 */
    @Schema(description = "상세주소", example = "")
    private String address2;

    /** 주소3 */
    @Schema(description = "확장주소3", example = "")
    private String address3;

    /** 주소4 */
    @Schema(description = "확장주소4", example = "")
    private String address4;

    /** 전화번호1 */
    @Schema(description = "전화번호1", example = "")
    private String phone1;

    /** 전화번호2 */
    @Schema(description = "전화번호2", example = "")
    private String phone2;

    /** 팩스 */
    @Schema(description = "팩스번호", example = "")
    private String fax;

    /** 이메일 */
    @Schema(description = "이메일", example = "")
    private String email;

    /** 직원 이름1 */
    @Schema(description = "관리 사원명1", example = "")
    private String empname1;

    /** 직원 이름2 */
    @Schema(description = "관리 사원명2", example = "")
    private String empname2;

    /** 직원 이름3 */
    @Schema(description = "관리 사원명3", example = "")
    private String empname3;

    /** 직원 이름4 */
    @Schema(description = "관리 사원명4", example = "")
    private String empname4;

    /** 직원 이름5 */
    @Schema(description = "관리 사원명5", example = "")
    private String empname5;

    /** 직원 전화번호1 */
    @Schema(description = "관리 사원 전화번호1", example = "")
    private String empphone1;

    /** 직원 전화번호2 */
    @Schema(description = "관리 사원 전화번호2", example = "")
    private String empphone2;

    /** 직원 전화번호3 */
    @Schema(description = "관리 사원 전화번호3", example = "")
    private String empphone3;

    /** 직원 전화번호4 */
    @Schema(description = "관리 사원 전화번호4", example = "")
    private String empphone4;

    /** 직원 전화번호5 */
    @Schema(description = "관리 사원 전화번호5", example = "")
    private String empphone5;

    /** 시작일 */
    @Schema(description = "거래 시작 일자", example = "")
    private String startdate;

    /** 종료일 */
    @Schema(description = "거래 종료 일자", example = "")
    private String enddate;

    /** 오픈 시간 */
    @Schema(description = "업무 시작 시간(24hr  2300)", example = "")
    private String opentime;

    /** 마감 시간 */
    @Schema(description = "업무 종료 시간(24hr  2300)", example = "")
    private String closingtime;

    /** 요청 배송 주 */
    @Schema(description = "배송요청요일", example = "")
    private String reqdlvweek;

    /** 요청 배송일 */
    @Schema(description = "배송요청일", example = "")
    private String reqdlvdate;

    /** 요청 배송 시간1 FROM */
    @Schema(description = "배송요청시간", example = "")
    private String reqdlvtime1From;

    /** 키 장소 */
    @Schema(description = "열쇠보관및수령장소", example = "")
    private String keyplace;

    /** 키 유형 */
    @Schema(description = "열쇠타입", example = "")
    private String keytype;

    /** 키 번호 */
    @Schema(description = "열쇠번호", example = "")
    private String keyno;

    /** 잠금 유형 */
    @Schema(description = "잠금장치타입", example = "")
    private String locktype;

    /** 잠금 번호 */
    @Schema(description = "잠금장치번호", example = "")
    private String lockno;

    /** 잠금 해제 유형 */
    @Schema(description = "잠금해제타입", example = "")
    private String unlocktype;

    /** 잠금 해제 번호 */
    @Schema(description = "잠금해제번호", example = "")
    private String unlockno;

    /** 출입구 장소 */
    @Schema(description = "진입위치", example = "")
    private String entryplace;

    /** 출입구 층 */
    @Schema(description = "진입층", example = "")
    private String entryfloor;

    /** 출입구 조건1 */
    @Schema(description = "진입조건1", example = "")
    private String entrycondition1;

    /** 출입구 조건2 */
    @Schema(description = "진입조건2", example = "")
    private String entrycondition2;

    /** 출입구 조건3 */
    @Schema(description = "진입조건3", example = "")
    private String entrycondition3;

    /** 주차 유형 */
    @Schema(description = "주차유형", example = "")
    private String parkingtype;

    /** 주차 장소 */
    @Schema(description = "주차위치", example = "")
    private String parkingplace;

    /** 주차 층 */
    @Schema(description = "주차층", example = "")
    private String parkingfloor;

    /** 적재 장소 */
    @Schema(description = "상차위치", example = "")
    private String loadplace;

    /** 적재 층 */
    @Schema(description = "상차층", example = "")
    private String loadfloor;

    /** 적재 조건1 */
    @Schema(description = "상차조건1", example = "")
    private String loadcondition1;

    /** 적재 조건2 */
    @Schema(description = "상차조건2", example = "")
    private String loadcondition2;

    /** 적재 조건3 */
    @Schema(description = "상차조건3", example = "")
    private String loadcondition3;

    /** 하역 장소 */
    @Schema(description = "하차위치", example = "")
    private String unloadplace;

    /** 하역 층 */
    @Schema(description = "하차층", example = "")
    private String unloadfloor;

    /** 하역 조건1 */
    @Schema(description = "하차조건1", example = "")
    private String unloadcondition1;

    /** 하역 조건2 */
    @Schema(description = "하차조건2", example = "")
    private String unloadcondition2;

    /** 하역 조건3 */
    @Schema(description = "하차조건3", example = "")
    private String unloadcondition3;

    /** 제한 차량 */
    @Schema(description = "진입제한차량", example = "")
    private String limitcar;

    /** 분당 적재 박스 수 */
    @Schema(description = "상차가능한분당박스수", example = "")
    private String loadboxpermin;

    /** 적재 도구 유형 */
    @Schema(description = "상차도구유형", example = "")
    private String loadtooltype;

    /** 분당 하역 박스 수 */
    @Schema(description = "하차가능한분당박스수", example = "")
    private String unloadboxpermin;

    /** 하역 도구 유형 */
    @Schema(description = "하차도구유형", example = "")
    private String unloadtooltype;

    /** 위도 */
    @Schema(description = "위도", example = "")
    private String latitude;

    /** 경도 */
    @Schema(description = "경도", example = "")
    private String longitude;

    /** 입고 전략 */
    @Schema(description = "입고전략", example = "")
    private String instrategy;

    /** 출고 전략 */
    @Schema(description = "출고전략", example = "")
    private String outstrategy;

    /** QC 유형 */
    @Schema(description = "검품유형", example = "")
    private String qctype;

    /** 검사 유형 */
    @Schema(description = "검수유형", example = "")
    private String inspecttype;

    /** 배송 전략1 */
    @Schema(description = "배송전략", example = "")
    private String dlvstrategy1;

    /** 배송 전략2 */
    @Schema(description = "배송전략", example = "")
    private String dlvstrategy2;

    /** 배송 전략3 */
    @Schema(description = "배송전략", example = "")
    private String dlvstrategy3;

    /** 배송 전략4 */
    @Schema(description = "배송전략", example = "")
    private String dlvstrategy4;

    /** 배송 전략5 */
    @Schema(description = "배송전략", example = "")
    private String dlvstrategy5;

    /** 약도1 */
    @Schema(description = "약도", example = "")
    private String roughmap1;

    /** 약도2 */
    @Schema(description = "약도", example = "")
    private String roughmap2;

    /** 약도3 */
    @Schema(description = "약도", example = "")
    private String roughmap3;

    /** 입고 레이아웃 */
    @Schema(description = "내부레이아웃", example = "")
    private String inlayout;

    /** 출고 레이아웃 */
    @Schema(description = "외부레이아웃", example = "")
    private String outlayout;

    /** 이미지01 */
    @Schema(description = "이미지", example = "")
    private String image01;

    /** 이미지02 */
    @Schema(description = "이미지", example = "")
    private String image02;

    /** 이미지03 */
    @Schema(description = "이미지", example = "")
    private String image03;

    /** 이미지04 */
    @Schema(description = "이미지", example = "")
    private String image04;

    /** 이미지05 */
    @Schema(description = "이미지", example = "")
    private String image05;

    /** 기타01 */
    @Schema(description = "기타", example = "")
    private String other01;

    /** 기타02 */
    @Schema(description = "기타", example = "")
    private String other02;

    /** 기타03 */
    @Schema(description = "기타", example = "")
    private String other03;

    /** 기타04 */
    @Schema(description = "기타", example = "")
    private String other04;

    /** 기타05 */
    @Schema(description = "기타", example = "")
    private String other05;

    /** 메모 */
    @Schema(description = "기타사항", example = "")
    private String memo;

    /** IF 파일 이름 */
    @Schema(description = "인터페이스파일명", example = "")
    private String iffilename;

    /** 상태 */
    @Schema(description = "상태", example = "")
    private String status;

    /** 요청 배송 시간1 TO */
    @Schema(description = "배송요구시간1차 종료", example = "")
    private String reqdlvtime1To;

    /** 요청 배송 시간2 FROM */
    @Schema(description = "배송요구시간2차 시작", example = "")
    private String reqdlvtime2From;

    /** 요청 배송 시간2 TO */
    @Schema(description = "배송요구시간2차 종료", example = "")
    private String reqdlvtime2To;

    /** 원거리유형 */
    @Schema(description = "원거리유형", example = "")
    private String distancetype;

    /** 거래처코드 */
    @Schema(description = "거래처코드", example = "")
    private String toCustkey;

    /** 인도처코드 */
    @Schema(description = "인도처코드", example = "")
    private String dropcustkey;

    /** 주출고처 */
    @Schema(description = "주출고처", example = "")
    private String dlvDccode;

    /** 고객사 거래처 코드(실제 고객사 시스템에 등록된 거래처 코드) */
    @Schema(description = "고객사 거래처 코드(실제 고객사 시스템에 등록된 거래처 코드)", example = "")
    private String storercustkey;

    /** 추가일 FROM */
    @Schema(description = "최초등록시간", example = "")
    private String adddateFrom;

    /** 추가일 TO */
    @Schema(description = "최초등록시간", example = "")
    private String adddateTo;

    /** YN */
    @Schema(description = "위.경도", example = "")
    private String yn;
    
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
	
	/** 상세정보 */
	@Schema(description = "상세정보", example = "")
	private MsCustDeliveryInfoPEntity master;
	
	/** 대량업로드리스트(excel) */
	@Schema(description = "대량업로드리스트(excel)", example = "")
	private List<MsCustDeliveryInfoPEntity> excelList;
	
	 /** 성공 여부 */
    @Schema(description = "성공 여부", example = "")
    private Integer success;

    /** 에러 코드 */
    @Schema(description = "에러 코드", example = "")
    private Integer err;

    /** 에러 메시지 */
    @Schema(description = "에러 메시지", example = "")
    private String errmsg;
    
    /** 반경 */
    @Schema(description = "반경", example = "")
    private String radius;

    /** 체류시간(초) */
    @Schema(description = "체류시간(초)", example = "")
    private String stytime;
    
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