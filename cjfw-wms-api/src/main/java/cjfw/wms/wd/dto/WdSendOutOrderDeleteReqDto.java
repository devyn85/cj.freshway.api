package cjfw.wms.wd.dto;

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
 * @date : 2025.06.16 
 * @description : 수기출고 삭제 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.16    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "수기출고 삭제 요청") 
public class WdSendOutOrderDeleteReqDto extends CommonProcedureDto {
    /** 저장 리스트 */
    List<WdSendOutOrderDeleteReqDto> deleteList;
    
    /** 물류센터 */
	@Schema(description = "물류센터", nullable = false, example = "")
	private String dccode;
	
	/** 문서번호 */
    @Schema(description = "문서번호", nullable = false, example = "")
    private String docno;

    /** 문서라인 */
    @Schema(description = "문서라인", nullable = false, example = "")
    private String docline;
    
    /** 문서유형 */
    @Schema(description = "문서유형", nullable = false, example = "")
    private String doctype;
    
    /** 거래처 */
    @Schema(description = "거래처", nullable = false, example = "")
    private String custkey;
}
