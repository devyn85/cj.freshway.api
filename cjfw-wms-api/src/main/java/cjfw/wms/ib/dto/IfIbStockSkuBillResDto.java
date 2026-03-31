package cjfw.wms.ib.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.08.21
 * @description : 마감상태관리 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.21 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "마감상태 관리 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IfIbStockSkuBillResDto extends CommonProcedureDto {

	
	/** 정산월(YYYYMM) */
    @Schema(description = "정산월(YYYYMM)")
    private String zmonth;

    /** 순번 */
    @Schema(description = "순번")
    private String seq;

    /** 구분코드 */
    @Schema(description = "구분코드")
    private String zcat;

    /** 저장위치(LGORT) */
    @Schema(description = "저장위치(LGORT)")
    private String lgort;

    /** 플랜트(WERKS) */
    @Schema(description = "플랜트(WERKS)")
    private String werks;

    /** 자재코드(MATNR) */
    @Schema(description = "자재코드(MATNR)")
    private String matnr;

    /** 참조문서번호(FRBNR: 공급업체 전표번호) */
    @Schema(description = "참조문서번호(FRBNR)")
    private String frbnr;

    /** 이력번호 */
    @Schema(description = "이력번호")
    private String histno;

    /** 구매문서번호(EBELN) */
    @Schema(description = "구매문서번호(EBELN)")
    private String zebeln;

    /** 구매문서품목(EBELP) */
    @Schema(description = "구매문서품목(EBELP)")
    private String zebelp;

    /** 전기일자(BUDAT: YYYYMMDD) */
    @Schema(description = "전기일자(BUDAT)")
    private String budat;

    /** 출고금액(문서통화기준) */
    @Schema(description = "출고금액")
    private String zwrbtrOut;

    /** 입고금액(문서통화기준) */
    @Schema(description = "입고금액")
    private String zwrbtrIn;

    /** 금액(문서통화기준) */
    @Schema(description = "금액(문서통화기준)")
    private String wrbtr;

    /** 수량 */
    @Schema(description = "수량")
    private String menge;

    /** 발주단위(BSTME) */
    @Schema(description = "발주단위(BSTME)")
    private String bstme;

    /** 비고1 */
    @Schema(description = "비고1")
    private String ztext1;

    /** 비고2 */
    @Schema(description = "비고2")
    private String ztext2;

    /** 비고3 */
    @Schema(description = "비고3")
    private String ztext3;

    /** 비고4 */
    @Schema(description = "비고4")
    private String ztext4;

    /** 비고5 */
    @Schema(description = "비고5")
    private String ztext5;
}
