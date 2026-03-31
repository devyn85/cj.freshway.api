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
 * @description : 센터자체감모 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Data
@Schema(description = "센터자체감모 Request DTO")
public class StAdjustmentReqDto extends CommonProcedureDto {
	
	/** 저장 리스트 */
    List<StAdjustmentResDto> saveList;	
    
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String fixdccode;
    
    /** 상품 */
    @Schema(description = "상품")
    private String sku;
    
    /** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;      
    
    /** LOTTABLE01YN */
    @Schema(description = "LOTTABLE01YN")
    private String lottable01yn;

    /** 조직 */
    @Schema(description = "조직")
    private String organize;

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

    /** 시리얼번호 */
    @Schema(description = "시리얼번호")
    private String serialno;

    /** BL번호 */
    @Schema(description = "BL번호")
    private String blno;

    /** 계약거래처 */
    @Schema(description = "계약거래처")
    private String contractcompany;
    
    /** 거래처(다중OR검색) */
	@MultiSearch
    @Schema(description = "거래처-다중OR검색")
    private List<List<String>> contractcompanyMulti;      
        
    
    /** 변환유형 (예: 'CL') */
    @Schema(description = "변환유형")
    private String converttype;
    
    /** 문서일자 */
    @Schema(description = "문서일자")
    private String docdt;

    /** 재고이동유형 */
    @Schema(description = "재고이동유형")
    private String stocktranstype;

    /** 인터페이스전송유형 */
    @Schema(description = "인터페이스전송유형")
    private String ifSendType;

    /** 작업프로세스코드 */
    @Schema(description = "작업프로세스코드")
    private String workprocesscode;

    /** OMS플래그 */
    @Schema(description = "OMS플래그")
    private String omsFlag;

    /** 비용코드 */
    @Schema(description = "비용코드")
    private String costcd;

    /** 입력유형 */
    @Schema(description = "입력유형")
    private String imputetype;

    /** 거래처코드 */
    @Schema(description = "거래처코드")
    private String custkey;    
    
    /** 세트여부 */
    @Schema(description = "세트여부")
    private String setYn;    
    
}
