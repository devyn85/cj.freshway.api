package cjfw.wms.st.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.06.25 
 * @description : 재고속성변경 헤더-Sub 목록 조회 Response DTO
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.25 ParkJinWoo 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StConvertCgExDcDetailListResDto extends CommonProcedureDto {
    /** 체크 여부 */
    @Schema(description = "체크 여부")
    private String checkYn;

    /** 재고 식별 KEY */
    @Schema(description = "재고 식별 KEY")
    private Long serialKey;
    
    @Schema(description = "재고 식별 KEY")
    private Long area;

    /** 물류센터 */
    @Schema(description = "물류센터")
    private String dcCode;

    /** 창고 */
    @Schema(description = "창고")
    private String organize;
    
    /** 창고명 */
    @Schema(description = "창고명")
    private String organizeNm;

    /** 로케이션 */
    @Schema(description = "로케이션")
    private String fromLoc;
    
    /** 로케이션 */
    @Schema(description = "로케이션")
    private String fromLot;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 상품명칭 */
    @Schema(description = "상품명칭")
    private String skuName;

    /** 상품분류 */
    @Schema(description = "상품분류")
    private String mcName;

    /** 단위 */
    @Schema(description = "단위")
    private String uom;

    /* ───── 재고 수량 ───── */

    /** 현재고수량 */
    @Schema(description = "현재고수량")
    private Double qty;

    /** 가용재고수량 */
    @Schema(description = "가용재고수량")
    private Double openQty;

    /** 재고할당수량 */
    @Schema(description = "재고할당수량")
    private Double qtyAllocated;

    /** 비킹재고 */
    @Schema(description = "비킹재고")
    private Double qtyPicked;

    /** 작업수량 */
    @Schema(description = "작업수량")
    private Double tranQty;

    /* ───── 환산재고 그룹 ───── */

    /** 평균중량 */
    @Schema(description = "평균중량")
    private Double avgWeight;

    /** 환산박스 */
    @Schema(description = "환산박스")
    private Double calBox;

    /** 실박스 */
    @Schema(description = "실박스")
    private Double realCfmBox;

    /** 작업박스수량 */
    @Schema(description = "작업박스수량")
    private Double tranBox;

    /* ───── 재고 속성/사유 ───── */

    /** FROM재고속성 */
    @Schema(description = "FROM재고속성")
    private String stockGradeName;

    /** TO재고속성 */
    @Schema(description = "TO재고속성")
    private String toStockGrade;

    /** 사유코드 */
    @Schema(description = "사유코드")
    private String reasonCode;

    /** 처리결과 */
    @Schema(description = "처리결과")
    private String reasonMsg;

    /* ───── 기간/LOTTABLE ───── */

    /** 기준일(유통,제조) */
    @Schema(description = "기준일(유통,제조)")
    private String lotTable01;

    /** 소비기한(잔여/전체) */
    @Schema(description = "소비기한(잔여/전체)")
    private String durationTerm;

    /* ───── SERIALINFO ───── */

    /** 이력번호 */
    @Schema(description = "이력번호")
    private String serialNo;

    /** B/L번호 */
    @Schema(description = "B/L번호")
    private String convSerialNo;

    /* ───── 기타 ───── */

    /** 재고ID */
    @Schema(description = "재고ID")
    private String fromStockId;

    /** 구매전표 */
    @Schema(description = "구매전표")
    private String poKey;

    /** 구매라인 */
    @Schema(description = "구매라인")
    private String poLine;
    /** 구매라인 */
    @Schema(description = "구매라인")
    private String fromStockGrade;
    /** 구매라인 */
    @Schema(description = "구매라인")
    private String fromStockType;
    
    /** boxFlag */
    @Schema(description = "boxFlag")
    private String boxFlag;


		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
