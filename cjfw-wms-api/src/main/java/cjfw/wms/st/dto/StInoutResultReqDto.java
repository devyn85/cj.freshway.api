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
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.07.31
 * @description : 수불현황 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.31 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "수불현황 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StInoutResultReqDto extends CommonDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String fixdccode;			

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;

    /** 화주코드 */
    @Schema(description = "화주코드")
    private String storerkey;

    /** 조직코드 */
    @Schema(description = "조직코드")
    private String organize;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;
    
    /** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;	    

    /** 재고유형 */
    @Schema(description = "재고유형")
    private String stocktype;

    /** 보관유형 */
    @Schema(description = "보관유형")
    private String storagetype;

    /** 조회시작일자 */
    @Schema(description = "조회시작일자")
    private String dt1;

    /** 조회종료일자 */
    @Schema(description = "조회종료일자")
    private String dt2;

    /** 권한-다중지역코드 */
    @Schema(description = "권한-다중지역코드")
    private String area;
    
	/** 3자물류 주문 유형  */
	@Schema(description = "3자물류 주문 유형 ")
	private String tplType;
	
	/** 위탁물류 제외  */
	@Schema(description = "위탁물류 제외")
	private String logisticsExcept;
	
	/** 통합배송 제외  */
	@Schema(description = "통합배송 제외")
	private String deliveryExcept;    
}
