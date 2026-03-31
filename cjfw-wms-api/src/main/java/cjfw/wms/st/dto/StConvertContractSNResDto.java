package cjfw.wms.st.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.11.14 
 * @description : 상품이력계약정보변경 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.14 ParkJinWoo 생성
 */
@Data
@Schema(description = "상품이력계약정보변경 요청")
public class StConvertContractSNResDto extends CommonProcedureDto {
    

    /** 체크여부 */
    @Schema(description = "체크여부")
    private String checkYn;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명")
    private String skuName;

    /** 이력번호 */
    @Schema(description = "이력번호")
    private String serialNo;

    /** 공장명 */
    @Schema(description = "공장명")
    private String factoryName;

    /** BL번호 */
    @Schema(description = "BL번호")
    private String convSerialNo;

    /** 도축일자 */
    @Schema(description = "도축일자")
    private String butcheryDt;

    /** 계약업체코드 */
    @Schema(description = "계약업체코드")
    private String contractCompany;

    /** 계약업체명 */
    @Schema(description = "계약업체명")
    private String contractCompanyName;

    /** 계약담당자명 */
    @Schema(description = "계약담당자명")
    private String contractCompanyEmpName1;

    /** 유효시작일자 */
    @Schema(description = "유효시작일자")
    private String fromValidDt;

    /** 유효종료일자 */
    @Schema(description = "유효종료일자")
    private String toValidDt;

    /** 계약유형 */
    @Schema(description = "계약유형")
    private String contractType;

    /** 바코드 */
    @Schema(description = "바코드")
    private String barcode;

    /** 구매주문번호 */
    @Schema(description = "구매주문번호")
    private String poKey;

    /** 구매주문라인번호 */
    @Schema(description = "구매주문라인번호")
    private String poLine;

    /** 헤더시리얼키 */
    @Schema(description = "헤더시리얼키")
    private String headerSerialKey;

    /** 디테일시리얼키 */
    @Schema(description = "디테일시리얼키")
    private String detailSerialKey;

    /** 원본계약업체코드 */
    @Schema(description = "원본계약업체코드")
    private String contractCompanyOrg;

    /** 원본계약유형 */
    @Schema(description = "원본계약유형")
    private String contractTypeOrg;

		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
