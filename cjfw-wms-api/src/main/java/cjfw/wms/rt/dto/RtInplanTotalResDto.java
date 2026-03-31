package cjfw.wms.rt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.04 
 * @description : 반품진행현황 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.04 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "반품진행현황 목록 결과")
public class RtInplanTotalResDto {
	/**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "DC001")
    private String dccode;

    /**
     * 창고
     */
    @Schema(description = "창고", example = "WH001")
    private String organize;

    /**
     * 창고명
     */
    @Schema(description = "창고명", example = "중앙물류창고")
    private String organizeName;

    /**
     * 고객주문번호
     */
    @Schema(description = "고객주문번호", example = "ORD20250604-001")
    private String docnoWd;

    /**
     * 출고일자
     */
    @Schema(description = "출고일자", example = "2025-06-03")
    private String docdtWd;

    /**
     * 고객반품주문번호
     */
    @Schema(description = "고객반품주문번호", example = "RET20250604-001")
    private String docno;

    /**
     * 반품요청일자
     */
    @Schema(description = "반품요청일자", example = "2025-06-04")
    private String slipdt;

    /**
     * 주문사유
     */
    @Schema(description = "주문사유", example = "단순변심")
    private String sotypename;

    /**
     * 진행상태
     */
    @Schema(description = "진행상태", example = "반품접수")
    private String statusname;

    /**
     * 반품차량번호
     */
    @Schema(description = "반품차량번호", example = "123가4567")
    private String returncarno;

    /**
     * 품목번호
     */
    @Schema(description = "품목번호", example = "001")
    private String docline;

    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "ITEM001")
    private String sku;

    /**
     * 상품명칭
     */
    @Schema(description = "상품명칭", example = "샘플상품A")
    private String skuname;

    /**
     * 플랜트
     */
    @Schema(description = "플랜트", example = "[PLT01]서울공장")
    private String plantDescr;

    /**
     * 저장유무
     */
    @Schema(description = "저장유무", example = "Y")
    private String channelname;

    /**
     * 저장조건
     */
    @Schema(description = "저장조건", example = "상온")
    private String storagetypename;

    /**
     * 구매단위
     */
    @Schema(description = "구매단위", example = "EA")
    private String uom;

    /**
     * 고객반품주문수량
     */
    @Schema(description = "고객반품주문수량", example = "10")
    private String orderqty;

    /**
     * 반품입고수량
     */
    @Schema(description = "반품입고수량", example = "8")
    private String confirmqty;

    /**
     * 미회수수량
     */
    @Schema(description = "미회수수량", example = "2")
    private String shortageqty;

    /**
     * 반품확정일자
     */
    @Schema(description = "반품확정일자", example = "2025-06-05")
    private String confirmdate;

    /**
     * 실물여부
     */
    @Schema(description = "실물여부", example = "Y")
    private String packingmethod;

    /**
     * 회수여부
     */
    @Schema(description = "회수여부", example = "회수완료")
    private String returntypename;

    /**
     * 미회수사유
     */
    @Schema(description = "미회수사유", example = "고객폐기")
    private String reasoncode;

    /**
     * 세부내역
     */
    @Schema(description = "세부내역", example = "상품 파손으로 인한 폐기")
    private String reasonmsg;

    /**
     * 귀책구분
     */
    @Schema(description = "귀책구분", example = "고객귀책")
    private String other01;

    /**
     * 귀속구분
     */
    @Schema(description = "귀속구분", example = "영업부서")
    private String blngdeptname;

    /**
     * 반품유형
     */
    @Schema(description = "반품유형", example = "일반반품")
    private String orderTypename;

    /**
     * 영업조직
     */
    @Schema(description = "영업조직", example = "영업본부")
    private String saleorganize;

    /**
     * 사업장
     */
    @Schema(description = "사업장", example = "본사")
    private String saledepartment;

    /**
     * 영업그룹
     */
    @Schema(description = "영업그룹", example = "가전영업")
    private String salegroup;

    /**
     * 관리처코드
     */
    @Schema(description = "관리처코드", example = "CUST001")
    private String fromCustkey;

    /**
     * 관리처명
     */
    @Schema(description = "관리처명", example = "ABC전자")
    private String fromCustname;

    /**
     * 고객코드
     */
    @Schema(description = "고객코드", example = "BILLCUST001")
    private String billtocustkey;

    /**
     * 고객명
     */
    @Schema(description = "고객명", example = "김철수")
    private String billtocustname;

    /**
     * Voc(소)
     */
    @Schema(description = "Voc(소)", example = "불량")
    private String other03;

    /**
     * Voc(세)
     */
    @Schema(description = "Voc(세)", example = "파손")
    private String other04;

    /**
     * 협력사반품
     */
    @Schema(description = "협력사반품", example = "Y")
    private String vendoreturn;

    /**
     * 협력사명
     */
    @Schema(description = "협력사명", example = "ABC부품")
    private String custname;

    /**
     * 반품요청일
     */
    @Schema(description = "반품요청일", example = "2025-06-04")
    private String startDt;

    /**
     * 반품확정일
     */
    @Schema(description = "반품확정일", example = "2025-06-05")
    private String endDt;

    /**
     * 반품수거일수
     */
    @Schema(description = "반품수거일수", example = "1")
    private String reCount;

    /**
     * */
    @Schema(description = "", example = "STR001")
    private String storerkey;

    /**
     * */
    @Schema(description = "", example = "2025-06-04")
    private String docdt;

    /**
     * */
    @Schema(description = "", example = "RT01")
    private String sotype;

    /**
     * */
    @Schema(description = "", example = "20")
    private String status;

    /**
     * */
    @Schema(description = "", example = "1")
    private String channel;

    /**
     * */
    @Schema(description = "", example = "ST01")
    private String storagetype;

    /**
     * */
    @Schema(description = "", example = "2025-06-05T10:30:00")
    private String confirmdate2; // Renamed to avoid conflict with `confirmdate`

    /**
     * */
    @Schema(description = "", example = "RTV")
    private String returntype;

    /**
     * */
    @Schema(description = "", example = "NORMAL")
    private String ordertype;

    /**
     * */
    @Schema(description = "", example = "CG01")
    private String custgroup;

    /**
     * */
    @Schema(description = "", example = "DOR")
    private String doctype;

    /**
     * */
    @Schema(description = "", example = "SRC001")
    private String sourcekey;

    /**
     * */
    @Schema(description = "", example = "SLIP001")
    private String slipno;

    /**
     * */
    @Schema(description = "", example = "N")
    private String delYn;

    /**
     * */
    @Schema(description = "", example = "true")
    private String ifAuditFile;

    /**
     * */
    @Schema(description = "", example = "false")
    private String ifSendFile;

    /**
     * */
    @Schema(description = "", example = "PLT01")
    private String plant;

    /**
     * */
    @Schema(description = "", example = "동탄물류센터")
    private String dcname;

    	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
