package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net)
 * @date : 2025.06.23
 * @description : 일배PO/SO연결모니터링 List DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.23 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@Schema(description = "일배PO/SO연결모니터링 List DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WdPoSoMonitoringResDto {

	 /** 물류센터코드 */
    @Schema(description = "물류센터코드")
    private String dccode;

    /** 센터명 */
    @Schema(description = "센터명")
    private String dcname;

    /** 마감코드 */
    @Schema(description = "마감코드")
    private String magamcode;

    /** 마감시간 */
    @Schema(description = "마감시간")
    private String closetime;

    /** 마감명 */
    @Schema(description = "마감명")
    private String magamname;

    /** SO건수 */
    @Schema(description = "SO건수")
    private BigDecimal socnt;

    /** 매핑건수 */
    @Schema(description = "매핑건수")
    private BigDecimal mapcnt;

    /** 미매핑건수 */
    @Schema(description = "미매핑건수")
    private BigDecimal diffcnt;

    /** 매핑율(%) */
    @Schema(description = "매핑율(%)")
    private BigDecimal maprate;

    /** 전표일자 */
    @Schema(description = "전표일자")
    private String slipdt;
}
