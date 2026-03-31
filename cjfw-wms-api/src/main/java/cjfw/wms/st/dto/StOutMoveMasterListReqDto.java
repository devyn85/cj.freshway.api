package cjfw.wms.st.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.29
 * @description : 외부비축센터간이동reqDto 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.29 ParkJinWoo 생성 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부비축센터간이동reqDto") 
public class StOutMoveMasterListReqDto extends CommonProcedureDto{
	
	List<StOutMoveMasterListResDto> saveList;
	
	/** 센터코드(FIX) */
    @Schema(description = "센터코드(FIX)")
    private String fixDcCode;



    /** 창고 다중선택 */
    @Schema(description = "창고 다중선택")
    private String organize;


    /** 재고타입 */
    @Schema(description = "재고타입")
    private String stockType;

    /** 재고속성 */
    @Schema(description = "재고속성")
    private String stockGrade;

    /** LOC From(>=) */
    @Schema(description = "LOC From(>=)")
    private String fromLoc;

    /** LOC To(<=) */
    @Schema(description = "LOC To(<=)")
    private String toLoc;

    /** 존(ZONE) */
    @Schema(description = "존(ZONE)")
    private String zone;

    /** 이력번호 */
    @Schema(description = "이력번호")
    private String serialNo;

    /** BL번호 다중값(콤마) */
    @Schema(description = "BL번호")
    private String blNo;
    
    /** BL 번호 */
    @MultiSearch
    @Schema(description = "BL 번호(다중 – 콤마/개행 구분)", nullable = false, example = "")
    private List<String> blNoMulti;
    
    /** 계약업체코드 */
    @Schema(description = "계약업체코드")
    private String contractCompany;

    /** SLIPDT(보관료 조회용) */
    @Schema(description = "SLIPDT(보관료 조회용)")
    private String slipDt;

    /** 보관유형(STORAGETYPE) */
    @Schema(description = "보관유형(STORAGETYPE)")
    private String storageType;

    /** SKU 다중선택 */
    @Schema(description = "SKU 다중선택")
    private String sku;

    /** LOTTABLE01 Y/N */
    @Schema(description = "LOTTABLE01 Y/N")
    private String lotTable01Yn;
    
    private String convertType;
    
    private String deliveryDate;
    
    private String uid;
    
    private String dcCode;
}
