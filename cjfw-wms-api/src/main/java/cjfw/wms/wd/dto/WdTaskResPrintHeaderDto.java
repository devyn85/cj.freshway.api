package cjfw.wms.wd.dto;

import java.math.BigDecimal;

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
public class WdTaskResPrintHeaderDto extends CommonDto{
		/**
	    * 디씨코드
	    */
	    @Schema(description = "디씨코드", example = "DC01")
	    private String dccode;

	    /**
	    * storerkey
	    */
	    @Schema(description = "storerkey", example = "CUST1")
	    private String storerkey;

	    /**
	    * 슬립날짜
	    */
	    @Schema(description = "슬립날짜", example = "20240101")
	    private String slipdt;

	    /**
	    * 작업시스템
	    */
	    @Schema(description = "작업시스템", example = "WMS")
	    private String tasksystem;

	    /**
	    * 피킹배치번호
	    */
	    @Schema(description = "피킹배치번호", example = "PB001")
	    private String pickBatchNo;

	    /**
	    * 피킹번호
	    */
	    @Schema(description = "피킹번호", example = "P001")
	    private String pickNo;

	    /**
	    * 구역
	    */
	    @Schema(description = "구역", example = "A")
	    private String zone;

	    /**
	    * 플랜트 설명
	    */
	    @Schema(description = "플랜트 설명", example = "Plant A")
	    private String plantdesc;

	    /**
	    * 순번
	    */
	    @Schema(description = "순번", example = "1")
	    private String rnum;

	    /**
	    * 메모
	    */
	    @Schema(description = "메모", example = "중요사항")
	    private String memo;

	    /**
	    * 프린트 횟수
	    */
	    @Schema(description = "프린트 횟수", example = "3")
	    private BigDecimal printCnt;
}
