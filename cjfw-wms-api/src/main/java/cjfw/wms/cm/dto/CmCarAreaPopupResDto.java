package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net) 
 * @date : 2025.05.09 
 * @description : 차량+권역 팝업 조회 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@Data
@Schema(description = "차량+권역 팝업 조회 응답 DTO")
public class CmCarAreaPopupResDto {

	/** 차량번호 */
	@Schema(description = "차량번호", nullable = false, example = "충북82바5527")
	private String code;
	
	/** 권역 */
	@Schema(description = "권역", example = "")
	private String name     ;
	
	/** 계약유형 */
	@Schema(description = "계약유형 ( 지입, 임시용차 등)", example = "FIXTEMPORARY,DELIVERY")
	private String contracttype     ;
	
	
}