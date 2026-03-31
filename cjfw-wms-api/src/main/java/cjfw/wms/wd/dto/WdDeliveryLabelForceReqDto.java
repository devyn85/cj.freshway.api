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
 * @date : 2025.10.17
 * @description : 출고 > 출고 > 배송 라벨 출력(예외 기준 적용) Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.17 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "출고 > 출고 > 배송 라벨 출력(예외 기준 적용) Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WdDeliveryLabelForceReqDto extends CommonProcedureDto {
	
	/* 01. 물류센터 */
	@Schema(description = "dccode")
	private String dccode;
	

	/** checkDccode */
	@Schema(description = "checkDccode", example = "2600")
	private String checkDccode;
	
    /** checkDccode(다중검색) */
    @MultiSearch
    @Schema(description = "checkDccode-다중검색")
    private List<String> checkDccodeMulti; 

	/* 02. 출고일자 */
	@Schema(description = "출고일자")
	private String taskdt;

	/* 03. C/D타입 */
	@Schema(description = "C/D타입")
	private String crossdocktype;

	/* 04. 대배치키 */
	@Schema(description = "대배치키")
	private String pickBatchNo;

	/* 05. 피킹번호 */
	@Schema(description = "피킹번호")
	private String pickNo;
	
	/* 06. 주문유형 */
	@Schema(description = "주문유형")
	private String ordertype;
	
	/* 07. CROSS센터 */
	@Schema(description = "CROSS센터")
	private String crossDc;
	
	/* 08. 출력방법  */
	@Schema(description = "출력방법")
	private String printmethod;

	/* 09. 피킹방법  */
	@Schema(description = "피킹방법")
	private String tasksystem;

	/* 10. 관리처코드  */
	@Schema(description = "관리처코드")
	private String toCustkey;
	
    /* 관리처코드-멀티 */
	@MultiSearch
    @Schema(description = "관리처코드-멀티")
    private List<List<String>> toCustkeyMulti;
	
	/* 11. 출력순서  */
	@Schema(description = "출력순서")
	private String printOrder;

	/* 12. 상품코드  */
	@Schema(description = "상품코드")
	private String sku;
	
    /* 상품코드-멀티 */
	@MultiSearch
    @Schema(description = "상품코드-멀티")
    private List<List<String>> skuMulti;
	
	/* 13. 상품분류 */
	@Schema(description = "상품분류")
	private String skugroup;
	
	/* 14. 저장조건 */
	@Schema(description = "저장조건")
	private String storagetype;

	/* 15. 원거리유형 */
	@Schema(description = "원거리유형")
	private String distancetype;
	
	/* 16. CROSS 재고 제외 */
	@Schema(description = "CROSS 재고 제외")
	private String crossYn;
	
	/* 주문번호 */
	@Schema(description = "주문번호 필요")
	private String docno;

    /* 관리처코드-멀티 */
	@MultiSearch
    @Schema(description = "주문번호-멀티")
    private List<List<String>> docnoMulti;
	
	/* usePgm */
	@Schema(description = "usePgm")
	private String usePgm;
	
	/* deliverydate */
	@Schema(description = "deliverydate")
	private String deliverydate;
	
	/* invoiceno */
	@Schema(description = "invoiceno")
	private String invoiceno;
	
	/* integrationLabelYn */
	@Schema(description = "integrationLabelYn")
	private String integrationLabelYn;
	
    /* 인보이스번호-멀티 */
	@MultiSearch
    @Schema(description = "인보이스번호-멀티")
    private List<List<String>> invoicenoMulti;
	
	@Schema(description = "기본정보_탭 저장 리스트")
	List<WdDeliveryLabelForceResT1Dto> saveList;
	
	@Schema(description = "기본정보_탭 저장 리스트")
	List<WdDeliveryLabelForceResT2Dto> saveDataList;

	
	
}
