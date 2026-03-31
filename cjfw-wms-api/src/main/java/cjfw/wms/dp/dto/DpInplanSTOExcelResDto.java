package cjfw.wms.dp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.19 
 * @description : 광역입고현황 엑셀 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.19 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "광역입고현황 엑셀 결과")
public class DpInplanSTOExcelResDto {
	@Schema(description = "구매유형", example = "일반구매")
    private String ordertypeName;
    @Schema(description = "광역입고일자", example = "2025-06-19")
    private String slipdt;
    @Schema(description = "광역주문번호", example = "DOC001")
    private String docno;
    @Schema(description = "물류센터", example = "DC01")
    private String fromDccode;
    @Schema(description = "물류센터명", example = "서울물류센터")
    private String fromDcname;
    @Schema(description = "창고", example = "WH001")
    private String fromCustkey;
    @Schema(description = "창고명", example = "제1창고")
    private String fromCustname;
    @Schema(description = "물류센터", example = "DC02")
    private String toDccode;
    @Schema(description = "물류센터명", example = "부산물류센터")
    private String toDcname;
    @Schema(description = "창고", example = "WH002")
    private String toCustkey;
    @Schema(description = "창고명", example = "제2창고")
    private String toCustname;
    @Schema(description = "상품코드", example = "ITEM001")
    private String sku;
    @Schema(description = "상품명칭", example = "프리미엄 쌀 10kg")
    private String skuname;
    @Schema(description = "플랜트", example = "[PLT01]김포공장")
    private String plantDescr;
    @Schema(description = "저장유무", example = "저장")
    private String channelname;
    @Schema(description = "저장조건", example = "상온")
    private String storagetypename;
    @Schema(description = "박스입수", example = "10")
    private String qtyperbox;
    @Schema(description = "구매단위", example = "EA")
    private String uom;
    @Schema(description = "평균중량", example = "0")
    private String avgweight;
    @Schema(description = "환산주문박스", example = "0")
    private String calorderbox;
    @Schema(description = "환산확정박스", example = "0")
    private String calconfirmbox;
    @Schema(description = "실박스", example = "0")
    private String realbox;
    @Schema(description = "주문수량", example = "100")
    private String orderqty;
    @Schema(description = "검수량출고", example = "95")
    private String inspectqtyWd;
    @Schema(description = "검수량입고", example = "90")
    private String inspectqty;
    @Schema(description = "확정수량출고", example = "95")
    private String confirmqtyWd;
    @Schema(description = "확정수량입고", example = "90")
    private String confirmqty;
    @Schema(description = "충량출고", example = "500.50")
    private String confirmweightWd;
    @Schema(description = "중량입고", example = "450.75")
    private String confirmweight;
    @Schema(description = "진행상태", example = "완료")
    private String statusname;
    @Schema(description = "생성인", example = "홍길동")
    private String addwho;
    @Schema(description = "등록일자", example = "2025-06-19T10:00:00")
    private String adddate;
    @Schema(description = "최종변경자", example = "김철수")
    private String editwho;
    @Schema(description = "최종변경시간", example = "2025-06-19T11:30:00")
    private String editdate;
    @Schema(description = "", example = "ORG001")
    private String fromOrganize;
    @Schema(description = "", example = "ORG002")
    private String toOrganize;
    @Schema(description = "", example = "2025-06-18")
    private String docdt;
    @Schema(description = "", example = "ORDER_TYPE_A")
    private String ordertype;
    @Schema(description = "", example = "COMPLETED")
    private String status;
    @Schema(description = "", example = "STR001")
    private String storerkey;
    @Schema(description = "", example = "DOC_TYPE_IN")
    private String doctype;
    @Schema(description = "", example = "1")
    private String docline;
    @Schema(description = "", example = "CHANNEL_ONLINE")
    private String channel;
    @Schema(description = "", example = "NORMAL")
    private String storagetype;
    @Schema(description = "", example = "COMPLETED")
    private String status2;
    @Schema(description = "", example = "PLANT01")
    private String plant;
    	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
