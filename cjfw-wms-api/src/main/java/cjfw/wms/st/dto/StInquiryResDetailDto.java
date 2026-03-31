package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.11.02
 * @description : 재고 > 재고조사 > 재고조사등록 Request DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.02 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고 > 재고조사 > 재고조사등록 Detail Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StInquiryResDetailDto extends CommonProcedureDto {
	
	/** 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;

	/** 물류센터명 */
	@Schema(description = "물류센터명")
	private String dcname;

	/** 창고 */
	@Schema(description = "창고")
	private String organize;
	
	/** 창고명 */
	@Schema(description = "창고명")
	private String organizename;

	/** 진행상태 */
	@Schema(description = "진행상태")
	private String status;

	/** 상품코드 */
	@Schema(description = "상품코드")
	private String sku;

	/** 상품명칭 */
	@Schema(description = "상품명칭")
	private String skuname;

	/** 창고구분 */
	@Schema(description = "창고구분")
	private String wharea;

	/** 창고층 */
	@Schema(description = "창고층")
	private String whareafloor;

	/** 피킹존 */
	@Schema(description = "피킹존")
	private String zone;

	/** ZONE명 */
	@Schema(description = "ZONE명")
	private String zonename;

	/** 로케이션 */
	@Schema(description = "로케이션")
	private String loc;

	/** 지시수량 */
	@Schema(description = "지시수량")
	private BigDecimal orderqty;

	/** 단위 */
	@Schema(description = "단위")
	private String uom;

	/** 총조사수량 */
	@Schema(description = "총조사수량")
	private BigDecimal scanqtyA;

	/** BOX */
	@Schema(description = "BOX")
	private BigDecimal box;

	/** EA */
	@Schema(description = "EA")
	private BigDecimal ea;

	/** 재고속성 */
	@Schema(description = "재고속성")
	private String stockgrade;

	/** 재고속성 */
	@Schema(description = "재고속성")
	private String stockgradeNm;

	/** 제조일자 */
	@Schema(description = "제조일자")
	private String manufacturedt;

	/** 소비일자 */
	@Schema(description = "소비일자")
	private String expiredt;

	/** 유통기간(잔여/전체) */
	@Schema(description = "유통기간(잔여/전체)")
	private String durationTerm;

	/** 이력번호 */
	@Schema(description = "이력번호")
	private String serialno;

	/** BL번호 */
	@Schema(description = "BL번호")
	private String convserialno;

	/** 재고ID */
	@Schema(description = "재고ID")
	private String stockid;

	/** 등록일자 */
	@Schema(description = "등록일자")
	private String editdate;

	/** 생성인 */
	@Schema(description = "생성인")
	private String editwho;

	/** AREA */
	@Schema(description = "AREA")
	private String area;

	/** INQUIRYDT */
	@Schema(description = "INQUIRYDT")
	private String inquirydt;

	/** INQUIRYNO */
	@Schema(description = "INQUIRYNO")
	private String inquiryno;

	/** SCANQTY_BOX */
	@Schema(description = "SCANQTY_BOX")
	private BigDecimal scanqtyBox;

	/** LOTTABLE01 */
	@Schema(description = "LOTTABLE01")
	private String lottable01;

	/** USERNAME */
	@Schema(description = "USERNAME")
	private String username;

	/** DURATIONTYPE */
	@Schema(description = "DURATIONTYPE")
	private String durationtype;

	/** DURATION */
	@Schema(description = "DURATION")
	private String duration;

	/** STORERKEY */
	@Schema(description = "STORERKEY")
	private String storerkey;
	
	/** TO_LOT */
	@Schema(description = "TO_LOT")
	private String toLot;

    /*** 커스텀 엑스트라 체크박스 */
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn = "N";
    
	/** 재고조사 별칭 */
	@Schema(description = "재고조사 별칭")
	private String inquiryName;
	
	/** 회차 */
	@Schema(description = "회차")
	private BigDecimal priority;
	
	/** lot */
	@Schema(description = "lot")
	private String lot;
	
	/** lottype */
	@Schema(description = "lottype")
	private String lottype;

	/** 박스입수 */
	@Schema(description = "박스입수")
	private BigDecimal qtyperbox;

	/** 모바일추가여부 */
	@Schema(description = "모바일추가여부")
	private String mobileAddYn;
	
	//////////////// 2025.12.12 추가	 //////////////////
	/** 저장조건 */
	@Schema(description = "저장조건")
	private String storagetype;
	
	/** 가용수량 */
	@Schema(description = "가용수량")
	private BigDecimal stdqty;

	/** 보류수량 */
	@Schema(description = "보류수량")
	private BigDecimal s1qty;

	/** 폐기수량 */
	@Schema(description = "폐기수량")
	private BigDecimal s3qty;
	
	/** 전산재고(속성) 합계 */
	@Schema(description = "전산재고(속성) 합계")
	private String storagetypeSum;
	
	/** 지시수량 합계 */
	@Schema(description = "지시수량 합계")
	private String taskSum;
	
	/** 조사수량 합계 */
	@Schema(description = "조사수량 합계")
	private BigDecimal inquiryQtySum;
	
	/** 비고 */
	@Schema(description = "비고")
	private String rmk;
	
	/** taskbox */
	@Schema(description = "taskbox")
	private String taskbox;
	
	/** taskea */
	@Schema(description = "taskea")
	private String taskea;
	
	/** 수정자ID */
	@Schema(description = "수정자ID")
	private String updId;
	
	/** 마지막 회차 */
	@Schema(description = "Last Priority")
	private BigDecimal lastpriority;
	
	/** 조사형태 */
	@Schema(description = "조사형태")
	private String inquirytype;    
	
	/** 지시여부 */
	@Schema(description = "지시여부")
	private String instructionYn;

	/** 커밋여부 */
	@Schema(description = "커밋여부")
	private String commitYn;
}
