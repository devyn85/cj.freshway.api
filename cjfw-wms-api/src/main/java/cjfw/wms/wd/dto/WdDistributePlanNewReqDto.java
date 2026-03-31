package cjfw.wms.wd.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.11.07 
 * @description : 미출예정확인(New) - 미출예정 요청 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.07 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "미출예정확인(New) - 미출예정 요청")
public class WdDistributePlanNewReqDto extends CommonDto {
    @Schema(description = "수급센터")
    private String poDccode[];

    @Schema(description = "출고센터")
    private String soDccode[];

    @Schema(description = "기간 FROM(YYYYMMDD)")
    private String slipdtFrom;

    @Schema(description = "기간 TO(YYYYMMDD)")
    private String slipdtTo;

    @Schema(description = "입고예정 여부")
    private String chkyn;

    @Schema(description = "이동중재고 여부")
    private String stochkyn;

    @Schema(description = "사유코드")
    private String reason;

    @Schema(description = "상품유형-1")
    private List<String> skutype;

    @Schema(description = "외식전용구분")
    private String reference15;

    @Schema(description = "물류센터 선택")
    private String quick;
    
    @Schema(description = "상품코드 멀티")
    private String[] multiSku;
    
    @Schema(description = "관리처코드")
    private String custkey;
    
    @MultiSearch
    @Schema(description = "관리처코드 멀티")
    private List<String> custkeyMulti;
    
    @Schema(description = "수급담당")
    private List<String> buyerkey;
    
    @Schema(description = "주문번호")
    private String docnoWd;
    
    @MultiSearch
    @Schema(description = "주문번호 멀티")
    private List<String> docnoWdMulti;
}
