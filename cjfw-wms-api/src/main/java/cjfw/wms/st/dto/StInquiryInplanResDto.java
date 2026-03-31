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
 * @date : 2025.10.28
 * @description : 재고 > 재고조사 > 재고 실사 지시 Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.28 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고 > 재고조사 > 재고 실사 지시 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StInquiryInplanResDto extends CommonProcedureDto {

	/* 데이터번호 */
	@Schema(description = "데이터번호")
	private String serialkey;
	
	/* 센터코드 */
	@Schema(description = "센터코드")
	private String dccode;

	/* 물류센터명 */
	@Schema(description = "물류센터명")
	private String dcname;

	/* 창고 */
	@Schema(description = "창고")
	private String organize;

	/* 창고명 */
	@Schema(description = "창고명")
	private String organizename;

	/* 상품코드 */
	@Schema(description = "상품코드")
	private String sku;

	/* 상품명 */
	@Schema(description = "상품명")
	private String skuname;

	/* 저장조건 */
	@Schema(description = "저장조건")
	private String storagetype;
	
	/* 저장조건 */
	@Schema(description = "저장조건")
	private String storagetypeNm;

	/* 창고층 */
	@Schema(description = "창고층")
	private BigDecimal whareafloor;

	/* 피킹존 */
	@Schema(description = "피킹존")
	private String zone;

	/* 로케이션 종류 */
	@Schema(description = "로케이션 종류")
	private String loccategory;

	/* 로케이션 */
	@Schema(description = "로케이션")
	private String loc;

	/* 수량 */
	@Schema(description = "수량")
	private BigDecimal qty;

	/* 단위 */
	@Schema(description = "단위")
	private String uom;

	/* 박스입수 */
	@Schema(description = "박스입수")
	private BigDecimal qtyperbox;

	/* BOX */
	@Schema(description = "BOX")
	private BigDecimal box;

	/* EA */
	@Schema(description = "EA")
	private BigDecimal ea;

	/* 재고속성 */
	@Schema(description = "재고속성")
	private String stockgradeNm;

	/* 재고위치 */
	@Schema(description = "재고위치")
	private String stocktypeNm;
	
    /* 유통기간임박혀부 */
    @Schema(description = "유통기간임박혀부")
    private String neardurationyn;

	/* 제조일자 */
	@Schema(description = "제조일자")
	private String manufacturedt;

	/* 소비일자 */
	@Schema(description = "소비일자")
	private String expiredt;

    /* 유통기간(잔여/전체) */
    @Schema(description = "유통기간(잔여/전체)")
    private String durationTerm;
    
	/* 이력번호 */
	@Schema(description = "이력번호")
	private String serialno;

	/* BL번호 */
	@Schema(description = "BL번호")
	private String convserialno;
	
    /* lottable01 */
    @Schema(description = "lottable01")
    private String lottable01;

    /* duration */
    @Schema(description = "duration")
    private String duration;
    
    /* durationtype */
    @Schema(description = "durationtype")
    private String durationtype;
    
    /* inquiryName */
    @Schema(description = "inquiryName")
    private String inquiryName;
    
    /* inquiryno */
    @Schema(description = "inquiryno")
    private String inquiryno;

    /** 커스텀 엑스트라 체크박스 */
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn = "N";
    
    /////////////////////////////////////////////////////////////////////////////////////////////// 저장시 필요한 컬럼
    /* AREA */
    @Schema(description = "AREA")
    private String area;

    /* LOT */
    @Schema(description = "LOT")
    private String lot;

    /* ORDERQTY */
    @Schema(description = "ORDERQTY")
    private BigDecimal orderqty;

    /* STOCKQTY */
    @Schema(description = "STOCKQTY")
    private BigDecimal stockqty;

    /* LOTTYPE */
    @Schema(description = "LOTTYPE")
    private String lottype;

    /* STOCKID */
    @Schema(description = "STOCKID")
    private String stockid;

    /* STOCKGRADE */
    @Schema(description = "STOCKGRADE")
    private String stockgrade;

    /** 소비기한잔여율 */
    @Schema(description = "소비기한잔여율")
    private BigDecimal usebydatefreert;

}
