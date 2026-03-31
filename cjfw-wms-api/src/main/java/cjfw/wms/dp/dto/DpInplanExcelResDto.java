package cjfw.wms.dp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.16 
 * @description : 입고진행현황 엑셀 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.16 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "입고진행현황 엑셀 결과")
public class DpInplanExcelResDto {
	@Schema(description = "입고전표번호", example = "SLIP_20250616_001")
    private String slipno;
    @Schema(description = "구매유형", example = "정상구매")
    private String ordertypeName;
    @Schema(description = "입고일자", example = "2025-06-16")
    private String slipdt;
    @Schema(description = "협력사코드", example = "CUST001")
    private String fromCustkey;
    @Schema(description = "협력사명", example = "한화솔루션")
    private String fromCustname;
    @Schema(description = "진행상태", example = "입고완료")
    private String statusname;
    @Schema(description = "품목번호", example = "001")
    private String docline;
    @Schema(description = "상품코드", example = "ITEM001")
    private String sku;
    @Schema(description = "상품명칭", example = "아반떼")
    private String skuname;
    @Schema(description = "플랜트", example = "[PLANT01]울산공장")
    private String plantDescr;
    @Schema(description = "저장유무", example = "저장완료")
    private String channelName;
    @Schema(description = "저장조건", example = "냉장")
    private String storagetypeName;
    @Schema(description = "유통이력", example = "추적대상")
    private String serialtypeName;
    @Schema(description = "박스입수", example = "12")
    private String qtyperbox;
    @Schema(description = "구매단위", example = "BOX")
    private String uom;
    @Schema(description = "구매수량", example = "100")
    private String orderqty;
    @Schema(description = "발주수량", example = "100")
    private String purchaseQty;
    @Schema(description = "결품수량", example = "0")
    private String shortageqty;
    @Schema(description = "입고예정량", example = "100")
    private String orderadjustqty;
    @Schema(description = "입고예정확정량", example = "100")
    private String openqty;
    @Schema(description = "입고검수량", example = "100")
    private String inspectqty;
    @Schema(description = "입고확정량", example = "100")
    private String confirmqty;
    @Schema(description = "입고중량", example = "500.5")
    private String confirmweight;
    @Schema(description = "SAP실적전송", example = "Y")
    private String ifAuditFile;
    @Schema(description = "전송시간", example = "2025-06-16 14:00:00")
    private String ifSendFile;
    @Schema(description = "ORDERTYPE", example = "NB")
    private String ordertype;
    @Schema(description = "STATUS", example = "COMPLETED")
    private String status;
    @Schema(description = "CHANNEL", example = "ONLINE")
    private String channel;
    @Schema(description = "STORAGETYPE", example = "COLD")
    private String storagetype;
    @Schema(description = "DEL_YN", example = "N")
    private String delYn;
    @Schema(description = "LOC", example = "A-01-01")
    private String loc;
    	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
