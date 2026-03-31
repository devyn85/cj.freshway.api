package cjfw.wms.wd.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.12.09
 * @description : 퀵접수(VSR)및처리 Request Entity DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.09 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Data
@Schema(description = "퀵접수(VSR)및처리 Request Entity")
public class WdQuickMonCloseEntity extends CommonProcedureDto {
    /** 1.고객사코드 */
    @Schema(description = "고객사코드")
    private String storerkey;

    /** 2.물류센터 */
    @Schema(description = "물류센터")
    private String dccode;

    /** 3.정산월 */
    @Schema(description = "정산월")
    private String sttlYm;

    /** 4.퀵주문번호 */
    @Schema(description = "퀵주문번호")
    private String quickDocno;

    /** 5.지급금액 */
    @Schema(description = "지급금액")
    private BigDecimal totalAmount;

    /** 6.기본요금 */
    @Schema(description = "기본요금")
    private BigDecimal basicCost;

    /** 7.추가요금 */
    @Schema(description = "추가요금")
    private BigDecimal addCost;

    /** 8.탁송요금 */
    @Schema(description = "탁송요금")
    private BigDecimal deliveryCost;

    /** 9.귀책부서 */
    @Schema(description = "귀책부서")
    private String respDept;

    /** 10.귀책사유 */
    @Schema(description = "귀책사유")
    private String respReason;

    /** 11.귀책담당자 */
    @Schema(description = "귀책담당자")
    private String respEmp;

    /** 12.정산엑셀발행여부 */
    @Schema(description = "정산엑셀발행여부")
    private String sttlExcelYn;

    /** 13.정산마감여부 */
    @Schema(description = "정산마감여부")
    private String sttlCloseYn;

    /** 14.정산마감일시 */
    @Schema(description = "정산마감일시")
    private String sttlCloseDate;
   
    /** 주문 일시 */
    @Schema(description = "주문 일시")
    private String orderDate;   
    
}
