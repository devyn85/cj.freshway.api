package cjfw.wms.kp.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.11.29 
 * @description : 광역출고검수현황 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.29 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "광역출고검수현황 목록 결과")
public class KpDpInspectMonitoringSTOResDto extends CommonProcedureDto{
	/**
     * 출고일자
     */
    @Schema(description = "출고일자", example = "2025-11-29")
    private String slipdt;

    /**
     * 공급물류센터
     */
    @Schema(description = "공급물류센터", example = "DC01")
    private String fromDc;

    /**
     * 공급물류센터명
     */
    @Schema(description = "공급물류센터명", example = "수도권센터")
    private String fromDcname;

    /**
     * 공급받는물류센터
     */
    @Schema(description = "공급받는물류센터", example = "DC02")
    private String toDc;

    /**
     * 공급받는물류센터명
     */
    @Schema(description = "공급받는물류센터명", example = "영남센터")
    private String toDcname;

    /**
     * 저장유무
     */
    @Schema(description = "저장유무", example = "Y")
    private String channelname;

    /**
     * 상차예정건수
     */
    @Schema(description = "상차예정건수", example = "100")
    private BigDecimal loadplancnt;

    /**
     * 상차완료건수
     */
    @Schema(description = "상차완료건수", example = "90")
    private BigDecimal loadcmpcnt;

    /**
     * 상차진행률
     */
    @Schema(description = "상차진행률", example = "90.00")
    private BigDecimal loadrate;

    /**
     * 결품건수
     */
    @Schema(description = "결품건수", example = "10")
    private BigDecimal shortagecnt;

    /**
     * 결품율
     */
    @Schema(description = "결품율", example = "10.00")
    private BigDecimal shortagerate;

    /**
     * 강제완료건수
     */
    @Schema(description = "강제완료건수", example = "5")
    private BigDecimal forcecmpcnt;

    /**
     * 강제완료률
     */
    @Schema(description = "강제완료률", example = "5.00")
    private BigDecimal forcecmprate;

    /**
     * 강제완료여부
     */
    @Schema(description = "강제완료여부", example = "N")
    private String forcecmpyn;

    /**
     * 채널
     */
    @Schema(description = "채널", example = "CH01")
    private String channel;
}
