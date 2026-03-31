package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.06.24
 * @description : 배차작업로그(거래처별) 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.24 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "배차작업로그(거래처별) 응답 DTO")
public class TmWorklogByCustomerDetailResDto {

    /** 시리얼키 */
    @Schema(description = "시리얼키", example = "7402")
    private String serialkey;

    /** 문서번호 */
    @Schema(description = "문서번호", example = "6000")
    private String docno;

    /** 거래처코드 */
    @Schema(description = "거래처코드", example = "2041")
    private String custkey;

    /** 슬립번호 */
    @Schema(description = "슬립번호", example = "1111")
    private String slipno;

    /** 배송번호 */
    @Schema(description = "배송번호", example = "0000")
    private String deliveryno;

    /** 선배차 차량번호 */
    @Schema(description = "선배차 차량번호", example = "2041광역차량")
    private String precarno;

    /** 선배차 POP */
    @Schema(description = "선배차 POP", example = "2041")
    private String predeliverygroup;

    /** 선배차 우선순위 */
    @Schema(description = "선배차 우선순위", example = "1")
    private String prepriority;

    /** 권역그룹 */
    @Schema(description = "권역그룹", example = "STO")
    private String districtgroup;

    /** 배송그룹 */
    @Schema(description = "배송그룹", example = "2041")
    private String deliverygroup;

    /** 우선순위 */
    @Schema(description = "우선순위", example = "1")
    private String priority;

    /** 차량번호 */
    @Schema(description = "차량번호", example = "2041광역차량")
    private String carno;

    /** 변경로그 */
    @Schema(description = "변경로그", example = "상태변경")
    private String modifylog;

    /** 경유지 */
    @Schema(description = "경유지", example = "경유지")
    private String route;

    /** 주문수량 */
    @Schema(description = "주문수량", example = "4")
    private String orderqty;

    /** 체적 */
    @Schema(description = "체적", example = "4")
    private String cube;

    /** 중량 */
    @Schema(description = "중량", example = "4")
    private String weight;

    /** 상태 */
    @Schema(description = "상태", example = "가배차처리")
    private String status;

    /** 삭제여부 */
    @Schema(description = "삭제여부", example = "삭제")
    private String delyn;

    /** 로그일시 */
    @Schema(description = "로그일시", example = "2016-02-01")
    private String logdate;

    /** 작업자 */
    @Schema(description = "작업자", example = "작업자")
    private String logwho;
}
