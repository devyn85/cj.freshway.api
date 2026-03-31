package cjfw.wms.comfunc.func.http.feign.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.comfunc.func.http.dto.PostsPutDto;
import cjfw.wms.comfunc.func.http.dto.UserGetResDto;
import cjfw.wms.comfunc.func.http.feign.service.FeignClientService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("comfunc/http/feignclient")
public class FeignClientController {

	private final FeignClientService feignClientService;
	
	@GetMapping(value = "/get")
	public ApiResult<List<UserGetResDto>> get() {
		return ApiResult.createResult(feignClientService.get());
	}

	@PutMapping(value = "/update")
	public ApiResult<PostsPutDto> update() {
		return ApiResult.createResult(feignClientService.update());
	}
}
