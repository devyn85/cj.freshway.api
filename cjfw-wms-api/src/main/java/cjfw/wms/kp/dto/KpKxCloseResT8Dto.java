package cjfw.wms.kp.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : sss (kduimux@cj.net)
 * @date : 2025.09.24
 * @description : 실적 미수신건 응답 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "실적 미수신건 응답")
public class KpKxCloseResT8Dto extends CommonDto {
    /** 문서유형 */
    @Schema(description = "문서유형")
    private String doctype;

    /** 조직코드 */
    @Schema(description = "조직코드")
    private String organize;

    /** 납품일자 */
    @Schema(description = "납품일자")
    private String deliverydate;

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;

    /** 문서번호 */
    @Schema(description = "문서번호")
    private String docno;

    /** 문서라인 */
    @Schema(description = "문서라인")
    private String docline;

    /** SKU */
    @Schema(description = "SKU")
    private String sku;

    /** 미확정수량 */
    @Schema(description = "미확정수량")
    private String openqty;

    /** 확정수량 */
    @Schema(description = "확정수량")
    private String confirmqty;

    /** IF확정여부 */
    @Schema(description = "IF확정여부")
    private String ifCfmYn;

    /** 기타텍스트 */
    @Schema(description = "기타텍스트")
    private String etcTxt;

    /** KX수락여부 */
    @Schema(description = "KX수락여부")
    private String kxAcceptYn;

    /** 등록일시 */
    @Schema(description = "등록일시")
    private String adddate;

    /** 수신자이메일 */
    @Schema(description = "수신자이메일")
    private String rcvrEmail;

    /** 수신자명 */
    @Schema(description = "수신자명")
    private String rcvrNm;
}