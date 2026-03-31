package cjfw.wms.dp.dto;

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
 * @date : 2025.12.01
 * @description : 입고 > 입고현황 > 입고 예정진행 현황(입차시간) Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.01 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "입고 > 입고현황 > 입고 예정진행 현황(입차시간) Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpInplanTimeResDto extends CommonProcedureDto {
	
	/* 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;

	/* 물류센터명 */
	@Schema(description = "물류센터명")
	private String dcname;

	/* 입고일자 */
	@Schema(description = "입고일자")
	private String deliverydate;

	/* FROM_입고예정시간 */
	@Schema(description = "FROM_입고예정시간")
	private String fromIntime;

	/* TO_입고예정시간 */
	@Schema(description = "TO_입고예정시간")
	private String toIntime;

	/* 협력사 */
	@Schema(description = "협력사")
	private String fromCustkey;
	
	/* 협력사타입 */
	@Schema(description = "협력사타입")
	private String fromCusttype;

	/* 협력사명 */
	@Schema(description = "협력사명")
	private String custname;

	/* 입고전표번호 */
	@Schema(description = "입고전표번호")
	private String docno;

	/* QR 입문시간 */
	@Schema(description = "QR 입문시간")
	private String realIntime;

	/* 냉장온도 */
	@Schema(description = "냉장온도")
	private BigDecimal temperature1;

	/* 냉동온도 */
	@Schema(description = "냉동온도")
	private BigDecimal temperature2;

	/* 하차검수시간 */
	@Schema(description = "하차검수시간")
	private String deliverytime;

	/* 저장유무 */
	@Schema(description = "저장유무")
	private String channel;

	/* 저장유무명 */
	@Schema(description = "저장유무명")
	private String channelName;

	/* diffMin */
	@Schema(description = "diffMin")
	private String diffMin;
	
	/* diffHhmi */
	@Schema(description = "diffHhmi")
	private String diffHhmi;
	
	/* DIFF_FLAG */
	@Schema(description = "diffFlag")
	private String diffFlag;
	
	/* comply30min */
	@Schema(description = "comply30min")
	private String comply30min;
	
	/* comply1hour */
	@Schema(description = "comply1hour")
	private String comply1hour;
}
