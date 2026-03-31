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
public class TmClaimCarListReqDto extends CommonDto {
	
	/**저장 리스트*/
	@Schema(description = "저장리스트")
	List<TmClaimCarListResDto> saveList;

//    /** 클레임 유형(상세 L) */
//    @Schema(description = "클레임유형L")
//    private String claimtypeL;

    /** 배송시작일 */
    @Schema(description = "배송시작일")
    private String fromDeliverydt;

    /** 배송종료일 */
    @Schema(description = "배송종료일")
    private String toDeliverydt;

    /** 다중 고객사코드 */
    @Schema(description = "다중고객사코드")
    private String multiToCustkey;

    /** 다중 차량번호 */
    @Schema(description = "다중차량번호")
    private String multiCarno;
    
    /**클레임 세분류*/
    @Schema(description = "클레임 세분류")
    private String claimDtlIdS;
    
    /**물류센터*/
    @Schema(description = "물류센터")
    private String fixDcCode;
    
    private String claimtypeL;
    private String claimtypeM;
    private String claimtypeS;
    private String claimtypeD;
    
    private String fromdt;
    private String todt;
    
}
