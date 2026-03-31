package cjfw.wms.st.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.07.11 
 * @description : 중계영업확정처리 Entity
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.11    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "중계영업확정처리 Entity") 
public class StConvertCFMEntity extends CommonDto {
    
    /** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String dccode;

    /** 고객사코드 */
    @Schema(description = "고객사코드", nullable = false, example = "")
    private String storerkey;

    /** 창고코드 */
    @Schema(description = "창고코드", nullable = false, example = "")
    private String organize;

    /** 창고명 */
    @Schema(description = "창고명", nullable = false, example = "")
    private String organizename;

    /** 생성일자 */
    @Schema(description = "생성일자", nullable = false, example = "")
    private String docdt;

    /** FROM_CUSTKEY */
    @Schema(description = "FROM_CUSTKEY", nullable = false, example = "")
    private String fromCustkey;

    /** 출고일자 */
    @Schema(description = "출고일자", nullable = false, example = "")
    private String deliverydate;

    /** 정상주문번호 */
    @Schema(description = "정상주문번호", nullable = false, example = "")
    private String pokey;

    /** 정상주문번호라인 */
    @Schema(description = "정상주문번호라인", nullable = false, example = "")
    private String poline;

    /** 조정구매번호 */
    @Schema(description = "조정구매번호", nullable = false, example = "")
    private String addPokey;

    /** DP_SOURCEKEYLINE */
    @Schema(description = "DP_SOURCEKEYLINE", nullable = false, example = "")
    private String dpSourcekeyline;

    /** 추가여부 */
    @Schema(description = "추가여부", nullable = false, example = "")
    private String addYn;

    /** 구매유형 */
    @Schema(description = "구매유형", nullable = false, example = "")
    private String exdcOrdertype;

    /** 이체여부 */
    @Schema(description = "이체여부", nullable = false, example = "")
    private String moveYn;

    /** 가중량여부 */
    @Schema(description = "가중량여부", nullable = false, example = "")
    private String tempYn;

    /** 오더항번 */
    @Schema(description = "오더항번", nullable = false, example = "")
    private String docline;

    /** SLIPDT */
    @Schema(description = "SLIPDT", nullable = false, example = "")
    private String slipdt;

    /** SLIPNO */
    @Schema(description = "SLIPNO", nullable = false, example = "")
    private String slipno;

    /** SLIPLINE */
    @Schema(description = "SLIPLINE", nullable = false, example = "")
    private String slipline;

    /** 상품코드 */
    @Schema(description = "상품코드", nullable = false, example = "")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명", nullable = false, example = "")
    private String skuname;

    /** 단위 */
    @Schema(description = "단위", nullable = false, example = "")
    private String uom;

    /** 분자 */
    @Schema(description = "분자", nullable = false, example = "")
    private String bunja;

    /** 분모 */
    @Schema(description = "분모", nullable = false, example = "")
    private String bunmo;

    /** LOTTABLE01 */
    @Schema(description = "LOTTABLE01", nullable = false, example = "")
    private String lottable01;

    /** 유통기한 */
    @Schema(description = "유통기한", nullable = false, example = "")
    private String durationTerm;

    /** REFERENCE02 */
    @Schema(description = "REFERENCE02", nullable = false, example = "")
    private String reference02;

    /** REASONCODE */
    @Schema(description = "REASONCODE", nullable = false, example = "")
    private String reasoncode;

    /** REASONMSG */
    @Schema(description = "REASONMSG", nullable = false, example = "")
    private String reasonmsg;

    /** 임박여부 */
    @Schema(description = "임박여부", nullable = false, example = "")
    private String neardurationyn;

    /** STATUS */
    @Schema(description = "STATUS", nullable = false, example = "")
    private String status;

    /** QTYPERBOX */
    @Schema(description = "QTYPERBOX", nullable = false, example = "")
    private BigDecimal qtyperbox;

    /** CHANNEL */
    @Schema(description = "CHANNEL", nullable = false, example = "")
    private String channel;

    /** TMP_ORDERQTY */
    @Schema(description = "TMP_ORDERQTY", nullable = false, example = "")
    private String tmpOrderqty;

    /** TMP_QCQTY */
    @Schema(description = "TMP_QCQTY", nullable = false, example = "")
    private BigDecimal tmpQcqty;

    /** 기준일 */
    @Schema(description = "기준일", nullable = false, example = "")
    private String duration;

    /** DURATIONTYPE */
    @Schema(description = "DURATIONTYPE", nullable = false, example = "")
    private String durationtype;

    /** LASTLOTTABLE01 */
    @Schema(description = "LASTLOTTABLE01", nullable = false, example = "")
    private String lastlottable01;

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

    /** STORAGETYPENAME */
    @Schema(description = "STORAGETYPENAME", nullable = false, example = "")
    private String storagetypename;

    /** 이력번호 */
    @Schema(description = "이력번호", nullable = false, example = "")
    private String tmpSerialno;

    /** BL번호 */
    @Schema(description = "BL번호", nullable = false, example = "")
    private String tmpConvserialno;

    /** TMP_FACTORYNAME */
    @Schema(description = "TMP_FACTORYNAME", nullable = false, example = "")
    private String tmpFactoryname;

    /** 고객코드 */
    @Schema(description = "고객코드", nullable = false, example = "")
    private String tmpContractcompany;
    
    /** 고객코드명 */
    @Schema(description = "고객코드명", nullable = false, example = "")
    private String tmpContractcompanyname;

    /** 계약구분 */
    @Schema(description = "계약구분", nullable = false, example = "")
    private String tmpContracttype;

    /** TMP_BUTCHERYDT */
    @Schema(description = "TMP_BUTCHERYDT", nullable = false, example = "")
    private String tmpButcherydt;

    /** TMP_FROMVALIDDT */
    @Schema(description = "TMP_FROMVALIDDT", nullable = false, example = "")
    private String tmpFromvaliddt;

    /** TMP_TOVALIDDT */
    @Schema(description = "TMP_TOVALIDDT", nullable = false, example = "")
    private String tmpTovaliddt;

    /** TMP_BARCODE */
    @Schema(description = "TMP_BARCODE", nullable = false, example = "")
    private String tmpBarcode;

    /** 판매단위 */
    @Schema(description = "판매단위", nullable = false, example = "")
    private String saleuom;

    /** 이력유형 */
    @Schema(description = "이력유형", nullable = false, example = "")
    private String serialtype;

    /** ran번호 */
    @Schema(description = "ran번호", nullable = false, example = "")
    private String ranno;

    /** hs번호 */
    @Schema(description = "hs번호", nullable = false, example = "")
    private String hsno;

    /** FACTORYDATE */
    @Schema(description = "FACTORYDATE", nullable = false, example = "")
    private String factorydate;

    /** FACTORYNAME */
    @Schema(description = "FACTORYNAME", nullable = false, example = "")
    private String factoryname;

    /** 영업확인사항메모 */
    @Schema(description = "영업확인사항메모", nullable = false, example = "")
    private String receiveMemo;

    /** 기본첨부파일 */
    @Schema(description = "기본첨부파일", nullable = false, example = "")
    private String refCustDocid;

    /** 추가첨부파일 */
    @Schema(description = "추가첨부파일", nullable = false, example = "")
    private String refDocid;

    /** 추가첨부파일 */
    @Schema(description = "추가첨부파일", nullable = false, example = "")
    private String addDocid;

    /** SERIALNO */
    @Schema(description = "SERIALNO", nullable = false, example = "")
    private String serialno;

    /** BL번호 */
    @Schema(description = "BL번호", nullable = false, example = "")
    private String convserialno;

    /** SERIALYN */
    @Schema(description = "SERIALYN", nullable = false, example = "")
    private String serialyn;

    /** MA상신 시 대금지급일 */
    @Schema(description = "MA상신 시 대금지급일", nullable = false, example = "")
    private String payYmd;

    /** MAPKEY_NO */
    @Schema(description = "MAPKEY_NO", nullable = false, example = "")
    private String mapkeyNo;

    /** MAPKEY_LINE */
    @Schema(description = "MAPKEY_LINE", nullable = false, example = "")
    private String mapkeyLine;

    /** DP_SOURCEKEY */
    @Schema(description = "DP_SOURCEKEY", nullable = false, example = "")
    private String dpSourcekey;

    /** 중량 */
    @Schema(description = "중량", nullable = false, example = "")
    private BigDecimal grossweight;

    /** 주문수량 */
    @Schema(description = "주문수량", nullable = false, example = "")
    private BigDecimal ordrQty;

    /** CREATEDATE */
    @Schema(description = "CREATEDATE", nullable = false, example = "")
    private String createdate;

    /** 확정자 : SCM 확정한 사람 */
    @Schema(description = "확정자 : SCM 확정한 사람", nullable = false, example = "")
    private String cfmwho;

    /** CFMDATE */
    @Schema(description = "CFMDATE", nullable = false, example = "")
    private String cfmdate;

    /** 최종 수정자 : 오더에 최종 수정자 */
    @Schema(description = "최종 수정자 : 오더에 최종 수정자", nullable = false, example = "")
    private String lastwho;

    /** LASTDATE */
    @Schema(description = "LASTDATE", nullable = false, example = "")
    private String lastdate;

    /** SERIALINFO_CFM_YN */
    @Schema(description = "SERIALINFO_CFM_YN", nullable = false, example = "")
    private String serialinfoCfmYn;

    /** CONFIRM_YN */
    @Schema(description = "CONFIRM_YN", nullable = false, example = "")
    private String confirmYn;

    /** TMP_SOKEY */
    @Schema(description = "TMP_SOKEY", nullable = false, example = "")
    private String tmpSokey;

    /** TMP_SOLINE */
    @Schema(description = "TMP_SOLINE", nullable = false, example = "")
    private String tmpSoline;

    /** TMP_DPKEY */
    @Schema(description = "TMP_DPKEY", nullable = false, example = "")
    private String tmpDpkey;

    /** TMP_DPLINE */
    @Schema(description = "TMP_DPLINE", nullable = false, example = "")
    private String tmpDpline;

    /** ORG */
    @Schema(description = "ORG", nullable = false, example = "")
    private String org;
    
    /** CANCELRMK */
    @Schema(description = "CANCELRMK", nullable = false, example = "")
    private String cancelrmk;
    
    /** ISSUE_NO */
    @Schema(description = "ISSUE_NO", nullable = false, example = "")
    private String issueNo;

    /** ADDPOLINE */
    @Schema(description = "ADDPOLINE", nullable = false, example = "")
    private String addpoline;

    /** 주문수량 */
    @Schema(description = "주문수량", nullable = false, example = "")
    private BigDecimal etcqty;

    /** EXDCRATE_YN */
    @Schema(description = "EXDCRATE_YN", nullable = false, example = "")
    private String exdcrateYn;
    
}
