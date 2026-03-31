package cjfw.wms.st.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.09.28 
 * @description : 출고재고보충(전센터) 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.28 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "출고재고보충(전센터) 요청") 
public class StLocMoveRPAllReqDto extends CommonProcedureDto {
	
	/** 저장 리스트 */
	List<StLocMoveRPAllResDto> saveList;
	
	/** 저장 리스트 */
	List<StLocMoveRPAllResDto> saveDataList;

	/** 저장구분자 */
	@Schema(description = "저장구분자", nullable = false, example = "")
	private String type;
	

	/**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "C100")
    private String fixdccode;
    
	
	/** 출고일자 */
	@Schema(description = "출고일자", nullable = false, example = "")
	private String docdt;
	/** 출고일자 */
	@Schema(description = "출고일자", nullable = false, example = "")
	private String slipdt;
	/** 상품코드 */
	@Schema(description = "상품코드", nullable = false, example = "")
	private String sku;
	
	/** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti; 
	
	/** fromZone */
	@Schema(description = "fromZone", nullable = false, example = "")
	private String fromZone;
	
	/** toZone */
	@Schema(description = "toZone", nullable = false, example = "")
	private String toZone;
	
	/** status */
	@Schema(description = "status", nullable = false, example = "")
	private String status;
	
	/** 발행여부 */
	@Schema(description = "발행여부", nullable = false, example = "")
	private String printyn;
	
	/** 보충번호 */
	@Schema(description = "보충번호", nullable = false, example = "")
	private String supplno;
	
	/** 보충번호(다중검색) */
	@MultiSearch
    @Schema(description = "보충번호-다중OR검색")
    private List<String> supplnoMulti; 
	
	/** reasoncode2 */
	@Schema(description = "reasoncode2", nullable = false, example = "")
	private String reasoncode2;
	
	/** processtype */
	@Schema(description = "processtype", nullable = false, example = "")
	private String processtype;
	
	/** 출력메모 */
	@Schema(description = "출력메모", nullable = false, example = "")
	private String printmemo;
	
	/** 처리여부 */
	@Schema(description = "처리여부", nullable = false, example = "")
	private String procyn;
	
	/** 지시여부 */
	@Schema(description = "지시여부", nullable = false, example = "")
	private String ifflagyn;
	
	/** 저장조건 */
	@Schema(description = "저장조건", nullable = false, example = "")
	private String storagetype;
	
	/** 원거리유형 */
	@Schema(description = "원거리유형", nullable = false, example = "")
	private String distancetype;
	
	/** 원거리유형(다중검색) */
	@MultiSearch
    @Schema(description = "원거리유형-다중OR검색")
    private List<String> distancetypeMulti; 
	
}
