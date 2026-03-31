package cjfw.wms.wd.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.10 
 * @description : 이력상품출고현황 목록 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.10 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "이력상품출고현황 목록 요청") 
public class WdInplanSNReqDto extends CommonDto {
	
    /** 물류센터 */
    @NotBlank(message = "물류센터 필수 입력 값입니다.")
    @Schema(description = "물류센터")
    private String fixdccode;			
    

    /** 창고-다중선택 */
	@Schema(description = "창고-다중선택", nullable = false, example = "4900,8774")
	private String organize;
	
    /** 창고(다중검색) */
	@MultiSearch
    @Schema(description = "창고-다중OR검색")
    private List<String> organizeMulti; 
	
	
	/** toVatno */
	@Schema(description = "toVatno", nullable = false, example = "")
	private String toVatno;

	/** 관리처코드 */
	@Schema(description = "관리처코드", nullable = false, example = "")
	private String toCustkey;
	
	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> toCustkeyMulti; 

	/** 저장조건 */
	@Schema(description = "저장조건", nullable = false, example = "")
	private String storagetype;
	
	/** 출고일자FROM */
	@Schema(description = "출고일자FROM", nullable = false, example = "")
	private String fromSlipdt;
	
	/** 출고일자TO */
	@Schema(description = "출고일자TO", nullable = false, example = "")
	private String toSlipdt;
	
	/** searchserial */
	@Schema(description = "searchserial", nullable = false, example = "")
	private String searchserial;
	
	/** 진행상태 */
	@Schema(description = "진행상태", nullable = false, example = "")
	private String status;
	
	/** 삭제여부 */
	@Schema(description = "삭제여부", nullable = false, example = "Y")
	private String delYn;
	
	/** 주문번호-다중선택 */
	@Schema(description = "주문번호-다중선택", nullable = false, example = "4900,8774")
	private String docno;
	
	/** 주문번호(다중검색) */
	@MultiSearch
    @Schema(description = "주문번호-다중OR검색")
    private List<String> docnoMulti; 
	
	/** 상품코드 */
	@Schema(description = "상품코드", nullable = false, example = "")
	private String sku;
	
	/** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;    	
	
	
	/** 주문사유 */
	@Schema(description = "주문사유", nullable = false, example = "")
	private String ordertype;

	/** 저장유무 */
	@Schema(description = "저장유무", nullable = false, example = "")
	private String channel;
	
	/** serialno */
	@Schema(description = "serialno", nullable = false, example = "")
	private String serialno;
	
	/** blno */
	@Schema(description = "blno", nullable = false, example = "")
	private String blno;
	
	/** contractcompany */
	@Schema(description = "contractcompany", nullable = false, example = "")
	private String contractcompany;
	
	/** dccode */
	@Schema(description = "dccode", nullable = false, example = "")
	private String dccode;
	
	/** storerkey */
	@Schema(description = "storerkey", nullable = false, example = "")
	private String storerkey;
	
	/** docdt */
	@Schema(description = "docdt", nullable = false, example = "")
	private String docdt;
	
	/** doctype */
	@Schema(description = "doctype", nullable = false, example = "")
	private String doctype;
	
	/** deliverygroup */
	@Schema(description = "deliverygroup", nullable = false, example = "")
	private String deliverygroup;
	
	/** carno */
	@Schema(description = "carno", nullable = false, example = "")
	private String carno;
	
	
}
