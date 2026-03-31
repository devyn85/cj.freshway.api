package cjfw.wms.st.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.08.25
 * @description : 재고폐기요청/처리 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Data
@Schema(description = "재고폐기요청/처리 Request DTO")
public class StDisuseRequestCenterReqDto extends CommonProcedureDto {
	
	/** 저장 리스트*/
    List<StDisuseRequestCenterResDto> saveList;		
    
    /** 처리결과 리스트 */
    List<StDisuseRequestCenterResResultDto> resultList;		    
    
	/** 저장 리스트 - 전자결재*/
    List<StDisuseRequestCenterResApprovalDto> saveElectApprovalList;	
    
    /** 저장 리스트 - 폐기처리*/
    List<StDisuseRequestCenterResProcessDto> saveProcessList;		  
    
    /** STO저장 리스트*/
    List<StDisuseRequestCenterResCJSTODto> saveSTOList;		
    
	
    /** 고정 센터 코드 */
    @Schema(description = "고정 센터 코드")
    private String fixdccode;		
	
    /** 조직(다중) */
    @Schema(description = "조직(다중)")
    private String organize;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;
    
    /** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;
	
    /** 창고(다중검색) */
	@MultiSearch
    @Schema(description = "창고-다중검색")
    private List<String> organizeMulti;  	

    /** 보관유형 */
    @Schema(description = "보관유형")
    private String storagetype;

    /** 유통기한구분YN */
    @Schema(description = "유통기한구분YN")
    private String lottable01yn;

    /** 존 */
    @Schema(description = "존")
    private String zone;

    /** 시리얼번호여부 */
    @Schema(description = "시리얼번호여부")
    private String serialnoyn;

    /** 시리얼번호 */
    @Schema(description = "시리얼번호")
    private String serialno;

    /** BL번호 */
    @Schema(description = "BL번호")
    private String blno;

    /** 계약거래처 */
    @Schema(description = "계약거래처")
    private String contractcompany;

    /** 재고유형 */
    @Schema(description = "재고유형")
    private String stocktype;

    /** 재고등급 */
    @Schema(description = "재고등급")
    private String stockgrade;

    /** 시작로케이션 */
    @Schema(description = "시작로케이션")
    private String fromloc;

    /** 종료로케이션 */
    @Schema(description = "종료로케이션")
    private String toloc;
    
    /** 요청월 */
    @Schema(description = "요청월")
    private String requestMm;

    /** 폐기유형 */
    @Schema(description = "폐기유형")
    private String disusetype;
    
    /** 폐기구분 */
    @Schema(description = "폐기구분")
    private String disuseDiv;
    
    /** 요청구분(1:폐기등록, 2:폐기요청) */
    @Schema(description = "요청구분(1:폐기등록, 2:폐기요청)")
    private String reqFlag;
    
    /** 문서일자 */
    @Schema(description = "문서일자")
    private String docdt;
    
    /** 인터페이스 전송 유형 */
    @Schema(description = "인터페이스 전송 유형")
    private String ifSendType;
    
    /** 작업프로세스코드 */
    @Schema(description = "작업프로세스코드")
    private String workprocesscode;
    
    /** OMS 플래그 */
    @Schema(description = "OMS 플래그")
    private String omsFlag;
    
    /** 재고이동유형 */
    @Schema(description = "재고이동유형")
    private String stocktranstype;
    
    /** 기준일자 From */
    @Schema(description = "기준일자 From")
    private String basedtFrom;
    
    /** 기준일자 To */
    @Schema(description = "기준일자 To")
    private String basedtTo;

    /** 결재유형 To */
    @Schema(description = "결재유형 To")
    private String approvalType; 
    
    /** 결재상태 */
    @Schema(description = "결재상태")
    private String apprstatus;


    /** 결재요청일자(From) */
    @Schema(description = "결재요청일자(From)")
    private String fromApprreqdt;

    /** 결재요청일자(To) */
    @Schema(description = "결재요청일자(To)")
    private String toApprreqdt;

    /** 전표일자(From) */
    @Schema(description = "전표일자(From)")
    private String fromSlipdt;

    /** 전표일자(To) */
    @Schema(description = "전표일자(To)")
    private String toSlipdt;

    /** 호출구분(1:재고폐기요청) */
    @Schema(description = "호출구분(1:재고폐기요청)")
    private String callFrom;
    
    /** 처리방안 */
    @Schema(description = "처리방안")
    private String disusemethodcd;
    

    @Schema(description = "이체유형-반품STO")
    private String stotype;
    
    @Schema(description = "deliverydate")
    private String deliverydate;
    
    /** sap조회여부 */
    @Schema(description = "sap조회여부")
    private String isSapSearchYn;

	/** 단가/금액표시 여부  */
	@Schema(description = "단가/금액표시 여부 ")
	private String viewPriceYn = "N";
}
