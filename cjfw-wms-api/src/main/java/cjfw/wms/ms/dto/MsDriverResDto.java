package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.com) 
 * @date : 2025.05.08 
 * @description : 기사정보 Controller Class 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.08 SangSuSung(kduimux@cj.com) 생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper=true) 
@Builder
public class MsDriverResDto extends CommonDto {
    /** 데이터번호 */
    @Schema(description = "데이터번호", example = "1")
    private String serialkey;

    /** 운전자고유키 */
    @Schema(description = "운전자고유키", example = "drv001")
    private String driverid;

    /** 운전자명 */
    @Schema(description = "운전자명", example = "김철수")
    private String drivername;

    /** 운전자성별 */
    @Schema(description = "운전자성별", example = "남")
    private String driversex;

    /** 운전자생년월일 */
    @Schema(description = "운전자생년월일", example = "19900101")
    private String driverbirthday;

    /** 운전자그룹 */
    @Schema(description = "운전자그룹", example = "GRP001")
    private String drivergroup;

    /** 운전자타입 */
    @Schema(description = "운전자타입", example = "TYPE_A")
    private String drivertype;

    /** 운전자카테고리 */
    @Schema(description = "운전자카테고리", example = "CATEGORY_B")
    private String drivercategory;

    /** 주소1 */
    @Schema(description = "주소1", example = "서울특별시")
    private String address1;

    /** 주소2 */
    @Schema(description = "주소2", example = "강남구")
    private String address2;

    /** 주소3 */
    @Schema(description = "주소3", example = "테헤란로 123")
    private String address3;

    /** 주소4 */
    @Schema(description = "주소4", example = "4층")
    private String address4;

    /** 운전자 1 전화번호 */
    @Schema(description = "운전자 1 전화번호", example = "01012345678")
    private String phone1;

    /** 운전자 2 전화번호 */
    @Schema(description = "운전자 2 전화번호", example = "07098765432")
    private String phone2;

    /** PDA 전화번호1 */
    @Schema(description = "PDA 전화번호1", example = "01011112222")
    private String pdaphone1;

    /** PDA 전화번호2 */
    @Schema(description = "PDA 전화번호2", example = "01033334444")
    private String pdaphone2;

    /** 면허번호 */
    @Schema(description = "면허번호", example = "11-12-123456-78")
    private String licenseno;

    /** 면허유형 */
    @Schema(description = "면허유형", example = "1종 보통")
    private String licensetype;

    /** 면허등록일자 */
    @Schema(description = "면허등록일자", example = "20100520")
    private String licencontdate;

    /** 면허만료일자 */
    @Schema(description = "면허만료일자", example = "20250520")
    private String licenexpidate;

    /** 사업자번호 */
    @Schema(description = "사업자번호", example = "1234567890")
    private String vatno;

    /** 사업장명 */
    @Schema(description = "사업장명", example = "ABC운수")
    private String vatname;

    /** 업무명 */
    @Schema(description = "업무명", example = "운송업")
    private String vatowner;

    /** 업태 */
    @Schema(description = "업태", example = "서비스")
    private String vattype;

    /** 업종 */
    @Schema(description = "업종", example = "화물운송")
    private String vatcategory;

    /** 사업자주소1 */
    @Schema(description = "사업자주소1", example = "서울특별시")
    private String vataddress1;

    /** 사업자주소2 */
    @Schema(description = "사업자주소2", example = "강남구")
    private String vataddress2;

    /** 사업자주소3 */
    @Schema(description = "사업자주소3", example = "역삼동")
    private String vataddress3;

    /** 사업자주소4 */
    @Schema(description = "사업자주소4", example = "테헤란로 100")
    private String vataddress4;

    /** 사업자전화 */
    @Schema(description = "사업자전화", example = "021234567")
    private String vatphone;

    /** 사업자팩스 */
    @Schema(description = "사업자팩스", example = "021234568")
    private String vatfax;

    /** 소속사키 */
    @Schema(description = "소속사키", example = "AG001")
    private String agentkey;

    /** 소속사명 */
    @Schema(description = "소속사명", example = "굿모닝운수")
    private String agentname;

    /** 계약유형 (지입, 임시용차 등) */
    @Schema(description = "계약유형", example = "지입")
    private String contracttype;

    /** 계약일자 */
    @Schema(description = "계약일자", example = "20200101")
    private String contractdate;

    /** 계약만료일자 */
    @Schema(description = "계약만료일자", example = "20251231")
    private String expiredate;

    /** 계약금액 */
    @Schema(description = "계약금액", example = "1000000")
    private String contractprice;

    /** 보험회사 */
    @Schema(description = "보험회사", example = "DB손해보험")
    private String insucompany;

    /** 보험유형 */
    @Schema(description = "보험유형", example = "책임보험")
    private String insurancetype;

    /** 보험명 */
    @Schema(description = "보험명", example = "운전자보험")
    private String insurance;

    /** 보험계약일 */
    @Schema(description = "보험계약일", example = "20230301")
    private String insucontdate;

    /** 보험만료일 */
    @Schema(description = "보험만료일", example = "20240228")
    private String insuexpidate;

    /** 기타정보1 */
    @Schema(description = "기타정보1", example = "비고1")
    private String other01;

    /** 기타정보2 */
    @Schema(description = "기타정보2", example = "비고2")
    private String other02;

    /** 기타정보3 */
    @Schema(description = "기타정보3", example = "비고3")
    private String other03;

    /** 기타정보4 */
    @Schema(description = "기타정보4", example = "비고4")
    private String other04;

    /** 기타정보5 */
    @Schema(description = "기타정보5", example = "비고5")
    private String other05;

    /** 상태 */
    @Schema(description = "상태", example = "ACTIVE")
    private String status;

    /** 삭제여부 */
    @Schema(description = "삭제여부", example = "N")
    private String delYn;

    /** 데이터흐름제어 */
    @Schema(description = "데이터흐름제어", example = "CONTROLLED")
    private String trafficcop;

    /** 아카이브제어 */
    @Schema(description = "아카이브제어", example = "ARCHIVED")
    private String archivecop;

    /** 최초등록시간 */
    @Schema(description = "최초등록시간", example = "2023-01-01 10:00:00")
    private String adddate;

    /** 최종변경시간 */
    @Schema(description = "최종변경시간", example = "2024-05-20 15:30:00")
    private String editdate;

    /** 최초등록자 */
    @Schema(description = "최초등록자", example = "ADMIN")
    private String addwho;

    /** 최종변경자 */
    @Schema(description = "최종변경자", example = "USER123")
    private String editwho;
}
