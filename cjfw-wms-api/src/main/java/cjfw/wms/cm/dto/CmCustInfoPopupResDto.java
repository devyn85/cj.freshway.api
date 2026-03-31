package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.06.23
 * @description : 거래처 정보(단건) 팝업조회 Response DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.23 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "거래처 정보(단건) 팝업조회 Response DTO")
public class CmCustInfoPopupResDto {
	/** 고객사코드 */
    @Schema(description = "고객사코드", example = "ST001")
    private String storerkey;

    /** 거래처명 */
    @Schema(description = "거래처명", example = "삼성전자")
    private String description;

    /** 사업주명 */
    @Schema(description = "사업주명", example = "홍길동")
    private String owner;

    /** 우편번호 */
    @Schema(description = "우편번호", example = "12345")
    private String zipcode;

    /** 관리 사원명1 */
    @Schema(description = "관리 사원명1", example = "김대리")
    private String empname1;

    /** 관리 사원 전화번호1 */
    @Schema(description = "관리 사원 전화번호1", example = "010-1111-2222")
    private String empphone1;

    /** 관리 사원명2 */
    @Schema(description = "관리 사원명2", example = "박대리")
    private String empname2;

    /** 관리 사원 전화번호2 */
    @Schema(description = "관리 사원 전화번호2", example = "010-3333-4444")
    private String empphone2;

    /** MA */
    @Schema(description = "MA", example = "MA123")
    private String ma;

    /** MA명 */
    @Schema(description = "MA명", example = "유통1팀")
    private String maname;
    
    /** MA연락처 */
    @Schema(description = "MA연락처", example = "유통1팀")
    private String maphone;

    /** 거래처 타입 */
    @Schema(description = "거래처 타입", example = "D")
    private String custtype;

    /** 부가세 유형 */
    @Schema(description = "부가세 유형", example = "과세")
    private String vattype;

    /** 부가세 카테고리 */
    @Schema(description = "부가세 카테고리", example = "A1")
    private String vatcategory;

    /** 사업자 번호 */
    @Schema(description = "사업자 번호", example = "123-45-67890")
    private String vatno;





    /** 고객사코드 */
    @Schema(description = "고객사코드")
    private String custkey;

    /** 고객사명 */
    @Schema(description = "고객사명")
    private String custname;

    /** 고객사명 */
    @Schema(description = "거래처등급")
    private String custgroup;

    /** 고객사명 */
    @Schema(description = "고객유형")
    private String custgroupname;

    /** 고객사명 */
    @Schema(description = "거래처 마감유형")
    private String custstrategy4;

    /** 주소매칭여부 */
    @Schema(description = "주소매칭여부")
    private String addressmatchyn;

    /** 본 주소 */
    @Schema(description = "본 주소")
    private String address1;

    /** 상세주소 */
    @Schema(description = "상세주소")
    private String address2;

    /** 전체주소 */
    @Schema(description = "전체주소")
    private String address;

    /** 대면검수여부 */
    @Schema(description = "대면검수여부")
    private String faceinspect;

    /** OTD(배송요구시간) */
    @Schema(description = "OTD(배송요구시간)")
    private String reqdlvtime2;

    /** OTD 시작시간 */
    @Schema(description = "OTD 시작시간")
    private String reqdlvtime2From;

    /** OTD 종료시간 */
    @Schema(description = "OTD 종료시간")
    private String reqdlvtime2To;

    /** 납품가능시간 */
    @Schema(description = "납품가능시간")
    private String deliveryavailabletime ;

    /** 건물개방시간 */
    @Schema(description = "건물개방시간")
    private String buildingopentime;

    /** 격오지여부 */
    @Schema(description = "격오지여부")
    private String distantYn;

    /** 납품대기여부 */
    @Schema(description = "납품대기여부")
    private String dlvWaitYn;

    /** 키즈분류여부 */
    @Schema(description = "키즈분류여부")
    private String kidsClYn;

    /** 장거리여부 */
    @Schema(description = "장거리여부")
    private String lngDistantYn;

    /** 하차난이도코드 */
    @Schema(description = "하차난이도코드")
    private String unloadLvlCd;

    /** 특수조건 통합표기 */
    @Schema(description = "특수조건 통합표기")
    private String specialConditions;

    /** 온도기록지 제출대상 */
    @Schema(description = "온도기록지 제출대상")
    private String tempertype;

    private String tempertypename;

    /** 출입KEY정보 */
    @Schema(description = "출입KEY정보")
    private String keytype;

    /** 출입KEY세부정보 */
    @Schema(description = "출입KEY세부정보")
    private String keydetail;

    /** 고객사 연락처 */
    @Schema(description = "고객사 연락처")
    private String phone1;

    /** 배송도착 알림 수신 여부 */
    @Schema(description = "배송도착 알림 수신 여부")
    private String deliverynotiyn;

    /** 담당MA명 */
    @Schema(description = "담당MA명")
    private String truthempname;

    /** 담당MA연락처 */
    @Schema(description = "담당MA연락처")
    private String truthphone;

    /** 메모 */
    @Schema(description = "메모")
    private String memo;

    /** 등록자명 */
    @Schema(description = "등록자명")
    private String addwhonm;

    /** 수정자명 */
    @Schema(description = "수정자명")
    private String editwhonm;

    /** 등록일시 */
    @Schema(description = "등록일시")
    private String adddate;

    /** 수정일시 */
    @Schema(description = "수정일시")
    private String editdate;

}