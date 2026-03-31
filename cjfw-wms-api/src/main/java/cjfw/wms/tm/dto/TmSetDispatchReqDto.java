package cjfw.wms.tm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : AI Assistant
 * @date : 2025.01.14
 * @description : TM 배차 설정 요청 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.14 AI Assistant          생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(value = {
    "startRow", "listCount", "skipCount", "totalCount", 
    "list", "pageNum", "totalPages", "gDccode"
}, allowGetters = false, allowSetters = false)
@Schema(description = "TM 배차 설정 요청 DTO")
public class TmSetDispatchReqDto extends CommonProcedureDto {

    /** 물류센터 코드 (getDccode용) */
    @Schema(description = "물류센터 코드", example = "2600", nullable = false)
    private String dccode;

    /** 배송일자 (YYYYMMDD 형식, 필수) */
    @Schema(description = "배송일자 (YYYYMMDD 형식)", example = "20250120",nullable = false,pattern = "^[0-9]{8}$")
    @jakarta.validation.constraints.NotBlank(message = "배송일자는 필수입니다")
    @jakarta.validation.constraints.Pattern(regexp = "^[0-9]{8}$", message = "배송일자는 YYYYMMDD 형식이어야 합니다")
    private String deliveryDate;

    /** 배송 유형 (기본값: 1) */
    @Schema(description = "배송 유형", example = "1", nullable = true)
    private String deliveryType;

    @Schema(description = "배송유형")
    private String tmDeliveryType;
    
    /** 거래처 코드 (단일) */
    @Schema(description = "거래처 코드 ',' 단위로 구분 다중검색", example = "CUST001", nullable = true)
    private String custCode;
    
    @Schema(description = "거래처 코드 리스트 (split하여 다중 검색)", example = "[CUST001]", hidden = true,   nullable = true)
    private String[] custCodeList;

    @Schema(description = "배차 유형별 옵션 설정 1:배송 / 2:조달", example = "1", nullable = true)
    private String planOptionType;

    @Schema(description = "버튼호출정보")
    private String planEntryType;

    private List<List<String>> chunkedCustCodeList;

    public String getDescription() {
        return dccode + "_" + deliveryDate + "_" + deliveryType + "_" + getGUserId();
    }

    public void setCustCodeListToArray () {
    	if(this.custCode != null && !this.custCode.equals("")) {
    		this.custCodeList = this.custCode.split(",");	
    	}
    }
    
}
