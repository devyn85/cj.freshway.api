package cjfw.wms.dp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.17 
 * @description : 이력상품입고현황 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.17 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "이력상품입고현황 목록 결과")
public class DpInplanSNResDto {
	@Schema(description = "입고일자", example = "2024-01-01")
    private String slipdt;
    @Schema(description = "물류센터", example = "SLC")
    private String dccode;
    @Schema(description = "창고", example = "MAIN_WH")
    private String organizedesc;
    @Schema(description = "구매번호", example = "PO20240101001")
    private String docno;
    @Schema(description = "구매유형", example = "정상구매")
    private String ordertypeName;
    @Schema(description = "협력사코드", example = "CUST001")
    private String fromCustkey;
    @Schema(description = "협력사명", example = "ABC전자")
    private String fromCustname;
    @Schema(description = "진행상태", example = "완료")
    private String statusName;
    @Schema(description = "", example = "2024-01-01")
    private String docdt;
    @Schema(description = "", example = "대전물류센터")
    private String dcname;
    @Schema(description = "", example = "PURCHASE")
    private String ordertype;
    @Schema(description = "", example = "COMPLETED")
    private String status;
    @Schema(description = "", example = "STR001")
    private String storerkey;
    @Schema(description = "", example = "PO")
    private String doctype;
    @Schema(description = "", example = "ORG001")
    private String organize;
    	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
