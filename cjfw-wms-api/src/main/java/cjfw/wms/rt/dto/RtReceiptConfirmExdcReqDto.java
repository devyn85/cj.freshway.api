package cjfw.wms.rt.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.06.16 
 * @description : 외부비축소비기한변경 조회 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.16    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부비축소비기한변경 조회 요청") 
public class RtReceiptConfirmExdcReqDto extends CommonProcedureDto {	
	
	List<RtReceiptConfirmExdcMasterResDto> saveMasterList;
	
	/* ───────── 기본 키 & 문서 식별 ───────── */
    @Schema(description = "체크 여부(Y/N)")
    private String checkYn;              // CHECKYN

    @Schema(description = "D/C 코드")
    private String dcCode;               // DCCODE

    @Schema(description = "StorerKey")
    private String storerkey;            // STORERKEY

    @Schema(description = "조직(창고)")
    private String organize;             // ORGANIZE

    @Schema(description = "작업 Area")
    private String area;                 // AREA

    @Schema(description = "문서일자(YYYYMMDD)")
    private String docdt;                // DOCDT

    @Schema(description = "문서번호")
    private String docNo;                // DOCNO

    @Schema(description = "문서라인")
    private String docLine;              // DOCLINE
    
    @Schema(description = "저장조건")
    private String storagetype;          // STORAGETYPE
    
    /** BL 번호 */
    @Schema(description = "BL 번호")
    private String blNo;
    
    /** BL 번호 */
    @MultiSearch
    @Schema(description = "BL 번호")
    private List<String> blNoMulti;

    /* ───────── 전표 정보 ───────── */
    @Schema(description = "전표일자")
    private String slipDt;               // SLIPDT

    @Schema(description = "전표번호")
    private String slipNo;               // SLIPNO

    @Schema(description = "전표라인")
    private String slipLine;             // SLIPLINE

    /* ───────── 상품 & 수량 ───────── */
    @Schema(description = "SKU 코드")
    private String sku;                  // SKU

    @Schema(description = "UOM(재고단위)")
    private String uom;                  // UOM

    @Schema(description = "분자(단위환산)")
    private Integer bunja;               // BUNJA

    @Schema(description = "분모(단위환산)")
    private Integer bunmo;               // BUNMO

    @Schema(description = "작업 수량(TRANQTY)")
    private Integer tranQty;             // TRANQTY

    /* ───────── 재고 위치 / 로트 ───────── */
    @Schema(description = "LotTable01(유통기한 등)")
    private String lottable01;           // LOTTABLE01

    @Schema(description = "Stock ID")
    private String stockId;              // STOCKID

    @Schema(description = "Stock Grade")
    private String stockGrade;           // STOCKGRADE

    @Schema(description = "결품 이동 수량")
    private Integer shortageTranQty;     // SHORTAGETRANQTY

    @Schema(description = "입고(To) 로케이션")
    private String toLoc;                // TOLOC

    /* ───────── 반품 사유 ───────── */
    @Schema(description = "사유 코드")
    private String reasonCode;           // REASONCODE

    @Schema(description = "사유 메시지")
    private String reasonMsg;            // REASONMSG

    @Schema(description = "반품 유형")
    private String returnType;           // RETURNTYPE

    /* ───────── 신규 3개 필드 (공통) ───────── */
    @Schema(description = "검사 시리얼키")
    private String inspectSerialKey;     // INSPECTSERIALKEY

    @Schema(description = "강제검수 여부('TRUE'/'FALSE')")
    private String forceInspect;         // FORCEINSPECT

    @Schema(description = "작업 박스 수량")
    private Integer tranBox;             // TRANBOX
    
}
