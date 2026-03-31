package cjfw.wms.ms.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import cjfw.wms.common.extend.CommonProcedureDto;
import cjfw.wms.ms.entity.MsPopMngEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : 
 * @date        : 2026.03.05
 * @description : 차량정보 조회 조건 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.03.05            생성
 */
@Data
@Schema(description = "차량정보 조회 조건 DTO")
public class MsCarDriverExcelReqDto extends CommonProcedureDto {
	/** 대량업로드리스트(excel) */
	@Schema(description = "대량업로드리스트(excel)")
	private List<MsCarDriverExcelResDto> saveList;
	
	/** spid */
	@Schema(description = "spid", example = "")
	private String spid;
	
	@Schema(description = "pakagename", example = "")
	private String pakagename;
	
	@Schema(description = "테이블 시리얼 번호")
    private String serialKey;
	
	@Schema(description = "멀티 물류센터 코드", example = "2600")
    private String multiDcCode[];

    @Schema(description = "물류센터 코드", example = "2600")
    private String dcCode;
	
    @Schema(description = "계약 유형")
    private String contractType;

    @Schema(description = "차량 고유 ID")
    private String carId;

    @Schema(description = "차량 번호")
    private String carNo;

    @Schema(description = "차량 용적")
    private BigDecimal carCapacity;

    @Schema(description = "최대 적재 중량")
    private BigDecimal maxWeight;

    @Schema(description = "적재함 사이즈")
    private String cargoSize;

    @Schema(description = "적재함 유형")
    private String cargoType;

    @Schema(description = "DTG 유형")
    private String dtgType;

    @Schema(description = "GPS 유형")
    private String gpsType;

    @Schema(description = "차량 등록증 등록일")
    private String registrationDate;

    @Schema(description = "기타 정보3")
    private String carOther03;

    @Schema(description = "최대 상차량")
    private BigDecimal maxLanding;

    @Schema(description = "차량 그룹")
    private String carGroup;

    @Schema(description = "강성 유무")
    private String checkCar;

    @Schema(description = "입차 시간")
    private String indt;

    @Schema(description = "출차 시간")
    private String outdt;

    @Schema(description = "대권역")
    private String lArea;

    @Schema(description = "중권역")
    private String mArea;

    @Schema(description = "소권역")
    private String sArea;

    @Schema(description = "연계 배송 차량1")
    private String linkCarNo1;

    @Schema(description = "연계 배송 차량2")
    private String linkCarNo2;

    @Schema(description = "연계 배송 차량3")
    private String linkCarNo3;

    @Schema(description = "차량 단축 번호")
    private String shortNo;

    @Schema(description = "블루투스 스캐너 지급 여부")
    private String btScannerGive;

    @Schema(description = "PDA 전화번호2")
    private String pdaPhone2;

    @Schema(description = "PDA 전화번호3")
    private String pdaPhone3;

    @Schema(description = "PDA 전화번호4")
    private String pdaPhone4;

    @Schema(description = "중복 로그인 가능 여부")
    private String allowLoginYn;

    @Schema(description = "SMP 지급 여부")
    private String smpGive;

    @Schema(description = "PDA 전화번호1")
    private String pdaPhone1;

    @Schema(description = "운전자 이름")
    private String driverName;

    @Schema(description = "운전자1 전화번호")
    private String phone1;
    
    @Schema(description = "운전자2 전화번호")
    private String phone2;

    @Schema(description = "운전자1 전화번호")
    private String phone3;

    @Schema(description = "운전자2 전화번호")
    private String phone4;

    @Schema(description = "운전자3 ID")
    private String driver3;

    @Schema(description = "운전자3 ID (중복)")
    private String driver3d;

    @Schema(description = "운전자4 ID")
    private String driver4;

    @Schema(description = "삭제 여부")
    private String delYn;

    @Schema(description = "최종 변경일")
    private String editDate;

    @Schema(description = "차량 용적 설명 (길이-너비-높이)")
    private String carCubeDescr;

    @Schema(description = "운전자 ID")
    private String driverId;

    @Schema(description = "연계 차량1 권역명")
    private String linkCarName1;

    @Schema(description = "연계 차량2 권역명")
    private String linkCarName2;

    @Schema(description = "연계 차량3 권역명")
    private String linkCarName3;

    @Schema(description = "최종 등록자명 (ID 포함)")
    private String editWho; // 쿼리에서 이미 '사용자명(ID)' 형식으로 처리

    @Schema(description = "시작일", example = "YYYYMMDD")
    private String fromDate;

    @Schema(description = "종료일", example = "YYYYMMDD")
    private String toDate;

    @Schema(description = "종료일2", example = "YYYYMMDD")
    private String toDate2;

    @Schema(description = "체크 여부")
    private String checkYn;
    
    @Schema(description = "운전자 1 이름", maxLength = 35)
    private String driver1;

    @Schema(description = "운전자 2 이름", maxLength = 35)
    private String driver2;
    
    @Schema(description = "정산여부")
    private String sttlYn;
    
    @Schema(description = "온도 기록계 번호")
    private String thermometerNo;
    
    @Schema(description = "온도기록계통신번호", maxLength = 20)
    private String thermometerCommNo;
    
    @Schema(description = "차량 너비")
    private BigDecimal carWidth;

    @Schema(description = "차량 길이")
    private BigDecimal carLength;
    
    @Schema(description = "차량높이")
    private BigDecimal vehicleHeight;
    
    @Schema(description = "적정 적재 중량")
    private BigDecimal optLoadWeight;
    
    @Schema(description = "차고지 위도")
    private BigDecimal latitude;

    @Schema(description = "차고지 경도")
    private BigDecimal longitude;
    
    @Schema(description = "조달 시작 시간", example = "HHMI")
    private String procFromHour;

    @Schema(description = "조달 종료 시간", example = "HHMI")
    private String procToHour;

    @Schema(description = "적정 PLT 수")
    private BigDecimal optPlt;
    
    @Schema(description = "차고지 주소1")
    private String garageAddress1;
    
    @Schema(description = "차고지 주소2")
    private String garageAddress2;
    
    @Schema(description = "차고지 주소3")
    private String garageAddress3;
    
    @Schema(description = "차고지 주소4")
    private String garageAddress4;
    
    @Schema(description = "운전자 메모")
    private String driverMemo;
    
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
    
    @Schema(description = "연식", maxLength = 4)
    private String vehicleYear;

    @Schema(description = "차량타입코드", maxLength = 10)
    private String vehicleTypeCd;
    
    @Schema(description = "재회전 가능 여부")
    private String reuseYn;
    
    @Schema(description = "운송협력사 코드")
    private String custKey;
    
    @Schema(description = "차량 소유 업체 키")
    private String carAgentKey;

    @Schema(description = "차량 소유 업체명")
    private String carAgentName;

    @Schema(description = "차량 업체 전화번호")
    private String carAgentPhone;
    
    @Schema(description = "출고 그룹 코드")
    private String outGroupCd;

    @Schema(description = "입차 시간", example = "0900")
    private String inTime;

    @Schema(description = "출차 시간", example = "1800")
    private String outTime;

    @Schema(description = "POP 번호")
    private String popno;

    @Schema(description = "TC 센터 코드")
    private String tcDcCode;

    @Schema(description = "도크 번호")
    private String dockno;
    
    @Schema(description = "연비")
    private BigDecimal fuelEfficiency;
    
    @Schema(description = "유효기간 만료여부")
    private String isExpirationDateExpired;
    
    @Schema(description = "근무시작시간")
    private String workFromHour;
    
    @Schema(description = "근무종료시간")
    private String workToHour;
    
    @Schema(description = "연비")
    private String stdFuelEfficiency;
    
    @Schema(description = "마감유형")
    private String carOrderCloseCd;
    
    @Schema(description = "센터 입출차정보")
    private List<MsCarDriverExcelReqDto> carDriverList;
    
    @Schema(description = "센터 입출차정보")
    private List<MsCarDriverExcelReqDto> entryInfoList;
    
    @Schema(description = "테이블 시리얼 번호")
    private String entrySerialKey;
    
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