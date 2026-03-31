package cjfw.wms.om.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.09.12
 * @description : 저장품발주삭제 기능을 구현한 Res DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.12        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "저장품발주삭제 조회")
public class OmPurchaseModifyResDto extends CommonProcedureDto {
	@Schema(description = "체크 여부", example = "0")
    private String checkYn;

    @Schema(description = "전표 일자", example = "20250912")
    private String slipDt;

    @Schema(description = "물류센터 코드", example = "DC01")
    private String dcCode;

    @Schema(description = "센터명")
    private String dcName;

    @Schema(description = "거래처 키", example = "CUST01")
    private String custKey;

    @Schema(description = "거래처명")
    private String custName;

    @Schema(description = "거래처 유형", example = "PO")
    private String custType;

    @Schema(description = "요청 번호", example = "REQ001")
    private String requestNo;

    @Schema(description = "출발지 센터 코드")
    private String fromDcCode;

    @Schema(description = "출발지 센터명")
    private String fromDcName;

    @Schema(description = "조직 코드")
    private String organize;

    @Schema(description = "조직명")
    private String organizeName;

    @Schema(description = "고객사 코드", example = "STORER01")
    private String storerKey;    

    @Schema(description = "발주 구분")
    private String purchaseType;

    @Schema(description = "배송 유형")
    private String deliveryType;

    @Schema(description = "PO 키")
    private String poKey;

    @Schema(description = "PO 라인")
    private String poLine;

    @Schema(description = "상품 코드")
    private String sku;

    @Schema(description = "상품명")
    private String skuName;
    
    @Schema(description = "주문수량")
    private String orderQty;

    @Schema(description = "단위 (UOM)")
    private String uom;

    @Schema(description = "상품 구매 단위 (UOM)")
    private String purchaseUom;

    @Schema(description = "시리얼 번호")
    private String serialNo;

    @Schema(description = "바코드_SN")
    private String barcodeSn;

    @Schema(description = "변환 시리얼 번호")
    private String convSerialNo;

    @Schema(description = "도축일")
    private String butcheryDt;

    @Schema(description = "공장명")
    private String factoryname;

    @Schema(description = "계약 유형")
    private String contractType;

    @Schema(description = "계약 업체 키")
    private String contractCompany;

    @Schema(description = "계약 업체명")
    private String contractCompanyName;

    @Schema(description = "유효 기간 시작일")
    private String fromValidDt;

    @Schema(description = "유효 기간 종료일")
    private String toValidDt;

    @Schema(description = "요청 라인")
    private String requestLine;

    @Schema(description = "삭제 여부")
    private String delYn;

    @Schema(description = "등록자 ID")
    private String addWho;

    @Schema(description = "등록일")
    private String addDate;

    @Schema(description = "수정자 ID")
    private String editWho;

    @Schema(description = "수정일")
    private String editDate;
    
    @Schema(description = "문서번호")
    private String docNo;

    @Schema(description = "출발지 조직 코드")
    private String fromOrganize;

    @Schema(description = "출발지 조직명")
    private String fromOrganizeName;

    @Schema(description = "전표 번호")
    private String slipNo;

    @Schema(description = "전표 라인")
    private String slipLine;

    @Schema(description = "삭제 처리 여부")
    private String delProcYn;
    
    @Schema(description = "최종 변경자")
    private String editWhoNm;
    
    @Schema(description = "최초 등록자")
    private String addWhoNm;
    
    @Schema(description = "분자")
    private BigDecimal bunja;
    
    @Schema(description = "분모")
    private BigDecimal bunmo;
    
    @Schema(description = "area")
    private String area;
    
    @Schema(description = "저장구분")
    private String channel;
    
    @Schema(description = "창고")
    private String route;
    
    @Schema(description = "창고조직")
    private String routeOrganize;
    
    @Schema(description = "EA환산")
    private BigDecimal eaCal;
    
    @Schema(description = "입고센터분배여부")
    private String allocationYn;
}
