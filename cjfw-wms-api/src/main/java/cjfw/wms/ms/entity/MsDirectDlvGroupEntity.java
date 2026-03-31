package cjfw.wms.ms.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.06.23 
 * @description : 발주직송그룹관리 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.23 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MsDirectDlvGroupEntity extends CommonDto {
			
	/** 직송그룹 */
	@Schema(description = "직송그룹", example = "")
	private String directdlvgroup;

	/** 센터코드 */
	@Schema(description = "센터코드", example = "")
	private String dccode;
	
	/** 고객사코드 */
	@Schema(description = "고객사코드", example = "")
	private String storerkey;

	/** 조직코드 */
	@Schema(description = "조직코드", example = "")
	private String organize;
	
	/** 협력사유형 */
	@Schema(description = "협력사유형", example = "")
	private String custtype;

	/** 협력사코드 */
	@Schema(description = "협력사코드", example = "")
	private String custkey;

	/** 총발주량 */
	@Schema(description = "총발주량", example = "")
	private String orderqty;

	/** 단위 */
	@Schema(description = "단위", example = "")
	private String uom;

	/** 메모사항 */
	@Schema(description = "메모사항", example = "")
	private String memo;
	
	 /** 성공 여부 */
    @Schema(description = "성공 여부", example = "")
    private Integer success;

    /** 에러 코드 */
    @Schema(description = "에러 코드", example = "")
    private Integer err;

    /** 에러 메시지 */
    @Schema(description = "에러 메시지", example = "")
    private String errmsg;
	
}