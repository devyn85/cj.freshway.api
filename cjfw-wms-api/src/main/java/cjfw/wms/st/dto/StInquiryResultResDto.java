package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.08.25
 * @description : 조사지시현황 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "조사지시현황 Master Response DTO")
@Data
public class StInquiryResultResDto extends CommonProcedureDto {
	/** 센터코드 */
	@Schema(description = "센터코드")
	private String dccode;

	/** 화주코드 */
	@Schema(description = "화주코드")
	private String storerkey;

	/** 조직코드 */
	@Schema(description = "조직코드")
	private String organize;

	/** 조사일자 */
	@Schema(description = "조사일자")
	private String inquirydt;

	/** 조사번호 */
	@Schema(description = "조사번호")
	private String inquiryno;
	
	/** 조사번호New */
	@Schema(description = "조사번호New")
	private String inquirynoNew;	


	/** 상태 */
	@Schema(description = "상태")
	private String status;

	/** 창고구역 */
	@Schema(description = "창고구역")
	private String wharea;
	
	/** 지역코드 */
	@Schema(description = "지역코드")
	private String area;	
	

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

	/** 상품코드 */
	@Schema(description = "상품코드")
	private String sku;

	/** 상품명 */
	@Schema(description = "상품명")
	private String skuname;
	
	/** 재고ID */
	@Schema(description = "재고ID")
	private String stockid;

	/** 단위 */
	@Schema(description = "단위")
	private String uom;

	/** 재고수량 */
	@Schema(description = "재고수량")
	private BigDecimal stockqty;

	/** 조사수량 */
	@Schema(description = "조사수량")
	private BigDecimal inquiryqty;

	/** 차이수량 */
	@Schema(description = "차이수량")
	private BigDecimal diffqty;

	/** 사유코드 */
	@Schema(description = "사유코드")
	private String reasoncode;

	/** 사유메시지 */
	@Schema(description = "사유메시지")
	private String reasonmsg;

	/** 조사번호 */
	@Schema(description = "조사번호")
	private String inquiryno2;

	/** 재고등급 */
	@Schema(description = "재고등급")
	private String stockgrade;

	/** 유통기한 */
	@Schema(description = "유통기한")
	private String lottable01;

	/** 유통기한정보 */
	@Schema(description = "유통기한정보")
	private String durationTerm;

	/** 시리얼번호 */
	@Schema(description = "시리얼번호")
	private String serialno;

	/** 유통기간 */
	@Schema(description = "유통기간")
	private String duration;

	/** 유통기간타입 */
	@Schema(description = "유통기간타입")
	private String durationtype;

	/** 처리여부 */
	@Schema(description = "처리여부")
	private String commityn;
	
	/** 조사별칭 */
	@Schema(description = "조사별칭")
	private String inquiryName;
	
	/** 조사별칭New */
	@Schema(description = "조사별칭New")
	private String inquiryNameNew;

	/** 상태명 */
	@Schema(description = "상태명")
	private String statusnm;
	
	/** 차이수량(합계) */
	@Schema(description = "차이수량(합계)")
	private BigDecimal totaldiffqty;
	
	/** 차이금액(합계) */
	@Schema(description = "차이금액(합계)")
	private BigDecimal totaldiffamt;
	
	/** 최종차수 */
	@Schema(description = "최종차수")
	private BigDecimal lastpriority;	

	/** 1차수량 */
	@Schema(description = "1차수량")
	private BigDecimal priority01;

	/** 1차차이 */
	@Schema(description = "1차차이")
	private BigDecimal diffqty01;

	/** 2차수량 */
	@Schema(description = "2차수량")
	private BigDecimal priority02;

	/** 2차차이 */
	@Schema(description = "2차차이")
	private BigDecimal diffqty02;

	/** 3차수량 */
	@Schema(description = "3차수량")
	private BigDecimal priority03;

	/** 3차차이 */
	@Schema(description = "3차차이")
	private BigDecimal diffqty03;

	/** 4차수량 */
	@Schema(description = "4차수량")
	private BigDecimal priority04;

	/** 4차차이 */
	@Schema(description = "4차차이")
	private BigDecimal diffqty04;

	/** 5차수량 */
	@Schema(description = "5차수량")
	private BigDecimal priority05;

	/** 5차차이 */
	@Schema(description = "5차차이")
	private BigDecimal diffqty05;

	/** 6차수량 */
	@Schema(description = "6차수량")
	private BigDecimal priority06;

	/** 6차차이 */
	@Schema(description = "6차차이")
	private BigDecimal diffqty06;

	/** 7차수량 */
	@Schema(description = "7차수량")
	private BigDecimal priority07;

	/** 7차차이 */
	@Schema(description = "7차차이")
	private BigDecimal diffqty07;

	/** 8차수량 */
	@Schema(description = "8차수량")
	private BigDecimal priority08;

	/** 8차차이 */
	@Schema(description = "8차차이")
	private BigDecimal diffqty08;

	/** 9차수량 */
	@Schema(description = "9차수량")
	private BigDecimal priority09;

	/** 9차차이 */
	@Schema(description = "9차차이")
	private BigDecimal diffqty09;

	/** 10차수량 */
	@Schema(description = "10차수량")
	private BigDecimal priority10;

	/** 10차차이 */
	@Schema(description = "10차차이")
	private BigDecimal diffqty10;

    /** 실사구분(0:소비기한,1:재고조사) */
    @Schema(description = "(0:소비기한,1:재고조사)")
    private String lottype;
    
    /** LOT */
    @Schema(description = "LOT")
    private String lot;
	
    /** 재고등급명 */
    @Schema(description = "재고등급명")
    private String stockgradenm;
    
    /** 날짜1 */
    @Schema(description = "날짜1")
    private String dt1;

    /** 날짜1 */
    @Schema(description = "날짜2")
    private String dt2;
    
    /** 주문수량 */
    @Schema(description = "주문수량")
    private BigDecimal orderqty;
    
    /** 차수 */
    @Schema(description = "차수")
    private BigDecimal priority;
    
    /** 차수New */
    @Schema(description = "차수New")
    private BigDecimal priorityNew;
    
    /** 커스텀 엑스트라 체크박스 */
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn = "N";

    /** 재고금액 */
    @Schema(description = "재고금액")
    private BigDecimal stockamt;
    
    /** 요청구분(1:로케이션별, 2:상품별) */
    @Schema(description = "요청구분(1:로케이션별, 2:상품별)")
    private String reqFlag;
    
	/** 조사형태 */
	@Schema(description = "조사형태")
	private String inquirytype;    
	
	/** 지시여부 */
	@Schema(description = "지시여부")
	private String instructionYn; 	
	
	/** 모바일추가여부 */
	@Schema(description = "모바일추가여부")
	private String mobileAddYn;

    /** SAP 단가  */
    @Schema(description = "SAP 단가 ")
    private BigDecimal purchaseprice;
    
}
