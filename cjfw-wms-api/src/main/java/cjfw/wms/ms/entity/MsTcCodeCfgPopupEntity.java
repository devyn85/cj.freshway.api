package cjfw.wms.ms.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.08.18 
 * @description : 출발지TC센터설정 팝업 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.18 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "출발지TC센터설정 팝업 Entity")
@EqualsAndHashCode(callSuper = false)
public class MsTcCodeCfgPopupEntity extends CommonDto{
    @Schema(description = "TC센터코드", example = "TC002")
    private String tcCode;
    
    @Schema(description = "TC센터명(부분일치 검색)", example = "대구")
    private String tcName;
    
    @Schema(description = "위도", example = "128.600661")
    private String latitude;
    
    @Schema(description = "경도", example = "35.871333")
    private String longitude;
    
    @Schema(description = "기본 주소", example = "경상북도 대구광역시 중구 동인동")
    private String address1;

    @Schema(description = "상세 주소", example = "경상북도 대구광역시 중구 동인동1가 404")
    private String address2;
    
    @Schema(description = "사용여부(Y/N)", example = "Y")
    private String useYn;
    
	@Schema(description = "작성일")
    private String addDate;

	@Schema(description = "수정일")
    private String editDate;

	@Schema(description = "작성자")
    private String addWho;

	@Schema(description = "수정자")
    private String editWho;
    
    @Schema(description = "사용자 물류센터코드", example = "2600")
    private String dcCode;
    
    @Schema(description = "반경")
    private String radius;
    
    @Schema(description = "체류시간")
    private String stytime;
}
