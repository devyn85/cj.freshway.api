package cjfw.wms.rt.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.10.13
 * @description : 협력사반품지시 Request DTO Class
 * @issues :
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.13 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "협력사반품지시 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RtReturnOutReqDto extends CommonProcedureDto {
    /** docdt */
    @Schema(description = "docdt")
    private String docdt;

    /** serialyn */
    @Schema(description = "serialyn")
    private String serialyn;

    /** stocktype */
    @Schema(description = "stocktype")
    private String stocktype;

    /** stockgrade */
    @Schema(description = "stockgrade")
    private String stockgrade;

    /** fromloc */
    @Schema(description = "fromloc")
    private String fromloc;

    /** toloc */
    @Schema(description = "toloc")
    private String toloc;

    /** blno */
    @Schema(description = "blno")
    private String blno;

    /** serialno */
    @Schema(description = "serialno")
    private String serialno;

    /** wdCustkey */
    @Schema(description = "wdCustkey")
    private String wdCustkey;

    /** sku */
    @Schema(description = "sku")
    private String sku;

    /** 상품(다중OR검색) */
    @MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;

    /** fromCustkey */
    @Schema(description = "fromCustkey")
    private String fromCustkey;

    /** 관리처코드(다중OR검색) */
    @MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> fromCustkeyMulti;

    @Schema(description = "출력리스트")
    private List<RtReturnOutResDto> saveList;
}
