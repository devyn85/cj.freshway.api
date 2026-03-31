package cjfw.wms.wd.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.05.09 
 * @description : 출고진행현황 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "출고진행현황 목록 결과")
public class WdInplanResDto extends CommonDto {
	/** 물류센터 */
    @Schema(description = "물류센터", nullable = true, example = "")
    private String dccode;

    /** 고객사코드 */
    @Schema(description = "고객사코드", nullable = true, example = "")
    private String storerkey;

    /** 창고 */
    @Schema(description = "창고", nullable = true, example = "")
    private String organize;

    /** 출고일자 */
    @Schema(description = "출고일자", nullable = true, example = "")
    private String slipdt;

    /** 출고번호 */
    @Schema(description = "출고번호", nullable = true, example = "")
    private String slipno;

    /** 출고라인 */
    @Schema(description = "출고순번", nullable = true, example = "")
    private String slipline;

    /** 주문일자 */
    @Schema(description = "주문일자", nullable = true, example = "")
    private String docdt;

    /** 주문번호 */
    @Schema(description = "주문번호", nullable = true, example = "")
    private String docno;

    /** 주문유형 */
    @Schema(description = "주문유형", nullable = true, example = "")
    private String doctype;

    /** 주문순번 */
    @Schema(description = "주문순번", nullable = true, example = "")
    private String docline;

    /** 주문유형 */
    @Schema(description = "주문유형", nullable = true, example = "")
    private String ordertype;    
    
    /** 유통이력 */
    @Schema(description = "유통이력", nullable = true, example = "")
    private String serialtypename;

    /** 주문사유 */
    @Schema(description = "주문사유", nullable = true, example = "")
    private String sotype;

    /** 영업그룹 */
    @Schema(description = "영업그룹", nullable = true, example = "")
    private String salegroup;

    /** 사업장 */
    @Schema(description = "사업장", nullable = true, example = "")
    private String saledepartment;

    /** 영업조직 */
    @Schema(description = "영업조직", nullable = true, example = "")
    private String saleorganize;

    /** 거래처그룹 */
    @Schema(description = "거래처그룹", nullable = true, example = "")
    private String custgroup;

    /** 판매처코드 */
    @Schema(description = "판매처코드", nullable = true, example = "")
    private String billtocustkey;

    /** 판매처명 */
    @Schema(description = "판매처명", nullable = true, example = "")
    private String billtocustname;

    /** 관리처코드 */
    @Schema(description = "관리처코드", nullable = true, example = "")
    private String toCustkey;

    /** 관리처명 */
    @Schema(description = "관리처명", nullable = true, example = "")
    private String toCustname;

    /** 분할관리처코드 */
    @Schema(description = "분할관리처코드", nullable = true, example = "")
    private String mngplcId;

    /** 분할관리처명 */
    @Schema(description = "분할관리처명", nullable = true, example = "")
    private String mngplcName;

    /** 진행상태 */
    @Schema(description = "진행상태", nullable = true, example = "")
    private String status;

    /** POP번호 */
    @Schema(description = "POP번호", nullable = true, example = "")
    private String deliverygroup;

    /** 차량번호 */
    @Schema(description = "차량번호", nullable = true, example = "")
    private String carno;

    /** 상품코드 */
    @Schema(description = "상품코드", nullable = true, example = "")
    private String sku;
    /** 상품(다중선택) */
    @Schema(description = "상품(다중선택)")
    private List<List<String>> skuMulti;  	    

    /** 상품명칭 */
    @Schema(description = "상품명칭", nullable = true, example = "")
    private String skuname;

    /** 저장유무 */
    @Schema(description = "저장유무", nullable = true, example = "")
    private String channel;

    /** 저장조건 */
    @Schema(description = "저장조건", nullable = true, example = "")
    private String storagetype;

    /** 주문수량 */
    @Schema(description = "주문수량", nullable = true, example = "0")
    private BigDecimal orderqty;

    /** 주문중량 */
    @Schema(description = "주문중량", nullable = true, example = "0.0")
    private BigDecimal orderweight;

    /** 취소량 */
    @Schema(description = "취소량", nullable = true, example = "0.0")
    private BigDecimal cancelqty;

    /** 출고중량 */
    @Schema(description = "출고중량", nullable = true, example = "0.0")
    private BigDecimal confirmweight;

    /** 판매단위 */
    @Schema(description = "판매단위", nullable = true, example = "")
    private String uom;

    /** 분배량 */
    @Schema(description = "분배량", nullable = true, example = "0.0")
    private BigDecimal processqty;

    /** 피킹량 */
    @Schema(description = "피킹량", nullable = true, example = "0.0")
    private BigDecimal workqty;

    /** 검수량 */
    @Schema(description = "검수량", nullable = true, example = "0.0")
    private BigDecimal inspectqty;

    /** 출고수량 */
    @Schema(description = "출고수량", nullable = true, example = "0.0")
    private BigDecimal confirmqty;

    /** 마감여부 */
    @Schema(description = "마감여부", nullable = true, example = "")
    private String closeyn;

    /** 삭제여부 */
    @Schema(description = "삭제여부", nullable = true, example = "")
    private String delYn;

    /** 수신검증파일명 */
    @Schema(description = "수신검증파일명", nullable = true, example = "")
    private String ifAuditFile;

    /** 송신파일형태 */
    @Schema(description = "송신파일형태", nullable = true, example = "")
    private String ifSendFile;

    /** 등록일시 */
    @Schema(description = "등록일시", nullable = true, example = "2025-05-16T14:21:00")
    private String adddate;
    
    /** 등록자 */    
    @Schema(description = "등록자", nullable = true, example = "")
    private String addwho;

    /** 플랜트 코드 */
    @Schema(description = "플랜트 코드", nullable = true, example = "")
    private String plant;

    /** 플랜트 */
    @Schema(description = "플랜트", nullable = true, example = "")
    private String plantDescr;

    /** 회수위치 */
    @Schema(description = "회수위치", nullable = true, example = "")
    private String loadplace;

    /** 경유지코드 */
    @Schema(description = "경유지코드", nullable = true, example = "")
    private String route;

    /** 경유지 */
    @Schema(description = "경유지", nullable = true, example = "")
    private String routeDescr;

    /** 배송수단 */
    @Schema(description = "배송수단", nullable = true, example = "")
    private String deliverytype;

    /** 이력관리대상 */
    @Schema(description = "이력관리대상", nullable = true, example = "")
    private String serialyn;

    /** 비정량 여부 */
    @Schema(description = "비정량 여부", nullable = true, example = "")
    private String line01;

    /** 사전주문조정의료여부 */
    @Schema(description = "사전주문조정의료여부", nullable = true, example = "")
    private String beforeShortageplanyn;

    /** 영업경로(대) */
    @Schema(description = "영업경로(대)", nullable = true, example = "")
    private String salecushrc1;

    /** 영업경로(중) */
    @Schema(description = "영업경로(중)", nullable = true, example = "")
    private String salecushrc2;

    /** 영업경로(소) */
    @Schema(description = "영업경로(소)", nullable = true, example = "")
    private String salecushrc3;

    /** 박스입수 */
    @Schema(description = "박스입수", nullable = true, example = "0")
    private BigDecimal qtyperbox;

    /** 제외대상여부 */
    @Schema(description = "제외대상여부", nullable = true, example = "")
    private String exceptYn;

    /** 실배송지 */
    @Schema(description = "실배송지", nullable = true, example = "")
    private String toVataddress1;

    /** 우편번호 */
    @Schema(description = "우편번호", nullable = true, example = "")
    private String toZipcode;

    /** 주문유형 */
    @Schema(description = "주문유형", nullable = true, example = "")
    private String tplTypeNm;

    /** 분류피킹업체 */
    @Schema(description = "분류피킹업체", nullable = true, example = "")
    private String dummy1;

    /*메인화면 처리용*/
    private String deliverydate;
    private BigDecimal cnt1;
    private BigDecimal cnt2;
    private BigDecimal cnt3;
    private BigDecimal cnt4;
}
