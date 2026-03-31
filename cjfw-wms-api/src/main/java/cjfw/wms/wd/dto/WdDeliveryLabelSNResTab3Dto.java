package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.10.15 
 * @description : 이력배송라벨출력-회수리스트 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.15 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "이력배송라벨출력-회수리스트 목록 결과")
public class WdDeliveryLabelSNResTab3Dto extends CommonDto{
	/**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "01")
    private String dccode;
    /**
     * 물류센터명
     */
    @Schema(description = "물류센터명", example = "강남DC")
    private String dcname;
    /**
     * 관리처코드
     */
    @Schema(description = "관리처코드", example = "CUSTKEY001")
    private String toCustkey;
    /**
     * 관리처명(배송인도처)
     */
    @Schema(description = "관리처명(배송인도처)", example = "CJ푸드")
    private String toCustname;
    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "SKU12345")
    private String sku;
    /**
     * 상품명칭
     */
    @Schema(description = "상품명칭", example = "샘플상품A")
    private String skuname;
    /**
     * POP번호
     */
    @Schema(description = "POP번호", example = "D01")
    private String deliverygroup;
    /**
     * 진행상태
     */
    @Schema(description = "진행상태", example = "배송라벨회수")
    private String status;
    /**
     * 회수대상-인보이스번호
     */
    @Schema(description = "회수대상-인보이스번호", example = "010101000000000000101001000")
    private String invoicenoPre;
    /**
     * 회수대상-수량
     */
    @Schema(description = "회수대상-수량", example = "10")
    private BigDecimal invoiceqtyPre;
    /**
     * 교환대상-인보이스번호
     */
    @Schema(description = "교환대상-인보이스번호", example = "010101000000000000101010000")
    private String invoiceno;
    /**
     * 교환대상-수량
     */
    @Schema(description = "교환대상-수량", example = "5")
    private BigDecimal invoiceqty;
    /**
     * 단위
     */
    @Schema(description = "단위", example = "EA")
    private String storeruom;
    /**
     * 회수대상-이력번호
     */
    @Schema(description = "회수대상-이력번호", example = "SN12345")
    private String serialno;
    /**
     * 회수대상-바코드
     */
    @Schema(description = "회수대상-바코드", example = "STOCKID001")
    private String stockid;
    /**
     * 회수대상-수량
     */
    @Schema(description = "회수대상-수량", example = "5")
    private BigDecimal cancelqty;
}
