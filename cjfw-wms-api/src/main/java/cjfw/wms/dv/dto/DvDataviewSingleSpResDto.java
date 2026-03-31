package cjfw.wms.dv.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net)
 * @date : 2025.06.09
 * @description : 자동창고처리현황 Response Dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.09 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@Schema(description = "자동창고처리현황 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DvDataviewSingleSpResDto {

    /** 데이터번호 */
    @Schema(description = "데이터번호")
    private Long serialkey;

    /** 자동창고고유번호 */
    @Schema(description = "자동창고고유번호")
    private Long asrsSn;

    /** 입출고처리유형 */
    @Schema(description = "입출고처리유형")
    private String doctype;

    /** 상태 */
    @Schema(description = "상태")
    private String status;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 설명 */
    @Schema(description = "설명")
    private String skuname;

    /** FROMLOC */
    @Schema(description = "FROMLOC")
    private String fromloc;

    /** TOLOC */
    @Schema(description = "TOLOC")
    private String toloc;

    /** 트랜잭션ID */
    @Schema(description = "트랜잭션ID")
    private String stockid;

    /** 박스변환수량 */
    @Schema(description = "박스변환수량")
    private BigDecimal caseqty;

    /** 개변환수량 */
    @Schema(description = "개변환수량")
    private BigDecimal eaqty;

    /** 처리수량 */
    @Schema(description = "처리수량")
    private BigDecimal totqty;

    /** 처리소스키 */
    @Schema(description = "처리소스키")
    private String sourcekey;

    /** 박스입수 */
    @Schema(description = "박스입수")
    private Long qtyperbox;

    /** 팩키 */
    @Schema(description = "팩키")
    private String packkey;

    /** 자동창고사용번호 */
    @Schema(description = "자동창고사용번호")
    private String asrsno;

    /** 전송파일명 */
    @Schema(description = "전송파일명")
    private String ifSendFile;

    /** 수신파일명 */
    @Schema(description = "수신파일명")
    private String ifReceiveFile;

    /** 최초등록시간 */
    @Schema(description = "최초등록시간")
    private String adddate;

    /** 최종변경시간 */
    @Schema(description = "최종변경시간")
    private String editdate;

    /** 최초등록자 */
    @Schema(description = "최초등록자")
    private String addwho;

    /** 최종변경자 */
    @Schema(description = "최종변경자")
    private String editwho;

    /** 고객사 */
    @Schema(description = "고객사")
    private String storerkey;


}
