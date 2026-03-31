package cjfw.wms.st.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.07.31
 * @description : 수불현황 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.31 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "수불현황 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StInoutResultResDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	/** 센터코드 */
	@Schema(description = "센터코드")
	private String dccode;

	/** 화주코드 */
	@Schema(description = "화주코드")
	private String storerkey;

	/** 조직코드 */
	@Schema(description = "조직코드")
	private String organize;

	/** 단위 */
	@Schema(description = "단위")
	private String uom;

	/** 상품코드 */
	@Schema(description = "상품코드")
	private String sku;

	/** 상품명 */
	@Schema(description = "상품명")
	private String skuname;

	/** 상품범주 */
	@Schema(description = "상품범주")
	private String mc;

	/** 기준단위 */
	@Schema(description = "기준단위")
	private String baseuom;

	/** 기초재고수 */
	@Schema(description = "기초재고수")
	private String baseqty;

	/** 입고 */
	@Schema(description = "입고")
	private String receiptqty;

	/** 고객반품 */
	@Schema(description = "고객반품")
	private String returnqty;

	/** 이체입고 */
	@Schema(description = "이체입고")
	private String transferqtyDp;

	/** 역감모 */
	@Schema(description = "역감모")
	private String surplusqty;

	/** 입고합계 */
	@Schema(description = "입고합계")
	private String totalDp;

	/** 출고 */
	@Schema(description = "출고")
	private String orderqty;

	/** 협력사반품 */
	@Schema(description = "협력사반품")
	private String vendorreturnqty;

	/** 이체출고 */
	@Schema(description = "이체출고")
	private String transferqtyWd;

	/** 감모 */
	@Schema(description = "감모")
	private String decreaseqty;

	/** 폐기수량 */
	@Schema(description = "폐기수량")
	private String disuseqty;

	/** 출고합계 */
	@Schema(description = "출고합계")
	private String totalWd;

	/** 재고수 */
	@Schema(description = "재고수")
	private String stockqty;

	/** 저장품입고 */
	@Schema(description = "저장품입고")
	private String receiptqtyChannel1;

	/** 일배입고 */
	@Schema(description = "일배입고")
	private String receiptqtyChannel2;

	/** 광역일배입고 */
	@Schema(description = "광역일배입고")
	private String receiptqtyChannel3;

	/** 저장품출고 */
	@Schema(description = "저장품출고")
	private String orderqtyChannel1;

	/** 일배출고 */
	@Schema(description = "일배출고")
	private String orderqtyChannel2;

	/** 광역일배출고 */
	@Schema(description = "광역일배출고")
	private String orderqtyChannel3;

	/** 저장품반품 */
	@Schema(description = "저장품반품")
	private String returnqtyChannel1;

	/** 일배반품 */
	@Schema(description = "일배반품")
	private String returnqtyChannel2;

	/** 광역일배반품 */
	@Schema(description = "광역일배반품")
	private String returnqtyChannel3;
	
	/** 3자물류 주문 유형  */
	@Schema(description = "3자물류 주문 유형 ")
	private String tplType;
}
