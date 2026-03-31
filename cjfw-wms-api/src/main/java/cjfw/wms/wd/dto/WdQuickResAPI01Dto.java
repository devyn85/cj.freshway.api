package cjfw.wms.wd.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.12.09
 * @description : 퀵접수(VSR)및처리 오더등록 DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.09 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Data
@Schema(description = "퀵접수(VSR)및처리 오더등록 DTO")
public class WdQuickResAPI01Dto extends CommonProcedureDto {

    /** 인성과 연동된 ID 값(cjfreshway.stnlogis.com 로그인 가능 유무로 확인가능) */
    @Schema(description = "인성과 연동된 ID 값(cjfreshway.stnlogis.com 로그인 가능 유무로 확인가능)")
    private String userId;

    /** 접수자 이름ㆍ상호 */
    @Schema(description = "접수자 이름ㆍ상호")
    private String cName;

    /** 접수자 연락처 */
    @Schema(description = "접수자 연락처")
    private String cMobile;

    /** 접수자 부서명 */
    @Schema(description = "접수자 부서명")
    private String cDeptName;

    /** 접수자 담당 */
    @Schema(description = "접수자 담당")
    private String cChargeName;

    /** 출발지 상호ㆍ이름 */
    @Schema(description = "출발지 상호ㆍ이름")
    private String sStart;

    /** 출발지 전화번호 */
    @Schema(description = "출발지 전화번호")
    private String startTelno;

    /** 출발지 부서명 */
    @Schema(description = "출발지 부서명")
    private String deptName;

    /** 출발지 담당 */
    @Schema(description = "출발지 담당")
    private String chargeName;

    /** 출발지 시ㆍ도 */
    @Schema(description = "출발지 시ㆍ도")
    private String startSido;

    /** 출발지 구ㆍ군 */
    @Schema(description = "출발지 구ㆍ군")
    private String startGugun;

    /** 출발지 동명 */
    @Schema(description = "출발지 동명")
    private String startDong;

    /** 출발지 지번(리) */
    @Schema(description = "출발지 지번(리)")
    private String startRi;

    /** 출발지 상세주소 */
    @Schema(description = "출발지 상세주소")
    private String startLocation;

    /** 도착지 상호ㆍ이름 */
    @Schema(description = "도착지 상호ㆍ이름")
    private String sDest;

    /** 도착지 전화번호 */
    @Schema(description = "도착지 전화번호")
    private String destTelno;

    /** 도착지 부서명 */
    @Schema(description = "도착지 부서명")
    private String destDept;

    /** 도착지 담당 */
    @Schema(description = "도착지 담당")
    private String destCharge;

    /** 도착지 시ㆍ도 */
    @Schema(description = "도착지 시ㆍ도")
    private String destSido;

    /** 도착지 구ㆍ군 */
    @Schema(description = "도착지 구ㆍ군")
    private String destGugun;

    /** 도착지 동명 */
    @Schema(description = "도착지 동명")
    private String destDong;

    /** 도착지 지번(리) */
    @Schema(description = "도착지 지번(리)")
    private String destRi;

    /** 도착지 상세주소 */
    @Schema(description = "도착지 상세주소")
    private String destLocation;

    /** 배송수단(1:오토, 2:다마스, 3:트럭, 4:밴, 5:라보, 6:지하철, 7:플렉스) */
    @Schema(description = "배송수단(1:오토, 2:다마스, 3:트럭, 4:밴, 5:라보, 6:지하철, 7:플렉스)")
    private String kind;

    /** 지급방법(1:선불, 2:착불, 3:신용, 4:송금, 5:수금) */
    @Schema(description = "지급방법(1:선불, 2:착불, 3:신용, 4:송금, 5:수금)")
    private String payGbn;

    /** 배송방법(1:편도, 3:왕복, 5:경유) */
    @Schema(description = "배송방법(1:편도, 3:왕복, 5:경유)")
    private String doc;

    /** 배송선택(1:일반, 3:급송, 5:조조, 7:야간, 8:할증, 9:과적, 0:택배, A:심야, B:휴일, C:납품, D:대기, F:눈비, 4:독차, 6:혼적, G:할인, M:마일, H:우편, I:행랑, J:해외, K:신문, Q:퀵, N:보관, O:혹한, P:상하차, R:명절) */
    @Schema(description = "배송선택(1:일반, 3:급송, 5:조조, 7:야간, 8:할증, 9:과적, 0:택배, A:심야, B:휴일, C:납품, D:대기, F:눈비, 4:독차, 6:혼적, G:할인, M:마일, H:우편, I:행랑, J:해외, K:신문, Q:퀵, N:보관, O:혹한, P:상하차, R:명절)")
    private String sfast;

    /** 물품종류(1:서류봉투, 2:소박스, 3:중박스, 4:대박스) */
    @Schema(description = "물품종류(1:서류봉투, 2:소박스, 3:중박스, 4:대박스)")
    private String itemType;

    /** 전달내용 */
    @Schema(description = "전달내용")
    private String memo;

    /** SMS로 전달 받을 핸드폰번호 */
    @Schema(description = "SMS로 전달 받을 핸드폰번호")
    private String smsTelno;

    /** 예약시간 사용여부(체크시:3) */
    @Schema(description = "예약시간 사용여부(체크시:3)")
    private String useCheck;

    /** 예약일(오늘 기준 최대 한달까지 선택가능) */
    @Schema(description = "예약일(오늘 기준 최대 한달까지 선택가능)")
    private String pickupDate;

    /** 예약일(시간단위) */
    @Schema(description = "예약일(시간단위)")
    private String pickHour;

    /** 예약일(분단위) */
    @Schema(description = "예약일(분단위)")
    private String pickMin;

    /** 예약일(초단위) */
    @Schema(description = "예약일(초단위)")
    private String pickSec;

    /** 차종구분 코드(01: 플축카고, 11: 리프트카고, ...) */
    @Schema(description = "차종구분 코드(01: 플축카고, 11: 리프트카고, ...)")
    private String carKind;

    /** 여분필드 */
    @Schema(description = "여분필드")
    private String oEtc1;

    /** 여분필드 */
    @Schema(description = "여분필드")
    private String oEtc2;

    /** 여분필드 */
    @Schema(description = "여분필드")
    private String oEtc3;

    /** 여분필드 */
    @Schema(description = "여분필드")
    private String oEtc4;

    /** 여분필드 */
    @Schema(description = "여분필드")
    private String oEtc5;

    /** 여분필드 */
    @Schema(description = "여분필드")
    private String oEtc6;

    /** 여분필드 */
    @Schema(description = "여분필드")
    private String oEtc7;

    /** 여분필드 */
    @Schema(description = "여분필드")
    private String oEtc8;

    /** 여분필드 */
    @Schema(description = "여분필드")
    private String oEtc9;

    /** 여분필드 */
    @Schema(description = "여분필드")
    private String oEtc10;  
    
    
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String fixdccode;	    
    
    /** 물류센터 - 그리드용*/
    @Schema(description = "물류센터")
    private String dccode;     
    
    // 응답
    /** serial_number */
    @Schema(description = "serial_number")
    private String serial_number; 
    
    /** 퀵주문번호 */
    @Schema(description = "퀵주문번호")
    private String quickDocno;   

    /** 센터접수번호 */
    @Schema(description = "센터접수번호")
    private String rcptNo;    
    
}
