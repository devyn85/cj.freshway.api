package cjfw.wms.dp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.18 
 * @description : 광역입고현황 주문현황 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.16 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "광역입고현황 주문현황 결과")
public class DpInplanSTODetailResDto {
	/**
     * 품목번호
     */
    @Schema(description = "품목번호", example = "1")
    private String docline;
    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "SKU001")
    private String sku;
    /**
     * 상품명칭
     */
    @Schema(description = "상품명칭", example = "생수 500ml")
    private String skuname;
    /**
     * 플랜트
     */
    @Schema(description = "플랜트", example = "[PLANT01]제1공장")
    private String plantDescr;
    /**
     * 저장유무
     */
    @Schema(description = "저장유무", example = "Y")
    private String channelname;
    /**
     * 저장조건
     */
    @Schema(description = "저장조건", example = "냉장")
    private String storagetypeName;
    /**
     * 이체단위
     */
    @Schema(description = "이체단위", example = "EA")
    private String uom;
    /**
     * 주문수량
     */
    @Schema(description = "주문수량", example = "100")
    private String orderqty;
    /**
     * 검수량출고
     */
    @Schema(description = "검수량출고", example = "90")
    private String inspectqtyWd;
    /**
     * 검수량입고
     */
    @Schema(description = "검수량입고", example = "90")
    private String inspectqty;
    /**
     * 확정수량출고
     */
    @Schema(description = "확정수량출고", example = "85")
    private String confirmqtyWd;
    /**
     * 확정수량입고
     */
    @Schema(description = "확정수량입고", example = "85")
    private String confirmqty;
    /**
     * 충량출고
     */
    @Schema(description = "충량출고", example = "85.5")
    private String confirmweightWd;
    /**
     * 중량입고
     */
    @Schema(description = "중량입고", example = "85.5")
    private String confirmweight;
    /**
     * 진행상태출고
     */
    @Schema(description = "진행상태출고", example = "완료")
    private String statusnameWd;
    /**
     * 진행상태입고
     */
    @Schema(description = "진행상태입고", example = "완료")
    private String statusname;
    /**
     * CHANNEL
     */
    @Schema(description = "CHANNEL", example = "ONLINE")
    private String channel;
    /**
     * STORAGETYPE
     */
    @Schema(description = "STORAGETYPE", example = "COLD")
    private String storagetype;
    /**
     * STATUS
     */
    @Schema(description = "STATUS", example = "FINISH")
    private String status;
    /**
     * QTYPERBOX
     */
    @Schema(description = "QTYPERBOX", example = "12")
    private String qtyperbox;
    /**
     * PLANT
     */
    @Schema(description = "PLANT", example = "PLANT01")
    private String plant;
    /**
     * DEL_YN
     */
    @Schema(description = "DEL_YN", example = "N")
    private String delYn;
    	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
