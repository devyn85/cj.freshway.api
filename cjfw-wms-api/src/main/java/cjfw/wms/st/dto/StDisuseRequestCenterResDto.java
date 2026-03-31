package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author :  sss (kduimux@cj.net)
 * @date : 2025.07.02
 * @description : 재고폐기요청/처리 기능을 구현한 Controller Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "재고폐기요청/처리 Response DTO")
public class StDisuseRequestCenterResDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	/** 재고번호 */
	@Schema(description = "재고번호")
	private String serialkey;
	
    /** 요청월 */
    @Schema(description = "요청월")
    private String requestMm;
    
    /** 순번 */
    @Schema(description = "순번")
    private BigDecimal seq;


    /** 문서번호 */
    @Schema(description = "문서번호")
    private String docno;

    /** 폐기구분 */
    @Schema(description = "폐기구분")
    private String disuseDiv;

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;

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
    
    /** 재고수량 */
    @Schema(description = "재고수량")
    private BigDecimal qty;    
    
    @Schema(description = "로트")
    private String lot;
    
    /** 할당수량 */
    @Schema(description = "할당수량")
    private BigDecimal qtyallocated;
    
    /** 피킹수량 */
    @Schema(description = "피킹수량")
    private BigDecimal qtypicked;

    /** 재고구분등급 */
    @Schema(description = "재고구분등급")
    private String stockgrade;

    /** 재고유형 */
    @Schema(description = "재고유형")
    private String stocktype;
    
    /** 유통임박여부 */
    @Schema(description = "유통임박여부")
    private String neardurationyn;

    /** 로트테이블01 */
    @Schema(description = "로트테이블01")
    private String lottable01;
    
    /** 유통기한 */	
    @Schema(description = "유통기한")
    private String durationTerm;

    /** 재고ID */
    @Schema(description = "재고ID")
    private String stockid;

    /** 조정수량 */
    @Schema(description = "조정수량")
    private BigDecimal adjustqty;

    /** 폐기유형 */
    @Schema(description = "폐기유형")
    private String disusetype;
    
    /** 총중량(조정수량*평균단가) */
    @Schema(description = "총중량(조정수량*평균단가)")
    private BigDecimal grossweight;    

    /** SAP단가 */
    @Schema(description = "SAP단가")
    private BigDecimal unitprice;   
    
    /** 폐기금액(조정수량*SAP단가*) */
    @Schema(description = "폐기금액(조정수량*SAP단가*)")
    private BigDecimal disuseprice;    
    
    /** 폐기비용(총중량*420) */
    @Schema(description = "폐기비용(총중량*420)")
    private BigDecimal disusecost;      
    
    /** KG당비용 */
    @Schema(description = "KG당비용")
    private BigDecimal costperkg;      
    
    
    /** 발생사유코드 */
    @Schema(description = "발생사유코드")
    private String reasoncode;
    
    /** 발생사유코드대분류") */
    @Schema(description = "발생사유코드(대분류")
    private String reasoncode1;    

    /** 귀책주체코드 */
    @Schema(description = "귀책주체코드")
    private String respPartyCd;
    
    /** 물류귀책배부코드 */
    @Schema(description = "물류귀책배부코드")
    private String logiRespDistbCd;

    /** 상품MAMD부서코드 */
    @Schema(description = "상품MAMD부서코드")
    private String deptCd;

    /** 귀속부서코드 */
    @Schema(description = "귀속부서코드(사실COST)")
    private String respDeptCd;
    
	@Schema(description = "귀속부서명")
	private String respDeptNm;

    /** 거래처코드 */
    @Schema(description = "거래처코드")
    private String custkey;
    
    /** 거래처코드 */
    @Schema(description = "거래처코드")
    private String custname;
    

    /** 폐기방안코드 */
    @Schema(description = "폐기방안코드")
    private String disuseMethodCd;

    /** 재고실물여부 */
    @Schema(description = "재고실물여부")
    private String stockRealYn;

    /** 사유내용1 */
    @Schema(description = "사유내용1")
    private String reasonmsg1;

    /** 사유내용2 */
    @Schema(description = "사유내용2")
    private String reasonmsg2;

    /** 비고 */
    @Schema(description = "비고")
    private String rmk;
    
    /** 화주코드 */
    @Schema(description = "화주코드")
    private String storerkey;

    /** 창고 */
    @Schema(description = "창고")
    private String organize;

    /** 구역 */
    @Schema(description = "구역")
    private String area;

    /** 재고위치명 */
    @Schema(description = "재고위치명")
    private String stocktypenm;

    /** 재고등급명 */
    @Schema(description = "재고등급명")
    private String stockgradename;

    /** 존 */
    @Schema(description = "존")
    private String zone;

    /** 가용수량 */
    @Schema(description = "가용수량")
    private BigDecimal openqty;

    /** 체인전용구분 */
    @Schema(description = "체인전용구분")
    private String reference15;

    /** 제당반품(Y:제당반품,N:제당외) */
    @Schema(description = "제당반품(Y:제당반품,N:제당외)")
    private String cheiljedangSkuYn;

    /** 변경사유코드(세부) */
    @Schema(description = "변경사유코드(세부)")
    private String reasonmsg2Detail;

    /** 반품번호 */
    @Schema(description = "반품번호")
    private String returnno;

    /** 평균중량 */
    @Schema(description = "평균중량")
    private String avgweight;   
    
    /** 저장조건 */
    @Schema(description = "저장조건")
    private String storagetype;
    
    @Schema(description = "협력사코드")
    private String fromCustkey;
    
    @Schema(description = "협력사명")
    private String fromCustname;
    
    @Schema(description = "변경여부")
    private String changedYn;
    
    /** 제조일자 */
    @Schema(description = "제조일자")
    private String manufacturedt;    
    
    /** 소비일자 */
    @Schema(description = "소비일자")
    private String expiredt;    
    
    /** 소비기한잔여율 */
    @Schema(description = "소비기한잔여율")
    private BigDecimal usebydatefreert;
    
    /** crudFlag */
    @Schema(description = "crudFlag")
    private String crudFlag;      
    
    /** 상태(00:등록,30:요청,50:결재중,90:완료) */
    @Schema(description = "상태(00:등록,30:요청,50:결재중,90:완료)")
    private String status;        

    /** 상태명 */
    @Schema(description = "상태명")
    private String statusnm; 
        
    /** 폐기금액 */
    @Schema(description = "폐기금액")
    private BigDecimal disuseAmount;

    /*반품전표일자 */
    @Schema(description = "반품전표일자")
    private String slipdt; 
    
    /*반품전표번호 */
    @Schema(description = "반품전표번호")
    private String slipno; 
    
    /*반품전표라인 */
    @Schema(description = "반품전표라인")
    private String slipline; 
    
    /*반품실거래처*/
    @Schema(description = "반품실거래처")
    private String rtCustkey; 
    
    /*반품실거래처명*/
    @Schema(description = "반품실거래처명")
    private String rtCustname; 
    
    /*2210 부서*/
    @Schema(description = "2210 부서")
    private String assingedDept; 
    
    /*2210 부서명*/
    @Schema(description = "2210 부서명")
    private String assingedDeptname; 
    
    /*협력사반품에서 타센터코드*/
    @Schema(description = "협력사반품에서 타센터코드")
    private String stoDccode; 
    
    /*반품차량번호*/
    @Schema(description = "반품차량번호")
    private String returncarno; 
    
    /** SAP 단가  */
    @Schema(description = "SAP 단가 ")
    private BigDecimal purchaseprice;
    
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