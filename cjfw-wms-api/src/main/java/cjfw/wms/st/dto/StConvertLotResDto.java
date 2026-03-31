package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : sss (kduimux@cj.net)
 * @date : 2025.06.02
 * @description : 유통기한변경 조회 Response DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "유통기한변경 조회 Response DTO")
public class StConvertLotResDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";


    @Schema(description = "체크여부")
    private String checkyn;

    @Schema(description = "센터코드")
    private String dccode;

    @Schema(description = "화주코드")
    private String storerkey;

    @Schema(description = "조직")
    private String organize;

    @Schema(description = "구역")
    private String area;

    @Schema(description = "구역명")
    private String areaname;

    @Schema(description = "재고등급")
    private String fromStockgrade;

    @Schema(description = "재고등급명")
    private String stockgradename;

    @Schema(description = "품목코드")
    private String sku;

    @Schema(description = "품목명")
    private String skuname;

    @Schema(description = "품목그룹")
    private String skugroup;

    @Schema(description = "MC(규격)")
    private String mc;

    @Schema(description = "출고로케이션")
    private String fromLoc;

    @Schema(description = "출고로트")
    private String fromLot;

    @Schema(description = "출고재고ID")
    private String fromStockid;

    @Schema(description = "출고재고유형")
    private String fromStocktype;

    @Schema(description = "출고재고유형명")
    private String fromStocktypedesc;

    @Schema(description = "단위")
    private String uom;

    @Schema(description = "수량")
    private BigDecimal qty;

    @Schema(description = "가용수량")
    private BigDecimal openqty;

    @Schema(description = "할당수량")
    private BigDecimal qtyallocated;

    @Schema(description = "피킹수량")
    private BigDecimal qtypicked;

    @Schema(description = "처리수량")
    private BigDecimal tranqty;

    @Schema(description = "평균중량")
    private BigDecimal avgweight;

    @Schema(description = "계산박스")
    private BigDecimal calbox;

    @Schema(description = "실가용박스")
    private BigDecimal realopenbox;

    @Schema(description = "실확정박스")
    private BigDecimal realcfmbox;

    @Schema(description = "처리박스")
    private BigDecimal tranbox;

    @Schema(description = "박스처리유무")
    private String boxflag;

    @Schema(description = "출고LOT속성1")
    private String fromLottable01;

    @Schema(description = "출고LOT속성2")
    private String fromLottable02;

    @Schema(description = "출고LOT속성3")
    private String fromLottable03;

    @Schema(description = "출고LOT속성4")
    private String fromLottable04;

    @Schema(description = "출고LOT속성5")
    private String fromLottable05;

    @Schema(description = "유통기한정보")
    private String durationTerm;

    @Schema(description = "유통기한타입")
    private String durationtype;

    @Schema(description = "유통기한")
    private String duration;

    @Schema(description = "시리얼번호")
    private String serialno;

    @Schema(description = "변환시리얼번호")
    private String convserialno;

    @Schema(description = "도축일자")
    private String butcherydt;

    @Schema(description = "계약업체코드")
    private String contractcompany;

    @Schema(description = "계약업체명")
    private String contractcompanyname;

    @Schema(description = "유효시작일")
    private String fromvaliddt;

    @Schema(description = "유효종료일")
    private String tovaliddt;

    @Schema(description = "계약유형")
    private String contracttype;

    @Schema(description = "바코드")
    private String barcode;

    @Schema(description = "공장명")
    private String factoryname;
    
    @Schema(description = "공장명")
    private String serialkey;    
    
    /** 제조일자 */
    @Schema(description = "제조일자")
    private String manufacturedt;    
    
    /** 소비일자 */
    @Schema(description = "소비일자")
    private String expiredt;    
    
    /** 소비기한잔여율 */
    @Schema(description = "소비기한잔여율")
    private BigDecimal usebydatefreert; 
    
    /** 사유코드 */
    @Schema(description = "사유코드")
    private String reasoncode;

    /** 사유메시지 */
    @Schema(description = "사유메시지")
    private String reasonmsg;
    
	/** LOTTABLE01 */
	@Schema(description = "LOTTABLE01")
	private String lottable01;    
    
}
