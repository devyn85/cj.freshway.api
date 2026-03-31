package cjfw.wms.st.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.10.10
 * @description : 재고조정 요청/처리 response dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.10 JiHoPark  생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "재고조정 요청/처리 request dto")
public class StAdjustmentRequestReqDto extends CommonProcedureDto {
	/** 물류센터 */
	@Schema(description = "물류센터")
	private String fixdccode;

	/** 창고 */
	@Schema(description = "창고")
	private String organize;

	/**상품코드*/
	@Schema(description = "상품코드")
	private String sku;

	/**저장조건*/
	@Schema(description = "저장조건")
	private String storagetype;

	/**피킹존*/
	@Schema(description = "피킹존")
	private String zone;

	/**재고위치*/
	@Schema(description = "재고위치")
	private String stocktype;

	/**재고속성*/
	@Schema(description = "재고속성")
	private String stockgrade;

	/**이력번호*/
	@Schema(description = "이력번호")
	private String serialno;

	/**B/L번호*/
	@Schema(description = "B/L번호")
	private String convserialno;

	/**FROM 로케이션*/
	@Schema(description = "FROM 로케이션")
	private String fromloc;

	/**TO 로케이션*/
	@Schema(description = "TO 로케이션")
	private String toloc;

	/**serialnoyn*/
	@Schema(description = "serialnoyn")
	private String serialnoyn;

	/**procedure*/
	@Schema(description = "procedure")
	private String procedure;

	/**ifSendType*/
	@Schema(description = "ifSendType")
	private String ifSendType;

	/**workprocesscode*/
	@Schema(description = "workprocesscode")
	private String workprocesscode;

	/**omsFlag*/
	@Schema(description = "omsFlag")
	private String omsFlag;

	/**stocktranstype*/
	@Schema(description = "stocktranstype")
	private String stocktranstype;

	/**계약업체*/
	@Schema(description = "계약업체")
	private String contractcompany;

	/** 조정일자 */
	@Schema(description = "조정일자")
	private String docdt;

	/**유통기한여부*/
	@Schema(description = "유통기한여부")
	private String lottable01yn;

	/**물류귀책배부*/
	@Schema(description = "물류귀책배부")
	private String processmain;

	/**발생사유*/
	@Schema(description = "발생사유")
	private String reasoncode;

	/**처리수량*/
	@Schema(description = "처리수량")
	private BigDecimal tranqty;

	/**진행상태*/
	@Schema(description = "진행상태")
	private String apprstatus;

	/**조정요청일자 from*/
	@Schema(description = "조정요청일자 from")
	private String fromApprreqdt;

	/**조정요청일자 to*/
	@Schema(description = "조정요청일자 to")
	private String toApprreqdt;

	/**조정일자 from*/
	@Schema(description = "조정일자 from")
	private String fromSlipdt;

	/**조정일자 to*/
	@Schema(description = "조정일자 to")
	private String toSlipdt;

	/**기준일 from*/
	@Schema(description = "기준일 from")
	private String basedtFrom;

	/**기준일 to*/
	@Schema(description = "기준일 to")
	private String basedtTo;

	/**유형*/
	@Schema(description = "유형")
	private String approvaltype;

	/**재고조정 요청 저장*/
	@Schema(description = "재고조정 요청 저장")
	private List<StAdjustmentRequestResDto> saveMasterList1;

	/**재고조정 처리 저장*/
	@Schema(description = "재고조정 결재 삭제")
	private List<StAdjustmentRequestElectApprovalDto> deleteMasterList3;

	/**재고조정 처리 저장*/
	@Schema(description = "재고조정 결재 전자결재")
	private List<StAdjustmentRequestElectApprovalDto> saveElectApproval;

	/**재고조정 처리 저장*/
	@Schema(description = "재고조정 처리 저장")
	private List<StAdjustmentRequestResDto> saveMasterList4;

	/**재고조정 처리 삭제*/
	@Schema(description = "재고조정 처리 삭제")
	private List<StAdjustmentRequestResDto> deleteMasterList4;



	/**귀책*/
	@Schema(description = "귀책")
	private String imputetype;

	/**귀속부서*/
	@Schema(description = "귀속부서")
	private String costcd;

	/**귀속부서명*/
	@Schema(description = "귀속부서명")
	private String costcdname;

	/**거래처*/
	@Schema(description = "거래처")
	private String custkey;

	/**처래처명*/
	@Schema(description = "처래처명")
	private String custname;

	/** 단가/금액표시 여부  */
	@Schema(description = "단가/금액표시 여부 ")
	private String viewPriceYn = "N";


}
