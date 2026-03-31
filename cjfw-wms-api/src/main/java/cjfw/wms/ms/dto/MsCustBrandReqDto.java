package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.07.10 
 * @description : 본점별 브랜드 마스터 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.27 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "본점별 브랜드 조회 요청 DTO")
public class MsCustBrandReqDto extends CommonDto{
	@Schema(description = "데이터 번호")
    private String serialKey;
	
	@Schema(description = "고객사 코드", example = "FW00")
	private String storerKey;
	
	@Schema(description = "다중 선택", example = "11,22,33")
	private String multiSelect;
	
	//	이하 병합 대상 컬럼
	@Schema(description = "본점 코드", example = "54135")
	private String custKey;
	
	@Schema(description = "본점명", example = "(주)대단한에프앤비(대단한갈비)")
	private String custName;
	
	@Schema(description = "거래처유형", example = "C")
	private String custType;
	
	@Schema(description = "브랜드명", example = "대단한갈비")
	private String brandName;
	
	@Schema(description = "FC브랜드코드", example = "")
	private String reference01;
	
	@Schema(description = "FC브랜드명", example = "")
	private String reference02;
	
    @Schema(description = "기타정보3", example = "")
    private String reference03;
}
