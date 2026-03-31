package cjfw.wms.ms.dto;

import cjfw.wms.common.annotation.MaskingId;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.11.17 
 * @description : 센터 권역 이력 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.17 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "센터 권역 이력 응답 DTO")
public class MsCenterDistrictHistoryResDto  extends CommonDto {
	
	@Schema(description = "센터코드")
	private String dccode;
	
	@Schema(description = "로그 타임스탬프")
	private String logTimestamp;
	
	@Schema(description = "시도명")
	private String ctpKorNm;
	
	@Schema(description = "센터명")
	private String dcname;
	
	@Schema(description = "시군구명")
	private String sigKorNm;
	
	@Schema(description = "행정동명")	
	private String hjdongNm;
	
	@Schema(description = "행정동 코드")
	private String hjdongCd;
	
	@Schema(description = "주문그룹 코드")
	private String ordGrp;
	
	@Schema(description = "주문그룹명")
	private String ordgrpNm;
	
	@Schema(description = "시작일자")
	private String fromdate;
	
	@Schema(description = "종료일자")
	private String todate;
	
	@Schema(description = "삭제 Y/N")
	private String delYn;
	
	@Schema(description = "추가일자")
	private String addDate;
	
	@Schema(description = "수정일자")
	private String editDate;

	@MaskingId
	@Schema(description = "추가자")
	private String addWho;
	
	@MaskingId
	@Schema(description = "수정자")
	private String editWho;

}
