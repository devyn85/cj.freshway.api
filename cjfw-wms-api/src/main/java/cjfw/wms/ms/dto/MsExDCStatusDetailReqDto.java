package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 *
 * @author : KimSunHo(sunhokim6229@cj.net)
 * @date : 2025.05.22
 * @description : 저장위치정보 요청 DTO
 * @issues :
 * -----------------------------------------------------------
 * DATE          AUTHOR                  MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.22    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "저장위치정보 요청")
public class MsExDCStatusDetailReqDto extends CommonDto {

	/** 플랜트 */
	@Schema(description = "플랜트", nullable = false, example = "")
	private String plant;

	/** 저장위치 */
	@Schema(description = "저장위치", nullable = false, example = "")
	private String storageloc;

	/** 조직코드 */
	@Schema(description = "조직코드", nullable = false, example = "")
	private String organize;

	/** 파일키 */
    @Schema(description = "파일키", nullable = false, example = "")
    private String serialkey;

    /** 적용 시작일 */
    @Schema(description = "적용 시작일자 (yyyy-MM-dd)", example = "2025-01-01")
    private String fromDt;

    /** 적용 종료일 */
    @Schema(description = "적용 종료일자 (yyyy-MM-dd)", example = "2025-01-31")
    private String thruDt;

    /** 저장조건 */
    @Schema(description = "저장조건 코드", example = "ST01")
    private String storagetype;

    /** 센터 코드 */
    @Schema(description = "센터 코드", example = "A111")
    private String dccode;

	/** 계약시작일 */
	@Schema(description = "계약시작일")
	private String startdate;

	/** 계약종료일 */
	@Schema(description = "계약종료일")
	private String enddate;

}
