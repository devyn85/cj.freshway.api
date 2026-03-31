package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StTransactionReqDto;
import cjfw.wms.st.dto.StTransactionResDto;
import cjfw.wms.st.service.StTransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 성상수
 * @date : 2025.05.30
 * @description : 재고처리현황 Controller Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
*/
@Tag(name = "재고 > 재고현황 > 재고처리현황", description = "재고처리현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/transaction")
public class StTransactionController {

	private final StTransactionService stTransactionService;

	/** @description :재고처리현황 화면 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 sss 생성 </pre>
	*/
	@Operation(summary = "재고처리현황 목록 조회", description = "트랜잭션 유형별 처리 현황 조회")
	@PostMapping(value="/v1.0/getMasterList")
	public ApiResult<List<StTransactionResDto>> getMasterList(@RequestBody StTransactionReqDto reqDto) {
		return ApiResult.createResult(stTransactionService.getMasterList(reqDto));
	}
}
