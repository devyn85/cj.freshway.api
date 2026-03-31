package cjfw.wms.ib.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.29 
 * @description : 보관료마감처리 현황 req 기능을 구현한 DTO Class
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.29 ParkJinWoo 생성
 */
@Schema(description = "보관료마감처리 현황 req DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IbCloseStoragefeeStatusReqDto extends CommonDto {
	

    /** 재고월 */
    @Schema(description = "재고월")
    private String stockMonth;

    /** 창고코드 */
    @Schema(description = "창고코드")
    private String organize;

    /** 정산여부 */
    @Schema(description = "정산여부")
    private String accountsYn;

}
