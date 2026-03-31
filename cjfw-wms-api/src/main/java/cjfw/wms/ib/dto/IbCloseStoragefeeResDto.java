package cjfw.wms.ib.dto;

import java.math.BigDecimal;

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
@Schema(description = "보관료마감처리  res DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IbCloseStoragefeeResDto extends CommonProcedureDto {

	
	  /** 카운트 */
    @Schema(description = "카운트")
    private int cnt;
    /** 데이터번호 */
    @Schema(description = "데이터번호")
    private BigDecimal serialKey;

    /** 재고월 */
    @Schema(description = "재고월")
    private String yyyymm;

    /** 보관장소(ORGANIZE 뒤 4자리) */
    @Schema(description = "보관장소(ORGANIZE 뒤 4자리)")
    private String storageLocation;

    /** 보관장소명 */
    @Schema(description = "보관장소명")
    private String storageLocName;

    /** 마감여부 */
    @Schema(description = "마감여부")
    private String closeYn;

    /** 전송건수 */
    @Schema(description = "전송건수")
    private Integer sendCnt;

    /** 수정자 */
    @Schema(description = "수정자")
    private String editWho;

    /** 수정자명 */
    @Schema(description = "수정자명")
    private String editWhoName;

    /** 수정일시 */
    @Schema(description = "수정일시")
    private String editDate;

    /** 버튼활성플래그 */
    @Schema(description = "버튼활성플래그")
    private String btnFlag;
    
    /** 버튼활성플래그 */
    @Schema(description = "버튼활성플래그")
    private String plant;
    
    /** 버튼활성플래그 */
    @Schema(description = "버튼활성플래그")
    private String closedt;
    
    /** 버튼활성플래그 */
    @Schema(description = "버튼활성플래그")
    private String organize;
    
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String dcCode;
}
