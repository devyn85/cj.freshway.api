package cjfw.wms.tm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Copyright 2025. CJ Freshway Co. all rights reserved.
* @author : ParkJinWoo 
* @date : 2025.08.07 
* @description : 배송클레임정보 클레임세부리스트 조회 요청Dto 기능을 구현한 Java Class 
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
@Schema(description = "배송클레임정보 클레임세부리스트 조회 요청Dto")
public class TmClaimCarClaimListReqDto extends CommonDto {
	


    /** 코드리스트 */
    @Schema(description = "코드리스트")
    private String codeList;
    
    /**기본코드값*/
    @Schema(description = "기본코드값")
    private String baseCode;
}
