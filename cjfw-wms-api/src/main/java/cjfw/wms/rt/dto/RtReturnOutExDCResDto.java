package cjfw.wms.rt.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.06.27 
 * @description : 외부비축협력사반품지시 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.27    KimSunHo(sunhokim6229@cj.net)   생성
 */

@Data
@Schema(description = "외부비축협력사반품지시 조회 결과")
public class RtReturnOutExDCResDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

    
    /** processtype */
    @Schema(description = "processtype", nullable = false, example = "")
    private String processtype;

    /** 물류센터코드 */
    @Schema(description = "물류센터코드", nullable = false, example = "")
    private String dccode;

    /** 물류센터명 */
    @Schema(description = "물류센터명", nullable = false, example = "")
    private String dcname;

    /** 창고코드 */
    @Schema(description = "창고코드", nullable = false, example = "")
    private String organize;
    
    /** 창고명 */
    @Schema(description = "창고명", nullable = false, example = "")
    private String organizename;

    /** 고객사 */
    @Schema(description = "고객사", nullable = false, example = "")
    private String storerkey;

    /** 권역 */
    @Schema(description = "권역", nullable = false, example = "")
    private String area;

    /** 거래처 */
    @Schema(description = "거래처", nullable = false, example = "")
    private String custkey;

    /** 거래처명 */
    @Schema(description = "거래처명", nullable = false, example = "")
    private String custname;

    /** 상품코드 */
    @Schema(description = "상품코드", nullable = false, example = "")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명", nullable = false, example = "")
    private String skuname;

    /** 플랜트 */
    @Schema(description = "플랜트", nullable = false, example = "")
    private String plant;

    /** 플랜트명 */
    @Schema(description = "플랜트명", nullable = false, example = "")
    private String plantDescr;

    /** 저장유형 */
    @Schema(description = "저장유형", nullable = false, example = "")
    private String storagetypename;

    /** 식별번호유무 */
    @Schema(description = "식별번호유무", nullable = false, example = "")
    private String serialyn;

    /** 식별번호유무 */
    @Schema(description = "식별번호유무", nullable = false, example = "")
    private String serialynname;

    /** 반품수량 */
    @Schema(description = "반품수량", nullable = false, example = "")
    private BigDecimal wdQty;

    /** 재고수량 */
    @Schema(description = "재고수량", nullable = false, example = "")
    private BigDecimal qty;

    /** 단위 */
    @Schema(description = "단위", nullable = false, example = "")
    private String uom;

    /** 로케이션 */
    @Schema(description = "로케이션", nullable = false, example = "")
    private String loc;

    /** LOT번호 */
    @Schema(description = "LOT번호", nullable = false, example = "")
    private String lot;

    /** 재고타입 */
    @Schema(description = "재고타입", nullable = false, example = "")
    private String stocktype;

    /** 재고타입명 */
    @Schema(description = "재고타입명", nullable = false, example = "")
    private String stocktypename;

    /** 등급 */
    @Schema(description = "등급", nullable = false, example = "")
    private String stockgrade;

    /** 등급명 */
    @Schema(description = "등급명", nullable = false, example = "")
    private String stockgradename;

    /** 사유코드 */
    @Schema(description = "사유코드", nullable = false, example = "")
    private String reasoncode;
    
    /** 기타01 */
    @Schema(description = "기타01", nullable = false, example = "")
    private String other01;

    /** 기타03 */
    @Schema(description = "기타03", nullable = false, example = "")
    private String other03;

    /** 기타04 */
    @Schema(description = "기타04", nullable = false, example = "")
    private String other04;

    /** 기타05 */
    @Schema(description = "기타05", nullable = false, example = "")
    private String other05;

    /** 소속부서코드 */
    @Schema(description = "소속부서코드", nullable = false, example = "")
    private String blngdeptcd;

    /** 기준일(유통,제조) */
    @Schema(description = "기준일(유통,제조)", nullable = false, example = "")
    private String lottable01;

    /** 소비기한 */
    @Schema(description = "소비기한", nullable = false, example = "")
    private String durationTerm;

    /** 재고ID */
    @Schema(description = "재고ID", nullable = false, example = "")
    private String stockid;

    /** 바코드 */
    @Schema(description = "바코드", nullable = false, example = "")
    private String barcode;

    /** 시리얼번호 */
    @Schema(description = "시리얼번호", nullable = false, example = "")
    private String serialno;

    /** B/L번호 */
    @Schema(description = "B/L번호", nullable = false, example = "")
    private String convserialno;

    /** 시리얼레벨 */
    @Schema(description = "시리얼레벨", nullable = false, example = "")
    private String seriallevel;

    /** 시리얼타입 */
    @Schema(description = "시리얼타입", nullable = false, example = "")
    private String serialtype;

    /** 도축장명 */
    @Schema(description = "도축장명", nullable = false, example = "")
    private String factoryname;

    /** 색상 */
    @Schema(description = "색상", nullable = false, example = "")
    private String colordescr;

    /** 원산지 */
    @Schema(description = "원산지", nullable = false, example = "")
    private String placeoforigin;

    /** 소비기한 */
    @Schema(description = "소비기한", nullable = false, example = "")
    private int duration;

    /** 소비기한유형 */
    @Schema(description = "소비기한유형", nullable = false, example = "")
    private String durationtype;

    /** 계약업체코드 */
    @Schema(description = "계약업체코드", nullable = false, example = "")
    private String contractcompany;

    /** 계약업체명 */
    @Schema(description = "계약업체명", nullable = false, example = "")
    private String contractcompanyname;

    /** 계약유형 */
    @Schema(description = "계약유형명", nullable = false, example = "")
    private String contracttypename;

    /** 도축일자 */
    @Schema(description = "도축일자", nullable = false, example = "")
    private String butcherydt;

    /** 유효시작일 */
    @Schema(description = "유효시작일", nullable = false, example = "")
    private String fromvaliddt;

    /** 유효종료일 */
    @Schema(description = "유효종료일", nullable = false, example = "")
    private String tovaliddt;

    /** 저장위치 */
    @Schema(description = "저장위치", nullable = false, example = "")
    private String storageloc;

    /** 예상박스수량 */
    @Schema(description = "예상박스수량", nullable = false, example = "")
    private BigDecimal etcqty1;

    /** 체크플래그 */
    @Schema(description = "체크플래그", nullable = false, example = "")
    private String checkFlag;

    /** 평균중량 */
    @Schema(description = "평균중량", nullable = false, example = "")
    private BigDecimal avgweight;

    /** 환산박스 */
    @Schema(description = "환산박스", nullable = false, example = "")
    private Double calbox;

    /** 실박스예정 */
    @Schema(description = "실박스예정", nullable = false, example = "")
    private BigDecimal realorderbox;

    /** 실박스확정 */
    @Schema(description = "실박스확정", nullable = false, example = "")
    private BigDecimal realcfmbox;

    /** 박스처리유무 */
    @Schema(description = "박스처리유무", nullable = false, example = "")
    private String boxflag;
    
    /** 잔여율 */
    @Schema(description = "잔여율", nullable = false, example = "")
    private Integer usebydateFreeRt;
    
    /** 최초등록시간 (yyyymmddhh24miss) */
    @Schema(description = "최초등록시간 (yyyymmddhh24miss)", nullable = false, example = "")
    private String adddate;

    /** 최종변경시간 (yyyymmddhh24miss) */
    @Schema(description = "최종변경시간 (yyyymmddhh24miss)", nullable = false, example = "")
    private String editdate;

    /** 최초등록자 */
    @Schema(description = "최초등록자", nullable = false, example = "")
    private String addwho;

    /** 최종변경자 */
    @Schema(description = "최종변경자", nullable = false, example = "")
    private String editwho;
    
    /** 최종변경자명 */
    @Schema(description = "최종변경자명", nullable = false, example = "")
    private String editwhoNm;
    
    
    /** 협력사코드 */
    @Schema(description = "협력사코드", nullable = false, example = "")
    private String toDccode;
    
    /** 협력사명 */
    @Schema(description = "협력사명", nullable = false, example = "")
    private String toDcname;
    
    /** listNo */
    @Schema(description = "listNo", nullable = false, example = "")
    private String listNo;
    
    /** wdDate */
    @Schema(description = "wdDate", nullable = false, example = "")
    private String wdDate;
    
    /** zone */
    @Schema(description = "zone", nullable = false, example = "")
    private String zone;

}
