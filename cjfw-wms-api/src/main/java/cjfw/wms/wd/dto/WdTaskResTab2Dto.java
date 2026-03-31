package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.08.29 
 * @description : 피킹작업지시-조회생성 상세 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.29 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "피킹작업지시-조회생성 상세 결과")
public class WdTaskResTab2Dto extends CommonDto{
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/**
     * 창고
     */
    @Schema(description = "창고", example = "MAIN_WH")
    private String organize;

    /**
     * 작업일자
     */
    @Schema(description = "작업일자", example = "2025-08-29")
    private String taskdt;

    /**
     * 작업방법
     */
    @Schema(description = "작업방법", example = "자동")
    private String tasksystemdesc;

    /**
     * 배치그룹
     */
    @Schema(description = "배치그룹", example = "GROUP_A")
    private String createdescr;

    /**
     * 저장조건
     */
    @Schema(description = "저장조건", example = "냉장")
    private String storagetypedesc;

    /**
     * 원거리유형
     */
    @Schema(description = "원거리유형", example = "근거리")
    private String distancetype;

    /**
     * 대배치키
     */
    @Schema(description = "대배치키", example = "BATCH12345")
    private String pickBatchNo;

    /**
     * 피킹번호
     */
    @Schema(description = "피킹번호", example = "PICK789")
    private String pickNo;

    /**
     * 고객수
     */
    @Schema(description = "고객수", example = "10")
    private BigDecimal custcount;

    /**
     * 전표수
     */
    @Schema(description = "전표수", example = "25")
    private BigDecimal docnocount;

    /**
     * 품목수
     */
    @Schema(description = "품목수", example = "50")
    private BigDecimal skucount;

    /**
     * 주문량
     */
    @Schema(description = "주문량", example = "1000")
    private BigDecimal storerorderqty;

    /**
     * 진행예정량
     */
    @Schema(description = "진행예정량", example = "950")
    private BigDecimal processqty;

    /**
     * 처리량
     */
    @Schema(description = "처리량", example = "900")
    private BigDecimal workqty;

    /**
     * 검수량
     */
    @Schema(description = "검수량", example = "890")
    private BigDecimal inspectqty;

    /**
     * 확정수량
     */
    @Schema(description = "확정수량", example = "890")
    private BigDecimal confirmqty;

    /**
     * I/F구분
     */
    @Schema(description = "I/F구분", example = "N")
    private String ifFlag;

    /**
     * 처리물량(KG)
     */
    @Schema(description = "처리물량(KG)", example = "125.50")
    private BigDecimal workkg;

    /**
     * 프린트횟수
     */
    @Schema(description = "프린트횟수", example = "3")
    private BigDecimal printCnt;

    /**
     * 프린트시간
     */
    @Schema(description = "프린트시간", example = "2025-08-29 15:14:15")
    private String printDate;

    /**
     * 스캔여부
     */
    @Schema(description = "스캔여부", example = "15")
    private BigDecimal scancount;

    /**
     * 모바일지시여부
     */
    @Schema(description = "모바일지시여부", example = "DC01")
    private String mobileInstructYn;
    
    /**
     * DC코드
     */
    @Schema(description = "DC코드", example = "DC01")
    private String dccode;

    /**
     * 화주키
     */
    @Schema(description = "화주키", example = "STORER001")
    private String storerkey;


    /**
     * 작업시스템
     */
    @Schema(description = "작업시스템", example = "WMS")
    private String tasksystem;

    /**
     * 플랜트
     */
    @Schema(description = "플랜트", example = "PLANT01")
    private String plant;
    
    /**
     * 생성키
     */
    @Schema(description = "생성키", example = "PLANT01")
    private String createkey;

    /**
     * 저장유형
     */
    @Schema(description = "저장유형", example = "RACK")
    private String storagetype;

    /**
     * 피킹리스트번호
     */
    @Schema(description = "피킹리스트번호", example = "PL20250829001")
    private String pickListNo;

    /**
     * 전표건수
     */
    @Schema(description = "전표건수", example = "25")
    private BigDecimal slipcount;

    /**
     * 작업건수
     */
    @Schema(description = "작업건수", example = "30")
    private BigDecimal taskcount;

    /**
     * 예정량
     */
    @Schema(description = "예정량", example = "950")
    private BigDecimal openqty;
}
