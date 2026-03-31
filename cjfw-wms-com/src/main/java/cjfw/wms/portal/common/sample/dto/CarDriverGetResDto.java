package cjfw.wms.portal.common.sample.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.04.17
 * @description : 샘플 기능을 구현한 DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.04.17        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
public class CarDriverGetResDto {

	private String defDcname;
	private String pdaSecexpidate;
	private String insuexpiDate;
	
	@Schema(description = "데이터번호", nullable = false, example = "12345")
    private long serialKey;

    @Schema(description = "차량고유ID", nullable = false, example = "IC0171")
    private String carId;

    @Schema(description = "차량번호 전체", nullable = false, example = "12가 3456")
    private String carNo;

    @Schema(description = "차량단축번호", nullable = true, example = "001")
    private String shortNo;

    @Schema(description = "차량그룹", nullable = true, example = "일반")
    private String carGroup;

    @Schema(description = "차량종류", nullable = true, example = "카고")
    private String carType;

    @Schema(description = "차량형태", nullable = true, example = "탑차")
    private String carCategory;

    @Schema(description = "차량용적", nullable = true, example = "5톤")
    private String carCapacity;

    @Schema(description = "용적코드(Length)-너비(Width)-높이(Height) 단위 mm", nullable = true, example = "1000-2000-3000")
    private String carCubeDescr;

    @Schema(description = "적재함분리유형", nullable = true, example = "단일")
    private String cargoDivType;

    @Schema(description = "적재함1 체적", nullable = true, example = "10.5")
    private Double cargoDivCube1;

    @Schema(description = "적재함2 체적", nullable = true, example = "5.2")
    private Double cargoDivCube2;

    @Schema(description = "제조회사 브랜드", nullable = true, example = "현대")
    private String brandName;

    @Schema(description = "기본창고코드", nullable = true, example = "WH001")
    private String defDccode;

    @Schema(description = "기본권역코드", nullable = true, example = "SEOUL")
    private String defDistrictcode;

    @Schema(description = "운전자 1 이름", nullable = true, example = "홍길동")
    private String driver1;

    @Schema(description = "운전자 2 이름", nullable = true, example = "김철수")
    private String driver2;

    @Schema(description = "운전자 1 전화번호", nullable = true, example = "010-1234-5678")
    private String phone1;

    @Schema(description = "운전자 2 전화번호", nullable = true, example = "010-9876-5432")
    private String phone2;

    @Schema(description = "주소1", nullable = true, example = "서울특별시 강남구")
    private String address1;

    @Schema(description = "주소2", nullable = true, example = "테헤란로 123")
    private String address2;

    @Schema(description = "주소3", nullable = true, example = "상세주소")
    private String address3;

    @Schema(description = "주소4", nullable = true, example = "추가 상세주소")
    private String address4;

    @Schema(description = "차고지주소1", nullable = true, example = "경기도 성남시")
    private String garageAddress1;

    @Schema(description = "차고지주소2", nullable = true, example = "분당구")
    private String garageAddress2;

    @Schema(description = "차고지주소3", nullable = true, example = "상세 차고지 주소")
    private String garageAddress3;

    @Schema(description = "차고지주소4", nullable = true, example = "추가 상세 차고지 주소")
    private String garageAddress4;

    @Schema(description = "PDA 전화번호1", nullable = true, example = "011-111-2222")
    private String pdaphone1;

    @Schema(description = "PDA 전화번호2", nullable = true, example = "011-333-4444")
    private String pdaphone2;

    @Schema(description = "PDA 보안인증번호", nullable = true, example = "abcdefg")
    private String pdasecuritykey;

    @Schema(description = "PDA 보안인증만료기간", nullable = true, example = "2025-05-01")
    private String pdasecexpDate;

    @Schema(description = "차량 소유주명", nullable = true, example = "박사장")
    private String owner;

    @Schema(description = "계약유형 ( 지입, 임시용차 등)", nullable = true, example = "지입")
    private String contractType;

    @Schema(description = "계약일자", nullable = true, example = "20250428")
    private String contractDate;

    @Schema(description = "계약만료일자", nullable = true, example = "20260428")
    private String expireDate;

    @Schema(description = "계약금액", nullable = true, example = "1000000")
    private Double contractPrice;

    @Schema(description = "차량 소유 업체", nullable = true, example = "ABC 물류")
    private String carAgentKey;

    @Schema(description = "차량 소유 업체명", nullable = true, example = "에이비씨 물류")
    private String carAgentName;

    @Schema(description = "차량업체전화", nullable = true, example = "02-123-4567")
    private String carAgentPhone;

    @Schema(description = "차량업체팩스", nullable = true, example = "02-987-6543")
    private String carAgentFax;

    @Schema(description = "보험회사", nullable = true, example = "삼성화재")
    private String insurance;

    @Schema(description = "보험계약일", nullable = true, example = "20250101")
    private String insuContDate;

    @Schema(description = "보험만료일", nullable = true, example = "20251231")
    private String insuExpDate;

    @Schema(description = "적재함유형", nullable = true, example = "일반")
    private String cargoType;

    @Schema(description = "적재함사이트", nullable = true, example = "대형")
    private String cargoSize;

    @Schema(description = "적재함체적", nullable = true, example = "15.0")
    private Double cargoCube;

    @Schema(description = "최소 적재 가능 체적 cm^3 1CBM:1000000", nullable = true, example = "100000")
    private Double minCube;

    @Schema(description = "최대 적재 가능 체적 cm^3 1CBM:1000000", nullable = true, example = "1500000")
    private Double maxCube;

    @Schema(description = "최소적재중량", nullable = true, example = "1000")
    private Double minWeight;

    @Schema(description = "최대적재중량", nullable = true, example = "5000")
    private Double maxWeight;

    @Schema(description = "DTG유형", nullable = true, example = "디지털")
    private String dtgType;

    @Schema(description = "DTG장비번호", nullable = true, example = "DTG-123")
    private String dtgNo;

    @Schema(description = "DTG장비통신번호", nullable = true, example = "010-1111-2222")
    private String dtgCommNo;

    @Schema(description = "GPS유형", nullable = true, example = "유선")
    private String gpsType;

    @Schema(description = "GPS장비번호", nullable = true, example = "GPS-456")
    private String gpsNo;

    @Schema(description = "GPS통신번호", nullable = true, example = "010-3333-4444")
    private String gpsCommNo;

    @Schema(description = "온도기록계유형", nullable = true, example = "디지털")
    private String thermometerType;

    @Schema(description = "온도기록계번호", nullable = true, example = "TEMP-789")
    private String thermometerNo;

    @Schema(description = "온도기록계통신번호", nullable = true, example = "010-5555-6666")
    private String thermometerCommNo;

    @Schema(description = "냉동기유형", nullable = true, example = "THERMO KING")
    private String refrigeratorType;

    @Schema(description = "연료유형", nullable = true, example = "경유")
    private String gasType;

    @Schema(description = "연료량", nullable = true, example = "100")
    private Double gasCube;

    @Schema(description = "표준연비", nullable = true, example = "10.5")
    private Double stdFuelEfficiency;

    @Schema(description = "기준연비", nullable = true, example = "9.8")
    private Double baseFuelEfficiency;

    @Schema(description = "차량등록증등록일", nullable = true, example = "20200101")
    private String registrationDate;

    @Schema(description = "기타정보1", nullable = true, example = "특이사항 없음")
    private String other01;

    @Schema(description = "기타정보2", nullable = true, example = "")
    private String other02;

    @Schema(description = "기타정보3", nullable = true, example = "")
    private String other03;

    @Schema(description = "상태", nullable = false, example = "00")
    private String status;

    @Schema(description = "삭제여부", nullable = false, example = "N")
    private String delYn;

    @Schema(description = "데이터흐름제어", nullable = true, example = "Y")
    private String trafficCop;

    @Schema(description = "아카이브제어", nullable = true, example = "N")
    private String archiveCop;

    @Schema(description = "최초등록시간", nullable = false, example = "2025-04-28T16:30:00")
    private String addDate;

    @Schema(description = "최종변경시간", nullable = false, example = "2025-04-28T16:30:00")
    private String editDate;

    @Schema(description = "최초등록자", nullable = false, example = "SYSTEM")
    private String addWho;

    @Schema(description = "최종변경자", nullable = false, example = "ADMIN")
    private String editWho;

    @Schema(description = "강성차량", nullable = true, example = "Y")
    private String checkCar;

    @Schema(description = "대권역", nullable = true, example = "수도권")
    private String larea;

    @Schema(description = "중권역", nullable = true, example = "서울")
    private String marea;

    @Schema(description = "소권역", nullable = true, example = "강남")
    private String sarea;

    @Schema(description = "입차시간", nullable = true, example = "202504281000")
    private String indt;

    @Schema(description = "출차시간", nullable = true, example = "202504281700")
    private String outdt;

    @Schema(description = "도크번호", nullable = true, example = "DOCK01")
    private String dockNo;

    @Schema(description = "연계배송차량1", nullable = true, example = "IC0172")
    private String linkCarNo1;

    @Schema(description = "연계배송차량2", nullable = true, example = "IC0173")
    private String linkCarNo2;

    @Schema(description = "연계배송차량", nullable = true, example = "IC0174")
    private String linkCarNo3;

    @Schema(description = "메모(차량)", nullable = true, example = "주의사항")
    private String carMemo;

    @Schema(description = "요청사항(차량)", nullable = true, example = "후진 주의")
    private String note;

    @Schema(description = "최대착지수", nullable = true, example = "3")
    private String maxLanding;

    @Schema(description = "블루투스스캐너지급 유뮤", nullable = true, example = "Y")
    private String btScannerGive;

    @Schema(description = "블루투스스캐너 시리얼번호", nullable = true, example = "BT-12345")
    private String btScannerSn;

    @Schema(description = "스마트폰 권역", nullable = true, example = "강남")
    private String smpGive;

    @Schema(description = "스파트폰IME", nullable = true, example = "IMEI-1234567890")
    private String smpImei;

    @Schema(description = "보조기사명3", nullable = true, example = "박보조")
    private String driver3;

    @Schema(description = "보조기사명4", nullable = true, example = "최보조")
    private String driver4;

    @Schema(description = "보조기사전화번호3", nullable = true, example = "010-1111-3333")
    private String phone3;

    @Schema(description = "보조기사전화번호4", nullable = true, example = "010-4444-5555")
    private String phone4;

    @Schema(description = "메모(기사)", nullable = true, example = "친절한 기사")
    private String driverMemo;

    @Schema(description = "요청사항(기사)", nullable = true, example = "안전 운전")
    private String driverNote;

    @Schema(description = "신규모바일장비ID2", nullable = true, example = "PDA-002")
    private String pdaphone3;

    @Schema(description = "신규모바일장비ID2", nullable = true, example = "PDA-003")
    private String pdaphone4;

    @Schema(description = "중복로그인가능여부", nullable = true, example = "N")
    private String allowLoginYn;

    @Schema(description = "적용시작일자", nullable = true, example = "19000101")
    private String fromdate;

    @Schema(description = "적용종료일자", nullable = true, example = "29991231")
    private String todate;

    @Schema(description = "적용종료일자2", nullable = false, example = "19000101")
    private String todate2;
}
