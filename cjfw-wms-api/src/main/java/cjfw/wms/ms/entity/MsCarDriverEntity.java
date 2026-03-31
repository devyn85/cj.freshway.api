package cjfw.wms.ms.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.08.12
 * @description : 차량정보 Entity
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.12        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "차량정보 Entity") 
public class MsCarDriverEntity extends CommonDto {	
	@Schema(description = "차량번호 전체 (PK)", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 40)
    private String carNo;

    @Schema(description = "데이터번호 (DB Sequence에 의해 자동 생성)", accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal serialKey;

    @Schema(description = "차량고유ID (Unique)", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 40)
    private String carId;

    @Schema(description = "차량단축번호", maxLength = 40)
    private String shortNo;

    @Schema(description = "차량그룹", maxLength = 20)
    private String carGroup;

    @Schema(description = "차량종류", maxLength = 10)
    private String carType;

    @Schema(description = "차량형태", maxLength = 10)
    private String carCategory;

    @Schema(description = "차량용적", maxLength = 30)
    private String carCapacity;

    @Schema(description = "용적코드(Length)-너비(Width)-높이(Height) 단위 mm", maxLength = 50)
    private String carCubeDescr;

    @Schema(description = "적재함분리유형", maxLength = 10)
    private String cargoDivType;

    @Schema(description = "적재함1 체적")
    private BigDecimal cargoDivCube1;

    @Schema(description = "적재함2 체적")
    private BigDecimal cargoDivCube2;

    @Schema(description = "제조회사 브랜드", maxLength = 100)
    private String brandName;

    @Schema(description = "기본창고코드", maxLength = 10)
    private String dcCode;

    @Schema(description = "기본권역코드", maxLength = 10)
    private String defDistrictCode;

    @Schema(description = "운전자 1 이름", maxLength = 35)
    private String driver1;

    @Schema(description = "운전자 2 이름", maxLength = 35)
    private String driver2;

    @Schema(description = "운전자 1 전화번호", maxLength = 20)
    private String phone1;

    @Schema(description = "운전자 2 전화번호", maxLength = 20)
    private String phone2;

    @Schema(description = "주소1", maxLength = 300)
    private String address1;

    @Schema(description = "주소2", maxLength = 300)
    private String address2;

    @Schema(description = "주소3", maxLength = 300)
    private String address3;

    @Schema(description = "주소4", maxLength = 300)
    private String address4;

    @Schema(description = "차고지주소1", maxLength = 300)
    private String garageAddress1;

    @Schema(description = "차고지주소2", maxLength = 300)
    private String garageAddress2;

    @Schema(description = "차고지주소3", maxLength = 300)
    private String garageAddress3;

    @Schema(description = "차고지주소4", maxLength = 300)
    private String garageAddress4;

    @Schema(description = "PDA 전화번호1", maxLength = 20)
    private String pdaPhone1;

    @Schema(description = "PDA 전화번호2", maxLength = 30)
    private String pdaPhone2;

    @Schema(description = "PDA 보안인증번호", maxLength = 30)
    private String pdaSecurityKey;

    @Schema(description = "PDA 보안인증만료기간")
    private String pdaSecExpiDate;

    @Schema(description = "차량 소유주명", maxLength = 35)
    private String owner;

    @Schema(description = "계약유형 ( 지입, 임시용차 등)", maxLength = 20)
    private String contractType;

    @Schema(description = "계약일자", maxLength = 40)
    private String contractDate;

    @Schema(description = "계약만료일자", maxLength = 20)
    private String expireDate;

    @Schema(description = "계약금액", defaultValue = "0")
    private BigDecimal contractPrice = BigDecimal.ZERO;

    @Schema(description = "차량 소유 업체", maxLength = 30)
    private String carAgentKey;

    @Schema(description = "차량 소유 업체명", maxLength = 50)
    private String carAgentName;

    @Schema(description = "차량업체전화", maxLength = 50)
    private String carAgentPhone;

    @Schema(description = "차량업체팩스", maxLength = 20)
    private String carAgentFax;

    @Schema(description = "보험회사", maxLength = 30)
    private String insurance;

    @Schema(description = "보험계약일", maxLength = 8)
    private String insuContDate;

    @Schema(description = "보험만료일", maxLength = 8)
    private String insuExpiDate;

    @Schema(description = "적재함유형", maxLength = 10)
    private String cargoType;

    @Schema(description = "적재함사이트", maxLength = 20)
    private String cargoSize;

    @Schema(description = "적재함체적", defaultValue = "0")
    private BigDecimal cargoCube = BigDecimal.ZERO;

    @Schema(description = "최소 적재 가능 체적 cm^3", defaultValue = "0")
    private BigDecimal minCube = BigDecimal.ZERO;

    @Schema(description = "최대 적재 가능 체적 cm^3", defaultValue = "0")
    private BigDecimal maxCube = BigDecimal.ZERO;

    @Schema(description = "최소적재중량", defaultValue = "0")
    private BigDecimal minWeight = BigDecimal.ZERO;

    @Schema(description = "최대적재중량", defaultValue = "0")
    private BigDecimal maxWeight = BigDecimal.ZERO;

    @Schema(description = "DTG유형", maxLength = 10)
    private String dtgType;

    @Schema(description = "DTG장비번호", maxLength = 50)
    private String dtgNo;

    @Schema(description = "DTG장비통신번호", maxLength = 50)
    private String dtgCommNo;

    @Schema(description = "GPS유형", maxLength = 10)
    private String gpsType;

    @Schema(description = "GPS장비번호", maxLength = 50)
    private String gpsNo;

    @Schema(description = "GPS통신번호", maxLength = 20)
    private String gpsCommNo;

    @Schema(description = "온도기록계유형", maxLength = 10)
    private String thermometerType;

    @Schema(description = "온도기록계번호", maxLength = 20)
    private String thermometerNo;

    @Schema(description = "온도기록계통신번호", maxLength = 20)
    private String thermometerCommNo;

    @Schema(description = "냉동기유형", maxLength = 30)
    private String refrigeratorType;

    @Schema(description = "연료유형", maxLength = 10)
    private String gasType;

    @Schema(description = "연료량")
    private BigDecimal gasCube;

    @Schema(description = "연비")
    private BigDecimal fuelEfficiency;

    @Schema(description = "기준연비")
    private BigDecimal baseFuelEfficiency;

    @Schema(description = "차량등록증등록일", maxLength = 8)
    private String registrationDate;

    @Schema(description = "기타정보1", maxLength = 100)
    private String other01;

    @Schema(description = "기타정보2", maxLength = 300)
    private String other02;

    @Schema(description = "기타정보3", maxLength = 100)
    private String other03;

    @Schema(description = "상태", defaultValue = "00", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 10)
    private String status = "00";

    @Schema(description = "삭제여부", defaultValue = "N", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 1)
    private String delYn = "N";

    @Schema(description = "데이터흐름제어", maxLength = 10)
    private String trafficCop;

    @Schema(description = "아카이브제어", maxLength = 1)
    private String archiveCop;

    @Schema(description = "강성차량", maxLength = 10)
    private String checkCar;

    @Schema(description = "대권역", maxLength = 20)
    private String lArea;

    @Schema(description = "중권역", maxLength = 50)
    private String mArea;

    @Schema(description = "소권역", maxLength = 50)
    private String sArea;

    @Schema(description = "입차시간", maxLength = 6)
    private String inDt;

    @Schema(description = "출차시간", maxLength = 6)
    private String outDt;

    @Schema(description = "도크번호", maxLength = 20)
    private String dockNo;

    @Schema(description = "연계배송차량1", maxLength = 30)
    private String linkCarNo1;

    @Schema(description = "연계배송차량2", maxLength = 30)
    private String linkCarNo2;

    @Schema(description = "연계배송차량3", maxLength = 30)
    private String linkCarNo3;

    @Schema(description = "메모(차량)", maxLength = 100)
    private String carMemo;

    @Schema(description = "요청사항(차량)", maxLength = 200)
    private String note;

    @Schema(description = "최대착지수", maxLength = 10)
    private String maxLanding;

    @Schema(description = "블루투스스캐너지급 유뮤", maxLength = 10)
    private String btScannerGive;

    @Schema(description = "블루투스스캐너 시리얼번호", maxLength = 10)
    private String btScannerSn;

    @Schema(description = "스마트폰 권역", maxLength = 10)
    private String smpGive;

    @Schema(description = "스마트폰IMEI", maxLength = 10)
    private String smpImei;

    @Schema(description = "보조기사명3", maxLength = 35)
    private String driver3;

    @Schema(description = "보조기사명4", maxLength = 35)
    private String driver4;

    @Schema(description = "보조기사전화번호3", maxLength = 20)
    private String phone3;

    @Schema(description = "보조기사전화번호4", maxLength = 20)
    private String phone4;

    @Schema(description = "메모(기사)", maxLength = 30)
    private String driverMemo;

    @Schema(description = "요청사항(기사)", maxLength = 200)
    private String driverNote;

    @Schema(description = "신규모바일장비ID1", maxLength = 20)
    private String pdaPhone3;

    @Schema(description = "신규모바일장비ID2", maxLength = 20)
    private String pdaPhone4;

    @Schema(description = "중복로그인가능여부", maxLength = 1)
    private String allowLoginYn;

    @Schema(description = "적용시작일자", defaultValue = "19000101", maxLength = 8)
    private String fromDate = "19000101";

    @Schema(description = "적용종료일자", defaultValue = "29991231", maxLength = 8)
    private String toDate = "29991231";

    @Schema(description = "적용종료일자2", defaultValue = "19000101", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 8)
    private String toDate2 = "19000101";

    @Schema(description = "최초등록시간 (자동 생성)", accessMode = Schema.AccessMode.READ_ONLY)
    private String addDate;

    @Schema(description = "최종변경시간 (자동 갱신)", accessMode = Schema.AccessMode.READ_ONLY)
    private String editDate;

    @Schema(description = "최초등록자", defaultValue = "SYSTEM", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 24)
    private String addWho = "SYSTEM";

    @Schema(description = "최종변경자", defaultValue = "SYSTEM", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 50)
    private String editWho = "SYSTEM";

    @Schema(description = "행정동코드", maxLength = 10)
    private String hjDongCd;

    @Schema(description = "정책설명", maxLength = 200)
    private String plcyDesc;

    @Schema(description = "출발지센터코드", maxLength = 14)
    private String fromDcCode;

    @Schema(description = "위도", maxLength = 20)
    private String latitude;

    @Schema(description = "경도", maxLength = 20)
    private String longitude;

    @Schema(description = "적정적재중량")
    private BigDecimal optLoadWeight;

    @Schema(description = "차량높이")
    private BigDecimal vehicleHeight;

    @Schema(description = "연식", maxLength = 4)
    private String vehicleYear;

    @Schema(description = "차량타입코드", maxLength = 10)
    private String vehicleTypeCd;

    @Schema(description = "조달가능여부", maxLength = 1)
    private String procYn;

    @Schema(description = "다회전가능여부", maxLength = 1)
    private String reuseYn;

    @Schema(description = "조달유형코드", maxLength = 10)
    private String procTypeCd;

    @Schema(description = "조달시작시간", maxLength = 6)
    private String procFromHour;

    @Schema(description = "조달종료시간", maxLength = 6)
    private String procToHour;

    @Schema(description = "적정PLT수")
    private BigDecimal optPlt;
    
    @Schema(description = "정산여부")
    private String sttlYn;
    
    @Schema(description = "기타 정보3")
    private String carOther03;
    
    @Schema(description = "운전자 ID")
    private String driverId;
    
    @Schema(description = "운전자명")
    private String driverName;
    
    @Schema(description = "면허 유형")
    private String licenseType;
    
    @Schema(description = "기타 정보1")
    private String driverOther01;
    
    @Schema(description = "기타 정보2")
    private String driverOther02;
    
    @Schema(description = "기타 정보3")
    private String driverOther03;
    
    @Schema(description = "면허 번호")
    private String licenseNo;
    
    @Schema(description = "면허 만료 일자", example = "YYYYMMDD")
    private String licenExpiDate;
    
    @Schema(description = "면허 등록 일자", example = "YYYYMMDD")
    private String licenContDate;
    
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
    
    @Schema(description = "근무시작시간")
    private String workFromHour;
    
    @Schema(description = "근무종료시간")
    private String workToHour;
    
    @Schema(description = "연비")
    private String stdFuelEfficiency;
    
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


