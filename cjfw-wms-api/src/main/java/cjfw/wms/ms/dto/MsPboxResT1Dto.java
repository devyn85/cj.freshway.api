package cjfw.wms.ms.dto;

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
 * @date : 2025.09.18
 * @description : 재고 > 공용기 관리 > P-BOX 관리/사용 현황 P-BOX등록_탭 Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.18 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고 > 공용기 관리 > P-BOX 관리/사용 현황 P-BOX등록_탭 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsPboxResT1Dto extends CommonProcedureDto {
	
	/* 01. 물류센터 */
	@Schema(description = "01. 물류센터")
	private String dccode;

	/* 01. 물류센터 */
	@Schema(description = "01. 물류센터")
	private String dcname;

	/* 02. P_BOX 번호 */
	@Schema(description = "02. P_BOX 번호")
	private String pboxNo;

	/* 03. 기사명 */
	@Schema(description = "03. 기사명")
	private String drivername;
	
	/* 03. 차량번호 */
	@Schema(description = "03. 차량번호")
	private String carno;

	/* 04. 차량할당일 */
	@Schema(description = "04. 차량할당일")
	private String carAllocateDt;

	/* 05. 출력 횟수 */
	@Schema(description = "05. 출력 횟수")
	private String printCnt;

	/* 06. 사용유무 */
	@Schema(description = "06. 사용유무")
	private String useYn;

	/* 07. 비고 */
	@Schema(description = "07. 비고")
	private String rmk;

	/* 08. 등록자 */
	@Schema(description = "08. 등록자")
	private String addwho;

	/* 09. 등록일시 */
	@Schema(description = "09. 등록일시")
	private String adddate;

	/* 10. 수정자 */
	@Schema(description = "10. 수정자")
	private String editwho;

	/* 11. 수정일시 */
	@Schema(description = "11. 수정일시")
	private String editdate;
	
	/* CNT */
	@Schema(description = "CNT")
	private String cnt;
	
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
