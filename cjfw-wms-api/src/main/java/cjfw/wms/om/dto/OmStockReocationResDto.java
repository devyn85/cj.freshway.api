package cjfw.wms.om.dto;

import java.util.List;

import com.opencsv.bean.CsvBindByName;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.07.24 
 * @description : 저장품자동발주관리 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class OmStockReocationResDto {
			
    /** 요청번호 */
    @Schema(description = "요청번호", example = "202511210930")
    private String reqNo;

    /** 협력사코드 */
    @CsvBindByName(column = "협력사코드")
    @Schema(description = "협력사코드", example = "")
    private String custkey;
    
    /** 협력사명 */
    @CsvBindByName(column = "협력사명")
    @Schema(description = "협력사명", example = "")
    private String custname;

    /** 상품코드 */
    @CsvBindByName(column = "상품코드")
    @Schema(description = "상품코드", example = "")
    private String sku;
    
    /** 상품명 */
    @CsvBindByName(column = "상품명")
    @Schema(description = "상품명", example = "")
    private String skuname;
    
    /** 저장조건 */
    @CsvBindByName(column = "저장조건")
    @Schema(description = "저장조건", example = "")
    private String storagetype;
    
    /** 출고센터코드 */
    @CsvBindByName(column = "출고센터")
    @Schema(description = "출고센터코드", example = "")
    private String wdDccode;
    
    /** 분석전센터코드 */
    @CsvBindByName(column = "AS-IS 배치센터")
    @Schema(description = "분석전센터코드", example = "")
    private String befDccode;
    
    /** 분석후센터코드 */
    @CsvBindByName(column = "TO-BE 배치센터")
    @Schema(description = "분석후센터코드", example = "")
    private String aftDccode;
    
    /** 변경내용 */
    @CsvBindByName(column = "변경내용")
    @Schema(description = "변경내용", example = "")
    private String editCntt;
    
    /** 최적화제외사유 */
    @CsvBindByName(column = "최적화제외이유")
    @Schema(description = "최적화제외사유", example = "")
    private String optExceptReason;
    
    /** 분석전재고수량 */
    @CsvBindByName(column = "AS-IS 재고수량")
    @Schema(description = "분석전재고수량", example = "")
    private Integer befQty;
    
    /** 분석후재고수량 */
    @CsvBindByName(column = "TO-BE 재고수량")
    @Schema(description = "분석후재고수량", example = "")
    private Integer aftQty;
    
    /** 분석전PLT수량 */
    @CsvBindByName(column = "AS-IS PLT수량")
    @Schema(description = "분석전PLT수량", example = "")
    private Integer befPltQty;
    
    /** 분석후PLT수량 */
    @CsvBindByName(column = "TO-BE PLT수량")
    @Schema(description = "분석후PLT수량", example = "")
    private Integer aftPltQty;
    
    /** 출고중량최근30일합계 */
    @CsvBindByName(column = "출고중량(KG)_최근30일합계")
    @Schema(description = "출고중량최근30일합계", example = "")
    private Integer wdWgt30Sum;
    
    /** 분석전이체수량 */
    @CsvBindByName(column = "AS-IS 이체량")
    @Schema(description = "분석전이체수량", example = "")
    private Integer befMoveQty;

    /** 분석후이체수량 */
    @CsvBindByName(column = "TO-BE 이체량")
    @Schema(description = "분석후이체수량", example = "")
    private Integer aftMoveQty;
    
    /** TO-BE (재배치 이후) 원화 개수 */
	@Schema(description = "TO-BE (재배치 이후) 원화 개수", example = "")
	private String toBeConversionCount;

	/** AS-IS (재배치 이전) 원화 개수 */
	@Schema(description = "AS-IS (재배치 이전) 원화 개수", example = "")
	private String asIsConversionCount;

	/** 해당 변화를 겪은 SKU 개수 */
	@Schema(description = "해당 변화를 겪은 SKU 개수", example = "")
	private String countOfSkus;
    
	/** 수정자 */
	@Schema(description = "수정자", example = "")
	private String addwho;

	/** 최초등록시간 */
	@Schema(description = "최초등록시간", example = "")
	private String adddate;

	/** 수정자 */
	@Schema(description = "수정자", example = "")
	private String editwho;

	/** 최종변경시간 */
	@Schema(description = "최종변경시간", example = "")
	private String editdate;	
	
	/** plt capa*/
	@Schema(description = "plt capa", example = "")
	private List<OmStockReocationPltResDto> pltList;
	
	/** capa*/
	@Schema(description = "capa", example = "")
	private List<OmStockReocationCapaResDto> capaList;
	
	/** weight*/
	@Schema(description = "weight", example = "")
	private List<OmStockReocationWeightResDto> weightList;
			
}