package cjfw.wms.dv.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : YangChangHwan (iamai@cj.net)
 * @date : 2025.06.14
 * @description : 일배입출차이현황 Request DTO
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.14 YangChangHwan (iamai@cj.net) 생성
 *         </pre>
 */
@Schema(description = "일배입출차이현황 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DvDataviewMultiSpReqDto extends CommonDto {

	// 기간(시작) - 시작 출고일자
	@Schema(description = "시작 출고일자" , nullable = false, example = "20250601")
	private String fromSlipdt;

	// 기간(종료) - 종료 출고일자
	@Schema(description = "종료 출고일자"  , nullable = false, example = "20250602")
	private String toSlipdt;

	/** 물류센터코드/명 */
	@Schema(description = "물류센터코드")
	private String dccode;
	
	/** 물류센터코드/명 */
	@Schema(description = "물류센터코드")
	private String fixdccode;

    /** 창고-다중선택 */
	@Schema(description = "창고-다중선택", nullable = false, example = "4900,8774")
	private String organize;
	
    /** 창고(다중검색) */
	@MultiSearch
    @Schema(description = "창고-다중OR검색")
    private List<String> organizeMulti; 

	/** 상품코드 */
	@Schema(description = "상품코드")
	private String sku;
	
	/** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;  
	
	/** 플랜트 */
	@Schema(description = "플랜트")
	private String plant;
	
	/** 협력사 */
	@Schema(description = "협력사")
	private String fromcustkey;
	
	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> fromcustkeyMulti; 
	
	/** 일배구분 */
	@Schema(description = "일배구분")
	private String putawaytype;
	
	/** 차이수량유무 */
	@Schema(description = "차이수량유무")
	private String diffqtyyn;

	// 출고일자
	@Schema(description = "출고일자" , example = "20250601")
	private String slipdt;

}
