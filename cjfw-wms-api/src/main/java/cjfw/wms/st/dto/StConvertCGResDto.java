package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 *
 * @author : 고혜미(laksjd0606@cj.net)
 * @date : 2025.09.18
 * @description : 재고속성변경조회 결과 DTO
 * @issues :
 * -----------------------------------------------------------
 * DATE          AUTHOR                  MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.18   고혜미(laksjd0606@cj.net)  생성
 */
@Data
@Schema(description = "재고속성변경조회 결과 DTO")
public class StConvertCGResDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";


	/** =====================그리드1===============================*/
    /** 창고구분 */
    @Schema(description = "창고구분")
    private String wharea;  

    /** 창고구분명 */
    @Schema(description = "창고구분명")
    private String whareaname;
    
    /** 창고층 */
    @Schema(description = "창고층")
    private String whareafloor;    

    /** 창고층명 */
    @Schema(description = "창고층명")
    private String whareafloorname;
    
    /** 피킹존 */
    @Schema(description = "피킹존")
    private String zone;     
    
    /** ZONE명 */
    @Schema(description = "ZONE명")
    private String zonename;     
    
    /** 로케이션수 */
    @Schema(description = "로케이션수")
    private BigDecimal loccnt;        
    
    /** 상품수 */
    @Schema(description = "상품수")
    private BigDecimal skucnt;      
    
    /** ID수 */
    @Schema(description = "ID수")
    private BigDecimal idcnt;     
    
    
	/** =====================그리드2===============================*/  
    /** 로케이션종류 */
    @Schema(description = "로케이션종류")
    private String loccategory;     
    
    /** 로케이션종류명 */
    @Schema(description = "로케이션종류명")
    private String loccategoryname;      
    
    /** 로케이션유형 */
    @Schema(description = "로케이션종류")
    private String loctype;     
    
    /** 로케이션유형명 */
    @Schema(description = "로케이션유형명")
    private String loctypename;       
    
    /** 재고속성 */
    @Schema(description = "재고속성")
    private String stockgrade;     
    
    /** 재고속성명 */
    @Schema(description = "재고속성명")
    private String stockgradename;       
    
    /** LOT수 */
    @Schema(description = "LOT수")
    private BigDecimal lotcnt;       

    
	/** =====================그리드3===============================*/ 
    /** 데이터번호 */
    @Schema(description = "데이터번호")
    private String serialkey;     
    
    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;     
    
    /** 고객사코드 */
    @Schema(description = "고객사코드")
    private String storerkey;   
    
    /** 창고 */
    @Schema(description = "창고")
    private String organize;      
    
    /** 로케이션 */
    @Schema(description = "로케이션")
    private String fromloc; 
    
    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;    
    
    /** 상품코드명 */
    @Schema(description = "상품코드명")
    private String skuname;  
    
    /** 상품분류 */
    @Schema(description = "상품분류")
    private String mc;     
    
    /** 상품분류명 */
    @Schema(description = "상품분류명")
    private String mcname;  
    
    /** 단위 */
    @Schema(description = "단위")
    private String uom;     
    
    /** 현재고수량 */
    @Schema(description = "현재고수량")
    private BigDecimal qty;       
    
    /** 가용재고수량 */
    @Schema(description = "가용재고수량")
    private BigDecimal openqty;      
    
    /** 재고할당수량 */
    @Schema(description = "재고할당수량")
    private BigDecimal qtyallocated;      
    
    /** 피킹재고 */
    @Schema(description = "피킹재고")
    private BigDecimal qtypicked;    
    
    /** 작업수량 */
    @Schema(description = "작업수량")
    private BigDecimal tranqty;      
       
    /** FROM재고속성 */
    @Schema(description = "FROM재고속성")
    private String fromstockgrade;       
    
    /** TO재고속성 */
    @Schema(description = "TO재고속성")
    private String tostockgrade;        
    
    /** 사유코드 */
    @Schema(description = "사유코드")
    private String reasoncode;       
    
    /** 처리결과 */
    @Schema(description = "처리결과")
    private String reasonmsg;      
    
    /** 기준일(유통,제조) */
    @Schema(description = "기준일(유통,제조)")
    private String lottable01;      
    
    /** 소비기간(잔여/전체) */
    @Schema(description = "소비기간(잔여/전체)")
    private String durationterm;      
    
    /** 이력번호 */
    @Schema(description = "이력번호")
    private String serialno;       
    
    /** 재고ID */
    @Schema(description = "재고ID")
    private String fromstockid;      
    
    /** 유통기간  */
    @Schema(description = "유통기간")
    private String duration;   
    
    /** 유통기한관리방법  */
    @Schema(description = "유통기한관리방법")
    private String durationtype;     
    
    /** 재고유형  */
    @Schema(description = "재고유형")
    private String fromstocktype;     

    /** 변경이력번호  */
    @Schema(description = "변경이력번호")
    private String convserialno;
    
    /** FROM LOT  */
    @Schema(description = "FROM LOT")
    private String fromlot;   
    
    /** 창고코드 SAP 창고 혹은 별도의 창고 코드  */
    @Schema(description = "창고코드 SAP 창고 혹은 별도의 창고 코드")
    private String area;    
    
    
}
