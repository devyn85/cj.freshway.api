package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.10.20
 * @description : 운송비정산서 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.20    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Schema(description = "운송사 이메일 조회 결과")
public class TmTrxCalculationReportEmailResDto {

    /** 이메일 */
    @Schema(description = "이메일", nullable = true, example = "")
    private String email;
  
}
