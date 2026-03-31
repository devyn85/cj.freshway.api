package cjfw.wms.dp.dto;

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
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.10
 * @description : 일배입고검수출력 Request DTO Class
 * @issues :
 *
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.10 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "일배입고검수출력 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpInspectDailyPrintReqDto extends CommonProcedureDto {
    /* SPID */
    @Schema(description = "SPID")
    private String spid;

    /**
     * 문서번호
     */
    @Schema(description = "문서번호")
    private String docno;

    /**
     * 문서번호
     */
    @MultiSearch
    @Schema(description = "문서번호", nullable = false, example = "")
    private List<String> docnoMulti;

    /**
     * 출발거래처코드
     */
    @Schema(description = "출발거래처코드")
    private String fromcustkey;

    /**
     * 보관유형
     */
    @Schema(description = "보관유형")
    private String storagetype;

    /**
     * 채널
     */
    @Schema(description = "채널")
    private String channel;

    /**
     * 상태
     */
    @Schema(description = "상태")
    private String status;

    /**
     * 문서일자(From)
     */
    @Schema(description = "문서일자(From)")
    private String docdtFrom;

    /**
     * 문서일자(To)
     */
    @Schema(description = "문서일자(To)")
    private String docdtTo;

    /**
     * 입고예정일(From)
     */
    @Schema(description = "입고예정일(From)")
    private String slipdtFrom;

    /**
     * 입고예정일(To)
     */
    @Schema(description = "입고예정일(To)")
    private String slipdtTo;

    /**
     * 상품코드
     */
    @Schema(description = "상품코드")
    private String sku;

    /**
     * 주문유형
     */
    @Schema(description = "주문유형")
    private String ordertype;

    /**
     * 배송그룹
     */
    @Schema(description = "배송그룹")
    private String gMultiCourier;

    /**
     * 조직(다중선택)
     */
    @Schema(description = "조직(다중선택)")
    private String gMultiOrganize;

    /**
     * 센터코드
     */
    @Schema(description = "센터코드")
    private String dccode;

    /**
     * 화주코드
     */
    @Schema(description = "화주코드")
    private String storerkey;

    /**
     * 조직
     */
    @Schema(description = "조직")
    private String organize;

    /**
     * 입고예정일
     */
    @Schema(description = "입고예정일")
    private String slipdt;

    /**
     * 입고예정번호
     */
    @Schema(description = "입고예정번호")
    private String slipno;

    /**
     * 프로세스타입
     */
    @Schema(description = "프로세스타입")
    private String processtype;

    /**
     * 출력리스트
     */
    @Schema(description = "출력리스트")
    private String printlist;

    /**
     * 수정여부
     */
    @Schema(description = "수정여부")
    private String modifyYn;

    /**
     * 출력유형
     */
    @Schema(description = "출력유형")
    private String printType;

    /**
     * 출력리스트
     */
    @Schema(description = "출력리스트")
    private List<DpInspectDailyPrintResDto> saveList;
}
