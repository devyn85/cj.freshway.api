package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.07 
 * @description : 배송클레임정보 클레임세부리스트Dto 기능을 구현한 Java Class 
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
@Schema(description = "배송클레임정보 클레임세부리스트 조회 Dto")
public class TmClaimCarClaimListResDto {

	
    /** 공통코드 */
    @Schema(description = "공통코드")
    private String specCode;
    /** 공통코드이름 */
    @Schema(description = "공통코드이름")
    private String specDescr;
    
    private String baseCode;

    private String speccategory;
    
    
}
