package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.11.15 
 * @description : 배송라벨출력-분류표출력 상세 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.15 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "배송라벨출력-분류표출력 상세 결과")
public class WdDeliveryLabelResTab1DetailDto extends CommonDto{
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/**
	 * tasksystem
	 */
	@Schema(description = "tasksystem", example = "PDA_STO")
	private String tasksystem;
	/**
     * 송장번호
     */
    @Schema(description = "송장번호", example = "INV123456789")
    private String invoiceno;
    /**
     * 고객키
     */
    @Schema(description = "고객키", example = "CUST001")
    private String custkey;
    /**
     * 고객명
     */
    @Schema(description = "고객명", example = "홍길동")
    private String custname;
    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "SKU001")
    private String sku;
    /**
     * 상품명
     */
    @Schema(description = "상품명", example = "샘플상품")
    private String skuname;
    /**
     * 센터코드
     */
    @Schema(description = "센터코드", example = "DC01")
    private String dccode;
    /**
     * storagetype
     */
    @Schema(description = "storagetype", example = "ST01")
    private String storagetype;
    /**
     * storagetypedesc
     */
    @Schema(description = "storagetypedesc", example = "보관유형설명")
    private String storagetypedesc;
    /**
     * 문서번호
     */
    @Schema(description = "문서번호", example = "DOC12345")
    private String docno;
    /**
     * 문서라인
     */
    @Schema(description = "문서라인", example = "0001")
    private String docline;
    /**
     * 단위
     */
    @Schema(description = "단위", example = "EA")
    private String storeruom;
    /**
     * 주문수량
     */
    @Schema(description = "주문수량", example = "100")
    private BigDecimal orderqty;
    /**
     * 출력수량
     */
    @Schema(description = "출력수량", example = "100")
    private BigDecimal printedqty;
    /**
     * 출력여부
     */
    @Schema(description = "출력여부", example = "Y")
    private String printYn;
    /**
     * 삭제여부
     */
    @Schema(description = "삭제여부", example = "N")
    private String delYn;
    /**
     * 상태색상
     */
    @Schema(description = "상태색상", example = "BLACK")
    private String statusColor;
    /**
     * 체크박스편집
     */
    @Schema(description = "체크박스편집", example = "checkbox")
    private String checkEdit;
    /**
     * 배송그룹
     */
    @Schema(description = "배송그룹", example = "CJ대한통운")
    private String deliverygroup;
    /**
     * 차량번호
     */
    @Schema(description = "차량번호", example = "12가1234")
    private String carno;
    /**
     * 차량그룹
     */
    @Schema(description = "차량그룹", example = "GROUP01")
    private String cargroup;
    /**
     * 출력일시
     */
    @Schema(description = "출력일시", example = "2025-11-15 10:00:00")
    private String printDate;
    /**
     * 출력횟수
     */
    @Schema(description = "출력횟수", example = "1")
    private BigDecimal printCnt;
    /**
     * SMS여부
     */
    @Schema(description = "SMS여부", example = "N")
    private String smsYn;
    /**
     * STO주문여부
     */
    @Schema(description = "STO주문여부", example = "N")
    private String stoYn;
}
