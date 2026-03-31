package cjfw.wms.rt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.05.28 
 * @description : 이력상품반품현황 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.28 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "이력상품반품현황 목록 결과")
public class RtInplanSNResDto {
	    /**
	     * 물류센터
	     */
	    @Schema(description = "물류센터", example = "DC001")
	    private String dccode;

	    /**
	     * 창고명
	     */
	    @Schema(description = "창고명", example = "중앙물류창고")
	    private String organizename;

	    /**
	     * 고객주문번호
	     */
	    @Schema(description = "고객주문번호", example = "CUSTORDER123")
	    private String sourcekey;

	    /**
	     * 출고일자
	     */
	    @Schema(description = "출고일자", example = "2023-01-15")
	    private String docdtWd;

	    /**
	     * 고객반품주문번호
	     */
	    @Schema(description = "고객반품주문번호", example = "RETURN12345")
	    private String docno;

	    /**
	     * 반품요청일자
	     */
	    @Schema(description = "반품요청일자", example = "2023-01-10")
	    private String slipdt;

	    /**
	     * 주문사유
	     */
	    @Schema(description = "주문사유", example = "고객 변심")
	    private String potypename;

	    /**
	     * 진행상태
	     */
	    @Schema(description = "진행상태", example = "반품 완료")
	    private String statusname;

	    /**
	     * 반품차량번호
	     */
	    @Schema(description = "반품차량번호", example = "CAR9876")
	    private String returncarnno;

	    /**
	     * 품목번호
	     */
	    @Schema(description = "품목번호", example = "001")
	    private String docline;

	    /**
	     * 상품코드
	     */
	    @Schema(description = "상품코드", example = "ITEM_A001")
	    private String sku;

	    /**
	     * 상품명칭
	     */
	    @Schema(description = "상품명칭", example = "노트북")
	    private String skuname;

	    /**
	     * 저장유무
	     */
	    @Schema(description = "저장유무", example = "Y")
	    private String channel;

	    /**
	     * 저장조건
	     */
	    @Schema(description = "저장조건", example = "상온")
	    private String storagetype;

	    /**
	     * 구매단위
	     */
	    @Schema(description = "구매단위", example = "EA")
	    private String uom;

	    /**
	     * 고객반품주문수량
	     */
	    @Schema(description = "고객반품주문수량", example = "5")
	    private String orderqty;

	    /**
	     * 반품입고수량
	     */
	    @Schema(description = "반품입고수량", example = "5")
	    private String confirmqty;

	    /**
	     * 반품입고일자
	     */
	    @Schema(description = "반품입고일자", example = "2023-01-18")
	    private String confirmdate;

	    /**
	     * 실물여부
	     */
	    @Schema(description = "실물여부", example = "Y")
	    private String packingmethod;

	    /**
	     * 회수여부
	     */
	    @Schema(description = "회수여부", example = "Y")
	    private String returntypename;

	    /**
	     * 미회수사유
	     */
	    @Schema(description = "미회수사유", example = "파손")
	    private String reasoncode;

	    /**
	     * 세부내역
	     */
	    @Schema(description = "세부내역", example = "화면 깨짐")
	    private String reasonmsg;

	    /**
	     * 귀책구분
	     */
	    @Schema(description = "귀책구분", example = "고객")
	    private String other01;

	    /**
	     * 귀속구분
	     */
	    @Schema(description = "귀속구분", example = "영업")
	    private String blngdeptname;

	    /**
	     * 반품유형
	     */
	    @Schema(description = "반품유형", example = "정상 반품")
	    private String ordertypename;

	    /**
	     * 영업조직
	     */
	    @Schema(description = "영업조직", example = "온라인사업부")
	    private String salegroup;

	    /**
	     * 사업장
	     */
	    @Schema(description = "사업장", example = "서울점")
	    private String saledepartment;

	    /**
	     * 영업그룹
	     */
	    @Schema(description = "영업그룹", example = "B2C")
	    private String custgroup;

	    /**
	     * 고객코드
	     */
	    @Schema(description = "고객코드", example = "CUST0001")
	    private String fromCustkey;

	    /**
	     * 고객명
	     */
	    @Schema(description = "고객명", example = "홍길동")
	    private String fromCustname;

	    /**
	     * VoC(소)
	     */
	    @Schema(description = "VoC(소)", example = "상품 불량")
	    private String other03;

	    /**
	     * VoC(세)
	     */
	    @Schema(description = "VoC(세)", example = "초기 불량")
	    private String other04;

	    /**
	     * 협력사반품
	     */
	    @Schema(description = "협력사반품", example = "N")
	    private String vendoreturn;

	    /**
	     * 협력사명
	     */
	    @Schema(description = "협력사명", example = "삼성전자")
	    private String custname;

	    /**
	     * 생성인
	     */
	    @Schema(description = "생성인", example = "user01")
	    private String addwho;

	    /**
	     * 확정자
	     */
	    @Schema(description = "확정자", example = "admin01")
	    private String editwho;

	    /**
	     * DCNAME
	     */
	    @Schema(description = "DCNAME", example = "중앙물류센터")
	    private String dcname;

	    /**
	     * ORGANIZE
	     */
	    @Schema(description = "ORGANIZE", example = "ORG01")
	    private String organize;

	    /**
	     * DOCDT
	     */
	    @Schema(description = "DOCDT", example = "2023-01-10")
	    private String docdt;

	    /**
	     * POTYPE
	     */
	    @Schema(description = "POTYPE", example = "RETURN")
	    private String potype;

	    /**
	     * STATUS
	     */
	    @Schema(description = "STATUS", example = "COMPLETED")
	    private String status;

	    /**
	     * STORERKEY
	     */
	    @Schema(description = "STORERKEY", example = "STR001")
	    private String storerkey;
// 미사용 중복에 따른 주석
//	    /**
//	     * RETURNTYPE
//	     */
//	    @Schema(description = "RETURNTYPE", example = "GOODS_RETURN")
//	    private String returnType;


	    /**
	     * DOCTYPE
	     */
	    @Schema(description = "DOCTYPE", example = "RMA")
	    private String doctype;

		/** RETURNCARNO */
		@Schema(description = "RETURNCARNO")
		private String returncarno;

		/** RETURNTYPE */
		@Schema(description = "RETURNTYPE")
		private String returntype;

		/** RETURTYPENNAME */
		@Schema(description = "RETURTYPENNAME")
		private String returtypenname;

		/** ORDERTYPE */
		@Schema(description = "ORDERTYPE")
		private String ordertype;

		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
