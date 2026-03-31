package cjfw.wms.st.dto;

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
 * @date : 2025.08.26
 * @description : 외부비축재고조정 request dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.26 JiHoPark  생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부비축재고조정 request dto")
public class StAdjustmentRequestExDCReqDto extends CommonProcedureDto {

	/** 물류센터 */
	@Schema(description = "물류센터")
	private String fixdccode;

	/** 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;

	/** 창고 */
	@Schema(description = "창고")
	private String organize;

	/** 상품코드 */
	@Schema(description = "상품코드")
	private String sku;

	/** 재고위치 */
	@Schema(description = "재고위치")
	private String stocktype;

	/** 재고속성 */
	@Schema(description = "재고속성")
	private String stockgrade;

	/** 이력번호 */
	@Schema(description = "이력번호")
	private String serialno;

	/** B/L번호 */
	@Schema(description = "B/L번호")
	private String convserialno;

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

	private String wdCust;

	private String wdMethod;

	/**출고증 비고*/
	@Schema(description = "출고증 비고")
	private String wdMemo;

	/**처리사유*/
	@Schema(description = "처리사유")
	private String reasonmsg;

	private String stocktranstype;

	/**박스조정 procedure*/
	@Schema(description = "박스조정 procedure")
	private String boxProcedure;

	/**박스조정 processtype*/
	@Schema(description = "박스조정 processtype")
	private String boxProcesstype;

	/**패키지가 수행할 커맨드*/
	@Schema(description = "패키지가 수행할 커맨드")
	private String boxCommand;

	/** 결재작성일자 */
	@Schema(description = "결재작성일자")
	private String approvalreqdt;

	/** 결재작성번호 */
	@Schema(description = "결재작성번호")
	private String approvalreqno;

	/** 문서번호 */
	@Schema(description = "문서번호")
	private String docno;

	/** 재고조정 요청 저장 */
	@Schema(description = "재고조정 요청 저장")
	private List<StAdjustmentRequestExDCResDto> saveMasterList1;

	/** 작업박스수량 저장 */
	@Schema(description = "작업박스수량 저장")
	private List<StAdjustmentRequestExDCResDto> saveBoxAdjustList;

	/** 재고조정 결재 삭제 */
	@Schema(description = "재고조정 결재 삭제")
	private List<StAdjustmentRequestExDCResDto> deleteMasterList3;

	/** 재고조정 결재 전자결재 */
	@Schema(description = "재고조정 결재 전자결재")
	private List<StAdjustmentRequestExDCElectApprovalDto> saveElectApproval;

	/** 재고조정 처리 저장 */
	@Schema(description = "재고조정 처리 저장")
	private List<StAdjustmentRequestExDCResDto> saveMasterList4;

	/** 재고조정 처리 저장 */
	@Schema(description = "재고조정 처리 삭제")
	private List<StAdjustmentRequestExDCResDto> deleteMasterList4;

}
