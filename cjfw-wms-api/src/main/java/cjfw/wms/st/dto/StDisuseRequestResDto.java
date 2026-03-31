package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.07.02
 * @description : OO 기능을 구현한 Controller Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.02 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부비축재고폐기요청 Response DTO")
public class StDisuseRequestResDto extends CommonProcedureDto{

    @Schema(description = "센터 코드")
    private String dccode;

    @Schema(description = "화주 코드")
    private String storerkey;

    @Schema(description = "조직")
    private String organize;

    /** 창고명 */
    @Schema(description = "창고명")
    private String organizename;

    @Schema(description = "지역")
    private String area;

    @Schema(description = "재고 위치명")
    private String stocktypenm;

    @Schema(description = "재고 위치")
    private String stocktype;

    @Schema(description = "재고 속성")
    private String stockgrade;

    @Schema(description = "재고 속성명")
    private String stockgradename;

    @Schema(description = "존")
    private String zone;

    @Schema(description = "로케이션")
    private String loc;

    @Schema(description = "상품 코드")
    private String sku;

    @Schema(description = "상품명")
    private String skuname;

    @Schema(description = "로트")
    private String lot;

    @Schema(description = "단위")
    private String uom;

    @Schema(description = "수량")
    private BigDecimal qty;

    @Schema(description = "가용 수량")
    private BigDecimal openqty;

    @Schema(description = "할당 수량")
    private BigDecimal qtyallocated;

    @Schema(description = "피킹 수량")
    private BigDecimal qtypicked;

    @Schema(description = "처리 수량")
    private BigDecimal tranqty;

    @Schema(description = "폐기 사유 코드")
    private String reasoncode;

    @Schema(description = "폐기 사유 메세지")
    private String reasonmsg;

    @Schema(description = "폐기유형")
    private String disusetype;

    @Schema(description = "귀책")
    private String imputetype;

    @Schema(description = "물류귀책배부")
    private String processmain;

    @Schema(description = "귀속부서코드")
    private String costcd;

    @Schema(description = "귀속부서명")
    private String costcdname;

    @Schema(description = "거래처 코드")
    private String custkey;

    @Schema(description = "거래처명")
    private String custname;

    @Schema(description = "유통기한 임박 여부")
    private String neardurationyn;

    @Schema(description = "유통기한")
    private String lottable01;

    @Schema(description = "유통기한 경과/기준")
    private String durationTerm;

    @Schema(description = "재고 ID")
    private String stockid;

    @Schema(description = "시리얼 번호")
    private String serialno;

    @Schema(description = "변환된 시리얼번호")
    private String convserialno;

    @Schema(description = "시리얼 레벨")
    private String seriallevel;

    @Schema(description = "시리얼 타입")
    private String serialtype;

    @Schema(description = "공장명")
    private String factoryname;

    @Schema(description = "색상")
    private String colordescr;

    @Schema(description = "원산지")
    private String placeoforigin;

    @Schema(description = "주문유형")
    private String ordertype;

    @Schema(description = "유통기한")
    private String duration;

    @Schema(description = "유통기한 타입")
    private String durationtype;

    @Schema(description = "도축일자")
    private String butcherydt;

    @Schema(description = "계약업체 코드")
    private String contractcompany;

    @Schema(description = "계약업체명")
    private String contractcompanyname;

    @Schema(description = "유통기한 시작일")
    private String fromvaliddt;

    @Schema(description = "유통기한 종료일")
    private String tovaliddt;

    @Schema(description = "계약유형")
    private String contracttype;

    /** 계약구분명 */
    @Schema(description = "계약구분명")
    private String contracttypename;

    @Schema(description = "바코드")
    private String barcode;

    @Schema(description = "평균중량")
    private BigDecimal avgweight;

    @Schema(description = "환산박스")
    private BigDecimal calbox;

    @Schema(description = "실박스예정")
    private BigDecimal realorderbox;

    @Schema(description = "실박스확정")
    private BigDecimal realcfmbox;

    @Schema(description = "작업박스")
    private BigDecimal tranbox;

    @Schema(description = "박스처리유무")
    private String boxflag;

    @Schema(description = "박스당 수량")
    private BigDecimal qtyperbox;

    /** 처리유형 */
    @Schema(description = "처리유형")
    private String processtype;

    /** 임시테이블유형 */
    @Schema(description = "임시테이블유형")
    private String temptabletype;

    /** 문서일자 */
    @Schema(description = "문서일자")
    private String docdt;

    /** 결재요청일자 */
    @Schema(description = "결재요청일자")
    private String appreqdt;

    /** 인터페이스 전송유형 */
    @Schema(description = "인터페이스 전송유형")
    private String ifsendtype;

    /** 작업프로세스코드 */
    @Schema(description = "작업프로세스코드")
    private String workprocesscode;

    /** OMS 플래그 */
    @Schema(description = "OMS 플래그")
    private String omsflag;

    /** 재고이동유형 */
    @Schema(description = "재고이동유형")
    private String stocktranstype;

    /** 외부출고 거래처 */
    @Schema(description = "외부출고 거래처")
    private String wdcust;

    /** 외부출고 방법 */
    @Schema(description = "외부출고 방법")
    private String wdmethod;

    /** 외부출고 비고 */
    @Schema(description = "외부출고 비고")
    private String wdmemo;

    /** 처리 결과 */
    @Schema(description = "처리 결과")
    private String processflag;

    /** 처리메시지 */
    @Schema(description = "처리메시지")
    private String processmsg;

	}