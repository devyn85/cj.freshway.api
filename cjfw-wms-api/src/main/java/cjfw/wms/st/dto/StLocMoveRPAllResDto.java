package cjfw.wms.st.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.09.28 
 * @description : 출고재고보충(전센터) 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.28 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "출고재고보충(전센터) 결과")
public class StLocMoveRPAllResDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	/**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "Example Value")
    private String dcname;
    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "Example Value")
    private String sku;
    /**
     * 상품명칭
     */
    @Schema(description = "상품명칭", example = "Example Value")
    private String skuname;
    /**
     * MC
     */
    @Schema(description = "MC", example = "Example Value")
    private String mc;
    /**
     * 저장조건
     */
    @Schema(description = "저장조건", example = "Example Value")
    private String storagetypename;
    /**
     * 단위
     */
    @Schema(description = "단위", example = "Example Value")
    private String uom;
    /**
     * 재고ID
     */
    @Schema(description = "재고ID", example = "Example Value")
    private String stockid;
    /**
     * 로케이션종류
     */
    @Schema(description = "로케이션종류", example = "Example Value")
    private String loccategory;
    /**
     * FROM로케이션
     */
    @Schema(description = "FROM로케이션", example = "Example Value")
    private String fromloc;
    /**
     * TO로케이션
     */
    @Schema(description = "TO로케이션", example = "Example Value")
    private String toloc;
    /**
     * 보충예정량-박스입수
     */
    @Schema(description = "보충예정량-박스입수", example = "100")
    private BigDecimal qtyperbox;
    /**
     * 보충예정량-BOX
     */
    @Schema(description = "보충예정량-BOX", example = "100")
    private BigDecimal openqtyBox;
    /**
     * 보충예정량-EA
     */
    @Schema(description = "보충예정량-EA", example = "100")
    private BigDecimal openqtyEa;
    /**
     * 보충예정량-재고속성
     */
    @Schema(description = "보충예정량-재고속성", example = "Example Value")
    private String stockgradename;
    /**
     * 보충량-BOX
     */
    @Schema(description = "보충량-BOX", example = "100")
    private BigDecimal confirmqtyBox;
    /**
     * 보충량-EA
     */
    @Schema(description = "보충량-EA", example = "100")
    private BigDecimal confirmqtyEa;
    /**
     * 기준일(소비,제조)
     */
    @Schema(description = "기준일(소비,제조)", example = "Example Value")
    private String lottable01;
    /**
     * (소비기간(잔여/전체)
     */
    @Schema(description = "(소비기간(잔여/전체)", example = "2024-01-01 10:00:00")
    private String durationTerm;
    /**
     * 발행여부
     */
    @Schema(description = "발행여부", example = "Y")
    private String printynname;
    /**
     * 지시여부
     */
    @Schema(description = "지시여부", example = "Y")
    private String ifflagynname;
    /**
     * 작업상태
     */
    @Schema(description = "작업상태", example = "Example Value")
    private String status;
    /**
     * 처리자
     */
    @Schema(description = "처리자", example = "Example Value")
    private String procname;
    /**
     * 보충번호
     */
    @Schema(description = "보충번호", example = "Example Value")
    private String supplno;
    /**
     * 보충라인
     */
    @Schema(description = "보충라인", example = "Example Value")
    private String supplline;
    /**
     * 생성일
     */
    @Schema(description = "생성일", example = "2024-01-01 10:00:00")
    private String adddate;
    /**
     * 수정일
     */
    @Schema(description = "수정일", example = "2024-01-01 10:00:00")
    private String editdate;
    /**
     * orderqtyMeBox
     */
    @Schema(description = "orderqtyMeBox", example = "100")
    private BigDecimal orderqtyMeBox;
    /**
     * orderqtyMeEa
     */
    @Schema(description = "orderqtyMeEa", example = "100")
    private BigDecimal orderqtyMeEa;
    /**
     * orderqtyNotmeBox
     */
    @Schema(description = "orderqtyNotmeBox", example = "100")
    private BigDecimal orderqtyNotmeBox;
    /**
     * orderqtyNotmeEa
     */
    @Schema(description = "orderqtyNotmeEa", example = "100")
    private BigDecimal orderqtyNotmeEa;
    /**
     * storerkey
     */
    @Schema(description = "storerkey", example = "Example Value")
    private String storerkey;
    /**
     * dccode
     */
    @Schema(description = "dccode", example = "Example Value")
    private String dccode;
    /**
     * organize
     */
    @Schema(description = "organize", example = "Example Value")
    private String organize;
    /**
     * storagetype
     */
    @Schema(description = "storagetype", example = "Example Value")
    private String storagetype;
    /**
     * openqty
     */
    @Schema(description = "openqty", example = "100")
    private BigDecimal openqty;
    /**
     * confirmqty
     */
    @Schema(description = "confirmqty", example = "100")
    private BigDecimal confirmqty;
    /**
     * stockgrade
     */
    @Schema(description = "stockgrade", example = "Example Value")
    private String stockgrade;
    /**
     * durationtype
     */
    @Schema(description = "durationtype", example = "Example Value")
    private String durationtype;
    /**
     * printyn
     */
    @Schema(description = "printyn", example = "Y")
    private String printyn;
    /**
     * ordertype
     */
    @Schema(description = "ordertype", example = "Example Value")
    private String ordertype;
    /**
     * slipdt
     */
    @Schema(description = "slipdt", example = "2024-01-01 10:00:00")
    private String slipdt;
    /**
     * slipno
     */
    @Schema(description = "slipno", example = "Example Value")
    private String slipno;
    /**
     * slipline
     */
    @Schema(description = "slipline", example = "Example Value")
    private String slipline;
    /**
     * orderqtyMe
     */
    @Schema(description = "orderqtyMe", example = "100")
    private BigDecimal orderqtyMe;
    /**
     * orderqtyNotme
     */
    @Schema(description = "orderqtyNotme", example = "100")
    private BigDecimal orderqtyNotme;
    /**
     * ifFlag
     */
    @Schema(description = "ifFlag", example = "Example Value")
    private String ifFlag;
    /**
     * ifSendFile
     */
    @Schema(description = "ifSendFile", example = "Example Value")
    private String ifSendFile;
    /**
     * ifDate
     */
    @Schema(description = "ifDate", example = "2024-01-01 10:00:00")
    private String ifDate;
    /**
     * ifflagyn
     */
    @Schema(description = "ifflagyn", example = "Y")
    private String ifflagyn;
}
