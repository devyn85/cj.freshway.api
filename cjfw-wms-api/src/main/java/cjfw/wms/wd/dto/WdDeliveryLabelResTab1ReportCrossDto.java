package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.11.15 
 * @description : 배송분류표회수리스트 일반 조회 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.15 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "배송분류표회수리스트 일반 조회 결과")
public class WdDeliveryLabelResTab1ReportCrossDto extends CommonDto{
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/**
     * 리포트 출력순서
     */
    @Schema(description = "리포트 출력순서", example = "1")
    private String reportgubun;
    /**
     * 리포트 타이틀
     */
    @Schema(description = "리포트 타이틀", example = "07 / 21 부분 삭제 리스트 (냉장)")
    private String reporttitle;
    /**
     * 저장조건
     */
    @Schema(description = "저장조건", example = "C")
    private String storagetype;
    /**
     * 저장조건명
     */
    @Schema(description = "저장조건명", example = "냉장")
    private String storagename;
    /**
     * POP번호
     */
    @Schema(description = "POP번호", example = "POP001=>POP002")
    private String deliverygroup;
    /**
     * 상품
     */
    @Schema(description = "상품", example = "SKU001")
    private String sku;
    /**
     * 상품명
     */
    @Schema(description = "상품명", example = "샘플상품")
    private String skuname;
    /**
     * 거래처코드
     */
    @Schema(description = "거래처코드", example = "CUST123")
    private String custkey;
    /**
     * 거래처명
     */
    @Schema(description = "거래처명", example = "샘플거래처")
    private String custname;
    /**
     * 단위
     */
    @Schema(description = "단위", example = "EA")
    private String uom;
    /**
     * 초기
     */
    @Schema(description = "초기", example = "100")
    private BigDecimal storerorderqty;
    /**
     * 마감
     */
    @Schema(description = "마감", example = "80")
    private BigDecimal storeropenqty;
    /**
     * 삭제
     */
    @Schema(description = "삭제", example = "20")
    private BigDecimal storeradjustqty;
    /**
     * 로케이션(크로스)
     */
    @Schema(description = "로케이션(크로스)", example = "A-01-01")
    private String location;
    /**
     * 박스입수
     */
    @Schema(description = "박스입수", example = "10")
    private BigDecimal qtyperbox;
}
