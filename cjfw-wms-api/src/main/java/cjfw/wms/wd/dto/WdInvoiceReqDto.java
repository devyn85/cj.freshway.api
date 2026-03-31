package cjfw.wms.wd.dto;

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
 * @author : KimDongHyeon (tirran123@cj.net) 
 * @date : 2025.05.09 
 * @description : 납품서출력 목록 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09  KimDongHyeon (tirran123@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "납품서출력 목록 요청") 
public class WdInvoiceReqDto extends CommonProcedureDto {
	
    /** 메인 그리드 저장 리스트 */
    List<WdInvoiceResDto> saveList;
    
    /** 상세 그리드 저장 리스트 */
    List<WdInvoiceDetailResDto> saveDetailList;    
    
    /** 납품일자(문서일자) */
    @Schema(description = "납품일자(문서일자)", example = "20250509")
    private String docdt;    
    
    /** 인보이스 출력 유형 */
	@Schema(description = "인보이스출력유형", nullable = false, example = "WD,RT,RTP")
	private String invoiceprinttype;
	
	/** 배송 그룹 */
	@Schema(description = "배송 그룹", example = "A01")
	private String deliverygroup;

	/** 차량 검색 키워드 */
	@Schema(description = "차량 검색 키워드", example = "12가3456")
	private String searchcar;

    /** 차량번호 */
    @Schema(description = "차량번호", example = "12가3456")
    private String carno;

	/** carno(다중검색) */
	@MultiSearch
    @Schema(description = "carno-다중OR검색")
    private List<List<String>> carnoMulti;

    /** 조직 코드(콤마구분) */
    @Schema(description = "조직 코드(콤마구분)", example = "ORG1,ORG2")
    private String gOrganize;

    /** 인보이스 시작일자 */
    @Schema(description = "인보이스시작일자", example = "20250501")
    private String dt1;

    /** 인보이스 종료일자 */
    @Schema(description = "인보이스종료일자", example = "20250531")
    private String dt2;

    /** 관리처코드 */
    @Schema(description = "관리처코드", nullable = false, example = "")
    private String toCustkey;

    /** 관리처코드 */
    @Schema(description = "관리처코드", nullable = false, example = "")
    private String custkey;

	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> toCustkeyMulti;
    
    /** 문서번호 */
    @Schema(description = "문서번호", example = "2182595701")
    private String docno;

    /** 인보이스출력키 */
    @Schema(description = "인보이스출력키", example = "IPK20250509")
    private String invoiceprintkey;

    /** 피킹리스트번호 */
    @Schema(description = "피킹리스트번호", example = "PLN20250509")
    private String pickListNo;

    /** gArea */
    @Schema(description = "gArea")
    private String gArea;

    /** gMultiOrganize */
    @Schema(description = "gMultiOrganize")
    private String gMultiOrganize;

    /** gMultiArea */
    @Schema(description = "gMultiArea")
    private String gMultiArea;

    /** gUserId */
    @Schema(description = "gUserId")
    private String gUserId;

    /** invoicedt */
    @Schema(description = "invoicedt")
    private String invoicedt;

    /** code */
    @Schema(description = "code")
    private String code;

    /** gMultiCourier */
    @Schema(description = "gMultiCourier")
    private String gMultiCourier;

    /** priority */
    @Schema(description = "priority")
    private String priority;

    /** deliverydt */
    @Schema(description = "deliverydt")
    private String deliverydt;

    /** returninclude */
    @Schema(description = "returninclude")
    private String returninclude;

    /** driverYn */
    @Schema(description = "driverYn")
    private String driverYn;

    /** gubun */
    @Schema(description = "gubun")
    private String gubun;
}
