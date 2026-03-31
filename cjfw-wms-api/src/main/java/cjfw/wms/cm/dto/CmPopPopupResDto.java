package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.06.13 
 * @description : POP 조회 팝업 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.13 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "POP 조회 응답 DTO")
public class CmPopPopupResDto {
	@Schema(description = "물류센터코드", nullable = false, example="")
    private String dcCode;

    @Schema(description = "물류센터명", nullable = false, example="")
    private String dcName;

    @Schema(description = "POP 번호", nullable = false, example="")
    private String popCode;

    @Schema(description = "권역명", nullable = false, example="")
    private String districtName;
}
