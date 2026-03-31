package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdDistributePlanSkuSumReqDto;
import cjfw.wms.wd.dto.WdDistributePlanSkuSumResDto;
import cjfw.wms.wd.service.WdDistributePlanSkuSumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net) 
 * @date : 2025.05.19 
 * @description : 미출 예정 확인 (상품별 합계) Controller Class 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.19 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@Tag(name = "출고 > 출고현황 > 미출예정확인(상품별합계)", description = "미출예정확인(상품별합계) 목록 조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/distributePlanSkuSum")
public class WdDistributePlanSkuSumController {
    
	private final WdDistributePlanSkuSumService wdDistributePlanSkuSumService; 
	
	
	/** @description : 미출예정확인(상품별합계) 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.25 YangChangHwan (iamai@cj.net) 생성 </pre>
	*/
	@Operation(summary = "미출예정확인(상품별합계) 목록 조회", description = "미출예정확인(상품별합계) 목록 조회")
	@GetMapping(value="/v1.0/getWdDistributePlanSkuSumList")
	public ApiResult<List<WdDistributePlanSkuSumResDto>> getWdDistributePlanSkuSumList(@Valid WdDistributePlanSkuSumReqDto reqDto) {
		
		return ApiResult.createResult(wdDistributePlanSkuSumService.getWdDistributePlanSkuSumList(reqDto));
	}
	
	 
}
