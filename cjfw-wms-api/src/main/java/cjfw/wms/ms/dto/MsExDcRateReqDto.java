package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
* 
* Copyright 2025. CJ OliveNetworks Co. all rights reserved. 
*
* @author : ParkJinWoo 
* @date : 2025.06.04 
* @description : SKU 스펙 조회 요청 DTO
* @issues : 
* ----------------------------------------------------------- 
* DATE AUTHOR MAJOR_ISSUE 
* ----------------------------------------------------------- 
* 2025.06.04 ParkJinWoo 생성
*/
@Data
@Schema(description = "외부창고요율관리 요청 DTO")
public class MsExDcRateReqDto extends CommonDto{


 
	/**시작일자 from*/
    @Schema(description = "시작일자 from", nullable = true, example = "")
    private String startdateFrom;

    /**시작일자 to*/
    @Schema(description = "시작일자 to", nullable = true, example = "")
    private String startdateTo;

    /**종료일자 from*/
    @Schema(description = "종료일자 from", nullable = true, example = "")
    private String enddateFrom;

    /**종료일자 to*/
    @Schema(description = "종료일자 to", nullable = true, example = "")
    private String enddateTo;

    /**삭제여부*/
    @Schema(description = "삭제여부", nullable = true, example = "")
    private String delYn;

    /**저장조건*/
    @Schema(description = "저장조건", nullable = true, example = "")
    private String storageType;

    /**상품스펙코드*/
    @Schema(description = "상품스펙코드", nullable = true, example = "")
    private String specCode;

    /**창고코드*/
    @Schema(description = "창고코드 다중선택 (쉼표 구분)", nullable = true, example = "")
    private String organize;

    /**상품코드*/
    @Schema(description = "상품코드 다중선택 (쉼표 구분)", nullable = true, example = "")
    private String sku;
    
    /**상품코드*/
    @Schema(description = "거래처", nullable = true, example = "")
    private String custKey;
    
    private String fixDcCode;
}
