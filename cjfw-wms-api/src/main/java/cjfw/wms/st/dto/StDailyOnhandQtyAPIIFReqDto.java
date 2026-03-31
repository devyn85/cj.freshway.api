package cjfw.wms.st.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.09.04 
 * @description : 외부창고 API 재고현황 인터페이스 저장 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.04    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부창고 API 재고현황 인터페이스 저장 요청") 
public class StDailyOnhandQtyAPIIFReqDto  {	
    
    List<StDailyOnhandQtyAPIIFDetailReqDto> data;
    
    /** 시스템구분코드 */
    @Schema(description = "시스템구분코드", nullable = true, example = "")
    private String system_type_cd;
    
    /** 업무요청구분 */
    @Schema(description = "업무요청구분", nullable = true, example = "")
    private String opcode;
    
    /** 요청자 아이디 */
    @Schema(description = "요청자 아이디", nullable = true, example = "")
    private String req_user;
    
    /** 데이터 건수 */
    @Schema(description = "데이터 건수", nullable = true, example = "")
    private Integer req_count;

}
