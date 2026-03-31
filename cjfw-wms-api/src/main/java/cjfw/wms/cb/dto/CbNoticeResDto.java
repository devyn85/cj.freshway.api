package cjfw.wms.cb.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.09.19 
 * @description : 공지사항 조회 응답 DTO
 */

@Data
@Schema(description = "공지사항 조회 응답")
public class CbNoticeResDto extends CommonDto {
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
    
    /** 팝업노출여부 */
    @Schema(description = "팝업노출여부")
    private String popYn;
    
    /** 게시문서종류코드 */
    @Schema(description = "게시문서종류코드")
    private String brdDocKndCd;
    
    /** 최초등록자 */
	@Schema(description = "최초등록자", example = "dev01")
	private String addWho;
    
    /** 최종변경자 */
	@Schema(description = "최종변경자", example = "dev01")
	private String editWho;
	
	/** 최초등록시간 */
	@Schema(description = "최초등록시간", example = "")
	private String addDate;
    
    /** 최종변경시간 */
	@Schema(description = "최종변경시간", example = "")
	private String editDate;
	
	/** 등록자명 */
	@Schema(description = "등록자명", example = "")
	private String regNm;
    
    /** 수정자명 */
	@Schema(description = "수정자명", example = "")
	private String updNm;
	
	/** 게시시작일자 */
	@Schema(description = "게시시작일자", example = "")
	private String brdStDt;
	
	/** 게시종료일자 */
	@Schema(description = "게시종료일자", example = "")
	private String brdExprDt;
	
	/** 첨부파일유무 */
	@Schema(description = "첨부파일유무", example = "")
	private String fileYn;
	
	/** 공지대상 */
	@Schema(description = "공지대상", example = "")
	private String brdTarget;
	
	/** 삭제여부 */
	@Schema(description = "삭제여부", example = "")
	private String delYn;
	
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}