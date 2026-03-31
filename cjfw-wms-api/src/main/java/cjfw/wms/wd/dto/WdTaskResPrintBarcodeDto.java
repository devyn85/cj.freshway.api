package cjfw.wms.wd.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.09.22 
 * @description : 피킹작업지시-PLT바코드 출력 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.22 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "피킹작업지시-PLT바코드 출력 결과")
public class WdTaskResPrintBarcodeDto extends CommonDto{
	  /**
	   * 출력수량
	   */
	  @Schema(description = "출력수량", example = "5")
	  private String printcnt;

	  /**
	   * 시작바코드번호
	   */
	  @Schema(description = "시작바코드번호", example = "1")
	  private String startBarcodeNo;
	  

}
