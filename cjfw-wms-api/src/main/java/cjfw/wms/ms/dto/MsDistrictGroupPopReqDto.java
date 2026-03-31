package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.08.26 
 * @description : 권역 그룹 마스터 POP 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.26 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "권역 그룹 마스터 POP 요청 DTO")
public class MsDistrictGroupPopReqDto extends CommonDto {

	@Schema(description = "테이블 번호")
	private String serialkey;

	@Schema(description = "참조 테이블 번호")
	private String refSerialkey;

	@Schema(description = "센터 코드")
	private String dccode;
	
	@Schema(description = "pop번호")
	private String popNo;
	
	@Schema(description = "조회 keyword( POP번호 + POP명 )")
	private String searchKeyword;
	
	@Schema(description = "pop명")
    private String popName;
	
	@Schema(description = "적용일자")
	private String effectiveDate;
	
	@Schema(description = "유효시작일자")
	private String fromDate;
	
	@Schema(description = "유효종료일자")
	private String toDate;
	
	@Schema(description = "삭제여부")
    private String delYn;
	
	@Schema(description = "비고")
    private String description;
	
	@Schema(description = "기본 pop Y/N")
	private String baseYn;
	
	@Schema(description = "기본 pop Y/N", hidden=true)
	private String[] gMultiDccodeList;

	@Schema(description = "다중 선택", example = "")
	private String multiSelect;

}
