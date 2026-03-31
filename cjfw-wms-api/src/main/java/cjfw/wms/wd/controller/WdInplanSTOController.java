package cjfw.wms.wd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdInplanSTOEntity;
import cjfw.wms.wd.dto.WdInplanSTOReqDto;
import cjfw.wms.wd.dto.WdInplanSTOResDto;
import cjfw.wms.wd.dto.WdInplanSTOResExcelDto;
import cjfw.wms.wd.dto.WdInplanSTOResTab1Dto;
import cjfw.wms.wd.dto.WdInplanSTOResTab2Dto;
import cjfw.wms.wd.dto.WdInplanSTOResTab3Dto;
import cjfw.wms.wd.service.WdInplanSTOService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "출고 > 출고현황 > 광역출고현황")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/wd/inplanSTO")
public class WdInplanSTOController {
    private final WdInplanSTOService wdInplanSTOservice;

    /**
	 * @description : 광역출고현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.13 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "광역출고현황 목록 조회", description = "광역출고현황 목록 조회")
    @PostMapping(value = "/v1.0/getMasterList")
    public ApiResult<List<WdInplanSTOResDto>> getMasterList(@RequestBody WdInplanSTOReqDto dto) {
        return ApiResult.createResult(wdInplanSTOservice.getMasterList(dto));
    }
    
    /**
	 * @description : 광역출고현황 - 주문현황
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.13 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "주문현황 목록 조회", description = "주문현황 목록 조회")
    @PostMapping("/v1.0/getMasterListTab1")
    public ApiResult<List<WdInplanSTOResTab1Dto>> getMasterListTab1(@RequestBody WdInplanSTOReqDto dto) {
        return ApiResult.createResult(wdInplanSTOservice.getMasterListTab1(dto));
    }
    
    /**
	 * @description : 광역출고현황 - 이력현황
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.13 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "이력현황 목록 조회", description = "이력현황 목록 조회")
    @PostMapping("/v1.0/getMasterListTab2")
    public ApiResult<List<WdInplanSTOResTab2Dto>> getMasterListTab2(@RequestBody WdInplanSTOReqDto dto) {
        return ApiResult.createResult(wdInplanSTOservice.getMasterListTab2(dto));
    }
    
    /**
	 * @description : 광역출고현황 - 출고이력정보
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.13 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "출고이력정보 목록 조회", description = "출고이력정보 목록 조회")
    @PostMapping("/v1.0/getMasterListTab3")
    public ApiResult<List<WdInplanSTOResTab3Dto>> getMasterListTab3(@RequestBody WdInplanSTOReqDto dto) {
        return ApiResult.createResult(wdInplanSTOservice.getMasterListTab3(dto));
    }
    
    /**
     * @description : 광역출고현황 - 엑셀다운로드
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.02.01 공두경	생성 </pre>
     */
    @Operation(summary = "광역출고현황 엑셀다운로드 조회", description = "광역출고현황 엑셀다운로드 조회")
    @PostMapping("/v1.0/getExcellist")
    public ApiResult<List<WdInplanSTOResExcelDto>> getExcellist(@RequestBody WdInplanSTOReqDto dto) {
    	return ApiResult.createResult(wdInplanSTOservice.getExcellist(dto));
    }
    
    /**
	 * @description : 광역출고현황 - 마스터 그리드 인쇄
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @PostMapping("/v1.0/getPrintMasterInvoice")
	public ApiResult<Map> getPrintMasterInvoice(@RequestBody WdInplanSTOReqDto dto) {
		
		List<WdInplanSTOEntity> masterList = wdInplanSTOservice.getPrintMasterInvoice(dto);
		List<WdInplanSTOEntity> detailList = wdInplanSTOservice.getPrintDetailInvoice(dto);
		return ApiResult.createResult(new HashMap<>(){{
			put("masterList", masterList);
			put("detailList", detailList);
		}});
    }
	
    
    /**
	 * @description : 광역출고현황 - 마스터 그리드 인쇄
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @PostMapping("/v1.0/getPrintDetailInvoice")
    public ApiResult<List<WdInplanSTOEntity>> getPrintDetailInvoice(@RequestBody WdInplanSTOReqDto dto) {
        return ApiResult.createResult(wdInplanSTOservice.getPrintDetailInvoice(dto));
    }
    
}
