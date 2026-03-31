package cjfw.wms.dv.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net)
 * @date : 2025.06.09
 * @description : 자동창고처리현황 Request Dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.09 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@Schema(description = "자동창고처리현황 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DvDataviewSingleSpReqDto extends CommonDto {

	/** 조회 시작일자 */
	@Schema(description = "전송일자")
	private String ifDate;

    /** 문서번호(처리소스키) */
    @Schema(description = "문서번호(처리소스키)")
    private String docno;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

}
