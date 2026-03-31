package cjfw.wms.tm.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System
 * @date : 2025.01.XX
 * @description : 배송현황(경로별) 조회 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.XX System      생성 </pre>
 */
@Data
@Schema(description = "배송현황(경로별) 조회 응답 DTO")
public class TmDeliveryStatusByRouteResDto {

    /**센터코드*/
    @Schema(description = "센터코드")
    private String dccode;

    /**센터명*/
    @Schema(description = "센터명")
    private String dcname;

    /**경로코드*/
    @Schema(description = "경로코드")
    private String course;

    /**경로명*/
    @Schema(description = "경로명")
    private String courseName;

    /**전체*/
    @Schema(description = "전체 (고객 출/도착 2회 기준)")
    private Integer alertCustCnt;

    /**출/도착보고*/
    @Schema(description = "출/도착보고 건수")
    private Integer alertCnt;

    /**보고율*/
    @Schema(description = "보고율 (%)")
    private BigDecimal alertRate;

}

