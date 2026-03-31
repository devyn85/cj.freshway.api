package cjfw.wms.om.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.10.31 
 * @description : 당일광역보충발주 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.31 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class OmOrderCreationSTOOrdBaseResDto {
			
	/** 선택 */
    @Schema(description = "선택", example = "")
    private String checkyn;

    /** 회사코드 */
    @Schema(description = "회사코드", example = "")
    private String storerkey;

    /** 센터코드 */
    @Schema(description = "센터코드", example = "")
    private String dccode;

    /** 상품코드 */
    @Schema(description = "상품코드", example = "")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명", example = "")
    private String description;

    /** 재고속성 */
    @Schema(description = "재고속성", example = "")
    private String stockgrade;

    /** 단위 */
    @Schema(description = "단위 ( 주문, 재고 모두 기본단위를 기준으로 처리함을 원칙으로 하기에 아래 수량정보에 별도의 변환작업을 하지 않음)", example = "")
    private String uom;

    /** DC_A 가용재고 */
    @Schema(description = "DC_A 가용재고", example = "")
    private Long openqty1;

    /** DC_B 가용재고 */
    @Schema(description = "DC_B 가용재고", example = "")
    private Long openqty2;

    /** DC_C 가용재고 */
    @Schema(description = "DC_C 가용재고", example = "")
    private Long openqty3;

    /** DC_D 가용재고 */
    @Schema(description = "DC_D 가용재고", example = "")
    private Long openqty4;

    /** DC_E 가용재고 */
    @Schema(description = "DC_E 가용재고", example = "")
    private Long openqty5;

    /** DC_F 가용재고 */
    @Schema(description = "DC_F 가용재고", example = "")
    private Long openqty6;

    /** 발주센터 가용재고 */
    @Schema(description = "발주센터 가용재고", example = "")
    private Long openqty9;

    /** 입고예정량 */
    @Schema(description = "입고예정량", example = "")
    private Long dpQty;

    /** 주문량 ( SO주문량, 저장품, 배분량 제외 ) */
    @Schema(description = "주문량 ( SO주문량, 저장품, 배분량 제외 )", example = "")
    private Long orderqty;

    /** 발주예정량 ( 주문량 - 발주센터재고 - 입고예정 ) */
    @Schema(description = "발주예정량 ( 주문량 - 발주센터재고 - 입고예정 )", example = "")
    private Long reqOrderqty;

    /** 분배량 */
    @Schema(description = "분배량", example = "")
    private Long processqty;
    
    /** sto주문수량 */
    @Schema(description = "sto주문수량", example = "")
    private Long stoOrderqty;

    /** 공급센터1 발주량(보유재고량을 최대값으로 발주예정량 우선 적용) */
    @Schema(description = "공급센터1 발주량(보유재고량을 최대값으로 발주예정량 우선 적용)", example = "")
    private Long req1;

    /** 공급센터2 발주량(발주예정량 - 공급센터1발주량) */
    @Schema(description = "공급센터2 발주량(발주예정량 - 공급센터1발주량)", example = "")
    private Long req2;

    /** 공급센터3 발주량(발주예정량 - 공급센터1발주량-공급센터2발주량) */
    @Schema(description = "공급센터3 발주량(발주예정량 - 공급센터1발주량-공급센터2발주량)", example = "")
    private Long req3;

    /** 공급센터4 발주량(발주예정량 - 공급센터1발주량-공급센터2발주량-공급센터3발주량) */
    @Schema(description = "공급센터4 발주량(발주예정량 - 공급센터1발주량-공급센터2발주량-공급센터3발주량)", example = "")
    private Long req4;

    /** 공급센터5 발주량(발주예정량 - 공급센터1발주량-공급센터2발주량-공급센터3발주량-공급센터4발주량) */
    @Schema(description = "공급센터5 발주량(발주예정량 - 공급센터1발주량-공급센터2발주량-공급센터3발주량-공급센터4발주량)", example = "")
    private Long req5;

    /** 공급센터6 발주량(발주예정량 - 공급센터1발주량-공급센터2발주량-공급센터3발주량-공급센터4발주량-공급센터5발주량) */
    @Schema(description = "공급센터6 발주량(발주예정량 - 공급센터1발주량-공급센터2발주량-공급센터3발주량-공급센터4발주량-공급센터5발주량)", example = "")
    private Long req6;
    
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
    
    /** 호출여부 */
	@Schema(description = "호출여부", example = "")
	private String callYn;
	
	/** 수급센터코드 */
	@Schema(description = "수급센터코드", example = "")
	private String toDccode;
	
	/** 공급센터코드 */
	@Schema(description = "공급센터코드", example = "")
	private String fromDccode;
	
	/** 센터마감유형 */
	@Schema(description = "센터마감유형", example = "")
	private String dcClosetype;
	
	/** 공급우선순위 */
	@Schema(description = "공급우선순위", example = "")
	private String fromPriority;
	
	/** 수급우선순위 */
	@Schema(description = "수급우선순위", example = "")
	private String toPriority;
	
	/** 문서번호 리스트 */
	@Schema(description = "문서번호 리스트", example = "")
	private String docnoList;
	
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
		
}