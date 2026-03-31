package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System Generated
 * @date : 2025.01.XX 
 * @description : 배송 권역 이력 조회 요청 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.01.XX System Generated 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "배송 권역 이력 조회 조건 DTO")
public class MsDeliveryDistrictHistoryReqDto extends CommonDto {

	@Schema(description = "센터 코드")
	private String dccode;

	@Schema(description = "센터 코드 리스트")
	private String[] gMultiDccodeList;

    @Schema(description = "권역그룹명", example = "권역")
    private String dlvgroupNm;

	@Schema(description = "배송 권역명", example = "권역")
	private String dlvdistrictNm;

    @Schema(description = "행정구역명", example = "안양")
    private String hjDistrictNm;

	@Schema(description = "적용 일자", example = "20251022")
	private String effectiveDate;
}

