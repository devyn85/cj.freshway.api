package cjfw.wms.cb.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.04.30 
 * @description : 커뮤니티 게시판/답변 조회 응답 DTO
 */

@Data
@Schema(description = "커뮤니티 게시판/답변 조회 응답")
public class CbCommunityResDto extends CommonDto {
    /** 게시물 고유키 */
    @Schema(description = "게시물 고유키", example = "1000001")
    private String serialKey;

    /** 게시판번호 */
    @Schema(description = "게시판번호", example = "1001")
    private String brdNum;

    /** 답변SEQ */
    @Schema(description = "답변SEQ", example = "0")
    private int seqNum;

    /** 게시판제목 */
    @Schema(description = "게시판제목", example = "공지사항")
    private String brdTit;

    /** 게시자ID */
    @Schema(description = "게시자ID", example = "user01")
    private String brdUsrId;

    /** 게시자센터코드 */
    @Schema(description = "게시자센터코드", example = "C001")
    private String brdUsrDcCode;

    /** 게시자센터명 */
    @Schema(description = "게시자센터명", example = "서울센터")
    private String brdUsrDcName;

    /** 게시판등록일자 */
    @Schema(description = "게시판등록일자", example = "20250822")
    private String brdRegDt;

    /** 조회횟수 */
    @Schema(description = "조회횟수", example = "15")
    private int qryFrq;
}