package cjfw.wms.om.entity;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KimSunHo(sunhokim6229@cj.net)
 * @date : 2026.03.10 
 * @description : 당일광역보충발주(FO) Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.03.10    KimSunHo(sunhokim6229@cj.net)   생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class OmOrderCreationSTOOrdBaseFOEntity extends CommonDto {

	/** 고객사코드 */
	@Schema(description = "고객사코드", example = "")
	private String storerkey;
	
	/** 문서일자 */
	@Schema(description = "문서일자", example = "")
	private String docdt;
	
	/** 문서유형 */
	@Schema(description = "문서유형", example = "")
	private String doctype;
	
	/** 문서번호 */
	@Schema(description = "문서번호", example = "")
	private String docno;
	
	/** 문서라인 */
	@Schema(description = "문서라인", example = "")
	private String docline;
	
	/** 센터코드 */
	@Schema(description = "센터코드", example = "")
	private String dccode;
	
	/** 배송일자 */
	@Schema(description = "배송일자", example = "")
	private String deliverydate;
	
	/** STO 문서일자 */
	@Schema(description = "STO 문서일자", example = "")
	private String stoDocdt;
	
	/** STO 문서유형 */
	@Schema(description = "STO 문서유형", example = "")
	private String stoDoctype;
	
	/** STO 문서번호 */
	@Schema(description = "STO 문서번호", example = "")
	private String stoDocno;
	
	/** STO 문서라인 */
	@Schema(description = "STO 문서라인", example = "")
	private String stoDocline;
	
	/** STO 센터코드 */
	@Schema(description = "STO 센터코드", example = "")
	private String stoDccode;
	
	/** STO 배송일자 */
	@Schema(description = "STO 배송일자", example = "")
	private String stoDeliverydate;
	
	/** 기타정보 1 */
	@Schema(description = "기타정보 1", example = "")
	private String sku;
	
	/** 기타정보 2 */
	@Schema(description = "기타정보 2", example = "")
	private BigDecimal orderqty;
	
	/** 기타정보 3 */
	@Schema(description = "기타정보 3", example = "")
	private BigDecimal stoOrderqty;
	
	/** 기타정보 4 */
    @Schema(description = "기타정보 4", example = "")
    private String other04;
    
    /** 기타정보 5 */
    @Schema(description = "기타정보 5", example = "")
    private String other05;
    
    /** 기타정보 6 */
    @Schema(description = "기타정보 6", example = "")
    private String other06;
    
    /** 기타정보 7 */
    @Schema(description = "기타정보 7", example = "")
    private String other07;
    
    /** 기타정보 8 */
    @Schema(description = "기타정보 8", example = "")
    private String other08;
	
	/** 삭제여부 */
	@Schema(description = "삭제여부", example = "")
	private String delYn;
	
	/** 성공 여부 */
    @Schema(description = "성공 여부", example = "")
    private Integer success;

    /** 에러 코드 */
    @Schema(description = "에러 코드", example = "")
    private Integer err;

    /** 에러 메시지 */
    @Schema(description = "에러 메시지", example = "")
    private String errmsg;
}