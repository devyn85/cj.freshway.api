package cjfw.wms.comfunc.func.file.dto;

import lombok.Data;

/**
 * 샘플 파일 조회 Response DTO
 */
@Data
public class SampleFilePageGetResDto {
	private String bbsSeq;	/* 게시번호 */
	private String bbsScp;	/* 공지대상 */
	private String bbsTp;	/* 공지구분 */
	private String bbsTitle;	/* 제목 */
	private String attchYn;	/* 첨부파일 */
	private String vwCnt;	/* 조회수 */
	private String regDt;	/* 게시일 */
	private String topYn;	/* 최상위 여부 */
	private String regrNm;	/* 게시자 */
	private String attchFileGrpNo;	/* 첨부파일 번호 */
}

/**
 * [MPA 참조]
 * > 응답
 * "data":[{"YM":"2021-06","CNT":91},{"YM":"2021-07","CNT":72}, ... ]
 */