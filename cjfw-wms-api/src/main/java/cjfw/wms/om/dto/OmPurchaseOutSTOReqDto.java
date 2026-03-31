package cjfw.wms.om.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.07.08
 * @description : 외부창고 이체발주 처리 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.08    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부창고 이체발주 처리 요청") 
public class OmPurchaseOutSTOReqDto extends CommonProcedureDto {	
    
    /** 저장리스트 */
    List<OmPurchaseOutSTOResDto> saveList;	
    
    /** 물류센터 */
	@Schema(description = "물류센터", nullable = false, example = "")
	private String dccode;

	/** 문서번호 */
    @Schema(description = "문서번호", nullable = false, example = "")
    private String docno;
    
    /** 상품코드 */
    @Schema(description = "상품코드", nullable = false, example = "")
    private String sku;
    
    /** I/F구분 */
    @Schema(description = "I/F구분", nullable = false, example = "")
    private String updateIfFlag;
	
	/** 인터페이스메모 */
    @Schema(description = "인터페이스메모", nullable = false, example = "")
    private String updateIfMemo;	
    
	/** I/F구분 */
    @Schema(description = "I/F구분", nullable = false, example = "")
    private String searchIfFlag;
   
}
