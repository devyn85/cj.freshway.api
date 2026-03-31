package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.05.12
 * @description : 협력사 검색 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.12 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "협력사 검색 응답")
public class CmSearchPartnerPopupResDto {
	/** 협력사코드 */
	@Schema(description = "협력사코드", nullable = false, example = "1012676")
	private String code;
	
	/** 협력사명 */
	@Schema(description = "협력사명", nullable = false, example = "사단법인 국제 청소년 연합")
	private String name;
	
	@Schema(description = "협력사유형", nullable = false, example = "비영리법인")
	private String custType;
	
	@Schema(description = "기본주소", nullable = false, example = "개인정보삭제")
	private String address1;
}