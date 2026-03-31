package cjfw.wms.rt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.09 
 * @description : 반품진행현황 Excel 조회 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.09 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "반품진행현황 Excel 조회 결과")
public class RtInplanTotalExcelResDto {
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
    @Schema(description = "고객주문번호", example = "ORD20250605001")
    private String docnoWd;

    /**
     * 출고일자
     */
    @Schema(description = "출고일자", example = "2025-06-05")
    private String docdtWd;

    /**
     * 고객반품주문번호
     */
    @Schema(description = "고객반품주문번호", example = "RET20250605001")
    private String docno;

    /**
     * 반품요청일자
     */
    @Schema(description = "반품요청일자", example = "2025-06-05")
    private String slipdt;

    /**
     * 주문사유
     */
    @Schema(description = "주문사유", example = "단순변심")
    private String sotypename;

    /**
     * 진행상태
     */
    @Schema(description = "진행상태", example = "처리중")
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
    @Schema(description = "상품명칭", example = "갤럭시 S24")
    private String skuname;

    /**
     * 저장유무
     */
    @Schema(description = "저장유무", example = "저장")
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
    @Schema(description = "고객반품주문수량", example = "10.0")
    private double orderqty;

    /**
     * 반품입고수량
     */
    @Schema(description = "반품입고수량", example = "8.0")
    private double confirmqty;

    /**
     * 미회수수량
     */
    @Schema(description = "미회수수량", example = "2.0")
    private double shortageqty;

    /**
     * 반품확정일자
     */
    @Schema(description = "반품확정일자", example = "2025-06-06")
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
    @Schema(description = "미회수사유", example = "고객거부")
    private String reasoncode;

    /**
     * 세부내역
     */
    @Schema(description = "세부내역", example = "상품훼손")
    private String reasonmsg;

    /**
     * 귀책구분
     */
    @Schema(description = "귀책구분", example = "판매자귀책")
    private String other01;

    /**
     * 귀속구분
     */
    @Schema(description = "귀속구분", example = "상품팀")
    private String blngDeptName;

    /**
     * 영업조직
     */
    @Schema(description = "영업조직", example = "영업1팀")
    private String salegroup;

    /**
     * 사업장
     */
    @Schema(description = "사업장", example = "본사")
    private String saledepartment;

    /**
     * 영업그룹
     */
    @Schema(description = "영업그룹", example = "온라인")
    private String custgroup;

    /**
     * 고객코드
     */
    @Schema(description = "고객코드", example = "CUST001")
    private String fromCustKey;

    /**
     * 고객명
     */
    @Schema(description = "고객명", example = "홍길동")
    private String fromCustName;

    /**
     * 클레임사유
     */
    @Schema(description = "클레임사유", example = "품질불량")
    private String other03;

    /**
     * 협력사반품
     */
    @Schema(description = "협력사반품", example = "Y")
    private String vendorreturn;

    /**
     * 협력사명
     */
    @Schema(description = "협력사명", example = "삼성전자")
    private String custname;

    /**
     * SAP실적전송
     */
    @Schema(description = "SAP실적전송", example = "Y")
    private String ifAuditFile;

    /**
     * 전송시간
     */
    @Schema(description = "전송시간", example = "2025-06-05 10:30:00")
    private String ifSendFile;

    /**
     * SAP클레임번호
     */
    @Schema(description = "SAP클레임번호", example = "SAPCLM001")
    private String sapclaimno;

    /**
     * 순번
     */
    @Schema(description = "순번", example = "1")
    private String claimseq;

    /**
     * 세부내역
     */
    @Schema(description = "세부내역", example = "고객요청")
    private String memo;

    /**
     * 작성자
     */
    @Schema(description = "작성자", example = "김철수")
    private String writer;

    /**
     * 작성일자
     */
    @Schema(description = "작성일자", example = "2025-06-05")
    private String writedate;

    /**
     * 작성시간
     */
    @Schema(description = "작성시간", example = "10:30:00")
    private String writetime;

    /**
     * Voc(세)
     */
    @Schema(description = "Voc(세)", example = "배송지연")
    private String other04;

    /**
     * DCNAME
     */
    @Schema(description = "DCNAME", example = "서초물류센터")
    private String dcname;

    /**
     * ORGANIZE
     */
    @Schema(description = "ORGANIZE", example = "ORGAN_001")
    private String organize;

    /**
     * STORERKEY
     */
    @Schema(description = "STORERKEY", example = "STORER_001")
    private String storerKey;

    /**
     * DOCDT
     */
    @Schema(description = "DOCDT", example = "2025-06-05")
    private String docdt;

    /**
     * SOTYPE
     */
    @Schema(description = "SOTYPE", example = "SO01")
    private String sotype;

    /**
     * STATUS
     */
    @Schema(description = "STATUS", example = "S01")
    private String status;

    /**
     * CHANNEL
     */
    @Schema(description = "CHANNEL", example = "1")
    private String channel;

    /**
     * STORAGETYPE
     */
    @Schema(description = "STORAGETYPE", example = "ST01")
    private String storagetype;

    /**
     * RETURNTYPE
     */
    @Schema(description = "RETURNTYPE", example = "RT01")
    private String returntype;

    /**
     * ORDERTYPE
     */
    @Schema(description = "ORDERTYPE", example = "OT01")
    private String ordertype;

    /**
     * ORDERTYPENAME
     */
    @Schema(description = "ORDERTYPENAME", example = "정상주문")
    private String ordertypename;

    /**
     * SALEORGANIZE
     */
    @Schema(description = "SALEORGANIZE", example = "판매조직1")
    private String saleorganize;

    /**
     * BILLTOCUSTKEY
     */
    @Schema(description = "BILLTOCUSTKEY", example = "BCUST001")
    private String billtocustkey;

    /**
     * BILLTOCUSTNAME
     */
    @Schema(description = "BILLTOCUSTNAME", example = "수취인명")
    private String billtocustname;

    /**
     * DOCTYPE
     */
    @Schema(description = "DOCTYPE", example = "DOCC01")
    private String doctype;

    /**
     * SOURCEKEY
     */
    @Schema(description = "SOURCEKEY", example = "SOURCE001")
    private String sourcekey;

    /**
     * SLIPNO
     */
    @Schema(description = "SLIPNO", example = "SLIP001")
    private String slipno;

    /**
     * DEL_YN
     */
    @Schema(description = "DEL_YN", example = "N")
    private String delYn;

    	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
