package cjfw.wms.cb.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.file.FileUpload;
import cjfw.core.model.ApiResult;
import cjfw.wms.cb.dto.CbNoticeDetailResDto;
import cjfw.wms.cb.dto.CbNoticeRecvReqDto;
import cjfw.wms.cb.dto.CbNoticeRecvResDto;
import cjfw.wms.cb.dto.CbNoticeReqDto;
import cjfw.wms.cb.dto.CbNoticeResDto;
import cjfw.wms.cb.service.CbNoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.09.19 
 * @description : 기준정보 > 게시판관리 > 공지사항 조회 및 저장 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.19 JiSooKim (jskim14@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 게시판관리 > 시스템정보", description = "공지사항 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cb/notice")
public class CbNoticeController {

	private final CbNoticeService cbNoticeService;

	/**
	 * @description : 공지사항 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.19 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "공지사항 조회", description = "공지사항 조회")
	@GetMapping(value = "/v1.0/getNoticeList")
	public ApiResult<List<CbNoticeResDto>> getNoticeList(@Valid CbNoticeReqDto cbNoticeReqDto) {
		return ApiResult.createResult(cbNoticeService.getNoticeList(cbNoticeReqDto));
	}
	
	/**
	 * @description : 공지사항 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.19 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "공지사항 상세 조회", description = "공지사항 상세 조회")
	@GetMapping(value = "/v1.0/getNoticeDetail")
	public ApiResult<CbNoticeDetailResDto> getNoticeDetail(@Valid CbNoticeReqDto cbNoticeReqDto) {
		return ApiResult.createResult(cbNoticeService.getNoticeDetail(cbNoticeReqDto));
	}
	
	/**
	 * @description : 수신처 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.19 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "수신처 조회", description = "수신처 조회")
	@GetMapping(value = "/v1.0/getNoticeRecvList")
	public ApiResult<List<CbNoticeRecvResDto>> getRecvList(@Valid CbNoticeReqDto cbNoticeReqDto) {
		return ApiResult.createResult(cbNoticeService.getRecvList(cbNoticeReqDto));
	}

	/**
	 * @description : 공지사항 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.19 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "공지사항 저장", description = "공지사항 저장")
	@PostMapping(value = "/v1.0/saveNotice", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ApiResult<String> saveNotice(
			@RequestPart(value="params", required = true) List<CbNoticeReqDto> cbNoticeReqDto,
			@RequestPart(value="file", required = false) List<MultipartFile> files,
			@RequestPart(value="fileInfoList", required = false) List<FileUpload> fileInfoList,
			@RequestPart(value="recvGroupList", required = false) List<CbNoticeRecvReqDto> recvGroupList) {
		return ApiResult.createResult(cbNoticeService.saveBoard(cbNoticeReqDto, files, fileInfoList, recvGroupList));
	}
}