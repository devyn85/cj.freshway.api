package cjfw.wms.st.dto;

import java.math.BigDecimal;

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
 * @date : 2025.11.12
 * @description : 재고 > 재고운영 > ABC 분석 기준_탭 Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.12 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고 > 재고운영 > ABC 분석 기준_탭 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StAbcQueryResT2Dto extends CommonProcedureDto {
	
	/* 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;

	/* 물류센터명 */
	@Schema(description = "물류센터명")
	private String dcname;

	/* 분석명칭 */
	@Schema(description = "분석명칭")
	private String abcName;

	/* 입고 가중치 */
	@Schema(description = "입고 가중치")
	private BigDecimal dpRatio;

	/* 출고 가중치 */
	@Schema(description = "출고 가중치")
	private BigDecimal wdRatio;

	/* 회전율 가중치 */
	@Schema(description = "회전율 가중치")
	private BigDecimal trunRatio;

	/* 선반랙 */
	@Schema(description = "선반랙")
	private BigDecimal shelfRackBox;

	/* 1단랙 */
	@Schema(description = "1단랙")
	private BigDecimal f1RackBox;

	/* 2단랙 */
	@Schema(description = "2단랙")
	private BigDecimal f2RackBox;

	/* 비고 */
	@Schema(description = "비고")
	private String rmk;

	/* 사용유무 */
	@Schema(description = "사용유무")
	private String useYn;

	/* 등록자 */
	@Schema(description = "등록자")
	private String addwho;

	/* 등록일시 */
	@Schema(description = "등록일시")
	private String adddate;

	/* 수정자 */
	@Schema(description = "수정자")
	private String editwho;

	/* 수정일시 */
	@Schema(description = "수정일시")
	private String editdate;

    /** 커스텀 엑스트라 체크박스 */
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn = "N";

}
