package cjfw.wms.comfunc.func.http.feign.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import cjfw.wms.comfunc.func.http.dto.PostsPutDto;
import cjfw.wms.comfunc.func.http.dto.UserGetResDto;
import feign.Headers;


@FeignClient(name="${feign.test.name}", url="${feign.test.url}", configuration = FeignClientConfig.class)
public interface FeignTestClient {

	/**
	 * GET Method, Header, RequestParam 샘플
	 */
	@Headers("x-requester-id: {requester}")
	@GetMapping(value="/users")
	List<UserGetResDto> getUsers(@RequestHeader("requester")String requester, @RequestParam(value="requestParam") String requestParam);


	/**
	 * PUT Method, PatheParam, RequestBody 샘플
	 */
	@PutMapping(value="/posts/{id}")
	PostsPutDto updatePosts(@PathVariable(value="id") Integer id, @RequestBody PostsPutDto postsPutDto);

}
