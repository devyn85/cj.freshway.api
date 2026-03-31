package cjfw.wms.rt.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.18 
 * @description : 외부비축반품확정 마스터 조회 기능을 구현한 reqDto Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.18 ParkJinWoo 생성
 */
@Data
@Schema(description = " 외부비축반품확정(출고내역) 마스터 조회 요청")
public class RtReceiptConfirmExdcMasterSubReqDto extends CommonProcedureDto{
	
	/** 저장 서브 리스트 */
	List<RtReceiptConfirmExdcMasterSubResDto> saveMasterSubList;
	
    /* ===== 단일조건 ===== */
	/**진행상태 */
    @Schema(description = "진행상태")
    private String status;

    /**주문번호 */
    @Schema(description = "주문번호")
    private String docnoWd;

    /**회수여부 */    
    @Schema(description = "회수여부")
    private String returnType;
    /**저장여부 */
    @Schema(description = "저장여부")
    private String channel;

    /**이력번호유무 */
    @Schema(description = "이력번호유무")
    private String serialYn;

    /**저장유형 */
    @Schema(description = "저장유형")
    private String storagetype;
    
    /**BL 번호*/
    @Schema(description = "BL 번호")
    private String blNo;
    
    /** BL 번호 */
    @MultiSearch
    @Schema(description = "BL 번호")
    private List<String> blNoMulti;

    /**Serial 번호*/
    @Schema(description = "Serial 번호")
    private String serialNo;

    /**WD 고객코드*/
    @Schema(description = "WD 고객코드")
    private String wdCustKey;

    /**반품요청일자From*/
    @Schema(description = "반품요청일자From")
    private String slipdtFrom;
    
    /**반품요청일자To*/
    @Schema(description = "반품요청일자To")
    private String slipdtTo;

    /* ===== 다중선택(콤마구분 문자열) ===== */
    /**상품코드*/
    @Schema(description = "상품코드")
    private String sku;
    
    /* ===== 다중선택(콤마구분 문자열) ===== */
    /**창코코드*/
    @Schema(description = "창코코드")
    private String organize;
    
    /**고객반품주문번호*/
    @Schema(description = "고객반품주문번호")
    private String docNo;
    
    /**물류센터*/
    @Schema(description = "물류센터")
	private String fixDcCode;
}
