package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : Auto Generated
 * @date : 2025.01.20
 * @description : TM 배송 클레임 조회 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.20 Auto Generated 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "TM 배송 클레임 조회 응답 DTO")
public class TmClaimListResDto {

    /** 전표일자 */
    @Schema(description = "전표일자", example = "20250120")
    private String slipdt;

    /** 물류센터 */
    @Schema(description = "물류센터", example = "2600")
    private String dccode;

    /** 거래처코드 */
    @Schema(description = "거래처코드", example = "557401044")
    private String custkey;

    /** 클레임(소)유형 */
    @Schema(description = "클레임(소)유형", example = "01")
    private String claimdtlids;

    /** 클레임(소)유형명 */
    @Schema(description = "클레임(소)유형명", example = "불량")
    private String specdescr;

    /** 반품번호 */
    @Schema(description = "반품번호", example = "RT20250120001")
    private String rtDocno;

    /** 반품품번 */
    @Schema(description = "반품품번", example = "001")
    private String rtDocline;

    /** 출고번호 */
    @Schema(description = "출고번호", example = "WD20250120001")
    private String wdDocno;

    /** 출고품번 */
    @Schema(description = "출고품번", example = "001")
    private String wdDocline;

    /** 상품코드 */
    @Schema(description = "상품코드", example = "SKU001")
    private String sku;

    /** 상품수량 */
    @Schema(description = "상품수량", example = "10")
    private String claimqty;

    /** 상품단위 */
    @Schema(description = "상품단위", example = "EA")
    private String claimuom;

    /** 메시지 */
    @Schema(description = "메시지", example = "배송 중 파손")
    private String memo;

    /** 작성자 */
    @Schema(description = "작성자", example = "admin")
    private String writer;

    /** 작성일자 */
    @Schema(description = "작성일자", example = "20250120")
    private String writedate;

    /** 작성시간 */
    @Schema(description = "작성시간", example = "143000")
    private String writetime;
}
