package cjfw.wms.rt.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.18 
 * @description : 외부비축반품확정 마스터 조회 기능을 구현한 reqDto Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.18 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부비축반품확정(입고내역) 마스터 조회 요청")
public class RtReceiptConfirmExdcMasterReqDto extends CommonProcedureDto{
	
	/** 저장 리스트 */
	@Schema(description = "저장 리스트")
	List<RtReceiptConfirmExdcMasterResDto> saveMasterList;
	/* ===== 단일조건 ===== */

	/** 진행상태 */
	@Schema(description = "진행상태")
	private String status;

	/** 고객주문번호 */
	@Schema(description = "주문번호")
	private String docnoWd;

	/** 회수여부 */
	@Schema(description = "회수여부")
	private String returnType;

	/** 저장유무 */
	@Schema(description = "저장여부")
	private String channel;

	/** 이력번호유무 */
	@Schema(description = "이력번호유무")
	private String serialYn;

	/** BL 번호 */
	@Schema(description = "BL 번호")
	private String blNo;
	
	/** BL 번호 */
	@MultiSearch
    @Schema(description = "BL 번호")
    private List<String> blNoMulti;

	/** Serial 번호 */
	@Schema(description = "Serial 번호")
	private String serialNo;

	/** WD 고객코드 */
	@Schema(description = "WD 고객코드")
	private String wdCustKey;
	
    @Schema(description = "저장조건")
    private String storagetype;

	/* ===== 기간조건 ===== */

	/** 반품요청일자 From */
	@Schema(description = "반품요청일자From")
	private String slipdtFrom;

	/** 반품요청일자 To */
	@Schema(description = "반품요청일자To")
	private String slipdtTo;

	/* ===== 다중선택(콤마구분 문자열) ===== */

	/** 상품코드 */
	@Schema(description = "상품코드")
	private String sku;

	/** 창고 */
	@Schema(description = "창고")
	private String organize;

	/** 문서번호 다중 선택(콤마 구분) */
	@Schema(description = "문서번호(DOCNO) 다중 선택(콤마 구분)")
	private String docNo;
	
	/**물류센터*/
    @Schema(description = "물류센터")
	private String fixDcCode;
}
