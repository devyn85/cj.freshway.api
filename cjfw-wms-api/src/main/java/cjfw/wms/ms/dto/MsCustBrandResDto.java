package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.07.10 
 * @description : 본점별 브랜드마스터 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.10 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "본점별 브랜드마스터 응답 DTO")
public class MsCustBrandResDto {
	@Schema(description = "데이터 번호", example = "")
	private String serialKey;
	
	@Schema(description = "체크여부", example = "")
	private String checkYn;
	
	@Schema(description = "본점코드", example = "")
	private String custKey;
	
	@Schema(description = "본점명", example = "")
	private String custName;
	
	@Schema(description = "거래처 유형", example = "")
	private String custType;
	
	@Schema(description = "브랜드 코드", example = "")
	private String brandCode;
	
	@Schema(description = "브랜드 이름", example = "")
	private String brandName;
	
	@Schema(description = "FC브랜드코드", example = "")
	private String reference01;
	
	@Schema(description = "FC브랜드명", example = "")
	private String reference02;
	
    @Schema(description = "등록자")
    private String addWho;
    
    @Schema(description = "등록일시")
    private String addDate;

    @Schema(description = "수정자")
    private String editWho;
    
    @Schema(description = "수정일시")
    private String editDate;
}
