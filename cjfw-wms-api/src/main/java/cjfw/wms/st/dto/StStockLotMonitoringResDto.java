package cjfw.wms.st.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.10.14 
 * @description : 유통기한 점검 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.14 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class StStockLotMonitoringResDto {
			
	/** 물류센터코드 */
    @Schema(description = "물류센터코드", example = "")
    private String dccode;

    /** 창고코드 */
    @Schema(description = "창고코드", example = "")
    private String organize;

    /** 재고유형명 */
    @Schema(description = "재고유형명", example = "")
    private String stocktype;

    /** 재고등급명 */
    @Schema(description = "재고등급명", example = "")
    private String stockgrade;

    /** 존(Zone) */
    @Schema(description = "존(Zone)", example = "")
    private String zone;

    /** PLT 재고 여부 */
    @Schema(description = "PLT 재고 여부", example = "")
    private String pltFlg;

    /** 로케이션 */
    @Schema(description = "로케이션", example = "")
    private String loc;

    /** 상품코드 */
    @Schema(description = "상품코드", example = "")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명", example = "")
    private String skuname;

    /** 단위 */
    @Schema(description = "단위", example = "")
    private String uom;

    /** 재고 수량 */
    @Schema(description = "재고 수량", example = "")
    private String qty;

    /** 박스당 수량 */
    @Schema(description = "박스당 수량", example = "")
    private String qtyperbox;

    /** OPEN 재고 수량 */
    @Schema(description = "OPEN 재고 수량", example = "")
    private String openqty;

    /** 할당 수량 */
    @Schema(description = "할당 수량", example = "")
    private String qtyallocated;

    /** 피킹 수량 */
    @Schema(description = "피킹 수량", example = "")
    private String qtypicked;

    /** 유통기한 타입 명 */
    @Schema(description = "유통기한 타입 명", example = "")
    private String durationtypeName;

    /** 임박 유통기한 여부 (Y/N) */
    @Schema(description = "임박 유통기한 여부 (Y/N)", example = "")
    private String neardurationyn;

    /** 로트속성01 (유통기한/제조일자) */
    @Schema(description = "로트속성01 (유통기한/제조일자)", example = "")
    private String lottable01;

    /** 잔여/총 유통기한 기간 (예: 10/180) */
    @Schema(description = "잔여/총 유통기한 기간 (예: 10/180)", example = "")
    private String durationTerm;

    /** 잔여 유통기한 비율 (%) */
    @Schema(description = "잔여 유통기한 비율 (%)", example = "")
    private String persent;

    /** 잔여 유통기한 비율 구간 구분 */
    @Schema(description = "잔여 유통기한 비율 구간 구분", example = "")
    private String percentDiv;

    /** 시리얼 번호 */
    @Schema(description = "시리얼 번호", example = "")
    private String serialno;

    /** 보관 유형명 */
    @Schema(description = "보관 유형명", example = "")
    private String storagetype;

    /** 총 유통기한 일수 */
    @Schema(description = "총 유통기한 일수", example = "")
    private String duration;

    /** 유통기한 타입 코드 */
    @Schema(description = "유통기한 타입 코드", example = "")
    private String durationtype;

    /** 출고 수량 (최근 1주) */
    @Schema(description = "출고 수량 (최근 1주)", example = "")
    private String shipqty1w;

    /** 출고 수량 (최근 2주) */
    @Schema(description = "출고 수량 (최근 2주)", example = "")
    private String shipqty2w;

    /** 출고 수량 (최근 3주) */
    @Schema(description = "출고 수량 (최근 3주)", example = "")
    private String shipqty3w;

    /** 출고 일수 (최근 1주) (26 - 실제 출고일수) */
    @Schema(description = "출고 일수 (최근 1주)", example = "")
    private String shipday1w;

    /** 출고 일수 (최근 2주) (26 - 실제 출고일수) */
    @Schema(description = "출고 일수 (최근 2주)", example = "")
    private String shipday2w;

    /** 출고 일수 (최근 3주) (26 - 실제 출고일수) */
    @Schema(description = "출고 일수 (최근 3주)", example = "")
    private String shipday3w;

    /** 오더 건수 (최근 1주) */
    @Schema(description = "오더 건수 (최근 1주)", example = "")
    private String ordcnt1w;

    /** 오더 건수 (최근 2주) */
    @Schema(description = "오더 건수 (최근 2주)", example = "")
    private String ordcnt2w;

    /** 오더 건수 (최근 3주) */
    @Schema(description = "오더 건수 (최근 3주)", example = "")
    private String ordcnt3w;

    /** 30일 평균 출고량 */
    @Schema(description = "30일 평균 출고량", example = "")
    private String avg30;

    /** 재고 소진 예상일수 (일평균 출고량 기준) */
    @Schema(description = "재고 소진 예상일수", example = "")
    private String gapdt;

    /** 재고 소진 예정일 (YYYYMMDD) */
    @Schema(description = "재고 소진 예정일 (YYYYMMDD)", example = "")
    private String exhaustiondt;

    /** 재고 소진 가능 여부 (유통기한 vs 소진예상일 비교) */
    @Schema(description = "재고 소진 가능 여부", example = "")
    private String exhaustionchk;

    /** 바이어명 */
    @Schema(description = "바이어명", example = "")
    private String buyername;
    
	/** 수정자 */
	@Schema(description = "수정자", example = "")
	private String addwho;

	/** 최초등록시간 */
	@Schema(description = "최초등록시간", example = "")
	private String adddate;

	/** 수정자 */
	@Schema(description = "수정자", example = "")
	private String editwho;

	/** 최종변경시간 */
	@Schema(description = "최종변경시간", example = "")
	private String editdate;
	
	/** 등록자명 */
    @Schema(description = "등록자명", example = "")
    private String regNm;

    /** 수정자명 */
    @Schema(description = "수정자명", example = "")
    private String updNm;
    
    /** 제조일자 */
    @Schema(description = "제조일자", example = "")
    private String manufacturedt;
    
    /** 소비일자 */
    @Schema(description = "소비일자", example = "")
    private String expiredt;

		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}