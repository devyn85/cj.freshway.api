package cjfw.wms.ms.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.06.24 
 * @description : 수급마스터관리(New) 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class MsPurchaseDCNewReqDto extends CommonDto {
	/** 산정일 (년월일) */
    @Schema(description = "산정일 (년월일)", example = "")
    private String yyyymmdd;

    /** 상품코드 */
    @Schema(description = "상품코드", example = "")
    private String sku;
    
    /** 상품코드 */
	@MultiSearch
	@Schema(description = "상품코드", example = "")
	private List<List<String>> skuMulti;

    /** 센터코드 */
    @Schema(description = "센터코드", example = "")
    private String dccode;
    
    /** 센터코드 */
   	@MultiSearch
   	@Schema(description = "센터코드", example = "")
   	private List<String> dccodeMulti;

    /** 수급담당 */
    @Schema(description = "수급담당", example = "")
    private String buyerkey;

    /** 센터별 고객코드 */
    @Schema(description = "센터별 고객코드", example = "")
    private String fromCustkey;
    
    /** 센터별 고객코드 */
   	@MultiSearch
   	@Schema(description = "센터별 고객코드", example = "")
   	private List<List<String>> fromCustkeyMulti;

    /** 저장조건 */
    @Schema(description = "저장조건", example = "")
    private String storagetype;

    /** 체인전용구분 */
    @Schema(description = "체인전용구분", example = "")
    private String reference15;

    /** 본점코드 */
    @Schema(description = "본점코드", example = "")
    private String parentCustkey;
    
    /** 본점코드 */
   	@MultiSearch
   	@Schema(description = "본점코드", example = "")
   	private List<List<String>> parentCustkeyMulti;

    /** 수발주유무 */
    @Schema(description = "수발주유무", example = "")
    private String purchaseYn; 

    /** 수발주OUTYN */
    @Schema(description = "수발주OUTYN", example = "")
    private String purchaseOutYn; 

    /** 재고유무 */
    @Schema(description = "재고유무", example = "")
    private String stockYn; 

    /** 이원화유무 */
    @Schema(description = "이원화유무", example = "")
    private String dupyn; 

    /** 산정일 (년월) */
    @Schema(description = "산정일 (년월)", example = "")
    private String yyyymm;

    /** 날짜  */
    @Schema(description = "날짜 ", example = "")
    private String lastday; 

    /** 날짜 (일자 목록) */
    @Schema(description = "날짜 (일자 목록)", example = "")
    private List<String> days; 

    /** 체크여부 */
    @Schema(description = "체크여부", example = "")
    private String chkyn; 

    /** MOQ (업체 단위) */
    @Schema(description = "MOQ (업체 단위)", example = "")
    private String moqVender; 
    
    /** 전월 시작 */
    @Schema(description = "전월 시작", example = "")
    private String prevMMStart;

    /** 전월 종료 */
    @Schema(description = "전월 종료", example = "")
    private String prevMMEnd;
    
}
