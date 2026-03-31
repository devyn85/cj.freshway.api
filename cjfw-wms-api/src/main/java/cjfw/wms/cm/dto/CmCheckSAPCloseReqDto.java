package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.07.02 
 * @description : SAP 마감 체크 요청 DTO
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.02    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Schema(description = "SAP 마감상태 조회 요청")
public class CmCheckSAPCloseReqDto extends CommonDto{
	/** 문서번호 */
	@Schema(description = "문서번호", example = "")
	private String docno;
	
	/** 문서라인 */
	@Schema(description = "문서라인", example = "")
	private String docline;
	
	/** 적용일자 */
    @Schema(description = "적용일자", example = "")
    private String deliverydate;

}