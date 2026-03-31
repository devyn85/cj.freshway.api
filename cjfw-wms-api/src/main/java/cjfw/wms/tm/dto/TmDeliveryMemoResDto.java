package cjfw.wms.tm.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.10.27
 * @description : 거래처별 메모사항 조회 response dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.27 JiHoPark  생성 </pre>
 */
@Data
@Schema(description = "거래처별 메모사항 조회 response dto")
public class TmDeliveryMemoResDto extends CommonProcedureDto {
	/** 고객사코드 */
	@Schema(description = "고객사코드")
	private String storerkey;

	/** 배송일자 */
	@Schema(description = "배송일자")
	private String deliverydt;

	/** 권역코드 */
	@Schema(description = "권역코드")
	private String districtcode;

	/** POP번호 */
	@Schema(description = "POP번호")
	private String deliverygroup;

	/** 배송번호 */
	@Schema(description = "배송번호")
	private String deliveryno;

	/** 차량번호 */
	@Schema(description = "차량번호")
	private String carno;

	/** 센터코드 */
	@Schema(description = "센터코드")
	private String dccode;

	/** 물류센터 */
	@Schema(description = "물류센터")
	private String dccodename;

	/** 물류센터 */
	@Schema(description = "물류센터명")
	private String dccodenm;

	/** 관리처코드 */
	@Schema(description = "관리처코드")
	private String toCustkey;

	/** 관리처명 */
	@Schema(description = "관리처명")
	private String toCustname;

	/** 고정메모 */
	@Schema(description = "고정메모")
	private String memo;

	/** 일별메모 */
	@Schema(description = "일별메모")
	private String tmmemo;

	/** 등록자 */
	@Schema(description = "등록자")
	private String editwho;

	/** 등록일시 */
	@Schema(description = "등록일시")
	private String editdate;

	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "0")
	private String chk = "0";
}
