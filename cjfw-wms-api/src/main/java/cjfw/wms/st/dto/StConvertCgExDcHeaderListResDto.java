package cjfw.wms.st.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved. 
 *
 * @author : ParkJinWoo 
 * @date : 2025.06.04 
 * @description :외부창고 요율 평균치 체크 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.17 ParkJinWoo 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StConvertCgExDcHeaderListResDto {   // gUserId 등은 CommonDto에서 상속
    /** 창고 Area 코드 */
    @Schema(description = "창고 Area 코드")
    private String whArea;

    /** 창고 Area 명 */
    @Schema(description = "창고 Area 명")
    private String whAreaName;

    /** 층 */
    @Schema(description = "층")
    private String whAreaFloor;

    /** 층 명 */
    @Schema(description = "층 명")
    private String whAreaFloorName;

    /** 존 코드 */
    @Schema(description = "존 코드")
    private String zone;

    /** 존 명 */
    @Schema(description = "존 명")
    private String zoneName;

    /** 로케이션 수 */
    @Schema(description = "로케이션 수")
    private Integer locCnt;

    /** SKU 수 */
    @Schema(description = "SKU 수")
    private Integer skuCnt;

    /** 재고 ID 수 */
    @Schema(description = "재고 ID 수")
    private Integer idCnt;

		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}