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
 * @author : YangChangHwan (iamai@cj.net)
 * @date : 2025.06.02
 * @description : 이력재고처리현황 조회 Request DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.02 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "이력재고처리현황 조회 Request DTO")
public class StTransactionSnReqDto extends CommonDto {
	
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String fixdccode;	

	/** 이력 여부 */
    @Schema(description = "이력 여부")
    private String serialyn;

    /** 상품코드 */
    @Schema(description = "상품코드 (다중 선택 콤마구분)")
    private String sku;
    
    /** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;         
    
	/** 시작일(From) */
	@Schema(description = "수정일자(From)")
	private String dt1;

	/** 수정일자(To) */
	@Schema(description = "종료일(To)")
	private String dt2;    

    /** 트랜잭션 */
    @Schema(description = "트랜잭션")
    private String trantype;

    /** 로케이션 */
    @Schema(description = "로케이션")
    private String loc;

    /** 이력번호 */
    @Schema(description = "이력번호")
    private String serialno;

    /** BL번호 */
    @Schema(description = "BL번호")
    private String blno;

    /** 계약업체 */
    @Schema(description = "계약회사")
    private String contractcompany;
    
    /** 거래처(다중OR검색) */
	@MultiSearch
    @Schema(description = "거래처-다중OR검색")
    private List<List<String>> contractcompanyMulti;      

}