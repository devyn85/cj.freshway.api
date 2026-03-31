package cjfw.wms.comfunc.func.bbs.dto;

import lombok.Data;

/**
 * 공지사항 리스트 조회 Request DTO
 */
@Data
public class BbsBoardListGetReqDto {
	private String isAdmin;			/*관리자 여부*/
	private String bbsScpCd;		/*공지대상*/
	private String bbsTpCd;			/*공지구분*/
	private String viewYn;			/*공지여부*/
	private String searchType; 		/*제목/제목+내용*/
	private String bbsTitleNote;	/*제목명*/
	private String fromDt;			/*게시기간 From*/
	private String thruDt;			/*게시기간 To*/
}
