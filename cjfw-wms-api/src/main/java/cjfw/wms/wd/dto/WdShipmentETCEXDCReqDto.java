package cjfw.wms.wd.dto;

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
 * @date : 2025.09.11
 * @description : 외부비축재고조정 request dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.11 JiHoPark  생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부센터매각출고처리 request dto")
public class WdShipmentETCEXDCReqDto extends CommonProcedureDto {
	/** 물류센터 */
	@Schema(description = "물류센터")
	private String fixdccode;

	/** 창고 */
	@Schema(description = "창고")
	private String organize;

	/** 상품코드 */
	@Schema(description = "상품코드")
	private String sku;

	/** 재고속성 */
	@Schema(description = "재고속성")
	private String stockgrade;

	/** 재고위치 */
	@Schema(description = "재고위치")
	private String stocktype;

	/** B/L번호 */
	@Schema(description = "B/L번호")
	private String convserialno;






	/** 이력번호 */
	@Schema(description = "이력번호")
	private String serialno;

	/** serialnoyn */
	@Schema(description = "serialnoyn")
	private String serialnoyn;

	/** contractcompany */
	@Schema(description = "contractcompany")
	private String contractcompany;

	/** lottable01yn */
	@Schema(description = "lottable01yn")
	private String lottable01yn;






	/** 기준일FROM */
	@Schema(description = "기준일FROM")
	private String basedtFrom;

	/** 기준일TO */
	@Schema(description = "기준일TO")
	private String basedtTo;

	/** 유형 */
	@Schema(description = "유형")
	private String approvaltype;






	/** 조정일자유형 */
	@Schema(description = "조정일자유형")
	private String searchDate;

	/** 조정/조정요청일자TO */
	@Schema(description = "조정/조정요청일자TO")
	private String toApprreqdtSlipdt;

	/** 조정/조정요청일자FROM */
	@Schema(description = "조정/조정요청일자FROM")
	private String fromApprreqdtSlipdt;

	/** 진행상태 */
	@Schema(description = "진행상태")
	private String apprstatus;

	/**procedure*/
	@Schema(description = "procedure")
	private String procedure;

	private String ifSendType;

	private String workprocesscode;

	private String omsFlg;

	private String docdt;

	private String stocktranstype;

	/**메모*/
	@Schema(description = "메모")
	private String wdMemo;

	/** 기타출고 요청 저장 */
	@Schema(description = "기타출고 요청 저장")
	private List<WdShipmentETCEXDCResDto> saveMasterList1;

	/** 기타출고 결재 삭제 */
	@Schema(description = "기타출고 결재 저장")
	private List<WdShipmentETCEXDCElectApprovalDto> saveMasterList3;

	/** 기타출고 결재 삭제 */
	@Schema(description = "기타출고 결재 삭제")
	private List<WdShipmentETCEXDCElectApprovalDto> deleteMasterList3;

	/** 기타출고 결재 전자결재 */
	@Schema(description = "기타출고 결재 전자결재")
	private List<WdShipmentETCEXDCElectApprovalDto> saveElectApproval;

	/** 기타출고 처리 저장 */
	@Schema(description = "기타출고 처리 저장")
	private List<WdShipmentETCEXDCResDto> saveMasterList4;

	/** 기타출고 처리 저장 */
	@Schema(description = "기타출고 처리 삭제")
	private List<WdShipmentETCEXDCResDto> deleteMasterList4;
}
