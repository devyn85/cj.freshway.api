package cjfw.wms.tm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.09.10 
 * @description : 연비관리 기능을 구현한 REQ DTO Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.10 ParkJinWoo 생성
 */

@Data
@Builder
@NoArgsConstructor            
@AllArgsConstructor
@Schema(description = "연비관리 조회 reqDto")
public class TmHjdDataListReqDto extends CommonDto {

	 /** 행정동코드 */
    @Schema(description = "행정동코드")
    private String hjdongCd;

    /** 시도명 */
    @Schema(description = "시도명")
    private String ctpKorNm;

    /** 시군구명 */
    @Schema(description = "시군구명")
    private String sigKorNm;

    /** 행정동명 */
    @Schema(description = "행정동명")
    private String hjdongNm;

    /** 이동사유코드 */
    @Schema(description = "이동사유코드")
    private String mvmnResCd;
    

    /** 이동사유코드 */
    @Schema(description = "이동사유코드")
    private String dcCode;
    
    private String carrierRange;
    
    private String courier;
    
    private String base;
}
