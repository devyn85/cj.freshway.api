package cjfw.wms.st.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.16
 * @description : 재고 > 재고조정 > 이력상품바코드변경 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.16 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고 > 재고조정 > 이력상품바코드변경 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StConvertIdSNReqDto extends CommonProcedureDto {

	/* 물류센터 */
	@Schema(description = "fixdccode")
	private String fixdccode;

	/* 창고 */
	@Schema(description = "fixstorerkey")
	private String fixstorerkey;

	/* 조직 */
	@Schema(description = "fixorganize")
	private String fixorganize;

	/* fixarea */
	@Schema(description = "fixarea")
	private String fixarea;
	
	/* 01. 상품코드 */
	@Schema(description = "01. 상품코드")
	private String sku;
	
    /* 상품코드-멀티 */
	@MultiSearch
    @Schema(description = "상품코드-멀티")
    private List<List<String>> skuMulti;
	
	/* 02. 상품분류 */
	@Schema(description = "02. 상품분류")
	private String skugroup;

	/* 03. FROM ZONE */
	@Schema(description = "03. FROM ZONE")
	private String fromzone;
	
	/* 04. TO ZONE */
	@Schema(description = "04. TO ZONE")
	private String tozone;

	/* 05. FROM 로케이션 */
	@Schema(description = "05. FROM 로케이션")
	private String fromloc;

	/* 06. TO 로케이션 */
	@Schema(description = "06. TO 로케이션")
	private String toloc;
	
	/* 07. 이력번호 */
	@Schema(description = "07. 이력번호")
	private String serialno;
	
    /* 사유코드 */
    @Schema(description = "사유코드")
    private String reasoncode;

    /* 사유메시지 */
    @Schema(description = "사유메시지")
    private String reasonmsg;

    /* 재고ID */
    @Schema(description = "재고ID")
    private String toStockid;
	
    /* 변환유형 */
    @Schema(description = "변환유형")
    private String converttype; 
    
	/* 메인그리드 저장 리스트 */
	@Schema(description = "메인그리드 저장 리스트")
	List<StConvertIdSNResDto> saveDataList;
	
}
