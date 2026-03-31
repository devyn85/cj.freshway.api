package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.09.29 
 * @description : 이력STO출고처리-STO출고확정 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.29 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "이력STO출고처리-STO출고확정 목록 결과")
public class WdSerialOrderSTOResTab2Dto extends CommonDto{
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/**
     * 출고일자
     */
    @Schema(description = "출고일자", example = "2025-09-29")
    private String slipdt;
    /**
     * 진행상태
     */
    @Schema(description = "진행상태", example = "COMPLETED")
    private String status;
    /**
     * 광역주문번호
     */
    @Schema(description = "광역주문번호", example = "1234567890")
    private String docno;
    /**
     * 공급센터-물류센터
     */
    @Schema(description = "공급센터-물류센터", example = "DC01")
    private String fromDccode;
    /**
     * 공급센터-물류센터명
     */
    @Schema(description = "공급센터-물류센터명", example = "CJ대한통운")
    private String fromDcname;
    /**
     * 공급받는센터-물류센터
     */
    @Schema(description = "공급받는센터-물류센터", example = "DC02")
    private String toDccode;
    /**
     * 공급받는센터-물류센터명
     */
    @Schema(description = "공급받는센터-물류센터명", example = "CJ프레시웨이")
    private String toDcname;
    /**
     * 상품수
     */
    @Schema(description = "상품수", example = "10")
    private BigDecimal skucnt;
    /**
     * 중량
     */
    @Schema(description = "중량", example = "500.50")
    private BigDecimal weight;
    /**
     * dccode
     */
    @Schema(description = "dccode", example = "DC01")
    private String dccode;
    /**
     * storerkey
     */
    @Schema(description = "storerkey", example = "STORER01")
    private String storerkey;
    /**
     * organize
     */
    @Schema(description = "organize", example = "ORG01")
    private String organize;
    /**
     * docdt
     */
    @Schema(description = "docdt", example = "2025-09-29")
    private String docdt;
}
