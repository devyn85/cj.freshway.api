package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.05.29
 * @description : 저장위치정보 목록 조회 요청 dto
 * @issues : <pre>
 * -----------------------------------------------------------
 * DATE AUTHOR MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.29 KwonJungYun (jungyun8667@cj.net) 생성
 */
@Data
@Schema(description = "저장위치정보 조회 결과")
public class MsExDCStatusListResDto {
    /** 센터 코드 */
    @Schema(description = "센터 코드", example = "A111")
    private String dccode;

    /** 플랜트 코드 */
    @Schema(description = "플랜트 코드", example = "A111")
    private String plant;

    /** 저장위치 코드 */
    @Schema(description = "저장위치 코드", example = "A111")
    private String storageloc;

    /** 저장위치명 */
    @Schema(description = "저장위치명", example = "물류센터")
    private String storagelocname;

    /** 권역명 (예: 수도권, 영남권 등) */
    @Schema(description = "권역", example = "수도권")
    private String district;

    /** 지역명 (예: 서울, 부산 등) */
    @Schema(description = "지역", example = "서울")
    private String area;

    /** 계약 여부 (Y/N) */
    @Schema(description = "계약여부", example = "Y")
    private String contractyn;

    /** 상품 분류 */
    @Schema(description = "상품분류", example = "축산물, 식품")
    private String sku;

    /** 계약 종료일 */
    @Schema(description = "계약종료일", example = "20251231")
    private String contractenddate;

    /** 계약 잔여일 수 (오늘 포함) */
    @Schema(description = "계약 잔여일 수", example = "215")
    private Long remainingdays;

    /** 재고금액 */
    @Schema(description = "재고금액", example = "12500000")
    private Long stockamount;
    /** 최초등록시간 */
    @Schema(description = "최초등록시간", example = "2023-01-01 10:00:00")
    private String adddate;

    /** 최종변경시간 */
    @Schema(description = "최종변경시간", example = "2024-05-20 15:30:00")
    private String editdate;

    /** 최초등록자 */
    @Schema(description = "최초등록자", example = "ADMIN")
    private String addwho;

    /** 최종변경자 */
    @Schema(description = "최종변경자", example = "USER123")
    private String editwho;

    /** serialkey */
    @Schema(description = "serialkey", example = "1111")
    private String serialkey;

    /** 창고 */
	@Schema(description = "창고")
	private String organize;

	/** 계약시작일 */
	@Schema(description = "계약시작일")
	private String startdate;

	/** 계약종료일 */
	@Schema(description = "계약종료일")
	private String enddate;
}
