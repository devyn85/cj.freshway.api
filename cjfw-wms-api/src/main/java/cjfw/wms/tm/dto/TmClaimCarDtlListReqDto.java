package cjfw.wms.tm.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Copyright 2025. CJ Freshway Co. all rights reserved.
* @author : ParkJinWoo 
* @date : 2025.08.07 
* @description : 배송클레임정보 조회 요청Dto 기능을 구현한 Java Class 
* @issues : 
* ----------------------------------------------------------- 
* DATE AUTHOR MAJOR_ISSUE 
* ----------------------------------------------------------- 
* 2025.08.07 ParkJinWoo 생성
*/

@Data
@Builder
@NoArgsConstructor            
@AllArgsConstructor
@Schema(description = "배송클레임정보 조회 요청Dto")
public class TmClaimCarDtlListReqDto extends CommonDto {
	
	/**저장 리스트*/
	@Schema(description = "저장리스트")
	List<TmClaimCarListDtlResDto> saveList;

    @Schema(description = "SAP클레임번호")
    private String sapClaimNo;
    
    @Schema(description = "물류센터")
    private String fixDcCode;
}
