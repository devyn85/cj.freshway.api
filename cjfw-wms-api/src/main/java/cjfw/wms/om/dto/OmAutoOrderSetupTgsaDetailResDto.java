package cjfw.wms.om.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : LeeJeongCheol (progs@cj.net) 
 * @date : 2026.03.06 
 * @description : 당일광역보충발주관리 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.03.06 LeeJeongCheol (progs@cj.net) 생성 </pre>
 */
@Data
public class OmAutoOrderSetupTgsaDetailResDto {
			
	/** 고객사코드 */
    @Schema(description = "고객사코드", example = "")
    private String storerkey;

    /** 발주코드 */
    @Schema(description = "발주코드", example = "")
    private String purchaseCd;

    /** 상품코드 */
    @Schema(description = "상품코드", example = "")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명", example = "")
    private String skuName;

    /** 구매단위구분코드 */
    @Schema(description = "구매단위구분코드", example = "")
    private String uomDivCd;

    /** 재고수량(미사용_통계데이터에서 취득으로변경) */
    @Schema(description = "재고수량(미사용_통계데이터에서 취득으로변경)", example = "")
    private Integer qty;

    /** 발주량산정기준(1-주문량, 2-안전재고량) */
    @Schema(description = "발주량산정기준(1-주문량, 2-안전재고량)", example = "")
    private String ordQtyDivCd;
    
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

	
	/** 저장시 검증 메시지 관련 컬럼 */
	/** 상품코드 유효성 */
	@Schema(description = "상품코드 유효성", example = "")
	private String skuChk;
	
	/** 중복등록 */
	@Schema(description = "중복등록", example = "")
	private String skuDupChk;
	
	/** 발주단위구분 코드 유효성 */
	@Schema(description = "발주단위구분 코드 유효성", example = "")
	private String codeChk;
	
	/** 중복등록 */
	@Schema(description = "중복등록", example = "")
	private String duplicateChk;
	
	/** 결과메시지 */
	@Schema(description = "결과메시지", example = "")
	private String resultMessage;
	
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/** excel 유효성 리스트 */
	@Schema(description = "excel 유효성 리스트", example = "N")
	private List<OmAutoOrderSetupTgsaDetailResDto> validExcelList;
	
}