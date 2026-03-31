package cjfw.wms.st.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.08.25
 * @description : PLT-ID변경 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "PLT-ID변경 Request DTO")
@Data
public class StConvertIdReqDto extends CommonProcedureDto {
    /** 저장 리스트 */
    List<StConvertIdResDto> saveList;	
    
    /** 센터코드 */
    @Schema(description = "센터코드")
    private String fixdccode;

    /** 화주코드 */
    @Schema(description = "화주코드")
    private String fixstorerkey;

    /** 조직 */
    @Schema(description = "조직")
    private String fixorganize;

    /** 구역 */
    @Schema(description = "구역")
    private String fixarea;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;
    
    /** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;    

    /** 상품그룹 */
    @Schema(description = "상품그룹")
    private String skugroup;

    /** ZONE from */
    @Schema(description = "ZONE from")
    private String fromzone;

    /** ZONE to */
    @Schema(description = "ZONE to")
    private String tozone;

    /** 위치 from */
    @Schema(description = "위치 from")
    private String fromloc;

    /** 위치 to */
    @Schema(description = "위치 to")
    private String toloc;

    /** 재고ID */
    @Schema(description = "재고ID")
    private String stockid;

    /** 시리얼번호 */
    @Schema(description = "시리얼번호")
    private String serialno;
    
    /** 사유코드 */
    @Schema(description = "사유코드")
    private String reasoncode;

    /** 사유메시지 */
    @Schema(description = "사유메시지")
    private String reasonmsg;
    
    /** 변환유형 */
    @Schema(description = "변환유형")
    private String converttype;
    
    /** 대상재고ID */
    @Schema(description = "대상재고ID")
    private String toStockid;
    
    
}
