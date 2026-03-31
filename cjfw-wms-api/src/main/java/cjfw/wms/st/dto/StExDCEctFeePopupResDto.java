package cjfw.wms.st.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.07.21
 * @description : 외부창고정산 기타비용등록 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.21   KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Schema(description = "외부창고정산 기타비용등록 조회 결과")
public class StExDCEctFeePopupResDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";


    /** 데이터번호 */
    @Schema(description = "데이터번호", nullable = false, example = "")
    private String serialkey;
    
    /** 공통코드 */
    @Schema(description = "공통코드", nullable = false, example = "")
    private String basecode;

    /** 공통코드명 */
    @Schema(description = "공통코드명", nullable = false, example = "")
    private String basedescr;
    
    /** 금액 */
    @Schema(description = "금액", nullable = false, example = "")
    private Integer tranprice;

    /** DATA1 */
    @Schema(description = "DATA1", nullable = false, example = "")
    private String data1;

    /** DATA2 */
    @Schema(description = "DATA2", nullable = false, example = "")
    private String data2;

    /** DATA3 */
    @Schema(description = "DATA3", nullable = false, example = "")
    private String data3;
    
    /** DATA4 */
    @Schema(description = "DATA4", nullable = false, example = "")
    private String data4;
    
    /** WORK_TRANSACTION_SN */
    @Schema(description = "WORK_TRANSACTION_SN", nullable = false, example = "")
    private Long workTransactionSn;

}
