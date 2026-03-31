package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 팝업 고객조회 Request DTO
 */
@Data
public class CmCustPopupReqDto extends CommonDto {
	@Schema(description = "고객명")
    private String name;

	@Schema(description = "다중선택")
    private String multiSelect;
    
    @Schema(description = "999개 청크 목록(OR IN 생성용)")
	private java.util.List<java.util.List<String>> codeGroups;
    
    @Schema(description = "물류센터 코드", example = "2600")
    private String dcCode;
    
    @Schema(description = "거래처유형", example = "C")
	private String custType;
    
    @Schema(description = "추가정보 사용여부", example="Y")
    private String expandedColumns;
    
//    
	@Schema(description = "본점 고객코드/명")
	private String dlvSearchVal;
	
	@Schema(description = "판매처 고객코드/명")
	private String saleCustSearchVal;
	
	@Schema(description = "관리처 고객코드/명")
	private String childCustSearchVal;
	
//	LIKE검색어 적용 시작
	@Schema(description = "검색어 영어")
	private String keywordEng;
	
	@Schema(description = "검색어 정규식")
	private String keywordRegexp;
	private String keywordRegexpDlv;
	private String keywordRegexpSale;
	private String keywordRegexpChild;
	
	@Schema(description = "영문검색어 정규식")		
	private String keywordEngRegexp;
	
	@Schema(description = "엔터검색 여부")
	private String isEnter;
	
	/** FIX검색여부.%1개일 */
	@Schema(description = "FIX검색여부.%1개일")
	private String fixedSearchYn;
	private String fixedSearchYnDlv;
	private String fixedSearchYnSale;
	private String fixedSearchYnChild;
	
	/** 콤마검색여부.엔터검색.input/돋보기 검색 시 콤마포함여부 */
	@Schema(description = "FIX검색여부.%1개일")
	private String commaSearchYn;
	
//	LIKE검색어 적용 시작	
}