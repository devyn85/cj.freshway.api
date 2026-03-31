package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.24 
 * @description : 자동취소 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.24 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "자동취소 목록 결과")
public class WdAllocationCancelResDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	@Schema(description = "출고일자", example = "2025-01-01")
    private String slipdt;
    @Schema(description = "진행상태", example = "정상")
    private String status;
    @Schema(description = "관리처코드", example = "CUST001")
    private String custkey;
    @Schema(description = "관리처명", example = "테스트관리처")
    private String custname;
    @Schema(description = "차량번호", example = "01버3355")
    private String carno;
    @Schema(description = "fromdate", example = "2025-01-01")
    private String fromdate;
    @Schema(description = "저장조건", example = "냉장")
    private String storagetype;
    @Schema(description = "전표수", example = "10")
    private BigDecimal slipcnt;
    @Schema(description = "상품수", example = "5")
    private BigDecimal skucnt;
    @Schema(description = "출고량", example = "100")
    private BigDecimal wdqty;
    @Schema(description = "DC코드", example = "DC001")
    private String dccode;
    @Schema(description = "STORERKEY", example = "STORER001")
    private String storerkey;
    @Schema(description = "ORGANIZE", example = "ORG001")
    private String organize;
    @Schema(description = "ORDERTYPE", example = "일반")
    private String ordertype;
    @Schema(description = "OTHER01", example = "OTHER_VALUE")
    private String other01;
}
