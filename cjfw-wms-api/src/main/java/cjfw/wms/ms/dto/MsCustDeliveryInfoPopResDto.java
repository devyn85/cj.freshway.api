package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2026.01.12 
 * @description : 추천실착지코드 팝업 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.01.12 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Schema(description = "추천실착지코드 팝업 응답 DTO")
public class MsCustDeliveryInfoPopResDto {
	
	/** 고객코드 */
	@Schema(description = "고객코드", example = "12340")
	private String custkey;
	
	/** 고객코드 */
	@Schema(description = "고객명")
	private String custname;
	
	    
    /** 지번주소 */
    @Schema(description = "지번주소", example = "")
    private String address1;
    
    /** 지번주소2 */
    @Schema(description = "지번주소2", example = "")
    private String address2;
        
    /** 실착지 거래처코드 */
    @Schema(description = "실착지 거래처코드", example = "")
    private String truthcustkey;
    
    /** 실착지 거래처코드 */
    @Schema(description = "실착지 거래처명", example = "")
    private String truthcustname;
    
    /** 실착지 거래처주소 */
    @Schema(description = "실착지 거래처주소", example = "")
    private String truthaddress1;
    
    /** 실착지 상세주소 */
    @Schema(description = "실착지 상세주소", example = "")
    private String truthaddress2;
        
    @Schema(description = "유사도", example = "")
    private String similarity;
}
