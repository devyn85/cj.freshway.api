package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StConvertLotReqDto;
import cjfw.wms.st.dto.StConvertLotResDto;
import cjfw.wms.st.service.StConvertLotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 성상수
 * @date : 2025.05.30
 * @description : 유통기한변경 Controller Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
*/
@Tag(name = "재고 > 재고조정 > 유통기한변경", description = "유통기한변경")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/convertLot")
public class StConvertLotController {

	private final StConvertLotService stConvertLotService;

	/** @description :유통기한변경 화면 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 sss 생성 </pre>
	*/
	@Operation(summary = "유통기한변경 목록 조회", description = "유통기한변경 목록 조회")
	@PostMapping(value="/v1.0/getMasterList")
	public ApiResult<List<StConvertLotResDto>> getMasterList(@RequestBody StConvertLotReqDto dto) {
		return ApiResult.createResult(stConvertLotService.getMasterList(dto));
	}
	
	/** @throws Exception 
	 * @description :유통기한변경 화면 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 sss 생성 </pre>
	*/
	@Operation(summary = "유통기한변경 목록 저장", description = "유통기한변경 목록 저장")
	@PostMapping(value="/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody StConvertLotReqDto reqDto)  {
		return ApiResult.createResult(stConvertLotService.saveMasterList(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}	
}
