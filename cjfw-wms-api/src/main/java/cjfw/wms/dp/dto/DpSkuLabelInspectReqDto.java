package cjfw.wms.dp.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : YangChangHwan (iamai@cj.net)
 * @date : 2025.06.24
 * @description : 입고라벨출력(검수) Request DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.24 YangChangHwan (iamai@cj.net) 생성
 *         </pre>
 */
@Schema(description = "입고라벨출력(검수) Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpSkuLabelInspectReqDto extends CommonDto {

	/** 입고일자(From) */
	@Schema(description = "입고일자(From)")
	private String slipdtFrom;

	/** 입고일자(To) */
	@Schema(description = "입고일자(To)")
	private String slipdtTo;

	/** 전표번호(다중) */
	@Schema(description = "전표번호(다중)")
	private String docno;

	/** 상품코드(다중) */
	@Schema(description = "상품코드(다중)")
	private String sku;

	/** 조정조건 */
	@Schema(description = "조정조건")
	private String storagetype;

	/** 문서일자(From) */
	@Schema(description = "문서일자(From)")
	private String docdtFrom;

	/** 문서일자(To) */
	@Schema(description = "문서일자(To)")
	private String docdtTo;

	/** 주문유형 */
	@Schema(description = "주문유형")
	private String ordertype;

    /** 물류센터코드/명 */
    @Schema(description = "물류센터코드/명")
    private String dccode;

    /** 회사 id */
    @Schema(description = "회사 id")
    private String storerkey;

    /** 협력사코드 */
    @Schema(description = "협력사코드")
    private String fromCustkey;

    /** 전표번호 */
    @Schema(description = "전표번호")
    private String slipno;

    /** 전표일자 */
    @Schema(description = "전표일자")
    private String slipdt;

}
