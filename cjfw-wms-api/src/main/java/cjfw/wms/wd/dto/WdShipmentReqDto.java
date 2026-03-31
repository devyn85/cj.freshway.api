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
 * @date : 2025.07.21 
 * @description : 출고확정처리 목록 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.21 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "출고확정처리 목록 요청") 
public class WdShipmentReqDto extends CommonProcedureDto {
	
	/** 메인그리드 출고확정 리스트 */
	List<WdShipmentResDto> saveConfirmList;
	/** 출고대상(tab1) 출고대상확정 리스트 */
	List<WdShipmentTab1DetailResDto> saveTab1List;
	/** 출고대상(tab2) 결품대상확정 리스트 */
	List<WdShipmentTab2DetailResDto> saveTab2List;
	/** 상차검수취소(tab3) 상차검수취소 리스트 */
	List<WdShipmentTab3DetailResDto> saveTab3List;
	
	

	/**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "C100")
    private String fixdccode;
    
	/** 출고일자FROM */
	@Schema(description = "출고일자FROM", nullable = false, example = "")
	private String fromSlipdt;
	
	/** 출고일자TO */
	@Schema(description = "출고일자TO", nullable = false, example = "")
	private String toSlipdt;
	
	/** status */
	@Schema(description = "status", nullable = false, example = "")
	private String status;
	
	/** 관리처코드 */
	@Schema(description = "관리처코드", nullable = false, example = "")
	private String toCustkey;
	
	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> toCustkeyMulti;
	
	/** carno */
	@Schema(description = "carno", nullable = false, example = "Y")
	private String carno;
	
	/** vendor */
	@Schema(description = "vendor", nullable = false, example = "")
	private String vendor;
	
	/** vendor(다중검색) */
	@MultiSearch
    @Schema(description = "vendor-다중OR검색")
    private List<String> vendorMulti; 
	
	/** 상품코드 */
	@Schema(description = "상품코드", nullable = false, example = "")
	private String sku;
	
	/** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti; 
	
	/** 통합channel배송 */
	@Schema(description = "channel", nullable = false, example = "")
	private String channel;
	
	/** organize */
	@Schema(description = "organize", nullable = false, example = "")
	private String organize;
	
	/** docno */
	@Schema(description = "docno", nullable = false, example = "")
	private String docno;
	
	/** docline */
	@Schema(description = "docline", nullable = false, example = "")
	private String docline;
	
	/** 주문번호(다중검색) */
	@MultiSearch
    @Schema(description = "주문번호-다중OR검색")
    private List<String> docnoMulti; 
	
	/** ordertype */
	@Schema(description = "ordertype", nullable = false, example = "")
	private String ordertype;
	
	/** inspectstatus */
	@Schema(description = "inspectstatus", nullable = false, example = "")
	private String inspectstatus;
	
	/** searchserial */
	@Schema(description = "searchserial", nullable = false, example = "")
	private String searchserial;
	
	/** serialno */
	@Schema(description = "serialno", nullable = false, example = "")
	private String serialno;
	
	/** blno */
	@Schema(description = "blno", nullable = false, example = "")
	private String blno;
	
	/** contractcompany */
	@Schema(description = "contractcompany", nullable = false, example = "")
	private String contractcompany;
	
	/** storerkey */
	@Schema(description = "storerkey", nullable = false, example = "")
	private String storerkey;
	
	/** dccode */
	@Schema(description = "dccode", nullable = false, example = "")
	private String dccode;
	
	/** deliverydt */
	@Schema(description = "deliverydt", nullable = false, example = "")
	private String deliverydt;
	
	/** slipdt */
	@Schema(description = "slipdt", nullable = false, example = "")
	private String slipdt;
	
	/** deliverygroup */
	@Schema(description = "deliverygroup", nullable = false, example = "")
	private String deliverygroup;
	
	/** priority */
	@Schema(description = "priority", nullable = false, example = "")
	private String priority;
	
	
	/** doctype */
	@Schema(description = "doctype", nullable = false, example = "")
	private String doctype;
	
}
