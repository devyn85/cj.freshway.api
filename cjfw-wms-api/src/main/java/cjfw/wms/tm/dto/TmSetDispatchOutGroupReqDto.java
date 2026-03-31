package cjfw.wms.tm.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import cjfw.wms.ms.dto.MsOutGroupSettingReqDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.27 
 * @description : 실비차 배차 요청 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.27 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "실비차 배차 요청 DTO")
@EqualsAndHashCode(callSuper = true)
public class TmSetDispatchOutGroupReqDto extends CommonDto {
    /** 물류센터 코드 */
    @Schema(description = "물류센터 코드", example = "2600", nullable = false)
    private String dccode;

    /** 배송일자 */
    @Schema(description = "배송일자 (YYYYMMDD 형식)", example = "20250120", nullable = false)
    private String deliveryDate;

    /** 배송 유형 */
    @Schema(description = "배송 유형", example = "1", nullable = true, allowableValues = {"1", "7", "3", "2"})
    private String tmDeliveryType;
    
    /** 거래처 코드  */
    @Schema(description = "거래처 코드 ',' 단위로 구분 다중검색", example = "CUST001", nullable = true)
    private String custCode;
    
    @Schema(description = "거래처 코드 리스트", example = "[CUST001]", hidden = true,   nullable = true)
    private String[] custCodeList;

    private List<List<String>> chunkedCustCodeList;
    
    @Schema(description = "요청 실비차 리스트")
	List<MsOutGroupSettingReqDto> outGroupOptionList;
	
    @Schema(description = "미배차 주문 리스트")
	List<TmSetDispatchUnassignedOrderResDto> unAssignedOrderList;

    public String getDescription() {
        return dccode + "_" + deliveryDate + "_" + tmDeliveryType + "_" + getGUserId();
    }

    public void setCustCodeListToArray () {
    	if(this.custCode != null && !this.custCode.equals("")) {
    		this.custCodeList = this.custCode.split(",");	
    	}
    }
}
