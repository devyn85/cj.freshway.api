package cjfw.wms.st.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부비축재고폐기요청 조회 Request DTO")
public class StDisuseRequestReqDto extends CommonProcedureDto {

    /** 고정 센터 코드 */
    @Schema(description = "고정 센터 코드")
    private String fixDccode;

    /** 재고 위치 유형 */
    @Schema(description = "재고 위치 유형")
    private String stocktype;

    /** 재고 속성 */

    @Schema(description = "재고 속성")
    private String stockgrade;

    /** 저장조건 */
    @Schema(description = "저장조건")
    private String storagetype;

    /** LOT테이블01 사용 여부 */
    @Schema(description = "LOT테이블01 사용 여부")
    private String lottable01yn;

    /** 존(ZONE) */
    @Schema(description = "존(ZONE)")
    private String zone;

    /** 상품코드 (다중 가능, 콤마구분) */
    @Schema(description = "상품코드 (다중 가능, 콤마구분)")
    private String sku;

    /** 조직 다중 선택 (콤마구분) */
    @Schema(description = "조직 다중 선택 (콤마구분)")
    private String organize;

    /** 시리얼 사용 여부 */
    @Schema(description = "시리얼 사용 여부")
    private String serialnoyn;

    /** 시리얼번호 */
    @Schema(description = "시리얼번호")
    private String serialno;

    /** B/L 번호 */
    @Schema(description = "B/L 번호")
    private String blno;
    
    /** B/L번호-다중검색 */
    @MultiSearch
    @Schema(description = "B/L번호-다중검색",  example = "")
    private List<String> blnoMulti;

    /** 계약 업체 코드 */
    @Schema(description = "계약 업체 코드")
    private String contractcompany;

    /** 결재진행상태 */
    @Schema(description = "결재진행상태")
    private String apprstatus;

    /** 결재요청 시작일자 */
    @Schema(description = "결재요청 시작일자")
    private String fromapprreqdt;

    /** 결재요청 종료일자 */
    @Schema(description = "결재요청 종료일자")
    private String toapprreqdt;

    /** 전표일자 시작일 */
    @Schema(description = "전표일자 시작일")
    private String fromslipdt;

    /** 전표일자 종료일 */
    @Schema(description = "전표일자 종료일")
    private String toslipdt;

    /** 결재기준 종료일자 */
    @Schema(description = "기준 종료일자")
    private String tobasedt;

    /** 결재기준 시작일 */
    @Schema(description = "기준 시작일")
    private String frombasedt;


    /** 결재유형  */
    @Schema(description = "결재유형")
    private String approvaltype;

    /** 그리드 폐기 요청 저장 목록*/
    @Schema(description = "그리드 저장용 파라미터")
    private List<StDisuseRequestResDto> saveRequestList;
    /** 그리드 폐기 처리 저장 목록*/
    @Schema(description = "그리드 저장용 파라미터")
    private List<StDisuseRequestProcessResDto> saveProcessList;
    /** 그리드 폐기 결재 저장 목록*/
    @Schema(description = "그리드 저장용 파라미터")
    private List<StDisuseRequestApprovalResDto> saveApprovalList;

    /** 문서일자 */
    @Schema(description = "문서일자")
    private String docdt;

    /** 결재요청일자 */
    @Schema(description = "결재요청일자")
    private String apprreqdt;

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


}