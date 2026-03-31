package cjfw.wms.common.extend;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.08.04 
 * @description : 오라클 트리거 호출 시 사용하는 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.04 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CommonTriggerDto  extends CommonDto {
	/** 성공여부(사용하지 않음) */
	@Schema(description = "성공여부(사용하지 않음)", example = "")
	private int success;
	
	/** 결과에러코드(0:성공) */
	@Schema(description = "결과에러코드(0:성공)", example = "")
	private int errCode;
	
	/** 에러메세지 */
	@Schema(description = "에러메세지", example = "")
	private String errMsg;
}
