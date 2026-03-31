package cjfw.wms.om.dto;

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
public class OmAutoOrderSetupTgsaResDto {
			
    /** 고객사코드 */
    @Schema(description = "고객사코드", example = "")
    private String storerkey;
    
	/** 공급받는센터코드 */
	@Schema(description = "공급받는센터코드", example = "")
	private String toDccode;
    
	/** 마감유형 */
	@Schema(description = "마감유형", example = "")
	private String dcClosetype;

    /** 발주코드 */
    @Schema(description = "발주코드", example = "")
    private String purchaseCd;

    /** 발주명 */
    @Schema(description = "발주명", example = "")
    private String purchaseName;

    /** 발주정보요약 */
    @Schema(description = "발주정보요약", example = "")
    private String purchaseInfo;
    
    /** 대상건수 */
    @Schema(description = "대상건수", example = "")
    private String skuCnt;

    /** 삭제여부 */
    @Schema(description = "삭제여부", example = "")
    private String delYn;
    
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
		
}