package cjfw.wms.wd.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net) 
 * @date : 2025.05.26  
 * @description : 상품 정보 팝업조회 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.19 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "미출 예정 확인 (상품별 합계) Request DTO")
public class WdDistributePlanSkuSumReqDto extends CommonDto {
    
	/** 전역 멀티 센터코드 */
    @Schema(description = "센터코드", example = "")
    private String gMultiDccode;

    /** 전역 멀티 고객사코드 */
    @Schema(description = "고객사코드", example = "")
    private String gMultiStorerkey;

    /** 전역 멀티 조직코드 */
    @Schema(description = "조직코드 (다중 선택, 콤마구분)", example = "ORG1,ORG2")
    private String gMultiOrganize;

    /** 전역 멀티 구역코드 */
    @Schema(description = "구역코드", example = "")
    private String gMultiArea;

    /** 납기일자 */
    @Schema(description = "납기일자", example = "20240520")
    private String deliverydate;

    /** 작업 문서일자 */
    @Schema(description = "작업 문서일자(DOCDT)", example = "20240519")
    private String docdt;

    /** 전역 멀티 배송그룹 */
    @Schema(description = "배송그룹", example = "")
    private String gMultiCourier;

    /** 미출고전표일자 */
    @Schema(description = "문서일자 (예: 20240519)", example = "20240519")
    private String slipdt;
    
    /** 상품코드 */
    @Schema(description = "상품코드 (다중 선택, 콤마구분)", example = "")
    private String skuCode;

    /** 미출예정 포함 여부 */
    @Schema(description = "미출예정 포함 여부 (0: 미포함, 1: 포함)", example = "1")
    private Integer chkyn;

}