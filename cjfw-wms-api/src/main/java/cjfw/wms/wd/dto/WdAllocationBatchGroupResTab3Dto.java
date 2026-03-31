package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.11 
 * @description : 출고재고분배-피킹유형 미정의 관리처 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.11 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "출고재고분배-피킹유형 미정의 관리처 목록 결과")
public class WdAllocationBatchGroupResTab3Dto extends CommonDto{
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	@Schema(description = "물류센터", example = "DCC01")
    private String dccode;
    @Schema(description = "배송일자", example = "2025-01-01")
    private String deliverydate;
    @Schema(description = "플랜트코드", example = "PLANT01")
    private String plant;
    @Schema(description = "플랜트명", example = "ABC 플랜트")
    private String description;
    @Schema(description = "플랜트", example = "PLANT02")
    private String pickbatchgroupPlant;
    @Schema(description = "거래처별POP등록여부", example = "Y")
    private String custxpopYn;
    @Schema(description = "저장조건", example = "보관")
    private String storagetype;
    @Schema(description = "원거리유형", example = "장거리")
    private String distancetype;
    @Schema(description = "관리처", example = "CUST001")
    private String toCustkey;
    @Schema(description = "관리처명", example = "대한민국 관리처")
    private String toCustname;
    @Schema(description = "피킹(원거리)유형", example = "단거리")
    private String mngDistancetype;
    @Schema(description = "주문번호", example = "ORD001")
    private String docno;
    @Schema(description = "우편번호", example = "ORD001")
    private String zipcode;
    @Schema(description = "주소1", example = "ORD001")
    private String address1;
    @Schema(description = "주소2", example = "ORD001")
    private String address2;
    @Schema(description = "주소3", example = "ORD001")
    private String address3;
    @Schema(description = "주소4", example = "ORD001")
    private String address4;
    @Schema(description = "serialkey", example = "ORD001")
    private BigDecimal serialkey;
    
    @Schema(description = "", example = "STORER001")
    private String storerkey;
    @Schema(description = "", example = "25")
    private String custstrategy4;
}
