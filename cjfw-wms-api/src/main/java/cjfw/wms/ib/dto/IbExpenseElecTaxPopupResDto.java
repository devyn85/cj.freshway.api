package cjfw.wms.ib.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.08.08
 * @description : 비용기표 매입세금계산서 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.08   KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "비용기표 매입세금계산서 조회 결과") 
public class IbExpenseElecTaxPopupResDto extends CommonProcedureDto {	
    
    /** 회사코드 */
    @Schema(description = "회사코드", nullable = false, example = "")
    private String bukrs;

    /** 세금계산서 승인번호 */
    @Schema(description = "세금계산서 승인번호", nullable = false, example = "")
    private String issueId;

    /** 세금계산서 일련번호(년월일+회사코드+12) 내부사용 */
    @Schema(description = "세금계산서 일련번호(년월일+회사코드+12) 내부사용", nullable = false, example = "")
    private String invSeq;

    /** 총 공급가 */
    @Schema(description = "총 공급가", nullable = false, example = "")
    private String chargetotal;

    /** 총 세액 */
    @Schema(description = "총 세액", nullable = false, example = "")
    private String taxtotal;

    /** 총액(공급가액+세액) */
    @Schema(description = "총액(공급가액+세액)", nullable = false, example = "")
    private String grandtotal;

    /** 수탁사업자 주소 */
    @Schema(description = "수탁사업자 주소", nullable = false, example = "")
    private String bpAddr;

    /** 수탁사업자 대표자명 */
    @Schema(description = "수탁사업자 대표자명", nullable = false, example = "")
    private String bpRepres;

    /** 수탁사업자 담당부서 */
    @Schema(description = "수탁사업자 담당부서", nullable = false, example = "")
    private String bpDeptname;

    /** 수탁사업자 담당자명 */
    @Schema(description = "수탁사업자 담당자명", nullable = false, example = "")
    private String bpPersname;

    /** 전자세금계산서 발행일시 */
    @Schema(description = "전자세금계산서 발행일시", nullable = false, example = "")
    private String issueDt;

    /** 전자세금계산서 작성일자 */
    @Schema(description = "전자세금계산서 작성일자", nullable = false, example = "")
    private String issueDate;

    /** 변경일 */
    @Schema(description = "변경일", nullable = false, example = "")
    private String aedat;

    /** 역발행여부 */
    @Schema(description = "역발행여부", nullable = false, example = "")
    private String invSign;
    
    /** 이메일주소 */
    @Schema(description = "이메일주소", nullable = false, example = "")
    private String email;

}
