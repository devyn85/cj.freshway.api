package cjfw.wms.comfunc.func.http.feign.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.wms.comfunc.func.http.dto.PostsPutDto;
import cjfw.wms.comfunc.func.http.dto.UserGetResDto;
import cjfw.wms.comfunc.func.http.feign.client.FeignTestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class FeignClientService {


	private final FeignTestClient feignTestClient;
	
	public List<UserGetResDto> get() {
		log.info("FeignClientService.get()");
		log.info("== feignclient start");
		List<UserGetResDto> userList = feignTestClient.getUsers("sampleHeader", "sampleRequestParam");
		log.info("== feignclient end");
		return userList;
	}

	public PostsPutDto update(){
		Integer id = 1;
		PostsPutDto putReqDto = PostsPutDto.builder()
				.id(id)
				.title("Title")
				.body("Body")
				.userId(1)
				.build();
		return feignTestClient.updatePosts(id, putReqDto);
	}
	
}
