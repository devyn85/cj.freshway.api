package cjfw.wms.wd.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.12.09
 * @description : 퀵배송조회 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.09 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "퀵배송조회 Request DTO")
@Data
public class WdQuickSearchReqDto extends CommonDto {
	/** API 조회 결과 */
	List<WdQuickResAPI02Dto> resultList;
	
	/** 리스트*/
    List<WdQuickResAPI02Dto> saveList;		

	/** 일자(From) */
	@Schema(description = "일자(From)")
	private String dt1;

	/** 일자(To) */
	@Schema(description = "일자(To)")
	private String dt2;
	
    /** 총건수 */
    @Schema(description = "총건수")
    private BigDecimal totalRecord;
    
    /** 총페이지 */
    @Schema(description = "총페이지")
    private BigDecimal totalPage;    
    
    /** 현재페이지 */
    @Schema(description = "현재페이지")
    private BigDecimal currentPage;
    
    /** 페이지당 건수 */
    @Schema(description = "페이지당 건수")
    private BigDecimal limit;	
	
}
