package cjfw.wms.tm.dto;

import cjfw.wms.common.annotation.MaskingEngName;
import cjfw.wms.common.annotation.MaskingName;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.06 
 * @description : 일별임시차현황 조회 결과 기능을 구현한 resDto Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.06 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "일별임시차현황 조회 결과 DTO")
public class TmResultTempCarListResDto extends CommonDto {
	 /** 배송일자 */
    @Schema(description = "배송일자")
    private String deliverydt;

    /** 배송번호 */
    @Schema(description = "배송번호")
    private String deliveryno;

    /** 배송그룹 */
    @Schema(description = "배송그룹")
    private String deliverygroup;

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dcCode;

    /** 택배사 */
    @Schema(description = "택배사")
    private String courier;

    /** 차량번호 */
    @Schema(description = "차량번호")
    private String carno;

    /** 권역코드 */
    @Schema(description = "권역코드")
    private String districtCode;

    /** 운송사명 */
    @Schema(description = "운송사명")
    private String carrierName;

    /** 기사명 */
    @MaskingName
    @Schema(description = "기사명")
    private String driverName;

    /** 차종 */
    @Schema(description = "차종")
    private String carType;

    /** 기본권역 */
    @Schema(description = "기본권역")
    private String defDistrictCode;

    /** 차량소유업체 */
    @Schema(description = "차량소유업체")
    private String carAgentKey;

    /** 권역명 */
    @Schema(description = "권역명")
    private String districtName;

    /** 배송건수 */
    @Schema(description = "배송건수")
    private Integer deliveryCnt;

    /** 물동량(KG) */
    @Schema(description = "물동량(KG)")
    private Double weight;

    /** 고객사수 */
    @Schema(description = "고객사수")
    private Integer custKeyCnt;

    /** 단가 */
    @Schema(description = "단가")
    private String unitPrice;
    
    /** 체적 */
    @Schema(description = "체적")
    private String cargoCube;
    
    private String carAgentName;
    
    /** 배송유형 */
    @Schema(description = "배송유형")
    private String tmDeliverytype;
    
    /** 배송유형명 */
    @Schema(description = "배송유형명")
    private String tmDeliverytypeName;
}
