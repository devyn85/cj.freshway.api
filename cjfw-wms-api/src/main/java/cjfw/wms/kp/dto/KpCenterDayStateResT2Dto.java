package cjfw.wms.kp.dto;

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
 * @date : 2025.09.03
 * @description : 지표 > 센터 운영 > 출고 유형별 물동 현황 수송물동_탭 Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.03 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "지표 > 센터 운영 > 출고 유형별 물동 현황 수송물동_탭 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpCenterDayStateResT2Dto extends CommonProcedureDto {

	/* 01. 물류센터 */
	@Schema(description = "01. 물류센터")
	private String dccode;
	
	/* 02. 물류센터명 * */
	@Schema(description = "02. 물류센터명 *")
	private String dcname;

	/* 03. 구간코드 */
	@Schema(description = "03. 구간코드")
	private String course;

	/* 04. 구간 * */
	@Schema(description = "04. 구간 *")
	private String coursenm;

	/* 05. 저장유무코드 */
	@Schema(description = "05. 저장유무코드")
	private String channel;

	/* 06. 저장유무 * */
	@Schema(description = "06. 저장유무 *")
	private String channelnm;

	/* 07. 지표,단위 코드 */
	@Schema(description = "07. 지표,단위 코드")
	private String title;

	/* 08. 지표 * */
	@Schema(description = "08. 지표 *")
	private String indicator;

	/* 09. 단위 * */
	@Schema(description = "09. 단위 *")
	private String uom;

	/* 10. 전월 일평균(월 단위) * */
	@Schema(description = "10. 전월 일평균(월 단위) *")
	private BigDecimal premonth;

	/* 11. 당월 일평균(월 단위)* */
	@Schema(description = "11. 당월 일평균(월 단위)*")
	private BigDecimal month;

	/* 12. 증감량(월 단위) * */
	@Schema(description = "12. 증감량(월 단위) *")
	private BigDecimal monthincdec;

	/* 13. 증감율(월 단위) * */
	@Schema(description = "13. 증감율(월 단위) *")
	private BigDecimal monthincdecper;

	/* 14. 전일(일 단위) * */
	@Schema(description = "14. 전일(일 단위) *")
	private BigDecimal preday;

	/* 15. 당일(일 단위) * */
	@Schema(description = "15. 당일(일 단위) *")
	private BigDecimal day;

	/* 16. 증감량(일 단위) * */
	@Schema(description = "16. 증감량(일 단위) *")
	private BigDecimal dayincdec;

	/* 17. 증감율(일 단위) * */
	@Schema(description = "17. 증감율(일 단위) *")
	private BigDecimal dayincdecper;

}
