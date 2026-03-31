package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.23 
 * @description : 배송라벨삭제현황 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.23 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "배송라벨삭제현황 목록 결과")
public class WdDeliveryLabelDelResDto extends CommonDto {
	@Schema(description = "물류센터", example = "DC001")
    private String dccode;
    @Schema(description = "인보이스번호", example = "INV2025062300001")
    private String invoiceno;
    @Schema(description = "주문번호", example = "ORD0001")
    private String docnoWd;
    @Schema(description = "품목번호", example = "1")
    private String docline;
    @Schema(description = "피킹리스트번호", example = "PKL001")
    private String pickListNo;
    @Schema(description = "관리처코드", example = "CUST001")
    private String toCustkeyWd;
    @Schema(description = "배송인도처명", example = "고객사명")
    private String tocustname;
    @Schema(description = "상품코드", example = "ITEM001")
    private String sku;
    @Schema(description = "상품명칭", example = "상품명")
    private String skuname;
    @Schema(description = "주문단위", example = "EA")
    private String storeruom;
    @Schema(description = "주문수량", example = "10")
    private BigDecimal orderqty;
    @Schema(description = "마감수량", example = "10")
    private BigDecimal openqty;
    @Schema(description = "차이수량", example = "10")
    private BigDecimal diffqty;
    @Schema(description = "POP번호", example = "POP001")
    private String deliverygroup;
    @Schema(description = "차량번호", example = "123가4567")
    private String carno;
    @Schema(description = "출력시간", example = "2025-06-23 10:00:00")
    private String printtime;
    @Schema(description = "출력수량", example = "5")
    private BigDecimal printqty;
}
