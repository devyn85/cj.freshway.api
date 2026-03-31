package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.10.30 
 * @description : 알림 요청
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "알림 요청")
public class CmMainNoticeReqDto extends CommonDto {
	/** 게시판번호 */
	@Schema(description = "게시판번호", example = "")
	private String brdnum;
}
