package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.07.18 
 * @description : 거래처별POP관리 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.18 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class MsPopMngPOPResDto {
	
	/** 순번 */
	@Schema(description = "순번", example = "")
	private String seqno;

	/** 권한 */
	@Schema(description = "권한", example = "")
	private String authority;
	
	/** 코드리스트 */
	@Schema(description = "코드리스트", example = "")
	private String codelist;
	
	/** 기본코드값 */
	@Schema(description = "기본코드값", example = "")
	private String basecode;
	
	/** 기본코드설명 */
	@Schema(description = "기본코드설명", example = "")
	private String basedescr;
	
	/** 최초등록자 */
	@Schema(description = "최초등록자", example = "")
	private String addwho;

	/** 최초등록시간 */
	@Schema(description = "최초등록시간", example = "")
	private String adddate;

	/** 최종변경자 */
	@Schema(description = "최종변경자", example = "")
	private String editwho;

	/** 최종변경시간 */
	@Schema(description = "최종변경시간", example = "")
	private String editdate;
	
	/** 최초등록자 */
	@Schema(description = "최초등록자", example = "")
	private String regNm;
	
	/** 최종변경자 */
	@Schema(description = "최종변경자", example = "")
	private String updNm;

}