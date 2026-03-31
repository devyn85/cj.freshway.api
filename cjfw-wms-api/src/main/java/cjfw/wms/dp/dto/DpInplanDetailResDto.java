package cjfw.wms.dp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.16 
 * @description : 입고진행현황 상세 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.16 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "입고진행현황 상세 결과")
public class DpInplanDetailResDto {
	@Schema(description = "품목번호", example = "1")
    private String docline;
    @Schema(description = "상품코드", example = "ITEM001")
    private String sku;
    @Schema(description = "상품명칭", example = "노트북")
    private String skuname;
    @Schema(description = "플랜트", example = "[PLANT01]메인플랜트")
    private String plantDescr;
    @Schema(description = "저장유무", example = "저장완료")
    private String channelName;
    @Schema(description = "저장조건", example = "상온")
    private String storagetypeName;
    @Schema(description = "serialtype", example = "추적필요")
    private String serialtype;
    @Schema(description = "유통이력", example = "추적필요")
    private String serialtypeName;
    @Schema(description = "박스입수", example = "10")
    private String qtyperbox;
    @Schema(description = "구매단위", example = "EA")
    private String uom;
    @Schema(description = "구매수량", example = "100")
    private String orderqty;
    @Schema(description = "발주수량", example = "120")
    private String purchaseqty;
    @Schema(description = "결품수량", example = "5")
    private String shortageqty;
    @Schema(description = "입고예정량", example = "95")
    private String orderadjustqty;
    @Schema(description = "입고예정확정량", example = "90")
    private String openqty;
    @Schema(description = "입고검수량", example = "88")
    private String inspectqty;
    @Schema(description = "입고확정량", example = "85")
    private String confirmqty;
    @Schema(description = "입고중량", example = "850.5")
    private String confirmweight;
    @Schema(description = "SAP실적전송", example = "Y")
    private String ifAuditFile;
    @Schema(description = "전송시간", example = "2025-06-16 10:30:00")
    private String ifSendFile;
    @Schema(description = "CHANNEL", example = "ONLINE")
    private String channel;
    @Schema(description = "STORAGETYPE", example = "FRZ")
    private String storagetype;
    @Schema(description = "DEL_YN", example = "N")
    private String delYn;
    @Schema(description = "PLANT", example = "P001")
    private String plant;
    	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
