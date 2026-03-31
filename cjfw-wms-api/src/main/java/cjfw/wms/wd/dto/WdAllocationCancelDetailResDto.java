package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.24 
 * @description : 자동취소 상세 및 지정취소 목록  결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.24 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "자동취소 상세 및 지정취소 목록 결과")
public class WdAllocationCancelDetailResDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	@Schema(description = "전표번호", example = "SLIP001")
    private String slipno;
    @Schema(description = "품목번호", example = "1")
    private String slipline;
    @Schema(description = "상품분류", example = "전자제품")
    private String skugroup;
    @Schema(description = "상품코드", example = "ITEM001")
    private String sku;
    @Schema(description = "상품명칭", example = "노트북")
    private String skuname;
    @Schema(description = "플랜트", example = "[PLANT01]메인플랜트")
    private String plantDescr;
    @Schema(description = "단위", example = "EA")
    private String uom;
    @Schema(description = "저장유무", example = "Y")
    private String channel;
    @Schema(description = "저장조건", example = "상온")
    private String storagetype;
    @Schema(description = "식별번호유무", example = "Y")
    private String serialyn;
    @Schema(description = "주문수량", example = "10")
    private BigDecimal orderqty;
    @Schema(description = "분배량", example = "5")
    private BigDecimal processqty;
    @Schema(description = "DC코드", example = "DC001")
    private String dccode;
    @Schema(description = "STORERKEY", example = "STORER001")
    private String storerkey;
    @Schema(description = "ORGANIZE", example = "ORG001")
    private String organize;
    @Schema(description = "AREA", example = "AREA001")
    private String area;
    @Schema(description = "DOCDT", example = "2025-01-01")
    private String docdt;
    @Schema(description = "DOCNO", example = "DOC001")
    private String docno;
    @Schema(description = "DOCTYPE", example = "TYPE001")
    private String doctype;
    @Schema(description = "DOCLINE", example = "1")
    private String docline;
    @Schema(description = "SLIPDT", example = "2025-01-01")
    private String slipdt;
    @Schema(description = "STATUS", example = "완료")
    private String status;
    @Schema(description = "PLANT", example = "PLANT01")
    private String plant;
}
