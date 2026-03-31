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
 * @date        : 2025.07.02
 * @description : KIT상품기준정보 조회 결과 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.02        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Schema(description = "KIT상품기준정보 조회 결과 DTO")
public class MsKitResDto {
	@Schema(description = "체크 여부", example = " ")
    private String checkYn;
	
	@Schema(description = "시리얼키")
    private long serialKey;

    @Schema(description = "순서 번호")
    private BigDecimal seq; // DENSE_RANK 결과이므로 BigDecimal

    @Schema(description = "물류센터 코드", example = "DC001")
    private String dcCode;
    
    @Schema(description = "소분 여부", example = "N")
    private String prtnYn;

    @Schema(description = "KIT 상품 코드", example = "KITSKU001")
    private String kitSku;

    @Schema(description = "KIT 상품명")
    private String kitNm;

    @Schema(description = "구성 상품 코드", example = "SKU001")
    private String sku;

    @Schema(description = "구성 상품명")
    private String description;

    @Schema(description = "수량")
    private BigDecimal qty; // 수량이므로 BigDecimal

    @Schema(description = "Kit상품 단위 (UOM)")
    private String kitUom;
    
    @Schema(description = "단위 (UOM)")
    private String uom;

    @Schema(description = "UOM 수량 (박스당 낱개수)", example = "10")
    private BigDecimal uomQty; // 수량이므로 BigDecimal

    @Schema(description = "시작 일자", example = "YYYYMMDD")
    private String fromDate;

    @Schema(description = "종료 일자", example = "YYYYMMDD")
    private String toDate;

    @Schema(description = "등록 일자")
    private String addDate;

    @Schema(description = "등록자")
    private String addWho;
    
    @Schema(description = "수정 일자")
    private String editDate;

    @Schema(description = "수정자")
    private String editWho;

    @Schema(description = "삭제 여부", example = "Y/N")
    private String delYn;

    @Schema(description = "업로드 플래그 (상품기준정보 오류 여부)", example = "N")
    private String uploadFlg;
    
    @Schema(description = "최종 변경자")
    private String editWhoNm;
    
    @Schema(description = "최초 등록자")
    private String addWhoNm;
    
    @Schema(description = "커스텀 엑스트라 체크박스(없으면 전체 선택 해제 안 됨)", example = "N")
    private String customRowCheckYn = "N";
    
    /** 체크결과 */
	@Schema(description = "체크결과", example = "N")
	private String processYn;
	
	/** 체크메세지 */
	@Schema(description = "체크메세지", example = "존재하지 않는 거래처유형 코드입니다.")
	private String processMsg;
}
