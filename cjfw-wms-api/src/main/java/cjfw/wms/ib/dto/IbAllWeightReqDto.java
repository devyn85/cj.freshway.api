package cjfw.wms.ib.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
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
 * @date : 2025.11.12
 * @description : 센터별물동량 정산 Request DTO Class
 * @issues :
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.12 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "센터별물동량 정산 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IbAllWeightReqDto extends CommonProcedureDto {
	/** fixdccode */
	@Schema(description = "fixdccode")
	private String fixdccode;

	/** fixdcname */
	@Schema(description = "fixdcname")
	private String fixdcname;

	/** yyyymm */
	@Schema(description = "yyyymm")
	private String yyyymm;

	/** yyyy */
	@Schema(description = "yyyy")
	private String yyyy;

	/** yy */
	@Schema(description = "yy")
	private String yy;

	/** mm */
	@Schema(description = "mm")
	private String mm;

	/** custname */
	@Schema(description = "custname")
	private String custname;

    /** slipdtFrom */
    @Schema(description = "slipdtFrom")
    private String slipdtFrom;

    /** slipdtTo */
    @Schema(description = "slipdtTo")
    private String slipdtTo;

	/** toyyyymm */
	@Schema(description = "toyyyymm")
	private String toyyyymm;

	/** 협력사 */
	@Schema(description = "협력사")
	private String custkey;

	/** rmk */
	@Schema(description = "rmk")
	private String rmk;

	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> custkeyMulti;

    @Schema(description = "출력리스트")
    private List<IbAllWeightResDto> saveList;
}
