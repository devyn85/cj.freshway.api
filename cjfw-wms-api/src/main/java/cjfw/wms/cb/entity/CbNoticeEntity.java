package cjfw.wms.cb.entity;

import java.util.Date;

import cjfw.wms.common.extend.CommonTriggerDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author      : JiSooKim (jskim14@cj.net)
 * @date        : 2025.09.19
 * @description : 공지사항 Entity
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.19        JiSooKim (jskim14@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "공지사항 Entity")
public class CbNoticeEntity extends CommonTriggerDto {
    /** 시리얼키 */
    @Schema(description = "시리얼키")
    private Long serialKey;

    /** 게시글번호 */
    @Schema(description = "게시글번호", maxLength = 20)
    private String brdNum;

    /** 작성자부서코드 */
    @Schema(description = "작성자부서코드", maxLength = 20)
    private String brdUsrDcCode;

    /** 작성자ID */
    @Schema(description = "작성자ID", maxLength = 20)
    private String brdUsrId;

    /** 등록일시 */
    @Schema(description = "등록일시", maxLength = 20)
    private String brdRegDt;

    /** 시작일시 */
    @Schema(description = "시작일시", maxLength = 20)
    private String brdStDt;

    /** 만료일시 */
    @Schema(description = "만료일시", maxLength = 20)
    private String brdExprDt;

    /** 문서구분코드 */
    @Schema(description = "문서구분코드", maxLength = 20)
    private String brdDocDivCd;

    /** 문서종류코드 */
    @Schema(description = "문서종류코드", maxLength = 20)
    private String brdDocKndCd;

    /** 제목 */
    @Schema(description = "제목", maxLength = 200)
    private String brdTit;

    /** 내용 */
    @Schema(description = "내용", maxLength = 4000)
    private String brdCntt;

    /** 수신부서코드 */
    @Schema(description = "수신부서코드", maxLength = 10, defaultValue = "STD")
    private String rcvDcCode;

    /** 수신창고키 */
    @Schema(description = "수신창고키", maxLength = 20, defaultValue = "STD")
    private String rcvStorerKey;

    /** 수신조직 */
    @Schema(description = "수신조직", maxLength = 10, defaultValue = "STD")
    private String rcvOrganize;

    /** 수신지역 */
    @Schema(description = "수신지역", maxLength = 10, defaultValue = "STD")
    private String rcvArea;

    /** 수신자ID */
    @Schema(description = "수신자ID", maxLength = 20, defaultValue = "STD")
    private String rcvUsrId;

    /** 정렬순서 */
    @Schema(description = "정렬순서")
    private Integer arrOrd;

    /** 조회빈도 */
    @Schema(description = "조회빈도")
    private Integer qryFrq;

    /** 사용여부 */
    @Schema(description = "사용여부", maxLength = 1)
    private String useYn;

    /** 상태 */
    @Schema(description = "상태", maxLength = 10, defaultValue = "00")
    private String status;

    /** 삭제여부 */
    @Schema(description = "삭제여부", maxLength = 1, defaultValue = "N")
    private String delYn;

    /** 트래픽담당자 */
    @Schema(description = "트래픽담당자", maxLength = 10)
    private String trafficCop;

    /** 아카이브담당자 */
    @Schema(description = "아카이브담당자", maxLength = 1)
    private String archiveCop;

    /** 등록일자 */
    @Schema(description = "등록일자")
    private Date addDate;

    /** 수정일자 */
    @Schema(description = "수정일자")
    private Date editDate;

    /** 등록자 */
    @Schema(description = "등록자", maxLength = 24, defaultValue = "SYSTEM")
    private String addWho;

    /** 수정자 */
    @Schema(description = "수정자", maxLength = 24, defaultValue = "SYSTEM")
    private String editWho;

    /** 팝업여부 */
    @Schema(description = "팝업여부", maxLength = 1, defaultValue = "N")
    private String popYn;
    
    /** 이동URL */
    @Schema(description = "이동URL", maxLength = 1, defaultValue = "/cm/cmCode")
    private String redirectUrl;
}
