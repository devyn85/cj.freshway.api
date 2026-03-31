package cjfw.wms.wd.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.14 
 * @description : 배송라벨출력(하나로엑셀) 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.14 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "배송라벨출력(하나로엑셀) 요청") 
public class WdDeliveryLabelForceHanaroReqDto extends CommonDto {
	

	/**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "C100")
    private String fixdccode;
    
	/**
	 * 하나로
	 */
	/** 출고일자 */
	@Schema(description = "출고일자", nullable = false, example = "")
	private String taskdt;

	/** 관리처코드 */
	@Schema(description = "관리처코드", nullable = false, example = "")
	private String toCustkey;
	
	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> toCustkeyMulti; 
	
	/** 상품코드 */
	@Schema(description = "상품코드", nullable = false, example = "")
	private String sku;
	
	/** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;    
	
	/**
	 * 쿠팡
	 */
	/** 대배치키 */
	@Schema(description = "대배치키", nullable = false, example = "")
	private String pickbatchno;
	
	/** 대배치키(다중검색) */
	@MultiSearch
    @Schema(description = "대배치키-다중OR검색")
    private List<String> pickbatchnoMulti; 
	
	/** CROSS센터 */
	@Schema(description = "CROSS센터", nullable = false, example = "")
	private String crossdc;
	
	/** 피킹번호 */
	@Schema(description = "피킹번호", nullable = false, example = "")
	private String pickno;

	/** 피킹번호(다중검색) */
	@MultiSearch
    @Schema(description = "피킹번호-다중OR검색")
    private List<String> picknoMulti; 	
	
	/** 피킹방법 */
	@Schema(description = "피킹방법", nullable = false, example = "")
	private String tasksystem;
	
	/** 주문유형 */
	@Schema(description = "주문유형", nullable = false, example = "")
	private String ordertype;
	
	/** 상품분류 */
	@Schema(description = "상품분류", nullable = false, example = "")
	private String skugroup;
	
	/** 저장조건 */
	@Schema(description = "저장조건", nullable = false, example = "")
	private String storagetype;
	
	/** C/D타입 */
	@Schema(description = "C/D타입", nullable = false, example = "")
	private String crossdocktype;
	
	/** 출력방법 */
	@Schema(description = "출력방법", nullable = false, example = "")
	private String printmethod;
	
		
	
}
