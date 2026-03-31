package cjfw.wms.st.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.18 
 * @description : 보충생성-거래처 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.18 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "보충생성-거래처 결과")
public class StLocMoveRPCustResTab1CustkeyDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	/**
     * 관리처코드
     */
    @Schema(description = "관리처코드", example = "CUST001")
    private String toCustkey;
    /**
     * 관리처명
     */
    @Schema(description = "관리처명", example = "ABC상사")
    private String custname;
    /**
     * 피킹유형
     */
    @Schema(description = "피킹유형", example = "ABC상사")
    private String distancetype;
    /**
     * 상품수
     */
    @Schema(description = "상품수", example = "10")
    private String skuCnt;
    /**
     * 예정량
     */
    @Schema(description = "예정량", example = "100")
    private String orderqty;
    /**
     * 분배량
     */
    @Schema(description = "분배량", example = "50")
    private String processqty;
    /**
     * 미분배량
     */
    @Schema(description = "미분배량", example = "50")
    private String unprocessqty;
}
