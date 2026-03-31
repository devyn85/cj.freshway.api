package cjfw.wms.om.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import cjfw.wms.om.entity.OmOrderCreationSTOOrdBaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.10.31 
 * @description : 당일광역보충발주 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.31 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class OmOrderCreationSTOOrdBaseReqDto extends CommonProcedureDto {
	
	/** 문서번호 */
	@Schema(description = "문서번호", example = "")
	private String docno;
	
	/** 재고량발주 */
	@Schema(description = "재고량발주", example = "")
	private String stordyn;
	
	/** 수급센터 */
	@Schema(description = "수급센터", example = "")
	private String dccode;
	
	/** 공급센터 */
	@Schema(description = "공급센터", example = "")
	private String dcA;
	
	/** 공급센터 */
	@Schema(description = "공급센터", example = "")
	private String dcB;
	
	/** 공급센터 */
	@Schema(description = "공급센터", example = "")
	private String dcC;
	
	/** 공급센터 */
	@Schema(description = "공급센터", example = "")
	private String dcD;
	
	/** 공급센터 */
	@Schema(description = "공급센터", example = "")
	private String dcE;
	
	/** 공급센터 */
	@Schema(description = "공급센터", example = "")
	private String dcF;
	
	/** 주문량강제발주 */
	@Schema(description = "주문량강제발주", example = "")
	private String onlyOrdqty;
	
	/** 이체일자 */
	@Schema(description = "이체일자", example = "")
	private String deliverydate;
	
	/** 축육상품제외 */
	@Schema(description = "축육상품제외", example = "")
	private String serialyn;
	
	/** 저장조건 */
	@Schema(description = "저장조건", example = "")
	private String storagetype;
	
	/** 고객마감유형 */
	@Schema(description = "고객마감유형", example = "")
	private String custorderclosetype;
	
	/** 마감유형 */
	@Schema(description = "마감유형", example = "")
	private String dailyDeadlineSto;
	
	/** 상품코드 */
	@Schema(description = "상품코드", example = "")
	private String sku;
	
	/** 상품코드 */
	@MultiSearch
	@Schema(description = "상품코드", example = "")
	private List<List<String>> skuMulti;
	
	/** 제외 상품코드 */
	@Schema(description = "제외 상품코드", example = "")
	private String skuExcept;
	
	/** 제외 상품코드 */
	@MultiSearch
	@Schema(description = "제외 상품코드", example = "")
	private List<List<String>> skuExceptMulti;
	
	/** 지정원거리유형(지정피킹유형) */
	@Schema(description = "지정원거리유형(지정피킹유형)", example = "")
	private String distancetype;
	
	/** 지정피킹유형 */
	@MultiSearch
	@Schema(description = "지정피킹유형", example = "")
	private List<String> distancetypeMulti;
	
	/** 제외원거리유형(제외피킹유형) */
	@Schema(description = "제외원거리유형(제외피킹유형)", example = "")
	private String setDistancetype;
	
	/** 제외피킹유형 */
	@MultiSearch
	@Schema(description = "제외피킹유형", example = "")
	private List<String> setDistancetypeMulti;
	
	/** 발주센터의 재고량 미선택시 가용재고량 */
	@Schema(description = "발주센터의 재고량 미선택시 가용재고량", example = "")
	private String stqtyyn;
	
	/** CROSS재고포함 */
	@Schema(description = "CROSS재고포함", example = "")
	private String crossyn;
	
	/** PO제외 */
	@Schema(description = "PO제외", example = "")
	private String poyn;
	
	/** STO제외 */
	@Schema(description = "STO제외", example = "")
	private String stoyn;
	
	/** KXSTO제외 */
	@Schema(description = "KXSTO제외", example = "")
	private String kxyn;
	
	/** 저장리스트 */
	@Schema(description = "저장리스트", example = "")
	private List<OmOrderCreationSTOOrdBaseEntity> saveList;
	
	/** FROM_고객사코드 */
    @Schema(description = "FROM_고객사코드")
    private String fromStorerkey;
    
    /** FROM_센터코드 */
	@Schema(description = "FROM_센터코드")
	private String fromDccode;
	
	/** FROM_조직코드 */
    @Schema(description = "FROM_조직코드")
    private String fromOrganize;
    
    /** FROM_창고코드 SAP 창고 혹은 별도의 창고 코드 */
    @Schema(description = "FROM_창고코드 SAP 창고 혹은 별도의 창고 코드")
    private String fromArea;
    
    /** FROM_상품코드 */
    @Schema(description = "FROM_상품코드")
    private String fromSku;
    
    /** FROM UOM */
    @Schema(description = "FROM UOM")
    private String fromUom;
    
    /** FROM_재고 등급( ABC ) */
    @Schema(description = "FROM_재고 등급( ABC)")
    private String fromStockgrade;
    
    /** TO 예정수량 */
    @Schema(description = "TO 예정수량")
    private BigDecimal toOrderqty;
    
    /** TO_고객사코드 */
    @Schema(description = "TO_고객사코드")
    private String toStorerkey;
    
    /** TO_창고코드 SAP 창고 혹은 별도의 창고 코드 */
    @Schema(description = "TO_창고코드 SAP 창고 혹은 별도의 창고 코드")
    private String toArea;
    
    /** TO_상품코드 */
    @Schema(description = "TO_상품코드")
    private String toSku;
    
    /** TO UOM */
    @Schema(description = "TO UOM")
    private String toUom;
    
    /** TO_재고 등급( ABC ) */
    @Schema(description = "TO_재고 등급( ABC)")
    private String toStockgrade;
    
    /** TO_센터코드 */
    @Schema(description = "TO_센터코드")
    private String toDccode;
    
    /** TO_조직코드 */
    @Schema(description = "TO_조직코드")
    private String toOrganize;
    
    /** stotype */
    @Schema(description = "stotype")
    private String stotype;
    
    /** stoStoragetype */
    @Schema(description = "stoStoragetype")
    private String stoStoragetype;


}
