package cjfw.wms.ms.dto;

import cjfw.wms.common.annotation.MaskingName;
import cjfw.wms.common.annotation.MaskingTelno;
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
 * @date : 2025.08.20 
 * @description : 협력사 담당자정보 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.03.16   생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Schema(description = "협력사 입고검수결과 수신자 마스터정보 조회(목록)")
public class MsCustDeliveryInfoPDetailPersonResDto extends CommonDto{
	/** 시리얼번호 */
    @Schema(description = "시리얼번호")
    private String serialkey;
    
	/** 협력사코드 */
    @Schema(description = "협력사코드")
    private String custkey;
    
    /** 구분 */
    @Schema(description = "구분")
    private String persontype; 
    
    /** 구분명 */
    @Schema(description = "구분명")
    private String persontypeNm; 
    
    /** 이름 */
    @MaskingName
    @Schema(description = "이름")
    private String empname; 
    
    /** 전화번호 */
    @MaskingTelno
    @Schema(description = "전화번호")
    private String empphone; 
    


}
