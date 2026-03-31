package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : ParkYoSep 
 * @date : 2025.11.18  
 * @description : 위탁재고현황 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.18 parkYosep (dytpq362@cj.net) 생성
 *         </pre>
 */
@Schema(description = "위탁재고현황 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StTplOnhandQtyResDto extends CommonProcedureDto {
    /** 커스텀 엑스트라 체크박스 */
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn = "N";
	/** 화주 */
    @Schema(description = "화주", nullable = true, example = "")
    private String tplUser;
    /** 물류센터 */
    @Schema(description = "물류센터", nullable = true, example = "")
    private String dccode;
    
    /** 재고위치 */
    @Schema(description = "재고위치")
    private String stocktype;

    /** 재고위치명 */
    @Schema(description = "재고위치명")
    private String stocktypenm;

    /** 재고위치 */
    @Schema(description = "재고속성")
    private String stockgrade;

    /** 재고위치명 */
    @Schema(description = "재고속성명")
    private String stockgradedesc;

    
//    /** 보관일수 */
//    @Schema(description = "보관일수", nullable = true, example = "")
//    private Integer storagedaycnt;

    /** 상품코드 */
    @Schema(description = "상품코드", nullable = true, example = "")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명", nullable = true, example = "")
    private String skuNm;

    /** 수량 */
    @Schema(description = "수량", nullable = true, example = "")
    private BigDecimal qty;

    /** 단위 */
    @Schema(description = "단위", nullable = true, example = "")
    private String uom;

    /** 박스수량 */
    @Schema(description = "박스수량", nullable = true, example = "")
    private BigDecimal boxqty;
    
    /** 입고일자 */
    @Schema(description = "입고일자", nullable = true, example = "")
    private String deliverydate; 
    /** 선하증권번호 */
    @Schema(description = "선하증권번호", nullable = true, example = "")
    private String convserialno;

    /** 이력번호 */
    @Schema(description = "이력번호")
    private String serialNo;
    
    /** 유통기한(잔여/전체) */
    @Schema(description = "유통기한(잔여/전체)")
    private String durationTerm;
    
    /** 기준일자 */
    @Schema(description = "기준일자")
    private String duration;    
    
    /** 소비기한잔여율 */
    @Schema(description = "소비기한잔여율")
    private String durationRate;  
    
    /** 최초등록자 */
    @Schema(description = "최초등록자", nullable = true, example = "")
    private String addwho;
    
    /** 최초등록자 */
    @Schema(description = "최초등록자", nullable = true, example = "")
    private String addwhoNm;

    /** 최초등록시간 */
    @Schema(description = "최초등록시간", nullable = true, example = "")
    private String adddate;

    /** 최종변경자 */
    @Schema(description = "최종변경자", nullable = true, example = "")
    private String editwho;
    
    /** 최종변경자 */
    @Schema(description = "최종변경자", nullable = true, example = "")
    private String editwhoNm;

    /** 최종변경시간 */
    @Schema(description = "최종변경시간", nullable = true, example = "")
    private String editdate;
}
