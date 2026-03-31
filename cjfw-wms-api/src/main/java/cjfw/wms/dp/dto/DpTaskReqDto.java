package cjfw.wms.dp.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.31
 * @description : 입고검수지정 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.31 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "입고검수지정 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpTaskReqDto extends CommonProcedureDto {
	/** docdtFrom */
	@Schema(description = "docdtFrom")
	private String docdtFrom;

	/** docdtTo */
	@Schema(description = "docdtTo")
	private String docdtTo;

	/** item */
	@Schema(description = "item")
	private String item;

	/** skugroup1 */
	@Schema(description = "skugroup1")
	private String skugroup1;

	/** skugroup2 */
	@Schema(description = "skugroup2")
	private String skugroup2;

	/** putawaytype */
	@Schema(description = "putawaytype")
	private String putawaytype;

	/** fromtaskdt */
	@Schema(description = "fromtaskdt")
	private String fromtaskdt;

	/** totaskdt */
	@Schema(description = "totaskdt")
	private String totaskdt;

	/** fromcustkey */
	@Schema(description = "fromcustkey")
	private String fromcustkey;

	/** sku */
	@Schema(description = "sku")
	private String sku;

	/** saveList */
	@Schema(description = "saveList")
	private List<DpTaskResDto> saveList;
}
