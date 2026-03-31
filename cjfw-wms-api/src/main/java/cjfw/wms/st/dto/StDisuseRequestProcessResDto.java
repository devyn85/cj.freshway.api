package cjfw.wms.st.dto;

import java.math.BigDecimal;
import java.util.List;

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
@Schema(description = "외부비축재고폐기요청처리 Response DTO")
public class StDisuseRequestProcessResDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";


	/** 센터 코드 */
	@Schema(description = "센터 코드")
	private String dccode;

	/** 화주 코드 */
	@Schema(description = "화주 코드")
	private String storerkey;

	/** 조직 */
	@Schema(description = "조직")
	private String organize;

	/** 창고명 */
    @Schema(description = "창고명")
    private String organizename;

	/** 지역 */
	@Schema(description = "지역")
	private String area;

	/** 문서유형 */
	@Schema(description = "문서유형")
	private String doctype;

	/** 문서일자 */
	@Schema(description = "문서일자")
	private String docdt;

	/** 문서번호 */
	@Schema(description = "문서번호")
	private String docno;

	/** 문서라인 */
	@Schema(description = "문서라인")
	private String docline;

	/** 전표일자 */
	@Schema(description = "전표일자")
	private String slipdt;

	/** 전표번호 */
	@Schema(description = "전표번호")
	private String slipno;

	/** 전표라인 */
	@Schema(description = "전표라인")
	private String slipline;

	/** 주문유형 */
	@Schema(description = "주문유형")
	private String ordertype;

	/** 입출고유형 */
	@Schema(description = "입출고유형")
	private String iotype;

	/** 전표유형 */
	@Schema(description = "전표유형")
	private String sliptype;

	/** 재고위치명 */
	@Schema(description = "재고위치명")
	private String stocktypenm;

	/** 재고위치 */
	@Schema(description = "재고위치")
	private String stocktype;

	/** 재고속성 */
	@Schema(description = "재고속성")
	private String stockgrade;

	/** 재고속성명 */
	@Schema(description = "재고속성명")
	private String stockgradename;

	/** 존 */
	@Schema(description = "존")
	private String zone;

	/** 로케이션 */
	@Schema(description = "로케이션")
	private String loc;

	/** 상품코드 */
	@Schema(description = "상품코드")
	private String sku;

	/** 상품명 */
	@Schema(description = "상품명")
	private String skuname;

	/** 로트 */
	@Schema(description = "로트")
	private String lot;

	/** 단위 */
	@Schema(description = "단위")
	private String uom;

	/** 수량 */
	@Schema(description = "수량")
	private BigDecimal tranqty;

	/** 폐기유형 */
	@Schema(description = "폐기유형")
	private String disusetype;

	/** 폐기유형명 */
	@Schema(description = "폐기유형명")
	private String disusetypename;

	/** 발생사유 */
	@Schema(description = "발생사유")
	private String reasoncode;

	/** 발생사유명 */
	@Schema(description = "발생사유명")
	private String reasoncodename;

	/** 귀속부서 */
	@Schema(description = "귀속부서")
	private String imputetype;

	/** 귀속부서명 */
	@Schema(description = "귀속부서명")
	private String imputetypename;

	/** 공정코드 */
	@Schema(description = "공정코드")
	private String processmain;

	/** 원가코드 */
	@Schema(description = "원가코드")
	private String costcd;

	/** 원가코드명 */
	@Schema(description = "원가코드명")
	private String costcdname;

	/** 거래처 코드 */
	@Schema(description = "거래처 코드")
	private String custkey;

	/** 거래처 명 */
	@Schema(description = "거래처 명")
	private String custname;

	/** 유통기한 임박 여부 */
	@Schema(description = "유통기한 임박 여부")
	private String neardurationyn;

	/** 유통기한 */
	@Schema(description = "유통기한")
	private String lottable01;

	/** 유통기한 경과/기준 */
	@Schema(description = "유통기한 경과/기준")
	private String durationterm;

	/** 재고ID */
	@Schema(description = "재고ID")
	private String stockid;

	/** 시리얼번호 */
	@Schema(description = "시리얼번호")
	private String serialno;

	/** 변환시리얼번호 */
	@Schema(description = "변환시리얼번호")
	private String convserialno;

	/** 시리얼레벨 */
	@Schema(description = "시리얼레벨")
	private String seriallevel;

	/** 시리얼타입 */
	@Schema(description = "시리얼타입")
	private String serialtype;

	/** 공장명 */
	@Schema(description = "공장명")
	private String factoryname;

	/** 색상 */
	@Schema(description = "색상")
	private String colordescr;

	/** 원산지 */
	@Schema(description = "원산지")
	private String placeoforigin;

	/** 유통기한(스펙상) */
	@Schema(description = "유통기한")
	private String duration;

	/** 유통기한타입 */
	@Schema(description = "유통기한타입")
	private String durationtype;

	/** 도축일자 */
	@Schema(description = "도축일자")
	private String butcherydt;

	/** 계약업체코드 */
	@Schema(description = "계약업체코드")
	private String contractcompany;

	/** 계약업체명 */
	@Schema(description = "계약업체명")
	private String contractcompanyname;

	/** 유통기한 시작일 */
	@Schema(description = "유통기한 시작일")
	private String fromvaliddt;

	/** 유통기한 종료일 */
	@Schema(description = "유통기한 종료일")
	private String tovaliddt;

	/** 계약유형 */
	@Schema(description = "계약유형")
	private String contracttype;
    /** 계약구분명 */
    @Schema(description = "계약구분명")
    private String contracttypename;

	/** 바코드 */
	@Schema(description = "바코드")
	private String barcode;

	/** 결재상태 */
	@Schema(description = "결재상태")
	private String approvalstatus;

	/** 결재상태명 */
	@Schema(description = "결재상태명")
	private String approvalstatusname;

	/** 결재요청번호 */
	@Schema(description = "결재요청번호")
	private String approvalreqno;

	/** 결재번호 */
	@Schema(description = "결재번호")
	private String approvalno;

	/** 결재일자 */
	@Schema(description = "결재일자")
	private String approvaldate;

	/** 체크플래그 */
	@Schema(description = "체크플래그")
	private String chkflag;

	/** 상태명 */
	@Schema(description = "상태명")
	private String statusaj;

	/** 평균중량 */
	@Schema(description = "평균중량")
	private BigDecimal avgweight;

	/** 환산박스수 */
	@Schema(description = "환산박스수")
	private BigDecimal calbox;

	/** 실박스예정 */
	@Schema(description = "실박스예정")
	private String realorderbox;

	/** 실박스확정 */
	@Schema(description = "실박스확정")
	private String realcfmbox;

	/** 처리박스수 */
	@Schema(description = "처리박스수")
	private BigDecimal tranbox;

	/** 박스처리유무 */
	@Schema(description = "박스처리유무")
	private String boxflag;

	/** 참조값8 */
	@Schema(description = "참조값8")
	private String reference08;

	/** 참조값9 */
	@Schema(description = "참조값9")
	private String reference09;

	/** 참조값10 */
	@Schema(description = "참조값10")
	private String reference10;

	/** 등록자명 */
	@Schema(description = "등록자명")
	private String addwho;
	
	/** 기준일자 From */
	@Schema(description = "기준일자 From")
	private String basedtFrom;

	/** 기준일자 To */
	@Schema(description = "기준일자 To")
	private String basedtTo;

	/** 승인유형 */
	@Schema(description = "승인유형")
	private String approvalType;

	/** 고객사코드 */
	@Schema(description = "고객사코드")
	private String gStorerkey;

	/** 사용자ID */
	@Schema(description = "사용자ID")
	private String gUserId;

	/** 센터코드 */
	@Schema(description = "센터코드")
	private String fixdccode;

	/** 구역 */
	@Schema(description = "구역")
	private String gArea;

	/** 창고 다중검색 */
	@Schema(description = "창고 다중검색")
	private List<String> gOrganizeMulti;

	/** 창고 다중검색 */
	@Schema(description = "창고 다중검색")
	private List<String> organizeMulti;

	/** 상품 다중검색 */
	@Schema(description = "상품 다중검색")
	private List<List<String>> skuMulti;

}