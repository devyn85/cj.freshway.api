package cjfw.wms.dev.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.com) 
 * @date : 2025.05.08 
 * @description : OO 기능을 구현한 Controller Class 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.08 SangSuSung(kduimux@cj.com) 생성 </pre>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true) 	
@Schema(description = "출고진행현황 목록 요청") 
public class DevPilot02ReqDto extends CommonProcedureDto {
	/** 메인그리드 저장 리스트 */
	List<DevPilot02ResDto> saveList;	
	
	/** 물류센터 */
	@Schema(description = "물류센터", nullable = false, example = "")
	private String dccode;				
	
	/** 상품코드 */
	@Schema(description = "상품코드", nullable = false, example = "")
	private String sku;
	
	/** 상품코드 다중선택 */
	private String multiSku;
	
	/** 코드리스트 */
    private String[] codeList;
    
	/** 사원번호 */
	@Schema(description = "사원번호", nullable = false, example = "")
	private String empNo;    
}
