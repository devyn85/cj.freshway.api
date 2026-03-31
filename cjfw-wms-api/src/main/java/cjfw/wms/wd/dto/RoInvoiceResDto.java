package cjfw.wms.wd.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.16
 * @description : 반품명세서출력 Master Response DTO Class (getMasterList)
 */
@Schema(description = "반품명세서출력 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoInvoiceResDto {
    /** 체크여부 */
    @Schema(description = "체크여부")
    private String checkYn;

    /** 차량번호 */
    @Schema(description = "차량번호")
    private String carno;

    /** 차량단축번호 */
    @Schema(description = "차량단축번호")
    private String shortno;

    /** 우선순위 */
    @Schema(description = "우선순위")
    private String priority;

    /** 배송일자 */
    @Schema(description = "배송일자")
    private String deliverydt;

    /** 배차그룹 */
    @Schema(description = "배차그룹")
    private String deliverygroup;

    /** 거래처수 */
    @Schema(description = "거래처수")
    private Integer custcnt;

    /** 전표수 */
    @Schema(description = "전표수")
    private Integer slipcnt;

    	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
