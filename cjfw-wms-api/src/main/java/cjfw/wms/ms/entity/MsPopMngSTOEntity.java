package cjfw.wms.ms.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.07.18 
 * @description : 거래처별POP관리 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.18 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MsPopMngSTOEntity extends CommonDto {
	
	/** 광역센터코드 */
	@Schema(description = "광역센터코드", example = "")
	private String dccode;

	/** POP 번호 */
	@Schema(description = "POP 번호", example = "")
	private String popno;

	/** 고객코드 */
	@Schema(description = "고객코드", example = "")
	private String storerkey;
	
	/** 센터코드 */
	@Schema(description = "실 주문센터", example = "")
	private String toDccode;	
	
	/** TO 배송일자 */
	@Schema(description = "TO 배송일자", example = "")
	private String toDeliverydt;

}