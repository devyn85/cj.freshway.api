package cjfw.wms.tm.entity;

import cjfw.wms.tm.dto.TmDispatchOptionsReqDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.09.26 
 * @description : 배차 옵션 설정 엔티티 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.26 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "배차 옵션 설정 엔티티")
public class TmPlanOptionEntity {
	
	    /** 데이터번호 */
	    @Schema(description = "데이터번호", nullable = false, example = "")
	    private Long serialkey;

		/** 센터코드 */
	    @Schema(description = "센터코드", nullable = false, example = "")
	    private String dccode;
	    
	    /** 배차 계획 타입 */
	    @Schema(description = "배차 계획 타입", nullable = false, example = "")
	    private String planOptionType;

	    /** 배차 계획 옵션 코드 */
	    @Schema(description = "배차 계획 옵션 코드", nullable = false, example = "")
	    private String planOptionCd;

	    /** 사용여부 */
	    @Schema(description = "사용여부", nullable = false, example = "")
	    private String useYn;

	    /** 설정값 */
	    @Schema(description = "설정값", nullable = false, example = "")
	    private String setValue;

        /** 최대 지정 값 */
        @Schema(description = "최대 지정 값 ", nullable = false, example = "")
        private String limitHigh;

        /** 최소 지정 값 */
        @Schema(description = "최소 지정 값 ", nullable = false, example = "")
        private String limitLow;


	    /** 추가일 */
	    @Schema(description = "추가일", nullable = false, example = "")
	    private String addDate;

	    /** 수정일 */
	    @Schema(description = "수정일", nullable = false, example = "")
	    private String editDate;
	    
	    /** 등록자 */
	    @Schema(description = "등록자", nullable = false, example = "")
	    private String addWho;
	    
	    /** 수정자 */
	    @Schema(description = "수정자", nullable = false, example = "")
	    private String editWho;

}
