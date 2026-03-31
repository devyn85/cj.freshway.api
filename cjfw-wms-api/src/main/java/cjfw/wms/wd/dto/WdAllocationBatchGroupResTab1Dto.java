package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.08 
 * @description : 출고재고분배-자동분배 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.08 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "출고재고분배-자동분배 목록 결과")
public class WdAllocationBatchGroupResTab1Dto extends CommonDto{
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/**
     * 작업지시일자
     */
    @Schema(description = "작업지시일자", example = "2023-01-01")
    private String slipdt;

    /**
     * 플랜트
     */
    @Schema(description = "플랜트", example = "P001")
    private String plant;

    /**
     * 저장조건
     */
    @Schema(description = "저장조건", example = "COLD")
    private String storagetype;
    
    /**
     * 저장조건명
     */
    @Schema(description = "저장조건명", example = "COLD")
    private String storagetypedesc;

    /**
     * 원거리유형
     */
    @Schema(description = "원거리유형", example = "LONG")
    private String distancetype;

    /**
     * 생성키
     */
    @Schema(description = "생성키", example = "CREATEKEY001")
    private String createkey;

    /**
     * 생성키명
     */
    @Schema(description = "생성키명", example = "생성키명입니다")
    private String createdescr;

    /**
     * 식별번호유무
     */
    @Schema(description = "식별번호유무", example = "Y")
    private String serialyn;

    /**
     * 주문량
     */
    @Schema(description = "주문량", example = "100")
    private BigDecimal orderqty;

    /**
     * 분배량
     */
    @Schema(description = "분배량", example = "50")
    private BigDecimal processqty;
    
    /**
     * STO할당량
     */
    @Schema(description = "STO할당량", example = "50")
    private BigDecimal stoqty;

    /**
     * 미분배량
     */
    @Schema(description = "미분배량", example = "50")
    private BigDecimal processopenqty;

    /**
     * DCCODE
     */
    @Schema(description = "DCCODE", example = "DC001")
    private String dccode;

    /**
     * STORERKEY
     */
    @Schema(description = "STORERKEY", example = "STORER001")
    private String storerkey;

    /**
     * SY_PROCESSTEMP_WD.용 생성 배치키
     */
    @Schema(description = "SY_PROCESSTEMP_WD.용 생성 배치키", example = "WAVEKEY001")
    private String wavekey;

    /**
     * BATCHGROUP
     */
    @Schema(description = "BATCHGROUP", example = "BATCH001")
    private String batchgroup;

    /**
     * OPENQTY
     */
    @Schema(description = "OPENQTY", example = "150")
    private BigDecimal openqty;

    /**
     * WORKQTY
     */
    @Schema(description = "WORKQTY", example = "70")
    private BigDecimal workqty;

    /**
     * INSPECTQTY
     */
    @Schema(description = "INSPECTQTY", example = "20")
    private BigDecimal inspectqty;

    /**
     * CONFIRMQTY
     */
    @Schema(description = "CONFIRMQTY", example = "10")
    private BigDecimal confirmqty;
    

    /**
     * 선마감여부
     */
    @Schema(description = "선마감여부", example = "BATCH001")
    private String preclosingYn;
}
