package cjfw.wms.om.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.09.26 
 * @description : CK주문결재내역 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.26 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class OmCkApprovalEntity extends CommonDto {

	/** 요청번호 */
	@Schema(description = "요청번호", example = "")
	private String requestno;
	
	/** 승인/반려 */
	@Schema(description = "승인/반려", example = "")
	private String apprStatus;
	
	/** 납품일 */
	@Schema(description = "납품일", example = "")
	private String dateFrom;
	
	/** 납품일 */
	@Schema(description = "납품일", example = "")
	private String dateTo;
	
	/** 상품코드 */
	@Schema(description = "상품코드", example = "")
	private String[] sku;
	
	/** serialkey */
	@Schema(description = "serialkey", example = "")
	private String serialkey;	

}