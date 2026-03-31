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
* @description : 배송전달사항 조회 요청Dto 기능을 구현한 Java Class 
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
@Schema(description = "배송전달사항 조회 요청Dto")
public class TmInplanMsgListReqDto extends CommonDto {
	
	/**저장 리스트*/
	@Schema(description = "저장리스트")
	List<TmInplanMsgListResDto> saveList;
	/** 시작문서일자(YYYYMMDD) */
    @Schema(description = "시작문서일자")
    private String fromDocdt;

    /** 종료문서일자(YYYYMMDD) */
    @Schema(description = "종료문서일자")
    private String toDocdt;

    /** 차량번호 */
    @Schema(description = "차량번호")
    private String carNo;

    /** SAP등록자 */
    @Schema(description = "SAP등록자")
    private String sapAddWho;

    /** 영업조직 */
    @Schema(description = "영업조직")
    private String saleOrganize;

    /** 고객코드(콤마 구분 문자열) */
    @Schema(description = "고객코드 다중선택")
    private String toCustkey;
    
    /** 고객코드(콤마 구분 문자열) */
    @Schema(description = "고객코드 다중선택")
    private String dcCode;
}
