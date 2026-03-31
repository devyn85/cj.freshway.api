package cjfw.wms.mg.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.07.31
 * @description : 입고라벨출력(광역) Request DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.31 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "입고라벨출력(광역) Detail Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MgModifyLogResSkuDto {

    /** 시리얼키 */
    @Schema(description = "시리얼키")
    private String serialkey;

    /** 수정일자 */
    @Schema(description = "수정일자")
    private String modifydate;

    /** 화주코드 */
    @Schema(description = "화주코드")
    private String storerkey;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명")
    private String skuname;

    /** 단위 */
    @Schema(description = "단위")
    private String uom;

    /** 출발시리얼번호 */
    @Schema(description = "출발시리얼번호")
    private String fromSerialno;

    /** 출발변환시리얼번호 */
    @Schema(description = "출발변환시리얼번호")
    private String fromConvserialno;

    /** 출발시리얼레벨 */
    @Schema(description = "출발시리얼레벨")
    private String fromSeriallevel;

    /** 출발시리얼타입 */
    @Schema(description = "출발시리얼타입")
    private String fromSerialtype;

    /** 출발공장명 */
    @Schema(description = "출발공장명")
    private String fromFactoryname;

    /** 출발원산지 */
    @Schema(description = "출발원산지")
    private String fromPlaceoforigin;

    /** 출발색상설명 */
    @Schema(description = "출발색상설명")
    private String fromColordescr;

    /** 출발유효일자 */
    @Schema(description = "출발유효일자")
    private String fromFromvaliddt;

    /** 출발유효일자2 */
    @Schema(description = "출발유효일자2")
    private String fromTovaliddt;

    /** 출발도축일자 */
    @Schema(description = "출발도축일자")
    private String fromButcherydt;

    /** 출발계약거래처 */
    @Schema(description = "출발계약거래처")
    private String fromContractcompany;

    /** 출발계약거래처명 */
    @Schema(description = "출발계약거래처명")
    private String fromContractcompanyname;

    /** 출발계약유형 */
    @Schema(description = "출발계약유형")
    private String fromContracttype;

    /** 도착시리얼번호 */
    @Schema(description = "도착시리얼번호")
    private String toSerialno;

    /** 도착변환시리얼번호 */
    @Schema(description = "도착변환시리얼번호")
    private String toConvserialno;

    /** 도착시리얼레벨 */
    @Schema(description = "도착시리얼레벨")
    private String toSeriallevel;

    /** 도착시리얼타입 */
    @Schema(description = "도착시리얼타입")
    private String toSerialtype;

    /** 도착공장명 */
    @Schema(description = "도착공장명")
    private String toFactoryname;

    /** 도착원산지 */
    @Schema(description = "도착원산지")
    private String toPlaceoforigin;

    /** 도착색상설명 */
    @Schema(description = "도착색상설명")
    private String toColordescr;

    /** 도착유효일자 */
    @Schema(description = "도착유효일자")
    private String toFromvaliddt;

    /** 도착유효일자2 */
    @Schema(description = "도착유효일자2")
    private String toTovaliddt;

    /** 도착도축일자 */
    @Schema(description = "도착도축일자")
    private String toButcherydt;

    /** 도착계약거래처 */
    @Schema(description = "도착계약거래처")
    private String toContractcompany;

    /** 도착계약거래처명 */
    @Schema(description = "도착계약거래처명")
    private String toContractcompanyname;

    /** 도착계약유형 */
    @Schema(description = "도착계약유형")
    private String toContracttype;

    /** 등록일자 */
    @Schema(description = "등록일자")
    private String editdate;

    /** 등록자 */
    @Schema(description = "등록자")
    private String editwho;

    /** 사용자명 */
    @Schema(description = "사용자명")
    private String username;

    /** 사유코드 */
    @Schema(description = "사유코드")
    private String reasoncode;

    /** 사유메시지 */
    @Schema(description = "사유메시지")
    private String reasonmsg;

    /** 바코드 */
    @Schema(description = "바코드")
    private String barcode;

}
