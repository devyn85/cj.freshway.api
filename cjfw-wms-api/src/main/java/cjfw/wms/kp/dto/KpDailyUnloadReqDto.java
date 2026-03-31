package cjfw.wms.kp.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2026.01.19
 * @description : 데일리 생산성 하역 지표 관리 response dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.01.19 JiHoPark  생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "데일리 생산성 하역 지표 관리 request dto")
public class KpDailyUnloadReqDto extends CommonProcedureDto {
	/** 물류센터 */
    @Schema(description = "물류센터")
    private String dccode;

    /** 일자년월 */
    @Schema(description = "일자년월")
    private String yyyymm;

    /** 일자from */
    @Schema(description = "일자from")
    private String deliverydtFrom;

    /** 일자to */
    @Schema(description = "일자to")
    private String deliverydtTo;

    /** 계약조회년도 */
    @Schema(description = "계약조회년도")
    private String contractDate;

    /** 예외조회년도 */
    @Schema(description = "예외조회년도")
    private String excptDate;

    /** 투입인원 save */
	@Schema(description = "투입인원 save")
	private List<KpDailyUnloadResDto> saveMasterList;

    /** 센터업무관리 insert */
	@Schema(description = "센터업무관리 insert")
	private List<KpDailyUnloadResDto> insertPopupMasterList;

	/**센터업무관리 update */
	@Schema(description = "센터업무관리 update")
	private List<KpDailyUnloadResDto> updatePopupMasterList;

	/**센터업무관리 delete */
	@Schema(description = "센터업무관리 delete")
	private List<KpDailyUnloadResDto> deletePopupMasterList;

	/** 분류피킹 제외 대상 insert */
	@Schema(description = "분류피킹 제외 대상 insert")
	private List<KpDailyUnloadResDto> insertPopupMasterList3;

	/**분류피킹 제외 대상 update */
	@Schema(description = "분류피킹 제외 대상 update")
	private List<KpDailyUnloadResDto> updatePopupMasterList3;

	/**분류피킹 제외 대상 delete */
	@Schema(description = "분류피킹 제외 대상 delete")
	private List<KpDailyUnloadResDto> deletePopupMasterList3;
}
