package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.08.29 
 * @description : 권역 그룹 POP 마스터 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@NoArgsConstructor
@Schema(description = "권역 그룹 POP 마스터 응답 DTO")
public class MsDistrictGroupPopResDto {

	@Schema(description = "테이블 번호")
	private String serialkey;

	@Schema(description = "센터 코드")
	private String dccode;
	
	@Schema(description = "pop번호")
	private String popNo;
	
	@Schema(description = "pop명")
    private String popName;
	
	@Schema(description = "유효 시작일")
    private String fromDate;
	
	@Schema(description = "유효 종료일")
    private String toDate;
	
	@Schema(description = "비고")
    private String description;
	
	@Schema(description = "삭제여부")
    private String delYn;
	
	@Schema(description = "기본 pop Y/N")
	private String baseYn;

	@Schema(description = "중복유형")
	private String dupType;

}
