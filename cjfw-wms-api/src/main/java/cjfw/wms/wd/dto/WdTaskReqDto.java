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
 * @date : 2025.08.29 
 * @description : 피킹작업지시 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.29 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "피킹작업지시 요청") 
public class WdTaskReqDto extends CommonProcedureDto {
	/** 피킹지시 저장 리스트 */
	List<WdTaskResTab1Dto> savePickingBatchList;
	/** 수동피킹 저장 리스트 */
	List<WdTaskResTab2Dto> saveManualPickingBatchList;
	/** 모바일피킹지시 저장 리스트 */
	List<WdTaskResTab2Dto> saveMobilePickingBatchList;
	/** 피킹생성취소 리스트 */
	List<WdTaskResTab2Dto> savePickingBatchDeleteList;
	/** 피킹분리 저장 리스트 */
	List<WdTaskResTab2DetailDto> saveDivisionTaskList;
	/** 피킹병합 저장 리스트 */
	List<WdTaskResTab2Dto> saveMergeTaskList;
	/** 피킹작업자 삭제 리스트 */
	List<WdTaskResTab3Dto> deletePickerList;
	
	
	/**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "C100")
    private String fixdccode;
    
	/** taskdt */
	@Schema(description = "taskdt", nullable = false, example = "")
	private String taskdt;
	
	/** 관리처코드 */
	@Schema(description = "관리처코드", nullable = false, example = "")
	private String toCustkey;
	
	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> toCustkeyMulti; 

	/** tasksystem */
	@Schema(description = "tasksystem", nullable = false, example = "")
	private String tasksystem;

	/** deliverygroup */
	@Schema(description = "deliverygroup", nullable = false, example = "")
	private String deliverygroup;
	
	/** 저장조건 */
	@Schema(description = "저장조건", nullable = false, example = "")
	private String storagetype;
	
	/** printOrder */
	@Schema(description = "printOrder", nullable = false, example = "")
	private String printOrder;
	
	/** orderbyPick */
	@Schema(description = "orderbyPick", nullable = false, example = "")
	private String orderbyPick;
	
	/** printpickinglist */
	@Schema(description = "printpickinglist", nullable = false, example = "")
	private String printpickinglist;
	
	/** printtype */
	@Schema(description = "printtype", nullable = false, example = "")
	private String printtype;
	
	/** crossYn */
	@Schema(description = "crossYn", nullable = false, example = "")
	private String crossYn;
	
	/** printmemo */
	@Schema(description = "printmemo", nullable = false, example = "")
	private String printmemo;
	
	
	/** plant */
	@Schema(description = "plant", nullable = false, example = "")
	private String plant;
	
	/** distancetype */
	@Schema(description = "distancetype", nullable = false, example = "")
	private String distancetype;
	
	/** createkey */
	@Schema(description = "createkey", nullable = false, example = "")
	private String createkey;
	
	/** docdt */
	@Schema(description = "docdt", nullable = false, example = "")
	private String docdt;
	
	/** pickBatchNo */
	@Schema(description = "pickBatchNo", nullable = false, example = "")
	private String pickBatchNo;
	
	/** pickNo */
	@Schema(description = "pickNo", nullable = false, example = "")
	private String pickNo;
	
	/** pickListNo */
	@Schema(description = "pickListNo", nullable = false, example = "")
	private String pickListNo;
	
	/** minPickBatchNo */
	@Schema(description = "minPickBatchNo", nullable = false, example = "")
	private String minPickBatchNo;
	
	/** minPickNo */
	@Schema(description = "minPickNo", nullable = false, example = "")
	private String minPickNo;
	
	/** minPickListNo */
	@Schema(description = "minPickListNo", nullable = false, example = "")
	private String minPickListNo;
	
	
	
	/** createtype */
	@Schema(description = "createtype", nullable = false, example = "")
	private String createtype;
	
	
	/** dccode */
	@Schema(description = "dccode", nullable = false, example = "")
	private String dccode;
	
	/** storerkey */
	@Schema(description = "storerkey", nullable = false, example = "")
	private String storerkey;
	
	/** sku */
	@Schema(description = "sku", nullable = false, example = "")
	private String sku;
	
	/** organize */
	@Schema(description = "organize", nullable = false, example = "")
	private String organize;		
	
	/** docno */
	@Schema(description = "docno", nullable = false, example = "")
	private String docno;
	
	/** wavekey */
	@Schema(description = "wavekey", nullable = false, example = "")
	private String wavekey;
	
	/** carno */
	@Schema(description = "carno", nullable = false, example = "")
	private String carno;
	/** printcnt */
	@Schema(description = "printcnt", nullable = false, example = "")
	private String printcnt;
	/** barcode */
	@Schema(description = "barcode", nullable = false, example = "")
	private String barcode;
	/** mobileFlag */
	@Schema(description = "mobileFlag", nullable = false, example = "")
	private String mobileFlag;
	/** 작업유형 */
	@Schema(description = "작업유형", nullable = false, example = "")
	private String tasktype;
	

	/** 멀티인쇄 pickListNo */
	@Schema(description = "pickListNos", nullable = false, example = "")
	private String pickListNos;	

	/** 멀티인쇄 pickListNo(다중OR검색) */
	@MultiSearch
    @Schema(description = "멀티인쇄 pickListNo-다중OR검색")
    private List<List<String>> pickListNosMulti; 
		
}
