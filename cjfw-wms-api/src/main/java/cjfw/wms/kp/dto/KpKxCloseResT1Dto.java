package cjfw.wms.kp.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net)
 * @date : 2025.09.24
 * @description : KP KX Close 응답 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "KP KX Close 응답")
public class KpKxCloseResT1Dto extends CommonDto {
    /** 문서구분 */
    @Schema(description = "문서구분", example = "STO01")
    private String doctype;

    /** 센터코드 */
    @Schema(description = "센터코드", example = "1000")
    private String dccode;

    /** 납품일자 */
    @Schema(description = "납품일자", example = "202509")
    private String deliveryDate;

    /** 문서수 */
    @Schema(description = "문서수")
    private Integer docCnt;

    /** 라인수 */
    @Schema(description = "라인수")
    private Integer lineCnt;

    /** 사용라인수 */
    @Schema(description = "사용라인수")
    private Integer useLineCnt;

    /** 삭제라인수 */
    @Schema(description = "삭제라인수")
    private Integer delLineCnt;

    /** 확정라인수 */
    @Schema(description = "확정라인수")
    private Integer confirmLineCnt;

    /** 계획중라인수 */
    @Schema(description = "계획중라인수")
    private Integer inplanLineCnt;

    /** 확정율 */
    @Schema(description = "확정율")
    private String tRate;

    /** 거래유형 */
    @Schema(description = "거래유형", example = "수신")
    private String tranType;

    /** 기준테이블 */
    @Schema(description = "기준테이블", example = "IF_DM_DOCUMENT_D")
    private String baseTable;

    /** 인터페이스ID */
    @Schema(description = "인터페이스ID", example = "CJKX0010")
    private String ifId;

    /** 성공수 */
    @Schema(description = "성공수")
    private Integer sucCnt;

    /** 실패수 */
    @Schema(description = "실패수")
    private Integer nonCnt;

    /** 오류수 */
    @Schema(description = "오류수")
    private Integer errCnt;
}