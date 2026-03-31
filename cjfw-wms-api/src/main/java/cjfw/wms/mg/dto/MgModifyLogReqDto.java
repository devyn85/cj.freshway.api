package cjfw.wms.mg.dto;

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
 * @author : sss (kduimux@cj.net)
 * @date : 2025.07.31
 * @description : 입고라벨출력(광역) Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.31 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "입고라벨출력(광역) Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MgModifyLogReqDto extends CommonDto {
	
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String fixdccode;	
    
	/** 상품코드 */
	@Schema(description = "상품코드")
	private String sku;
	
    /** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;		

	/** 시리얼여부 */
	@Schema(description = "시리얼여부")
	private String serialyn;

	/** 수정일자(From) */
	@Schema(description = "수정일자(From)")
	private String dt1;

	/** 수정일자(To) */
	@Schema(description = "수정일자(To)")
	private String dt2;

	/** 수정유형 */
	@Schema(description = "수정유형")
	private String modifytype;

	/** 사유코드 */
	@Schema(description = "사유코드")
	private String reasoncode;

	/** 로케이션 */
	@Schema(description = "로케이션")
	private String loc;

	/** 시리얼번호 */
	@Schema(description = "시리얼번호")
	private String serialno;

	/** BL번호 */
	@Schema(description = "BL번호")
	private String blno;

	/** 계약거래처 */
	@Schema(description = "계약거래처")
	private String contractcompany;
	
    /** 거래처(다중OR검색) */
	@MultiSearch
    @Schema(description = "거래처-다중OR검색")
    private List<List<String>> contractcompanyMulti;  		

}
