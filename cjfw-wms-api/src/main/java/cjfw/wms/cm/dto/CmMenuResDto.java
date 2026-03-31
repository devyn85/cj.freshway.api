package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.08.18 
 * @description : 메뉴 조회 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.18 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "메뉴 조회 응답")
public class CmMenuResDto {

	/** 프로그램코드 */
	@Schema(description = "프로그램코드", example = "WM10255045")
	private String progCd;
	
	/** 프로그램명 */
	@Schema(description = "프로그램명", example = "입고확정처리")
	private String progNm;
	
	/** 메뉴순번 */
	@Schema(description = "메뉴순번", example = "1")
	private String menuSeq;
	
	/** 프로그램내부순번 */
	@Schema(description = "프로그램내부순번", example = "0101040209")
	private String progNo;
	
	/** 프로그램URL */
	@Schema(description = "프로그램URL", example = "")
	private String progUrl;

}