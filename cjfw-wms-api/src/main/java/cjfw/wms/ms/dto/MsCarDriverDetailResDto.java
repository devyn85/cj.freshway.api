package cjfw.wms.ms.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.08.12
 * @description : 차량정보 조회 결과 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.12        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Schema(description = "차량정보 조회 결과 DTO")
public class MsCarDriverDetailResDto {
	@Schema(description = "데이터 번호")
    private String serialKey;

    @Schema(description = "운전자 시리얼 키 (SERIALKEY와 동일)")
    private String driverSerialKey;

    @Schema(description = "차량 고유 ID")
    private String carId;

    @Schema(description = "차량 번호")
    private String carNo;

    @Schema(description = "차량 단축 번호")
    private String shortNo;

    @Schema(description = "차량 그룹")
    private String carGroup;

    @Schema(description = "차량 종류")
    private String carType;

    @Schema(description = "차량 형태")
    private String carCategory;

    @Schema(description = "차량 용적")
    private String carCapacity;

    @Schema(description = "용적 코드 설명")
    private String carCubeDescr;

    @Schema(description = "제조 회사 브랜드")
    private String brandName;
    
    @Schema(description = "창고 코드")
    private String dcCode;

    @Schema(description = "기본 권역 코드")
    private String defDistrictCode;

    @Schema(description = "운전자 2 ID")
    private String driver2;

    @Schema(description = "운전자 3 ID")
    private String driver3;

    @Schema(description = "운전자 4 ID")
    private String driver4;

    @Schema(description = "운전자 2 이름")
    private String driverName2;

    @Schema(description = "차고지 주소1")
    private String garageAddress1;

    @Schema(description = "차고지 주소2")
    private String garageAddress2;

    @Schema(description = "차고지 주소3")
    private String garageAddress3;

    @Schema(description = "차고지 주소4")
    private String garageAddress4;

    @Schema(description = "PDA 전화번호1")
    private String pdaPhone1;

    @Schema(description = "PDA 전화번호2")
    private String pdaPhone2;

    @Schema(description = "PDA 전화번호3")
    private String pdaPhone3;

    @Schema(description = "PDA 전화번호4")
    private String pdaPhone4;

    @Schema(description = "중복 로그인 가능 여부")
    private String allowLoginYn;

    @Schema(description = "차량 소유주명")
    private String owner;

    @Schema(description = "계약 유형")
    private String contractType;

    @Schema(description = "계약 일자", example = "YYYYMMDD")
    private String contractDate;

    @Schema(description = "만료 일자", example = "YYYYMMDD")
    private String expireDate;

    @Schema(description = "계약 금액")
    private BigDecimal contractPrice;

    @Schema(description = "차량 소유 업체 키")
    private String carAgentKey;

    @Schema(description = "차량 소유 업체명")
    private String carAgentName;

    @Schema(description = "차량 업체 전화번호")
    private String carAgentPhone;

    @Schema(description = "차량 업체 팩스")
    private String carAgentFax;

    @Schema(description = "보험 회사")
    private String insurance;

    @Schema(description = "보험 계약일", example = "YYYYMMDD")
    private String insuContDate;

    @Schema(description = "보험 만료일", example = "YYYYMMDD")
    private String insuExpiDate;

    @Schema(description = "적재함 유형")
    private String cargoType;

    @Schema(description = "적재함 사이즈")
    private String cargoSize;

    @Schema(description = "적재함 체적")
    private BigDecimal cargoCube;

    @Schema(description = "최소 적재 가능 체적")
    private BigDecimal minCube;

    @Schema(description = "최대 적재 가능 체적")
    private BigDecimal maxCube;

    @Schema(description = "최소 적재 중량")
    private BigDecimal minWeight;

    @Schema(description = "최대 적재 중량")
    private BigDecimal maxWeight;

    @Schema(description = "DTG 유형")
    private String dtgType;

    @Schema(description = "DTG 장비 번호")
    private String dtgNo;

    @Schema(description = "DTG 장비 통신 번호")
    private String dtgCommNo;

    @Schema(description = "GPS 유형")
    private String gpsType;

    @Schema(description = "GPS 장비 번호")
    private String gpsNo;

    @Schema(description = "GPS 통신 번호")
    private String gpsCommNo;

    @Schema(description = "온도 기록계 유형")
    private String thermometerType;

    @Schema(description = "온도 기록계 번호")
    private String thermometerNo;

    @Schema(description = "온도 기록계 통신 번호")
    private String thermometerCommNo;

    @Schema(description = "냉동기 유형")
    private String refrigeratorType;

    @Schema(description = "연료 유형")
    private String gasType;

    @Schema(description = "연료량")
    private BigDecimal gasCube;

    @Schema(description = "연비")
    private BigDecimal fuelEfficiency;

    @Schema(description = "기준 연비")
    private BigDecimal baseFuelEfficiency;

    @Schema(description = "차량 등록증 등록일", example = "YYYYMMDD")
    private String registrationDate;

    @Schema(description = "기타 정보1")
    private String carOther01;

    @Schema(description = "기타 정보2")
    private String carOther02;

    @Schema(description = "기타 정보3")
    private String carOther03;

    @Schema(description = "상태")
    private String status;

    @Schema(description = "삭제 여부")
    private String delYn;

    @Schema(description = "데이터 흐름 제어")
    private String trafficCop;

    @Schema(description = "아카이브 제어")
    private String archiveCop;

    @Schema(description = "최초 등록 시간", example = "YYYYMMDDHH24MISS")
    private String addDate;

    @Schema(description = "최종 변경 시간", example = "YYYYMMDDHH24MISS")
    private String editDate;

    @Schema(description = "최초 등록자 (사용자명(ID) 형식)", example = "홍길동(ADDUSER)")
    private String addWho;

    @Schema(description = "최종 변경자 (사용자명(ID) 형식)", example = "김철수(EDITUSER)")
    private String editWho;

    @Schema(description = "PDA 보안 인증 번호")
    private String pdaSecurityKey;

    @Schema(description = "PDA 보안 인증 만료 기간", example = "YYYYMMDD")
    private String pdaSecExpiDate;

    @Schema(description = "운전자 ID")
    private String driverId;

    @Schema(description = "운전자명")
    private String driverName;

    @Schema(description = "운전자 성별")
    private String driverSex;

    @Schema(description = "운전자 생년월일", example = "YYYYMMDD")
    private String driverBirthday;

    @Schema(description = "운전자 그룹")
    private String driverGroup;

    @Schema(description = "운전자 타입")
    private String driverType;

    @Schema(description = "운전자 카테고리")
    private String driverCategory;

    @Schema(description = "운전자 주소1")
    private String address1;

    @Schema(description = "운전자 주소2")
    private String address2;

    @Schema(description = "운전자 주소3")
    private String address3;

    @Schema(description = "운전자 주소4")
    private String address4;

    @Schema(description = "운전자1 전화번호")
    private String phone1;

    @Schema(description = "운전자2 전화번호")
    private String phone2;

    @Schema(description = "운전자1 전화번호 (중복)")
    private String phone3;

    @Schema(description = "운전자2 전화번호 (중복)")
    private String phone4;

    @Schema(description = "운전자1 PDA 전화번호")
    private String driverPdaPhone1; // 충돌 방지를 위해 이름 변경

    @Schema(description = "운전자2 PDA 전화번호")
    private String driverPdaPhone2; // 충돌 방지를 위해 이름 변경

    @Schema(description = "면허 번호")
    private String licenseNo;

    @Schema(description = "면허 유형")
    private String licenseType;

    @Schema(description = "면허 등록 일자", example = "YYYYMMDD")
    private String licenContDate;

    @Schema(description = "면허 만료 일자", example = "YYYYMMDD")
    private String licenExpiDate;

    @Schema(description = "사업자 번호")
    private String vatNo;

    @Schema(description = "사업장명")
    private String vatName;

    @Schema(description = "사업주명")
    private String vatOwner;

    @Schema(description = "업태")
    private String vatType;

    @Schema(description = "업종")
    private String vatCategory;

    @Schema(description = "사업자 주소1")
    private String vatAddress1;

    @Schema(description = "사업자 주소2")
    private String vatAddress2;

    @Schema(description = "사업자 주소3")
    private String vatAddress3;

    @Schema(description = "사업자 주소4")
    private String vatAddress4;

    @Schema(description = "사업자 전화")
    private String vatPhone;

    @Schema(description = "사업자 팩스")
    private String vatFax;

    @Schema(description = "소속사 키")
    private String agentKey;

    @Schema(description = "소속사명")
    private String agentName;

    @Schema(description = "계약 유형")
    private String driverContractType; // 중복 필드이므로 이름 변경

    @Schema(description = "계약 일자", example = "YYYYMMDD")
    private String driverContractDate; // 중복 필드이므로 이름 변경

    @Schema(description = "계약 만료 일자", example = "YYYYMMDD")
    private String driverExpireDate; // 중복 필드이므로 이름 변경

    @Schema(description = "계약 금액")
    private BigDecimal driverContractPrice; // 중복 필드이므로 이름 변경

    @Schema(description = "보험 회사")
    private String insuCompany;

    @Schema(description = "보험 유형")
    private String insuranceType;

    @Schema(description = "보험명")
    private String insuranceName; // 쿼리에서 `INSURANCE`로 선택되므로 `insurance`로 이미 존재

//    @Schema(description = "보험 계약일", example = "YYYYMMDD")
//    private String insuContDate; // 중복 필드이므로 이름 변경
//
//    @Schema(description = "보험 만료일", example = "YYYYMMDD")
//    private String insuExpiDate; // 중복 필드이므로 이름 변경

    @Schema(description = "기타 정보1")
    private String driverOther01;

    @Schema(description = "기타 정보2")
    private String driverOther02;

    @Schema(description = "기타 정보3")
    private String driverOther03;

    @Schema(description = "기타 정보4")
    private String driverOther04;

    @Schema(description = "기타 정보5")
    private String driverOther05;

    @Schema(description = "대권역")
    private String lArea;

    @Schema(description = "중권역")
    private String mArea;

    @Schema(description = "소권역")
    private String sArea;

    @Schema(description = "입차 시간")
    private String indt;

    @Schema(description = "출차 시간")
    private String outdt;

    @Schema(description = "도크 번호")
    private String dockNo;

    @Schema(description = "메모")
    private String carMemo;

    @Schema(description = "요청 사항")
    private String note;

    @Schema(description = "강성 유무")
    private String checkCar;

    @Schema(description = "연계 배송 차량1")
    private String linkCarNo1;

    @Schema(description = "연계 배송 차량2")
    private String linkCarNo2;

    @Schema(description = "연계 배송 차량3")
    private String linkCarNo3;

    @Schema(description = "블루투스 스캐너 지급 여부")
    private String btScannerGive;

    @Schema(description = "SMP 지급 여부")
    private String smpGive;

    @Schema(description = "운전자 메모")
    private String driverMemo;

    @Schema(description = "운전자 요청 사항")
    private String driverNote;

    @Schema(description = "적재물 구분 유형")
    private String cargoDivType;

    @Schema(description = "최대 상차량")
    private BigDecimal maxLanding;

    @Schema(description = "연계 차량1 권역명")
    private String linkCarName1;

    @Schema(description = "연계 차량2 권역명")
    private String linkCarName2;

    @Schema(description = "연계 차량3 권역명")
    private String linkCarName3;

    @Schema(description = "POP 번호")
    private String popNo;
    
    @Schema(description = "최종 변경자")
    private String editWhoNm;
    
    @Schema(description = "최초 등록자")
    private String addWhoNm;
    
    @Schema(description = "행정동 코드")
    private String hjdongCd;

    @Schema(description = "출발지 코드")
    private String fromDccode;

    @Schema(description = "차고지 위도")
    private BigDecimal latitude;

    @Schema(description = "차고지 경도")
    private BigDecimal longitude;

    @Schema(description = "적정 적재 중량")
    private BigDecimal optLoadWeight;

    @Schema(description = "차량 높이")
    private BigDecimal vehicleHeight;

    @Schema(description = "연식", example = "2025")
    private String vehicleYear;

    @Schema(description = "차종 코드")
    private String vehicleTypeCd;

    @Schema(description = "조달 가능 여부")
    private String procYn;

    @Schema(description = "재회전 가능 여부")
    private String reuseYn;

    @Schema(description = "조달유형 구분 코드")
    private String procTypeCd;

    @Schema(description = "조달 시작 시간", example = "HHMI")
    private String procFromHour;

    @Schema(description = "조달 종료 시간", example = "HHMI")
    private String procToHour;

    @Schema(description = "적정 PLT 수")
    private BigDecimal optPlt;

    @Schema(description = "차량 너비")
    private BigDecimal carWidth;

    @Schema(description = "차량 길이")
    private BigDecimal carLength;

    @Schema(description = "배송 여부")
    private String deliveryYn;

    @Schema(description = "수송 여부")
    private String carryYn;

    @Schema(description = "당일 조달 여부")
    private String procSamedayYn;

    @Schema(description = "창고 조달 여부")
    private String procStorageYn;

    @Schema(description = "자차 여부")
    private String ownCarYn;

    @Schema(description = "반품 여부")
    private String returnYn;

    @Schema(description = "보조 운전자 여부")
    private String subDriverYn;

    @Schema(description = "고객사 코드")
    private String custKey;
    
    @Schema(description = "고객사명")
    private String custName;
    
    @Schema(description = "정산여부")
    private String sttlYn;
    
    @Schema(description = "시작일", example = "YYYYMMDD")
    private String fromDate;

    @Schema(description = "종료일", example = "YYYYMMDD")
    private String toDate;

    @Schema(description = "종료일2", example = "YYYYMMDD")
    private String toDate2;
    
    @Schema(description = "근무시작시간")
    private String workFromHour;
    
    @Schema(description = "근무종료시간")
    private String workToHour;
    
    @Schema(description = "마감유형")
    private String carOrderCloseCd;
    
    /** 반경 */
    @Schema(description = "반경", example = "100")
    private BigDecimal radius;
    
    /** 체류시간 */
    @Schema(description = "체류시간", example = "100")
    private BigDecimal stytime;
    
    /** 2회전 기본적재중량  */
    @Schema(description = "2회전 기본적재중량")
    private BigDecimal baseWeight2nd;
    
    /** 2회전 최대적재중량 */
    @Schema(description = "2회전 최대적재중량")
    private BigDecimal maxWeight2nd;
    
    /** 2회전 기본착지수 */
    @Schema(description = "2회전 기본착지수")
    private String baseLanding2nd;
    
    /** 2회전 최대착지수 */
    @Schema(description = "2회전 최대착지수 ")
    private String maxLanding2nd;
}
