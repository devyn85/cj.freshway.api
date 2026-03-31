package cjfw.wms.cb.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.04.30 
 * @description : 커뮤니티 게시판 조회 요청 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "커뮤니티 게시판 조회 요청")
public class CbCommunityReqDto extends CommonDto {
	
	/** 시리얼키 */
    @Schema(description = "시리얼키")
    private Long serialKey;
    
    /** 게시판번호 */
    @Schema(description = "게시판번호", example = "")
    private String brdNum;

    /** 문서구분코드 */
    @Schema(description = "문서구분코드", example = "ADMINBOARD")
    private String brdDocDivCd;

    /** 게시자ID */
    @Schema(description = "게시자ID", example = "")
    private String brdUsrId;

    /** 게시자센터코드 */
    @Schema(description = "게시자센터코드", example = "")
    private String brdUsrDcCode;

    /** 게시판제목 */
    @Schema(description = "게시판제목", example = "")
    private String brdTit;

    /** 게시판내용 */
    @Schema(description = "게시판내용", example = "")
    private String brdCntt;
    
    /** 시작일시 */
    @Schema(description = "시작일시")
    private String brdStDt;

    /** 만료일자 */
    @Schema(description = "만료일자", example = "")
    private String brdExprDt;

    /** 답변SEQ */
    @Schema(description = "답변SEQ", example = "0")
    private String seqNum;

    /** 삭제여부 */
    @Schema(description = "삭제여부", example = "N")
    private String delYn;

    /** 기타 검색조건 */
    @Schema(description = "기타 검색조건", example = "")
    private String etc;
}