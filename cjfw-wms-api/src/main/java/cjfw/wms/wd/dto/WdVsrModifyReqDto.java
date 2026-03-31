package cjfw.wms.wd.dto;

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
 * @date : 2025.10.21
 * @description : 출고 > 출고 > CS 출고 정정 요청 대응 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.21 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "출고 > 출고 > CS 출고 정정 요청 대응 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WdVsrModifyReqDto extends CommonProcedureDto {
	
	/* 01. 물류센터 */
	@Schema(description = "dccode")
	private String dccode;

	/* 02. 조회일자(From) */
	@Schema(description = "조회일자(From)")
	private String ifDateFrom;

	/* 03. 조회일자(To) */
	@Schema(description = "조회일자(To)")
	private String ifDateTo;
	
	/* 04. 처리구분 */
	@Schema(description = "04. 처리구분")
	private String status;
	
	/* saveList */
	@Schema(description = "saveDataList")
	private List<WdVsrModifyResDto> saveDataList;
}
