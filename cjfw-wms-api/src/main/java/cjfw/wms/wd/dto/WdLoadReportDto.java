package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.11.13
 * @description : 출차지시처리 request dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.13 JiHoPark  생성 </pre>
 */
@Data
@Schema(description = "출차지시처리 report dto")
public class WdLoadReportDto extends CommonProcedureDto {
	/** 센터코드 */
	@Schema(description = "센터코드")
	private String dccode;

	/** 차량번호 */
	@Schema(description = "차량번호")
	private String carno;

	/** 배송일자 */
	@Schema(description = "배송일자")
	private String deliverydt;

	/** key */
	@Schema(description = "key")
	private String key;

	/** 구분 */
	@Schema(description = "구분")
	private String gubun;




	/** 센터명 */
	@Schema(description = "센터명")
	private String dcname;

	/** 차량 */
	@Schema(description = "차량")
	private String carname;

	/** 거래처명 */
	@Schema(description = "거래처명")
	private String custname;

	/** channel */
	@Schema(description = "channel")
	private String channel;

	/** channelnm */
	@Schema(description = "channelnm")
	private String channelnm;

	/** 메모 */
	@Schema(description = "메모")
	private String memo;

	/** tmmemo */
	@Schema(description = "tmmemo")
	private String tmmemo;

	/** 배송그룹 */
	@Schema(description = "배송그룹")
	private String deliverygroup;





	/** 거래처명 */
	@Schema(description = "거래처명")
	private String vendor;

	/** 제품 */
	@Schema(description = "제품")
	private String sku;

	/** 제품명 */
	@Schema(description = "제품명")
	private String skuname;

	/** 수량 */
	@Schema(description = "수량")
	private BigDecimal qty;

	/** 고객원주문단위 */
	@Schema(description = "고객원주문단위")
	private String storeruom;

	/** 저장타입 */
	@Schema(description = "저장타입")
	private String storagetype;

	/** convKg */
	@Schema(description = "convKg")
	private String convKg;

	/** 운전자명 */
	@Schema(description = "운전자명")
	private String drivername;

	/** TO 거래처코드 */
	@Schema(description = "TO 거래처코드")
	private String toCustkey;


}
