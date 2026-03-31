package cjfw.wms.st.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.12.11
 * @description : 외부비축재고조정 entity
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.11 JiHoPark  생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부비축재고조정 Entity")
public class StAdjustmentRequestExDCEntity {

	/** 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;

	/** 결재작성일자 */
	@Schema(description = "결재작성일자")
	private String approvalreqdt;

	/** 결재작성번호 */
	@Schema(description = "결재작성번호")
	private String approvalreqno;

	/** 진행상태 */
	@Schema(description = "진행상태")
	private String apprstatus;

	/** docId */
	@Schema(description = "docId")
	private String docId;

	/** 결재자(반려자)ID */
	@Schema(description = "결재자(반려자)ID")
	private String approveId;

	/** 결재자(반려자)명 */
	@Schema(description = "결재자(반려자)명")
	private String approveNm;
}
