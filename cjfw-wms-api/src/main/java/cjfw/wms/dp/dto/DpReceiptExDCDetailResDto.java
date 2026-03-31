package cjfw.wms.dp.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.07.14
 * @description : 외부비축입고처리 상세 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.14    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Schema(description = "외부비축입고처리 상세 조회 결과")
public class DpReceiptExDCDetailResDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";


    /** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String dccode;

    /** 문서번호 */
    @Schema(description = "문서번호", nullable = false, example = "")
    private String docno;

    /** 고객사 */
    @Schema(description = "고객사", nullable = false, example = "")
    private String storerkey;

    /** 문서유형 */
    @Schema(description = "문서유형", nullable = false, example = "")
    private String doctype;

    /** POTYPE */
    @Schema(description = "POTYPE", nullable = false, example = "")
    private String potype;

    /** POTYPENAME */
    @Schema(description = "POTYPENAME", nullable = false, example = "")
    private String potypename;

    /** 창고 */
    @Schema(description = "창고", nullable = false, example = "")
    private String organize;

    /** 창고명 */
    @Schema(description = "창고명", nullable = false, example = "")
    private String organizename;

    /** 주문유형 */
    @Schema(description = "주문유형", nullable = false, example = "")
    private String ordertype;

    /** 주문유형명 */
    @Schema(description = "주문유형명", nullable = false, example = "")
    private String ordertypename;

    /** 상태 */
    @Schema(description = "상태", nullable = false, example = "")
    private String status;

    /** 상태명 */
    @Schema(description = "상태명", nullable = false, example = "")
    private String statusname;

    /** 문서생성일자 */
    @Schema(description = "문서생성일자", nullable = false, example = "")
    private String docdt;

    /** 협력사코드 */
    @Schema(description = "협력사코드", nullable = false, example = "")
    private String fromCustkey;

    /** 협력사명 */
    @Schema(description = "협력사명", nullable = false, example = "")
    private String fromCustname;

    /** ALL_CANCEL_STATUS */
    @Schema(description = "ALL_CANCEL_STATUS", nullable = false, example = "")
    private String allCancelStatus;

    /** 확정자 */
    @Schema(description = "확정자", nullable = false, example = "")
    private String editwho;
    
    /** 확정자명 */
    @Schema(description = "확정자명", nullable = false, example = "")
    private String editwhoNm;

    /** 가/진오더 여부 */
    @Schema(description = "가/진오더 여부", nullable = false, example = "")
    private String realYn;
    
    /** 가/진오더 여부 */
    @Schema(description = "가/진오더 여부", nullable = false, example = "")
    private String realYnNm;

    /** 이력정보확정자 */
    @Schema(description = "이력정보확정자", nullable = false, example = "")
    private String lastwho;
    
    /** 이력정보확정자명 */
    @Schema(description = "이력정보확정자명", nullable = false, example = "")
    private String lastwhoNm;

    /** 품목번호 */
    @Schema(description = "품목번호", nullable = false, example = "")
    private String docline;

    /** 전표일자 */
    @Schema(description = "전표일자", nullable = false, example = "")
    private String slipdt;

    /** 전표번호 */
    @Schema(description = "전표번호", nullable = false, example = "")
    private String slipno;

    /** 전표번호라인 */
    @Schema(description = "전표번호라인", nullable = false, example = "")
    private String slipline;

    /** 상품코드 */
    @Schema(description = "상품코드", nullable = false, example = "")
    private String sku;

    /** 상품명칭 */
    @Schema(description = "상품명칭", nullable = false, example = "")
    private String skuname;

    /** 구매단위 */
    @Schema(description = "구매단위", nullable = false, example = "")
    private String uom;

    /** BUNJA */
    @Schema(description = "BUNJA", nullable = false, example = "")
    private String bunja;

    /** BUNMO */
    @Schema(description = "BUNMO", nullable = false, example = "")
    private String bunmo;

    /** 구매수량 */
    @Schema(description = "구매수량", nullable = false, example = "")
    private BigDecimal orderqty;

    /** INSPECTQTY */
    @Schema(description = "INSPECTQTY", nullable = false, example = "")
    private BigDecimal inspectqty;

    /** 입고수량 */
    @Schema(description = "입고수량", nullable = false, example = "")
    private BigDecimal confirmqty;

    /** 결품수량 */
    @Schema(description = "결품수량", nullable = false, example = "")
    private BigDecimal shortageqty;

    /** SHORTAGETRANQTY */
    @Schema(description = "SHORTAGETRANQTY", nullable = false, example = "0")
    private BigDecimal shortagetranqty;

    /** 처리작업량  (입고/결품) */
    @Schema(description = "처리작업량  (입고/결품)", nullable = false, example = "0")
    private BigDecimal tranqty;

    /** TOLOC */
    @Schema(description = "TOLOC", nullable = false, example = "")
    private String toloc;

    /** 기준일(유통,제조) */
    @Schema(description = "기준일(유통,제조)", nullable = false, example = "STD")
    private String lottable01;

    /** 소비기간(잔여/전체) */
    @Schema(description = "소비기간(잔여/전체)", nullable = false, example = "")
    private String durationTerm;

    /** COLORDESCR */
    @Schema(description = "COLORDESCR", nullable = false, example = "")
    private String colordescr;

    /** REFERENCE02 */
    @Schema(description = "REFERENCE02", nullable = false, example = "")
    private String reference02;

    /** REASONCODE */
    @Schema(description = "REASONCODE", nullable = false, example = "")
    private String reasoncode;

    /** REASONMSG */
    @Schema(description = "REASONMSG", nullable = false, example = "")
    private String reasonmsg;
    
    /** 반려사유 */
    @Schema(description = "반려사유", nullable = false, example = "")
    private String  reasoncodeReject;

    /** 소비기한임박여부 */
    @Schema(description = "소비기한임박여부", nullable = false, example = "")
    private String neardurationyn;

    /** 박스입수 */
    @Schema(description = "박스입수", nullable = false, example = "")
    private BigDecimal qtyperbox;

    /** CHANNEL */
    @Schema(description = "CHANNEL", nullable = false, example = "")
    private String channel;

    /** 기준일(유통,제조) */
    @Schema(description = "기준일(유통,제조)", nullable = false, example = "")
    private BigDecimal duration;

    /** DURATIONTYPE */
    @Schema(description = "DURATIONTYPE", nullable = false, example = "")
    private String durationtype;

    /** LASTLOTTABLE01 */
    @Schema(description = "LASTLOTTABLE01", nullable = false, example = "")
    private String lastLottable01;

    /** STOCKID */
    @Schema(description = "STOCKID", nullable = false, example = "")
    private String stockid;

    /** STOCKGRADE */
    @Schema(description = "STOCKGRADE", nullable = false, example = "")
    private String stockgrade;
    
    /** PLANT */
    @Schema(description = "PLANT", nullable = false, example = "")
    private String plant;

    /** PLANT_DESCR */
    @Schema(description = "PLANT_DESCR", nullable = false, example = "")
    private String plantDescr;

    /** CHANNEL_NAME */
    @Schema(description = "CHANNEL_NAME", nullable = false, example = "")
    private String channelName;

    /** 저장조건명 */
    @Schema(description = "저장조건명", nullable = false, example = "")
    private String storagetypename;

    /** 이력번호 */
    @Schema(description = "이력번호", nullable = false, example = "")
    private String serialno;

    /** B/L번호 */
    @Schema(description = "B/L번호", nullable = false, example = "")
    private String convserialno;

    /** FACTORYNAME */
    @Schema(description = "FACTORYNAME", nullable = false, example = "")
    private String factoryname;

    /** 계약업체 */
    @Schema(description = "계약업체", nullable = false, example = "")
    private String contractcompany;

    /** 계약업체명 */
    @Schema(description = "계약업체명", nullable = false, example = "")
    private String contractcompanyname;

    /** 계약유형 */
    @Schema(description = "계약유형", nullable = false, example = "")
    private String contracttype;
    
    /** 계약유형명 */
    @Schema(description = "계약유형명", nullable = false, example = "")
    private String contracttypename;

    /** BUTCHERYDT */
    @Schema(description = "BUTCHERYDT", nullable = false, example = "")
    private String butcherydt;

    /** FROMVALIDDT */
    @Schema(description = "FROMVALIDDT", nullable = false, example = "")
    private String fromvaliddt;

    /** TOVALIDDT */
    @Schema(description = "TOVALIDDT", nullable = false, example = "")
    private String tovaliddt;

    /** 바코드 */
    @Schema(description = "바코드", nullable = false, example = "")
    private String barcode;

    /** SERIALORDERQTY */
    @Schema(description = "SERIALORDERQTY", nullable = false, example = "")
    private BigDecimal serialorderqty;

    /** SERIALINSPECTQTY */
    @Schema(description = "SERIALINSPECTQTY", nullable = false, example = "")
    private BigDecimal serialinspectqty;

    /** SERIALSCANWEIGHT */
    @Schema(description = "SERIALSCANWEIGHT", nullable = false, example = "")
    private BigDecimal serialscanweight;

    /** CHECKFLAG */
    @Schema(description = "CHECKFLAG", nullable = false, example = "")
    private String checkflag;

    /** LINE01 */
    @Schema(description = "LINE01", nullable = false, example = "")
    private String line01;

    /** FONTCOLOR */
    @Schema(description = "FONTCOLOR", nullable = false, example = "")
    private String fontcolor;

    /** 평균중량 */
    @Schema(description = "평균중량", nullable = false, example = "")
    private BigDecimal avgweight;

    /** 환산박스 */
    @Schema(description = "환산박스", nullable = false, example = "")
    private BigDecimal calbox;

    /** 실박스예정 */
    @Schema(description = "실박스예정", nullable = false, example = "")
    private BigDecimal realorderbox;

    /** 실박스확정 */
    @Schema(description = "실박스확정", nullable = false, example = "")
    private BigDecimal realcfmbox;

    /** 작업박스 */
    @Schema(description = "작업박스", nullable = false, example = "")
    private BigDecimal tranbox;

    /** 박스처리유무 */
    @Schema(description = "박스처리유무", nullable = false, example = "")
    private String boxflag;

    /** 가확정 여부 */
    @Schema(description = "가확정 여부", nullable = false, example = "")
    private String tempYn;
    
    /** 가중량 여부 */
    @Schema(description = "가중량 여부", nullable = false, example = "")
    private String tempWeightYn;
    
    /** 처리결과 */
    @Schema(description = "처리결과", nullable = false, example = "")
    private String resultmsg;
    
    /** 구매요청번호 */
    @Schema(description = "구매요청번호", nullable = false, example = "")
    private String mapkeyNo;
    
    /** 구매요청라인번호 */
    @Schema(description = "구매요청라인번호", nullable = false, example = "")
    private String mapkeyLine;
    
    /** ISSUE_NO */
    @Schema(description = "ISSUE_NO", nullable = false, example = "")
    private String issueNo;
    
    /** 진오더생성여부 */
    @Schema(description = "진오더생성여부", nullable = false, example = "")
    private String sokeyYn;
    
    /** 요율등록여부 */
    @Schema(description = "요율등록여부", nullable = false, example = "")
    private String exdcrateYn;
    
    /** 구매유형 */
    @Schema(description = "구매유형", nullable = false, example = "")
    private String exdcOrdertype;
    
    /** 이체여부 */
    @Schema(description = "이체여부", nullable = false, example = "")
    private String moveYn;
    
    /** 첨부파일-내부용 */
    @Schema(description = "첨부파일-내부용", nullable = false, example = "")
    private String refDocid;
    
    /** 첨부파일-외부용 */
    @Schema(description = "첨부파일-외부용", nullable = false, example = "")
    private String refCustDocid;    

    /** 첨부파일-추가서류 외 */
    @Schema(description = "첨부파일-추가서류 외", nullable = false, example = "")
    private String addDocid;
    
    /** 일반/로컬구매 */
    @Schema(description = "일반/로컬구매", nullable = false, example = "")
    private String dptype;
    
    /** 구매처리일자 */
    @Schema(description = "구매처리일자", nullable = false, example = "")
    private String deliverydate;

}
