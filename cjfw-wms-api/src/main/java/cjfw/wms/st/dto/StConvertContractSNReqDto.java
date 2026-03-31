package cjfw.wms.st.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.11.14 
 * @description : 상품이력계약정보변경 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.14 ParkJinWoo 생성
 */
@Schema(description = "재고 > 제고조정 > 상품이력계약정보변경")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StConvertContractSNReqDto extends CommonProcedureDto {
	
	private List<StConvertContractSNResDto> saveList;
	 /** 상품 다중선택(콤마구분) */
    @Schema(description = "상품 다중선택(콤마구분)")
    private String multiSku;

    /** 이력번호 */
    @Schema(description = "이력번호")
    private String serialNo;

    /** BL번호(Like 검색값) */
    @Schema(description = "BL번호(Like 검색값)")
    private String blNo;

    /** 계약업체코드 */
    @Schema(description = "계약업체코드")
    private String contractCompany;
}
