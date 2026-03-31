package cjfw.wms.kp.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.14 
 * @description : 외부창고재고모니터링 detial res DTO 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.14 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부창고재고모니터링 detial res DTO")
public class KpEXstorageMonitoringDetailTcsSubListResDto {

	/** MAPKEY_NO */
    @Schema(description = "MAPKEY_NO")
    private String mapkeyNo;

    /** SOKEY */
    @Schema(description = "SOKEY")
    private String soKey;

    /** DOCLINE */
    @Schema(description = "DOCLINE")
    private String docLine;

    /** SKU */
    @Schema(description = "SKU")
    private String sku;

    /** SKUNAME */
    @Schema(description = "SKUNAME")
    private String skuName;

    /** STORAGETYPENAME */
    @Schema(description = "STORAGETYPENAME")
    private String storageTypeName;

    /** ORG */
    @Schema(description = "ORG")
    private String org;

    /** ORGANIZE */
    @Schema(description = "ORGANIZE")
    private String organize;

    /** ORGANIZENAME */
    @Schema(description = "ORGANIZENAME")
    private String organizeName;

}
