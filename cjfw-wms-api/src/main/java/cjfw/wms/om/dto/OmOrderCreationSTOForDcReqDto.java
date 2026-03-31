package cjfw.wms.om.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.09.17
 * @description : 저장품센터간이체 조회 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.17 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "저장품센터간이체 조회 요청")
public class OmOrderCreationSTOForDcReqDto extends CommonProcedureDto {
    /** 저장 리스트 */
    @Schema(description = "저장 리스트")
    private java.util.List<OmOrderCreationSTOForDcResDto> saveList;
    
    /** 공급받는 센터 (DC_B) */
    @Schema(description = "공급받는 센터")
    private String DC_B;

    /** 공급 센터 (DC_A) */
    @Schema(description = "공급 센터")
    private String DC_A;

    /** 직렬관리 제외 여부 (1이면 제외) */
    @Schema(description = "직렬관리 제외 여부(1이면 제외)")
    private Integer SERIALYN;

    /** 보관유형 */
    @Schema(description = "보관유형")
    private String STORAGETYPE;

    /** 다중 상품코드 목록 */
    @Schema(description = "다중 상품코드 목록")
    private String MULTI_SKU;

    /** 이체일자(납품일자) */
    @Schema(description = "이체일자")
    private String DELIVERYDATE;
    
    @Schema(description = "")
    private String fromDccode;
    
    private String dcA;
}
