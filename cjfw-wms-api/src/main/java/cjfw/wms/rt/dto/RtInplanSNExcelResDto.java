package cjfw.wms.rt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.05.28 
 * @description : 이력상품반품현황 excel 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.28 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "이력상품반품현황 excel 결과")
public class RtInplanSNExcelResDto {
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
    @Schema(description = "고객주문번호", example = "ORD20250530001")
    private String sourcekey;

    /**
     * 출고일자
     */
    @Schema(description = "출고일자", example = "2025-05-30")
    private String docdtWd;

    /**
     * 고객반품주문번호
     */
    @Schema(description = "고객반품주문번호", example = "RET20250530001")
    private String docno;

    /**
     * 반품요청일자
     */
    @Schema(description = "반품요청일자", example = "2025-05-30")
    private String slipdt;

    /**
     * 주문사유
     */
    @Schema(description = "주문사유", example = "상품불량")
    private String potypename;

    /**
     * 진행상태
     */
    @Schema(description = "진행상태", example = "처리완료")
    private String statusname;

    /**
     * 반품차량번호
     */
    @Schema(description = "반품차량번호", example = "12가3456")
    private String returncarno;

    /**
     * 품목번호
     */
    @Schema(description = "품목번호", example = "001")
    private String docline;

    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "ITEM002")
    private String sku;

    /**
     * 상품명칭
     */
    @Schema(description = "상품명칭", example = "냉장육")
    private String skuname;

    /**
     * 저장유무
     */
    @Schema(description = "저장유무", example = "Y")
    private String channel;

    /**
     * 저장조건
     */
    @Schema(description = "저장조건", example = "냉장")
    private String storagetype;

    /**
     * 구매단위
     */
    @Schema(description = "구매단위", example = "KG")
    private String uom;

    /**
     * 평균중량
     */
    @Schema(description = "평균중량", example = "0.0")
    private double avgweight;

    /**
     * 환산주문박스
     */
    @Schema(description = "환산주문박스", example = "0.0")
    private double calorderbox;

    /**
     * 환산확정박스
     */
    @Schema(description = "환산확정박스", example = "0.0")
    private double calconfirmbox;

    /**
     * 실박스
     */
    @Schema(description = "실박스", example = "0.0")
    private double realbox;

    /**
     * 고객반품주문수량
     */
    @Schema(description = "고객반품주문수량", example = "50.0")
    private double orderqty;

    /**
     * 반품입고수량
     */
    @Schema(description = "반품입고수량", example = "45.0")
    private double confirmqty;

    /**
     * 반품확정일자
     */
    @Schema(description = "반품확정일자", example = "2025-06-01")
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
    @Schema(description = "미회수사유", example = "고객부재")
    private String reasoncode;

    /**
     * 귀책구분
     */
    @Schema(description = "귀책구분", example = "제조사귀책")
    private String other01;

    /**
     * 반품유형
     */
    @Schema(description = "반품유형", example = "정상반품")
    private String ordertypename;

    /**
     * 영업조직
     */
    @Schema(description = "영업조직", example = "영업2팀")
    private String salegroup;

    /**
     * 사업장
     */
    @Schema(description = "사업장", example = "지점1")
    private String saledepartment;

    /**
     * 영업그룹
     */
    @Schema(description = "영업그룹", example = "오프라인")
    private String custgroup;

    /**
     * 고객코드
     */
    @Schema(description = "고객코드", example = "CUST002")
    private String fromCustKey;

    /**
     * 고객명
     */
    @Schema(description = "고객명", example = "김영희")
    private String fromCustName;

    /**
     * VoC(소)
     */
    @Schema(description = "VoC(소)", example = "배송오류")
    private String other03;

    /**
     * 협력사반품
     */
    @Schema(description = "협력사반품", example = "N")
    private String vendorreturn;

    /**
     * 협력사명
     */
    @Schema(description = "협력사명", example = "CJ푸드빌")
    private String custname;

    /**
     * 기준일(유통,제조)
     */
    @Schema(description = "기준일(유통,제조)", example = "2025-05-25")
    private String lottable01;

    /**
     * 유통기간(잔여/전체)
     */
    @Schema(description = "유통기간(잔여/전체)", example = "5/7")
    private String durationTerm;

    /**
     * 이력번호
     */
    @Schema(description = "이력번호", example = "SN000000001")
    private String serialno;

    /**
     * 바코드
     */
    @Schema(description = "바코드", example = "BARC0000001")
    private String barcodeSn;

    /**
     * B/L번호
     */
    @Schema(description = "B/L번호", example = "BL0000001")
    private String convserialno;

    /**
     * 도축일자
     */
    @Schema(description = "도축일자", example = "2025-05-20")
    private String butcherydt;

    /**
     * 도축장
     */
    @Schema(description = "도축장", example = "하림")
    private String factoryname;

    /**
     * 계약유형
     */
    @Schema(description = "계약유형", example = "정기계약")
    private String contracttype;

    /**
     * 계약업체
     */
    @Schema(description = "계약업체", example = "CUST003")
    private String wdCustKey;

    /**
     * 계약업체명
     */
    @Schema(description = "계약업체명", example = "이마트")
    private String wdCustName;

    /**
     * FROM
     */
    @Schema(description = "FROM", example = "2025-05-01")
    private String fromvaliddt;

    /**
     * TO
     */
    @Schema(description = "TO", example = "2025-06-30")
    private String tovaliddt;

    /**
     * 스캔예정량
     */
    @Schema(description = "스캔예정량", example = "10.0")
    private double serialorderqty;

    /**
     * 스캔량
     */
    @Schema(description = "스캔량", example = "8.0")
    private double serialinspectqty;

    /**
     * 스캔중량
     */
    @Schema(description = "스캔중량", example = "80.0")
    private double serialscanweight;

    /**
     * DCNAME
     */
    @Schema(description = "DCNAME", example = "서울물류센터")
    private String dcname;

    /**
     * ORGANIZE
     */
    @Schema(description = "ORGANIZE", example = "ORG002")
    private String organize;

    /**
     * DOCDT
     */
    @Schema(description = "DOCDT", example = "2025-05-30")
    private String docdt;

    /**
     * POTYPE
     */
    @Schema(description = "POTYPE", example = "PO002")
    private String potype;

    /**
     * STATUS
     */
    @Schema(description = "STATUS", example = "ST002")
    private String status;

    /**
     * STORERKEY
     */
    @Schema(description = "STORERKEY", example = "STOR002")
    private String storerkey;

    /**
     * RETURNTYPE
     */
    @Schema(description = "RETURNTYPE", example = "RT002")
    private String returntype;

    /**
     * ORDERTYPE
     */
    @Schema(description = "ORDERTYPE", example = "OT002")
    private String ordertype;

    /**
     * DOCTYPE
     */
    @Schema(description = "DOCTYPE", example = "DT002")
    private String doctype;

    /**
     * SERIALLEVEL
     */
    @Schema(description = "SERIALLEVEL", example = "1")
    private String seriallevel;

    /**
     * SERIALTYPE
     */
    @Schema(description = "SERIALTYPE", example = "TYPEA")
    private String serialtype;

    /**
     * COLORDESCR
     */
    @Schema(description = "COLORDESCR", example = "빨간색")
    private String colordescr;

    /**
     * PLACEOFORIGIN
     */
    @Schema(description = "PLACEOFORIGIN", example = "국내산")
    private String placeoforigin;

    /**
     * DURATION
     */
    @Schema(description = "DURATION", example = "7")
    private String duration;

    /**
     * DURATIONTYPE
     */
    @Schema(description = "DURATIONTYPE", example = "일")
    private String durationtype;

    /**
     * PLANT
     */
    @Schema(description = "PLANT", example = "PLANT001")
    private String plant;

    /**
     * PLANT_DESCR
     */
    @Schema(description = "PLANT_DESCR", example = "[PLANT001]공장명")
    private String plantDescr;

    /** * 제조 */
    @Schema(description = "* 제조")
    private String lotManufacture;

    /** * 유통 */
    @Schema(description = "* 유통")
    private String lotExpire;

    	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
