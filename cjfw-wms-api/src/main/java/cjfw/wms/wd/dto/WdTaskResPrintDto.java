package cjfw.wms.wd.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.09.03 
 * @description : 피킹작업지시-피킹리스트 출력 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.03 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "피킹작업지시-피킹리스트 출력 결과")
public class WdTaskResPrintDto extends CommonDto{
	/** 레포트 헤더 리스트	 */
	List<WdTaskResPrintHeaderDto> reportHeaderList;
	/** 레포트 상세 리스트	 */
	List<WdTaskResPrintDetailDto> reportDetailList;
	/** 레포트 STD 리스트	 */
	List<WdTaskResPrintSTDDto> reportSTDlList;
	/** 레포트 PLT바코드 리스트	 */
	WdTaskResPrintBarcodeDto reportBarcodelList;
}
