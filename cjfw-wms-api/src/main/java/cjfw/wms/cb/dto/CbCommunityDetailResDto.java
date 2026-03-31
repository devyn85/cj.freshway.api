package cjfw.wms.cb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net)
 * @date : 2025.08.22
 * @description : 커뮤니티 게시판 상세 조회 응답 DTO
 */
@Data
@Schema(description = "커뮤니티 게시판 상세 조회 응답")
public class CbCommunityDetailResDto {
    @Schema(description = "게시물 고유키", example = "1000001")
    private String serialKey;

    @Schema(description = "게시판번호", example = "1001")
    private String brdNum;

    @Schema(description = "게시자센터코드", example = "C001")
    private String brdUsrDcCode;

    @Schema(description = "게시자센터명", example = "서울센터")
    private String brdUsrDcName;

    @Schema(description = "게시자ID", example = "user01")
    private String brdUsrId;

    @Schema(description = "게시자명", example = "홍길동")
    private String brdUsrName;

    @Schema(description = "게시판등록일자", example = "20250822")
    private String brdRegDt;

    @Schema(description = "게시시작일자", example = "20250822")
    private String brdStDt;

    @Schema(description = "게시종료일자", example = "20250831")
    private String brdExprDt;

    @Schema(description = "게시문서구분코드", example = "DOC01")
    private String brdDocDivCd;

    @Schema(description = "게시문서종류코드", example = "KND01")
    private String brdDocKndCd;

    @Schema(description = "게시판제목", example = "공지사항")
    private String brdTit;

    @Schema(description = "게시판내용", example = "내용입니다.")
    private String brdCntt;

    @Schema(description = "정렬순서", example = "1")
    private Integer arrOrd;

    @Schema(description = "조회횟수", example = "15")
    private Integer qryFrq;

    @Schema(description = "삭제여부", example = "N")
    private String delYn;

    @Schema(description = "최초등록시간", example = "20250822093000")
    private String addDate;

    @Schema(description = "최종변경시간", example = "20250822120000")
    private String editDate;

    @Schema(description = "최초등록자", example = "user01")
    private String addWho;

    @Schema(description = "최초등록자명", example = "홍길동")
    private String addWhoName;

    @Schema(description = "최종변경자", example = "user02")
    private String editWho;
}
