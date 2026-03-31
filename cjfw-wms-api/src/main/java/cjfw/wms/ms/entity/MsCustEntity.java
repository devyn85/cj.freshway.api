package cjfw.wms.ms.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.06.09 
 * @description : 고객정보(New) Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.27 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MsCustEntity extends CommonDto {
			
	/** 데이터번호 */
	@Schema(description = "데이터번호", example = "")
	private String serialkey;
	
	/** 거래처유형 */
	@Schema(description = "거래처유형", example = "")
	private String custtype;

	/** 거래처 그룹 */
	@Schema(description = "거래처 그룹", example = "")
	private String custgroup;
	
	/** 거래처전략5 */
	@Schema(description = "거래처전략5", example = "")
	private String custstrategy5;

	/** 2차점 라벨출력 옵션 */
	@Schema(description = "2차점 라벨출력 옵션", example = "")
	private String labelstrategy;
		
	/** 최종변경시간 */
	@Schema(description = "최종변경시간", example = "")
	private String editdate;

	/** 최종변경자 */
	@Schema(description = "최종변경자", example = "")
	private String editwho;
	
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