package cjfw.wms.om.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.20 
 * @description : 일배협력사별주문현황 프린트 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.20 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "일배협력사별주문현황 프린트 결과")
public class OmOrderCustDailyPrintResDto {
	/**
     * 출고일자
     */
    @Schema(description = "출고일자", example = "2024-06-20")
    private String deliverydate;
    /**
     * 협력사코드
     */
    @Schema(description = "협력사코드", example = "CUST001")
    private String fromCustkey;
    /**
     * 협력사명
     */
    @Schema(description = "협력사명", example = "ABC협력사")
    private String fromCustname;
    /**
     * 관리처코드
     */
    @Schema(description = "관리처코드", example = "MGMT001")
    private String toCustkey;
    /**
     * 관리처명
     */
    @Schema(description = "관리처명", example = "XYZ관리처")
    private String toCustname;
    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "ITEM001")
    private String sku;
    /**
     * 상품명칭
     */
    @Schema(description = "상품명칭", example = "삼겹살 500g")
    private String skuname;
    /**
     * 저장유무
     */
    @Schema(description = "저장유무", example = "Y")
    private String putawaytype;
    /**
     * 저장조건
     */
    @Schema(description = "저장조건", example = "냉장")
    private String storagetype;
    /**
     * 입수
     */
    @Schema(description = "입수", example = "10")
    private String qtyperbox;
    /**
     * 판매단위
     */
    @Schema(description = "판매단위", example = "EA")
    private String storeruom;
    /**
     * 주문수량
     */
    @Schema(description = "주문수량", example = "50")
    private String storeropenqty;
    /**
     * 주문중량
     */
    @Schema(description = "주문중량", example = "250.5")
    private String storeropenweight;
    /**
     * 출고수량
     */
    @Schema(description = "출고수량", example = "48")
    private String confirmqty;
    /**
     * 출고중량
     */
    @Schema(description = "출고중량", example = "240.2")
    private String confirmweight;
    /**
     * 취소량
     */
    @Schema(description = "취소량", example = "2")
    private String cancelqty;
}
