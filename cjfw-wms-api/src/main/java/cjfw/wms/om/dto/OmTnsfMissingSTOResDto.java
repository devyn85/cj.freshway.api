package cjfw.wms.om.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.11.12 
 * @description : 누락분 STO 이체 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.12 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class OmTnsfMissingSTOResDto {
			
	/** 수급센터 */
    @Schema(description = "수급센터", example = "")
    private String dccode;

    /** 수급센터명 */
    @Schema(description = "수급센터명", example = "")
    private String dcname;
    
    /** 공급센터(코드만 이렇게 씀) */
    @Schema(description = "공급센터", example = "")
    private String toDccode;

    /** 공급센터명(코드만 이렇게 씀) */
    @Schema(description = "공급센터명", example = "")
    private String toDcname;

    /** 상품코드 */
    @Schema(description = "상품코드", example = "")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명", example = "")
    private String skuname;

    /** 공급센터로케이션 */
    @Schema(description = "공급센터로케이션", example = "")
    private String loc;

    /** 이체단위 */
    @Schema(description = "이체단위", example = "")
    private String uom;

    /** 요청수량 */
    @Schema(description = "요청수량", example = "")
    private String orderqty;

    /** 누락주문수량(누락분요청량) */
    @Schema(description = "누락주문수량(누락분요청량)", example = "")
    private String missOrderqty;

    /** 수급센터현재고 */
    @Schema(description = "수급센터현재고", example = "")
    private String openqty;

    /** 재고량(1순위) */
    @Schema(description = "재고량(1순위)", example = "")
    private String openqty1;
    
    /** 공급센터명 */
    @Schema(description = "공급센터명", example = "")
    private String dcname1;

    /** 재고량(2순위) */
    @Schema(description = "재고량(2순위)", example = "")
    private String openqty2;

    /** 공급센터명 */
    @Schema(description = "공급센터명", example = "")
    private String dcname2;
    
    /** 재고량(3순위) */
    @Schema(description = "재고량(3순위)", example = "")
    private String openqty3;
    
    /** 공급센터명 */
    @Schema(description = "공급센터명", example = "")
    private String dcname3;
    
    /** 재고량(4순위) */
    @Schema(description = "재고량(4순위)", example = "")
    private String openqty4;
    
    /** 공급센터명 */
    @Schema(description = "공급센터명", example = "")
    private String dcname4;
    
    /** 재고량(5순위) */
    @Schema(description = "재고량(5순위)", example = "")
    private String openqty5;
    
    /** 공급센터명 */
    @Schema(description = "공급센터명", example = "")
    private String dcname5;

    /** 발주량(센터) */
    @Schema(description = "발주량(센터)", example = "")
    private String orderDccode;
    
    /** 발주량(센터명) */
    @Schema(description = "발주량(센터명)", example = "")
    private String orderDcname;

    /** 발주량(발주량) */
    @Schema(description = "발주량(발주량)", example = "")
    private String supplyqty;

    /** 대응여부 */
    @Schema(description = "대응여부", example = "")
    private String respYn;

    /** 대응담당자(받는센터) ID */
    @Schema(description = "대응담당자(받는센터) ID", example = "")
    private String respId;

    /** 대응담당자(받는센터) 명 */
    @Schema(description = "대응담당자(받는센터) 명", example = "")
    private String respNm;

    /** 원_문서일자 */
    @Schema(description = "원_문서일자", example = "")
    private String orgDocdt;

    /** 원_문서번호 */
    @Schema(description = "원_문서번호", example = "")
    private String orgDocno;

    /** 원_문서라인 */
    @Schema(description = "원_문서라인", example = "")
    private String orgDocline;
	
    /** 회차 */
    @Schema(description = "회차", example = "")
    private String priority;
    
    /** 이체일자 */
    @Schema(description = "이체일자", example = "")
    private String deliverydate;
    
    /** 저장품 누락분 요청 사유 */
    @Schema(description = "저장품 누락분 요청 사유", example = "")
    private String reqReasoncode;
    
    /** 누락분이체대응여부 */
    @Schema(description = "누락분이체대응여부", example = "")
    private String missMoveRespYn;
    
    /** 누락분이체대응담당자ID */
    @Schema(description = "누락분이체대응담당자ID", example = "")
    private String missMoveRespId;
    
    /** 누락분이체대응담당자명 */
    @Schema(description = "누락분이체대응담당자명", example = "")
    private String missMoveRespNm;

    /** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/** 등록자 */
	@Schema(description = "등록자", example = "")
	private String regNm;
	
	/** 수정자 */
	@Schema(description = "수정자", example = "")
	private String updNm;
	
	/** 등록자 */
	@Schema(description = "등록자", example = "")
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
	
	/** 저장조건 */
	@Schema(description = "저장조건", example = "")
	private String storagetype;
	
	/** 사유 */
	@Schema(description = "사유", example = "")
	private String rmk;
	
	/** 사유 */
	@Schema(description = "사유", example = "")
	private String fromDccode;
	private String fromDcname;
	private String stoStatus;
		
}