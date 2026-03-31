package cjfw.wms.ms.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import cjfw.wms.ms.entity.MsPlantXSLFileEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.05.30
 * @description : 저장위치정보 저장 요청 dto
 * @issues : <pre>
 * -----------------------------------------------------------
 * DATE AUTHOR MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.30 KwonJungYun (jungyun8667@cj.net) 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "저장위치정보 요청")
public class MsPlantXSLSaveReqDto extends CommonDto {

	/** 첨부파일 docId */
	@Schema(description = "첨부파일 docId")
	private String docId;

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

    @Schema(description = "파일키")
    private String serialkey;

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

	@Schema(description = "첨부파일")
    private List<MsPlantXSLFileEntity> fileList;

    /** 협력사 */
    @Schema(description = "협력사" , nullable = false, example = "")
    private String memo;
}
