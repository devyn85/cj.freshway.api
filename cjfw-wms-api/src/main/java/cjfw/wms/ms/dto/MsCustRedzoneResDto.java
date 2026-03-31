package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.05.27 
 * @description : 특별관리고객현황 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.27 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "특별관리고객현황 조회 응답 DTO")
public class MsCustRedzoneResDto {
	@Schema(description = "고객코드", example = "121671001")
	private String custKey;
	
	@Schema(description = "고객명", example = "지에스칼텍스(주)여수공장")
	private String custNm;
	
	@Schema(description = "표시사항코드", example = "A006")
	private String markWord;
	
	@Schema(description = "표시사항명", example = "핵심고객")
	private String markWordNm;
	
	@Schema(description = "등록자", example = "김은혜님[keh1996ab]")
	private String regId;
	
	@Schema(description = "등록일시", example = "20250224191342")
	private String regDate;
	
	@Schema(description = "수정자", example = "LOGISONEIF")
	private String editWho;
	
	@Schema(description = "수정일시", example = "2025-02-24 오후 7:15:08")
	private String editDate;
	
	@Schema(description = "고객마감유형")
	private String custOrderCloseType;
}
