package cjfw.wms.kp.dto;


import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.14 
 * @description :외부창고재고모니터링 localDetail res DTO 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.14 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부창고재고모니터링 localDetail req DTO")
public class KpEXstorageMonitoringLocalDetailListReqDto extends CommonDto{


    /** 구매요청번호 */
    @Schema(description = "구매요청번호(MAPKEY_NO)")
    private String mapkeyNo;
}
