package cjfw.wms.comfunc.func.bbs.dto;

import java.util.List;

import cjfw.core.file.dto.FileComInfoDto;
import lombok.Data;

/**
 * 공지사항 상세 Response DTO
 */
@Data
public class BbsBoardDetailGetResDto {
	private String bbsTp;
	private String bbsTpCd;
	private String regrNm;
	private String regId;
	private String regrDtm;
	private String thruDt;
	private String fromDt;
	private String topYn;
	private String vwCnt;
	private String bbsTitle;
	private String bbsNote;
	private String popYn;
	private String vwYn;
	private String attchFileGrpNo;
	private String bbsScpCd;
	
	private List<FileComInfoDto> fileGroup;
	
}
