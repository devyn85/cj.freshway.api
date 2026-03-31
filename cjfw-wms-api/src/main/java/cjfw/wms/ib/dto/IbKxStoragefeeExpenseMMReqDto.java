package cjfw.wms.ib.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.12.29
 * @description : 비용기표(1000센터) REQ DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.29         ParkYoSep (dytpq362@cj.net)      생성
 */
@Schema(description = "비용기표(1000센터) REQ DTO")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IbKxStoragefeeExpenseMMReqDto extends CommonProcedureDto {
	/** 저장리스트 */
    List<IbKxStoragefeeExpenseMMResDto> saveList;	
    
    @Schema(description = "대상 연월 (YYYYMM)")
    private String closeDate;
    
    @Schema(description = "세금계산서 번호")
    private String issueId;
    
    @Schema(description = "시리얼 키")
    private BigDecimal serialKey;
    

}
