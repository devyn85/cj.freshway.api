/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.comfunc.func.mail.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.comfunc.func.mail.dto.SampleMailSendReqDto;
import cjfw.wms.comfunc.func.mail.service.SampleMailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "메일 전송", description = "SMTP 메일전송 샘플")
@RestController
@Slf4j
@RequestMapping("comfunc/func/mail")
@RequiredArgsConstructor
public class SampleMailController {

	private final SampleMailService sampleMailService;

	/**
	 * 템플릿 메일 전송 컨트롤러<br>
	 * form 입력을 받지 않고 메일을 전송<br>
	 */
	@Operation(summary = "메일 전송", description = "form 입력을 받지 않고 메일을 전송")
	@PostMapping(value = "/sendSimpleMail")
	public ApiResult sendSimpleMail (@RequestBody SampleMailSendReqDto sampleMailSendReqDto) {
		// 샘플 정보 생성
		sampleMailSendReqDto.setMailTemplateId("basic.mtp");
		sampleMailSendReqDto.setMailTitle("Test mail");
		sampleMailSendReqDto.setSenderAddr("tester@cj.net");
		sampleMailSendReqDto.setSenderId("tester");
		sampleMailSendReqDto.setNewFileName("NewAttachFileName.txt");
		sampleMailSendReqDto.setPhysicFilePath(".");
		sampleMailSendReqDto.setPhysicFileName("hello.txt");
		if(sampleMailSendReqDto.getReceiverAddr() == null){
			sampleMailSendReqDto.setReceiverAddr("test@cj.net");
		}

		boolean isSuc = sampleMailService.sendSimpleMail(sampleMailSendReqDto);
		if(isSuc){
			return ApiResult.createResult("메일을 발송했습니다.");
		} else{
			return ApiResult.createResult("발송을 실패 하였습니다. 수신자 중 수신을 못한 수신자가 있을 수 있습니다.", 101);
		}
	}
	
	/**
	 * [ 추가 참조 샘플 코드 - 코드만 참조 ]
	 * 메일 보내기 서비스 호출<br>
	 * form에 입력 내용을 바탕으로 메일 전송<br>
	 */
//	@RequestMapping(value = "/sendMail")
//	public Map sendMail (HttpServletRequest request, HttpServletResponse response, Map inParams) {
//		inParams.setVariable("MAIL_RICIEVER_ADDR", request.getParameter("recv_addr"));
//		inParams.setVariable("MAIL_RICIEVER_USER_ID", request.getParameter("user_id"));
//		inParams.setVariable("MAIL_TEMPLETE", request.getParameter("templete"));
//		inParams.setVariable("MAIL_TILTLE", request.getParameter("title"));
//		inParams.setVariable("MAIL_ARGS", request.getParameter("args"));
//
//		Map outParams = sampleMailService.sendMail(inParams);
//		return outParams;
//	}
}
