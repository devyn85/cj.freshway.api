package cjfw.wms.ms.dto;

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
 * @date : 2025.08.23 
 * @description : 외부창고정산 시뮬레이션 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.23    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor            
@AllArgsConstructor
@Schema(description = "외부창고정산 시뮬레이션 요청 DTO")
public class MsExDCSimulationReqDto extends CommonProcedureDto {
    
    /** 저장리스트 */
    @Schema(description = "저장리스트", nullable = false, example = "")
    List<MsExDCSimulationResDto> saveList;
    
    /** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String dccode;
    
    /** 기준창고 */
    @Schema(description = "기준창고", nullable = false, example = "")
    private String baseOrganize;
    
    /** 비교창고 */
    @Schema(description = "비교창고", nullable = false, example = "")
    private String cfOrganize;
    
    /** 기준월 */
    @Schema(description = "기준월", nullable = false, example = "")
    private String stockmonth;
    
    /** 프로세스타입 */
    @Schema(description = "PROCESSTYPE")
    private String processtype;

}
