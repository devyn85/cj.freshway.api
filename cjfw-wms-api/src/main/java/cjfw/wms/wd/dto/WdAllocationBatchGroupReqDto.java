package cjfw.wms.wd.dto;

import java.math.BigDecimal;
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
 * @date : 2025.07.08 
 * @description : 출고재고분배 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.08 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "출고재고분배 요청") 
public class WdAllocationBatchGroupReqDto extends CommonProcedureDto {
	/** 배치별분배 저장 리스트 */
	List<WdAllocationBatchGroupResTab1Dto> saveList;
	
	/** 거래처별분배 저장 리스트 */
	List<WdAllocationBatchGroupResTab1CustDto> saveDetail1List;
	
	/** 전표별분배 저장 리스트 */
	List<WdAllocationBatchGroupResTab1SlipDto> saveDetail2List;
	
	/** 상품별분배 저장 리스트 */
	List<WdAllocationBatchGroupResTab1SkuDto> saveDetail3List;
	
	/** 피킹존별분배 저장 리스트 */
	List<WdAllocationBatchGroupResTab1ZoneDto> saveDetail4List;
	
	/** 지정분배 저장 리스트 */
	List<WdAllocationBatchGroupResTab2DetailDto> saveTab2List;
	
	/** 차량별분배 저장 리스트 */
	List<WdAllocationBatchGroupResTab4Dto> saveDataCarnoList;
	
	
	/** 하단탭번호 */
	@Schema(description = "하단탭번호", nullable = false, example = "")
	private String key;
	
	/** 관리처코드 */
	@Schema(description = "관리처코드", nullable = false, example = "")
	private String toCustkey;
	
	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> toCustkeyMulti;
	
	/** 관리처유형 */
	@Schema(description = "관리처유형", nullable = false, example = "")
	private String toCusttype;
	
	/** status */
	@Schema(description = "status", nullable = false, example = "")
	private String status;
	
	/** 저장조건 */
	@Schema(description = "저장조건", nullable = false, example = "")
	private String storagetype;
	
	/** serialyn */
	@Schema(description = "serialyn", nullable = false, example = "")
	private String serialyn;
	
	/** 출고일자FROM */
	@Schema(description = "출고일자FROM", nullable = false, example = "")
	private String fromSlipdt;
	
	/** 출고일자TO */
	@Schema(description = "출고일자TO", nullable = false, example = "")
	private String toSlipdt;
	
	/** workprocesscode */
	@Schema(description = "workprocesscode", nullable = false, example = "")
	private String workprocesscode;
	
	/** ordertype */
	@Schema(description = "ordertype", nullable = false, example = "")
	private String ordertype;
	
	/** plant */
	@Schema(description = "plant", nullable = false, example = "")
	private String plant;
	
	/** distancetype */
	@Schema(description = "distancetype", nullable = false, example = "")
	private String distancetype;
	
	/** batchgroup */
	@Schema(description = "batchgroup", nullable = false, example = "")
	private String batchgroup;
	
	/** slipdt */
	@Schema(description = "slipdt", nullable = false, example = "")
	private String slipdt;
	
	/** fixdccode : 조회조건 */
	@Schema(description = "fixdccode", nullable = false, example = "")
	private String fixdccode;
	
	/** dccode */
	@Schema(description = "dccode", nullable = false, example = "")
	private String dccode;
	
	/** storerkey */
	@Schema(description = "storerkey", nullable = false, example = "")
	private String storerkey;
	
	/** allocfixtype */
	@Schema(description = "allocfixtype", nullable = false, example = "")
	private String allocfixtype;
	
	/** sku */
	@Schema(description = "sku", nullable = false, example = "")
	private String sku;
	
	/** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti; 
	
	/** organize */
	@Schema(description = "organize", nullable = false, example = "")
	private String organize;		
	
	/** toLoc */
	@Schema(description = "toLoc", nullable = false, example = "")
	private String toLoc;
	
	/** toStockid */
	@Schema(description = "toStockid", nullable = false, example = "")
	private String toStockid;
	
	/** custkey */
	@Schema(description = "custkey", nullable = false, example = "")
	private String custkey;
	
	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> custkeyMulti;
	
	
	/** slipno */
	@Schema(description = "slipno", nullable = false, example = "")
	private String slipno;
	
	/** slipline */
	@Schema(description = "slipline", nullable = false, example = "")
	private String slipline;
	
	/** docno */
	@Schema(description = "docno", nullable = false, example = "")
	private String docno;
	
	/** 주문번호(다중검색) */
	@MultiSearch
    @Schema(description = "주문번호-다중OR검색")
    private List<String> docnoMulti; 
	
	/** carno */
	@Schema(description = "carno", nullable = false, example = "")
	private String carno;
	
	/** popno */
	@Schema(description = "popno", nullable = false, example = "")
	private String popno;
	
	/** fromdate */
	@Schema(description = "fromdate", nullable = false, example = "")
	private String fromdate;
	
	/** serialkey */
	@Schema(description = "serialkey", nullable = false, example = "")
	private BigDecimal serialkey;
	
	/** 원거리유형 */
	@Schema(description = "원거리유형", nullable = false, example = "")
	private String mngDistancetype;
	

    /** 소비기한잔여율 */
    @Schema(description = "소비기한잔여율", nullable = false, example = "")
    private BigDecimal usebydateFreeRt;

    /** 비고 */
    @Schema(description = "비고", nullable = false, example = "")
    private String rmk1;
    

    /** 사용여부 */
    @Schema(description = "사용여부", nullable = false, example = "")
    private String useYn;
    
    /** STO 조회 구분 */
    @Schema(description = "STO 조회 구분", nullable = false, example = "ALLOCATON_BATCHGROUP")
    private String searchtype;
    
    /** 선마감여부 */
    @Schema(description = "선마감여부", nullable = false, example = "Y")
    private String dailyDeadlineYn;
    
    /** 피킹존 */
    @Schema(description = "피킹존", nullable = false, example = "")
    private String zone;
	
}
