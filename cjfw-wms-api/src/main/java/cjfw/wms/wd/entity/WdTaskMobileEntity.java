package cjfw.wms.wd.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @date : 2025.09.25 
 * @description : 피킹작업지시-모바일피킹지시 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                 MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.25 	공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "피킹작업지시-모바일피킹지시 Entity") 
public class WdTaskMobileEntity extends CommonDto {
	

	/** organize */
	private String organize;
	
    /** 작업시스템 */
    private String tasksystem;

    /** 피킹배치번호 */
    private String pickBatchNo;

    /** 피킹리스트번호 */
    private String pickListNo;

    /** 피킹번호 */
    private String pickNo;

    /** DC코드 */
    private String dccode;

    /** 화주키 */
    private String storerkey;
    
    /** 작업일시 */
    private String taskdt;
    
    /** mobileFlag */
	private String mobileFlag;
    
	/** 사용자 */
    @Schema(description = "사용자", nullable = false, example = "")
    private String gUserId;

}
