package cjfw.wms.st.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.08.25
 * @description : 재고폐기요청/처리 - 처리결과 Request DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고폐기요청/처리 - 처리결과 Response DTO")
@Data
public class StDisuseRequestCenterResResultDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";


    /** 체크여부 */
    @Schema(description = "체크여부")
    private String checkyn;

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;

    /** 화주코드 */
    @Schema(description = "화주코드")
    private String storerkey;

    /** 창고 */
    @Schema(description = "창고")
    private String organize;

    /** 구역 */
    @Schema(description = "구역")
    private String area;

    /** 재고유형 */
    @Schema(description = "재고유형")
    private String stocktype;

    /** 재고유형명 */
    @Schema(description = "재고유형명")
    private String stocktypenm;

    /** 재고등급 */
    @Schema(description = "재고등급")
    private String stockgrade;

    /** 재고등급명 */
    @Schema(description = "재고등급명")
    private String stockgradename;
    
    /** 폐기유형명 */
    @Schema(description = "폐기유형명")
    private String disusetypename;

    /** 존 */
    @Schema(description = "존")
    private String zone;

    /** 로케이션 */
    @Schema(description = "로케이션")
    private String loc;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명")
    private String skuname;

    /** 단위 */
    @Schema(description = "단위")
    private String uom;

    /** 수량 */
    @Schema(description = "수량")
    private BigDecimal qty;

    /** 가용수량 */
    @Schema(description = "가용수량")
    private String openqty;

    /** 할당수량 */
    @Schema(description = "할당수량")
    private BigDecimal qtyallocated;

    /** 피킹수량 */
    @Schema(description = "피킹수량")
    private BigDecimal qtypicked;

    /** 조정수량 */
    @Schema(description = "조정수량")
    private BigDecimal tranqty;

    /** 폐기유형 */
    @Schema(description = "폐기유형")
    private String disusetype;

    /** 발생사유코드 */
    @Schema(description = "발생사유코드")
    private String reasoncode;
    
    /** 발생사유코드명 */
    @Schema(description = "발생사유코드명")
    private String reasoncodename;

    /** 귀책 */
    @Schema(description = "귀책")
    private String imputetype;

    /** 물류비귀책여부 */
    @Schema(description = "물류비귀책여부")
    private String processmain;

    /** 귀속부서 */
    @Schema(description = "귀속부서")
    private String costcd;
    
    
    /** 귀속부서명 */
    @Schema(description = "귀속부서명")
    private String costcdname;

    /** 거래처코드 */
    @Schema(description = "거래처코드")
    private String custkey;
    
    /** 거래처명 */
    @Schema(description = "거래처명")
    private String custname;

    /** 유통기한 */
    @Schema(description = "유통기한")
    private String lottable01;

    /** 로트 */
    @Schema(description = "로트")
    private String lot;

    /** 재고ID */
    @Schema(description = "재고ID")
    private String stockid;

    /** 처리플래그 */
    @Schema(description = "처리플래그")
    private String processflag;

    /** 처리메시지 */
    @Schema(description = "처리메시지")
    private String processmsg;

    /** 공장명 */
    @Schema(description = "공장명")
    private String factoryname;

    /** B/L 번호 */
    @Schema(description = "B/L 번호")
    private String convserialno;

    /** 도축일자 */
    @Schema(description = "도축일자")
    private String butcherydt;

    /** 계약거래처 */
    @Schema(description = "계약거래처")
    private String contractcompany;

    /** 계약거래처명 */
    @Schema(description = "계약거래처명")
    private String contractcompanyname;

    /** 유효일자 */
    @Schema(description = "유효일자")
    private String fromvaliddt;

    /** 유효일자2 */
    @Schema(description = "유효일자2")
    private String tovaliddt;

    /** 계약유형 */
    @Schema(description = "계약유형")
    private String contracttype;

    /** 바코드 */
    @Schema(description = "바코드")
    private String barcode;

    /** 평균중량 */
    @Schema(description = "평균중량")
    private BigDecimal avgweight;

    /** 환산박스 */
    @Schema(description = "환산박스")
    private String calbox;

    /** 실박스예정 */
    @Schema(description = "실박스예정")
    private String realorderbox;

    /** 실박스확정 */
    @Schema(description = "실박스확정")
    private String realcfmbox;

    /** 작업박스 */
    @Schema(description = "작업박스")
    private String tranbox;

    /** 제조일자 */
    @Schema(description = "제조일자")
    private String manufacturedt;    
    
    /** 소비일자 */
    @Schema(description = "소비일자")
    private String expiredt;    
    
    /** 소비기한잔여율 */
    @Schema(description = "소비기한잔여율")
    private BigDecimal usebydatefreert;    
    
	/** 비고 */
	@Schema(description = "비고")
	private String rmk;
	
    @Schema(description = "변경귀속부서명")
    private String toRespDeptNm;

    @Schema(description = "변경거래처명")
    private String chgCustname;
    
	
	@Schema(description = "변경귀책주체코드")
	private String toRespPartyCd;

	@Schema(description = "변경귀속부서코드")
	private String toRespDeptCd;

	@Schema(description = "변경거래처코드")
	private String chgCustkey;	
}
