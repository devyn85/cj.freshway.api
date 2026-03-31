package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 고혜미
 * @date : 2025.10.16 
 * @description : 처리결과 목록 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.16 고혜미 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "처리결과 목록 요청") 
public class WdShipmentETCResTab2Dto extends CommonProcedureDto {
	/**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "DC01")
    private String dccode;
    /**
     * 창고
     */
    @Schema(description = "창고", example = "WH01")
    private String organize;
    /**
     * 재고위치
     */
    @Schema(description = "재고위치", example = "NORMAL")
    private String stocktype;
    /**
     * 재고속성
     */
    @Schema(description = "재고속성", example = "GOOD")
    private String stockgrade;
    /**
     * 피킹존
     */
    @Schema(description = "피킹존", example = "ZONE01")
    private String zone;
    /**
     * 로케이션
     */
    @Schema(description = "로케이션", example = "LOC01")
    private String loc;
    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "SKU001")
    private String sku;
    /**
     * 상품명칭
     */
    @Schema(description = "상품명칭", example = "오렌지")
    private String skuname;
    /**
     * 단위
     */
    @Schema(description = "단위", example = "EA")
    private String uom;
    /**
     * 현재고수량
     */
    @Schema(description = "현재고수량", example = "1000")
    private BigDecimal qty;
    /**
     * 가용재고수량
     */
    @Schema(description = "가용재고수량", example = "950")
    private BigDecimal openqty;
    /**
     * 재고할당수량
     */
    @Schema(description = "재고할당수량", example = "50")
    private BigDecimal qtyallocated;
    /**
     * 피킹재고
     */
    @Schema(description = "피킹재고", example = "20")
    private BigDecimal qtypicked;
    /**
     * 처리수량
     */
    @Schema(description = "처리수량", example = "10")
    private BigDecimal etcqty;
    /**
     * 처리유형
     */
    @Schema(description = "처리유형", example = "ISSUE")
    private String potype;
    /**
     * 처리사유
     */
    @Schema(description = "처리사유", example = "DEFECT")
    private String reasoncode;
    /**
     * 세부사유
     */
    @Schema(description = "세부사유", example = "박스 파손")
    private String reasonmsg;
    /**
     * 물류귀책배부
     */
    @Schema(description = "물류귀책배부", example = "DAMAGE")
    private String other04;
    /**
     * 기타
     */
    @Schema(description = "기타", example = "특이사항 없음")
    private String other05;
    /**
     * 소비기한임박여부
     */
    @Schema(description = "소비기한임박여부", example = "Y")
    private String neardurationyn;
    /**
     * 기준일(소비,제조)
     */
    @Schema(description = "기준일(소비,제조)", example = "2025-10-30")
    private String lottable01;
    /**
     * 소비기간(잔여/전체)
     */
    @Schema(description = "소비기간(잔여/전체)", example = "10/30")
    private String durationTerm;
    /**
     * 이력번호
     */
    @Schema(description = "이력번호", example = "SER001")
    private String serialno;
    /**
     * 바코드
     */
    @Schema(description = "바코드", example = "BAR001")
    private String barcode;
    /**
     * B/L번호
     */
    @Schema(description = "B/L번호", example = "BL001")
    private String convserialno;
    /**
     * 도축일자
     */
    @Schema(description = "도축일자", example = "2025-10-20")
    private String butcherydt;
    /**
     * 도축장
     */
    @Schema(description = "도축장", example = "한우농장")
    private String factoryname;
    /**
     * 계약유형
     */
    @Schema(description = "계약유형", example = "계약재배")
    private String contracttype;
    /**
     * 계약업체
     */
    @Schema(description = "계약업체", example = "CONT01")
    private String contractcompany;
    /**
     * 계약업체명
     */
    @Schema(description = "계약업체명", example = "대한영농")
    private String contractcompanyname;
    /**
     * 유효일자(FROM)
     */
    @Schema(description = "유효일자(FROM)", example = "2025-10-01")
    private String fromvaliddt;
    /**
     * 유효일자(TO)
     */
    @Schema(description = "유효일자(TO)", example = "2025-10-31")
    private String tovaliddt;
    /**
     * 로트
     */
    @Schema(description = "로트", example = "LOT001")
    private String lot;
    /**
     * 주문유형
     */
    @Schema(description = "주문유형", example = "DDGA")
    private String ordertype;
    /**
     * 소비기간
     */
    @Schema(description = "소비기간", example = "30")
    private String duration;
    /**
     * 소비기한관리방법
     */
    @Schema(description = "소비기한관리방법", example = "D")
    private String durationtype;
    /**
     * 개체식별/소비이력
     */
    @Schema(description = "개체식별/소비이력", example = "SID001")
    private String stockid;
    /**
     * 프로세스 플래그
     */
    @Schema(description = "프로세스 플래그", example = "Y")
    private String processflag;
    /**
     * 프로세스 메시지
     */
    @Schema(description = "프로세스 메시지", example = "정상 처리")
    private String processmsg;
    /**
     * 등급
     */
    @Schema(description = "등급", example = "1++")
    private String seriallevel;
    /**
     * 규격
     */
    @Schema(description = "규격", example = "대")
    private String serialtype;
    /**
     * 부위명
     */
    @Schema(description = "부위명", example = "등심")
    private String colordescr;
    /**
     * 원산지
     */
    @Schema(description = "원산지", example = "국내산")
    private String placeoforigin;
    /**
     * 창고마스터키
     */
    @Schema(description = "창고마스터키", example = "STR001")
    private String storerkey;
	
}
