package cjfw.wms.ib.dto;

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
 * @date : 2025.09.25
 * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_정산관리_탭 Response DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.25 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_정산관리_탭 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IbTirdPartyMastResT4Dto extends CommonProcedureDto {
	
	/* 01. 마감현황 */
	@Schema(description = "01. 마감현황")
	private String allocCarStatus;

	/* 02. 배송일자 */
	@Schema(description = "02. 배송일자")
	private String deliverydate;

	/* 03. 협력사코드 */
	@Schema(description = "03. 협력사코드")
	private String fromcustkey;

	/* 04. 협력사명 */
	@Schema(description = "04. 협력사명")
	private String fromcustnm;

	/* 05. 일배구분 */
	@Schema(description = "05. 일배구분")
	private String channel;

	/* 06. 물류센터 */
	@Schema(description = "06. 물류센터")
	private String dccode;

	/* 06. 물류센터명 */
	@Schema(description = "06. 물류센터명")
	private String dcname;

	/* 07. 라벨건수 */
	@Schema(description = "07. 라벨건수")
	private BigDecimal barCnt;

	/* 08. 검수건수 */
	@Schema(description = "08. 검수건수")
	private BigDecimal scanCnt;

	/* 09. 강제확정(3PL) */
	@Schema(description = "09. 강제확정(3PL)")
	private BigDecimal cfPl3;

	/* 10. 강제확정(협력사) */
	@Schema(description = "10. 강제확정(협력사)")
	private BigDecimal cfCust;

	/* 11. 미확정 */
	@Schema(description = "11. 미확정")
	private BigDecimal ncf;

	/* 12. 미입고 */
	@Schema(description = "12. 미입고")
	private BigDecimal ndpCnt;

	/* 13. 검수 */
	@Schema(description = "13. 검수")
	private BigDecimal other01Cnt;

	/* 14. 미검수 */
	@Schema(description = "14. 미검수")
	private BigDecimal other02Cnt;

	/* 15. 광역일배 */
	@Schema(description = "15. 광역일배")
	private BigDecimal other03Cnt;

	/* 16. FW */
	@Schema(description = "16. FW")
	private String ibcffw;
	
	/* 17. IBCFFWDATE */
	@Schema(description = "17. IBCFFWDATE")
	private String ibcffwdate;

	/* 18. 3PL */
	@Schema(description = "18. 3PL")
	private String ibcf3pl;
	
	/* 19. IBCF3PLDATE */
	@Schema(description = "18. IBCF3PLDATE")
	private String ibcf3pldate;

	/* 20. 물류비 */
	@Schema(description = "20. 물류비")
	private BigDecimal logiprice;

	/* 21. 확정유무 */
	@Schema(description = "21. 확정유무")
	private String ibcfyn;

	/* 22. 확정시간 */
	@Schema(description = "22. 확정시간")
	private String ifcfdate;

	/* DELIVERYTYPE_PL3 */
	@Schema(description = "DELIVERYTYPE_PL3")
	private String deliverytypePl3;

	/* OTHER01_PRICE */
	@Schema(description = "OTHER01_PRICE")
	private String other01Price;

	/* OTHER02_PRICE */
	@Schema(description = "OTHER02_PRICE")
	private String other02Price;

	/* OTHER03_PRICE */
	@Schema(description = "OTHER03_PRICE")
	private String other03Price;
	
	/* 중분류 */
	@Schema(description = "중분류")
	private String labelClYn;

	/** 도착거래처코드 */
	@Schema(description = "도착거래처코드")
	private String toCustkey;

	/** ORD_NO */
	@Schema(description = "ORD_NO")
	private String ordNo;

	/** 도착거래처명 */
	@Schema(description = "도착거래처명")
	private String toCustname;
	
    /** 커스텀 엑스트라 체크박스 */
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn = "N";

}
