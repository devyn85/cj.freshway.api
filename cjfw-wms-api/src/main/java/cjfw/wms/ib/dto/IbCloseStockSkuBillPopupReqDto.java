package cjfw.wms.ib.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.08.21
 * @description : 마감상태관리 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.21 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "마감상태 관리 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IbCloseStockSkuBillPopupReqDto extends CommonDto {
	

    /** 마감월(YYYYMM) */
    @Schema(description = "마감월(YYYYMM)")
    private String zmonth;

    /** 저장위치 */
    @Schema(description = "저장위치")
    private String lgort;
    /** 타입 */
    @Schema(description = "타입")
    private String RetType;
    
 
    
    
    
}
