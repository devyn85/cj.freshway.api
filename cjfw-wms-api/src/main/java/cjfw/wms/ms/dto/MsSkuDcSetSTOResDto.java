package cjfw.wms.ms.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.04
 * @description : 센터상품속성(광역일배) 조회 결과 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.04        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Schema(description = "센터상품속성(광역일배) 조회 결과 DTO")
public class MsSkuDcSetSTOResDto {
	
	@Schema(description = "데이터 번호")
    private String serialKey;

    @Schema(description = "센터 코드", example = "DC001")
    private String dcCode;

    @Schema(description = "대상 센터 코드")
    private String toDcCode;

    @Schema(description = "고객사", example = "CUST001")
    private String storerKey;

    @Schema(description = "상품 코드", example = "SKU001")
    private String sku;

    @Schema(description = "상품 설명")
    private String skuDescr;

    @Schema(description = "삭제 여부", example = "N")
    private String delYn;

    @Schema(description = "최초 등록 시간", example = "20250704143000")
    private String addDate;

    @Schema(description = "최종 변경 시간", example = "20250704143000")
    private String editDate;

    @Schema(description = "최초 등록자")
    private String addWho;

    @Schema(description = "최종 변경자")
    private String editWho;

    @Schema(description = "SMS 처리 대상 여부")
    private String smsYn;
    
    @Schema(description = "최종 변경자")
    private String editWhoNm;
    
    @Schema(description = "최초 등록자")
    private String addWhoNm;
    
    /** 체크결과 */
	@Schema(description = "체크결과", example = "N")
	private String processYn;
	
	/** 체크메세지 */
	@Schema(description = "체크메세지", example = "존재하지 않는 거래처유형 코드입니다.")
	private String processMsg;
}
