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
 * @date : 2025.11.12
 * @description : 출차지시처리 request dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.12 JiHoPark  생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "출차지시처리 request dto")
public class WdLoadReqDto extends CommonProcedureDto {
	/** 센터코드 */
	@Schema(description = "센터코드")
	private String dccode;

	/** 출고일자 - from */
	@Schema(description = "출고일자 - from")
	private String fromSlipdt;

	/** 출고일자 - to */
	@Schema(description = "출고일자 - to")
	private String toSlipdt;

	/** 저장유무 */
	@Schema(description = "저장유무")
	private String channel;

	/** 관리처 */
	@Schema(description = "관리처")
	private String toCustkey;

	/** 상품 */
	@Schema(description = "상품")
	private String sku;

	/** 창고 */
	@Schema(description = "창고")
	private String organize;

	/** 협력사 */
	@Schema(description = "협력사")
	private String vendor;

	/** POP번호 */
	@Schema(description = "POP번호")
	private String deliverygroup;

	/** 검수진행상태 */
	@Schema(description = "검수진행상태")
	private String status;

	/** 검수완료여부 */
	@Schema(description = "검수완료여부")
	private String inspectedyn;

	/** 차량번호 */
	@Schema(description = "차량번호")
	private String carno;

	/** 출력기준 */
	@Schema(description = "출력기준")
	private String searchtype;

	/** 출력유형 */
	@Schema(description = "출력유형")
	private String searchgubun;



	/** fromDocdt */
	@Schema(description = "fromDocdt")
	private String fromDocdt;

	/** toDocdt */
	@Schema(description = "toDocdt")
	private String toDocdt;

	/** 고객사주문유형 */
	@Schema(description = "고객사주문유형")
	private String ordertype;

	/** 문서번호 */
	@Schema(description = "문서번호")
	private String docno;

	/** 고객사코드 */
	@Schema(description = "고객사코드")
	private String storerkey;

	/** 출고일자 */
	@Schema(description = "출고일자")
	private String slipdt;

	/** 배송일자 */
	@Schema(description = "배송일자")
	private String deliverydt;

	/** 회차 */
	@Schema(description = "회차")
	private String priority;



	/** 문서유형 */
	@Schema(description = "문서유형")
	private String doctype;

	/** channelnm */
	@Schema(description = "channelnm")
	private String channelnm;

	/** 상차지시서 조회 목록*/
	@Schema(description = "상차지시서 조회 목록")
	private List<WdLoadResDto> printMasterList;
}
