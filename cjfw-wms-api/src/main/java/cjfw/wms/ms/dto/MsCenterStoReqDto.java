package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.08.04 
 * @description : 센터이체마스터 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class MsCenterStoReqDto extends CommonDto {
	
	/** 데이터번호 */
	@Schema(description = "데이터번호", example = "")
	private String serialkey;
	
	/** 수급센터코드 */
    @Schema(description = "수급센터코드")
    private String toDccode;

    /** 센터마감유형 */
    @Schema(description = "센터마감유형")
    private String dcClosetype;

    /** 공급우선순위 */
    @Schema(description = "공급우선순위")
    private String fromPriority;

    /** 공급센터코드 */
    @Schema(description = "공급센터코드")
    private String fromDccode;

    /** 수급우선순위 */
    @Schema(description = "수급우선순위")
    private String toPriority;

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;

    /** 피킹유형 */
    @Schema(description = "피킹유형")
    private String pickedType;

    /** 그룹유형 */
    @Schema(description = "그룹유형")
    private String groupType;

    /** 적용여부 */
    @Schema(description = "적용여부")
    private String applyYn;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;
    
	/** 수급센터우선순위적용여부 */
	@Schema(description = "수급센터우선순위적용여부", example = "")
	private String toDccodePriorityApplyYn;	

}
