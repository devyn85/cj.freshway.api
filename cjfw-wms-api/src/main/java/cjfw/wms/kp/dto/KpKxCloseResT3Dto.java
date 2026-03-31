package cjfw.wms.kp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net)
 * @date : 2025.12.01
 * @description : 문서내역정보 조회(DOC) RES DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "문서내역정보 조회(DOC) RES DTO")
public class KpKxCloseResT3Dto {
    @Schema(description = "체크여부")
    private String checkyn;

    @Schema(description = "화주코드")
    private String storerkey;

    @Schema(description = "센터코드")
    private String dccode;

    @Schema(description = "조직코드")
    private String organize;

    @Schema(description = "문서일자")
    private String docdt;

    @Schema(description = "문서구분")
    private String doctype;

    @Schema(description = "문서번호")
    private String docno;

    @Schema(description = "문서라인")
    private String docline;

    @Schema(description = "SKU")
    private String sku;

    @Schema(description = "단위")
    private String uom;

    @Schema(description = "주문수량")
    private String orderqty;

    @Schema(description = "주문조정수량")
    private String orderadjustqty;

    @Schema(description = "확정수량")
    private String confirmqty;

    @Schema(description = "센터코드(중복)")
    private String dccode2;

    @Schema(description = "조직코드(중복)")
    private String organize2;

    @Schema(description = "공장코드")
    private String plant;

    @Schema(description = "저장위치")
    private String storageloc;

    @Schema(description = "화주주문수량")
    private String storerorderqty;

    @Schema(description = "화주조정수량")
    private String storeradjustqty;

    @Schema(description = "화주미확정수량")
    private String storeropenqty;

    @Schema(description = "화주확정수량")
    private String storerconfirmqty;

    @Schema(description = "화주단위")
    private String storeruom;

    @Schema(description = "공장단가")
    private String factoryprice;

    @Schema(description = "매입단가")
    private String purchaseprice;

    @Schema(description = "판매단가")
    private String saleprice;

    @Schema(description = "부가세")
    private String vat;

    @Schema(description = "채널")
    private String channel;

    @Schema(description = "로트정보")
    private String lottable01;

    @Schema(description = "납품일자")
    private String deliverydate;

    @Schema(description = "삭제여부")
    private String delYn;

    @Schema(description = "상태")
    private String status;

    @Schema(description = "취소수량")
    private String cancelqty;

    @Schema(description = "사유코드")
    private String reasoncode;

    @Schema(description = "사유메시지")
    private String reasonmsg;

    @Schema(description = "작업공정코드")
    private String workprocesscode;

    @Schema(description = "아카이브여부")
    private String archivecop;
    
    @Schema(description = "IF_ID")
    private String ifId;

    @Schema(description = "IF_FLAG")
    private String ifFlag;

    @Schema(description = "IF_DATE")
    private String ifDate;

    @Schema(description = "IF_MEMO")
    private String ifMemo;

    @Schema(description = "SLIPNO")
    private String slipno;

    @Schema(description = "SLIPLINE")
    private String slipline;

    @Schema(description = "프로세스 타입")
    private String procType;

    @Schema(description = "시리얼키")
    private String serialkey;
    
    /** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	
	
	
}
