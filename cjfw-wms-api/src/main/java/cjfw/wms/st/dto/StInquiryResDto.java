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
 * @date : 2025.11.02
 * @description : 재고 > 재고조사 > 재고조사등록 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.02 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고 > 재고조사 > 재고조사등록 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StInquiryResDto extends CommonProcedureDto {
	
	/* 센터코드 */
	@Schema(description = "센터코드")
	private String dccode;

	/* 물류센터 */
	@Schema(description = "물류센터")
	private String dcname;
	
	/* 창고 */
	@Schema(description = "창고")
	private String organize;
	
	/* 창고명 */
	@Schema(description = "창고명")
	private String organizename;

	/* 진행상태 */
	@Schema(description = "진행상태")
	private String status;
	
	/* 진행상태 */
	@Schema(description = "진행상태")
	private String statusNm;

	/* 조사일자 */
	@Schema(description = "조사일자")
	private String inquirydt;

	/* 조사번호 */
	@Schema(description = "조사번호")
	private String inquiryno;

	/* 재고조사 별칭 */
	@Schema(description = "재고조사 별칭")
	private String inquiryName;

	/* 회차 */
	@Schema(description = "회차")
	private String priority;

	/* 로케이션수 */
	@Schema(description = "로케이션수")
	private BigDecimal loccnt;

	/* 상품수 */
	@Schema(description = "상품수")
	private BigDecimal skucnt;

	/* 원지시번호 */
	@Schema(description = "원지시번호")
	private String sourcekey;

	/* 메모 */
	@Schema(description = "메모")
	private String memo;

	/* 등록일자 */
	@Schema(description = "등록일자")
	private String adddate;

	/* 생성인 */
	@Schema(description = "생성인")
	private String addwho;

	/* 사용자이름 */
	@Schema(description = "사용자이름")
	private String username;

	/* STORERKEY */
	@Schema(description = "STORERKEY")
	private String storerkey;

	/* AREA */
	@Schema(description = "AREA")
	private String area;

	/* LOTTYPE */
	@Schema(description = "LOTTYPE")
	private String lottype;
	
	/* 등록자ID */
	@Schema(description = "등록자ID")
	private String updId;

		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/** 지시여부 */
	@Schema(description = "지시여부")
	private String instructionYn;
	
	/** 조사형태 */
	@Schema(description = "조사형태")
	private String inquirytype;    
	
	/** 모바일추가여부 */
	@Schema(description = "모바일추가여부")
	private String mobileAddYn;   	
}
