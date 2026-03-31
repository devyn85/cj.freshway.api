package cjfw.wms.wd.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.11.17
 * @description : 모니터링 > 검수 > 검수 공정별 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.17 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "모니터링 > 검수 > 검수 공정별 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WdItemTraceReqDto extends CommonProcedureDto {
	
	/* 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;
	
	/* 출고일자 */
	@Schema(description = "출고일자")
	private String deliverydate;

	/* 저장유무 */
	@Schema(description = "저장유무")
	private String channel;
	
	/* 상품코드 */
	@Schema(description = "상품코드")
	private String sku;
	
	/* 상품코드-멀티 */
	@MultiSearch
    @Schema(description = "상품코드-멀티")
    private List<List<String>> skuMulti;  
	
	/* 저장조건 */
	@Schema(description = "저장조건")
	private String storagetype;
	
	/* 협력사 */
	@Schema(description = "협력사")
	private String fromCustkey;
	
	/* 협력사-멀티 */
	@MultiSearch
    @Schema(description = "협력사-멀티")
    private List<List<String>> fromCustkeyMulti;
	
	/* 관리처 */
	@Schema(description = "관리처")
	private String toCustkey;
	
	/* 관리처-멀티 */
	@MultiSearch
	@Schema(description = "관리처-멀티")
	private List<List<String>> toCustkeyMulti;  
	
	/* 주문번호 */
	@Schema(description = "주문번호")
	private String docno;
	
	/* 결품여부 */
	@Schema(description = "결품여부")
	private String shortageYn;
	
	/* 구분 */
	@Schema(description = "구분")
	private String gubun;
}
