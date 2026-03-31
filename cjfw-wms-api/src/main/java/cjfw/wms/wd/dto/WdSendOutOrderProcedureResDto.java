package cjfw.wms.wd.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.06.23 
 * @description : 외부비축출고지시 감모 삭제 파라미터 조회 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                 MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.23    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부비축출고지시 감모 삭제 파라미터 조회 결과") 
public class WdSendOutOrderProcedureResDto extends CommonProcedureDto {
    /** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String dccode;

    /** 고객사 */
    @Schema(description = "고객사", nullable = false, example = "")
    private String storerkey;

    /** 창고 */
    @Schema(description = "창고", nullable = false, example = "")
    private String organize;

    /** area */
    @Schema(description = "area", nullable = false, example = "")
    private String area;

    /** docdt */
    @Schema(description = "docdt", nullable = false, example = "")
    private String docdt;

    /** docno */
    @Schema(description = "docno", nullable = false, example = "")
    private String docno;

    /** docline */
    @Schema(description = "docline", nullable = false, example = "")
    private String docline;

    /** slipdt */
    @Schema(description = "slipdt", nullable = false, example = "")
    private String slipdt;

    /** slipno */
    @Schema(description = "slipno", nullable = false, example = "")
    private String slipno;

    /** slipline */
    @Schema(description = "slipline", nullable = false, example = "")
    private String slipline;

    /** ordertype */
    @Schema(description = "ordertype", nullable = false, example = "")
    private String ordertype;

    /** sliptype */
    @Schema(description = "sliptype", nullable = false, example = "")
    private String sliptype;

    /** 상품코드 */
    @Schema(description = "상품코드", nullable = false, example = "")
    private String sku;

    /** 단위 */
    @Schema(description = "단위", nullable = false, example = "")
    private String uom;

    /** tranqty */
    @Schema(description = "tranqty", nullable = false, example = "")
    private String tranqty;

    /** stockid */
    @Schema(description = "stockid", nullable = false, example = "")
    private String stockid;

    /** stockgrade */
    @Schema(description = "stockgrade", nullable = false, example = "")
    private String stockgrade;

    /** 거래처명 */
    @Schema(description = "loc", nullable = false, example = "")
    private String loc;

    /** 상품코드 */
    @Schema(description = "lot", nullable = false, example = "")
    private String lot;

    /** iotype*/
    @Schema(description = "iotype", nullable = false, example = "")
    private String iotype;
    
}
