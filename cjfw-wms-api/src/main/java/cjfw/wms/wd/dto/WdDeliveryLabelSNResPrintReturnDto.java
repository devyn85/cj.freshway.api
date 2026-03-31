package cjfw.wms.wd.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.10.15 
 * @description : 이력배송라벨출력 회수리스트 출력 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.15 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "이력배송라벨출력 회수리스트 출력 결과")
public class WdDeliveryLabelSNResPrintReturnDto extends CommonDto{
	/**
     * DC코드
     */
    @Schema(description = "DC코드", example = "DC01")
    private String dccode;

    /**
     * DC명
     */
    @Schema(description = "DC명", example = "군포DC")
    private String dcname;

    /**
     * 고객사코드
     */
    @Schema(description = "고객사코드", example = "TO_CUSTKEY01")
    private String toCustkey;

    /**
     * 고객사명
     */
    @Schema(description = "고객사명", example = "CJ대한통운")
    private String toCustname;

    /**
     * SKU
     */
    @Schema(description = "SKU", example = "ITEM0001")
    private String sku;

    /**
     * SKU명
     */
    @Schema(description = "SKU명", example = "새우깡")
    private String skuname;

    /**
     * 배송그룹
     */
    @Schema(description = "배송그룹", example = "G1")
    private String deliverygroup;

    /**
     * 상태
     */
    @Schema(description = "상태", example = "배송라벨회수")
    private String status;

    /**
     * 라벨 재발행용 INVOICENO
     */
    @Schema(description = "라벨 재발행용 INVOICENO", example = "INVOICE_PRE_001")
    private String invoicenoPre;

    /**
     * 라벨 재발행용 수량
     */
    @Schema(description = "라벨 재발행용 수량", example = "10.00 BOX")
    private String invoiceqtyPre;

    /**
     * 인보이스 번호
     */
    @Schema(description = "인보이스 번호", example = "INVOICE0001")
    private String invoiceno;

    /**
     * 인보이스 수량
     */
    @Schema(description = "인보이스 수량", example = "5.00 BOX")
    private String invoiceqty;

    /**
     * 단위
     */
    @Schema(description = "단위", example = "BOX")
    private String storeruom;

    /**
     * 재고ID
     */
    @Schema(description = "재고ID", example = "STOCKID001")
    private String stockid;

    /**
     * 시리얼번호
     */
    @Schema(description = "시리얼번호", example = "SN0001")
    private String serialno;

    /**
     * 취소수량
     */
    @Schema(description = "취소수량", example = "2.00 BOX")
    private String cancelqty;
}
