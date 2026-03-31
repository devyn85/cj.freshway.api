package cjfw.wms.dp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.16 
 * @description : 입고진행현황 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.16 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "입고진행현황 목록 결과")
public class DpInplanResDto {
	@Schema(description = "물류센터", example = "DC001")
    private String dccode;
    @Schema(description = "창고", example = "MAIN_WH")
    private String organizedesc;
    @Schema(description = "입고전표번호", example = "SLIP12345")
    private String slipno;
    @Schema(description = "구매유형", example = "일반구매")
    private String ordertypeName;
    @Schema(description = "통합배송 주문유형", example = "통합배송")
    private String tplTypeNm;
    @Schema(description = "입고일자", example = "2023-01-01")
    private String slipdt;
    @Schema(description = "협력사코드", example = "CUST001")
    private String fromCustkey;
    @Schema(description = "협력사명", example = "테스트협력사")
    private String fromCustname;
    @Schema(description = "진행상태", example = "완료")
    private String status;
    @Schema(description = "STORERKEY", example = "STORER001")
    private String storerkey;
    @Schema(description = "DOCTYPE", example = "DOC001")
    private String doctype;
    @Schema(description = "ORGANIZE", example = "ORGANIZE001")
    private String organize;
    @Schema(description = "ORDERTYPE", example = "ORDERTYPE001")
    private String ordertype;
    @Schema(description = "DOCDT", example = "2023-01-01")
    private String docdt;
    	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
