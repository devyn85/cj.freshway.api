package cjfw.wms.wd.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.14 
 * @description : 배송라벨출력(하나로엑셀) 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.14 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "배송라벨출력(하나로엑셀) 결과")
public class WdDeliveryLabelForceHanaroResDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	@Schema(description = "관리처코드", example = "CUST001")
    private String toCustkey;
    @Schema(description = "납품처명1", example = "하나로마트")
    private String lblCustname1;
    @Schema(description = "상품코드", example = "ITEM001")
    private String lblSku;
    @Schema(description = "상품명1", example = "신선란")
    private String lblSkuname1;
    @Schema(description = "상품명2", example = "대란10구")
    private String lblSkuname2;
    @Schema(description = "입수량", example = "30")
    private String lblQtyperbox;
    @Schema(description = "수량", example = "100")
    private String lblQty;
    @Schema(description = "총주문수량", example = "200")
    private String lblQty2;
    @Schema(description = "EA바코드", example = "1234567890123")
    private String lblEabarcode;
    @Schema(description = "BOX바코드", example = "9876543210987")
    private String lblBoxbarcode;
    @Schema(description = "바코드1", example = "1122334455667")
    private String lblBarcode1;
    @Schema(description = "유통기한", example = "2025-12-31")
    private String lblStockLottable01;
    @Schema(description = "문서번호", example = "DOC001")
    private String docno;
    @Schema(description = "품목번호", example = "LINE001")
    private String docline;
    @Schema(description = "배송일자", example = "2025-07-14")
    private String lblDeliverydt;
    @Schema(description = "", example = "1")
    private String lblCnt;
}
