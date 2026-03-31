package cjfw.wms.dp.dto;

import java.math.BigDecimal;
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
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.12.01
 * @description : 입고 > 입고현황 > 입고 예정진행 현황(입차시간) Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.01 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "입고 > 입고현황 > 입고 예정진행 현황(입차시간) Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpInplanTimeReqDto extends CommonProcedureDto {

	/* 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;
	
	/* 저장유무 */
	@Schema(description = "저장유무")
	private String channel;
	
	/* 협력사코드 */
	@Schema(description = "협력사코드")
	private String custkey;
	
    /* 협력사코드-멀티 */
	@MultiSearch
    @Schema(description = "협력사코드-멀티")
    private List<List<String>> custkeyMulti;
	
	/* 예정입고 차이 시간 */
	@Schema(description = "예정입고 차이 시간")
	private BigDecimal diffHour;
	
	/* 예정입고 차이 시간 */
	@Schema(description = "예정입고 차이 시간 From")
	private BigDecimal diffHourFrom;
	
	/* 예정입고 차이 시간 */
	@Schema(description = "예정입고 차이 시간 To")
	private BigDecimal diffHourTo;
	
	/* 준수여부 */
	@Schema(description = "준수여부")
	private String complyYn;

	/* 입고일자 From */
	@Schema(description = "입고일자 From")
	private String deliverydateFrom;

	/* 입고일자 To */
	@Schema(description = "입고일자 To")
	private String deliverydateTo;
	
	/* 입고전표번호 */
	@Schema(description = "입고전표번호")
	private String docno;

	
}
