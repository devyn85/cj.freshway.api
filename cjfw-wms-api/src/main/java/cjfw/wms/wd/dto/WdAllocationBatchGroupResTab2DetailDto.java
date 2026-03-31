package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.08 
 * @description : 지정분배 상세 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.10 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "지정분배 상세 결과")
public class WdAllocationBatchGroupResTab2DetailDto extends CommonDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "DC001")
    private String dccode;
    /**
     * 창고
     */
    @Schema(description = "창고", example = "ORG001")
    private String organize;
    /**
     * 로케이션
     */
    @Schema(description = "로케이션", example = "A-01-01")
    private String loc;
    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "ITEM001")
    private String sku;
    /**
     * 상품명칭
     */
    @Schema(description = "상품명칭", example = "남성 티셔츠")
    private String skuname;
    /**
     * 상품분류
     */
    @Schema(description = "상품분류", example = "TP")
    private String mc;
    /**
     * 현재고수량
     */
    @Schema(description = "현재고수량", example = "100")
    private BigDecimal qty;
    /**
     * 가용재고수량
     */
    @Schema(description = "가용재고수량", example = "50")
    private BigDecimal openqty;
    /**
     * 지정량
     */
    @Schema(description = "지정량", example = "0")
    private BigDecimal allocateqty;
    /**
     * 단위
     */
    @Schema(description = "단위", example = "EA")
    private String uom;
    /**
     * 재고위치
     */
    @Schema(description = "재고위치", example = "정상")
    private String stocktypename;
    /**
     * 준일(소비,제조)
     */
    @Schema(description = "준일(소비,제조)", example = "2023-01-01")
    private String lottable01;
    /**
     * 이력번호
     */
    @Schema(description = "이력번호", example = "SERIAL001")
    private String serialno;
    /**
     * 박스바코드
     */
    @Schema(description = "박스바코드", example = "")
    private String boxbarcode;
    @Schema(description = "화주키", example = "STORER001")
    private String storerkey;
    @Schema(description = "지역", example = "AREA001")
    private String area;
    @Schema(description = "LOT", example = "LOT001")
    private String lot;
    @Schema(description = "식별번호 유무", example = "Y")
    private String serialyn;
    @Schema(description = "재고 ID", example = "STOCKID001")
    private String stockid;
    @Schema(description = "재고 등급", example = "A")
    private String stockgrade;
    @Schema(description = "재고 유형", example = "GOOD")
    private String stocktype;
}
