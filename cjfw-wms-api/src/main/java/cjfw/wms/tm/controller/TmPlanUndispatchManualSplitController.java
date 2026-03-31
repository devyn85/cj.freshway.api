package cjfw.wms.tm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.wms.tm.dto.TmPlanUndispatchManualSplitReqDto;
import cjfw.wms.tm.dto.TmPlanUndispatchManualSplitResDto;
import cjfw.wms.tm.service.TmPlanUndispatchManualSplitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/tm/planUndispatchManualSplit")
@RequiredArgsConstructor
@Tag(name = "배송 > 배차계획", description = "수동분할배차 컨트롤러")
public class TmPlanUndispatchManualSplitController {
	
	private final TmPlanUndispatchManualSplitService tmPlanUndispatchManualSplitService;
	
	@Operation(summary = "실착지 거래처 조회", description = "실착지 거래처 조회")
	@GetMapping(value = "/v1.0/getMasterList")
	public List<TmPlanUndispatchManualSplitResDto> getMasterList(TmPlanUndispatchManualSplitReqDto dto) {
		return tmPlanUndispatchManualSplitService.getMasterList(dto);
	}
	
}
