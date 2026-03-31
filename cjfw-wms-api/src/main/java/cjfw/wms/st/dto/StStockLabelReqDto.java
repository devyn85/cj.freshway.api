package cjfw.wms.st.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.07.31
 * @description : 재고라벨출력 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.31 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고라벨출력 Request DTO")
@Data
public class StStockLabelReqDto extends CommonDto {

    /** 센터코드(고정) */
    @Schema(description = "센터코드(고정)")
    private String fixdccode;

    /** 조직 */
    @Schema(description = "조직")
    private String organize;

    /** 재고유형 */
    @Schema(description = "재고유형")
    private String stocktype;

    /** 등급 */
    @Schema(description = "등급")
    private String stockgrade;

    /** 로케이션 from */
    @Schema(description = "로케이션 from")
    private String fromloc;

    /** 로케이션 to */
    @Schema(description = "로케이션 to")
    private String toloc;

    /** 존 */
    @Schema(description = "존")
    private String zone;

    /** 보관유형 */
    @Schema(description = "보관유형")
    private String storagetype;

    /** 상품코드(다중) */
    @Schema(description = "상품코드(다중)")
    private String sku;
    
    /** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;    

    /** LOT 구분 */
    @Schema(description = "LOT 구분")
    private String lottable01yn;

    /** 시리얼번호 */
    @Schema(description = "시리얼번호")
    private String serialno;

    /** 재고ID(다중) */
    @Schema(description = "재고ID(다중)")
    private String stockid;
}
