package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net)
 * @date : 2025.08.29
 * @description : 센터 권역 주문 그룹 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
public class MsCenterDistrictOrdGrpResDto extends CommonDto {

    @Schema(description = "테이블 키")
    private String serialkey;

    @Schema(description = "행정동코드")
    private String hjdongCd;

    @Schema(description = "센터코드")
    private String dccode;

    @Schema(description = "센터명")
    private String dcname;

    @Schema(description = "센터코드")
    private String pr1Dccode;

    @Schema(description = "센터명")
    private String pr1Dcname;

    @Schema(description = "센터코드")
    private String pr2Dccode;

    @Schema(description = "센터명")
    private String pr2Dcname;

    @Schema(description = "행정동명")
    private String hjdongNm;

    @Schema(description = "주문그룹")
    private String ordGrp;

    @Schema(description = "주문그룹명")
    private String ordGrpNm;

    @Schema(description = "FW/FO")
    private String dcgroup;

    @Schema(description = "유효시작일")
    private String fromDate;

    @Schema(description = "유효종료일")
    private String toDate;

    @Schema(description = "삭제여부")
    private String delYn;

    @Schema(description = "배송물류센터")
    private String deliveryDccode;

    @Schema(description = "주문그룹 일련키")
    private String ordgrpSerialkey;

    @Hidden
    private String editDate;
    @Hidden
    private String editWho;

}
