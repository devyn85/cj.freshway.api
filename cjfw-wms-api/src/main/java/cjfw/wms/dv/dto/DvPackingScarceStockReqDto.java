package cjfw.wms.dv.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
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
 * @date : 2025.06.10
 * @description : 부족분리스트 Request DTO
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.10 YangChangHwan (iamai@cj.net) 생성
 *         </pre>
 */
@Schema(description = "부족분리스트 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DvPackingScarceStockReqDto extends CommonDto {

	/** 판매처코드 */
	@Schema(description = "판매처코드")
	private String toVatno;

	/** 출고일자 */
	@Schema(description = "출고일자", nullable = false)
	private String slipdt;

	/** 상품번호 */
	@Schema(description = "상품번호")
	private String sku;

	/** 저장조건 */
	@Schema(description = "저장조건")
	private String storagetype;

	/** 현재고 */
	@Schema(description = "현재고", example = "1 or 0")
	private String stockyn;

	/** 가용재고 */
	@Schema(description = "가용재고", example = "1 or 0")
	private String openyn;

	/** 분배수량 */
	@Schema(description = "분배수량", example = "1 or 0")
	private String allocatedyn;

	/** 피킹재고수량 */
	@Schema(description = "피킹재고", example = "1 or 0")
	private String pickedyn;

	/** 실가용재고 */
	@Schema(description = "실가용재고", example = "1 or 0")
	private String realityyn;
	
	/** fixdccode */
	@Schema(description = "fixdccode", nullable = false, example = "")
	private String fixdccode;

    /** 창고-다중선택 */
	@Schema(description = "창고-다중선택", nullable = false, example = "4900,8774")
	private String organize;
	
    /** 창고(다중검색) */
	@MultiSearch
    @Schema(description = "창고-다중OR검색")
    private List<String> organizeMulti; 

}
