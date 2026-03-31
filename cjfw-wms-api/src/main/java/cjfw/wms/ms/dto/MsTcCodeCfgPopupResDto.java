package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "출발지TC센터설정 팝업 조회 응답 DTO")
public class MsTcCodeCfgPopupResDto {
    @Schema(description = "TC센터코드", example = "TC002")
    private String tcCode;
    
    @Schema(description = "TC센터명", example = "대구")
    private String tcName;
    
    @Schema(description = "코드")
    private String code;
    
    @Schema(description = "명")
    private String name;
    
    @Schema(description = "위도", example = "128.600661")
    private String latitude;
    
    @Schema(description = "경도", example = "35.871333")
    private String longitude;
    
    @Schema(description = "반경")
    private String radius;
    
    @Schema(description = "체류시간")
    private String stytime;
    
    @Schema(description = "주소", example = "경상북도 대구광역시 중구")
    private String address1;

    @Schema(description = "상세 주소", example = "경상북도 대구광역시 중구 동인동1가 404")
    private String address2;
    
    @Schema(description = "사용여부(Y/N)", example = "Y")
    private String useYn;
    
    @Schema(description = "등록 일시 (yyyy-MM-dd HH:mm:ss)")
    private String addDate;

    @Schema(description = "등록자")
    private String addWho;

    @Schema(description = "수정 일시 (yyyy-MM-dd HH:mm:ss)")
    private String editDate;

    @Schema(description = "수정자")
    private String editWho;
    
    // @Schema(description = "사용자 물류센터코드", example = "2600")
    // private String dcCode;
}
