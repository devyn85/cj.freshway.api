package cjfw.wms.wd.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.05.21 
 * @description : 출고진행현황 팝업 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.21 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "출고진행현황 팝업 결과")
public class WdHomeDeliveryInvoicePopupDeliveryStatusResDto {
	/**
     * 지사명 (처리점소명과 동일)
     */
    @Schema(description = "지사명", example = "강남지사")
    private String dealtBranNm;

    /**
     * 스캔 구분 (접수 구분)
     */
    @Schema(description = "스캔 구분 (접수 구분)", example = "C")
    private String rcptDv;

    /**
     * 스캔 일자 (YYYYMMDD 형식)
     */
    @Schema(description = "스캔 일자 (YYYYMMDD)", example = "20250521")
    private String scanYmd;

    /**
     * 스캔 시간 (HH24MISS 형식)
     */
    @Schema(description = "스캔 시간 (HH24MISS)", example = "143000")
    private String scanHour;

    /**
     * 인수자명 (처리 사원명)
     */
    @Schema(description = "인수자명 (처리 사원명)", example = "홍길동")
    private String dealtEmpNm;

    /**
     * 기타 상세 사유
     */
    @Schema(description = "기타 상세 사유", example = "고객 부재로 인한 반송")
    private String detailRsn;

    /**
     * 등록 일자 (YYYYMMDD 형식)
     */
    @Schema(description = "등록 일자 (YYYYMMDD)", example = "20250521")
    private String addYmd;

    /**
     * 등록 시간 (HH24MISS 형식)
     */
    @Schema(description = "등록 시간 (HH24MISS)", example = "143500")
    private String addHour;

    /**
     * 스캔 장비 정보 (고정값: '스캔장비')
     */
    @Schema(description = "스캔 장비", example = "스캔장비")
    private String scanDvc;

    /**
     * 콘솔 스캔 정보 (고정값: '콘솔스캔')
     */
    @Schema(description = "콘솔 스캔", example = "콘솔스캔")
    private String cslDvc;
}
