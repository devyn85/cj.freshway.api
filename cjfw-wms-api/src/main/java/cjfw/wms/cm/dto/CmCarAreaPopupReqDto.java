package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net) 
 * @date : 2025.05.09 
 * @description : 차량+권역 팝업 조회 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "차량+권역 팝업 조회 요청 DTO")
public class CmCarAreaPopupReqDto extends CommonDto {
	
	/** 차량번호 */
	@Schema(description = "차량번호", nullable = false, example = "충북82바5527")
	private String code;
	
	/** 차량+권역 */
	@Schema(description = "권역", example = "화천")
	private String name     ;
	
	/** 다중선택 */
	@Schema(description = "다중(차량번호)선택", example = "충북82바5527,충북82아5503,충북82아5505")
	private String multiSelect;
	
}
