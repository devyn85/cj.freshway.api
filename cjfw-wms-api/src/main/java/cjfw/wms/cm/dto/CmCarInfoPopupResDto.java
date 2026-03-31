package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.06.23
 * @description : 차량 정보 조회(단건) 응답 dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.23 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "차량 정보 조회(단건) 응답 dto")
public class CmCarInfoPopupResDto {

    /** 차량 일련번호 */
    @Schema(description = "차량 일련번호", example = "CAR000001")
    private String serialkey;

    /** 차량번호 */
    @Schema(description = "차량번호", example = "서울12가1234")
    private String carno;

    /** 차량그룹 */
    @Schema(description = "차량그룹", example = "1TON")
    private String cargroup;

    /** 차량종류 */
    @Schema(description = "차량종류", example = "냉동탑차")
    private String cartype;

    /** 차량형태 */
    @Schema(description = "차량형태", example = "탑차")
    private String carcategory;

    /** 차량용적 */
    @Schema(description = "차량용적", example = "2.5")
    private String carcapacity;

    /** 차량체적(장폭고) */
    @Schema(description = "차량체적(장폭고)", example = "400x200x180")
    private String carcubedescr;

    /** 차량브랜드 */
    @Schema(description = "차량브랜드", example = "현대")
    private String brandname;

    /** 운전자 1 이름 */
    @Schema(description = "운전자 1 이름", example = "홍길동")
    private String driver1;

    /** 운전자 2 이름 */
    @Schema(description = "운전자 2 이름", example = "김철수")
    private String driver2;

    /** 운전자 1 전화번호 */
    @Schema(description = "운전자 1 전화번호", example = "010-1111-2222")
    private String phone1;

    /** 운전자 2 전화번호 */
    @Schema(description = "운전자 2 전화번호", example = "010-3333-4444")
    private String phone2;

    /** 차량 소유주명 */
    @Schema(description = "차량 소유주명", example = "이영희")
    private String owner;

    /** 계약유형 */
    @Schema(description = "계약유형", example = "지입")
    private String contracttype;

    /** 계약일자 */
    @Schema(description = "계약일자", example = "20240101")
    private String contractdate;

    /** 계약만료일 */
    @Schema(description = "계약만료일", example = "20241231")
    private String expiredate;

    /** 차량 소유 업체 */
    @Schema(description = "차량 소유 업체", example = "CJ로지스틱스")
    private String caragentkey;

    /** 보험회사 */
    @Schema(description = "보험회사", example = "삼성화재")
    private String insurance;

    /** 보험계약일 */
    @Schema(description = "보험계약일", example = "20240101")
    private String insucontdate;

    /** 보험만료일 */
    @Schema(description = "보험만료일", example = "20241231")
    private String insuexpidate;

    /** 최소체적 */
    @Schema(description = "최소체적", example = "1.5")
    private String mincube;

    /** 최대체적 */
    @Schema(description = "최대체적", example = "3.0")
    private String maxcube;

    /** 최소중량 */
    @Schema(description = "최소중량", example = "500")
    private String minweight;

    /** 최대중량 */
    @Schema(description = "최대중량", example = "2000")
    private String maxweight;

    /** 상태 */
    @Schema(description = "상태", example = "ACTIVE")
    private String status;

    /** 삭제여부 */
    @Schema(description = "삭제여부", example = "N")
    private String delYn;

    /** 최초등록시간 */
    @Schema(description = "최초등록시간", example = "20250101120000")
    private String adddate;

    /** 최종변경시간 */
    @Schema(description = "최종변경시간", example = "20250623145900")
    private String editdate;

    /** 최초등록자 */
    @Schema(description = "최초등록자", example = "admin")
    private String addwho;

    /** 최종변경자 */
    @Schema(description = "최종변경자", example = "user01")
    private String editwho;
}