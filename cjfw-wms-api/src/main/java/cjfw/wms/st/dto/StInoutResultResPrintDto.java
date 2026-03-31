package cjfw.wms.st.dto;

import java.util.List;

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
 * @description : 수불현황 Print DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.31 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "수불현황 Print Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StInoutResultResPrintDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	/** 레포트 Header */
	//List<StInoutResultResPrintDto> reportHeader;
	List<StInoutResultResPrintDto> reportList;
	
	/** 상품코드 */
	@Schema(description = "상품코드")
	private String sku;	
	
	/** 상품명 */
	@Schema(description = "상품명")
	private String skuname;

	/** 보관유형 */
	@Schema(description = "보관유형")
	private String storagetype;

	/** 위치 */
	@Schema(description = "위치")
	private String loc;

	/** 평균재고수량 */
	@Schema(description = "평균재고수량")
	private String avgqty;

	/** 출고합계 */
	@Schema(description = "출고합계")
	private String totalWd;

	/** 평균출고합계 */
	@Schema(description = "평균출고합계")
	private String avgtotalWd;

	/** 회전일수 */
	@Schema(description = "회전일수")
	private String turnoverday;

	/** 공급자 */
	@Schema(description = "공급자")
	private String provider;

}
