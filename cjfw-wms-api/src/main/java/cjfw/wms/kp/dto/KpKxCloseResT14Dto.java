package cjfw.wms.kp.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : sss (kduimux@cj.net)
 * @date : 2025.10.15
 * @description : IF 송수신 응답 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "IF 송수신 응답")
public class KpKxCloseResT14Dto extends CommonDto {

    /** IF ID */
    @Schema(description = "IF ID")
    private String ifId;

    /** IF명 */
    @Schema(description = "IF명")
    private String ifName;

    /** 송신완료 */
    @Schema(description = "송신완료")
    private String statusE;

    /** 송신대기 */
    @Schema(description = "송신대기")
    private String statusP;

    /** 미송신 */
    @Schema(description = "미송신")
    private String statusN;

    /** 송신실패 */
    @Schema(description = "송신실패")
    private String statusZ;

    /** 수신완료 */
    @Schema(description = "수신완료")
    private String statusK;

    /** 수신대기 */
    @Schema(description = "수신대기")
    private String statusY;

    /** 수신실패 */
    @Schema(description = "수신실패")
    private String statusS;
    
    /** 체크여부 */
    @Schema(description = "체크여부")
    private String checkyn;

    /** IF구분 */
    @Schema(description = "IF구분")
    private String ifType;

    /** 시리얼키 */
    @Schema(description = "시리얼키")
    private BigDecimal serialkey;

    /** IF 목적지 */
    @Schema(description = "IF 목적지")
    private String ifDestination;

    /** IF 상태 */
    @Schema(description = "IF 상태")
    private String ifFlag;

    /** IF 메모 */
    @Schema(description = "IF 메모")
    private String ifMemo;

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;

    /** 조직코드 */
    @Schema(description = "조직코드")
    private String organize;

    /** 문서번호 */
    @Schema(description = "문서번호")
    private String docno;

    /** 문서라인 */
    @Schema(description = "문서라인")
    private String docline;

    /** 전표번호 */
    @Schema(description = "전표번호")
    private String slipno;

    /** 전표라인 */
    @Schema(description = "전표라인")
    private String slipline;

    /** SKU */
    @Schema(description = "SKU")
    private String sku;

    /** 등록일시 */
    @Schema(description = "등록일시")
    private String adddate;

    
}
