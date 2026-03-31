package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.06.16
 * @description : 외부비축소비기한변경 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.04    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Schema(description = "외부비축BL내재고이관 조회 결과")
public class StAdjustmentTRBLExDCResDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";


    /** 물류센터 */
    @Schema(description = "물류센터", nullable = true, example = "")
    private String dccode;

    /** 고객사 */
    @Schema(description = "고객사", nullable = true, example = "")
    private String storerkey;

    /** 창고 */
    @Schema(description = "창고", nullable = true, example = "")
    private String organize;

    /** 창고 */
    @Schema(description = "창고", nullable = true, example = "")
    private String area;

    /** 재고유형 */
    @Schema(description = "재고유형", nullable = true, example = "")
    private String stocktypenm;

    /** 재고유형 */
    @Schema(description = "재고유형", nullable = true, example = "")
    private String stocktype;

    /** 재고 등급(ABC) */
    @Schema(description = "재고 등급(ABC)", nullable = true, example = "")
    private String stockgrade;

    /** 재고 등급(ABC) */
    @Schema(description = "재고 등급(ABC)", nullable = true, example = "")
    private String stockgradename;

    /** ZONE */
    @Schema(description = "ZONE", nullable = true, example = "")
    private String zone;

    /** 로케이션 */
    @Schema(description = "로케이션", nullable = true, example = "")
    private String loc;

    /** 상품코드 */
    @Schema(description = "상품코드", nullable = true, example = "")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명", nullable = true, example = "")
    private String skuname;

    /** 로트번호 */
    @Schema(description = "로트번호", nullable = true, example = "")
    private String lot;

    /** 단위 */
    @Schema(description = "단위", nullable = true, example = "")
    private String uom;

    /** 재고수량 */
    @Schema(description = "재고수량", nullable = true, example = "")
    private BigDecimal qty;

    /** 가용재고 */
    @Schema(description = "가용재고", nullable = true, example = "")
    private BigDecimal openqty;

    /** 재고할당수량 */
    @Schema(description = "재고할당수량", nullable = true, example = "")
    private BigDecimal qtyallocated;

    /** 피킹재고수량 */
    @Schema(description = "피킹재고수량", nullable = true, example = "")
    private BigDecimal qtypicked;

    /** 출고작업량 */
    @Schema(description = "출고작업량", nullable = true, example = "")
    private BigDecimal tranqty;

    /** 사유 */
    @Schema(description = "사유", nullable = true, example = "")
    private String reasoncode;

    /** 처리결과 */
    @Schema(description = "처리결과", nullable = true, example = "")
    private String reasonmsg;

    /** IMPUTETYPE */
    @Schema(description = "IMPUTETYPE", nullable = true, example = "")
    private String imputetype;

    /** processmain */
    @Schema(description = "processmain", nullable = true, example = "")
    private String processmain;

    /** 귀속부서 */
    @Schema(description = "귀속부서", nullable = true, example = "")
    private String costcd;

    /** 귀속부서명 */
    @Schema(description = "귀속부서명", nullable = true, example = "")
    private String costcdname;

    /** 고객사코드 */
    @Schema(description = "고객사코드", nullable = true, example = "")
    private String custkey;

    /** 고객사명 */
    @Schema(description = "고객사명", nullable = true, example = "")
    private String custname;

    /** NEARDURATIONYN */
    @Schema(description = "NEARDURATIONYN", nullable = true, example = "")
    private String neardurationyn;

    /** LOTTABLE01 */
    @Schema(description = "LOTTABLE01", nullable = true, example = "")
    private String lottable01;

    /** 소비기간(잔여/전체) */
    @Schema(description = "소비기간(잔여/전체)", nullable = true, example = "")
    private String durationTerm;
    
    /** 소비기간잔여율 */
    @Schema(description = "소비기간잔여율", nullable = true, example = "")
    private BigDecimal usebydateFreeRt;

    /** 재고 구분 ID */
    @Schema(description = "재고 구분 ID", nullable = true, example = "")
    private String stockid;

    /** 이력번호 */
    @Schema(description = "이력번호", nullable = true, example = "")
    private String serialno;

    /** B/L번호 */
    @Schema(description = "B/L번호", nullable = true, example = "")
    private String convserialno;

    /** SERIALLEVEL */
    @Schema(description = "SERIALLEVEL", nullable = true, example = "")
    private String seriallevel;

    /** SERIALTYPE */
    @Schema(description = "SERIALTYPE", nullable = true, example = "")
    private String serialtype;

    /** FACTORYNAME */
    @Schema(description = "FACTORYNAME", nullable = true, example = "")
    private String factoryname;

    /** COLORDESCR */
    @Schema(description = "COLORDESCR", nullable = true, example = "")
    private String colordescr;

    /** PLACEOFORIGIN */
    @Schema(description = "PLACEOFORIGIN", nullable = true, example = "")
    private String placeoforigin;

    /** 주문유형 */
    @Schema(description = "주문유형", nullable = true, example = "")
    private String ordertype;

    /** 유통기간 */
    @Schema(description = "유통기간", nullable = true, example = "")
    private String duration;

    /** 유통기한관리방법 */
    @Schema(description = "유통기한관리방법", nullable = true, example = "")
    private String durationtype;

    /** 도축일 */
    @Schema(description = "도축일", nullable = true, example = "")
    private String butcherydt;

    /** 계약업체 */
    @Schema(description = "계약업체", nullable = true, example = "")
    private String contractcompany;

    /** 계약업체명 */
    @Schema(description = "계약업체명", nullable = true, example = "")
    private String contractcompanyname;

    /** FROMVALIDDT */
    @Schema(description = "FROMVALIDDT", nullable = true, example = "")
    private String fromvaliddt;

    /** TOVALIDDT */
    @Schema(description = "TOVALIDDT", nullable = true, example = "")
    private String tovaliddt;

    /** 계약유형 */
    @Schema(description = "계약유형", nullable = true, example = "")
    private String contracttype;

    /** 바코드 */
    @Schema(description = "바코드", nullable = true, example = "")
    private String barcode;

    /** 평균중량 */
    @Schema(description = "평균중량", nullable = true, example = "")
    private BigDecimal avgweight;

    /** 환산박스 */
    @Schema(description = "환산박스", nullable = true, example = "")
    private BigDecimal calbox;

    /** 실박스예정 */
    @Schema(description = "실박스예정", nullable = true, example = "")
    private BigDecimal realorderbox;

    /** 실박스확정 */
    @Schema(description = "실박스확정", nullable = true, example = "")
    private BigDecimal realcfmbox;

    /** 작업박스 */
    @Schema(description = "작업박스", nullable = true, example = "")
    private BigDecimal tranbox;

    /** 박스처리유무 */
    @Schema(description = "박스처리유무", nullable = true, example = "")
    private String boxflag;

    /** PO번호 */
    @Schema(description = "PO번호", nullable = true, example = "")
    private String pokey;

    /** PO라인 */
    @Schema(description = "PO라인", nullable = true, example = "")
    private String poline;

    /** 가/진여부 */
    @Schema(description = "가/진여부", nullable = true, example = "")
    private String realYn;
    
    /** 가/진여부명 */
    @Schema(description = "가/진여부명", nullable = true, example = "")
    private String realYnNm;

}
