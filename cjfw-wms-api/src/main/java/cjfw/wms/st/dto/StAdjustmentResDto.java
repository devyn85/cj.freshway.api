package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.08.25
 * @description : 센터자체감모 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "센터자체감모 Master Response DTO")
@Data
public class StAdjustmentResDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;

    /** 화주코드 */
    @Schema(description = "화주코드")
    private String storerkey;

    /** 조직 */
    @Schema(description = "조직")
    private String organize;

    /** 구역 */
    @Schema(description = "구역")
    private String area;

    /** 재고유형명 */
    @Schema(description = "재고유형명")
    private String stocktypenm;

    /** 재고유형 */
    @Schema(description = "재고유형")
    private String stocktype;

    /** 재고등급 */
    @Schema(description = "재고등급")
    private String stockgrade;

    /** 재고등급명 */
    @Schema(description = "재고등급명")
    private String stockgradename;

    /** 존 */
    @Schema(description = "존")
    private String zone;

    /** 로케이션 */
    @Schema(description = "로케이션")
    private String loc;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명")
    private String skuname;

    /** 로트 */
    @Schema(description = "로트")
    private String lot;

    /** 단위 */
    @Schema(description = "단위")
    private String uom;

    /** 수량 */
    @Schema(description = "수량")
    private BigDecimal qty;

    /** 가용수량 */
    @Schema(description = "가용수량")
    private BigDecimal openqty;

    /** 할당수량 */
    @Schema(description = "할당수량")
    private BigDecimal qtyallocated;

    /** 피킹수량 */
    @Schema(description = "피킹수량")
    private BigDecimal qtypicked;

    /** 이동수량 */
    @Schema(description = "이동수량")
    private BigDecimal tranqty;

    /** 사유코드 */
    @Schema(description = "사유코드")
    private String reasoncode;

    /** 사유메시지 */
    @Schema(description = "사유메시지")
    private String reasonmsg;

    /** 입력유형 */
    @Schema(description = "입력유형")
    private String imputetype;

    /** 처리주체 */
    @Schema(description = "처리주체")
    private String processmain;

    /** 원가코드 */
    @Schema(description = "원가코드")
    private String costcd;

    /** 원가코드명 */
    @Schema(description = "원가코드명")
    private String costcdname;

    /** 거래처코드 */
    @Schema(description = "거래처코드")
    private String custkey;

    /** 거래처명 */
    @Schema(description = "거래처명")
    private String custname;

    /** 유통기한임박여부 */
    @Schema(description = "유통기한임박여부")
    private String neardurationyn;

    /** 유통기한 */
    @Schema(description = "유통기한")
    private String lottable01;

    /** 유통기한_기간 */
    @Schema(description = "유통기한_기간")
    private String durationTerm;

    /** 재고ID */
    @Schema(description = "재고ID")
    private String stockid;

    /** 시리얼번호 */
    @Schema(description = "시리얼번호")
    private String serialno;

    /** 변환시리얼번호 */
    @Schema(description = "변환시리얼번호")
    private String convserialno;

    /** 시리얼레벨 */
    @Schema(description = "시리얼레벨")
    private String seriallevel;

    /** 시리얼타입 */
    @Schema(description = "시리얼타입")
    private String serialtype;

    /** 공장명 */
    @Schema(description = "공장명")
    private String factoryname;

    /** 색상설명 */
    @Schema(description = "색상설명")
    private String colordescr;

    /** 원산지 */
    @Schema(description = "원산지")
    private String placeoforigin;

    /** 주문유형 */
    @Schema(description = "주문유형")
    private String ordertype;

    /** 유통기한기간 */
    @Schema(description = "유통기한기간")
    private String duration;

    /** 유통기한구분 */
    @Schema(description = "유통기한구분")
    private String durationtype;

    /** 도축일자 */
    @Schema(description = "도축일자")
    private String butcherydt;

    /** 계약거래처 */
    @Schema(description = "계약거래처")
    private String contractcompany;
    
    /** 계약거래처(다중검색) */
    @Schema(description = "계약거래처(다중검색)")
    private String contractcompanyMulti;    

    /** 계약거래처명 */
    @Schema(description = "계약거래처명")
    private String contractcompanyname;

    /** 유효일자 */
    @Schema(description = "유효일자")
    private String fromvaliddt;

    /** 유효일자2 */
    @Schema(description = "유효일자2")
    private String tovaliddt;

    /** 계약유형 */
    @Schema(description = "계약유형")
    private String contracttype;

    /** 바코드 */
    @Schema(description = "바코드")
    private String barcode;

    /** 기타05 */
    @Schema(description = "기타05")
    private String other051;
    
    /** 제조일자 */
    @Schema(description = "제조일자")
    private String manufacturedt;    
    
    /** 소비일자 */
    @Schema(description = "소비일자")
    private String expiredt;    
    
    /** 소비기한잔여율 */
    @Schema(description = "소비기한잔여율")
    private BigDecimal usebydatefreert;    
}
