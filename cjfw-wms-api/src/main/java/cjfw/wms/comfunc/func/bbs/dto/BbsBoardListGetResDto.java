package cjfw.wms.comfunc.func.bbs.dto;

import lombok.Data;

/**
 * 공지사항 리스트 조회 Response DTO
 */
@Data
public class BbsBoardListGetResDto {
	private String bbsSeq;     /* 게시번호 */
	private String bbsScpCd;     /* 공지대상 */
	private String bbsTp;      /* 공지구분 */
	private String bbsTitle;   /* 제목 */
	private String attchYn;    /* 첨부파일 */
	private String vwCnt;      /* 조회수 */
	private String topYn;      /* 최상위 여부 */
	private String regDt;      /* 게시일 */
	private String regrNm;     /* 게시자 */
}
