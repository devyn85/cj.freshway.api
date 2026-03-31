package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.09.18 
 * @description : 피킹작업지시(차량)-조회생성 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.18 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "피킹작업지시-조회생성(차량) 목록 결과")
public class WdTaskResTab4Dto extends CommonDto{
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/**
     * 작업지시일자
     */
    @Schema(description = "작업지시일자", example = "2023-10-26")
    private String taskdt;

    /**
     * 차량번호
     */
    @Schema(description = "차량번호", example = "999버3377")
    private String carno;
    
    /**
     * 플랜트
     */
    @Schema(description = "플랜트", example = "P001")
    private String plantdesc;

    /**
     * 저장조건
     */
    @Schema(description = "저장조건", example = "DRY")
    private String storagetypedesc;

    /**
     * 원거리유형
     */
    @Schema(description = "원거리유형", example = "NEAR")
    private String distancetype;

    /**
     * 생성키
     */
    @Schema(description = "생성키", example = "KEY123")
    private String createkey;

    /**
     * 생성키명
     */
    @Schema(description = "생성키명", example = "생성키 이름")
    private String createdescr;

    /**
     * 지시건수
     */
    @Schema(description = "지시건수", example = "100")
    private BigDecimal taskcount;

    /**
     * 진행예정량
     */
    @Schema(description = "진행예정량", example = "500")
    private BigDecimal processqty;

    /**
     * 센터코드
     */
    @Schema(description = "센터코드", example = "DC01")
    private String dccode;

    /**
     * 고객사
     */
    @Schema(description = "고객사", example = "CUST001")
    private String storerkey;

    /**
     * 출고일자
     */
    @Schema(description = "출고일자", example = "2023-10-27")
    private String docdt;

    /**
     * 작업시스템
     */
    @Schema(description = "작업시스템", example = "WMS")
    private String tasksystem;

    /**
     * 작업유형
     */
    @Schema(description = "작업유형", example = "PICKING")
    private String tasktype;

    /**
     * 웨이브키
     */
    @Schema(description = "웨이브키", example = "WAVE001")
    private String wavekey;

    /**
     * 전표일자
     */
    @Schema(description = "전표일자", example = "2023-10-26")
    private String slipdt;

    /**
     * 전표번호
     */
    @Schema(description = "전표번호", example = "SLIP123")
    private String slipno;

    /**
     * 거래처코드
     */
    @Schema(description = "거래처코드", example = "CUSTBIZ001")
    private String custkey;

    /**
     * 거래처명
     */
    @Schema(description = "거래처명", example = "거래처상호")
    private String custname;

    /**
     * 플랜트
     */
    @Schema(description = "플랜트", example = "P001")
    private String plant;

    /**
     * 저장유형
     */
    @Schema(description = "저장유형", example = "RACK")
    private String storagetype;

    /**
     * 수량|예정량
     */
    @Schema(description = "수량|예정량", example = "1000")
    private BigDecimal openqty;

    /**
     * 수량|작업량
     */
    @Schema(description = "수량|작업량", example = "800")
    private BigDecimal workqty;

    /**
     * 수량|검수량
     */
    @Schema(description = "수량|검수량", example = "750")
    private BigDecimal inspectqty;

    /**
     * 수량|확정량
     */
    @Schema(description = "수량|확정량", example = "750")
    private BigDecimal confirmqty;

    /**
     * SY_PROCESSTEMP_WD용
     */
    @Schema(description = "SY_PROCESSTEMP_WD용", example = "OTHER01_VALUE")
    private String other01;

    /**
     * SY_PROCESSTEMP_WD용
     */
    @Schema(description = "SY_PROCESSTEMP_WD용", example = "OTHER02_VALUE")
    private String other02;

    /**
     * SY_PROCESSTEMP_WD용
     */
    @Schema(description = "SY_PROCESSTEMP_WD용", example = "OTHER03_VALUE")
    private String other03;

    /**
     * SY_PROCESSTEMP_WD용
     */
    @Schema(description = "SY_PROCESSTEMP_WD용", example = "OTHER04_VALUE")
    private String other04;

    /**
     * SY_PROCESSTEMP_WD용
     */
    @Schema(description = "SY_PROCESSTEMP_WD용", example = "OTHER05_VALUE")
    private String other05;
}
