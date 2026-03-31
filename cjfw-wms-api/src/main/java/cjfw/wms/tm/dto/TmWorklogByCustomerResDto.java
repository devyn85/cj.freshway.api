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
public class TmWorklogByCustomerResDto {

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;

    /** 배송일자 */
    @Schema(description = "배송일자")
    private String deliverydt;

    /** 권역그룹 */
    @Schema(description = "권역그룹")
    private String districtgroup;

    /** 배송그룹 */
    @Schema(description = "배송그룹")
    private String deliverygroup;

    /** 우선순위 */
    @Schema(description = "우선순위")
    private String priority;

    /** 차량번호 */
    @Schema(description = "차량번호")
    private String carno;

    /** 전표수 */
    @Schema(description = "전표수")
    private Integer slipcnt;

    /** 적재용적(CUBE) */
    @Schema(description = "적재용적(CUBE)")
    private Double cube;

    /** 중량 */
    @Schema(description = "중량")
    private Double weight;

    /** 상태 */
    @Schema(description = "상태")
    private String status;

    /** 거래처코드 */
    @Schema(description = "거래처코드")
    private String custkey;

    /** 거래처명 */
    @Schema(description = "거래처명")
    private String custname;

    /** 실착지 거래처코드 */
    @Schema(description = "실착지 거래처코드")
    private String truthcustkey;

    /** 실착지 거래처코드명 */
    @Schema(description = "실착지 거래처코드명")
    private String truthcustkeyname;

    /** 거래처유형 */
    @Schema(description = "거래처유형")
    private String custtype;

    /** 화주사코드 */
    @Schema(description = "화주사코드")
    private String storerkey;
}
