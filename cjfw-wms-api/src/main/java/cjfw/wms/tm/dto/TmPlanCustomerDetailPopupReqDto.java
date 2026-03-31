package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.29 
 * @description : 거래처 상세팝업 요청 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "거래처 상세팝업 요청 DTO") 
public class TmPlanCustomerDetailPopupReqDto {
	
	@Schema(description = "실착지 키")
	private String truthCustkey;
	
	@Schema(description = "고객사 코드")
	private String storerkey;
	
	@Schema(description = "거래처유형")
	private String custtype;
	
	@Schema(description = "배송일자")
	private String deliveryDate;
	
	@Schema(description = "배송유형")
	private String tmDeliveryType;
	
	@Schema(description = "센터코드")
	private String dccode;
	
	@Schema(description = "배차상태")
	private String dispatchStatus;
	
	@Schema(description = "차량번호(배차 저장 이전 요청 X)", nullable = true)
	private String carno;
	
    /** 거래처 코드 (단일) */
    @Schema(description = "거래처 코드 ',' 단위로 구분 다중검색", nullable = true)
    private String custCode;
    
    @Schema(description = "거래처 코드 리스트", hidden = true,   nullable = true)
    private String[] custCodeList;
    
    
    public void setCustCodeListToArray () {
    	if(this.custCode != null && !this.custCode.equals("")) {
    		this.custCodeList = this.custCode.split(",");	
    	}
    }

}
