package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.08.26 
 * @description : 센터별 행정동 권역 저장 요청 DTO 
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
@EqualsAndHashCode(callSuper = true)
@Schema(description = "센터 권역 행정동 요청 DTO")
public class MsCenterDistrictHjdongReqDto extends CommonDto {
	
	@Schema(description = "테이블 키")
	private String serialkey;
	
	@Schema(description = "센터 코드")
	private String dccode;
	
	@Schema(description = "행정동명")
	private String hjdongNm;
	
	@Schema(description = "행정동 코드")
	private String hjdongCd;
	
	@Schema(description = "유효 시작일")
	private String fromDate;
	
	@Schema(description = "유효 종료일")
	private String toDate;
	
	@Schema(description = "삭제여부")
	private String delYn;

	@Schema(description = "비고")
	private String rmk;

	@Hidden
	private int rowCount;

	@Hidden
	private String editDate;
	@Hidden
	private String editWho;

}
