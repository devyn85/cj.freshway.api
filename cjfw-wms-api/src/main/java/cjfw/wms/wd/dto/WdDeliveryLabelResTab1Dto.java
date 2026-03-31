package cjfw.wms.wd.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.11.15 
 * @description : 배송라벨출력-분류표출력 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.15 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "배송라벨출력-분류표출력 목록 결과")
public class WdDeliveryLabelResTab1Dto extends CommonDto{
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/**
     * slipdt
     */
    @Schema(description = "slipdt", example = "2024-01-01")
    private String slipdt;
    /**
     * dccode
     */
    @Schema(description = "dccode", example = "DC01")
    private String dccode;
    /**
     * organize
     */
    @Schema(description = "organize", example = "ORG01")
    private String organize;
    /**
     * tasksystem
     */
    @Schema(description = "tasksystem", example = "WMS")
    private String tasksystem;
    /**
     * plant
     */
    @Schema(description = "plant", example = "P01")
    private String plant;
    /**
     * plantdesc
     */
    @Schema(description = "plantdesc", example = "플랜트설명")
    private String plantdesc;
    /**
     * storagetype
     */
    @Schema(description = "storagetype", example = "ST01")
    private String storagetype;
    
    /**
     * storagetypedesc
     */
    @Schema(description = "storagetypedesc", example = "보관유형설명")
    private String storagetypedesc;
    /**
     * distancetype
     */
    @Schema(description = "distancetype", example = "DT01")
    private String distancetype;
    /**
     * pickBatchNo
     */
    @Schema(description = "pickBatchNo", example = "PB001")
    private String pickBatchNo;
    /**
     * pickListNo
     */
    @Schema(description = "pickListNo", example = "PL001")
    private String pickListNo;
    
    /**
     * pickNo
     */
    @Schema(description = "pickNo", example = "P001")
    private String pickNo;
    /**
     * custcnt
     */
    @Schema(description = "custcnt", example = "10")
    private BigDecimal custcnt;
    /**
     * skucnt
     */
    @Schema(description = "skucnt", example = "50")
    private BigDecimal skucnt;
    /**
     * docnocnt
     */
    @Schema(description = "docnocnt", example = "20")
    private BigDecimal docnocnt;
    /**
     * invoicecnt
     */
    @Schema(description = "invoicecnt", example = "100")
    private BigDecimal invoicecnt;
    /**
     * preprintcnt
     */
    @Schema(description = "preprintcnt", example = "5")
    private BigDecimal preprintcnt;
    /**
     * modifycnt
     */
    @Schema(description = "modifycnt", example = "2")
    private BigDecimal modifycnt;
    /**
     * delcnt
     */
    @Schema(description = "delcnt", example = "1")
    private BigDecimal delcnt;
    
}
