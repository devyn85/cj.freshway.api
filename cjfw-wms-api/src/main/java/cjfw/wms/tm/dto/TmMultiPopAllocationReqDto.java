package cjfw.wms.tm.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.06.02 
 * @description : 거래처별이중 POP 배차 현황 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.02    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "거래처별이중 POP 배차 현황 요청") 
public class TmMultiPopAllocationReqDto extends CommonDto {	
    /** 저장 리스트 */
    List<TmMultiPopAllocationResDto> saveList; 
    
    /** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String fixdccode;
    
    /** 배송일자 */
	@Schema(description = "배송일자", nullable = false, example = "")
	private String deliverydate;
	
	/** 거래처코드 */
	@Schema(description = "거래처코드", nullable = false, example = "")
	private String custkey;
	
	/** 거래처코드 */
	@MultiSearch 
    @Schema(description = "거래처코드", nullable = false, example = "")
    private List<String> custkeyMulti;
	
}
