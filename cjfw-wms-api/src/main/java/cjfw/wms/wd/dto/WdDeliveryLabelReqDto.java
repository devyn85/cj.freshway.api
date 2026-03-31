package cjfw.wms.wd.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.11.15 
 * @description : 이력배송라벨출력 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.15 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "배송라벨출력 요청") 
public class WdDeliveryLabelReqDto extends CommonProcedureDto {
	
	/** 인쇄 리스트(목록) */
	List<WdDeliveryLabelResTab1Dto> savePrintHeaderList;
	/** 인쇄 리스트(상세) */
	List<WdDeliveryLabelResTab1DetailDto> savePrintDetailList;

		
	/** 관리처코드 */
	@Schema(description = "관리처코드", nullable = false, example = "")
	private String toCustkey;
	
	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> toCustkeyMulti; 
	
	/** skugroup */
	@Schema(description = "skugroup", nullable = false, example = "")
	private String skugroup;
	
	/** sku */
	@Schema(description = "sku", nullable = false, example = "")
	private String sku;
	/** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;    	
	
	/** fixdccode */
	@Schema(description = "fixdccode", nullable = false, example = "")
	private String fixdccode;
	
	/** dccode */
	@Schema(description = "dccode", nullable = false, example = "")
	private String dccode;
	

	/** checkDccode */
	@Schema(description = "checkDccode", example = "2600")
	private String checkDccode;
	
    /** checkDccode(다중검색) */
    @MultiSearch
    @Schema(description = "checkDccode-다중검색")
    private List<String> checkDccodeMulti; 
    

	/* integrationLabelYn */
	@Schema(description = "integrationLabelYn")
	private String integrationLabelYn;


	/** 출고일자 */
	@Schema(description = "출고일자", nullable = false, example = "")
	private String slipdt;
	
	/** 대배치키 */
	@Schema(description = "대배치키", nullable = false, example = "")
	private String pickBatchNo;
	
	/** pickListNo */
	@Schema(description = "pickListNo", nullable = false, example = "")
	private String pickListNo;
	
	/**
     * 배송분류표회수리스트용pickListNo
     */
    @Schema(description = "배송분류표회수리스트용pickListNo", example = "PL001")
    private String pickListNos;
    
    /** 배송분류표회수리스트용pickListNo(다중검색) */
	@MultiSearch
    @Schema(description = "배송분류표회수리스트용pickListNo-다중OR검색")
    private List<String> pickListNosMulti;
	
	/** 피킹번호 */
	@Schema(description = "피킹번호", nullable = false, example = "")
	private String pickNo;
	
	/** 피킹방법 */
	@Schema(description = "피킹방법", nullable = false, example = "")
	private String tasksystem;
	
	/** zone */
	@Schema(description = "zone", nullable = false, example = "")
	private String zone;	

	/** zone(다중OR검색) */
	@MultiSearch
    @Schema(description = "zone-다중OR검색")
    private List<String> zoneMulti;    

	/** printmethod */
	@Schema(description = "printmethod", nullable = false, example = "")
	private String printmethod;

	/** ordertype */
	@Schema(description = "ordertype", nullable = false, example = "")
	private String ordertype;
	
	/** C/D타입 */
	@Schema(description = "C/D타입", nullable = false, example = "")
	private String crossdocktype;
	
	/** printorder */
	@Schema(description = "printorder", nullable = false, example = "")
	private String printorder;
		
	/** printpickinglist */
	@Schema(description = "printpickinglist", nullable = false, example = "")
	private String printpickinglist;
		
	/** crossDc */
	@Schema(description = "crossDc", nullable = false, example = "")
	private String crossDc;
	
	/** location */
	@Schema(description = "location", nullable = false, example = "")
	private String location;
	
	/** choice */
	@Schema(description = "choice", nullable = false, example = "")
	private String choice;
	
	/** storagetype */
	@Schema(description = "storagetype", nullable = false, example = "")
	private String storagetype;
	
	/**
     * 배송분류표회수리스트용storagetype
     */
    @Schema(description = "배송분류표회수리스트용storagetype", example = "ST01")
    private String storagetypes;
    
    /** 배송분류표회수리스트용storagetype(다중검색) */
	@MultiSearch
    @Schema(description = "배송분류표회수리스트용storagetype-다중OR검색")
    private List<String> storagetypesMulti;
	
	/** invoiceno */
	@Schema(description = "invoiceno", nullable = false, example = "")
	private String invoiceno;
	
	/** usePgm */
	@Schema(description = "usePgm", nullable = false, example = "")
	private String usePgm;
	
	/** distancetype */
	@Schema(description = "distancetype", nullable = false, example = "")
	private String distancetype; 
	
	/** distancetype(다중OR검색) */
	@MultiSearch
    @Schema(description = "distancetype-다중OR검색")
    private List<String> distancetypeMulti;    
	


    /**
     * prdOrd1
     */
    @Schema(description = "prdOrd1", example = "")
    private String prdOrd1;
    
    /**
     * prdOrd2
     */
    @Schema(description = "prdOrd2", example = "")
    private String prdOrd2;
    
    /**
     * prdOrd3
     */
    @Schema(description = "prdOrd3", example = "")
    private String prdOrd3;
    
    /**
     * prdOrd4
     */
    @Schema(description = "prdOrd4", example = "")
    private String prdOrd4;
    
    /**
     * prdOrd5
     */
    @Schema(description = "prdOrd5", example = "")
    private String prdOrd5;
    
    /**
     * prdOrd6
     */
    @Schema(description = "prdOrd6", example = "")
    private String prdOrd6;
    
    /**
     * prdOrd7
     */
    @Schema(description = "prdOrd7", example = "")
    private String prdOrd7;
    
    /**
     * prdOrd8
     */
    @Schema(description = "prdOrd8", example = "")
    private String prdOrd8;
		
}
