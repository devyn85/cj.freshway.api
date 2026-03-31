package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.05.27 
 * @description : 특별관리고객현황 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.27 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "특별관리고객현황 조회 요청 DTO")
public class MsCustRedzoneReqDto extends CommonDto{
	private String custCode;
	private String markWord;
}
