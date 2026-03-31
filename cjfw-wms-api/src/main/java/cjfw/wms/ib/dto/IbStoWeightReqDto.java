package cjfw.wms.ib.dto;

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
 * @date : 2025.10.24
 * @description : 센터별물동량 Request DTO Class
 * @issues :
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.24 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "센터별물동량 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IbStoWeightReqDto extends CommonProcedureDto {
    /**
     * yyyymm
     */
    @Schema(description = "yyyymm")
    private String yyyymm;
    /**
     * flowType
     */
    @Schema(description = "flowType")
    private String flowType;
    /**
     * yyyymm
     */
    @Schema(description = "yyyymm")
    private String toyyyymm;

    /**
     * fixdccode
     */
    @Schema(description = "fixdccode")
    private String fixdccode;

    /**
     * fixdccode
     */
    @MultiSearch
    @Schema(description = "fixdccode")
    private List<String> fixdccodeMulti;

    /**
     * filterkey
     */
    @Schema(description = "filterkey")
    private String filterkey;

    /**
     * masterkey
     */
    @Schema(description = "masterkey")
    private String masterkey;

    /**
     * delYn
     */
    @Schema(description = "delYn")
    private String delYn;

    /**
     * activeKey
     */
    @Schema(description = "activeKey")
    private String activeKey;

    /**
     * 출력리스트
     */
    @Schema(description = "출력리스트")
    private List<IbStoWeightResDto> saveList;

    /**
     * 출력리스트
     */
    @Schema(description = "출력리스트")
    private List<IbStoWeightResDto> deleteList;

    @Schema(description = "최종변경자", example = "김철수")
    private String editwho;

    @Schema(description = "체크타입", example = "cust")
    private String checkType;
}
