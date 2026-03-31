package cjfw.wms.ms.dto;

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
 * @date : 2025.07.25 
 * @description : 고객배송조건 수신이력 팝업 조회 요청 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.25 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Schema(description = "고객배송조건 수신이력 조회 요청 DTO")
public class MsCustDlvInfoHisPopupReqDto extends CommonDto{
	@Schema(description = "물류센터", example = "FW00")
    private String storerKey;

    @Schema(description = "거래처코드", example = "12345")
    private String custCode;

    @Schema(description = "조회 시작일자 (YYYYMMDD)", example = "20250101")
    private String fromDate;

    @Schema(description = "조회 종료일자 (YYYYMMDD)", example = "20251231")
    private String toDate;
}
