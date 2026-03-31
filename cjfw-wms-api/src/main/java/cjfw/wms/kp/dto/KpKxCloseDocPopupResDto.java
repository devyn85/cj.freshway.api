package cjfw.wms.kp.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net)
 * @date : 2025.12.11
 * @description : 문서정보 팝업 RES DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "문서정보 팝업 RES DTO")
public class KpKxCloseDocPopupResDto {
    @Schema(description = "시리얼키")
    private String serialkey;
    @Schema(description = "화주코드")
    private String storerkey;
    @Schema(description = "문서일자")
    private String docdt;
    @Schema(description = "문서구분")
    private String doctype;
    @Schema(description = "문서번호")
    private String docno;
    @Schema(description = "몰송장번호")
    private String mallinvoice;
    @Schema(description = "택배사")
    private String courier;
    @Schema(description = "송장유형")
    private String invoicetype;
    @Schema(description = "송장번호")
    private String invoiceno;
    @Schema(description = "POKEY")
    private String pokey;
    @Schema(description = "SOURCEKEY")
    private String sourcekey;
    @Schema(description = "센터코드")
    private String dccode;
    @Schema(description = "조직코드")
    private String organize;
    @Schema(description = "주문유형")
    private String ordertype;
    @Schema(description = "쇼핑몰")
    private String shoppingmall;
    @Schema(description = "출발거래처코드")
    private String fromCustkey;
    @Schema(description = "출발거래처명")
    private String fromCustname;
    @Schema(description = "출발거래처유형")
    private String fromCusttype;
    @Schema(description = "출발조직코드")
    private String fromOrganize;
    @Schema(description = "출발청구거래처코드")
    private String fromBilltokey;
    @Schema(description = "출발국가")
    private String fromCountry;
    @Schema(description = "출발도/시")
    private String fromState;
    @Schema(description = "출발시/군/구")
    private String fromCity;
    @Schema(description = "출발우편번호")
    private String fromZipcode;
    @Schema(description = "출발주소(표시)")
    private String fromAddressDisp;
    @Schema(description = "출발주소1")
    private String fromAddress1;
    @Schema(description = "출발주소2")
    private String fromAddress2;
    @Schema(description = "출발전화1(표시)")
    private String fromPhone1Disp;
    @Schema(description = "출발전화1")
    private String fromPhone1;
    @Schema(description = "출발전화2(표시)")
    private String fromPhone2Disp;
    @Schema(description = "출발전화2")
    private String fromPhone2;
    @Schema(description = "출발팩스")
    private String fromFax;
    @Schema(description = "출발사업자번호")
    private String fromVatno;
    @Schema(description = "출발사업자명")
    private String fromVatowner;
    @Schema(description = "출발사업자유형")
    private String fromVattype;
    @Schema(description = "출발사업자구분")
    private String fromVatcategory;
    @Schema(description = "출발사업자주소1")
    private String fromVataddress1;
    @Schema(description = "출발사업자주소2")
    private String fromVataddress2;
    @Schema(description = "출발사업자전화")
    private String fromVatphone;
    @Schema(description = "출발사업자팩스")
    private String fromVatfax;
    @Schema(description = "출발담당자1")
    private String fromEmpname1;
    @Schema(description = "출발담당자2")
    private String fromEmpname2;
    @Schema(description = "출발담당자전화1")
    private String fromEmpphone1;
    @Schema(description = "출발담당자전화2")
    private String fromEmpphone2;
    @Schema(description = "도착거래처코드")
    private String toCustkey;
    @Schema(description = "도착거래처명")
    private String toCustname;
    @Schema(description = "도착거래처유형")
    private String toCusttype;
    @Schema(description = "도착조직코드")
    private String toOrganize;
    @Schema(description = "도착청구거래처코드")
    private String toBilltokey;
    @Schema(description = "도착국가")
    private String toCountry;
    @Schema(description = "도착도/시")
    private String toState;
    @Schema(description = "도착시/군/구")
    private String toCity;
    @Schema(description = "도착우편번호")
    private String toZipcode;
    @Schema(description = "도착주소(표시)")
    private String toAddressDisp;
    @Schema(description = "도착주소1")
    private String toAddress1;
    @Schema(description = "도착주소2")
    private String toAddress2;
    @Schema(description = "도착전화1(표시)")
    private String toPhone1Disp;
    @Schema(description = "도착전화1")
    private String toPhone1;
    @Schema(description = "도착전화2(표시)")
    private String toPhone2Disp;
    @Schema(description = "도착전화2")
    private String toPhone2;
    @Schema(description = "도착팩스")
    private String toFax;
    @Schema(description = "도착사업자번호")
    private String toVatno;
    @Schema(description = "도착사업자명")
    private String toVatowner;
    @Schema(description = "도착사업자유형")
    private String toVattype;
    @Schema(description = "도착사업자구분")
    private String toVatcategory;
    @Schema(description = "도착사업자주소1")
    private String toVataddress1;
    @Schema(description = "도착사업자주소2")
    private String toVataddress2;
    @Schema(description = "도착사업자전화")
    private String toVatphone;
    @Schema(description = "도착사업자팩스")
    private String toVatfax;
    @Schema(description = "도착담당자1")
    private String toEmpname1;
    @Schema(description = "도착담당자2")
    private String toEmpname2;
    @Schema(description = "도착담당자전화1")
    private String toEmpphone1;
    @Schema(description = "도착담당자전화2")
    private String toEmpphone2;
    @Schema(description = "우선순위")
    private String priority;
    @Schema(description = "채널")
    private String channel;
    @Schema(description = "주문수량")
    private String orderqty;
    @Schema(description = "미처리수량")
    private String openqty;
    @Schema(description = "처리수량")
    private String processqty;
    @Schema(description = "작업수량")
    private String workqty;
    @Schema(description = "검수수량")
    private String inspectqty;
    @Schema(description = "확정수량")
    private String confirmqty;
    @Schema(description = "송장수량")
    private String invoiceqty;
    @Schema(description = "반품수량")
    private String returnqty;
    @Schema(description = "할인율")
    private String dcrate;
    @Schema(description = "마진율")
    private String marginrate;
    @Schema(description = "배송메모")
    private String deliverymemo;
    @Schema(description = "요청메모")
    private String requestmemo;
    @Schema(description = "확정일자")
    private String confirmdate;
    @Schema(description = "매입일자")
    private String purchasedate;
    @Schema(description = "전표일자")
    private String postingdate;
    @Schema(description = "유효일자")
    private String effectivedate;
    @Schema(description = "납품일자")
    private String deliverydate;
    @Schema(description = "배송경로")
    private String deliveryroute;
    @Schema(description = "배송유형")
    private String deliverytype;
    @Schema(description = "배송그룹")
    private String deliverygroup;
    @Schema(description = "배송비유형")
    private String deliveryfeetype;
    @Schema(description = "배송비")
    private String deliveryfee;
    @Schema(description = "메모1")
    private String memo1;
    @Schema(description = "메모2")
    private String memo2;
    @Schema(description = "참조1")
    private String reference01;
    @Schema(description = "참조2")
    private String reference02;
    @Schema(description = "참조3")
    private String reference03;
    @Schema(description = "참조4")
    private String reference04;
    @Schema(description = "참조5")
    private String reference05;
    @Schema(description = "참조6")
    private String reference06;
    @Schema(description = "참조7")
    private String reference07;
    @Schema(description = "참조8")
    private String reference08;
    @Schema(description = "참조9")
    private String reference09;
    @Schema(description = "참조10")
    private String reference10;
    @Schema(description = "상태")
    private String status;
    @Schema(description = "삭제여부")
    private String delYn;
    @Schema(description = "OMS_FLAG")
    private String omsFlag;
    @Schema(description = "IF_FLAG")
    private String ifFlag;
    @Schema(description = "IF_CNT")
    private String ifCnt;
    @Schema(description = "IF_DATE")
    private String ifDate;
    @Schema(description = "IF_MEMO")
    private String ifMemo;
    @Schema(description = "IF_RECEIVE_TYPE")
    private String ifReceiveType;
    @Schema(description = "IF_RECEIVE_FILE")
    private String ifReceiveFile;
    @Schema(description = "IF_AUDIT_FILE")
    private String ifAuditFile;
    @Schema(description = "IF_SEND_TYPE")
    private String ifSendType;
    @Schema(description = "IF_SEND_FILE")
    private String ifSendFile;
    @Schema(description = "PROCPOSS_YN")
    private String procpossYn;
    @Schema(description = "PROCPOSS_MSG")
    private String procpossMsg;
    @Schema(description = "TRAFFICCOP")
    private String trafficcop;
    @Schema(description = "ARCHIVECOP")
    private String archivecop;
    @Schema(description = "등록일자")
    private String adddate;
    @Schema(description = "수정일자")
    private String editdate;
    @Schema(description = "등록자")
    private String addwho;
    @Schema(description = "수정자")
    private String editwho;
    
    /*
     * KX 실적현황 탭
     * */
    @JsonAlias({"ORGANIZE_NAME"})
    @Schema(description = "창고명")
    private String organizeName;
    @JsonAlias({"KX_DCCODE"})
    @Schema(description = "센터코드(KX)")
    private String kxDccode;
    @Schema(description = "품목번호")
    private String docline;
    @Schema(description = "출고수량")
    private String shippedqty;
    @Schema(description = "소유권이전번호")
    private String transferkey;
    @JsonAlias({"PROC_TIME"})
    @Schema(description = "최초 실적 처리시간")
    private String procTime;
    @JsonAlias({"CREATE_TIME"})
    @Schema(description = "I/F내역 생성시간")
    private String createTime;
    @Schema(description = "소유권이전번호")
    private String orderkey;
    @Schema(description = "소유권이전품목번호")
    private String orderlinenumber;
    @Schema(description = "상품코드")
    private String sku;
    @Schema(description = "상품명")
    private String skuname;
    @Schema(description = "저장단위")
    private String storagetype;
    @Schema(description = "단위")
    private String uom;
    
    
    private String line01;

    private String serialyn;
    
    private String plantDescr;
    
    private String lottable01;
    
    private String cancelqty;
    
    private String closeyn;
    
    private String orderadjustqty;
}