package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.06.19 
 * @description : GPS좌표등록 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.19 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Schema(description = "GPS좌표등록 응답 DTO")
public class MsCustDeliveryInfoResDto {
	
	/** 고객코드 */
	@Schema(description = "고객코드", example = "12340")
	private String custkey;
	
	/** 고객명 */
	@Schema(description = "고객명", example = "12340")
	private String custname;
	
	/** 거래처 유형 */
	@Schema(description = "거래처 유형", example = "C")
	private String custtype;
	
	/** 위도 */
	@Schema(description = "위도", example = "35.2272799475294")
	private String latitude;
	
	/** 경도 */
	@Schema(description = "경도", example = "128.635822693316")
	private String longitude;
	
	/** FROM CUST NAME */
    @Schema(description = "FROM CUST NAME", example = "")
    private String fromCustname;

    /** 거래처코드(FROM) */
    @Schema(description = "거래처코드", example = "")
    private String fromCustkey;
    
    /** 거래처 유형 */
    @Schema(description = "거래처 유형", example = "")
    private String custtypeNm;
	
    /** 체크결과 */
	@Schema(description = "체크결과", example = "N")
	private String processYn;
	
	/** 체크메세지 */
	@Schema(description = "체크메세지", example = "존재하지 않는 거래처유형 코드입니다.")
	private String processMsg;
	
	/** 체크여부 */
    @Schema(description = "체크여부", example = "0")
    private String checkyn;
    
    /** 거래처 그룹 */
    @Schema(description = "거래처 그룹", example = "")
    private String custgroup;
    
    /** 고객사코드 */
    @Schema(description = "고객사코드", example = "")
    private String storerkey;
        
    /** 배송처코드 */
    @Schema(description = "배송처코드", example = "")
    private String dlvcustkey;
    
    /** 인도처코드 */
    @Schema(description = "인도처코드", example = "")
    private String dropcustkey;
    
    /** 우편번호 */
    @Schema(description = "우편번호", example = "")
    private String zipcode;
    
    /** 주소 */
    @Schema(description = "주소", example = "")
    private String address;
    
    /** 지번주소 */
    @Schema(description = "지번주소", example = "")
    private String address1;
    
    /** 지번주소2 */
    @Schema(description = "지번주소2", example = "")
    private String address2;
    
    /** 검수유형 */
    @Schema(description = "검수유형", example = "")
    private String inspecttype;
    
    /** 배송요청요일 */
    @Schema(description = "배송요청요일", example = "")
    private String reqdlvweek;
    
    /** 배송요청일 */
    @Schema(description = "배송요청일", example = "")
    private String reqdlvdate;
    
    /** 배송요청시간 시작 */
    @Schema(description = "배송요청시간 시작", example = "")
    private String reqdlvtime1From;
    
    /** 배송요청시간 종료 */
    @Schema(description = "배송요청시간 종료", example = "")
    private String reqdlvtime1To;
    
    /** 배송요청시간 시작 */
    @Schema(description = "배송요청시간 시작", example = "")
    private String reqdlvtime2From;
    
    /** 배송요청시간 종료 */
    @Schema(description = "배송요청시간 종료", example = "")
    private String reqdlvtime2To;
    
    /** 배송요청시간 시작 */
    @Schema(description = "배송요청시간 시작", example = "")
    private String deliveryrequesttimestart;
    
    /** 배송요청시간 종료 */
    @Schema(description = "배송요청시간 종료", example = "")
    private String deliveryrequesttimeend;
    
    /** 열쇠보관및수령장소 */
    @Schema(description = "열쇠보관및수령장소", example = "")
    private String keyplace;
    
    /** 상태 */
    @Schema(description = "상태", example = "")
    private String status;
    
    /** 지역유형 */
    @Schema(description = "지역유형", example = "")
    private String districttype;
    
    /** 지역그룹 */
    @Schema(description = "지역그룹", example = "")
    private String districtgroup;
    
    /** 차량번호 */
    @Schema(description = "차량번호", example = "")
    private String carno;
    
    /** 주출고처 */
    @Schema(description = "주출고처", example = "")
    private String dlvDccode;
    
    /** 원거리유형 */
    @Schema(description = "원거리유형", example = "")
    private String distancetype;
    
    /** 최초등록시간 */
    @Schema(description = "최초등록시간", example = "")
    private String adddate;
    
    /** 최종변경시간 */
    @Schema(description = "최종변경시간", example = "")
    private String editdate;
    
    /** 주소 길이 여부 */
    @Schema(description = "주소 길이 여부", example = "Y")
    private String addrLeng;
    
    /** 기타1 */
    @Schema(description = "기타1", example = "")
    private String other01;
    
    /** 강성 거래처 */
    @Schema(description = "강성 거래처", example = "")
    private String other02;
    
    /** 지역구분(대) */
    @Schema(description = "지역구분(대)", example = "")
    private String other03;
    
    /** 지역구분(중) */
    @Schema(description = "지역구분(중)", example = "")
    private String other04;
    
    /** 지역구분(소) */
    @Schema(description = "지역구분(소)", example = "")
    private String other05;
    
    /** 실착지 거래처코드 */
    @Schema(description = "실착지 거래처코드", example = "")
    private String truthcustkey;
    
    /** 실착지 거래처주소 */
    @Schema(description = "실착지 거래처주소", example = "")
    private String truthaddress1;
    
    /** 실착지 상세주소 */
    @Schema(description = "실착지 상세주소", example = "")
    private String truthaddress2;
    
    /** 실착지 우편번호 */
    @Schema(description = "실착지 우편번호", example = "")
    private String truthzipcode;
    
    /** 실착지 거래처주소 */
    @Schema(description = "실착지 거래처주소", example = "")
    private String arrivaladdress;
    
    /** 실착지 상세주소 */
    @Schema(description = "실착지 상세주소", example = "")
    private String arrivaldetailaddress;
    
    /** 실착지 우편번호 */
    @Schema(description = "실착지 우편번호", example = "")
    private String arrivalpostalcode;

    /** 검수자용 납품서 출력 여부 */
    @Schema(description = "검수자용 납품서 출력 여부", example = "")
    private String inspectorprintyn;
    
    /** 검수 실무자 전화번호 */
    @Schema(description = "검수 실무자 전화번호", example = "")
    private String inspectionworkerphone;
    
    /** 온도기록지 제출 대상 여부 */
    @Schema(description = "온도기록지 제출 대상 여부", example = "")
    private String temptarget;
    
    /** 건물 주차장 진입 가능 높이 */
    @Schema(description = "건물 주차장 진입 가능 높이", example = "")
    private String parkingheight;
    
    /** 고객사 납품 가능 시간 */
    @Schema(description = "고객사 납품 가능 시간", example = "")
    private String deliveryavailabletime;
    
    /** 고객사 건물 개방 시간 */
    @Schema(description = "고객사 건물 개방 시간", example = "")
    private String buildingopentime;
    
    /** 초도배송시 전달사항 */
    @Schema(description = "초도배송시 전달사항", example = "")
    private String initdeliverydesc;
    
    /** 초도배송 요청시간 시작 */
    @Schema(description = "초도배송 요청시간 시작", example = "")
    private String initrequesttimestart;

    /** 초도배송 요청시간 종료 */
    @Schema(description = "초도배송 요청시간 종료", example = "")
    private String initrequesttimeend;

    /** 초도배송 대면검수 여부 */
    @Schema(description = "초도배송 대면검수 여부", example = "")
    private String initftfinspectionyn;

    /** 초도배송 담당자 연락처 */
    @Schema(description = "초도배송 담당자 연락처", example = "")
    private String initdeliverycontact;
    
    /** 초도배송 요청일자 */
    @Schema(description = "초도배송 요청일자", example = "")
    private String initrequestdt;
    
    /** 평일 배차 */
    @Schema(description = "평일 배차", example = "")
    private String weekdaydelivery;
    
    /** 주말 배차 */
    @Schema(description = "주말 배차", example = "")
    private String weekenddelivery;
    
    /** 배송/키즈자차/수송 */
    @Schema(description = "배송/키즈자차/수송", example = "")
    private String keykind;
    
    /** 업장 출입 방법 */
    @Schema(description = "업장 출입 방법", example = "")
    private String accessway;
    
    /** 업장 출입 방법 */
    @Schema(description = "업장 출입 방법", example = "")
    private String movemententry;
    
    /** 적재위치(냉동) */
    @Schema(description = "적재위치(냉동)", example = "")
    private String freezeplace;

    /** 요청사항(거래처카드) */
    @Schema(description = "요청사항(거래처카드)", example = "")
    private String cardmemo;
    
    /** 메모(납품서반영) */
    @Schema(description = "메모(납품서반영)", example = "")
    private String supplymemo;
    
    /** 층수 */
    @Schema(description = "층수", example = "")
    private String floor;
    
    /** 차량진입 제한(톤수) */
    @Schema(description = "차량진입 제한(톤수)", example = "")
    private String tonnage;
    
    /** 하차작업 시간(분) */
    @Schema(description = "하차작업 시간(분)", example = "")
    private String unloaddt;
    
    /** 부피짐 거래처 */
    @Schema(description = "부피짐 거래처", example = "")
    private String volumecust;
    
    /** 비밀번호 */
    @Schema(description = "비밀번호", example = "")
    private String password;
    
    /** 적재위치(냉장/상온) */
    @Schema(description = "적재위치(냉장/상온)", example = "")
    private String coldplace;
    
    /** 거래처 열쇠 종류 */
    @Schema(description = "거래처 열쇠 종류", example = "")
    private String keycusttype;
    
    /** WMS 대면검수여부 */
    @Schema(description = "WMS 대면검수여부", example = "")
    private String faceinspect;
    
    /** WMS 대면검수여부 */
    @Schema(description = "WMS 대면검수여부", example = "")
    private String ftfinspectionyn;
    
    /** 센터 강성 거래처 */
    @Schema(description = "센터 강성 거래처", example = "")
    private String dccheckcust;
    
    /** 거래처 열쇠 여부 */
    @Schema(description = "거래처 열쇠 여부", example = "")
    private String keytype;
    
    /** (WMS) 거래처 열쇠여부 */
    @Schema(description = "(WMS) 거래처 열쇠여부", example = "")
    private String keytype2;
    
    /** 고객사 출입 key 세부정보 */
    @Schema(description = "고객사 출입 key 세부정보", example = "")
    private String keydetail;
    
    /** (WMS)거래처 열쇠 수령장소 */
    @Schema(description = "(WMS)거래처 열쇠 수령장소", example = "")
    private String keypoint;
    
    /** 2차점 라벨출력 옵션 */
    @Schema(description = "2차점 라벨출력 옵션", example = "")
    private String labelstrategy;
    
    /** 거래처전략5 */
    @Schema(description = "거래처전략5", example = "")
    private String custstrategy5;
    
    /** 납품위치 */
    @Schema(description = "납품위치", example = "")
    private String unloadplace;
    
    /** WMS납품위치 */
    @Schema(description = "WMS납품위치", example = "")
    private String unloadplace2;
    
    /** 회수위치 */
    @Schema(description = "회수위치", example = "")
    private String loadplace;
    
    /** WMS회수위치 */
    @Schema(description = "WMS회수위치", example = "")
    private String loadplace2;
    
    /** 적재위치(냉동) */
    @Schema(description = "적재위치(냉동)", example = "")
    private String goodslocationfrozen;
    
    /** 적재위치(냉장/상온) */
    @Schema(description = "적재위치(냉장/상온)", example = "")
    private String goodslocationrefrig;
    
    /** HTEMPERATURE */
    @Schema(description = "HTEMPERATURE", example = "")
    private String goodslocationroom;
    
    /** WMS회수위치 */
    @Schema(description = "WMS회수위치", example = "")
    private String returnlocation;
    
    /** 배송전략1 */
    @Schema(description = "배송전략1", example = "")
    private String dlvstrategy1;
    
    /** 진입조건1 */
    @Schema(description = "진입조건1", example = "")
    private String entrycondition1;
    
    /** 하차조건1 */
    @Schema(description = "하차조건1", example = "")
    private String unloadcondition1;
    
    /** 하차조건2 */
    @Schema(description = "하차조건2", example = "")
    private String unloadcondition2;
    
    /** RED zone 여부 */
    @Schema(description = "RED zone 여부", example = "")
    private String qctype;
    
    /** 권역 */
    @Schema(description = "권역", example = "")
    private String districtcode;
    
    /** 거래처 마감유형 */
    @Schema(description = "거래처 마감유형", example = "")
    private String custstrategy4;
    
    /** MA */
    @Schema(description = "MA", example = "")
    private String empname1;
    
    /** 담당자전화번호1 */
    @Schema(description = "담당자전화번호1", example = "")
    private String empphone1;
    
    /** 담당자전화번호2 */
    @Schema(description = "담당자전화번호2", example = "")
    private String empphone2;
    
    /** 담당자전화번호3 */
    @Schema(description = "담당자전화번호3", example = "")
    private String empphone3;
    
    /** 담당자연락처 */
    @Schema(description = "담당자연락처", example = "")
    private String truthphone;
    
    /** 담당자명 */
    @Schema(description = "담당자명", example = "")
    private String truthempname;
    
    /** 최초등록자명 */
    @Schema(description = "최초등록자명", example = "")
    private String addwho;
    
    /** 최종등록자명 */
    @Schema(description = "최종등록자명", example = "")
    private String editwho;
    
    /** 최초등록자명 */
    @Schema(description = "최초등록자명", example = "")
    private String regNm;
    
    /** 최종등록자명 */
    @Schema(description = "최종등록자명", example = "")
    private String updNm;
    
    /** 삭제유무 */
    @Schema(description = "삭제유무", example = "")
    private String delYn;
    
    /** HTEMPERATURE */
    @Schema(description = "HTEMPERATURE", example = "")
    private String htemperature;
    
    /** 군납 여부 */
    @Schema(description = "군납 여부", example = "N")
    private String armyYn;
    
    /** 본점 */
    @Schema(description = "본점", example = "")
    private String hqCustkey;
    
    /** 본점 */
    @Schema(description = "본점", example = "")
    private String hqCustname;
    
    /** 판매처 */
    @Schema(description = "판매처", example = "")
    private String saleCustkey;
    	
    /** 판매처 */
    @Schema(description = "판매처", example = "")
    private String saleCustname;

    /** 관리처 */
    @Schema(description = "관리처", example = "")
    private String mngCustkey;
    
    /** 관리처 */
    @Schema(description = "관리처", example = "")
    private String mngCustname;
    
    /** 배송정보적용요청여부 */
    @Schema(description = "배송정보적용요청여부", example = "")
    private String dlvInfoApplyYn;
    
    /** 거래처 */
    @Schema(description = "거래처", example = "")
    private String issueno;
    
    /** 업장출입이동동선 */
    @Schema(description = "업장출입이동동선", example = "")
    private String storeAccessRoute1;
    
    /** 업장출입이동동선 */
    @Schema(description = "업장출입이동동선", example = "")
    private String storeAccessRoute2;
    
    /** 업장출입이동동선 */
    @Schema(description = "업장출입이동동선", example = "")
    private String storeAccessRoute3;
    
    /** 업장출입이동동선 */
    @Schema(description = "업장출입이동동선", example = "")
    private String storeAccessRoute4;
    
    /** 상품적재위치(냉동) */
    @Schema(description = "상품적재위치(냉동)", example = "")
    private String goodslocationfrozen1;
    
    /** 상품적재위치(냉동) */
    @Schema(description = "상품적재위치(냉동)", example = "")
    private String goodslocationfrozen2;
    
    /** 상품적재위치(냉장) */
    @Schema(description = "상품적재위치(냉장)", example = "")
    private String goodslocationrefrig1;
    
    /** 상품적재위치(냉장) */
    @Schema(description = "상품적재위치(냉장)", example = "")
    private String goodslocationrefrig2;
    
    /** 상품적재위치(상온) */
    @Schema(description = "상품적재위치(상온)", example = "")
    private String goodslocationroom1;
        
    /** 상품적재위치(상온) */
    @Schema(description = "상품적재위치(상온)", example = "")
    private String goodslocationroom2;
    
    /** 반품 */
    @Schema(description = "반품", example = "")
    private String returnlocation1;
    
    /** 반품 */
    @Schema(description = "반품", example = "")
    private String returnlocation2;
    
	/** 거래처 정보 조회 (단건) */
	@Schema(description = "거래처 정보 조회 (단건)", example = "")
	private MsCustDeliveryInfoMasterResDto masterDto;
	
	/** 협력사 입고검수결과 수신자 마스터정보 조회(목록) */
	@Schema(description = "협력사 입고검수결과 수신자 마스터정보 조회(목록)", example = "")
	private MsCustDeliveryInfoResDto masterCRMDto;
	
	/** 거래처코드 유효성 */
    @Schema(description = "거래처코드 유효성", example = "")
    private String custkeyChk;
    
    /** 실착지 유효성 */
    @Schema(description = "실착지 유효성", example = "")
    private String truthcustkeyChk;
    
    /** 거래처유형 유효성 */
    @Schema(description = "거래처유형 유효성", example = "")
    private String custtypeChk;
    
    /** 주소값 유효성 */
    @Schema(description = "주소값 유효성", example = "")
    private String addressChk;
    
    /** 기등록 유효성 */
    @Schema(description = "기등록 유효성", example = "")
    private String alreadyChk;
    
    /** 코드중복 유효성 */
    @Schema(description = "코드중복 유효성", example = "")
    private String duplicateChk;
    
    /** SRM 라벨출력유형 API 호출 */
    @Schema(description = "SRM 라벨출력유형 API 호출", example = "")
    private String srmSendFlag;
    
    /** 지정권역명*/
    @Schema(description = "지정권역명", example = "")
    private String districtname;
    
    /** 커스텀 엑스트라 체크박스(없으면 전체 선택 해제 안 됨) */
    @Schema(description = "커스텀 엑스트라 체크박스(없으면 전체 선택 해제 안 됨)", example = "N")
    private String customRowCheckYn = "N";
    
    @Schema(description = "배송요청시간", example = "")
    private String deliveryRequestTime;
    
    @Schema(description = "추천실착지코드", example = "")
    private String recTruthcustkey;
    
    /** 코드중복 유효성 */
    @Schema(description = "건물개방시간 유효성", example = "")
    private String buildingopentimeChk;
    
    /** 코드중복 유효성 */
    @Schema(description = "납품가능시간 유효성", example = "")
    private String deliveryavailabletimeChk;
    
    /** 코드중복 유효성 */
    @Schema(description = "건물진입가능높이 유효성", example = "")
    private String parkingheightChk;
    
    /** 코드중복 유효성 */
    @Schema(description = "업장출입동선 유효성", example = "")
    private String accesswayChk;
    
    
}
