package cjfw.wms.st.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : ParkYoSep 
 * @date : 2025.11.04  
 * @description : 위탁입출고현황 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.04 parkYosep (dytpq362@cj.net) 생성
 *         </pre>
 */
@Schema(description = "위탁입출고현황 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StTplTransactionsResDto extends CommonProcedureDto {
	
    /** 커스텀 엑스트라 체크박스 */
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn = "N";
    
    /** 일자 */
    @Schema(description = "일자")
    private String docDt;

    /** 입출고구분 */
    @Schema(description = "입출고구분")
    private String inoutType;

    /** 재고위치 */
    @Schema(description = "재고위치")
    private String stocktype;

    /** 재고위치명 */
    @Schema(description = "재고위치명")
    private String stocktypenm;

    /** 재고위치 */
    @Schema(description = "재고속성")
    private String stockgrade;

    /** 재고위치명 */
    @Schema(description = "재고속성명")
    private String stockgradedesc;

    /** 단위 */
    @Schema(description = "단위")
    private String uom;

    /** 수량 */
    @Schema(description = "수량")
    private String qty;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 상품명*/
    @Schema(description = "상품명")
    private String skunm;

    /** BL번호 */
    @Schema(description = "BL번호")
    private String convSerialNo;

    /** 이력번호 */
    @Schema(description = "이력번호")
    private String serialNo;

    /** 요청번호 */
    @Schema(description = "요청번호")
    private String requestNo;

    /** 시작일자 */
    @Schema(description = "시작일자")
    private String fromDate;

    /** 종료일자 */
    @Schema(description = "종료일자")
    private String toDate;

    
    /** 기준일자 */
    @Schema(description = "기준일자")
    private String lot;     
    
    /** 유통기한(잔여/전체) */
    @Schema(description = "유통기한(잔여/전체)")
    private String durationTerm;     
    
    /** 잔여소비일자 */
    @Schema(description = "잔여소비일자")
    private String duration;    
    
    /** 소비기한잔여율 */
    @Schema(description = "소비기한잔여율")
    private String durationRate;  
    
    /** 비고 */
    @Schema(description = "비고")
    private String rmk;
}
