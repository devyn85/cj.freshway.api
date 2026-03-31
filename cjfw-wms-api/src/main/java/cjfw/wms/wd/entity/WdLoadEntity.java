package cjfw.wms.wd.entity;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.11.13
 * @description : 출차지시처리 entity
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.13 JiHoPark  생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "출차지시처리 Entity")
public class WdLoadEntity extends CommonProcedureDto {
	/** 처리자 */
	@Schema(description = "처리자")
	private String processcreator;

	/** SPID */
	@Schema(description = "SPID")
	private String spid;

	/** 처리플래그 */
	@Schema(description = "처리플래그")
	private String processflag;

	/** 배송일자 */
	@Schema(description = "배송일자")
	private String deliverydt;

	/** POP번호 */
	@Schema(description = "POP번호")
	private String deliverygroup;

	/** 차량번호 */
	@Schema(description = "차량번호")
	private String carno;

	/** 회차 */
	@Schema(description = "회차")
	private String priority;

	/** 출고일자 */
	@Schema(description = "출고일자")
	private String slipdt;

	/** 전표번호 */
	@Schema(description = "전표번호")
	private String slipno;

	/** 전표유형 */
	@Schema(description = "전표유형")
	private String sliptype;

	/** 고객사코드 */
	@Schema(description = "고객사코드")
	private String storerkey;

	/** 거래처유형 */
	@Schema(description = "거래처유형")
	private String custtype;

	/** 거래처코드 */
	@Schema(description = "거래처코드")
	private String custkey;

	/** 검수진행상태 */
	@Schema(description = "검수진행상태")
	private String status;

	/** 삭제여부 */
	@Schema(description = "DEL_YN")
	private String delYn;

	/** 등록일자 */
    @Schema(description = "등록일자")
    private String adddate;

    /** 수정일자 */
    @Schema(description = "수정일자")
    private String editdate;

    /** 등록자 */
    @Schema(description = "등록자")
    private String addwho;

    /** 수정자 */
    @Schema(description = "수정자")
    private String editwho;

}
