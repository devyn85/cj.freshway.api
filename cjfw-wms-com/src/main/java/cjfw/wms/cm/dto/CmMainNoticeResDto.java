package cjfw.wms.cm.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.10.30 
 * @description : 알림 응답
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "알림 응답")
public class CmMainNoticeResDto {
	/** 게시판번호 */
	@Schema(description = "게시판번호", example = "")
	private String brdnum;
	
	/** 제목 */
	@Schema(description = "제목", example = "")
	private String brdtit;
	
	/** 내용 */
	@Schema(description = "내용", example = "")
	private String brdcntt;
	
	/** 팝업여부 */
	@Schema(description = "팝업여부", example = "")
	private String popYn;
	
	/** 등록일자 */
	@Schema(description = "등록일자", example = "")
	private String adddate;
	
	/** 이동URL */
    @Schema(description = "이동URL", example = "/cm/cmCode")
    private String redirectUrl;
    
    /** 게시문서종류코드 */
    @Schema(description = "게시문서종류코드", example = "EVENT")
    private String brdDocKndCd;
    
    /** 읽음여부 */
    @Schema(description = "읽음여부", example = "N")
    private String readYn;
    
    /** 파일 목록 */
    @Schema(description = "파일 목록", example = "")
    private List<CmFileUploadResDto> fileList;
}
