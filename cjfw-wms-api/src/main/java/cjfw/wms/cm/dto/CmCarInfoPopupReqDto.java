package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.06.23
 * @description : 차량 정보 조회(단건) 요청 dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.23 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "차량 정보 조회(단건) 요청 dto")
public class CmCarInfoPopupReqDto extends CommonDto {
    /** 차량 일련번호 */
    @Schema(description = "차량 일련번호", example = "CAR000001")
    private String serialkey;

    /** 차량번호 */
    @Schema(description = "차량번호", example = "서울12가1234")
    private String carno;
}
