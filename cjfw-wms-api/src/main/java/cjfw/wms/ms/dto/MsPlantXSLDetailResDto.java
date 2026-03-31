package cjfw.wms.ms.dto;

import java.util.List;
import java.util.Map;

import cjfw.wms.ms.entity.MsExDCSpecRateEntity;
import cjfw.wms.ms.entity.MsPlantXSLFileEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.05.29
 * @description : 저장위치정보 조회 결과 Dto Class
 * @issues : <pre>
 * -----------------------------------------------------------
 * DATE AUTHOR MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.29 KwonJungYun (jungyun8667@cj.net) 생성
 */
@Data
@Schema(description = "저장위치정보 조회 결과")
public class MsPlantXSLDetailResDto {

	@Schema(description = "물류센터 코드")
    private String dccode;

    @Schema(description = "플랜트 코드")
    private String plant;

    @Schema(description = "저장위치")
    private String storageloc;

    @Schema(description = "내역/설명")
    private String description;

    @Schema(description = "우편번호")
    private String zipcode;

    @Schema(description = "기본 주소")
    private String address1;

    @Schema(description = "상세 주소")
    private String address2;

    @Schema(description = "전화번호 1")
    private String phone1;

    @Schema(description = "전화번호 2")
    private String phone2;

    @Schema(description = "팩스번호 1")
    private String fax;

    @Schema(description = "이메일 1")
    private String email;

    @Schema(description = "사업자번호")
    private String vatno;

    @Schema(description = "팩스번호 2")
    private String fax2;

    @Schema(description = "이메일 2")
    private String email2;

    @Schema(description = "협력사 코드")
    private String partnercode;

    @Schema(description = "권역")
    private String district;

    @Schema(description = "지역")
    private String area;

    @Schema(description = "계약 시작일")
    private String contractstartdate;

    @Schema(description = "계약 종료일")
    private String contractenddate;

    @Schema(description = "저장 품목")
    private String savecode;

    @Schema(description = "계약 여부")
    private String contractyn;

    @Schema(description = "비고")
    private String remark;

    @Schema(description = "사이트 ID")
    private String siteid;

    @Schema(description = "사이트 비밀번호")
    private String sitepw;

    @Schema(description = "사이트 주소")
    private String siteaddr;

    @Schema(description = "파일키")
    private String serialkey;

    @Schema(description = "계약서 수")
    private String fileCnt;

    @Schema(description = "계약 갱신 알림 여부")
    private String contractrenewalalam;

    @Schema(description = "첨부파일 목록 (파일명 리스트)")
    private List<String> attachedfiles;

    @Schema(description = "계약 유의사항")
    private String contractmemo;

    @Schema(description = "계약 연도 목록")
    private List<Map<String, Object>> contractyears;

	@Schema(description = "첨부파일")
    private List<MsPlantXSLFileEntity> fileList;

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

    /** 계약변경알림여부 */
    @Schema(description = "계약변경알림여부", nullable = false, example = "")
    private String contractchgntcyn;

    /** 계약비고 */
    @Schema(description = "계약비고", nullable = false, example = "")
    private String contractrmk;

    /** 소비기한 이메일발송 */
    @Schema(description = "소비기한 이메일발송", nullable = false, example = "")
    private String usebydateemailsendyn;

    /** 소비기한 잔여율 */
    @Schema(description = "소비기한 잔여율", nullable = false, example = "")
    private String usebydatefreert;

    /** 협력사 */
    @Schema(description = "협력사" , nullable = false, example = "")
    private String memo;

    /** 협력사명 */
    @Schema(description = "협력사명" , nullable = false, example = "")
    private String memoname;

    /** 창고 요율 정보 */
    @Schema(description = "창고 요율 정보" , nullable = false, example = "")
    private List<MsExDCSpecRateEntity> specRateList;
}
