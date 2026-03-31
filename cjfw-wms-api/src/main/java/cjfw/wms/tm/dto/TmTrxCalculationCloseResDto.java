package cjfw.wms.tm.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2026.02.04 
 * @description : 운송료정산 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.02.04 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "운송료정산 조회 Dto")
public class TmTrxCalculationCloseResDto extends CommonDto {

	/** 정산월 */
    @Schema(description = "정산월")
    private String sttlYm;

    /** 물류센터코드 */
    @Schema(description = "물류센터코드")
    private String dcCode;

    /** 운송사코드 */
    @Schema(description = "운송사코드")
    private String courier;
    /** 운송사코드 */
    @Schema(description = "운송사코드")
    private String courierName;

    /** 정산마감여부 */
    @Schema(description = "정산마감여부")
    private String closeYn;

    /** 최초등록시간 */
    @Schema(description = "최초등록시간")
    private String adddate;

    /** 최종변경시간 */
    @Schema(description = "최종변경시간")
    private String editdate;

    /** 최초등록자 */
    @Schema(description = "최초등록자")
    private String addwho;

    /** 등록자명 */
    @Schema(description = "등록자명")
    private String addwhoname;

    /** 최종변경자 */
    @Schema(description = "최종변경자")
    private String editwho;

    /** 수정자명 */
    @Schema(description = "수정자명")
    private String editwhoname;
    
    /** 배송일자 */
    @Schema(description = "배송일자")
    private String deliverydate;
    
}
