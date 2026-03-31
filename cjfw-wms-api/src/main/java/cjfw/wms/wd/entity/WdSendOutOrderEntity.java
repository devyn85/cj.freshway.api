package cjfw.wms.wd.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.07.11 
 * @description : 외부비축출고지시 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                 MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.11    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부비축출고지시 Entity") 
public class WdSendOutOrderEntity extends CommonDto {
    /** checktype */
    @Schema(description = "checktype", nullable = false, example = "")
    private String checktype;

    /** checkyn */
    @Schema(description = "checkyn", nullable = false, example = "")
    private String checkyn;
    
    /** 정상오더번호 */
    @Schema(description = "정상오더번호", nullable = false, example = "")
    private String normalNo;

    /** 승인번호 */
    @Schema(description = "승인번호", nullable = false, example = "")
    private String mapkeyNo;

    /** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String dccode;

    /** storerkey */
    @Schema(description = "storerkey", nullable = false, example = "")
    private String storerkey;

    /** 창고 */
    @Schema(description = "창고", nullable = false, example = "")
    private String organize;

    /** 창고명 */
    @Schema(description = "창고명", nullable = false, example = "")
    private String organizename;

    /** doctype */
    @Schema(description = "doctype", nullable = false, example = "")
    private String doctype;

    /** channel */
    @Schema(description = "channel", nullable = false, example = "")
    private String channel;

    /** docdt */
    @Schema(description = "docdt", nullable = false, example = "")
    private String docdt;

    /** docno */
    @Schema(description = "docno", nullable = false, example = "")
    private String docno;

    /** docline */
    @Schema(description = "docline", nullable = false, example = "")
    private String docline;

    /** sliptype */
    @Schema(description = "sliptype", nullable = false, example = "")
    private String sliptype;

    /** 전표일자 */
    @Schema(description = "전표일자", nullable = false, example = "")
    private String slipdt;

    /** 전표번호 */
    @Schema(description = "전표번호", nullable = false, example = "")
    private String slipno;

    /** 전표라인 */
    @Schema(description = "slipline", nullable = false, example = "")
    private String slipline;

    /** 거래처 */
    @Schema(description = "거래처", nullable = false, example = "")
    private String custkey;

    /** 거래처명 */
    @Schema(description = "거래처명", nullable = false, example = "")
    private String custname;

    /** 상품코드 */
    @Schema(description = "상품코드", nullable = false, example = "")
    private String sku;

    /** 상품명칭*/
    @Schema(description = "상품명칭", nullable = false, example = "")
    private String skuname;

    /** 주문수량 */
    @Schema(description = "주문수량", nullable = false, example = "")
    private String orderqty;

    /** 주문단위 */
    @Schema(description = "주문단위", nullable = false, example = "")
    private String storersuom;

    /** 유통기한임박여부 */
    @Schema(description = "유통기한임박여부", nullable = false, example = "")
    private String neardurationyn;
    
    /** 잔여율 */
    @Schema(description = "잔여율", nullable = false, example = "")
    private BigDecimal usebydateFreeRt;

    /** 기준일(유통,제조) */
    @Schema(description = "기준일(유통,제조)", nullable = false, example = "")
    private String lottable01;

    /** 유통기한(잔여/전체) */
    @Schema(description = "유통기한(잔여/전체)", nullable = false, example = "")
    private String durationTerm;

    /** 이력번호 */
    @Schema(description = "이력번호", nullable = false, example = "")
    private String serialno;

    /** 도축장 */
    @Schema(description = "도축장", nullable = false, example = "")
    private String factoryname;

    /** */
    @Schema(description = "", nullable = false, example = "")
    private String placeoforigin;

    /** B/L번호 */
    @Schema(description = "B/L번호", nullable = false, example = "")
    private String convserialno;

    /** 도축일자 */
    @Schema(description = "도축일자", nullable = false, example = "")
    private String butcherydt;

    /** 계약업체 */
    @Schema(description = "계약업체", nullable = false, example = "")
    private String contractcompany;

    /** 계약업체명 */
    @Schema(description = "계약업체명", nullable = false, example = "")
    private String contractcompanyname;

    /** 유효일자(FROM) */
    @Schema(description = "유효일자(FROM)", nullable = false, example = "")
    private String fromvaliddt;

    /** 유효일자(TO) */
    @Schema(description = "유효일자(TO)", nullable = false, example = "")
    private String tovaliddt;

    /** 계약유형 */
    @Schema(description = "계약유형", nullable = false, example = "")
    private String contracttype;

    /** 바코드 */
    @Schema(description = "바코드", nullable = false, example = "")
    private String barcode;

    /** 총중량 */
    @Schema(description = "총중량", nullable = false, example = "")
    private String grossweight;

    /** 생성인 */
    @Schema(description = "생성인", nullable = false, example = "")
    private String addwho;

    /** 주문수량 */
    @Schema(description = "주문수량", nullable = false, example = "")
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

    /** 박스수량 */
    @Schema(description = "박스수량", nullable = false, example = "")
    private BigDecimal boxqty;

    /** 박스단위 */
    @Schema(description = "박스단위", nullable = false, example = "")
    private String boxuom;

    /** 박스플래그 */
    @Schema(description = "박스플래그", nullable = false, example = "")
    private String boxflag;

    /** 전체 취소 상태 */
    @Schema(description = "전체 취소 상태", nullable = false, example = "")
    private String allCancelStatus;

    /** 수신 팩스 번호 */
    @Schema(description = "수신 팩스 번호", nullable = false, example = "")
    private String recvfaxno;

    /** 수신자 이름 */
    @Schema(description = "수신자 이름", nullable = false, example = "")
    private String recvname;

    /** 수신자 이메일 */
    @Schema(description = "수신자 이메일", nullable = false, example = "")
    private String recvemail;

    /** 팩스 전송 횟수 */
    @Schema(description = "팩스 전송 횟수", nullable = false, example = "")
    private Integer faxCnt;

    /** 팩스 전송 성공 횟수 */
    @Schema(description = "팩스 전송 성공 횟수", nullable = false, example = "")
    private Integer faxSuccCnt;

    /** 팩스 전송 성공 전화번호 */
    @Schema(description = "팩스 전송 성공 전화번호", nullable = false, example = "")
    private String faxSuccTrPhone;

    /** 팩스 전송 실패 횟수 */
    @Schema(description = "팩스 전송 실패 횟수", nullable = false, example = "")
    private Integer faxFailCnt;

    /** 팩스 전송 실패 전화번호 */
    @Schema(description = "팩스 전송 실패 전화번호", nullable = false, example = "")
    private String faxFailTrPhone;
    
    /** 이메일 전송 횟수 */
    @Schema(description = "이메일 전송 횟수", nullable = false, example = "")
    private Integer emailCnt;
    
    /** 이메일 발송자 */
    @Schema(description = "이메일 발송자", nullable = false, example = "")
    private String emailTrSendname;
    
    /** 이메일 발송시간 */
    @Schema(description = "이메일 발송 시간", nullable = false, example = "")
    private String emailTrSenddate;

    /** 출력 전송 횟수 */
    @Schema(description = "출력 전송 횟수", nullable = false, example = "")
    private Integer prtCnt;

    /** 팩스 성공 전송 일시 (YYYY-MM-DD HH24:MI) */
    @Schema(description = "팩스 성공 전송 일시", nullable = false, example = "")
    private String faxSuccTrSenddate;

    /** 팩스 성공 전송자 이름 */
    @Schema(description = "팩스 성공 전송자 이름", nullable = false, example = "")
    private String faxSuccTrSendname;

    /** 팩스 실패 전송 일시 (YYYY-MM-DD HH24:MI) */
    @Schema(description = "팩스 실패 전송 일시", nullable = false, example = "")
    private String faxFailTrSenddate;

    /** 팩스 실패 전송자 이름 */
    @Schema(description = "팩스 실패 전송자 이름", nullable = false, example = "")
    private String faxFailTrSendname;

    /** 문서 메모1 (DM_DOCUMENT_D.MEMO1) */
    @Schema(description = "문서 메모1", nullable = false, example = "")
    private String memo1;

    /** 문서 메모2 (WD_EXDC_DOCUMENT.MEMO1) */
    @Schema(description = "문서 메모2", nullable = false, example = "")
    private String memo2;

    /** 출고지시수단 */
    @Schema(description = "출고지시수단", nullable = false, example = "")
    private String commCode;

    /** 추가일자 (빈 문자열) */
    @Schema(description = "추가일자", nullable = false, example = "")
    private String adddate;

    /** 발송요청자 */
    @Schema(description = "발송요청자", nullable = false, example = "")
    private String docnoconfirm;
    
    /** 출고방법 */
    @Schema(description = "출고방법", nullable = false, example = "")
    private String deliverytypename;
}
