package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.10.31 
 * @description : 위탁물류입고요청 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.31 ParkJinWoo 생성
 */
@Data
@Schema(description = "위탁물류입고요청 결과 DTO")
public class StTplIssueReqResDto extends CommonProcedureDto {

	  /** 인도예정일자 */
    @Schema(description = "인도예정일자")
    private String deliveryDate;

    /** 요청일자 */
    @Schema(description = "요청일자")
    private String docdt;

    /** 문서번호 */
    @Schema(description = "문서번호")
    private String docNo;

    /** 문서라인번호 */
    @Schema(description = "문서라인번호")
    private String docLine;

    /** 창고코드 */
    @Schema(description = "창고코드")
    private String organize;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명")
    private String skuName;

    /** 기준단위 */
    @Schema(description = "기준단위")
    private String baseUom;

    /** 주문수량 */
    @Schema(description = "주문수량")
    private BigDecimal orderQty;

    /** BOX 수량 */
    @Schema(description = "BOX 수량")
    private BigDecimal boxQty;

    /** PLT 수량 */
    @Schema(description = "PLT 수량")
    private BigDecimal pltQty;

    /** 입고예정일자 */
    @Schema(description = "입고예정일자")
    private String slipDt;

    /** 소비기한(남은/총기간 표기) */
    @Schema(description = "소비기한 표기(남은/총기간)")
    private String durationTerm;

    /** 소비기한 잔여율(%) */
    @Schema(description = "소비기한 잔여율(%)")
    private String durationRate;
    private String durationTo;
    private String durationFrom;

    /** 기준일자(LOT 기준) */
    @Schema(description = "기준일자(LOT 기준)")
    private String lottable01;

    /** 문서유형 */
    @Schema(description = "문서유형")
    private String docType;

    /** 입출구분 */
    @Schema(description = "입출구분")
    private String ioType;

    /** 주문유형 */
    @Schema(description = "주문유형")
    private String orderType;

    /** 작업프로세스코드 */
    @Schema(description = "작업프로세스코드")
    private String workProcessCode;

    /** 화주코드 */
    @Schema(description = "화주코드")
    private String storerKey;

    /** 수신거래처코드 */
    @Schema(description = "수신거래처코드")
    private String toCustKey;

    /** 수신거래처명 */
    @Schema(description = "수신거래처명")
    private String toCustName;
    
    /** 수신거래처명 */
    @Schema(description = "수신거래처명")
    private String custType;
    

    /** 발신거래처코드 */
    @Schema(description = "발신거래처코드")
    private String fromCustKey;

    /** 발신거래처명 */
    @Schema(description = "발신거래처명")
    private String fromCustName;

    /** 재고ID */
    @Schema(description = "재고ID")
    private String stockId;

    /** 이력번호(시리얼) */
    @Schema(description = "이력번호(시리얼)")
    private String serialNo;

    /** B/L 번호(컨버전) */
    @Schema(description = "B/L 번호(컨버전)")
    private String convSerialNo;
    /** 비고 */
    @Schema(description = "비고 (dataField: rmk)")
    private String rmk;
    
    
    /** 배송유형 */
    @Schema(description = "배송유형")
    private String deliverytype;
    
    /** 첨부파일 */
    @Schema(description = "첨부파일")
    private String attachment;
    
    /** 소비기한 */
    @Schema(description = "소비기한")
    private String duration;
    
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String dcCode;
    
    /** IFID */
    @Schema(description = "IFID")
    private String ifId;
    
    /**  */
    @Schema(description = "화주유형")
    private String tplType;
    
    /** 화주ID */
    @Schema(description = "화주ID")
    private String tplBcnrId;
    
    /** otcoGrpcd */
    @Schema(description = "otcoGrpcd")
    private String otcoGrpcd;
    
    /** 화주유형 */
    @Schema(description = "화주유형")
    private String tplDtlType;
    
    /** IF도착지 */
    @Schema(description = "IF도착지")
    private String ifDestination;
    
    /** 입고시간 */
    @Schema(description = "입고시간")
    private String openTime;
    
    /** 출고수량 */
    @Schema(description = "출고수량")
    private String qty;
    /** 단위 */
    @Schema(description = "단위")
    private String uom;
    
    /** 재고 */
    @Schema(description = "재고")
    private String stockQty;
    /** 등록자 ID */
    @Schema(description = "등록자 ID", nullable = true, example = "")
    private String addWho;

    /** 수정자 ID */
    @Schema(description = "수정자 ID", nullable = true, example = "")
    private String editWho;
    /** 등록자 ID */
    @Schema(description = "등록자 ID", nullable = true, example = "")
    private String addWhoId;

    /** 수정자 ID */
    @Schema(description = "수정자 ID", nullable = true, example = "")
    private String editWhoId;
    
    /** 등록일시 */
    @Schema(description = "등록일시", nullable = true, example = "")
    private String addDate;

    /** 수정일시 */
    @Schema(description = "수정일시", nullable = true, example = "")
    private String editDate;

    /** 행 상태 */
    @Schema(description = "행상태")
    private String rowStatus;
    
    /** 업로드Flag*/
    @Schema(description = "업로드Flag")
    private String uploadFlag;
    

    /** errMsg*/
    @Schema(description = "errMsg")
    private String errMsg;
    
    /** 고객코드*/
    @Schema(description = "고객코드")
    private String custkey;

		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
