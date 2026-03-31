package cjfw.wms.dp.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.28
 * @description : 입고하차관리 Request DTO Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.28 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "입고하차관리 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpUnloadReqDto extends CommonProcedureDto {

	/** 광역출고일자(From) */
	@Schema(description = "광역출고일자(From)")
	private String fromSlipdt;

	/** 광역출고일자(To) */
	@Schema(description = "광역출고일자(To)")
	private String toSlipdt;

	/** 출고문서번호(다중) */
	@Schema(description = "출고문서번호(다중)")
	private String docno;

	/** 상품코드(다중) */
	@Schema(description = "상품코드(다중)")
	private String sku;

	/** 상품코드(다중) - 상세조회용 */
	@Schema(description = "상품코드(다중) - 상세조회용")
	private String multiSku;

	/** 출고문서일자(From) */
	@Schema(description = "출고문서일자(From)")
	private String fromDocdt;

	/** 출고문서일자(To) */
	@Schema(description = "출고문서일자(To)")
	private String toDocdt;

	/** 문서일자 */
	@Schema(description = "문서일자")
	private String docdt;

	/** 배송일자(From) */
	@Schema(description = "배송일자(From)")
	private String fromDeliverydt;

	/** 배송일자(To) */
	@Schema(description = "배송일자(To)")
	private String toDeliverydt;

	/** 센터코드 */
	@Schema(description = "센터코드")
	private String dccode;

	/** 화주코드 */
	@Schema(description = "화주코드")
	private String storerkey;

	/** 출발거래처코드 */
	@Schema(description = "출발거래처코드")
	private String fromCustkey;

	/** 도착거래처코드 */
	@Schema(description = "도착거래처코드")
	private String toCustkey;

	/** 배송그룹 */
	@Schema(description = "배송그룹")
	private String gMultiCourier;

	/** 입고예정일 */
	@Schema(description = "입고예정일")
	private String slipdt;

	/** 입고예정일(From) */
	@Schema(description = "입고예정일(From)")
	private String slipdtFrom;

	/** 입고예정일(To) */
	@Schema(description = "입고예정일(To)")
	private String slipdtTo;

	/** 문서일자(From) */
	@Schema(description = "문서일자(From)")
	private String docdtFrom;

	/** 문서일자(To) */
	@Schema(description = "문서일자(To)")
	private String docdtTo;

	/** 보관유형 */
	@Schema(description = "보관유형")
	private String storagetype;

	/** 조인여부 */
	@Schema(description = "조인여부")
	private String joinfg;

	/** 하차시간(From) */
	@Schema(description = "하차시간(From)")
	private String unloadtimeFrom;

	/** 하차시간(To) */
	@Schema(description = "하차시간(To)")
	private String unloadtimeTo;

	/** 온도적정성 */
	@Schema(description = "온도적정성")
	private String tempoptiyn;

	/** 채널 */
	@Schema(description = "채널")
	private String channel;

	/** 협력사 다중선택 */
	@Schema(description = "협력사 다중선택")
	private String multiFromCustkey;

	/** 엑셀 상품 여부*/
	@Schema(description = "엑셀 상품 여부")
	private String skuYn;

	/** 센터이동제외*/
	@Schema(description = "센터이동제외")
	private String moveYn;

    /** 센터별 고객코드 */
   	@MultiSearch
   	@Schema(description = "센터별 고객코드", example = "")
   	private List<List<String>> fromCustkeyMulti;

    /** 상품코드 */
	@MultiSearch
	@Schema(description = "상품코드", example = "")
	private List<List<String>> skuMulti;

	/** 저장리스트*/
	@Schema(description = "저장리스트")
	private List<DpUnloadResCarLogDto> saveList;
}
