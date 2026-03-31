package cjfw.batch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 김환수 (hwansoo.kim@cj.net)
 * @date : 2025.11.11
 * @description : MS_CUST(거래처마스터) 주소정제 DTO (배치용)  
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.11 김환수 (hwansoo.kim@cj.net) 생성 </pre>
 */

@Data
@Schema(description = "주소정제 대상 고객정보") 
public class MsCustReqDto {

	@Schema(description = "BATCH JOB NAME")
	private String jobName;
	@Schema(description = "JOB INSTANCE ID")
	private long jobInstanceId;
	@Schema(description = "FETCH ROW COUNT")
	private String fetchCnt;
	@Schema(description = "API구분")
	private String apiJob;

}
