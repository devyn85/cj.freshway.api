package cjfw.wms.ms.dto;

import java.math.BigDecimal;

import cjfw.wms.common.annotation.MaskingName;
import cjfw.wms.common.annotation.MaskingTelno;
import cjfw.wms.common.extend.CommonDto;
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
@Schema(description = "차량정보 조회 결과 DTO")
public class MsCarDriverResDto extends CommonDto {	
	@Schema(description = "데이터 번호 (PK)")
	private String serialKey;
		
	@Schema(description = "기본 센터 코드")
    private String dcCode;
	
	@Schema(description = "기본 센터 명")
    private String dcName;
	
	@Schema(description = "TC 센터 코드")
    private String tcDcCode;
    
	@Schema(description = "TC 센터 명")
    private String tcName;
	
    @Schema(description = "계약 유형")
    private String contractType;

    @Schema(description = "차량 고유 ID")
    private String carId;

    @Schema(description = "차량 번호")
    private String carNo;

    @Schema(description = "차량 용적")
    private String carCapacity;

    @Schema(description = "적정 적재 중량")
    private BigDecimal optLoadWeight;
    
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
    private String vehicleYear;

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

    @Schema(description = "도크 번호")
    private String dockNo;

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

    @MaskingName
    @Schema(description = "운전자 이름")
    private String driverName;

    @MaskingTelno
    @Schema(description = "운전자1 전화번호")
    private String phone1;
    
    @MaskingTelno
    @Schema(description = "운전자2 전화번호")
    private String phone2;

    @MaskingTelno
    @Schema(description = "운전자1 전화번호")
    private String phone3;

    @MaskingTelno
    @Schema(description = "운전자2 전화번호")
    private String phone4;
    
    @MaskingName
    @Schema(description = "운전자2 ID")
    private String driver2;

    @MaskingName
    @Schema(description = "운전자3 ID")
    private String driver3;

    @MaskingName
    @Schema(description = "운전자3 ID (중복)")
    private String driver3d;

    @MaskingName
    @Schema(description = "운전자4 ID")
    private String driver4;

    @Schema(description = "삭제 여부")
    private String delYn;

    @Schema(description = "최종 변경일")
    private String editDate;

    @Schema(description = "차량 용적 설명 (길이-너비-높이)")
    private String carCubeDescr;
    
    @Schema(description = "운전자 ID")
    private String driver1;
    
    @Schema(description = "최종 등록자명 (ID 포함)")
    private String editWho; // 쿼리에서 이미 '사용자명(ID)' 형식으로 처리

    @Schema(description = "시작일", example = "YYYYMMDD")
    private String fromDate;

    @Schema(description = "종료일", example = "YYYYMMDD")
    private String toDate;

    @Schema(description = "종료일2", example = "YYYYMMDD")
    private String toDate2;
        
    @Schema(description = "최초 등록자")
    private String addWho;
    
    @Schema(description = "최초 등록일")
    private String addDate;
    
    @Schema(description = "차량 너비")
    private BigDecimal carWidth;

    @Schema(description = "차량 길이")
    private BigDecimal carLength;
    
    @Schema(description = "차량높이")
    private BigDecimal vehicleHeight;
    
    @Schema(description = "최종 변경자")
    private String editWhoNm;
    
    @Schema(description = "최초 등록자")
    private String addWhoNm;

    @Schema(description = "근무시작시간")
    private String workFromHour;
    
    @Schema(description = "근무종료시간")
    private String workToHour;
    
    @Schema(description = "연비")
    private BigDecimal fuelEfficiency;
    
    /** 반경 */
    @Schema(description = "반경", example = "100")
    private BigDecimal radius;
    
    /** 체류시간 */
    @Schema(description = "체류시간", example = "100")
    private BigDecimal stytime;
}
