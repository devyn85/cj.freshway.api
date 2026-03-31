package cjfw.wms.wd.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.22
 * @description : 재고 > 공용기 관리업 > PLT 수불 관리  Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.22 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고 > 공용기 관리업 > PLT 수불 관리 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WdPltDpReqDto extends CommonDto {
	
	/* dccode */
	@Schema(description = "dccode")
	private String dccode;

	/* deliverydtFrom */
	@Schema(description = "deliverydtFrom")
	private String deliverydtFrom;

	/* deliverydtTo */
	@Schema(description = "deliverydtTo")
	private String deliverydtTo;

	/* pltComDv */
	@Schema(description = "pltComDv")
	private String pltComDv;
	
	/* 협력사 */
	@Schema(description = "협력사")
	private String custkey;
	
	/* 협력사-멀티 */
	@MultiSearch
    @Schema(description = "협력사-멀티")
    private List<List<String>> custkeyMulti;  
	
	/* saveList */
	@Schema(description = "saveDataList")
	private List<WdPltDpResDto> saveDataList;
	
}
