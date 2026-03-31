package cjfw.wms.ib.dto;

import cjfw.wms.common.extend.CommonDto;
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
public class IbCloseStockSkuBillPopupResDto extends CommonDto {
	/** 센터코드 */
    @Schema(description = "센터코드")
    private String dcCode;

    /** 저장위치 */
    @Schema(description = "저장위치")
    private String storageLoc;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 수량 */
    @Schema(description = "수량")
    private String orderQty;

    /** 단위 */
    @Schema(description = "단위")
    private String uom;

    /** 참조문서번호(FRBNR) */
    @Schema(description = "참조문서번호(FRBNR)")
    private String convSerialNo;

    /** 이력번호 */
    @Schema(description = "이력번호")
    private String serialNo;

    /** 구매문서번호(EBELN) */
    @Schema(description = "구매문서번호(EBELN)")
    private String zebeln;

    /** 구매문서품목(EBELP) */
    @Schema(description = "구매문서품목(EBELP)")
    private String poLine;

    /** 전기일자(BUDAT) */
    @Schema(description = "전기일자(BUDAT)")
    private String slipDt;

    /** 전표번호(ROWID) */
    @Schema(description = "전표번호(ROWID)")
    private String slipNo;

    /** 전표라인 */
    @Schema(description = "전표라인")
    private String slipLine;

    /** 출고금액 */
    @Schema(description = "출고금액")
    private String zwrbtrOut;

    /** 입고금액 */
    @Schema(description = "입고금액")
    private String zwrbtrIn;

    /** 금액 */
    @Schema(description = "금액")
    private String wrbtr;

    /** 재고금액 */
    @Schema(description = "재고금액")
    private String stockAmt;

    /** 단가 */
    @Schema(description = "단가")
    private String price;

    /** 재고금액메시지 */
    @Schema(description = "재고금액메시지")
    private String stockAmtMsg;

    /** 재고수량 */
    @Schema(description = "재고수량")
    private String stockQty;
    
    /** 타입 */
    @Schema(description = "타입")
    private String ChkAmt;
}
