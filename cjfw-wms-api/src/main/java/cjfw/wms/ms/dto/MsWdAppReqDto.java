package cjfw.wms.ms.dto;

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
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.10.24
 * @description : 기준정보 > 물류센터 정보 > 결품대응 POP그룹 관리 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.24 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "기준정보 > 물류센터 정보 > 결품대응 POP그룹 관리 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsWdAppReqDto extends CommonProcedureDto {
	
	/* dccode */
	@Schema(description = "dccode")
	private String dccode;

	/* popGroup */
	@Schema(description = "popGroup")
	private String popGroup;

	/* storagetype */
	@Schema(description = "storagetype")
	private String storagetype;

	/* useYn */
	@Schema(description = "useYn")
	private String useYn;
	
	/* saveDetailFlag */
	@Schema(description = "saveDetailFlag")
	private String saveDetailFlag;
	
	/* saveMasterList */
	@Schema(description = "saveMasterList")
	private List<MsWdAppResDto> saveMasterList;
	
	/* saveDetailList */
	@Schema(description = "saveDetailList")
	private List<MsWdAppResDetailDto> saveDetailList;
}
