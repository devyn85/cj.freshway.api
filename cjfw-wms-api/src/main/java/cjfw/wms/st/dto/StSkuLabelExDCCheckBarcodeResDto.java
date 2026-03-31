package cjfw.wms.st.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : baechan (c_bae@cj.net)
 * @date : 2025.09.05
 * @description : 상품이력번호등록(재고생성) Master Response DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.05 baechan (c_bae@cj.net) 생성
 *         </pre>
 */
@Data
@Schema(description = "상품이력번호등록(재고생성) 바코드 중복 조회 응답")
public class StSkuLabelExDCCheckBarcodeResDto {
	/** 응답 결과 코드 */
	@Schema(description = "응답 결과 코드", example = "")
	private String result;
		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}