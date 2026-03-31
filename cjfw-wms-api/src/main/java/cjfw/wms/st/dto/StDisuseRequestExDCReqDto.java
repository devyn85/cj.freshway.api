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
 * @description : 외부비축재고폐기처리 request dto
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
@Schema(description = "외부비축재고폐기처리 request dto")
public class StDisuseRequestExDCReqDto extends CommonProcedureDto {

	/**조회조건*/
	/** 물류센터 */
	@Schema(description = "물류센터")
	private String fixdccode;

	/** 창고 */
	@Schema(description = "창고")
	private String organize;

	/** 재고위치 */
	@Schema(description = "재고위치")
	private String stocktype;

	/** 재고속성 */
	@Schema(description = "재고속성")
	private String stockgrade;

	/** 상품코드 */
	@Schema(description = "상품코드")
	private String sku;

	/** 이력번호 */
	@Schema(description = "이력번호")
	private String serialno;

	/** B/L번호 */
	@Schema(description = "B/L번호")
	private String convserialno;

	/** serialnoyn */
	@Schema(description = "serialnoyn")
	private String serialnoyn;

	/** 조정일자유형 */
	@Schema(description = "조정일자유형")
	private String searchDate;

	/** 기준일FROM */
	@Schema(description = "기준일FROM")
	private String basedtFrom;

	/** 기준일TO */
	@Schema(description = "기준일TO")
	private String basedtTo;

	/** 유형 */
	@Schema(description = "유형")
	private String approvaltype;

	/** 진행상태 */
	@Schema(description = "진행상태")
	private String apprstatus;

	/** 조정/조정요청일자TO */
	@Schema(description = "조정/조정요청일자TO")
	private String toApprreqdtSlipdt;

	/** 조정/조정요청일자FROM */
	@Schema(description = "조정/조정요청일자FROM")
	private String fromApprreqdtSlipdt;

	/**요청 저장 조건*/
	/**결재요청일자*/
	@Schema(description = "결재요청일자")
	private String docdt;

	/**ifSendType*/
	@Schema(description = "ifSendType")
	private String ifSendType;

	/**workprocesscode*/
	@Schema(description = "workprocesscode")
	private String workprocesscode;

	/**omsFlg*/
	@Schema(description = "omsFlg")
	private String omsFlg;

	/**출고자*/
	@Schema(description = "출고자")
	private String wdCust;

	/**출고방법*/
	@Schema(description = "출고방법")
	private String wdMethod;

	/**출고증 비고*/
	@Schema(description = "출고증 비고")
	private String wdMemo;

	/**처리사유*/
	@Schema(description = "처리사유")
	private String reasonmsg;

	/** 폐기 요청 저장 */
	@Schema(description = "폐기 요청 저장")
	private List<StDisuseRequestExDCResDto> saveMasterList1;

	/** 폐기 처리 저장 */
	@Schema(description = "폐기 처리 저장")
	private List<StDisuseRequestExDCResDto> saveMasterList4;

	/** 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;

	/** contractcompany */
	@Schema(description = "contractcompany")
	private String contractcompany;

	/** lottable01yn */
	@Schema(description = "lottable01yn")
	private String lottable01yn;

	/** zone */
	@Schema(description = "zone")
	private String zone;

	/** storagetype */
	@Schema(description = "storagetype")
	private String storagetype;

	/**procedure*/
	@Schema(description = "procedure")
	private String procedure;

	private String stocktranstype;

	/** 결재작성일자 */
	@Schema(description = "결재작성일자")
	private String approvalreqdt;

	/** 결재작성번호 */
	@Schema(description = "결재작성번호")
	private String approvalreqno;

	/** 문서번호 */
	@Schema(description = "문서번호")
	private String docno;

	/** 등록일자 */
	@Schema(description = "등록일자")
	private String adddate;
}
