package cjfw.wms.om.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.07.08
 * @description : 외부창고 이체발주 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.08    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부창고 이체발주 조회 결과") 
public class OmPurchaseOutSTOResDto extends CommonProcedureDto {	
    
	/** 물류센터 */
	@Schema(description = "물류센터", nullable = false, example = "")
	private String dccode;
	
	/** 사업장 */
    @Schema(description = "사업장", nullable = false, example = "")
    private String plant;
    
    /** 스토리지로케이션 SAP로케이션 */
    @Schema(description = "스토리지로케이션 SAP로케이션", nullable = false, example = "")
    private String storageloc;
    
    /** 고객사원주문량 */
    @Schema(description = "고객사원주문량", nullable = false, example = "")
    private String storerorderqty;
    
    /** 고객원주문단위 */
    @Schema(description = "고객원주문단위", nullable = false, example = "")
    private String storeruom;
    
    /** 배송완료일자 */
    @Schema(description = "배송완료일자", nullable = false, example = "")
    private String deliverydate;

	/** 문서번호 */
    @Schema(description = "문서번호", nullable = false, example = "")
    private String docno;
    
    /** 문서라인 */
    @Schema(description = "문서라인", nullable = false, example = "")
    private String docline;
    
    /** 상품코드 */
    @Schema(description = "상품코드", nullable = false, example = "")
    private String sku;
    
    /** 주문유형 */
    @Schema(description = "주문유형", nullable = false, example = "")
    private String ordertype;
    
    /** FROM_정산처코드 */
    @Schema(description = "FROM_정산처코드", nullable = false, example = "")
    private String fromBilltokey;
    
    /** 고객사코드 */
    @Schema(description = "고객사코드", nullable = false, example = "")
    private String storerkey;
    
    /** 쇼핑몰 */
    @Schema(description = "쇼핑몰", nullable = false, example = "")
    private String shoppingmall;
    
    /** 삭제여부 */
    @Schema(description = "삭제여부", nullable = false, example = "")
    private String delYn;
    
    /** 실행여부 */
    @Schema(description = "실행여부", nullable = false, example = "")
    private String callYn;
    
    /** I/F구분 */
    @Schema(description = "I/F구분", nullable = false, example = "")
    private String updateIfFlag;
	
	/** 인터페이스메모 */
    @Schema(description = "인터페이스메모", nullable = false, example = "")
    private String updateIfMemo;	
    
	/** I/F구분 */
    @Schema(description = "I/F구분", nullable = false, example = "")
    private String searchIfFlag;
   
}
