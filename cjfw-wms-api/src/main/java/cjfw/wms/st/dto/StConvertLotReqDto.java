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

/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : sss (kduimux@cj.net)
 * @date : 2025.06.02
 * @description : 유통기한변경 조회 Request DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.02 sss (kduimux@cj.net) 생성 </pre>
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "유통기한변경 조회 Request DTO")
public class StConvertLotReqDto extends CommonProcedureDto {
    /** 저장 리스트 */
    List<StConvertLotResDto> saveList;	

    /** 물류센터 */
    @Schema(description = "물류센터")
    private String fixdccode;		

	/** 상품코드 */
	@Schema(description = "sku")
	private String sku;
	
    /** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;		

	/** 상품분류 */
	@Schema(description = "skugroup")
	private String skugroup;

	/** fromzone */
	@Schema(description = "fromzone")
	private String fromzone;

	/** tozone */
	@Schema(description = "tozone")
	private String tozone;

	/** fromloc */
	@Schema(description = "fromloc")
	private String fromloc;

	/** toloc */
	@Schema(description = "toloc")
	private String toloc;

	/** 재고속성 */
	@Schema(description = "stockgrade")
	private String stockgrade;

	/** serialno */
	@Schema(description = "serialno")
	private String serialno;

	/** blno */
	@Schema(description = "blno")
	private String blno;

	/** contractcompany */
	@Schema(description = "contractcompany")
	private String contractcompany;
	
    /** 거래처(다중OR검색) */
	@MultiSearch
    @Schema(description = "거래처-다중OR검색")
    private List<List<String>> contractcompanyMulti;	
	
	/** sortkey */
	@Schema(description = "sortkey")
	private String sortkey;
	
	/** sortorder */
	@Schema(description = "")
	private String searchserial;
	
	
    /** 변환유형 (예: 'CL') */
    @Schema(description = "변환유형")
    private String converttype; 

    /** 기준일(소비,제조) */
    @Schema(description = "기준일(소비,제조)")
    private String lottable01;

    /** lottable02 */
    @Schema(description = "lottable02")
    private String lottable02;

    /** lottable03 */
    @Schema(description = "lottable03")
    private String lottable03;

    /** lottable04 */
    @Schema(description = "lottable04")
    private String lottable04;

    /** lottable05 */
    @Schema(description = "lottable05")
    private String lottable05;

    /** 사유코드 */
    @Schema(description = "사유코드")
    private String reasoncode;

    /** 사유메시지 */
    @Schema(description = "사유메시지")
    private String reasonmsg;
	
    /** 날짜구분 */
    @Schema(description = "날짜구분")
    private String dtFlag;
				

}