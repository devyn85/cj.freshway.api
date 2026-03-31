package cjfw.wms.ib.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.29 
 * @description : 보관료마감처리  req 기능을 구현한 DTO Class
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.29 ParkJinWoo 생성
 */
@Schema(description = "보관료마감처리  req DTO")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IbCloseStoragefeeReqDto extends CommonProcedureDto {
	
	  /** 저장리스트 */
    @Schema(description = "저장리스트")
	 List<IbCloseStoragefeeResDto> saveList;
	  /** 마감월(YYYYMM) */
    @Schema(description = "마감월(YYYYMM)")
    private String closeMonth;
   
    @Schema(description = "마감월(YYYYMM)")
    private String closedt;
    
    /** 창고코드 */
    @Schema(description = "창고코드")
    private String organize;

    /** 마감여부(Y/N) */
    @Schema(description = "마감여부(Y/N)")
    private String closeYn;
    
    /** 재고월 */
    @Schema(description = "재고월")
    private String yyyymm;

    /** 보관장소(ORGANIZE 뒤 4자리) */
    @Schema(description = "보관장소(ORGANIZE 뒤 4자리)")
    private String storageLocation;
    
    /** 보관장소(ORGANIZE 뒤 4자리) */
    @Schema(description = "보관장소(ORGANIZE 뒤 4자리)")
    private String plant;
    
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String dcCode;
    
    private String stockMonth;
    
    private String accountsYn;
}
