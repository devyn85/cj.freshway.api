package cjfw.wms.st.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonTriggerDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.08.25
 * @description : 재고조사결과처리 Request Entity Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "재고조사결과처리 Entity") 
public class StInquiryMoveResSkuEntity extends CommonTriggerDto {
    /** 체크여부 */
    @Schema(description = "체크여부")
    private String checkyn;

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;

    /** 화주코드 */
    @Schema(description = "화주코드")
    private String storerkey;

    /** 조직 */
    @Schema(description = "조직")
    private String organize;

    /** 구역 */
    @Schema(description = "구역")
    private String area;

    /** 조사일자 */
    @Schema(description = "조사일자")
    private String inquirydt;

    /** 조사번호 */
    @Schema(description = "조사번호")
    private String inquiryno;

    /** 상태 */
    @Schema(description = "상태")
    private String status;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명")
    private String skuname;

    /** 창고구역 */
    @Schema(description = "창고구역")
    private String wharea;

    /** 창고층 */
    @Schema(description = "창고층")
    private String whareafloor;

    /** 존 */
    @Schema(description = "존")
    private String zone;

    /** 존명 */
    @Schema(description = "존명")
    private String zonename;

    /** 로케이션 */
    @Schema(description = "로케이션")
    private String loc;

    /** 로트 */
    @Schema(description = "로트")
    private String lot;

    /** 지시수량 */
    @Schema(description = "지시수량")
    private BigDecimal orderqty;

    /** 단위 */
    @Schema(description = "단위")
    private String uom;

    /** 박스스캔수량 */
    @Schema(description = "박스스캔수량")
    private BigDecimal scanqtyBox;

    /** 낱개스캔수량 */
    @Schema(description = "낱개스캔수량")
    private BigDecimal scanqtyA;

    /** 처리수량 */
    @Schema(description = "처리수량")
    private BigDecimal tranqty;

    /** 등급 */
    @Schema(description = "등급")
    private String stockgrade;
    
    /** 등급명 */
    @Schema(description = "등급명")
    private String stockgradenm;

    /** LOT정보 */
    @Schema(description = "LOT정보")
    private String lottable01;

    /** 유효기간/기간 */
    @Schema(description = "유효기간/기간")
    private String durationTerm;

    /** 시리얼번호 */
    @Schema(description = "시리얼번호")
    private String serialno;

    /** 수정일자 */
    @Schema(description = "수정일자")
    private String editdate;

    /** 수정자 */
    @Schema(description = "수정자")
    private String editwho;

    /** 사용자명 */
    @Schema(description = "사용자명")
    private String username;

    /** 바코드 */
    @Schema(description = "바코드")
    private String stockid;

    /** 기간유형 */
    @Schema(description = "기간유형")
    private String durationtype;

    /** 기간 */
    @Schema(description = "기간")
    private String duration;

    /** 출발로케이션 */
    @Schema(description = "출발로케이션")
    private String fromLoc;
	
	/** 조사별칭 */
	@Schema(description = "조사별칭")
	private String inquiryName;

	/** 상태명 */
	@Schema(description = "상태명")
	private String statusnm;
	
	/** Commit status name */
	@Schema(description = "결과처리여부명")
	private String commitYnNm;	
	
	/** 순번 */
	@Schema(description = "순번")
	private BigDecimal priority;

    @Schema(description = "출고로트")
    private String fromLot;

    @Schema(description = "출고재고ID")
    private String fromStockid;

    @Schema(description = "출고재고유형")
    private String fromStocktype;

    @Schema(description = "출고재고유형명")
    private String fromStocktypedesc;
    
    @Schema(description = "출고LOT속성1")
    private String fromLottable01;

    @Schema(description = "출고LOT속성2")
    private String fromLottable02;

    @Schema(description = "출고LOT속성3")
    private String fromLottable03;

    @Schema(description = "출고LOT속성4")
    private String fromLottable04;

    @Schema(description = "출고LOT속성5")
    private String fromLottable05;
    
    /** 제조일자 */
    @Schema(description = "제조일자")
    private String manufacturedt;    
    
    /** 소비일자 */
    @Schema(description = "소비일자")
    private String expiredt;    
    
    @Schema(description = "소비기한잔여율")
    private BigDecimal usebydatefreert; 
    
    /** 사유코드 */
    @Schema(description = "사유코드")
    private String reasoncode;

    /** 사유메시지 */
    @Schema(description = "사유메시지")
    private String reasonmsg;
    
    /** 재고등급 */
    @Schema(description = "재고등급")
    private String fromStockgrade;
    
    /** 재고등급명 */
    @Schema(description = "재고등급명")
    private String stockgradename;
    
    /** 변경소비기한 */
    @Schema(description = "변경소비기한")
    private String toLot;
    
    /** 결과처리여부 */
    @Schema(description = "결과처리여부")
    private String commitYn;
    
    /** 최종차수 */
    @Schema(description = "최종차수")
    private BigDecimal lastpriority;
    
    /** 커스텀 엑스트라 체크박스 */
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn = "N";
    
	/** 조사형태 */
	@Schema(description = "조사형태")
	private String inquirytype;    
	
	/** 모바일추가여부 */
	@Schema(description = "모바일추가여부")
	private String mobileAddYn;    
	
	/** 이동가능여부 */
	@Schema(description = "이동가능여부")
	private String canMoveYn;
	
    /** 이동가능수량 */
    @Schema(description = "이동가능수량")
    private BigDecimal posbqty;
    
	/** 지시여부 */
	@Schema(description = "지시여부")
	private String instructionYn;    
	
	/** caseFlag(CASE1 ~ CASE2) */
	@Schema(description = "caseFlag")
	private String caseFlag;
	
    /** 소비기한처리수량 */
    @Schema(description = "소비기한처리수량")
    private BigDecimal lottableTranqty;	
  
}
