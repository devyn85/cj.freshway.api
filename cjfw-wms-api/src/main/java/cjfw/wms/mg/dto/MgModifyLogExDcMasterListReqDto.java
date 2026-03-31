package cjfw.wms.mg.dto;


import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.11 
 * @description :외부비축재고변경사유현황 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.11 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "기둥/빈 공간 정보 조회 조건 DTO")
public class MgModifyLogExDcMasterListReqDto extends CommonDto {

	/**조회 시작일자 (MODIFYDT FROM)*/
    @Schema(description = "조회 시작일자 (MODIFYDT FROM)")
    private String modifyDateFrom;

    /**조회 종료일자 (MODIFYDT TO)*/
    @Schema(description = "조회 종료일자 (MODIFYDT TO)")
    private String modifyDateTo;

    /**수정유형*/
    @Schema(description = "수정유형")
    private String modifyType;

    /**사유코드*/
    @Schema(description = "사유코드 LIKE 검색")
    private String reasonCode;

    /**로케이션*/
    @Schema(description = "LOC LIKE 검색")
    private String loc;

    /**B/L번호*/
    @Schema(description = "B/L번호")
    private String blNo;
    
    /* ───── 추가 검색 항목 ───── */
    /**이력번호*/
    @Schema(description = "이력번호")
    private String serialyn;
    
    /**상품코드*/
    @Schema(description = "상품코드")
    private String sku;
    
    /**창고코드*/
    @Schema(description = "창고코드")
    private String organize;
    /**창고코드*/
    @Schema(description = "창고코드")
    private String area;
    
    /**물류센터*/
    @Schema(description = "물류센터")
    private String fixDcCode;
    
}
