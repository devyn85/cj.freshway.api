package cjfw.wms.wd.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 고혜미
 * @date : 2025.10.15
 * @description : 매각출고처리 목록 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.15 고혜미 생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "매각출고처리 목록 요청") 
public class WdShipmentETCReqDto extends CommonProcedureDto {
	
	/** 기타출고목록 저장 리스트 */
	List<WdShipmentETCResTab1Dto> saveList;
	
	/** 매각내역 저장 리스트 */
	List<WdShipmentETCResTab3Dto> saveList3;
	
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String fixdccode;
    
    /** 창고 */
    @Schema(description = "창고")
    private String organize;      
    
    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;    
    
    /** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;
	
    /** 로케이션from */
    @Schema(description = "로케이션from")
    private String fromLocName;
    
    /** 로케이션to */
    @Schema(description = "로케이션to")
    private String toLocName;
    
    /** 로케이션from */
    @Schema(description = "로케이션from")
    private String fromloc;
    
    /** 로케이션to */
    @Schema(description = "로케이션to")
    private String toloc;
    
    /** 재고속성 */
    @Schema(description = "재고속성")
    private String stockgrade;

    /** 처리일자 */
    @Schema(description = "처리일자")
    private String docdt;
    
	/** 처리유형 */
	@Schema(description = "처리유형")
	private String potype;
	
	/** 처리사유 */
	@Schema(description = "처리사유")
	private String reasoncode;

	/** 세부사유 */
	@Schema(description = "세부사유")
	private String reasonmsg;

	/** 물류귀책배부 */
	@Schema(description = "물류귀책배부")
	private String other05;

	/** 귀속부서 */
	@Schema(description = "귀속부서")
	private String costcd;

	/** 거래처 */
	@Schema(description = "거래처")
	private String custkey;
	
	/** 매각등록일 */
	@Schema(description = "매각등록일")
	private String disposeDate;
	
	/** 매각등록일[from] */
	@Schema(description = "매각등록일[from]")
	private String fromDisposeDate;
	
	/** 매각등록일[to] */
	@Schema(description = "매각등록일[to]")
	private String toDisposeDate;
	
	/** 매각등록일[to] */
	@Schema(description = "매각등록일[to]")
	private String wdDate;
	
	
	
}
