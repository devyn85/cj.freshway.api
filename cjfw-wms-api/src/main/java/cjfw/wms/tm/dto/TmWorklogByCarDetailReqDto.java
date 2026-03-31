package cjfw.wms.tm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.06.24
 * @description : 배차작업로그(차량별) 요청 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.24 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "배차작업로그(거래처별) 요청 DTO")
public class TmWorklogByCarDetailReqDto extends CommonDto {
    /** 센터코드 */
    @Schema(description = "센터코드", required = true, example = "D01")
    private String dccode;

    /** 배송일자 */
    @Schema(description = "배송일자", required = false, example = "2025-06-24")
    private String deliverydt;

    /** 차량번호 */
    @Schema(description = "차량번호", required = false, example = "123가4567")
    private String carno;

    /** 거래처코드 */
    @Schema(description = "거래처코드", required = false, example = "CUST001")
    private String custCode;

    /** 화주사코드 */
    @Schema(description = "화주사코드", required = false, example = "OWNER01")
    private String storerkey;

    /** 권역그룹 */
    @Schema(description = "권역그룹", required = false, example = "서울권역")
    private String districtgroup;

    /** 배송그룹 */
    @Schema(description = "배송그룹", required = false, example = "POP_A")
    private String deliverygroup;

    /** 우선순위 */
    @Schema(description = "우선순위", required = false, example = "1")
    private String priority;
}
