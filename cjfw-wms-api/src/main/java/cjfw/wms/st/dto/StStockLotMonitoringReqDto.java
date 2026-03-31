package cjfw.wms.st.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.10.14 
 * @description : 유통기한 점검 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.14 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class StStockLotMonitoringReqDto extends CommonDto {
	
	/** 물류센터코드 (고정) */
    @Schema(description = "물류센터코드 (고정)", example = "")
    private String fixdccode;
            
    /** 창고코드  */
    @Schema(description = "창고코드", example = "")
    private String organize;
    
    /** 창고코드 (다중 선택) */
    @MultiSearch
    @Schema(description = "창고코드 (다중 선택)", example = "")
    private List<String> organizeMulti;
    
    /** 상품코드 (다중 선택) */
    @Schema(description = "상품코드 (다중 선택)", example = "")
    private String sku;
    
    /** 상품코드 (다중 선택) */
    @MultiSearch
    @Schema(description = "상품코드 (다중 선택)", example = "")
    private List<List<String>> skuMulti;

    /** 재고유형 */
    @Schema(description = "재고유형", example = "")
    private String stocktype;

    /** 재고등급 */
    @Schema(description = "재고등급", example = "")
    private String stockgrade;

    /** 로케이션 (From) */
    @Schema(description = "로케이션 (From)", example = "")
    private String fromloc;

    /** 로케이션 (To) */
    @Schema(description = "로케이션 (To)", example = "")
    private String toloc;

    /** 존 (Zone) (다중 선택, 콤마 구분자) */
    @Schema(description = "존 (Zone) (다중 선택, 콤마 구분자)", example = "")
    private String zone;
        
    /** 시리얼번호 */
    @Schema(description = "시리얼번호", example = "")
    private String serialno;

    /** 로케이션 카테고리 */
    @Schema(description = "로케이션 카테고리", example = "")
    private String loccategory;

    /** 로트속성01 (유통기한/제조일자) */
    @Schema(description = "로트속성01 (유통기한/제조일자)", example = "")
    private String lottable01;

    /** 로트속성01 사용 여부 (Y: STD 제외, N: STD만) */
    @Schema(description = "로트속성01 사용 여부 (Y/N)", example = "")
    private String lottable01yn;

    /** 재고수량 0 제외 여부 (1: 0 제외) */
    @Schema(description = "재고수량 0 제외 여부 (1: 0 제외)", example = "")
    private String zeroQtyYn;

    /** 정렬 기준 (LOC/DATE) */
    @Schema(description = "정렬 기준 (LOC/DATE)", example = "")
    private String sortkey;

    /** 보관유형 */
    @Schema(description = "보관유형", example = "")
    private String storagetype;
    
    /** 소진가능여부 */
    @Schema(description = "소진가능여부", example = "")
    private String exhaustionchk;
    
    /** 수급담당자 */
    @Schema(description = "수급담당자", example = "")
    private String buyerkey;
    
    /** 소비기한 임박여부 */
    @Schema(description = "소비기한 임박여부", example = "")
    private String neardurationyn;
}
