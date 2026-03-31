package cjfw.wms.ms.dto;

import cjfw.wms.common.annotation.MaskingId;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System Generated
 * @date : 2025.01.XX 
 * @description : 배송 권역 이력 조회 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.01.XX System Generated 생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Schema(description = "배송 권역 이력 조회 결과 DTO")
public class MsDeliveryDistrictHistoryResDto extends CommonDto {
		
	@Schema(description = "센터명")
	private String dcname;

	@Schema(description = "이력일시")
	private String hisdate;

	@Schema(description = "권역그룹 ID")
	private String dlvgroupId;
	
	@Schema(description = "권역그룹명")
	private String dlvgroupNm;
	
	@Schema(description = "권역 ID")
	private String dlvdistrictId;
	
	@Schema(description = "배송 권역명")
	private String dlvdistrictNm;
	
	@Schema(description = "대표POP번호")
	private String popno;

	@Schema(description = "대표POP명")
	private String popname;

	@Schema(description = "시도")
	private String ctpKorNm;

	@Schema(description = "시/군/구")
	private String sigKorNm;

	@Schema(description = "행정동명")
	private String hjdongNm;
	
	@Schema(description = "행정동코드")
	private String hjdongCd;
	
	@Schema(description = "적용시작일")
	private String fromDate;
	
	@Schema(description = "적용종료일")
	private String toDate;

	@Schema(description = "시작시간")
	private String fromHour;

	@Schema(description = "종료시간")
	private String toHour;

	@Schema(description = "삭제여부")
	private String delYn;

	@Schema(description = "등록일시")
	private String adddate;

    @MaskingId
	@Schema(description = "등록자")
	private String addwho;

	@Schema(description = "수정일시")
	private String editdate;

    @MaskingId
	@Schema(description = "수정자")
	private String editwho;
}

