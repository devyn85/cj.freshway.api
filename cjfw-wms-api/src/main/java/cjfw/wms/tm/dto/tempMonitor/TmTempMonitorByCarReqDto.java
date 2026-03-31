package cjfw.wms.tm.dto.tempMonitor;

import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System Generated
 * @date : 2025.01.27 
 * @description : 차량별 온도 모니터링 조회 요청 DTO
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.01.27 System Generated 생성
 */
@Data
@Builder
@NoArgsConstructor            
@AllArgsConstructor
@Schema(description = "차량별 온도 모니터링 조회 요청 DTO")
public class TmTempMonitorByCarReqDto extends CommonDto {
	
    @Schema(description = "센터코드", example = "2600")
    private String dccode;
    
    @Schema(description = "차량번호 리스트")
    private List<String> carnoList;

    @Schema(description = "배송일자", example = "20241202")
    private String deliverydt;

    @Schema(description = "계약유형", example = "DELIVERY")
    private String contracttype;

    @Schema(description = "회차", example = "1")
    private String priority = "1";
}
