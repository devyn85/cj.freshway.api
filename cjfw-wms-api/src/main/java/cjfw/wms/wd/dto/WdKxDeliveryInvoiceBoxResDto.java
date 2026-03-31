package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.annotation.MaskingAddress;
import cjfw.wms.common.annotation.MaskingName;
import cjfw.wms.common.annotation.MaskingTelno;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.12.26
 * @description : 택배송장발행(온라인) response dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.26 sss  생성 </pre>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "택배송장발행(온라인) response dto")
public class WdKxDeliveryInvoiceBoxResDto extends CommonProcedureDto{
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "0")
	private String chk = "0";
	
	/******************** 택배기준 *************************/
	/**물류센터*/
	@Schema(description = "박스번호")
	private String boxno;

	/**물류센터*/
	@Schema(description = "박스명")
	private String boxnm;

	/**물류센터*/
	@Schema(description = "박스내경길이")
	private BigDecimal innerLength;

	/**물류센터*/
	@Schema(description = "박스내경폭")
	private BigDecimal innerWidth;

	/**물류센터*/
	@Schema(description = "박스내경높이")
	private BigDecimal innerHeight;

	/**물류센터*/
	@Schema(description = "박스외경길이")
	private BigDecimal outerLength;

	/**물류센터*/
	@Schema(description = "박스외경폭")
	private BigDecimal outerWidth;

	/**물류센터*/
	@Schema(description = "박스외경높이")
	private BigDecimal outerHeight;

	/**단가*/
	@Schema(description = "단가")
	private BigDecimal price;
	
	/**체적*/
	@Schema(description = "체적")
	private BigDecimal cube;
	
	/**체적*/
	@Schema(description = "체적")
	private int capacity;

	/**사용여부*/
	@Schema(description = "사용여부")
	private String useYn;
	
	/** 저장조건 */
	@Schema(description = "저장조건", example = "")
	private String storagetype;
	
	/**충부피*/
	@Schema(description = "충부피")
	private BigDecimal totvolume;
    
}
