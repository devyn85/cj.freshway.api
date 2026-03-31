package cjfw.wms.st.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : sss (kduimux@cj.net)
 * @date : 2025.06.02
 * @description : 이력재고처리현황 조회 Request DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.02 sss (kduimux@cj.net) 생성 </pre>
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "이력재고처리현황 조회 Request DTO")
public class StTransactionReqDto extends CommonDto {
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String fixdccode;	

    /** 거래유형 */
    @Schema(description = "거래유형")
    private String trantype;

    /** 문서번호 */
    @Schema(description = "문서번호")
    private String docno;  
    
    /** 거래일자(From) */
    @Schema(description = "거래일자(From)")
    private String trandateFrom;

    /** 거래일자(To) */
    @Schema(description = "거래일자(To)")
    private String trandateTo;

    /** 품목코드 */
    @Schema(description = "품목코드")
    private String sku;
    
    /** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;    

    /** 시리얼여부 */
    @Schema(description = "시리얼여부")
    private String serialyn;

    /** 로케이션 */
    @Schema(description = "로케이션")
    private String loc;   
    
    /** 문서일자 */
    @Schema(description = "문서일자")
    private String docdt;   
    
    /** 시작일자 */
    @Schema(description = "시작일자", example = "20250501")
    private String dt1;

    /** 종료일자 */
    @Schema(description = "종료일자", example = "20250531")
    private String dt2; 
    
    /** 세트여부 */
    @Schema(description = "세트여부")
    private String setYn;     

}