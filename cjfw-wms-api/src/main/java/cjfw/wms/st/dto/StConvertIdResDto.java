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
 * @author : sss (kduimux@cj.net)
 * @date : 2025.08.25
 * @description : PLT-ID변경 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "PLT-ID변경 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StConvertIdResDto extends CommonProcedureDto {
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

    /** 조직 */
    @Schema(description = "조직")
    private String organize;

    /** 구역 */
    @Schema(description = "구역")
    private String area;

    /** 구역명 */
    @Schema(description = "구역명")
    private String areaname;

    /** 등급 */
    @Schema(description = "등급")
    private String fromStockgrade;

    /** 등급명 */
    @Schema(description = "등급명")
    private String stockgradename;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명")
    private String skuname;

    /** 상품그룹 */
    @Schema(description = "상품그룹")
    private String skugroup;

    /** 위치 */
    @Schema(description = "위치")
    private String fromLoc;

    /** 단위 */
    @Schema(description = "단위")
    private String uom;

    /** 수량 */
    @Schema(description = "수량")
    private String qty;

    /** 가용수량 */
    @Schema(description = "가용수량")
    private String openqty;

    /** 할당수량 */
    @Schema(description = "할당수량")
    private String qtyallocated;

    /** 피킹수량 */
    @Schema(description = "피킹수량")
    private String qtypicked;

    /** 이동수량 */
    @Schema(description = "이동수량")
    private String tranqty;

    /** LOT */
    @Schema(description = "LOT")
    private String fromLot;

    /** 제조일자 */
    @Schema(description = "제조일자")
    private String lottable01;

    /** 유통기한 */
    @Schema(description = "유통기한")
    private String durationTerm;

    /** 재고ID */
    @Schema(description = "재고ID")
    private String fromStockid;

    /** 재고유형 */
    @Schema(description = "재고유형")
    private String stocktype;

    /** 재고유형명 */
    @Schema(description = "재고유형명")
    private String stocktypedesc;

    /** 색상 */
    @Schema(description = "색상")
    private String colordescr;

    /** 유통기한일수 */
    @Schema(description = "유통기한일수")
    private String duration;

    /** 유통기한구분 */
    @Schema(description = "유통기한구분")
    private String durationtype;
    
    /** 유효기간여부 */
    @Schema(description = "유효기간여부")
    private String neardurationyn;    
    
    /** 제조일자 */
    @Schema(description = "제조일자")
    private String manufacturedt;    
    
    /** 소비일자 */
    @Schema(description = "소비일자")
    private String expiredt;    
    
    /** 소비기한잔여율 */
    @Schema(description = "소비기한잔여율")
    private BigDecimal usebydatefreert;
    
    /** 변환유형 */
    @Schema(description = "변환유형")
    private String converttype;    
    
    /** 대상재고ID */
    @Schema(description = "대상재고ID")
    private String toStockid;

}
