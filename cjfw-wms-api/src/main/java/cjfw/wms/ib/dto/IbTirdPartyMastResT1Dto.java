package cjfw.wms.ib.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.25
 * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_단가마스터_탭 Response DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.25 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_단가마스터_탭 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IbTirdPartyMastResT1Dto extends CommonProcedureDto {
	
	/* 01. 물류센터 */
	@Schema(description = "01. 물류센터")
	private String dccode;

	/* 01. 물류센터 */
	@Schema(description = "01. 물류센터")
	private String dcname;

	/* 02. 단가유형 */
	@Schema(description = "02. 단가유형")
	private String pricetype;
	
	/* 02. 단가유형 */
	@Schema(description = "02. 단가유형")
	private String pricetypeNm;

	/* 03. 피킹대행단가(원) */
	@Schema(description = "03. 피킹대행단가(원)")
	private String pickprice;

	/* 04. 등록자 */
	@Schema(description = "04. 등록자")
	private String addwho;

	/* 05. 등록일시 */
	@Schema(description = "05. 등록일시")
	private String adddate;

	/* 06. 수정자 */
	@Schema(description = "06. 수정자")
	private String editwho;

	/* 07. 수정일시 */
	@Schema(description = "07. 수정일시")
	private String editdate;
	
	/* 등록자 ID */
	@Schema(description = "등록자 ID")
	private String addId;
	
	/* 수정자 ID */
	@Schema(description = "수정자 ID")
	private String updId;
	
    /** 커스텀 엑스트라 체크박스 */
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn = "N";

}
